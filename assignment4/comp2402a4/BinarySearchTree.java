package comp2402a4;
// Thanks to Pat Morin for the skeleton of this file!

import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

public class BinarySearchTree<Node extends BinarySearchTree.BSTNode<Node,T>, T> extends
		BinaryTree<Node> implements SSet<T> {

	protected Comparator<T> c;
	
	public static class BSTNode<Node extends BSTNode<Node,T>,T>
		extends BinaryTree.BTNode<Node> {
    // Usually these would not be public, but for server tests it's necessary.
		public T x;       // Holds the data value of this node.
    public int s;     // The size of the tree rooted at this node.
	}

	/**
	 * The number of nodes (elements) currently in the tree
	 */
	protected int n;
	

	/**
	 * An extension of BSTNode that you can actually instantiate.
	 */
  protected static class BSTEndNode<T> extends BSTNode<BSTEndNode<T>,T> {
			public BSTEndNode() {
				this.parent = this.left = this.right = null;
        this.x = null;
        this.s = 1;       // A single node has size 1.
			}
	}

	protected Node newNode(T x) {
		Node u = super.newNode();
		u.x = x;
    u.s = 1;              // A single node has size 1.
		return u;
	}

	public BinarySearchTree(Node sampleNode, Node nil, Comparator<T> c) {
		super(sampleNode, nil);
		this.c = c; 
	}

	public BinarySearchTree(Node sampleNode, Comparator<T> c) {
		super(sampleNode);
		this.c = c; 
	}

	public BinarySearchTree(Node sampleNode) {
		this(sampleNode, new DefaultComparator<T>());
	}

	/**
   * Compute the size (number of nodes) of the tree rooted at u
	 * @return the size of the subtree rooted at u
	 */
	public int size2(Node u) {
    // TODO: You shouldn't have to change this method, but u.s will be 
    // incorrect unless you update it where necessary.
    return u.s;
	}
	
	/**
	 * Search for a value in the tree
	 * @return the last node on the search path for x
	 */
	protected Node findLast(T x) {
		Node w = r, prev = nil;
		while (w != nil) {
			prev = w;
			int comp = c.compare(x, w.x);
			if (comp < 0) {
				w = w.left;
			} else if (comp > 0) {
				w = w.right;
			} else {
				return w;
			}
		}
		return prev;
	}
	
	/**
	 * Search for a value in the tree
	 * @return the last "left turn" node on the search path for x
	 */
	protected Node findGENode(T x) {
		Node w = r, z = nil;
		while (w != nil) {
			int comp = c.compare(x, w.x);
			if (comp < 0) {
				z = w;        // We're turning left! Save z.
				w = w.left;
			} else if (comp > 0) {
				w = w.right;
			} else {
				return w;
			}
		}
		return z;
	}

	/**
	 * Search for a value in the tree
	 * @return the min value y >= x; null if x is larger than everything in tree
	 */
	public T find(T x) {
		Node w = r, z = nil;
		while (w != nil) {
			int comp = c.compare(x, w.x);
			if (comp < 0) {
				z = w;
				w = w.left;
			} else if (comp > 0) {
				w = w.right;
			} else {
				return w.x;
			}
		}
		return z == nil ? null : z.x;
	}

	/**
	 * Search for a value in the tree
	 * @return the last "right turn" node on the search path for x
	 */
	protected Node findLTNode(T x) {
		Node u = r, z = nil;
		while (u != nil) {
			int comp = c.compare(x, u.x);
			if (comp < 0) {
				u = u.left;
			} else if (comp > 0) {
				z = u;      // We're turning right! Save z.
				u = u.right;
			} else {
				return u;
			}
		}
		return z;
	}

  /*
   * Return the value that follows x (i.e. the minimum value
   * y such that y > x. If no such element exists, return null
   * @return the successor value of x.
   */
  public T succ(T x) {
    // TODO: Implement this method. It should match slowSucc, but
    // should be faster :-)
	Node temp = r;
	Node tempLarge=null;
	if(temp!=null){
		if(c.compare(temp.x, x) >0){
			tempLarge = temp;
		}
	}

	while(temp!=null){
		if(c.compare(temp.x, x) <= 0){ //x is larger or equal to node.x
			temp = temp.right;
			if(temp!=null && c.compare(temp.x, x) > 0){
				tempLarge = temp;
			}
		}
		else{ // x is smaller then node
			if(temp.left!=null){
				if(c.compare(temp.left.x, x) > 0){
					temp = temp.left;
					tempLarge=temp;
				}
				else{
					temp = temp.left;
				}
			}
			else{
				break;
			}
		}
	}
	if(tempLarge!=null){
		return tempLarge.x;
	}
	else{
		return null;
	}
  }

  /*
   * Return the value that follows x (i.e. the minimum value
   * y such that y > x. If no such element exists, return null
   * @return the successor value of x.
   */
  public T slowSucc(T x) {
    // Does an inorder traversal in O(n) time.
		Iterator<T> it = iterator();
    while( it.hasNext() ) {
      T curr = (T)(it.next());
      if( c.compare(curr, x) > 0 ) { // we have our first > elt
        return curr;                 // Return this element
      }
    }
    return null;            // never found anything
  }

  /*
   * Return the value that precedes x (i.e. the maximum value
   * y such that y < x. If no such element exists, return null
   * @return the predecessor value of x.
   */
  public T pred(T x) {
    // TODO: Implement this method. It should match slowPred, but
    // should be faster :-)
    Node temp = r;
	Node tempSmall=null;
	if(temp!=null){
		if(c.compare(temp.x, x) < 0){
			tempSmall = temp;
		}
	}

	while(temp!=null){
		if(c.compare(temp.x, x) >= 0){ //x is smaller or equal to node.x
			temp = temp.left;
			if(temp!=null && c.compare(temp.x, x) < 0){
				tempSmall = temp;
			}
		}
		else{ // x is smaller then node
			if(temp.right!=null){
				if(c.compare(temp.right.x, x) < 0){
					temp = temp.right;
					tempSmall=temp;
				}
				else{
					temp = temp.right;
				}
			}
			else{
				break;
			}
		}
	}
	if(tempSmall!=null){
		return tempSmall.x;
	}
	else{
		return null;
	}
  }

  /*
   * Return the value that precedes x (i.e. the maximum value
   * y such that y < x. If no such element exists, return null
   * @return the predecessor value of x.
   */
  public T slowPred(T x) {
    // Does an in-order traversal in O(n) time.
    T pred = null; // Keep track of the predecessor of curr
		Iterator<T> it = iterator();
    while( it.hasNext() ) {
      T curr = it.next();
      if( c.compare(curr, x) >= 0 ) { // we have our first >= elt
        return pred;                  // Return that last element we saw
      }
      pred = curr;
    }
    return pred;                    // never found anything >= elt
  }

  /*
   * Return a BST that contains all elements >= x, and remove them
   * from this. 
   * @return the BST containing all elements >= x
   */
  public BinarySearchTree<Node,T> chop(T x) {
	Node sample = super.newNode();
	BinarySearchTree<Node,T> other = new BinarySearchTree<Node, T>(sample);

	Node temp = r;
	Node tempLarge=null;
	Node lastLeft=null;
	Node lastRight=null;
	Node firstRight=null;
	Node firstLeft=null;

	if(temp!=null){
		if(c.compare(temp.x, x) >=0){
			tempLarge = temp;
		}
	}

	while(temp!=null){
		if(c.compare(temp.x, x) < 0){ //node.x is smaller then x
			if(firstRight==null){
				firstRight = temp;
			}
			lastRight=temp;
			temp = temp.right;
			if(temp!=null && c.compare(temp.x, x) >= 0){
				if(lastLeft!=null){
					//node manipulation
					lastLeft.left = temp;
					temp.parent.right =null;
					Node temp2 = temp.parent;
					while(temp2!=null){
						temp2.s = temp2.s - temp.s;
						temp2 = temp2.parent;
					}
					temp2 = lastLeft;
					while(temp2!=null){
						temp2.s = temp2.s + temp.s;
						temp2 = temp2.parent;
					}
					temp.parent = lastLeft;
				}
				tempLarge = temp;
			}
		}
		else{ // node.x is larger then or equal to x
			if(temp.left!=null){
				if(c.compare(temp.left.x, x) >= 0){
					if(firstLeft==null){
						firstLeft = temp;
					}
					lastLeft = temp;
					temp = temp.left;
					tempLarge=temp;
				}
				else{
					if(firstLeft==null){
						firstLeft = temp;
					}
					lastLeft = temp;
					temp = temp.left;
					if(lastRight!=null && temp!=null){
						if(lastRight.right!=null){
							lastRight.right.parent=null;
							Node temp2 = lastRight;
							while(temp2!=null){
								temp2.s = temp2.s - lastRight.right.s;
								temp2 = temp2.parent;
							}
						}
						lastRight.right = temp;
						Node temp2 = lastRight;
						while(temp2!=null){
							temp2.s = temp2.s + temp.s;
							temp2 = temp2.parent;
						}
						temp2 = temp.parent;
						while(temp2!=null){
							temp2.s = temp2.s - temp.s;
							temp2 = temp2.parent;
						}
						temp.parent.left = null;
						temp.parent = lastRight;
					}
					else if(lastRight==null){
						Node temp2 = temp.parent;
						while(temp2 != null){
							temp2.s = temp2.s - temp.s;
							temp2 = temp2.parent;
						}
						temp.parent.left = null;
						temp.parent=null;
					}
				}
			}
			else{
				if(c.compare(temp.x, x) >= 0 && firstLeft==null){
					other.r = temp;
					if(temp.parent!=null){
						Node temp2 = temp.parent;
						while(temp2 !=null){
							temp2.s = temp2.s - temp.s;
							temp2 = temp2.parent;
						}
						temp.parent.right=null;
						temp.parent=null;
					}
				}
				break;
			}
		}
	}
	if(firstLeft!=null){
		other.r = firstLeft;
		other.n = other.r.s;
		if(other.r.parent!=null){
			other.r.parent.right = null;
			other.r.parent.s = other.r.parent.s - other.r.s;
			other.r.parent = null;
		}
	}
	else{
		if(other.r!=null){
			other.n = other.r.s;
		}
	}
	
	if(lastLeft!=null && lastLeft.left!=null && c.compare(tempLarge.x, lastLeft.left.x) > 0 ){
		lastLeft.left.parent=null;
		lastLeft.left=null;
	}

	if(firstRight!=null){
		this.r = firstRight;
		this.n = this.r.s;
	}
	else{
		this.r=null;
		this.n=0;
	}

    return other;
  }

  
  /*
   * Return a BST that contains all elements >= x, and remove them
   * from this. 
   * Runs in O(n*height) time, where in a balanced tree height is O(log n).
   * @return the BST containing all elements >= x
   */
  public BinarySearchTree<Node,T> slowChop(T x) {
		Node sample = super.newNode();
		BinarySearchTree<Node,T> other = new 
        BinarySearchTree<Node, T>(sample);

    // Iterate through the n nodes in-order.
    // When see value >=x, add to new BST in O(height) time, and
    // remove it from this BST (on next iteration) in O(height) time.
		Iterator<T> it = iterator();
    T prev = null;
    while( it.hasNext() ) {
      T curr = (T)(it.next());
      if( c.compare(curr, x) >= 0 ) { // we have our first >= x 
        other.add(curr);
        if( prev != null ) {
          this.remove(prev);          // safe to remove now
        }
        prev = curr;
      }
    }
    if( prev != null ) {
      this.remove(prev); // edge case, get that last one!
    }
    return other; 
  }



	/**
	 * Add the node u as a child of node p -- ASSUMES p has no child
	 * where u should be added
	 * @param p
	 * @param u
	 * @return true if the child was added, false otherwise
	 */
	protected boolean addChild(Node p, Node u) {
		if (p == nil) {
			r = u;              // inserting into empty tree
		} else {
			int comp = c.compare(u.x, p.x);
			if (comp < 0) {
				p.left = u;
			} else if (comp > 0) {
				p.right = u;
			} else {
				return false;   // u.x is already in the tree
			}
			u.parent = p;
      // Update all ancestors of u to have one bigger size
      while( p != nil ) {
		p.s+=1;
        p = p.parent;
      }
		}
		n++;
		return true;		
	}

	/**
	 * Add a new value
	 * @param x
	 * @return
	 */
	public boolean add(T x) {
		Node p = findLast(x);
		return addChild(p, newNode(x));		
	}

	/**
	 * Add a new value
	 * @param x
	 * @return
	 */
	public boolean add(Node u) {
		Node p = findLast(u.x);
		return addChild(p, u);		
	}

	/**
	 * Remove the node u --- ASSUMING u has at most one child
	 * @param u
	 */
	protected void splice(Node u) {
		Node s, p; // s is the child of u, p is the parent.
		if (u.left != nil) {
			s = u.left;
		} else {
			s = u.right;
		}
		if (u == r) { // There is no parent, just replace r with s.
			r = s;
			p = nil;
		} else {
			p = u.parent;
			if (p.left == u) {
				p.left = s; // splice out u
			} else {
				p.right = s;  // splice out u
			}
		}
		if (s != nil) {
			s.parent = p;
		}
    while( p != nil ) {
		p.s-=1;
      p = p.parent;
    }
		n--;
	}
	
	/**
	 * Remove the node u from the binary search tree
	 * @param u
	 */
	protected void remove(Node u) {
		if (u.left == nil || u.right == nil) {
			splice(u);
		} else {
			Node w = u.right;
			while (w.left != nil) 
				w = w.left;
			u.x = w.x;
			splice(w);
		}
	}
	

	/**
	 * Remove a node
	 * @param x
	 * @return
	 */
	public boolean remove(T x) {
		Node u = findLast(x);
		if (u != nil && c.compare(x,u.x) == 0) {
			remove(u);
			return true;
		}
		return false;
	}
	
	public String toString() {
		String s = "[";
		Iterator<T> it = iterator();
		while (it.hasNext()) {
			s += it.next().toString() + (it.hasNext() ? "," : "");
		}
		s += "]";
		return s;
	}
	
  // Why do I need this method, shouldn't inheritance and polymorphism
  // take care of this?
	public Node firstNode() {
    return super.firstNode();
  }

  // Why do I need this method, shouldn't inheritance and polymorphism
  // take care of this?
	public Node nextNode(Node u) {
    return super.nextNode(u);
  }


	public Iterator<T> iterator(Node u) {
		class BTI implements Iterator<T> {
			protected Node w, prev;
			public BTI(Node iw) {
				w = iw;
			}
			public boolean hasNext() {
				return w != nil;
			}
			public T next() {
				T x = w.x;
				prev = w;
				w = nextNode(w);
				return x;
			}
			public void remove() {
				// NOTE: This is a bug.  remove() methods have to be changed
        // DON'T USE REMOVE ON THE ITERATOR				
				BinarySearchTree.this.remove(prev);
			}
		}
		return new BTI(u);
	}

	public Iterator<T> iterator() {
		return iterator(firstNode());
	}

	public Iterator<T> iterator(T x) {
		return iterator(findGENode(x));
	}
	
	public int size() {
		return n;
	}

	public void clear() {
		super.clear();
		n = 0;
	}

	public Comparator<? super T> comparator() {
		return c;
	}




	public static BinarySearchTree<BSTEndNode<Integer>,Integer> emptyBST() {
    return randomBST(0);
	}

	public static BinarySearchTree<BSTEndNode<Integer>,Integer> randomBST(int n) {
		BSTEndNode<Integer> sample = new BSTEndNode<Integer>();
		BinarySearchTree<BSTEndNode<Integer>,Integer> t = new 
        BinarySearchTree<BSTEndNode<Integer>, Integer>(sample);
		Random rand = new Random();
    for( int i=0; i < n; i++ ) {
      int value = rand.nextInt(3*n);
      t.add(value);
    }

		return t;
	}

}
