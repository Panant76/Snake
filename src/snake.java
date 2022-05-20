import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class snake {

    // Условимся, что currentPosition[0] - это координата змейки по вертикали,
    // а currentPosition[1] - это координата змейки по горизонтали
    static int[] currentPosition = new int[2];
    static int[] TPos = new int[2];
    static String[][] field;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String direction;

        field = buildField(10, 10);
        printField(field);
        System.out.println("Любой символ для начала");


        while (!(direction= reader.readLine()).equalsIgnoreCase("Exit")) {
            field[TPos[0]][TPos[1]] = " T ";
            int cubeValue = rollCube();
            System.out.println("Направление");
            direction = reader.readLine();
            move(cubeValue, direction);
            if (TPos[0] == currentPosition[0] && TPos[1] == currentPosition[1]) {
                field[currentPosition[0]][currentPosition[1]] = " X ";
                TPos[0] = T();
                TPos[1] = T();
            }
            printField(field);

            System.out.println("Любой символ для продолжения");
        }
    }

    static void move(int cubeValue, String direction) {
        int oldVertical = currentPosition[0];
        int oldHorizontal = currentPosition[1];
        field[oldVertical][oldHorizontal] = " . ";

        if (direction.equalsIgnoreCase("right")) {
            oldHorizontal = currentPosition[1] + cubeValue;
            if (oldHorizontal > field.length - 1) currentPosition[1] = oldHorizontal - field.length;
            else currentPosition[1] = oldHorizontal;
            field[currentPosition[0]][currentPosition[1]] = " o ";
        }
        if (direction.equalsIgnoreCase("down")) {
            oldVertical = currentPosition[0] + cubeValue;
            if (oldVertical > field.length - 1) currentPosition[0] = oldVertical - field.length;
            else currentPosition[0] = oldVertical;
            field[currentPosition[0]][currentPosition[1]] = " o ";
        }
        if (direction.equalsIgnoreCase("up")) {
            oldVertical = currentPosition[0] - cubeValue;
            if (oldVertical < 0) currentPosition[0] = oldVertical + field.length;
            else currentPosition[0] = oldVertical;
            field[currentPosition[0]][currentPosition[1]] = " o ";
        }
        if (direction.equalsIgnoreCase("left")) {
            oldHorizontal = currentPosition[1] - cubeValue;
            if (oldHorizontal < 0) currentPosition[1] = oldHorizontal + field.length;
            else currentPosition[1] = oldHorizontal;
            field[currentPosition[0]][currentPosition[1]] = " o ";
        }
    }

    static int rollCube() {
        Random random = new Random();
        int cubeValue = random.nextInt(7);
        System.out.println(cubeValue);
        return cubeValue;
    }

    static int T() {
        Random rnd = new Random();
        return rnd.nextInt(field.length);
    }

    static String[][] buildField(int width, int height) {
        String[][] field = new String[height][width];
        for (String[] strings : field) {
            Arrays.fill(strings, " . ");
        }
        field[0][0] = " o ";
        return field;
    }

    static void printField(String[][] field) {
        for (String[] strings : field) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }
    }
}
