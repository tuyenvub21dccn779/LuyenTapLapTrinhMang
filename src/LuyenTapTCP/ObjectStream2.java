/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapTCP;

import TCP.Student;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.rmi.CORBA.Stub;

/**
[Mã câu hỏi (qCode): Ilk1CHUE].  Một chương trình server cho phép kết nối qua giao thức TCP 
* tại cổng 2209 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây 
* dựng một chương trình client tương tác với server sử dụng các luồng đối tượng
* (ObjectOutputStream/ObjectInputStream) theo kịch bản dưới đây:
Biết lớp TCP.Student gồm các thuộc tính (id int,code String, gpa float, gpaLetter String) và 
private static final long serialVersionUID = 20151107;
a.	Gửi đối tượng là một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng 
* "studentCode;qCode". Ví dụ: "B15DCCN999;1D059A3F"
b.	Nhận một đối tượng là thể hiện của lớp TCP.Student từ server
c.	Chuyển đổi giá trị điểm số gpa của đối tượng nhận được sang dạng điểm chữ và gán 
* cho gpaLetter.  Nguyên tắc chuyển đổi
i.	3.7 – 4 -> A
ii.	3.0 – 3.7 -> B
iii.	2.0 – 3.0 -> C
iv.	1.0 – 2.0 -> D
v.	0 – 1.0 -> F
d.     Gửi đối tượng đã được xử lý ở trên lên server.
e.     Đóng kết nối và kết thúc chương trình
 * @author Acer
 */
public class ObjectStream2 {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109", 2209);
            String strSend = "B21DCCN136;bNAiakfx";
            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
            oos.writeObject(strSend);
            oos.flush();
            
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            Student stu = (Student) ois.readObject();
            
            float gpa = stu.getGpa();
            String gpaLetter = "";
            if(gpa >= 3.7) gpaLetter = "A";
            else if(gpa >= 3.0) gpaLetter = "B";
            else if(gpa >= 2.0) gpaLetter = "C";
            else if(gpa >= 1.0) gpaLetter = "D";
            else gpaLetter = "F";
            System.out.println(stu);
            stu.setGpaLetter(gpaLetter);
            System.out.println(stu);
            oos.writeObject(stu);
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
