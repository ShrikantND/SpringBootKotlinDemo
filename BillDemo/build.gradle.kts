import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22" apply false
	kotlin("plugin.jpa") version "1.7.22" apply false
	id("jacoco")
	id("org.springframework.boot") version "3.0.5" apply false
	id("io.spring.dependency-management") version "1.1.0" apply false
}

//jacoco {
//	toolVersion = "0.8.8"
//}

//tasks.test {
//	finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
//}
//tasks.jacocoTestReport {
//	dependsOn(tasks.test) // tests are required to run before generating the report
//}
//tasks.jacocoTestReport {
//	reports {
//		xml.required.set(false)
//		csv.required.set(false)
//		html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
//	}
//}

java.sourceCompatibility = JavaVersion.VERSION_17

allprojects {

	group = "com.bill.demo"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenLocal()
		mavenCentral()
	}
}

//val projectGroup: String by project
//val applicationVersion: String by project
//allprojects {
//	group = projectGroup
//	version = applicationVersion
//
//	repositories {
//		mavenCentral()
//	}
//}

subprojects {
	apply(plugin = "jacoco")
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")
	apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")

	dependencies {
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		testImplementation("org.springframework.boot:spring-boot-starter-test"){
			exclude("org.mockito:mockito-core")
		}
		testImplementation("com.ninja-squad:springmockk:3.0.1")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		implementation("io.jsonwebtoken:jjwt-api:0.11.5")
		implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
		implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
		implementation("org.springframework.boot:spring-boot-starter")
		implementation("org.springframework.boot:spring-boot-starter-web")
		implementation("org.springframework.boot:spring-boot-starter-security")
	}

	jacoco {
		toolVersion = "0.8.8"
	}

	tasks.getByName("bootJar") {
		enabled = false
	}

	tasks.getByName("jar") {
		enabled = true
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "17"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	tasks.test {
		finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
	}
	tasks.jacocoTestReport {
		dependsOn(tasks.test) // tests are required to run before generating the report
	}
	tasks.jacocoTestReport {
		reports {
			xml.required.set(false)
			csv.required.set(false)
			html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
		}
	}

}


