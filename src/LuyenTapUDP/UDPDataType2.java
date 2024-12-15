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
import java.util.HashSet;
import java.util.Set;

/**
[Mã câu hỏi (qCode): Yhc1B30p].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”. Ví dụ: “;B15DCCN001;73457A17”
b.	Nhận thông điệp là một chuỗi từ server theo định dạng “requestId;n;A1,A2,...An” , với
-	requestId là chuỗi ngẫu nhiên duy nhất
-	n là một số ngẫu nhiên nhỏ hơn 100.
-            A1, A2 ... Am (m <= n) là các giá trị ngẫu nhiên nhỏ hơn hoặc bằng n và có thể trùng nhau.
Ex: requestId;10;2,3,5,6,5
c.	Tìm kiếm các giá trị còn thiếu và gửi lên server theo định dạng “requestId;B1,B2,...,Bm”
Ex: requestId;1,4,7,8,9,10
d.	Đóng socket và kết thúc chương trình.
 * @author Acer
 */
public class UDPDataType2 {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress inet = InetAddress.getByName("203.162.10.109");
            
            byte[] data = new byte[1024];
            data = ";B21DCCN023;zHeS09lp".getBytes();
            DatagramPacket dpSend = new DatagramPacket(data, data.length, inet, 2207);
            client.send(dpSend);
            
            byte[] buff = new byte[1024];
            DatagramPacket dpReceive = new DatagramPacket(buff, buff.length);
            client.receive(dpReceive);
            
            String receiveStr = new String(dpReceive.getData()).trim();
            System.out.println(receiveStr);
            String[] arr = receiveStr.split(";");
            int n = Integer.parseInt(arr[1]);
            String[] nums = arr[2].split(",");
            Set<Integer> set = new HashSet<>();
            for(String num : nums) {
                Integer tmp = Integer.parseInt(num);
                set.add(tmp);
            }
            
            StringBuilder res = new StringBuilder(arr[0] + ";");
            for(int i=1; i<=n; i++) {
                if(!set.contains(i)) {
                    res.append(i + ",");
                }
            }
            
            res.deleteCharAt(res.length()-1);
            System.out.println(res.toString());
            data = res.toString().getBytes();
            dpSend = new DatagramPacket(data, data.length, inet, 2207);
            client.send(dpSend);
            
            
        } catch(SocketException | UnknownHostException ex) {
            ex.printStackTrace();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
