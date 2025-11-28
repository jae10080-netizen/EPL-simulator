package premierleague.league;

import premierleague.team.Team;
import java.util.*;

/**
 * Standings: 순위표 관리
 * - 매 라운드마다 update(teams) 호출로 정렬 갱신
 * - print()로 현재 순위를 콘솔에 출력
 */
public class Standings {

    private List<Team> table = new ArrayList<>();

    /**
     * 매 라운드 후 테이블 업데이트
     */
    public void update(List<Team> teams) {
        table = new ArrayList<>(teams);

        // 정렬 기준: 승점 → 득실차 → 득점 → 팀명
        table.sort((a, b) -> {
            if (b.getPoints() != a.getPoints())
                return b.getPoints() - a.getPoints();
            if (b.getGoalDifference() != a.getGoalDifference())
                return b.getGoalDifference() - a.getGoalDifference();
            if (b.getGoalsFor() != a.getGoalsFor())
                return b.getGoalsFor() - a.getGoalsFor();
            return a.getName().compareTo(b.getName());
        });
    }

    /**
     * 순위표 출력
     */
    public void print() {
        System.out.println("\n=== 25-26 프리미어리그 순위표 ===");
        System.out.println("순위 | 팀                    |  승점 |  승 |  무 |  패 |  득점 |  실점 |  득실차");
        int pos = 1;
        for (Team t : table) {
            System.out.printf("%3d | %-20s | %4d | %3d | %3d | %3d | %4d | %4d | %+5d\n",
                    pos++,
                    t.getName(), 
                    t.getPoints(),
                    t.getWins(), //승
                    t.getDraws(), //무
                    t.getLosses(), //패
                    t.getGoalsFor(), 
                    t.getGoalsAgainst(),
                    t.getGoalDifference());
        }
    }
}

