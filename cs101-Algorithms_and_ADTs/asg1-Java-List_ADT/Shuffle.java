/* $Id: Shuffle.java,v 1.2 2010-10-10 04:47:56-07 - - $
 * Derek Frank, dmfrank@ucsc.edu
 * 
 * NAME
 *   Shuffle
 * 
 * DESCRIPTION
 *   Reads permutations line by line from a file and shuffles a list
 * the length of the permutation according to the operators found in
 * the permutations.  The list is shuffled until it returns to its
 * original state and the number of shuffles is counted.  The first
 * shuffle along with the number of shuffles needed to return the
 * list to its original state are written to a file specified by the
 * user line by line with its corresponding permutation.  The first
 * line read from the file by Shuffle is assumed to be the number of
 * permutations in the file.
 */

import java.io.*;
import java.util.Scanner;

class Shuffle {
  
  /* shuffle()
   * Shuffles a given integer list of length n according to the
   * operations associated with another given integer list of
   * length n.
   */
  static void shuffle(List L, List P) {
    int temp, operator, i, j, jLimit;
    int prevOperator = 1; // must be at least 1 first time
    P.moveFirst(); // move current operator to front
    L.moveLast(); // move current position to end (or 1)
    
    for ( i=1; i<=P.getLength(); ++i ) {
      operator = P.getCurrent();
      temp = L.getFirst();
      jLimit = (operator - prevOperator);
      
      // either move current marker right or left
      if ( jLimit < 0 ) {
        jLimit = (-1*jLimit);
        for ( j=jLimit; j>0; --j ) {
          L.movePrev();
        }
      }else if ( jLimit > 0 ) {
        for ( j=0; j<jLimit; ++j ) {
          L.moveNext();
        }
      }
      
      L.insertAfterCurrent(temp);
      P.moveNext(); // move operator current-marker right
      prevOperator = operator; // keep track of previous current marker
      L.deleteFirst();
    }
  }
  
  public static void main (String[] args) throws IOException {
    Scanner infile = null;
    PrintWriter outfile = null;
    String line = null;
    int lineNumber = 0;
    int i, n, lengthP, numberOfP, data, ordern;
    
    if(args.length < 2) {
      System.out.println("Usage: Shuffle infile outfile");
      System.exit(1);
    }
    
    infile = new Scanner(new File(args[0]));
    outfile = new PrintWriter(new FileWriter(args[1]));
    List permutation = new List();
    List list = new List();
    List copy;
    String[] token = null;
    
    while( infile.hasNextLine() ) {
      ordern = 0; // count for order
      ++lineNumber;
      line = infile.nextLine()+" "; // add extra space so split works right
      token = line.split("\\s+"); // split line around white space
      n = token.length;
      
      if ( lineNumber <= 1 ) {
        numberOfP = (int)Integer.parseInt(token[0]);
      }else {
        for ( i=0; i<n; ++i ) {
          data = (int)Integer.parseInt(token[i]);
          permutation.insertAfterLast(data);
        }
        lengthP = permutation.getLength();
        for (i=1; i<=lengthP; ++i) {
          list.insertAfterLast(i);
        }
        
        copy = list.copy();
        shuffle(list, permutation);
        ++ordern;
        outfile.printf("(%s)", list);
        
        while ( !list.equals(copy) ) {
          shuffle(list, permutation);
          ++ordern;
        }
        outfile.printf(" order = %d%n", ordern);
        list.makeEmpty();
        permutation.makeEmpty();
        copy.makeEmpty();
      }
    }
    
    infile.close();
    outfile.close();
  }
}
