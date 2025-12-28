@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

expect class GLMapManager() {

    fun initialize(
        context: GLContext,
        apiKey: String
    )

    fun setTileDownloadingAllowed(value: Boolean)
}