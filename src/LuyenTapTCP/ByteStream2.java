/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapTCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Comparator;

/**
[Mã câu hỏi (qCode): fmUMeQU1].  Một chương trình server cho phép kết nối qua giao thức TCP 
* tại cổng 2206 (thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một 
* chương trình client tương tác tới server ở trên sử dụng các luồng byte 
* (InputStream/OutputStream) để trao đổi thông tin theo thứ tự: 
a.	Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". 
* Ví dụ: "B16DCCN999;2B3A6510"
b.	Nhận dữ liệu từ server là một chuỗi các giá trị số nguyên được phân tách nhau bởi ký 
* tự ",". Ví dụ: 1,3,9,19,33,20
c.	Tìm và gửi lên server giá trị lớn thứ hai cùng vị trí xuất hiện của nó trong chuỗi.
* Ví dụ: 20,5
d.	Đóng kết nối và kết thúc chương trình.
 * @author Acer
 */
public class ByteStream2 {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109", 2206);
            
            String strSend = "B21DCCN136;fmUMeQU1";
            OutputStream os = client.getOutputStream();
            os.write(strSend.getBytes());
            os.flush();
            
            InputStream is = client.getInputStream();
            byte[] buff = new byte[1024];
            is.read(buff);
            String strReceive = new String(buff);
            String[] arr = strReceive.trim().split(",");
            Integer max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
            Integer pos1 = 0, pos2 = 0;
            for(int i = 0; i < arr.length; i++) {
                Integer tmp = Integer.parseInt(arr[i]);
                if(tmp > max1) {
                    max2 = max1;
                    max1 = tmp;
                    pos2 = pos1;
                    pos1 = i;
                }
                else if(tmp > max2) {
                    max2 = tmp;
                    pos2 = i;
                }
                
            }
            
            strSend = max2 + "," + pos2;
            System.out.println(strReceive);
            System.out.println(strSend);
            os.write(strSend.getBytes());
            os.flush();
            
            is.close();
            os.close();
            client.close();
            
            
            
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
