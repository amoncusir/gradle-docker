package info.digitalpoet.gradle.docker

import org.gradle.api.tasks.TaskAction

/** <!-- Documentation for: info.digitalpoet.gradle.docker.DockerRemove on 4/09/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
open class DockerRemove: DockerBaseTask()
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    var id: String? = null

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    //~ Methods ========================================================================================================

    @TaskAction
    fun removeBuilds()
    {
        if (id == null && !buildHash.exists())
            throw IllegalArgumentException("Set a id prop or build a docker image with BuildDocker task")

        val image = id ?: buildHash.readText()

        println("Remove builds with image: $image")

        bash("docker", "rmi", "-f", image)()
            { "Docker Push failed, exited with result: $exitCode and message:\n${getStandardError()}" }
    }

    //~ Operators ======================================================================================================
}
