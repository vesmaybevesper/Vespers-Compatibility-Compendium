plugins {
    `maven-publish`
    id("fabric-loom")
    //id("dev.kikugie.j52j")
    //id("me.modmuss50.mod-publish-plugin")
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
}

dependencies {
    fun fapi(vararg modules: String) = modules.forEach {
        modImplementation(fabricApi.module(it, deps["fabric_api"]))
    }

    minecraft("com.mojang:minecraft:$mcVersion")
    @Suppress("UnstableApiUsage")
    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-1.21.1:2024.11.17@zip")
        //mappings("dev.lambdaurora:yalmm:1.21.1+build.7")
    })
    modImplementation("net.fabricmc:fabric-loader:${deps["fabric_loader"]}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${deps["fabric_api"]}")
    modImplementation("dev.isxander:yet-another-config-lib:${deps["yacl"]}")
    modImplementation ("maven.modrinth:eveningstarlib:${deps["esl"]}")
    modCompileOnly("maven.modrinth:modmenu:${deps["modmenu"]}")
    // modCompileOnly("maven.modrinth:effective:${deps["effective"]}")
    modCompileOnly("maven.modrinth:effectual:${deps["effectual"]}")
    modCompileOnly("maven.modrinth:particle-rain:${deps["particle-rain"]}")
    //modCompileOnly("maven.modrinth:lodestonelib:${deps["lodestone"]}")
    modCompileOnly("maven.modrinth:wakes:${deps["wakes"]}")
    modCompileOnly("maven.modrinth:owo-lib:${deps["owo-lib"]}")
    //modCompileOnly("maven.modrinth:enchancement:${deps["enchancement"]}")
    //modCompileOnly("maven.modrinth:emi:${deps["emi"]}")
    modCompileOnly("maven.modrinth:entity-model-features:${deps["emf"]}")
    modCompileOnly("maven.modrinth:entitytexturefeatures:${deps["etf"]}")
    //modCompileOnly("maven.modrinth:iceberg:${deps["iceberg"]}")
    modCompileOnly("maven.modrinth:geckolib:${deps["geckolib"]}")
    //modCompileOnly("maven.modrinth:supplementaries:${deps["supplementaries"]}")
    modCompileOnly("maven.modrinth:jei:${deps["jei"]}")
    modCompileOnly("maven.modrinth:betterf3:${deps["betterf3"]}")
    modCompileOnly("maven.modrinth:jade:${deps["jade"]}")
    modCompileOnly("maven.modrinth:particular-reforged:${deps["particular"]}")
    /*implementation("com.github.bawnorton.mixinsquared:mixinsquared-fabric:0.3.6-beta.1")
    include("com.github.bawnorton.mixinsquared:mixinsquared-fabric:0.3.6-beta.1")
    annotationProcessor("com.github.bawnorton.mixinsquared:mixinsquared-fabric:0.3.6-beta.1")*/



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
    val java = if (stonecutter.eval(mcVersion, ">=1.20.6")) JavaVersion.VERSION_21 else JavaVersion.VERSION_17
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
    from(tasks.remapJar.get().archiveFile)
    into(rootProject.layout.buildDirectory.file("libs/${mod.version}"))
    dependsOn("build")
}

/*
publishMods {
    file = tasks.remapJar.get().archiveFile
    additionalFiles.from(tasks.remapSourcesJar.get().archiveFile)
    displayName = "${mod.name} ${mod.version} for $mcVersion"
    version = mod.version
    changelog = rootProject.file("CHANGELOG.md").readText()
    type = BETA
    modLoaders.add("fabric", "quilt")

    dryRun = providers.environmentVariable("MODRINTH_TOKEN")
        .getOrNull() == null || providers.environmentVariable("CURSEFORGE_TOKEN").getOrNull() == null

    modrinth {
        projectId = property("publish.modrinth").toString()
        accessToken = providers.environmentVariable("MODRINTH_TOKEN")
        minecraftVersions.add(mcVersion)
        requires {
            slug = "fabric-api"
        }
    }

    curseforge {
        projectId = property("publish.curseforge").toString()
        accessToken = providers.environmentVariable("CURSEFORGE_TOKEN")
        minecraftVersions.add(mcVersion)
        requires {
            slug = "fabric-api"
        }
    }
}
*/
/*
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
*/