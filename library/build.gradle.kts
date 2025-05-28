plugins {
    alias(libs.plugins.android.library)
    `maven-publish`
}

android {
    namespace = "com.shockwave.pdfium"
    compileSdk = 35
    ndkVersion = "27.1.12297006"

    defaultConfig {
        minSdk = 21
        buildConfigField("String","VERSION_NAME","\"${defaultConfig.versionName}\"")

        externalNativeBuild {
            cmake {
                arguments("-DANDROID_SUPPORT_FLEXIBLE_PAGE_SIZES=ON")
            }
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    externalNativeBuild {
        cmake {
            path("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }

    buildFeatures {
        buildConfig = true
    }

    publishing {
        singleVariant("release")
    }
}

dependencies {
    implementation(libs.androidx.core)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "com.aldagram"
                artifactId = "PdfiumAndroid"
                version = "2.0.0"

                from(components["release"])
            }
        }
    }
}
