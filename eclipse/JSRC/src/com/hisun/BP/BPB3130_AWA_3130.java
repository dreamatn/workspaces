package com.hisun.BP;

public class BPB3130_AWA_3130 {
    public char VB_FLG = ' ';
    public short VB_FLG_NO = 0;
    public BPB3130_BV_INFO[] BV_INFO = new BPB3130_BV_INFO[99];
    public char CK_TYP = ' ';
    public short CK_TYP_NO = 0;
    public String REP_TLR = " ";
    public short REP_TLR_NO = 0;
    public char CHK_TYP = ' ';
    public short CHK_TYP_NO = 0;
    public String TX_CODE = " ";
    public short TX_CODE_NO = 0;
    public String INVNTY = " ";
    public short INVNTY_NO = 0;
    public char STS = ' ';
    public short STS_NO = 0;
    public String PLBOX_NO = " ";
    public short PLBOX_NO_NO = 0;
    public String HANDLER = " ";
    public short HANDLER_NO = 0;
    public String INTY_NM = " ";
    public short INTY_NM_NO = 0;
    public String HLD_NM = " ";
    public short HLD_NM_NO = 0;
    public BPB3130_AWA_3130() {
        for (int i=0;i<99;i++) BV_INFO[i] = new BPB3130_BV_INFO();
    }
}
