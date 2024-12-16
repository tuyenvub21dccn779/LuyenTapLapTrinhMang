/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapRMI;

import RMI.Event;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import RMI.ObjectService;

/**
[Mã câu hỏi (qCode): fPcNQc7G].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện 
* cho phép triệu gọi từ xa để quản lý thông tin các sự kiện trong hệ thống tổ chức sự kiện. 
* Chương trình sẽ ngẫu nhiên tạo ra đối tượng Event với các giá trị ban đầu và cung cấp cho 
* RMI client như sau:
    Giao diện từ xa:
public interface ObjectService extends Remote {
    public Serializable requestObject(String studentCode, String qCode) throws RemoteException;
    public void submitObject(String studentCode, String qCode, Serializable object) throws RemoteException;
}
Lớp Event gồm các thuộc tính: id String, eventName String, eventDate String, 
* expectedAttendance int, eventCode String.
•	Trường dữ liệu: private static final long serialVersionUID = 20241131L;
•	02 hàm khởi dựng:
public Event()
   	public Event(String id, String eventName, String eventDate, int expectedAttendance)
Trong đó:
•	Interface ObjectService và lớp Event được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa ObjectService được đăng ký với 
* RegistryServer: RMIObjectService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với đối 
* tượng sự kiện được nhận từ RMI Server:
a. Triệu gọi phương thức requestObject để nhận đối tượng Event ngẫu nhiên từ server.
b. Tạo mã eventCode cho sự kiện dựa trên các quy tắc sau:
•	Nếu expectedAttendance >= 1000, thêm "L" vào eventCode;     Nếu expectedAttendance 
* từ 500 đến 999, thêm "M" vào eventCode;     Nếu expectedAttendance dưới 500, thêm "S" vào 
* eventCode.
•	Thêm chữ cái đầu và cuối của eventName, tất cả viết hoa.
•	Thêm ngày và tháng từ eventDate (theo định dạng "ddMM").
Ví dụ: Nếu sự kiện có tên là "Charity Run", ngày diễn ra là "2024-05-12" và số lượng người 
* tham gia dự kiến là 1200, mã eventCode sẽ là "LCR1205".
c. Cập nhật giá trị eventCode trong đối tượng Event.
d. Triệu gọi phương thức submitObject để gửi đối tượng Event đã được xử lý trở lại server.
e. Kết thúc chương trình client.
 * @author Acer
 */
public class RMIObject3 {
    public static void main(String[] args) {
        String path = "rmi://203.162.10.109/RMIObjectService";
        String studentCode = "B21DCCN158", qCode = "EveDB920";
        try {
            ObjectService service = (ObjectService) Naming.lookup(path);
            Event event = (Event) service.requestObject(studentCode, qCode);
            String eventName = event.getEventName().toUpperCase();
            String eventDate = event.getEventDate();
            StringBuilder eventCode = new StringBuilder();
            if(event.getExpectedAttendance() >= 1000) eventCode.append("L");
            else if(event.getExpectedAttendance() >= 500) eventCode.append("M");
            else eventCode.append("S");
            eventCode.append(eventName.charAt(0));
            eventCode.append(eventName.charAt(eventName.length()-1));
            String[] arr = eventDate.split("-");
            eventCode.append(arr[2] + arr[1]);
            System.out.println(event);
            event.setEventCode(eventCode.toString());
            System.out.println(event);
            
            service.submitObject(studentCode, qCode, event);
            
        } catch (NotBoundException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
