/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LuyenTapTCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
[Mã câu hỏi (qCode): RfEl9os6].  Một chương trình server hỗ trợ kết nối qua giao thức TCP 
* tại cổng 2206 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu xây dựng 
* chương trình client thực hiện kết nối tới server sử dụng luồng byte dữ liệu 
* (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
    a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ:
    * "B16DCCN999;76B68B3B".
    b. Nhận dữ liệu từ server là một chuỗi các giá trị số nguyên được phân tách bởi ký tự ",". 
    * Ví dụ: 5,10,20,25,50,40,30,35.
    c. Tìm chuỗi con tăng dần dài nhất và gửi độ dài của chuỗi đó lên server theo format
    * "chuỗi tăng dài nhất; độ dài". Ví dụ: 5,10,20,25,50;5
    d. Đóng kết nối và kết thúc chương trình.
    * 
 O(n*n) solution: https://takeuforward.org/data-structure/printing-longest-increasing-subsequence-dp-42/
 O(nlogn) solution: https://stackoverflow.com/questions/6129682/longest-increasing-subsequenceonlogn
 * @author Acer
 */
public class ByteStream4 {
    
    public static List<Integer> LIS(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int[] hash = new int[n];
        Arrays.fill(hash, 1);
        for(int i=0; i< n; i++) {
            hash[i] = i;
            for(int prev_index = 0; prev_index < i; prev_index ++) {
                if(arr[prev_index] < arr[i] && 1 + dp[prev_index] > dp[i]) {
                    hash[i] = prev_index;
                    dp[i] = dp[prev_index] + 1;
                }
            }
        }
        
        int ans = -1;
        int lastIndex = -1;
        for(int i=0; i<n; i++) {
            if(dp[i] > ans) {
                ans = dp[i];
                lastIndex = i;
            }
        }
        
        List<Integer> list = new ArrayList<>();
        list.add(arr[lastIndex]);
        
        while(hash[lastIndex] != lastIndex) {
            lastIndex = hash[lastIndex];
            list.add(arr[lastIndex]);
        }
        
        List<Integer> res = new ArrayList<>();
        for(int i=list.size()-1; i>=0; i--) {
            res.add(list.get(i));
        }
        return res;
    }
    
    public static void main(String[] args) {
        try {
            Socket client = new Socket("203.162.10.109", 2206);
            
            byte[] data = "B21DCCN193;o5U5fdAL".getBytes();
            OutputStream os = client.getOutputStream();
            os.write(data);
            os.flush();
            
            byte[] buff = new byte[1024];
            InputStream is = client.getInputStream();
            is.read(buff);
            
            String strReceive = new String(buff).trim();
            System.out.println(strReceive.length());
            String[] arr = strReceive.split(",");
            
            int[] res = new int[arr.length];
            for(int i=0; i<arr.length; i++) {
                res[i] = Integer.parseInt(arr[i]);
            }
            
            List<Integer> list = LIS(res);
            
            StringBuilder ans = new StringBuilder();
            for(int x : list) {
                ans.append(x + ",");
            }
            ans.deleteCharAt(ans.length()-1);
            ans.append(";" + list.size());
            System.out.println(list);
            System.out.println(ans.toString());
            data = ans.toString().getBytes();
            os.write(data);
            os.flush();
            
            os.close();
            is.close();
            client.close();
            
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
