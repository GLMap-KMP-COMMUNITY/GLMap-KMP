@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

import globus.glmap.GLMapManager as AndroidGLMapManager

actual class GLMapManager actual constructor() {

    actual fun initialize(
        context: GLContext,
        apiKey: String,
    ) {
        AndroidGLMapManager.Initialize(context, apiKey, null)
    }

    actual fun setTileDownloadingAllowed(value: Boolean) {
        AndroidGLMapManager.SetTileDownloadingAllowed(value)
    }

}