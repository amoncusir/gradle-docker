package info.digitalpoet.gradle.docker

import org.gradle.api.tasks.TaskAction

/** <!-- Documentation for: info.digitalpoet.gradle.docker.DockerPrepare on 3/09/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
open class DockerPrepare: DockerBaseTask()
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    //~ Methods ========================================================================================================

    @TaskAction
    fun prepareEnvironment()
    {
        println("Init Docker environment")

        if (!buildDir.exists())
        {
            println("Creating docker build folder: ${buildDir.absolutePath}")
            buildDir.mkdirs()
        }

//        if (!buildProperties.exists())
//        {
//            println("Creating docker build properties: ${buildProperties.absolutePath}")
//            buildProperties.createNewFile()
//        }
    }

    //~ Operators ======================================================================================================
}
