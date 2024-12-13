import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Handles file staging in a version control system.
 * This class allows files to be staged (prepared for commit) by copying them to the staging area.
 */
public class Staging {

    /**
     * Adds a file to the staging area.
     * - Verifies that the source file exists before proceeding.
     * - Creates necessary parent directories in the staging area.
     * - Copies the file from the source location to the staging directory.
     * - Overwrites any existing file in the staging area with the same name.
     *
     * @param filePath The path of the file to be staged, relative to the working directory.
     * @throws IOException if an I/O error occurs during file or directory operations.
     */
    public static void add(String filePath) throws IOException {
        // Resolve the source file and staging area paths
        Path sourcePath = Paths.get(filePath);
        Path stagingPath = Paths.get(Repository.getStagingDir(), filePath);

        // Check if the source file exists
        if (!Files.exists(sourcePath)) {
            System.out.println("File does not exist: " + filePath);
            return;
        }

        // Ensure the parent directories in the staging area exist
        try {
            Files.createDirectories(stagingPath.getParent());
        } catch (IOException e) {
            throw new IOException("Failed to create directories for staging path: " + stagingPath.getParent(), e);
        }

        // Copy the file to the staging area
        try {
            Files.copy(sourcePath, stagingPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Added " + filePath + " to staging area.");
        } catch (IOException e) {
            throw new IOException("Failed to copy file to staging area: " + sourcePath + " -> " + stagingPath, e);
        }
    }
}
