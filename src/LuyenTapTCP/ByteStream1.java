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
public class ByteStream1 {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109", 2206);
            
            OutputStream os = client.getOutputStream();
            String strSend = "B21DCCN609;QT7swpBc";
            os.write(strSend.getBytes());
            os.flush();
            
            InputStream is = client.getInputStream();
            byte[] buff = new byte[1024];
            is.read(buff);
            
            String receiveStr = new String(buff).trim();
            System.out.println(receiveStr);
            
            String[] arr = receiveStr.split(",");
            ArrayList<Integer> list = new ArrayList<>();
            for(String x : arr) {
                Integer tmp = Integer.parseInt(x);
                list.add(tmp);
            }
            
            list.sort(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
                
            });
            System.out.println(list);
            int res = Integer.MAX_VALUE;
            int num1 = 0, num2 = 0;
            for(int i=1; i<list.size(); i++) {
                if(list.get(i) - list.get(i-1) <= res) {
                    num1 = list.get(i-1);
                    num2 = list.get(i);
                    res = num2 - num1;
                }
            }
            strSend = res + "," + num1 + "," + num2;
            System.out.println(strSend);
            os.write(strSend.getBytes());
            os.flush();
            
            os.close();
            is.close();
            client.close();
            
        } catch(IOException ex) {
            ex.printStackTrace();
        }
            
    }
}
