/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapRMI;

import java.rmi.Naming;
import RMI.DataService;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
[Mã câu hỏi (qCode): xqdPjHkJ].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý dữ liệu.
Giao diện từ xa:
public interface DataService extends Remote {
public Object requestData(String studentCode, String qCode) throws RemoteException;
public void submitData(String studentCode, String qCode, Object data) throws RemoteException;
}
Trong đó:
•	Interface DataService được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa DataService được đăng ký với RegistryServer với 
* tên là: RMIDataService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ 
* liệu nhận được từ RMI Server:
a. Triệu gọi phương thức requestData để nhận một số nguyên dương lớn từ server, gọi là N.
b. Thực hiện phân rã số N thành các thừa số nguyên tố. Kết quả trả về là danh sách các thừa 
* số nguyên tố của N.
Ví dụ: Với N = 84, kết quả là danh sách “2, 2, 3, 7”.
c. Triệu gọi phương thức submitData để gửi danh sách các thừa số nguyên tố đã tìm được trở 
* lại server.
d. Kết thúc chương trình client.
 * @author Acer
 */
public class RMIData7 {
    
    public static List<Integer> phanTichThuaSoNguyenTo(int n) {
        List<Integer> list = new ArrayList<>();
        for(int i=2; i<Math.sqrt(n); i++) {
            while(n % i == 0) {
                list.add(i);
                n /= i;
            }
        }
        if(n != 1) list.add(n);
        return list;
    }
    
    public static void main(String[] args) {
        String url = "rmi://203.162.10.109/RMIDataService";
        String student = "B21DCCN131", qCode = "xqdPjHkJ";
        try {
            DataService service = (DataService) Naming.lookup(url);
            Integer n = (Integer) service.requestData(student, qCode);
            System.out.println(n);
            List<Integer> list = phanTichThuaSoNguyenTo(n);
            System.out.println(list);
            service.submitData(student, qCode, list);
            
        } catch (NotBoundException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
