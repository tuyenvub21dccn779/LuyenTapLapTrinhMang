/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
[Mã câu hỏi (qCode): 71Eegaik].  Một chương trình server cho phép kết nối qua TCP tại cổng 
* 2207 (hỗ trợ thời gian liên lạc tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu là xây dựng chương 
* trình client tương tác với server bằng các byte stream (DataInputStream/DataOutputStream) để 
* trao đổi thông tin theo trình tự sau:

a. Gửi một chuỗi chứa mã sinh viên và mã câu hỏi ở định dạng "studentCode;qCode". 
* Ví dụ: "B10DCCN000;A1B2C3D4".

b. Nhận từ server một mảng chứa n số nguyên, với n được gửi từ máy chủ. Ví dụ: Server gửi 
* mảng [5, 9, 3, 6, 8].

c. Tính tổng, trung bình cộng, và phương sai của mảng. Gửi kết quả lần lượt lên server dưới 
* dạng số nguyên và float. Ví dụ, gửi lên lần lượt: 31, 6.2, 4.5599995.

d. Đóng kết nối và kết thúc chương trình.
 * @author Acer
 */
public class DataStream1 {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109", 2207);
            
            String strSend = "B18DCAT183;71Eegaik";
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            dos.writeUTF(strSend);
            
            DataInputStream dis = new DataInputStream(client.getInputStream());
            ArrayList<Integer> list = new ArrayList<>();
            Integer n = dis.readInt();
            Integer sum = 0;
            for(int i=0; i<n; i++) {
                Integer num = dis.readInt();
                list.add(num);
                sum += num;
            }
            Float average = 1f * sum / n;
            Float std = 0f;
            for(Integer x : list) {
                std += (1f * x - average) * (1f * x - average);
            }
            std /= n;
            dos.writeInt(sum);
            dos.writeFloat(average);
            dos.writeFloat(std);
            
            dos.close();
            dis.close();
            client.close();
            
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
