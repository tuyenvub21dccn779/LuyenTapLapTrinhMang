/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapWS;

import java.util.Calendar;
import java.util.Date;
import javax.xml.datatype.XMLGregorianCalendar;
import vn.medianews.Employee;
import vn.medianews.ObjectService;
import vn.medianews.ObjectService_Service;

/**
[Mã câu hỏi (qCode): t2VxH1YG].  Một dịch vụ web được định nghĩa và mô tả trong tệp ObjectService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/ObjectService?wsdl để xử lý các bài toán với đối tượng.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với ObjectService thực hiện các công việc sau:
a. Triệu gọi phương thức requestEmployee với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một đối tượng Employee từ server. Đối tượng này có các thuộc tính:
•	startDate: kiểu Date, đại diện cho ngày bắt đầu công việc của nhân viên.
•	endDate: kiểu Date, đại diện cho ngày kết thúc công việc của nhân viên.
b. Tính toán số ngày làm việc (workingDays) giữa startDate và endDate, loại trừ các ngày cuối tuần (thứ Bảy và Chủ Nhật). Cập nhật thuộc tính workingDays trong đối tượng Employee với giá trị đã tính toán.
c. Triệu gọi phương thức submitEmployee(String studentCode, String qCode, Employee object) để gửi đối tượng Employee với số ngày làm việc đã được tính toán trở lại server.
Ví dụ: Nếu đối tượng Employee có startDate là 01-09-2023 (thứ Sáu) và endDate là 07-09-2023 (thứ Năm), số ngày làm việc sẽ tính từ ngày 01-09 đến 07-09, loại trừ ngày cuối tuần, kết quả là 5 ngày làm việc. 
Đối tượng Employee với giá trị workingDays = 5 sẽ được gửi lại server qua phương thức submitEmployee.
d. Kết thúc chương trình client.
 * @author Acer
 */
public class WSObjectService2 {
    
    public static Date toDate(XMLGregorianCalendar calendar) {
        return calendar.toGregorianCalendar().getTime();
    }
    
    public static int getWorkingDays(Date startDate, Date endDate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        
        int count = 0;
        
        while(!startCal.after(endCal)) {
            int day = startCal.get(Calendar.DAY_OF_WEEK);
            if((day != Calendar.SATURDAY) && (day != Calendar.SUNDAY))
                count++;
            startCal.add(Calendar.DATE, 1);
        }
        return count;
    }
    
    public static void main(String[] args) {
        ObjectService_Service service = new ObjectService_Service();
        ObjectService port = service.getObjectServicePort();
        String studentCode = "B21DCCN588", qCode = "t2VxH1YG";
        Employee emp = port.requestEmployee(studentCode, qCode);
        emp.setWorkingDays(getWorkingDays(toDate(emp.getStartDate()), toDate(emp.getEndDate())));
        port.submitEmployee(studentCode, qCode, emp);
    }
}
