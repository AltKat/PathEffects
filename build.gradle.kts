plugins {
    kotlin("jvm") version "2.0.20-Beta2"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "io.github.altkat"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") {
        name = "spigotmc-repo"
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
    maven {
        name = "essentialsxReleases"
        url = uri("https://repo.essentialsx.net/releases")
    }
    maven {
        name = "essentialsxSnapshots"
        url = uri("https://repo.essentialsx.net/snapshots")
    }
    maven {
        url = uri("https://papermc.io/repo/repository/maven-public/")
    }

}


dependencies {
    compileOnly("org.spigotmc:spigot-api:1.21-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.xerial:sqlite-jdbc:3.41.2.2")
    implementation("net.essentialsx:EssentialsX:2.20.1")
    implementation("io.papermc:paperlib:1.0.6")
}

val targetJavaVersion = 21
kotlin {
    jvmToolchain(targetJavaVersion)
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}
