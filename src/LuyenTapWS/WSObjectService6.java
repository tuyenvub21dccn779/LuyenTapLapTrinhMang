/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapWS;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import vn.medianews.EmployeeY;
import vn.medianews.ObjectService;
import vn.medianews.ObjectService_Service;

/**
[Mã câu hỏi (qCode): poeG9pqC].  Một dịch vụ web được định nghĩa và mô tả trong tệp
* ObjectService.wsdl, được triển khai trên server tại 
* URL http://<Exam_IP>:8080/JNPWS/ObjectService?wsdl để xử lý các bài toán với đối tượng.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với ObjectService thực hiện 
* các công việc sau:
a. Triệu gọi phương thức requestListEmployeeY với tham số đầu vào là mã sinh viên (studentCode) 
* và mã câu hỏi (qCode) để nhận về một danh sách đối tượng EmployeeY từ server. Mỗi đối 
* tượng EmployeeY có các thuộc tính:
•	name: kiểu String, đại diện cho tên của nhân viên.
•	startDate: kiểu Date, đại diện cho ngày bắt đầu làm việc của nhân viên.
b. Sắp xếp danh sách EmployeeY theo thứ tự thâm niên từ cao đến thấp (người có ngày bắt đầu 
* làm việc sớm nhất sẽ đứng đầu danh sách).
    Nếu hai nhân viên có cùng ngày bắt đầu làm việc, giữ nguyên thứ tự ban đầu của họ trong 
    * danh sách.
c. Triệu gọi phương thức 
* submitListEmployeeY(String studentCode, String qCode, List<EmployeeY> data) để gửi danh 
* sách nhân viên đã sắp xếp trở lại server.
d. Kết thúc chương trình client.
 * @author Acer
 */
public class WSObjectService6 {
    public static void main(String[] args) {
        String studentCode = "B21DCCN131", qCode = "eXaHlVMv";
        ObjectService_Service service = new ObjectService_Service();
        ObjectService port = service.getObjectServicePort();
        List<EmployeeY> list = port.requestListEmployeeY(studentCode, qCode);
        System.out.println(list);
        list.sort(new Comparator<EmployeeY>(){
            @Override
            public int compare(EmployeeY o1, EmployeeY o2) {
                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(o1.getStartDate().toGregorianCalendar().getTime());
                
                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(o2.getStartDate().toGregorianCalendar().getTime());
                
                if(cal1.before(cal2)) return -1;
                else return 1;
            }
        });
        System.out.println(list);
        port.submitListEmployeeY(studentCode, qCode, list);
    }
}
