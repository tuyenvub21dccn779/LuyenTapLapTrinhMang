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
[Mã câu hỏi (qCode): zmBfyMxR].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện 
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
* "Từ khóa;Chuỗi đầu vào"
b. Thực hiện thao tác mã hóa Vigenère cho chuỗi nhận được. Biết rằng, mã hóa Vigenère thực hiện 
* mã hóa mỗi ký tự trong chuỗi đầu vào được dịch đi một khoảng bằng với vị trí tương ứng của ký 
* tự trong từ khóa. (Từ khóa được lặp lại để khớp với độ dài của chuỗi)
Ví dụ: chuỗi ban đầu "PTIT;HELLO" -> từ khóa "PTIT" và chuỗi mã hóa là: "WXTED"
c. Triệu gọi phương thức submitCharacter để gửi chuỗi đã được mã hóa trở lại server.
d. Kết thúc chương trình client.
 * @author Acer
 */
public class RMICharacter4 {
    public static void main(String[] args) {
        String path = "rmi://203.162.10.109/RMICharacterService";
        String studentCode = "B21DCCN193", qCode = "zmBfyMxR";
        try {
            CharacterService service = (CharacterService) Naming.lookup(path);
            String strReceive = service.requestCharacter(studentCode, qCode);
            System.out.println(strReceive);
            String[] arr = strReceive.split(";");
            StringBuilder ans = new StringBuilder();
            int j = 0;
            for(int i=0; i < arr[1].length(); i++) {
                char newCharacter = (char) ((arr[0].charAt(j) + arr[1].charAt(i) - 'a' * 2) % 26 + 'a');
                ans.append(newCharacter);
                j++;
                if(j >= arr[0].length()) j = 0;
            }
            System.out.println(ans.toString());
            service.submitCharacter(studentCode, qCode, ans.toString());
        } catch (NotBoundException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
