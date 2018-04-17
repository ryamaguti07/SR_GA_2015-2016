
public class knn {
	
	private static final int k = 5;
	private double nghood[] = new double[k];
	private int k_close[] = new int[k];
	
	public knn()
	{
		for(int i=0;i<k;i++)
			this.nghood[i]=0;
	}
	public void createNghood(int trg_usr)
	{
		correlation pearson_correlation = new correlation();
		double aux;
	
		for (int i=0;i<matriz.train.length;i++)
		{
			if(i!=trg_usr)
			{
				 aux = pearson_correlation.getPearson(matriz.train[trg_usr], matriz.train[i]);
				for(int j=0;j<k;j++)
				{
					
					if (aux>=nghood[j])
					{
						this.nghood[j] = aux;
						this.k_close[j] = i;
					}
				}
			}
				
		}
	}
	public double getKnn(int trg_usr,int trg_item)
	{
		
		int count2 = 0;
		double mean=0,count=0, mean2=0;
		this.createNghood(trg_usr);
		for (int i=0;i<matriz.train[0].length;i++)
		{
			if(matriz.train[trg_usr][i] !=-1)
			{
				mean += matriz.train[trg_usr][i];
				count++;
			}
		}
		mean = mean/count;
		double n=0,d=0,p;
		for(int i=0;i<k;i++)
		{
			for (int j=0;j<matriz.train[0].length;j++)
			{
				if(matriz.train[this.k_close[i]][j] !=-1)
				{
					mean2 += matriz.train[this.k_close[i]][j];
					count2++;
				}
			}
			mean2 = mean2/count2;
			n =n + this.nghood[i]*(matriz.train[this.k_close[i]][trg_item] - mean2); 
			d +=this.nghood[i];
		}
		
		p = mean +n/d ;
		return p;
	}
	public void createNghood_test(int trg_usr)
	{
		correlation pearson_correlation = new correlation();
		double aux;
	
		for (int i=0;i<matriz.u.length;i++)
		{
			if(i!=trg_usr)
			{
				 aux = pearson_correlation.getPearson(matriz.u[trg_usr], matriz.u[i]);
				for(int j=0;j<k;j++)
				{
					
					if (aux>=nghood[j])
					{
						this.nghood[j] = aux;
						this.k_close[j] = i;
					}
				}
			}
				
		}
	}
	public double getKnn_test(int trg_usr,int trg_item)
	{
		
		int count2 = 0;
		double mean=0,count=0, mean2=0;
		this.createNghood_test(trg_usr);
		for (int i=0;i<matriz.u[0].length;i++)
		{
			if(matriz.u[trg_usr][i] !=-1)
			{
				mean += matriz.u[trg_usr][i];
				count++;
			}
		}
		mean = mean/count;
		double n=0,d=0,p;
		for(int i=0;i<k;i++)
		{
			for (int j=0;j<matriz.u[0].length;j++)
			{
				if(matriz.u[this.k_close[i]][j] !=-1)
				{
					mean2 += matriz.u[this.k_close[i]][j];
					count2++;
				}
			}
			mean2 = mean2/count2;
			n =n + this.nghood[i]*(matriz.u[this.k_close[i]][trg_item] - mean2); 
			d +=this.nghood[i];
		}
		
		p = mean +n/d ;
		
		return p;
		
	}
}
