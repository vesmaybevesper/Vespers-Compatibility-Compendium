plugins {
	id("mod-platform")
	id("dev.kikugie.loom-back-compat")
}

stonecutter {
	val (version, loader) = current.project.split('-', limit = 2)
	properties.tags(version, loader)

	replacements.string(current.parsed >= "1.21.11") {
		replace("ResourceLocation", "Identifier")
		replace("location()", "identifier()")
	}
	replacements.string(current.parsed >= "26.1.2") {
		replace("FabricDataOutput", "FabricPackOutput")
	}
}

val eslRawVersion = prop("deps.eveningstarlib")
val eslCleanVersion = eslRawVersion.replace(Regex("""(-(?:fabric|forge|neoforge|quilt))?\+.*$"""), "")

platform {
	loader = "fabric"
	dependencies {
		required("minecraft") {
			fabricLikeVersionRange = prop("deps.minecraft")
		}
		required("fabric-api") {
			slug("fabric-api")
			fabricLikeVersionRange = ">=${prop("deps.fabric-api")}"
		}
		required("fabricloader") {
			fabricLikeVersionRange = ">=${prop("deps.fabric-loader")}"
		}
		required("eveningstarlib") {
			slug("eveningstarlib")
			fabricLikeVersionRange = ">=$eslCleanVersion"
		}
		required("yet_another_config_lib_v3"){
			slug("yacl")
			fabricLikeVersionRange = ">=${prop("deps.yet_another_config_lib_v3")}"
		}
		optional("modmenu") {
			slug("modmenu")
			fabricLikeVersionRange = ">=${prop("deps.modmenu")}"
		}
		optional("wakes"){
			slug("wakes")
			fabricLikeVersionRange = ">=${prop("deps.wakes")}"
		}
		optional("effectual") {
			slug("effectual")
			fabricLikeVersionRange = ">=${prop("deps.effectual")}"
		}
		optional("entity_texture_features"){
			slug("entitytexturefeatures", "entity-texture-features-fabric")
			fabricLikeVersionRange = ">=${prop("deps.entity_texture_features")}"
		}
		optional("iceberg"){
			slug("iceberg")
			fabricLikeVersionRange = ">=${prop("deps.iceberg")}"
		}
		optional("jei"){
			slug("jei")
			fabricLikeVersionRange = ">=${prop("deps.jei")}"
		}
		if (stonecutter.current.parsed <= "1.21.11") {
			optional("particle-rain") {
				slug("particle-rain")
				fabricLikeVersionRange = ">=${prop("deps.particle-rain")}"
			}
		}
		if (stonecutter.current.parsed <= "1.21.1") {
			optional("effective") {
				slug("effective")
				fabricLikeVersionRange = ">=${prop("deps.effective")}"
			}
			optional("emi"){
				slug("emi")
				fabricLikeVersionRange = ">=1.1.7"
			}
			optional("jade") {
				slug("jade")
				fabricLikeVersionRange = ">=${prop("deps.jade")}"
			}
			optional("supplementaries"){
				slug("supplementaries")
				fabricLikeVersionRange = ">=${prop("deps.supplementaries")}"
			}
		}
	}
}

loom {
	accessWidenerPath = rootProject.file("src/main/resources/aw/${sc.current.version}.accesswidener")
	runs.named("client") {
		client()
		ideConfigGenerated(true)
		runDir = "run/"
		environment = "client"
		programArgs("--username=Dev")
		configName = "Fabric Client"
	}
	runs.named("server") {
		server()
		ideConfigGenerated(true)
		runDir = "run/"
		environment = "server"
		configName = "Fabric Server"
	}
}

fabricApi {
	configureDataGeneration {
		outputDirectory = file("${rootDir}/versions/datagen/${sc.current.version.split("-")[0]}/src/main/generated")
		client = true
	}
}

repositories {
	mavenCentral()
	strictMaven("https://maven.terraformersmc.com/", "com.terraformersmc") { name = "TerraformersMC" }
	strictMaven("https://api.modrinth.com/maven", "maven.modrinth") { name = "Modrinth" }
}

configurations.all {
	resolutionStrategy {
		force("net.fabricmc:fabric-loader:${prop("deps.fabric-loader")}")
	}
}

dependencies {
	minecraft("com.mojang:minecraft:${prop("deps.minecraft")}")
	if (sc.current.parsed < "26") {
		mappings(loom.layered {
			officialMojangMappings()
			if (hasProperty("deps.parchment"))
				parchment("org.parchmentmc.data:parchment-${prop("deps.parchment")}@zip")
		})
	}
	modImplementation("net.fabricmc:fabric-loader:${prop("deps.fabric-loader")}")
	// implementation(libs.moulberry.mixinconstraints)
	// include(libs.moulberry.mixinconstraints)
	modImplementation("net.fabricmc.fabric-api:fabric-api:${prop("deps.fabric-api")}")
	modCompileOnly("com.terraformersmc:modmenu:${prop("deps.modmenu")}")
	modImplementation("maven.modrinth:yacl:${prop("deps.yet_another_config_lib_v3")}")
	modImplementation("maven.modrinth:eveningstarlib:${prop("deps.eveningstarlib")}")
	if (sc.current.parsed <= "1.21.1") {
		modCompileOnly("maven.modrinth:effective:${prop("deps.effective")}")
		modCompileOnly("maven.modrinth:lodestonelib:${prop("deps.lodestone")}")
		modCompileOnly("maven.modrinth:jade:${prop("deps.jade")}")
		modCompileOnly("maven.modrinth:supplementaries:${prop("deps.supplementaries")}")
	}
	if (sc.current.parsed < "26"){
		modCompileOnly("maven.modrinth:particle-rain:${prop("deps.particle-rain")}")
	}
	modCompileOnly("maven.modrinth:architectury-api:${prop("deps.architectury-api")}")
	modCompileOnly("maven.modrinth:wakes:${prop("deps.wakes")}")
	modCompileOnly("maven.modrinth:effectual:${prop("deps.effectual")}")
	modCompileOnly("maven.modrinth:cloth-config:${prop("deps.cloth-config")}")
	modCompileOnly("maven.modrinth:entitytexturefeatures:${prop("deps.entity_texture_features")}")
	modCompileOnly("maven.modrinth:iceberg:${prop("deps.iceberg")}")
	modCompileOnly("maven.modrinth:jei:${prop("deps.jei")}")

}
