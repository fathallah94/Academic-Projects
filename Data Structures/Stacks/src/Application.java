import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Application {
	BufferedReader input = new BufferedReader (new InputStreamReader (System.in));
	Object [] expression;
	Object[] output= new Object[1000];
	String input2;
	int outputCounter=0;
	public Application() throws IOException{
		menu();
	}
	////
	public void menu() throws IOException{
		char choice ='z';
		char choice2='z';
		while(choice!='0'){
			System.out.print("1.enter new expression.\n0.quit.\n");
			choice=input.readLine().charAt(0);
			if(choice=='1'){
				System.out.println("1.Enter symbolic expression.\n2.Enter numeric expression.\n");
				choice2=input.readLine().charAt(0);
				while(choice2!='1' && choice2!='2'){
					System.out.println("re-enter a correct choice :");
					choice2=input.readLine().charAt(0);
				}
				System.out.println("Enter expression with space separated elements:");
				input2=input.readLine();
				boolean flag=true;
				while(flag){
					int i;
					for(i=0;i<input2.length();i++){
						if(input2.charAt(i)!=' ' && input2.charAt(i)!='+' && input2.charAt(i)!='-' && input2.charAt(i)!='*' && input2.charAt(i)!='/' && input2.charAt(i)!='(' && input2.charAt(i)!=')' && !Character.isDigit(input2.charAt(i)) && !Character.isLetter(input2.charAt(i)) ){
							flag=true;
							System.out.println("Enter valid space separated expression :");
							input2=input.readLine();
							break;
						}
					}
					if(i==input2.length())
						flag=false;
				}
				 
			
				expression=input2.split(" ");	
				outputCounter=0;
				validate();
				Convert();
				if(choice2=='1'){
					readNumbers();
				}
				System.out.println("Numeric value :");
				for(int i=0;i<outputCounter;i++){
					System.out.print(output[i]+" " );
				}
				System.out.println("\n");
				evaluate();
			}
			else if(choice!='0'){
				System.out.println("re-enter a correct choice");
			}
		}
	}
	////
	public void Convert(){
		Stack newStack= new Stack();
		
		for(int i=0;i<expression.length;i++){
			if(expression[i].equals("("))
				newStack.push(expression[i]);
			else if(expression[i].equals("/") || expression[i].equals("*")){
				boolean flag=true;
				while(flag){
					if(newStack.isEmpty()){
						newStack.push(expression[i]);
						flag=false;
					}
					else if(newStack.peek().equals("(") || newStack.peek().equals("+") || newStack.peek().equals("-")){
						newStack.push(expression[i]);
						flag=false;
					}
					else{
						output[outputCounter++]=newStack.pop();
					}
				}
			}
			else if(expression[i].equals("+") || expression[i].equals("-")){
				boolean flag=true;
				while(flag){
					if(newStack.isEmpty()){
						newStack.push(expression[i]);
						flag=false;
					}
					else if(newStack.peek().equals("(")){
						newStack.push(expression[i]);
						flag=false;
					}
					else{
						output[outputCounter++]=newStack.pop();
					}
				}
			}
			else if(expression[i].equals(")")){
				while(!newStack.isEmpty()){
					if(newStack.peek().equals("(")){
						Object temp=newStack.pop();
						break;
					}
					else
						output[outputCounter++]=newStack.pop();
				}
			}
			else{
				output[outputCounter++]=expression[i];
			}
		}
		while(!newStack.isEmpty()){
			output[outputCounter++]=newStack.pop();
		}
		System.out.println("the Post fix expression :");
		for(int i=0;i<outputCounter;i++){
			System.out.print(output[i] +" ");
		}
		System.out.println("\n");
	}
	//////
	public void readNumbers() throws IOException{
		for(int i=0;i<outputCounter;i++){
			if(!output[i].equals("(") && !output[i].equals(")") && !output[i].equals("+") && !output[i].equals("-") && !output[i].equals("*") && !output[i].equals("/")){
			System.out.println("Enter value of variable "+output[i]+" :");
			output[i]=input.readLine();
			}
		}
		
	}
	////
	public void evaluate(){
		Stack newStack = new Stack();
		boolean flag=true;
		for(int i=0;i<outputCounter;i++){
			Float x,y;
			if(output[i].equals("+")){
				try{
				x=Float.parseFloat((String)newStack.peek());
				x=Float.parseFloat((String)newStack.pop());
				}catch(ClassCastException e){
					x=(Float) newStack.pop();
					}
				try{
					y=Float.parseFloat((String)newStack.peek());
					y=Float.parseFloat((String)newStack.pop());
					}catch(ClassCastException e){
						y=(Float) newStack.pop();
						}				
				newStack.push(x+y);
			}
			else if(output[i].equals("-")){
				try{
					x=Float.parseFloat((String)newStack.peek());
					x=Float.parseFloat((String)newStack.pop());
					}catch(ClassCastException e){
						x=(Float) newStack.pop();
						}
					try{
						y=Float.parseFloat((String)newStack.peek());
						y=Float.parseFloat((String)newStack.pop());
						}catch(ClassCastException e){
							y=(Float) newStack.pop();
							}			
				newStack.push(y-x);
			} 
			else if(output[i].equals("*")){
				try{
					x=Float.parseFloat((String)newStack.peek());
					x=Float.parseFloat((String)newStack.pop());
					}catch(ClassCastException e){
						x=(Float) newStack.pop();
						}
					try{
						y=Float.parseFloat((String)newStack.peek());
						y=Float.parseFloat((String)newStack.pop());
						}catch(ClassCastException e){
							y=(Float) newStack.pop();
							}			
				newStack.push(x*y);
			} 
			else if(output[i].equals("/")){
				try{
					x=Float.parseFloat((String)newStack.peek());
					x=Float.parseFloat((String)newStack.pop());
					}catch(ClassCastException e){
						x=(Float) newStack.pop();
						}
					try{
						y=Float.parseFloat((String)newStack.peek());
						y=Float.parseFloat((String)newStack.pop());
						}catch(ClassCastException e){
							y=(Float) newStack.pop();
							}
				if(x==0){
					System.out.println("The answer is undefined.");
					flag=false;
					break;
				}
				else
					newStack.push(y/x);
				} 
			else{
				newStack.push(output[i]);
			}
		}
		if(flag)
			System.out.println("result = " + newStack.pop());
	
	}
	
	////
	public void validate() throws IOException{
		while(true){
			if(brackets() && signs())
				break;
			System.out.println("Enter a valid space separated expression :");
			expression=input.readLine().split(" ");
		}
	}
	////
	public boolean brackets(){
		Stack newStack = new Stack();
		for(int i=0;i<expression.length;i++){
			if(expression[i].equals("("))
				newStack.push(expression[i]);
			else if(expression[i].equals(")")){
				if(newStack.isEmpty())
					return false;
				else if(!newStack.pop().equals("("))
					return false;
			}
		}
		if(newStack.isEmpty())
			return true;
		else
			return false;
	}
	////
	public boolean signs(){
		if(expression[0].equals(")") || expression[0].equals("+") || expression[0].equals("-") || expression[0].equals("*") || expression[0].equals("/"))
			return false;
		for(int i=1;i<expression.length-1;i++){
			if(expression[i].equals("+") || expression[i].equals("-") || expression[i].equals("*")|| expression[i].equals("/")){
				if(expression[i+1].equals(")") || expression[i+1].equals("+") || expression[i+1].equals("-") || expression[i+1].equals("*")|| expression[i+1].equals("/"))
					return false;
				if(expression[i-1].equals("(") ||expression[i-1].equals("+") || expression[i-1].equals("-") || expression[i-1].equals("*")|| expression[i-1].equals("/"))
					return false;
			}
			else if(expression[i].equals("(")){
				if(expression[i+1].equals(")") || expression[i+1].equals("(") || expression[i+1].equals("+") || expression[i+1].equals("-") || expression[i+1].equals("*")|| expression[i+1].equals("/"))
					return false;
				if(expression[i-1].equals(")") || expression[i-1].equals("("))
					return false;
				if(!expression[i-1].equals("+") && !expression[i-1].equals("-") && !expression[i-1].equals("*") && !expression[i-1].equals("/"))
					return false;
			}
			else if(expression[i].equals(")")){
				if(expression[i-1].equals(")") || expression[i-1].equals("(") || expression[i-1].equals("+") || expression[i-1].equals("-") || expression[i-1].equals("*")|| expression[i-1].equals("/"))
					return false;
				if(expression[i+1].equals(")") || expression[i+1].equals("("))
					return false;
				if(!expression[i+1].equals("+") && !expression[i+1].equals("-") && !expression[i+1].equals("*") && !expression[i+1].equals("/"))
					return false;
			}
		}
		if(expression[expression.length-1].equals("(") || expression[expression.length-1].equals("+") || expression[expression.length-1].equals("-") || expression[expression.length-1].equals("*") || expression[expression.length-1].equals("/"))
			return false;
		
		return true;
	}
	////
	
	static public void main(String[] args) throws IOException{
		
		Application app = new Application();
		
	}
}
