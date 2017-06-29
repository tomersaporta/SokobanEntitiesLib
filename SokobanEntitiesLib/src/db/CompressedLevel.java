package db;

import java.io.Serializable;
import java.util.HashMap;

import sokoElements.ElementMovableFactory;
import sokoElements.ElementUnmovableFactory;
import sokoElements.Player;
import sokoElements.Position;
import sokoElements.Target;
import sokoElements.movable;
import sokoElements.unmovable;

@SuppressWarnings("serial")
public class CompressedLevel implements Serializable {
	private String levelID;
	private char[][] board;
	private HashMap<Character,String> typeOfElement;
	
	public CompressedLevel() {
		this.board=null;
		initElementsHash();
	}
	public CompressedLevel(String levelID,char[][] board) {
		this.levelID=levelID;
		this.board=board;
		initElementsHash();
	}

	public String getLevelID() {
		return levelID;
	}
	public void setLevelID(String levelID) {
		this.levelID = levelID;
	}
	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}
	
	public void initElementsHash(){
		this.typeOfElement=new HashMap<Character, String>();
		this.typeOfElement.put('@', "movable");
		this.typeOfElement.put('A', "movable");
		this.typeOfElement.put('o', "unmovable");
		this.typeOfElement.put('#', "unmovable");
		this.typeOfElement.put(' ', "unmovable");
	}
	
	
	public Level decompressLevel(){
		
		if(this.board==null)
			return null;
			
		ElementMovableFactory movableFactory=new ElementMovableFactory();
		ElementUnmovableFactory unmovableFactory=new ElementUnmovableFactory();
		
		int row=this.board.length;
		int col=this.board[0].length;
		
		Level newLevel=new Level(row,col,levelID);
		for(int i=0;i<row;i++)
		{
			for (int j=0;j<col;j++)
			{
				switch(this.typeOfElement.get(this.board[i][j])){
					case "movable":
						movable mv=movableFactory.createMovable(this.board[i][j], new Position(i,j));
						newLevel.setMovearrInIndex(mv);
						if(mv instanceof Player)
							newLevel.addToListPlayer(mv);  //Player
						else
							newLevel.addToListBox(mv); //Box
						break;
					case "unmovable":
						unmovable unm=unmovableFactory.createUnmovable(this.board[i][j], new Position(i,j));
						newLevel.setBackgroundInIndex(unm); // Background
						if(unm instanceof Target)
							newLevel.addToListTarget(unm); //Target
						break;
						
					default: break;
				}
			}
		}
		newLevel.setInitBoard(newLevel.getLevelBored());
		
		return newLevel;
	}

}
