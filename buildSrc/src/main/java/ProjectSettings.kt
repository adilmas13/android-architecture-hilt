//#############################
// GLOBAL PROJECT SETTINGS
//#############################
/*
*     const val versionCode =
        AppVersion.versionMajor * 10000
    + AppVersion.versionMinor * 100
    + AppVersion.versionPatch
* */

const val kotlinVersion = "1.4.10"
const val gradleVersion = "4.0.1"
const val navVersion = "2.2.1"
const val dokkaVersion = "1.4.0-rc"
const val hiltVersion = "2.28-alpha"

object AppVersion {
    const val versionMajor = 0
    const val versionMinor = 1
    const val versionPatch = 0
}

object AppConfig {
    const val applicationId = "com.androidarchitecture"
    const val compileSdkVersion = 29
    const val buildToolsVersion = "30.0.1"
    const val targetSdkVersion = 29
    const val minSdkVersion = 21 // android 5.0 and above

    val versionName = "$AppVersion.versionMajor.$AppVersion.versionMinor.$AppVersion.versionPatch"

    const val versionCode =
        (AppVersion.versionMajor * 10000) + (AppVersion.versionMinor * 100) + (AppVersion.versionPatch)

    const val enableCodeShrinking = true
    const val enableDebugLogs = false
    const val enableProguard = true
}

//#############################
// DEPENDENCIES DEFINITIONS
//#############################

const val retrofitVersion = "2.8.1"
const val okHttpVersion = "4.5.0"
const val coroutinesVersion = "1.3.9"
const val lifecycleVersion = "2.2.0"
const val multiDexVersion = "2.0.1"
const val coilVersion = "0.13.0"
const val hiltViewModelVersion = "1.0.0-alpha02"

object Dependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
    const val core = "androidx.core:core-ktx:1.3.1"
    const val appcompat = "androidx.appcompat:appcompat:1.2.0"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.1"
    const val viewPager = "androidx.viewpager2:viewpager2:1.0.0-beta04"
    const val recyclerView = "androidx.recyclerview:recyclerview:1.1.0-beta04"
    const val cardView = "androidx.cardview:cardview:1.0.0"
    const val designSupport = "com.google.android.material:material:1.2.0-alpha05"
    const val navigation = "androidx.navigation:navigation-fragment-ktx:$navVersion"
    const val navigation_ui = "androidx.navigation:navigation-ui-ktx:$navVersion"
    const val multidex = "androidx.multidex:multidex:$multiDexVersion"
    const val coil = "io.coil-kt:coil:$coilVersion"
    const val lifeCycleCompiler = "androidx.lifecycle:lifecycle-compiler:$lifecycleVersion"
    const val arch = "android.arch.lifecycle:extensions:1.1.1"
    const val hilt = "com.google.dagger:hilt-android:$hiltVersion"
    const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:$hiltViewModelVersion"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
    const val hiltViewModelCompiler = "androidx.hilt:hilt-compiler:$hiltViewModelVersion"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitConverter =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.7.0"
    const val okHttp3Logging = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
    const val javaX = "javax.inject:javax.inject:1"
    const val kotlinxSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.0-RC"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
}

object TestDependencies {
    const val junit = "junit:junit:4.12"
    const val runner = "androidx.test:runner:1.2.0"
    const val espresso = "androidx.test.espresso:espresso-core:3.2.0"
}

val appDependencies = mapOf(
    "kotlin" to Dependencies.kotlin,
    "core" to Dependencies.core,
    "appcompat" to Dependencies.appcompat,
    "constraintLayout" to Dependencies.constraintLayout,
    "viewPager" to Dependencies.viewPager,
    "recyclerView" to Dependencies.recyclerView,
    "cardView" to Dependencies.cardView,
    "designSupport" to Dependencies.designSupport,
    "navigation" to Dependencies.navigation,
    "navigation_ui" to Dependencies.navigation_ui,
    "multidex" to Dependencies.multidex,
    "coil" to Dependencies.coil,
    "lifeCycleCompiler" to Dependencies.lifeCycleCompiler,
    "arch" to Dependencies.arch,
    "hilt" to Dependencies.hilt,
    "hiltViewModel" to Dependencies.hiltViewModel
)

val annotationProcessingDependencies = mapOf(
    "hiltCompiler" to Dependencies.hiltCompiler,
    "hiltViewModelViewModelCompiler" to Dependencies.hiltViewModelCompiler
)

val dataDependencies = mapOf(
    "retrofit" to Dependencies.retrofit,
    "retrofitConverter" to Dependencies.retrofitConverter,
    "okHttp3Logging" to Dependencies.okHttp3Logging,
    "javaX" to Dependencies.javaX,
    "coroutines" to Dependencies.coroutines
)

val domainDependencies = mapOf(
    "kotlin" to Dependencies.kotlin,
    "kotlinxSerialization" to Dependencies.kotlinxSerialization,
    "coroutines" to Dependencies.coroutines,
    "coroutinesAndroid" to Dependencies.coroutinesAndroid,
    "javaX" to Dependencies.javaX
)

val unitTestDependencies = mapOf(
    "junit" to TestDependencies.junit
)

val uiTestDependencies = mapOf(
    "runner" to TestDependencies.runner,
    "espresso" to TestDependencies.espresso
)