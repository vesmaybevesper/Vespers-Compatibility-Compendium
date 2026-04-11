plugins {
    `maven-publish`
    id("net.fabricmc.fabric-loom")
    //id("dev.kikugie.j52j")
    id("me.modmuss50.mod-publish-plugin")
}

class ModData {
    val id = property("mod.id").toString()
    val name = property("mod.name").toString()
    val version = property("mod.version").toString()
    val group = property("mod.group").toString()
}

class ModDependencies {
    operator fun get(name: String) = property("deps.$name").toString()
}

val mod = ModData()
val deps = ModDependencies()
val mcVersion = stonecutter.current.version
val mcDep = property("mod.mc_dep").toString()

version = "${mod.version}+$mcVersion"
group = mod.group
base { archivesName.set(mod.id) }

loom {
    splitEnvironmentSourceSets()

    mods {
        create("template") {
            sourceSet(sourceSets["main"])
            sourceSet(sourceSets["client"])
        }
    }
}

repositories {
    fun strictMaven(url: String, alias: String, vararg groups: String) = exclusiveContent {
        forRepository { maven(url) { name = alias } }
        filter { groups.forEach(::includeGroup) }
    }
    strictMaven("https://www.cursemaven.com", "CurseForge", "curse.maven")
    strictMaven("https://api.modrinth.com/maven", "Modrinth", "maven.modrinth")
    maven("https://mvn.devos.one/releases/")
    maven {
        name = "Gegy"
        url = uri("https://maven.gegy.dev/releases/")
    }
    maven {
        name = "ParchmentMC"
        url = uri("https://maven.parchmentmc.org")
    }
    maven { url = uri("https://maven.bawnorton.com/releases")}
    maven { url = uri("https://maven.enjarai.dev/mirrors")}
    maven("https://maven.isxander.dev/releases") {
        name = "Xander Maven"
    }
    maven("https://maven.terraformersmc.com/releases/")
    maven {
        name = "Terraformers"
        url = uri("https://maven.terraformersmc.com/")
    }
}

dependencies {
    fun fapi(vararg modules: String) = modules.forEach {
        implementation(fabricApi.module(it, deps["fabric_api"]))
    }

    minecraft("com.mojang:minecraft:$mcVersion")
    implementation("net.fabricmc:fabric-loader:${deps["fabric_loader"]}")
    implementation("net.fabricmc.fabric-api:fabric-api:${deps["fabric_api"]}")
    implementation("dev.isxander:yet-another-config-lib:${deps["yacl"]}")
    implementation ("maven.modrinth:eveningstarlib:${deps["esl"]}")
    compileOnly("com.terraformersmc:modmenu:${deps["modmenu"]}")
    //compileOnly("maven.modrinth:effective:${deps["effective"]}")
    //compileOnly("maven.modrinth:effectual:${deps["effectual"]}")
    compileOnly("maven.modrinth:particle-rain:${deps["particle-rain"]}")
    //compileOnly("maven.modrinth:lodestonelib:${deps["lodestone"]}")
    //compileOnly("maven.modrinth:wakes:${deps["wakes"]}")
    compileOnly("maven.modrinth:owo-lib:${deps["owo-lib"]}")
    //compileOnly("maven.modrinth:enchancement:${deps["enchancement"]}")
    //compileOnly("maven.modrinth:emi:${deps["emi"]}")
    compileOnly("maven.modrinth:entity-model-features:${deps["emf"]}")
    compileOnly("maven.modrinth:entitytexturefeatures:${deps["etf"]}")
    //compileOnly("maven.modrinth:iceberg:${deps["iceberg"]}")
    compileOnly("maven.modrinth:geckolib:${deps["geckolib"]}")
    //compileOnly("maven.modrinth:supplementaries:${deps["supplementaries"]}")
    compileOnly("maven.modrinth:jei:${deps["jei"]}")
    //compileOnly("maven.modrinth:betterf3:${deps["betterf3"]}")
    compileOnly("maven.modrinth:jade:${deps["jade"]}")
    compileOnly("maven.modrinth:mouse-tweaks:${deps["mousetweaks"]}")
    compileOnly("me.shedaniel.cloth:cloth-config-fabric:15.0.140")

fapi(
        // Add modules from https://github.com/FabricMC/fabric
        "fabric-lifecycle-events-v1",
    )
}

loom {
    decompilers {
        get("vineflower").apply { // Adds names to lambdas - useful for mixins
            options.put("mark-corresponding-synthetics", "1")
        }
    }

    runConfigs.all {
        ideConfigGenerated(true)
        vmArgs("-Dmixin.debug.export=true")
        runDir = "../../run"
    }
}

java {
    withSourcesJar()
    val java = JavaVersion.VERSION_25
    targetCompatibility = java
    sourceCompatibility = java
}

tasks.processResources {
    inputs.property("id", mod.id)
    inputs.property("name", mod.name)
    inputs.property("version", mod.version)
    inputs.property("mcdep", mcDep)

    val map = mapOf(
        "id" to mod.id,
        "name" to mod.name,
        "version" to mod.version,
        "mcdep" to mcDep
    )

    filesMatching("fabric.mod.json") { expand(map) }
}

tasks.register<Copy>("buildAndCollect") {
    group = "build"
    from(tasks.jar.get().archiveFile)
    into(rootProject.layout.buildDirectory.file("libs/${mod.version}"))
    dependsOn("build")
}


publishMods {
    file = tasks.jar.get().archiveFile
    displayName = "${mod.name} ${mod.version} for $mcVersion"
    version = mod.version
    changelog = rootProject.file("CHANGELOG.md").readText()
    type = ALPHA
    modLoaders.add("fabric")

    dryRun = false

    modrinth {
        projectId = property("publish.modrinth").toString()
        accessToken = ""
        minecraftVersions.add(mcVersion)
        requires {
            slug = "fabric-api"
            slug = "yacl"
            slug = "eveningstarlib"
        }
        optional {
            slug = "modmenu"
            slug = "entitytexturefeatures"
            slug = "jade"
            slug = "jei"
            slug = "mouse-tweaks"
        }
    }

    curseforge {
        projectId = property("publish.curseforge").toString()
        accessToken = ""
        minecraftVersions.add(mcVersion)
        requires {
            slug = "fabric-api"
            slug = "yacl"
            slug = "eveningstarlib"
        }
        optional {
            slug = "modmenu"
            slug = "entity-texture-features-fabric"
            slug = "jade"
            slug = "jei"
            slug = "mouse-tweaks"
        }
    }
}
publishing {
    repositories {
        maven("...") {
            name = "..."
            credentials(PasswordCredentials::class.java)
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }

    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "${property("mod.group")}.${mod.id}"
            artifactId = mod.version
            version = mcVersion

            from(components["java"])
        }
    }
}