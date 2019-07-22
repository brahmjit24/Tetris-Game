import java.util.*;
import java.io.IOException;
import java.io.*;
//-----------------Class for shape stack--------------------------------
class shapest { 
    int shape1;
    int x;
    int y;
}
//-----------------------File Handling saving the state of data-------------------
class Data implements Serializable
{
 char boardGrid[][]=new char[20][20];
 int shapeType;
 int x;
 int y;
 void read(char[][] board,int shapeType,int x,int y)
 {
   this.shapeType=shapeType;
   this.x=x;
   this.y=y;
   for(int i=0;i<20;i++)
   {
     for(int j=0;j<20;j++)
     {
         boardGrid[i][j]=board[i][j];
       }
    }
   
 }
 
 void display()
 {
 System.out.println("Data Saved :-----");
 System.out.println("Shape Type: "+shapeType+"  StartI : "+x+"  StartJ : "+y);
 System.out.println("Saved Grid: =>");
 for(int i=0;i<20;i++)
 {
 for(int j=0;j<20;j++)
 {
 System.out.print(boardGrid[i][j]);
 }
 System.out.println();
 }
 }
 
}
//-------------------------------Linked Stack-------------------------------
class LinkedStack{
    
    private class Node
    {
        shapest data;
        Node link;
    }
    Node top;
    LinkedStack()
    {
        this.top=null;
    }
    public void push(shapest shp)
    {
        Node temp=new Node();
        if(temp==null)
        {
            System.out.println("OverFlow Stack");
            return;
        }
        temp.data=new shapest();
        temp.data.shape1=shp.shape1;
        temp.data.x=shp.x;
        temp.data.y=shp.y;
        temp.link=top;
        top=temp;
    }
    public boolean empty()
    {
        return top==null;
    }
    
