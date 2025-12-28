package compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitViewController
import kotlinx.cinterop.ExperimentalForeignApi
import map.GLMapView
import platform.UIKit.UIViewController

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun GLMap(
    modifier: Modifier,
    mapView: GLMapView
) {
    val uiViewController = remember { UIViewController() }

    UIKitViewController(
        modifier = modifier,
        factory = {
            uiViewController.setView(mapView.mapView)
            uiViewController
        }
    )
}