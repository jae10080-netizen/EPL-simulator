package premierleague.match;

import premierleague.team.Player;
import premierleague.team.Team; // Team 클래스 import 필요
import premierleague.util.RandomEngine;

/**
 * 경기 이벤트 클래스 및 타입 정의
 */
public class Event {
    // [수정] TACKLE 추가
    public enum EventType {
        GOAL, SAVE, TACKLE, PASS, FOUL, YELLOW_CARD, SHOT, NONE
    }

    private EventType type;
    private Player player;
    private int minute;

    public Event(EventType type, Player player, int minute) {
        this.type = type;
        this.player = player;
        this.minute = minute;
    }

    public EventType getType() { return type; }
    public Player getPlayer() { return player; }
    public int getMinute() { return minute; }

    public String getDescription() {
        // 팀 이름 가져오기 (Null 방지)
        String teamName = (player != null && player.getTeam() != null) ? player.getTeam().getName() : "Unknown";

        switch (type) {
            case GOAL:
                return minute + "' ⚽ [" + teamName + "] " + player.getName() + "의 환상적인 득점!";

            case SAVE:
                return minute + "' 🧤 [" + teamName + "] " + player.getName() + "의 슈퍼세이브!";

            case TACKLE: // [추가] 수비수 이벤트
                return minute + "' 🛡️ [" + teamName + "] " + player.getName() + "의 깔끔한 태클!";

            case PASS: // [수정] 멘트 수정 (슈팅 -> 패스)
                return minute + "' 👟 [" + teamName + "] " + player.getName() + "의 날카로운 킬패스!";

            case FOUL:
                return minute + "' ⚠ [" + teamName + "] " + player.getName() + "의 파울!";

            case YELLOW_CARD:
                return minute + "' 🟨 [" + teamName + "] " + player.getName() + "에게 옐로 카드!";

            case SHOT:
                return minute + "' 🔥 [" + teamName + "] " + player.getName() + "의 강력한 슈팅! 아쉽게 빗나갑니다.";
            
            default:
                return minute + "' (경기 진행 중...)";
        }
    }

    /**
     * 득점 이벤트 생성 헬퍼
     */
    public static Event createGoalEvent(Team team) {
        // 득점자는 공격수 위주로 뽑기
        Player scorer = team.getRandomScorer();
        int minute = RandomEngine.getInt(1, 90);
        return new Event(EventType.GOAL, scorer, minute);
    }

    /**
     * [핵심 수정] 이벤트 종류에 따라 '맞는 포지션'의 선수를 데려옵니다.
     */
    public static Event createMinorEvent(Team team) {
        int minute = RandomEngine.getInt(1, 90);
        int roll = RandomEngine.getInt(1, 100);

        // 확률 분포 (합이 100이 안 되면 나머지는 NONE)
        // 1. 선방 (15%) -> 무조건 골키퍼
        if (roll <= 15) {
            return new Event(EventType.SAVE, team.getGoalkeeper(), minute);
        }
        // 2. 태클 (15%) -> 수비수 위주
        else if (roll <= 30) {
            return new Event(EventType.TACKLE, team.getRandomDefender(), minute);
        }
        // 3. 패스 (20%) -> 미드필더 위주
        else if (roll <= 50) {
            return new Event(EventType.PASS, team.getRandomMidfielder(), minute);
        }
        // 4. 슈팅 (15%) -> 공격수 위주
        else if (roll <= 65) {
            return new Event(EventType.SHOT, team.getRandomShooter(), minute);
        }
        // 5. 파울 (10%) -> 아무나
        else if (roll <= 75) {
            return new Event(EventType.FOUL, team.getRandomPlayer(), minute);
        }
        // 6. 경고 (5%) -> 아무나
        else if (roll <= 80) {
            return new Event(EventType.YELLOW_CARD, team.getRandomPlayer(), minute);
        }

        // 나머지는 이벤트 없음
        return new Event(EventType.NONE, team.getRandomPlayer(), minute);
    }
}
