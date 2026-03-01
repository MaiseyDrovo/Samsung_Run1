import java.util.Random;

public class Person {
    Random random = new Random();
    private int personX, personY;
    private String personImage = "\\uD83E\\uDDD9\\u200D";
    private int personLive = 3;

    public int getX(){
        return personX;
    }
    public int getY() {
        return personY;
    }
    public int getLive() {
        return personLive;
    }
    public String getImage(){
        return personImage;
    }

    void move(int personX, int personY) {
        this.personX = personX;
        this.personY = personY;
    }
    public boolean moveCorrect(int personX, int personY) {
        return this.personX == personX && Math.abs(this.personY - personY) == 1 || this.personY == personY && Math.abs(this.personX - personX) == 1;
    }
    public void downLive(){
        personLive--;
    }

    Person(int sizeBoard) {
        personY = sizeBoard;
        int n = random.nextInt(sizeBoard);
        personX = n == 0 ? 1 : n;
    }  
}
