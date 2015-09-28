
public class Node<Type extends Comparable<Type>> implements Comparable<Type>{
	Type element ;
	Node<Type> left;
	Node<Type> right;
	int height;

	public Node(Type element){
		this.element=element;
		left=null;
		right=null;
	}
	
	public Node(Type element , Node<Type> left , Node<Type> right){
		this.element=element;
		this.left=left;
		this.right=right;
		height =0;
	}
	
	public int compareTo(Type element){
		return this.element.compareTo(element);
	}

}
