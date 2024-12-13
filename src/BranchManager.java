import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Manages branches and logging in a version control system.
 * This class provides methods to log commit metadata, create new branches, and switch between branches.
 */
public class BranchManager {

    /**
     * Logs the metadata of all commits in the repository.
     * Commit metadata is read from the `metadata.txt` file in each commit directory.
     *
     * @throws IOException if an I/O error occurs while accessing the commits directory or metadata files.
     */
    public static void log() throws IOException {
        Files.walk(Paths.get(Repository.getCommitsDir()), 1)
                .filter(Files::isDirectory)
                .filter(path -> !path.equals(Paths.get(Repository.getCommitsDir())))
                .forEach(commitDir -> {
                    try {
                        List<String> meta = Files.readAllLines(commitDir.resolve("metadata.txt"));
                        System.out.println(String.join("\n", meta));
                    } catch (IOException e) {
                        System.err.println("Failed to read metadata from: " + commitDir);
                        e.printStackTrace();
                    }
                });
    }

    /**
     * Creates a new branch with the specified name.
     * The branch name is appended to the branches file if it does not already exist.
     *
     * @param name The name of the branch to create.
     * @throws IOException if an I/O error occurs while accessing or updating the branches file.
     */
    public static void branch(String name) throws IOException {
        List<String> branches = Files.readAllLines(Paths.get(Repository.getBranchesFile()));

        if (branches.contains(name)) {
            System.out.println("Branch already exists: " + name);
            return;
        }

        Files.write(Paths.get(Repository.getBranchesFile()), (name + "\n").getBytes(), StandardOpenOption.APPEND);
        System.out.println("Created new branch: " + name);
    }

    /**
     * Switches to the specified branch by updating the HEAD file.
     * The branch must exist in the branches file to switch to it.
     *
     * @param name The name of the branch to switch to.
     * @throws IOException if an I/O error occurs while accessing the branches or HEAD file.
     */
    public static void checkout(String name) throws IOException {
        List<String> branches = Files.readAllLines(Paths.get(Repository.getBranchesFile()));

        if (!branches.contains(name)) {
            System.out.println("Branch does not exist: " + name);
            return;
        }

        Files.write(Paths.get(Repository.getHeadFile()), (name + "\n").getBytes());
        System.out.println("Switched to branch: " + name);
    }
}
