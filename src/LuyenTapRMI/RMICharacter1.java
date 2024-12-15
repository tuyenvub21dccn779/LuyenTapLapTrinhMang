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
 *
 * @author Acer
 */
public class RMICharacter1 {
    public static void main(String[] args) {
        String path = "rmi://203.162.10.109/RMICharacterService";
        String studentCode = "B21DCCN609", qCode = "mSSqvcbh";
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
