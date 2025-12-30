plugins {
    id("java")
    id("org.springframework.boot")
}

dependencies {
    implementation(project(":infrastructure"))
    implementation(project(":controller"))
    implementation(project(":usecase"))
    implementation(project(":domain"))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}

// Habilita a criação do JAR Executável (Fat Jar) apenas para este módulo
tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = true
}

// Desabilita a criação do JAR simples (sem dependências)
tasks.getByName<Jar>("jar") {
    enabled = false
}

