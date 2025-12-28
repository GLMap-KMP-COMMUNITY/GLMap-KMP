@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

import globus.glmap.GLMapAnimation as AndroidGLMapAnimation
import drawable.GLMapDrawable
import points.GeoPoint
import points.MapPoint

actual class GLMapAnimation {
    private val native: AndroidGLMapAnimation

    constructor(native: AndroidGLMapAnimation) {
        this.native = native
    }

    actual fun flyToGeoPoint(geoPoint: GeoPoint) {
        native.flyToGeoPoint(geoPoint.native)
    }

    actual fun setPosition(drawable: GLMapDrawable, position: MapPoint) {
        native.setPosition(drawable.native, position.native)
    }

    actual fun setAngle(drawable: GLMapDrawable, angle: Float) {
        native.setAngle(drawable.native, angle)
    }
}