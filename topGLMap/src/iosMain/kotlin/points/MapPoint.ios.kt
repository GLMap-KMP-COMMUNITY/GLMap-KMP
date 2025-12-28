package points

import iosGLMapCore.GLMapGeoPointFromMapPoint
import iosGLMapCore.GLMapPointMake
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents

@Suppress(names = ["EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING"])
@OptIn(ExperimentalForeignApi::class)
actual class MapPoint actual constructor(x: Double, y: Double) {
    internal val native = GLMapPointMake(x, y)
    actual val x: Double
        get() = native.useContents { x }
    actual val y: Double
        get() = native.useContents { y }

    actual fun toGeoPoint(): GeoPoint =
        GLMapGeoPointFromMapPoint(native).useContents {
            return GeoPoint(lat, lon)
        }
}