pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
rootProject.name = "GithubApiPlayground"

include(":app")
include(":core:common")
include(":core:data")
include(":core:database")
include(":core:dispatcher")
include(":core:domain")
include(":core:model")
include(":core:navigation")
include(":core:network")
include(":core:ui")
include(":core:testing")
include(":feature:repodetail")
include(":feature:repolist")
