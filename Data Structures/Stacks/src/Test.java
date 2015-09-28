import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;


public class Test {
	static public void main(String[] args) throws IOException{
		Stack test = new Stack();
		char choice='z';
		while(choice!='0'){
			System.out.print("1:Push.\n2:Pop\n3:Peek\n4:Get size.\n5:Check if empty.\n0:quit.\n");
			BufferedReader input = new BufferedReader (new InputStreamReader (System.in));
			choice = input.readLine().charAt(0);
			if(choice=='1'){
				System.out.println("Enter element :");
				test.push(input.readLine());
			}
			else if(choice=='2'){
				try{
				System.out.println(test.pop());
				}catch(EmptyStackException e){System.out.println("Stack is empty.");}
			}
			else if(choice=='3'){
				try{
				System.out.println(test.peek());
				}catch(EmptyStackException e){System.out.println("Stack is empty.");}
			}
			else if(choice=='4'){
				System.out.println(test.size());
			}
			else if(choice=='5'){
				System.out.println(test.isEmpty());
			}
		}
	}
}