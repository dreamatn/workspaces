package com.hisun.BP;

public class BPCOAPLL {
    public BPCOAPLL_RC RC = new BPCOAPLL_RC();
    public char FUNC = ' ';
    public char MODIFY_STS = ' ';
    public String OUTPUT_FMT = " ";
    public int APP_NO = 0;
    public int APP_BR = 0;
    public int BR = 0;
    public char APP_TYPE = ' ';
    public char APP_STS = ' ';
    public int BEG_DT = 0;
    public int END_DT = 0;
    public String APP_NOTE = " ";
    public int APP_DT = 0;
    public int CONF_SEQ = 0;
    public BPCOAPLL_BV_INFO[] BV_INFO = new BPCOAPLL_BV_INFO[10];
    public int MOV_DT = 0;
    public int IN_BR = 0;
    public String IN_TLR = " ";
    public char FLG = ' ';
    public int SEQ = 0;
    public String TR_TLR = " ";
    public int CNT = 0;
    public BPCOAPLL() {
        for (int i=0;i<10;i++) BV_INFO[i] = new BPCOAPLL_BV_INFO();
    }
}
