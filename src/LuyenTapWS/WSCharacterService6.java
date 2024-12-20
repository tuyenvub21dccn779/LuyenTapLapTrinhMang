/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapWS;

import vn.medianews.CharacterService;
import vn.medianews.CharacterService_Service;

/**
[Mã câu hỏi (qCode): aMYdRBxt].  Một dịch vụ web được định nghĩa và mô tả trong tệp 
* CharacterService.wsdl, được triển khai trên server tại 
* URL http://<Exam_IP>:8080/JNPWS/CharacterService?wsdl để xử lý các bài toán về chuỗi và ký tự.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với CharacterService thực 
* hiện các công việc sau:
a. Triệu gọi phương thức requestString với tham số đầu vào là mã sinh viên (studentCode) và 
* mã câu hỏi (qCode) để nhận về một chuỗi (String) từ server. Chuỗi có thể chứa các ký tự đặc 
* biệt và khoảng trắng.
b. Tạo một chuỗi mới từ chuỗi nhận được bằng cách:
•	Loại bỏ tất cả các ký tự đặc biệt, số, chỉ các ký tự chữ cái.
•	Đảo ngược chuỗi kết quả sau khi đã loại bỏ các ký tự đặc biệt.
c. Triệu gọi phương thức 
* submitCharacterString(String studentCode, String qCode, String cleanedAndReversedString) để 
* gửi chuỗi đã được làm sạch và đảo ngược trở lại server.
Ví dụ: Nếu chuỗi nhận được từ phương thức requestString là "hello@ world! 2024", sau khi loại 
* bỏ các ký tự đặc biệt sẽ thành "helloworld", và khi đảo ngược sẽ là "dlrowolleh". Chuỗi kết 
* quả "dlrowolleh" sẽ được gửi lại server qua phương thức submitCharacterString.
d. Kết thúc chương trình client.
 * @author Acer
 */
public class WSCharacterService6 {
    public static void main(String[] args) {
        String studentCode = "B21DCCN131", qCode = "aMYdRBxt";
        CharacterService_Service service = new CharacterService_Service();
        CharacterService port = service.getCharacterServicePort();
        String strReceive = port.requestString(studentCode, qCode);
        StringBuilder ans = new StringBuilder();
        for(char x : strReceive.toCharArray()) {
            if(Character.isAlphabetic(x)) ans.append(x);
        }
        ans.reverse();
        System.out.println(strReceive);
        System.out.println(ans.toString());
        port.submitCharacterString(studentCode, qCode, ans.toString());
    }
}
