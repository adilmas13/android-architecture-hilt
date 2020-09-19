// TODO: ktlint, dotta
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs")
    id("dagger.hilt.android.plugin")
}

android {
    signingConfigs{
        getByName("debug"){
            storeFile = file("debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
        create("release") {
            keyAlias = "android"
            keyPassword = "architecture"
            storeFile = file("../keystore")
            storePassword = "android"
        }
    }

    compileSdkVersion(AppConfig.compileSdkVersion)
    buildToolsVersion(AppConfig.buildToolsVersion)
    defaultConfig {
        applicationId = (AppConfig.applicationId)
        minSdkVersion(AppConfig.minSdkVersion)
        targetSdkVersion(AppConfig.targetSdkVersion)
        versionCode = (AppConfig.versionCode)
        versionName = (AppConfig.versionName)
        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true
        resConfigs("en") //including only english resource files - done to reduce apk size
    }
    buildTypes {
        getByName("release") {
            isDebuggable = AppConfig.enableDebugLogs
            isShrinkResources = AppConfig.enableCodeShrinking
            isMinifyEnabled = AppConfig.enableProguard
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
            buildConfigField("String", "BASE_URL", "\"https://reqres.in/\"")
        }
        getByName("debug") {
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-dev"
            buildConfigField("String", "BASE_URL", "\"https://reqres.in/\"")
        }
    }
    compileOptions { // adding Java 8 bytecode since Coil, Hilt requires it
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets {
        getByName("main") { java.srcDirs(file("./src/main/kotlin")) }
        getByName("test") { java.srcDirs(file("src/test/kotlin")) }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":data"))
    implementation(project(":domain"))
    appDependencies.forEach { (_, v) -> implementation(v) }
    annotationProcessingDependencies.forEach { (_, v) -> kapt(v) }
    unitTestDependencies.forEach { (_, v) -> testImplementation(v) }
    uiTestDependencies.forEach { (_, v) -> androidTestImplementation(v) }
}
