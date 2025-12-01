package premierleague.match;

import premierleague.team.Player;
import premierleague.team.Team;
import premierleague.util.RandomEngine;

public class Event {
    // EventType에 NONE은 이제 안 씁니다.
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

    // 시간 설정 메소드 (MatchEngine에서 사용)
    public void setMinute(int minute) {
        this.minute = minute;
    }

    public EventType getType() { return type; }
    public Player getPlayer() { return player; }
    public int getMinute() { return minute; }

    public String getDescription() {
        String teamName = (player != null && player.getTeam() != null) ? player.getTeam().getName() : "Unknown";

        switch (type) {
            case GOAL:
                return minute + "' ⚽ [" + teamName + "] " + player.getName() + "의 환상적인 득점!";
            case SAVE:
                return minute + "' 🧤 [" + teamName + "] " + player.getName() + "의 슈퍼세이브!";
            case TACKLE:
                return minute + "' 🛡️ [" + teamName + "] " + player.getName() + "의 결정적인 태클 성공!";
            case PASS:
                return minute + "' 👟 [" + teamName + "] " + player.getName() + "의 날카로운 킬패스!";
            case FOUL:
                return minute + "' ⚠ [" + teamName + "] " + player.getName() + "의 파울!";
            case YELLOW_CARD:
                return minute + "' 🟨 [" + teamName + "] " + player.getName() + "에게 옐로 카드!";
            case SHOT:
                return minute + "' 🔥 [" + teamName + "] " + player.getName() + "의 강력한 슈팅! 아쉽게 빗나갑니다.";
            default:
                return ""; // 빈 문자열 반환 (혹시라도 NONE이면 출력 안 함)
        }
    }

    public static Event createGoalEvent(Team team) {
        Player scorer = team.getRandomScorer();
        int minute = RandomEngine.getInt(1, 90);
        return new Event(EventType.GOAL, scorer, minute);
    }

    // [수정] 확률을 100%로 꽉 채워서 '경기 진행 중'이 안 나오게 함
    public static Event createMinorEvent(Team team) {
        int minute = RandomEngine.getInt(1, 90);
        int roll = RandomEngine.getInt(1, 100);

        // 1. 선방 (15%)
        if (roll <= 15) {
            return new Event(EventType.SAVE, team.getGoalkeeper(), minute);
        }
        // 2. 태클 (20%) -> 수비수 활약 증가
        else if (roll <= 35) {
            return new Event(EventType.TACKLE, team.getRandomDefender(), minute);
        }
        // 3. 패스 (25%) -> 미드필더 활약 증가
        else if (roll <= 60) {
            return new Event(EventType.PASS, team.getRandomMidfielder(), minute);
        }
        // 4. 슈팅 (25%) -> 공격수 슈팅 빈도 증가
        else if (roll <= 85) {
            return new Event(EventType.SHOT, team.getRandomShooter(), minute); // Shooter(FW+MF) 사용
        }
        // 5. 파울 (10%)
        else if (roll <= 95) {
            return new Event(EventType.FOUL, team.getRandomPlayer(), minute);
        }
        // 6. 경고 (5%) -> 나머지 전부 옐로카드 (빈틈 없음)
        else {
            return new Event(EventType.YELLOW_CARD, team.getRandomPlayer(), minute);
        }
    }
}
    
 