    public shapest pop()
    {
        if(top==null)
        {
            System.out.println("Underflow Stack");
            return null;
        }
        else
        {
        shapest temp=new shapest();
        temp=top.data;
        top=(top).link;
        return temp;
        }
    }
    public void clear()
    {
        top=null;
    }
}
//-----------------Shape Class with different shapes and versions--------
class shape{
    int startI=1;
    int startJ=(int)(Math.random()*15)+1;
    int version=0;
    int minI,maxI,minJ,maxJ,countSpace;
    int coordinates[][]=new int[4][2];
    boolean canRotate=true;
    public void fillboard(Board brd)
    {   
        for(int i=0;i<4;i++)
        {
            if(coordinates[i][0]>0)
          brd.boardMatrix[coordinates[i][0]][coordinates[i][1]]='#'; 
        } 
    }
    
    
    public void removeOldCoordinates(Board brd)
    {
        for(int i=0;i<4;i++)
        {
            
          brd.boardMatrix[coordinates[i][0]][coordinates[i][1]]=' '; 
        } 
    }
    
    
    public void left()
    {
        System.out.println(canRotate);
        if(minJ>1 )
        startJ--;  
    }
    
    
    public void right()
    { System.out.println(canRotate);
        if(maxJ<18 )
        startJ++;
    }
    public void down()
    { System.out.println(canRotate);
        if(maxI<18)
        startI++;
    }
    public void up()
    { System.out.println(canRotate);
        if(minI>1)
        startI--;
    }
    public void fillCoordinates(Board brd,int shape)
    {
        switch(shape)
        {
         case 0:fillCoordinates0(brd);
               break;
         case 1:fillCoordinates1(brd);
               break;
         case 2:fillCoordinates2(brd);
               break;
         case 3:fillCoordinates3(brd);
               break;
         case 4:fillCoordinates4(brd);
               break;       
                
        }
    }
        ////-----------------L_SHAPE----------------------------------
    public void fillCoordinates0(Board brd)
    {
        countSpace=0;
        if(version==0 )
        {    
        coordinates[0][0]=startI;
        coordinates[0][1]=startJ;
        coordinates[1][0]=startI+1;
        coordinates[1][1]=startJ;
        coordinates[2][0]=startI+2;
        coordinates[2][1]=startJ;
        coordinates[3][0]=startI+2;
        coordinates[3][1]=startJ+1;
            minI=coordinates[0][0];
            maxI=coordinates[3][0];
            minJ=coordinates[0][1];
            maxJ=coordinates[3][1];
                for(int j=1;j<3;j++)
                {
                    if( (startI)<19 &&(startJ+j)<19 &&  brd.boardMatrix[startI][startJ+j]==' ')
                        countSpace++; 
                }
               if( (startI-1)<19 &&(startJ+2)<19 &&  brd.boardMatrix[startI-1][startJ+2]==' ')
                        countSpace++; 
            
            System.out.println(countSpace);
            if(countSpace==3)
                canRotate=true;
            else
                canRotate=false;
        }
        else if(version==1)
        {
        coordinates[0][0]=startI;
        coordinates[0][1]=startJ;
        coordinates[1][0]=startI;
        coordinates[1][1]=startJ+1;
        coordinates[2][0]=startI;
        coordinates[2][1]=startJ+2;
        coordinates[3][0]=startI-1;
        coordinates[3][1]=startJ+2;
            minI=coordinates[3][0];
            maxI=coordinates[0][0];
            minJ=coordinates[0][1];
            maxJ=coordinates[3][1]; 
             for(int i=1;i<3;i++)
                {
                    if( (startI+i)<19 &&(startJ+1)<19 && brd.boardMatrix[startI+i][startJ+1]==' ')
                        countSpace++; 
                }
            System.out.println(countSpace);
            if(countSpace==2)
                canRotate=true;
            else
                canRotate=false;
        }
        else if(version==2)
        {
        coordinates[0][0]=startI;
        coordinates[0][1]=startJ;
        coordinates[1][0]=startI;
        coordinates[1][1]=startJ+1;
        coordinates[2][0]=startI+1;
        coordinates[2][1]=startJ+1;
        coordinates[3][0]=startI+2;
        coordinates[3][1]=startJ+1;
            minI=coordinates[0][0];
            maxI=coordinates[3][0];
            minJ=coordinates[0][1];
            maxJ=coordinates[3][1];
               if( (startI)<19 &&(startJ+2)<19 &&  brd.boardMatrix[startI][startJ+2]==' ')
                        countSpace++; 
               if( (startI+1)<19 &&(startJ)<19 &&  brd.boardMatrix[startI+1][startJ]==' ')
                        countSpace++; 
            System.out.println(countSpace);
            if(countSpace==2)
                canRotate=true;
            else
                canRotate=false;
        }
        else if(version==3)
        {
        coordinates[0][0]=startI;
        coordinates[0][1]=startJ;
        coordinates[1][0]=startI;
        coordinates[1][1]=startJ+1;
        coordinates[2][0]=startI;
        coordinates[2][1]=startJ+2;
        coordinates[3][0]=startI+1;
        coordinates[3][1]=startJ;
            minI=coordinates[0][0];
            maxI=coordinates[3][0];
            minJ=coordinates[0][1];
            maxJ=coordinates[2][1];
            for(int i=0;i<2;i++)
                {
                    if((startI+2)<19&&(startJ+i)<19&&brd.boardMatrix[startI+2][startJ+i]==' ')
                        countSpace++; 
                }
            System.out.println(countSpace);
            if(countSpace==2)
                canRotate=true;
            else
                canRotate=false;
        }
    }
                ////-----------------I_SHAPE----------------------------------
    public void fillCoordinates1(Board brd)
    {
        countSpace=0;
        if(version%2==0)
        {
        coordinates[0][0]=startI;
        coordinates[0][1]=startJ;
        coordinates[1][0]=startI+1;
        coordinates[1][1]=startJ;
        coordinates[2][0]=startI+2;
        coordinates[2][1]=startJ;
        coordinates[3][0]=startI+3;
        coordinates[3][1]=startJ;
            minI=coordinates[0][0];
            maxI=coordinates[3][0];
            minJ=coordinates[0][1];
            maxJ=coordinates[0][1];
            for(int j=1;j<=3;j++)
                {
                    if( (startI)<19&&(startJ+j)<19&&brd.boardMatrix[startI][startJ+j]==' ')
                        countSpace++; 
                }
            
            System.out.println(countSpace);
            if(countSpace==3)
                canRotate=true;
            else
                canRotate=false;
        }
        else if (version%2==1)
        {
        coordinates[0][0]=startI;
        coordinates[0][1]=startJ;
        coordinates[1][0]=startI;
        coordinates[1][1]=startJ+1;
        coordinates[2][0]=startI;
        coordinates[2][1]=startJ+2;
        coordinates[3][0]=startI;
        coordinates[3][1]=startJ+3;
            minI=coordinates[0][0];
            maxI=coordinates[0][0];
            minJ=coordinates[0][1];
            maxJ=coordinates[3][1];
            for(int j=1;j<=3;j++)
                {
                    if( (startI+j)<19 &&(startJ)<19 &&  brd.boardMatrix[startI+j][startJ]==' ')
                        countSpace++; 
                }
            
            System.out.println(countSpace);
            if(countSpace==3)
                canRotate=true;
            else
                canRotate=false;
        }
    }
        ////-----------------Z_SHAPE----------------------------------
    public void fillCoordinates2(Board brd)
    {
         countSpace=0;
        if(version%2==0)
        {
        coordinates[0][0]=startI;
        coordinates[0][1]=startJ;
        coordinates[1][0]=startI+1;
        coordinates[1][1]=startJ;
        coordinates[2][0]=startI+1;
        coordinates[2][1]=startJ+1;
        coordinates[3][0]=startI+2;
        coordinates[3][1]=startJ+1;
            minI=coordinates[0][0];
            maxI=coordinates[3][0];
            minJ=coordinates[0][1];
            maxJ=coordinates[3][1];
            for(int j=0;j<2;j++)
                {
                    if( (startI-j)<19 &&(startJ+1)<19 &&  brd.boardMatrix[startI-j][startJ+1]==' ')
                        countSpace++; 
                }
               if( (startI-1)<19 &&(startJ+2)<19 &&  brd.boardMatrix[startI-1][startJ+2]==' ')
                        countSpace++; 
            
            System.out.println(countSpace);
            if(countSpace==3)
                canRotate=true;
            else
                canRotate=false;
        }
        else if(version%2==1)
        {
        coordinates[0][0]=startI;
        coordinates[0][1]=startJ;
        coordinates[1][0]=startI;
        coordinates[1][1]=startJ+1;
        coordinates[2][0]=startI-1;
        coordinates[2][1]=startJ+1;
        coordinates[3][0]=startI-1;
        coordinates[3][1]=startJ+2;
            minI=coordinates[2][0];
            maxI=coordinates[0][0];
            minJ=coordinates[0][1];
            maxJ=coordinates[3][1];
            for(int j=0;j<2;j++)
                {
                    if( (startI+1)<19 &&(startJ+j)<19 &&  brd.boardMatrix[startI+1][startJ+j]==' ')
                        countSpace++; 
                }
               if( (startI+2)<19 &&(startJ+1)<19 &&  brd.boardMatrix[startI+2][startJ+1]==' ')
                        countSpace++; 
            
            System.out.println(countSpace);
            if(countSpace==3)
                canRotate=true;
            else
                canRotate=false;
        }
        
    }
       ////-----------------Sq_SHAPE---------------------------------
    public void fillCoordinates3(Board brd)
    {
         countSpace=0;
        coordinates[0][0]=startI;
        coordinates[0][1]=startJ;
        coordinates[1][0]=startI+1;
        coordinates[1][1]=startJ;
        coordinates[2][0]=startI;
        coordinates[2][1]=startJ+1;
        coordinates[3][0]=startI+1;
        coordinates[3][1]=startJ+1;
            minI=coordinates[0][0];
            maxI=coordinates[3][0];
            minJ=coordinates[0][1];
            maxJ=coordinates[3][1]; 
    }
    
