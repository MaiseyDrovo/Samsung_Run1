import java.util.Random;
import java.util.Scanner;

public class Monster {
    Random random = new Random();
    private int MonsterX, monsterY;
    private String monsterImage = "\uD83E\uDDDF\u200D";

    Monster(int sizeBoard){
        monsterY = random.nextInt(sizeBoard - 1);
        MonsterX = random.nextInt(sizeBoard);
    }

    public String getImage() {
        return monsterImage;
    }

    public int getY() {
        return monsterY;
    }

    public int getX() {
        return MonsterX;
    }

    public boolean conflictPerson(int personX, int personY){
        return personY == monsterY && personX == MonsterX;
    }

    
    public boolean taskMonster(int difficultGame) {
        if (difficultGame == 1) {
            Scanner scanner = new Scanner(System.in);
            Random random = new Random();
            int numOne = random.nextInt(300);
            int numTwo = random.nextInt(300);
            int trueAnswer = numOne + numTwo;
            System.out.println("Реши пример: " + numOne + " + " + numTwo + " = ?");
            int monsterAnswer = scanner.nextInt();
            if (trueAnswer == monsterAnswer) {
                System.out.println("Верно! Ты победил монстра");
                return true;
            }else {
                System.out.println("Ты проиграл эту битву!");
                return false;
            }
        } else {
             
        }
        return false;
    }
}