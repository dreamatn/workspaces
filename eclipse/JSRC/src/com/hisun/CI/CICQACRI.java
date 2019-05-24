package com.hisun.CI;

public class CICQACRI {
    public CICQACRI_RC RC = new CICQACRI_RC();
    public char FUNC = ' ';
    public CICQACRI_DATA DATA = new CICQACRI_DATA();
    public CICQACRI_O_DATA O_DATA = new CICQACRI_O_DATA();
    public CICQACRI_AGR_NO_AREA[] AGR_NO_AREA = new CICQACRI_AGR_NO_AREA[3000];
    public CICQACRI() {
        for (int i=0;i<3000;i++) AGR_NO_AREA[i] = new CICQACRI_AGR_NO_AREA();
    }
}
