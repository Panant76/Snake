import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class snake {

    // Условимся, что currentPosition[0] - это координата змейки по вертикали,
    // а currentPosition[1] - это координата змейки по горизонтали
    static int[] currentPosition = new int[2];
    static int[] TPosition = new int[2];
    static String[][] field;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        field = buildField(10, 10);
        printField(field);
        String direction;
        System.out.println("Любой символ для начала");
        while (!(direction = reader.readLine()).equalsIgnoreCase("Exit")) {
            int cubeValue = rollCube();
            System.out.println("Направление");
            direction= reader.readLine();
            if (direction.equals("")){return;}
            move(cubeValue, direction);
            printField(field);

            System.out.println("'roll' для продолжения");
        }
    }
    static void move(int cubeValue, String direction) {
        int oldVertical = currentPosition[0];
        int oldHorizontal = currentPosition[1];
        int oTVertical = TPosition[0];
        int oTHorizontal =TPosition[1];
        field[oldVertical][oldHorizontal] = " . ";
        field[oTVertical][oTHorizontal]=" . ";

        if (direction.equalsIgnoreCase("right")) {

            int cp = currentPosition[1] + cubeValue;
                if(cp > field.length) {
                    //System.out.println("!!!!!!");
                    currentPosition[1] = cp - field.length;
                }else currentPosition[1]=cp;
            /*TPosition[0]=TPosition[0]+cubeValue;
            TPosition[1]=TPosition[1]+cubeValue;*/
            field[currentPosition[0]][currentPosition[1]] = " o ";
            //field[TPosition[0]][TPosition[1]]=" T ";
        }
       /* if (direction.equalsIgnoreCase("down")) {
            currentPosition[0] = currentPosition[0] + cubeValue;
            field[currentPosition[0]][currentPosition[1]] = " o ";
            field[TPosition[0]+cubeValue][TPosition[1]+cubeValue]=" T ";
        }
        if (direction.equalsIgnoreCase("up")) {
            currentPosition[0] = currentPosition[0] - cubeValue;
            field[currentPosition[0]][currentPosition[1]] = " o ";
            field[TPosition[0]+cubeValue][TPosition[1]+cubeValue]=" T ";
        }
        if (direction.equalsIgnoreCase("left")) {
            currentPosition[1] = currentPosition[1] - cubeValue;
            field[currentPosition[0]][currentPosition[1]] = " o ";
            field[TPosition[0]+cubeValue][TPosition[1]+cubeValue]=" T ";
        }*/
    }

    static int rollCube() {
        Random random = new Random();
        int cubeValue = random.nextInt(7);
        System.out.println(cubeValue);
        return cubeValue;
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
