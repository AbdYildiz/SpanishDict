import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int count = scan.nextInt();

        for (int i=0; i<count; i++){
            findRepeatedWords(scan.nextLine());
        }
    }

    public static void findRepeatedWords(String sentence) {
        Set<String> seenWords = new HashSet<>();
        StringBuilder result = new StringBuilder();

        // Regex to match words
        Pattern pattern = Pattern.compile("\\b(\\w+)\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sentence);

        // Process the sentence word by word
        while (matcher.find()) {
            String word = matcher.group(1);
            String lowerCaseWord = word.toLowerCase();

            // Append the word if it's the first occurrence, otherwise skip it
            if (!seenWords.contains(lowerCaseWord)) {
                seenWords.add(lowerCaseWord);
                result.append(word).append(matcher.group().substring(word.length()));
                result.append(" ");
            }
        }
        System.out.println(result.toString().trim().replaceAll("\\s+", " "));
    }
}