package comp2402a3;

import java.util.Iterator;
import java.util.Random;

public class Tester {

  public static void testShuffle(int n1, int n2) {
    System.out.println( "Test DLLIst Shuffle------");
    DLList<Integer> l = new DLList<Integer>();

    for( int i = 0; i < n1; i++ ) {
      l.add(i, l.size());
    }
    MyList<Integer> other = new DLList<Integer>();

    for( int i = 0; i < n2; i++ ) {
      other.add(i, other.size());
    }

    System.out.printf("List 1: %s\n", l);
    System.out.printf("List 2: %s\n", other );
    MyList<Integer> newL = l.shuffle( other );
    System.out.printf("Shuffle: %s\n", newL );
    System.out.println( "Done Test DLLIst Shuffle------");
  }

  public static void testShrink(int n) {
    System.out.println( "Test DLLIst Shrink------");
    Random rand = new java.util.Random();
    DLList<Integer> l = new DLList<>();
    for( int i=0; i < 3*n; i++ ) {
      l.add(rand.nextInt(n));
    }
    System.out.printf( "Contents before shrink: %s\n", l);
    l.shrink();
    System.out.printf( "Contents after shrink: %s\n", l );

    System.out.println( "Done Test DLLIst Shrink------");
  }

  public static void testFunction() {
    System.out.println( "Test DLLIst Shrink------");
    DLList<String> l = new DLList<>();
    l.add("a");
    l.add("a");
    l.add("b");
    l.add("c");
    l.add("d");
    l.add("e");
    l.add("d");
    l.add("e");
    l.add("e");
    l.add("d");
    l.add("e");
    l.add("d");
    l.add("c");
    System.out.printf( "Contents before shrink: %s\n", l);
    l.shrink();
    System.out.printf( "Contents after shrink: %s\n", l );

    System.out.println( "Done Test DLLIst Shrink------");
  }

  public static void testDLListChop(int n1, int n2) {
    System.out.println( "Test DLList Chop------");
    DLList<Integer> l = new DLList<Integer>();

    if( n2 > n1 ) {
      n1 = n2;
    }

    for( int i = 0; i < n1; i++ ) {
      l.add(i, l.size());
    }

    System.out.printf( "Before chop(%d): %s\n", n2, l );
    MyList<Integer> newL = l.chop( n2 );
    System.out.printf( "After chop(%d): %s, %s\n", n2, l, newL );
    System.out.println( "Done Test DLList Chop------");
  }

  public static void testReverse(int n) {
    System.out.println( "Test Skiplist Reverse------");
    SkiplistList<Integer> l = new SkiplistList<Integer>();

    for( int i = 0; i < n; i++ ) {
      l.add(i, l.size());
    }
    System.out.printf( "Before reverse: %s\n", l );
    System.out.printf( "size()=%d\n", l.size() );
    for( int i=0; i < l.size(); i++ ) {
      System.out.printf( "get(%d)=%d\n", i, l.get(i) );
    }
    l.reverse();
    System.out.printf( "After reverse: %s\n", l);
    System.out.printf( "size()=%d\n", l.size() );
    for( int i=0; i < l.size(); i++ ) {
      System.out.printf( "get(%d)=%d\n", i, l.get(i) );
    }
    System.out.println( "Done Test Skiplist Reverse------");
  }

  public static void testSkiplistChop(int n1, int n2) {
    System.out.println( "Test Skiplist Chop------");
    SkiplistList<Integer> l = new SkiplistList<Integer>();

    if( n2 > n1 ) {
      n1 = n2;
    }

    for( int i = 0; i < n1; i++ ) {
      l.add(i, l.size());
    }

    System.out.printf( "Before chop(%d): %s\n", n2, l );
    System.out.printf( "size()=%d\n", l.size() );
    for( int i=0; i < l.size(); i++ ) {
      System.out.printf( "get(%d)=%d\n", i, l.get(i) );
    }
    MyList<Integer> newL = l.chop( n2 );
    System.out.printf( "After chop(%d): %s, %s\n", n2, l, newL );
    System.out.printf( "size()=%d\n", l.size() );
    for( int i=0; i < l.size(); i++ ) {
      System.out.printf( "get(%d)=%d\n", i, l.get(i) );
    }
    System.out.println("other:");
    for( int i=0; i < newL.size(); i++ ) {
      System.out.printf( "get(%d)=%d\n", i, newL.get(i) );
    }
    System.out.println( "Done Test Skiplist Chop------");
  }


  public static void main(String[] args) {
    // TODO: Add your own tests! These are not exhaustive at _all_.
    // Try different numbers, big numbers, small numbers... but
    // also try different types, different orderings, etc.
    // Try to anticipate where your code is weak, and see if you can
    // test those weaknesses and maybe get rid of them.

    /*
    testShuffle(10, 10);
    testShuffle(10, 5);
    testShuffle(5, 10);

    //testShrink(4);
    //testShrink(100001);
    //testFunction();



    testDLListChop(10, 6);
    testDLListChop(10, 1);


    testReverse(10);
    testReverse(9);
    */

    //testSkiplistChop(10, 5);
    //testSkiplistChop(10, 10);
    //testSkiplistChop(10, 1);
    for(int x=0; x<102;x++){
      testSkiplistChop(101, x);
    }

  }
}
