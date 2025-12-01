package premierleague.league;

import premierleague.match.Match;
import premierleague.match.MatchEngine;
import premierleague.team.Team;
import premierleague.team.Player; // 추가됨

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;   // 추가됨
import java.util.Comparator;    // 추가됨

public class League {

    private List<Team> teams;
    private List<List<Match>> schedule;
    private Team userTeam;
    private Standings standings;

    public League() {
        this.teams = new ArrayList<>();
        this.standings = new Standings();
        loadTeams();
    }

    private void loadTeams() {
        // 팀을 생성함. 여기서 팀 뒤 숫자는 가중치(weight)
        teams.add(new Team("아스널", 88));
        teams.add(new Team("맨체스터 시티", 92));
        teams.add(new Team("리버풀", 89));
        teams.add(new Team("첼시", 80));
        teams.add(new Team("맨체스터 유나이티드", 82));
        teams.add(new Team("토트넘 홋스퍼", 84));
        teams.add(new Team("뉴캐슬 유나이티드", 83));
        teams.add(new Team("브라이튼 앤 호브 알비온", 78));
        teams.add(new Team("애스턴 빌라", 81));
        teams.add(new Team("웨스트햄 유나이티드", 77));
        teams.add(new Team("브렌트포드", 76));
        teams.add(new Team("풀럼", 75));
        teams.add(new Team("울버햄프턴 원더러스", 74));
        teams.add(new Team("크리스탈 팰리스", 73));
        teams.add(new Team("에버턴", 73));
        teams.add(new Team("본머스", 72));
        teams.add(new Team("노팅엄 포레스트", 71));
        teams.add(new Team("선덜랜드", 70));
        teams.add(new Team("리즈 유나이티드", 72));
        teams.add(new Team("번리", 68));
    }
    
    
    //[수정] 번호로 팀선택하기 위해 인덱스 기반 메소드 추가
    public void selectUserTeamByIndex(int index) {
        if (index >= 0 && index < teams.size()) {
        	userTeam = teams.get(index);
        } else {
        	userTeam = null;
        }
    }

    public Team getUserTeam() { return userTeam; }

 // [최종] 왼쪽 열 길이를 계산해서 스페이스바로 채우는 방식 (탭 X)
    public void printTeamList() {
        System.out.println("--------------------------------------------------------------------------");
        
        // 왼쪽 열이 차지할 고정 너비 (35칸)
        // (가장 긴 팀 이름인 '울버햄프턴 원더러스'도 충분히 들어갈 크기입니다)
        int fixedWidth = 35; 

        // 2개씩 건너뛰면서 반복 (i는 0, 2, 4, ...)
        for (int i = 0; i < teams.size(); i += 2) {
            
            // 1. 왼쪽 팀 정보 가져오기
            Team leftTeam = teams.get(i);
            String leftStr = (i + 1) + ". " + leftTeam.getName();
            
            // 2. 왼쪽 팀 출력
            System.out.print(leftStr);
            
            // 3. [핵심] 35칸이 될 때까지 스페이스바 채우기
            // 현재 글자의 '화면상 길이'를 계산합니다.
            int len = getVisualLength(leftStr);
            int padding = fixedWidth - len;
            
            // 길이가 부족한 만큼 공백 반복 출력
            for (int k = 0; k < padding; k++) {
                System.out.print(" ");
            }

            // 4. 오른쪽 팀이 있으면 출력 (i+1 번째 팀)
            if (i + 1 < teams.size()) {
                Team rightTeam = teams.get(i + 1);
                String rightStr = (i + 2) + ". " + rightTeam.getName();
                System.out.println(rightStr);
            } else {
                System.out.println(); // 오른쪽 팀 없으면 줄바꿈만
            }
        }
        
        System.out.println("--------------------------------------------------------------------------");
    }

    // 한글은 2칸, 그 외(영어, 숫자, 공백)는 1칸으로 계산하는 계산기
    private int getVisualLength(String s) {
        int length = 0;
        for (char c : s.toCharArray()) {
            if (c >= '가' && c <= '힣') {
                length += 2; // 한글
            } else {
                length += 1; // 영어, 숫자, 특수문자
            }
        }
        return length;
    }

    public void initializeSeason() {
        ScheduleGenerator generator = new ScheduleGenerator();
        schedule = generator.generate(teams);
        for (Team t : teams) t.resetStats();
    }

    public Match getUserMatch(int round) {
        if (schedule == null || round < 1 || round > schedule.size()) return null;
        for (Match m : schedule.get(round - 1)) {
            if (m.contains(userTeam)) return m;
        }
        return null;
    }

