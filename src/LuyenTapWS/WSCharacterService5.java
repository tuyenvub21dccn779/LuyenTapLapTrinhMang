/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapWS;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import vn.medianews.CharacterService;
import vn.medianews.CharacterService_Service;

/**
[Mã câu hỏi (qCode): RPl1De7n].  Một dịch vụ web được định nghĩa và mô tả trong tệp 
* CharacterService.wsdl, được triển khai trên server tại 
* URL http://<Exam_IP>:8080/JNPWS/CharacterService?wsdl để xử lý các bài toán về chuỗi và ký tự.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với CharacterService thực 
* hiện các công việc sau:
a. Triệu gọi phương thức requestCharacter với tham số đầu vào là mã sinh viên (studentCode) 
* và mã câu hỏi (qCode) để nhận về một danh sách ký tự (List<Integer>) từ server.
b. Sắp xếp danh sách ký tự nhận được theo thứ tự từ điển (alphabetical order).
c. Triệu gọi phương thức 
* submitCharacterCharArray(String studentCode, String qCode, List<Integer> data) để gửi 
* danh sách ký tự đã sắp xếp trở lại server.
Ví dụ: Nếu mảng ký tự nhận được từ phương thức requestCharacter là ['d', 'a', 'c', 'b'], 
* sau khi sắp xếp theo thứ tự từ điển, mảng kết quả sẽ là ['a', 'b', 'c', 'd']. Mảng này 
* sẽ được gửi lại server qua phương thức submitCharacter.
d. Kết thúc chương trình client.
 * @author Acer
 */
public class WSCharacterService5 {
    public static void main(String[] args) {
        String studentCode = "B21DCCN158", qCode = "RPl1De7n";
        CharacterService_Service service = new CharacterService_Service();
        CharacterService port = service.getCharacterServicePort();
        List<Integer> list = port.requestCharacter(studentCode, qCode);
        System.out.println(list);
        list.sort(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
            
        });
        System.out.println(list);
        port.submitCharacterCharArray(studentCode, qCode, list);
    }
}
