package com.hisun.BP;

public class BPB4681_AWA_4681 {
    public BPB4681_BR_INFO[] BR_INFO = new BPB4681_BR_INFO[20];
    public int PRE_BR = 0;
    public short PRE_BR_NO = 0;
    public char RM_CR_FL = ' ';
    public short RM_CR_FL_NO = 0;
    public String REMARK = " ";
    public short REMARK_NO = 0;
    public BPB4681_AWA_4681() {
        for (int i=0;i<20;i++) BR_INFO[i] = new BPB4681_BR_INFO();
    }
}
