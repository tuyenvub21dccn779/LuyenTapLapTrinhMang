/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapTCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Acer
 */
public class ByteStream2 {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109", 2206);
            
            String strSend = "B18DCCN143;OXxe69lK";
            OutputStream os = client.getOutputStream();
            os.write(strSend.getBytes());
            os.flush();
            
            InputStream is = client.getInputStream();
            byte[] buff = new byte[1024];
            is.read(buff);
            String strReceive = new String(buff);
            String[] arr = strReceive.trim().split(",");
            Integer max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
            Integer pos1 = 0, pos2 = 0;
            for(int i = 0; i < arr.length; i++) {
                Integer tmp = Integer.parseInt(arr[i]);
                if(tmp > max1) {
                    max2 = max1;
                    max1 = tmp;
                    pos2 = pos1;
                    pos1 = i;
                }
                else if(tmp > max2) {
                    max2 = tmp;
                    pos2 = i;
                }
                
            }
            
            strSend = max2 + "," + pos2;
            System.out.println(strReceive);
            System.out.println(strSend);
            os.write(strSend.getBytes());
            os.flush();
            
            is.close();
            os.close();
            client.close();
            
            
            
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
