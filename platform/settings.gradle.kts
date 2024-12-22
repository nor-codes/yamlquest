rootProject.name = "platform"
include("libraries")
include("libraries:parser")
findProject(":libraries:parser")?.name = "parser"
include("libraries:http-client")
findProject(":libraries:http-client")?.name = "http-client"
