
public class slopeOne_test {
	SlopeOne r3 = new SlopeOne();
	private double slopeOne_mae;
	private static double slope_matrix[][] = new double[matriz.u.length][matriz.u[0].length];
	public slopeOne_test()
	{
		int count = 0;
		double sum = 0,aux,slope;
		for (int i = 3200; i <matriz.u.length; i++)
		{

			for(int j = 0; j<matriz.u[0].length; j++)
			{
				if (matriz.u[i][j] != -1)
				{
					count ++;
					slope = r3.getSlopeOne(matriz.u, i, j);
					aux = matriz.u[i][j] - slope;
					slope_matrix[i][j] = slope;
			//		System.out.println("nota: "+matriz.u[i][j]+" slopeP: "+slope);
					if (aux<0)
					{
						aux = aux *(-1);
					}
					sum += aux;
				}
			}
		}
		this.slopeOne_mae = sum/count;
	}
	
	public double getSlopeOneMae()
	{
		return this.slopeOne_mae;
	}
	
	public static double getSlopeOneRating(int usr, int item)
	{
		return slope_matrix[usr][item];
	}
}
