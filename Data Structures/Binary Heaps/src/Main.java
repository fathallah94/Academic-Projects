import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
	static void heapsMenu(BufferedReader br)throws Exception{
		BHeap h= new BHeap();
		while(true){
			System.out.println("1.Build Max Heap.\n2.Max Heapify.\n3.Extract Max.\n4.Insert.\n5.Heap Sort.\n0.back.");
			int choice=Integer.parseInt(br.readLine());
			if(choice==1){
				System.out.println("Enter array :");
				String[] in = br.readLine().split(" ");
				int[] a = new int[in.length];
				for(int i=0;i<a.length;i++)
					a[i]= Integer.parseInt(in[i]);
				
				h.buildMaxHeap(a);
				for(int i=1;i<=h.heapSize;i++)
					System.out.print(h.arr[i]+" ");
				System.out.println();
			}
			else if(choice==2){
				System.out.println("Enter index :");
				int x = Integer.parseInt(br.readLine());
				h.maxHeapify(x);
				for(int i=1;i<=h.heapSize;i++)
					System.out.print(h.arr[i]+" ");
				System.out.println();
			}
			else if(choice==3){
				System.out.println("max = "+h.extractMax());
				for(int i=1;i<=h.heapSize;i++)
					System.out.print(h.arr[i]+" ");
				System.out.println();
			}
			else if(choice==4){
				System.out.println("Enter value :");
				int x = Integer.parseInt(br.readLine());
				h.insert(x);
				for(int i=1;i<=h.heapSize;i++)
					System.out.print(h.arr[i]+" ");
				System.out.println();
			}
			else if(choice==5){
				System.out.println("Enter array to be sorted :");
				String[] in = br.readLine().split(" ");
				int[] a = new int[in.length];
				for(int i=0;i<a.length;i++)
					a[i]=Integer.parseInt(in[i]);
				
				h.heapsort(a);
				for(int i=0;i<a.length;i++)
					System.out.print(a[i]+" ");
				System.out.println();
			}
			else if(choice==0)
				break;
		}
	}
	
	static void sortingMenu(BufferedReader br)throws Exception{
		Sorting s = new Sorting();
		
		while(true){
			System.out.println("Enter array to be sorted :");
			String[] in = br.readLine().split(" ");
			int[] a = new int[in.length];
			for(int i=0;i<a.length;i++)
				a[i]=Integer.parseInt(in[i]);
			
			System.out.println("1.Bubble.\n2.Selection.\n3.Insertion.\n4.Merge.\n5.Quick.\n0.back.");
			int choice = Integer.parseInt(br.readLine());
			if(choice==1){
				s.bubble(a);
				for(int i=0;i<a.length;i++)
					System.out.print(a[i]+" ");
				System.out.println();
			}
			else if(choice==2){
				s.selection(a);
				for(int i=0;i<a.length;i++)
					System.out.print(a[i]+" ");
				System.out.println();
			}
			else if(choice==3){
				s.insertion(a);
				for(int i=0;i<a.length;i++)
					System.out.print(a[i]+" ");
				System.out.println();
			}
			else if(choice==4){
				s.merge(a,0,a.length-1,true);
				for(int i=0;i<a.length;i++)
					System.out.print(a[i]+" ");
				System.out.println();
			}
			else if(choice==5){
				s.quick(a,0,a.length-1);
				for(int i=0;i<a.length;i++)
					System.out.print(a[i]+" ");
				System.out.println();
			}
			else if(choice==0)
				break;
		}
	}
	
	public static void main(String[] args)throws Exception{
		int choice;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true){
			System.out.println("1.Heaps.\n2.Sorting.\n0.exit.");
			choice = Integer.parseInt(br.readLine());
			if(choice==1)
				heapsMenu(br);
			else if(choice==2)
				sortingMenu(br);
			else if(choice==0)
				break;
			else
				System.out.println("wrong entry");
		}
	}
}
