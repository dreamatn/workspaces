package com.hisun.BP;

public class BPCQBCCY {
    public BPCQBCCY_RC RC = new BPCQBCCY_RC();
    public char CHG_CCY_FLG = ' ';
    public int CCY_CNT = 0;
    public BPCQBCCY_INFO[] INFO = new BPCQBCCY_INFO[300];
    public BPCQBCCY() {
        for (int i=0;i<300;i++) INFO[i] = new BPCQBCCY_INFO();
    }
}
