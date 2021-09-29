package comp2402a1;

import java.util.AbstractList;
import java.util.Collection;

/**
 * This class implements the MyList interface as a single array a.
 * Elements are stored at positions a[0],...,a[size()-1].  
 * Doubling/halving is used to resize the array a when necessary. 
 * @author morin
 * @author sharp
 *
 * @param <T> the type of objects stored in the List
 */
public class MyArrayStack<T> implements MyList<T> { 
	
	/**
	 * The array used to store elements
	 */
	T[] a;
	
	/**
	 * The number of elements stored
	 */
	int n;
	
	/**
	 * Resize the internal array
	 */
	protected void resize() {
		@SuppressWarnings("unchecked")
		T[] b = (T[])new Object[Math.max(n*2, 1)];
		for (int i = 0; i < n; i++) {
			b[i] = a[i];
		}
		a = b;
	}

	/**
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	public MyArrayStack() {
		a = (T[])new Object[1];
		n = 0;
	}

	/**
	 * Constructor
   *
   * @param cap
	 */
	@SuppressWarnings("unchecked")
	public MyArrayStack(int cap) {
		a = (T[])new Object[cap];
		n = 0;
	}
	
	public int size() {
		return n;
	}

	public T get(int i) {
		if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
		return a[i];
	}
	
	public T set(int i, T x) {
		if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
		T y = a[i];
		a[i] = x;
		return y;
	}
	
	public void add(int i, T x) {
		if (i < 0 || i > n) throw new IndexOutOfBoundsException();
		if (n + 1 > a.length) resize();
		for (int j = n; j > i; j--) 
			a[j] = a[j-1];
		a[i] = x;
		n++;
	}
	
	public T remove(int i) {
		if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
		T x = a[i];
		for (int j = i; j < n-1; j++) 
			a[j] = a[j+1];
		n--;
		if (a.length >= 3*n) resize();
		return x;
	}

  public String toString() {
    String s = a.toString();
    // This is the default behaviour of toString.
    // TODO: Override this with more useful behaviour for debugging.
    return s;
  }

  public MyList<T> shuffle(MyList<T> other) {
    // TODO: Return the shuffle of this and other.
	MyArrayStack<T> shuffled = new MyArrayStack<>();
	int totalSize, otherSizePos=0, thisSizePos=0, shuffleSizePos=0;
	if(other.size() >= this.size()){
		totalSize = other.size();
	}
	else{
		totalSize = this.size();
	}

	for(int x=0; x<totalSize; x++){
		if(thisSizePos<=(this.size()-1)){
			shuffled.add(shuffleSizePos, this.get(thisSizePos));
			thisSizePos++;
			shuffleSizePos++;
		}
		if(otherSizePos<=(other.size()-1)){
			shuffled.add(shuffleSizePos, other.get(otherSizePos));
			otherSizePos++;
			shuffleSizePos++;
		}
	}
    return shuffled;
  }

	public MyList<MyList<T>> countOff(int n) {
    // TODO: Return a list of n lists, made by counting off
    // the elements in rounds of n.
    MyList<MyList<T>> finalList = new MyArrayStack<MyList<T>>();
	
	if(n==0){
		return finalList;
	}

	for(int x=0; x<n; x++){
		finalList.add(x, new MyArrayStack<T>());
	}

	for(int x=0; x<this.size(); x++){
		int temp = (int)Math.floor(x/n);
		finalList.get(x%n).add(temp, this.get(x));
	}

    return finalList; 
  }

  @SuppressWarnings("unchecked")
  public void reverse() {
    // TODO: Reverse this MyArrayStack.
	T temp = (T)new Object();
	int oppositePos=this.size()-1;
	for(int x=0; x<Math.floor(this.size()/2); x++){
		temp = this.get(x);
		this.set(x,this.get(oppositePos));
		this.set(oppositePos, temp);
		oppositePos--;
	}
  }
}
