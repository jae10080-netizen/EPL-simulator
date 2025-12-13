package premierleague.util;

import premierleague.team.Team;

public class ProbabilityCalculator {

    // [수정] 상대 수비력을 고려한 현실적인 골 확률 계산기
    public static double goalProbability(Team attacker, Team defender) {

        double attackStat = attacker.getWeight(); // 공격팀 전력 (예: 맨시티 95)
        double defenseStat = defender.getWeight(); // 수비팀 전력 (예: 선덜랜드 70)

        // ★ 밸런스 핵심 공식 ★
        // 내 공격력에서 상대 수비력의 40%만큼을 깎습니다.
        // 예: 맨시티(95) vs 선덜랜드(70)
        // -> 95 - (70 * 0.4) = 67점 (공격 성공률 높음)
        // 예: 선덜랜드(70) vs 맨시티(95)
        // -> 70 - (95 * 0.4) = 32점 (공격 성공률 폭망)
        // 내 가중치에서 상대 기중치의 40%만큼을 깎습니다.
        double effectivePower = attackStat - (defenseStat * 0.4);

        // 기준점 160으로 나누어 확률(0.0 ~ 1.0) 도출
        double probability = effectivePower / 160.0;

        // 최소 확률(5%)과 최대 확률(95%) 보정
        if (probability < 0.05) probability = 0.05;
        if (probability > 0.95) probability = 0.95;

        return probability;
    }
}