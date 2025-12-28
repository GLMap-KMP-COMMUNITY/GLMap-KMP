package points

import globus.glmap.MapGeoPoint as AndroidMapGeoPoint
import globus.glmap.MapPoint as AndroidGLMapPoint

@Suppress(names = ["EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING"])
actual class MapPoint actual constructor(x: Double, y: Double) {
    internal val native = AndroidGLMapPoint(x, y)

    actual val x: Double
        get() = native.x
    actual val y: Double
        get() = native.y

    actual fun toGeoPoint(): GeoPoint {
        val geoPoint = AndroidMapGeoPoint(native)
        return GeoPoint(geoPoint.lat, geoPoint.lon)
    }
}