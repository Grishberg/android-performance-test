package com.github.grishberg.performance.command

import com.github.grishberg.tests.ConnectedDeviceWrapper
import com.github.grishberg.tests.common.RunnerLogger
import java.io.BufferedReader
import java.io.InputStreamReader



private const val TAG = "AssempleApkCommand"

class AssempleApkCommand(
        private val logger: RunnerLogger
) : LauncherCommand {
    override fun execute(device: ConnectedDeviceWrapper) {
        val command = "./assemble.sh"


        val tr = Runtime.getRuntime().exec(arrayOf(command))
        val rd = BufferedReader(InputStreamReader(tr.inputStream))
        val s = rd.readLine()
        println(s)
        /*
        val p = Runtime.getRuntime().exec(command)
        p.waitFor()

        val reader = BufferedReader(InputStreamReader(p.inputStream))

        var line: String?
        while (true) {
            line = reader.readLine()
            if (line == null) {
                break
            }
            logger.i(TAG, line)
        }
        */
    }
}