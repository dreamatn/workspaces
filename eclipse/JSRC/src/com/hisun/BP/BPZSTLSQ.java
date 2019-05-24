package com.hisun.BP;

import com.hisun.AI.*;
import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.IB.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSTLSQ {
    int JIBS_tmp_int;
    DBParm BPTTLT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZSTLSQ";
    String CPN_R_STARTBR_TLT = "BP-R-STARTBR-TLT    ";
    String CPN_R_TLR_SIGN_HIS = "BP-TLR-SIGN-HIS-BRW";
    short WK_MAX_OUTPUT = 500;
    String AI_INQ_GRP_CLOSE = "AI-INQ-GRP-CLOSE";
    BPZSTLSQ_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSTLSQ_WS_TEMP_VARIABLE();
    BPZSTLSQ_WS_LVL WS_LVL = new BPZSTLSQ_WS_LVL();
    String WS_ERR_MSG = " ";
    char WS_MSG_TYPE = ' ';
    String WS_INFO = " ";
    BPZSTLSQ_WS_ERROR_INFO WS_ERROR_INFO = new BPZSTLSQ_WS_ERROR_INFO();
    int WS_COUNT = 0;
    int WS_COUNT1 = 0;
    char WS_TBL_FLAG = ' ';
    short WS_CNT_I = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRTLTB BPCRTLTB = new BPCRTLTB();
    BPRTLT BPRTLT = new BPRTLT();
    BPRMSG BPRMSG = new BPRMSG();
    AICPZMIB AICPZMIB = new AICPZMIB();
    DDCSZMQC DDCSZMQC = new DDCSZMQC();
    BPCOTLSQ BPCOTLSQ = new BPCOTLSQ();
    BPCRHIST BPCRHIST = new BPCRHIST();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    AICPGINF AICPGINF = new AICPGINF();
    BPCPBVBR BPCPBVBR = new BPCPBVBR();
    BPCPCSBR BPCPCSBR = new BPCPCSBR();
    IBCCASH IBCCASH = new IBCCASH();
    AICPQGRP AICPQGRP = new AICPQGRP();
    BPCFPLCK BPCFPLCK = new BPCFPLCK();
    BPCCJWST BPCCJWST = new BPCCJWST();
    SCCGWA SCCGWA;
    BPCSTLSQ BPCSTLSQ;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSTLSQ BPCSTLSQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTLSQ = BPCSTLSQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        if (BPCSTLSQ.CHK_FLG == 'Y') {
            B000_MAIN_PROC();
            if (pgmRtn) return;
        } else {
            B020_MAIN_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "BPZSTLSQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTB);
        IBS.init(SCCGWA, BPCRHIST);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_01_QUERY_TLR_SIGNOFF();
        if (pgmRtn) return;
        B010_01_CHECK_BALANCE();
        if (pgmRtn) return;
        B020_02_CHECK_ACCOUNT();
        if (pgmRtn) return;
        B020_06_CHECK_BOXPL();
        if (pgmRtn) return;
        B070_CHECK_ZMQC();
        if (pgmRtn) return;
        B020_05_CHECK_BV_ONWAY();
        if (pgmRtn) return;
        B030_CHECK_CASH_ONWAY();
        if (pgmRtn) return;
        B040_CHECK_IB_ACCOUNT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AICPQGRP);
        AICPQGRP.INPUT_DATA.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        B050_CHECK_AI_GRP();
        if (pgmRtn) return;
        B060_CHECK_BPTCJWST();
        if (pgmRtn) return;
    }
    public void B020_01_QUERY_TLR_SIGNOFF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        BPRTLT.NEW_BR = BPCSTLSQ.TLR_BR;
        BPRTLT.KEY.TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, BPRTLT.SIGN_STS);
        CEP.TRC(SCCGWA, BPRTLT.NEW_BR);
        T000_GROUP_BPTTLT_BR();
        if (pgmRtn) return;
        T001_GROUP_BPTTLT_BR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_COUNT);
        CEP.TRC(SCCGWA, WS_COUNT1);
        if (WS_COUNT != 0 
            || WS_COUNT1 != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_STS_IS_O_T;
            S000_ERR_MSG_PROC_INFO();
            if (pgmRtn) return;
        }
    }
    public void B010_01_CHECK_BALANCE() throws IOException,SQLException,Exception {
        WS_TEMP_VARIABLE.WS_CNT = 0;
        IBS.init(SCCGWA, AICPZMIB);
        AICPZMIB.BR = BPCSTLSQ.TLR_BR;
        CEP.TRC(SCCGWA, AICPZMIB.BR);
        S000_CALL_AIZPZMIB();
        if (pgmRtn) return;
        if (AICPZMIB.FLG == 'Y') {
            IBS.init(SCCGWA, BPRMSG);
            WS_MSG_TYPE = 'W';
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ZERO_BALANCE_EXIST;
            WS_TEMP_VARIABLE.WS_CNT = 1;
            while (WS_TEMP_VARIABLE.WS_CNT <= AICPZMIB.CNT 
                && WS_TEMP_VARIABLE.WS_CNT <= 30) {
                WS_ERROR_INFO.WS_AC_INFO[WS_TEMP_VARIABLE.WS_CNT-1].WS_AC = "AC:";
                WS_ERROR_INFO.WS_AC_INFO[WS_TEMP_VARIABLE.WS_CNT-1].WS_AC_NO = AICPZMIB.OUT_DATA.OUT_OCCURS.get(WS_TEMP_VARIABLE.WS_CNT-1).AC_NO;
                if (WS_TEMP_VARIABLE.WS_CNT == 30) {
                    WS_ERROR_INFO.WS_MORE_INFO = " AND MORE...";
                }
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_CNT);
                CEP.TRC(SCCGWA, AICPZMIB.CNT);
                WS_TEMP_VARIABLE.WS_CNT = WS_TEMP_VARIABLE.WS_CNT + 1;
            }
            WS_INFO = IBS.CLS2CPY(SCCGWA, WS_ERROR_INFO);
            S000_ERR_MSG_PROC_INFO();
            if (pgmRtn) return;
        }
    }
    public void B020_02_CHECK_ACCOUNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPGINF);
        AICPGINF.BR = BPCSTLSQ.TLR_BR;
        CEP.TRC(SCCGWA, AICPGINF.BR);
        S000_CALL_AIZPGINF();
        if (pgmRtn) return;
    }
    public void B020_05_CHECK_BV_ONWAY() throws IOException,SQLException,Exception {
        WS_INFO = " ";
        IBS.init(SCCGWA, BPCPBVBR);
        BPCPBVBR.BR = BPCSTLSQ.TLR_BR;
        S000_CALL_BPZPBVBR();
        if (pgmRtn) return;
        if (BPCPBVBR.DL_BVOW_FLG == 'Y') {
            WS_MSG_TYPE = 'W';
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_HAVE_ONWAY;
            S000_ERR_MSG_PROC_INFO();
            if (pgmRtn) return;
        }
        if (BPCPBVBR.SL_BVOW_FLG == 'Y') {
            WS_MSG_TYPE = 'W';
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_HAVE_ONWAY;
            S000_ERR_MSG_PROC_INFO();
            if (pgmRtn) return;
        }
    }
    public void B030_CHECK_CASH_ONWAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPCSBR);
        WS_INFO = " ";
        BPCPCSBR.BR = BPCSTLSQ.TLR_BR;
        S000_CALL_BPZPCSBR();
        if (pgmRtn) return;
    }
    public void B040_CHECK_IB_ACCOUNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCCASH);
        WS_INFO = " ";
        IBCCASH.POST_CTR = BPCSTLSQ.TLR_BR;
        IBCCASH.FUNC = 'C';
        S000_CALL_IBZCASH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, IBCCASH.CLOSE_FLAG);
        if (IBCCASH.CLOSE_FLAG == 'N') {
            WS_MSG_TYPE = 'W';
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_CLOSE_ERROR;
            S000_ERR_MSG_PROC_INFO();
            if (pgmRtn) return;
        }
    }
    public void B020_06_CHECK_BOXPL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFPLCK);
        BPCFPLCK.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBS.CALLCPN(SCCGWA, "BP-F-BOXPLAN-CHK", BPCFPLCK);
    }
    public void B050_CHECK_AI_GRP() throws IOException,SQLException,Exception {
        S000_CALL_AIZPQGRP();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQGRP.OUTPUT_DATA.CLOSE);
        if (AICPQGRP.OUTPUT_DATA.CLOSE == 'N') {
            WS_MSG_TYPE = 'W';
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_CLOSE_ERROR3;
            S000_ERR_MSG_PROC_INFO();
            if (pgmRtn) return;
        }
    }
    public void B070_CHECK_ZMQC() throws IOException,SQLException,Exception {
        DDCSZMQC.FR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBS.CALLCPN(SCCGWA, "DD-SVR-ZMQC", DDCSZMQC);
    }
    public void B060_CHECK_BPTCJWST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCJWST);
        BPCCJWST.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPTCJWST();
        if (pgmRtn) return;
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "GWA-AC-DATE");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        WS_TBL_FLAG = 'Y';
        B010_STARTBR_PROCESS();
        if (pgmRtn) return;
        WS_TEMP_VARIABLE.WS_CNT = 0;
        WS_CNT_I = 1;
        while (WS_TBL_FLAG != 'N' 
            && WS_CNT_I <= WK_MAX_OUTPUT 
            && SCCMPAG.FUNC != 'E') {
            B020_READNEXT_PROCESS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_TBL_FLAG);
            if (WS_TBL_FLAG == 'Y') {
                CEP.TRC(SCCGWA, BPRTLT.TLR_TYP);
                if (BPCSTLSQ.SIGN_STS == ' ') {
                    WS_TEMP_VARIABLE.WS_CNT = WS_TEMP_VARIABLE.WS_CNT + 1;
                    CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_CNT);
                    CEP.TRC(SCCGWA, "111");
                    if (WS_TEMP_VARIABLE.WS_CNT == 1) {
                        B040_01_OUT_TITLE();
                        if (pgmRtn) return;
                    }
                    B040_02_OUT_BRW_DATA();
                    if (pgmRtn) return;
                    WS_CNT_I += 1;
                } else {
                    CEP.TRC(SCCGWA, "222");
                    CEP.TRC(SCCGWA, BPRTLT.TLR_TYP);
                    if (BPRTLT.NEW_BR == BPCSTLSQ.TLR_BR 
                        && BPRTLT.SIGN_STS == BPCSTLSQ.SIGN_STS) {
                        WS_TEMP_VARIABLE.WS_CNT = WS_TEMP_VARIABLE.WS_CNT + 1;
                        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_CNT);
                        if (WS_TEMP_VARIABLE.WS_CNT == 1) {
                            B040_01_OUT_TITLE();
                            if (pgmRtn) return;
                        }
                        CEP.TRC(SCCGWA, "OUT-RECORD");
                        CEP.TRC(SCCGWA, BPCSTLSQ.SIGN_STS);
                        CEP.TRC(SCCGWA, BPRTLT.KEY.TLR);
                        CEP.TRC(SCCGWA, BPRTLT.SIGN_STS);
                        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                            B040_02_OUT_BRW_DATA_CN();
                            if (pgmRtn) return;
                        } else {
                            B040_02_OUT_BRW_DATA();
                            if (pgmRtn) return;
                        }
                        WS_CNT_I += 1;
                    }
                }
            }
            CEP.TRC(SCCGWA, WS_CNT_I);
        }
        B030_ENDBR_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_CNT);
        BPCSTLSQ.CNT = WS_TEMP_VARIABLE.WS_CNT;
        if (WS_CNT_I > WK_MAX_OUTPUT) {
            WS_ERR_MSG = SCCCTLM_MSG.SC_ERR_ROW_LIMIT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_STARTBR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        CEP.TRC(SCCGWA, BPCSTLSQ.TLR_BR);
        BPRTLT.NEW_BR = BPCSTLSQ.TLR_BR;
        BPRTLT.SIGN_STS = BPCSTLSQ.SIGN_STS;
        CEP.TRC(SCCGWA, BPRTLT.SIGN_STS);
        if (BPCSTLSQ.SIGN_STS == ' ') {
            BPCRTLTB.INFO.FUNC = 'I';
        } else {
            BPCRTLTB.INFO.FUNC = 'S';
        }
        BPCRTLTB.INFO.LEN = 1404;
        BPCRTLTB.INFO.POINTER = BPRTLT;
        S000_CALL_BPZRTLTB();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_PROCESS() throws IOException,SQLException,Exception {
        BPCRTLTB.INFO.FUNC = 'N';
        BPCRTLTB.INFO.LEN = 1404;
        BPCRTLTB.INFO.POINTER = BPRTLT;
        S000_CALL_BPZRTLTB();
        if (pgmRtn) return;
        if (BPCRTLTB.RETURN_INFO == 'F') {
            WS_TBL_FLAG = 'Y';
        } else if (BPCRTLTB.RETURN_INFO == 'N') {
            WS_TBL_FLAG = 'N';
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_ENDBR_PROCESS() throws IOException,SQLException,Exception {
        BPCRTLTB.INFO.FUNC = 'E';
        BPCRTLTB.INFO.LEN = 1404;
        BPCRTLTB.INFO.POINTER = BPRTLT;
        S000_CALL_BPZRTLTB();
        if (pgmRtn) return;
        if (BPCRTLTB.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 145;
        SCCMPAG.SCR_ROW_CNT = 50;
        SCCMPAG.SCR_COL_CNT = 6;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTLSQ);
        BPCOTLSQ.TLR = BPRTLT.KEY.TLR;
        BPCOTLSQ.TLR_BR = BPRTLT.NEW_BR;
        BPCOTLSQ.TLR_CN_NM = BPRTLT.TLR_CN_NM;
        BPCOTLSQ.TLR_TYP = BPRTLT.TLR_TYP;
        BPCOTLSQ.TLR_LVL = BPRTLT.TLR_LVL;
        BPCOTLSQ.TLR_STS = BPRTLT.TLR_STS;
        BPCOTLSQ.SIGN_STS = BPRTLT.SIGN_STS;
        if (BPRTLT.SIGN_STS == 'T' 
            || BPRTLT.SIGN_STS == 'C') {
            IBS.init(SCCGWA, BPCRHIST);
            if (BPRTLT.SIGN_STS == 'T') {
                BPCRHIST.FUNC = 'T';
            } else {
                BPCRHIST.FUNC = 'C';
            }
            BPCRHIST.TLR = BPRTLT.KEY.TLR;
            CEP.TRC(SCCGWA, BPRTLT.KEY.TLR);
            CEP.TRC(SCCGWA, BPRTLT.SIGN_STS);
            CEP.TRC(SCCGWA, BPCOTLSQ.SIGN_OFF_TM);
            CEP.TRC(SCCGWA, BPCRHIST.INFO[1-1].AC_DT);
            CEP.TRC(SCCGWA, BPCRHIST.INFO[1-1].TX_DT);
            CEP.TRC(SCCGWA, BPCRHIST.INFO[1-1].TX_TM);
            CEP.TRC(SCCGWA, BPCRHIST.INFO[1-1].RMK);
            if (BPCRHIST.INFO[1-1].AC_DT != SCCGWA.COMM_AREA.AC_DATE) {
            } else {
                BPCOTLSQ.SIGN_OFF_TM = BPCRHIST.INFO[1-1].TX_TM;
            }
        }
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOTLSQ);
        SCCMPAG.DATA_LEN = 145;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_02_OUT_BRW_DATA_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTLSQ);
        BPCOTLSQ.TLR = BPRTLT.KEY.TLR;
        BPCOTLSQ.TLR_BR = BPRTLT.NEW_BR;
        BPCOTLSQ.TLR_CN_NM = BPRTLT.TLR_CN_NM;
        BPCOTLSQ.TLR_TYP = BPRTLT.TLR_TYP;
        BPCOTLSQ.TLR_LVL = BPRTLT.TLR_LVL;
        BPCOTLSQ.TLR_STS = BPRTLT.TLR_STS;
        BPCOTLSQ.SIGN_STS = BPRTLT.SIGN_STS;
        CEP.TRC(SCCGWA, BPRTLT.KEY.TLR);
        CEP.TRC(SCCGWA, BPRTLT.NEW_BR);
        CEP.TRC(SCCGWA, BPRTLT.TLR_CN_NM);
        CEP.TRC(SCCGWA, BPRTLT.TLR_TYP);
        CEP.TRC(SCCGWA, BPRTLT.TLR_LVL);
        CEP.TRC(SCCGWA, BPRTLT.TLR_STS);
        CEP.TRC(SCCGWA, BPRTLT.SIGN_STS);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOTLSQ);
        SCCMPAG.DATA_LEN = 145;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRTLTB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLT, BPCRTLTB);
        if (BPCRTLTB.RC.RC_CODE != 0) {
            WS_ERR_MSG = "" + BPCRTLTB.RC.RC_CODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRHIST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_TLR_SIGN_HIS, BPCRHIST);
        CEP.TRC(SCCGWA, BPCRHIST.RC);
        if (BPCRHIST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRHIST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPZMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ZERO-MIB", AICPZMIB);
    }
    public void S000_CALL_AIZPGINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-CHK-GRVS-EXP", AICPGINF);
        CEP.TRC(SCCGWA, AICPGINF.RC.RC_CODE);
        if (AICPGINF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCSTLSQ.CHK_FLG);
            if (BPCSTLSQ.CHK_FLG == 'Y') {
                WS_MSG_TYPE = 'W';
                WS_LVL.WS_LVL1 = 0;
                WS_LVL.WS_LVL2 = 0;
            } else {
                if (BPCSTLSQ.CHK_FLG == 'N') {
                } else {
                    WS_MSG_TYPE = 'A';
                    WS_LVL.WS_LVL1 = 8;
                    WS_LVL.WS_LVL2 = 8;
                }
            }
            CEP.TRC(SCCGWA, WS_MSG_TYPE);
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_GRVS_EXP_EXIST;
            CEP.TRC(SCCGWA, WS_ERR_MSG);
            WS_INFO = "GINF IS OVER DATE";
            S000_ERR_MSG_PROC_INFO_01();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCSBR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-BR-CS-CHK", BPCPCSBR);
        if (BPCPCSBR.RC.RC_CODE != 0) {
            WS_MSG_TYPE = 'W';
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPCSBR.RC);
            S000_ERR_MSG_PROC_INFO();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPBVBR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-TLR-CSCHK", BPCPBVBR);
        if (BPCPBVBR.RC.RC_CODE != 0) {
            WS_MSG_TYPE = 'W';
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPBVBR.RC);
            S000_ERR_MSG_PROC_INFO();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZCASH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-CORP-CASH-PROC", IBCCASH);
    }
    public void S000_CALL_AIZPQGRP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-INQ-GRP-CLOSE", AICPQGRP);
    }
    public void S000_CALL_BPTCJWST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-ACQ-CHK", BPCCJWST);
    }
    public void T000_GROUP_BPTTLT_BR() throws IOException,SQLException,Exception {
        BPTTLT_RD = new DBParm();
        BPTTLT_RD.TableName = "BPTTLT";
        BPTTLT_RD.set = "WS-COUNT=COUNT(*)";
        BPTTLT_RD.where = "TLR_BR = :BPRTLT.NEW_BR "
            + "AND SIGN_STS = 'O' "
            + "AND TLR_TYP < > 'S' "
            + "AND TLR < > :BPRTLT.KEY.TLR";
        IBS.GROUP(SCCGWA, BPRTLT, this, BPTTLT_RD);
    }
    public void T001_GROUP_BPTTLT_BR() throws IOException,SQLException,Exception {
        BPTTLT_RD = new DBParm();
        BPTTLT_RD.TableName = "BPTTLT";
        BPTTLT_RD.set = "WS-COUNT1=COUNT(*)";
        BPTTLT_RD.where = "TLR_BR = :BPRTLT.NEW_BR "
            + "AND SIGN_STS = 'T' "
            + "AND TLR_TYP < > 'S' "
            + "AND TLR < > :BPRTLT.KEY.TLR";
        IBS.GROUP(SCCGWA, BPRTLT, this, BPTTLT_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_INFO() throws IOException,SQLException,Exception {
        SCCMSG SCCMSG = new SCCMSG();
        IBS.init(SCCGWA, SCCMSG);
        SCCMSG.MSGID = WS_ERR_MSG;
        SCCMSG.TYPE = WS_MSG_TYPE;
        SCCMSG.INFO = WS_INFO;
        CEP.ERR(SCCGWA, SCCMSG);
    }
    public void S000_ERR_MSG_PROC_INFO_01() throws IOException,SQLException,Exception {
        SCCMSG SCCMSG = new SCCMSG();
        IBS.init(SCCGWA, SCCMSG);
        SCCMSG.MSGID = WS_ERR_MSG;
        SCCMSG.TYPE = WS_MSG_TYPE;
        SCCMSG.LVL.LVL1 = (char) WS_LVL.WS_LVL1;
        SCCMSG.LVL.LVL2 = (char) WS_LVL.WS_LVL2;
        SCCMSG.INFO = WS_INFO;
        CEP.ERR(SCCGWA, SCCMSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