      ////-----------------T_SHAPE----------------------------------
    public void fillCoordinates4(Board brd)
    {countSpace=0;
        if(version==0)
        { 
        coordinates[0][0]=startI;
        coordinates[0][1]=startJ;
        coordinates[1][0]=startI;
        coordinates[1][1]=startJ+1;
        coordinates[2][0]=startI;
        coordinates[2][1]=startJ+2;
        coordinates[3][0]=startI+1;
        coordinates[3][1]=startJ+1;
            minI=coordinates[0][0];
            maxI=coordinates[3][0];
            minJ=coordinates[0][1];
            maxJ=coordinates[2][1];
            for(int j=1;j<3;j++)
                {
                    if( (startI+j)<19 &&(startJ)<19 &&  brd.boardMatrix[startI+j][startJ]==' ')
                        countSpace++; 
                }

            System.out.println(countSpace);
            if(countSpace==2)
                canRotate=true;
            else
                canRotate=false;
        }
        else if(version==1)
        {
        coordinates[0][0]=startI;
        coordinates[0][1]=startJ;
        coordinates[1][0]=startI+1;
        coordinates[1][1]=startJ;
        coordinates[2][0]=startI+2;
        coordinates[2][1]=startJ;
        coordinates[3][0]=startI+1;
        coordinates[3][1]=startJ+1;
            minI=coordinates[0][0];
            maxI=coordinates[2][0];
            minJ=coordinates[0][1];
            maxJ=coordinates[3][1];
            for(int j=1;j<3;j++)
                {
                    if( (startI)<19 &&(startJ+j)<19 &&  brd.boardMatrix[startI][startJ+j]==' ')
                        countSpace++; 
                }
               if( (startI-1)<19 &&(startJ+1)<19 &&  brd.boardMatrix[startI-1][startJ+1]==' ')
                        countSpace++; 
            
            System.out.println(countSpace);
            if(countSpace==3)
                canRotate=true;
            else
                canRotate=false;
        }
        else if(version==2)
        {
        coordinates[0][0]=startI;
        coordinates[0][1]=startJ;
        coordinates[1][0]=startI;
        coordinates[1][1]=startJ+1;
        coordinates[2][0]=startI;
        coordinates[2][1]=startJ+2;
        coordinates[3][0]=startI-1;
        coordinates[3][1]=startJ+1;
            minI=coordinates[3][0];
            maxI=coordinates[0][0];
            minJ=coordinates[0][1];
            maxJ=coordinates[2][1];
            for(int j=1;j<3;j++)
                {
                    if( (startI+j)<19 &&(startJ)<19 &&  brd.boardMatrix[startI+j][startJ]==' ')
                        countSpace++; 
                }
               if( (startI+1)<19 &&(startJ-1)<19 &&  brd.boardMatrix[startI+1][startJ-1]==' ')
                        countSpace++; 
            
            System.out.println(countSpace);
            if(countSpace==3)
                canRotate=true;
            else
                canRotate=false;
        }
        else if(version==3)
        {
        coordinates[0][0]=startI;
        coordinates[0][1]=startJ;
        coordinates[1][0]=startI+1;
        coordinates[1][1]=startJ;
        coordinates[2][0]=startI+2;
        coordinates[2][1]=startJ;
        coordinates[3][0]=startI+1;
        coordinates[3][1]=startJ-1;
            minI=coordinates[0][0];
            maxI=coordinates[2][0];
            minJ=coordinates[3][1];
            maxJ=coordinates[0][1];
            for(int j=1;j<3;j++)
                {
                    if( (startI)<19 &&(startJ+j)<19 &&  brd.boardMatrix[startI][startJ+j]==' ')
                        countSpace++; 
                }
               if( (startI+1)<19 &&(startJ+1)<19 &&  brd.boardMatrix[startI+1][startJ+1]==' ')
                        countSpace++; 
            
            System.out.println(countSpace);
            if(countSpace==3)
                canRotate=true;
            else
                canRotate=false;
        }
        
    }
}
//------------------Board-----------------------------------
class Board {
   public char boardMatrix[][]=new char[20][20];
   public void createboard()
   {
    for(int i=0;i<20;i++)
    {
        for(int j=0;j<20;j++)
        {
            if(i==0||j==0||i==19||j==19)
                boardMatrix[i][j]='*';
            else 
                boardMatrix[i][j]=' ';
        }
    }
   }
    
