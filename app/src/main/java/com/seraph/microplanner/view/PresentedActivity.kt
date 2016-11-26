package com.seraph.microplanner.view

import android.support.v7.app.AppCompatActivity
import com.seraph.microplanner.Cancellable
import com.seraph.microplanner.Locator
import com.seraph.microplanner.presenter.Presenter
import com.seraph.microplanner.presenter.PresenterFactory

/**
 * Created by Alex on 27.11.2016.
 */

abstract class PresentedActivity<P : Presenter> : AppCompatActivity() {
    protected lateinit var presenter: P

    protected fun setPresenter(select: PresenterFactory.() -> P) {
        presenter = Locator.from(this).locate(PresenterFactory::class.java).select()
    }

    override fun onDestroy() {
        super.onDestroy()
        val currentPresenter = presenter
        if (currentPresenter is Cancellable) currentPresenter.cancel()
    }
}
