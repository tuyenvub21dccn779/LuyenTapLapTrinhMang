/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapTCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
[Mã câu hỏi (qCode): UnGVgyGP].  Một chương trình server cho phép kết nối qua giao thức 
* TCP tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là 
* xây dựng một chương trình client thực hiện kết nối tới server và sử dụng luồng ký tự 
* (BufferedWriter/BufferedReader) để trao đổi thông tin theo kịch bản
a.	Gửi một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". 
* Ví dụ: "B15DCCN999;5E263AE1"
b.	Nhận một chuỗi ngẫu nhiên từ server
c.	Tách chuỗi đã nhận thành 2 chuỗi và gửi lần lượt theo thứ tự lên server
           i.	Chuỗi thứ nhất gồm các ký tự và số (loại bỏ các ký tự đặc biệt)
          ii.	Chuỗi thứ hai gồm các ký tự đặc biệt
d.	Đóng kết nối và kết thúc chương trình
 * @author Acer
 */
public class CharacterStream4 {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109", 2208);
            
            String strSend = "B21DCCN131;qbI7dFkP";
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bw.write(strSend);
            bw.newLine();
            bw.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String strReceive = br.readLine().trim();
            System.out.println(strReceive);
            StringBuilder str1 = new StringBuilder(), str2 = new StringBuilder();
            for(char x : strReceive.toCharArray()) {
                if(Character.isAlphabetic(x) || Character.isDigit(x)) {
                    str1.append(x);
                } else {
                    str2.append(x);
                }
            }
            System.out.println(str1.toString());
            System.out.println(str2.toString());
            
            bw.write(str1.toString());
            bw.newLine();
            bw.write(str2.toString());
            bw.newLine();
            bw.flush();
            
            br.close();
            bw.close();
            client.close();
            
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
