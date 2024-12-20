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
[Mã câu hỏi (qCode): xV5cqI5G].  Một chương trình server hỗ trợ kết nối qua giao thức TCP 
* tại cổng 2206 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu xây dựng 
* chương trình client thực hiện kết nối tới server trên sử dụng luồng byte dữ liệu 
* (InputStream/OutputStream) để trao đổi thông tin theo thứ tự: 
a.	Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". 
* Ví dụ: "B16DCCN999;FF49DC02"
b.	Nhận dữ liệu từ server là một chuỗi các giá trị số nguyên được phân tách nhau bởi ký 
* tự ","
Ex: 1,3,9,19,33,20
c.	Thực hiện tìm giá trị khoảng cách nhỏ nhất của các phần tử nằm trong chuỗi và hai giá 
* trị lớn nhất tạo nên khoảng cách đó. Gửi lên server chuỗi gồm 
* "khoảng cách nhỏ nhất, số thứ nhất, số thứ hai". Ex: 1,19,20
d.	Đóng kết nối và kết thúc
 * @author Acer
 */
public class ByteStream1 {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109", 2206);
            
            OutputStream os = client.getOutputStream();
            String strSend = "B18DCAT183;xV5cqI5G";
            os.write(strSend.getBytes());
            os.flush();
            
            InputStream is = client.getInputStream();
            byte[] buff = new byte[1024];
            is.read(buff);
            
            String receiveStr = new String(buff).trim();
            System.out.println(receiveStr);
            
            String[] arr = receiveStr.split(",");
            ArrayList<Integer> list = new ArrayList<>();
            for(String x : arr) {
                Integer tmp = Integer.parseInt(x);
                list.add(tmp);
            }
            
            list.sort(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
                
            });
            System.out.println(list);
            int res = Integer.MAX_VALUE;
            int num1 = 0, num2 = 0;
            for(int i=1; i<list.size(); i++) {
                if(list.get(i) - list.get(i-1) <= res) {
                    num1 = list.get(i-1);
                    num2 = list.get(i);
                    res = num2 - num1;
                }
            }
            strSend = res + "," + num1 + "," + num2;
            System.out.println(strSend);
            os.write(strSend.getBytes());
            os.flush();
            
            os.close();
            is.close();
            client.close();
            
        } catch(IOException ex) {
            ex.printStackTrace();
        }
            
    }
}
