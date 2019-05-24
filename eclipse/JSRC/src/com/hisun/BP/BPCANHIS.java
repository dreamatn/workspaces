package com.hisun.BP;

public class BPCANHIS {
    public char RETURN_INFO = ' ';
    public String ACO_IAC = " ";
    public int STR_DT = 0;
    public int END_DT = 0;
    public BPCANHIS_DT_INFO[] DT_INFO = new BPCANHIS_DT_INFO[60];
    public BPCANHIS() {
        for (int i=0;i<60;i++) DT_INFO[i] = new BPCANHIS_DT_INFO();
    }
}
