/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapWS;

import java.util.List;
import vn.medianews.CharacterService;
import vn.medianews.CharacterService_Service;

/**
 *
 * @author Acer
 */
public class WSCharacterService1 {
    public static void main(String[] args) {
        CharacterService_Service service = new CharacterService_Service();
        CharacterService port = service.getCharacterServicePort();
        String studentCode = "B21DCCN609", qCode = "fcaZpZ9q";
        List<String> list = port.requestStringArray(studentCode, qCode);
        System.out.println(list);
        String shortest = list.get(0), longest = list.get(0);
        for(String x : list) {
            if(x.length() < shortest.length()) {
                shortest = x;
            }
            if(x.length() > longest.length()) {
                longest = x;
            }
        }
        String ans = longest + ";" + shortest;
        System.out.println(ans);
        port.submitCharacterString(studentCode, qCode, ans);
    }
}
