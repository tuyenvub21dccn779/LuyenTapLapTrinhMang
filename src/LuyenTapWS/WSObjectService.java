/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapWS;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vn.medianews.ObjectService;
import vn.medianews.ObjectService_Service;
import vn.medianews.Order;

/**
 [Mã câu hỏi (qCode): 7DKXZoSc].  Một dịch vụ web được định nghĩa và mô tả trong tệp 
 * ObjectService.wsdl, được triển khai trên server tại 
 * URL http://<Exam_IP>:8080/JNPWS/ObjectService?wsdl để xử lý các bài toán với đối tượng.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với ObjectService thực hiện 
* các công việc sau:
a. Triệu gọi phương thức requestListOrder với tham số đầu vào là mã sinh viên (studentCode) 
* và mã câu hỏi (qCode) để nhận về danh sách đối tượng Order từ server. Mỗi đối tượng Order 
* có các thuộc tính:
•	customerId: kiểu String, đại diện cho mã khách hàng.
•	amount: kiểu float, đại diện cho giá trị của đơn hàng.
•	status: kiểu String, đại diện cho trạng thái của đơn hàng, với các trạng thái có thể 
* là "completed", "pending", hoặc "canceled".
b. Thực hiện lọc và chỉ giữ lại các hóa đơn của khách hàng có tổng giá trị đơn hàng cao nhất
c. Triệu gọi phương thức submitListOrder(String studentCode, String qCode, List<Order> data)
* để gửi danh sách hóa đơn của khách hàng có tổng giá trị đơn hàng cao nhất.
d. Kết thúc chương trình client.
 * @author Acer
 */
public class WSObjectService {
    public static void main(String[] args) {
        ObjectService_Service service = new ObjectService_Service();
        ObjectService port = service.getObjectServicePort();
        String studentCode = "B21DCCN779", qCode = "7DKXZoSc";
        List<Order> list = port.requestListOrder(studentCode, qCode);
        Map<String, Float> map = new HashMap<>();
        List<String> listSort = new ArrayList<>();
        for(Order x : list) {
            if(map.containsKey(x.getCustomerId())) {
                Float newValue = map.get(x.getCustomerId()) + x.getAmount();
                map.put(x.getCustomerId(), newValue);
            } else {
                map.put(x.getCustomerId(), x.getAmount());
                listSort.add(x.getCustomerId());
            }
        }
        listSort.sort(new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                float value1 = map.get(o1), value2 = map.get(o2);
                if(value1 > value2) return -1;
                else return 1;
            }
        });
        List<Order> ans = new ArrayList<>();
        for(Order x : list) {
            if(x.getCustomerId().equals(listSort.get(0))) ans.add(x);
        }
        port.submitListOrder(studentCode, qCode, ans);
    }
}
