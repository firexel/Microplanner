package com.seraph.microplanner.presenter.mock

import com.seraph.microplanner.model.Issue
import com.seraph.microplanner.model.Plan
import com.seraph.microplanner.model.Tag
import com.seraph.microplanner.presenter.PlanPresenter
import com.seraph.microplanner.units.Duration
import com.seraph.microplanner.units.hours
import com.seraph.microplanner.units.minutes
import java.util.*

/**
 * Created by Alex on 27.11.2016.
 */
class MockPlanPresenter(private val view: PlanPresenter.View) : PlanPresenter {
    init {
        view.showPlan(MockPlan())
    }

    override fun onAddIssue() {
    }

    private data class MockTag(
            override val name: String,
            override val color: Int) : Tag

    private data class MockIssue(
            override val title: String,
            override val estimate: Duration,
            override val tag: Tag) : Issue

    private class MockPlan : Plan {
        override val start: Calendar = Calendar.getInstance().apply {
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        override val issues: List<Issue> = listOf(
                MockIssue("", 1.hours(), MockTag("mail", 0xffa15b1c.toInt())),
                MockIssue("", 45.minutes(), MockTag("launch", 0xffdfae43.toInt())),
                MockIssue("Refactor the component", 3.hours() + 15.minutes(), MockTag("programming", 0xfff2e6c6.toInt())),
                MockIssue("Meeting with management", 1.hours(), MockTag("meeting", 0xff7d8e74.toInt())),
                MockIssue("Refactor another component", 2.hours(), MockTag("programming", 0xfff2e6c6.toInt()))
        )
    }
}