/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapRMI;

import RMI.CharacterService;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
[Mã câu hỏi (qCode): CgmAuYMm].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho 
* phép triệu gọi từ xa để xử lý chuỗi. Cụ thể:
Giao diện từ xa:
    public interface CharacterService extends Remote {
        public String requestCharacter(String studentCode, String qCode) throws RemoteException;
        public void submitCharacter(String studentCode, String qCode, String strSubmit) throws RemoteException;
    }
Trong đó:
•	Interface CharacterService được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa CharacterService được đăng ký với RegistryServer 
* với tên là: RMICharacterService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với chuỗi 
* được nhận từ RMI Server:
a. Triệu gọi phương thức requestCharacter để nhận chuỗi ngẫu nhiên từ server. 
b. Thực hiện thao tác giải mã Caesar (mã hóa chuỗi bằng cách dịch từng ký tự đi một số lượng 
* vị trí nhất định). Biết rằng giá trị dịch đúng bằng kích thước của chuỗi chia lấy dư cho 7.
c. Triệu gọi phương thức submitCharacter để gửi chuỗi đã được giải mã trở lại server.
d. Kết thúc chương trình client.
 * @author Acer
 */
public class RMICharacter1 {
    public static void main(String[] args) {
        String path = "rmi://203.162.10.109/RMICharacterService";
        String studentCode = "B18DCAT183", qCode = "fPFC9G8w";
        try {
            CharacterService service = (CharacterService) Naming.lookup(path);
            String strReceive = service.requestCharacter(studentCode, qCode);
            System.out.println(strReceive);
            int k = strReceive.length() % 7;
            System.out.println(strReceive.length() + " " + k);
            String ans = "";
            
            for(char x : strReceive.toCharArray()) {
                char tmp = x;
                if(x >= 'A' && x <= 'Z') {
                    tmp = (char)((x - 'A' - k + 26) % 26 + 'A');
                } else if(x >= 'a' && x <= 'z') {
                    tmp = (char) ((x - 'a' - k + 26) % 26 + 'a');
                }
                ans += tmp;
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
