/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapTCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
[Mã câu hỏi (qCode): IGMisjbU].  Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2206 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). 
Yêu cầu xây dựng chương trình client thực hiện kết nối tới server trên sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự: 
a.	Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;C64967DD"
b.	Nhận dữ liệu từ server là một chuỗi gồm các giá trị nguyên được phân tách với nhau bằng  "|"
Ex: 2|5|9|11
c.	Thực hiện tìm giá trị tổng của các số nguyên trong chuỗi và gửi lên server
Ex: 27
d.	Đóng kết nối và kết thúc
 * @author Acer
 */
public class ByteStream3 {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109", 2206);
            
            byte[] dataSend = "B21DCCN023;IGMisjbU".getBytes();
            OutputStream os = client.getOutputStream();
            os.write(dataSend);
            os.flush();
            
            InputStream is = client.getInputStream();
            byte[] dataReceive = new byte[1024];
            is.read(dataReceive);
            
            String strReceive = new String(dataReceive);
            System.out.println(strReceive);
            
            String[] arr = strReceive.trim().split("\\|");
            int sum = 0;
            for(String x : arr) {
                sum += Integer.parseInt(x);
            }
            System.out.println(sum);
            dataSend = (sum + "").getBytes();
            os.write(dataSend);
            os.flush();
            
            is.close();
            os.close();
            client.close();
            
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
