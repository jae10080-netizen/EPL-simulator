package premierleague.team;

import premierleague.util.RandomEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * 팀 클래스
 * - name: 팀 이름
 * - weight: 0~100 (팀 전력; 이 값이 높을수록 득점 확률 우위)
 * - players: 선수 리스트 (기본 자동 생성 포함)
 * - points, goalsFor, goalsAgainst: 리그 통계
 */
public class Team {

    private String name;
    private int weight;
    private List<Player> players = new ArrayList<>();

    private int points = 0;
    private int goalsFor = 0;
    private int goalsAgainst = 0;

    public Team(String name, int weight) {
        this.name = name;
        this.weight = weight;
        generateDefaultPlayers();
    }

    /*
    // 로직을 보기 위해서 일단 기본 선수 생성해봄. (FW3, MF4, DF3, GK1 총 11명)
    private void generateDefaultPlayers() {
       
        for (int i = 1; i <= 3; i++) {
            players.add(new Player(name + "_FW" + i, Position.FW, 70 + RandomEngine.getInt(-5, 10), this));
        }
        for (int i = 1; i <= 4; i++) {
            players.add(new Player(name + "_MF" + i, Position.MF, 68 + RandomEngine.getInt(-5, 8), this));
        }
        for (int i = 1; i <= 3; i++) {
            players.add(new Player(name + "_DF" + i, Position.DF, 66 + RandomEngine.getInt(-5, 6), this));
        }
        players.add(new Player(name + "_GK1", Position.GK, 72 + RandomEngine.getInt(-5, 6), this));
    }
*/ //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    private void generateDefaultPlayers() {

        // ============================
        // 1) Arsenal (아스날)
        // ============================
        if (name.equals("Arsenal")) {
            players.add(new Player("가브리엘 제주스", Position.FW, 84, this));
            players.add(new Player("부카요 사카", Position.FW, 88, this));
            players.add(new Player("가브리엘 마르티넬리", Position.FW, 86, this));

            players.add(new Player("마르틴 외데고르", Position.MF, 89, this));
            players.add(new Player("데클란 라이스", Position.MF, 88, this));
            players.add(new Player("토마스 파티", Position.MF, 84, this));
            players.add(new Player("카이 하베르츠", Position.MF, 85, this));

            players.add(new Player("윌리엄 살리바", Position.DF, 87, this));
            players.add(new Player("벤 화이트", Position.DF, 85, this));
            players.add(new Player("가브리엘 마갈량이스", Position.DF, 84, this));

            players.add(new Player("다비드 라야", Position.GK, 86, this));
            return;
        }

        // ============================
        // 2) Man City (맨체스터 시티)
        // ============================
        if (name.equals("Man City")) {
            players.add(new Player("엘링 홀란드", Position.FW, 92, this));
            players.add(new Player("훌리안 알바레스", Position.FW, 86, this));
            players.add(new Player("필 포든", Position.FW, 89, this));

            players.add(new Player("케빈 더 브라위너", Position.MF, 91, this));
            players.add(new Player("로드리", Position.MF, 90, this));
            players.add(new Player("베르나르두 실바", Position.MF, 88, this));
            players.add(new Player("마테우스 누네스", Position.MF, 82, this));

            players.add(new Player("후벵 디아스", Position.DF, 88, this));
            players.add(new Player("카일 워커", Position.DF, 85, this));
            players.add(new Player("나단 아케", Position.DF, 83, this));

            players.add(new Player("에데르송", Position.GK, 88, this));
            return;
        }

        // ============================
        // 3) Liverpool (리버풀)
        // ============================
        if (name.equals("Liverpool")) {
            players.add(new Player("모하메드 살라", Position.FW, 89, this));
            players.add(new Player("다르윈 누녜스", Position.FW, 84, this));
            players.add(new Player("루이스 디아스", Position.FW, 86, this));

            players.add(new Player("알렉시스 맥 알리스터", Position.MF, 85, this));
            players.add(new Player("도미닉 소보슬라이", Position.MF, 84, this));
            players.add(new Player("커티스 존스", Position.MF, 81, this));
            players.add(new Player("하비 엘리엇", Position.MF, 80, this));

            players.add(new Player("버질 반 다이크", Position.DF, 88, this));
            players.add(new Player("이브라히마 코나테", Position.DF, 84, this));
            players.add(new Player("앤디 로버트슨", Position.DF, 84, this));

            players.add(new Player("알리송 베케르", Position.GK, 89, this));
            return;
        }

