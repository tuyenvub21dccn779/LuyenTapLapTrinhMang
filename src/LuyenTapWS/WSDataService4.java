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
[Mã câu hỏi (qCode): XL4TfRuQ].  Một dịch vụ web được định nghĩa và mô tả trong tệp 
* DataService?wsdl, được triển khai trên server tại 
* URL http://<Exam_IP>:8080/JNPWS/DataService?wsdl để xử lý các bài toán với dữ liệu nguyên thủy.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với DataService thực hiện 
* các công việc sau:
a. Triệu gọi phương thức getData với tham số đầu vào là mã sinh viên (studentCode) và mã câu 
* hỏi (qCode) để nhận về một danh sách số nguyên (List<Integer>) từ server.
b. Sắp xếp lại mảng số nguyên nhận được sao cho phần tử chẵn và lẻ xen kẽ nhau theo thứ tự 
* xuất hiện. Nếu không thể xen kẽ đều, các phần tử còn lại sẽ nối tiếp ở cuối mảng. Đảm bảo 
* các phần tử chẵn ở vị trí đầu tiên trong mảng sắp xếp.
c. Triệu gọi phương thức submitDataIntArray(String studentCode, String qCode, List<Integer> data)
* để gửi danh sách số nguyên đã sắp xếp theo thứ tự chẵn-lẻ xen kẽ trở lại server.
Ví dụ: Nếu mảng số nguyên nhận được từ phương thức getData là [1, 2, 3, 4, 5, 6], sau khi sắp 
* xếp xen kẽ chẵn-lẻ, mảng kết quả sẽ là [2, 1, 4, 3, 6, 5]. Mảng này sẽ được gửi lại server 
* qua phương thức submitData.
d. Kết thúc chương trình client.
 * @author Acer
 */
public class WSDataService4 {
    public static void main(String[] args) {
        DataService_Service service = new DataService_Service();
        DataService port = service.getDataServicePort();
        String studentCode = "B18DCAT183", qCode = "UbJwi86w";
        List<Integer> list = port.getData(studentCode, qCode);
        List<Integer> chan = new ArrayList<>();
        List<Integer> le = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        for(int x : list) {
            if(x % 2 == 0) chan.add(x);
            else le.add(x);
        }
        
        int i = 0, j = 0;
        while(i < chan.size() && j < le.size()) {
            ans.add(chan.get(i));
            ans.add(le.get(j));
            i++; j++;
        }
        while(i < chan.size()) {
            ans.add(chan.get(i));
            i++;
        }
        while(j < le.size()) {
            ans.add(le.get(j));
            j++;
        }
        System.out.println(list);
        System.out.println(ans);
        port.submitDataIntArray(studentCode, qCode, ans);
    }
}
