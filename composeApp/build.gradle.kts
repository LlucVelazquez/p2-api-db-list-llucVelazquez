//Això està al plugin composeMultiplatform
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
//Això està al  plugin kotlinMultiplatform
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeFeatureFlag
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree
//import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
//import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
//import org.jetbrains.kotlin.gradle.dsl.JvmTarget
//import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack
//import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig


plugins {
    alias(libs.plugins.kotlinMultiplatform)
    //alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)

    //SQL Delight
    //id("app.cash.sqldelight") version "2.0.2"
    alias(libs.plugins.sqldelight)
    kotlin("plugin.serialization") version "1.8.20"

}


sqldelight {
    databases {
        create("Database") {
            packageName.set("cat.itb.dam.m78.dbdemo3.db")
            schemaOutputDirectory.set(file("src/commonMain/sqldelight/databases"))
            //verifyMigrations.set(true)
        }
    }
}

//Bloque de configuración para Kotlin Multiplatform
kotlin {

    //Target JVM
    jvm("desktop")


    //Target Android

//    androidTarget {
//        @OptIn(ExperimentalKotlinGradlePluginApi::class)
//        compilerOptions {
//            jvmTarget.set(JvmTarget.JVM_11)
//        }
//    }
//
//    //Terget Wasm
//    @OptIn(ExperimentalWasmDsl::class)
//    wasmJs {
//        moduleName = "composeApp"
//        browser {
//            val rootDirPath = project.rootDir.path
//            val projectDirPath = project.projectDir.path
//            commonWebpackConfig {
//                outputFileName = "composeApp.js"
//                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
//                    static = (static ?: mutableListOf()).apply {
//                        // Serve sources to debug inside browser
//                        add(rootDirPath)
//                        add(projectDirPath)
//                    }
//                    open = mapOf("app" to mapOf(
//                                "name" to "google chrome", // "edge"
//                                "arguments" to listOf("--js-flags=--experimental-wasm-gc")
//                                )
//                            )
//                }
//            }
//        }
//        binaries.executable()
//    }



    //Bloque de configuración de dependències
    sourceSets {

        commonMain.dependencies {
            //implementation(compose.runtime)
            //implementation(compose.foundation)
            //implementation(compose.material)
            implementation(compose.material3)
            //implementation(compose.ui)
            //implementation(compose.components.resources)
            //implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            //implementation(libs.androidx.lifecycle.runtime.compose)
            //implementation("org.slf4j:slf4j-api:2.0.17")
            //implementation("org.slf4j:slf4j-simple:2.0.17")
            //SQL Delight
            //implementation(libs.delight.coroutines.extensions)
            //implementation("io.coil-kt.coil3:coil-compose:3.1.0")
            //implementation("io.coil-kt.coil3:coil-network-ktor3:3.1.0")
            implementation("io.ktor:ktor-serialization-kotlinx-json:3.1.0")
            implementation("io.ktor:ktor-client-core:3.1.0")
            implementation("io.ktor:ktor-client-cio:3.1.0")
            implementation("io.ktor:ktor-client-content-negotiation:3.1.0")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
            //implementation("com.russhwolf:multiplatform-settings-no-arg:1.3.0")
            //implementation("com.russhwolf:multiplatform-settings-serialization:1.3.0")
            //implementation("io.coil-kt.coil3:coil-compose:3.1.0")
            //implementation("io.coil-kt.coil3:coil-network-ktor3:3.1.0")
            implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.1")

        }

        val desktopMain by getting //Això és per que té un nom diferent al defalut
        desktopMain.dependencies {
            //Sql Delight
            implementation(libs.delight.desktop.driver)
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)

        }

//        androidMain.dependencies {
//            implementation(compose.preview)
//            implementation(libs.androidx.activity.compose)
//            //Sql Delight
//            implementation(libs.delight.android.driver)
//
//        }
//        nativeMain.dependencies {
//            implementation(libs.delight.native.driver)
//        }
    }
}

//Configuraciones específicas para escritorio
compose.desktop {
    application {
        mainClass = "cat.itb.dam.m78.dbdemo3.view.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "cat.itb.dam.m78.dbdemo3"
            packageVersion = "1.0.0"
        }
    }
}

//Configuraciones específicas de Android
//android {
//    namespace = "cat.itb.dam.m78.dbdemo3"
//    compileSdk = libs.versions.android.compileSdk.get().toInt()
//
//    defaultConfig {
//        applicationId = "cat.itb.dam.m78.dbdemo3"
//        minSdk = libs.versions.android.minSdk.get().toInt()
//        targetSdk = libs.versions.android.targetSdk.get().toInt()
//        versionCode = 1
//        versionName = "1.0"
//    }
//    packaging {
//        resources {
//            excludes += "/META-INF/{AL2.0,LGPL2.1}"
//        }
//    }
//    buildTypes {
//        getByName("release") {
//            isMinifyEnabled = false
//        }
//    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_11
//        targetCompatibility = JavaVersion.VERSION_11
//    }
//}

//dependencies {
//    debugImplementation(compose.uiTooling)
//}