        // ============================
        // 4) Chelsea (첼시)
        // ============================
        if (name.equals("Chelsea")) {
            players.add(new Player("니콜라 잭슨", Position.FW, 82, this));
            players.add(new Player("라힘 스털링", Position.FW, 84, this));
            players.add(new Player("크리스토퍼 은쿤쿠", Position.FW, 85, this));

            players.add(new Player("엔조 페르난데스", Position.MF, 83, this));
            players.add(new Player("모이세스 카이세도", Position.MF, 83, this));
            players.add(new Player("코너 갤러거", Position.MF, 82, this));
            players.add(new Player("콜 팔머", Position.MF, 85, this));

            players.add(new Player("티아고 실바", Position.DF, 84, this));
            players.add(new Player("리스 제임스", Position.DF, 85, this));
            players.add(new Player("벤 칠웰", Position.DF, 82, this));

            players.add(new Player("로베르트 산체스", Position.GK, 81, this));
            return;
        }

        // ============================
        // 5) Man United (맨체스터 유나이티드)
        // ============================
        if (name.equals("Man United")) {
            players.add(new Player("라스무스 호일룬", Position.FW, 82, this));
            players.add(new Player("마커스 래시포드", Position.FW, 86, this));
            players.add(new Player("안토니", Position.FW, 81, this));

            players.add(new Player("브루노 페르난데스", Position.MF, 88, this));
            players.add(new Player("카세미루", Position.MF, 86, this));
            players.add(new Player("메이슨 마운트", Position.MF, 83, this));
            players.add(new Player("코비 마이누", Position.MF, 79, this));

            players.add(new Player("리산드로 마르티네스", Position.DF, 84, this));
            players.add(new Player("라파엘 바란", Position.DF, 85, this));
            players.add(new Player("루크 쇼", Position.DF, 83, this));

            players.add(new Player("앙드레 오나나", Position.GK, 84, this));
            return;
        }

        // ============================
        // 6) Tottenham (토트넘 홋스퍼)
        // ============================
        if (name.equals("Tottenham")) {
            players.add(new Player("손흥민", Position.FW, 90, this));
            players.add(new Player("히샬리송", Position.FW, 82, this));
            players.add(new Player("데얀 쿨루셉스키", Position.FW, 83, this));

            players.add(new Player("제임스 매디슨", Position.MF, 86, this));
            players.add(new Player("로드리고 벤탄쿠르", Position.MF, 84, this));
            players.add(new Player("파페 마타르 사르", Position.MF, 80, this));
            players.add(new Player("이브 비수마", Position.MF, 82, this));

            players.add(new Player("크리스티안 로메로", Position.DF, 85, this));
            players.add(new Player("미키 판 더 펜", Position.DF, 84, this));
            players.add(new Player("데스티니 우도지", Position.DF, 82, this));

            players.add(new Player("굴리엘모 비카리오", Position.GK, 84, this));
            return;
        }

        // ============================
        // 7) Newcastle (뉴캐슬 유나이티드)
        // ============================
        if (name.equals("Newcastle")) {
            players.add(new Player("알렉산더 이삭", Position.FW, 84, this));
            players.add(new Player("앤서니 고든", Position.FW, 83, this));
            players.add(new Player("미겔 알미론", Position.FW, 82, this));

            players.add(new Player("브루노 기마랑이스", Position.MF, 85, this));
            players.add(new Player("산드로 토날리", Position.MF, 84, this));
            players.add(new Player("조엘링톤", Position.MF, 84, this));
            players.add(new Player("션 롱스태프", Position.MF, 81, this));

            players.add(new Player("스벤 보트만", Position.DF, 83, this));
            players.add(new Player("키어런 트리피어", Position.DF, 85, this));
            players.add(new Player("댄 번", Position.DF, 81, this));

            players.add(new Player("닉 포프", Position.GK, 84, this));
            return;
        }

        // ============================
        // 8) Brighton (브라이튼 & 호브 알비온)
        // ============================
        if (name.equals("Brighton")) {
            players.add(new Player("에반 퍼거슨", Position.FW, 80, this));
            players.add(new Player("미토마 카오루", Position.FW, 84, this));
            players.add(new Player("안수 파티", Position.FW, 82, this));

            players.add(new Player("파스칼 그로스", Position.MF, 83, this));
            players.add(new Player("빌리 길모어", Position.MF, 77, this));
            players.add(new Player("솔리 마치", Position.MF, 81, this));
            players.add(new Player("훌리오 엔시소", Position.MF, 78, this));

            players.add(new Player("루이스 덩크", Position.DF, 83, this));
            players.add(new Player("페르비스 에스투피냔", Position.DF, 82, this));
            players.add(new Player("조엘 벨트만", Position.DF, 79, this));

            players.add(new Player("바르트 베르브뤼헌", Position.GK, 79, this));
            return;
        }

