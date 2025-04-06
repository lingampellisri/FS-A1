public class SegmentTree{
    int[] nums;
    int n;
    int[] segTree;
    int[] lazy;
    int size;
    int zerosCnt;
    public SegmentTree(int[] nums){
        this.nums = nums;
        n = nums.length;
        size = (n & (n - 1)) == 0 ? 2*n-1 : 4*n;
        segTree = new int[size];
        lazy = new int[size];
        zerosCnt = 0;
        buildTree(0,0,n-1);
    }

    private void buildTree(int i,int l,int r){
        if(l == r){
            if(nums[l] == 0) zerosCnt++;
            segTree[i] = nums[l];
            return;
        }
        int mid = (l + r)/2;
        buildTree(2*i+1,l,mid);
        buildTree(2*i+2,mid+1,r);
        segTree[i] = segTree[2*i+1] + segTree[2*i+2];
    }

    public void rangeUpdate(int l,int r){ // this is taking more time 
        for(int i = l ; i <= r; i++){
            if(nums[i] > 0){
                update(i,nums[i] - 1,0,0,n-1); // update only if it is +ve
            }
        }

    }

    public void rangeUdpate(int i,int l,int r,int start,int end,int val){
        if(i >= size) return;
        // first check does the lazy node at this ith index have any value
        if( lazy[i] != 0){
            // first add iska value * (number of nodes below it) to segment tree ka node 
            segTree[i] += (r - l + 1) * lazy[i];

            // Only if this is not a leaf node , propagate this value to down below nodes
            if(l != r){
                lazy[2*i+1] += lazy[i];
                lazy[2*i+2] += lazy[i];
            }

            // reset this lazy node to 0
            lazy[i] = 0;
        }

        // Now check if am i in range of start to end
        if(r < start || l > end) return;

        // check if you are in range
        else if(l >= start && r <= end){

            // Now we should update this value 
            segTree[i] += (r - l + 1) * val;

            // Don't forget to propagate the value to the below nodes
            if(l != r){
                lazy[2*i+1] += val;
                lazy[2*i+2] += val;
            }
            return;
        }

        // In case of overlapping range go left and right and bring the answer
        int mid = l + (r - l)/2;
        rangeUdpate(2*i+1,l,mid,start,end,val);
        rangeUdpate(2*i+2,mid+1,r,start,end,val);

        // once everything is done update the node value 
        segTree[i] = segTree[2*i+1] + segTree[2*i+2];
    }

    public void update(int index, int val, int i, int l, int r) {
        if (l == r) {
            nums[index] = val;  // Update the original array
            segTree[i] = val;   // Update the segment tree
            return;
        }

        int mid = l + (r - l) / 2;
        if (index <= mid) {
            update(index, val, 2 * i + 1, l, mid);
        } else {
            update(index, val, 2 * i + 2, mid + 1, r);
        }

        segTree[i] = segTree[2 * i + 1] + segTree[2 * i + 2];
    }

}