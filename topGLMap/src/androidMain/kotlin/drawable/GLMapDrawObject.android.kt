@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package drawable

import globus.glmap.GLMapDrawObject as AndroidDrawObject

actual abstract class GLMapDrawObject {
    internal val nativeDrawObject: AndroidDrawObject

    constructor(native: AndroidDrawObject){
        this.nativeDrawObject = native
    }

    actual fun getDrawOrder(): Int {
        return nativeDrawObject.drawOrder
    }

    actual fun setHidden(hidden: Boolean) {
        nativeDrawObject.isHidden = hidden
    }

    actual fun isHidden(): Boolean {
        return nativeDrawObject.isHidden
    }
}