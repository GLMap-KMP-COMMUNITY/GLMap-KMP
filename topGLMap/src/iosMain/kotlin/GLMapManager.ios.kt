@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

import GLMapCore.GLMapManager as IosGLMapManager
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual class GLMapManager {
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
        IosGLMapManager.sharedManager.tileDownloadingAllowed = value
    }

}