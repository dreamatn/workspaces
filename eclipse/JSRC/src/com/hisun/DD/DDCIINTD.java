package com.hisun.DD;

public class DDCIINTD {
    public DDCIINTD_RC RC = new DDCIINTD_RC();
    public char DEP_POST_PERIOD1 = ' ';
    public short DEP_POST_DATE1 = 0;
    public char DEP_POST_PERIOD2 = ' ';
    public short DEP_POST_DATE2 = 0;
    public String CCY = " ";
    public int CURR_DATE = 0;
    public int NEXT_DATE = 0;
    public DDCIINTD_OUTPUT_INFO[] OUTPUT_INFO = new DDCIINTD_OUTPUT_INFO[6];
    public int D_POST_DATE1 = 0;
    public int D_POST_DATE2 = 0;
    public DDCIINTD() {
        for (int i=0;i<6;i++) OUTPUT_INFO[i] = new DDCIINTD_OUTPUT_INFO();
    }
}
