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
[Mã câu hỏi (qCode): f9C30B1k].  [Loại bỏ ký tự đặc biệt, số, trùng và giữ nguyên thứ tự xuất 
* hiện]
Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 2208 . Yêu cầu là xây 
* dựng một chương trình client trao đổi thông tin với server theo kịch bản dưới đây:
a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng 
* ";studentCode;qCode". Ví dụ: ";B15DCCN001;06D6800D"
b.	Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;strInput"
•	requestId là chuỗi ngẫu nhiên duy nhất
•	strInput là chuỗi thông điệp cần xử lý
c.	Thực hiện loại bỏ ký tự đặc biệt, số, ký tự trùng và giữ nguyên thứ tự xuất hiện của 
* chúng. Gửi thông điệp lên server theo định dạng "requestId;strOutput", trong đó strOutput 
* là chuỗi đã được xử lý ở trên
d.	Đóng socket và kết thúc chương trình.
 * @author Acer
 */
public class UDPString {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress inet = InetAddress.getByName("203.162.10.109");
            
            byte[] data = ";B21DCCN131;f9C30B1k".getBytes();
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