    public void simulateUserMatchWithUI(Match match) {
        MatchEngine engine = new MatchEngine(teams);
        engine.simulateMatchWithUI(match);
    }

    public void simulateOtherMatches(int round) {
        if (schedule == null || round < 1 || round > schedule.size()) return;
        MatchEngine engine = new MatchEngine(teams);
        for (Match m : schedule.get(round - 1)) {
            if (!m.contains(userTeam)) {
                engine.simulateSilently(m);
            }
        }
    }

    public void updateStandings() { standings.update(teams); }
    public void printStandings() { standings.print(); }

    /*
     시즌 종료 후 최종 결과, 우승팀, 득점왕 출력
     */
    public void printFinalResult() {
        System.out.println("\n\n############################################");
        System.out.println("#######  시즌 종료 (25-26)  #######");
        System.out.println("############################################");

        // 1. 최종 순위표 업데이트 및 출력
        standings.update(teams);
        standings.print();

        // 2. 우승팀 찾기 (Standings는 teams를 복사해서 정렬하지만, 여기서는 teams를 직접 정렬해서 찾음)
        // 로직 일관성을 위해 Standings와 동일한 로직으로 정렬
        teams.sort((a, b) -> {
            if (b.getPoints() != a.getPoints()) return b.getPoints() - a.getPoints();
            if (b.getGoalDifference() != a.getGoalDifference()) return b.getGoalDifference() - a.getGoalDifference();
            return b.getGoalsFor() - a.getGoalsFor();
        });
        Team champion = teams.get(0);

        System.out.println("\n🏆 🏆 🏆 2025-26 ENGLAND PREMIER LEAGUE CHAMPIONS 🏆 🏆 🏆");
        System.out.println("        >>>  " + champion.getName().toUpperCase() + "  <<<");
        System.out.println("============================================");

     // 3. 득점왕(Golden Boot) 찾기
        List<Player> allPlayers = new ArrayList<>();
        for (Team t : teams) {
            allPlayers.addAll(t.getPlayers());
        }
        
        // 골 순으로 내림차순 정렬
        allPlayers.sort((p1, p2) -> p2.getGoals() - p1.getGoals());
        
        System.out.println("\n👟 2025-26 GOLDEN BOOT WINNER (득점왕)");

        if (!allPlayers.isEmpty()) {
            int maxGoals = allPlayers.get(0).getGoals(); // 1등의 골 수 저장
            
            // 리스트 전체를 돌면서 1등과 골 수가 같은 사람을 모두 출력
            for (Player p : allPlayers) {
                if (p.getGoals() == maxGoals) {
                    System.out.println("PLAYER: " + p.getName() + " (" + p.getTeam().getName() + ")");
                    System.out.println("GOALS : " + p.getGoals());
                    System.out.println("--------------------------------");
                } else {
                    break; // 골 수가 줄어들면 반복문 종료
                }
            }
        }
        System.out.println("############################################");
        
     // ... (기존 리그 득점왕 출력 코드 아래에 추가) ...

        System.out.println("\n--------------------------------------------");
        System.out.println("      🎯 내 팀 순위 (" + userTeam.getName() + ") 🎯");
        System.out.println("--------------------------------------------");

        // 1. 내 팀 순위 찾기
        // (위에서 이미 teams 리스트가 성적순으로 정렬되어 있으므로, 인덱스만 찾으면 됨)
        int myRank = teams.indexOf(userTeam) + 1; 
        System.out.println("내 팀 순위 : " + myRank + "위");

        // 2. 내 팀 내 최다 득점자 찾기
        List<Player> mySquad = new ArrayList<>(userTeam.getPlayers());
        // 골 많이 넣은 순서로 정렬
        mySquad.sort((p1, p2) -> p2.getGoals() - p1.getGoals());

        if (!mySquad.isEmpty()) {
            Player myAce = mySquad.get(0);
            System.out.println("팀 내 득점왕 : " + myAce.getName() + " (" + myAce.getGoals() + "골)");
            
            // (선택사항) 공동 득점자가 있다면 같이 출력
            for (int i = 1; i < mySquad.size(); i++) {
                if (mySquad.get(i).getGoals() == myAce.getGoals()) {
                    System.out.println("              " + mySquad.get(i).getName() + " (" + mySquad.get(i).getGoals() + "골)");
                } else {
                    break;
                }
            }
        }
        
        // 기존의 마지막 줄
        System.out.println("############################################");
    }
    }
