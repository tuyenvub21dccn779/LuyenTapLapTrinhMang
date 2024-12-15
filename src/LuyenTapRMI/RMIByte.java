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
[Mã câu hỏi (qCode): Ng84MbVa].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện 
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
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu 
* nhị phân nhận được từ RMI Server:
a. Triệu gọi phương thức requestData để nhận một mảng dữ liệu nhị phân (byte[]) từ server, đại 
* diện cho một chuỗi văn bản ASCII.
b. Thực hiện mã hóa Caesar cho mảng dữ liệu nhị phân bằng cách dịch chuyển mỗi byte trong mảng 
* đi một số bước cố định trong bảng mã ASCII. Số bước dịch chuyển là số ký tự ASCII trong mảng 
* dữ liệu.
    Ví dụ: Nếu dữ liệu nhị phân nhận được là [72, 101, 108, 108, 111] (tương ứng với 
    * chuỗi "Hello"), chương trình sẽ thực hiện mã hóa Caesar với độ dịch là 5. Kết quả mã 
    * hóa là mảng [77, 108, 113, 113, 116], tương ứng với chuỗi "Mlqqt".
c. Triệu gọi phương thức submitData để gửi mảng dữ liệu đã được mã hóa bằng Caesar trở lại server.
d. Kết thúc chương trình client.
 * @author Acer
 */
public class RMIByte {
    public static void main(String[] args) {
        try {
            ByteService service = (ByteService) Naming.lookup("rmi://203.162.10.109/RMIByteService");
            String studentCode = "B21DCCN023", qCode = "Ng84MbVa";
            byte[] data = service.requestData(studentCode, qCode);
            String str = new String(data);
            for(int x : data) {
                System.out.print(x + " ");
            }
            System.out.println("");
            for(int i=0; i<data.length; i++) {
                data[i] = (byte) (data[i] + str.length());
            }
            for(int x : data) {
                System.out.print(x + " ");
            }
            System.out.println("");
            service.submitData(studentCode, qCode, data);
        } catch (NotBoundException ex) {
            Logger.getLogger(RMIByte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(RMIByte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(RMIByte.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
