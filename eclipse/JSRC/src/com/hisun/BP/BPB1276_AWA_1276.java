package com.hisun.BP;

public class BPB1276_AWA_1276 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String CMMT_NO = " ";
    public short CMMT_NO_NO = 0;
    public String AC = " ";
    public short AC_NO = 0;
    public int PROC_DT = 0;
    public short PROC_DT_NO = 0;
    public BPB1276_CLT_LOOP[] CLT_LOOP = new BPB1276_CLT_LOOP[10];
    public BPB1276_AWA_1276() {
        for (int i=0;i<10;i++) CLT_LOOP[i] = new BPB1276_CLT_LOOP();
    }
}
