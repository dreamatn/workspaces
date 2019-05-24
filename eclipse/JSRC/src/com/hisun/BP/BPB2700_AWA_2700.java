package com.hisun.BP;

public class BPB2700_AWA_2700 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String HLD_IDTP = " ";
    public short HLD_IDTP_NO = 0;
    public String HLD_IDNO = " ";
    public short HLD_IDNO_NO = 0;
    public String HLD_NM = " ";
    public short HLD_NM_NO = 0;
    public String HLD_PHN = " ";
    public short HLD_PHN_NO = 0;
    public String HLD_ADD = " ";
    public short HLD_ADD_NO = 0;
    public String HLD_EML = " ";
    public short HLD_EML_NO = 0;
    public int FLS_RECN = 0;
    public short FLS_RECN_NO = 0;
    public BPB2700_CCYS[] CCYS = new BPB2700_CCYS[20];
    public BPB2700_AWA_2700() {
        for (int i=0;i<20;i++) CCYS[i] = new BPB2700_CCYS();
    }
}
