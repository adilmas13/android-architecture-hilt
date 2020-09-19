// TODO: ktlint, dotta
plugins {
  `java-library`
  id("kotlin")
  id("kotlinx-serialization")
}

dependencies {
  domainDependencies.forEach { (_, v) -> implementation(v) }
}
