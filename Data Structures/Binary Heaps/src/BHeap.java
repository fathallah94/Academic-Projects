
public class BHeap {
	public int length,heapSize;
	public int[] arr;
	
	public BHeap(){
		heapSize=0;
		length = 100000;
		arr = new int[length+5];
	}
	
	private void expand(int size){
		int[] temp = new int[heapSize+1];
		for(int i=1;i<=heapSize;i++)
			temp[i]=arr[i];
		int ns = Math.max(size,2*length);
		arr = new int[ns+5];
		for(int i=1;i<=heapSize;i++)
			arr[i]=temp[i];
	}
	
	public void maxHeapify(int i){
		
		while(2*i<=heapSize){
			int left,right,max;
			left=arr[2*i];
			max = Math.max(arr[i],left);
			if(2*i+1<=heapSize){
				right = arr[2*i+1];
				max = Math.max(max,right);
			}
			if(max==arr[i])
				break;
			else if(max==arr[2*i]){
				int temp = arr[i];
				arr[i]=arr[2*i];
				arr[2*i]=temp;
				i=2*i;
			}
			else{
				int temp = arr[i];
				arr[i]=arr[2*i+1];
				arr[2*i+1]=temp;
				i=2*i+1;
			}
		}
		
	}
	
	
	public void buildMaxHeap(int[] a){
		if(a.length>this.length)
			expand(a.length);
		for(int i=1;i<=a.length;i++){
			arr[i]=a[i-1];
		}
		heapSize=a.length;
		for(int i=heapSize/2;i>0;i--)
			maxHeapify(i);
	
	}
	
	public int extractMax(){
		int temp = arr[1];
		arr[1]=arr[heapSize];
		arr[heapSize]=temp;
		heapSize--;
		maxHeapify(1);
		return temp;
	}
	
	public void insert(int node){
		if(heapSize==length)
			expand(length);
		
		arr[++heapSize]=node;
		int i = heapSize;
		while(i>1){
			if(arr[i]<=arr[i/2])break;
			
			int temp=arr[i];
			arr[i]=arr[i/2];
			arr[i/2]=temp;
			i=i/2;
		}
	}
	
	public void heapsort(int[] a){
		buildMaxHeap(a);
		for(int i=heapSize;i>0;i--){
			a[i-1]=extractMax();
		}
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args){
		BHeap h = new BHeap();
		int[] a = {4,1,3,2,-1,5};
		h.heapsort(a);
		
		for(int i=0;i<a.length;i++)
			System.out.println(a[i]);
	}
}
