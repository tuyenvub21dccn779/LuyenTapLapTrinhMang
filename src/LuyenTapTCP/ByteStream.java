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
[Mã câu hỏi (qCode): EwxVmDN5].  Một chương trình server cho phép kết nối qua giao thức TCP 
* tại cổng 2206 (thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một 
* chương trình client tương tác tới server ở trên sử dụng các luồng byte 
* (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:

a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". 
* Ví dụ: "B16DCCN999;A63D9404".

b. Nhận dữ liệu từ server là một chuỗi các số nguyên được sắp xếp ngẫu nhiên, các số được 
* phân tách nhau bởi ký tự ",". Ví dụ: "2,15,4,3,6,8,10,7,1".

c. Sắp xếp tăng dần các giá trị chẵn và sau đó tăng dần các giá trị lẻ trong dãy số. 
* Ví dụ: "[2, 4, 6, 8, 10];[1, 3, 7, 15]". Gửi chuỗi được sắp xếp này lên server.

d. Đóng kết nối và kết thúc chương trình.
 * @author Acer
 */
public class ByteStream {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109", 2206);
            //1 gui ma sinh vien
            OutputStream os = client.getOutputStream();
            String str = "B21DCCN175;EwxVmDN5";
            os.write(str.getBytes());
            
            //2 nhan du lieu 
            InputStream is = client.getInputStream();
            byte[] buffer = new byte[1024];
            is.read(buffer);
            str = new String(buffer);
            System.out.println(str);
            ArrayList<Integer> chan = new ArrayList<>();
            ArrayList<Integer> le = new ArrayList<>();
            
            String[] arr = str.trim().split(",");
            for(String x : arr) {
                Integer tmp = Integer.parseInt(x);
                if(tmp % 2 == 0) chan.add(tmp);
                else le.add(tmp);
            }
            
            chan.sort(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
                
            });
            
            le.sort(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
                
            });
            
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for(int x : chan) {
                sb.append(x + ", ");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
            sb.append("];[");
            for(int x : le) {
                sb.append(x + ", ");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
            System.out.println(sb.toString());
            os.write(sb.toString().getBytes());
            
            is.close();
            os.close();
            client.close();
            
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
