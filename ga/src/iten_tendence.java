
public class iten_tendence {
	
	
	public double getItenMean(int trg_iten[]) // calcula a media que os usuarios avaliram esse item
	{
		double mean =0;
		int zeros=0;
		for (int i = 0;i<trg_iten.length;i++ )
		{
			if (trg_iten[i] != -1) //calcula apenas para itens avaliados
			{
				mean += (double)trg_iten[i];
			}
			else
			{
				zeros++; // ve quantos itens nao foram avaliados
			}
		}
		mean = (mean/(trg_iten.length-zeros)); // faz a media
		return mean;
		
	}
	
	public double getItenTendence(int trg_iten[],int trg_usr[]) // calcula a tendencia desse item
	{
		
		double t = 0,mean=0;
		int zeros =0;
		
		user_tendence usr_mean = new user_tendence();
		mean = usr_mean.getUserMean(trg_usr);
		for (int i = 0;i<trg_iten.length;i++ )
		{
			if (trg_iten[i] != -1) //faz o calculo apenas para itens avaliados
			{
				t += (trg_iten[i]-mean);
			}
			else
			{
				zeros++;
			}
		}
		t = t/(trg_iten.length-zeros);
		return t; 
	}


}
