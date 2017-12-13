package binarySearchTree;

import java.util.NoSuchElementException;
import java.util.Queue;
/*
// Implementation was taken from Sedgwick
public class RedBlackTree<K extends Comparable<K>, T> implements BinarySearchTree<K, T> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private Node root;
	
	private class Node {
		K key;
		T payload;
		Node leftChild;
		Node rightChild;
		int N;
		boolean color;
		
		Node(K key, T payload, int n, boolean color) {
			this.key = key;
			this.payload = payload;
			N = n;
			this.color = color;
		}

		@Override
		public String toString() {
			return "Node [key=" + key + ", payload=" + payload + ", N=" + N + "]";
		}
	} 
	
	private boolean isRed(Node node) {
		return (node != null && node.color == RED);
	}
	
	private int size(Node node) {
		return (node != null) ? node.N : 0;
	}
	
	private Node rotateLeft(Node node) {
		Node rightChild = node.rightChild;
		node.rightChild = rightChild.leftChild;
		rightChild.leftChild = node;
		rightChild.color = node.color;
		node.color = RED;
		rightChild.N = node.N;
		node.N = 1 + size(node.leftChild) + size(node.rightChild);
		return rightChild;
	}
	
	private Node rotateRight(Node node) {
		Node leftChild = node.leftChild;
		node.leftChild = leftChild.rightChild;
		leftChild.rightChild = node;
		leftChild.color = node.color;
		node.color = RED;
		leftChild.N = node.N;
		node.N = 1 + size(node.leftChild) + size(node.rightChild);
		return leftChild;
	}
	
	private void flipColors(Node node) {
		node.color = RED;
		node.leftChild.color = BLACK;
		node.rightChild.color = BLACK;
	}
	
	private Node insert(Node node, K key, T value) {
		if (node == null) {
			return new Node(key, value, 1, RED);
		} else {
			int compared = key.compareTo(node.key);
			
			if (compared < 0) {
				node.leftChild = insert(node.leftChild, key, value);
			} else if (compared > 0) {
				node.rightChild = insert(node.rightChild, key, value);
			} else {
				node.payload = value;
			}
			
			if (isRed(node.rightChild) && !isRed(node.leftChild)) {
				node = rotateLeft(node);
			}
			if (isRed(node.leftChild) && isRed(node.leftChild.leftChild)) {
				node = rotateRight(node);
			}
			if (isRed(node.leftChild) && isRed(node.rightChild)) {
				flipColors(node);
			}
			
			node.N = size(node.leftChild) + size(node.rightChild) + 1;
			return node;
		}
	}
	
	@Override
	public T find(K withKey) {
		Node current = root;
		
		while (current.key.compareTo(withKey) != 0) {
			
			current = (withKey.compareTo(current.key) < 0) ? 
					current.leftChild : current.rightChild;
			if (current == null) {
				return null;
			}
		}
		
		return current.payload;
	}

	@Override
	public void insert(K key, T value) {
		root = insert(root, key, value);
		root.color = BLACK;
	}
	
	private Node moveRedLeft(Node node) {
		flipColors(node);
		if (isRed(node.rightChild.leftChild)) {
			node.rightChild = rotateRight(node.rightChild);
			node = rotateLeft(node);
		}
		return node;
	}
	
	public void deleteMin() {
		if (!isRed(root.leftChild) && !isRed(root.rightChild)) {
			root.color = RED;
		}
		root = deleteMin(root);
		if (!isEmpty()) {
			root.color = BLACK;
		}
	}
	
	private Node deleteMin(Node node) {
		if (node.leftChild == null) {
			return null;
		}
		if (!isRed(node.leftChild) && !isRed(node.leftChild.leftChild)) {
			node = moveRedLeft(node);
		}
		node.leftChild = deleteMin(node.leftChild);
		return balance(node);
	}
	
	private Node balance(Node n) {
	    if (n == null) {
			return null;
		}
		if (isRed(n.rightChild)) {
			n = rotateLeft(n);
		}
		if (isRed(n.leftChild) && isRed(n.leftChild.leftChild)) {
			n = rotateRight(n);
		}
		if (isRed(n.leftChild) && isRed(n.rightChild)) {
			flipColors(n);
		}
		
		n.N = size(n.leftChild) + size(n.rightChild) + 1;
	    return n;
	}
	
	private Node moveRedRight(Node node) {
		flipColors(node);
		if (!isRed(node.leftChild.leftChild)) {
			node = rotateRight(node);
		}
		return node;
	}
	
	public void deleteMax() {
		if (!isRed(root.leftChild) && !isRed(root.rightChild)) {
			root.color = RED;
		}
		root = deleteMax(root);
		if (!isEmpty()) {
			root.color = BLACK;
		}
	}
	
	private Node deleteMax(Node node) {
		if (isRed(node.leftChild)) {
			node = rotateRight(node);
		}
		if (node.rightChild == null) {
			return null;
		}
		if (!isRed(node.rightChild) && !isRed(node.rightChild.leftChild)) {
			node = moveRedRight(node);
		}
		node.rightChild = deleteMax(node.rightChild);
		return balance(node);
	}

	@Override
	public T delete(K byKey) {
		if (!isRed(root.leftChild) && !isRed(root.rightChild)) {
			root.color = RED;
		}
		root = delete(root, byKey);
		if (!isEmpty()) {
			root.color = BLACK;
			return root.payload;
		}
		return null;
	}
	
	public boolean isEmpty() {
	     return size() == 0;
	}

	public int size() {
	    return size(root);
	}

	public T get(K key) {
        return get(root, key);
    }

    private T get(Node x, K key) {
        if (key == null) {
        		throw new IllegalArgumentException("calls get() with a null key");
        }
        if (x == null) {
        		return null;
        } 
        final int compared = key.compareTo(x.key);
        if (compared < 0) {
        		return get(x.leftChild, key);
        } else if (compared > 0) {
        		return get(x.rightChild, key);
        } else {
        	 	return x.payload;
        }
    }
	
	public K min() {
		if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
	    return min(root).key;
	} 

	private Node min(Node x) { 
		if (x != null) {
			return (x.leftChild != null) ? min(x.leftChild) : x;
		}
		return x;
	}
	
	private Node delete(Node node, K key) {
		if (key.compareTo(node.key) < 0) {
			
			if (!isRed(node.leftChild) && !isRed(node.leftChild.leftChild)) {
				node = moveRedLeft(node);
			}
			node.leftChild = delete(node.leftChild, key);
		} else {
			
			if (isRed(node.leftChild)) {
				node = rotateRight(node);
			}
			if (key.compareTo(node.key) == 0 && node.rightChild == null) {
				return null;
			}
			if (!isRed(node.rightChild) && !isRed(node.rightChild.leftChild)) {
				node = moveRedRight(node);
			}
			if (key.compareTo(node.key) == 0) {
				K rightKey = min(node.rightChild).key;
				node.payload = get(node.rightChild, rightKey);
				node.key = rightKey;
				node.rightChild = deleteMin(node.rightChild);
			} else {
				node.rightChild = delete(node.rightChild, key);
			}
		}
		return balance(node);
	}

	@Override
	public String traverse(TraverseOrder order) {
		final StringBuffer sb = new StringBuffer();
		switch (order) {
		case IN_ORDER: inOrder(sb, root);
			break;
		case POST_ORDER: postOrder(sb, root);
			break;
		case PRE_ORDER: preOrder(sb, root);
			break;
		}
		return sb.toString();
	}
	
	
	private void preOrder(final StringBuffer sb, final Node localRoot) {
		if (localRoot != null) {
			sb.append(localRoot.toString());
			sb.append('\n');
			preOrder(sb, localRoot.leftChild);
			preOrder(sb, localRoot.rightChild);
		}
	}

	private void inOrder(final StringBuffer sb, final Node localRoot) {
		if (localRoot != null) {
			inOrder(sb, localRoot.leftChild);
			sb.append(localRoot.toString());
			sb.append('\n');
			inOrder(sb, localRoot.rightChild);
		}
	}

	private void postOrder(final StringBuffer sb, final Node localRoot) {
		if (localRoot != null) {
			postOrder(sb, localRoot.leftChild);
			postOrder(sb, localRoot.rightChild);
			sb.append(localRoot.toString());
			sb.append('\n');
		}
	}
}
*/
public class RedBlackTree<Key extends Comparable<Key>, Value> implements BinarySearchTree<Key, Value> {

