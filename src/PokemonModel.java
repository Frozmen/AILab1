public class PokemonModel {
	
	 public String Name;
	 public String SpAtk;//x
	 public String SpDef;//y
	 public String Speed;//z
	 
	 public PokemonModel(String Name, String SpAtk, String SpDef, String Speed){
		 this.Name = Name;
		 this.SpAtk = SpAtk;
		 this.SpDef = SpDef;
		 this.Speed = Speed;
	 }
	    
	 @Override
	public String toString() {
		return "Name: " + Name + ", SpAtk: " + SpAtk + ", SpDef: " + SpDef + ", Speed: " + Speed;
	}
	 
	public double getX(){
		return Integer.parseInt(SpAtk);
	}
	
	public double getY(){
		return Integer.parseInt(SpDef);
	}
	
	public double getZ(){
		return Integer.parseInt(Speed);
	}
}
