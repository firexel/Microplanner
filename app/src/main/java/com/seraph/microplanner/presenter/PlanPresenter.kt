package com.seraph.microplanner.presenter

import com.seraph.microplanner.model.Plan

/**
 * Created by Alex on 27.11.2016.
 */

interface PlanPresenter : Presenter {
    fun onAddIssue()

    interface View {
        fun showPlan(plan: Plan)
    }
}
