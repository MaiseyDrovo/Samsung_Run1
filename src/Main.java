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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int step = 0;
        int sizeBoard = 5;
        Person person = new Person(sizeBoard);
        Monster monster = new Monster(sizeBoard);

        String[][] board = new String[sizeBoard][sizeBoard];
        for (int y = 1; y <= sizeBoard; y++) {
            for (int x = 1; x <= sizeBoard; x++) {
                board[y - 1][x - 1] = "  ";
            }
        }

        int count_monster = sizeBoard * sizeBoard - sizeBoard - 1;
        for (int i = 0; i <= count_monster; i++) {
            board[random.nextInt(sizeBoard - 1)][random.nextInt(sizeBoard)] = monster.getImage();
        }

        board[person.getY() - 1][person.getX() - 1] = person.getImage();

        String castle = "\uD83C\uDFF0";
        int castleX = 1 + random.nextInt(sizeBoard);
        int castleY = 1;
        board[castleY - 1][castleX - 1] = castle;



        System.out.println("Готов начать?");
        String answer = scanner.nextLine();
        System.out.println(answer);
        switch (answer) {
            case "да" ->  {
            // case "??" -> {
                System.out.println("Начинаем играть");
                System.out.println("Выбери сложность игры(от 1 до 5):");
                int difficultGame = scanner.nextInt();
                System.out.println("Выбранная сложность: " + difficultGame);



                while (true) {
                    outputBoard(board);
                    System.out.println("Введите куда будет ходить персонаж(ход возможен только по вертикали и горизонтали на одну клетку)");
                    System.out.println("Координаты персонажа - (x: " + person.getX() + ", y: " + person.getY() + ")");
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    if (person.moveCorrect(x, y)) {
                        if (board[y - 1][x - 1].equals("  ")) {
                            board[person.getY() - 1][person.getX() - 1] = "  ";
                            person.move(x, y);
                            board[person.getY() - 1][person.getX() - 1] = person.getImage();
                            step++;
                            System.out.println("Ход корректный; Новые координаты: " + person.getX() + ", " + person.getY() + "\nХод номер: " + step);
                        } else if (board[person.getY() - 1][person.getX()-1].equals(castle)) {
                            System.out.println("Вы прошли игру");
                            break;
                        } else {
                            // if (monster.conflictPerson(x, y)) {
                                if (monster.taskMonster(difficultGame)) {
                                    board[person.getY() - 1][person.getX() - 1] = "  ";
                                    person.move(x, y);
                                    board[person.getY() - 1][person.getX() - 1] = person.getImage();
                                    step++;
                                } else {
                                    person.downLive();
                                }
                            // }
                        }
                    } else {
                        System.out.println("Неккоректный ход");
                        // System.out.println("Координаты не изменены");
                    }
                    if (person.getLive() <= 0) {
                        System.out.println("Закончились жизни. Итог: ");
                        break;
                    }
                }
                break;
            }
            case "нет" ->  {
                System.out.println("Жаль, приходи еще!");
                break;
            }
            // default -> {
            //     System.out.println("Чего?");
            // }
        }
    }
}
