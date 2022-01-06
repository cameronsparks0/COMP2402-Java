package comp2402a4;
import java.io.*;
import java.util.*;

public class Tester {
  protected static void println(String msg) {
    for (String line : msg.split("\n")) {
      System.out.println(line);
    }
  }

  protected static void printf(String format, Object... args) {
    System.out.printf(format, args);
  }


  static void printTree(BinaryTree<BinaryTree.EndNode> t) {
    ByteArrayOutputStream os = new java.io.ByteArrayOutputStream();
    PrintStream ps = new java.io.PrintStream(os);
    PrintWriter pw = new PrintWriter(ps);
    //t.prettyPrint(pw);
    pw.flush();
    println(os.toString());
  }

  static void testBinaryTree(BinaryTree<BinaryTree.EndNode> t) {
    printf("\nBinaryTree Testing\n");
    printf("Tree: %s\n", t);

    // Recursive and iterative size should be the same because they are given
    // to you in the starter code.
    int a0 = t.size();  // This is the recursive size.
    int a1 = t.size2(); // This is the (iterative) size.
    printf( "recursive t.size(): %d; iterative t.size2(): %d\n", a0, a1 );

    // Recursive height is provided in the starter code, but iterative
    // is for your to implement. If your height2 implementation is correct,
    // they should match.
    a0 = t.height();    // This is the recursive height.
    a1 = t.height2();   // This is your (iterative) height.
    printf( "recursive t.height(): %d; your t.height2(): %d\n", a0, a1 );
    if (a0 != a1) {
      printf("Failed height test\n");
      printf("\tExpected: %d, Got: %d.\n", a0, a1);
      if (t.size() < 100) {
        println("\tThis is the tree on which the code failed:");
        printf("\t%s\n", t);
      }
    }

    // An example on how to iterate through a BinaryTree.
    BinaryTree.EndNode node = t.firstNode();
    for( int i=0; i < t.size(); i++ ) {
      a0 = t.size(node);
      if( i % 10 == 0 )  printf( "t.size(u):%d\n", a0 );
      node = t.nextNode(node);
    }
  }


  static void testBinarySearchTree(
        BinarySearchTree<BinarySearchTree.BSTEndNode<Integer>, Integer> t) {
    printf("\nBinarySearchTree Testing\n");
    printf("Tree: %s\n", t);

    // Recursive and iterative size should be the same because they are given
    // to you in the starter code.
    int a0 = t.size();  // This is the recursive size.
    int a1 = t.size2(); // This is the (iterative) size.
    printf( "recursive t.size(): %d; iterative t.size2(): %d\n", a0, a1 );

    // Test size on the given random tree with n nodes.
    testSize(t);

    // Create a smaller tree from scratch that you can play around with.
    // It may be easier to debug with a tree whose structure you know.
    t = BinarySearchTree.emptyBST();
    List<Integer> al = 
     // Arrays.asList(new Integer[]{8, 4, 12, 1, 0, 6, 8, 4, 12, 1, 6, 0});
      new ArrayList<>(List.of(8, 4, 12, 1, 0, 6, 8, 4, 12, 1, 6, 0));
    // Note that 8 will be the root, then 4 its left child and 12 its right,
    // etc. you can determine your tree by ordering the nodes in level order.
    for( Integer i : al ) {
      t.add(i);
    }
    testSize(t);

    // Now perform removals and ensure the size of all nodes is correct each
    // time.
    for( Integer i : al ) {
      t.remove(i);
      testSize(t);
    }

  }

  public static void testSize(
      BinarySearchTree<BinarySearchTree.BSTEndNode<Integer>,Integer> t ) {

    // Iterate through the given bst t, and check that each node
    // has the correct size (i.e. your size2(node) method matches the
    // given recursive size(node) values.)
    BinarySearchTree.BSTEndNode<Integer> node = t.firstNode();
    int n = t.size2();
    for( int i=0; i < n; i++ ) {
      int a0 = t.size(node);    // the provided recursive method
      int a1 = t.size2(node);   // your (hopefully iterative) O(1) method
      //printf( "t.size(u):%d, t.size2(u): %d\n", a0, a1 );
      if (a0 != a1) {
        printf("Failed size2 test\n");
        printf("\tcorrect != result (%d!=%d)\n", a0, a1);
        if (t.size() < 100) {
          println("\tThis is the tree on which the code failed:");
          printf("\t%s\n", t);
        }
      }
      node = t.nextNode(node);
    }
  }
 
