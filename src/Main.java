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
        Scanner scanner = new Scanner(System.in, "Windows-1251");
        Random random = new Random();
        int step = 0;
        int sizeBoard = 5;
        Person person = new Person(sizeBoard);
        Monster monster = new Monster(sizeBoard);
        monster = new BigMonster(sizeBoard);

        String[][] board = new String[sizeBoard][sizeBoard];
        for (int y = 1; y <= sizeBoard; y++) {
            for (int x = 1; x <= sizeBoard; x++) {
                board[y - 1][x - 1] = "  ";
            }
        }

        int countMonster = sizeBoard * sizeBoard - sizeBoard - 1;
        Monster[] arrMonster = new Monster[countMonster + 1];
        Monster test;
        for (int i = 0; i <= countMonster; i++) {
            if (random.nextBoolean()) {
                test = new Monster(sizeBoard);
            } else {
                test = new BigMonster(sizeBoard);
            }
            if (board[test.getY()][test.getX()].equals("  ")) {
                board[test.getY()][test.getX()] = test.getImage();
                arrMonster[i] = test;
            }
        }

        board[person.getY() - 1][person.getX() - 1] = person.getImage();

        String castle = "\uD83C\uDFF0";
        int castleX = 1 + random.nextInt(sizeBoard);
        int castleY = 1;
        board[castleY - 1][castleX - 1] = castle;



        System.out.println("Готов начать?");
        String answer = scanner.nextLine();
        switch (answer) {
            case "да" ->  {
            // case "1" -> {
                System.out.println("Начинаем играть");
                System.out.println("Выбери сложность игры(от 1 до 5):");
                int difficultGame = scanner.nextInt();
                System.out.println("Выбранная сложность: " + difficultGame);
                System.out.println("Введите способ перемещения ('WASD' или 'по координатам'):");
                String moveSelect = scanner.next();
                System.out.println("Выбранный способ перемещения: " + moveSelect);



                while (true) {
                    outputBoard(board);
                    System.out.println("Введите куда будет ходить персонаж(ход возможен только по вертикали и горизонтали на одну клетку)");
                    System.out.println("Координаты персонажа - (x: " + person.getX() + ", y: " + person.getY() + ")");
                    int x = person.getX();
                    int y = person.getY();
                    if (moveSelect.equals("WASD")) {
                        String dirrection = scanner.nextLine();
                        switch (dirrection) {
                            case "w" -> y = (person.getY() - 1);
                            case "a" -> x = (person.getX() - 1);
                            case "s" -> y = (person.getY() + 1);
                            case "d" -> x = (person.getX() + 1);
                        }
                    } else {
                        x = scanner.nextInt();
                        y = scanner.nextInt();
                    }
                    if (person.moveCorrect(x, y)) {
                        if (board[y - 1][x - 1].equals("  ")) {
                            board[person.getY() - 1][person.getX() - 1] = "  ";
                            person.move(x, y);
                            board[person.getY() - 1][person.getX() - 1] = person.getImage();
                            step++;
                            System.out.println("Ход корректный; Новые координаты: " + person.getX() + ", " + person.getY() + "\nХод номер: " + step);
                        } else if (board[y - 1][x - 1].equals(castle)) {
                            System.out.println("Вы прошли игру");
                            break;
                        } else {
                            // for (Monster monster : arrMonster) {
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
                            // }
                        }
                    } else {
                        System.out.println("Неккоректный ход, координаты не изменены");
                    }
                    if (person.getLive() <= 0) {
                        System.out.println("Закончились жизни. Итог: ");
                        break;
                    }
                }
            }
            case "нет" -> System.out.println("Жаль, приходи еще!");
            default -> System.out.println("Чего?");
        }
    }
}
