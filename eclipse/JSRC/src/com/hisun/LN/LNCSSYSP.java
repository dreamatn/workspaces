package com.hisun.LN;

public class LNCSSYSP {
    public LNCSSYSP_RC RC = new LNCSSYSP_RC();
    public char FUN_CODE = ' ';
    public int DISB_REF = 0;
    public LNCSSYSP_PART_TSQ_OP PART_TSQ_OP = new LNCSSYSP_PART_TSQ_OP();
    public LNCSSYSP_PART_INFO[] PART_INFO = new LNCSSYSP_PART_INFO[99];
    public LNCSSYSP() {
        for (int i=0;i<99;i++) PART_INFO[i] = new LNCSSYSP_PART_INFO();
    }
}
