@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

import GLMap.GLMapAnimation as IosGLMapAnimation
import drawable.GLMapDrawable
import kotlinx.cinterop.ExperimentalForeignApi
import points.GeoPoint
import points.MapPoint
import points.extensions.asMapMapPoint
import points.extensions.asMapGeoPoint

@OptIn(ExperimentalForeignApi::class)
actual class GLMapAnimation {
    private val native: IosGLMapAnimation

    constructor(native: IosGLMapAnimation) {
        this.native = native
    }

    actual fun flyToGeoPoint(geoPoint: GeoPoint) {
        native.flyToGeoPoint(geoPoint.native.asMapGeoPoint())
    }

    actual fun setPosition(drawable: GLMapDrawable, position: MapPoint) {
        native.setPosition(
            position = position.native.asMapMapPoint(),
            forDrawable = drawable.native
        )
    }

    actual fun setAngle(drawable: GLMapDrawable, angle: Float) {
        native.setAngle(
            forDrawable = drawable.native,
            angle = angle
        )
    }
}