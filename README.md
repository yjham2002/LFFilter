# LeaderFollower Filter

- A filter for extracting atomic keyword from queried sentence

*Conception*

> This filter excludes the word token which is enclosing some other keyword.
> Therefore, a word list that has been applied this filter will consist of atomic keyword(Lowest Range).
> By this filter, the efficiency of search engine will be increased since the amount of words to search is small.

*Usage*

```java
import java.util.HashMap;
import java.util.Map;

public class AppMain {
    public static void main(String[] args){
        String s = "";
        WordSet wordSet = new WordSet();
        wordSet.addWord(new Word("대한민국", "대한민국", 0));
        wordSet.addWord(new Word("서울", "서울", 0));
        wordSet.addWord(new Word("학동", "학동", 0));
        wordSet.addWord(new Word("과일", "과일", 0));
        wordSet.addWord(new Word("배", "배", 0));
        wordSet.addWord(new Word("사과", "사과", 0));

        Map<String,Integer> map = new HashMap<>();
        map.put("학동,배", 0);
        map.put("과일,사과", 0);
        map.put("과일,배", 0);
        map.put("대한민국,서울", 0);
        map.put("대한민국,학동", 0);
        map.put("서울,학동", 0);

        s = "";
        for(Word w : wordSet.getWords()) s += w.getNorWord() + " ";
        System.out.println("검색키워드 [" + s.trim() + "]");
        s = "";
        for(Word ws : LFFilter.execute(wordSet, map).getWords()) s += ws.getNorWord() + " ";
        System.out.println("추출키워드 [" + s.trim() + "]");
    }
}
```

*Output*
```sh
검색키워드 [대한민국 서울 학동 과일 배 사과]
추출키워드 [배 사과]

Process finished with exit code 0
```

> This project is made for supplement of search engine developed by RICHWARE SYSTEMS(C)