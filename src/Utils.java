
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
	
	public static boolean isWithinSafeZone(int player, int position) {
		System.out.println("Position: " + position);
        if(player == 1)
            for(int i = 1; i <= 6; i++) {
                if(position == i || position == i+8)
                    return true;
            }
        else {
        	for(int i = 1; i <= 6; i++) {
	            if(position == i+48 || position == i+56)
	                return true;
            }
        }
        return false;
    }
	
	
}
