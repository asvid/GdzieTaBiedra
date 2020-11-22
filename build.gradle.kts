buildscript {
    val kotlin_version by extra("1.4.10")
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath(ProjectDeps.gradlePlugin)
        classpath(ProjectDeps.kotlinPlugin)
        classpath(ProjectDeps.kotlinSerialization)
        classpath(ProjectDeps.kotlinExtensions)
        classpath(ProjectDeps.firebasePlugin) {
            exclude(group = "com.google.guava", module = "guava-jdk5")
        }
        classpath(ProjectDeps.googleServices)
        classpath(ProjectDeps.crashlytics)
        classpath(ProjectDeps.dokka)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}
