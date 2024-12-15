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
import java.util.HashMap;

/**
 *
 * @author Acer
 */
public class UDPString {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress inet = InetAddress.getByName("203.162.10.109");
            
            byte[] data = ";B21DCCN609;zw3wCrXA".getBytes();
            DatagramPacket dpSend = new DatagramPacket(data, data.length, inet, 2208);
            client.send(dpSend);
            
            byte[] buff = new byte[1024];
            DatagramPacket dpReceive = new DatagramPacket(buff, buff.length);
            client.receive(dpReceive);
            HashMap<Character, Integer> map = new HashMap<>();
            String receiveStr = new String(dpReceive.getData()).trim();
            System.out.println(receiveStr);
            String[] arr = receiveStr.split(";");
            StringBuilder sb = new StringBuilder(arr[0] + ";");
            for(char x : arr[1].toCharArray()) {
                if(Character.isAlphabetic(x) && !map.containsKey(x)) {
                    sb.append(x);
                    map.put(x, 1);
                }
            }
            data = sb.toString().getBytes();
            System.out.println(sb.toString());
            dpSend = new DatagramPacket(data, data.length, inet, 2208);
            client.send(dpSend);
            
            client.close();
            
        } catch(SocketException | UnknownHostException ex) {
            ex.printStackTrace();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
