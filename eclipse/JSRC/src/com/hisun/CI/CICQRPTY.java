package com.hisun.CI;

public class CICQRPTY {
    public char IFF_REC_IND = ' ';
    public short BKCODE = 0;
    public String ID = " ";
    public String ROOTID_NO = " ";
    public String NAME = " ";
    public char STAFF_IND = ' ';
    public char SEC161B_IND = ' ';
    public char S83_EXEMPT = ' ';
    public String TOT_EFF_DATE = " ";
    public String TOT_EXP_DATE = " ";
    public CICQRPTY_CODE_INF[] CODE_INF = new CICQRPTY_CODE_INF[70];
    public String CP_GCODE = " ";
    public String CP_GEFF_DATE = " ";
    public String CP_GEXP_DATE = " ";
    public char CP_G_UNAUTH = ' ';
    public CICQRPTY_CP_INF[] CP_INF = new CICQRPTY_CP_INF[41];
    public String CNAME = " ";
    public CICQRPTY() {
        for (int i=0;i<70;i++) CODE_INF[i] = new CICQRPTY_CODE_INF();
        for (int i=0;i<41;i++) CP_INF[i] = new CICQRPTY_CP_INF();
    }
}
