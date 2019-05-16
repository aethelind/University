import java.util.Scanner;

class Matrix
{
  private int[][] matrix;
  private int rows;
  private int cols;
  private Scanner s = new Scanner(System.in);
  
  Matrix(int n, int m)
  {
    rows=n;
    cols=m;
    matrix = new int[rows][cols];    
  }
  void fillMatrix()
  {
    for(int i=0; i<rows; i++)
    {
      for(int j=0; j<cols; j++)
      {
        System.out.println("Please enter value for row " + (i+1) + ", column " + (j+1) + ":");
        matrix[i][j]=s.nextInt();
      }
    }
  }
  void printMatrix()
  {
    System.out.println();
    for(int i=0; i<rows; i++)
    {
      for(int j=0; j<cols; j++)
      {
        System.out.print(matrix[i][j] + "\t");
      }
      System.out.println();
    }
    System.out.println();
  }
  void switchRows(int a, int b)
  {
    int[] tmp = new int[cols];
    
    for(int j=0; j<cols; j++)
      tmp[j]=matrix[a][j];
    
    for(int j=0; j<cols; j++)
      matrix[a][j]=matrix[b][j];
    
    for(int j=0; j<cols; j++)
      matrix[b][j]=tmp[j];
    
  }
  void multRow(int row, int k)
  {
    // kR1 -> R1
    
    for(int j=0; j<cols; j++)
      matrix[row][j] = k*(matrix[row][j]);
      
  }
  void addRows(int row1, int row2, int k)
  {
    // R1 + kR2 -> R1
    
    int tmp[] = new int[cols];
    
    for(int j=0; j<cols; j++)
      tmp[j] = k*(matrix[row2][j]);
    
    for(int j=0; j<cols; j++)
      matrix[row1][j]+=tmp[j];
    
    
  }
  boolean takeRE()
  {
    double k;
    int leadingCol=0;
    boolean flag;
    
    for(int top=0; top<rows; top++)
    {
      flag = false;
      
      outer:
      for(int j=top; j<cols; j++)
      {
        for(int i=top; i<rows; i++)
        {
          if(matrix[i][j] != 0)
          {
            flag=true;
            leadingCol=j;
            this.switchRows(top, i);
            top=i;
            break outer;
          }
        }
      }
      
      if(flag=false)
        return true;
      
      System.out.println("top, leadingcol, " + top + " " + leadingCol);
      this.printMatrix();
      
      k=(1)/(matrix[top][leadingCol]);
      System.out.println("k   " + k);
      
      this.multRow(top, k);
      
      for(int i=top; i<rows; i++)
      {
        if(matrix[i][leadingCol] != 0)
        {
          this.addRows(top, i, (-(matrix[i][leadingCol])));
        }
      }  
    }
    
    return true;
  }
  /*void takeRRE()
  {
    
  }
  int getRank()
  {
    
  }
  void getSolution()
  {
    
  }*/
}