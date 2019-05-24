package com.hisun.BP;

public class BPCSARAP {
    public BPCSARAP_RC RC = new BPCSARAP_RC();
    public char RETURN_INFO = ' ';
    public BPCSARAP_INFO INFO = new BPCSARAP_INFO();
    public String OUTPUT_FMT = " ";
    public int APP_NO = 0;
    public char APP_STS = ' ';
    public int UP_BR = 0;
    public int APP_BR = 0;
    public int APP_DT = 0;
    public int CONF_NO = 0;
    public BPCSARAP_APP_INFO[] APP_INFO = new BPCSARAP_APP_INFO[5];
    public BPCSARAP() {
        for (int i=0;i<5;i++) APP_INFO[i] = new BPCSARAP_APP_INFO();
    }
}
