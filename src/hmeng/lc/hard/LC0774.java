package hmeng.lc.hard;

import java.util.PriorityQueue;
import java.util.Queue;

public class LC0774 {

    /*
     * binary search O(nlogW)  W = maxDiff(first_station, last_station)
     */
    public double minmaxGasDist2(int[] stations, int K) {
        if (stations == null || stations.length == 0) return 0.0;
        int[] dist = new int[stations.length-1];
        for (int i = 0; i < stations.length-1; i++) {
            dist[i] = stations[i+1] - stations[i];
        }
        double left = 0.0, right = stations[stations.length-1] - stations[0];

        while (left+1e-6 < right) {
            double mid = left + (right-left)/2;
            int count = 0;
            for (int i = 0; i < dist.length; i++) {
                count += Math.ceil(dist[i] / mid) - 1;
            }
            if (count <= K) right = mid;
            else left = mid;
        }
        
        return left;
    }
    
    /*
     * heap solution,  O(Klog(n))
     */
    public double minmaxGasDist(int[] stations, int K) {
        if (stations == null || stations.length == 0) return 0.0;
        Queue<int[]> queue = new PriorityQueue<>((l, r) -> (double)l[0]/l[1] < (double)r[0]/r[1] ? 1 : -1);
        for (int i = 1; i < stations.length; i++) {
            queue.offer(new int[] {stations[i] - stations[i-1], 1});
        }
        
        for (int k = 0; k < K; k++) {
            int[] diff = queue.poll();
            diff[1]++;
            queue.offer(diff);
        }
        
        int[] top = queue.peek();
        return (double)top[0] / top[1];
    }
    
    public static void main(String[] args) {
        LC0774 lc = new LC0774();
        int[] stations = {10,19,25,27,56,63,70,87,96,97};
        int K = 3;
        System.out.println(lc.minmaxGasDist2(stations, K));
    }

}