   public void print()
    {
       for(int i=0;i<20;i++)
    {
        for(int j=0;j<20;j++)
        {
            System.out.print(boardMatrix[i][j]);
        }
           System.out.println();
    } 
    }
    
}
//------------------Main------------------------------------
class Game{
    
    
    
    public static boolean notInObj(shape obj,int x,int y)
    {
        boolean flag=true;
        for(int i=0;i<4;i++)
        {
          if(obj.coordinates[i][0]==x && obj.coordinates[i][1]==y) 
             {
               flag=false;
               break;
               }
        } 
        return flag;
    }
    
    public static boolean collides(shape obj,Board b)
    {
        int flag=0;
        for(int i=0;i<4;i++)
        {
            int x=obj.coordinates[i][0];
            int y=obj.coordinates[i][1];
          if(b.boardMatrix[x+1][y]=='#'&&notInObj(obj,x+1,y))
          {
              flag=1;
              break;
          }
        }
        if(flag==1)
            return true;
        else
            return false;
    }
    
    public static boolean noLeftCollision(shape obj,Board b)
    {
     
        int flag=0;
        for(int i=0;i<4;i++)
        {
            int x=obj.coordinates[i][0];
            int y=obj.coordinates[i][1];
          if( b.boardMatrix[x][y-1]=='#'&&notInObj(obj,x,y-1))
          {
              flag=1;
              break;
          }
        }
        if(flag==1)
            return false;
        else
            return true;
        
        
    }
    
