/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ThucHanhLTM;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Acer
 */
public class TongCacSo {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("172.188.19.218", 1604);
            
            //1 gui ma sinh vien va ma cau hoi
            OutputStream os = client.getOutputStream();
            os.write("B21DCCN779;jeZEzCU".getBytes());
            
            //2 nhan chuoi tu server
            InputStream is = client.getInputStream();
            byte[] data = new byte[1024];
            is.read(data);
            
            
            //3 thuc hien tinh toan
            String str = new String(data);
            System.out.println(str);
            String[] arr = str.trim().split("\\|");
            long sum = 0;
            for(String x : arr) {
                sum += Long.parseLong(x);
            }
            str = "" + sum;
            os.write(str.getBytes());
            
            os.close();
            is.close();
            client.close();
            
        } catch (IOException ex) {
            Logger.getLogger(TongCacSo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
