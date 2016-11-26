package com.seraph.microplanner

import com.seraph.microplanner.presenter.PresenterFactory
import com.seraph.microplanner.presenter.mock.MockPresenterFactory

/**
 * Created by Alex on 27.11.2016.
 */
class Application : android.app.Application(), Locator.Host {
    override val locator: Locator = Locator()

    override fun onCreate() {
        super.onCreate()
        locator.register(PresenterFactory::class.java, MockPresenterFactory())
    }
}