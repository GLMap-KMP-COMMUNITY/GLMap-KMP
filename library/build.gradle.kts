import com.android.build.api.dsl.androidLibrary
import org.gradle.api.problems.internal.GradleCoreProblemGroup.compilation
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.net.URI

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.vanniktech.mavenPublish)
    id("io.github.frankois944.spmForKmp") version "1.4.0"
}

group = "org.top.glmap"
version = "1.0.0"

kotlin {

    androidLibrary {
        namespace = "org.top.glmap"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        withJava() // enable java compilation support
        withHostTestBuilder {}.configure {}
        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }

        compilations.configureEach {
            compileTaskProvider.configure{
                compilerOptions {
                    jvmTarget.set(
                        JvmTarget.JVM_11
                    )
                }
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.compilations {
            getting {
                // Choose the cinterop name
                cinterops.create("MyCinterop")
            }
        }
    }


    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
        }

        androidMain.dependencies {
            implementation(libs.bundles.glmap)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()

    coordinates(group.toString(), "library", version.toString())

    pom {
        name = "GLMap Kotlin Multiplatform Library (COMMUNITY)"
        description = "A library."
        inceptionYear = "2025"
        url = "https://github.com/kotlin/multiplatform-library-template/"
        developers {
            developer {
                name = "Gleb Borisov"
                email = "topsycrettschannel@gmail.com"
                url = "https://github.com/TopsyCretts"
            }
        }
    }
}

swiftPackageConfig {
    val gLMapVersion = libs.glmap

    create("MyCinterop") {
        minIos = "13.0"
        dependency {
            remoteBinary(
                url = URI("https://globus.software/download/GLMapCore-$gLMapVersion.zip"),
                packageName = "GLMapCore",
                checksum = "c4edf9b3757cc9fe8d02a1c6783f6f1c2c2e50d602074db1f92fd8aa56a67a54",
                exportToKotlin = true
            )
            remoteBinary(
                url = URI("https://globus.software/download/GLMap-$gLMapVersion.zip"),
                packageName = "GLMap",
                checksum = "d977bab5085b3ab7016a30d1248f9f33a288b76cdb4c6a53480ed838dc49f3c5",
                exportToKotlin = true
            )
            remoteBinary(
                url = URI("https://globus.software/download/GLRoute-$gLMapVersion.zip"),
                packageName = "GLRoute",
                checksum = "fe6276fa206f06b9853fbc9276f5a99756dcb35f096f58b80338034fc8fc6fbf",
                exportToKotlin = true
            )
            remoteBinary(
                url = URI("https://globus.software/download/GLSearch-$gLMapVersion.zip"),
                packageName = "GLSearch",
                checksum = "6c72a8251da3e408bbd8e40f063db6e6c4c76a05daf57910af0303e6fbac28a3",
                exportToKotlin = true
            )

        }
    }
}
