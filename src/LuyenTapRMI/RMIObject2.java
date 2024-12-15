/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapRMI;

import RMI.Employee;
import RMI.ObjectService;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Acer
 */
public class RMIObject2 {

    public static int sumNumber(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    public static int countPrime(int n) {
        int count = 2;
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                count += 2;
            }
        }
        if (Math.sqrt(n) == 1d * sqrt) {
            count--;
        }
        return count;
    }

    public static void main(String[] args) {
        String path = "rmi://203.162.10.109/RMIObjectService";
        String studentCode = "B21DCCN588", qCode = "ECsrm7cE";
        try {
            ObjectService service = (ObjectService) Naming.lookup(path);
            Employee emp = (Employee) service.requestObject(studentCode, qCode);
            System.out.println(emp);

            int exper = emp.getExperienceYears();
            double factor = 1d * (exper + countPrime(exper) + sumNumber(exper)) / 100;
            emp.setFinalSalary((1 + factor) * emp.getBaseSalary());
            
            System.out.println(emp);
            service.submitObject(studentCode, qCode, emp);

        } catch (NotBoundException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
