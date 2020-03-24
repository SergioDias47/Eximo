
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
	
	
}
