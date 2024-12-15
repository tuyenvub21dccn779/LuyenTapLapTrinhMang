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
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Acer
 */
public class RMIByte1 {
    public static void main(String[] args) {
        String path = "rmi://203.162.10.109/RMIByteService";
        String studentCode = "B21DCCN609", qCode = "x1EgEjOG";
        try {
            ByteService service = (ByteService) Naming.lookup(path);
            byte[] data = service.requestData(studentCode, qCode);
            ArrayList<Integer> list = new ArrayList<>();
            for(int i=0; i<data.length-1; i++) {
                list.add((int)data[i]);
            }
            list.sort(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            byte[] buff = new byte[2];
            int k = data[data.length-1];
            int num = list.get(k-1);
            buff[0] = (byte) num;
            for(int i=0; i<data.length-1; i++) {
                if(num == data[i]) {
                    buff[1] = (byte) (i+1);
                    break;
                }
            }
            service.submitData(studentCode, qCode, buff);
            
        } catch (NotBoundException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
