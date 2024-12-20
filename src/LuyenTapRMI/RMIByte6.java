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
import java.util.ArrayList;
import java.util.List;

/**
[Mã câu hỏi (qCode): ugnfPE6b].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện 
* cho phép triệu gọi từ xa để xử lý dữ liệu nhị phân.
Giao diện từ xa:
public interface ByteService extends Remote {
public byte[] requestData(String studentCode, String qCode) throws RemoteException;
public void submitData(String studentCode, String qCode, byte[] data) throws RemoteException;
}
Trong đó:
•	Interface ByteService được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa ByteService được đăng ký với RegistryServer với tên 
* là: RMIByteService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ 
* liệu byte nhận được từ RMI Server:
a. Triệu gọi phương thức requestData để nhận một mảng dữ liệu byte từ server, đại diện cho một 
* chuỗi dữ liệu byte tổng quát.
b. Thực hiện nén dữ liệu sử dụng thuật toán Run-Length Encoding (RLE) bằng cách ghi nhận mỗi 
* byte và số lần lặp liên tiếp của nó. Kết quả nén là một mảng mới biểu diễn các cặp 
* (byte, số lần lặp).
Ví dụ: Nếu dữ liệu byte nhận được là [10, 10, 10, 20, 20, 30, 30, 30, 30], chương trình sẽ 
* thực hiện nén RLE như sau:
    Kết quả nén RLE là mảng [10, 3, 20, 2, 30, 4].
c. Triệu gọi phương thức submitData để gửi mảng dữ liệu đã được nén RLE trở lại server.
d. Kết thúc chương trình client.
 * @author Acer
 */
public class RMIByte6 {
    public static void main(String[] args) {
        String url = "rmi://203.162.10.109/RMIByteService";
        String studentCode = "B21DCCN158", qCode = "ugnfPE6b";
        try {
            ByteService service = (ByteService) Naming.lookup(url);
            byte[] data = service.requestData(studentCode, qCode);
            List<Integer> res = new ArrayList<>();
            int count = 1;
            for(int i=1; i < data.length; i++) {
                if((int)data[i] != (int)data[i-1]) {
                    res.add((int) data[i-1]);
                    res.add(count);
                    count = 1;
                } else count++;
            }
            res.add((int) data[data.length-1]);
            res.add(count);
            
            System.out.println(res);
            byte[] ans = new byte[res.size()];
            for(int i=0; i<res.size(); i++) {
                ans[i] = (byte) (int) res.get(i);
            }
            service.submitData(studentCode, qCode, ans);
            
            
        } catch (NotBoundException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
