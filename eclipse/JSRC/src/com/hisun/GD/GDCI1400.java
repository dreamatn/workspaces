package com.hisun.GD;

public class GDCI1400 {
    public String TXCCY = " ";
    public char TXCTYP = ' ';
    public int TX_BR = 0;
    public double TXAMT = 0;
    public String TX_CRAC = " ";
    public char ADV_FLG = ' ';
    public String ADV_AC = " ";
    public String CNTR_NO = " ";
    public String TXRMK = " ";
    public String TXMMO = " ";
    public GDCI1400_DR_SEC[] DR_SEC = new GDCI1400_DR_SEC[10];
    public GDCI1400() {
        for (int i=0;i<10;i++) DR_SEC[i] = new GDCI1400_DR_SEC();
    }
}
