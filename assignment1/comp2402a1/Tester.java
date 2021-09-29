package comp2402a1;

import java.util.Iterator;
import java.util.Random;

public class Tester {

  public static void testAddToBack(int n) {
    System.out.println( "Test AddToBack------");
    MyList<Integer> mal = new MyArrayStack<Integer>();
    System.out.println( mal );

    for( int i = 0; i < n; i++ ) {
      mal.add(i, mal.size());
      System.out.println( mal );
    }
    System.out.println( "Done Test AddToBack------");
  }

  public static void testRemoveFromFront(int n) {
    System.out.println( "Test RemoveFromFront------");
    MyList<Integer> mal = new MyArrayStack<Integer>();

    for( int i = 0; i < n; i++ ) {
      mal.add(i, mal.size());
    }

    System.out.println( mal );
    for( int i = 0; i < n; i++ ) {
      mal.remove(0);
      System.out.println( mal );
    }
    System.out.println( "Done Test RemoveFromFront------");
  }

  public static void test(){
    MyList<Integer> mal = new MyArrayStack<Integer>();
    for( int i = 0; i < 9; i++ ) {
      mal.add(i, mal.size());
    }
    mal.reverse();
    for(int i=0; i<9;i++){
      System.out.println(mal.get(i));
    }
  }

  public static void main(String[] args) {
    testAddToBack(10);
    testRemoveFromFront(10);
    test();
  }
}
