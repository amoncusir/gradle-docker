package info.digitalpoet.gradle.docker

import org.gradle.api.tasks.TaskAction
import javax.inject.Inject

/** <!-- Documentation for: info.digitalpoet.gradle.dockerBuild.BuildDocker on 31/08/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
@Suppress("unused")
open class BuildDocker private constructor(private val docker: DockerBuild)
    : DockerBaseTask(), DockerBuild by docker
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    @Inject
    constructor(): this(DefaultDockerBuilder())

    //~ Open Methods ===================================================================================================

    //~ Methods ========================================================================================================

    @TaskAction
    fun build()
    {
        if (!buildDir.exists()) throw IllegalStateException("Need prepare environment")

        buildImage(docker.getArguments())
    }

    /**
     *
     * @param arguments Array<String>
     * @return String
     */
    private fun buildImage(arguments: Array<String>)
    {
        val args = arrayListOf("docker", "build", "--iidfile", buildHash.absolutePath).plus(arguments)

        println("Build Docker: ${args.joinToString(" ")}")

        bash(args)() { "Docker Build failed, exited with result: $exitCode and message:\n${getStandardError()}" }
    }

    //~ Operators ======================================================================================================
}
