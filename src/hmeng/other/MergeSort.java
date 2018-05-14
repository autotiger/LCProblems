package hmeng.other;

import java.util.Arrays;

public class MergeSort {

    public void mergeSort(int[] a, int l, int r) {
        if (l == r) return;
        int m = l + (r-l)/2;
        mergeSort(a, l, m);
        mergeSort(a, m+1, r);
        merge(a, l, m, r);
    }
    
    private void merge(int[] a, int l, int m, int r) {
        int[] res = new int[r-l+1];
        int l1 = l, l2 = m+1, index = 0;
        while (l1 <= m || l2 <= r) {
            int lv1 = Integer.MAX_VALUE, lv2 = Integer.MAX_VALUE;
            if (l1 <= m) lv1 = a[l1];
            if (l2 <= r) lv2 = a[l2];
            if (lv1 <= lv2) {
                res[index++] = lv1;
                l1++;
            } else {
                res[index++] = lv2;
                l2++;
            }
        }
        
        for (int i = l; i <= r; i++) {
            a[i] = res[i-l];
        }
    }
    
    public static void main(String[] args) {
        int[] a = {3, 4, 9, 0, -1, 5};
        MergeSort ms = new MergeSort();
        ms.mergeSort(a, 0, a.length-1);
        System.out.println(Arrays.toString(a));
    }

}
