/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapRMI;

import RMI.DataService;
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
public class RMIData {
    public static void main(String[] args) {
        try {
            DataService service = (DataService) Naming.lookup("rmi://203.162.10.109/RMIDataService");
            String studentCode = "B21DCCN779", qCode = "6by3RMCs";
            Object receiveData = service.requestData(studentCode, qCode);
            String ans = "";
            if(receiveData instanceof Integer) {
                int[] arr = {1, 2, 5, 10};
                int num = (Integer) receiveData;
                int count = 0;
                for(int i=arr.length-1; i>=0; i--) {
                    count += num / arr[i];
                    for(int j=0; j<num/arr[i]; j++) {
                        ans += arr[i] + ",";
                    }
                    num %= arr[i];
                }
                if(count == 0) ans = "0; 0";
                else ans = count + "; " + ans.substring(0, ans.length()-1);
            } else {
                ans = "-1";
            }
            System.out.println(ans);
            service.submitData(studentCode, qCode, ans);
            
        } catch (NotBoundException ex) {
            Logger.getLogger(RMIData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(RMIData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(RMIData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
