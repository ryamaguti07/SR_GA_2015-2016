import java.util.Random;

public class cromossomo
{
    int SIZE = 8;
    private int[] genes = new int[SIZE];


    public int getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, int gene) {
        this.genes[index] = gene;
    }

    public void randGenes() {
        Random rand = new Random();
        for(int i=0; i<SIZE; ++i) {
            this.setGene(i, rand.nextInt(2)); 
        }
    }

    public void mutate() {
        Random rand = new Random();
        int index = rand.nextInt(SIZE);
        this.setGene(index, 1-this.getGene(index));    
    }


    
    public void imprime()
    {
    	for(int i = 0;i<SIZE;i++)
    	System.out.printf(""+this.getGene(i));
    }
}