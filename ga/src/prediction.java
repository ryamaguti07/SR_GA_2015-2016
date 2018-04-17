
public class prediction {

	
	public double  getPrediction (int trg_user[],int trg_iten[])
	{
		double b = 0.5;
		double p=0;
		double usr_mean=0,iten_mean=0,usr_tend=0,iten_tend=0; 
		user_tendence u = new user_tendence();
		iten_tendence i = new iten_tendence();
		
		usr_mean = u.getUserMean(trg_user);
		iten_mean = i.getItenMean(trg_iten);
		usr_tend = u.getUserTendence(trg_user,trg_iten);
		iten_tend =  i.getItenTendence(trg_iten, trg_user);
		
		if (usr_tend>=iten_mean && iten_tend>=usr_mean)
		{
			p = Math.max((usr_mean+iten_mean), (iten_mean+usr_tend));
			
		}
		else if(usr_tend<iten_mean && iten_tend<usr_mean) {
			p = Math.min((usr_mean+iten_mean), (iten_mean+usr_tend));
		
		}
		else if(usr_tend<iten_mean && iten_tend>usr_mean){
			p = Math.min(Math.max(usr_mean, (iten_mean+usr_tend)*b +(usr_mean+iten_tend)*(1-b)), iten_mean);
		
		}
		else{
			
			p = iten_mean*b + usr_mean*(1-b);
		}
		
		return p;
	}
	
	
}
