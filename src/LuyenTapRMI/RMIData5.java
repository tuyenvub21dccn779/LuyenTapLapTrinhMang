/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapRMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import RMI.DataService;

/**
[Mã câu hỏi (qCode): mbaOgGZY].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện 
* cho phép triệu gọi từ xa để xử lý dữ liệu.
Giao diện từ xa:
public interface DataService extends Remote {
public Object requestData(String studentCode, String qCode) throws RemoteException;
public void submitData(String studentCode, String qCode, Object data) throws RemoteException;
}
Trong đó:
•	Interface DataService được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa DataService được đăng ký với RegistryServer với tên 
* là: RMIDataService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ 
* liệu nhận được từ RMI Server:
a. Triệu gọi phương thức requestData để nhận một chuỗi chá tập hợp các số thực từ server.
b. Tính toán phương sai (variance) và độ lệch chuẩn (standard deviation) của tập hợp dữ liệu 
* này (làm tròn tới 02 chữ số thập phân)
Ví dụ: Với tập dữ liệu nhận được “2.0, 4.0, 4.0, 4.0, 5.0, 5.0, 7.0, 9.0”, phương sai là 4.00 
* và độ lệch chuẩn là 2.00 
c. Triệu gọi phương thức submitData để gửi chuỗi chứa kết quả phương sai và độ lệch chuẩn trở 
* lại server dưới dạng một cặp giá trị “variance : standard deviation”
d. Kết thúc chương trình client.
 * @author Acer
 */
public class RMIData5 {
    public static void main(String[] args) {
        String url = "rmi://203.162.10.109/RMIDataService";
        String studentCode = "B21DCCN136", qCode = "q7gFIbku";
        try {
            DataService service = (DataService) Naming.lookup(url);
            String strReceive = (String) service.requestData(studentCode, qCode);
            System.out.println(strReceive);
            String[] arr = strReceive.trim().split(", ");
            double variance = 0;
            double average = 0;
            double[] list = new double[arr.length];
            for(int i = 0; i < arr.length; i++) {
                list[i] = Double.parseDouble(arr[i]);
                average += list[i];
            }
            average /= list.length;
            for(double x : list) {
                variance += (x - average) * (x - average);
            }
            variance /= list.length;
            double std = Math.sqrt(variance);
            
            String strSend = String.format("%.2f : %.2f", variance, std);
            System.out.println(strSend);
            service.submitData(studentCode, qCode, strSend);
            
        } catch (NotBoundException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
