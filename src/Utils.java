public class Utils {
	
	/*
	 * Returns the next player;
	 * I.e. returns 1 if input player is 2, or 2 if input player is 1.
	 */
	public static int otherPlayer(int currentPlayer) {
		return currentPlayer % 2 + 1;
	}
	
	/*
	 * Auxiliary function for move processing. 
	 */
	public static int getSign(int currentPlayer) {
		int sign = 1;
		if(currentPlayer == Constants.PLAYER_2) {
			sign = -1;
		}
		return sign;
	}
	
	/*
	 * Checks if a position belongs to the drop zone of the specified player.
	 */
	public static boolean isWithinDropZone(Position position, int player) {
        if(player == Constants.PLAYER_1)
            for(int i = 1; i <= 6; i++) {
                if(position.x == i && (position.y == 0 || position.y == 1))
                    return true;
            }
        else {
        	for(int i = 1; i <= 6; i++) {
	            if(position.x == i && (position.y == 6 || position.y == 7))
	                return true;
            }
        }
        return false;
    }
	
	/*
	 * Returns the name of a grid button. 
	 */
	public static String getButtonName(Position pos) {
		return pos.x + "," + pos.y;
	}
	
	/*
	 * Returns the position that matches the specified button name.
	 */
	public static Position getButtonPos(String name) {
		String[] parts = name.split(",");
		if(parts.length != 2) {
			System.err.println("Parsing invalid button name!");
			return new Position(Constants.ERROR, Constants.ERROR);
		}
		return new Position(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
	}
	
	
}