import java.util.Random;
public class individuo {

	cromossomo[] individuo = new cromossomo[3];
	double fitness=0;
	double cfitness = 0;
	double rfitness = 0;
	public individuo(cromossomo pai1,cromossomo pai2,cromossomo pai3)
	{
		individuo[0] = pai1;
		individuo[1] = pai2;
		individuo[2] = pai3;
	}

	public cromossomo corte_cabeca()
	{
	     return individuo[0];
	}
	
	public cromossomo corte_meio()
	{
		return individuo[1];
	}
	
	
	public cromossomo corte_rabo()
	{
	     return individuo[2];
	}
	
	public void mutate()
	{
		 Random rand = new Random();
		 int i = rand.nextInt(3);
		individuo[i].mutate();
		
	}
	public double getFitness() {
        return fitness;
    }


    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

	public double evaluate() {
		double fitness = 0;
		int w1 =0;
		int w2 = 0;
		int w3 =0;
		int cromossomo_aux = 0;
		cromossomo aux = new cromossomo();
		
		aux = this.corte_cabeca();
		for (int j = 0 ;j<8;j++)
		{
			cromossomo_aux = aux.getGene(j);
			w1 += (int) (cromossomo_aux * Math.pow(2,j));
		}
		aux = this.corte_meio();
		for (int j = 0 ;j<8;j++)
		{
			cromossomo_aux = aux.getGene(j);
			w2 += (int) (cromossomo_aux * Math.pow(2,j));
		}
		aux = this.corte_rabo();
		for (int j = 0 ;j<8;j++)
		{
			cromossomo_aux = aux.getGene(j);
			w3 += (int) (cromossomo_aux * Math.pow(2,j));
		}
		//p = (w1*r1 + w2*r2 + w3*r3)/(w1+w2+w3);
		//ver erro e retornar fitness ;s
			mae calc_fit = new mae();
			fitness = calc_fit.getMAE(w1, w2,w3);
			this.setFitness(fitness);
			return this.fitness;

	}
    
    public void print()
    {
    	int w1 =0;
		int w2 = 0;
		int w3 = 0;
    	int cromossomo_aux = 0;
		cromossomo aux = new cromossomo();
		
		aux = this.corte_cabeca();
		for (int j = 0 ;j<8;j++)
		{
			cromossomo_aux = aux.getGene(j);
			w1 += (int) (cromossomo_aux * Math.pow(2,j));
		}
		aux = this.corte_meio();
		for (int j = 0 ;j<8;j++)
		{
			cromossomo_aux = aux.getGene(j);
			w2 += (int) (cromossomo_aux * Math.pow(2,j));
		}
		aux = this.corte_rabo();
		for (int j = 0 ;j<8;j++)
		{
			cromossomo_aux = aux.getGene(j);
			w3 += (int) (cromossomo_aux * Math.pow(2,j));
		}
		System.out.println("w1 : "+w1+"   w2: "+w2 +"  w3:"+w3);
    }
    public int w1()
    {
    	int w1 = 0;
    	int cromossomo_aux = 0;
    	cromossomo aux = new cromossomo();
		
		aux = this.corte_cabeca();
		for (int j = 0 ;j<8;j++)
		{
			cromossomo_aux = aux.getGene(j);
			w1 += (int) (cromossomo_aux * Math.pow(2,j));
		}
		return w1;
    }
    public int w2()
    {
    	int w2 = 0;
    	int cromossomo_aux = 0;
    	cromossomo aux = new cromossomo();
		
		aux = this.corte_meio();
		for (int j = 0 ;j<8;j++)
		{
			cromossomo_aux = aux.getGene(j);
			w2 += (int) (cromossomo_aux * Math.pow(2,j));
		}
		return w2;
    }
    public int w3()
    {
    	int w2 = 0;
    	int cromossomo_aux = 0;
    	cromossomo aux = new cromossomo();
		
		aux = this.corte_rabo();
		for (int j = 0 ;j<8;j++)
		{
			cromossomo_aux = aux.getGene(j);
			w2 += (int) (cromossomo_aux * Math.pow(2,j));
		}
		return w2;
    }
}
