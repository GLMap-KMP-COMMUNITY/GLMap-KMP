package points

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class GeoPoint(lat: Double, lon: Double) {
    val lat: Double
    val lon: Double

    fun toMapPoint(): MapPoint
    fun distanceToGeoPoint(geoPoint: GeoPoint): Double
}
