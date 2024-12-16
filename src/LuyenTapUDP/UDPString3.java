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
[Mã câu hỏi (qCode): FPcLJ69v].  [Loại bỏ ký tự đặc biệt và ký tự trùng giữ nguyên thứ tự xuất 
* hiện]
Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 2208 . Yêu cầu là xây dựng 
* một chương trình client trao đổi thông tin với server theo kịch bản dưới đây:
a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng 
* ";studentCode;qCode”. Ví dụ: ";B15DCCN001;B34D51E0"
b.	Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;str1;str2".
•	requestId là chuỗi ngẫu nhiên duy nhất
•	str1,str2 lần lượt là chuỗi thứ nhất và chuỗi thứ hai
c.	Loại bỏ các ký tự trong chuỗi thứ nhất mà xuất hiện trong chuỗi thứ hai, giữ nguyên 
* thứ tự xuất hiện. Gửi thông điệp là một chuỗi lên server theo định dạng 
* "requestId;strOutput", trong đó chuỗi strOutput là chuỗi đã được xử lý ở trên.
d.	Đóng socket và kết thúc chương trình.
 * @author Acer
 */
public class UDPString3 {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress inet = InetAddress.getByName("203.162.10.109");
            int port = 2208;
            
            byte[] byteSend = ";B21DCCN158;FPcLJ69v".getBytes();
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
