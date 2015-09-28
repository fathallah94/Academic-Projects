import java.util.EmptyStackException;

public class Stack implements MyLinkedList {
	private Node top;
	private int size;
	
	public Stack(){
		top=null;
		size=0;
	}
	@Override
	public int size(){
		return size;
	}
	@Override
	public boolean isEmpty(){
		if(size==0)
			return true;
		return false;
	}
	@Override
	public void push(Object element){
		Node v= new Node(element,top);
		top=v;
		size++;
	}
	@Override
	public Object pop() throws EmptyStackException {
		if(isEmpty())
			throw new EmptyStackException();
		Object temp=top.getElement();
		top=top.getNext();
		size--;
		return temp;
	}
	@Override
	public Object peek() throws EmptyStackException {
		if(isEmpty())
			throw new EmptyStackException();
		return top.getElement();
	}

}
