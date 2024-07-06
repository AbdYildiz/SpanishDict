import javax.sound.midi.Soundbank;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MyRegex {
//        static Pattern pattern = Pattern.compile("\\d{1,3}\\S\\d{1,3}\\S\\d{1,3}\\S\\d{1,3}");
//        static Pattern pattern = Pattern.compile("\\b(\\w+)\\b.*\\b\\1\\b", Pattern.CASE_INSENSITIVE);

    public static boolean isIP(String ip){
        Pattern pattern = Pattern.compile("^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
        return pattern.matcher(ip).matches();

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
