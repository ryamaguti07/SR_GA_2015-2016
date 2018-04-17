
public class knn_test {
	knn r1 = new knn();
	private static double knn_mae;
	private static double knn_matrix_test[][] = new double[matriz.u.length][matriz.u[0].length];
	public void create()
	{
		int count=0;
		double sum = 0,aux,knn;
		for (int i = 3200; i < matriz.u.length; i++)
		{
			
			
		
			 for(int j = 0; j<matriz.u[0].length; j++)
			 {
				 if(matriz.u[i][j]!=-1)
				 {	 
					
					 count++;
					 knn = (r1.getKnn_test( i, j));
					 aux = matriz.u[i][j] - knn;
					 knn_matrix_test[i][j] = knn;
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
	public static double getKnnMae()
	{
		return knn_mae;
	}
	
	public static double getKnnRating(int usr, int item)
	{
		return knn_matrix_test[usr][item];
	}
	
}

