/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapRMI;

import java.rmi.Naming;
import RMI.CharacterService;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Acer
 */
public class RMICharacter2 {
    
    public static String toBinary(int n) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<values.length; i++) {
            int cnt = n / values[i];
            n %= values[i];
            if(cnt > 0) {
                for(int j=0; j<cnt; j++) sb.append(romans[i]);
            }
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        String path = "rmi://203.162.10.109/RMICharacterService";
        String studentCode = "B18DCCN143", qCode = "mI1alyyk";
        try {
            CharacterService service = (CharacterService) Naming.lookup(path);
            String strReceive = service.requestCharacter(studentCode, qCode);
            System.out.println(strReceive);
            String ans = toBinary(Integer.parseInt(strReceive));
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
