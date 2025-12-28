@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

import drawable.GLMapDrawable
import points.GeoPoint
import points.MapPoint

expect class GLMapAnimation {
    fun flyToGeoPoint(geoPoint: GeoPoint)
    fun setPosition(drawable: GLMapDrawable, position: MapPoint)

    fun setAngle(drawable: GLMapDrawable, angle: Float)
}