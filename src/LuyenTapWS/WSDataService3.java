/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapWS;

import java.util.ArrayList;
import java.util.List;
import vn.medianews.DataService;
import vn.medianews.DataService_Service;

/**
 *
 * @author Acer
 */
public class WSDataService3 {
    
    public static String toBinary(int n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            sb.append(n % 2);
            n /= 2;
        }
        return sb.reverse().toString();
    }
    
    public static void main(String[] args) {
        DataService_Service service = new DataService_Service();
        DataService port = service.getDataServicePort();
        String studentCode = "B18DCCN143", qCode = "cApCgJCs";
        List<Integer> listReceive = port.getData(studentCode, qCode);
        System.out.println(listReceive);
        List<String> listSend = new ArrayList<>();
        for(Integer x : listReceive) {
            listSend.add(toBinary(x));
        }
        port.submitDataStringArray(studentCode, qCode, listSend);
        System.out.println(listSend);
    }
}
