import java.util.Random;
import java.util.Scanner;

public class Monster {
    Random random = new Random();
    int MonsterX, monsterY;
    String monsterImage = "\uD83E\uDDDF\u200D";

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

    public boolean conflictPerson(int x, int y){
        return (((y - 1) == monsterY) && ((x - 1) == MonsterX));
    }


    public boolean taskMonster(int difficultGame) {
        Scanner scanner = new Scanner(System.in);
        int numOne = random.nextInt(100 * difficultGame), numTwo = random.nextInt(100 * difficultGame);
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
    }
}
