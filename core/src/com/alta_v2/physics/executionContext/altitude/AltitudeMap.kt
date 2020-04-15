package com.alta_v2.physics.executionContext.altitude

import mu.KotlinLogging

/**
 * Describes the altitude map.
 */
class AltitudeMap(val tileWidth: Int,
                  val tileHeight: Int,
                  private val altitudes: Array<Array<AltitudeMapPoint>>) {

    private val log = KotlinLogging.logger {  }

    /**
     * Gets the status of point on map.
     */
    fun getPointStatus(x: Int, y: Int): PointAvailability? {
        return if (isOutOfBounds(x, y)) {
            null
        } else altitudes[x][y].pointAvailability
    }

    /**
     * Gets the ID of object o given position.
     */
    fun getObjectId(x: Int, y: Int): Int? {
        return if (isOutOfBounds(x, y)) {
            null
        } else altitudes[x][y].objectId
    }

    /**
     * Marks point as free for movement.
     *
     * @param x - the X coordinate of point.
     * @param y - the Y coordinate of point.
     */
    fun markAsFree(x: Int, y: Int) {
        if (isOutOfBounds(x, y)) {
            log.error("Index of bound for altitudes [{}: {}]", x, y)
            return
        }
        altitudes[x][y].pointAvailability = PointAvailability.FREE
        altitudes[x][y].objectId = null
    }

    /**
     * Marks point as barrier for movement.
     *
     * @param x - the X coordinate of point.
     * @param y - the Y coordinate of point.
     */
    fun markAsBarrier(x: Int, y: Int) {
        if (isOutOfBounds(x, y)) {
            log.error("Index of bound for altitudes [{}: {}]", x, y)
            return
        }
        altitudes[x][y].pointAvailability = PointAvailability.BARRIER
    }

    /**
     * Marks point as busy for movement by objectId.
     *
     * @param x - the X coordinate of point.
     * @param y - the Y coordinate of point.
     * @param objectId - the ID of object.
     */
    fun markAsObject(x: Int, y: Int, objectId: Int) {
        if (isOutOfBounds(x, y)) {
            log.error("Index of bound for altitudes [{}: {}]", x, y)
            return
        }
        altitudes[x][y].pointAvailability = PointAvailability.OBJECT
        altitudes[x][y].objectId = objectId
    }

    private fun isOutOfBounds(x: Int, y: Int): Boolean {
        return x < 0 || x >= altitudes.size || y < 0 || y >= altitudes[x].size
    }
}