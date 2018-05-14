package hmeng.lc.common;

/*
 * Binary indexed tree
 */
public class BIT {
    int[] nums;
    int size;
    public BIT(int size) {
        this.size = size;
        nums = new int[size+1];
    }
    
    public void update(int pos, int delta) {
        while(pos <= size) {
            nums[pos] += delta;
            pos += lowbit(pos);
        }
    }
    
    public int query(int pos) {
        int sum = 0;
        while (pos > 0) {
            sum += nums[pos];
            pos -= lowbit(pos);
        }
        
        return sum;
    }
    
    private int lowbit(int i) {
        return i & (-i);
    }
}
