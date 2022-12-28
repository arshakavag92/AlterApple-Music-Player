package core

import org.gradle.api.JavaVersion

object Config {
    const val APPLICATION_ID = "com.arshak.freeiptv"
    const val MIN_SDK_VERSION = 23
    const val MAX_SDK_VERSION = 29
    const val VERSION_CODE = 1
    const val MULTIDEX_ENABLED = true
    val JAVA_VERSION = JavaVersion.VERSION_1_8
    const val COMPILE_SDK_VERSION = 31
    const val VERSION_NAME = "1.0"
}