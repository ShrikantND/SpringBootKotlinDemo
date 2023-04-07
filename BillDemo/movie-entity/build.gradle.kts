
//plugins {
//	kotlin("plugin.allopen") version "1.8.0"
//
//}
//
//allOpen {
//	annotation("jakarta.persistence.Entity")
//	annotation("jakarta.persistence.Embeddable")
//	annotation("jakarta.persistence.MappedSuperclass")
//}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("com.h2database:h2")
}

