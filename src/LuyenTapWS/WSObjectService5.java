/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapWS;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import vn.medianews.CustomerY;
import vn.medianews.ObjectService;
import vn.medianews.ObjectService_Service;

/**
[Mã câu hỏi (qCode): 2HOZ5iWf].  Một dịch vụ web được định nghĩa và mô tả trong tệp 
* ObjectService.wsdl, được triển khai trên server tại 
* URL http://<Exam_IP>:8080/JNPWS/ObjectService?wsdl để xử lý các bài toán với đối tượng.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với ObjectService thực hiện 
* các công việc sau:
a. Triệu gọi phương thức requestListCustomerY với tham số đầu vào là mã sinh viên (studentCode) 
* và mã câu hỏi (qCode) để nhận về danh sách đối tượng CustomerY từ server. Mỗi đối tượng 
* CustomerY có các thuộc tính:
    customerId: kiểu String, đại diện cho mã khách hàng.
    lastTransactionDate: kiểu Date, đại diện cho ngày giao dịch gần nhất của khách hàng.
b. Lọc và giữ lại các khách hàng chưa có giao dịch nào trong vòng 6 tháng qua (so với ngày 
* hiện tại).
c. Triệu gọi phương thức submitListCustomerY(String studentCode, String qCode, List<CustomerY> customerYs) để gửi danh sách các khách hàng không hoạt động trong 6 tháng trở lại server. 
d. Kết thúc chương trình client.
 * @author Acer
 */
public class WSObjectService5 {
    public static void main(String[] args) {
        String studentCode = "B21DCCN136", qCode = "2HOZ5iWf";
        ObjectService_Service service = new ObjectService_Service();
        ObjectService port = service.getObjectServicePort();
        List<CustomerY> list = port.requestListCustomerY(studentCode, qCode);
        Calendar nowCal = Calendar.getInstance();
        List<CustomerY> ans = new ArrayList<>();
        for(CustomerY cus : list) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(cus.getLastTransactionDate().toGregorianCalendar().getTime());
            cal.add(Calendar.MONTH, 6);
            if(cal.before(nowCal)) ans.add(cus);
        }
        System.out.println(list);
        System.out.println(ans);
        port.submitListCustomerY(studentCode, qCode, ans);
        
    }
}
