package com.seraph.microplanner

/**
 * Created by Alex on 27.11.2016.
 */

import android.content.Context
import java.util.*

class Locator {
    private val locatedMap = HashMap<Class<*>, Locatable<*>>()

    fun <T> locate(clazz: Class<T>): T {
        val locatable = locatedMap[clazz] ?: throw IllegalStateException()
        try {
            return clazz.cast(locatable.locate())
        } catch (e: Exception) {
            throw IllegalStateException(e)
        }
    }

    fun <T, I : T> register(clazz: Class<T>, impl: I) {
        locatedMap.put(clazz, EagerLocatable(impl))
    }

    fun <T, I : T> registerLazy(clazz: Class<T>, lazyInitializer: LazyInitialisable<I>) {
        locatedMap.put(clazz, LazyLocatable(lazyInitializer))
    }

    interface Host {
        val locator: Locator
    }

    internal interface Locatable<T> {
        @Throws(Exception::class)
        fun locate(): T
    }

    interface LazyInitialisable<T> {
        @Throws(Exception::class)
        fun initialize(): T
    }

    private class EagerLocatable<T>(val instance: T?) : Locatable<T> {
        override fun locate(): T {
            if (instance != null) {
                return instance
            }
            throw IllegalStateException()
        }
    }

    private class LazyLocatable<T>(val initializable: LazyInitialisable<T>) : Locatable<T> {
        private var instance: T? = null

        @Throws(Exception::class)
        override fun locate(): T {
            synchronized(this) {
                var implInstance = instance
                if (implInstance == null) {
                    implInstance = initializable.initialize()
                    if (implInstance == null) {
                        throw IllegalStateException()
                    }
                }
                instance = implInstance
                return implInstance
            }
        }
    }

    companion object {
        fun from(context: Context): Locator {
            when {
                context is Host -> return context.locator
                context.applicationContext is Host -> return (context.applicationContext as Host).locator
                else -> throw IllegalArgumentException()
            }
        }
    }
}