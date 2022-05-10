import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        int tx = T();

        while (!(direction = reader.readLine()).equalsIgnoreCase("Exit")) {
            field[TPos[0]][TPos[1]] = " T ";
            int cubeValue = rollCube();
            System.out.println("Направление");
            direction = reader.readLine();
            move(cubeValue, direction);
            if (TPos[0] == currentPosition[0] && TPos[1] == currentPosition[1]) {
                field[currentPosition[0]][currentPosition[1]] = " X ";
                tx = T();
            }
            printField(field);

            System.out.println("Любой символ для продолжения");
        }
    }

    static void move(int cubeValue, String direction) {
        int fl = field.length;
        int oldVertical = currentPosition[0];
        int oldHorizontal = currentPosition[1];
        int oTVertical = TPos[0];
        int oTHorizontal = TPos[1];
        field[oldVertical][oldHorizontal] = " . ";

        if (direction.equalsIgnoreCase("right")) {
            oldHorizontal = currentPosition[1] + cubeValue;
            if (oldHorizontal > fl - 1) currentPosition[1] = oldHorizontal - fl;
            else currentPosition[1] = oldHorizontal;
            field[currentPosition[0]][currentPosition[1]] = " o ";
        }
        if (direction.equalsIgnoreCase("down")) {
            oldVertical = currentPosition[0] + cubeValue;
            if (oldVertical > fl - 1) currentPosition[0] = oldVertical - fl;
            else currentPosition[0] = oldVertical;
            field[currentPosition[0]][currentPosition[1]] = " o ";
        }
        if (direction.equalsIgnoreCase("up")) {
            oldVertical = currentPosition[0] - cubeValue;
            if (oldVertical < 0) currentPosition[0] = oldVertical + fl;
            else currentPosition[0] = oldVertical;
            field[currentPosition[0]][currentPosition[1]] = " o ";
        }
        if (direction.equalsIgnoreCase("left")) {
            oldHorizontal = currentPosition[1] - cubeValue;
            if (oldHorizontal < 0) currentPosition[1] = oldHorizontal + fl;
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
        int tx = rnd.nextInt(field.length);
        System.out.println(tx);
        TPos[0] = tx;
        TPos[1] = tx;
        return tx;
    }

    static String[][] buildField(int width, int height) {
        String[][] field = new String[height][width];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = " . ";
            }
        }
        field[0][0] = " o ";
        return field;
    }

    static void printField(String[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }
}
