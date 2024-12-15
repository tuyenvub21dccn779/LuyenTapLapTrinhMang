/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapTCP;

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
public class DataStream {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109", 2207);
            // 1. gui ma sinh vien
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            String str = "B18DCCN143;Sxwh1zel";
            dos.writeUTF(str);
            
            // 2. nhan chuoi ma hoa
            DataInputStream dis = new DataInputStream(client.getInputStream());
            str = dis.readUTF();
            int s = dis.readInt();
            System.out.println(str);
            System.out.println(s);
            
            //3 thuc hien va gui ket qua len server
            StringBuilder sb = new StringBuilder();
            for(char x : str.toCharArray()) {
                int newChar = x;
                if(x >= 'A' && x <= 'Z') {
                    newChar = ((x - 65) + s) % 26 + 65;
                } else if(x >= 'a' && x <= 'z') {
                    newChar = ((x - 97) + s) % 26 + 97;
                }
                sb.append((char)newChar);
            }
            
            System.out.println(sb.toString());
            dos.writeUTF(sb.toString());
            
            dos.close();
            dis.close();
            client.close();
            
        } catch (IOException ex) {
            Logger.getLogger(DataStream.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
