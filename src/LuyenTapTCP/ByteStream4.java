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
[Mã câu hỏi (qCode): o5U5fdAL].  
Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2206 (hỗ trợ thời gian giao 
* tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu xây dựng chương trình client thực hiện kết nối tới 
* server sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
    a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". 
    * Ví dụ: "B16DCCN999;76B68B3B".
    b. Nhận dữ liệu từ server là một chuỗi các giá trị số nguyên được phân tách bởi ký tự ",". 
    * Ví dụ: 5,10,20,25,50,40,30,35.
    c. Tìm chuỗi con tăng dần dài nhất và gửi độ dài của chuỗi đó lên server. 
    * Ví dụ: 5,10,20,25 có độ dài 4.
    d. Đóng kết nối và kết thúc chương trình.
 * @author Acer
 */
public class ByteStream4 {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109", 2206);
            
            byte[] data = "B21DCCN193;o5U5fdAL".getBytes();
            OutputStream os = client.getOutputStream();
            os.write(data);
            os.flush();
            System.out.println("send success");
            
            byte[] buff = new byte[1024];
            InputStream is = client.getInputStream();
            is.read(buff);
            System.out.println("receive success");
            
            String strReceive = new String(buff).trim();
            System.out.println(strReceive.length());
            String[] arr = strReceive.split(",");
            int count = 1, res = 1;
            StringBuilder ans = new StringBuilder(arr[0]);
            for(int i=1; i < arr.length; i++) {
                if(arr[i].compareTo(arr[i-1]) == 1) count++;
                else {
                    if(count > res) {
                        ans = new StringBuilder();
                        for(int j=i-count; j<i; j++) {
                            ans.append(arr[i] + ",");
                        }
                        ans.deleteCharAt(ans.length()-1);
                    } else count = 1;
                }
            }
            
            System.out.println(ans.toString());
            data = ans.toString().getBytes();
            os.write(data);
            os.flush();
            
            os.close();
            is.close();
            client.close();
            
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
