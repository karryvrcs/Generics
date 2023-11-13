package dev.lpa;

import java.util.ArrayList;
import java.util.List;


// T is just a convention, short for whatever type you want to use this Team class for.
// S U V etc. for 2nd, 3rd, 4th types.

// Obviously, we don't expect to insert a String object into our team member (Only baseball player/ football player is acceptable)
// We have to limit the types that we use it.

// Generic classes can be bounded, limiting the types that can use it.
// This extends keyword doesn't have the same meaning as extends, when it's used in a class declaration.
// This isn't saying our type T extends Player, although it could.
// This is saying the parameterized type T, has to be a Player or a [subtype] of Player.
// The Player could have been either a class or an interface.

// Generic upper bounds are used with generic types to restrict the type of objects that can be stored in a collection or used in a method.
// The upper bound is specified using the extends keyword, which limits the types can be passed to the type parameter.

record Affiliation (String name, String type, String countryCode){

    @Override
    public String toString(){
        return name + " (" + type + " in " + countryCode + ")";
    }
}

public class Team<T extends Player, S> {
    private String teamName;

    // Declare variable as interface type
    private List<T> teamMembers = new ArrayList<>();
    private int totalWins = 0;
    private int totalLosses = 0;
    private int totalTies = 0;

    private S affiliation;

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public Team(String teamName, S affiliation) {
        this.teamName = teamName;
        this.affiliation = affiliation;
    }

    public void addTeamMember (T t){
        if(!teamMembers.contains(t)){
            teamMembers.add(t);
        }
    }

    public void listTeamMembers() {
        System.out.println(teamName + " Roster:");
        System.out.println((affiliation == null? "[no affiliation]" : " AFFILIATION: " + affiliation));
        int i = 1;
        for (T t: teamMembers){
            System.out.print(i + ". ");
            System.out.println(t.name());
            i++;
        }
    }

    public int ranking(){
        return (totalLosses * 2) + totalTies + 1;
    }

    public String setScore(int ourScore, int theirScore){
        String message = "lost to";
        if (ourScore > theirScore){
            totalWins++;
            message = "beat";
        } else if (ourScore == theirScore){
            totalTies++;
            message = "tied";
        } else {
            totalLosses++;
        }
        return message;
    }

    @Override
    public String toString() {
        return teamName + " (Ranked " + ranking() + ")";
    }
}
