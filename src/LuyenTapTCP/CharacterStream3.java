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
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author Acer
 */
public class CharacterStream3 {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109", 2208);
            
            String strSend = "B18DCCN143;7nXjttSW";
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bw.write(strSend);
            bw.newLine();
            bw.flush();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String strReceive = br.readLine();
            String[] arr = strReceive.trim().split("\\s+");
            String res = arr[0];
            for(String x : arr) {
                if(x.length() > res.length()) res = x;
            }
            int k = strReceive.indexOf(res);
            
            System.out.println(res + " " + k);
            
            bw.write(res);
            bw.newLine();
            bw.flush();
            bw.write(k + "");
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
