buildscript {
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
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}
