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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Acer
 */
public class RMIData1 {
    public static String getNext(ArrayList<Integer> list) {
        int i = list.size()-2;
        String ans = "";
        while(i >= 0 && list.get(i) >= list.get(i+1)) {
            i--;
        }
        if(i==-1) {
            for(int j=0; j<list.size(); j++) {
                ans += j + ",";
            }
        } else {
            int j = list.size() - 1;
            while(list.get(i) >= list.get(j)) {
                j--;
            }
            int temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
            j = list.size() - 1;
            while(i != j && i != list.size()) {
                temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
                i++; j--;
            }
            for(j=0; j<list.size(); j++) {
                ans += list.get(j) + ",";
            }
        }
        return ans.substring(0, ans.length()-1);
    }
    
    public static void main(String[] args) {
        String path = "rmi://203.162.10.109/RMIDataService";
        String studentCode = "B21DCCN609", qCode = "mcKOKcQx";
        try {
            DataService service = (DataService) Naming.lookup(path);
            
            String strReceive = (String) service.requestData(studentCode, qCode);
            
            System.out.println(strReceive);
            String[] arr = strReceive.trim().split(", ");
            ArrayList<Integer> list = new ArrayList<>();
            for(String x : arr) {
                list.add(Integer.parseInt(x));
            }
            
            String ans = getNext(list);
            System.out.println(ans);
            service.submitData(studentCode, qCode, ans);
            
        } catch (NotBoundException ex) {
            Logger.getLogger(RMIData1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(RMIData1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(RMIData1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
