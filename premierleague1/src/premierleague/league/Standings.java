package premierleague.league;

import premierleague.team.Team;
import java.util.*;

public class Standings {

    private List<Team> table = new ArrayList<>();

    public void update(List<Team> teams) {
        table = new ArrayList<>(teams);

        // 순위 정렬 로직 (승점 -> 득실차 -> 다득점 -> 이름)
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

    // [수정] 순위표 정렬 깨짐 해결 버전
    public void print() {
        System.out.println("\n=== 25-26 프리미어리그 순위표 ===");
        
        // 헤더 출력 (간격을 대략적으로 맞춤)
        System.out.println("순위 | 팀                       | 승점 |  승 |  무 |  패 | 득점 | 실점 | 득실차");
        System.out.println("---------------------------------------------------------------------------------");
        
        int pos = 1;
        // 팀 이름이 차지할 고정 너비 (26칸)
        int nameColumnWidth = 26; 

        for (Team t : table) {
            // 1. 순위 출력
            System.out.printf("%4d | ", pos++);

            // 2. 팀 이름 출력 + 정렬 맞추기 (핵심!)
            String name = t.getName();
            System.out.print(name);
            
            int len = getVisualLength(name);
            int padding = nameColumnWidth - len;
            
            // 부족한 만큼 공백 채우기
            for (int k = 0; k < padding; k++) {
                System.out.print(" ");
            }

            // 3. 나머지 스탯 출력
            System.out.printf("| %4d | %3d | %3d | %3d | %4d | %4d | %+6d\n",
                    t.getPoints(), 
                    t.getWins(),   
                    t.getDraws(),  
                    t.getLosses(), 
                    t.getGoalsFor(), 
                    t.getGoalsAgainst(),
                    t.getGoalDifference());
        }
        System.out.println("---------------------------------------------------------------------------------");
    }

    // [추가] 한글 길이 계산기 (한글=2칸, 그외=1칸)
    private int getVisualLength(String s) {
        int length = 0;
        for (char c : s.toCharArray()) {
            if (c >= '가' && c <= '힣') {
                length += 2; // 한글
            } else {
                length += 1; // 영어, 숫자, 공백
            }
        }
        return length;
    }
}

