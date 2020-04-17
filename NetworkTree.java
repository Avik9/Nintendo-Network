import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Avik Kadakia
 * email:avik.kadakia@stonybrook.edu
 * Stony Brook ID: 111304945
 *
 * Class: CSE 214.02
 * Recitation: CSE 214 - R.14
 *
 * The class creates a <code>NetworkTree</code> which will serve as the tree manager for the NetworkTree.
 */
public class NetworkTree
{
    private NetworkNode root; // root of the tree
    private NetworkNode cursor; // cursor of the tree

    /**
     * Makes the cursor at the root.
     */
    public void cursorToRoot()
    {
        cursor = root;
    }

    /**
     * Cuts the node at the cursor.
     *
     * @return
     *      Returns the node at the cursor.
     */
    public NetworkNode cutCursor()
    {
        NetworkNode temp = null;

        if(cursor == root)
        {
            temp = root;
        }

        else
        {
            temp = cursor;

            cursor.getParent().decreaseNumChild();

            cursor = null;

            cursor = root;
        }

        return temp;
    }

    /**
     * Adds a child to the current tree.
     *
     * @param index
     *      Adds a child at this given space.
     *
     * @param node
     *      Adds this node as the child.
     *
     * @throws HoleInArrayException
     *      Throws an exception if the index that it is being added at leaves a hole in the array.
     *
     * @throws IndexOutOfBoundsException
     *      Throws an exception nwhenn the index entered is greater than the size of the array.
     */
    public void addChild(int index, NetworkNode node) throws HoleInArrayException, IndexOutOfBoundsException
    {
        // Tree was never created, add the new node as a root.
        if(root == null){
            this.root = node;
            this.cursor = node;
            return;
        }

        NetworkNode[] children = cursor.getChildren();

        // Checking whether the index being inserted is a valid one.

        // Index is out of the bounds of the array.
        if(index > 9 || index < 0) throw new IndexOutOfBoundsException();

        // Index before is empty, thus adding it at this index would create an exception.
        else if(index != 0 && children[index - 1] == null){
            throw new HoleInArrayException();
        }

        else
        {
            // Spot is taken.
            if(children[index] != null){
                // Move all the other elements up.
                for(int i = cursor.getNumChild(); i > index; i--)
                {
                    children[i] = children[i-1];
                }
            }

            // Add the node to the children of the parent.
            children[index] = node;
            children[index].setParent(getCursor());
            getCursor().increaseNumChild();
        }
    }

    /**
     * Pastes a child in the tree as the cursor's child.
     *
     * @param child
     *      Passtes this node as a child of the cursor.
     *
     * @throws HoleInArrayException
     *      Throws an exception when pasting the creates a hole in the array.
     *
     * @throws SlotTakenException
     *      Tries to paste the
     */
    public void pasteChild(NetworkNode child) throws HoleInArrayException
    {
        addChild(cursor.getNumChild() + 1, child);
    }

    /**
     *  Moves cursor to the child.
     *
     * @param index
     *      Takes in the cursor to move to.
     *
     * @throws InvalidInputException
     *      If the index entered is wrong, it throws an exception.
     */
    public void cursorToChild(int index) throws InvalidInputException
    {
        if(index < 0)
        {
            throw new InvalidInputException();
        }
        else {
            cursor = cursor.getChildren()[index];
        }
    }

    /**
     * Moves cursor to the parent.
     */
    public void cursorToParent()
    {
        if(cursor == root)
        {
            System.out.println("Cursor is at the root.");
        }
        else
        {
            cursor = cursor.getParent();
        }
    }

    /**
     *  Creates a file with the tree.
     *
     * @param tree
     *      Writes the tree passed in to the file.
     *
     * @param filename
     *      Names the file as the passed name.
     *
     * @throws FileNotFoundException
     *      Throws an exception if the file is not found.
     */
    public static void writeToFile(NetworkTree tree, String filename) throws FileNotFoundException
    {
        File f1 = new File(filename);

        PrintWriter pw = new PrintWriter(f1);

        pw.println(tree.getTreeForFile(tree.getRoot(), 0));

        pw.close();
    }

    /**
     *      Reads the file and creates a file.
     *
     * @param fileName
     *      Reads the file with the passed name.
     *
     * @return
     *      Returns the tree created.
     *
     * @throws FileNotFoundException
     *      Throws an exception if the file is not found.
     *
     * @throws HoleInArrayException
     *      Throws an exception if there is a hole in the array.
     *
     * @throws InvalidInputException
     *      Throws an exception if the entered value is incorrect.
     */
    public static NetworkTree readFile(String fileName) throws FileNotFoundException, HoleInArrayException, InvalidInputException
    {
        NetworkTree tree = new NetworkTree();

        File file = new File(fileName);
        Scanner fileReader = new Scanner(file);
        int indexToAddAt = -1;

        // Create the root of the tree.
        tree.addChild(0, new NetworkNode(fileReader.nextLine(), false));

        // Go through the entire file.
        while(fileReader.hasNextLine()){

            String toParse = fileReader.nextLine();

            for(int i = 0; i < toParse.length(); i++){

                // Traverse the tree.
                if(Character.isDigit(toParse.charAt(i)) && Character.isDigit(toParse.charAt(i+1))){
                    tree.cursorToChild(Character.getNumericValue(toParse.charAt(i)) - 1);

                }
                else{
                    indexToAddAt = Character.getNumericValue(toParse.charAt(i)) - 1;
                    toParse = toParse.substring(i + 1);
                    break;
                }
            }
            if(toParse.contains("-"))
            {
                tree.addChild(indexToAddAt, new NetworkNode(toParse.substring(1), true));
            }else{
                tree.addChild(indexToAddAt, new NetworkNode(toParse, false));
            }

            tree.cursorToRoot();
        }

        return tree;
    }

    /**
     *  Returns the cursor.
     *
     * @return
     *      Returns the cursor.
     */
    public NetworkNode getCursor()
    {
        return cursor;
    }

    /**
     *  Returns the root.
     *
     * @return
     *      Returns the root.
     */
    public NetworkNode getRoot()
    {
        return root;
    }

    /**
     * Prints the tree.
     *
     * @param node
     *      Prints the tree at this node.
     */
    public void print(NetworkNode node, int spaces)
    {

        if(node == cursor)
        {
            System.out.print("->");
        }

        if(node.isNintendo())
        {
            System.out.print("-");
        }
        else
        {
            System.out.print("+");
        }

        System.out.print(node.toString() + "\n");

        if (node.isBroken())
        {
            System.out.print(" ~Fault~");
        }

        for(int i = 0; i < node.getNumChild(); i++)
        {
            for(int s = 0; s < spaces; s++){
                System.out.print("  ");
            }
            print(node.getChildren()[i], spaces + 1);
        }
    }

    /**
     *  Adds a tree in the file.
     *
     * @param node
     *      Prints the tree at this node.
     *
     * @param spaces
     *      Enters the number of spaces.
     *
     * @return
     *      Returns the string of the printed file.
     */
    public String getTreeForFile(NetworkNode node, int spaces){

        String treeList = "";
        treeList += node.getName() + "\n";

        if(node.getNumChild() == 0){
            return node.getName() + "\n";
        }

        for(int i = 0; i < node.getNumChild(); i++){

            treeList += (i + 1) + getTreeForFile(node.getChildren()[i], spaces + 1);

        }
        return treeList;
    }
}
