/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapWS;

import java.util.ArrayList;
import java.util.List;
import vn.medianews.DataService;
import vn.medianews.DataService_Service;

/**
[Mã câu hỏi (qCode): CwFkVptU].  Một dịch vụ web được định nghĩa và mô tả trong tệp 
* DataService?wsdl, được triển khai trên server tại 
* URL http://<Exam_IP>:8080/JNPWS/DataService?wsdl để xử lý các bài toán với dữ liệu nguyên thủy.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với DataService thực hiện 
* các công việc sau:
a. Triệu gọi phương thức getDataDouble với tham số đầu vào là mã sinh viên (studentCode) và 
* mã câu hỏi (qCode) để nhận về một số thập phân (double) từ server.
b. Làm tròn số thập phân nhận được tới 02 chữ số thập phân và thực hiện chuyển đổi số thập 
* phân nhận được thành một phân số tối giản. Lưu tử số và mẫu số nguyên của phân số đó vào 
* danh sách (List<Integer>) với phần tử đầu tiên là tử số và phần tử thứ hai là mẫu số.
c. Triệu gọi phương thức submitDataIntArray(String studentCode, String qCode, List<Integer> data)
* để gửi phân số đơn giản nhất đã chuyển đổi trở lại server.
Ví dụ: Nếu số thập phân nhận được từ phương thức getData là 0.75, chương trình client sẽ 
* chuyển đổi thành phân số tối giản là [3, 4], và gửi mảng [3, 4] trở lại server qua phương 
* thức submitData.
d. Kết thúc chương trình client.
 * @author Acer
 */
public class WSDataService5 {
    
    public static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a%b);
    }
    
    public static void main(String[] args) {
        String studentCode = "B21DCCN193", qCode = "CwFkVptU";
        DataService_Service service = new DataService_Service();
        DataService port = service.getDataServicePort();
        double data = port.getDataDouble(studentCode, qCode);
        System.out.println(data);
        int tu = (int) (data * 100);
        int uocChung = gcd(tu, 100);
        List<Integer> ans = new ArrayList<>();
        ans.add(tu / uocChung);
        ans.add(100 / uocChung);
        System.out.println(ans);
        port.submitDataIntArray(studentCode, qCode, ans);
    }
}
