package com.hisun.AI;

public class AIB4530_AWA_4530 {
    public String RPT_ID = " ";
    public short RPT_ID_NO = 0;
    public String GL_ITEM = " ";
    public short GL_ITEM_NO = 0;
    public String RPT_NAME = " ";
    public short RPT_NAME_NO = 0;
    public String CAT = " ";
    public short CAT_NO = 0;
    public short SEQ = 0;
    public short SEQ_NO = 0;
    public char CAL_MTH = ' ';
    public short CAL_MTH_NO = 0;
    public String DESC = " ";
    public short DESC_NO = 0;
    public short GL_SEQ = 0;
    public short GL_SEQ_NO = 0;
    public AIB4530_TIMS[] TIMS = new AIB4530_TIMS[60];
    public AIB4530_AWA_4530() {
        for (int i=0;i<60;i++) TIMS[i] = new AIB4530_TIMS();
    }
}
