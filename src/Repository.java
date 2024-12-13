import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Handles initialization and management of a version control system repository.
 * This class provides methods to initialize the repository structure and access key repository paths.
 */
public class Repository {

    /** The root directory of the repository. */
    private static final String REPO_DIR = ".myvcs";

    /** The staging directory where files are prepared for commits. */
    private static final String STAGING_DIR = REPO_DIR + "/staging";

    /** The directory where commit data is stored. */
    private static final String COMMITS_DIR = REPO_DIR + "/commits";

    /** The file that stores branch information. */
    private static final String BRANCHES_FILE = REPO_DIR + "/branches.txt";

    /** The file that tracks the current branch (HEAD). */
    private static final String HEAD_FILE = REPO_DIR + "/HEAD";

    /**
     * Initializes the repository by creating the necessary directory structure and files.
     *
     * @throws IOException if any of the files or directories cannot be created.
     */
    public static void init() throws IOException {
        File repo = new File(REPO_DIR);

        if (repo.exists()) {
            System.out.println("Repository already initialized.");
            return;
        }

        if (!repo.mkdir()) {
            throw new IOException("Failed to create repository directory: " + REPO_DIR);
        }

        if (!new File(STAGING_DIR).mkdir()) {
            throw new IOException("Failed to create staging directory: " + STAGING_DIR);
        }

        if (!new File(COMMITS_DIR).mkdir()) {
            throw new IOException("Failed to create commits directory: " + COMMITS_DIR);
        }

        Files.write(Paths.get(BRANCHES_FILE), "main\n".getBytes());
        Files.write(Paths.get(HEAD_FILE), "main\n".getBytes());

        System.out.println("Initialized an empty repository.");
    }

    /**
     * Gets the root directory of the repository.
     *
     * @return the path to the repository directory.
     */
    public static String getRepoDir() {
        return REPO_DIR;
    }

    /**
     * Gets the path to the staging directory.
     *
     * @return the path to the staging directory.
     */
    public static String getStagingDir() {
        return STAGING_DIR;
    }

    /**
     * Gets the path to the commits directory.
     *
     * @return the path to the commits directory.
     */
    public static String getCommitsDir() {
        return COMMITS_DIR;
    }

    /**
     * Gets the path to the branches file.
     *
     * @return the path to the branches file.
     */
    public static String getBranchesFile() {
        return BRANCHES_FILE;
    }

    /**
     * Gets the path to the HEAD file.
     *
     * @return the path to the HEAD file.
     */
    public static String getHeadFile() {
        return HEAD_FILE;
    }
}
