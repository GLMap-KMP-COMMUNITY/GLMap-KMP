package points

/**
 * MapPoint is used store coordinates in map internal format
 */
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class MapPoint(x: Double, y: Double) {
    val x: Double
    val y: Double

    fun toGeoPoint(): GeoPoint
}