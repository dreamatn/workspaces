package com.hisun.BP;

public class BPB4870_AWA_4870 {
    public String TX_CODE = " ";
    public short TX_CODE_NO = 0;
    public String CHNL_NO = " ";
    public short CHNL_NO_NO = 0;
    public String SRV_CODE = " ";
    public short SRV_CODE_NO = 0;
    public short RULE_NO = 0;
    public short RULE_NO_NO = 0;
    public String DESC = " ";
    public short DESC_NO = 0;
    public char RULE_STS = ' ';
    public short RULE_STS_NO = 0;
    public char CTRL_TYP = ' ';
    public short CTRL_TYP_NO = 0;
    public String MSG_CODE = " ";
    public short MSG_CODE_NO = 0;
    public String MSG_DESC = " ";
    public short MSG_DESC_NO = 0;
    public char MSG_TYPE = ' ';
    public short MSG_TYPE_NO = 0;
    public String ATH_LVL = " ";
    public short ATH_LVL_NO = 0;
    public char AUTO_IND = ' ';
    public short AUTO_IND_NO = 0;
    public short CNT = 0;
    public short CNT_NO = 0;
    public BPB4870_RULE_ARR[] RULE_ARR = new BPB4870_RULE_ARR[5];
    public BPB4870_AWA_4870() {
        for (int i=0;i<5;i++) RULE_ARR[i] = new BPB4870_RULE_ARR();
    }
}
