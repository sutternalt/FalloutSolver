package com.majoras;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * A complete (saturated?) graph of Words with edgeweights equal to the number of letters each word has in common
 * at each letter position
 */

class WordGraph {

    private TreeSet<Word> words;

    WordGraph(String[] wordsIn)
    {
        //create unlinked Words
        words = new TreeSet<>();
        HashSet<Word> unorderedWords = new HashSet<>();

        for(String wordStr : wordsIn)
        {
            unorderedWords.add(new Word(wordStr));
        }

        //link Words with edges to every other Word
        for(Word word:unorderedWords)
        {
            for(Word otherWord:unorderedWords)
            {
                word.addEdge(otherWord);
            }
        }

        //add all the words to the treeSet
        words.addAll(unorderedWords);
    }

    /**
     * generates a set of words reachable from this word that have the specified number of letters in common
     *
     * [dev note: I'm uncomfortable with this method being static]
     *
     * @param word the origin word in the graph
     * @param lettersInCommon the number of letters in common we're filtering neighbors by
     * @return a set of words with the number of lettersInCommon to the input word
     */
    Set<Word> getWordsFrom(String wordLabel, int lettersInCommon)
    {
        Word word = this.find(wordLabel);

        //get a set of all edges from the word
        //then filter that set down to edges with only the correct number of letters in common
        Set <WordEdge> edgesWeCareAbout = word.getEdges()
                .stream()
                .filter(edge -> (edge.getLettersInCommon() == lettersInCommon))
                .collect(Collectors.toSet());
        //get a set of words from that set of edges
        Set<Word> linkedwords = new HashSet<>();
        edgesWeCareAbout.forEach(edge -> linkedwords.addAll(edge.linkedWords()));
        //get rid of the word we're coming from in that set
        linkedwords.remove(word);
        //return that set
        return linkedwords;
    }

    /**
     * Finds the word with the label contained in this graph or returns null
     * @param label
     * @return
     */
    private Word find(String label)
    {
        Word foundWord = null;
        Word word;

        Iterator it = words.iterator();
        while(foundWord == null && it.hasNext())
        {
           word = (Word)it.next();
            if(word.getLabel().equals(label))
            {
                foundWord = word;
            }
        }

        return foundWord;
    }

    /**
     *
     * @return word with the most unique letters in common's
     */
    Word getBestWord(){
        return words.last();
    }

    /**
     * Checks to see if the wordgraph contains a particular word
     *
     * Note that this must check by label to account for newly-created/unlinked words having a unique LIC's count of 0,
     * while those in the graph would have a non-zero unique LIC's
     *
     * @param wordLabel the label of the word we're looking for
     * @return true iff this WordGraph contains word
     */
    boolean contains(String wordLabel)
    {
        HashSet<String> wordLabels = new HashSet<>();
        for(Word w : words)
        {
            wordLabels.add(w.getLabel());
        }
        return wordLabels.contains(wordLabel);
    }
}