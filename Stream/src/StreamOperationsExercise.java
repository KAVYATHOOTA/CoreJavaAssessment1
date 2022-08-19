import java.util.*;
import java.util.stream.Collectors;

public class StreamOperationsExercise {
    public static void main(String[] args) {
        List<Player> player = Arrays.asList(new Player("Sai",400,20000,400,"India"),new Player("Ram",423,21000,420,"England"),new Player("Kittu",425,22000,425,"India"),new Player("Rohit",300,15000,500,"India"),new Player("Dev",200,12000,250,"India"));

        displayPlayers(player);
        displayPlayerForCountry(player,"India");
        getPlayerNames(player);
        getAverageRunsByCountry(player,"India");
        getPlayerNamesSorted(player);
        getPlayerCountry(player);
        getMaxRunsPlayer(player);
        findPlayer( player,"Ram");
        checkHighScorerByCountry(player ,"India");
    }
    public static void displayPlayers(List<Player> p) {
        List<String> playerName = p.stream().map(u->u.getPlayerName()).collect(Collectors.toList());
        System.out.println(playerName);
    }
    static void displayPlayerForCountry(List<Player> p,String country){
        List<String> playerNameByCountry =  p.stream()
                .filter(player ->
                        player.getCountry().equals(country) && player.getHighestScore() > 100
                ).map(u->u.getPlayerName()).collect(Collectors.toList());
        System.out.println(playerNameByCountry);
    }
    static void getPlayerNames(List<Player> p) {
        LinkedList<String> l1 = p.stream().filter(player -> player.getRuns() > 5000)
                .sorted((p1, p2) -> p2.getPlayerName().compareTo(p1.getPlayerName()))
                .map(
                        player -> player.getPlayerName()
                ).collect(Collectors.toCollection(LinkedList::new));
        System.out.println(l1);
    }
    public static void getAverageRunsByCountry(List<Player> p,String country) {
        List<Integer> getRuns = p.stream().filter(player -> {
                    return player.getCountry().equals(country);
                }).map(p1->p1.getRuns())
                .collect(Collectors.toList());
        OptionalDouble average = getRuns.stream().mapToInt(x1-> x1.intValue()).average();
        System.out.println("AverageRuns"+average);
    }
    public static void getPlayerNamesSorted(List<Player> p){
        List<String> l3 =p.stream()
                .sorted((p1, p2) -> p1.getCountry().compareTo(p2.getCountry())
                ).map(p1->p1.getPlayerName()).collect(Collectors.toList());
        System.out.println("By country  "+l3);
        List<String> l4 = p.stream().sorted(Comparator.comparingInt(Player::getMatchesPlayed))
                .map(p1->p1.getPlayerName()).collect(Collectors.toList());
        System.out.println("By matches played  "+ l4);
    }
    public static void getPlayerCountry(List<Player> p){
        Map<Object, Object> countryNameWithPlayerName = p.stream().filter(player -> player.getMatchesPlayed() > 200).collect(Collectors.toMap(p1->p1.getPlayerName(), p1->p1.getCountry()));
        System.out.println(countryNameWithPlayerName);
    }
    public static void getMaxRunsPlayer(List<Player> p) {
        Player product1 = p.stream().max((p1,p2)->p1.getRuns() > p2.getRuns() ? 1:-1).get();
        System.out.println(product1);
    }
    public static void findPlayer(List<Player> p,String name) {
        String james = p.stream()
                .filter(customer -> name.equals(customer.getPlayerName())).map(p1->p1.getCountry())
                .findAny()
                .orElse(null);
        System.out.println(james);
    }
    public static void checkHighScorerByCountry(List<Player> p,String name) {
        boolean match = p.stream().anyMatch(s -> s.getRuns()>10000 && s.getCountry().equals(name)
        );
        System.out.println(match);

    }
}