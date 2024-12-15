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
[Mã câu hỏi (qCode): NVtFJvJv].  Một dịch vụ web được định nghĩa và mô tả trong tệp CharacterService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/CharacterService?wsdl để xử lý các bài toán về chuỗi và ký tự.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với CharacterService thực hiện các công việc sau:
a. Triệu gọi phương thức requestStringArray với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một danh sách chuỗi (List<String>) từ server.
b. Phân loại các từ trong mảng chuỗi thành các nhóm có cùng số lượng nguyên âm. Tạo một chuỗi cho mỗi nhóm, trong đó liệt kê các từ cách nhau bằng dấu phẩy, và sắp xếp các từ theo thứ tự từ điển trong mỗi nhóm.
c. Triệu gọi phương thức submitCharacterStringArray(String studentCode, String qCode, List<String> data) để gửi danh sách chuỗi kết quả trở lại server, trong đó mỗi phần tử là một nhóm từ với cùng số lượng nguyên âm.
Ví dụ: Nếu danh sách chuỗi nhận được từ phương thức requestCharacter là ["apple", "banana", "pear", "grape", "kiwi"], các nhóm có thể là:
•	Nhóm 2 nguyên âm: "apple, banana"
•	Nhóm 1 nguyên âm: "grape, kiwi, pear"
Danh sách kết quả sẽ là ["apple, banana", "grape, kiwi, pear"], và danh sách này sẽ được gửi lại server qua phương thức submitCharacter.
d. Kết thúc chương trình client.
 * @author Acer
 */
public class WSCharacterService3 {
    
    public static boolean isVowel(char x) {
        x = Character.toLowerCase(x);
        return x == 'u' || x == 'e' || x == 'o' || x == 'a' || x == 'i';
    }
    
    public static int countVowel(String s) {
        int count = 0;
        for(char x : s.toCharArray()) {
            if(isVowel(x)) count++;
        }
        return count;
    }
    
    public static void main(String[] args) {
        String studentCode = "B21DCCN193", qCode = "NVtFJvJv";
        CharacterService_Service service = new CharacterService_Service();
        CharacterService port = service.getCharacterServicePort();
        List<String> list = port.requestStringArray(studentCode, qCode);
        System.out.println(list);
        list.sort(new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
            
        });
        List<List<String>> res = new ArrayList<>();
        for(String x : list) {
            int count = countVowel(x);
            if(res.size() <= count) {
                while(res.size() <= count) res.add(new ArrayList<String>());
            }
            res.get(count).add(x);
        }
        list = new ArrayList<String>();
        for(int i = 0; i < res.size(); i++) {
            StringBuilder sb = new StringBuilder();
            for(String x : res.get(i)) {
                sb.append(x + ", ");
            }
            sb.delete(sb.length()-2, sb.length());
            list.add(sb.toString());
        }
        System.out.println(list);
        port.submitCharacterStringArray(studentCode, qCode, list);
    }
}
