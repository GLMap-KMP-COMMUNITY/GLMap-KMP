package points

import iosGLMapCore.GLMapGeoPointMake
import iosGLMapCore.GLMapPointFromMapGeoPoint
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

@Suppress(names = ["EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING"])
@OptIn(ExperimentalForeignApi::class)
actual class GeoPoint actual constructor(lat: Double, lon: Double) {
    internal val native = GLMapGeoPointMake(lat, lon)

    actual val lat: Double
        get() = native.useContents { this.lat }
    actual val lon: Double
        get() = native.useContents { this.lon }

    actual fun toMapPoint(): MapPoint =
        GLMapPointFromMapGeoPoint(native).useContents {
            MapPoint(x = this.x, y = this.y)
        }


    actual fun distanceToGeoPoint(geoPoint: GeoPoint): Double {
        val distance = calculateDistanceHaversine(
            this.lat,
            this.lon,
            geoPoint.lat,
            geoPoint.lon
        )
        return distance
    }

    private fun calculateDistanceHaversine(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double
    ): Double {

        fun toRadians(deg: Double): Double = deg / 180.0 * PI

        val earthRadius = 6371000.0 // Earth radius in meters

        val dLat = toRadians(lat2 - lat1)
        val dLon = toRadians(lon2 - lon1)

        val a = sin(dLat / 2).pow(2) +
                cos(toRadians(lat1)) * cos(toRadians(lat2)) *
                sin(dLon / 2).pow(2)

        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return earthRadius * c
    }
}