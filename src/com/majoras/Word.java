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
     *  polls all edges for their letters-in-common, then reports the number of unique LIC's to numUnique_LettersInCommon_s
     *
     *  Zero counts as a unique letter in common
     */
    private void calculateUniqueLICs()
    {
        HashSet<Integer> lettersInCommon_s = new HashSet<>();

        for(WordEdge edge : edges)
        {
            lettersInCommon_s.add(edge.getLettersInCommon());
        }
        numUnique_LettersInCommon_s = lettersInCommon_s.size();
    }

    /**
     * adds an edge linking from this Word to successor, provided edge does not already exist
     *
     * If edge does exist, addEdge does nothing
     * @param successor The word to link to from this word
     */
    void addEdge(Word successor)
    {
        //prevent addEdge from adding this word or a word already connected to this word
        boolean alreadyConnected = (successor.equals(this) || connectedWords.contains(successor));

        //if not, create a new edge connecting the two and add it to this Word's edges,
        // add successor's label to connectedWords, and finally recalculate the number of unique LIC's
        if(!alreadyConnected)
        {
            WordEdge connection = new WordEdge(this, successor);
            edges.add(connection);

            connectedWords.add(successor);

            calculateUniqueLICs();
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
     * two Words are compared by the number of unique LIC's they have; -1 if this object is less than the other
     *
     * Two words are equal (compareTo returns 0) iff Word.equals returns true; the secondary ordering of Words is
     * alphabetically.
     *
     * @param otherWord the Word to compare this word to
     * @return 1 if the other Word has fewer unique LIC's, 0 if they are equal, -1 if the other word has more unique LIC's
     */
    @Override
    public int compareTo(Word otherWord) {
        int comparison = Integer.compare(this.numUnique_LettersInCommon_s, otherWord.getNumUnique_LettersInCommon_s());
        if(comparison == 0 && !this.equals(otherWord))
        {
            comparison = label.compareTo(otherWord.getLabel());
        }
        //otherwise, equality is accurate

        return comparison;
    }

    /**
     * calculates how many letters in each relative position predecessor and successor have in common
     *
     * assumes the two words are the same case and the same length
     *
     * For example, (FORD, FORT) = 3, but (FORD, TROF) = 0
     * @param otherWord The word you're comparing against
     */
    int lettersInCommon(Word otherWord){
        assert (getLabel().length() == otherWord.getLabel().length()) : "Words are different lengths!";
        String otherString = otherWord.getLabel();

        int lettersInCommon = 0;
        char[] predecessorChars = label.toCharArray();
        char[] successorChars = otherString.toCharArray();

        for(int i = 0; i<predecessorChars.length; i++)
        {
            if(predecessorChars[i] == successorChars[i]){
                lettersInCommon++;
            }
        }

        return lettersInCommon;
    }

    int getNumUnique_LettersInCommon_s() {
        return numUnique_LettersInCommon_s;
    }
}