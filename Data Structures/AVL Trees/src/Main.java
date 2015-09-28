import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
	public static void main(String[] args)throws Exception{
		Dictionary dictionary = new Dictionary();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int choice;
		while(true){
			System.out.println("1.Load dictionary.\n2.Print dictionary size.\n3.Insert word.\n4.Look up a word.\n5.Remove word.\n6.Batch look ups.\n7.Batch deletions.\n0.exit.");
			try{
				choice = Integer.parseInt(br.readLine()) ;
			}catch(Exception e){
				System.out.println("Wrong input");
				continue;
			}
			
			if(choice==1){
				dictionary.loadDictionary();
				dictionary.printSize();
				dictionary.printHeight();
			}
			else if(choice==2)
				dictionary.printSize();
			else if(choice==3){
				System.out.println("Enter word :");
				dictionary.insert(br.readLine());
				dictionary.printSize();
				dictionary.printHeight();
			}
			else if(choice==4){
				System.out.println("Enter word :");
				dictionary.lookUp(br.readLine());
			}
			else if(choice==5){
				System.out.println("Enter word :");
				dictionary.remove(br.readLine());
				dictionary.printSize();
				dictionary.printHeight();
			}
			else if(choice==6){
				dictionary.BatchLookups();
				
			}
			else if(choice==7){
				dictionary.BatchDeletions();
				dictionary.printSize();
				dictionary.printHeight();
			}
			else if(choice==0)
				break;
			else
				System.out.println("Wrong input");
		}
	}
}
