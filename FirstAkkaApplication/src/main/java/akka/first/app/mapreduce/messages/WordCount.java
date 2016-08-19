package akka.first.app.mapreduce.messages;

/**
 * Created by Ben on 2016/8/19.
 */
public final class WordCount {
    private final String word;
    private final Integer count;

    public WordCount(String inWord, Integer inCount) {
        this.word = inWord;
        this.count = inCount;
    }

    public String getWord() {
        return word;
    }

    public Integer getCount() {
        return count;
    }
}
