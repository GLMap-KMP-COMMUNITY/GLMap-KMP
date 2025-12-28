package points.extensions

import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import GLMapCore.GLMapGeoPoint as CoreGeoPoint
import GLMap.GLMapGeoPoint as MapGeoPoint

@OptIn(ExperimentalForeignApi::class)
internal fun CValue<CoreGeoPoint>.asMapGeoPoint(): CValue<MapGeoPoint> {
    @Suppress("UNCHECKED_CAST")
    return this as CValue<MapGeoPoint>
}
