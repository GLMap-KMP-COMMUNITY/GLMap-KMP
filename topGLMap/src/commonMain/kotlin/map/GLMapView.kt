package map

import GLContext
import GLMapAnimation
import androidx.compose.ui.geometry.Offset
import drawable.GLMapDrawObject
import kotlinx.coroutines.flow.StateFlow
import points.GeoPoint
import points.MapPoint

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class GLMapView(context: GLContext) {
    val center: StateFlow<GeoPoint>
    val bBox: StateFlow<GLMapBBox>

    val width: Int
    val height: Int

    fun setZoomLevel(zoom: Double)
    fun getZoomLevel(): Double

    fun cancelAnimations()
    fun animate(block: (GLMapAnimation?) -> Unit)

    fun add(drawable: GLMapDrawObject)
    fun remove(drawable: GLMapDrawObject)

    fun convertDisplayToInternal(x: Double, y: Double): MapPoint
    fun convertInternalToDisplay(mapPoint: MapPoint): Offset

    fun mapZoomForBBox(bBox: GLMapBBox, mapWidth: Int, mapHeight: Int): Double

    fun setAngle(angle: Float)

    fun setAttributionPosition(placement: GLMapPlacement)

}

