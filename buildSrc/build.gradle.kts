import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}


open class BluesquarePlugin : Plugin<Project> {
    override fun apply(project: Project) {

    }
}