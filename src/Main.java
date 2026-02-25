import java.util.Random;
import java.util.Scanner;

public class Main {
    static void outputBoard(String[][] board) {
        String leftBlock = " | ";
        String rightBlock = " |";
        String wall = " + —— + —— + —— + —— + —— + ";
        for (String[] raw : board) {
            System.out.println(wall);
            for (String col : raw) {
                System.out.print(leftBlock + col);
            }
            System.out.println(rightBlock);
        }
        System.out.println(wall);
    }
    static boolean taskMonster(int key) {
        if (key == 1) {
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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int step = 0;
        int sizeBoard = 5;

        String[][] board = new String[sizeBoard][sizeBoard];
        for (int y = 1; y <= sizeBoard; y++) {
            for (int x = 1; x <= sizeBoard; x++) {
                board[y - 1][x - 1] = "  ";
            }
        }

        String monster = "Мм";
        int count_monster = sizeBoard * sizeBoard - sizeBoard - 1;
        for (int i = 0; i <= count_monster; i++) {
            board[random.nextInt(sizeBoard - 1)][random.nextInt(sizeBoard)] = monster;
        }

        String person = "Г";
        int personX = 1 + sizeBoard / 2;
        int personY = 1 + sizeBoard / 2;
        int personLive = 3;
        board[personY - 1][personX - 1] = person;

        String castle = "\uD83C\uDFF0";
        int castleX = 1 + random.nextInt(sizeBoard);
        int castleY = 1;
        board[castleY - 1][castleX - 1] = castle;



        System.out.println("Готов начать?");
        String answer = scanner.nextLine();
        switch (answer) {
            case "да" -> System.out.println("Начинаем играть");
            case "нет" -> System.out.println("Жаль, приходи еще!");
            default -> System.out.println("Чего?");
        }



        System.out.println("Выбери сложность игры(от 1 до 5):");
        int difficultGame = scanner.nextInt();
        System.out.println("Выбранная сложность: " + difficultGame);



        while (true) {
            outputBoard(board);
            System.out.println("Введите куда будет ходить персонаж(ход возможен только по вертикали и горизонтали на одну клетку)");
            System.out.println("Координаты персонажа - (x: " + personX + ", y: " + personY + ")");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            if (x != personX && y != personY) {
                System.out.println("Неккоректный ход");
            } else if (Math.abs(x - personX) == 1 || Math.abs(y - personY) == 1) {
                if (board[y - 1][x - 1].equals("  ")) {
                    board[personY - 1][personX - 1] = "  ";
                    personX = x;
                    personY = y;
                    board[personY - 1][personX - 1] = person;
                    step++;
                    System.out.println("Ход корректный; Новые координаты: " + personX + ", " + personY + "\nХод номер: " + step);
                } else if (board[(personY - 1)][personX-1].equals(castle)) {
                    System.out.println("Вы прошли игру");
                    break;
                } else {
                    // taskMonster();
                    int key = difficultGame;
                    if (taskMonster(key)) {
                        board[personY - 1][personX - 1] = "  ";
                        personX = x;
                        personY = y;
                        board[personY - 1][personX - 1] = person;
                        step++;
                    } else {
                        personLive--;
                    }
                }
            } else {
                System.out.println("Координаты не изменены");
            }
            if (personLive <= 0) {
                System.out.println("Закончились жизни. Итог: ");
                break;
            }
        }
    }
}