        // ============================
        // 9) Aston Villa (아스톤 빌라)
        // ============================
        if (name.equals("Aston Villa")) {
            players.add(new Player("올리 왓킨스", Position.FW, 85, this));
            players.add(new Player("레온 베일리", Position.FW, 82, this));
            players.add(new Player("무사 디아비", Position.FW, 84, this));

            players.add(new Player("더글라스 루이스", Position.MF, 84, this));
            players.add(new Player("존 맥긴", Position.MF, 83, this));
            players.add(new Player("부바카르 카마라", Position.MF, 82, this));
            players.add(new Player("제이콥 램지", Position.MF, 80, this));

            players.add(new Player("파우 토레스", Position.DF, 84, this));
            players.add(new Player("에즈리 콘사", Position.DF, 81, this));
            players.add(new Player("뤼카 디뉴", Position.DF, 80, this));

            players.add(new Player("에밀리아노 마르티네스", Position.GK, 85, this));
            return;
        }

        // ============================
        // 10) West Ham (웨스트 햄 유나이티드)
        // ============================
        if (name.equals("West Ham")) {
            players.add(new Player("재러드 보웬", Position.FW, 85, this));
            players.add(new Player("미카일 안토니오", Position.FW, 80, this));
            players.add(new Player("대니 잉스", Position.FW, 79, this));

            players.add(new Player("루카스 파케타", Position.MF, 84, this));
            players.add(new Player("에드손 알바레스", Position.MF, 82, this));
            players.add(new Player("토마시 수첵", Position.MF, 81, this));
            players.add(new Player("제임스 워드-프라우스", Position.MF, 83, this));

            players.add(new Player("쿠르트 주마", Position.DF, 81, this));
            players.add(new Player("나예프 아구에르드", Position.DF, 80, this));
            players.add(new Player("블라디미르 쿠팔", Position.DF, 79, this));

            players.add(new Player("알퐁스 아레올라", Position.GK, 82, this));
            return;
        }

        // ============================
        // 11) Brentford (브렌트퍼드)
        // ============================
        if (name.equals("Brentford")) {
            players.add(new Player("아이반 토니", Position.FW, 84, this));
            players.add(new Player("브라이언 음베우모", Position.FW, 81, this));
            players.add(new Player("요안 위사", Position.FW, 78, this));

            players.add(new Player("크리스티안 뇌르고르", Position.MF, 80, this));
            players.add(new Player("비탈리 야넬트", Position.MF, 77, this));
            players.add(new Player("프랭크 오녜카", Position.MF, 76, this));
            players.add(new Player("마티아스 옌센", Position.MF, 78, this));

            players.add(new Player("이선 피녹", Position.DF, 80, this));
            players.add(new Player("벤 미", Position.DF, 79, this));
            players.add(new Player("리코 헨리", Position.DF, 78, this));

            players.add(new Player("마르크 플레컨", Position.GK, 81, this));
            return;
        }

        // ============================
        // 12) Fulham (풀럼)
        // ============================
        if (name.equals("Fulham")) {
            players.add(new Player("라울 히메네스", Position.FW, 78, this));
            players.add(new Player("윌리안", Position.FW, 79, this));
            players.add(new Player("바비 데 코르도바-리드", Position.FW, 77, this));

            players.add(new Player("주앙 팔리냐", Position.MF, 84, this));
            players.add(new Player("안드레아스 페레이라", Position.MF, 79, this));
            players.add(new Player("톰 케어니", Position.MF, 77, this));
            players.add(new Player("해리슨 리드", Position.MF, 77, this));

            players.add(new Player("토신 아다라비오요", Position.DF, 78, this));
            players.add(new Player("팀 림", Position.DF, 76, this));
            players.add(new Player("안토니 로빈슨", Position.DF, 77, this));

            players.add(new Player("베른트 레노", Position.GK, 82, this));
            return;
        }

        // ============================
        // 13) Wolves (울버햄튼 원더러스)
        // ============================
        if (name.equals("Wolves")) {
            players.add(new Player("마테우스 쿠냐", Position.FW, 81, this));
            players.add(new Player("황희찬", Position.FW, 81, this));
            players.add(new Player("페드로 네투", Position.FW, 82, this));

            players.add(new Player("마리오 레미나", Position.MF, 80, this));
            players.add(new Player("주앙 고메스", Position.MF, 79, this));
            players.add(new Player("파블로 사라비아", Position.MF, 79, this));
            players.add(new Player("토미 도일", Position.MF, 76, this));

            players.add(new Player("막스 킬먼", Position.DF, 81, this));
            players.add(new Player("크레이그 도슨", Position.DF, 78, this));
            players.add(new Player("라얀 아이트-누리", Position.DF, 77, this));

            players.add(new Player("조제 사", Position.GK, 83, this));
            return;
        }

