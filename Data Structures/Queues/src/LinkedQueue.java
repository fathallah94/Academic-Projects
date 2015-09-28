
public class LinkedQueue implements MyQueue{
	private int size;
	private Node head,tail;
	public LinkedQueue(){
		size=0;
		head=null;
		tail=null;
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
	public void enqueue(Object element){
		Node v = new Node(element,null);
		if(size==0){
			head=v;
			tail=v;
		}
		else{
			tail.setNext(v);
			tail=v;
		}
		size++;
	}
	@Override
	public Object dequeue()throws NullPointerException{
		if(isEmpty())
			throw new NullPointerException("Queue is empty.");
		Object temp=head.getElement();
		head=head.getNext();
		size--;
		if(size==0)
			tail=null;
		return temp;
	}
}
