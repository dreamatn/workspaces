package com.hisun.LN;

public class LNCDLAQ {
    public LNCDLAQ_RC RC = new LNCDLAQ_RC();
    public String DD_AC = " ";
    public int PAGE_NUM = 0;
    public LNCDLAQ_OUT_HEAD OUT_HEAD = new LNCDLAQ_OUT_HEAD();
    public char RT_LN_FLG = ' ';
    public LNCDLAQ_OUT_INFO[] OUT_INFO = new LNCDLAQ_OUT_INFO[10];
    public LNCDLAQ() {
        for (int i=0;i<10;i++) OUT_INFO[i] = new LNCDLAQ_OUT_INFO();
    }
}
