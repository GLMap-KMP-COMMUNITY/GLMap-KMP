package map

import GLMapCore.GLMapError as IosGLMapError
import GLMapCore.GLMapError_CURL
import GLMapCore.GLMapError_HTTP
import GLMapCore.GLMapError_System
import GLMapCore.GLMapError_Valhalla
import GLMapCore.GLMapError_XZ
import kotlinx.cinterop.ExperimentalForeignApi

@Suppress(names = ["EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING"])
@OptIn(ExperimentalForeignApi::class)
actual class GLMapError {
    private val native: IosGLMapError

    constructor(native: IosGLMapError) {
        this.native = native
    }

    actual fun getErrorDomain(): String {
        if (isHTTPError()) {
            return "HTTP"
        } else if (isCURLError()) {
            return "CURL"
        } else if (isXZError()) {
            return "XZ"
        } else if (isValhallaError()) {
            return "Valhalla"
        }
        return "ERRNO"
    }

    actual fun isSystemError(): Boolean {
       return native == GLMapError_System
    }


    actual fun isHTTPError(): Boolean {
        return native == GLMapError_HTTP
    }

    actual fun isCURLError(): Boolean {
        return native == GLMapError_CURL
    }

    actual fun isXZError(): Boolean {
        return native == GLMapError_XZ
    }

    actual fun isValhallaError(): Boolean {
        return native == GLMapError_Valhalla
    }

}