        // ============================
        // 14) Crystal Palace (크리스탈 팰리스)
        // ============================
        if (name.equals("Crystal Palace")) {
            players.add(new Player("오드손 에두아르", Position.FW, 78, this));
            players.add(new Player("에베레치 에제", Position.FW, 83, this));
            players.add(new Player("마이클 올리스", Position.FW, 81, this));

            players.add(new Player("윌 휴즈", Position.MF, 76, this));
            players.add(new Player("제퍼슨 레르마", Position.MF, 80, this));
            players.add(new Player("셰이크 두쿠레", Position.MF, 79, this));
            players.add(new Player("제프리 슐럽", Position.MF, 77, this));

            players.add(new Player("마크 게히", Position.DF, 82, this));
            players.add(new Player("요아킴 안데르센", Position.DF, 80, this));
            players.add(new Player("타이릭 미첼", Position.DF, 77, this));

            players.add(new Player("샘 존스톤", Position.GK, 79, this));
            return;
        }

        // ============================
        // 15) Everton (에버턴)
        // ============================
        if (name.equals("Everton")) {
            players.add(new Player("도미닉 칼버트-르윈", Position.FW, 80, this));
            players.add(new Player("드와이트 맥닐", Position.FW, 78, this));
            players.add(new Player("잭 해리슨", Position.FW, 79, this));

            players.add(new Player("압둘라예 두쿠레", Position.MF, 79, this));
            players.add(new Player("아마두 오나나", Position.MF, 81, this));
            players.add(new Player("이드리사 게예", Position.MF, 78, this));
            players.add(new Player("제임스 가너", Position.MF, 77, this));

            players.add(new Player("제임스 타르코프스키", Position.DF, 80, this));
            players.add(new Player("재러드 브랜스웨이트", Position.DF, 77, this));
            players.add(new Player("비탈리 미콜렌코", Position.DF, 77, this));

            players.add(new Player("조던 픽포드", Position.GK, 84, this));
            return;
        }

        // ============================
        // 16) Bournemouth (AFC 본머스)
        // ============================
        if (name.equals("Bournemouth")) {
            players.add(new Player("도미닉 솔랑케", Position.FW, 81, this));
            players.add(new Player("마커스 태버니어", Position.FW, 77, this));
            players.add(new Player("저스틴 클라위버르트", Position.FW, 76, this));

            players.add(new Player("필립 빌링", Position.MF, 78, this));
            players.add(new Player("루이스 쿡", Position.MF, 77, this));
            players.add(new Player("라이언 크리스티", Position.MF, 76, this));
            players.add(new Player("알렉스 스콧", Position.MF, 74, this));

            players.add(new Player("로이드 켈리", Position.DF, 78, this));
            players.add(new Player("마르코스 세네시", Position.DF, 77, this));
            players.add(new Player("밀로스 케르케즈", Position.DF, 75, this));

            players.add(new Player("네투", Position.GK, 80, this));
            return;
        }

        // ============================
        // 17) Nottingham Forest (노팅엄 포레스트)
        // ============================
        if (name.equals("Nottingham Forest")) {
            players.add(new Player("타이워 아워니이", Position.FW, 80, this));
            players.add(new Player("칼럼 허드슨-오도이", Position.FW, 78, this));
            players.add(new Player("앤서니 엘랑가", Position.FW, 76, this));

            players.add(new Player("모건 깁스-화이트", Position.MF, 81, this));
            players.add(new Player("이브라힘 상가레", Position.MF, 83, this));
            players.add(new Player("다닐루", Position.MF, 78, this));
            players.add(new Player("라이언 예이츠", Position.MF, 76, this));

            players.add(new Player("무사 니아카테", Position.DF, 79, this));
            players.add(new Player("윌리 볼리", Position.DF, 78, this));
            players.add(new Player("누노 타바레스", Position.DF, 76, this));

            players.add(new Player("맷 터너", Position.GK, 77, this));
            return;
        }

