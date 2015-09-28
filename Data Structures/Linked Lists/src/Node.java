
public class Node {
	private Object element;
	private Node next;
	private int coof,exp;
	public Node(Object element , Node next){
		this.element=element;
		String str=element.toString();
		String[] arr=str.split(",");
		coof=Integer.parseInt(arr[0]);
		exp=Integer.parseInt(arr[1]);
		this.next=next;
	}
	public Object getElement(){
		return element;
	}
	public Node getNext(){
		return next;
	}
	public int getCoof(){
		return coof;
	}
	public int getExp(){
		return exp;
	}
	public void setElement(Object newElement){
		element=newElement;
		String str=newElement.toString();
		String[] arr=str.split(",");
		coof=Integer.parseInt(arr[0]);
		exp=Integer.parseInt(arr[1]);
		
	}
	public void setNext(Node newNext){
		next=newNext;
	}
}
