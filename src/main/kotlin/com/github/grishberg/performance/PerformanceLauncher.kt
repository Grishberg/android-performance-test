package com.github.grishberg.performance

import com.android.ddmlib.AndroidDebugBridge
import com.github.grishberg.tests.InstrumentalExtension
import com.github.grishberg.tests.adb.AdbWrapper
import com.github.grishberg.tests.adb.AdbWrapperImpl

private const val TAG = "PerformanceLauncher"

class PerformanceLauncher {
    private val logger = Log4JLogger()
    private val adb = initAdbConnection(logger)

    /**
     * Start performance tests.
     */
    fun measurePerformance(
            firstSourceJava: Boolean,
            firstSourceImport: String,
            firstSourceCode: String,
            secondSourceJava: Boolean,
            secondSourceImport: String,
            secondSourceCode: String): String {

        val resultsPrinter: ResultsPrinter = ConsoleResultPrinter()

        val commandsFabric = CommandsFabric(adb, logger, resultsPrinter,
                firstSourceJava, firstSourceImport, firstSourceCode,
                secondSourceJava, secondSourceImport, secondSourceCode)
        commandsFabric.execute()
        return resultsPrinter.results()
    }

    private fun initAdbConnection(logger: Log4JLogger): AdbWrapper {
        val adb = AdbWrapperImpl()
        val instrumentalExtension = InstrumentalExtension()
        var androidSdkPath: String? = instrumentalExtension.androidSdkPath
        if (androidSdkPath == null) {
            logger.i(TAG, "androidSdkPath is empty, get path from env ANDROID_HOME")
            androidSdkPath = System.getenv("ANDROID_HOME")
            logger.i(TAG, "androidSdkPath = {}", androidSdkPath)
        }
        AndroidDebugBridge.initIfNeeded(false)
        adb.init(androidSdkPath!!, logger)
        adb.waitForAdb()
        return adb
    }

}