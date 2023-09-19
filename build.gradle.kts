// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.android.library") version "8.1.1" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.0.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.3")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.4.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")
    }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}