import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActivityLogger {
    private List<String> logEntries = new ArrayList<>();

    public void log(String message) {
        logEntries.add(message);
        System.out.println(message);  // Optional: Print to console for real-time feedback
    }

    public void saveLogToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (String entry : logEntries) {
                writer.write(entry + "\n");
            }
            System.out.println("Log saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getLogEntries() {
        return logEntries;
    }
}
