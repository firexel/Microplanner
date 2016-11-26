package com.seraph.microplanner.view

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.Toolbar
import com.seraph.microplanner.R
import com.seraph.microplanner.model.Plan
import com.seraph.microplanner.presenter.PlanPresenter

class PlanActivity : PresentedActivity<PlanPresenter>(), PlanPresenter.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan)
        setSupportActionBar(findViewById(R.id.toolbar) as Toolbar)
        setPresenter { newPlanPresenter(this@PlanActivity) }

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { presenter.onAddIssue() }
    }

    override fun showPlan(plan: Plan) {
    }
}
