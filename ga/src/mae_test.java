
public class mae_test {

		private double total_mae;
		
		
		public double getMAE(int w1,int w2,int w3)
		{

		double sum = 0,aux=0;
		int count = 0;

		for (int i = 3200; i <matriz.u.length; i++)
		{

			for(int j = 0; j<matriz.u[0].length; j++)
			{
				if(matriz.u[i][j]!=-1)
				{	
					count ++;
					int size = matriz.u.length;
					int[] item_array = new int[size];
					for (int k=0;k<size;k++){
						item_array[k] = matriz.u[k][j];
					}
					aux =   matriz.u[i][j] - ((w1*knn_test.getKnnRating(i, j)) + (w2*tendence_test.getTendenceRating(i, j))+(w3*slopeOne_test.getSlopeOneRating(i, j)))/(w1+w2+w3);
					if(aux < 0)
					{
						aux = aux * (-1);

					}
					sum += aux;
				}
			}

		}
		aux = sum/count;
		total_mae = aux;
		return total_mae;
		
		
		
		
	}
}



