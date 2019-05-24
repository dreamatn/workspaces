package com.hisun.BP;

public class BPB8050_AWA_8050 {
    public long JRNNO = 0;
    public short JRNNO_NO = 0;
    public String AC = " ";
    public short AC_NO = 0;
    public String REF_NO = " ";
    public short REF_NO_NO = 0;
    public String TX_TOOL = " ";
    public short TX_TOOL_NO = 0;
    public String TX_CCY = " ";
    public short TX_CCY_NO = 0;
    public String TX_CD = " ";
    public short TX_CD_NO = 0;
    public String TX_TLR = " ";
    public short TX_TLR_NO = 0;
    public int STR_DT = 0;
    public short STR_DT_NO = 0;
    public int END_DT = 0;
    public short END_DT_NO = 0;
    public String TYP_CD = " ";
    public short TYP_CD_NO = 0;
    public int TX_DT = 0;
    public short TX_DT_NO = 0;
    public short JRN_SEQ = 0;
    public short JRN_SEQ_NO = 0;
    public String CI_NO = " ";
    public short CI_NO_NO = 0;
    public BPB8050_TX_AMTS[] TX_AMTS = new BPB8050_TX_AMTS[2];
    public BPB8050_TX_DATES[] TX_DATES = new BPB8050_TX_DATES[2];
    public BPB8050_TX_TIMES[] TX_TIMES = new BPB8050_TX_TIMES[2];
    public int TX_BR = 0;
    public short TX_BR_NO = 0;
    public short TX_DP = 0;
    public short TX_DP_NO = 0;
    public char DC_FLG = ' ';
    public short DC_FLG_NO = 0;
    public char STS = ' ';
    public short STS_NO = 0;
    public int AC_DT = 0;
    public short AC_DT_NO = 0;
    public BPB8050_AWA_8050() {
        for (int i=0;i<2;i++) TX_AMTS[i] = new BPB8050_TX_AMTS();
        for (int i=0;i<2;i++) TX_DATES[i] = new BPB8050_TX_DATES();
        for (int i=0;i<2;i++) TX_TIMES[i] = new BPB8050_TX_TIMES();
    }
}
