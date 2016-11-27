package com.seraph.microplanner.model

import com.seraph.microplanner.units.Duration

/**
 * Created by Alex on 27.11.2016.
 */
interface Issue {
    val title:String
    val estimate: Duration
    val tag:Tag
}