
public class tendence_mae {
	prediction r2 = new prediction();
	private double tendence_mae;
	private static double tendence_matrix[][] = new double[matriz.u.length][matriz.train[0].length];
	public tendence_mae()
	{
		int count = 0;
		double sum = 0,aux,tendence;
		for (int i = 0; i < matriz.u.length; i++)
		{

			for(int j = 0; j<matriz.u[0].length; j++)
			{
				if(matriz.u[i][j]!=-1 && i%3 != 0)
				{
					count ++;
					int size = matriz.u.length;
					int[] item_array = new int[size];
					for (int k=0;k<size;k++){
						item_array[k] = matriz.u[k][j];
					}
					tendence = r2.getPrediction(matriz.u[i], item_array); 
					aux = matriz.u[i][j] - tendence;
					tendence_matrix[i][j] = tendence;
					System.out.println(".");
					if (aux<0)
					{
						aux = aux *(-1);
					}
					sum += aux;
				}
			}
		}
		this.tendence_mae = sum/count;
	}
	
	public double getTendenceMae()
	{
		return this.tendence_mae;
	}
	
	public static double getTendenceRating(int usr, int item)
	{
		return tendence_matrix[usr][item];
	}
}
