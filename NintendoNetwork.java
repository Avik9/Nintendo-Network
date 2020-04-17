import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

/**
 * @author Avik Kadakia
 * email:avik.kadakia@stonybrook.edu
 * Stony Brook ID: 111304945
 *
 * Class: CSE 214.02
 * Recitation: CSE 214 - R.14
 *
 * The class creates a <code>NintendoNetwork</code> which will serve as the main for the project.
 */

public class NintendoNetwork {

    private static Scanner sc = new Scanner(System.in);// Scanner
    private static NetworkTree tree;// Creates a new tree
    private static NetworkNode temp = new NetworkNode("Temporary Node", false);// Creates a temporary node

    /**
     * Main method.
     */
    public static void main(String [] args)
    {
        System.out.println("Welcome to the Nintendo Network Manager.\n");

        menu();

        runMain();
    }

    /**
     * Prints the menu.
     */
    private static void menu()
    {
        System.out.println("Menu:\n\n" +
                "\tL) Load from file\n" +
                "\tP) Print Tree\n" +
                "\tC) Move cursor to a child node\n" + //Cursor to child (index number)
                "\tR) Move cursor to root\n" +
                "\tU) Move cursor up to parent\n" +
                "\tA) Add a child\n" + //(index, type, prompt for text)
                "\tX) Remove/Cut Cursor and its subtree\n" +
                "\tV) Paste Cursor and its subtree\n" +
                "\tS) Save to file\n" +
                "\tB) Mark cursor as broken/fixed\n" +
                "\tQ) Quit");
    }

    /**
     * Runs the main.
     */
    private static void runMain() {
        try {
            System.out.print("\nPlease select an option: ");
            String op = sc.next();

            char letter = op.toUpperCase().charAt(0);

            //        System.out.println("To test - Line 47, main, the letter read in is: " + letter);

            if (letter == 'Q') {
                quitProgram();
            }

            while (!(letter == 'Q')) {
                switch (letter) {
                    case ('L'):
                        loadFromFile();
                        break;

                    case ('P'):
                        printTree();
                        break;

                    case ('C'):
                        moveCursorToChildNode();
                        break;

                    case ('R'):
                        moveCursorToRoot();
                        break;

                    case ('U'):
                        moveCursorToParent();
                        break;

                    case ('A'):
                        addChild();
                        break;

                    case ('X'):
                        cutCursor();
                        break;

                    case ('V'):
                        pasteCursor();
                        break;

                    case ('S'):
                        saveToFile();
                        break;

                    case ('B'):
                        markBroken();
                        break;

                    default: {
                        throw new InvalidInputException();
                    }
                }
            }
        } catch (IndexOutOfBoundsException i) {
            System.out.println("The number you entered was too high.");

            runMain();
        } catch (FileNotFoundException f) {
            System.out.println("A file was not found. Please try again.");

            runMain();
        } catch (NoTreeException n) {
            System.out.println("A file was not found. Please try again.");

            runMain();
        } catch (HoleInArrayException h) {
            System.out.println("The position at which you tried to add a child leaves a hole in the array. Please try with a lower value.");

            runMain();
        } catch (NullPointerException s) {
            System.out.println("The option you entered is incorrect. Please try again.");

            runMain();
        } catch (InvalidInputException i) {
            System.out.println("The option you chose is incorrect. Please try again.");

            runMain();
        } catch (EndException e) {
            System.out.println("\nGoodbye!");
        }
    }

    /**
     * Loads the file.
     *
     * @throws FileNotFoundException
     *      Throws an exception if the file is not found.
     *
     * @throws HoleInArrayException
     *      Throws an exception if there is a hole in the array.
     *
     * @throws InvalidInputException
     *      Throws an exception if the input is invalid.
     */
    public static void loadFromFile() throws FileNotFoundException, HoleInArrayException, InvalidInputException
    {
        System.out.print("Please enter filename: ");
        String fileName = sc.next();

        tree = NetworkTree.readFile(fileName);
    }

    /**
     * Prints Tree.
     *
     * @throws NoTreeException
     *      Throws an exception if the tree isn't found.
     */
    public static void printTree() throws NoTreeException
    {
        if(tree == null)
        {
            throw new NoTreeException();
        }

        else
        {
            tree.print(tree.getRoot(), 2);

            runMain();
        }
    }

    /**
     * Moves the cursor to its child node.
     *
     * @throws NoTreeException
     *      Throws an exception if the tree isn't found.
     *
     * @throws InvalidInputException
     *      Throws an exception if the input is invalid.
     */
    public static void moveCursorToChildNode() throws NoTreeException, InvalidInputException
    {
        if(tree == null)
        {
            throw new NoTreeException();
        }

        else
        {
            System.out.print("Please enter an index: ");
            tree.cursorToChild(sc.nextInt() - 1);

            System.out.println("Cursor moved to " + tree.getCursor().getName() + ".\n");

            runMain();
        }
    }

