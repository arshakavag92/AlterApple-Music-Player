buildscript {
    extra["gradle_version"] = "3.5.0"
    extra["kotlin_version"] = "1.3.60"

    repositories {
        google()
        jcenter()
        maven(url = "https://plugins.gradle.org/m2/")

    }
    dependencies {
        classpath(core.Dependencies.Plugins.gradle)
        classpath(core.Dependencies.Plugins.kotlin_gradle_plugin)
        classpath(core.Dependencies.Plugins.navigation_safeargs_plugin)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://maven.google.com")
        maven(url = "https://jitpack.io")
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}