    private static final boolean RED   = true;
    private static final boolean BLACK = false;

    private Node root;     // root of the BST

    // BST helper node data type
    private class Node {
        private Key key;           // key
        private Value val;         // associated data
        private Node left, right;  // links to left and right subtrees
        private boolean color;     // color of parent link
        private int size;          // subtree count

        public Node(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }

		@Override
		public String toString() {
			return "Node [key=" + key + ", val=" + val + "]";
		}
    }

    /**
     * Initializes an empty symbol table.
     */
    public RedBlackTree() {
    }

   /***************************************************************************
    *  Node helper methods.
    ***************************************************************************/
    // is node x red; false if x is null ?
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    // number of node in subtree rooted at x; 0 if x is null
    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    } 


    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return size(root);
    }

   /**
     * Is this symbol table empty?
     * @return {@code true} if this symbol table is empty and {@code false} otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }


   /***************************************************************************
    *  Standard BST search.
    ***************************************************************************/

    /**
     * Returns the value associated with the given key.
     * @param key the key
     * @return the value associated with the given key if the key is in the symbol table
     *     and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    // value associated with the given key in subtree rooted at x; null if no such key
    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.val;
        }
        return null;
    }

    /**
     * Does this symbol table contain the given key?
     * @param key the key
     * @return {@code true} if this symbol table contains {@code key} and
     *     {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

   /***************************************************************************
    *  Red-black tree insertion.
    ***************************************************************************/

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old 
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     *
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }

        root = put(root, key, val);
        root.color = BLACK;
        // assert check();
    }

    // insert the key-value pair in the subtree rooted at h
    private Node put(Node h, Key key, Value val) { 
        if (h == null) return new Node(key, val, RED, 1);

        int cmp = key.compareTo(h.key);
        if      (cmp < 0) h.left  = put(h.left,  key, val); 
        else if (cmp > 0) h.right = put(h.right, key, val); 
        else              h.val   = val;

        // fix-up any right-leaning links
        if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h);
        if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left)  &&  isRed(h.right))     flipColors(h);
        h.size = size(h.left) + size(h.right) + 1;

        return h;
    }

   /***************************************************************************
    *  Red-black tree deletion.
    ***************************************************************************/

    /**
     * Removes the smallest key and associated value from the symbol table.
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // delete the key-value pair with the minimum key rooted at h
    private Node deleteMin(Node h) { 
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }


    /**
     * Removes the largest key and associated value from the symbol table.
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // delete the key-value pair with the maximum key rooted at h
    private Node deleteMax(Node h) { 
        if (isRed(h.left))
            h = rotateRight(h);

        if (h.right == null)
            return null;

        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);

        h.right = deleteMax(h.right);

        return balance(h);
    }

    /**
     * Removes the specified key and its associated value from this symbol table     
     * (if the key is in this symbol table).    
     *
     * @param  key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
 
    // delete the key-value pair with the given key rooted at h
    private Node delete(Node h, Key key) { 
        // assert get(h, key) != null;

        if (key.compareTo(h.key) < 0)  {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                // h.val = get(h.right, min(h.right).key);
                // h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }

   /***************************************************************************
    *  Red-black tree helper functions.
    ***************************************************************************/

    // make a left-leaning link lean to the right
    private Node rotateRight(Node h) {
        // assert (h != null) && isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    // make a right-leaning link lean to the left
    private Node rotateLeft(Node h) {
        // assert (h != null) && isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    // flip the colors of a node and its two children
    private void flipColors(Node h) {
        // h must have opposite color of its two children
        // assert (h != null) && (h.left != null) && (h.right != null);
        // assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
        //    || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private Node moveRedLeft(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

        flipColors(h);
        if (isRed(h.right.left)) { 
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private Node moveRedRight(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        flipColors(h);
        if (isRed(h.left.left)) { 
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    // restore red-black tree invariant
    private Node balance(Node h) {
        // assert (h != null);

        if (isRed(h.right))                      h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }


   /***************************************************************************
    *  Utility functions.
    ***************************************************************************/

    /**
     * Returns the height of the BST (for debugging).
     * @return the height of the BST (a 1-node tree has height 0)
     */
    public int height() {
        return height(root);
    }
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

   /***************************************************************************
    *  Ordered symbol table methods.
    ***************************************************************************/

    /**
     * Returns the smallest key in the symbol table.
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    } 

    // the smallest key in subtree rooted at x; null if no such key
    private Node min(Node x) { 
        // assert x != null;
        if (x.left == null) return x; 
        else                return min(x.left); 
    } 

    /**
     * Returns the largest key in the symbol table.
     * @return the largest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    } 

    // the largest key in the subtree rooted at x; null if no such key
    private Node max(Node x) { 
        // assert x != null;
        if (x.right == null) return x; 
        else                 return max(x.right); 
    } 


    /**
     * Returns the largest key in the symbol table less than or equal to {@code key}.
     * @param key the key
     * @return the largest key in the symbol table less than or equal to {@code key}
     * @throws NoSuchElementException if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null) return null;
        else           return x.key;
    }    

    // the largest key in the subtree rooted at x less than or equal to the given key
    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0)  return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t; 
        else           return x;
    }

    /**
     * Returns the smallest key in the symbol table greater than or equal to {@code key}.
     * @param key the key
     * @return the smallest key in the symbol table greater than or equal to {@code key}
     * @throws NoSuchElementException if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("calls ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) return null;
        else           return x.key;  
    }

    // the smallest key in the subtree rooted at x greater than or equal to the given key
    private Node ceiling(Node x, Key key) {  
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0)  return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t; 
        else           return x;
    }

    /**
     * Return the kth smallest key in the symbol table.
     * @param k the order statistic
     * @return the {@code k}th smallest key in the symbol table
     * @throws IllegalArgumentException unless {@code k} is between 0 and
     *     <em>n</em>–1
     */
    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + k);
        }
        Node x = select(root, k);
        return x.key;
    }

    // the key of rank k in the subtree rooted at x
    private Node select(Node x, int k) {
        // assert x != null;
        // assert k >= 0 && k < size(x);
        int t = size(x.left); 
        if      (t > k) return select(x.left,  k); 
        else if (t < k) return select(x.right, k-t-1); 
        else            return x; 
    } 

    /**
     * Return the number of keys in the symbol table strictly less than {@code key}.
     * @param key the key
     * @return the number of keys in the symbol table strictly less than {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    } 

    // number of keys less than key in the subtree rooted at x
    private int rank(Key key, Node x) {
        if (x == null) return 0; 
        int cmp = key.compareTo(x.key); 
        if      (cmp < 0) return rank(key, x.left); 
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right); 
        else              return size(x.left); 
    } 


    /**
     * Returns the number of keys in the symbol table in the given range.
     *
     * @param  lo minimum endpoint
     * @param  hi maximum endpoint
     * @return the number of keys in the sybol table between {@code lo} 
     *    (inclusive) and {@code hi} (inclusive)
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *    is {@code null}
     */
    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else              return rank(hi) - rank(lo);
    }

	@Override
	public Value find(Key withKey) {
		return this.get(withKey);
	}

	@Override
	public void insert(Key key, Value value) {
		this.put(key, value);
	}

	@Override
	public Value delete(Key key) {
	       if (key == null) throw new IllegalArgumentException("argument to delete() is null");
	        if (!contains(key)) return null;

	        // if both children of root are black, set root to red
	        if (!isRed(root.left) && !isRed(root.right))
	            root.color = RED;

	        root = delete(root, key);
	        if (!isEmpty()) root.color = BLACK;
	        // assert check();
		return null;
	}

	@Override
	public String traverse(TraverseOrder order) {
		final StringBuffer sb = new StringBuffer();
		switch (order) {
		case IN_ORDER: inOrder(sb, root);
			break;
		case POST_ORDER: postOrder(sb, root);
			break;
		case PRE_ORDER: preOrder(sb, root);
			break;
		}
		return sb.toString();
	}
	
	
	private void preOrder(final StringBuffer sb, final Node localRoot) {
		if (localRoot != null) {
			sb.append(localRoot.toString());
			sb.append('\n');
			preOrder(sb, localRoot.left);
			preOrder(sb, localRoot.right);
		}
	}

	private void inOrder(final StringBuffer sb, final Node localRoot) {
		if (localRoot != null) {
			inOrder(sb, localRoot.left);
			sb.append(localRoot.toString());
			sb.append('\n');
			inOrder(sb, localRoot.right);
		}
	}

	private void postOrder(final StringBuffer sb, final Node localRoot) {
		if (localRoot != null) {
			postOrder(sb, localRoot.left);
			postOrder(sb, localRoot.right);
			sb.append(localRoot.toString());
			sb.append('\n');
		}
	}

}

