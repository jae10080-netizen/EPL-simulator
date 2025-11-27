package premierleague.league;

import premierleague.match.Match;
import premierleague.match.MatchEngine;
import premierleague.team.Team;
import premierleague.team.Player; // 추가됨

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;   // 추가됨
import java.util.Comparator;    // 추가됨

public class League {

    private List<Team> teams;
    private List<List<Match>> schedule;
    private Team userTeam;
    private Standings standings;

    public League() {
        this.teams = new ArrayList<>();
        this.standings = new Standings();
        loadTeams();
    }

    private void loadTeams() {
        // 팀을 생성함. 여기서 팀 뒤 숫자는 가중치(weight)
        teams.add(new Team("아스널", 88));
        teams.add(new Team("맨체스터 시티", 92));
        teams.add(new Team("리버풀", 86));
        teams.add(new Team("첼시", 80));
        teams.add(new Team("맨체스터 유나이티드", 82));
        teams.add(new Team("토트넘 홋스퍼", 84));
        teams.add(new Team("뉴캐슬 유나이티드", 83));
        teams.add(new Team("브라이튼 앤 호브 알비온", 78));
        teams.add(new Team("애스턴 빌라", 81));
        teams.add(new Team("웨스트햄 유나이티드", 77));
        teams.add(new Team("브렌트포드", 76));
        teams.add(new Team("풀럼", 75));
        teams.add(new Team("울버햄프턴 원더러스", 74));
        teams.add(new Team("크리스탈 팰리스", 73));
        teams.add(new Team("에버턴", 73));
        teams.add(new Team("본머스", 72));
        teams.add(new Team("노팅엄 포레스트", 71));
        teams.add(new Team("사우샘프턴", 70));
        teams.add(new Team("레스터 시티", 72));
        teams.add(new Team("입스위치 타운", 68));
    }

    public void selectUserTeam(String name) {
        userTeam = teams.stream()
                .filter(t -> t.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public Team getUserTeam() { return userTeam; }

    public void printTeamList() {
        for (Team t : teams) System.out.println("- " + t.getName());
    }

    public void initializeSeason() {
        ScheduleGenerator generator = new ScheduleGenerator();
        schedule = generator.generate(teams);
        for (Team t : teams) t.resetStats();
    }

    public Match getUserMatch(int round) {
        if (schedule == null || round < 1 || round > schedule.size()) return null;
        for (Match m : schedule.get(round - 1)) {
            if (m.contains(userTeam)) return m;
        }
        return null;
    }

    public void simulateUserMatchWithUI(Match match) {
        MatchEngine engine = new MatchEngine(teams);
        engine.simulateMatchWithUI(match);
    }

    public void simulateOtherMatches(int round) {
        if (schedule == null || round < 1 || round > schedule.size()) return;
        MatchEngine engine = new MatchEngine(teams);
        for (Match m : schedule.get(round - 1)) {
            if (!m.contains(userTeam)) {
                engine.simulateSilently(m);
            }
        }
    }

    public void updateStandings() { standings.update(teams); }
    public void printStandings() { standings.print(); }

    /*
     시즌 종료 후 최종 결과, 우승팀, 득점왕 출력
     */
    public void printFinalResult() {
        System.out.println("\n\n############################################");
        System.out.println("#######  시즌 종료 (25-26)  #######");
        System.out.println("############################################");

        // 1. 최종 순위표 업데이트 및 출력
        standings.update(teams);
        standings.print();

        // 2. 우승팀 찾기 (Standings는 teams를 복사해서 정렬하지만, 여기서는 teams를 직접 정렬해서 찾음)
        // 로직 일관성을 위해 Standings와 동일한 로직으로 정렬
        teams.sort((a, b) -> {
            if (b.getPoints() != a.getPoints()) return b.getPoints() - a.getPoints();
            if (b.getGoalDifference() != a.getGoalDifference()) return b.getGoalDifference() - a.getGoalDifference();
            return b.getGoalsFor() - a.getGoalsFor();
        });
        Team champion = teams.get(0);

        System.out.println("\n🏆 🏆 🏆 PREMIER LEAGUE CHAMPIONS 🏆 🏆 🏆");
        System.out.println("        >>>  " + champion.getName().toUpperCase() + "  <<<");
        System.out.println("============================================");

        // 3. 득점왕(Golden Boot) 찾기
        List<Player> allPlayers = new ArrayList<>();
        for (Team t : teams) {
            allPlayers.addAll(t.getPlayers());
        }
        
        // 골 순으로 내림차순 정렬
        allPlayers.sort((p1, p2) -> p2.getGoals() - p1.getGoals());
        
        Player topScorer = allPlayers.get(0);
        
        System.out.println("\n👟 GOLDEN BOOT WINNER (득점왕)");
        System.out.println("PLAYER: " + topScorer.getName() + " (" + topScorer.getTeam().getName() + ")");
        System.out.println("GOALS : " + topScorer.getGoals());
        
        // (선택사항) 공동 득점자가 있을 경우 체크
        for(int i=1; i<allPlayers.size(); i++) {
            if(allPlayers.get(i).getGoals() == topScorer.getGoals()) {
                System.out.println("        " + allPlayers.get(i).getName() + " (" + allPlayers.get(i).getTeam().getName() + ")");
            } else {
                break;
            }
        }
        System.out.println("############################################");
    }
}