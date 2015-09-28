import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Test {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	
	static public void main(String[] args) throws IOException{
		char choice='z';
		int size;
		System.out.println("Enter size of array implemented queue :");
		size=Integer.parseInt(input.readLine());
		LinkedQueue queue = new LinkedQueue();
		ArrayQueue queue2=new ArrayQueue(size);
		while(choice!='0'){
			
			System.out.println("1.Enqueue.\n2.Dequeue.\n3.Get Size.\n4.Chekc if empty.\n0.quit.\n");
			choice=input.readLine().charAt(0);
			if(choice=='1'){
				System.out.println("Enter element :");
				Object temp=input.readLine();
				queue.enqueue(temp);
				try{
				queue2.enqueue(temp);
				}catch(IllegalStateException e){System.out.println("Array Queue is full.");}
					
					
			}
			else if(choice=='2'){
				try{
				System.out.println("Linked queue : "+queue.dequeue());
				System.out.println("Array queue : "+queue2.dequeue());
				}catch(NullPointerException e){System.out.println("Queue is empty.");}
			}
			else if(choice=='3'){
				System.out.println("Linked queue : "+queue.size());
				System.out.println("Array queue : "+queue2.size());
			}
			else if(choice=='4'){
				System.out.println("Linked queue : "+queue.isEmpty());
				System.out.println("Array queue : "+queue.isEmpty());
			}
			else if(choice!='0'){
				System.out.println("Enter a valid choice :");
				choice=input.readLine().charAt(0);
			}
		}
	}
}
