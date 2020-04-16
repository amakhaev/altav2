package com.alta_v2.common

interface Destroyable {
    /**
     * Destroys the object. After calling of this method the object can't be used since it's invalid.
     */
    fun destroy()
}