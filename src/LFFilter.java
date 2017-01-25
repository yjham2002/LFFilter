import java.util.*;

public class LFFilter {

    protected static class Bigram{
        public String concat, w1, w2;
        public Bigram(String w1, String w2){
            concat = w1 + "," + w2;
            this.w1 = w1;
            this.w2 = w2;
        }
    }

    public static WordSet execute(WordSet wordSet, Map<String,Integer> map){
        WordSet retVal = wordSet;
        boolean flag = true;
        while(flag){
            flag = false;
            for(Bigram b : getBigram(retVal.getWords()))
                if (map.containsKey(b.concat)) {
                    flag = true;
                    for(int i = 0; i < retVal.getWords().size(); i++)
                        if(retVal.getWords().get(i).getNorWord().equals(b.w1)) retVal.getWords().remove(i);
                }
        }
        Collections.sort(retVal.getWords(), new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.getNorWord().compareTo(o2.getNorWord());
            }
        });
        return retVal;
    }

    protected static List<Bigram> getBigram(List<Word> list){
        List<Bigram> retVal = new ArrayList<Bigram>();
        for(Word w1 : list) for(Word w2 : list)
                if(!w1.getNorWord().equals(w2.getNorWord())) retVal.add(new Bigram(w1.getNorWord(), w2.getNorWord()));
        return retVal;
    }

}
