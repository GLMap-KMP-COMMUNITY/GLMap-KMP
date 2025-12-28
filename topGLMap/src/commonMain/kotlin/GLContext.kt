import androidx.compose.runtime.ProvidableCompositionLocal

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect abstract class GLContext

expect val LocalGLContext: ProvidableCompositionLocal<GLContext>