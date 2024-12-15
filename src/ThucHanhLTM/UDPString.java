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
public class UDPString {
    
    public static String chuanHoa(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.toLowerCase().substring(1);
        
    } 
    
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            
            byte[] data = new byte[1024];
            InetAddress inetAdd = InetAddress.getByName("203.162.10.109");
            data = ";B21DCCN779;lzrOy0f3".getBytes();
            DatagramPacket dpSend = new DatagramPacket(data, data.length, inetAdd, 2208);
            client.send(dpSend);
            System.out.println("client send success");
            
            byte[] buff = new byte[1024];
            DatagramPacket dpReceive = new DatagramPacket(buff, buff.length);
            client.receive(dpReceive);
            
            String receive = new String(dpReceive.getData());
            System.out.println(receive);
            String[] arr = receive.trim().split("[;\\s]");
            String sendData = "";
            for(int i=1; i<arr.length; i++) {
                System.out.println(arr[i]);
               sendData += chuanHoa(arr[i].trim()) + " "; 
            }
            sendData = arr[0] + ";" + sendData;
            System.out.println(sendData);
            
            data = sendData.trim().getBytes();
            dpSend = new DatagramPacket(data, data.length, dpReceive.getAddress(), dpReceive.getPort());
            System.out.println("client send success");
            
        } catch(SocketException | UnknownHostException ex) {
            ex.printStackTrace();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        
    }
}
