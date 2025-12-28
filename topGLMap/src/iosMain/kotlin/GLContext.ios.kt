@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

actual abstract class GLContext private constructor() {
    companion object {
        val INSTANCE = object : GLContext() {}
    }
}

actual val LocalGLContext: ProvidableCompositionLocal<GLContext>
    get() = staticCompositionLocalOf { GLContext.INSTANCE }