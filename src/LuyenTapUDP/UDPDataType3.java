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
[Mã câu hỏi (qCode): HRj05VoA].  Một chương trình server cho phép giao tiếp qua giao thức 
* UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với 
* server theo kịch bản:
a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng 
* “;studentCode;qCode”. Ví dụ: “;B15DCCN001;DC73CA2E”
b.	Nhận thông điệp là một chuỗi từ server theo định dạng “requestId;a1,a2,...,a50” 
-	requestId là chuỗi ngẫu nhiên duy nhất
-	a1 -> a50 là 50 số nguyên ngẫu nhiên
c.	Thực hiện tìm giá trị lớn nhất và giá trị nhỏ nhất thông điệp trong a1 -> a50 và gửi 
* thông điệp lên lên server theo định dạng “requestId;max,min”
d.	Đóng socket và kết thúc chương trình
 * @author Acer
 */
public class UDPDataType3 {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress inet = InetAddress.getByName("203.162.10.109");
            int port = 2207;
            String studentCode = "B21DCCN131", qCode = "HRj05VoA";
            
            byte[] data = (";" + studentCode + ";" + qCode ).getBytes();
            DatagramPacket dpSend = new DatagramPacket(data, data.length, inet, port);
            client.send(dpSend);
            
            byte[] buff = new byte[1024];
            DatagramPacket dpReceive = new DatagramPacket(buff, buff.length);
            client.receive(dpReceive);
            
            String strReceive = new String(dpReceive.getData());
            System.out.println(strReceive);
            String[] arr = strReceive.trim().split(";");
            StringBuilder ans = new StringBuilder(arr[0] + ";");
            arr = arr[1].split(",");
            int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
            for(String x : arr) {
                int tmp = Integer.parseInt(x);
                max = Math.max(max, tmp);
                min = Math.min(min, tmp);
            }
            ans.append(max + "," + min);
            System.out.println(ans.toString());
            data = ans.toString().getBytes();
            dpSend = new DatagramPacket(data, data.length, inet, port);
            client.send(dpSend);
            
            client.close();
            
        } catch(SocketException | UnknownHostException ex) {
            ex.printStackTrace();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
