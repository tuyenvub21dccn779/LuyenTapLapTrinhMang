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
[Mã câu hỏi (qCode): q70JYbFC].  Mật mã caesar, còn gọi là mật mã dịch chuyển, để giải mã thì 
* mỗi ký tự nhận được sẽ được thay thế bằng một ký tự cách nó một đoạn s. Ví dụ: với s = 3 thì 
* ký tự “A” sẽ được thay thế bằng ký tự “D”.
Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu xây dựng 
* chương trình client trao đổi thông tin với server theo kịch bản mô tả dưới đây:
a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng 
* ";studentCode;qCode". Ví dụ: ";B15DCCN001;825EE3A7"
b.	Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;strEncode;s".
•	requestId là chuỗi ngẫu nhiên duy nhất
•	strEncode là chuỗi thông điệp bị mã hóa
•	s là số nguyên chứa giá trị độ dịch của mã
c.	Giải mã tìm thông điệp ban đầu và gửi lên server theo định dạng “requestId;strDecode”
d.	Đóng socket và kết thúc chương trình.
 * @author Acer
 */
public class UDPDataType {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress inetServer = InetAddress.getByName("203.162.10.109");
            int portServer = 2207;
            
            byte[] dataSend = ";B21DCCN175;q70JYbFC".getBytes();
            DatagramPacket dpSend = new DatagramPacket(dataSend, dataSend.length, inetServer, portServer);
            client.send(dpSend);
            
            byte[] dataReceive = new byte[1024];
            DatagramPacket dpReceive = new DatagramPacket(dataReceive, dataReceive.length);
            client.receive(dpReceive);
            
            String strReceive = new String(dpReceive.getData()).trim();
            System.out.println(strReceive);
            String[] arr = strReceive.split(";");
            int s = Integer.parseInt(arr[2]);
            String ans = "";
            for(char x : arr[1].toCharArray()) {
                char tmp = x;
                if(x >= 'a' && x <= 'z') {
                    tmp = (char)((x - 'a' + s) % 26 + 'a');
                } else if(x >= 'A' && x <= 'Z') {
                    tmp = (char) ((x - 'A' + s) % 26 + 'A');
                }
                ans += tmp;
            }
            ans = arr[0] + ";" + ans;
            System.out.println(ans);
            dataSend = ans.getBytes();
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
