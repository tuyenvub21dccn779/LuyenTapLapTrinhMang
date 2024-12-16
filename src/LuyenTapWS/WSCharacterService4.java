/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapWS;

import java.util.ArrayList;
import java.util.List;
import vn.medianews.CharacterService;
import vn.medianews.CharacterService_Service;

/**
[Mã câu hỏi (qCode): rFVOtJEg].  Một dịch vụ web được định nghĩa và mô tả trong tệp 
* CharacterService.wsdl, được triển khai trên server tại 
* URL http://<Exam_IP>:8080/JNPWS/CharacterService?wsdl để xử lý các bài toán về chuỗi và ký tự.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với CharacterService thực 
* hiện các công việc sau:
a. Triệu gọi phương thức requestCharacter với tham số đầu vào là mã sinh viên (studentCode) và 
* mã câu hỏi (qCode) để nhận về một danh sách ký tự (List<Integer>) từ server.
b. Thực hiện xoay vòng các ký tự trong danh sách theo chiều phải, số lần xoay vòng bằng đúng giá 
* trị nguyên trong bảng mã ASCII của ký tự đầu tiên.
c. Triệu gọi phương thức submitCharacterCharArray(String studentCode, String qCode, 
* List<Integer> data) để gửi mảng ký tự đã xoay vòng trở lại server.
Ví dụ: Nếu mảng ký tự nhận được từ phương thức requestCharacter là ['a', 'b', 'c', 'd'], 
* giá trị nguyên của ký tự 'a' trong bảng mã ASCII là 97, thực hiện xoay vòng phải 97 lần 
* sẽ trả lại mảng ['d', 'a', 'b', 'c']. Mảng này sẽ được gửi lại server qua phương thức 
* submitCharacterCharArray.
d. Kết thúc chương trình client.
 * @author Acer
 */
public class WSCharacterService4 {
    public static void main(String[] args) {
        String studentCode = "B21DCCN158", qCode = "RPl1De7n";
        CharacterService_Service service = new CharacterService_Service();
        CharacterService port = service.getCharacterServicePort();
        List<Integer> list = port.requestCharacter(studentCode, qCode);
        StringBuilder sb = new StringBuilder();
        for(int x : list) {
            sb.append((char) x);
        }
        sb.append(sb);
        int k = list.get(0) % list.size();
        List<Integer> ans = new ArrayList<>();
        for(int i=list.size()-k; i<sb.length()-k; i++) {
            ans.add((int) sb.charAt(i));
        }
        System.out.println(list);
        System.out.println(ans);
        port.submitCharacterCharArray(studentCode, qCode, ans);
    }
}
