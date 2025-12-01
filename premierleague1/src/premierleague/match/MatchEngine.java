package premierleague.match;

import premierleague.team.Team;
import premierleague.util.ProbabilityCalculator;
import premierleague.util.RandomEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MatchEngine {

    private List<Team> teams;

    public MatchEngine(List<Team> teams) {
        this.teams = teams;
    }

    public void simulateMatchWithUI(Match match) {
        Team home = match.getHomeTeam();
        Team away = match.getAwayTeam();

        // ★ [핵심 수정] 공격 기회 횟수를 다시 줄였습니다! (현실성 복구)
        // 기존(과다 득점): (weight / 15) + 3  --> 약 9~10회 (너무 많음)
        // 변경(현실적): (weight / 18)        --> 약 4~5회 (적절함)
        
        int attemptsHome = (int)(home.getWeight() / 18) + RandomEngine.getInt(0, 2);
        int attemptsAway = (int)(away.getWeight() / 18) + RandomEngine.getInt(0, 2);
        
        // 최소 2번은 공격하도록 보정 (너무 심심하지 않게)
        if (attemptsHome < 2) attemptsHome = 2;
        if (attemptsAway < 2) attemptsAway = 2;

        System.out.println("================================");
        System.out.println("★  " + home.getName() + " (Home) vs (Away) " + away.getName() + "  ★");
        System.out.println("================================");
        sleep(1500);

        // --- 전반전 ---
        System.out.println("\n📣 삐익~! 전반전 시작합니다! (1st Half Kick-off)");
        sleep(1000);

        int homeFirstHalf = (attemptsHome + 1) / 2;
        int awayFirstHalf = (attemptsAway + 1) / 2;

        simulateHalf(match, home, away, homeFirstHalf, awayFirstHalf, 1, 45, true);

        // --- 하프타임 ---
        System.out.println("\n⏰ 전반 종료. 하프타임 (Half Time)");
        System.out.println("현재 스코어 >> " + home.getName() + " " + match.getHomeScore() + " : " + match.getAwayScore() + " " + away.getName());
        System.out.println("--------------------------------");
        sleep(2000);

        // --- 후반전 ---
        System.out.println("\n📣 삐익~! 후반전 시작합니다! (2nd Half Kick-off)");
        sleep(1000);

        int homeSecondHalf = attemptsHome - homeFirstHalf;
        int awaySecondHalf = attemptsAway - awayFirstHalf;

        simulateHalf(match, home, away, homeSecondHalf, awaySecondHalf, 46, 90, true);

        // --- 추가시간 ---
        int addedTime = RandomEngine.getInt(2, 6);
        System.out.println("\n⏱️ 추가시간 " + addedTime + "분이 주어집니다...");
        sleep(1500);

        simulateDramaTime(match, home, away, addedTime, true);

        // --- 경기 종료 ---
        System.out.println("\n================================");
        System.out.println("📣 삐익~ 삑! 경기 종료 (Full Time)");
        System.out.println("최종 스코어: " + home.getName() + " " + match.getHomeScore() +
                " : " + match.getAwayScore() + " " + away.getName());
        System.out.println("================================\n");

        match.finish();
        sleep(2000);
    }

    private void simulateHalf(Match match, Team home, Team away, int hChance, int aChance, int startMin, int endMin, boolean isUI) {
        List<Event> halfEvents = new ArrayList<>();

        for (int i = 0; i < hChance; i++) {
            int time = RandomEngine.getInt(startMin, endMin);
            Event event = createAttackEvent(match, home, away, time);
            if (event != null) halfEvents.add(event);
        }

        for (int i = 0; i < aChance; i++) {
            int time = RandomEngine.getInt(startMin, endMin);
            Event event = createAttackEvent(match, away, home, time);
            if (event != null) halfEvents.add(event);
        }

        Collections.sort(halfEvents, Comparator.comparingInt(Event::getMinute));

        if (isUI) {
            for (Event e : halfEvents) {
                System.out.println(e.getDescription());
                match.addEvent(e); 
                sleep(1500); 
            }
        } else {
            for (Event e : halfEvents) match.addEvent(e);
        }
    }

    private Event createAttackEvent(Match match, Team attacker, Team defender, int time) {
        double prob = ProbabilityCalculator.goalProbability(attacker, defender);
        
        if (RandomEngine.getDouble() < prob) {
            return new Event(Event.EventType.GOAL, attacker.getRandomScorer(), time);
        } 
        
        if (RandomEngine.chance(90)) {
            Event e = Event.createMinorEvent(defender); 
            e.setMinute(time); // 시간 동기화
            return e;
        }
        return null; 
    }

    private void simulateDramaTime(Match match, Team home, Team away, int addedTime, boolean isUI) {
        List<Event> dramaEvents = new ArrayList<>();

        if (RandomEngine.getDouble() < ProbabilityCalculator.goalProbability(home, away) * 0.4) {
            int time = 90 + RandomEngine.getInt(1, addedTime);
            dramaEvents.add(new Event(Event.EventType.GOAL, home.getRandomScorer(), time));
        }

        if (RandomEngine.getDouble() < ProbabilityCalculator.goalProbability(away, home) * 0.4) {
            int time = 90 + RandomEngine.getInt(1, addedTime);
            dramaEvents.add(new Event(Event.EventType.GOAL, away.getRandomScorer(), time));
        }
        
        Collections.sort(dramaEvents, Comparator.comparingInt(Event::getMinute));
        
        for (Event e : dramaEvents) {
            match.addEvent(e);
            if (isUI) System.out.println("🔥 극장골!!! " + e.getDescription());
            sleep(1000);
        }
    }

    // UI 없는 빠른 진행 (여기도 횟수를 줄여야 순위표가 정상으로 나옴!)
    public void simulateSilently(Match match) {
        Team home = match.getHomeTeam();
        Team away = match.getAwayTeam();

        // [수정] 여기도 똑같이 공격 횟수 대폭 감소
        int attemptsHome = (int)(home.getWeight() / 18) + RandomEngine.getInt(0, 2);
        int attemptsAway = (int)(away.getWeight() / 18) + RandomEngine.getInt(0, 2);
        
        if (attemptsHome < 2) attemptsHome = 2;
        if (attemptsAway < 2) attemptsAway = 2;

        simulateHalf(match, home, away, attemptsHome, attemptsAway, 1, 90, false);
        match.finish();
    }

    private void sleep(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) {} // ms / 10 or 100으로 속도 조정해서 시뮬레이션 하는 코드 
    }
}