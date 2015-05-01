/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.akhil.tvutils.extract;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author akhil
 */
public class Extract {

    static String dir = "B:\\tv\\Modern Family\\season 2";
    File file = new File(dir);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Extract ex = new Extract();
        ex.scan(new File(dir));

    }

    public void scan(File file) {

        for (File f : file.listFiles()) {
            //System.out.println(f.getAbsolutePath());
            if (f.isDirectory()) {
                scan(f);
            } else if (f.getName().endsWith("rar")) {
                String cmd[] = {"C:\\Program Files\\7-Zip\\7z.exe","x",f.getAbsolutePath()};
                System.out.println(Arrays.toString(cmd));
                executeCommand(cmd, dir);
                break;
            }
        }

    }

    public int executeCommand(String[] cmd, String dir) {
        int ret = 0;

        // Create ProcessBuilder instance for UNIX command ls -l
        java.lang.ProcessBuilder pb = new java.lang.ProcessBuilder(cmd);
        String line;
        // You can change the working directory
        pb.directory(new java.io.File(dir));
        try {
            // Start new process
            java.lang.Process p = pb.start();
            BufferedReader bri = new BufferedReader
        (new InputStreamReader(p.getInputStream()));
      BufferedReader bre = new BufferedReader
        (new InputStreamReader(p.getErrorStream()));
      while ((line = bri.readLine()) != null) {
        System.out.println(line);
      }
      bri.close();
      while ((line = bre.readLine()) != null) {
        System.out.println(line);
      }
      bre.close();
      p.waitFor();
      System.out.println("Done.");
        } catch (InterruptedException ex) {
            Logger.getLogger(Extract.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Extract.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }
}
