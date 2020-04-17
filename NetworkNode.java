/**
 * @author Avik Kadakia
 * email:avik.kadakia@stonybrook.edu
 * Stony Brook ID: 111304945
 *
 * Class: CSE 214.02
 * Recitation: CSE 214 - R.14
 *
 * The class creates a <code>NetworkNode</code> which holds the type of component being represented, an array of
 * children, and string for the text.
 **/
public class NetworkNode {

    private String name; // name of the network node
    private boolean isNintendo; // whether it is nintendo or not
    private boolean isBroken; // whether it is broken or not
    private NetworkNode parent; // parent node
    private NetworkNode[] children; // the children nodes
    private int numChild = 0; // number of children in the array
    private final int maxChildren = 8; // number of max children for the tree

    /**
     * A constructor for the class.
     *
     * @param nName
     *      Changes the name to the name in the parameter.
     *
     * @param nIsNintendo
     *      Changes the boolean to the boolean in the parameter.
     */
    public NetworkNode(String nName, boolean nIsNintendo)
    {
        name = nName;
        isNintendo = nIsNintendo;
        isBroken = false;
        children = new NetworkNode[maxChildren];
        numChild = 0;
    }

    /**
     * Returns the name of the node.
     *
     * @return
     *      Returns a String with the name of the Network Node.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Changes the name of the node.
     *
     * @param name
     *      Changes the name of the Network Node.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns whether it is a nintendo.
     *
     * @return
     *      Returns a boolean stating whether it is a nintendo or not.
     */
    public boolean isNintendo()
    {
        return isNintendo;
    }

    /**
     * Changes whether the device it is a nintendo or not.
     *
     * @param nintendo
     *      Changes the boolean of the device if it is a nintendo or not.
     */
    public void setNintendo(boolean nintendo)
    {
        isNintendo = nintendo;
    }

    /**
     * Returns whether it is broken.
     *
     * @return
     *      Returns a boolean stating whether it is broken or not.
     */
    public boolean isBroken()
    {
        return isBroken;
    }

    /**
     * Changes whether the device is broken or not.
     *
     * @param broken
     *
     */
    public void setBroken(boolean broken)
    {
        isBroken = broken;
    }

    /**
     * Returns the parent node.
     *
     * @return
     *      Returns the parent node.
     */
    public NetworkNode getParent()
    {
        return this.parent;
    }

    /**
     * Changes the parent of the node.
     *
     * @param parent
     *      Changes the parent of the node.
     */
    public void setParent(NetworkNode parent)
    {
        this.parent = parent;
    }

    /**
     * Returns the array of children.
     *
     * @return
     *      Returns the array of the children.
     */
    public NetworkNode[] getChildren()
    {
        return children;
    }

    /**
     * Changes the children of the Network Node.
     *
     * @param child
     *      Changes the children.
     */
    public void setChildren(NetworkNode[] child)
    {
        this.children = child;
    }

    /**
     * Returns the number of max children.
     *
     * @return
     *      Returns an integer with the max children in the array.
     */
    public int getMaxChildren()
    {
        return maxChildren;
    }

    /**
     * Returns the number of number of children.
     *
     * @return
     *      Returns an integer with the number of children.
     */
    public int getNumChild()
    {
        return numChild;
    }

    /**
     * Increases the number of children.
     */
    public void increaseNumChild()
    {
        numChild++;
    }

    /**
     * Decreases the number of children.
     */
    public void decreaseNumChild()
    {
        numChild--;
    }

    /**
     * Prints out the name of the node.
     *
     * @return
     *      Returns a string with the name of the node.
     */
    public String toString()
    {
        return this.getName();
    }
}