package info.digitalpoet.gradle.docker

import org.gradle.api.tasks.TaskAction
import javax.annotation.Nullable

/** <!-- Documentation for: info.digitalpoet.gradle.docker.DockerTag on 4/09/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
open class DockerTag: DockerBaseTask()
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    @get:Nullable
    var sourceImage: String? = null

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
    fun prepareTag()
    {
        if(sourceImage == null && !buildHash.exists())
            throw IllegalArgumentException("Need a sourceImage prop or build a docker image with BuildDocker task")

        val source = sourceImage ?: buildHash.readText()

        println("Tag source: $source")

        for (tag in tags!!)
        {
            println("Tag a docker image: source: $source - target: $tag")
            makeTag(source, tag)
        }
    }

    private fun makeTag(source: String, target: String)
    {
        bash("docker", "tag", source, target)()
            { "Docker Tag failed, exited with result: $exitCode and message:\n${getStandardError()}" }
    }

    //~ Operators ======================================================================================================
}
