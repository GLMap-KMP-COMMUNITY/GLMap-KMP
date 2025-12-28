@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import com.android.build.api.dsl.androidLibrary
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.kotlinCocoapods)
    id("maven-publish")
}

group = "org.top.glmap"
version = "1.0.0"

kotlin {

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = "1.0"
        summary = "Some description for a Kotlin/Native module"
        homepage = "Link to a Kotlin/Native module homepage"

        name = "TopPod"

        ios.deploymentTarget = "13.0"
        framework {
            baseName = "topGLMap"
            isStatic = false
            transitiveExport = true
        }

        pod("GLMapCore") {
            version = "1.12.0"
            packageName = "iosGLMapCore"
        }

        pod("GLMap") {
            version = "1.12.0"
            packageName = "iosGLMap"
        }

        // Maps custom Xcode configuration to NativeBuildType
        xcodeConfigurationToNativeBuildType["CUSTOM_DEBUG"] = NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["CUSTOM_RELEASE"] = NativeBuildType.RELEASE
    }

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
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(
                        JvmTarget.JVM_11
                    )
                }
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.foundation)
            api(libs.androidx.lifecycle.runtime.compose)
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

    //signAllPublications()

    coordinates(group.toString(), "topGLMap", version.toString())

    pom {
        name = "GLMap Kotlin Multiplatform Library (COMMUNITY)"
        description = "A Topsy's GLMap."
        inceptionYear = "2025"
        url = "https://github.com/TopsyCretts/GLMap-KMP"
        developers {
            developer {
                name = "Gleb Borisov"
                email = "topsycrettschannel@gmail.com"
                url = "https://github.com/TopsyCretts"
            }
        }
    }
}
