/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WriteIntoNotepad;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 *
 *
 */
public class WriteIntoNotepad {

    private static final String FILENAME = "SensorReport.txt";
    static BufferedWriter bw = null;
    static FileWriter fw = null;

    public static void writeIntoNotepad(String text) {
        try {
            File file = new File(FILENAME);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            // true = append file
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(text);
            bw.write("\r\n");
        } catch (IOException e) {
            //e.printStackTrace();
            //System.out.println("exception1");
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                //ex.printStackTrace();
                //System.out.println("Exception2");
            }
        }
    }

}
