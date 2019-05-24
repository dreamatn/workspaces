package com.hisun.BP;

public class BPCAHIST {
    public char RETURN_INFO = ' ';
    public String ACO_IAC = " ";
    public int STR_DT = 0;
    public int END_DT = 0;
    public BPCAHIST_DT_INFO[] DT_INFO = new BPCAHIST_DT_INFO[60];
    public BPCAHIST() {
        for (int i=0;i<60;i++) DT_INFO[i] = new BPCAHIST_DT_INFO();
    }
}
