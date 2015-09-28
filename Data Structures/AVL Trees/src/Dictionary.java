import java.io.BufferedReader;
import java.io.FileReader;


public class Dictionary {
	private int size;
	private AvlTree<String> tree;
	private BufferedReader br ;
	
	public Dictionary(){
		size=0;
		tree = new AvlTree<String>();
	}
	
	public void printSize(){
		System.out.println("Dictionary size = "+size);
		System.out.println();
	}
	
	public void printHeight(){
		System.out.println("Tree height = "+(tree.treeHeight()));
		System.out.println();
	}
	
	public void insert(String str){
		if(tree.search(str))
			System.out.println("ERROR : word already in the dictionary!\n");
		else{
			tree.insert(str);
			size++;
		}
	}
	
	public void loadDictionary()throws Exception{
		br = new BufferedReader(new FileReader("dictionary.txt"));
		while(br.ready()){
			insert(br.readLine());
		}
	}
	
	public void lookUp(String str){
		if(tree.search(str))
			System.out.println("YES");
		else
			System.out.println("NO");
	}
	
	
	public void BatchLookups()throws Exception{
		br = new BufferedReader(new FileReader("queries.txt"));
		int num = 0;
		while(br.ready()){
			String str = br.readLine();
			if(tree.search(str)){
				num++;
				System.out.println(str+" : YES.");
			}
			else
				System.out.println(str+" : NO.");
		}
		System.out.println("total number of found words = "+num);
		System.out.println();
	}
	

	public void remove(String str){
		if(!tree.search(str))
			System.out.println("ERROR : word isnot found in the dictionary!\n");
		else{
			tree.delete(str);
			size--;
		}
	}
	
	public void BatchDeletions()throws Exception{
		br = new BufferedReader(new FileReader("deletions.txt"));
		while(br.ready()){
			remove(br.readLine());
		}
	}
	
}
