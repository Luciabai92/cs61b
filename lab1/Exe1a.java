public class Exe1a {
     public static void main(String[] args) {
        int col=0;
        int row=0;
        while(row<=5){
           while(col<=row){
              if(col==row){
                 System.out.println('*');
                 col=col+1;
             }else{
                System.out.print('*'); 
                col=col+1;
             }
          }
          row=row+1;
          col=0;
       }
    }
 }