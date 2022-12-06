package com.coopnc.effectivejava3rd.item01.exam01;

public class PlayerReports {
    private Player player;
    private boolean isSpecial;
    private boolean isInjury;

    // 선수리포트에에 어떠한 요청을 하는지 명시적으로 알 수가 없고 동일한 Signature 를 가질 경우 특별 선수인지, 부상 등재인지 알 수가 없음.
    /**
    public PlayerReports(Player player, boolean isSpecial) {
        this.player = player;
        this.isSpecial = isSpecial;
    }

    public PlayerReports(Player player, boolean isInjury) {
        this.player = player;
        this.isInjury = isInury;
    }*/

    // 외부에서 생성되지 않도록 생성자를 제공하지 않음.
    private PlayerReports() {
    }

    // 명시적인 이름을 가질 수 있으며, 동일한 Signature 를 사용할 수 있음.
    public static void addSpecialPlayer(Player player, boolean isSpecial) {
        PlayerReports playerReports = new PlayerReports();
        playerReports.player = player;
        playerReports.isSpecial = isSpecial;
    }

    public static void addInjuredPlayer(Player player, boolean isInjury) {
        PlayerReports playerReports = new PlayerReports();
        playerReports.player = player;
        playerReports.isInjury = isInjury;
    }
}
