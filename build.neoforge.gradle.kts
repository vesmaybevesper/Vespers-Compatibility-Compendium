plugins {
	id("mod-platform")
	id("net.neoforged.moddev")
}

stonecutter {
	val (version, loader) = current.project.split('-', limit = 2)
	properties.tags(version, loader)

	replacements.string(current.parsed >= "1.21.11") {
		replace("ResourceLocation", "Identifier")
		replace("location()", "identifier()")
	}
}

val eslRawVersion = prop("deps.eveningstarlib")
val eslCleanVersion = eslRawVersion.replace(Regex("""(-(?:fabric|forge|neoforge|quilt))?\+.*$"""), "")

platform {
	loader = "neoforge"
	dependencies {
		required("minecraft") {
			forgeLikeVersionRange = prop("deps.minecraft")
		}
		required("neoforge") {
			forgeLikeVersionRange.set("[1,)")
		}
		required("eveningstarlib") {
			slug("eveningstarlib")
			fabricLikeVersionRange = ">=$eslCleanVersion"
		}
		required("yet_another_config_lib_v3"){
			slug("yacl")
			fabricLikeVersionRange = ">=${prop("deps.yet_another_config_lib_v3")}"
		}
		optional("wakes"){
			slug("wakes-reforged")
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
		if (stonecutter.current.parsed.equals("1.21.1")) {
			optional("particle-rain") {
				slug("particle-rain")
				fabricLikeVersionRange = ">=${prop("deps.particle-rain")}"
			}
		}
	}
}

neoForge {
	version = prop("deps.neoforge")
	accessTransformers.from(rootProject.file("src/main/resources/aw/${stonecutter.current.version}.cfg"))
	validateAccessTransformers = true

	if (hasProperty("deps.parchment")) parchment {
		val (mc, ver) = prop("deps.parchment").split(':')
		mappingsVersion = ver
		minecraftVersion = mc
	}

	runs {
		register("client") {
			client()
			gameDirectory = file("run/")
			ideName = "NeoForge Client (${stonecutter.current.version})"
			programArgument("--username=Dev")
		}
		register("server") {
			server()
			gameDirectory = file("run/")
			ideName = "NeoForge Server (${stonecutter.current.version})"
		}
	}

	mods {
		register(prop("mod.id")) {
			sourceSet(sourceSets["main"])
		}
	}
	sourceSets["main"].resources.srcDir("${rootDir}/versions/datagen/${sc.current.version.split("-")[0]}/src/main/generated")
}

repositories {
	mavenCentral()
	strictMaven("https://api.modrinth.com/maven", "maven.modrinth") { name = "Modrinth" }
}

dependencies {
	// implementation(libs.moulberry.mixinconstraints)
	// jarJar(libs.moulberry.mixinconstraints)
	implementation("maven.modrinth:yacl:${prop("deps.yet_another_config_lib_v3")}")
	implementation("maven.modrinth:eveningstarlib:${prop("deps.eveningstarlib")}")
	if (stonecutter.current.parsed.equals("1.21.1")) {
		compileOnly("maven.modrinth:particle-rain:${prop("deps.particle-rain")}")
	}
	compileOnly("maven.modrinth:architectury-api:${prop("deps.architectury-api")}")
	compileOnly("maven.modrinth:wakes-reforged:${prop("deps.wakes")}")
	compileOnly("maven.modrinth:effectual:${prop("deps.effectual")}")
	compileOnly("maven.modrinth:cloth-config:${prop("deps.cloth-config")}")
	compileOnly("maven.modrinth:entitytexturefeatures:${prop("deps.entity_texture_features")}")
	compileOnly("maven.modrinth:iceberg:${prop("deps.iceberg")}")
	compileOnly("maven.modrinth:jei:${prop("deps.jei")}")
}

tasks.named("createMinecraftArtifacts") {
	dependsOn(tasks.named("stonecutterGenerate"))
}
