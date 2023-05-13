public class Player {

    int playerID, clicks, speed, low = 10, med = 20, high = 30;
    
    public Player(int a) {
        playerID = a;
    }

    public void Speed(int c) {
        if (c <= 2) {
            speed = low;
        } else if (c <= 8) {
            speed = med;
        } else if (c <12) {
            speed = high;
        }
    }

    public int getSpeed(){
        return speed;
    }

    public int getPlayerNumber() {
        return playerID;
    }
}
