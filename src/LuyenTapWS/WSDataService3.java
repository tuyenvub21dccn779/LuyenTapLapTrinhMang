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
[Mã câu hỏi (qCode): IgWZ8GXM].  Một dịch vụ web được định nghĩa và mô tả trong tệp 
* DataService?wsdl, được triển khai trên server tại 
* URL http://<Exam_IP>:8080/JNPWS/DataService?wsdl để xử lý các bài toán với dữ liệu nguyên thủy.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với DataService thực hiện 
* các công việc sau:
a. Triệu gọi phương thức getData với tham số đầu vào là mã sinh viên (studentCode) và mã câu 
* hỏi (qCode) để nhận về một danh sách số nguyên (List<Integer>) từ server.
b. Chuyển đổi số nguyên nhận được từ hệ thập phân sang hệ nhị phân và biểu diễn kết quả dưới 
* dạng chuỗi nhị phân.
c. Triệu gọi phương thức submitDataStringArray(String studentCode, String qCode, 
* List<String> data) để gửi chuỗi nhị phân đã chuyển đổi trở lại server.
Ví dụ: Nếu mỗi số nguyên nhận được từ phương thức getData, chương trình client sẽ chuyển đổi 
* sang chuỗi nhị phân là "1010", và gửi mảng chuỗi này trở lại server qua phương thức submitData.
d. Kết thúc chương trình client.
 * @author Acer
 */
public class WSDataService3 {
    
    public static String toBinary(int n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            sb.append(n % 2);
            n /= 2;
        }
        return sb.reverse().toString();
    }
    
    public static void main(String[] args) {
        DataService_Service service = new DataService_Service();
        DataService port = service.getDataServicePort();
        String studentCode = "B21DCCN175", qCode = "IgWZ8GXM";
        List<Integer> listReceive = port.getData(studentCode, qCode);
        System.out.println(listReceive);
        List<String> listSend = new ArrayList<>();
        for(Integer x : listReceive) {
            listSend.add(toBinary(x));
        }
        port.submitDataStringArray(studentCode, qCode, listSend);
        System.out.println(listSend);
    }
}
