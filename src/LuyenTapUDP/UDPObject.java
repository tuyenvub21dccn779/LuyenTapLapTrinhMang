/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapUDP;

import UDP.Student;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author Acer
 */
public class UDPObject {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress inet = InetAddress.getByName("203.162.10.109");
            
            byte[] data = ";B21DCCN588;jY3hq7tm".getBytes();
            DatagramPacket dpSend = new DatagramPacket(data, data.length, inet, 2209);
            client.send(dpSend);
            
            byte[] buff = new byte[1024];
            DatagramPacket dpReceive = new DatagramPacket(buff, buff.length);
            client.receive(dpReceive);
            
            ByteArrayInputStream bais = new ByteArrayInputStream(dpReceive.getData());
            DataInputStream dis = new DataInputStream(bais);
            byte[] requestId = new byte[8];
            dis.read(requestId);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Student stu = (Student) ois.readObject();
            System.out.println(stu);
            
            String[] arr = stu.getName().toLowerCase().split("\\s+");
            StringBuilder sbName = new StringBuilder();
            StringBuilder sbEmail = new StringBuilder(arr[arr.length-1]);
            
            for(int i = 0; i<arr.length; i++) {
                if(i!=arr.length-1) {
                    sbEmail.append(arr[i].charAt(0));
                }
                
                sbName.append(Character.toUpperCase(arr[i].charAt(0)) + arr[i].substring(1) + " ");
            }
            stu.setEmail(sbEmail.toString().trim() + "@ptit.edu.vn");
            stu.setName(sbName.toString().trim());
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            dos.write(requestId);
            
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(stu);
            System.out.println(stu);
            
            data = baos.toByteArray();
            dpSend = new DatagramPacket(data, data.length, inet, 2209);
            client.send(dpSend);
            
            client.close();
            
        } catch(SocketException | UnknownHostException ex) {
            ex.printStackTrace();
        } catch(IOException ex) {
            ex.printStackTrace();
        } catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
