// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("org.jetbrains.dokka") version "1.4.0"
}
buildscript {

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:$gradleVersion")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
        classpath ("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
        classpath ("org.jetbrains.dokka:dokka-gradle-plugin:$dokkaVersion")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete (rootProject.buildDir)
}

val ktlint by configurations.creating

dependencies {
    ktlint("com.pinterest:ktlint:0.39.0")
    // ktlint(project(":custom-ktlint-ruleset")) // in case of custom ruleset
}

val outputDir = "${project.buildDir}/reports/ktlint/"
val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))

val ktlintCheck by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Check Kotlin code style."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args = listOf("src/**/*.kt")
}

val ktlintFormat by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Fix Kotlin code style deviations."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args = listOf("-F", "src/**/*.kt")
}
