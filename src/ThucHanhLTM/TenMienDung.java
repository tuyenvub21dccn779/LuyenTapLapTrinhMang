/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ThucHanhLTM;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Acer
 */
public class TenMienDung {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("172.188.19.218", 1606);
            //1 gui ma sv va ma cau hoi
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bw.write("B21DCCN779;gZD49Ji");
            bw.newLine();
            bw.flush();
            
            //2 nhan du lieu 
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
            String str = br.readLine();
            
            String[] arr = str.split(",");
            String ans = "";
            for(String x : arr) {
                x = x.trim();
                if(x.endsWith(".edu")) {
                    ans += x + ", ";
                }
            }
            
            
            
            System.out.println(ans.substring(0, ans.length()-2));
            bw.write(ans.substring(0, ans.length()-2));
            bw.newLine();
            bw.flush();
            
            bw.close();
            br.close();
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(TenMienDung.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
