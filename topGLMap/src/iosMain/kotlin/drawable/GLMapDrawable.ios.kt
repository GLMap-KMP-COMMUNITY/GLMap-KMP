@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package drawable

import GLMap.GLMapDrawable as IosGLMapDrawable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import map.GLMapView
import points.MapPoint
import platform.CoreGraphics.CGPointMake
import platform.UIKit.UIEdgeInsetsMake
import platform.UIKit.UIScreen
import points.extensions.asMapMapPoint
import kotlin.math.roundToInt

@OptIn(ExperimentalForeignApi::class)
actual abstract class GLMapDrawable : GLMapDrawObject {
    internal val native: IosGLMapDrawable

    constructor(drawable: IosGLMapDrawable): super(drawable) {
        native = drawable
    }

    actual fun setPosition(mapPoint: MapPoint) {
        val converted = mapPoint.native.asMapMapPoint()
        native.setPosition(converted)
    }

    actual fun getPosition(): MapPoint =
        native.position.useContents {
            MapPoint(x = this.x, y = this.y)
        }

    actual fun setOffset(offset: IntOffset) {
        val cgPoint = CGPointMake(
            offset.x.toDouble(),
            offset.y.toDouble()
        )
        native.setOffset(cgPoint)
    }

    actual fun setAlignment(size: IntSize, alignment: Alignment) {
        val screenScale = UIScreen.mainScreen.scale
        (size / screenScale.roundToInt()).let { (width, height) ->
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
        native.setRotatesWithMap(rotate)
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
        val point = CGPointMake(
            x.toDouble(), y.toDouble()
        )
        val padding = UIEdgeInsetsMake(
            top = paddingTop.toDouble(),
            left = paddingLeft.toDouble(),
            right = paddingRight.toDouble(),
            bottom = paddingBottom.toDouble(),
        )
        return native.hitTest(
            point = point,
            onMap = map.mapView,
            withPaddings = padding
        )
    }

}