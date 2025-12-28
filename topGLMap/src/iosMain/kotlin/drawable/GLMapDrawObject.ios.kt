@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package drawable

import kotlinx.cinterop.ExperimentalForeignApi
import GLMap.GLMapDrawObject as IosDrawObject

@OptIn(ExperimentalForeignApi::class)
actual abstract class GLMapDrawObject {
    val nativeDrawObject: IosDrawObject

    constructor(native: IosDrawObject){
        this.nativeDrawObject = native
    }

    actual fun getDrawOrder(): Int {
        return nativeDrawObject.drawOrder
    }

    actual fun setHidden(hidden: Boolean) {
        nativeDrawObject.setHidden(hidden)
    }

    actual fun isHidden(): Boolean {
        return nativeDrawObject.hidden
    }
}