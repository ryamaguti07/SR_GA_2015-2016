import java.util.*;
/**
* Daniel Lemire
* A simple implementation of the weighted slope one
* algorithm in Java for item-based collaborative 
* filtering. 
* Assumes Java 1.5.
*
*
*
* June 1st 2006.
* Revised by Marco Ponzi on March 29th 2007
* 
* 
* November 27th 2013
* Modify by Renan Yamaguti to return a single
* prevision given the matrix the target user and 
* the target item as long -1 means no avaliaton
* 
*/

public class SlopeOne {
  
  public  double getSlopeOne(int matrix[][],int pred_usr,int trg_item){
    // this the data base
int value;
value = matrix[pred_usr][trg_item];
    Map<Integer,Map<Integer,Float>> data = new HashMap<Integer,Map<Integer,Float>>();
    // hire the real value
    matrix[pred_usr][trg_item]=-1;
    // getting all the itens
    mAllItems = new Integer[matrix[0].length];
    for (int i=0;i<matrix[0].length;i++)
    {
    	
    	mAllItems[i] = i;
    }
 
    
    // looking for the users who rated itens
    for(int i=0;i<matrix.length;i++)
    {
    	HashMap<Integer,Float> user = new HashMap<Integer,Float>();
    	for(int j=0;j<matrix[0].length;j++)
    	{
    		if(matrix[i][j]!=-1)
    			user.put(mAllItems[j], (float) matrix[i][j]);
    	}
    	data.put(new Integer(i), user);
    }
 
   

    SlopeOne so = new SlopeOne(data);
  
    HashMap<Integer,Float> trg_user = new HashMap<Integer,Float>();
    int aux=0;
    
    // getting the target user and putting all its ratings in his hash map 
    for (int i=0;i<matrix[0].length;i++)
    {
    	if(matrix[pred_usr][i]!=-1)
    	{	
    		aux = matrix[pred_usr][i];
    		trg_user.put(i, (float) aux);
    	}
    }
    matrix[pred_usr][trg_item] = value;

//getting the prediction
    double p=0;
    // getting the prediction as zero if the list is empty
    if (so.predict(trg_user).isEmpty())
    {
    	return p;
    }
    else
    {
    	if(so.predict(trg_user).containsKey(trg_item))
    	{	
    		p=so.predict(trg_user).remove(trg_item);
    	}
    		 
   
    }
    	
    return p;
  }
  
  Map<Integer,Map<Integer,Float>> mData;
  Map<Integer,Map<Integer,Float>> mDiffMatrix;
  Map<Integer,Map<Integer,Integer>> mFreqMatrix;
  
  static Integer[] mAllItems;
  
  public SlopeOne(Map<Integer,Map<Integer,Float>> data) {
    mData = data;
    buildDiffMatrix();    
  }
  
  public SlopeOne() {

}

/**
  * Based on existing data, and using weights,
  * try to predict all missing ratings.
  * The trick to make this more scalable is to consider
  * only mDiffMatrix entries having a large  (>1) mFreqMatrix
  * entry.
  *
  * It will output the prediction 0 when no prediction is possible.
  */
  public Map<Integer,Float> predict(Map<Integer,Float> user) {
    HashMap<Integer,Float> predictions = new HashMap<Integer,Float>();
    HashMap<Integer,Integer> frequencies = new HashMap<Integer,Integer>();
    for (Integer j : mDiffMatrix.keySet()) {
      frequencies.put(j,0);
      predictions.put(j,0.0f);
    }
    for (Integer j : user.keySet()) {
      for (Integer k : mDiffMatrix.keySet()) {
        try {
        float newval = ( mDiffMatrix.get(k).get(j).floatValue() + user.get(j).floatValue() ) * mFreqMatrix.get(k).get(j).intValue();
        predictions.put(k, predictions.get(k)+newval);
        frequencies.put(k, frequencies.get(k)+mFreqMatrix.get(k).get(j).intValue());
        } catch(NullPointerException e) {}
      }
    }
    HashMap<Integer,Float> cleanpredictions = new HashMap<Integer,Float>();
    for (Integer j : predictions.keySet()) {
    	if (frequencies.get(j)>0) {
          cleanpredictions.put(j, predictions.get(j).floatValue()/frequencies.get(j).intValue());
    	}
    }
    for (Integer j : user.keySet()) {
         cleanpredictions.put(j,user.get(j));
    }
    return cleanpredictions;
  }
  
  /**
  * Based on existing data, and not using weights,
  * try to predict all missing ratings.
  * The trick to make this more scalable is to consider
  * only mDiffMatrix entries having a large  (>1) mFreqMatrix
  * entry.
  */
  public Map<Integer,Float> weightlesspredict(Map<Integer,Float> user) {
    HashMap<Integer,Float> predictions = new HashMap<Integer,Float>();
    HashMap<Integer,Integer> frequencies = new HashMap<Integer,Integer>();
    for (Integer j : mDiffMatrix.keySet()) {
      predictions.put(j,0.0f);
      frequencies.put(j,0);
    }
    for (Integer j : user.keySet()) {
      for (Integer k : mDiffMatrix.keySet()) {
        System.out.println("Average diff between "+j+" and "+ k + " is "+mDiffMatrix.get(k).get(j).floatValue()+" with n = "+mFreqMatrix.get(k).get(j).floatValue());
        float newval = ( mDiffMatrix.get(k).get(j).floatValue() + user.get(j).floatValue() ) ;
        predictions.put(k, predictions.get(k)+newval);
      }
    }
    for (Integer j : predictions.keySet()) {
        predictions.put(j, predictions.get(j).floatValue()/user.size());
    }
    for (Integer j : user.keySet()) {
      predictions.put(j,user.get(j));
    }
    return predictions;
  }


  
  public void buildDiffMatrix() {
    mDiffMatrix = new HashMap<Integer,Map<Integer,Float>>();
    mFreqMatrix = new HashMap<Integer,Map<Integer,Integer>>();
    // first iterate through users
    for(Map<Integer,Float> user : mData.values()) {
      // then iterate through user data
      for(Map.Entry<Integer,Float> entry: user.entrySet()) {
        if(!mDiffMatrix.containsKey(entry.getKey())) {
          mDiffMatrix.put(entry.getKey(), new HashMap<Integer,Float>());
          mFreqMatrix.put(entry.getKey(), new HashMap<Integer,Integer>());
        }
        for(Map.Entry<Integer,Float> entry2: user.entrySet()) {
          int oldcount = 0;
          if(mFreqMatrix.get(entry.getKey()).containsKey(entry2.getKey()))
            oldcount = mFreqMatrix.get(entry.getKey()).get(entry2.getKey()).intValue();
          float olddiff = 0.0f;
          if(mDiffMatrix.get(entry.getKey()).containsKey(entry2.getKey()))
            olddiff = mDiffMatrix.get(entry.getKey()).get(entry2.getKey()).floatValue();
          float observeddiff = entry.getValue() - entry2.getValue();
          mFreqMatrix.get(entry.getKey()).put(entry2.getKey(),oldcount + 1);
          mDiffMatrix.get(entry.getKey()).put(entry2.getKey(),olddiff+observeddiff);          
        }
      }
    }
    for (Integer j : mDiffMatrix.keySet()) {
      for (Integer i : mDiffMatrix.get(j).keySet()) {
        float oldvalue = mDiffMatrix.get(j).get(i).floatValue();
        int count = mFreqMatrix.get(j).get(i).intValue();
        mDiffMatrix.get(j).put(i,oldvalue/count);
      }
    }
  }
}

