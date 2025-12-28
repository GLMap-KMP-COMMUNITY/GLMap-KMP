package compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import map.GLMapView

@Composable
expect fun GLMap(
    modifier: Modifier,
    mapView: GLMapView
)