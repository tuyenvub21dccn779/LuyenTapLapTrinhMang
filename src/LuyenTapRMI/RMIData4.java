/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapRMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import RMI.DataService;

/**
[Mã câu hỏi (qCode): gkeQa7xa].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý dữ liệu.
Giao diện từ xa:
public interface DataService extends Remote {
public Object requestData(String studentCode, String qCode) throws RemoteException;
public void submitData(String studentCode, String qCode, Object data) throws RemoteException;
}
Trong đó:
•	Interface DataService được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa DataService được đăng ký với RegistryServer với tên là: RMIDataService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu nhận được từ RMI Server:
a. Triệu gọi phương thức requestData để nhận hai số nguyên dương N và K từ server, đại diện cho khoảng cần kiểm tra (N <= số < K).
b. Xác định tất cả các số nguyên đối xứng (Palindrome Number) trong khoảng từ N đến K. Kết quả trả về là danh sách các số đối xứng thỏa mãn yêu cầu.
Ví dụ: Với N = 50 và K = 150, kết quả là [55, 66, 77, 88, 99, 101, 111, 121, 131, 141].
c. Triệu gọi phương thức submitData để gửi đối tượng List<Integer> danh sách các số nguyên đối xứng đã tìm được trở lại server.
d. Kết thúc chương trình client.
 * @author Acer
 */
public class RMIData4 {
    
    public static boolean checkSymmetricalNumber(int n) {
        StringBuilder sb = new StringBuilder(n + "");
        return sb.toString().equals(sb.reverse().toString());
    }
    
    public static void main(String[] args) {
        String path = "rmi://203.162.10.109/RMIDataService";
        String studentCode = "B21DCCN193", qCode = "gkeQa7xa";
        try {
            DataService service = (DataService) Naming.lookup(path);
            String strReceive = (String) service.requestData(studentCode, qCode);
            String[] arr = strReceive.trim().split("; ");
            System.out.println(strReceive);
            int n = Integer.parseInt(arr[0]), k = Integer.parseInt(arr[1]);
            List<Integer> ans = new ArrayList<>();
            for(int i=n; i<=k; i++){
                if(checkSymmetricalNumber(i)) ans.add(i);
            }
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
