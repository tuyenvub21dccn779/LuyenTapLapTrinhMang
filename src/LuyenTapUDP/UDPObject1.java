/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapUDP;

import UDP.Product;
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
[Mã câu hỏi (qCode): zYE75XAS].  Thông tin sản phẩm vì một lý do nào đó đã bị sửa đổi thành không đúng, cụ thể:
a.	Tên sản phẩm bị đổi ngược từ đầu tiên và từ cuối cùng, ví dụ: “lenovo thinkpad T520” bị chuyển thành “T520 thinkpad lenovo”
b.	Số lượng sản phẩm cũng bị đảo ngược giá trị, ví dụ từ 9981 thành 1899

Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2209. Yêu cầu là xây dựng một chương trình client giao tiếp với server để gửi/nhận các sản phẩm theo mô tả dưới đây:
a.	Đối tượng trao đổi là thể hiện của lớp Product được mô tả như sau
•	Tên đầy đủ của lớp: UDP.Product
•	Các thuộc tính: id String, code String, name String, quantity int
•	Một hàm khởi tạo có đầy đủ các thuộc tính được liệt kê ở trên
•	Trường dữ liệu: private static final long serialVersionUID = 20161107; 
b.	Giao tiếp với server theo kịch bản
•       Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”. Ví dụ: “;B15DCCN001;EE29C059”

•	Nhận thông điệp chứa: 08 byte đầu chứa chuỗi requestId, các byte còn lại chứa một đối tượng là thể hiện của lớp Product từ server. Trong đối tượng này, các thuộc tính id, name và quantity đã được thiết lập giá trị.
•	Sửa các thông tin sai của đối tượng về tên và số lượng như mô tả ở trên và gửi đối tượng vừa được sửa đổi lên server theo cấu trúc:
08 byte đầu chứa chuỗi requestId và các byte còn lại chứa đối tượng Product đã được sửa đổi.
•	Đóng socket và kết thúc chương trình.
 * @author Acer
 */
public class UDPObject1 {
    public static void main(String[] args) {
            try {
                DatagramSocket client = new DatagramSocket();
                InetAddress inet = InetAddress.getByName("203.162.10.109");
                
                byte[] data = ";B21DCCN023;zYE75XAS".getBytes();
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
                Product pro = (Product) ois.readObject();
                System.out.println(pro);
                
                StringBuilder sb = new StringBuilder(pro.getQuantity() + "");
                pro.setQuantity(Integer.parseInt(sb.reverse().toString().trim()));
                
                String[] arr = pro.getName().split("\\s+");
                String temp = arr[0];
                arr[0] = arr[arr.length-1];
                arr[arr.length-1] = temp;
                
                String strName = "";
                for(String x : arr) {
                    strName += x + " ";
                }
                strName = strName.trim();
                
                pro.setName(strName);
                System.out.println(pro);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(baos);
                dos.write(requestId);
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(pro);
                
                data = baos.toByteArray();
                dpSend = new DatagramPacket(data, data.length, inet, 2209);
                client.send(dpSend);
                
                dis.close();
                ois.close();
                bais.close();
                
                dos.close();
                oos.close();
                baos.close();
                
                client.close();
                
            } catch (SocketException | UnknownHostException ex) {
                ex.printStackTrace();
            } catch(IOException ex) {
                ex.printStackTrace();
            } catch(ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            
            
    }
}
