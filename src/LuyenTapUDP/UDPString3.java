/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author Acer
 */
public class UDPString3 {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress inet = InetAddress.getByName("203.162.10.109");
            int port = 2208;
            
            byte[] byteSend = ";B21DCCN211;pVnV61id".getBytes();
            DatagramPacket dpSend = new DatagramPacket(byteSend, byteSend.length, inet, port);
            client.send(dpSend);
            
            byte[] byteReceive = new byte[1024];
            DatagramPacket dpReceive = new DatagramPacket(byteReceive, byteReceive.length);
            client.receive(dpReceive);
            
            String strReceive = new String(dpReceive.getData()).trim();
            String[] arr = strReceive.split(";");
            int[] mark = new int[256];
            for(char x : arr[2].toCharArray()) {
                mark[x] = 1;
            }
            StringBuilder sb = new StringBuilder();
            for(char x : arr[1].toCharArray()) {
                if(mark[x] == 0) sb.append(x);
            }
            String strSend = arr[0] + ";" + sb.toString();
            byteSend = strSend.getBytes();
            dpSend = new DatagramPacket(byteSend, byteSend.length, inet, port);
            client.send(dpSend);
            System.out.println(strReceive);
            System.out.println(strSend);
            client.close();
            
        } catch(SocketException | UnknownHostException ex) {
            ex.printStackTrace();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
