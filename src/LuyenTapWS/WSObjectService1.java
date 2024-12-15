/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapWS;

import vn.medianews.ObjectService;
import vn.medianews.ObjectService_Service;
import vn.medianews.ProductY;

/**
[Mã câu hỏi (qCode): 3jWw3Fkt].  Một dịch vụ web được định nghĩa và mô tả trong tệp 
* ObjectService.wsdl, được triển khai trên server tại 
* URL http://<Exam_IP>:8080/JNPWS/ObjectService?wsdl để xử lý các bài toán với đối tượng.
Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với ObjectService thực hiện 
* các công việc sau:
a. Triệu gọi phương thức requestProductY với tham số đầu vào là mã sinh viên (studentCode) 
* và mã câu hỏi (qCode) để nhận về một đối tượng ProductY từ server. Đối tượng này có các 
* thuộc tính:
•	price (giá gốc): float, đại diện cho giá của sản phẩm.
•	taxRate (thuế): float, đại diện cho phần trăm thuế áp dụng trên giá gốc.
•	discount (chiết khấu): float, đại diện cho phần trăm chiết khấu áp dụng trên giá gốc.
b. Thực hiện 
•	Tính toán giá cuối cùng của sản phẩm (finalPrice) theo công thức:
        		finalPrice = price * (1 + taxRate / 100) * (1 - discount / 100)
•	Cập nhật thuộc tính finalPrice trong đối tượng ProductY với giá trị đã tính toán.
c. Triệu gọi phương thức submitProductY(String studentCode, String qCode, ProductY object)
* để gửi đối tượng ProductY với giá cuối cùng đã được tính toán trở lại server.
Ví dụ: Nếu đối tượng ProductY có các thuộc tính price = 100.0, taxRate = 10.0, và 
* discount = 5.0, thì finalPrice sẽ được tính là:
    		finalPrice = 100 * (1 + 10/100) * (1 - 5/100) = 104.5
Đối tượng ProductY với giá trị finalPrice = 104.5 sẽ được gửi lại server qua phương thức 
* submitProductY.
d. Kết thúc chương trình client.
 * @author Acer
 */
public class WSObjectService1 {
    public static void main(String[] args) {
        ObjectService_Service service = new ObjectService_Service();
        ObjectService port = service.getObjectServicePort();
        String studentCode = "B21DCCN023", qCode = "BBFTn6il";
        ProductY pro = port.requestProductY(studentCode, qCode);
        float finalPrice = pro.getPrice() * (1 + pro.getTaxRate() / 100) * (1 - pro.getDiscount() / 100);
        pro.setFinalPrice(finalPrice);
        port.submitProductY(studentCode, qCode, pro);
    }
}
