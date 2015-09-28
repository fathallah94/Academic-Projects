import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;


public class Test {
	static int[] arr,temp;
	
	static void generateRandom(int size){
		Random rand = new Random();
		arr = new int[size];
		for(int i=0;i<size;i++)
			arr[i]= rand.nextInt(1000);
	}
	static void generateSorted(int size){
		arr = new int[size];
		for(int i=0;i<size;i++)
			arr[i]=i;
	}
	static void generateInverted(int size){
		arr = new int[size];
		for(int i=0;i<size;i++)
			arr[i]=size-i;
	}
	static void test(){
		BHeap h = new BHeap();
		Sorting s = new Sorting();
		int size = arr.length;
		
		tempo();
		long start = System.currentTimeMillis();
		h.heapsort(temp);
		System.out.print("heap sort : ");
		for(int i=0;i<temp.length;i++)
			System.out.print(temp[i]+" ");
		System.out.println();
		long end = System.currentTimeMillis();
//		System.out.println("heap sort time for size"+size+" is "+(end-start)+"Milliseconds.");
		
		tempo();
		start = System.currentTimeMillis();
		s.bubble(temp);
		System.out.print("bubble sort : ");
		for(int i=0;i<temp.length;i++)
			System.out.print(temp[i]+" ");
		System.out.println();
		end = System.currentTimeMillis();
//		System.out.println("Bubble sort time for size"+size+" is "+(end-start)+"Milliseconds.");

		tempo();
		start = System.currentTimeMillis();
		s.selection(temp);
		System.out.print("selection sort : ");

		for(int i=0;i<temp.length;i++)
			System.out.print(temp[i]+" ");
		System.out.println();
		end = System.currentTimeMillis();
//		System.out.println("Selection sort time for size"+size+" is "+(end-start)+"Milliseconds.");

		tempo();
		start = System.currentTimeMillis();
		s.insertion(temp);
		System.out.print("insertion sort : ");

		for(int i=0;i<temp.length;i++)
			System.out.print(temp[i]+" ");
		System.out.println();
		end = System.currentTimeMillis();
//		System.out.println("Insertion sort time for size"+size+" is "+(end-start)+"Milliseconds.");

		tempo();
		start = System.currentTimeMillis();
		s.merge(temp,0,temp.length-1,true);
		System.out.print("merge sort : ");

		for(int i=0;i<temp.length;i++)
			System.out.print(temp[i]+" ");
		System.out.println();
		end = System.currentTimeMillis();
//		System.out.println("Merge sort time for size"+size+" is "+(end-start)+"Milliseconds.");

		tempo();
		start = System.currentTimeMillis();
		s.quick(temp,0,temp.length-1);
		System.out.print("quick sort : ");

		for(int i=0;i<temp.length;i++)
			System.out.print(temp[i]+" ");
		System.out.println();
		end = System.currentTimeMillis();
//		System.out.println("Quick sort time for size"+size+" is "+(end-start)+"Milliseconds.");

	}
	static void tempo(){
		temp = new int[arr.length];
		for(int i=0;i<arr.length;i++)
			temp[i]=arr[i];
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		generateRandom(n);
		for(int i=0;i<n;i++)
			System.out.print(arr[i]+"  ");
		System.out.println();
		
		test();
//		for(int i=10;i<=100000;i*=10){
//			System.out.println("size:"+i+"  ==========================================");
//
//			generateRandom(i);
//			System.out.println("Random array :");
//			test();
//			System.out.println("*******************************************");
//			
//			generateSorted(i);
//			System.out.println("Sorted array :");
//			test();
//			System.out.println("*******************************************");
//			
//			generateInverted(i);
//			System.out.println("Inverted array :");
//			test();
//			System.out.println("*******************************************");
//			
//			System.out.println("==========================================");
//		}
	}
}
