package com.majoras;

import java.util.HashSet;
import java.util.Set;

/**
 * edges for the word graph with a natural order of lettersInCommon
 */
class WordEdge implements Comparable<WordEdge>
{
    private int lettersInCommon;
    private Word predecessor;
    private Word successor;

    WordEdge(Word predecessor, Word successor)
    {
        this.predecessor = predecessor;
        this.successor = successor;
        lettersInCommon = predecessor.lettersInCommon(successor);
    }

    /**
     *
     * @return a Set containing both the predecessor and successor words linked by this edge
     */
    Set<Word> linkedWords()
    {
        HashSet<Word> wordsSet = new HashSet<>();
        wordsSet.add(predecessor);
        wordsSet.add(successor);
        return wordsSet;
    }

    int getLettersInCommon() {
        return lettersInCommon;
    }

    @Override
    public int compareTo(WordEdge edge) {
        return Integer.compare(this.lettersInCommon,edge.getLettersInCommon());
    }
}