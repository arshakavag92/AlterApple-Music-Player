import core.*

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Config.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(Config.MIN_SDK_VERSION)
        targetSdkVersion(Config.MAX_SDK_VERSION)
        versionCode = Config.VERSION_CODE
        versionName = Config.VERSION_NAME

        testInstrumentationRunner = Dependencies.Tests.instrumentation_runner
        consumerProguardFiles(Dependencies.Proguard.consumer_rules)

        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android.txt"),
                    Dependencies.Proguard.proguard_rules
                )
            }
        }

        dataBinding {
            isEnabled = true
        }

        kapt {
            generateStubs = true
        }

        compileOptions {
            sourceCompatibility = Config.JAVA_VERSION
            targetCompatibility = Config.JAVA_VERSION
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    api(Dependencies.Kotlin.kotlin_std)
    api(Dependencies.Kotlin.coroutines)

    api(Dependencies.AndroidXLibraries.appcompat)
    api(Dependencies.AndroidXLibraries.ktx_core)
    api(Dependencies.AndroidXLibraries.constraint_layout)
    api(Dependencies.AndroidXLibraries.preferences)
    api(Dependencies.AndroidXLibraries.preferences_ktx)
    api(Dependencies.AndroidXLibraries.multidex)
    api(Dependencies.AndroidXLibraries.navigation_ui)
    api(Dependencies.AndroidXLibraries.navigation_ui_ktx)
    api(Dependencies.AndroidXLibraries.navigation_fragment)
    api(Dependencies.AndroidXLibraries.navigation_fragment_ktx)

    api(Dependencies.AndroidXLibraries.lifecycle_extensions)
    api(Dependencies.AndroidXLibraries.lifecycle_livedata)
    api(Dependencies.AndroidXLibraries.lifecycle_livedata_ktx)
    api(Dependencies.AndroidXLibraries.androidx_security_crypto_version)

//    kapt(Dependencies.ThirdPartyLibs.glide_compiler)
//    kapt(Dependencies.Plugins.databinding_compiler)
//    api(Dependencies.ThirdPartyLibs.glide)
//
//    api(Dependencies.ThirdPartyLibs.glide_okhttp_integration) {
//        exclude("glide-parent")
//    }
    
    api(Dependencies.ThirdPartyLibs.picasso)

    api(Dependencies.ThirdPartyLibs.retrofit)
    api(Dependencies.ThirdPartyLibs.retrofit_converter_gson)
    api(Dependencies.ThirdPartyLibs.retrofit_converter_scalars)
    api(Dependencies.ThirdPartyLibs.retrofit_logging_interceptor)
    api(Dependencies.ThirdPartyLibs.joda_time)
    api(Dependencies.ThirdPartyLibs.lottie)

    api(Dependencies.ThirdPartyLibs.koin_android)
    api(Dependencies.ThirdPartyLibs.koin_androidx_extensions)
    api(Dependencies.ThirdPartyLibs.koin_androidx_viewmodel)
    api(Dependencies.ThirdPartyLibs.koin_androidx_scope)
    api(Dependencies.ThirdPartyLibs.secure_preferences)

    api(Dependencies.Tests.junit) //testApi
    api(Dependencies.Tests.junit_androidx_extension) // testImplementation
    api(Dependencies.Tests.espresso_extension_version) // testImplementation
}
