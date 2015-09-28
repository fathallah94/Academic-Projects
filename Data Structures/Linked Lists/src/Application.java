import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Application {
	SLinkedList A = new SLinkedList();
	SLinkedList B= new SLinkedList();
	SLinkedList C = new SLinkedList();
	SLinkedList R = new SLinkedList();
	BufferedReader input = new BufferedReader (new InputStreamReader (System.in));
	
	public Application() throws IOException{
		menu();
	}
	////
	public void setVariable() throws IOException{
		System.out.println("Insert the variable name : A,B or C");
		char choice=input.readLine().charAt(0);
		if(choice=='A'){
			A.clear();
			System.out.println("Insert the polynomial terms in the form:coeff1,exponent1 coeff2,exponent2 ..");
			String[] arr=input.readLine().split(" ");
			for(int i=0;i<arr.length;i++)
				A.add(arr[i]);
		}
		else if(choice=='B'){
			B.clear();
			System.out.println("Insert the polynomial terms in the form:coeff1,exponent1 coeff2,exponent2 ..");
			String[] arr=input.readLine().split(" ");
			for(int i=0;i<arr.length;i++)
				B.add(arr[i]);
		}
		else if(choice=='C'){
			C.clear();
			System.out.println("Insert the polynomial terms in the form:coeff1,exponent1 coeff2,exponent2 ..");
			String[] arr=input.readLine().split(" ");
			for(int i=0;i<arr.length;i++)
				C.add(arr[i]);
		}
	}
	////
	public void printVariable() throws IOException{
		System.out.println("Insert the variable name : A,B,C or R");
		char choice=input.readLine().charAt(0);
		if(choice=='A'){
				if(A.isEmpty())
					System.out.println("The polynomial isnot set.");
				else{
					System.out.print( A.getCoof(0) + "x^" + A.getExp(0)) ;

					for(int i=1;i<A.size();i++)
						System.out.print("+" + A.getCoof(i) + "x^" + A.getExp(i)) ;
					System.out.println("");
				}
		}
		else if(choice=='B'){
			if(B.isEmpty())
				System.out.println("The polynomial isnot set.");
			else{
				System.out.print( B.getCoof(0) + "x^" + B.getExp(0)) ;

				for(int i=1;i<B.size();i++)
					System.out.print("+" + B.getCoof(i) + "x^" + B.getExp(i)) ;
				System.out.println("");
			}
		}
		else if(choice=='C'){
			if(C.isEmpty())
				System.out.println("The polynomial isnot set.");
			else{
				System.out.print( C.getCoof(0) + "x^" + C.getExp(0)) ;

				for(int i=1;i<C.size();i++)
					System.out.print("+" + C.getCoof(i) + "x^" + C.getExp(i)) ;
				System.out.println("");
			}
		}
		else if(choice=='R'){
			if(R.isEmpty())
				System.out.println("The polynomial isnot set.");
			else{
				System.out.print( R.getCoof(0) + "x^" + R.getExp(0)) ;

				for(int i=1;i<R.size();i++)
					System.out.print("+" + R.getCoof(i) + "x^" + R.getExp(i)) ;
				System.out.println("");
			}
		}
		
	}
	////
	public void addPoly() throws IOException{
		R.clear();
		System.out.println("Enter the first operand variable name : A,B or C");
		char choice=input.readLine().charAt(0);
		while( (choice=='A'&& A.isEmpty()) || (choice=='B'&& B.isEmpty()) || (choice=='C'&& C.isEmpty()) ){
			System.out.println("Variable isnot sit\nEnter the first operand variable name : A,B or C ");
			choice=input.readLine().charAt(0);
		}
		
		System.out.println("Enter the second operand variable name : A,B or C");
		char choice2=input.readLine().charAt(0);
		while( (choice2=='A'&& A.isEmpty()) || (choice2=='B'&& B.isEmpty()) || (choice2=='C'&& C.isEmpty()) ){
			System.out.println("Variable isnot sit\nEnter the first operand variable name : A,B or C ");
			choice2=input.readLine().charAt(0);
		}
		
		if((choice=='A' && choice2=='B') || (choice=='B' && choice2=='A')){
			boolean[] a= new boolean[A.size()];
			boolean[] b=new boolean[B.size()];
			for(int i=0;i<A.size();i++){
				for(int j=0;j<B.size();j++){
					if(A.getExp(i)==B.getExp(j)){
						String element=Integer.toString(A.getCoof(i) + B.getCoof(j) ) +","+ Integer.toString(A.getExp(i));
						R.add(element);
						a[i]=true;
						b[j]=true;
					}
				}
			}
			for(int i=0;i<a.length;i++){
				if(!a[i]){
					R.add(A.get(i));
				}
			}
			for(int i=0;i<b.length;i++){
				if(!b[i])
					R.add(B.get(i));
			}
			
		}
		
		else if((choice=='A' && choice2=='C') || (choice=='C' && choice2=='A')){
			boolean[] a= new boolean[A.size()];
			boolean[] c=new boolean[C.size()];
			for(int i=0;i<A.size();i++){
				for(int j=0;j<C.size();j++){
					if(A.getExp(i)==C.getExp(j)){
						String element=Integer.toString(A.getCoof(i) + C.getCoof(j) ) +","+ Integer.toString(A.getExp(i));
						R.add(element);
						a[i]=true;
						c[j]=true;
					}
				}
			}
			for(int i=0;i<a.length;i++){
				if(!a[i]){
					R.add(A.get(i));
				}
			}
			for(int i=0;i<c.length;i++){
				if(!c[i])
					R.add(C.get(i));
			}
			
		}
		
		else if((choice=='B' && choice2=='C') || (choice=='C' && choice2=='B')){
			boolean[] b= new boolean[B.size()];
			boolean[] c=new boolean[C.size()];
			for(int i=0;i<B.size();i++){
				for(int j=0;j<C.size();j++){
					if(B.getExp(i)==C.getExp(j)){
						String element=Integer.toString(B.getCoof(i) + C.getCoof(j) ) +","+ Integer.toString(B.getExp(i));
						R.add(element);
						b[i]=true;
						c[j]=true;
					}
				}
			}
			for(int i=0;i<b.length;i++){
				if(!b[i]){
					R.add(B.get(i));
				}
			}
			for(int i=0;i<c.length;i++){
				if(!c[i])
					R.add(C.get(i));
			}
			
		}
		
		
		
	}
	////
	public void subtract() throws IOException{
		R.clear();
		System.out.println("Enter the first operand variable name : A,B or C");
		char choice=input.readLine().charAt(0);
		while( (choice=='A'&& A.isEmpty()) || (choice=='B'&& B.isEmpty()) || (choice=='C'&& C.isEmpty()) ){
			System.out.println("Variable isnot sit\nEnter the first operand variable name : A,B or C ");
			choice=input.readLine().charAt(0);
		}
		
		System.out.println("Enter the second operand variable name : A,B or C");
		char choice2=input.readLine().charAt(0);
		while( (choice2=='A'&& A.isEmpty()) || (choice2=='B'&& B.isEmpty()) || (choice2=='C'&& C.isEmpty()) ){
			System.out.println("Variable isnot sit\nEnter the first operand variable name : A,B or C ");
			choice2=input.readLine().charAt(0);
		}
		
		if((choice=='A' && choice2=='B') ){
			boolean[] a= new boolean[A.size()];
			boolean[] b=new boolean[B.size()];
			for(int i=0;i<A.size();i++){
				for(int j=0;j<B.size();j++){
					if(A.getExp(i)==B.getExp(j)){
						String element=Integer.toString(A.getCoof(i) - B.getCoof(j) ) +","+ Integer.toString(A.getExp(i));
						R.add(element);
						a[i]=true;
						b[j]=true;
					}
				}
			}
			for(int i=0;i<a.length;i++){
				if(!a[i]){
					String element=Integer.toString(A.getCoof(i)) +","+ Integer.toString(A.getExp(i));
					R.add(element);
				}
			}
			for(int i=0;i<b.length;i++){
				if(!b[i]){
					String element=Integer.toString(-B.getCoof(i)) +","+ Integer.toString(B.getExp(i));
					R.add(element);
				}
			}
			
		}
		
		else if((choice=='B' && choice2=='A') ){
			boolean[] a= new boolean[A.size()];
			boolean[] b=new boolean[B.size()];
			for(int i=0;i<A.size();i++){
				for(int j=0;j<B.size();j++){
					if(A.getExp(i)==B.getExp(j)){
						String element=Integer.toString(B.getCoof(j) - A.getCoof(i) ) +","+ Integer.toString(A.getExp(i));
						R.add(element);
						a[i]=true;
						b[j]=true;
					}
				}
			}
			for(int i=0;i<a.length;i++){
				if(!a[i]){
					String element=Integer.toString(-A.getCoof(i)) +","+ Integer.toString(A.getExp(i));
					R.add(element);
				}
			}
			for(int i=0;i<b.length;i++){
				if(!b[i]){
					String element=Integer.toString(B.getCoof(i)) +","+ Integer.toString(B.getExp(i));
					R.add(element);
				}
			}
			
		}
		
		else if((choice=='A' && choice2=='C')){
			boolean[] a= new boolean[A.size()];
			boolean[] c=new boolean[C.size()];
			for(int i=0;i<A.size();i++){
				for(int j=0;j<C.size();j++){
					if(A.getExp(i)==C.getExp(j)){
						String element=Integer.toString(A.getCoof(i) - C.getCoof(j) ) +","+ Integer.toString(A.getExp(i));
						R.add(element);
						a[i]=true;
						c[j]=true;
					}
				}
			}
			for(int i=0;i<a.length;i++){
				if(!a[i]){
					R.add(A.get(i));
				}
			}
			for(int i=0;i<c.length;i++){
				if(!c[i]){
				String element=Integer.toString(-C.getCoof(i) ) +","+ Integer.toString(C.getExp(i));
				R.add(element);
				}
			}
			
		}
		else if((choice=='C' && choice2=='A')){
			boolean[] a= new boolean[A.size()];
			boolean[] c=new boolean[C.size()];
			for(int i=0;i<A.size();i++){
				for(int j=0;j<C.size();j++){
					if(A.getExp(i)==C.getExp(j)){
						String element=Integer.toString(C.getCoof(j) - A.getCoof(i) ) +","+ Integer.toString(A.getExp(i));
						R.add(element);
						a[i]=true;
						c[j]=true;
					}
				}
			}
			for(int i=0;i<a.length;i++){
				if(!a[i]){
					String element=Integer.toString(-A.getCoof(i) ) +","+ Integer.toString(A.getExp(i));
					R.add(element);
				}
			}
			for(int i=0;i<c.length;i++){
				if(!c[i]){
				String element=Integer.toString(C.getCoof(i) ) +","+ Integer.toString(C.getExp(i));
				R.add(element);
				}
			}
			
		}
		else if((choice=='B' && choice2=='C') ){
			boolean[] b= new boolean[B.size()];
			boolean[] c=new boolean[C.size()];
			for(int i=0;i<B.size();i++){
				for(int j=0;j<C.size();j++){
					if(B.getExp(i)==C.getExp(j)){
						String element=Integer.toString(B.getCoof(i) - C.getCoof(j) ) +","+ Integer.toString(B.getExp(i));
						R.add(element);
						b[i]=true;
						c[j]=true;
					}
				}
			}
			for(int i=0;i<b.length;i++){
				if(!b[i]){
					R.add(B.get(i));
				}
			}
			for(int i=0;i<c.length;i++){
				if(!c[i]){
					String element=Integer.toString(-C.getCoof(i) ) +","+ Integer.toString(C.getExp(i));
					R.add(element);
				}
			}
			
		}
		else if((choice=='C' && choice2=='B') ){
			boolean[] b= new boolean[B.size()];
			boolean[] c=new boolean[C.size()];
			for(int i=0;i<B.size();i++){
				for(int j=0;j<C.size();j++){
					if(B.getExp(i)==C.getExp(j)){
						String element=Integer.toString(C.getCoof(j) - B.getCoof(i) ) +","+ Integer.toString(B.getExp(i));
						R.add(element);
						b[i]=true;
						c[j]=true;
					}
				}
			}
			for(int i=0;i<b.length;i++){
				if(!b[i]){
					String element=Integer.toString(-B.getCoof(i) ) +","+ Integer.toString(B.getExp(i));
					R.add(element);
				}
			}
			for(int i=0;i<c.length;i++){
				if(!c[i]){
					String element=Integer.toString(C.getCoof(i) ) +","+ Integer.toString(C.getExp(i));
					R.add(element);
				}
			}
			
		}
		
		
		
	}
	////
	public void multiply() throws IOException{
		R.clear();
		System.out.println("Enter the first operand variable name : A,B or C");
		char choice=input.readLine().charAt(0);
		while( (choice=='A'&& A.isEmpty()) || (choice=='B'&& B.isEmpty()) || (choice=='C'&& C.isEmpty()) ){
			System.out.println("Variable isnot sit\nEnter the first operand variable name : A,B or C ");
			choice=input.readLine().charAt(0);
		}
		
		System.out.println("Enter the second operand variable name : A,B or C");
		char choice2=input.readLine().charAt(0);
		while( (choice2=='A'&& A.isEmpty()) || (choice2=='B'&& B.isEmpty()) || (choice2=='C'&& C.isEmpty()) ){
			System.out.println("Variable isnot sit\nEnter the first operand variable name : A,B or C ");
			choice2=input.readLine().charAt(0);
		}
		
		if((choice=='A' && choice2=='B') || (choice=='B' && choice2=='A')){
			boolean flag=false;
			for(int i=0;i<A.size();i++){
				for(int j=0;j<B.size();j++){
						flag=false;
						int coof=A.getCoof(i) * B.getCoof(j);
						int exp=A.getExp(i) + B.getExp(j);
						for(int k=0;k<R.size();k++){
							if(R.getExp(k) == exp){
								coof+=R.getCoof(k);
								String element=Integer.toString(coof) +","+ Integer.toString(exp);
								R.set(k,element);
								flag=true;
								break;
							}
						}
						if(!flag){
							String element=Integer.toString(coof) +","+ Integer.toString(exp);	
							R.add(element);
						}
				}
			}			
		}
		
		else if((choice=='A' && choice2=='C') || (choice=='C' && choice2=='A')){
			boolean flag=false;
			for(int i=0;i<A.size();i++){
				for(int j=0;j<C.size();j++){
						flag=false;
						int coof=A.getCoof(i) * C.getCoof(j);
						int exp=A.getExp(i) + C.getExp(j);
						for(int k=0;k<R.size();k++){
							if(R.getExp(k) == exp){
								coof+=R.getCoof(k);
								String element=Integer.toString(coof) +","+ Integer.toString(exp);
								R.set(k,element);
								flag=true;
								break;
							}
						}
						if(!flag){
							String element=Integer.toString(coof) +","+ Integer.toString(exp);	
							R.add(element);
						}
				}
			}			
		}
		else if((choice=='B' && choice2=='C') || (choice=='C' && choice2=='B')){
			boolean flag=false;
			for(int i=0;i<B.size();i++){
				for(int j=0;j<C.size();j++){
						flag=false;
						int coof=B.getCoof(i) * C.getCoof(j);
						int exp=B.getExp(i) + C.getExp(j);
						for(int k=0;k<R.size();k++){
							if(R.getExp(k) == exp){
								coof+=R.getCoof(k);
								String element=Integer.toString(coof) +","+ Integer.toString(exp);
								R.set(k,element);
								flag=true;
								break;
							}
						}
						if(!flag){
							String element=Integer.toString(coof) +","+ Integer.toString(exp);	
							R.add(element);
						}
				}
			}			
		}
		
		
	}
	////
	public void clearPoly() throws IOException{
		System.out.println("Enter the polynomial variable you want to clear : A,B,C or R");
		char choice = input.readLine().charAt(0);
		if(choice =='A')
			A.clear();
		else if(choice =='B')
			B.clear();
		else if(choice =='C')
			C.clear();
		else if (choice == 'R')
			R.clear();
	}
	////
	public void eval() throws IOException{
		System.out.println("Enter the polynomial variable you want to clear : A,B or C");
		char choice = input.readLine().charAt(0);
		double a=new Double (input.readLine());
		double result=0;
		if(choice =='A'){
			if(A.isEmpty())
				System.out.println("The polynomial isnot set.");
			else{
			for(int i=0;i<A.size();i++){
				result+= A.getCoof(i) * Math.pow(a,A.getExp(i));
			}
			System.out.println(result);

			}
		}
		else if(choice =='B'){
			if(B.isEmpty())
				System.out.println("The polynomial isnot set.");
			else{
			for(int i=0;i<B.size();i++){
				result+= B.getCoof(i) * Math.pow(a,B.getExp(i));
			}	
			System.out.println(result);

			}
		}
		else if(choice =='C'){
			if(C.isEmpty())
				System.out.println("The polynomial isnot set.");
			else{
			for(int i=0;i<C.size();i++){
				result+= C.getCoof(i) * Math.pow(a,C.getExp(i));
			}
			System.out.println(result);

			}
		}
	}
	////
	public  void menu() throws IOException{
		char choice;
		while(true){
			System.out.print("Please choose an action\n-----------------------\n1- Set a polynomial variable\n2- Print the value of a polynomial variable\n3- Add two polynomials\n4- Subtract two polynomials\n5- Multiply two polynomials\n6- Evaluate a polynomial at some point\n7- Clear a polynomial variable\n");
			choice=input.readLine().charAt(0);
			if(choice=='1'){
				setVariable();
			}
			else if(choice=='2'){
				printVariable();
			}
			else if(choice =='3'){
				addPoly();
			}
			else if(choice=='4'){
				subtract();
			}
			else if(choice=='5')
				multiply();
			else if(choice =='6')
				eval();
			else if(choice =='7')
				clearPoly();
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		Application newApp = new Application();
		
	}
	

}
