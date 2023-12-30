package groups;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BSTMap<K extends Comparable<K>, V> {

	private TreeNode root = null;

	private final TreeNode BOUND_MARKER = null;

	private int size = 0;

	private HashSet<K> keySet = new HashSet<>();

	// The nodes of <this> are doubly linked; with each a pointer to its children
	// and its parent
	private class TreeNode {
		TreeNode leftChild = BOUND_MARKER;
		TreeNode rightChild = BOUND_MARKER;
		TreeNode parent = BOUND_MARKER;
		Pair<K, V> e;

		public String toString() {
			String left = leftChild == null ? "null" : leftChild.e.toString();
			String right = rightChild == null ? "null" : rightChild.e.toString();
//			return e.toString();
			return e + "[l: " + left + ", r: " + right + "]";
		}
	}

	public BSTMap() {
		// TODO Auto-generated constructor stub
	}

	public void put(K key, V value) {

		if (this.contains(key))
			throw new IllegalArgumentException("Duplicate Key: " + key);
		else
			this.keySet.add(key);

		TreeNode insert = new TreeNode();
		insert.e = new Pair<>(key, value);

		TreeNode cur = root;

		if (this.size == 0) {
			this.root = insert;
			this.size++;
			return;
		}

		while (true) {
			if (key.compareTo(cur.e.key) < 0) {
//				System.out.println(key + " is less than " + cur.e.key);
				if (cur.leftChild != this.BOUND_MARKER) {
//					System.out.println("Moving to " + cur.leftChild);
					cur = cur.leftChild;
				} else {
					cur.leftChild = insert;
					insert.parent = cur;
					break;
				}
			}

			else {
//				System.out.println(key + " is greater than " + cur.e.key);
				if (cur.rightChild != this.BOUND_MARKER) {
//					System.out.println("Moving to " + cur.rightChild);
					cur = cur.rightChild;
				} else {
					cur.rightChild = insert;
					insert.parent = cur;
					break;
				}
			}

		}

		this.size++;

//		this.balance();
	}

	public boolean contains(K target) {

		return this.keySet.contains(target);
	}

	public V get(K target) throws Exception {

		if (!this.contains(target))
			throw new Exception();

		TreeNode cur = root;

		while (true) {

			if (cur == this.BOUND_MARKER) {
				throw new Exception();
			}

			K rootK = this.root.e.key;

			if (rootK.equals(target)) {
				return cur.e.value;
			}

			if (target.compareTo(rootK) < 0)
				cur = root.leftChild;
			if (target.compareTo(rootK) > 0)
				cur = root.rightChild;

		}
	}

	public V getOrElseNull(K target) {

		TreeNode cur = root;

		while (true) {

			if (cur == this.BOUND_MARKER) {
				return null;
			}

			K rootK = this.root.e.key;

			if (rootK.equals(target)) {
				return cur.e.value;
			}

			if (target.compareTo(rootK) < 0)
				cur = root.leftChild;
			if (target.compareTo(rootK) > 0)
				cur = root.rightChild;

		}
	}

	public Pair<K, V> remove(K target) {
		if (!this.contains(target))
			throw new IllegalArgumentException();

		// Find node to be removed
		TreeNode cur = this.root;

		while (true) {
			// Current node is the node to be removed
			if (cur.e.key.equals(target))
				break;

			// Continue searching for target node

			if (cur.e.key.compareTo(target) > 0) {
				// Go Left
				cur = cur.leftChild;
			} else {
				// Go Right
				cur = cur.rightChild;
			}
		}

		// Saving the current e value to return.
		Pair<K, V> e = cur.e;

		/*
		 * Current node is target node.
		 * 
		 * Next step is too remove the target node and fix the BST
		 */

		// Case where removed node has no children.
		if (cur.rightChild == this.BOUND_MARKER && cur.leftChild == this.BOUND_MARKER) {
			if (cur.parent.rightChild == cur) {
				cur.parent.rightChild = this.BOUND_MARKER;
			} else {
				cur.parent.leftChild = this.BOUND_MARKER;
			}
			return e;
		}

		// Case where removed node has children.
		if (height(cur.leftChild) > height(cur.rightChild)) {
			/*
			 * Taking from the left side of the tree.
			 * 
			 * We want to find the largest child on the left tree.
			 */
			TreeNode largestLeft = getLargest(cur.leftChild);

			largestLeft.parent.rightChild = largestLeft.leftChild;
			largestLeft.leftChild.parent = largestLeft.parent;
			cur.e = largestLeft.e;
		} else {
			/*
			 * Taking from the right side of the tree
			 * 
			 * We want to find the smallest child on the right tree
			 */
			TreeNode smallestRight = getSmallest(cur.rightChild);

			smallestRight.parent.leftChild = smallestRight.rightChild;
			smallestRight.rightChild.parent = smallestRight.parent;
			cur.e = smallestRight.e;
		}

		this.balance();

		return e;
	}

	public TreeNode getLargest(TreeNode root) {
		if (root.rightChild == this.BOUND_MARKER)
			return root;

		return getLargest(root.rightChild);
	}

	public TreeNode getSmallest(TreeNode root) {
		if (root.leftChild == this.BOUND_MARKER)
			return root;

		return getSmallest(root.leftChild);
	}

	public int size() {
		return this.size;
	}

	private int height(TreeNode root) {
		if (root == BOUND_MARKER)
			return 0;

		int lHeight = height(root.leftChild) + 1;
		int rHeight = height(root.rightChild) + 1;

		if (lHeight >= rHeight)
			return lHeight;
		else
			return rHeight;
	}

	private void balance() {

		int lHeight = height(this.root.leftChild);
		int rHeight = height(this.root.rightChild);

		if (lHeight - rHeight >= 2) {

			TreeNode lChild = this.root.leftChild;
			this.root.leftChild = lChild.rightChild;
			lChild.rightChild.parent = this.root;
			lChild.rightChild = this.root;
			this.root.parent = lChild;
			lChild.parent = this.BOUND_MARKER;
			this.root = lChild;

		} else if (rHeight - lHeight >= 2) {

			TreeNode rChild = this.root.rightChild;
			this.root.rightChild = rChild.leftChild;
			rChild.leftChild.parent = this.root;
			rChild.leftChild = this.root;
			this.root.parent = rChild;
			rChild.parent = this.BOUND_MARKER;
			this.root = rChild;
		}

	}

	public void printTreeLineByLine() {
		if (root == null) {
			System.out.println("Tree is empty");
			return;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			int levelSize = queue.size();

			for (int i = 0; i < levelSize; i++) {
				TreeNode current = queue.poll();
				System.out.print(current + " ");

				if (current.leftChild != BOUND_MARKER) {
					queue.add(current.leftChild);
				}

				if (current.rightChild != BOUND_MARKER) {
					queue.add(current.rightChild);
				}
			}

			System.out.println(); // Move to the next line after finishing each level
		}
	}

}
