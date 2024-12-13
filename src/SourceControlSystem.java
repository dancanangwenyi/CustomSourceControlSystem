/**
 * Entry point for the source control system.
 * This class handles command-line arguments to perform operations such as initializing a repository,
 * staging files, committing changes, viewing logs, and managing branches.
 */
public class SourceControlSystem {

    /**
     * Main method to process command-line arguments and execute source control commands.
     *
     * Supported Commands:
     * - `init`: Initializes a new repository.
     * - `add <file>`: Stages the specified file for the next commit.
     * - `commit <message>`: Commits staged changes with a commit message.
     * - `log`: Displays the commit history.
     * - `branch <name>`: Creates a new branch with the given name.
     * - `checkout <name>`: Switches to the specified branch.
     *
     * @param args Command-line arguments.
     *             - The first argument specifies the command.
     *             - Subsequent arguments provide additional input (e.g., file path, branch name, or commit message).
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No command provided. Please specify a command.");
            return;
        }

        try {
            switch (args[0]) {
                case "init" -> Repository.init();

                case "add" -> {
                    if (args.length < 2) {
                        System.out.println("Usage: add <file>");
                    } else {
                        Staging.add(args[1]);
                    }
                }

                case "commit" -> {
                    if (args.length < 2) {
                        System.out.println("Usage: commit <message>");
                    } else {
                        Commit.commit(args[1]);
                    }
                }

                case "log" -> BranchManager.log();

                case "branch" -> {
                    if (args.length < 2) {
                        System.out.println("Usage: branch <name>");
                    } else {
                        BranchManager.branch(args[1]);
                    }
                }

                case "checkout" -> {
                    if (args.length < 2) {
                        System.out.println("Usage: checkout <name>");
                    } else {
                        BranchManager.checkout(args[1]);
                    }
                }

                default -> System.out.println("Unknown command: " + args[0] + ". Available commands: init, add, commit, log, branch, checkout.");
            }
        } catch (Exception e) {
            System.err.println("An error occurred while executing the command: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
