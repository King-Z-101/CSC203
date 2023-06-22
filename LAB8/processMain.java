public class processMain {
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException(
                    "Expected exactly 1 argument: a file name.");
        }
        String filePath = args[0];
        //String filePath = "multTest.txt";
        processFile.read(filePath);
    }

}

