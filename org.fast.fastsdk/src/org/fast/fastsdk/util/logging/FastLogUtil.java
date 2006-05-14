/**
 * 
 */
package org.fast.fastsdk.util.logging;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author …Ú»›÷€
 */
public class FastLogUtil {
    public static List parse(File file) {
        List logs = new ArrayList();
        FileInputStream fileStream = null;
        try {
            fileStream = new FileInputStream(file);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                fileStream));
        String str = null;
        try {
            while ((str = reader.readLine()) != null) {
                FastLogEntry entry = new FastLogEntry();
                StringTokenizer tokenizer = new StringTokenizer(str, "#");
                entry.setTime(tokenizer.nextToken());
                tokenizer.nextToken();
                entry.setLevel(tokenizer.nextToken());
                entry.setLocation(tokenizer.nextToken());
                if (tokenizer.hasMoreTokens())
                    entry.setMessage(tokenizer.nextToken());
                logs.add(entry);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return logs;
    }

    public static void clearLogs(File logFile) {
        if (logFile.exists()) {
            try {
                FileOutputStream fileStream = new FileOutputStream(logFile);
                fileStream.write(new byte[0]);
                fileStream.flush();
                fileStream.close();
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
