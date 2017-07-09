package sokoElements;


/**
 * 
 * Define the PLAYER element
 */
@SuppressWarnings("serial")
public class Player extends CommonGeneralElement implements movable{
	
	@Override
	public char getType() {
		return 'A';
	}

	public Player() {
		super.setRepresntive('A');
	}

	public Player(Position pos){
		super.setRepresntive('A');
		super.setPosition(pos);

	}
	

}
