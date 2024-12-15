/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
[Mã câu hỏi (qCode): V2Rr50gY].  Một chương trình máy chủ cho phép kết nối qua TCP tại cổng 2207 
* (hỗ trợ thời gian liên lạc tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng chương trình 
* client tương tác với server trên bằng các byte stream (DataInputStream/DataOutputStream) 
* để trao đổi thông tin theo trình tự sau:
a. Gửi một chuỗi chứa mã sinh viên và mã câu hỏi ở định dạng "studentCode;qCode". 
* Ví dụ: "B15DCCN999;B1F1FDCD"
b. Nhận hai số nguyên a và b tương ứng từ máy chủ
c. Tính ước chung lớn nhất, bội chung nhỏ nhất, tổng, tích. Gửi từng giá trị số nguyên 
* theo thứ tự trên đến máy chủ.
d. Đóng kết nối và kết thúc chương trình.
 * @author Acer
 */
public class DataStream3 {
    
    public static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a%b);
    }
    
    public static int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }
    
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109", 2207);
            
            String strSend = "B21DCCN175;lvmzy8ks";
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            dos.writeUTF(strSend);
            
            DataInputStream dis = new DataInputStream(client.getInputStream());
            int a = dis.readInt(), b = dis.readInt();
            int num1 = gcd(a, b), num2 = lcm(a, b), sum = a + b, tich = a * b;
            System.out.println(a + " " + b);
            System.out.println(num1 + " " + num2 + " " + sum + " " + tich);
            dos.writeInt(num1);
            dos.writeInt(num2);
            dos.writeInt(sum);
            dos.writeInt(tich);
            
            dis.close();
            dos.close();
            client.close();
            
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
