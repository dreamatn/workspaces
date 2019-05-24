package com.hisun.GD;

public class GDCSPAAC {
    public char MN_FLG = ' ';
    public String TXCCY = " ";
    public char TXCTYP = ' ';
    public int TX_BR = 0;
    public char TXSTLT = ' ';
    public double TXAMT = 0;
    public double PNAMT = 0;
    public String TX_CRAC = " ";
    public char ADV_FLG = ' ';
    public String ADV_AC = " ";
    public String CNTR_NO = " ";
    public String TXRMK = " ";
    public String TXMMO = " ";
    public GDCSPAAC_DR_SEC[] DR_SEC = new GDCSPAAC_DR_SEC[10];
    public GDCSPAAC() {
        for (int i=0;i<10;i++) DR_SEC[i] = new GDCSPAAC_DR_SEC();
    }
}
