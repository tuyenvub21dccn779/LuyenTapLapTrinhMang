/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ThucHanhLTM;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Acer
 */
public class TongHieuTich {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("172.188.19.218", 1605);
            //1 gui ma sinh vien
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            String str = "B21DCCN779;z53cHLq";
            dos.writeUTF(str);
            
            //2 nhan 2 so a, b
            DataInputStream dis = new DataInputStream(client.getInputStream());
            int a = dis.readInt();
            int b = dis.readInt();
            
            System.out.println("2 so: " + a + ", " + b);
            
            //3 thuc hien va gui ket qua len server
            dos.writeInt(a + b);
            dos.writeInt(a - b);
            dos.writeInt(a * b);
            
            dos.close();
            dis.close();
            client.close();
            
            
        } catch (IOException ex) {
            Logger.getLogger(TongHieuTich.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
