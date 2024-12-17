/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapRMI;

import RMI.BookX;
import java.rmi.Naming;
import RMI.ObjectService;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
[Mã câu hỏi (qCode): LC36eJt2].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện 
* cho phép triệu gọi từ xa để xử lý mã hóa dữ liệu tác phẩm trong hệ thống quản lý thư viện. 
* Chương trình sẽ ngẫu nhiên tạo ra đối tượng BookX với các giá trị ban đầu và cung cấp cho 
* RMI client như sau:
    Giao diện từ xa:
public interface ObjectService extends Remote {
    public Serializable requestObject(String studentCode, String qCode) throws RemoteException;
    public void submitObject(String studentCode, String qCode, Serializable object) throws RemoteException;
}
Lớp BookX gồm các thuộc tính: id String, title String, author String, yearPublished int, 
* genre String, code String.
Trường dữ liệu: private static final long serialVersionUID = 20241124L;
02 hàm khởi dựng:
    public BookX()
    public BookX(String id, String title, String author, int yearPublished, String genre)
Trong đó:
    Interface ObjectService và lớp BookX được viết trong package RMI.
    Đối tượng cài đặt giao diện từ xa ObjectService được đăng ký với 
    * RegistryServer: RMIObjectService.

Yêu cầu:  Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với 
* đối tượng sách được nhận từ RMI Server:
a. Triệu gọi phương thức requestObject để nhận đối tượng BookX ngẫu nhiên từ server.
b. Tạo mã code cho sách dựa trên các quy tắc mã hóa sau:
•	Lấy chữ cái đầu tiên và cuối cùng trong tên tác giả (author). 
•	    Lấy hai chữ số cuối cùng của yearPublished.
•	    Số lượng chữ cái trong genre của sách.
•	    Độ dài của title chia lấy dư cho 10 (ví dụ: với tiêu đề dài 32 ký tự, giá trị này 
* sẽ là 2).
    Kết hợp tất cả các thành phần trên để tạo ra mã code theo định dạng: 
    * [Chữ cái đầu và cuối tên tác giả][Hai chữ số cuối của năm xuất bản]
    * [Số chữ cái của genre][Độ dài title modulo 10].	
    * Ví dụ, nếu tác giả là "Mark Twain", năm xuất bản là 1884, thể loại là "Fiction" với 7 ký tự, 
    * và tiêu đề có 24 ký tự, mã code sẽ là: "MT8474".
c. Cập nhật giá trị code trong đối tượng BookX. 
d. Triệu gọi phương thức submitObject để gửi đối tượng BookX đã được xử lý trở lại server.
e. Kết thúc chương trình client.
 * @author Acer
 */
public class RMIObject4 {
    
    public static int countAlphabetic(String s) {
        int count = 0;
        for(char x : s.toCharArray()) {
            if(Character.isAlphabetic(x)) count ++;
        }
        return count;
    }
    
    public static void main(String[] args) {
        String url = "rmi://203.162.10.109/RMIObjectService";
        String studentCode = "B21DCCN136", qCode = "LC36eJt2";
        try {
            ObjectService service = (ObjectService) Naming.lookup(url);
            BookX book = (BookX) service.requestObject(studentCode, qCode);
            System.out.println(book);
            String author = book.getAuthor();
            String ans = "" + author.charAt(0) + author.charAt(author.length()-1);
            String yearPublished = book.getYearPublished() + "";
            ans += yearPublished.substring(2);
            ans += "" + countAlphabetic(book.getGenre()) + book.getTitle().length() % 10;
            book.setCode(ans);
            System.out.println(book);
            service.submitObject(studentCode, qCode, book);
            
        } catch (NotBoundException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
