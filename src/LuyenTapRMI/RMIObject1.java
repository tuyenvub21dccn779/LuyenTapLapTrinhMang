/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapRMI;

import RMI.Book;
import RMI.ObjectService;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Acer
 */
public class RMIObject1 {
    public static void main(String[] args) {
        String path = "rmi://203.162.10.109/RMIObjectService";
        String studentCode = "B21DCCN609", qCode = "7DZ8hRWl";
        try {
            ObjectService service = (ObjectService) Naming.lookup(path);
            Book book = (Book) service.requestObject(studentCode, qCode);
            StringBuilder ans = new StringBuilder();
            
            String[] arr = book.getAuthor().split("\\s+");
            ans.append(Character.toUpperCase(arr[0].charAt(0)));
            ans.append(Character.toUpperCase(arr[arr.length-1].charAt(0)));
            
            ans.append(book.getYearPublished() % 100);
            
            ans.append(book.getTitle().length());
            
            String pageCount = book.getPageCount() + "";
            while(pageCount.length() < 3) {
                pageCount = "0" + pageCount;
            }
            
            ans.append(pageCount);
            System.out.println(book);
            System.out.println(ans);
            book.setCode(ans.toString());
            service.submitObject(studentCode, qCode, book);
            
        } catch (NotBoundException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
        
    }
}
