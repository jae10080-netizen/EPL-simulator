package premierleague.controller;

import premierleague.league.League;
import premierleague.match.Match;

import java.util.Scanner;

public class GameController {

    private League league;
    private String userTeamName;

    public GameController() {
        this.league = new League();
    }

    public void startSeason() {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== England Premier League Simulator 25-26 ===");
        
        // --- [수정]팀 선택 로직 번호입력 방식 ---
        boolean teamSelected = false;
        while (!teamSelected) {
            System.out.println("\n플레이할 팀의 번호를 선택하세요:");
            league.printTeamList();
            System.out.print("번호입력 >> ");

            try {
                String input = sc.nextLine();
                int teamIndex = Integer.parseInt(input);

                // 유효한 번호인지 확인 (1 ~ 20)
                // 내부적으로는 0-based index 사용하므로 -1 해줌
                league.selectUserTeamByIndex(teamIndex - 1);

                if (league.getUserTeam() != null) {
                    teamSelected = true;
                    System.out.println("✅ " + league.getUserTeam().getName() + " 팀이 선택되었습니다. 시즌을 시작합니다!");
                } else {
                    System.out.println("잘못된 번호입니다. 목록에 있는 번호를 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
        // --- 팀 선택 로직 수정 끝 ---
        
     // ★ [추가] 여기서 바로 시작하지 않고 기다림! ★
        System.out.println("\n준비가 되셨다면 [Enter] 키를 누르세요...");
        sc.nextLine(); // 사용자가 엔터를 칠 때까지 대기

        System.out.println("⚽ 2025-26 England Premier league 시즌을 시작합니다! ⚽");
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // 1초 뜸 들이기


        league.initializeSeason();

        int targetRound = 0;

        // 38라운드 진행
        for (int round = 1; round <= 38; round++) {
            System.out.println("\n\n===== ROUND " + round + " =====");

         
            league.simulateUserMatchWithUI(league.getUserMatch(round));


            league.simulateOtherMatches(round);
            league.updateStandings();

            //마지막 라운드면(38 라운드) 메뉴를 띄우지 않고 루프를 빠져나감 -> 바로 엔딩으로
            if (round == 38) {
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
                continue; 
            }

            // 자동 진행 중이라면 스킵
            if (round < targetRound) {
                System.out.println(">> " + targetRound + " ROUND까지 자동 진행 중... (현재 " + round+" ROUND" + " 완료)");
                try { Thread.sleep(800); } catch (InterruptedException e) {}
                continue;
            }
            
            targetRound = 0; 

            // 메뉴 선택 로직
            boolean nextRound = false;
            while (!nextRound) {
                System.out.println("\n[" + round + "라운드 종료] 무엇을 하시겠습니까?");
                System.out.println("1. 다음 라운드 진행");
                System.out.println("2. 현재 순위표 출력");
                System.out.println("3. 특정 라운드까지 자동 진행");
                System.out.print("선택 >> ");

                try {
                    String input = sc.nextLine();
                    int choice = Integer.parseInt(input);

                    if (choice == 1) {
                        nextRound = true;
                    } else if (choice == 2) {
                        league.printStandings();
                    } else if (choice == 3) {
                        System.out.print("몇 라운드까지 진행하시겠습니까? (현재: " + round + " / 최대: 38) >> ");
                        int target = Integer.parseInt(sc.nextLine());
                        if (target <= round || target > 38) {
                            System.out.println("잘못된 범위입니다.");
                        } else {
                            targetRound = target;
                            nextRound = true;
                        }
                    } else {
                        System.out.println("잘못된 입력입니다.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("숫자를 입력해주세요.");
                }
            }
        }

        // 시즌 종료 후 최종 결과 출력
        league.printFinalResult();
        sc.close();
    }
}