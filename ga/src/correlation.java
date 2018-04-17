public class correlation {
		public  double getPearson(int[] usr1,int[] usr2){
		double result = 0;
		double sum_sq_x = 0;
		double sum_sq_y = 0;
		double sum_coproduct = 0;
		double mean_x = usr1[0];
		double mean_y = usr2[0];
		
		for(int i=2;i<usr1.length+1;i++)
		{
			
				double sweep =Double.valueOf(i-1)/i;
				double delta_x = usr1[i-1]-mean_x;
				double delta_y = usr2[i-1]-mean_y;
				sum_sq_x += delta_x * delta_x * sweep;
				sum_sq_y += delta_y * delta_y * sweep;
				sum_coproduct += delta_x * delta_y * sweep;
				mean_x += delta_x / i;
				mean_y += delta_y / i;
	
		}
		double pop_sd_x = (double) Math.sqrt(sum_sq_x/usr1.length);
		double pop_sd_y = (double) Math.sqrt(sum_sq_y/usr1.length);
		double cov_x_y = sum_coproduct / usr1.length;
		result = cov_x_y / (pop_sd_x*pop_sd_y);
		return result;
		}
		
}
