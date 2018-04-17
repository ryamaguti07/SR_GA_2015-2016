import java.util.Random;

public class Population
{
    final static int ELITISM_K = 5;
    final static int POP_SIZE = 200+ ELITISM_K;  // population size
    final static int MAX_ITER = 2000;             // max number of iterations
    final static double MUTATION_RATE = 0.15;     // probability of mutation
    final static double CROSSOVER_RATE = 0.8;     // probability of crossover
    final static double LIMIT = 0;     // probability of crossover
    private static Random m_rand = new Random();  // random-number generator
    private individuo[] m_population;
    private double totalFitness;

    public Population() {
        m_population = new individuo[POP_SIZE];

        // lowt population
        for (int i = 0; i < POP_SIZE; i++) {
            cromossomo p1 = new cromossomo();
            cromossomo p2 = new cromossomo();
            cromossomo p3 = new cromossomo();
            p1.randGenes();
            p2.randGenes();
            p3.randGenes();
            m_population[i] = new individuo(p1,p2,p3);
        
        }

        // evaluate current population
        this.evaluate();
    }

    public void setPopulation(individuo[] newPop) {
        // this.m_population = newPop;
        System.arraycopy(newPop, 0, this.m_population, 0, POP_SIZE);
    }

    public individuo[] getPopulation() {
        return this.m_population;
    }

    public double evaluate() {
        this.totalFitness = 0.0;
        for (int i = 0; i < POP_SIZE; i++) {
            this.totalFitness += m_population[i].evaluate();
        }
        this.quick_sort(m_population, 0,POP_SIZE-1);
        return this.totalFitness;
        
    }

    

    public  void quick_sort(individuo []v,int low, int high) {
        int meio;

        if (low < high) {
                meio = partition(v, low, high);
                quick_sort(v, low, meio);
                quick_sort(v, meio + 1, high);
        }
}

public  int partition(individuo []v, int low, int high) {
        int  topo, i;
        individuo pivo;
        pivo = v[low];
        topo = low;

        for (i = low + 1; i <= high; i++) {
                if (v[i].getFitness() < pivo.getFitness()) {
                        v[topo] = v[i];
                        v[i] = v[topo + 1];
                        topo++; 
                }
        }
        v[topo] = pivo;
        return topo;
}
    
    
    public individuo rouletteWheelSelection() {
        double randNum = m_rand.nextDouble() * this.totalFitness;
        int idx;
        for (idx=0; idx<POP_SIZE && randNum>0; idx++) {
            randNum -= m_population[idx].getFitness();
        }
        return m_population[idx-1];
    }

    public individuo findBestindividuo() {
      
        return m_population[0];       
    }

    public individuo[] getElitList()
    {
    	individuo[] elit = new individuo[ELITISM_K];
    	
    	for (int i = 0; i<ELITISM_K;i++)
    	{
    		elit[i] = this.m_population[i];
    	}
    	return elit;
    }
  // contas lokas
    public static individuo[] crossover(individuo indiv1,individuo indiv2) {
        individuo newIndiv[] = new individuo[2];
        Random rand = new Random();
		 int i = rand.nextInt(3);
		 switch (i)
		 {
		 case 0:
		 		newIndiv[0] = new individuo(indiv1.corte_cabeca(),indiv2.corte_meio(), indiv2.corte_rabo());//cabeca 1º pai
		 		newIndiv[1] = new individuo(indiv2.corte_cabeca(),indiv1.corte_meio(), indiv1.corte_rabo());
		 break;
		 case 1:
			 	newIndiv[0] = new individuo(indiv1.corte_cabeca(),indiv2.corte_meio(), indiv1.corte_rabo());//cabeca 2º pai
		 		newIndiv[1] = new individuo(indiv2.corte_cabeca(),indiv1.corte_meio(), indiv2.corte_rabo());
		 break;
		 case 2:
			 	newIndiv[0] = new individuo(indiv1.corte_cabeca(),indiv1.corte_meio(), indiv2.corte_rabo());//cabeca 3º pai
		 		newIndiv[1] = new individuo(indiv2.corte_cabeca(),indiv2.corte_meio(), indiv1.corte_rabo());
		 break;
		 }
        return newIndiv;
    }


