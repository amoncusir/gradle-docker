package info.digitalpoet.gradle.docker

import groovy.lang.GString
import javax.annotation.Nullable

/** <!-- Documentation for: info.digitalpoet.gradle.dockerBuild.DockerBuild on 31/08/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface DockerBuild
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    var imageName: String?

    var imageVersion: String?

    var dockerfile: String

    var context: String

    var noCache: Boolean

    var latestTag: Boolean

    var labels: Map<String, GString>

    var buildArgs: Map<String, GString>

    var args: Map<String, GString>

    //~ Properties =====================================================================================================

    //~ Methods ========================================================================================================

    fun getArguments(): Array<String>

    //~ Operators ======================================================================================================
}

class DefaultDockerBuilder: DockerBuild
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    @get:Nullable
    override var imageName: String? = null

    @get:Nullable
    override var imageVersion: String? = null

    override var dockerfile: String =  "./Dockerfile"

    override var context: String =  "."

    override var noCache: Boolean = false

    override var latestTag: Boolean = true

    override var labels: Map<String, GString> = mutableMapOf()

    override var buildArgs: Map<String, GString> = mutableMapOf()

    override var args: Map<String, GString> = mutableMapOf()

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    //~ Methods ========================================================================================================

    fun getMainTag(): Array<String> = arrayOf("-t", "${imageName!!}:${imageVersion!!}")

    fun getDockerfileArg(): Array<String> = arrayOf("--file", dockerfile)

    fun getContext(): Array<String> = arrayOf(context)

    fun getNoCache(): Array<String> = if(noCache) arrayOf("--no-cache") else arrayOf()

    fun getLatestTag(): Array<String> = if(latestTag) arrayOf("-t", "${imageName!!}:latest") else arrayOf()

    fun getLabels(): Array<String> = if (labels.isEmpty()) arrayOf() else labels
        .map { (k, v) -> arrayOf("--label", "$k=$v") }
        .reduce { acc: Array<String>, strings: Array<String> -> acc.plus(strings) }

    fun getBuildArgs(): Array<String> = if (buildArgs.isEmpty()) arrayOf() else buildArgs
        .map { (k, v) -> arrayOf("--build-arg", "$k=$v") }
        .reduce { acc: Array<String>, strings: Array<String> -> acc.plus(strings) }

    fun getArgs(): Array<String> = if (args.isEmpty()) arrayOf() else args
            .map { (k, v) -> arrayOf(k, "$v") }
            .reduce { acc: Array<String>, strings: Array<String> -> acc.plus(strings) }

    override fun getArguments(): Array<String> = arrayOf<String>()
        .plus(getNoCache())
        .plus(getDockerfileArg())
        .plus(getMainTag())
        .plus(getLatestTag())
        .plus(getLabels())
        .plus(getBuildArgs())
        .plus(getArgs())
        .plus(getContext())

    //~ Operators ======================================================================================================
}
