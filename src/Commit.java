import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Handles committing changes in a version control system.
 * This class is responsible for creating a commit, storing staged changes, and recording metadata.
 */
public class Commit {

    /**
     * Creates a new commit with the specified commit message.
     * - The current branch is read from the HEAD file.
     * - A unique commit ID is generated, and a directory for the commit is created.
     * - All files from the staging directory are copied to the commit directory.
     * - Metadata about the commit is stored in the commit directory.
     * - The staging directory is cleared after the commit is created.
     *
     * @param message The commit message describing the changes.
     * @throws IOException if an I/O error occurs during file or directory operations.
     */
    public static void commit(String message) throws IOException {
        // Get the current branch from the HEAD file
        String branch = Files.readString(Paths.get(Repository.getHeadFile())).trim();

        // Generate a unique commit ID and create the commit directory
        String commitId = UUID.randomUUID().toString();
        Path commitDir = Paths.get(Repository.getCommitsDir(), commitId);

        try {
            Files.createDirectory(commitDir);
        } catch (IOException e) {
            throw new IOException("Failed to create commit directory: " + commitDir, e);
        }

        // Get the staging directory and copy files to the commit directory
        Path stagingDir = Paths.get(Repository.getStagingDir());
        Files.walk(stagingDir)
                .filter(Files::isRegularFile)
                .forEach(source -> {
                    try {
                        // Resolve the target path within the commit directory
                        Path target = commitDir.resolve(stagingDir.relativize(source));
                        Files.createDirectories(target.getParent()); // Ensure parent directories exist
                        Files.copy(source, target); // Copy the file
                    } catch (IOException e) {
                        System.err.println("Failed to copy file: " + source + " to " + commitDir);
                        e.printStackTrace();
                    }
                });

        // Create and write metadata for the commit
        String commitMeta = "Branch: " + branch + "\nMessage: " + message + "\n";
        try {
            Files.write(commitDir.resolve("metadata.txt"), commitMeta.getBytes());
        } catch (IOException e) {
            throw new IOException("Failed to write metadata for commit: " + commitId, e);
        }

        System.out.println("Committed changes with ID: " + commitId);

        // Clear the staging directory after committing
        Files.walk(stagingDir)
                .filter(Files::isRegularFile)
                .forEach(source -> {
                    try {
                        Files.delete(source);
                    } catch (IOException e) {
                        System.err.println("Failed to delete file from staging: " + source);
                        e.printStackTrace();
                    }
                });
    }
}
