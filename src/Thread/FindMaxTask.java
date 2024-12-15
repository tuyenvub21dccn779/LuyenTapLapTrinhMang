/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thread;

import java.util.concurrent.Callable;

/**
 *
 * @author Acer
 */
public class FindMaxTask implements Callable<Integer> {
    
    private int[] data;
    private int start;
    private int end;
    
    FindMaxTask(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }
    
    public Integer call() {
        int max = Integer.MIN_VALUE;
        for (int i = start; i < end; i++) {
            max = Math.max(max, i);
        }
        return max;
    }
    
}
