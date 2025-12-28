package compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import map.GLMapView

@Composable
actual fun GLMap(
    modifier: Modifier,
    mapView: GLMapView
) {
    AndroidView(
        modifier = modifier,
        factory = { _ ->
            mapView.mapView.apply {
                clipToOutline = true
            }
        }
    )
}