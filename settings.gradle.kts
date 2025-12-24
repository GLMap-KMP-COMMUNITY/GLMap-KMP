import java.net.URI

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = URI("https://maven.globus.software/artifactory/libs") }
    }
}

rootProject.name = "GLMap KMP (COMMUNITY)"
include(":topGLMap")
