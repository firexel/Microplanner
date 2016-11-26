package com.seraph.microplanner.presenter.mock

import com.seraph.microplanner.units.Duration
import com.seraph.microplanner.model.Issue
import com.seraph.microplanner.model.Plan
import com.seraph.microplanner.presenter.PlanPresenter

/**
 * Created by Alex on 27.11.2016.
 */
class MockPlanPresenter(private val view: PlanPresenter.View) : PlanPresenter {
    init {
        view.showPlan(MockPlan())
    }

    override fun onAddIssue() {
    }

    override fun cancel() {

    }

    private class MockIssue(override val estimate: Duration) : Issue

    private class MockPlan : Plan {
        override val issues: List<Issue>
            get() = listOf()
    }
}