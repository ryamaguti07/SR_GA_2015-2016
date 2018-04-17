
public class matrix_aux {
	
	
	public double Choser(int user_item[][],int usr, int item)
	{
		int[] item_array = new int [user_item[0].length];
		double p=0;
		prediction aux = new prediction();
		
		for (int count = 0 ; count<user_item[0].length; count ++)
		{
			
			item_array[count]=0;
		}
		for (int i =0 ; i<= user_item.length;i++)
		{
			for(int j=0;j<=user_item[0].length;j++)
			{
				if (j == item)
				{
					item_array[i]=user_item[i][j];
				}
			}
		}
		p = aux.getPrediction(user_item[usr], item_array);
		
		return p;
	}
}