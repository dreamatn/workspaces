package com.hisun.BP;

public class BPREXSEQ_DAT_TXT {
    public BPREXSEQ_CCY_DATA[] CCY_DATA = new BPREXSEQ_CCY_DATA[18];
    public char FIX_IND = ' ';
    public BPREXSEQ_DAT_TXT() {
        for (int i=0;i<18;i++) CCY_DATA[i] = new BPREXSEQ_CCY_DATA();
    }
}
