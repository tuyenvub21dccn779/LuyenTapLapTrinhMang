/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapRMI;

import RMI.ByteService;
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
public class RMIByte2 {
    
    public static String toOctal(int n) {
        String res = "";
        while(n > 0) {
            res = (n % 8) + res;
            n /= 8;
        }
        while(res.length() < 3) {
            res = "0" + res;
        }
        return res;
    }
    
    public static void main(String[] args) {
        String path = "rmi://203.162.10.109/RMIByteService";
        String studentCode = "B21DCCN588", qCode = "URRQ18h8";
        try {
            ByteService service = (ByteService) Naming.lookup(path);
            byte[] data = service.requestData(studentCode, qCode);
            String ans = "";
            for(int x : data) {
                System.out.print(x + " ");
                ans += toOctal(x);
            }
            System.out.println("");
            System.out.println(ans);
            service.submitData(studentCode, qCode, ans.getBytes());
            
        } catch (NotBoundException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
