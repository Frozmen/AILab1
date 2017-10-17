
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.LongToDoubleFunction;

public class Main {

	public static final String CSV_NAME = "pokemon.csv";
	public static final int CLUSTER_NUMBER = 4;
	
	public static final boolean isXNull = false;
	public static final boolean isYNull = false;
	public static final boolean isZNull = true;
	
	static List<Cluster> clusters = new ArrayList<>();
	
    public static void main(String[] args){
    	
       for(int i=0; i<CLUSTER_NUMBER; i++){
    	   Cluster cluster = new Cluster();
    	   cluster.id = i;
    	   clusters.add(cluster);
       }
       long startTime = System.currentTimeMillis();
       System.out.println("Star time: " + startTime);
       List<PokemonModel> data = CsvFileReader.readCsvFile();
       generateClusters(data);
       ClustersChart chart1 = new ClustersChart(clusters);
       chart1.draw();
       
       while(sortClusters());
       

       System.out.println("Results: ");
       for(Cluster cluster : clusters){
  		 System.out.println(cluster.toString());
  	 }
       System.out.println("Total time: " + (System.currentTimeMillis() - startTime));
       System.out.println("Total swaps: " + swaps);
       ClustersChart chart = new ClustersChart(clusters);
       chart.draw();
       
    }
    
    public static void generateClusters(List<PokemonModel> data){
    	 for(PokemonModel pokemonModel : data){
      	   int toCluster = (int) (Math.random() * CLUSTER_NUMBER);
      	   clusters.get(toCluster).add(pokemonModel);
         }
    	 int index = 0;
    	 for(Cluster cluster : clusters){
    		 index++;
    		 System.out.println(cluster.toString());
    	 }
    }
    
    public static void calculateCenters(){
     System.out.println("________________________________________________");
   	 for(Cluster cluster : clusters){
   		 cluster.calculateCenter();
   		System.out.println(cluster.toString());
   	 }
   }
    
    public static boolean sortClusters(){
    	calculateCenters();
    	boolean needAgain = false;
    	 int index = 0;
    	 for(Cluster cluster : clusters){
    		 Iterator<PokemonModel> it = cluster.datas.iterator();
    		 while(it.hasNext()){
    			 boolean needRemove = filterForModel(it.next(), index);
    			 if(needRemove){
    				 needAgain = true;
    				 it.remove();
    			 }
    		 }
    		 index++;    		 
    	 }
    	return needAgain;
    }
    
    static int swaps = 0;
    public static boolean filterForModel(PokemonModel model, int fromCluster){
    	boolean isMoving = false;
    	double currMin = Double.MAX_VALUE;
    	int toCluster = 0;
    	for(int i = 0; i<clusters.size(); i++){
    		double tempMin = clusters.get(i).getDistanceToPoint(model);
    		if(tempMin<currMin){
    			currMin = tempMin;
    			toCluster = i;
    		}
    	}
    	//need moving
    	if(toCluster != fromCluster){
    		//clusters.get(fromCluster).remove(model);
        	clusters.get(toCluster).add(model);
        	swaps++;
        	isMoving = true;
    	}    	
    	return isMoving;
    }

}
