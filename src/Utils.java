public class Utils {
	public static int otherPlayer(int currentPlayer) {
		return currentPlayer % 2 + 1;
	}
	
	public static int getSign(int currentPlayer) {
		int sign = 1;
		if(currentPlayer == Constants.PLAYER_2) {
			sign = -1;
		}
		return sign;
	}
	
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
	
	public static String getButtonName(Position pos) {
		return pos.x + "," + pos.y;
	}
	
	public static Position getButtonPos(String name) {
		String[] parts = name.split(",");
		if(parts.length != 2) {
			System.err.println("Parsing invalid button name!");
			return new Position(Constants.ERROR, Constants.ERROR);
		}
		return new Position(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
	}
	
	
}