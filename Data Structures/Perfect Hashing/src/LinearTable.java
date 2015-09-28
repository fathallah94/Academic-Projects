import java.util.ArrayList;

public class LinearTable {
	public int[] h1 ;
	QuadraticTable[] tables ;
	private HashingMethod method;
	public int rebuildings ;
	private boolean built;
	
	public LinearTable(){
		method = new HashingMethod();
		rebuildings=0;
		built=false;
	}
	
	public void build(ArrayList<Integer> list){
		if(list.size()==0)
			return ;
		
		ArrayList<Integer>[] arr = new ArrayList[list.size()];
		for(int i=0;i<list.size();i++)
			arr[i]=new ArrayList<Integer>();
		
		
		int size = 31-Integer.numberOfLeadingZeros(list.size());
		h1 = method.createH(size);
		for(int i=0;i<list.size();i++){
			int ind = method.HX(h1, list.get(i));
			arr[ind].add(list.get(i));
		}
		
		rebuildings=0;
		tables = new QuadraticTable[arr.length];
		for(int i=0;i<arr.length;i++){
			tables[i] = new QuadraticTable();
			tables[i].build(arr[i]);
			rebuildings+=tables[i].rebuildings;
		}
		built=true;
	}
	
	public boolean lookUp(int x){
		if(!built)
			return false;
		int ind = method.HX(h1, x);
		if(ind>=tables.length)
			return false;
		
		return tables[ind].lookUp(x);
	}
	public void printStats(){
		int size = 0;
		for(int i=0;i<tables.length;i++)
			size+=tables[i].table.size();
		
		System.out.println("total memory occupied : "+size);
		System.out.println("total number of rebuildings : "+rebuildings);
	}
	public void printSingleStats(){
		System.out.println("single numbers of rebuildings :");
		for(int i=0;i<tables.length;i++)
			System.out.println(i+" : "+tables[i].rebuildings);
	}
	public static void main(String[] args){
		LinearTable table = new LinearTable();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<=1000;i++)
			list.add(i);
		
		
		table.build(list);
		System.out.println(table.lookUp(-501));
		System.out.println(table.lookUp(-50));
		System.out.println(table.lookUp(2));
		System.out.println(table.lookUp(5));
		System.out.println(table.lookUp(0));
		System.out.println(table.lookUp(501));
		System.out.println(table.lookUp(1000));
		table.printStats();
	}
}
