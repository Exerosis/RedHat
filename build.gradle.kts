plugins { kotlin("jvm").version("1.4.0") }

group = "com.github.exerosis.redhat"
version = "1.0.0"

repositories { mavenCentral() }

tasks.compileKotlin { kotlinOptions.jvmTarget = "1.8" }