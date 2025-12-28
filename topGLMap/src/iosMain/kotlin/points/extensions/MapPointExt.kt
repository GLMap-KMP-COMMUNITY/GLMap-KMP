package points.extensions

import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import iosGLMapCore.GLMapPoint as CoreMapPoint
import iosGLMap.GLMapPoint as MapMapPoint

@OptIn(ExperimentalForeignApi::class)
internal fun CValue<CoreMapPoint>.asMapMapPoint(): CValue<MapMapPoint> {
    @Suppress("UNCHECKED_CAST")
    return this as CValue<MapMapPoint>
}
