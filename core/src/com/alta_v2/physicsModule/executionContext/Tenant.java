package com.alta_v2.physicsModule.executionContext;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

/**
 * Provides facade of reservable data.
 */
@RequiredArgsConstructor
public class Tenant {

    private final String alias;
    private final String salt = UUID.randomUUID().toString();

    /**
     * Gets the unique identifier of tenant.
     */
    public String getId() {
        return this.alias + "_" + salt;
    }

}
