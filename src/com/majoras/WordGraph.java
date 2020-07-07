package com.majoras;

import java.util.HashSet;
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
        for(String wordStr : wordsIn)
        {
            words.add(new Word(wordStr));
        }

        //link Words with edges to every other Word
        for(Word word:words)
        {
            for(Word otherWord:words)
            {
                word.addEdge(otherWord);
            }
        }
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
    Set<Word> getWordsFrom(Word word, int lettersInCommon)
    {
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
     *
     * @return word with the most unique letters in common's
     */
    Word getBestWord(){
        return words.last();
    }

    /**
     *
     * @param word the word we're looking for
     * @return true iff this WordGraph contains word
     */
    boolean contains(Word word)
    {
        return words.contains(word);
    }
}