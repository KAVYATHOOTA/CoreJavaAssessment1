import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollectorsExercise {
    public static void main(String[] args) {
        List<Player> player = Arrays.asList(new Player("Sai",400,20000,400,"India"),new Player("Ram",423,21000,420,"England"),new Player("Kittu",425,22000,425,"India"),new Player("Rohit",300,15000,500,"India"),new Player("Dev",200,12000,250,"India"));
        getPlayersByCountry(player);
        getPlayerNamesByCountry(player);
        getTotalPlayersByCountry(player);
        getTotalRunsByCountry(player);
    }
    public static void getPlayersByCountry(List<Player> p){
        Map<Object, Object> countryNameWithPlayerName = p.stream().collect(Collectors.toMap(p1->p1.getPlayerName(), p1->p1.getCountry()));
        System.out.println(countryNameWithPlayerName);
    }
    public static void getPlayerNamesByCountry(List<Player> p){
        Map<Object, Object> countryNameWithPlayerName1 = p.stream().filter(player -> player.getMatchesPlayed() > 100).collect(Collectors.toMap(p1->p1.getPlayerName(),p1->p1.getCountry()));
        System.out.println(countryNameWithPlayerName1);
    }
    public static void getTotalPlayersByCountry(List<Player> p) {
        Map<String, Long>  n2 = p.stream().collect(Collectors.groupingBy(k -> k.getCountry(), Collectors.counting()));;
        System.out.println(n2);
    }
    public static void getTotalRunsByCountry(List<Player> p) {
        Map<String, Long>  n3 = p.stream().collect(Collectors.groupingBy(k -> k.getCountry(), Collectors.summingLong(p5->p5.getRuns())));
        System.out.println(n3);
    }
}