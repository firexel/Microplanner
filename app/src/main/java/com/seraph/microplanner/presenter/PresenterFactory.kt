package com.seraph.microplanner.presenter

/**
 * Created by Alex on 27.11.2016.
 */

interface PresenterFactory {
    fun newPlanPresenter(view: PlanPresenter.View):PlanPresenter
}
