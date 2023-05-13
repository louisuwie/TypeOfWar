public class Player {
    
    int playerID;
    int low, med, high;
    int speed;

    public Player(int id) {
        this.playerID = id;
        this.speed = 1;
        this.low = 6;
        this.med = 8;
        this.high = 10;
    }

    public void calculateSpeed(int clicks) {
        if (playerID == 1) {
            if (clicks <= 1) {
                speed = low * -1;
            } else if (clicks <= 2) {
                speed = med * -1;
            } else if (clicks <= 3) {
                speed = high * -1;
            }
        } else if (playerID == 2) {
            if (clicks <= 3) {
                speed = low * 1;
            } else if (clicks <= 6) {
                speed = med * 1;
            } else if (clicks <= 9) {
                speed = high * 1;
            }
        }
    }

    public int getSpeed() {
        return speed;
    }
}
