package Util;

import Exceptions.DukeException;
import Tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileHelper {
    private static String fileName = "duke.txt";
    private static String fileDir = "dukeDb";

    public static void initDb() throws Exception {
        File dir = new File(fileDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File tmp = new File(dir, fileName);
        if (!tmp.exists()) {
            tmp.createNewFile();
        }
    }

    static void saveContent(String content) throws Exception {
        FileWriter myWriter = new FileWriter(fileDir + "/" + fileName);
        myWriter.write(content);
        myWriter.close();
    }

    public static void saveTaskList(ArrayList<Task> tasks) throws DukeException {
        String contentToSave = "";
        for (int i = 0; i < tasks.size(); i++) {
            contentToSave += tasks.get(i).getDbEntryDescription();

            if (i < tasks.size() - 1) {
                contentToSave += "\n";
            }
        }

        try {
            FileHelper.saveContent(contentToSave);
        } catch (Exception e) {
            throw new DukeException("Unable to save content to file, " + e.getMessage());
        }
    }
}