    public static boolean noRightCollision(shape obj,Board b)
    {
        int flag=0;
        for(int i=0;i<4;i++)
        {
            int x=obj.coordinates[i][0];
            int y=obj.coordinates[i][1];
          if( b.boardMatrix[x][y+1]=='#'&&notInObj(obj,x,y+1))
          {
              flag=1;
              break;
          }
        }
        if(flag==1)
            return false;
        else
            return true;
        
    }
    
    public static void comeDown(shape obj,int arr[],Board b,int x)
    {
        for(int i=x-1;arr[i]!=0;i--)
        {
            for(int j=1;j<19;j++)
            {
                b.boardMatrix[i+1][j]=b.boardMatrix[i][j];
            }
            arr[i+1]=arr[i];
        }
    }
    
    public static void printEnd()
    {
        System.out.println("                                                             ****   ");
             System.out.println("                                                             ****   ");
             System.out.println(" ************        ****         ***          ******        ****   ");
             System.out.println(" ************        *****        ***          ***  ***      ****    ");
             System.out.println(" ***                 *** **       ***          ***   ***     ****    ");
             System.out.println(" ***                 ***  **      ***          ***    ***    ****        ");
             System.out.println(" *********           ***   **     ***          ***     ***   ****        ");
             System.out.println(" *********           ***    **    ***          ***     ***   ****       ");
             System.out.println(" ***                 ***     **   ***          ***    ***    ****       ");
             System.out.println(" ***                 ***      **  ***          ***   ***            ");
             System.out.println(" ***********         ***       ******          ***  ***            ");
             System.out.println(" ***********         ***        *****          ******        |**| ");  
    }
    
