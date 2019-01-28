package com.github.grishberg.performance

import java.io.BufferedWriter
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter
import java.nio.file.Files
import java.nio.file.Paths


private const val TEPLATE_DIR = "templates"
private const val SOURCE_DIR = "Performeter/app/src/main/java/com/grishberg/performeter/samples"

class SourceFileSystem {
    /**
     * Reads file from templates.
     */
    fun readTemplateFile(fileName: String): String {
        try {
            return String(Files.readAllBytes(Paths.get("$TEPLATE_DIR/$fileName")))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return ""
    }

    /**
     * Writes source to file.
     */
    fun writeSourceFile(fileName: String, source: String) {
        BufferedWriter(OutputStreamWriter(
                FileOutputStream("$SOURCE_DIR/$fileName"), "utf-8"))
                .use { writer -> writer.write(source) }
    }
}