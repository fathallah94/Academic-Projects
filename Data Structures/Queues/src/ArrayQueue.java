
public class ArrayQueue implements MyQueue {
	private int front,rear,size,n;
	private Object[] arr;
	public ArrayQueue(int n){
		this.n=n;
		front=0;
		rear=0;
		size=0;
		arr= new Object[n];
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
	public void enqueue(Object element)throws IllegalStateException{
		if(size==n)
			throw new IllegalStateException("Queue is full.");
		arr[rear]=element;
		if(rear==n-1)
			rear=0;
		else
			rear++;
		size++;
		
	}
	@Override
	public Object dequeue()throws NullPointerException{
		if(size==0)
			throw new NullPointerException("Queue is empty.");
		Object temp=arr[front];
		arr[front]=null;
		if(front==n-1)
			front=0;
		else
			front++;
		size--;
		return temp;
	}
}
