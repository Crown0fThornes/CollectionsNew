package groups;

public class BSTMap<K extends Comparable<K>, V> {

	private TreeNode root = null;
	
	private final TreeNode BOUND_MARKER = null;
	
	private int size = 0;
	
	private class TreeNode {
		TreeNode leftChild = BOUND_MARKER;
		TreeNode rightChild = BOUND_MARKER;
		Pair<K,V> e;
	}
	
	public BSTMap() {
		// TODO Auto-generated constructor stub
	}
	
	public void put(K key, V value) {
		
		if (this.contains(key)) throw new IllegalArgumentException("Duplicate Key: " + key);
		
		TreeNode insert = new TreeNode();
		insert.e = new Pair<>(key, value);
		
		TreeNode cur = root;
		
		if (this.size == 0) {
			root = insert;
			return;
		}
		
		while (true) {
			if (cur.e.key.compareTo(key) > 0) {
				if (cur.leftChild != this.BOUND_MARKER) {
					cur = cur.leftChild;
				} else {
					cur.leftChild = insert;
					break;
				}
			}
			
			else {
				if (cur.rightChild != this.BOUND_MARKER) {
					cur = cur.rightChild;
				} else {
					cur.rightChild = insert;
					break;
				}
			}
			
		}
		
		this.balance();
	}
	
	public boolean contains(K target) {
		
		TreeNode cur = root;
		
		while (true) {
			
			if(cur == this.BOUND_MARKER) {
				return false;
			}
			
			K rootK = this.root.e.key;
			
			if(rootK.equals(target)) {
				return true;
			}
			
			if (target.compareTo(rootK) < 0) cur = root.leftChild;
			if (target.compareTo(rootK) > 0) cur = root.rightChild;
			
			
		}
	}
	
	public V get(K target) throws Exception {
		
		if (!this.contains(target)) throw new Exception();
		
		TreeNode cur = root;
		
		while (true) {
			
			if(cur == this.BOUND_MARKER) {
				throw new Exception();
			}
			
			K rootK = this.root.e.key;
			
			if(rootK.equals(target)) {
				return cur.e.value;
			}
			
			if (target.compareTo(rootK) < 0) cur = root.leftChild;
			if (target.compareTo(rootK) > 0) cur = root.rightChild;
			
			
		}
	}
	
	public V getOrElseNull(K target) {
		
		TreeNode cur = root;
		
		while (true) {
			
			if(cur == this.BOUND_MARKER) {
				return null;
			}
			
			K rootK = this.root.e.key;
			
			if(rootK.equals(target)) {
				return cur.e.value;
			}
			
			if (target.compareTo(rootK) < 0) cur = root.leftChild;
			if (target.compareTo(rootK) > 0) cur = root.rightChild;
			
			
		}
	}
	
	public void remove(K target) {
		if (!this.contains(target)) throw new IllegalArgumentException();
		
		TreeNode bottomLeft = root;
		while (bottomLeft.leftChild != BOUND_MARKER) bottomLeft = bottomLeft.rightChild;
		
		
		
	}
	
	public void siftDown() {
		
	}
	
	public int size() {
		return this.size;
	}
	
	private int height(TreeNode root) {
		if (root == BOUND_MARKER) return 0;
	
		int lHeight = height(root.leftChild) + 1;
		int rHeight = height(root.rightChild) + 1;
		
		if(lHeight >= rHeight) return lHeight;
		else return rHeight;
	}
	
	private void balance() {
		
		int lHeight = height(this.root.leftChild);
		int rHeight = height(this.root.rightChild);
		
		if (lHeight - rHeight >= 2) {
			
			TreeNode lChild = this.root.leftChild;
			this.root.leftChild = lChild.rightChild;
			lChild.rightChild = this.root;
			this.root = lChild;
			
		} else if (rHeight - lHeight >= 2) {
			
			TreeNode rChild = this.root.rightChild;
			this.root.rightChild = rChild.leftChild;
			rChild.leftChild = this.root;
			this.root = rChild;
			
		}
		
	}

	
	

}
