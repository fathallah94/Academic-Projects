import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader fr = new BufferedReader(new FileReader("Keys10000.txt"));
		
		QuadraticTable Qtable = new QuadraticTable();
		LinearTable Ltable = new LinearTable();
		
		ArrayList<Integer> keys = new ArrayList<Integer>();
		while(fr.ready()){
			keys.add(Integer.parseInt(fr.readLine()));
		}
		
		int choice = -1;
		while(true){
			System.out.println("1.Quadratic load.\n2.Linear load.\n3.Quadratic Stats.\n4.Linear Stats.\n5.Quadratic Lookup.\n6.Linear Lookup.\n0.exit\n");
			try{
			choice = Integer.parseInt(br.readLine());
			}catch(Exception e ){
				System.out.println("wrong input\n\n");
				continue;
			}
			if(choice==0)break;
			else if(choice==1){
				Qtable.build(keys);
			}
			else if(choice==2){
				Ltable.build(keys);
			}
			else if(choice==3){
				Qtable.printStats();
			}
			else if(choice==4){
				Ltable.printStats();
			}
			else if(choice==5){
				System.out.println("Enter value :");
				int x = Integer.parseInt(br.readLine());
				System.out.println(Qtable.lookUp(x));
			}
			else if(choice==6){
				System.out.println("Enter value :");
				int x = Integer.parseInt(br.readLine());
				System.out.println(Ltable.lookUp(x));
			}
		}
		
	}
}
