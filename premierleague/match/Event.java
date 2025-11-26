package premierleague.match;

import premierleague.team.Player;

/**
 * 경기 이벤트 클래스 및 타입 정의
 */
public class Event {
    public enum EventType {
        GOAL, SAVE, FOUL, YELLOW_CARD, RED_CARD, SHOT, NONE
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
        if (type == EventType.GOAL) {
            return minute + "' ⚽ GOAL by " + player.getName();
        } else if (type == EventType.SAVE) {
            return minute + "' 🧤 Great Save by " + player.getName();
        } else if (type == EventType.FOUL) {
            return minute + "' ⚠ Foul by " + player.getName();
        } else if (type == EventType.YELLOW_CARD) {
            return minute + "' 🟨 Yellow Card to " + player.getName();
        } else if (type == EventType.RED_CARD) {
            return minute + "' 🟥 Red Card to " + player.getName();
        } else if (type == EventType.SHOT) {
            return minute + "' 🔥 Shot by " + player.getName();
        }
        return minute + "' (No event)";
    }

    /**
     * 득점 이벤트 생성 헬퍼
     */
    public static Event createGoalEvent(premierleague.team.Team team) {
        premierleague.team.Player scorer = team.getRandomScorer();
        int minute = premierleague.util.RandomEngine.getInt(1, 90);
        return new Event(EventType.GOAL, scorer, minute);
    }

    /**
     * 소규모 이벤트 생성 헬퍼 (선방, 슈팅, 카드 등)
     */
    public static Event createMinorEvent(premierleague.team.Team team) {
        premierleague.team.Player p = team.getRandomPlayer();
        int minute = premierleague.util.RandomEngine.getInt(1, 90);
        int roll = premierleague.util.RandomEngine.getInt(1, 100);

        if (roll <= 10) return new Event(EventType.SAVE, p, minute);
        if (roll <= 30) return new Event(EventType.SHOT, p, minute);
        if (roll <= 45) return new Event(EventType.FOUL, p, minute);
        if (roll <= 55) return new Event(EventType.YELLOW_CARD, p, minute);
        if (roll <= 58) return new Event(EventType.RED_CARD, p, minute);
        return new Event(EventType.NONE, p, minute);
    }
}
