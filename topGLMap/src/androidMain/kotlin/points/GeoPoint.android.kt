package points

import globus.glmap.MapGeoPoint as AndroidMapGeoPoint
import globus.glmap.MapPoint as AndroidGLMapPoint

@Suppress(names = ["EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING"])
actual class GeoPoint actual constructor(lat: Double, lon: Double) {
    internal val native = AndroidMapGeoPoint(lat, lon)

    actual val lat: Double
        get() = native.lat
    actual val lon: Double
        get() = native.lon


    actual fun toMapPoint(): MapPoint {
        val mapPoint = AndroidGLMapPoint(native)
        return MapPoint(mapPoint.x, mapPoint.y)
    }

    actual fun distanceToGeoPoint(geoPoint: GeoPoint): Double {
        return native.distanceToGeoPoint(geoPoint.native)
    }
}