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
[Mã câu hỏi (qCode): GLExF2cl].  Một dịch vụ web được định nghĩa và mô tả trong tệp 
* CharacterService.wsdl, được triển khai trên server tại 
* URL http://<Exam_IP>:8080/JNPWS/CharacterService?wsdl để xử lý các bài toán về chuỗi và ký tự.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với CharacterService thực 
* hiện các công việc sau:
a. Triệu gọi phương thức requestString với tham số đầu vào là mã sinh viên (studentCode) và 
* mã câu hỏi (qCode) để nhận về một chuỗi (String) từ server. Chuỗi có thể chứa các từ được 
* phân tách bằng dấu cách hoặc dấu gạch dưới.
b. Chuyển đổi chuỗi đã nhận được sang ba định dạng khác nhau:
•	PascalCase: Mỗi từ bắt đầu bằng chữ in hoa, không có khoảng cách giữa các từ.
•	camelCase: Từ đầu tiên viết thường, các từ tiếp theo viết hoa chữ cái đầu và viết 
* liền nhau.
•	snake_case: Các từ được viết thường và nối với nhau bằng dấu gạch dưới.
c. Triệu gọi phương thức submitCharacterStringArray(String studentCode, String qCode, 
* List<String> data) để gửi mảng kết quả chứa ba chuỗi đã định dạng trở lại server, theo 
* thứ tự: PascalCase, camelCase, snake_case.
Ví dụ: Nếu chuỗi nhận được từ phương thức requestCharacter là "hello world example", các 
* chuỗi kết quả sẽ là:
•	PascalCase: "HelloWorldExample"
•	camelCase: "helloWorldExample"
•	snake_case: "hello_world_example"
Mảng kết quả sẽ là ["HelloWorldExample", "helloWorldExample", "hello_world_example"], và sẽ 
* được gửi lại server qua phương thức submitCharacter.
d. Kết thúc chương trình client.
 * @author Acer
 */
public class WSCharacterService {
    public static void main(String[] args) {
        CharacterService_Service service = new CharacterService_Service();
        CharacterService port = service.getCharacterServicePort();
        String studentCode = "B21DCCN023", qCode = "GLExF2cl";
        String receiveStr = port.requestString(studentCode, qCode);
        System.out.println(receiveStr);
        String[] arr = receiveStr.split("[\\s_]");
        System.out.println(arr.length);
        String pascalCase = "", camelCase = "", snakeCase = "";
        for(int i = 0; i < arr.length; i++) {
            String tmp = arr[i].toLowerCase();
            String tmp2 = Character.toUpperCase(tmp.charAt(0)) + tmp.substring(1);
            pascalCase += tmp2;
            if(i == 0) {
                camelCase += tmp;
            } else {
                camelCase += tmp2;
            }
            snakeCase += tmp;
            if(i != arr.length-1) snakeCase += "_";
        }
        List<String> ans = new ArrayList<>();
        ans.add(pascalCase); 
        ans.add(camelCase);
        ans.add(snakeCase);
        System.out.println(ans);
        port.submitCharacterStringArray(studentCode, qCode, ans);
        
    }
}
