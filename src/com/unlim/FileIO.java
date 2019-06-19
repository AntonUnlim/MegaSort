package com.unlim;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileIO {
    private static final String LOG_FILE = "result.txt";
    private static final File file = new File(LOG_FILE);
    public static void writeMsgToLogFile(String logString) {
        try {
            FileWriter fileLog = new FileWriter(file, true);
            fileLog.write(logString);
            fileLog.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearLogFile() {
        try {
            PrintWriter clear = new PrintWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
