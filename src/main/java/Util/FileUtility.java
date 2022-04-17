package Util;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileUtility {
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

    static void readContent() throws Exception {
        File myObj = new File(fileDir + "/" + fileName);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            System.out.println(data);
        }
        myReader.close();
    }
}
