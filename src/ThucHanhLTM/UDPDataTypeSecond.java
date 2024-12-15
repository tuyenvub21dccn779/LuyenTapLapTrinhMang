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
 *
 * @author Acer
 */
public class UDPDataTypeSecond {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            
            byte[] data = new byte[1024];
            InetAddress inetAdd = InetAddress.getByName("203.162.10.109");
            data = ";B21DCCN779;GmQHTD0d".getBytes();
            DatagramPacket dpSend = new DatagramPacket(data, data.length, inetAdd, 2207);
            client.send(dpSend);
            System.out.println("client send success");
            
            byte[] buff = new byte[1024];
            DatagramPacket dpReceive = new DatagramPacket(buff, buff.length);
            client.receive(dpReceive);
            
            String receive = new String(dpReceive.getData());
            System.out.println(receive);
            int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
            int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
            String[] arr = receive.trim().split("[;,]");
            for(int i=1; i<arr.length; i++) {
                int temp = Integer.parseInt(arr[i]);
                System.out.println(temp);
                if(temp > max1) {
                    max1 = temp;
                } else if (temp > max2) {
                    max2 = temp;
                }
                
                if(temp < min1) {
                    min1 = temp;
                } else if (temp < min2) {
                    min2 = temp;
                }
            }
            
            String sendData = arr[0] + ";" + max2 + "," + min2;
            System.out.println(sendData);
            data = sendData.getBytes();
            dpSend = new DatagramPacket(data, data.length, dpReceive.getAddress(), dpReceive.getPort());
            client.send(dpSend);
            System.out.println("client send success");
            
        } catch(SocketException | UnknownHostException ex ) {
            ex.printStackTrace();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
