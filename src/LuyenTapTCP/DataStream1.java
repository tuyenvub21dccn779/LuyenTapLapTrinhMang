/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Acer
 */
public class DataStream1 {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109", 2207);
            
            String strSend = "B21DCCN588;R4oXzYbh";
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            dos.writeUTF(strSend);
            
            DataInputStream dis = new DataInputStream(client.getInputStream());
            ArrayList<Integer> list = new ArrayList<>();
            Integer n = dis.readInt();
            Integer sum = 0;
            for(int i=0; i<n; i++) {
                Integer num = dis.readInt();
                list.add(num);
                sum += num;
            }
            Float average = 1f * sum / n;
            Float std = 0f;
            for(Integer x : list) {
                std += (1f * x - average) * (1f * x - average);
            }
            std /= n;
            dos.writeInt(sum);
            dos.writeFloat(average);
            dos.writeFloat(std);
            
            dos.close();
            dis.close();
            client.close();
            
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
