package com.hisun.CM;

public class CMCQFHIS {
    public CMCQFHIS_RC RC = new CMCQFHIS_RC();
    public int AC_DT = 0;
    public long JRNNO = 0;
    public char DC_FLG = ' ';
    public String INQ_CTL = " ";
    public String INQ_REF = " ";
    public CMCQFHIS_INQ_RTN INQ_RTN = new CMCQFHIS_INQ_RTN();
    public int CLEAR_DATE = 0;
    public int CNT = 0;
    public CMCQFHIS_INFO[] INFO = new CMCQFHIS_INFO[60];
    public CMCQFHIS() {
        for (int i=0;i<60;i++) INFO[i] = new CMCQFHIS_INFO();
    }
}
