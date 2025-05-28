plugins {
    alias(libs.plugins.android.library)
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
