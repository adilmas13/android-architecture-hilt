//apply plugin: 'com.android.library'
//apply plugin: 'kotlin-android'
//apply plugin: 'kotlin-android-extensions'
//apply plugin: 'kotlinx-serialization'
//apply from: '../project_settings.gradle'
//apply from: "../ktlint.gradle"
//apply from: "../dokka.gradle"

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    id("kotlinx-serialization")
}

android {
    compileSdkVersion (AppConfig.compileSdkVersion)
    buildToolsVersion (AppConfig.buildToolsVersion)

    defaultConfig {
        minSdkVersion (AppConfig.minSdkVersion)
        targetSdkVersion (AppConfig.targetSdkVersion)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner  = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles ("consumer-rules.pro")
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
        }
        getByName("release") {
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation (project(":domain"))
    DataDependencies.d.forEach { implementation(it) }
 //   implementation dataDependencies.values()
  //  implementation unitTestDependencies.values()
}