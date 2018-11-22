# Docker #

A gradle task for run docker commands and build your project.


an simple example to set tasks for upload in aws ECR:
```groovy
def awsRepo =  "XXXXXXXXXXXX.dkr.ecr.eu-west-1.amazonaws.com/service/foo"

def awsTags = [ "${awsRepo}:latest".toString(), "${awsRepo}:${project.version}".toString() ]

task dockerClean(type: DockerClean) {}

task dockerPrepare(type: DockerPrepare) {
    dependsOn dockerClean
}

task dockerBuild(type: BuildDocker) {
    dependsOn dockerPrepare

    imageName "${bootJar.baseName}"
    imageVersion "${project.version}"

    latestTag true

    labels version: "${project.version}"
    buildArgs JAR_PATH: "./build/libs/${bootJar.baseName}-${project.version}.jar"
}

task dockerRemoveBuilds(type: DockerRemove) {}

task awsDockerTag(type: DockerTag) {
    tags awsTags
}

task awsDockerPush(type: DockerPush) {
    tags awsTags
}

task makeUserCredentials(type: Shell) {
    interpreter "/bin/bash", "-c"
    sh "\$(aws ecr get-login --no-include-email --region eu-west-1)"
}
```

And run gradle command: `$ ./gradlew --rerun-tasks makeUserCredentials dockerClean dockerPrepare dockerBuild awsDockerTag awsDockerPush dockerRemoveBuilds`
