/** Exercise 1b DrawTriangle */
public class DrawTriangle 
{
     public static void Triangle(int N) 
     {
        int col=0;
        int row=0;
        while(row<=N)
        {
           while(col<=row)
           {
              if(col==row)
              {
                 System.out.println('*');
                 col=col+1;
             }
             else
             {
                System.out.print('*'); 
                col=col+1;
             }
          }
          row=row+1;
          col=0;
       }
    }

    public static void main(String[] args) 
    {
      Triangle(10);
    }
 }