package premierleague.match;

import premierleague.team.Team;
import premierleague.util.RandomEngine;
import premierleague.util.ProbabilityCalculator;

import java.util.Collections; 
import java.util.Comparator;  
import java.util.List;

/**
 * MatchEngine: 실제 경기 시뮬레이션을 담당
 * - simulateMatchWithUI: 이벤트를 시간순으로 정렬하여 출력하도록 수정됨
 */
public class MatchEngine {

    private List<Team> teams;

    public MatchEngine(List<Team> teams) {
        this.teams = teams;
    }

    /**
     * UI 표시가 필요한 경기 (유저 팀 포함)
     * 수정: 이벤트를 생성 즉시 출력하지 않고, 모아서 시간순 정렬 후 출력
     */
    public void simulateMatchWithUI(Match match) {
        Team home = match.getHomeTeam();
        Team away = match.getAwayTeam();
        
        
        //[수정] 찬스 횟수 1~10 -> 2~6
        int attemptsHome = RandomEngine.getInt(2, 6);
        int attemptsAway = RandomEngine.getInt(2, 6);

        System.out.println("================================");
        System.out.println(home.getName() + " (Home) vs (Away) " + away.getName());
        
        // --- 1. 이벤트 생성 및 수집 (출력 X) ---
        
        // 홈 공격 찬스
        for (int i = 0; i < attemptsHome; i++) {
            double prob = ProbabilityCalculator.goalProbability(home, away);
            if (RandomEngine.getDouble() < prob) {
                match.addEvent(Event.createGoalEvent(home));
            } else {
                match.addEvent(Event.createMinorEvent(home));
            }
        }

        // 어웨이 공격 찬스
        for (int i = 0; i < attemptsAway; i++) {
            double prob = ProbabilityCalculator.goalProbability(away, home);
            if (RandomEngine.getDouble() < prob) {
                match.addEvent(Event.createGoalEvent(away));
            } else {
                match.addEvent(Event.createMinorEvent(away));
            }
        }

        // --- 2. 시간 순서대로 정렬 (핵심) ---
        List<Event> events = match.getEvents();
        // 분 기준으로 오름차순 정렬
        Collections.sort(events, Comparator.comparingInt(Event::getMinute));

        // --- 3. 정렬된 이벤트 출력 ---
        System.out.println("--------------------------------");
        System.out.println("경기 시작 (Kick-off)");
        
        for (Event e : events) {
            // NONE 타입(아무 일도 없음)은 출력하지 않음
            if (e.getType() != Event.EventType.NONE) {
                System.out.println(e.getDescription());
                
                // (선택사항) 출력시 약간의 딜레이를 주어 긴장감 조성
                try { Thread.sleep(300); } catch (InterruptedException ex) {}
            }
        }
        System.out.println("경기 종료 (Full-time)");

        // --- 4. 결과 출력 및 마무리 ---
        System.out.println("--------------------------------");
        System.out.println("결과: " + home.getName() + " " + match.getHomeScore() +
                " : " + match.getAwayScore() + " " + away.getName());
        System.out.println("================================");

        // 경기 마무리 (점수 반영, 승점 반영)
        match.finish();
    }

    /**
     * UI 없이 결과만 시뮬레이션 (다른 9경기) - 기존과 동일
     */
    public void simulateSilently(Match match) {
        Team home = match.getHomeTeam();
        Team away = match.getAwayTeam();
        
        //[수정] 찬수 횟수 하향 위와 동일
        int attemptsHome = RandomEngine.getInt(2, 6);
        int attemptsAway = RandomEngine.getInt(2, 6);

        int homeGoals = 0;
        int awayGoals = 0;

        for (int i = 0; i < attemptsHome; i++) {
            double prob = ProbabilityCalculator.goalProbability(home, away);
            if (RandomEngine.getDouble() < prob) {
                match.addEvent(Event.createGoalEvent(home));
                homeGoals++;
            }
        }

        for (int i = 0; i < attemptsAway; i++) {
            double prob = ProbabilityCalculator.goalProbability(away, home);
            if (RandomEngine.getDouble() < prob) {
                match.addEvent(Event.createGoalEvent(away));
                awayGoals++;
            }
        }

        match.setScore(homeGoals, awayGoals);
        match.finish();
    }
}