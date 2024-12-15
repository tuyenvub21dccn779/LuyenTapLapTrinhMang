/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapRMI;

import RMI.DataService;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
[Mã câu hỏi (qCode): swZtSFb4].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện 
* cho phép triệu gọi từ xa để xử lý dữ liệu.
Giao diện từ xa:
public interface DataService extends Remote {
public Object requestData(String studentCode, String qCode) throws RemoteException;
public void submitData(String studentCode, String qCode, Object data) throws RemoteException;
}
Trong đó:
•	Interface DataService được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa DataService được đăng ký với RegistryServer với 
* tên là: RMIDataService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu
* nhận được từ RMI Server:
a. Triệu gọi phương thức requestData để nhận một số nguyên dương N từ server, đại diện cho 
* giới hạn trên của khoảng cần kiểm tra.
b. Thực hiện tìm tất cả các số nguyên tố trong khoảng từ 1 đến N. Ví dụ: Với N = 10, kết quả 
* là danh sách các số nguyên tố “2, 3, 5, 7”.
c. Triệu gọi phương thức submitData để gửi List< Integer> danh sách các số nguyên tố đã tìm 
* được trở lại server.
d. Kết thúc chương trình client.
 * @author Acer
 */
public class RMIData2 {
    
    public static boolean checkPrime(int n) {
        for(int i=2; i<=Math.sqrt(n); i++) {
            if(n%i==0) return false;
        }
        return n > 1;
    }
    
    public static List<Integer> getPrimes(int n) {
        List<Integer> list = new ArrayList<>();
        for(int i=2; i<=n; i++) {
            if(checkPrime(i)) list.add(i);
        }
        return list;
    }
    
    public static void main(String[] args) {
        String path = "rmi://203.162.10.109/RMIDataService";
        String studentCode = "B21DCCN175", qCode = "swZtSFb4";
        try {
            DataService service = (DataService) Naming.lookup(path);
            Integer n = (Integer) service.requestData(studentCode, qCode);
            List<Integer> ans = getPrimes(n);
            System.out.println(n);
            System.out.println(ans);
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
