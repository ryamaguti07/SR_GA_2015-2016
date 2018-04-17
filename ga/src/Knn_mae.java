
public class Knn_mae {
	knn r1 = new knn();
	private double knn_mae;
	private static double knn_matrix[][] = new double[matriz.train.length][matriz.train[0].length];
	public void create()
	{
		int count=0;
		double sum = 0,aux,knn;
		for (int i = 0; i < matriz.train.length; i++)
		{
			
			
		
			 for(int j = 0; j<matriz.train[0].length; j++)
			 {
				 if(matriz.train[i][j]!=-1)
				 {	 
					
					 count++;
					 knn = (r1.getKnn( i, j));
					 aux = matriz.train[i][j] - knn;
					 knn_matrix[i][j] = knn;
					System.out.println(".");
					 if (aux<0)
					 {
						 aux = aux * (-1);

					 }
					 sum +=aux;
				 }
			 }
		}
		
		this.knn_mae = sum/count;
		
	}
	public double getKnnMae()
	{
		return this.knn_mae;
	}
	
	public static double getKnnRating(int usr, int item)
	{
		return knn_matrix[usr][item];
	}
	
}
