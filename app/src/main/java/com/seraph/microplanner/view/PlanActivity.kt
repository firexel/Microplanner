package com.seraph.microplanner.view

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.seraph.microplanner.R
import com.seraph.microplanner.model.Issue
import com.seraph.microplanner.model.Plan
import com.seraph.microplanner.presenter.PlanPresenter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class PlanActivity : PresentedActivity<PlanPresenter>(), PlanPresenter.View {

    private val planAdapter = PlanAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan)
        setSupportActionBar(findViewById(R.id.toolbar) as Toolbar)
        setTitle(R.string.today)
        setPresenter { newPlanPresenter(this@PlanActivity) }

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { presenter.onAddIssue() }

        val list = findViewById(R.id.issues_list) as RecyclerView
        list.addItemDecoration(PaddingDecoration(2.dp(this).toInt()))
        list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        list.adapter = planAdapter
    }

    override fun showPlan(plan: Plan) {
        planAdapter.plan = plan
    }

    class PlanAdapter() : RecyclerView.Adapter<PlanAdapter.ViewHolder>() {
        val startFormat: DateFormat = SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT)

        var plan: Plan = EmptyPlan()
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun getItemCount(): Int = plan.issues.size

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent!!.context)
                    .inflate(R.layout.item_issue, parent, false)

            return ViewHolder(view, startFormat)
        }

        override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
            holder?.showIssue(plan, plan.issues[position])
        }

        class ViewHolder(itemView: View, val startFormat: DateFormat) : RecyclerView.ViewHolder(itemView) {

            val startText = itemView.findViewById(R.id.text_start) as TextView
            val titleText = itemView.findViewById(R.id.text_title) as TextView
            val tagText = itemView.findViewById(R.id.text_tag) as TextView
            val colorMarker: View = itemView.findViewById(R.id.view_color_marker)

            fun showIssue(plan: Plan, issue: Issue) {
                titleText.text = issue.title
                titleText.visibility = if (issue.title.isEmpty()) View.GONE else View.VISIBLE
                tagText.text = "#" + issue.tag.name
                colorMarker.setBackgroundColor(issue.tag.color)
                colorMarker.minimumHeight = Math.max(
                        48.dp(itemView.context),
                        32.dp(itemView.context) * issue.estimate.hours).toInt()
                startText.text = startFormat.format(plan.issueStartTime(issue).time)
            }
        }

        private class EmptyPlan : Plan {
            override val start: Calendar = Calendar.getInstance()
            override val issues: List<Issue> = emptyList()
        }
    }
}