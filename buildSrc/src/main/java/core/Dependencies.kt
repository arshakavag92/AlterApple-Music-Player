package core

object Dependencies {

    object Versions {
        const val kotlin_verion = "1.3.50"

        // AndroidX Dpendncies
        const val androidx_appcompat_version = "1.1.0"
        const val androidx_ktx_core_version = "1.1.0"
        const val androidx_constraintlayout_version = "1.1.3"
        const val androidx_lifecycle_version = "2.2.0-beta01"
        const val androidx_navigation_version = "2.1.0"
        const val androidx_multidex_vesion = "2.0.1"
        const val androidx_preferences_version = "1.1.0"
        const val androidx_architecture_components_version = "2.1.0"

        const val junit_version = "4.12"
        const val junit_extension_version = "1.1.0"
        const val espresso_extension_version = "3.1.1"

        const val retrofit_version = "2.6.2"
        const val retrofit_scalars_factory_version = "2.1.0"
        const val retrofit_logging_interceptor_version = "4.2.1"

        const val koin_version = "2.0.1"
        const val lottie_version = "3.3.1"

        const val lib_glide_version = "4.11.0"
        const val lib_play_services_version = "15.0.1"
        const val lib_material_version = "1.0.0"
        const val lib_apache_vesion = "3.7"
        const val lib_coroutines_version = "1.3.0"
        const val lib_joda_time = "2.9.9"

    }

    object AndroidXLibraries {
        val appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat_version}"
        val ktx_core = "androidx.core:core-ktx:${Versions.androidx_ktx_core_version}"
        val constraint_layout =
            "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintlayout_version}"
        val preferences = "androidx.preference:preference:${Versions.androidx_preferences_version}"
        val preferences_ktx =
            "androidx.preference:preference-ktx:${Versions.androidx_preferences_version}"
        val multidex = "androidx.multidex:multidex:${Versions.androidx_multidex_vesion}"
    }

    object ThirdPartyLibs {

        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
        const val retrofit_converter_gson =
            "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"
        const val retrofit_converter_scalars =
            "com.squareup.retrofit2:converter-scalars:${Versions.retrofit_scalars_factory_version}"
        const val retrofit_logging_interceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.retrofit_logging_interceptor_version}"
        const val apache_commons = "org.apache.commons:commons-lang3:${Versions.lib_apache_vesion}"
        const val joda_time = "joda-time:joda-time:${Versions.lib_joda_time}"

        const val koin_android = "org.koin:koin-android:${Versions.koin_version}"
        const val koin_androidx_scope = "org.koin:koin-androidx-scope:${Versions.koin_version}"
        const val koin_androidx_viewmodel =
            "org.koin:koin-androidx-viewmodel:${Versions.koin_version}"
        const val koin_androidx_extensions = "org.koin:koin-androidx-ext:${Versions.koin_version}"

        const val glide = "com.github.bumptech.glide:glide:${Versions.lib_glide_version}"
        const val glide_compiler =
            "com.github.bumptech.glide:compiler:${Versions.lib_glide_version}"
        const val glide_okhttp_integration =
            "com.github.bumptech.glide:okhttp3-integration:${Versions.lib_glide_version}"
        const val lottie = "com.airbnb.android:lottie:${Versions.lottie_version}"
    }

    object Kotlin {
        val kotlin_std = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_verion}"
        val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.lib_coroutines_version}"
    }

    object Tests {
        val junit = "junit:junit:${Versions.junit_version}"
        val junit_androidx_extension = "androidx.test.ext:junit:${Versions.junit_extension_version}"
        val espresso_extension_version =
            "androidx.test.espresso:espresso-core:${Versions.espresso_extension_version}"
        val instrumentation_runner = "androidx.test.runner.AndroidJUnitRunner"
    }

    object Proguard {
        const val consumer_rules = "consumer-rules.pro"
        const val proguard_rules = "proguard-rules.pro"
    }
}