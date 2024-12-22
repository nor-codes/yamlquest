rootProject.name = "platform"
include("libraries")
include("libraries:parser")
findProject(":libraries:parser")?.name = "parser"
include("libraries:executor")
findProject(":libraries:executor")?.name = "executor"
