package premierleague.util;

import premierleague.team.Team;

/**
 * 득점 확률 계산기
 * - team: 공격팀의 전력(0~100)
 * - opponent: 수비팀의 전력(0~100)
 *
 * 반환값: 0.0 ~ 1.0 (골이 발생할 확률)
 *
 * 기본 아이디어:
 * adjustedAttack = team.weight * randomFactor(0.7~1.3)
 * adjustedDefense = opponent.weight * randomFactor(0.85~1.15)
 * probability = adjustedAttack / (adjustedAttack + adjustedDefense)
 *
 * 이렇게 하면 팀 전력 차이가 확률로 자연스럽게 반영됨.
 */
public class ProbabilityCalculator {

    public static double goalProbability(Team team, Team opponent) {

        double attack = team.getWeight();
        double defense = opponent.getWeight();

        // 랜덤 보정 (0.7 ~ 1.3)
        double attackFactor = 0.7 + RandomEngine.getDouble() * 0.6;
        double defenseFactor = 0.85 + RandomEngine.getDouble() * 0.3;

        double adjustedAttack = attack * attackFactor;
        double adjustedDefense = defense * defenseFactor;

        // 0으로 나누는 것을 방지
        double denom = adjustedAttack + adjustedDefense;
        if (denom <= 0.0001) return 0.5;

        double prob = adjustedAttack / denom;

        // 안정화: 확률이 극단적으로 치우치지 않도록 clamp
        if (prob < 0.05) prob = 0.05;
        if (prob > 0.95) prob = 0.95;

        return prob;
    }
}
