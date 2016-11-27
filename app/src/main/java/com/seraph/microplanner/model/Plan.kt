package com.seraph.microplanner.model

import java.util.*

/**
 * Created by Alex on 27.11.2016.
 */
interface Plan {
    val start: Calendar
    val issues: List<Issue>

    fun issueStartTime(issue: Issue): Calendar {
        return issues.takeWhile { it !== issue }.fold(
                start.clone() as Calendar,
                { c, i -> c.apply { add(Calendar.MILLISECOND, i.estimate.millis.toInt()) } }
        )
    }
}