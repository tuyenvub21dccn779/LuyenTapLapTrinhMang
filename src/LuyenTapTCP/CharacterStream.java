/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapTCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author Acer
 */
public class CharacterStream {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109", 2208);
            //1 gui ma sinh vien
            String str = "B21DCCN193;fEsn7BTP";
            BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(client.getOutputStream()));
            bw.write(str);
            bw.newLine();
            bw.flush();
            System.out.println(client.toString());
            
            // 2. nhan chuoi tu server
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            str = br.readLine();
            System.out.println(str);
            
            //3 thuc hien va gui ket qua len server
            int[] count = new int[256];
            StringBuilder sb = new StringBuilder();
            for(char x : str.toCharArray()) {
                count[x]++;
                if(Character.isAlphabetic(x) && count[x] == 1) {
                    sb.append(x);
                }
            }
            System.out.println(sb.toString());
            
            bw.write(sb.toString());
            bw.newLine();
            bw.flush();
            
            bw.close();
            br.close();
            client.close();
            
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        
        
    }
}
