import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import edu.wpi.first.deployutils.deploy.artifact.FileTreeArtifact
import edu.wpi.first.gradlerio.deploy.roborio.FRCJavaArtifact
import edu.wpi.first.gradlerio.deploy.roborio.RoboRIO
import edu.wpi.first.toolchain.NativePlatforms

plugins {
    application
    id("edu.wpi.first.GradleRIO")
    id("com.github.johnrengelman.shadow")
}

configure<JavaApplication> {
    mainClass = "com.cpr3663.p2024.Main"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

group = "org.example"
version = "1.0-SNAPSHOT"

deploy {
    targets {
        create("roborio", RoboRIO::class.java) {
            team = project.frc.getTeamOrDefault(3663)
            debug = project.frc.getDebugOrDefault(false)

            this.artifacts {
                create("frcJava", FRCJavaArtifact::class.java) {
                    setJarTask(tasks.named<ShadowJar>("shadowJar").get())
                }

                create("frcStaticFileDeploy", FileTreeArtifact::class.java) {
                    files = project.fileTree("src/main/deploy")
                    directory = "/home/lvuser/deploy"
                }
            }
        }
    }
}

repositories {
    mavenCentral()

    maven {
        url = uri("https://maven.pkg.github.com/Mechanical-Advantage/AdvantageKit")
        credentials {
            username = "Mechanical-Advantage-Bot"
            password = "\u0067\u0068\u0070\u005f\u006e\u0056\u0051\u006a\u0055\u004f\u004c\u0061\u0079\u0066\u006e\u0078\u006e\u0037\u0051\u0049\u0054\u0042\u0032\u004c\u004a\u006d\u0055\u0070\u0073\u0031\u006d\u0037\u004c\u005a\u0030\u0076\u0062\u0070\u0063\u0051"
        }
    }
}

dependencies {
    wpi.java.deps.wpilib().forEach { addProvider("implementation", it) }
    wpi.java.vendor.java().forEach { addProvider("implementation", it) }

    wpi.java.deps.wpilibJniDebug(NativePlatforms.roborio).forEach { addProvider("roborioDebug", it) }
    wpi.java.vendor.jniDebug(NativePlatforms.roborio).forEach { addProvider("roborioDebug", it) }

    wpi.java.deps.wpilibJniRelease(NativePlatforms.roborio).forEach { addProvider("roborioRelease", it) }
    wpi.java.vendor.jniRelease(NativePlatforms.roborio).forEach { addProvider("roborioRelease", it) }

    wpi.java.deps.wpilibJniDebug(NativePlatforms.desktop).forEach { addProvider("nativeDebug", it) }
    wpi.java.vendor.jniDebug(NativePlatforms.desktop).forEach { addProvider("nativeDebug", it) }
    wpi.sim.enableDebug().forEach { addProvider("simulationDebug", it) }

    wpi.java.deps.wpilibJniRelease(NativePlatforms.desktop).forEach { addProvider("nativeRelease", it) }
    wpi.java.vendor.jniRelease(NativePlatforms.desktop).forEach { addProvider("nativeRelease", it) }
    wpi.sim.enableRelease().forEach { addProvider("simulationRelease", it) }

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

configurations.all {
    exclude(group = "edu.wpi.first.wpilibj")
}

tasks.test {
    useJUnitPlatform()
}

// Simulation configuration (e.g. environment variables).
wpi.sim.addGui().defaultEnabled = true
wpi.sim.addDriverstation()

// Configure jar and deploy tasks
wpi.java.configureExecutableTasks(tasks.named<ShadowJar>("shadowJar").get())
wpi.java.configureTestTasks(tasks.test.get())
wpi.java.debugJni.set(false)