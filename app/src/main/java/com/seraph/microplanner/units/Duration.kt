package com.seraph.microplanner.units

/**
 * Created by Alex on 27.11.2016.
 */
class Duration internal constructor(millis: Long) {
    val millis: Long = millis

    val seconds: Int
        get() = (millis / 1000).toInt()

    val minutes: Int
        get() = seconds / 60

    val hours: Int
        get() = minutes / 60

    operator fun plus(other: Duration) = Duration(millis + other.millis)
}

fun Long.milliseconds(): Duration = Duration(this)
fun Int.milliseconds(): Duration = this.toLong().milliseconds()
fun Int.seconds(): Duration = (this * 1000).milliseconds()
fun Int.minutes(): Duration = (this * 60).seconds()
fun Int.hours(): Duration = (this * 60).minutes()