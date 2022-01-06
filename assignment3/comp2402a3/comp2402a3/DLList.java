package comp2402a3;
// Thanks to Patrick Morin for the skeleton of this file.

import java.util.ListIterator;
import java.util.Random;

/**
 * An implementation of the List interface as a doubly-linked circular list. A
 * dummy node is used to simplify the code.
 * 
 * @author morin
 * @author sharp
 * 
 * @param <T>
 *            the type of elements stored in the list
 */
public class DLList<T> implements MyList<T> {
	class Node {
		T x;
		Node prev, next;
	}
	
	/**
	 * Number of nodes in the list
	 */
	int n;

	/**
	 * The dummy node. We use the convention that dummy.next = first and
	 * dummy.prev = last
	 */
	protected Node dummy;

	public DLList() {
		dummy = new Node();
		dummy.next = dummy;
		dummy.prev = dummy;
		n = 0;
	}

	/**
	 * Add a new node containing x before the node p
	 * 
	 * @param w
	 *            the node to insert the new node before
	 * @param x
	 *            the value to store in the new node
	 * @return the newly created and inserted node
	 */
	protected Node addBefore(Node w, T x) {
		Node u = new Node();
		u.x = x;
		u.prev = w.prev;
		u.next = w;
		u.next.prev = u;
		u.prev.next = u;
		n++;
		return u;
	}

	/**
	 * Remove the node p from the list
	 * 
	 * @param w
	 *            the node to remove
	 */
	protected void remove(Node w) {
		w.prev.next = w.next;
		w.next.prev = w.prev;
		n--;
	}

	/**
	 * Get the i'th node in the list
	 * 
	 * @param i
	 *            the index of the node to get
	 * @return the node with index i
	 */
	protected Node getNode(int i) {
		Node p = null;
		if (i < n / 2) {
			p = dummy.next;
			for (int j = 0; j < i; j++)
				p = p.next;
		} else {
			p = dummy;
			for (int j = n; j > i; j--)
				p = p.prev;
		}
		return p;
	}

	public int size() {
		return n;
	}

	public boolean add(T x) {
		addBefore(dummy, x);
		return true;
	}

	public T remove(int i) {
		if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
		Node w = getNode(i);
		remove(w);
		return w.x;
	}

	public void add(int i, T x) {
		if (i < 0 || i > n) throw new IndexOutOfBoundsException();
		addBefore(getNode(i), x);
	}

	public T get(int i) {
		if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
		return getNode(i).x;
	}

	public T set(int i, T x) {
		if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
		Node u = getNode(i);
		T y = u.x;
		u.x = x;
		return y;
	}

	public void clear() {
		dummy.next = dummy;
		dummy.prev = dummy;
		n = 0;
	}
 
  public MyList<T> shuffle(MyList<T> other ) {
    // Create a new DLList
	int max;
	DLList<T> merged = new DLList<T>();
	Node thisNode=null;
	if(size()>=0){
		thisNode = getNode(0);
	}

	if(other.size()>=size()){
		max=other.size();
	}
	else{
		max=size();
	}
	for(int i=0; i<max; i++){
		if(size()-1>=i){
			merged.add(thisNode.x);
			thisNode = thisNode.next;
		}
		if(other.size()-1>=i){
			merged.add(other.get(i));
		}
	}
    // TODO: implement shuffle
    return merged;
  }

  public MyList<T> chop( int i ) {
    // Create a new DLList
    DLList<T> other = new DLList<T>();
	if(size()-1<i){ //If greater then just return other
		return other;
	}
	Node start = getNode(i);
	Node temp = start.prev;
	Node last = getNode(size()-1);
	start.prev=other.dummy;
	last.next=other.dummy;
	other.dummy.next=start;
	other.dummy.prev=last;
	other.n=size()-i;
	if(i==0){
		dummy.next=dummy;
		dummy.prev=dummy;
		n=0;
	}
	else{
		Node lastOfThis = temp;
		lastOfThis.next=dummy;
		dummy.prev=lastOfThis;
		n = i;
	}
    // TODO: implement chop

    return other;
  }

  public void shrink() {
	Node thisNode=dummy.next;
	if(size()<=1){
		return;
	}
	while(thisNode!=dummy.prev){
		if(thisNode.x.equals(thisNode.next.x)){
			thisNode = thisNode.prev;
			remove(thisNode.next.next);
			remove(thisNode.next);
		}
		else{
			thisNode = thisNode.next;
		}
	}
  }

  public void reverse() {
    // YOU DO NOT HAVE TO IMPLEMENT THIS IN THIS ASSIGNMENT.
    // (You have to implement it in the SkiplistList class, however.)
    // The server will not test this method.
  }

  public String toString() {
    StringBuilder retStr = new StringBuilder();
    retStr.append("[");
    Node u = dummy.next;
    while( u != dummy ) {
      retStr.append(u.x);
      u = u.next;
      if( u != dummy ) {
        retStr.append(", ");
      }
    }
    retStr.append("]");
    return retStr.toString();
  }

	class Iterator implements ListIterator<T> {
		/**
		 * The list we are iterating over
		 */
		DLList<T> l;

		/**
		 * The node whose value is returned by next()
		 */
		Node p;

		/**
		 * The last node whose value was returned by next() or previous()
		 */
		Node last;

		/**
		 * The index of p
		 */
		int i;

		Iterator(DLList<T> il, int ii) {
			l = il;
			i = ii;
			p = l.getNode(i);
		}

		public boolean hasNext() {
			return p != dummy;
		}

		public T next() {
			T x = p.x;
			last = p;
			p = p.next;
			i++;
			return x;
		}

		public int nextIndex() {
			return i;
		}

		public boolean hasPrevious() {
			return p.prev != dummy;
		}

		public T previous() {
			p = p.prev;
			last = p;
			i--;
			return p.x;
		}

		public int previousIndex() {
			return i - 1;
		}

		public void add(T x) {
			DLList.this.addBefore(p, x);
		}

		public void set(T x) {
			last.x = x;
		}

		public void remove() {
			if (p == last) {
				p = p.next;
			}
			DLList.this.remove(last);
		}

	}
}
