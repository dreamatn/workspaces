package com.hisun.BP;

public class BPCPORLO {
    public BPCPORLO_RC RC = new BPCPORLO_RC();
    public String BNK = " ";
    public int BR = 0;
    public char FLAG = ' ';
    public char UNDER_LVL = ' ';
    public int LAST_BR = 0;
    public short SUB_NUM = 0;
    public char NEXT_CALL_FLG = ' ';
    public BPCPORLO_SUB_BR_DATA[] SUB_BR_DATA = new BPCPORLO_SUB_BR_DATA[100];
    public BPCPORLO() {
        for (int i=0;i<100;i++) SUB_BR_DATA[i] = new BPCPORLO_SUB_BR_DATA();
    }
}
