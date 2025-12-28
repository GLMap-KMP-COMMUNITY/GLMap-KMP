@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package drawable

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import map.GLMapView
import globus.glmap.GLMapDrawable as AndroidGLMapDrawable
import points.MapPoint

actual abstract class GLMapDrawable : GLMapDrawObject {
    internal val native: AndroidGLMapDrawable

    constructor(native: AndroidGLMapDrawable) : super(native) {
        this.native = native
    }

    actual fun setPosition(mapPoint: MapPoint) {
        native.position = mapPoint.native
    }

    actual fun getPosition(): MapPoint {
        val nativeMapPoint = native.position
        return MapPoint(nativeMapPoint.x, nativeMapPoint.y)
    }

    actual fun setOffset(offset: IntOffset) {
        native.setOffset(offset.x, offset.y)
    }

    actual fun setAlignment(
        size: IntSize,
        alignment: Alignment
    ) {
        size.let { (width, height) ->
            val offset = when (alignment) {
                Alignment.TopStart -> {
                    IntOffset(
                        0,
                        height
                    )
                }

                Alignment.TopCenter -> {
                    IntOffset(
                        width / 2,
                        height
                    )
                }

                Alignment.TopEnd -> {
                    IntOffset(
                        width,
                        height
                    )
                }

                Alignment.CenterStart -> {
                    IntOffset(
                        0,
                        height / 2
                    )
                }

                Alignment.Center -> {
                    IntOffset(
                        width / 2,
                        height / 2
                    )
                }

                Alignment.CenterEnd -> {
                    IntOffset(
                        width,
                        height / 2
                    )
                }

                Alignment.BottomStart -> {
                    IntOffset(
                        0,
                        0
                    )
                }

                Alignment.BottomCenter -> {
                    IntOffset(
                        width / 2,
                        0
                    )
                }

                Alignment.BottomEnd -> {
                    IntOffset(
                        width,
                        0
                    )
                }

                else -> {
                    IntOffset.Zero
                }
            }
            setOffset(offset)
        }
    }

    actual fun setRotateWithMap(rotate: Boolean) {
        native.isRotatesWithMap = rotate
    }

    actual fun testHit(
        map: GLMapView,
        x: Float,
        y: Float,
        paddingLeft: Int,
        paddingRight: Int,
        paddingTop: Int,
        paddingBottom: Int
    ): Boolean {
        return native.hitTest(
            map.mapView.renderer,
            x,
            y,
            paddingLeft,
            paddingRight,
            paddingTop,
            paddingBottom
        )
    }

}