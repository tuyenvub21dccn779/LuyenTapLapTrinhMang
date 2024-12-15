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
[Mã câu hỏi (qCode): fEsn7BTP].  Một chương trình server cho phép kết nối qua giao thức TCP 
* tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng 
* một chương trình client tương tác với server sử dụng các luồng byte 
* (BufferedWriter/BufferedReader) theo kịch bản sau: 
a.	Gửi một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". 
* Ví dụ: "B15DCCN999;BAA62945"
b.	Nhận một chuỗi ngẫu nhiên từ server
Ví dụ: dgUOo ch2k22ldsOo
c.	Liệt kê các ký tự (là chữ hoặc số) xuất hiện nhiều hơn một lần trong chuỗi và số 
* lần xuất hiện của chúng và gửi lên server
Ví dụ: d:2,O:2,o:2,2:3,
d.	Đóng kết nối và kết thúc chương trình.
 * @author Acer
 */
public class CharacterStream1 {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109", 2208);
            
            String strSend = "B21DCCN175;TtRXRAEe";
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bw.write(strSend);
            bw.newLine();
            bw.flush();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String strReceive = br.readLine();
            System.out.println(strReceive);
            int[] count = new int[256];
            for(char x : strReceive.toCharArray()) {
                count[x] ++;
            }
            strSend = "";
            for(char x : strReceive.toCharArray()) {
                if((count[x] > 1) && (Character.isAlphabetic(x) || Character.isDigit(x)) ) {
                    strSend += x + ":" + count[x] + ",";
                    count[x] = 0;
                }
            }
            System.out.println(strSend);
            bw.write(strSend);
            bw.newLine();
            bw.flush();
            
            bw.close();
            br.close();
            client.close();
            
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
