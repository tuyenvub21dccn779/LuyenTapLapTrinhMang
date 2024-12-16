/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapUDP;

import UDP.Student;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
[Mã câu hỏi (qCode): yvb6yH98].  Một chương trình server cho phép giao tiếp qua giao thức UDP 
* tại cổng 2209. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo 
* kịch bản sau:
Đối tượng trao đổi là thể hiện của lớp UDP.Student được mô tả:
•	Tên đầy đủ lớp: UDP.Student
•	Các thuộc tính: id String,code String, name String, email String
•	02 Hàm khởi tạo: 
o	public Student(String id, String code, String name, String email)
o	public Student(String code)
•	Trường dữ liệu: private static final long serialVersionUID = 20171107
Thực hiện:
•       Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng 
* “;studentCode;qCode”. Ví dụ: “;B15DCCN001;EE29C059”
b.	Nhận thông điệp chứa: 08 byte đầu chứa chuỗi requestId, các byte còn lại chứa một 
* đối tượng là thể hiện của lớp Student từ server. Trong đó, các thông tin được thiết lập 
* gồm id và name.
c.	Yêu cầu:
-	Chuẩn hóa tên theo quy tắc: Chữ cái đầu tiên in hoa, các chữ cái còn lại in thường 
* và gán lại thuộc tính name của đối tượng
-	Tạo email ptit.edu.vn từ tên người dùng bằng cách lấy tên và các chữ cái bắt đầu của 
* họ và tên đệm. Ví dụ: nguyen van tuan nam -> namnvt@ptit.edu.vn. Gán giá trị này cho thuộc
* tính email của đối tượng nhận được
-	Gửi thông điệp chứa đối tượng xử lý ở bước c lên Server với cấu trúc: 08 byte đầu 
* chứa chuỗi requestId và các byte còn lại chứa đối tượng Student đã được sửa đổi.
d.	Đóng socket và kết thúc chương trình.
 * @author Acer
 */
public class UDPObject {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress inet = InetAddress.getByName("203.162.10.109");
            
            byte[] data = ";B21DCCN136;h0dnleHi".getBytes();
            DatagramPacket dpSend = new DatagramPacket(data, data.length, inet, 2209);
            client.send(dpSend);
            
            byte[] buff = new byte[1024];
            DatagramPacket dpReceive = new DatagramPacket(buff, buff.length);
            client.receive(dpReceive);
            
            ByteArrayInputStream bais = new ByteArrayInputStream(dpReceive.getData());
            DataInputStream dis = new DataInputStream(bais);
            byte[] requestId = new byte[8];
            dis.read(requestId);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Student stu = (Student) ois.readObject();
            System.out.println(stu);
            
            String[] arr = stu.getName().toLowerCase().split("\\s+");
            StringBuilder sbName = new StringBuilder();
            StringBuilder sbEmail = new StringBuilder(arr[arr.length-1]);
            
            for(int i = 0; i<arr.length; i++) {
                if(i!=arr.length-1) {
                    sbEmail.append(arr[i].charAt(0));
                }
                
                sbName.append(Character.toUpperCase(arr[i].charAt(0)) + arr[i].substring(1) + " ");
            }
            stu.setEmail(sbEmail.toString().trim() + "@ptit.edu.vn");
            stu.setName(sbName.toString().trim());
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            dos.write(requestId);
            
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(stu);
            System.out.println(stu);
            
            data = baos.toByteArray();
            dpSend = new DatagramPacket(data, data.length, inet, 2209);
            client.send(dpSend);
            
            client.close();
            
        } catch(SocketException | UnknownHostException ex) {
            ex.printStackTrace();
        } catch(IOException ex) {
            ex.printStackTrace();
        } catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
