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

/**
[Mã câu hỏi (qCode): YLTqaIEn].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho 
* phép triệu gọi từ xa để xử lý dữ liệu nhị phân.
Giao diện từ xa:
public interface ByteService extends Remote {
public byte[] requestData(String studentCode, String qCode) throws RemoteException;
public void submitData(String studentCode, String qCode, byte[] data) throws RemoteException;
}
Trong đó:
•	Interface ByteService được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa ByteService được đăng ký với RegistryServer với tên 
* là: RMIByteService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu 
* byte nhận được từ RMI Server:
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
public class RMIByte4 {
    public static void main(String[] args) {
        String path = "rmi://203.162.10.109/RMIByteService";
        String studentCode = "B21DCCN136", qCode = "YLTqaIEn";
        try {
            ByteService service = (ByteService) Naming.lookup(path);
            byte[] data = service.requestData(studentCode, qCode);
            ArrayList<Integer> list = new ArrayList<>();
            int count = 1;
            System.out.print(((int) data[0]) + ", ");
            for(int i=1; i < data.length; i++) {
                System.out.print(((int) data[i]) + ", ");
                if(data[i] != data[i-1]) {
                    list.add((int)data[i-1]);
                    list.add(count);
                    count = 1;
                } else count++;
            }
            System.out.println("");
            list.add((int)data[data.length-1]);
            list.add(count);
            System.out.println(list);
            data = new byte[list.size()];
            for(int i = 0; i<list.size(); i++) {
                data[i] = (byte) (int) list.get(i);
            }
            service.submitData(studentCode, qCode, data);
            
        } catch (NotBoundException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
