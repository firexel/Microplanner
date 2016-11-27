package com.seraph.microplanner.units

/**
 * Created by Alex on 27.11.2016.
 */
class Duration internal constructor(val millis: Long) {
    val seconds: Float
        get() = millis / 1000f

    val minutes: Float
        get() = seconds / 60f

    val hours: Float
        get() = minutes / 60f

    operator fun plus(other: Duration) = Duration(millis + other.millis)
}

fun Long.milliseconds(): Duration = Duration(this)
fun Int.milliseconds(): Duration = this.toLong().milliseconds()
fun Int.seconds(): Duration = (this * 1000).milliseconds()
fun Int.minutes(): Duration = (this * 60).seconds()
fun Int.hours(): Duration = (this * 60).minutes()