package comp2402a2;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * This class implements the MyDeque interface.
 * @author sharp
 *
 * @param <T> the type of objects stored in the MyDeque
 */
public class MyFastDeque<T> implements MyDeque<T> { 
	Deque<T> l;
	T first, last;
	public MyFastDeque() {
		l = new ArrayDeque<>();
	}

	public int size() {
		return l.size();
	}
	
	public void addFirst(T x) {
		if(x!=null){
			if(l.size()!=0){
				first = l.getFirst();
			}
			l.addFirst(x);
			if(l.size()>=2){
				if(l.getFirst().equals(first)){
					l.removeFirst();
					l.removeFirst();
				}
			}
		}
	}
	
	public void addLast(T x) {
		if(x!=null){
			if(l.size()!=0){
				last = l.getLast();
			}
			l.addLast(x);
			if(l.size()>=2){
				if(l.getLast().equals(last)){
					l.removeLast();
					l.removeLast();
				}
			}
		}
	}
	
	public T removeFirst() {
		if(l.size()==0){
			return null;
		}
		else{
			return l.removeFirst();
		}
	}

	public T removeLast() {
		if(l.size()==0){
			return null;
		}
		else{
			return l.removeLast();
		}
	}
}
