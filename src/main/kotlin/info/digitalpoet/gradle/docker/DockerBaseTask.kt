package info.digitalpoet.gradle.docker

import info.digitalpoet.gradle.shell.Command
import info.digitalpoet.gradle.shell.CommandError
import info.digitalpoet.gradle.shell.CommandResult
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.OutputFile
import java.io.File

/** <!-- Documentation for: info.digitalpoet.gradle.dockerBuild.DockerBaseTask on 31/08/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
open class DockerBaseTask: DefaultTask()
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    @get:OutputDirectory
    val buildDir: File = project.buildDir.resolve("docker")

//    @get:OutputFile
//    val buildProperties: File = buildDir.resolve("build.properties")

    @get:OutputFile
    val buildHash: File = buildDir.resolve("hash")

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    //~ Methods ========================================================================================================

    protected fun bash(args: List<String>): Command = Command(args).apply { directory = project.projectDir }

    protected fun bash(vararg args: String): Command = bash(args.asList())

    protected operator fun Command.invoke(error: CommandResult.() -> String)
    {
        val result = this(redirect = listOf(System.out))

        if (!result.hasSuccess())
        {
            throw CommandError(error(result))
        }
    }

    //~ Operators ======================================================================================================
}
