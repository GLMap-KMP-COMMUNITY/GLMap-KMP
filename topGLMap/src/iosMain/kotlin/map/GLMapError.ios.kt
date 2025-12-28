package map

import iosGLMapCore.GLMapError as IosGLMapError
import iosGLMapCore.GLMapError_CURL
import iosGLMapCore.GLMapError_HTTP
import iosGLMapCore.GLMapError_System
import iosGLMapCore.GLMapError_Valhalla
import iosGLMapCore.GLMapError_XZ
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