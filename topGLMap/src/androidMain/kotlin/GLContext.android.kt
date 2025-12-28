@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

import android.content.Context
import androidx.compose.ui.platform.LocalContext

actual typealias GLContext = Context

actual inline val LocalGLContext get() = LocalContext