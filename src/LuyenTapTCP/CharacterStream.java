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
[Mã câu hỏi (qCode): h1u1qZu7].  [Loại bỏ ký tự đặc biệt, trùng và giữ nguyên thứ tự xuất hiện] 
* Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 (hỗ trợ thời gian 
* giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương 
* tác tới server sử dụng các luồng ký tự (BufferedReader/BufferedWriter) theo kịch bản dưới đây:
a.	Gửi một chuỗi gồm mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". 
* Ví dụ: "B15DCCN999;7D6265E3"
b.	Nhận một chuỗi ngẫu nhiên từ server
c.	Loại bỏ ký tự đặc biệt, số, ký tự trùng và giữ nguyên thứ tự xuất hiện của ký tự. 
* Gửi chuỗi đã được xử lý lên server.
d.	Đóng kết nối và kết thúc chương trình
 * @author Acer
 */
public class CharacterStream {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109", 2208);
            //1 gui ma sinh vien
            String str = "B18DCAT183;WAGEbtBq";
            BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(client.getOutputStream()));
            bw.write(str);
            bw.newLine();
            bw.flush();
            System.out.println(client.toString());
            
            // 2. nhan chuoi tu server
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            str = br.readLine();
            System.out.println(str);
            
            //3 thuc hien va gui ket qua len server
            int[] count = new int[256];
            StringBuilder sb = new StringBuilder();
            for(char x : str.toCharArray()) {
                count[x]++;
                if(Character.isAlphabetic(x) && count[x] == 1) {
                    sb.append(x);
                }
            }
            System.out.println(sb.toString());
            
            bw.write(sb.toString());
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