    /**
     * Moves cursor to the root.
     *
     * @throws NoTreeException
     *      Throws an exception if the tree isn't found.
     */
    public static void moveCursorToRoot() throws NoTreeException
    {
        if(tree == null)
        {
            throw new NoTreeException();
        }

        else
        {
            if(tree.getCursor() == tree.getRoot())
            {
                System.out.println("The cursor is already at root.");
            }

            else
            {
                tree.cursorToRoot();

                System.out.println("Cursor moved to " + tree.getCursor().getName() + ".\n");

                runMain();
            }
        }
    }

    /**
     * Moves cursor to the parent.
     *
     * @throws NoTreeException
     *      Throws an exception if the tree isn't found.
     */
    public static void moveCursorToParent() throws NoTreeException
    {
        if(tree == null)
        {
            throw new NoTreeException();
        }

        else
        {
            if (tree.getCursor() == tree.getRoot())
            {
                System.out.println("The cursor is at root.");
            }

            else
            {
                tree.cursorToParent();

                System.out.println("Cursor moved to " + tree.getCursor().getName() + ".\n");

                runMain();
            }
        }
    }

    /**
     * Adds a child to the tree.
     *
     * @throws HoleInArrayException
     *      Throws an exception if there is a hole in the array.
     */
    public static void addChild() throws HoleInArrayException
    {
        System.out.print("Please enter an index: ");
        int index = sc.nextInt();

        System.out.print("Index registered as: " + index);

        boolean isNintendo = false;

        System.out.print("\n\nIs nintendo registered as: " + isNintendo);

        System.out.print("\n\nPlease enter device name: ");
        String deviceName = sc.next();

        System.out.print("\n\ndeviceName registered as: " + deviceName);

        System.out.print("\n\nIs this Nintendo (Y/N): ");
        String input = sc.next();

        char nintendo = (input.toUpperCase()).charAt(0);

        if(nintendo == 'Y')
        {
            isNintendo = true;
        }

        NetworkNode newNode = new NetworkNode(deviceName, isNintendo);

        if(tree == null)
        {
            index = 0;
            tree = new NetworkTree();
            tree.addChild(index, newNode);
        }

        else
        {
            tree.addChild(index, newNode);

            System.out.println("Device added.");
        }

        runMain();
    }

    /**
     * Cuts the cursor.
     *
     * @throws NoTreeException
     *      Throws an exception if the tree isn't found.
     */
    public static void cutCursor() throws NoTreeException
    {
        if(tree == null)
        {
            throw new NoTreeException();
        }

        else
        {
            if(tree.getCursor() == tree.getRoot())
            {
                temp = tree.cutCursor();
                System.out.println("The entire tree has been cut.");
            }

            else
            {
                temp = tree.cutCursor();

                System.out.println(temp.toString() + " cut, cursor is at " + tree.getRoot().toString());
            }

            tree.cursorToRoot();
        }

        runMain();
    }

    /**
     * Pastes the cursor.
     *
     * @throws HoleInArrayException
     *      Throws an exception if there is a hole in the array.
     */
    public static void pasteCursor() throws HoleInArrayException
    {
        if(temp.getName().equals("Temporary Node"))
        {
            System.out.println("The cursor has not been cut yet.");
        }

        else
        {
            if(tree == null)
            {
                tree = new NetworkTree();
                tree.addChild(0, temp);

                System.out.println(tree.getRoot().toString() + " has been pasted as the root.");
            }

            else
            {
                tree.pasteChild(temp);
            }
        }

        runMain();
    }

    /**
     * Saves the tree to a file.
     *
     * @throws FileNotFoundException
     *      Throws an exception if the file is not found.
     *
     * @throws NoTreeException
     *      Throws an exception if the tree isn't found.
     */
    public static void saveToFile() throws FileNotFoundException, NoTreeException
    {
        if(tree == null)
        {
            throw new NoTreeException();
        }

        String fileName = sc.nextLine();

        tree.writeToFile(tree, fileName);

        runMain();
    }

    /**
     * Marks the cursor is broken.
     *
     * @throws NoTreeException
     *      Throws an exception if the tree isn't found.
     */
    public static void markBroken() throws NoTreeException
    {
        if(tree == null)
        {
            throw new NoTreeException();
        }

        else
        {
            tree.getCursor().setBroken(!tree.getCursor().isBroken());

            runMain();
        }
    }

    /**
     * Quits program.
     *
     * @throws EndException
     *      Throws an exception if the method is called.
     */
    public static void quitProgram() throws EndException
    {

        System.out.println("\nIt was fun working with you. Have a good rest of the day!");

        throw new EndException();

    }
}