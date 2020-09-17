//apply plugin: 'java-library'
//apply plugin: 'kotlin'
//apply plugin: 'kotlinx-serialization'
//apply from: "../ktlint.gradle"
//apply from: "../dokka.gradle"

plugins {
  `java-library`
  id("kotlin")
  id("kotlinx-serialization")
}


dependencies {
  //  implementation domainDependencies.values()
  DomainDependencies.d.forEach({implementation(it)})
}
