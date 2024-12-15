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

/**
 *
 * @author Acer
 */
public class RMIByte4 {
    public static void main(String[] args) {
        String path = "rmi://203.162.10.109/RMIByteService";
        String studentCode = "B21DCCN211", qCode = "QWLelwrH";
        try {
            ByteService service = (ByteService) Naming.lookup(path);
            byte[] data = service.requestData(studentCode, qCode);
            ArrayList<Integer> list = new ArrayList<>();
            int count = 1;
            System.out.print(((int) data[0]) + ", ");
            for(int i=1; i < data.length; i++) {
                System.out.print(((int) data[i]) + ", ");
                if(data[i] != data[i-1]) {
                    list.add((int)data[i-1]);
                    list.add(count);
                    count = 1;
                } else count++;
            }
            System.out.println("");
            list.add((int)data[data.length-1]);
            list.add(count);
            System.out.println(list);
            data = new byte[list.size()];
            for(int i = 0; i<list.size(); i++) {
                data[i] = (byte) (int) list.get(i);
            }
            service.submitData(studentCode, qCode, data);
            
        } catch (NotBoundException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
