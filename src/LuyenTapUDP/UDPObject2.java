/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapUDP;

import UDP.Customer;
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
[Mã câu hỏi (qCode): U35FtuNH].  Thông tin khách hàng được yêu cầu thay đổi định dạng lại cho 
* phù hợp với khu vực, cụ thể:
a.	Tên khách hàng cần được chuẩn hóa theo định dạng mới. 
* Ví dụ: nguyen van hai duong -> DUONG, Nguyen Van Hai
b.	Ngày sinh của khách hàng đang ở dạng mm-dd-yyyy, cần được chuyển thành định dạng 
* dd/mm/yyyy. Ví dụ: 10-11-2012 -> 11/10/2012
c.	Tài khoản khách hàng được tạo từ các chữ cái in thường được sinh tự động từ họ tên 
* khách hàng. Ví dụ: nguyen van hai duong -> nvhduong


Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2209. Yêu cầu là xây 
* dựng một chương trình client giao tiếp với server theo mô tả sau:
a.	Đối tượng trao đổi là thể hiện của lớp UDP.Customer được mô tả như sau
•	Tên đầy đủ của lớp: UDP.Customer
•	Các thuộc tính: id String, code String, name String, , dayOfBirth String, 
* userName String
•	Một Hàm khởi tạo với đầy đủ các thuộc tính được liệt kê ở trên
•	Trường dữ liệu: private static final long serialVersionUID = 20151107; 

b.	Client giao tiếp với server theo các bước
•       Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng 
* “;studentCode;qCode”. Ví dụ: “;B15DCCN001;EE29C059”

•	Nhận thông điệp chứa: 08 byte đầu chứa chuỗi requestId, các byte còn lại chứa một đối 
* tượng là thể hiện của lớp Customer từ server. Trong đó, các thuộc tính 
* id, code, name,dayOfBirth đã được thiết lập sẵn.
•	Yêu cầu thay đổi thông tin các thuộc tính như yêu cầu ở trên và gửi lại đối tượng 
* khách hàng đã được sửa đổi lên server với cấu trúc:
08 byte đầu chứa chuỗi requestId và các byte còn lại chứa đối tượng Customer đã được sửa đổi.
•	Đóng socket và kết thúc chương trình.
 * @author Acer
 */
public class UDPObject2 {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress inet = InetAddress.getByName("203.162.10.109");
            int port = 2209;
            
            byte[] byteSend = ";B21DCCN175;UbkkhTDD".getBytes();
            DatagramPacket dpSend = new DatagramPacket(byteSend, byteSend.length, inet, port);
            client.send(dpSend);
            
            byte[] byteReceive = new byte[1024];
            DatagramPacket dpReceive = new DatagramPacket(byteReceive, byteReceive.length);
            client.receive(dpReceive);
            
            ByteArrayInputStream bais = new ByteArrayInputStream(dpReceive.getData());
            byte[] requestId = new byte[8];
            DataInputStream dis = new DataInputStream(bais);
            dis.read(requestId);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Customer cust = (Customer) ois.readObject();
            
            String[] arr = cust.getName().toLowerCase().split("\\s+");
            StringBuilder newName = new StringBuilder(), newUserName = new StringBuilder();
            for(int i=0; i<arr.length; i++) {
                if(i == arr.length-1) {
                    newName.insert(0, arr[i].toUpperCase() + ", ");
                    newUserName.append(arr[i]);
                } else {
                    newName.append(Character.toUpperCase(arr[i].charAt(0)) + arr[i].substring(1) + " ");
                    newUserName.append(arr[i].charAt(0));
                }
                
            }
            System.out.println(cust);
            cust.setName(newName.toString().trim());
            cust.setUserName(newUserName.toString());
            
            arr = cust.getDayOfBirth().split("-");
            cust.setDayOfBirth(arr[1] + "/" + arr[0] + "/" + arr[2]);
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            dos.write(requestId);
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(cust);
            System.out.println(cust);
            byteSend = baos.toByteArray();
            dpSend = new DatagramPacket(byteSend, byteSend.length, inet, port);
            client.send(dpSend);
            
            bais.close();
            dis.close();
            ois.close();
            
            baos.close();
            dos.close();
            oos.close();
            
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
