package com.github.grishberg.performance.command;

import com.github.grishberg.tests.ConnectedDeviceWrapper;
import com.github.grishberg.tests.common.RunnerLogger;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AssembleCommand implements LauncherCommand {
    private static final String TAG = AssembleCommand.class.getSimpleName();
    private final RunnerLogger logger;

    public AssembleCommand(RunnerLogger logger) {
        this.logger = logger;
    }

    @Override
    public void execute(@NotNull ConnectedDeviceWrapper device) {
        ProcessBuilder processBuilder = new ProcessBuilder();

        // -- Linux --

        // Run a shell command
        //processBuilder.command("bash", "-c", "sh assemble.sh");

        Path currentRelativePath = Paths.get("").toAbsolutePath();
        // Run a shell script
        processBuilder.command(currentRelativePath.toString() + "/assemble.sh");

        // -- Windows --

        // Run a command
        //processBuilder.command("cmd.exe", "/c", "dir C:\\Users\\mkyong");

        // Run a bat file
        //processBuilder.command("C:\\Users\\mkyong\\hello.bat");

        try {

            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                logger.i(TAG, "/tgradle: " + line);
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                logger.i(TAG, "Success!");
            } else {
                logger.e(TAG, "Error: read log.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
