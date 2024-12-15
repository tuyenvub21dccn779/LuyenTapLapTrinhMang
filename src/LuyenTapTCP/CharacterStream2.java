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
public class CharacterStream2 {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109", 2208);
            
            String strSend = "B21DCCN588;qryAKEwg";
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bw.write(strSend);
            bw.newLine();
            bw.flush();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String strReceive = br.readLine();
            System.out.println(strReceive);
            String[] arr = strReceive.trim().split("\\s+");
            Arrays.sort(arr, new Comparator<String>(){
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            strSend = "";
            for(String x : arr) {
                strSend += x + " ";
            }
            
            strSend = strSend.trim();
            System.out.println(strSend);
            bw.write(strSend);
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
