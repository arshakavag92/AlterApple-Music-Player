package core

import org.gradle.api.JavaVersion

object Config {
    const val APPLICATION_ID = "com.arshak.freeiptv"
    val MIN_SDK_VERSION = 22
    val MAX_SDK_VERSION = 29
    val VERSION_CODE = 1
    val MULTIDEX_ENABLED = true
    val JAVA_VERSION = JavaVersion.VERSION_1_8
    const val COMPILE_SDK_VERSION = 29
    const val VERSION_NAME = "1.0"
}