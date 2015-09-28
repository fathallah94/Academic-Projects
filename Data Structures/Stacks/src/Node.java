
public class Node {
	private Object element;
	private Node next;
	public Node(Object obj , Node v){
		element=obj;
		next=v;
	}
	public Object getElement(){
		return element;
	}
	public Node getNext(){
		return next;
	}
	public void setElemenet(Object newElement){
		element=newElement;
	}
	public void setNext(Node newNext){
		next=newNext;
	}
}
