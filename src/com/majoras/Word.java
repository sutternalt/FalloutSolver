package com.majoras;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *  Node class for the word graph with a natural order of numUnique_LettersInCommon_s
 */
class Word implements Comparable<Word>
{
    private String label; //the literal word
    private int numUnique_LettersInCommon_s; //the number of unique number-of-letters-in-common's this word has relative to all other words in the graph
    private TreeSet<WordEdge> edges; //sorted set of edges arranged by number of connections
    private Set<Word> connectedWords; //set of words connected to by this word

    Word(String word)
    {
        edges = new TreeSet<>();
        connectedWords = new HashSet<>();
        numUnique_LettersInCommon_s = 0;
        this.label = word;
    }

    /**
     * adds an edge linking from this Word to successor, provided edge does not already exist
     * @param successor The word to link to from this word
     */
    void addEdge(Word successor)
    {
        //check if successor is in connectedWords
        boolean alreadyConnected = connectedWords.contains(successor);

        //if not, create a new edge connecting the two and add it to this Word's edges,
        // add successor's label to connectedWords, and finally recalculate the number of unique LIC's
        if(!alreadyConnected)
        {
            WordEdge connection = new WordEdge(this, successor);
            edges.add(connection);

            connectedWords.add(successor);

            WordGraph.calculateUniqueLICs(this);
        }
        //otherwise, do nothing; this edge has already been added
    }

    String getLabel()
    {
        return label;
    }

    TreeSet<WordEdge> getEdges()
    {
        return edges;
    }

    /**
     * two Words are equal if they have the same label
     * @param o the object to compare to
     * @return true if the two objects are Words and have the same label; false otherwise
     */
    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Word objAsWord = (Word) o;
        return label.equals(objAsWord.getLabel());
    }
    @Override
    public int hashCode(){
        return label.hashCode();
    }

    /**
     * two Words are compared by the number of unique LIC's they have
     * @param word the Word to compare this word to
     * @return 1 if the other Word has fewer unique LIC's, 0 if they are equal, -1 if the other word has more unique LIC's
     */
    @Override
    public int compareTo(Word word) {
        return Integer.compare(this.numUnique_LettersInCommon_s, WordGraph.getNumUnique_LettersInCommon_s(word.numUnique_LettersInCommon_s));
    }

    /**
     * calculates how many letters in each relative position predecessor and successor have in common
     *
     * assumes the two words are the same case and the same length
     *
     * For example, (FORD, FORT) = 3, but (FORD, TROF) = 0
     * @param wordEdge
     */
    void calculateLettersInCommon(WordEdge wordEdge){
        assert (getLabel().length() == wordEdge.successor.getLabel().length()) : "Words are different lengths!";

        wordEdge.lettersInCommon = 0;
        char[] predecessorChars = getLabel().toCharArray();
        char[] successorChars = wordEdge.successor.getLabel().toCharArray();

        for(int i = 0; i<predecessorChars.length; i++)
        {
            if(predecessorChars[i] == successorChars[i]){
                wordEdge.lettersInCommon++;
            }
        }
    }
}