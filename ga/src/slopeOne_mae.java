
public class slopeOne_mae {

	SlopeOne r3 = new SlopeOne();
	private double slopeone_mae;
	private static double slopeone_matrix[][] = new double[matriz.u.length][matriz.u[0].length];
	public slopeOne_mae()
	{
		int count = 0;
		double sum = 0,aux,slope;
		for (int i = 0; i < matriz.u.length; i++)
		{

			for(int j = 0; j<matriz.u[0].length; j++)
			{
				if(matriz.u[i][j]!=-1 && i%3 != 0)
				{
					count ++;
					slope = r3.getSlopeOne(matriz.u, i, j);
							aux = matriz.u[i][j] - slope;
					slopeone_matrix[i][j] = slope;
					//System.out.println(" aa    nota: "+matriz.u[i][j]+" slope :"+slope);
					System.out.println(".");
					if (aux<0)
					{
						aux = aux *(-1);
					}
					sum += aux;
				}
			}
		}
		this.slopeone_mae = sum/count;
	}

	public double getSlopeOneMae()
	{
		return this.slopeone_mae;
	}

	public static double getTendenceRating(int usr, int item)
	{
		return slopeone_matrix[usr][item];
	}
}


