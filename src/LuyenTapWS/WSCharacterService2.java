/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapWS;

import java.util.Comparator;
import java.util.List;
import vn.medianews.CharacterService;
import vn.medianews.CharacterService_Service;

/**
[Mã câu hỏi (qCode): iJuvyk9C].  Một dịch vụ web được định nghĩa và mô tả trong tệp CharacterService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/CharacterService?wsdl để xử lý các bài toán về chuỗi và ký tự.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với CharacterService thực hiện các công việc sau:
a. Triệu gọi phương thức requestStringArray với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một danh sách chuỗi (List<String>) từ server..
b. Sắp xếp mảng theo độ dài của từ theo thứ tự tăng dần độ dài của từ.
c. Triệu gọi phương thức submitCharacterStringArray(String studentCode, String qCode, List<String> data) để gửi mảng chuỗi đã sắp xếp trở lại server.
Ví dụ: Nếu mảng chuỗi nhận được từ phương thức requestCharacter là ["apple", "banana", "fig", "pineapple"] mảng sắp xếp tăng dần theo độ dài sẽ là ["fig", "apple", "banana", "pineapple"].
d. Kết thúc chương trình client.
 * @author Acer
 */
public class WSCharacterService2 {
    public static void main(String[] args) {
        CharacterService_Service service = new CharacterService_Service();
        CharacterService port = service.getCharacterServicePort();
        String studentCode = "B21DCCN211", qCode = "iJuvyk9C";
        List<String> list = port.requestStringArray(studentCode, qCode);
        System.out.println(list);
        list.sort(new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
            
        });
        System.out.println(list);
        port.submitCharacterStringArray(studentCode, qCode, list);
    }
}
