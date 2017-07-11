import java.io.*;
import java.util.*;

public class FSreader 
{
  public static String read(String path)
 {   
	 StringBuilder s= new StringBuilder("");
    try
   {   
	    FileInputStream f1 = new FileInputStream("./Downloads/Flat File System/FI.txt");
	    FileInputStream f2 = new FileInputStream("./Downloads/Flat File System/FS.txt");
	    byte[] arr = new byte[48];
	    String R[]= new String[20];
	    int c=0;
	    byte[][] b = new byte[8192][128];
		int k;
		StringTokenizer st = new StringTokenizer(path,"/");
		String lname="";
		String dname="";
		  
	     while(st.hasMoreTokens()){
	   	 dname=lname;
	   	 lname=st.nextToken();}
	     for( k=0;k<8192;k++)f2.read(b[k],0,128);
				
	while((f1.read(arr,0,48))!=-1)
    {       int j;
			int d=0;
			int x=0;
			int a=0;
			for( j=0;j<32;j++) if(arr[j]==0)break;
			String name = new String(arr,0,j);
			R[c++]=name;
			 
	 if(lname.equals(name))
	{
		//type of file 
			if(arr[33]<0){
				a=arr[33]+256; 
				}
			else a=arr[33];
			if(a==0){}
			else 
				if(a==128){}
				else if(a==160){}
			 
		//directory entry
			if(arr[34]==0&&arr[35]==0)//do nothing for root directory{}
	        if(arr[34]<0){ 
	          d=(arr[34]+255)*256;}
			else d=(arr[34]-1)*256;
			if(arr[35]<0)
				d=d+arr[35]+255;
			else d=d+arr[35]-1;			
			 
if(R[d].equals(dname))
		
{
		//size	
				int size=0;
				int n=0;
				for(j=36;j<40;j++){
					size=size*256+arr[j];
					
				}
				if(size>512){	
				n=(size-512)/126;		
				}
				
			//blocks
				for(j=40;j<48;j++)
				{
					
					if(arr[j]!=0){					
					if(j%2==0){						
						if(arr[j]<0)x=256*(arr[j]+256);
						else x=256*arr[j];
					         }
					else {
						if(arr[j]<0){x=x+arr[j]+256;}
						else x=x+arr[j];
						if(j==47&&n!=0){s.append(new String(b[x]),0,126);}
						else s.append(new String(b[x]));
						}
					}
				}
				
				int y=0;
				n+=2;
				while(n!=0)
				{
   				if(b[x][126]<0)	
					y=(b[x][126]+256)*256;
				else
					y=(b[x][126])*256;
				if(b[x][127]<0)	
					y=y+b[x][127]+256;
				else
					y=y+b[x][127];				
				s.append(new String(b[y],0,126));
				n--;
				x=y;
				}
				s.append(new String(b[y+1]).trim());
				
}
							 
    } 
    }
	              
    }catch(Exception e){System.out.println(e);}   
             if(s.equals("")){return null;} 
             return s.toString();
		
 }
	
 public static void main(String args[]){
 	    Scanner scan = new Scanner(System.in);
		String asd=scan.next();
		scan.close();
		System.out.println(FSreader.read(asd));
		           
       }	
}