  public static void testPredSucc(int n) {

    println("\nTest Pred and Succ");
    BinarySearchTree<BinarySearchTree.BSTEndNode<Integer>,Integer> t =
      BinarySearchTree.emptyBST();

    for( int i=1; i < n; i++ ) {
      t.add(2*i);
    }

    for( int i=0; i <= 2*n; i++ ) {
      Integer a0 = t.slowPred(i);    // This is the slow version of pred
      Integer a1 = t.pred(i);        // This is your pred.
      if( (a0 == null) && (a1 == null) ) continue;
      if( (a0 == null) && (a1 != null) || 
          (a0 != null) && (a1 == null) || 
          !a0.equals(a1) ) {
        printf("Failed pred test\n");
        printf("\tExpected: %d, Got: %d.\n", a0, a1);
      }
    }

    for( int i=0; i <= 2*n; i++ ) {
      Integer a0 = t.slowSucc(i);    // This is the slow version of succ
      Integer a1 = t.succ(i);        // This is your succ.
      if( (a0 == null) && (a1 == null) ) continue;
      if( (a0 == null) && (a1 != null) || 
          (a0 != null) && (a1 == null) || 
          !a0.equals(a1) ) {
        printf("Failed succ test\n");
        printf("\tExpected: %d, Got: %d.\n", a0, a1);
      }
    }


  }

  public static void testChop(int n) {

    println("\nTest Chop");
    // Create two BSTs that are exactly the same.
    BinarySearchTree<BinarySearchTree.BSTEndNode<Integer>,Integer> t0 = BinarySearchTree.emptyBST();
    BinarySearchTree<BinarySearchTree.BSTEndNode<Integer>,Integer> t1 = BinarySearchTree.emptyBST();
    BinarySearchTree<BinarySearchTree.BSTEndNode<Integer>,Integer> t2 = BinarySearchTree.emptyBST();
    
    t2.add(7);
    t2.add(17);
    t2.add(5);
    t2.add(-1);
    t2.add(6);
    t2.add(2);
    t2.add(9);
    t2.add(8);
    t2.add(10);
    t2.add(20);


    // Create two identical random BSTs.
    Random rand = new Random();
    for( int i=0; i < n; i++ ) {
      int value = rand.nextInt(3*n);
      t0.add(value);
      t1.add(value);
    }

    // We will chop at 3n (past the end), at 2n and n (so, middle-ish),
    // then close to the front. 
    ArrayList<Integer> values = new ArrayList<>(
        List.of(3*n, 2*n, n, 2, 1, 0));

    for( int value : values ) {
      //printf( "Before chop(%d), t0.size()=%d, t1.size()=%d\n",
           //value, t0.size2(), t1.size2() );
      //printf( "t0: %s, t1: %s\n", t0, t1);
      BinarySearchTree<BinarySearchTree.BSTEndNode<Integer>,Integer> other0 = 
              t0.slowChop( value );
      BinarySearchTree<BinarySearchTree.BSTEndNode<Integer>,Integer> other1 = 
              t1.chop( value );
      int a0 = t0.size();
      int a1 = t1.size();
      if (a0 != a1) {
        printf("Failed chop test, this size is incorrect.\n");
        printf("\tExpected: %d; Got: %d\n", a0, a1);
        printf( "\tt0: %s, t1: %s\n", t0, t1);
      }
      a0 = other0.size();
      a1 = other1.size();
      if (a0 != a1) {
        printf("Failed chop test, other size is incorrect.\n");
        printf("\tExpected: %d; Got: %d\n", a0, a1);
        printf( "\tt0: %s, t1: %s\n", t0, t1);
      }
    }

    /*
    System.out.println("BEFORE!");
    System.out.println(t2);
    System.out.println("AFTER!");
    BinarySearchTree<BinarySearchTree.BSTEndNode<Integer>,Integer> t3 = t2.chop(2);
    System.out.println("Chop" + t3);
    System.out.println("NON-CHOP"+t2);
    System.out.println("Size of Non-Chop="+t2.size());
    System.out.println("Size of Chop="+t3.size());
    */

  }

  public static void main(String[] args) {
    int n = 20;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    }
    printf("Testing with n = %d\n", n);

    // Tests for the BinaryTree class.
    BinaryTree<BinaryTree.EndNode> t = BinaryTree.randomTree(n);
    //testBinaryTree(t);

    // Tests for the BinarySearchTree class.
    BinarySearchTree<BinarySearchTree.BSTEndNode<Integer>,Integer> bst = 
                        BinarySearchTree.randomBST(n);
    /*
    testBinarySearchTree(bst);


    testPredSucc(n);
    */

    testChop(n);

  }

}
