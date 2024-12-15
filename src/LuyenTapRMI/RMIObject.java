/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapRMI;

import RMI.ObjectService;
import RMI.Order;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
[Mã câu hỏi (qCode): hqC9fb2H].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để quản lý thông tin đơn hàng trong hệ thống thương mại điện tử. Chương trình sẽ ngẫu nhiên tạo ra đối tượng Order với các giá trị ban đầu và cung cấp cho RMI client như sau:
    Giao diện từ xa:
public interface ObjectService extends Remote {
    public Serializable requestObject(String studentCode, String qCode) throws RemoteException;
    public void submitObject(String studentCode, String qCode, Serializable object) throws RemoteException;
}
Lớp Order gồm các thuộc tính: id String, customerCode String, orderDate String, shippingType String, orderCode String.
•	Trường dữ liệu: private static final long serialVersionUID = 20241132L;
•	02 hàm khởi dựng:
    public Order()
    public Order(String id, String customerCode, String orderDate, String shippingType)
Trong đó:
•	Interface ObjectService và lớp Order được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa ObjectService được đăng ký với RegistryServer: RMIObjectService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với đối tượng đơn hàng được nhận từ RMI Server:
a. Triệu gọi phương thức requestObject để nhận đối tượng Order ngẫu nhiên từ server.
b. Tạo mã orderCode cho đơn hàng dựa trên các quy tắc sau:
•	Bắt đầu bằng hai ký tự đầu của shippingType, viết in hoa.
•	Kế đến là ba ký tự cuối của customerCode.
•	Cuối cùng là ngày và tháng từ orderDate (theo định dạng "ddMM").
Ví dụ: Nếu đơn hàng có mã khách hàng là "C123456", ngày đặt hàng là "2023-10-05", và loại giao hàng là "Express", thì mã orderCode sẽ là "EX4560510".
c. Cập nhật giá trị orderCode trong đối tượng Order.
d. Triệu gọi phương thức submitObject để gửi đối tượng Order đã được xử lý trở lại server.
e. Kết thúc chương trình client.
 * @author Acer
 */
public class RMIObject {
    public static void main(String[] args) {
        try {
            ObjectService service = (ObjectService) Naming.lookup("rmi://203.162.10.109/RMIObjectService");
            String studentCode = "B21DCCN023", qCode = "hqC9fb2H";
            Order order = (Order) service.requestObject(studentCode, qCode);
            String orderCode = order.getShippingType().toUpperCase().substring(0, 2) + 
                    order.getCustomerCode().substring(order.getCustomerCode().length()-3) + 
                    order.getOrderDate().substring(8) + order.getOrderDate().substring(5, 7);
            order.setOrderCode(orderCode);
            System.out.println(order);
            service.submitObject(studentCode, qCode, order);
        } catch (NotBoundException ex) {
            Logger.getLogger(RMIObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(RMIObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(RMIObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
