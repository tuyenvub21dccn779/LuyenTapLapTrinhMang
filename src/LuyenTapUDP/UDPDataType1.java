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
public class UDPDataType1 {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress inet = InetAddress.getByName("203.162.10.109");
            int port = 2207;
            
            byte[] bytesSend = ";B21DCCN211;GT290jsN".getBytes();
            DatagramPacket dpSend = new DatagramPacket(bytesSend, bytesSend.length, inet, port);
            client.send(dpSend);
            System.out.println("Send success");
            byte[] bytesReceive = new byte[1024];
            DatagramPacket dpReceive = new DatagramPacket(bytesReceive, bytesReceive.length);
            client.receive(dpReceive);
            System.out.println("Receive success");
            
            String strReceive = new String(dpReceive.getData());
            System.out.println(strReceive);
            String[] arr = strReceive.trim().split(";");
            String strSend = arr[0];
            arr = arr[1].trim().split(",");
            int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
            int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
            
            for(String x : arr) {
                int temp = Integer.parseInt(x);
                if(temp > max1) {
                    max2 = max1;
                    max1 = temp;
                } else if(temp > max2) {
                    max2 = temp;
                }
                
                if(temp < min1) {
                    min2 = min1;
                    min1 = temp;
                } else if(temp < min2) {
                    min2 = temp;
                }
            }
            strSend = strSend + ";" + max2 + "," + min2;
            bytesSend = strSend.getBytes();
            dpSend = new DatagramPacket(bytesSend, bytesSend.length, inet, port);
            client.send(dpSend);
            System.out.println(strSend);
            
            client.close();
            
        } catch(SocketException | UnknownHostException ex) {
            ex.printStackTrace();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
