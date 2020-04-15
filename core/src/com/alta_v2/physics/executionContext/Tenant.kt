package com.alta_v2.physics.executionContext

import java.util.*

/**
 * Provides facade of reservable data.
 */
class Tenant(private val alias: String) {

    private val salt = UUID.randomUUID().toString()

    /**
     * Gets the unique identifier of tenant.
     */
    val id: String
        get() = alias + "_" + salt
}