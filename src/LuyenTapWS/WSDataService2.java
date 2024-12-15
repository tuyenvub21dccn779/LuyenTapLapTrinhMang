/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapWS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import vn.medianews.DataService;
import vn.medianews.DataService_Service;

/**
[Mã câu hỏi (qCode): wnTFZAN5].  Một dịch vụ web được định nghĩa và mô tả trong tệp 
* DataService?wsdl, được triển khai trên server tại 
* URL http://<Exam_IP>:8080/JNPWS/DataService?wsdl để xử lý các bài toán với dữ liệu nguyên thủy.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với DataService thực hiện các 
* công việc sau:
a. Triệu gọi phương thức getData với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi 
* (qCode) để nhận về một danh sách số nguyên (List<Integer>) từ server.
b. Với danh sách số nguyên nhận được, xóa tất cả các phần tử trùng lặp, giữ lại lần xuất hiện 
* đầu tiên của mỗi phần tử. Mảng kết quả sẽ chỉ chứa các phần tử duy nhất theo thứ tự xuất 
* hiện ban đầu.
c. Triệu gọi phương thức submitDataIntArray(String studentCode, String qCode, List<Integer> data)
* để gửi mảng kết quả đã loại bỏ các phần tử trùng lặp trở lại server.
Ví dụ: Nếu mảng số nguyên nhận được từ phương thức getData là [1, 2, 2, 3, 4, 3, 5], mảng kết 
* quả sau khi loại bỏ phần tử trùng lặp là [1, 2, 3, 4, 5]. Mảng này sẽ được gửi lại server 
* qua phương thức submitData.
d. Kết thúc chương trình client.
* 
 * @author Acer
 */
public class WSDataService2 {
    public static void main(String[] args) {
        String studentCode = "B21DCCN588", qCode = "wnTFZAN5";
        DataService_Service service = new DataService_Service();
        DataService port = service.getDataServicePort();
        List<Integer> list = port.getData(studentCode, qCode);
        System.out.println(list);
        List<Integer> ans = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for(Integer x : list) {
            if(!set.contains(x)) {
                ans.add(x);
                set.add(x);
            }
        }
        System.out.println(ans);
        port.submitDataIntArray(studentCode, qCode, ans);
    }
}
