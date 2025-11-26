package premierleague.league;

import premierleague.match.Match;
import premierleague.team.Team;

import java.util.*;

/**
 * ScheduleGenerator
 * - 라운드 로빈 알고리즘으로 기본 19라운드를 만들고,
 * - 홈/어웨이 반전 라운드를 추가하여 총 38라운드 생성
 */
public class ScheduleGenerator {

    /**
     * 20팀 기준 Round-Robin 스케줄 생성
     * @param teams 20개의 팀 리스트
     * @return 38개의 라운드(각 라운드는 Match 리스트)
     */
    public List<List<Match>> generate(List<Team> teams) {

        int n = teams.size();
        List<Team> temp = new ArrayList<>(teams);

        // 팀이 홀수면 bye 추가 (본 프로젝트는 20팀으로 짝수)
        if (n % 2 == 1) {
            temp.add(null);
            n++;
        }

        List<List<Match>> rounds = new ArrayList<>();
        int half = n / 2;

        for (int round = 0; round < n - 1; round++) {
            List<Match> roundMatches = new ArrayList<>();
            for (int i = 0; i < half; i++) {
                Team home = temp.get(i);
                Team away = temp.get(n - 1 - i);
                if (home != null && away != null) {
                    roundMatches.add(new Match(home, away));
                }
            }
            rounds.add(roundMatches);

            // 회전(첫번째 팀은 고정)
            Team last = temp.remove(temp.size() - 1);
            temp.add(1, last);
        }

        // rounds는 19라운드. 홈/어웨이 반전 라운드를 추가하여 38라운드 완성
        List<List<Match>> allRounds = new ArrayList<>();
        allRounds.addAll(rounds);
        for (List<Match> r : rounds) {
            List<Match> rev = new ArrayList<>();
            for (Match m : r) {
                rev.add(new Match(m.getAwayTeam(), m.getHomeTeam()));
            }
            allRounds.add(rev);
        }

        return allRounds;
    }
}
