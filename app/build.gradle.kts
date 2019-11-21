import core.*

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {

    compileSdkVersion(Config.COMPILE_SDK_VERSION)

    defaultConfig {
        applicationId = Config.APPLICATION_ID
        minSdkVersion(Config.MIN_SDK_VERSION)
        targetSdkVersion(Config.MAX_SDK_VERSION)
        versionCode = Config.VERSION_CODE
        versionName = Config.VERSION_NAME
        multiDexEnabled = Config.MULTIDEX_ENABLED
        testInstrumentationRunner = Dependencies.Tests.instrumentation_runner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    lintOptions {
        disable("InvalidPackage"/* Some libraries have issues with this*/, "ParcelCreator")
//        xmlOutput  = file ("$project.buildDir/reports/lint/lint-report.xml")
    }

    androidExtensions {
        isExperimental = true
    }

    compileOptions {
        sourceCompatibility = Config.JAVA_VERSION
        targetCompatibility = Config.JAVA_VERSION
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Dependencies.Kotlin.kotlin_std)
    implementation(Dependencies.Kotlin.coroutines)

    implementation(Dependencies.AndroidXLibraries.appcompat)
    implementation(Dependencies.AndroidXLibraries.ktx_core)
    implementation(Dependencies.AndroidXLibraries.constraint_layout)
    implementation(Dependencies.AndroidXLibraries.preferences)
    implementation(Dependencies.AndroidXLibraries.preferences_ktx)
    implementation(Dependencies.AndroidXLibraries.multidex)

    implementation(Dependencies.ThirdPartyLibs.glide)
    implementation(Dependencies.ThirdPartyLibs.retrofit)
    implementation(Dependencies.ThirdPartyLibs.retrofit_converter_gson)
    implementation(Dependencies.ThirdPartyLibs.retrofit_converter_scalars)
    implementation(Dependencies.ThirdPartyLibs.retrofit_logging_interceptor)
    implementation(Dependencies.ThirdPartyLibs.joda_time)

    testImplementation(Dependencies.Tests.junit)
    androidTestImplementation(Dependencies.Tests.junit_androidx_extension)
    androidTestImplementation(Dependencies.Tests.espresso_extension_version)
}
