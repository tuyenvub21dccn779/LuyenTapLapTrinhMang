/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ThucHanhLTM;

import UDP.Product;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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

    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }

    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bin = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bin);
        return ois.readObject();
    }

    public static byte[] xuLy(byte[] data) throws IOException, ClassNotFoundException {
        byte[] requestId = new byte[8];
        System.arraycopy(data, 0, requestId, 0, 8);
        byte[] obj = new byte[data.length - 8];
        System.arraycopy(data, 8, obj, 0, data.length - 8);

        Product product = (Product) deserialize(obj);
        System.out.println(product);
        product.chuanHoa();
        System.out.println(product);
        obj = serialize(product);
        byte[] sendData = new byte[obj.length + 8];
        System.arraycopy(requestId, 0, sendData, 0, 8);
        System.arraycopy(obj, 0, sendData, 8, obj.length);

        return sendData;
    }

    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();

            byte[] data = new byte[1024];
            data = ";B21DCCN779;6WrdzcOf".getBytes();
            DatagramPacket dpSend = new DatagramPacket(data, data.length, InetAddress.getByName("203.162.10.109"), 2209);
            client.send(dpSend);
            System.out.println("client send success");

            byte[] buff = new byte[1024];
            DatagramPacket dpReceive = new DatagramPacket(buff, buff.length);
            client.receive(dpReceive);
            byte[] receive = new byte[dpReceive.getLength()];
            System.arraycopy(dpReceive.getData(), 0, receive, 0, dpReceive.getLength());
            
            System.out.println("client receive success");
            for(byte x : receive) {
                System.out.print(x);
            }
            System.out.println("");
            data = xuLy(receive);
            dpSend = new DatagramPacket(data, data.length, dpReceive.getAddress(), dpReceive.getPort());
            client.send(dpSend);
            System.out.println("client send success");

        } catch (SocketException | UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
