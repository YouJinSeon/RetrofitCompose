object Versions {
    const val compileSdk = 33

    const val minSdk = 24
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object AndroidX {
    const val core = "androidx.core:core-ktx:1.9.0"
    const val appcompat = "androidx.appcompat:appcompat:1.6.1"
    const val material = "com.google.android.material:material:1.9.0"

    object Lifecycle {
        private const val lifecycleVersion = "2.5.1"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
        const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion"
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
    }

}

object Kotlin {
    const val version = "1.9.10"
    const val coroutineVersion = "1.7.3"

    const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion"
    const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2"
    const val serializationPlugin = "org.jetbrains.kotlin:kotlin-serialization:$version"
}

object Test {
    const val junit = "junit:junit:4.13.2"
    const val junitExt = "androidx.test.ext:junit:1.1.3"
    const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
    const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:3.2.0"
}

object Dagger {
    private const val daggerVersion = "2.44"
    const val hiltAndroid = "com.google.dagger:hilt-android:$daggerVersion"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:$daggerVersion"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$daggerVersion"
}

const val inject = "javax.inject:javax.inject:1"
