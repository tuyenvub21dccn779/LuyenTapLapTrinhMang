/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapTCP;

import TCP.Customer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
[Mã câu hỏi (qCode): 1GHAANjo].  Thông tin khách hàng cần thay đổi định dạng lại cho phù hợp với khu vực, cụ thể:
a.	Tên khách hàng cần được chuẩn hóa theo định dạng mới. Ví dụ: nguyen van hai duong -> DUONG, Nguyen Van Hai
b.	Ngày sinh của khách hàng hiện đang ở dạng mm-dd-yyyy, cần được chuyển thành định dạng dd/mm/yyyy. Ví dụ: 10-11-2012 -> 11/10/2012
c.	Tài khoản khách hàng là các chữ cái in thường được sinh tự động từ họ tên khách hàng. Ví dụ: nguyen van hai duong -> nvhduong

Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2209 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng đối tượng (ObjectInputStream / ObjectOutputStream) thực hiện gửi/nhận đối tượng khách hàng và chuẩn hóa. Cụ thể:
a.	Đối tượng trao đổi là thể hiện của lớp Customer918 được mô tả như sau
      •	Tên đầy đủ của lớp: TCP.Customer918
      •	Các thuộc tính: id int, code String, name String, dayOfBirth String, userName String
      •	Hàm khởi tạo đầy đủ các thuộc tính được liệt kê ở trên
      •	Trường dữ liệu: private static final long serialVersionUID = 918; 
b.	Tương tác với server theo kịch bản dưới đây:
	1) Gửi đối tượng là một chuỗi gồm mã sinh viên và mã câu hỏi ở định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;F2DA54F3"
	2) Nhận một đối tượng là thể hiện của lớp Customer918 từ server với các thông tin đã được thiết lập
	3) Thay đổi định dạng theo các yêu cầu ở trên và gán vào các thuộc tính tương ứng.  Gửi đối tượng đã được sửa đổi lên server
	4) Đóng socket và kết thúc chương trình.
 * @author Acer
 */
public class ObjectStream1 {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109", 2209);
            String strSend = "B21DCCN193;1GHAANjo";
            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
            oos.writeObject(strSend);
            oos.flush();
            
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            Customer cus = (Customer) ois.readObject();
            
            String[] arr = cus.getName().toLowerCase().split("\\s+");
            StringBuilder newName = new StringBuilder(), newUserName = new StringBuilder();
            System.out.println(cus);
            for(int i=0; i<arr.length; i++) {
                if(i == arr.length-1) {
                    newName.insert(0, arr[i].toUpperCase() + ", ");
                    newUserName.append(arr[i]);
                } else {
                    newName.append(Character.toUpperCase(arr[i].charAt(0)) + arr[i].substring(1) + " ");
                    newUserName.append(arr[i].charAt(0));
                }
            }
            cus.setName(newName.toString().trim());
            cus.setUserName(newUserName.toString());
            arr = cus.getDayOfBirth().split("-");
            cus.setDayOfBirth(arr[1] + "/" + arr[0] + "/" + arr[2]);
            
            System.out.println(cus);
            
            oos.writeObject(cus);
            oos.flush();
            
            ois.close();
            oos.close();
            client.close();
            
        } catch(IOException ex) {
            ex.printStackTrace();
        } catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
