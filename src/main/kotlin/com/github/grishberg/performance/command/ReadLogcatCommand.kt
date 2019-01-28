package com.github.grishberg.performance.command

import com.github.grishberg.performance.ResultsPrinter
import com.github.grishberg.tests.ConnectedDeviceWrapper

private const val READ_LOGCAT_COMMAND = "logcat -v threadtime -d"
private const val CLEAR_LOGCAT_COMMAND = "logcat -c"

/**
 * Reads logcat and parses duration result.
 */
class ReadLogcatCommand(
        private val resultsPrinter: ResultsPrinter
) : LauncherCommand {

    override fun execute(device: ConnectedDeviceWrapper) {
        val logcatOutput = device.executeShellCommandAndReturnOutput(READ_LOGCAT_COMMAND)
        val regex = "\\[PERF\\]\\s+\\:\\s\\(d1=(\\d+), d2=(\\d+)\\)".toRegex()
        for (i in 0 until 10) {
            val results = regex.find(logcatOutput)
            if (results != null) {
                val duration1 = results.groupValues[1].toLong()
                val duration2 = results.groupValues[2].toLong()
                resultsPrinter.populateResult(device, duration1, duration2)
                break
            }
            Thread.sleep(500)
        }
        device.executeShellCommand(CLEAR_LOGCAT_COMMAND)
    }
}