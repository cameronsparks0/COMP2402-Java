package comp2402a2;

import java.util.List;
import java.util.ArrayList;

/**
 * This class implements the MyStack interface.
 * @author sharp
 *
 * @param <T> the type of objects stored in the MyStack
 */
public class MyFastStack<T> implements MyStack<T> { 
	List<T> l;
	public MyFastStack() {
		l = new ArrayList<>();
	}

	public int size() {
		return l.size();
	}

	
	public void push(T x) {
		if(x!=null){
			l.add(x);
			if(size()>=2){
				if(l.get(size()-1).equals(l.get(size()-2))){
					l.remove(size()-1);
					l.remove(size()-1);
				}
			}
		}
	}
	
	public T pop() {
		if(l.size()==0){
			return null;
		}
		else{
			return l.remove(l.size()-1);
		}
	}
}
