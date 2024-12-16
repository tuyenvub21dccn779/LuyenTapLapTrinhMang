/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapTCP;

import TCP.Product;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
[Mã câu hỏi (qCode): 4vIveczb].  Một chương trình server cho phép kết nối qua giao thức TCP tại 
* cổng 2209 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu là xây dựng 
* một chương trình client tương tác với server sử dụng các luồng đối tượng 
* (ObjectOutputStream/ObjectInputStream) theo kịch bản dưới đây:

Biết lớp TCP.Product gồm các thuộc tính (id int, name String, price double, int discount) và 
* private static final long serialVersionUID = 20231107;

a. Gửi đối tượng là một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". 
* Ví dụ: "B15DCCN999;1E08CA31"

b. Nhận một đối tượng là thể hiện của lớp TCP.Product từ server.

c. Tính toán giá trị giảm giá theo price theo nguyên tắc: Giá trị giảm giá (discount) bằng tổng 
* các chữ số trong phần nguyên của giá sản phẩm (price). Thực hiện gán giá trị cho thuộc tính 
* discount và gửi lên đối tượng nhận được lên server.

d. Đóng kết nối và kết thúc chương trình.
 * @author Acer
 */
public class ObjectStream3 {
    
    public static int sumNumber(String s) {
        int sum = 0;
        for(char x : s.toCharArray()) {
            if(Character.isDigit(x)) sum += x - '0';
        }
        return sum;
    }
    
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109", 2209);
            
            String strSend = "B21DCCN158;4vIveczb";
            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
            oos.writeObject(strSend);
            oos.flush();
            
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            Product pro = (Product) ois.readObject();
            System.out.println(pro);
            pro.setDiscount(sumNumber((int) pro.getPrice() + ""));
            System.out.println(pro);
            oos.writeObject(pro);
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
