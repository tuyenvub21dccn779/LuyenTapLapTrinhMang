/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
[Mã câu hỏi (qCode): b5WzlcV4].  Mật mã caesar, còn gọi là mật mã dịch chuyển, để giải mã thì 
* mỗi ký tự nhận được sẽ được thay thế bằng một ký tự cách nó một đoạn s. Ví dụ: với s = 3 thì 
* ký tự “A” sẽ được thay thế bằng ký tự “D”.
Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2207 (hỗ trợ thời gian giao 
* tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng chương trình client tương tác với 
* server trên, sử dụng các luồng byte (DataInputStream/DataOutputStream) để trao đổi thông 
* tin theo thứ tự:
a.	Gửi một chuỗi gồm mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". 
* Ví dụ: "B15DCCN999;D68C93F7"
b.	Nhận lần lượt chuỗi đã bị mã hóa caesar và giá trị dịch chuyển s nguyên
c.	Thực hiện giải mã ra thông điệp ban đầu và gửi lên Server
d.	Đóng kết nối và kết thúc chương trình.
 * @author Acer
 */
public class DataStream {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109", 2207);
            // 1. gui ma sinh vien
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            String str = "B21DCCN136;b5WzlcV4";
            dos.writeUTF(str);
            
            // 2. nhan chuoi ma hoa
            DataInputStream dis = new DataInputStream(client.getInputStream());
            str = dis.readUTF();
            int s = dis.readInt();
            System.out.println(str);
            System.out.println(s);
            
            //3 thuc hien va gui ket qua len server
            StringBuilder sb = new StringBuilder();
            for(char x : str.toCharArray()) {
                int newChar = x;
                if(x >= 'A' && x <= 'Z') {
                    newChar = ((x - 65) + s) % 26 + 65;
                } else if(x >= 'a' && x <= 'z') {
                    newChar = ((x - 97) + s) % 26 + 97;
                }
                sb.append((char)newChar);
            }
            
            System.out.println(sb.toString());
            dos.writeUTF(sb.toString());
            
            dos.close();
            dis.close();
            client.close();
            
        } catch (IOException ex) {
            Logger.getLogger(DataStream.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
