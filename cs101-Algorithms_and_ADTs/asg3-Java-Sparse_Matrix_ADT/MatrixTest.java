/* $Id: MatrixTest.java,v 1.2 2010-10-31 07:26:38-07 dmfrank - $
 * Derek Frank, dmfrank@ucsc.edu
 * 
 * NAME
 *   MatrixTest
 * 
 * DESCRIPTION
 *   A main class used to run Matrix.java and List.java and to test
 * their abilities to function.
 */


import java.io.*;
import java.util.Scanner;

class MatrixTest {
  
  public static void main (String args[]) {
    int n = 5;
    double d = 32.00;
    double x = 2;
    
    Matrix M = new Matrix(n);
    for (int i=1; i<n; i=i+2) {
      for (int j=1; j<n; j=j+2) {
        M.changeEntry(i,j,d);
        d = d-1.5;
      }
    }
    M.changeEntry(2,3,5.0);
    M.changeEntry(5,4,2.0);
    Matrix A = new Matrix(n);
    for (int i=1; i<=n; ++i) {
      for (int j=1; j<=n; ++j) {
        A.changeEntry(i,j,d);
        d = d+3.2;
      }
    }
    
    // Test copy() and scalarMult()
    Matrix N = M.copy();
    Matrix xN = N.scalarMult(x);
    System.out.printf("Matrix M has %d non-zero entries:%n%s%n",M.getNNZ(),M);
    System.out.printf("Matrix N has %d non-zero entries:%n%s%n",N.getNNZ(),N);
    System.out.printf("Matrix xN has %d non-zero entries:%n%s%n",xN.getNNZ(),xN);
    
    // Test equals and check to be sure altering a copied Matrix
    // does not affect the Matrix that was copied
    boolean MequalsN = M.equals(N);
    boolean NequalsxN = N.equals(xN);
    if ( MequalsN ) {
      System.out.printf("M equals N%n%n");
    }else {
      System.out.printf("M does not equal N%n%n");
    }
    if ( NequalsxN ) {
      System.out.printf("N equals xN%n%n");
    }else {
      System.out.printf("N does not equal xN%n%n");
    }
    
    // Test add() and sub()
    Matrix MaddN = M.add(N);
    System.out.printf("Matrix MaddN has %d non-zero entries:%n%s%n",MaddN.getNNZ(),MaddN);
    Matrix MsubN = M.sub(N);
    System.out.printf("Matrix MsubN has %d non-zero entries:%n%s%n",MsubN.getNNZ(),MsubN);
    Matrix MaddA = M.add(A);
    System.out.printf("Matrix MaddA has %d non-zero entries:%n%s%n",MaddA.getNNZ(),MaddA);
    Matrix MsubA = M.sub(A);
    System.out.printf("Matrix MsubA has %d non-zero entries:%n%s%n",MsubA.getNNZ(),MsubA);
    Matrix AsubM = A.sub(M);
    System.out.printf("Matrix AsubM has %d non-zero entries:%n%s%n",AsubM.getNNZ(),AsubM);
    System.out.printf("Matrix A has %d non-zero entries:%n%s%n",A.getNNZ(),A);
    
    // Test transpose()
    Matrix tA = A.transpose();
    System.out.printf("Matrix tA has %d non-zero entries:%n%s%n",tA.getNNZ(),tA);
    
    // Test makeZero() and mult()
    Matrix AdotM = A.mult(M);
    System.out.printf("Matrix AdotM has %d non-zero entries:%n%s%n",AdotM.getNNZ(),AdotM);
    M.makeZero();
    Matrix AdotEmptyM = A.mult(M);
    System.out.printf("Matrix AdotEmptyM has %d non-zero entries:%n%s%n",AdotEmptyM.getNNZ(),AdotEmptyM);
      
  }
  
  
}
