import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileReader;  
import java.util.StringTokenizer;
import java.util.Random;
public abstract class  matriz  {
	
	final static double TRAINING = 80;



	protected static int u[][]	= new int[4000][50];
	protected  static int train[][] = new int[3200][50];
	protected static int test[][] = new int[800][50];
	
	
	
	public static void  create() {
		for (int i = 0;i<4000;i++)
		{
			for(int j =0;j<50;j++)
			{
				u[i][j] = -1;
			}
	}
		File f = new File("D:/work (PC)/coloca/bin/assistments_2009_2010-apenas_originais-sem_abertas-cnj-denso-49 (1) - Copia.csv");  
		if(!f.exists())  
		{  System.out.println("nao existe");
			System.exit(-1);  
		}  
		try {  
			BufferedReader in = new BufferedReader(new FileReader(f));  
			String line;   
			while((line = in.readLine())!=null)  
			{    
				StringTokenizer token = new StringTokenizer(line, ";");  
				if(token.countTokens() != 3)   
				{  
					System.err.println("Erro lendo arquivo");  
					System.exit(-1);  
				}  
				int a = 0, b = 0 , c = 0,d=0;  
				while(token.hasMoreElements())  
				{  
					a = Integer.parseInt(token.nextToken());  
					b = Integer.parseInt(token.nextToken()); 
					c = Integer.parseInt(token.nextToken());  
					u[a][b]= c;
			
					
				}  

			}  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  
		
		

		for (int i = 0;i<u.length;i++)
		{
			for(int j = 0;j<u[0].length;j++)
				
			{
				if(i<3200)
				{
					train[i][j] = u[i][j];
				}
				
			}
		}

	
	
	}
	
}



