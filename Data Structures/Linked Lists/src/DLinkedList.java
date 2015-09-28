

public class DLinkedList implements MyLinkedList {
	private int size;
	private DNode header,trailer;
	public DLinkedList(){
		size=0;
		header = new DNode(null,null,null);
		trailer = new DNode(null,header,null);
		header.setNext(trailer);
	}
	////
	@Override
	 public void add(Object element){
		 DNode newNode = new DNode (element,trailer.getPrevious(),trailer);
		 trailer.getPrevious().setNext(newNode);
		 trailer.setPrevious(newNode);
		 size++;
	 }
	 ////
	@Override
	 public void add(int index, Object element) throws IllegalAccessError  {
		 if(index>size || index<0){
			throw new IllegalAccessError("This index is out of boundary.");
		}
		 if(index==0){
			 DNode newNode = new DNode (element,header,trailer);
		 }
		 else{
		 DNode temp = header.getNext();
		 for(int i=0;i<index-1;++i){
			 temp=temp.getNext();
		 }
		 DNode newNode=new DNode (element,temp,temp.getNext());
		 temp.getNext().setPrevious(newNode);
		 temp.setNext(newNode);		
		 }
		 size++;
		 
	 }
	 ////
	@Override
	 public Object get(int index) throws IllegalAccessError  {
		 if(index>=size || index<0){
			throw new IllegalAccessError("This index is out of boundary.");
		}
		 DNode temp= header.getNext();
		 for(int i=0;i<index;++i)
			 temp=temp.getNext();
		 return temp.getElement();
		 
	 }
	 ////
	@Override
	 public void set(int index, Object element)throws IllegalAccessError {
		 if(index>=size || index<0){
				throw new IllegalAccessError("This index is out of boundary.");
		}
		DNode temp = header.getNext();
		for(int i=0;i<index;++i)
			temp=temp.getNext();
		temp.setElement(element);
	}
	////
	@Override
	 public void clear(){
		 if(size!=0){
			 header.setNext(trailer);
			 header.setPrevious(null);
			 trailer.setNext(null);
			 trailer.setPrevious(header);
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
	 ////
	@Override
	 public void remove(int index)throws IllegalAccessError {
		 if(index>=size || index<0){
				throw new IllegalAccessError("This index is out of boundary.");
		}
		 DNode temp=header.getNext();
		 for(int i=0;i<index;++i)
			 temp=temp.getNext();
		 temp.getPrevious().setNext(temp.getNext());
		 temp.getNext().setPrevious(temp.getPrevious());
		 temp.setNext(null);
		 temp.setPrevious(null);
		 temp=null;
		 size--;
	 }
	 ////
	@Override
	 public int size(){
		 return size;
	 }
	 ////
	@Override
	 public DLinkedList sublist(int fromIndex, int toIndex) throws IllegalAccessError {
		 if(fromIndex >=size || fromIndex<0 || toIndex>=size || toIndex<0)
			 throw new IllegalAccessError("These indeces are out of boundary.");
		 if(fromIndex>toIndex)
			 throw new IllegalAccessError("Invalid Indeces.");
		 else{
		 DLinkedList subList = new DLinkedList();
		 DNode temp=header.getNext();
		 for(int i=0;i<fromIndex;++i)
			 temp=temp.getNext();
		 for(int i=fromIndex;i<=toIndex;++i){
			 subList.add(temp.getElement());
			 temp=temp.getNext();
		 }
		 return subList;
		 }
			 
	 }
	 ////
	@Override
	 public boolean contains(Object o){
		 DNode temp=header.getNext();
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
	 
	 

}