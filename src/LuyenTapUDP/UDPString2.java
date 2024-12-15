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
[Mã câu hỏi (qCode): MotjgvGJ].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2208. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:

a. Gửi một thông điệp chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN001;9F8C2D3A".
b. Nhận một thông điệp từ server theo định dạng "requestId;data", với:
    requestId là chuỗi ngẫu nhiên duy nhất.
    data là một chuỗi ký tự liên tiếp cần xử lý. Ví dụ: "requestId;aaabbbccdaa"

c. Xử lý chuỗi bằng cách đếm số lượng ký tự liên tiếp và gom chúng lại theo định dạng "số_lần_ký_tự". Gửi kết quả về server theo định dạng:
    "requestId;processedData"
Ví dụ: Với chuỗi "aaabbbccdaa", kết quả sẽ là: "requestId;3a3b2c1d2a"

d. Đóng socket và kết thúc chương trình.
 * @author Acer
 */
public class UDPString2 {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress inetServer = InetAddress.getByName("203.162.10.109");
            int portServer = 2208;
            
            byte[] dataSend = ";B21DCCN023;MotjgvGJ".getBytes();
            DatagramPacket dpSend = new DatagramPacket(dataSend, dataSend.length, inetServer, portServer);
            client.send(dpSend);
            
            byte[] dataReceive = new byte[1024];
            DatagramPacket dpReceive = new DatagramPacket(dataReceive, dataReceive.length);
            client.receive(dpReceive);
            
            String strReceive = new String(dpReceive.getData()).trim();
            
            String[] arr = strReceive.split(";");
            System.out.println(arr[1]);
            int[] count = new int[256];
            StringBuilder ans = new StringBuilder();
            for(char x : arr[1].toCharArray()) {
                count[x] ++;
            }
            for(char x : arr[1].toCharArray()) {
                if(count[x] != 0) {
                    ans.append(count[x]);
                    ans.append(x);
                    count[x] = 0;
                }
            }
            System.out.println(ans.toString());
            
            String strSend = arr[0] + ";" + ans.toString();
            dataSend = strSend.getBytes();
            dpSend = new DatagramPacket(dataSend, dataSend.length, inetServer, portServer);
            client.send(dpSend);
            
            client.close();
            
        } catch(SocketException | UnknownHostException ex) {
            ex.printStackTrace();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
