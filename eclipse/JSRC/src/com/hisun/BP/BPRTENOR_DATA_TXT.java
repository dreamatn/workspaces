package com.hisun.BP;

public class BPRTENOR_DATA_TXT {
    public char DATE_CHECK = ' ';
    public BPRTENOR_CODE_DETAIL[] CODE_DETAIL = new BPRTENOR_CODE_DETAIL[20];
    public char EXINT_FLG = ' ';
    public BPRTENOR_INT_DETAIL[] INT_DETAIL = new BPRTENOR_INT_DETAIL[20];
    public BPRTENOR_DATA_TXT() {
        for (int i=0;i<20;i++) CODE_DETAIL[i] = new BPRTENOR_CODE_DETAIL();
        for (int i=0;i<20;i++) INT_DETAIL[i] = new BPRTENOR_INT_DETAIL();
    }
}
