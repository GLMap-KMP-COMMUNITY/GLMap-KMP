@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

import kotlinx.cinterop.ExperimentalForeignApi
import iosGLMapCore.GLMapManager as IosGLMapManager

@OptIn(ExperimentalForeignApi::class)
actual class GLMapManager actual constructor() {
    actual fun initialize(
        context: GLContext,
        apiKey: String
    ) {
        IosGLMapManager.activateWithApiKey(
            apiKey = apiKey,
            resourcesBundle = null,
            andStoragePath = null
        )
    }

    actual fun setTileDownloadingAllowed(value: Boolean) {
        IosGLMapManager.sharedManager.setTileDownloadingAllowed(value)
    }

}