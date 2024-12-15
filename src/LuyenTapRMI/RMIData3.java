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
import java.util.Comparator;

/**
 *
 * @author Acer
 */
public class RMIData3 {
    public static void main(String[] args) {
        String path = "rmi://203.162.10.109/RMIDataService";
        String studentCode = "B21DCCN211", qCode = "hkx4dSD5";
        try {
            DataService service = (DataService) Naming.lookup(path);
            String strReceive = (String) service.requestData(studentCode, qCode);
            String[] arr = strReceive.split("; ");
            int k = Integer.parseInt(arr[1]);
            arr = arr[0].split(", ");
            ArrayList<Integer> list = new ArrayList<>();
            for(String x : arr) {
                list.add(Integer.parseInt(x));
            }
            list.sort(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
                
            });
//            String ans = list.get(list.size() - k).toString();
            System.out.println(list.get(list.size() - k));
            service.submitData(studentCode, qCode, list.get(list.size() - k));
            
        } catch (NotBoundException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
