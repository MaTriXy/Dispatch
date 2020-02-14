import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.*

/*
 * Copyright (C) 2020 Rick Busarow
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
  id(Plugins.androidLibrary)
  id(Plugins.kotlinAndroid)
  id(Plugins.kotlinAndroidExtensions)
  id(Plugins.mavenPublish)
  id(Plugins.dokka)
}

android {
  compileSdkVersion(Versions.compileSdk)

  defaultConfig {
    minSdkVersion(Versions.minSdk)
    targetSdkVersion(Versions.targetSdk)
    versionName = Versions.versionName

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }
  }

  useLibrary("android.test.runner")
  useLibrary("android.test.base")
  useLibrary("android.test.mock")
}

dependencies {

  implementation(Libs.Androidx.Lifecycle.common)
  implementation(Libs.Androidx.Lifecycle.liveData)
  testImplementation(Libs.Androidx.Lifecycle.runtime)
  implementation(Libs.Androidx.Fragment.ktx) {
    exclude(group = "androidx.lifecycle", module = "lifecycle-runtime-ktx")
  }

  implementation(Libs.JakeWharton.timber)

  implementation(Libs.Kotlin.stdlib)

  implementation(Libs.Kotlinx.Coroutines.android)
  implementation(Libs.Kotlinx.Coroutines.core)

  implementation(project(":core"))
  implementation(project(":extensions"))
  testImplementation(project(":core-test-junit4"))
  testImplementation(project(":core-test-junit5"))
  testImplementation(project(":internal-test"))



  testImplementation(Libs.JUnit.jUnit5)
  testImplementation(Libs.KotlinTest.junit5runner)
  testImplementation(Libs.Kotlinx.Coroutines.test)
  testImplementation(Libs.MockK.core)

  debugImplementation(Libs.Androidx.Fragment.testing){
    exclude(group = "androidx.lifecycle", module = "lifecycle-runtime-ktx")
  }

  testImplementation(Libs.Androidx.testRunner)
  testImplementation(Libs.Androidx.espresso)
  testImplementation(Libs.Androidx.testRules)
  testImplementation(Libs.Androidx.testRunner)
  testImplementation(Libs.Robolectric.core)
}
