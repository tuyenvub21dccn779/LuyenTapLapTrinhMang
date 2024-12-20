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
 [Mã câu hỏi (qCode): 1LyvNl3h].  Một dịch vụ web được định nghĩa và mô tả trong tệp 
 * DataService?wsdl, được triển khai trên server tại 
 * URL http://<Exam_IP>:8080/JNPWS/DataService?wsdl để xử lý các bài toán với dữ liệu nguyên thủy.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với DataService thực hiện các 
* công việc sau:
a. Triệu gọi phương thức getData với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi 
* (qCode) để nhận về một danh sách số nguyên (List<Integer>) từ server. Phần tử đầu tiên của 
* mảng này là giá trị K.
b. Với danh sách số nguyên nhận được, sử dụng giá trị K (phần tử đầu tiên của của danh sách) 
* để xác định phần tử lớn thứ K và nhỏ thứ K trong các phần tử còn lại (loại bỏ phần tử đầu 
* tiên khi tính toán).
c. Triệu gọi phương thức submitDataIntArray(String studentCode, String qCode, List<Integer> data) 
* để gửi mảng kết quả chứa: phần tử lớn thứ K, phần tử nhỏ thứ K đã tìm được trở lại server.
Ví dụ: Nếu mảng số nguyên nhận được từ phương thức getData là [3, 5, 1, 4, 2], giá trị K là 3. C
* hương trình client sẽ tìm phần tử lớn thứ 3 và nhỏ thứ 3 trong mảng [5, 1, 4, 2], kết quả là 
* mảng [2, 4] và gửi kết quả này trở lại server qua phương thức submitData.
d. Kết thúc chương trình client.
 * @author Acer
 */
public class WSDataService1 {
    public static void main(String[] args) {
        DataService_Service service = new DataService_Service();
        DataService port = service.getDataServicePort();
        String studentCode = "B21DCCN131", qCode = "dfjmFZM2";
        List<Integer> list = port.getData(studentCode, qCode);
        System.out.println(list.size());
        System.out.println(list);
        List<Integer> listSort = new ArrayList<>();
        for(int i=1; i<list.size(); i++) {
            listSort.add(list.get(i));
        }
        listSort.sort(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
            
        });
        int k = list.get(0);
        int num1 = listSort.get(k-1);
        int num2 = listSort.get(listSort.size()-k);
        List<Integer> ans = new ArrayList<>();
        ans.add(num2);
        ans.add(num1);
        System.out.println(ans);
        port.submitDataIntArray(studentCode, qCode, ans);
    }
}
