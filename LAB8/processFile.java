import javax.imageio.IIOException;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class processFile {
    public static void read(String input){
        try{
            File inputFile = new File(input);
            Scanner myReader = new Scanner(inputFile);

            File output = new File("drawMe.txt");
            FileWriter myWriter = new FileWriter(output);

            ArrayList<Point> Points = new ArrayList<>();

            String line = null;
            double x, y, z;
            while (myReader.hasNext()){
                line = myReader.nextLine();
                if (line.length() > 0 ) {
                    String[] words= line.split(",");
                    x = Double.parseDouble(words[0]);
                    y = Double.parseDouble(words[1]);
                    z = Double.parseDouble(words[2]);
                    Point dot = new Point(x, y, z);
                    Points.add(dot);
                    //Point dot = processData(myReader.nextLine());
                    //write(myWriter, myReader.nextLine());
                }
            }
            List<Point> newPoints = processData(Points);
            write(myWriter, newPoints);
            myReader.close();
            myWriter.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File not Found: " + input);
        }
        catch (IOException e){
            System.out.println("Failure in Writing Stage");
        }
    }
    public static void write(FileWriter output, List<Point> newPoints) throws IOException {

        for (Point point : newPoints) {
            output.write(String.valueOf(point.x)+ ", " + String.valueOf(point.y) + ", " + String.valueOf(point.z));
            output.write(System.lineSeparator());
        }
    }

    public static List<Point> processData(ArrayList<Point> oldPoints){
        List<Point> newPoints = oldPoints.stream()
                .filter(point -> point.z <= 2)
                .map(point -> new Point((point.x * 0.5) - 150, (point.y * 0.5) - 37, point.z * 0.5))
                .collect(Collectors.toList());
        return newPoints;
    }
}
