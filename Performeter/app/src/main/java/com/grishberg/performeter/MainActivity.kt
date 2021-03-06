package com.grishberg.performeter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.grishberg.performeter.samples.JavaSample1
import com.grishberg.performeter.samples.JavaSample2
import com.grishberg.performeter.samples.KotlinSample1
import com.grishberg.performeter.samples.KotlinSample2

private const val MODE_EXTRA = "mode"
private const val ITERATIONS_COUNT = 500000

/**
 * --es mode "k vs k"
 * --es mode "j vs k"
 * --es mode "k vs j"
 * --es mode "j vs j"
 */
class MainActivity : AppCompatActivity() {
    private lateinit var reporter: ResultReporter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        reporter = LogReporter()

        launchPerfTests()
    }

    private fun launchPerfTests() {
        val mode = intent.getStringExtra(MODE_EXTRA)
        val runnable1: Runnable
        val runnable2: Runnable
        when (mode) {
            "k vs k" -> {
                runnable1 = KotlinSample1()
                runnable2 = KotlinSample2()
            }
            "k vs j", "j vs k" -> {
                runnable1 = JavaSample1()
                runnable2 = KotlinSample2()
            }
            else -> {
                runnable1 = JavaSample1()
                runnable2 = JavaSample2()
            }
        }
        start(runnable1, runnable2)
    }

    private fun start(runnable1: Runnable, runnable2: Runnable) {
        val startTime = SystemClock.uptimeMillis()

        runnable1.run()
        runnable2.run()

        for (i in 0 until ITERATIONS_COUNT) {
            runnable1.run()
        }
        val duration1 = SystemClock.uptimeMillis() - startTime

        val startTime2 = SystemClock.uptimeMillis()
        for (i in 0 until ITERATIONS_COUNT) {
            runnable2.run()
        }
        val duration2 = SystemClock.uptimeMillis() - startTime2

        reporter.reportPerfResults(duration1, duration2)
    }
}
