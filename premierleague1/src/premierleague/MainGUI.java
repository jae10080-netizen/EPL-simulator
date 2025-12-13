package premierleague;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.io.PrintStream;

// GameController를 가져옵니다.
import premierleague.controller.GameController; 

public class MainGUI extends JFrame {

    private JTextArea gameLogArea; 
    private JButton mainActionButton; // 다음 라운드 진행 버튼
    private JButton standingsButton;  // 순위표 보기 버튼
    private JButton autoRunButton;    // 자동 진행 버튼
 // [MainGUI.java 클래스 맨 위, 멤버 변수 목록에 추가]
    private JTextField teamInputField; // 팀 번호 입력 필드
    private JButton selectTeamButton; // 팀 선택 완료 버튼
    
    // 컨트롤러 객체
    private GameController controller = new GameController(); 

    public MainGUI() {
        // 1. 윈도우 창 설정
        setTitle("England Premier League Simulator 25-26 (GUI)");
        setSize(1000, 800); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 2. 중계 화면 (텍스트 영역)
        gameLogArea = new JTextArea();
        gameLogArea.setEditable(false); 
        gameLogArea.setFont(new Font("D2Coding", Font.PLAIN, 14)); 
        JScrollPane scrollPane = new JScrollPane(gameLogArea);
        add(scrollPane, BorderLayout.CENTER);

        // 3. 버튼 구성 (순위표, 자동 진행 포함)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); 

        mainActionButton = new JButton("시뮬레이션 시작 (팀 선택)");
        standingsButton = new JButton("현재 순위표 보기");    
        autoRunButton = new JButton("자동 진행 (특정 라운드까지)"); 

        mainActionButton.setFont(new Font("Gothic", Font.BOLD, 16));
        standingsButton.setFont(new Font("Gothic", Font.PLAIN, 14));
        autoRunButton.setFont(new Font("Gothic", Font.PLAIN, 14));

        mainActionButton.setActionCommand("START_SEASON"); 
        standingsButton.setActionCommand("SHOW_STANDINGS");
        autoRunButton.setActionCommand("AUTO_RUN");

        buttonPanel.add(mainActionButton);
        buttonPanel.add(standingsButton); 
        buttonPanel.add(autoRunButton);   
        add(buttonPanel, BorderLayout.SOUTH);

        // 4. [핵심 기술] System.out.println 내용을 이 화면으로 납치
        redirectSystemStreams();

        // 5. 버튼 기능 연결 (이벤트 핸들러)
        
        // --- A. 메인 버튼 (시작 / 다음 라운드) ---
        mainActionButton.addActionListener(e -> {
            // 버튼을 누르는 동안 중복 클릭 방지
            mainActionButton.setEnabled(false); 
            
            new Thread(() -> {
                if (e.getActionCommand().equals("START_SEASON")) {
                    startGameLogic();
                } else if (e.getActionCommand().equals("NEXT_ROUND")) {
                    playRoundLogic();
                }
                // 작업이 끝나면 버튼 다시 활성화 (시즌 종료가 아니면)
                if (!e.getActionCommand().equals("FINISHED")) {
                    SwingUtilities.invokeLater(() -> mainActionButton.setEnabled(true));
                }
            }).start();
        });
        
        // --- B. 순위표 보기 버튼 ---
        standingsButton.addActionListener(e -> {
            if (e.getActionCommand().equals("SHOW_STANDINGS")) {
                new Thread(() -> {
                    // GameController의 순위표 출력 메소드 호출
                    controller.printStandings(); 
                    SwingUtilities.invokeLater(() -> gameLogArea.setCaretPosition(gameLogArea.getDocument().getLength()));
                }).start();
            }
        });

        // --- C. 자동 진행 버튼 ---
        autoRunButton.addActionListener(e -> {
            if (e.getActionCommand().equals("AUTO_RUN")) {
                handleAutoRun(); // 자동 진행 처리 메소드 호출
            }
        });
    }

