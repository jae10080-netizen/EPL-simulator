package premierleague.team;

public class Player {

    private String name;
    private Position position;
    private Team team;
    
    // [추가] 개인 득점 기록
    private int goals = 0;

    public Player(String name, Position position, Team team) {
        this.name = name;
        this.position = position;
        this.team = team;
    }

    public String getName() { return name; }
    public Position getPosition() { return position; }
    public Team getTeam() { return team; }
    
    // [추가] 득점 관련 메소드
    public int getGoals() { return goals; }
    public void addGoal() { this.goals++; }
}