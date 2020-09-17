// Top-level build file where you can add configuration options common to all sub-projects/modules.
/*apply plugin: 'org.jetbrains.dokka'*/
//
//plugins{
//    id("org.jetbrains.dokka")
//}
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