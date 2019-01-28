import com.github.grishberg.performance.PerformanceLauncher

fun main(args: Array<String>) {

    val launcher = FileSourcePerformanceLauncher(PerformanceLauncher())
    launcher.readSourcesAndLaunch()
    System.exit(0)
    println("Done")
}