    /**
     * 시즌 초기화 및 GUI를 통한 팀 선택을 처리합니다.
     */
    private void startGameLogic() {
        
        // 1. GameController를 통해 팀 목록을 가져와서 메시지 생성
        String teamList = controller.getFormattedTeamList(); 
        String message = "플레이할 팀 번호 (1-20)를 입력하세요:\n\n" + teamList;
        
        // 2. 팝업으로 팀 번호 입력 받기
        String teamNumberStr = JOptionPane.showInputDialog(this, 
            message, // 팀 목록이 포함된 메시지 사용
            "팀 선택", 
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (teamNumberStr == null) {
            System.out.println("시즌 시작이 취소되었습니다.");
            return;
        }

        try {
            int teamIndex = Integer.parseInt(teamNumberStr);
            
            if (teamIndex >= 1 && teamIndex <= 20) {
                // Controller 호출 (팀 인덱스 0~19로 변환하여 전달)
                controller.startSeason(teamIndex - 1); 
                
                // 버튼 상태 업데이트: 다음 라운드 진행
                mainActionButton.setActionCommand("NEXT_ROUND");
                updateMainButtonText(); // 텍스트 업데이트 헬퍼 메소드 호출
                
            } else {
                JOptionPane.showMessageDialog(this, "번호는 1부터 20까지만 입력 가능합니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "유효한 숫자를 입력해 주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * 다음 라운드를 한 번 진행하고 시즌 종료 여부를 확인합니다.
     */
    private void playRoundLogic() {
        int finishedRound = controller.playNextRound(); 
        
        // 스크롤을 항상 맨 아래로
        gameLogArea.setCaretPosition(gameLogArea.getDocument().getLength());

        if (finishedRound == -1) {
            // 시즌 종료 처리
            mainActionButton.setText("시즌이 종료되었습니다.");
            mainActionButton.setActionCommand("FINISHED");
            mainActionButton.setEnabled(false);
            standingsButton.setEnabled(false);
            autoRunButton.setEnabled(false);
        } else {
            // 다음 라운드 진행 가능
            updateMainButtonText();
        }
    }
    
    /**
     * 자동 진행 기능을 처리합니다.
     */
    private void handleAutoRun() {
        int currentRound = controller.getCurrentRound();

        if (currentRound > 38) {
            JOptionPane.showMessageDialog(this, "이미 시즌이 종료되었습니다.", "시즌 종료", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        String targetStr = JOptionPane.showInputDialog(this, 
            "몇 라운드까지 진행하시겠습니까? (현재: " + currentRound + " / 최대: 38)", 
            "자동 진행 설정", 
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (targetStr == null) return;

        try {
            int targetRound = Integer.parseInt(targetStr);

            if (targetRound <= currentRound || targetRound > 38) {
                JOptionPane.showMessageDialog(this, "잘못된 범위입니다. 현재 라운드보다 크고 38 이하인 숫자를 입력하세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 자동 진행 중 다른 버튼 비활성화
            mainActionButton.setEnabled(false); 
            standingsButton.setEnabled(false);
            autoRunButton.setEnabled(false);
            
            // 2. 자동 진행 (스레드 처리)
            new Thread(() -> {
                System.out.println("====== " + targetRound + "라운드까지 자동 진행합니다. ======");
                
                // 목표 라운드에 도달하거나 시즌이 끝날 때까지 반복
                while (controller.getCurrentRound() <= targetRound && controller.getCurrentRound() <= 38) {
                    controller.playNextRound();
                    // 시뮬레이션 속도 조절 
                    try { Thread.sleep(30); } catch (InterruptedException ex) {} 
                }
                
                // 진행 완료 후 버튼 상태 업데이트
                SwingUtilities.invokeLater(() -> {
                    updateMainButtonText(); 
                    mainActionButton.setEnabled(true);
                    standingsButton.setEnabled(true);
                    autoRunButton.setEnabled(true);
                    
                    // 마지막 라운드가 끝났다면 최종 결과 출력
                    if(controller.getCurrentRound() > 38) {
                        controller.printFinalResult(); // GameController에 이 메소드가 있어야 합니다.
                        playRoundLogic(); // 최종 종료 상태로 전환
                    }
                });
            }).start();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "유효한 숫자를 입력해 주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * 메인 버튼의 텍스트를 현재 라운드에 맞게 업데이트합니다.
     */
    private void updateMainButtonText() {
        int nextRound = controller.getCurrentRound();
        mainActionButton.setText("다음 라운드 진행 (Round " + nextRound + ")");
    }


    // --- 콘솔 출력을 GUI로 연결하는 설정 (수정 금지) ---
    private void redirectSystemStreams() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) {
                updateTextArea(String.valueOf((char) b));
            }
            @Override
            public void write(byte[] b, int off, int len) {
                updateTextArea(new String(b, off, len));
            }
            @Override
            public void write(byte[] b) {
                write(b, 0, b.length);
            }
        };
        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));
    }

    private void updateTextArea(final String text) {
        SwingUtilities.invokeLater(() -> {
            gameLogArea.append(text);
            // 스크롤을 항상 맨 아래로 유지
            gameLogArea.setCaretPosition(gameLogArea.getDocument().getLength());
        });
    }

    public static void main(String[] args) {
    	//폰트 D2Coding으로 강제변경 코드
    	UIManager.put("Label.font", new Font("D2Coding", Font.PLAIN, 14));
        UIManager.put("TextField.font", new Font("D2Coding", Font.PLAIN, 14));
        UIManager.put("OptionPane.messageFont", new Font("D2Coding", Font.PLAIN, 14));
        UIManager.put("OptionPane.buttonFont", new Font("D2Coding", Font.PLAIN, 14));
        SwingUtilities.invokeLater(() -> {
            new MainGUI().setVisible(true);
        });
    }
}