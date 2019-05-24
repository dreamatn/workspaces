package com.hisun.DC;

public class DCCUDDCK {
    public DCCUDDCK_RC RC = new DCCUDDCK_RC();
    public String AC_NO = " ";
    public DCCUDDCK_CARD_INFO[] CARD_INFO = new DCCUDDCK_CARD_INFO[5];
    public char JOIN_CRD_FLG = ' ';
    public DCCUDDCK() {
        for (int i=0;i<5;i++) CARD_INFO[i] = new DCCUDDCK_CARD_INFO();
    }
}
