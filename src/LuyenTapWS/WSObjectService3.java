/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapWS;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import vn.medianews.ObjectService;
import vn.medianews.ObjectService_Service;
import vn.medianews.Project;

/**
[Mã câu hỏi (qCode): BPbKOPvF].  Một dịch vụ web được định nghĩa và mô tả trong tệp ObjectService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/ObjectService?wsdl để xử lý các bài toán với đối tượng.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với ObjectService thực hiện các công việc sau:
a. Triệu gọi phương thức requestListProject với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về danh sách đối tượng Project từ server. Mỗi đối tượng Project có các thuộc tính:
•	projectId: kiểu String, đại diện cho mã dự án.
•	projectName: kiểu String, đại diện cho tên dự án.
•	completionPercentage: kiểu float, đại diện cho tỷ lệ hoàn thành của dự án (tính theo %).
•	dueDate: kiểu Date, đại diện cho hạn hoàn thành của dự án.
b. Lọc và giữ lại các dự án có completionPercentage từ 80% trở lên và có hạn hoàn thành (dueDate) trong vòng 15 ngày tới (tính từ ngày hiện tại).
c. Triệu gọi phương thức submitListProject(String studentCode, String qCode, List<Project> projects) để gửi danh sách các dự án gần hoàn thành và cần ưu tiên trở lại server. 
d. Kết thúc chương trình client.
 * @author Acer
 */
public class WSObjectService3 {
    public static void main(String[] args) {
        ObjectService_Service service = new ObjectService_Service();
        ObjectService port = service.getObjectServicePort();
        String studentCode = "B18DCCN143", qCode = "BPbKOPvF";
        List<Project> list = port.requestListProject(studentCode, qCode);
        List<Project> ans = new ArrayList<>();
        Date now = new Date();
        for(Project pro : list) {
            System.out.println(pro);
            long diffInMillies = pro.getDueDate().toGregorianCalendar().getTimeInMillis() - now.getTime();
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if(pro.getCompletionPercentage() >= 80 && diff <= 15) ans.add(pro);
        }
        port.submitListProject(studentCode, qCode, ans);
    }
}
