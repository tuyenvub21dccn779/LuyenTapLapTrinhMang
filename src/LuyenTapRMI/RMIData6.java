/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapRMI;

/**
[Mã câu hỏi (qCode): jYl1twvh].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho 
* phép triệu gọi từ xa để xử lý dữ liệu.
Giao diện từ xa:
public interface DataService extends Remote {
public Object requestData(String studentCode, String qCode) throws RemoteException;
public void submitData(String studentCode, String qCode, Object data) throws RemoteException;
}
Trong đó:
•	Interface DataService được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa DataService được đăng ký với RegistryServer với tên 
* là: RMIDataService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu 
* nhận được từ RMI Server:
a. Triệu gọi phương thức requestData để nhận một chuỗi gồm tập hợp số nguyên và một số nguyên 
* K từ server với định dạng: “mảng; số nguyên K”.
b. Sử dụng thuật toán sinh tổ hợp để tạo ra tất cả các tổ hợp kích thước K của tập hợp đã cho. 
* Kết quả trả về là danh sách các tổ hợp con có K phần tử.
Ví dụ: Với tập hợp [1, 2, 3, 4] và K = 2, kết quả là danh sách các tổ hợp 
* [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]].
c. Triệu gọi phương thức submitData để gửi List<List<Integer>> chứa danh sách các tổ hợp đã 
* sinh được trở lại server.
d. Kết thúc chương trình client.
 * @author Acer
 */
public class RMIData6 {
    public static void main(String[] args) {
        
    }
}
