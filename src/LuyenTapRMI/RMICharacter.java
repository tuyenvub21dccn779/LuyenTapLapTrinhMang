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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Acer
 */
public class RMICharacter {

    public static void main(String[] args) {
        try {
            CharacterService characterService = (CharacterService) Naming.lookup("rmi://203.162.10.109/RMICharacterService");
            HashMap<Character, Integer> map = new HashMap<>();
            map.put('I', 1);
            map.put('V', 5);
            map.put('X', 10);
            map.put('L', 50);
            map.put('C', 100);
            map.put('D', 500);
            map.put('M', 1000);
            String studentCode = "B21DCCN588", qCode = "UNH9uNaS";
            StringBuilder receiveStrBuilder = new StringBuilder(characterService.requestCharacter(studentCode, qCode));
            String receiverStr = receiveStrBuilder.reverse().toString();
            System.out.println(receiveStrBuilder.toString());
            Integer ans = map.get(receiverStr.charAt(0));
            for(int i = 1; i <receiverStr.length(); i++) {
                Integer nowInt = map.get(receiverStr.charAt(i));
                Integer preInt = map.get(receiverStr.charAt(i-1));
                if(nowInt < preInt) ans -= nowInt;
                else ans += nowInt;
            }
            System.out.println(ans);
            characterService.submitCharacter(studentCode, qCode, ans+"");
            
            
        } catch (NotBoundException ex) {
            Logger.getLogger(RMICharacter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(RMICharacter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(RMICharacter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
