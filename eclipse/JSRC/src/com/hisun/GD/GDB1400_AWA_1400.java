package com.hisun.GD;

public class GDB1400_AWA_1400 {
    public String TXCCY = " ";
    public short TXCCY_NO = 0;
    public char TXCTYP = ' ';
    public short TXCTYP_NO = 0;
    public int TX_BR = 0;
    public short TX_BR_NO = 0;
    public double TXAMT = 0;
    public short TXAMT_NO = 0;
    public String TX_CRAC = " ";
    public short TX_CRAC_NO = 0;
    public char ADV_FLG = ' ';
    public short ADV_FLG_NO = 0;
    public String ADV_AC = " ";
    public short ADV_AC_NO = 0;
    public String CNTR_NO = " ";
    public short CNTR_NO_NO = 0;
    public String TXRMK = " ";
    public short TXRMK_NO = 0;
    public String TXMMO = " ";
    public short TXMMO_NO = 0;
    public GDB1400_DR_SEC[] DR_SEC = new GDB1400_DR_SEC[10];
    public GDB1400_AWA_1400() {
        for (int i=0;i<10;i++) DR_SEC[i] = new GDB1400_DR_SEC();
    }
}
