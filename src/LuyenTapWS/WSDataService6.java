/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapWS;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import vn.medianews.DataService;
import vn.medianews.DataService_Service;

/**
[Mã câu hỏi (qCode): SrXnlTXc].  Một dịch vụ web được định nghĩa và mô tả trong tệp 
* DataService?wsdl, được triển khai trên server tại 
* URL http://<Exam_IP>:8080/JNPWS/DataService?wsdl để xử lý các bài toán với dữ liệu nguyên thủy.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với DataService thực hiện các 
* công việc sau:
a. Triệu gọi phương thức getDataDouble với tham số đầu vào là mã sinh viên (studentCode) và mã 
* câu hỏi (qCode) để nhận về một số thập phân (double) từ server.
b. Lấy phần nguyên của số thập phân nhận được. Liệt kê tất cả các ước số của phần nguyên này và 
* đếm số lượng ước số. Kết quả sẽ là một danh sách số nguyên, trong đó phần tử đầu tiên là số 
* lượng ước số và các phần tử tiếp theo là các ước số theo thứ tự tăng dần.
c. Triệu gọi phương thức submitDataIntArray(String studentCode, String qCode, 
* List<Integer> data) để gửi danh sách kết quả chứa số lượng ước số và lần lượt các ước số 
* trở lại server.
Ví dụ: Nếu số nguyên nhận được từ phương thức getDataDouble là 12.69, các ước số là 
* [1, 2, 3, 4, 6, 12], tổng số lượng ước là 6, nên danh sách gửi lại sẽ là 
* [6, 1, 2, 3, 4, 6, 12].
d. Kết thúc chương trình client.
 * @author Acer
 */
public class WSDataService6 {
    
    public static List<Integer> getAnswer(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        res.add(n);
        int sqrt = (int) Math.sqrt(n);
        for(int i=2; i< sqrt ; i++) {
            if(n % i == 0) {
                res.add(i);
                if(1f * sqrt != Math.sqrt(n))
                    res.add(n / i);
            }
        }
        res.sort(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
            
        });    
        res.add(0, res.size());
        return res;
    }
    
    public static void main(String[] args) {
        String studentCode = "B21DCCN023", qCode = "SrXnlTXc";
        DataService_Service service = new DataService_Service();
        DataService port = service.getDataServicePort();
        int n = (int) port.getDataDouble(studentCode, qCode);
        List<Integer> ans = getAnswer(n);
        System.out.println(n);
        System.out.println(ans);
        port.submitDataIntArray(studentCode, qCode, ans);
    }
}
