package com.hisun.AI;

public class AICX522 {
    public int ADJ_DATE = 0;
    public String COA_FLG = " ";
    public AICX522_COA_NO[] COA_NO = new AICX522_COA_NO[5];
    public String ITM_NO_NEW = " ";
    public String ENG_NM_NEW = " ";
    public String CHS_NM_NEW = " ";
    public char FILLER11 = 0X02;
    public String TS = " ";
    public AICX522() {
        for (int i=0;i<5;i++) COA_NO[i] = new AICX522_COA_NO();
    }
}
