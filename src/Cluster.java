import java.util.ArrayList;
import java.util.List;

public class Cluster {
	public int id;
	public List<PokemonModel> datas = new ArrayList<>();
	public double centerX, centerY, centerZ;
	
	public void setCenter(double x, double y, double z){
		this.centerX = x;
		this.centerY = y;
		this.centerZ = z;
	}
	
	public void add(PokemonModel model){
		datas.add(model);
	}
	
	public void remove(PokemonModel model){
		datas.remove(model);
	}
	
	public void calculateCenter(){
		double tempX = 0, tempY = 0, tempZ = 0;
		for(PokemonModel model : datas){
			tempX+=model.getX();
			tempY+=model.getY();
			tempZ+=model.getZ();
		}
		centerX = tempX/datas.size()*(Main.isXNull ? 0 : 1);
		centerY = tempY/datas.size()*(Main.isYNull ? 0 : 1);
		centerZ = tempZ/datas.size()*(Main.isZNull ? 0 : 1);
	}
	
	public double getDistanceToPoint(PokemonModel model){
		return Math.sqrt(((model.getX() - centerX)*(model.getX() - centerX) * (Main.isXNull ? 0 : 1))
				+ ((model.getY() - centerY)*(model.getY() - centerY) * (Main.isYNull ? 0 : 1))
				+ ((model.getZ() - centerZ)*(model.getZ() - centerZ) * (Main.isZNull ? 0 : 1)));
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Cluster " + id + ": " + datas.size() + " elements, center: " + centerX + " " + centerY + " " + centerZ;
	}
}
