package com.hisun.CI;

public class CICBCVOU_DATA {
    public String CI_NO = " ";
    public int PAGE_NUM = 0;
    public int TOTAL_PAGE = 0;
    public int TOTAL_NUM = 0;
    public int CURR_PAGE = 0;
    public int PAGE_ROW = 0;
    public char LAST_PAGE = ' ';
    public CICBCVOU_DATA_DETAIL[] DATA_DETAIL = new CICBCVOU_DATA_DETAIL[30];
    public CICBCVOU_DATA() {
        for (int i=0;i<30;i++) DATA_DETAIL[i] = new CICBCVOU_DATA_DETAIL();
    }
}
