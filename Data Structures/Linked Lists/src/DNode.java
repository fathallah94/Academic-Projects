
public class DNode {
	private Object element;
	private DNode next,previous;
	private int coof,exp;
	public DNode(Object element , DNode previous , DNode next){
		this.element=element;
		this.next=next;
		this.previous=previous;
	}
	public Object getElement(){
		return element;
	}
	public DNode getNext(){
		return next;
	}
	public DNode getPrevious(){
		return previous;
	}
	public void setElement(Object newElement){
		element=newElement;
	}
	public void setNext(DNode newNext){
		next=newNext;
	}
	public void setPrevious(DNode newPrevious){
		previous=newPrevious;
	}

}
