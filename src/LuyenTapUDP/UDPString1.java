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
[Mã câu hỏi (qCode): fk8bWpc5].  Một chương trình server cho phép kết nối qua giao thức UDP tại 
* cổng 2208. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch 
* bản dưới đây:
a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng 
* “;studentCode;qCode”. Ví dụ: “;B15DCCN001;5B35BCC1”
b.	Nhận thông điệp từ server theo định dạng “requestId;data” 
-	requestId là một chuỗi ngẫu nhiên duy nhất
-	data là chuỗi dữ liệu cần xử lý
c.	Xử lý chuẩn hóa chuỗi đã nhận thành theo nguyên tắc 
i.	Ký tự đầu tiên của từng từ trong chuỗi là in hoa
ii.	Các ký tự còn lại của chuỗi là in thường
Gửi thông điệp chứa chuỗi đã được chuẩn hóa lên server theo định dạng “requestId;data”
d.	Đóng socket và kết thúc chương trình
 * @author Acer
 */
public class UDPString1 {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress inetServer = InetAddress.getByName("203.162.10.109");
            int portServer = 2208;
            
            byte[] data = ";B21DCCN136;gXVNYsyU".getBytes();
            DatagramPacket dpSend = new DatagramPacket(data, data.length, inetServer, portServer);
            client.send(dpSend);
            
            byte[] buff = new byte[1024];
            DatagramPacket dpReceive = new DatagramPacket(buff, buff.length);
            client.receive(dpReceive);
            
            String strReceive = new String(dpReceive.getData());
            System.out.println(strReceive);
            String[] arr = strReceive.trim().split(";");
            String ans = arr[0] + ";";
            
            arr = arr[1].toLowerCase().split("\\s+");
            for(String x : arr) {
                ans += Character.toUpperCase(x.charAt(0)) + x.substring(1) + " ";
            }
            
            data = ans.getBytes();
            dpSend = new DatagramPacket(data, data.length, inetServer, portServer);
            client.send(dpSend);
            System.out.println(ans);
            client.close();
            
        } catch(SocketException | UnknownHostException ex) {
            ex.printStackTrace();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
