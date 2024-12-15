/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapWS;

import java.util.ArrayList;
import java.util.List;
import vn.medianews.Customer;
import vn.medianews.ObjectService;
import vn.medianews.ObjectService_Service;

/**
[Mã câu hỏi (qCode): PwomDibK].  Một dịch vụ web được định nghĩa và mô tả trong tệp ObjectService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/ObjectService?wsdl để xử lý các bài toán với đối tượng.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với ObjectService thực hiện các công việc sau:
a. Triệu gọi phương thức requestListCustomer với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về danh sách đối tượng Customer từ server. Mỗi đối tượng Customer có các thuộc tính:
•	customerId: kiểu String, đại diện cho mã khách hàng.
•	location: kiểu String, đại diện cho địa điểm của khách hàng.
•	purchaseCount: kiểu int, đại diện cho số lần mua hàng của khách hàng.
•	totalSpent: kiểu float, đại diện cho tổng số tiền đã chi tiêu của khách hàng.
b. Lọc và giữ lại các khách hàng có totalSpent lớn hơn 5000 và có purchaseCount từ 5 trở lên.
c. Triệu gọi phương thức submitListCustomer(String studentCode, String qCode, List<Customer> customers) để gửi danh sách các khách hàng tiềm năng trở lại server. 
d. Kết thúc chương trình client.
 * @author Acer
 */
public class WSObjectService4 {
    public static void main(String[] args) {
        String studentCode = "B21DCCN193", qCode = "PwomDibK";
        ObjectService_Service service = new ObjectService_Service();
        ObjectService port = service.getObjectServicePort();
        List<Customer> list = port.requestListCustomer(studentCode, qCode);
        List<Customer> ans = new ArrayList<>();
        for(Customer cus : list) {
            if(cus.getPurchaseCount() >= 5 && cus.getTotalSpent() > 5000) {
                ans.add(cus);
            }
        }
        port.submitListCustomer(studentCode, qCode, ans);
    }
}
