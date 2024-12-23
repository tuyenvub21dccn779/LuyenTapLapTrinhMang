package LuyenTapTCP;
import TCP.Laptop;
import TCP.Product917;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
[Mã câu hỏi (qCode): 53kn2xt6].  Thông tin sản phẩm vì một lý do nào đó đã bị sửa đổi thành không đúng, cụ thể:
a) Tên sản phẩm bị đổi ngược từ đầu tiên và từ cuối cùng, ví dụ: “lenovo thinkpad T520” bị chuyển thành “T520 thinkpad lenovo”
b) Số lượng sản phẩm cũng bị đảo ngược giá trị, ví dụ từ 9981 thành 1899

Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2209 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng đối tượng (ObjectInputStream / ObjectOutputStream) để gửi/nhận và sửa các thông tin bị sai của sản phẩm. Chi tiết dưới đây:
a) Đối tượng trao đổi là thể hiện của lớp Laptop được mô tả như sau
      •	Tên đầy đủ của lớp: TCP.Laptop
      •	Các thuộc tính: id int, code String, name String, quantity int
      •	Hàm khởi tạo đầy đủ các thuộc tính được liệt kê ở trên
      •	Trường dữ liệu: private static final long serialVersionUID = 20150711L; 
b)	Tương tác với server theo kịch bản
1)	Gửi đối tượng là chuỗi chứa mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;5AD2B818"
2)	Nhận một đối tượng là thể hiện của lớp Laptop từ server
3)	Sửa các thông tin sai của sản phẩm về tên và số lượng.  Gửi đối tượng vừa được sửa sai lên server
4)	Đóng socket và kết thúc chương trình.
 * @author Acer
 */
public class ObjectStream {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109", 2209);
            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
            oos.writeObject("B18DCAT183;kMTPCZJD");
            oos.flush();
            
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            Laptop laptop = (Laptop) ois.readObject();
            System.out.println(laptop);
            
            StringBuilder sb = new StringBuilder(laptop.getQuantity() + "");
            laptop.setQuantity(Integer.parseInt(sb.reverse().toString()));
            
            String[] arr = laptop.getName().split("\\s+");
            sb = new StringBuilder("");
            String tmp = arr[0];
            arr[0] = arr[arr.length-1];
            arr[arr.length - 1] = tmp;
            for(int i = 0; i < arr.length; i++) {
                sb.append(arr[i] + " ");
            }
            
            laptop.setName(sb.toString().trim());
            oos.writeObject(laptop);
            oos.flush();
            
            System.out.println(laptop);
            
            oos.close();
            ois.close();
            client.close();
            
        } catch(IOException ex) {
            ex.printStackTrace();
        } catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}