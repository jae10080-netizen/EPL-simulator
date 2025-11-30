package premierleague.team;

import premierleague.util.RandomEngine;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private int wins = 0;  //승
    private int draws = 0; //무
    private int losses = 0; //패
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
            players.add(new Player(name + "_FW" + i, Position.FW + RandomEngine.getInt(-5), this));
        }
        for (int i = 1; i <= 4; i++) {
            players.add(new Player(name + "_MF" + i, Position.MF + RandomEngine.getInt(-5, 8), this));
        }
        for (int i = 1; i <= 3; i++) {
            players.add(new Player(name + "_DF" + i, Position.DF + RandomEngine.getInt(-5, 6), this));
        }
        players.add(new Player(name + "_GK1", Position.GK + RandomEngine.getInt(-5, 6), this));
    }
*/ //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    private void generateDefaultPlayers() {

        // ============================
        // 1) Arsenal (아스널)
        // ============================
        if (name.equals("아스널")) {
            players.add(new Player("빅토르 요케레스", Position.FW, this));
            players.add(new Player("부카요 사카", Position.FW, this));
            players.add(new Player("트로사르", Position.FW, this));

            players.add(new Player("마르틴 외데고르", Position.MF, this));
            players.add(new Player("데클란 라이스", Position.MF, this));
            players.add(new Player("주비멘디", Position.MF, this));
            players.add(new Player("에제", Position.MF, this));

            players.add(new Player("윌리엄 살리바", Position.DF,  this));
            players.add(new Player("칼라피오리", Position.DF,  this));
            players.add(new Player("가브리엘 마갈량이스", Position.DF,  this));

            players.add(new Player("다비드 라야", Position.GK, this));
            return;
        }

        // ============================
        // 2) Man City (맨체스터 시티)
        // ============================
        if (name.equals("맨체스터 시티")) {
            players.add(new Player("엘링 홀란드", Position.FW, this));
            players.add(new Player("제레미 도투", Position.FW, this));
            players.add(new Player("필 포든", Position.FW, this));

            
            players.add(new Player("로드리", Position.MF, this));
            players.add(new Player("베르나르두 실바", Position.MF,  this));
            players.add(new Player("마테우스 누네스", Position.MF, this));

            players.add(new Player("후벵 디아스", Position.DF,  this));
            players.add(new Player("그바르디올", Position.DF,  this));
            players.add(new Player("존 스톤스", Position.DF,  this));
            players.add(new Player("나단 아케", Position.DF, this));

            players.add(new Player("돈나룸마", Position.GK, this));
            return;
        }

        // ============================
        // 3) Liverpool (리버풀)
        // ============================
        if (name.equals("리버풀")) {
            players.add(new Player("모하메드 살라", Position.FW,  this));
            players.add(new Player("코디 각포", Position.FW,  this));
            players.add(new Player("에키티케", Position.FW, this));

            players.add(new Player("알렉시스 맥 알리스터", Position.MF, this));
            players.add(new Player("도미닉 소보슬라이", Position.MF, this));
            players.add(new Player("그라벤베르흐", Position.MF, this));
            

            players.add(new Player("버질 반 다이크", Position.DF, this));
            players.add(new Player("이브라히마 코나테", Position.DF,  this));
            players.add(new Player("앤디 로버트슨", Position.DF,  this));
            players.add(new Player("밀로스 케르케즈", Position.DF, this));

            players.add(new Player("알리송 베케르", Position.GK, this));
            return;
        }

        // ============================
        // 4) Chelsea (첼시)
        // ============================
        if (name.equals("첼시")) {
            players.add(new Player("페드로 네투", Position.FW, this));
            players.add(new Player("후앙 페드로", Position.FW,  this));
            players.add(new Player("가르나초", Position.FW,  this));

            players.add(new Player("엔조 페르난데스", Position.MF, this));
            players.add(new Player("모이세스 카이세도", Position.MF, this));
            players.add(new Player("콜 팔머", Position.MF, this));

            players.add(new Player("찰로바", Position.DF, this));
            players.add(new Player("리스 제임스", Position.DF, this));
            players.add(new Player("쿠쿠렐라", Position.DF,this));
            players.add(new Player("귀스토", Position.DF, this));

            players.add(new Player("로베르트 산체스", Position.GK, this));
            return;
        }

        // ============================
        // 5) Man United (맨체스터 유나이티드)
        // ============================
        if (name.equals("맨체스터 유나이티드")) {
            players.add(new Player("아마드 디알로", Position.FW, this));
            players.add(new Player("마테우스 쿠냐", Position.FW, this));
            players.add(new Player("음뵈모", Position.FW,  this));

            players.add(new Player("브루노 페르난데스", Position.MF, this));
            players.add(new Player("카세미루", Position.MF, this));
            players.add(new Player("메이슨 마운트", Position.MF, this));
           

            players.add(new Player("요로", Position.DF, this));
            players.add(new Player("더리흐트", Position.DF, this));
            players.add(new Player("루크 쇼", Position.DF, this));
            players.add(new Player("도르구", Position.DF, this));
            
            players.add(new Player("바인드르", Position.GK, this));
            return;
        }

        // ============================
        // 6) Tottenham (토트넘 홋스퍼)
        // ============================
        if (name.equals("토트넘 홋스퍼")) {
            players.add(new Player("쿠드스", Position.FW, this));
            players.add(new Player("히샬리송", Position.FW, this));
            

            players.add(new Player("사비 시몬스", Position.MF, this));
            players.add(new Player("로드리고 벤탄쿠르", Position.MF, this));
            players.add(new Player("파페 마타르 사르", Position.MF, this));
            players.add(new Player("주앙 팔리냐", Position.MF, this));

            players.add(new Player("크리스티안 로메로", Position.DF, this));
            players.add(new Player("미키 판 더 펜", Position.DF, this));
            players.add(new Player("제드 스펜스", Position.DF, this));
            players.add(new Player("페드로 포로", Position.DF, this));
            
            players.add(new Player("굴리엘모 비카리오", Position.GK, this));
            return;
        }

        // ============================
        // 7) Newcastle (뉴캐슬 유나이티드)
        // ============================
        if (name.equals("뉴캐슬 유나이티드")) {
            players.add(new Player("닉 볼테마트", Position.FW, this));
            players.add(new Player("하비 반스", Position.FW, this));
            

            players.add(new Player("브루노 기마랑이스", Position.MF, this));
            players.add(new Player("산드로 토날리", Position.MF, this));
            players.add(new Player("조엘링톤", Position.MF, this));
            players.add(new Player("제이콥 머피", Position.MF, this));

            players.add(new Player("스벤 보트만", Position.DF, this));
            players.add(new Player("키어런 트리피어", Position.DF, this));
            players.add(new Player("댄 번", Position.DF, this));
            players.add(new Player("말릭 티아우", Position.DF, this));

            players.add(new Player("닉 포프", Position.GK, this));
            return;
        }

        // ============================
        // 8) Brighton (브라이튼 & 호브 알비온)
        // ============================
        if (name.equals("브라이튼 앤 호브 알비온")) {
            players.add(new Player("얀쿠바 민테", Position.FW, this));
            players.add(new Player("대니 웰백", Position.FW, this));
            

            players.add(new Player("야신 아야리", Position.MF, this));
            players.add(new Player("디에고 고메스", Position.MF, this));
            players.add(new Player("카를로스 발레바", Position.MF, this));
            players.add(new Player("마츠 비퍼", Position.MF, this));

            players.add(new Player("루이스 덩크", Position.DF, this));
            players.add(new Player("얀폴 반 헤케", Position.DF, this));
            players.add(new Player("막심 드 쿠이퍼", Position.DF, this));
            players.add(new Player("페르디 카디오글루", Position.DF, this));
            
            players.add(new Player("바르트 베르브뤼헌", Position.GK, this));
            return;
        }

        // ============================
        // 9) Aston Villa (아스톤 빌라)
        // ============================
        if (name.equals("애스턴 빌라")) {
            players.add(new Player("올리 왓킨스", Position.FW, this));
            players.add(new Player("에반 게상", Position.FW, this));
            

            players.add(new Player("아마두 오나나", Position.MF, this));
            players.add(new Player("존 맥긴", Position.MF, this));
            players.add(new Player("모르간 로저스", Position.MF, this));
            players.add(new Player("부바카르 카마라", Position.MF, this));

            players.add(new Player("파우 토레스", Position.DF, this));
            players.add(new Player("에즈리 콘사", Position.DF, this));
            players.add(new Player("뤼카 디뉴", Position.DF, this));
            players.add(new Player("매티 캐쉬", Position.DF, this));
            
            players.add(new Player("에밀리아노 마르티네스", Position.GK, this));
            return;
        }

        // ============================
        // 10) West Ham (웨스트 햄 유나이티드)
        // ============================
        if (name.equals("웨스트햄 유나이티드")) {
            players.add(new Player("재러드 보웬", Position.FW, this));
            players.add(new Player("크리센시오", Position.FW, this));
            

            players.add(new Player("루카스 파케타", Position.MF, this));
            players.add(new Player("마테우스 피지", Position.MF, this));
            players.add(new Player("토마시 수첵", Position.MF, this));
            players.add(new Player("제임스 워드-프라우스", Position.MF, this));

            players.add(new Player("아론 완비사카", Position.DF, this));
            players.add(new Player("맥스 킬만", Position.DF, this));
            players.add(new Player("말릭 디우프", Position.DF, this));
            players.add(new Player("장-클레르 토디보", Position.DF, this));
            
            players.add(new Player("알퐁스 아레올라", Position.GK, this));
            return;
        }

        // ============================
        // 11) Brentford (브렌트퍼드)
        // ============================
        if (name.equals("브렌트포드")) {
            players.add(new Player("아이반 토니", Position.FW, this));
            players.add(new Player("당고 우아타라", Position.FW, this));
            

            players.add(new Player("케빈 샤데", Position.MF, this));
            players.add(new Player("야르몰류크", Position.MF, this));
            players.add(new Player("미켈 담스고르", Position.MF, this));
            players.add(new Player("조던 헨더슨", Position.MF, this));
            
            players.add(new Player("미카엘 카요데", Position.DF, this));
            players.add(new Player("반 덴 버그 세프", Position.DF, this));
            players.add(new Player("콜린스", Position.DF, this));
            players.add(new Player("크리스토페르 아예르", Position.DF, this));
            
            players.add(new Player("켈러허", Position.GK, this));
            return;
        }

        // ============================
        // 12) Fulham (풀럼)
        // ============================
        if (name.equals("풀럼")) {
            players.add(new Player("라울 히메네스", Position.FW, this));
            players.add(new Player("케빈", Position.FW, this));
            players.add(new Player("알렉스 이워비", Position.FW, this));

            players.add(new Player("해리 윌슨", Position.MF, this));
            players.add(new Player("조슈아 킹", Position.MF, this));
            players.add(new Player("라이언 세세뇽", Position.MF, this));
            players.add(new Player("산데르 베르게", Position.MF, this));

            players.add(new Player("요아킴 안데르센", Position.DF, this));
            players.add(new Player("칼빈 배시", Position.DF, this));
            players.add(new Player("케니 테테", Position.DF, this));

            players.add(new Player("베른트 레노", Position.GK, this));
            return;
        }

        // ============================
        // 13) Wolves (울버햄튼 원더러스)
        // ============================
        if (name.equals("울버햄프턴 원더러스")) {
            players.add(new Player("존 아리아스", Position.FW, this));
            players.add(new Player("잭슨 차추아", Position.FW, this));
            players.add(new Player("라르센", Position.FW, this));

            players.add(new Player("마샬 무네치", Position.MF, this));
            players.add(new Player("주앙 고메스", Position.MF, this));
            players.add(new Player("안드레", Position.MF, this));
            

            players.add(new Player("엠마누엘 아그바두", Position.DF, this));
            players.add(new Player("산티아고 부에노", Position.DF, this));
            players.add(new Player("휴고 부에노", Position.DF, this));
            players.add(new Player("라다슬라프 크레이치", Position.DF, this));
            
            players.add(new Player("샘 존스톤", Position.GK, this));
            return;
        }

        // ============================
        // 14) Crystal Palace (크리스탈 팰리스)
        // ============================
        if (name.equals("크리스탈 팰리스")) {
            players.add(new Player("이스마엘라 사르", Position.FW, this));
            players.add(new Player("장-필립 마테타", Position.FW, this));
            players.add(new Player("예리미 피노", Position.FW, this));

            players.add(new Player("다이치 가마다", Position.MF, this));
            players.add(new Player("아담 워튼", Position.MF, this));
            

            players.add(new Player("마크 게히", Position.DF, this));
            players.add(new Player("막상스 라크루아", Position.DF, this));
            players.add(new Player("타이릭 미첼", Position.DF, this));
            players.add(new Player("크리스 리차즈", Position.DF, this));
            players.add(new Player("다니엘 무뇨스", Position.DF, this));
            
            players.add(new Player("딘 헨더슨", Position.GK, this));
            return;
        }

        // ============================
        // 15) Everton (에버턴)
        // ============================
        if (name.equals("에버턴")) {
            players.add(new Player("일리만 은디아예", Position.FW, this));
            players.add(new Player("베투", Position.FW, this));
            

            players.add(new Player("커어넌 듀스버리-홀", Position.MF, this));
            players.add(new Player("잭 그릴리시", Position.MF, this));
            players.add(new Player("이드리사 게예", Position.MF, this));
            players.add(new Player("제임스 가너", Position.MF, this));

            players.add(new Player("제임스 타르코프스키", Position.DF, this));
            players.add(new Player("제이크 오브라이언", Position.DF, this));
            players.add(new Player("비탈리 미콜렌코", Position.DF, this));
            players.add(new Player("마이클 킨", Position.DF, this));
            
            players.add(new Player("조던 픽포드", Position.GK, this));
            return;
        }

        // ============================
        // 16) Bournemouth (AFC 본머스)
        // ============================
        if (name.equals("본머스")) {
            players.add(new Player("데이빗 브룩스", Position.FW, this));
            players.add(new Player("앙투안 세메뇨", Position.FW, this));
            players.add(new Player("메바니우송", Position.FW, this));

           
            players.add(new Player("마커스 타버니어", Position.MF, this));
            players.add(new Player("타일러 아담스", Position.MF, this));
            players.add(new Player("알렉스 스콧", Position.MF, this));

            players.add(new Player("바포데 디아키테", Position.DF, this));
            players.add(new Player("마르코스 세네시", Position.DF, this));
            players.add(new Player("아드리안 트뤼페르", Position.DF, this));
            players.add(new Player("알레한드로 히메네스", Position.DF, this));
            
            players.add(new Player("조르제 페트로비치", Position.GK, this));
            return;
        }

        // ============================
        // 17) Nottingham Forest (노팅엄 포레스트)
        // ============================
        if (name.equals("노팅엄 포레스트")) {
            players.add(new Player("크리스 우드", Position.FW, this));
            players.add(new Player("이고르 제주스", Position.FW, this));
            players.add(new Player("단 은도예", Position.FW, this));

            players.add(new Player("모건 깁스-화이트", Position.MF, this));
            players.add(new Player("이브라힘 상가레", Position.MF, this));
            players.add(new Player("엘리엇 안데르손", Position.MF, this));

            players.add(new Player("무리요", Position.DF, this));
            players.add(new Player("네코 윌리엄스", Position.DF, this));
            players.add(new Player("니콜라 밀렌코비치", Position.DF, this));
            players.add(new Player("모라토", Position.DF, this));
            
            players.add(new Player("마츠 셀스", Position.GK, this));
            return;
        }

        // ============================
        // 18) Southampton (사우샘프턴) - (승격팀)
        // ============================
        if (name.equals("선덜랜드")) {
            players.add(new Player("베르트란드 트라오레", Position.FW, this));
            players.add(new Player("헴스디네 탈비", Position.FW, this));
            players.add(new Player("윌슨 이시도어", Position.FW, this));

            players.add(new Player("엔조 르페", Position.MF, this));
            players.add(new Player("크라니트 자카", Position.MF, this));
            players.add(new Player("노아 사디키", Position.MF, this));
           

            players.add(new Player("베다니엘 발라드", Position.DF, this));
            players.add(new Player("노르디 물레르 무키엘", Position.DF, this));
            players.add(new Player("트라이 훔", Position.DF, this));
            players.add(new Player("만다바", Position.DF, this));
            
            players.add(new Player("로빈 뢰프스", Position.GK, this));
            return;
        }

        // ============================
        // 19) Leicester City (레스터 시티) - (승격팀)
        // ============================
        if (name.equals("리즈 유나이티드")) {
            players.add(new Player("루카스 은메차", Position.FW, this));
            players.add(new Player("노아 오카포", Position.FW, this));
            players.add(new Player("도미니크 칼버트-르윈", Position.FW, this));

            players.add(new Player("브랜든 아론슨", Position.MF, this));
            players.add(new Player("안톤 스타치", Position.MF, this));
            players.add(new Player("션 롱스태프", Position.MF, this));
            

            players.add(new Player("에단 암파두", Position.DF, this));
            players.add(new Player("가브리엘 구드문손", Position.DF, this));
            players.add(new Player("제이든 보글", Position.DF, this));
            players.add(new Player("조 로든", Position.DF, this));
            
            players.add(new Player("페리 루카스", Position.GK, this));
            return;
        }

        // ============================
        // 20) Ipswich Town (입스위치 타운) - (승격팀)
        // ============================
        if (name.equals("번리")) {
            players.add(new Player("롬 차오나", Position.FW, this));
            players.add(new Player("라일 포스터", Position.FW, this));
            players.add(new Player("재이든 앤서니", Position.FW, this));

            players.add(new Player("조시 로렌트", Position.MF, this));
            players.add(new Player("플로렌치누", Position.MF, this));
            players.add(new Player("치무아냐 우고추쿠", Position.MF, this));
            players.add(new Player("조시 쿨렌", Position.MF, this));

            players.add(new Player("퀼린치 하트맨", Position.DF, this));
            players.add(new Player("카일 워커", Position.DF, this));
            players.add(new Player("막심 에스테베", Position.DF, this));

            players.add(new Player("마르틴 두브라브카", Position.GK, this));
            return;
        }
    }
    
    
    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    
    public String getName() { return name; }
    public int getWeight() { return weight; }
    public List<Player> getPlayers() { return players; }

    // 리그 통계 관련 getter
    public int getPoints() { return points; }
    
    public int getWins() { return wins; }
    public int getDraws() { return draws; }
    public int getLosses() { return losses; }
    public int getGoalsFor() { return goalsFor; }
    public int getGoalsAgainst() { return goalsAgainst; }
    public int getGoalDifference() { return goalsFor - goalsAgainst; }

    // 시즌 초기화
    public void resetStats() {
        points = 0;
        wins = 0;
        draws = 0;
        losses = 0;
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
        if (scored > conceded) {
        	points += 3;
        	wins ++;
        }else if(scored == conceded) {
        	points += 1;
        	draws++;
        	
        }else {
        	losses++;
        }      
      }

    /**
     * 랜덤 득점자 반환 (포지션 비율 적용)
     * - FW: 60%, MF: 30%, DF: 10%
     */
    public Player getRandomScorer() {
        int roll = RandomEngine.getInt(1,100);
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
    
 // ---------------------------------------------------------
    // ★ [추가] 포지션별 선수 뽑기 메소드들 (이벤트용)
    // ---------------------------------------------------------

    // 1. 골키퍼 뽑기 (선방 이벤트)
    // 팀에 등록된 GK를 찾아서 반환합니다.
    public Player getGoalkeeper() {
        for (Player p : players) {
            if (p.getPosition() == Position.GK) return p;
        }
        // 혹시 GK가 없으면 아무나 반환 (오류 방지)
        return getRandomPlayer(); 
    }

    // 2. 수비수 뽑기 (태클 이벤트)
    // DF 포지션 선수들 중에서 한 명을 랜덤으로 뽑습니다.
    public Player getRandomDefender() {
        return getPlayerByPosition(Position.DF);
    }

    // 3. 미드필더 뽑기 (패스 이벤트)
    // MF 포지션 선수들 중에서 한 명을 랜덤으로 뽑습니다.
    public Player getRandomMidfielder() {
        return getPlayerByPosition(Position.MF);
    }

    // 4. 공격수 뽑기 (슈팅 이벤트)
    // FW 포지션 선수들 중에서 한 명을 랜덤으로 뽑습니다.
    public Player getRandomForward() {
        return getPlayerByPosition(Position.FW);
    }
    
 // ★ [수정] 슈팅할 선수 뽑기 (공격수 + 미드필더 포함)
    public Player getRandomShooter() {
        List<Player> candidates = players.stream()
                // FW 또는 MF 포지션인 선수만 모음
                .filter(p -> p.getPosition() == Position.FW || p.getPosition() == Position.MF)
                .collect(Collectors.toList());
        
        // [수정] 예외 처리 삭제! (무조건 FW나 MF가 있으므로 바로 리턴)
        return candidates.get(RandomEngine.getInt(0, candidates.size() - 1));
    }

    // [내부 도구] 특정 포지션에서 랜덤으로 한 명 뽑는 메소드
    private Player getPlayerByPosition(Position pos) {
        // 해당 포지션 선수들만 모으기
        List<Player> candidates = players.stream()
                .filter(p -> p.getPosition() == pos)
                .collect(Collectors.toList());
        
        // 그 중에서 한 명 랜덤 뽑기
        if (!candidates.isEmpty()) {
            return candidates.get(RandomEngine.getInt(0, candidates.size() - 1));
        }
        // 해당 포지션 선수가 없으면 전체에서 아무나 뽑기
        return getRandomPlayer(); 
    }
}
