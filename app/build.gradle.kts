import core.*

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
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

    dataBinding {
        isEnabled = true
    }

    kapt {
        generateStubs = true
    }

    androidExtensions {
        isExperimental = true
    }

    compileOptions {
        sourceCompatibility = Config.JAVA_VERSION
        targetCompatibility = Config.JAVA_VERSION
    }

    tasks.withType(org.jetbrains.kotlin.gradle.dsl.KotlinCompile::class).all {
        kotlinOptions {
            val options = this as org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
            options.jvmTarget = "1.8"
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
    implementation(project(":core"))

    implementation(Dependencies.AndroidXLibraries.media)
    kapt(Dependencies.Plugins.databinding_compiler)

    kapt(Dependencies.ThirdPartyLibs.glide_compiler)
    implementation(Dependencies.ThirdPartyLibs.glide)
    implementation(Dependencies.ThirdPartyLibs.glide_transformations)

    implementation(Dependencies.ThirdPartyLibs.glide_okhttp_integration) {
        exclude("glide-parent")
    }
}


