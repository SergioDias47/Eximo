public class Position {
    public final int x;
    public final int y;
    
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /*
     * Prints the coordinates of the position on the console.
     */
    public void print() {
    	System.out.println("(" + x + ", " + y + ")");
    }
    
    @Override
    public boolean equals(Object o) {
    	if (o == this) { 
            return true; 
        }    
        if (!(o instanceof Position)) { 
            return false; 
        }     
        Position p = (Position) o;
        return (x == p.x && y == p.y);
    }
    
    
}