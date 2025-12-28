package map

import globus.glmap.GLMapError as AndroidGLMapError

@Suppress(names = ["EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING"])
actual class GLMapError {
    private val native: AndroidGLMapError

    constructor(native: AndroidGLMapError) {
        this.native = native
    }

    actual fun getErrorDomain(): String = native.getErrorDomain()

    actual fun isSystemError(): Boolean = native.isSystemError

    actual fun isHTTPError(): Boolean = native.isHTTPError

    actual fun isCURLError(): Boolean = native.isCURLError

    actual fun isXZError(): Boolean = native.isXZError

    actual fun isValhallaError(): Boolean = native.isValhallaError

}