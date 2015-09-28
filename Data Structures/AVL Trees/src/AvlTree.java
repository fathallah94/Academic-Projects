
public class AvlTree<Type extends Comparable<Type>>{
	Node<Type> root ;
	////////////////////////////// Height //////////////////////////////////////////////////////////////
	private int getHeight(Node<Type> node){
		if(node==null)
			return -1;
		return node.height;
	}
	public int treeHeight(){
		return getHeight(root);
	}
	/////////////////////////////search///////////////////////////////////////////////////////////////
	public boolean search(Type element){
		Node<Type> temp = this.root;
		while(temp!=null){
			int res = temp.compareTo(element);
			if(res<0)
				temp=temp.right;
			else if(res>0)
				temp=temp.left;
			else
				return true;
		}
		return false;
	}
	///////////////////////////////// Insert ///////////////////////////////////////////////////////////

	public void insert(Type element){
		this.root = insert(element , this.root);		
	}
	
	private Node<Type> insert(Type element , Node<Type> t){
		if(t==null)
			return new Node<Type>(element,null,null);
		
		int res = t.compareTo(element);
		if(res<0)
			t.right = insert(element, t.right);
		else if(res>0)
			t.left = insert(element,t.left);
		
		return balance(t);
	}
	///////////////////////////delete/////////////////////////////////////////////////////////////////

	public void delete(Type element){
		this.root = delete(element,this.root);
	}
	
	private Node<Type> delete(Type element, Node<Type> t){
		if(t==null)
			return t;
		
		int res = t.compareTo(element);
		if(res>0)
			t.left = delete(element,t.left);
		else if(res<0)
			t.right = delete(element,t.right);
		else if(t.left!=null && t.right!=null){
			t.element = successor(t).element;
			t.right = delete(t.element,t.right);
		}
		else{
			if(t.left!=null)
				t = t.left;
			else
				t=t.right;
		}
		return balance(t);
	}
	///////////////////////////balance/////////////////////////////////////////////////////////////////

	private Node<Type> balance(Node<Type> t){
		if(t==null)
			return t;
		
		if(getHeight(t.left)-getHeight(t.right)>1){
			if(getHeight(t.left.left) >= getHeight(t.left.right))
				t = rotateLeft(t);
			else
				t= doubleRotateLeft(t);
		}
		else if(getHeight(t.right)-getHeight(t.left)>1){
			if(getHeight(t.right.right) >= getHeight(t.right.left))
				t = rotateRight(t);
			else
				t= doubleRotateRight(t);
		}
		
		t.height = Math.max(getHeight(t.left),  getHeight(t.right))+1;
		return t;
	}
	///////////////////////////rotations/////////////////////////////////////////////////////////////////

	private Node<Type> rotateLeft(Node<Type> t){
		Node<Type> t2 = t.left;
		t.left = t2.right;
		t2.right = t;
		
		t.height = Math.max(getHeight(t.left),  getHeight(t.right))+1;
		t2.height = Math.max(getHeight(t2.left),  getHeight(t2.right))+1;
		return t2;
	}
	private Node<Type> rotateRight(Node<Type> t){
		Node<Type> t2 = t.right;
		t.right = t2.left;
		t2.left = t;
		
		t.height = Math.max(getHeight(t.left),  getHeight(t.right))+1;
		t2.height = Math.max(getHeight(t2.left),  getHeight(t2.right))+1;
		return t2;
	}
	
	private Node<Type> doubleRotateLeft(Node<Type> t){
		t.left = rotateRight(t.left);
		return rotateLeft(t);
	}
	
	private Node<Type> doubleRotateRight(Node<Type> t){
		t.right = rotateLeft(t.right);
		return rotateRight(t);
	}
	////////////////////////successor////////////////////////////////////////////////////////////////////

	private Node<Type> successor(Node<Type> t){
		Node<Type> temp = t.right;
		while(temp.left!=null){
			temp = temp.left;
		}
		return temp ;
	}
	
	public static void main(String[] args)throws Exception{
		AvlTree<Integer> t = new AvlTree<Integer>();
		
		t.insert(1);
		t.insert(2);
		t.insert(3);
		t.insert(4);
		t.insert(5);

		System.out.println(t.search(1));
		System.out.println(t.search(2));
		System.out.println(t.search(3));
		System.out.println(t.search(4));
		System.out.println(t.search(5));
		System.out.println(t.search(6));
		System.out.println(t.search(0));

		System.out.println("===========================");
		t.delete(1);
		System.out.println(t.search(1));
		System.out.println(t.search(2));
		System.out.println(t.search(3));
		System.out.println(t.search(4));
		System.out.println(t.search(5));
		System.out.println(t.search(6));
		System.out.println(t.search(0));
		
		System.out.println("===========================");

		t.delete(2);
		System.out.println(t.search(1));
		System.out.println(t.search(2));
		System.out.println(t.search(3));
		System.out.println(t.search(4));
		System.out.println(t.search(5));
		System.out.println(t.search(6));
		System.out.println(t.search(0));
		
		System.out.println("===========================");

		t.delete(3);
		System.out.println(t.search(1));
		System.out.println(t.search(2));
		System.out.println(t.search(3));
		System.out.println(t.search(4));
		System.out.println(t.search(5));
		System.out.println(t.search(6));
		System.out.println(t.search(0));
		
		System.out.println("===========================");

		t.delete(4);
		System.out.println(t.search(1));
		System.out.println(t.search(2));
		System.out.println(t.search(3));
		System.out.println(t.search(4));
		System.out.println(t.search(5));
		System.out.println(t.search(6));
		System.out.println(t.search(0));
		
		System.out.println("===========================");

		t.delete(5);
		System.out.println(t.search(1));
		System.out.println(t.search(2));
		System.out.println(t.search(3));
		System.out.println(t.search(4));
		System.out.println(t.search(5));
		System.out.println(t.search(6));
		System.out.println(t.search(0));
	
		System.out.println("===========================");
		System.out.println(t.treeHeight());
		t.insert(0);
		System.out.println(t.search(1));
		System.out.println(t.search(2));
		System.out.println(t.search(3));
		System.out.println(t.search(4));
		System.out.println(t.search(5));
		System.out.println(t.search(6));
		System.out.println(t.search(0));
	
		System.out.println("===========================");
		t.delete(6);
		System.out.println(t.search(6));
		System.out.println(t.search(0));
		t.insert(6);
		System.out.println(t.search(6));
		t.delete(0);
		System.out.println(t.search(0));
	}
}