        // ============================
        // 18) Southampton (사우샘프턴) - (승격팀)
        // ============================
        if (name.equals("Southampton")) {
            players.add(new Player("아담 암스트롱", Position.FW, 78, this));
            players.add(new Player("셰이 아담스", Position.FW, 77, this));
            players.add(new Player("라이언 프레이저", Position.FW, 75, this));

            players.add(new Player("윌 해리스", Position.MF, 74, this));
            players.add(new Player("조 아리보", Position.MF, 75, this));
            players.add(new Player("스튜어트 암스트롱", Position.MF, 76, this));
            players.add(new Player("플린 다운스", Position.MF, 72, this));

            players.add(new Player("베드나렉", Position.DF, 78, this));
            players.add(new Player("잭 스티븐스", Position.DF, 75, this));
            players.add(new Player("카일 워커-피터스", Position.DF, 78, this));

            players.add(new Player("알렉스 매카시", Position.GK, 76, this));
            return;
        }

        // ============================
        // 19) Leicester City (레스터 시티) - (승격팀)
        // ============================
        if (name.equals("Leicester City")) {
            players.add(new Player("제이미 바디", Position.FW, 79, this));
            players.add(new Player("패트슨 다카", Position.FW, 77, this));
            players.add(new Player("압둘 파타우", Position.FW, 75, this));

            players.add(new Player("키어넌 듀스버리-홀", Position.MF, 80, this));
            players.add(new Player("윌프레드 은디디", Position.MF, 78, this));
            players.add(new Player("해리 윙크스", Position.MF, 77, this));
            players.add(new Player("스테피 마비디디", Position.MF, 76, this));

            players.add(new Player("워트 파스", Position.DF, 79, this));
            players.add(new Player("제임스 저스틴", Position.DF, 78, this));
            players.add(new Player("히카르두 페레이라", Position.DF, 78, this));

            players.add(new Player("마스 알브레흐트센", Position.GK, 77, this));
            return;
        }

        // ============================
        // 20) Ipswich Town (입스위치 타운) - (승격팀)
        // ============================
        if (name.equals("Ipswich Town")) {
            players.add(new Player("코너 채플린", Position.FW, 76, this));
            players.add(new Player("네이선 브로드헤드", Position.FW, 74, this));
            players.add(new Player("조지 허스트", Position.FW, 73, this));

            players.add(new Player("샘 모시", Position.MF, 75, this));
            players.add(new Player("마시모 루옹고", Position.MF, 73, this));
            players.add(new Player("웨스 번스", Position.MF, 74, this));
            players.add(new Player("코너 엘라", Position.MF, 72, this));

            players.add(new Player("루크 울펜든", Position.DF, 72, this));
            players.add(new Player("카메론 버지스", Position.DF, 71, this));
            players.add(new Player("해리 클라크", Position.DF, 70, this));

            players.add(new Player("바츨라프 흐라발", Position.GK, 72, this));
            return;
        }
    }
    
    
    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    
    public String getName() { return name; }
    public int getWeight() { return weight; }
    public List<Player> getPlayers() { return players; }

    // 리그 통계 관련 getter
    public int getPoints() { return points; }
    public int getGoalsFor() { return goalsFor; }
    public int getGoalsAgainst() { return goalsAgainst; }
    public int getGoalDifference() { return goalsFor - goalsAgainst; }

    // 시즌 초기화
    public void resetStats() {
        points = 0;
        goalsFor = 0;
        goalsAgainst = 0;
    }

    /**
     * 경기 결과 반영
     * @param scored  우리팀 득점
     * @param conceded 상대 득점
     */
    public void addMatchResult(int scored, int conceded) {
        goalsFor += scored;
        goalsAgainst += conceded;
        if (scored > conceded) points += 3;
        else if (scored == conceded) points += 1;
    }

    /**
     * 랜덤 득점자 반환 (포지션 비율 적용)
     * - FW: 60%, MF: 30%, DF: 10% (권장)
     */
    public Player getRandomScorer() {
        int roll = RandomEngine.getInt(1, 100);
        Position pos;
        if (roll <= 60) pos = Position.FW;
        else if (roll <= 90) pos = Position.MF;
        else pos = Position.DF;

        // 해당 포지션에서 랜덤 선택
        List<Player> candidates = new ArrayList<>();
        for (Player p : players) {
            if (p.getPosition() == pos) candidates.add(p);
        }
        if (candidates.isEmpty()) {
            // 없다면 전체에서 랜덤
            return players.get(RandomEngine.getInt(0, players.size() - 1));
        }
        return candidates.get(RandomEngine.getInt(0, candidates.size() - 1));
    }

    /**
     * 랜덤 선수(이벤트용)
     */
    public Player getRandomPlayer() {
        return players.get(RandomEngine.getInt(0, players.size() - 1));
    }
}
