public class mse {
		 
	private double total_mse;
	
	
	public double getMSE(int w1,int w2, int w3)
	{

	double sum = 0,aux=0;
	int count = 0;

	for (int i = 0; i < matriz.train.length; i++)
	{

		for(int j = 0; j<matriz.train[0].length; j++)
		{
			if(matriz.train[i][j]!=-1)
			{	
				count ++;
				int size = matriz.train.length;
				int[] item_array = new int[size];
				for (int k=0;k<size;k++){
					item_array[k] = matriz.train[k][j];
				}
				aux =   Math.pow((matriz.train[i][j] - ((w1*Knn_mae.getKnnRating(i, j)) + (w2*tendence_mae.getTendenceRating(i, j))+(w3*slopeOne_mae.getTendenceRating(i, j)))/(w1+w2+w3)),2);
				sum += aux;
			}
		}

	}
	aux = sum/count;
	total_mse = aux;
	return total_mse;
	}
	
	
	
	
}
