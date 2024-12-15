/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Thread;

import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Acer
 */
public class ReturnDigestUserInterface {
    
    public static void main(String[] args) {
        for (String filename : args) {
            // calculate the digest
            ReturnDigest dr = new ReturnDigest(filename);
            dr.start();
            
            // now print the result
            StringBuilder result = new StringBuilder(filename);
            result.append(": ");
            byte[] digest = dr.getDigest();
            result.append(DatatypeConverter.printHexBinary(digest));
            System.out.println(result);
        }
    }
    
}
