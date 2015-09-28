


public class SLinkedList implements MyLinkedList{
	private Node head;
	private int size;
	public SLinkedList(){
		head=null;
		size=0;
	}
	/////////
	@Override
	public void add(Object element){
		if(size==0){
			Node newNode= new Node(element,null);
			head=newNode;
			size++;			
		}
		else{
			Node temp=head;
			while(temp.getNext()!=null){
				temp=temp.getNext();
			}
			Node newNode= new Node (element,null);
			temp.setNext(newNode);
			size++;
			
		}
	}
	///////
	
	@Override
	public void add(int index , Object element ) throws IllegalAccessError  {
		if(index>size || index<0){
			throw new IllegalAccessError("This index is out of boundary.");
		}
		else if(index ==0){
			Node newNode =  new Node(element,head);
			head=newNode;
			size++;
		}
		else {
			Node temp=head;
			for(int i=0;i<index-1;++i){
				temp=temp.getNext();
			}
			Node newNode = new Node(element,temp.getNext());
			temp.setNext(newNode);
			size++;
		}
	}
	/////
	@Override
	 public Object get(int index)throws IllegalAccessError {
		 if(index>=size || index<0){
				throw new IllegalAccessError("This index is out of boundary.");
		}
		 
		 Node temp=head;
		 for(int i=0;i<index;++i){
			 temp=temp.getNext();
		 }
		 return temp.getElement();
	 }
	//////
	@Override
	 public void set(int index, Object element)throws IllegalAccessError {
		 if(index>=size || index<0){
				throw new IllegalAccessError("This index is out of boundary.");
		}
		 Node temp=head;
		 for(int i=0;i<index;++i){
			 temp=temp.getNext();
		 }
		 temp.setElement(element);
	 }
	 /////
	@Override
	 public void clear(){
		 if(head!=null){
		 head.setNext(null);
		 head=null;
		 size=0;
		 }
	 }
	 ////
	@Override
	 public boolean isEmpty(){
		 if(size==0)
			 return true;
		 else
			 return false;
	 }
	 /////
	@Override
	 public void remove(int index)throws IllegalAccessError {
		 if(index>=size || index<0){
				throw new IllegalAccessError("This index is out of boundary.");
		}
		 if(index==0){
			 Node temp = head.getNext();
			 head.setNext(null);
			 head=temp;
		 }
		 else{
		Node temp=head;
		for(int i=0;i<index-1;++i)
			temp=temp.getNext();
		temp.setNext(temp.getNext().getNext());
		size--;
		}
	 }
	/////
	@Override
	 public int size(){
		 return size;
	 }
	 /////
	@Override
	 public boolean contains (Object o){
		 Node temp=head;
		 int i;
		 
		 for(i=0;i<=size && temp!=null ;++i){
			 if(temp.getElement()==o){
				return true;
			 }
			 temp=temp.getNext();
		 }
		 return false;
	 }
	 ////
	 @Override
	 public SLinkedList sublist (int fromIndex, int toIndex) throws IllegalAccessError {
		 if(fromIndex >=size || fromIndex<0 || toIndex>=size || toIndex<0)
			 throw new IllegalAccessError("These indeces are out of boundary.");
		 if(fromIndex>toIndex)
			 throw new IllegalAccessError("Invalid Indeces.");
		 else{
		 SLinkedList subList = new SLinkedList();
		 Node temp=head;
		 for(int i=0;i<fromIndex;++i)
			 temp=temp.getNext();
		 for(int i=fromIndex;i<=toIndex;++i){
			 subList.add(temp.getElement());
			 temp=temp.getNext();
		 }
		 return subList;
		 }
			 
	 }
	 /////
	 
	 public int getCoof(int index)throws IllegalAccessError {
		 if(index>=size || index<0){
				throw new IllegalAccessError("This index is out of boundary.");
		}
		 
		 Node temp=head;
		 for(int i=0;i<index;++i){
			 temp=temp.getNext();
		 }
		 return temp.getCoof();
	 }
	 ////
	 public int getExp(int index)throws IllegalAccessError {
		 if(index>=size || index<0){
				throw new IllegalAccessError("This index is out of boundary.");
		}
		 
		 Node temp=head;
		 for(int i=0;i<index;++i){
			 temp=temp.getNext();
		 }
		 return temp.getExp();
	 }
	/*public static void main(String[] args){
		 SLinkedList list= new SLinkedList();
		 
		 list.add(0);
		 list.add(1);
		 list.add(2);
		 list.add(4);
		 list.add(3,3);
		 list.add(5,5);
		 System.out.println(list.get(5));
		

	 }*/
}
