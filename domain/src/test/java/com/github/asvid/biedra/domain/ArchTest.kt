package com.github.asvid.biedra.domain

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses

@AnalyzeClasses(packages = ["com.github.asvid.biedra"])
class ArchTest {

    @ArchTest
    fun `no classes should depend on Android SDK`(importedClasses: JavaClasses) {
        val rule = noClasses().should().dependOnClassesThat().resideInAPackage("..android..")
        rule.check(importedClasses)
    }

    @ArchTest
    fun `JodaTime shouldn't be used in domain package`(importedClasses: JavaClasses) {
        val rule = noClasses().should().dependOnClassesThat().resideInAPackage("..joda..")
        rule.check(importedClasses)
    }
}