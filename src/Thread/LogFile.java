/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thread;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

/**
 *
 * @author Acer
 */
public class LogFile {
    private Writer out;
    
    public LogFile(File f) throws IOException {
        FileWriter fw = new FileWriter(f);
        this.out = new BufferedWriter(fw);
    }
    
//    public void writeEntry(String message) throws IOException {
//        synchronized(out) {
//            Date d = new Date();
//            out.write(d.toString());
//            out.write("\t");
//            out.write(message);
//            out.write("\r\n");
//        }
//    }
    
//    public void writeEntry(String message) throws IOException {
//        synchronized(this) {
//            Date d = new Date();
//            out.write(d.toString());
//            out.write("\t");
//            out.write(message);
//            out.write("\r\n");
//        }
//    }

    public synchronized void writeEntry(String message) throws IOException {
        Date d = new Date();
        out.write(d.toString());
        out.write("\t");
        out.write(message);
        out.write("\r\n");
    }    
    
    public void close() throws IOException {
        out.flush();
        out.close();
    }
}
