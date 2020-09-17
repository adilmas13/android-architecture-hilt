//apply plugin: 'com.android.application'
//apply plugin: 'kotlin-android'
//apply plugin: 'kotlin-android-extensions'
//apply plugin: 'kotlin-kapt'
//apply plugin: 'androidx.navigation.safeargs'
//apply plugin: 'dagger.hilt.android.plugin'
//apply from: "../ktlint.gradle"
//apply from: "../dokka.gradle"

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs")
    id("dagger.hilt.android.plugin")
}

/*def keystorePropertiesFile = rootProject.file("keystore.properties")
def keystoreProperties = new Properties()
keystoreProperties.load(new FileInputStream(keystorePropertiesFile))*/

android {
/*    signingConfigs {
        config {
            keyAlias keystoreProperties['keyAlias']
            keyPassword keystoreProperties['keyPassword']
            storeFile file(keystoreProperties['storeFile'])
            storePassword keystoreProperties['storePassword']
        }
    }*/
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
            isDebuggable = false
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
//            signingConfig signingConfigs.config
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
//    sourceSets {
//        main.java.srcDirs += 'src/main/kotlin'
//        test.java.srcDirs += 'src/test/kotlin'
//    }
//    sourceSets {
////        main {
////            java.srcDir("src/main/kotlin")
////        }
////        test {
////            java.srcDir("src/test/kotlin")
////        }
//    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":data"))
    implementation(project(":domain"))
    /* implementation appDependencies.values()
     kapt annotationProcessingDependencies.values()
     testImplementation unitTestDependencies.values()
     androidTestImplementation uiTestDependencies.values()*/

    AppDependencies.now.forEach { implementation(it) }
    AnnotationProcessingDependencies.now.forEach { kapt(it) }
}
