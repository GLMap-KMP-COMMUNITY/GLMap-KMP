package points.extensions

import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import iosGLMapCore.GLMapGeoPoint as CoreGeoPoint
import iosGLMap.GLMapGeoPoint as MapGeoPoint

@OptIn(ExperimentalForeignApi::class)
internal fun CValue<CoreGeoPoint>.asMapGeoPoint(): CValue<MapGeoPoint> {
    @Suppress("UNCHECKED_CAST")
    return this as CValue<MapGeoPoint>
}
