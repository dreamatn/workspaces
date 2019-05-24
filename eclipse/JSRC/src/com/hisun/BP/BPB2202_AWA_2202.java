package com.hisun.BP;

public class BPB2202_AWA_2202 {
    public String TLR = " ";
    public short TLR_NO = 0;
    public String CCY = " ";
    public short CCY_NO = 0;
    public String CASH_TYP = " ";
    public short CASH_TYP_NO = 0;
    public String PLBOX_NO = " ";
    public short PLBOX_NO_NO = 0;
    public char PLBOX_TP = ' ';
    public short PLBOX_TP_NO = 0;
    public double GD_AMT = 0;
    public short GD_AMT_NO = 0;
    public double BD_AMT = 0;
    public short BD_AMT_NO = 0;
    public double HBD_AMT = 0;
    public short HBD_AMT_NO = 0;
    public BPB2202_PAR_INFO[] PAR_INFO = new BPB2202_PAR_INFO[90];
    public char VB_FLG = ' ';
    public short VB_FLG_NO = 0;
    public char CHK_FLG = ' ';
    public short CHK_FLG_NO = 0;
    public String TX_CODE = " ";
    public short TX_CODE_NO = 0;
    public double AMT = 0;
    public short AMT_NO = 0;
    public char BOXP_TYP = ' ';
    public short BOXP_TYP_NO = 0;
    public String INVNTY = " ";
    public short INVNTY_NO = 0;
    public String HANDLER = " ";
    public short HANDLER_NO = 0;
    public String INTY_NM = " ";
    public short INTY_NM_NO = 0;
    public String HLD_NM = " ";
    public short HLD_NM_NO = 0;
    public BPB2202_AWA_2202() {
        for (int i=0;i<90;i++) PAR_INFO[i] = new BPB2202_PAR_INFO();
    }
}
