import java.util.ArrayList;

public class QuadraticTable {
	public int[] h ;
	public ArrayList<node> table ;
	public int rebuildings ;
	private HashingMethod method;
	private boolean built;
	
	public QuadraticTable(){
		table = new ArrayList<node>();
		method = new HashingMethod();
		rebuildings=0;
		built=false;
	}

	public void build(ArrayList<Integer> list){
		table.clear();
//		node dummy = new node();
		for(int i=0;i<list.size()*list.size();i++)
			table.add(new node());
		if(list.size()==0)
			return;
		int size = 31-Integer.numberOfLeadingZeros(table.size());
		h = method.createH(size);
		rebuildings=0;
		
		while(true){
			boolean flag=true;
			for(int i=0;i<list.size();i++){
				int ind = method.HX(h, list.get(i));
				if(table.get(ind).dummy==false){
					flag=false;
					break;
				}
				table.get(ind).set(list.get(i));
			}
			if(flag)
				break;
			else{
				rebuildings++;
				h = method.createH(size);
				for(int i=0;i<table.size();i++)
					table.get(i).reset();
			}
		}
		built=true;
	}
	
	public boolean lookUp(int x){
		if(!built)
			return false;
		
		int ind = method.HX(h, x);
		if(ind<table.size() && table.get(ind).value==x)
			return true;
		return false;
	}
	
	public void printStats(){
		System.out.println("total memory occupied : "+table.size());
		System.out.println("number of rebuildings : "+rebuildings);
	}
	public static void main(String[] args)throws Exception{
		QuadraticTable table = new QuadraticTable();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=-500;i<=500;i++)
			list.add(i);
		
		table.build(list);
		System.out.println(table.lookUp(-501));
		System.out.println(table.lookUp(2));
		System.out.println(table.lookUp(5));
		System.out.println(table.lookUp(0));
		System.out.println(table.lookUp(501));
		System.out.println(table.lookUp(1000));
		table.printStats();

	}
}
