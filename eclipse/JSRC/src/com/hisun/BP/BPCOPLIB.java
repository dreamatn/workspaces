package com.hisun.BP;

public class BPCOPLIB {
    public BPCOPLIB_RC RC = new BPCOPLIB_RC();
    public char FUNC = ' ';
    public char MODIFY_STS = ' ';
    public String OUTPUT_FMT = " ";
    public int APP_NO = 0;
    public int APP_BR = 0;
    public String APP_NOTE = " ";
    public int UP_BR = 0;
    public char APP_TYPE = ' ';
    public char APP_STS = ' ';
    public int BEG_DT = 0;
    public int END_DT = 0;
    public int APP_DT = 0;
    public int CONF_SEQ = 0;
    public int APP_NUM = 0;
    public int OUT_NUM = 0;
    public BPCOPLIB_BV_INFO[] BV_INFO = new BPCOPLIB_BV_INFO[10];
    public BPCOPLIB() {
        for (int i=0;i<10;i++) BV_INFO[i] = new BPCOPLIB_BV_INFO();
    }
}