    public static char chkQuit(int arr[],char a,int arr2[])
    {
        char ch=a;
        for(int i=1;i<19;i++)
        {
            if(arr[i]>=17)
            {ch='q';
             
            break;}
        }
        if(arr2[2]>0)
            ch='q';
        return ch;
    }
    
    static int flag=0;
    public static void chkAndDelete(Board b,shape obj,int arr[],Stack<Character> activity,LinkedStack stack)
    {
        for(int i=0;i<4;i++)
                  {
            System.out.println(arr[obj.coordinates[i][0]]+";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                  if(arr[obj.coordinates[i][0]]>=18) 
          {for(int j=1;j<19;j++)
          {
              b.boardMatrix[obj.coordinates[i][0]][j]=' '; 
          }
           System.out.println("DELETED");
           activity.clear();
           stack.clear();
           flag=1;
            arr[obj.coordinates[i][0]]=0;
           comeDown(obj,arr,b,obj.coordinates[i][0]);
          }
                  }  
    }
    
    public static void delete_(Board brd,shape obj)
    {
        for(int i=0;i<4;i++)
        {
          brd.boardMatrix[obj.coordinates[i][0]][obj.coordinates[i][1]]=' '; 
        } 
    }
      public static void fix_(Board brd,shape obj)
    {
        for(int i=0;i<4;i++)
        {
            System.out.println(obj.coordinates[i][0]+"___"+obj.coordinates[i][1]);
          brd.boardMatrix[obj.coordinates[i][0]][obj.coordinates[i][1]]='#'; 
        } 
    }
    
    public static void reflex(Board b,shape obj,shapest s)
    {   
        obj.startI=s.x;
        obj.startJ=s.y;
        System.out.println(s.shape1+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        
    }
    
    public static void main(String args[]) throws Exception
    {
        int count_X[]=new int[20];
        int count_Y[]=new int[20];
        shape obj=new shape();
        Board brd=new Board();
        brd.createboard();
        Scanner s=new Scanner(System.in);
//----------Undo Stack Creating------------
        LinkedStack stack = new LinkedStack();
        Stack<Character> activity=new Stack<Character>();  
//-------------------------------------Redo Stacks ---------
        LinkedStack stack2 = new LinkedStack();
        Stack<Character> activity2=new Stack<Character>();
        
        
        
        char c='w';
        int random=3;
        shapest obj2=new shapest();
        shapest obj3=new shapest();
        while(c!='q')
        {
         
            
        
        obj.fillCoordinates(brd,random);
        obj.fillboard(brd);
        brd.print();  
            
                while(c!='q')
                {

                 c=s.next().charAt(0);
                    if(c!='u'&&c!='i'&&c!='p')
                    {
                        activity2.clear();
                        stack2.clear();
                    }
                  new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
                if(c!='q')
                {
                    
                    //---file Save--code----
                    
                    if(c=='0')
                      { Data d=new Data();
                        d.read(brd.boardMatrix,random,obj.startI,obj.startJ);
                        FileOutputStream fos=new FileOutputStream("A.data");
                        ObjectOutputStream oos=new ObjectOutputStream(fos);
                        oos.writeObject(d);
                        oos.flush();
                        oos.close();
                        fos.close();
                        
                        printEnd();
                        System.out.println("DATA HAS BEEN SAVED IN FILE");
                        
                         FileInputStream fis=new FileInputStream("A.data");
                            ObjectInputStream ois=new ObjectInputStream(fis);
                            Data d2=(Data)ois.readObject();
                            d2.display();
                         ois.close();
                        fis.close();
                        System.exit(1);
                        }
                    
                    //----starting from old saved file---------
                    
                    
                    if(c=='1')
                    {
                    
                     FileInputStream fis=new FileInputStream("A.data");
                            ObjectInputStream ois=new ObjectInputStream(fis);
                            Data d2=(Data)ois.readObject();
                        ois.close();
                        fis.close();
                       if(d2!=null) 
                      {  for(int i=0;i<20;i++)
                        {
                        for(int j=0;j<20;j++)
                        {
                        brd.boardMatrix[i][j]=d2.boardGrid[i][j];
                        }
                        }
                        random=d2.shapeType;
                        obj=new shape();
                        obj.startI=d2.x;
                        obj.startJ=d2.y;
                        obj.fillCoordinates(brd,random);
                        
                        stack.clear();
                        activity.clear();
                        }
                    
                    }
                    
                    //-------New Game -----------
                    if(c=='n')
                    {
                        System.out.println("NEW GAME STARTS");
                        brd.createboard();
                        obj=new shape();
                        obj.startI=1;
                        obj.startJ=(int)(Math.random()*15)+1;
                        random=3;
                        obj.fillCoordinates(brd,random);
                        Data d=new Data();
                        d.read(brd.boardMatrix,random,obj.startI,obj.startJ);
                        FileOutputStream fos=new FileOutputStream("A.data");
                        ObjectOutputStream oos=new ObjectOutputStream(fos);
                        oos.writeObject(d);
                        oos.flush();
                        oos.close();
                        fos.close();
                        
                    }
                    
                    ///REDO CODES --------------------------------------------------------------------------------------
                    
                    
                    
                    
                    if(c=='i')
                    {
                        if(activity2.empty()==true)
                        {
                            System.out.println("NO Redo Possible");
                            
                            
                        }
                        else
                        {char c2=activity2.pop();
                         activity.push(c2);
                         
                         System.out.println(c2+"<---C2");
                         if(c2=='l'&&obj.canRotate)
                    obj.version++;
                    if(c2=='r' && obj.canRotate)
                    obj.version--;
                    if(obj.version<0)
                    obj.version=3;
                    if(c2=='d'&& noRightCollision(obj,brd))
                    obj.right();
                    if(c2=='a'&& noLeftCollision(obj,brd))
                    obj.left();
                          if(c2=='s')
                    obj.down();
                        if(c2=='1')
                        {
                            
                           System.out.println("TIME TO Refrom"+obj.startI+"_"+obj.startJ);
                                            fix_(brd,obj);
                                            shapest ss=stack2.pop();
//                            brd.print();
//                            System.exit(1);
                                            obj3=new shapest();
                                            obj3.shape1=ss.shape1;
                                            obj3.x=obj.startI;
                                            obj3.y=obj.startJ;
                                            stack.push(obj3);
                                            reflex(brd,obj,ss);
                                            random++;
                                            if(random>4)
                                                random=0;
                        }
                    }
                    }
                    
                    
                    //==================================================================================================================================
                    if(c=='l'&&obj.canRotate)
                    obj.version++;
                    if(c=='r' && obj.canRotate)
                    obj.version--;
                    if(obj.version<0)
                    obj.version=3;
                    if(c=='d'&& noRightCollision(obj,brd))
                    obj.right();
                    if(c=='a'&& noLeftCollision(obj,brd))
                    obj.left();
                    
                    //----------------UNDOS CODE------------------------
                    if(c=='u')
                    {
                        if(activity.empty()==true||stack.empty()==true)
                            System.out.println("No Further UNDO's");
                        else if(stack.empty()!=true)
                        {
                                     char ch=activity.pop();
                                      activity2.push(ch);                            
                                      {
                                            if(ch=='l'&&obj.canRotate)
                                        obj.version--;
                                        if(ch=='r' && obj.canRotate)
                                        obj.version++;
                                        if(obj.version<0)
                                        obj.version=3;
                                        if(ch=='d'&& noLeftCollision(obj,brd))
                                        obj.left();
                                        if(ch=='a'&& noRightCollision(obj,brd))
                                        obj.right();
                                        if(ch=='s')
                                        obj.up();
                                        if(ch=='1')
                                        {
                                            System.out.println("TIME TO DELETE");
                                            delete_(brd,obj);
                                            shapest ss=stack.pop();
                                            obj3=new shapest();
                                            obj3.shape1=ss.shape1;
                                            obj3.x=obj.startI;
                                            obj3.y=obj.startJ;
                                            stack2.push(obj3);
                                            reflex(brd,obj,ss);
                                            random--;
                                            if(random<0)
                                                random=4;
                                            for(int i=0;i<4;i++)
                                           {
                                             System.out.println(obj.coordinates[i][0]);
                                             System.out.println( obj.coordinates[i][1]);
                                             count_X[obj.coordinates[i][0]]--;
                                             count_Y[obj.coordinates[i][1]]--;    
                                            }  
                                        }
                                        }
                    }
                    }
                    
                    
                    if(c=='s')
                    obj.down();
                    
                    if(c!='u'&&c!='i')
                 activity.push(c);
                    
                    
                    //-------------Printing  Redo Stack ------------
                    
                    if(c=='p')
                    {
                        System.out.println("Activity Redo:::::");
                        while(activity2.empty()==false)
                        {
                            System.out.println(activity2.pop());
                        }
                        System.out.println("Shape Redo:::::");
                        while(stack2.empty()==false)
                        {
                            shapest we=new shapest();
                            we=stack2.pop();
                            System.out.println("Shape->"+we.shape1+"X->"+we.x+"Y->"+we.y);
                            
                        }
                        System.out.println("::::::::::END:::::::::");
                    }
                    
                    
                    //--------------------Printing Undo Stack----------------
                    
                     if(c=='m')
                    {
                        System.out.println("Activity Undo:::::");
                        while(activity.empty()==false)
                        {
                            System.out.println(activity.pop());
                        }
                        System.out.println("Shape Undo:::::");
                        while(stack.empty()==false)
                        {
                            shapest we=new shapest();
                            we=stack.pop();
                            System.out.println("Shape->"+we.shape1+"X->"+we.x+"Y->"+we.y);
                            
                        }
                        System.out.println(":::::::::END::::::::"+random);
                         
                    }
                    
        
                  obj.version=obj.version%4;
                  obj.removeOldCoordinates(brd); 
                     if(c=='i'&&(obj.maxI==18 || collides(obj,brd)))
                      {for(int i=0;i<4;i++)
                        {System.out.println(obj.coordinates[i][0]+"I<<<");
                          System.out.println( obj.coordinates[i][1]+"J<<<");
                          brd.boardMatrix[obj.coordinates[i][0]][obj.coordinates[i][1]]='#'; 
                          count_X[obj.coordinates[i][0]]++;
                          count_Y[obj.coordinates[i][1]]++;
                        }  
                    }
                  obj.fillCoordinates(brd,random);
                  obj.fillboard(brd);
                  brd.print();
                   
                  if(c!='u'&&c!='i'&&(obj.maxI==18 || collides(obj,brd)))
                  { 
                      System.out.println("ENDGAME");
                      for(int i=0;i<4;i++)
                  {
                          System.out.println(obj.coordinates[i][0]);
                          System.out.println( obj.coordinates[i][1]);
                  brd.boardMatrix[obj.coordinates[i][0]][obj.coordinates[i][1]]='#'; 
                          count_X[obj.coordinates[i][0]]++;
                          count_Y[obj.coordinates[i][1]]++;
                  }  
                      c=chkQuit(count_Y,c,count_X);
                      obj2.shape1=random;
                      obj2.x=obj.startI;
                      obj2.y=obj.startJ;
                       stack.push(obj2);
                      System.out.println("Time for chk and delete");
                      chkAndDelete(brd,obj,count_X,activity,stack);
                      if(flag==1)
                      {
                          activity.clear();
                          stack.clear();
                          flag=0;
                      }
                      random++;
                      random=random%5;
                      break;
                    }
                    
                    
                    
                    
                }
                }
            if(c=='q')
                break;
            if(c!='u')
            obj=new shape();
            
           
            obj2=new shapest();
            activity.push('1');
    }
        printEnd();
    }
    
    
    
}