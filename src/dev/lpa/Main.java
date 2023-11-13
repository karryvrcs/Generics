package dev.lpa;


interface Player {

    // Any method we add without a method body, is implicitly public and static on an interface.
    String name();
    // A method without method body on interface, should be overridden in the class which implements this interface.
    // Record class is already override the name method (not getName(), just name() method in record)
}

record BaseballPlayer(String name, String position) implements Player{}
record FootballPlayer(String name, String position) implements Player{}
record VolleyballPlayers(String name, String position) implements Player{}

public class Main {
    public static void main(String[] args) {
        BaseballTeam phillies1 = new BaseballTeam("Philadephia Phillies");
        BaseballTeam astros1 = new BaseballTeam("Houston Astros");
        scoreResult(phillies1, 3, astros1, 5);


        SportsTeam phillies = new SportsTeam("Philadephia Phillies");
        SportsTeam astros = new SportsTeam("Houston Astros");
        scoreResult(phillies, 3, astros, 5);

        System.out.println("---");

        SportsTeam afc = new SportsTeam("Adelaide Crows");
        var tex = new FootballPlayer("Tex Walker", "Centre half forward");

        // But this team has a problem: there's no type checking when it comes to team members.
        var guthrie = new BaseballPlayer("D Guthrie", "Center Fielder");
        afc.addTeamMember(guthrie);
        afc.listTeamMembers();

        var abc = new BaseballPlayer("name ABC", "def");
        afc.addTeamMember(tex);

        afc.listTeamMembers();

        // Our team (Supposedly a football team) has a football Player and a baseball player.


        System.out.println("---");
        System.out.println("Generic:::");
        // Address this issue using generic

        // Specify the type parameter of team (baseballplayer)
        Team<BaseballPlayer, Affiliation> phillies2 = new Team("Philadephia Phillies");
        Team<BaseballPlayer, Affiliation> astros2 = new Team("Houston Astros");
        scoreResult(phillies2, 3, astros2, 5);

        System.out.println("---List Team members");
        // It's acceptable that adding a baseball player to baseball team.
        phillies2.addTeamMember(guthrie);
        phillies2.addTeamMember(abc);
        phillies2.listTeamMembers();
        // When we try to add a football player to baseball team, java throw out a Compile error:
//        phillies2.addTeamMember(tex);



        System.out.println("---");
        System.out.println(astros); // Override the toString method

        var harper = new BaseballPlayer("B Harper", "Right Fielder");
        var marsh = new BaseballPlayer("B Marsh", "Right Fielder");
        phillies.addTeamMember(harper);
        phillies.addTeamMember(marsh);
        phillies.listTeamMembers();

        System.out.println("---");
        var philly = new Affiliation("city", "Philadelphia", "US");
//        Team<VolleyballPlayers, Affiliation> volTeam = new Team<>("volTeam", philly);
        Team<VolleyballPlayers, String> volTeam = new Team<>("volTeam", "String");


        var volTom = new VolleyballPlayers("Tom", "front");
        var volJack = new VolleyballPlayers("Jack", "front");
        volTeam.addTeamMember(volTom);
        volTeam.addTeamMember(volJack);

        volTeam.listTeamMembers();


        // Obviously, we don't expect to insert a String object into our team member (Only baseball player/ football player is acceptable)
        // We have to limit the types that we use it.

//        System.out.println("-".repeat(20));
//        Team<String> adelaide = new Team<>("Adelaide Storm");
//        adelaide.addTeamMember("N Roberts");
//        adelaide.listTeamMembers();
//        var canberra = new Team<String>("Canberra Heat");
//        canberra.addTeamMember("B Black");
//        canberra.listTeamMembers();
//        scoreResult(canberra, 0, adelaide, 1);

        // We can't use generic class with primitive data type.
        // Team<int>  // ERROR!!
        // Team<Integer> // acceptable!

    }



    public static void scoreResult(BaseballTeam team1, int t1_score,
                                   BaseballTeam team2, int t2_score){
        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }

    public static void scoreResult(SportsTeam team1, int t1_score,
                                   SportsTeam team2, int t2_score){
        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }

    public static void scoreResult(Team team1, int t1_score,
                                   Team team2, int t2_score){
        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }
}