import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int step = 0;
        int sizeBoard = 5;

//        String gamingField = "+ —— + —— + —— +\n"
//                + "|    |    |    |\n"
//                + "+ —— + —— + —— +\n"
//                + "|    | " + monster + " |    |\n"
//                + "+ —— + —— + —— +\n"
//                + "| " + person + " |    |    |\n"
//                + "+ —— + —— + —— +";
        String leftBlock = " | ";
        String rightBlock = " |";
        String wall = " + —— + —— + —— + —— + —— + ";
        String[][] board = new String[sizeBoard][sizeBoard];

        String person = "Г";
        int personX = 1 + sizeBoard / 2;
        int personY = 1 + sizeBoard / 2;
        int personLive = 5;

        String monster = "Мм";
        int count_monster = sizeBoard * sizeBoard - sizeBoard - 1;
        for (int i = 0; i <= count_monster; i++) {
            board[random.nextInt(sizeBoard - 1)][random.nextInt(sizeBoard)] = monster;
        }

        String castle = "\uD83C\uDFF0";
        int castleX = 1 + random.nextInt(sizeBoard);
        int castleY = 1;



        System.out.println("Готов начать?");
        String answer = scanner.nextLine();
        switch (answer) {
            case "да":
                System.out.println("Начинаем играть");
                break;
            case "нет":
                System.out.println("Жаль, приходи еще!");
                break;
            default:
                System.out.println("Чего?");
        }



        System.out.println("Выбери сложность игры(от 1 до 5):");
        int difficultGame = scanner.nextInt();
        System.out.println("Выбранная сложность: " + difficultGame);



        for (int y = 1; y <= sizeBoard; y++) {
            System.out.println(wall);
            for (int x = 1; x <= sizeBoard; x++) {
                System.out.print(leftBlock);
                board[y - 1][x - 1] = "  ";
            }
            System.out.println(rightBlock);
        }
        System.out.println(wall);



        while (true) {
            System.out.println("Введите куда будет ходить персонаж(ход возможен только по вертикали и горизонтали на одну клетку)");
            System.out.println("Координаты персонажа - (x: " + personX + ", y: " + personY + "))");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            if (x != personX && y != personY) {
                System.out.println("Неккоректный ход");
            } else if (Math.abs(x - personX) == 1 || Math.abs(y - personY) == 1) {
                if (board[(personY - 1)][personX].equals("  ")) {
                    board[(personY - 1)][personX] = "  ";
                    personX = x;
                    personY = y;
                    step++;
                    System.out.println("Ход корректный; Новые координаты: " + personX + ", " + personY + "\nХод номер: " + step);
                } else if (board[(personY - 1)][personX].equals(castle)) {
                    System.out.println("Вы прошли игру");
                    break;
                } else {
                    System.out.println("Решите задачу.");
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