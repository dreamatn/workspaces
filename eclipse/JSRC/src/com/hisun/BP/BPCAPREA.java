package com.hisun.BP;

public class BPCAPREA {
    public BPCAPREA_BR_INFO[] BR_INFO = new BPCAPREA_BR_INFO[20];
    public int PRE_BR = 0;
    public char RM_CR_FL = ' ';
    public String REMARK = " ";
    public char FILLER9 = 0X01;
    public int LAST_DT = 0;
    public String LAST_TL = " ";
    public BPCAPREA() {
        for (int i=0;i<20;i++) BR_INFO[i] = new BPCAPREA_BR_INFO();
    }
}
