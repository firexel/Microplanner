package com.seraph.microplanner.presenter.mock

import com.seraph.microplanner.presenter.PlanPresenter
import com.seraph.microplanner.presenter.PresenterFactory

/**
 * Created by Alex on 27.11.2016.
 */
class MockPresenterFactory : PresenterFactory {
    override fun newPlanPresenter(view: PlanPresenter.View): PlanPresenter {
        return MockPlanPresenter(view)
    }
}