rootProject.name = "platform"
include("libraries")
include("libraries:parser")
findProject(":libraries:parser")?.name = "parser"
include("libraries:http-client")
findProject(":libraries:http-client")?.name = "http-client"
include("integration")
include("integration:yamlquest-cli")
findProject(":integration:yamlquest-cli")?.name = "yamlquest-cli"
