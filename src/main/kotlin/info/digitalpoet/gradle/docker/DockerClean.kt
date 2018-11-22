package info.digitalpoet.gradle.docker

import org.gradle.api.tasks.TaskAction

/** <!-- Documentation for: info.digitalpoet.gradle.docker.DockerClean on 3/09/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
open class DockerClean: DockerBaseTask()
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    //~ Methods ========================================================================================================

    @TaskAction
    fun cleanDockerBuild()
    {
        if (buildDir.exists())
        {
            println("Clean docker build folder: ${buildDir.absolutePath}")
            buildDir.deleteRecursively()
        }
    }

    //~ Operators ======================================================================================================
}
