/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapRMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import RMI.CharacterService;

/**
[Mã câu hỏi (qCode): 0KHH5auE].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện 
* cho phép triệu gọi từ xa để xử lý chuỗi.
Giao diện từ xa:
public interface CharacterService extends Remote {
public String requestCharacter(String studentCode, String qCode) throws RemoteException;
public void submitCharacter(String studentCode, String qCode, String strSubmit) throws RemoteException;
}
Trong đó:
•	Interface CharacterService được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa CharacterService được đăng ký với RegistryServer với 
* tên là: RMICharacterService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với chuỗi 
* được nhận từ RMI Server:
a. Triệu gọi phương thức requestCharacter để nhận chuỗi ngẫu nhiên từ server với định dạng: 
* "Chuỗi đầu vào".
b. Thực hiện đếm tần số xuất hiện của mỗi ký tự trong chuỗi đầu vào và tạo ra chuỗi kết quả 
* theo định dạng <Ký tự><Số lần xuất hiện>, sắp xếp theo thứ tự xuất hiện của các ký tự trong 
* chuỗi.
Ví dụ: Chuỗi đầu vào "AAABBC" -> Kết quả: "A3B2C1".
c. Triệu gọi phương thức submitCharacter để gửi chuỗi kết quả trở lại server.
d. Kết thúc chương trình client.
 * @author Acer
 */
public class RMICharacter5 {
    public static void main(String[] args) {
        String url = "rmi://203.162.10.109/RMICharacterService";
        String studentCode = "B21DCCN175", qCode = "0KHH5auE";
        try {
            CharacterService service = (CharacterService) Naming.lookup(url);
            String strReceive = service.requestCharacter(studentCode, qCode);
            System.out.println(strReceive);
            String ans = "";
            int[] count = new int[256];
            for(char x : strReceive.toCharArray()) {
                count[x] ++;
            }
            for(char x : strReceive.toCharArray()) {
                if(count[x] > 0) {
                    ans += x + "" + count[x];
                    count[x] = 0;
                }
            }
            System.out.println(ans);
            service.submitCharacter(studentCode, qCode, ans);
            
        } catch (NotBoundException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
