package map

import GLContext
import GLMapAnimation
import iosGLMapCore.GLMapBBoxMake
import androidx.compose.ui.geometry.Offset
import drawable.GLMapDrawObject
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import platform.CoreGraphics.CGPointMake
import platform.CoreGraphics.CGSizeMake
import platform.UIKit.UIScreen
import points.GeoPoint
import points.MapPoint
import points.extensions.asMapMapPoint
import kotlin.math.log
import kotlin.math.roundToInt
import iosGLMap.GLMapPlacement as IosGLMapPlacement
import iosGLMap.GLMapView as IosGLMapView

@Suppress(names = ["EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING"])
@OptIn(ExperimentalForeignApi::class)
actual class GLMapView actual constructor(context: GLContext) {

    internal val mapView = IosGLMapView()

    private val _center = MutableStateFlow(GeoPoint(0.0, 0.0))
    actual val center: StateFlow<GeoPoint>
        get() = _center

    private val _bBox = MutableStateFlow(
        GLMapBBox()
    )
    actual val bBox: StateFlow<GLMapBBox>
        get() = _bBox

    actual val width: Int
        get() = mapView.frame.useContents {
            (this.size.width * UIScreen.mainScreen.scale).roundToInt()
        }
    actual val height: Int
        get() = mapView.frame.useContents {
            (this.size.height * UIScreen.mainScreen.scale).roundToInt()
        }

    init {
        CoroutineScope(Dispatchers.Default + SupervisorJob()).launch {
            while (isActive) {
                val geoPoint = mapView.mapGeoCenter.useContents {
                    GeoPoint(
                        lat = this.lat,
                        lon = this.lon
                    )
                }
                _center.update { geoPoint }

                val bBox = mapView.bbox.useContents {
                    GLMapBBox(
                        originX = this.origin.x,
                        originY = this.origin.y,
                        sizeX = this.size.x,
                        sizeY = this.size.y
                    )
                }
                _bBox.update { bBox }
                delay(1000)
            }
        }
    }

    actual fun setZoomLevel(zoom: Double) {
        mapView.mapZoomLevel = zoom
    }
    actual fun getZoomLevel(): Double = mapView.mapZoomLevel

    actual fun cancelAnimations() {
        mapView.cancelMapAnimations()
    }
    actual fun animate(block: (GLMapAnimation?) -> Unit) {
        mapView.animate { animation ->
            animation?.let {
                block(GLMapAnimation(animation))
            }
        }
    }

    actual fun add(drawable: GLMapDrawObject) {
        mapView.add(drawable.nativeDrawObject)
    }
    actual fun remove(drawable: GLMapDrawObject) {
        mapView.remove(drawable.nativeDrawObject)
    }

    actual fun convertDisplayToInternal(x: Double, y: Double): MapPoint {
        val cgPoint = CGPointMake(x, y)
        val mapPoint = mapView
            .makeMapPointFromDisplayPoint(cgPoint)
            .useContents { this }
        return MapPoint(mapPoint.x, mapPoint.y)
    }
    actual fun convertInternalToDisplay(mapPoint: MapPoint): Offset {
        @Suppress("UNCHECKED_CAST")
        val offset = mapView.makeDisplayPointFromMapPoint(mapPoint.native.asMapMapPoint()).useContents { this }
        return Offset(offset.x.toFloat(), offset.y.toFloat())
    }

    actual fun mapZoomForBBox(bBox: GLMapBBox, mapWidth: Int, mapHeight: Int): Double {
        @Suppress("UNCHECKED_CAST")
        val mapPoint = MapPoint(bBox.originX, bBox.originY).native

        @Suppress("UNCHECKED_CAST")
        val nativeBBox = GLMapBBoxMake(
            mapPoint,
            bBox.sizeX,
            bBox.sizeY
        ) as CValue<iosGLMap.GLMapBBox>
        val viewSize = CGSizeMake(
            mapWidth.toDouble() / UIScreen.mainScreen.scale,
            mapHeight.toDouble() / UIScreen.mainScreen.scale
        )
        val scale = mapView.mapScaleForBBox(nativeBBox, viewSize)
        return log(scale, 2.toDouble())
    }

    actual fun setAngle(angle: Float) {
        mapView.setMapAngle(angle)
    }

    actual fun setAttributionPosition(placement: GLMapPlacement) {
        mapView.attributionPosition = placement.asIosNative()
    }

    private fun GLMapPlacement.asIosNative(): IosGLMapPlacement {
        return when (this){
            GLMapPlacement.TopLeft -> IosGLMapPlacement.GLMapPlacement_TopLeft
            GLMapPlacement.TopCenter ->  IosGLMapPlacement.GLMapPlacement_TopCenter
            GLMapPlacement.TopRight ->  IosGLMapPlacement.GLMapPlacement_TopRight
            GLMapPlacement.BottomLeft ->  IosGLMapPlacement.GLMapPlacement_BottomLeft
            GLMapPlacement.BottomCenter ->  IosGLMapPlacement.GLMapPlacement_BottomCenter
            GLMapPlacement.BottomRight ->  IosGLMapPlacement.GLMapPlacement_BottomRight
            GLMapPlacement.Hidden ->  IosGLMapPlacement.GLMapPlacement_Hidden
        }
    }
}