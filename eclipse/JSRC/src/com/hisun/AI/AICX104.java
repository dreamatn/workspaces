package com.hisun.AI;

public class AICX104 {
    public AICX104_BASIC_INFO BASIC_INFO = new AICX104_BASIC_INFO();
    public AICX104_MID_OCCURS[] MID_OCCURS = new AICX104_MID_OCCURS[400];
    public AICX104_END_OCCURS[] END_OCCURS = new AICX104_END_OCCURS[100];
    public AICX104() {
        for (int i=0;i<400;i++) MID_OCCURS[i] = new AICX104_MID_OCCURS();
        for (int i=0;i<100;i++) END_OCCURS[i] = new AICX104_END_OCCURS();
    }
}
