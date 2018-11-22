package info.digitalpoet.gradle.docker

import org.gradle.api.tasks.TaskAction

/** <!-- Documentation for: info.digitalpoet.gradle.docker.DockerPush on 4/09/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
open class DockerPush: DockerBaseTask()
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    private var tags: List<String>? = null

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    //~ Methods ========================================================================================================

    fun tags(tags: Collection<String>?)
    {
        this.tags = tags?.toList()
    }

    fun tags(tags: Array<String>?)
    {
        this.tags = tags?.toList()
    }

    @TaskAction
    fun preparePush()
    {
        println("Push docker tags: $tags")

        for (tag in tags!!)
        {
            println("Push docker image: $tag")
            push(tag)
        }
    }

    private fun push(tag: String)
    {
        bash("docker", "push", tag)()
            { "Docker Push failed, exited with result: $exitCode and message:\n${getStandardError()}" }
    }

    //~ Operators ======================================================================================================
}
