public class user_tendence  {
	

	
	public double getUserMean(int trg_user[]) //calcula das avaliacoes feitas por um usuario
	{
		double mean =0;
		int zeros = 0;
		for (int i = 0;i<trg_user.length;i++ )
		{
			if (trg_user[i]!=-1)  //considera apenas valores nao nulo, ou seja, valores ja avaliados
			{
				mean += (double)trg_user[i];
			}
			else
			{
				
				zeros++;
			}
		}
		mean = (mean/(trg_user.length-zeros)); // calcula a media
		return mean;
		
	}
	
	public double getUserTendence(int trg_user[],int trg_item[]) // calcula a tendencia de um usuario a avaliar um item
	{
		double t = 0, mean = 0;
		int zeros=0;
		iten_tendence usr_mean = new iten_tendence();
		mean = usr_mean.getItenMean(trg_item);
		for (int i = 0;i<trg_user.length;i++ )
		{
			if (trg_user[i]!=-1) // apenas valores validos
			{
				t += (trg_user[i]-mean);
			}
			else
			{
				zeros++;
			}
		}
		t = t/(trg_user.length-zeros);
		return t; 
	}


}
