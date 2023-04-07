tasks.getByName("bootJar") {
	enabled = true
}

tasks.getByName("jar") {
	enabled = false
}

dependencies {
	//implementation(":movie-entity")
	api(project(":movie-entity"))
	api(project(":movie-security"))
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	testImplementation("org.springframework.security:spring-security-test")
	//runtimeOnly("com.h2database:h2")
}