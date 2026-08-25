plugins {
	id("mod-platform")
	id("net.neoforged.moddev.legacyforge")
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
	loader = "forge"
	dependencies {
		required("minecraft") {
			forgeLikeVersionRange = prop("deps.minecraft")
		}
		required("forge") {
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
		optional("particle-rain") {
			slug("particle-rain")
			fabricLikeVersionRange = ">=${prop("deps.particle-rain")}"
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

legacyForge {
	version = "${prop("deps.minecraft")}-${prop("deps.forge")}"

	validateAccessTransformers = true

	accessTransformers.from(
		rootProject.file("src/main/resources/aw/${sc.current.version}.cfg")
	)

	runs {
		register("client") {
			client()
			gameDirectory = file("run/")
			ideName = "Forge Client (${sc.current.version})"
			programArgument("--username=Dev")
		}
		register("server") {
			server()
			gameDirectory = file("run/")
			ideName = "Forge Server (${sc.current.version})"
		}
	}


	mods {
		register(prop("mod.id")) {
			sourceSet(sourceSets["main"])
		}
	}
}

mixin {
	add(sourceSets.main.get(), "${prop("mod.id")}.mixins.refmap.json")
	config("${prop("mod.id")}.mixins.json")
}

repositories {
	mavenCentral()
	strictMaven("https://api.modrinth.com/maven", "maven.modrinth") { name = "Modrinth" }
}

dependencies {
	annotationProcessor("org.spongepowered:mixin:${libs.versions.mixin.get()}:processor")

	// implementation(libs.moulberry.mixinconstraints)
	// jarJar(libs.moulberry.mixinconstraints)

	modImplementation("maven.modrinth:yacl:${prop("deps.yet_another_config_lib_v3")}")
	modImplementation("maven.modrinth:eveningstarlib:${prop("deps.eveningstarlib")}")
	modCompileOnly("maven.modrinth:jade:${prop("deps.jade")}")
	modCompileOnly("maven.modrinth:supplementaries:${prop("deps.supplementaries")}")
	modCompileOnly("maven.modrinth:particle-rain:${prop("deps.particle-rain")}")
	modCompileOnly("maven.modrinth:architectury-api:${prop("deps.architectury-api")}")
	modCompileOnly("maven.modrinth:wakes-reforged:${prop("deps.wakes")}")
	modCompileOnly("maven.modrinth:effectual:${prop("deps.effectual")}")
	modCompileOnly("maven.modrinth:cloth-config:${prop("deps.cloth-config")}")
	modCompileOnly("maven.modrinth:entitytexturefeatures:${prop("deps.entity_texture_features")}")
	modCompileOnly("maven.modrinth:iceberg:${prop("deps.iceberg")}")
	modCompileOnly("maven.modrinth:jei:${prop("deps.jei")}")
}

sourceSets {
	main {
		resources.srcDir(
			"${rootDir}/versions/datagen/${sc.current.version.split("-")[0]}/src/main/generated"
		)
	}
}

tasks.named("createMinecraftArtifacts") {
	dependsOn(tasks.named("stonecutterGenerate"))
}
