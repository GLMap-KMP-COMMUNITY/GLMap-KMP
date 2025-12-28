@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package drawable

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import map.GLMapView
import points.MapPoint

expect abstract class GLMapDrawable : GLMapDrawObject {
    fun setPosition(mapPoint: MapPoint)
    fun getPosition(): MapPoint
    fun setOffset(offset: IntOffset)
    fun setAlignment(size: IntSize, alignment: Alignment)
    fun setRotateWithMap(rotate: Boolean)

    fun testHit(
        map: GLMapView,
        x: Float,
        y: Float,
        paddingLeft: Int,
        paddingRight: Int,
        paddingTop: Int,
        paddingBottom: Int
    ): Boolean
}