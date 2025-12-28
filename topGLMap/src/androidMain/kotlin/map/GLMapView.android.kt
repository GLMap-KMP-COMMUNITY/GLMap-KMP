package map

import GLContext
import GLMapAnimation
import androidx.compose.ui.geometry.Offset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import points.GeoPoint
import points.MapPoint
import drawable.GLMapDrawObject
import globus.glmap.GLMapView as AndroidGLMapView
import globus.glmap.GLMapBBox as AndroidGLMapBBox
import globus.glmap.GLMapViewRenderer as AndroidGLMapViewRenderer

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class GLMapView actual constructor(context: GLContext) {
    internal val mapView = AndroidGLMapView(context)

    private val _center = MutableStateFlow(GeoPoint(0.0, 0.0))
    actual val center: StateFlow<GeoPoint>
        get() = _center

    private val _bBox = MutableStateFlow(
        GLMapBBox()
    )
    actual val bBox: StateFlow<GLMapBBox>
        get() = _bBox

    actual val width: Int
        get() = mapView.width
    actual val height: Int
        get() = mapView.height

    init {
        CoroutineScope(Dispatchers.Default + SupervisorJob()).launch {
            val renderer = mapView.renderer
            while (isActive) {
                val geoPoint = renderer.mapGeoCenter
                _center.update { GeoPoint(geoPoint.lat, geoPoint.lon) }

                val bBox = renderer.bBox
                _bBox.update {
                    GLMapBBox(
                        originX = bBox.origin_x,
                        originY = bBox.origin_y,
                        sizeX = bBox.size_x,
                        sizeY = bBox.size_y
                    )
                }
                delay(1000)
            }
        }
    }

    actual fun setZoomLevel(zoom: Double) {
        mapView.renderer.mapZoom = zoom
    }

    actual fun getZoomLevel(): Double {
        return mapView.renderer.mapZoom
    }

    actual fun cancelAnimations() {
        mapView.renderer.cancelMapAnimations()
    }

    actual fun animate(block: (GLMapAnimation?) -> Unit) {
        mapView.renderer.animate { animation ->
            block(GLMapAnimation(animation))
        }
    }

    actual fun add(drawable: GLMapDrawObject) {
        mapView.renderer.add(drawable.nativeDrawObject)
    }

    actual fun remove(drawable: GLMapDrawObject) {
        mapView.renderer.remove(drawable.nativeDrawObject)
    }

    actual fun convertDisplayToInternal(x: Double, y: Double): MapPoint {
        val mapPoint = mapView.renderer.convertDisplayToInternal(x, y)
        return MapPoint(mapPoint.x, mapPoint.y)
    }

    actual fun convertInternalToDisplay(mapPoint: MapPoint): Offset {
        val nativeMapPoint = mapPoint.native
        val offset = mapView.renderer.convertInternalToDisplay(nativeMapPoint)
        return Offset(offset.x, offset.y)
    }

    actual fun mapZoomForBBox(bBox: GLMapBBox, mapWidth: Int, mapHeight: Int): Double {
        val nativeBBox = AndroidGLMapBBox(
            bBox.originX,
            bBox.originY,
            bBox.sizeX,
            bBox.sizeY
        )
        return mapView.renderer.mapZoomForBBox(
            nativeBBox,
            mapWidth,
            mapHeight
        )
    }

    actual fun setAngle(angle: Float) {
        mapView.renderer.mapAngle = angle
    }

    actual fun setAttributionPosition(placement: GLMapPlacement) {
        mapView.renderer.setAttributionPosition(placement.asAndroidNative())
    }

    private fun GLMapPlacement.asAndroidNative(): Int {
        return when (this) {
            GLMapPlacement.TopLeft -> AndroidGLMapViewRenderer.GLMapPlacement.TopLeft
            GLMapPlacement.TopCenter -> AndroidGLMapViewRenderer.GLMapPlacement.TopCenter
            GLMapPlacement.TopRight -> AndroidGLMapViewRenderer.GLMapPlacement.TopRight
            GLMapPlacement.BottomLeft -> AndroidGLMapViewRenderer.GLMapPlacement.BottomLeft
            GLMapPlacement.BottomCenter -> AndroidGLMapViewRenderer.GLMapPlacement.BottomCenter
            GLMapPlacement.BottomRight -> AndroidGLMapViewRenderer.GLMapPlacement.BottomRight
            GLMapPlacement.Hidden -> AndroidGLMapViewRenderer.GLMapPlacement.Hidden
        }
    }
}