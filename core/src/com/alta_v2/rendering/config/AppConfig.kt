package com.alta_v2.rendering.config

import kotlin.properties.Delegates

class AppConfig {

    lateinit var title: String
    var width by Delegates.notNull<Int>()
    var height by Delegates.notNull<Int>()
}