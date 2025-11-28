package premierleague.match;

import premierleague.team.Team;
import java.util.ArrayList;
import java.util.List;

public class Match {

    private Team home;
    private Team away;
    private int homeScore = 0;
    private int awayScore = 0;
    private List<Event> events = new ArrayList<>();

    public Match(Team home, Team away) {
        this.home = home;
        this.away = away;
    }

    public Team getHomeTeam() { return home; }
    public Team getAwayTeam() { return away; }
    public int getHomeScore() { return homeScore; }
    public int getAwayScore() { return awayScore; }
    public List<Event> getEvents() { return events; }

    public boolean contains(Team t) {
        if (t == null) return false;
        return t.equals(home) || t.equals(away);
    }

    public void addEvent(Event e) {
        if (e != null) events.add(e);
        
        if (e != null && e.getType() == Event.EventType.GOAL) {
            // 팀 점수 반영
            if (e.getPlayer().getTeam().equals(home)) homeScore++;
            else if (e.getPlayer().getTeam().equals(away)) awayScore++;

            // 선수 개인 득점 기록 추가
            e.getPlayer().addGoal();
        }
    }

    public void finish() {
        home.addMatchResult(homeScore, awayScore);
        away.addMatchResult(awayScore, homeScore);
    }

    public void setScore(int homeScore, int awayScore) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }
}