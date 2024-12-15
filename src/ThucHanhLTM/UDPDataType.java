/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ThucHanhLTM;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 203.162.10.109
 * @author Acer
 */
public class UDPDataType {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            
            byte[] data = new byte[1024];
            data = ";B21DCCN779;ehRHsZov".getBytes();
            DatagramPacket dpSend = new DatagramPacket(data, data.length, InetAddress.getByName("203.162.10.109"), 2207);
            client.send(dpSend);
            System.out.println("client send success!");
            
            byte[] buff = new byte[1024];
            DatagramPacket dpReceive = new DatagramPacket(buff, buff.length);
            client.receive(dpReceive);
            
            String receive = new String(dpReceive.getData());
            String[] arr = receive.trim().split("[;,]");
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for(int i = 1; i<arr.length; i++) {
                System.out.println(arr[i]);
                int temp = Integer.parseInt(arr[i]);
                max = Math.max(max, temp);
                min = Math.min(temp, min);
            }
            
            String sendData = arr[0] + ";" + max + "," + min;
            System.out.println(sendData);
            data = sendData.getBytes();
            dpSend = new DatagramPacket(data, data.length, dpReceive.getAddress(), dpReceive.getPort());
            client.send(dpSend);
            
        } catch(SocketException | UnknownHostException ex) {
            ex.printStackTrace();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
