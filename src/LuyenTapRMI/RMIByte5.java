/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapRMI;

import RMI.ByteService;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
[Mã câu hỏi (qCode): 5vHFqlaO].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý dữ liệu nhị phân.
Giao diện từ xa:
public interface ByteService extends Remote {
public byte[] requestData(String studentCode, String qCode) throws RemoteException;
public void submitData(String studentCode, String qCode, byte[] data) throws RemoteException;
}
Trong đó:
•	Interface ByteService được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa ByteService được đăng ký với RegistryServer với tên là: RMIByteService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu nhị phân nhận được từ RMI Server:
a. Triệu gọi phương thức requestData để nhận một mảng dữ liệu nhị phân (byte[]) từ server.
b. Chuyển đổi mảng dữ liệu nhị phân nhận được thành một chuỗi biểu diễn hex. Mỗi byte trong mảng sẽ được chuyển đổi thành hai ký tự hex tương ứng.
Ví dụ: Nếu dữ liệu nhị phân nhận được là [72, 101, 108, 108, 111], chương trình sẽ chuyển đổi mảng này thành chuỗi hex "48656c6c6f", tương ứng với chuỗi "Hello" trong ASCII.
c. Triệu gọi phương thức submitData để gửi chuỗi biểu diễn hex đã chuyển đổi thành mảng byte trở lại server.
d. Kết thúc chương trình client.
 * @author Acer
 */
public class RMIByte5 {
    
    public static String hex = "0123456789abcdef";
    
    public static String toHex(int n) {
        StringBuilder res = new StringBuilder();
        while(n > 0) {
            res.append(hex.charAt(n % 16));
            n /= 16;
        }
        return res.reverse().toString();
    }
    
    public static void main(String[] args) {
        String path = "rmi://203.162.10.109/RMIByteService";
        String studentCode = "B21DCCN193", qCode = "5vHFqlaO";
        try {
            ByteService service = (ByteService) Naming.lookup(path);
            byte[] data = service.requestData(studentCode, qCode);
            String ans = "";
            for(int x : data) {
                System.out.print(x + ", ");
                ans += toHex(x);
            }
            System.out.println("");
            System.out.println(ans);
            service.submitData(studentCode, qCode, ans.getBytes());
        } catch (NotBoundException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
        
    }
}
