package map

import points.MapPoint
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class GLMapBBox {
    /** X coordinate of the origin of the bounding box  */
    var originX: Double = 0.0

    /** Y coordinate of the origin of the bounding box  */
    var originY: Double = 0.0

    /** X size of the bounding box  */
    var sizeX: Double = 0.0

    /** Y size of the bounding box  */
    var sizeY: Double = 0.0

    /** Default constructor. Initialize empty bounding box  */
    constructor() {
        clear()
    }

    /**
     * Initialize bbox with given data
     *
     * @param originX X coordinate of the origin of the bounding box
     * @param originY Y coordinate of the origin of the bounding box
     * @param sizeX X size of the bounding box
     * @param sizeY Y size of the bounding box
     */
    constructor(originX: Double, originY: Double, sizeX: Double, sizeY: Double) {
        this.originX = originX
        this.originY = originY
        this.sizeX = sizeX
        this.sizeY = sizeY
    }

    /**
     * Copy constructor
     *
     * @param bbox bbox to copy
     */
    constructor(bbox: GLMapBBox) {
        originX = bbox.originX
        originY = bbox.originY
        sizeX = bbox.sizeX
        sizeY = bbox.sizeY
    }

    /**
     * Reset bbox to empty
     */
    fun clear() {
        originX = 0.0
        originY = 0.0
        sizeX = -1.0
        sizeY = -1.0
    }

    /**
     * Initialize bbox with data
     *
     * @param originX X coordinate of the origin of the bounding box
     * @param originY Y coordinate of the origin of the bounding box
     * @param sizeX X size of the bounding box
     * @param sizeY Y size of the bounding box
     */
    fun assign(originX: Double, originY: Double, sizeX: Double, sizeY: Double) {
        this.originX = originX
        this.originY = originY
        this.sizeX = sizeX
        this.sizeY = sizeY
    }

    /**
     * Center of bbox
     *
     * @return center of bbox
     */
    fun center(): MapPoint {
        return MapPoint(originX + sizeX / 2, originY + sizeY / 2)
    }

    /**
     * Join this bbox with given
     *
     * @param bbox bbox to add
     */
    fun addBBox(bbox: GLMapBBox?) {
        if (bbox == null) return

        if (sizeX < 0 || sizeY < 0) {
            originX = bbox.originX
            originY = bbox.originY
            sizeX = bbox.sizeX
            sizeY = bbox.sizeY
        } else {
            addPoint(bbox.originX, bbox.originY)
            addPoint(bbox.originX + bbox.sizeX, bbox.originY + bbox.sizeY)
        }
    }

    /**
     * Add point to bbox
     *
     * @param x x coordinate of point
     * @param y y coordinate of point
     */
    fun addPoint(x: Double, y: Double) {
        if (sizeX < 0 || sizeY < 0) {
            originX = x
            originY = y
            sizeX = 0.0
            sizeY = 0.0
        } else {
            val brx = originX + sizeX
            val bry = originY + sizeY

            if (originX > x) {
                originX = x
                sizeX = brx - x
            }

            if (brx < x) {
                sizeX = x - originX
            }

            if (originY > y) {
                originY = y
                sizeY = bry - y
            }

            if (bry < y) {
                sizeY = y - originY
            }
        }
    }

    /**
     * Add point to bbox
     *
     * @param pt point to add
     */
    fun addPoint(pt: MapPoint) {
        addPoint(pt.x, pt.y)
    }

    override fun equals(other: Any?): Boolean {
        if (other is GLMapBBox) {
            val tOther: GLMapBBox = other
            return originX == tOther.originX && originY == tOther.originY && sizeX == tOther.sizeX && sizeY == tOther.sizeY
        }
        return false
    }

    override fun hashCode(): Int {
        return originX.toInt() and (0xFF + (originY.toInt() and 0XFF) shl 8 + (sizeX.toInt() and 0XFF) shl 8 + (sizeY.toInt() and 0XFF) shl 8)
    }

    /**
     * Rotates bbox
     * @param angle angle to rotate
     */
    fun rotate(angle: Double) {
        var angle = angle
        if (angle == 0.0) return

        angle = toRadians(angle)
        val dsin = sin(angle)
        val dcos = cos(angle)

        val cx = originX + sizeX / 2
        val cy = originY + sizeY / 2

        val x1 = originX - cx
        val x2 = x1 + sizeX
        val y1 = originY - cy
        val y2 = y1 + sizeY

        clear()
        addPoint(x1 * dcos - y1 * dsin + cx, x1 * dsin + y1 * dcos + cy)
        addPoint(x1 * dcos - y2 * dsin + cx, x1 * dsin + y2 * dcos + cy)
        addPoint(x2 * dcos - y1 * dsin + cx, x2 * dsin + y1 * dcos + cy)
        addPoint(x2 * dcos - y2 * dsin + cx, x2 * dsin + y2 * dcos + cy)
    }

    /**
     * Checks if bbox intersects with other bbox
     * @param b other bbox
     * @return true if intersects
     */
    fun intersects(b: GLMapBBox): Boolean {
        return originX <= b.originX + b.sizeX && b.originX <= originX + sizeX && originY <= b.originY + b.sizeY && b.originY <= originY + sizeY
    }

    private fun toRadians(deg: Double): Double = deg / 180.0 * PI
}