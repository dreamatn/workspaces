package com.hisun.BP;

public class BPCSBVMI {
    public BPCSBVMI_RC RC = new BPCSBVMI_RC();
    public int MOV_DT = 0;
    public long CONF_NO = 0;
    public int OUT_BR = 0;
    public String OUT_TLR = " ";
    public char BV_STS = ' ';
    public int CNT = 0;
    public BPCSBVMI_BV_DATA[] BV_DATA = new BPCSBVMI_BV_DATA[10];
    public String FEE_ACNO = " ";
    public char PB_FLG = ' ';
    public char BR_FLG = ' ';
    public char IN_TYP = ' ';
    public int APP_NO = 0;
    public char APP_TYPE = ' ';
    public char BRFLG = ' ';
    public int CNT1 = 0;
    public BPCSBVMI() {
        for (int i=0;i<10;i++) BV_DATA[i] = new BPCSBVMI_BV_DATA();
    }
}