    public static void main(String[] args) {
 
        individuo[] newPop = new individuo[POP_SIZE];
        individuo[] indiv = new individuo[2];
        individuo[] elit_list = new individuo[ELITISM_K];
        int iter = 0,flag = 0;
        matriz.create();
     
        Knn_mae knn = new Knn_mae();
        double tempoInicial_tend = System.currentTimeMillis();
        tendence_mae tendence = new tendence_mae();
        double tempoFinal_tend = System.currentTimeMillis();  
        double tempotend = (tempoFinal_tend - tempoInicial_tend)/1000;
        //matriz.create();
        
        
        double tempoInicial_knn = System.currentTimeMillis();
        knn.create();
      
        double tempoFinal_knn = System.currentTimeMillis();  
        double tempoknn = (tempoFinal_knn - tempoInicial_knn)/1000;
        
        
        double tempoInicial_slope = System.currentTimeMillis();
        slopeOne_mae slope= new slopeOne_mae();
        double tempoFinal_slope = System.currentTimeMillis();  
        double tempoSlope = (tempoInicial_slope - tempoFinal_slope)/1000;
        
        Population pop = new Population();
        
        
        double tempoInicial_train = System.currentTimeMillis();
        // current population
        System.out.println("----- Geração "+(iter)+"--------");
        System.out.println("Individuo: ");
        pop.findBestindividuo().print();
        System.out.print(" Fitness Total= " + pop.totalFitness);
        System.out.println(" - Melhor Fitness = " +
            pop.findBestindividuo().getFitness()); 
       System.out.println("KNN MAE: "+knn.getKnnMae());
        System.out.println("Tendence MAE: "+tendence.getTendenceMae());
        System.out.println("Slope One MAE: "+slope.getSlopeOneMae());
        if (pop.findBestindividuo().getFitness() <= LIMIT)
        {
        	iter = MAX_ITER;
        	flag++;
        }

        // main loop
        int count;
        for (iter = 0; iter < MAX_ITER; iter++) {
            
        	  if (flag!=0)
        		   	break;
        	  
        	count = 0;

            // Elitism
        	System.arraycopy(pop.getElitList(), 0, elit_list, 0, ELITISM_K);
            for (int i=0; i<ELITISM_K; i++) {
                newPop[count] = elit_list[i];
                count++;
            }

            // build new Population
            while (count < POP_SIZE) {
                // Selection
                indiv[0] = pop.rouletteWheelSelection();
                indiv[1] = pop.rouletteWheelSelection();
   
                // Crossover
                if ( m_rand.nextDouble() < CROSSOVER_RATE ) {
                    indiv = crossover(indiv[0], indiv[1]);
                }

                // Mutation
                if ( m_rand.nextDouble() < MUTATION_RATE ) {
                    indiv[0].mutate();
                }
                if ( m_rand.nextDouble() < MUTATION_RATE ) {
                    indiv[1].mutate();
                }

                // add to new population
                newPop[count] = indiv[0];
                newPop[count+1] = indiv[1];
                count +=2;
            }
            pop.setPopulation(newPop);

            // reevaluate current population
            pop.evaluate();
            System.out.println("----- Geração "+(iter+1)+"--------");
            System.out.println("Individuo: ");
            pop.findBestindividuo().print();
            System.out.print(" Fitness Total= " + pop.totalFitness);
            System.out.println(" - Melhor Fitness = " +
                pop.findBestindividuo().getFitness()); 
            System.out.println("KNN MAE: "+knn.getKnnMae());
            System.out.println("Tendence MAE: "+tendence.getTendenceMae());
            System.out.println("Slope One MAE: "+slope.getSlopeOneMae());
            if (pop.findBestindividuo().getFitness() <= slope.getSlopeOneMae() && pop.findBestindividuo().getFitness() <= tendence.getTendenceMae())
            {
            	break;
            }
        }

        // best indiv
        individuo bestIndiv = pop.findBestindividuo();
        if (flag!=0)
        	System.out.println("Um bom individuo apareceu! na primeira geração ");
        else
        {
        	System.out.println("Um bom individuo apareceu! na geração "+(iter+1));
        }
        bestIndiv.print();
        System.out.println("Fitness : "+bestIndiv.getFitness());
        double tempoFinal_train = System.currentTimeMillis();  
        double tempotrain = (tempoFinal_train - tempoInicial_train)/1000;
        System.out.println("----------Agora comeca os testes na matriz-------------");
        
        double tempoInicial_test = System.currentTimeMillis();  
        knn_test Knn_test = new knn_test();
        tendence_test tendence_test = new tendence_test();
        mae_test teste = new mae_test();
        Knn_test.create();
        double tempoFinal_test = System.currentTimeMillis();  
        double tempotest = (tempoFinal_test - tempoInicial_test)/1000;
        
        double tempoInicial_slope_test = System.currentTimeMillis();
        slopeOne_test slope_test= new slopeOne_test();
        double tempoFinal_slope_test = System.currentTimeMillis();  
        double tempoSlope_test = (tempoFinal_slope_test - tempoInicial_slope_test)/1000;
        
        bestIndiv.print();
        System.out.println("-------------treinamento-----------");
        System.out.println("");
        System.out.println("KNN MAE: "+knn.getKnnMae());
        System.out.println("Tendence MAE: "+tendence.getTendenceMae());
        System.out.println("Slope One MAE: "+slope.getSlopeOneMae());
        System.out.println( "MAE da composição: "+bestIndiv.getFitness());
         
        System.out.println("--------------testes--------------");
       System.out.println("KNN MAE: "+knn_test.getKnnMae());
       System.out.println("Tendence MAE: "+tendence_test.getTendenceMae());
       System.out.println("Slope One MAE: "+slope_test.getSlopeOneMae());
       System.out.println( "MAE da composição: "+teste.getMAE(bestIndiv.w1(),bestIndiv.w2(), bestIndiv.w3()));
       
       System.out.println("Tempo KNN: "+tempoknn);
       System.out.println("Tempo Tend: "+tempotend);
       System.out.println("Tempo Slope One: " +tempoSlope_test);
       System.out.println("Tempo treinamento: "+tempotrain);
       System.out.println("Tempo teste: "+tempotest);
      
    }
}