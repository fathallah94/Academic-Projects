import java.util.Random;


public class Sorting {
	
	public void bubble(int[] arr){
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr.length-1;j++){
				if(arr[j]>arr[j+1]){
					int temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
				}
			}
		}
	}
	
	public void selection(int[] arr){
		for(int i=0;i<arr.length-1;i++){
			int min=arr[i],minj=i;
			for(int j=i+1;j<arr.length;j++){
				if(arr[j]<min){
					min=arr[j];
					minj=j;
				}
			}
			int temp=arr[i];
			arr[i]=arr[minj];
			arr[minj]=temp;
		}
	}
	
	public void insertion(int[] arr){
		for(int i=1;i<arr.length;i++){
			int temp=arr[i],j;
			for(j=i-1;j>=0 && temp<arr[j] ;j--){
				arr[j+1]=arr[j];
			}
			arr[j+1]=temp;
		}
	}
	int[] temp;

	public void merge(int[] arr,int lo,int hi,boolean flag){
		if(flag){
			temp = new int[arr.length];
		}
		int mid=(lo+hi)/2;
		if(lo<hi){
			merge(arr,lo,mid,false);
			merge(arr,mid+1,hi,false);
			merging(arr,lo,mid,hi);
		}
	}
	private void merging(int[] arr,int lo ,int mid,int high){
		int l=lo,r=mid+1,i=lo;
		
		while(l<=mid && r<=high){
			if(arr[l]<=arr[r])
				temp[i++]=arr[l++];
			else
				temp[i++]=arr[r++];
		}
		
		for(int j=l;j<=mid;j++)
			temp[i++]=arr[j];
		
		for(int j=r;j<=high;j++)
			temp[i++]=arr[j];

		for(int j=lo;j<=high;j++)
			arr[j]=temp[j];
	}
	private boolean sorted(int[] arr){
		for(int i=1;i<arr.length;i++){
			if(arr[i]<arr[i-1])
				return false;
		}
		return true;
	}
	private boolean inverted(int[] arr){
		for(int i=1;i<arr.length;i++){
			if(arr[i]>arr[i-1])
				return false;
		}
		return true;
	}
	public void quick(int[] arr,int left,int right){
		if(sorted(arr))
			return;
		if(inverted(arr)){
			for(int i=0;i<arr.length/2;i++){
				int temp = arr[i];
				arr[i]=arr[arr.length-1-i];
				arr[arr.length-1-i]=temp;
			}
			return;
		}
		if(right-left<=0)
			return;
		
		int pivot=left,k=left;
		for(int i=left+1;i<=right;i++){
			if(arr[i]<arr[pivot]){
				int temp = arr[i];
				arr[i]=arr[++k];
				arr[k]=temp;
			}
		}
		int temp = arr[pivot];
		arr[pivot]=arr[k];
		arr[k]=temp;
		
		quick(arr,left,k-1);
		quick(arr,k+1,right);
		
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args){
		int[] a = {4,1,3,2,16,9,10,14,8,7,200,-200};
		Sorting s = new Sorting();
		s.quick(a,0,a.length-1);
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
	}
}
