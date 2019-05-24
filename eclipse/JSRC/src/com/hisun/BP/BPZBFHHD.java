package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRCWAT;
import com.hisun.SC.SCRJPRM;

public class BPZBFHHD {
    boolean pgmRtn = false;
    String PROD_CD_01 = "RDP00002";
    String PROD_CD_02 = "RDP00495";
    String WS_ERR_MSG = " ";
    int WS_LENGTH = 0;
    int WS_RCD_SEQ = 0;
    int WS_AC_DT = 0;
    char WS_PROD_CD_FLG = ' ';
    char WS_HIS_FLG = ' ';
    char WS_ALARDY_COUNT = 'N';
    int WS_COUNT_LAST_NO = 0;
    int WS_COUNT_HIS_NO = 0;
    char WS_BFHHD_BROWS_COND = ' ';
    int WS_BFHHD_FETCH_NO = 0;
    char WS_HOT_END_FLG = ' ';
    char WS_HOD_HIS_FLG = ' ';
    char WS_HOT_READ_FLG = ' ';
    char WS_HIS_READ_FLG = ' ';
    char WS_END_HT1_BR_FLG = ' ';
    char WS_END_HHD_BR_FLG = ' ';
    char WS_BFHT1_BRW_FLG = ' ';
    char WS_BFHHD_BRW_FLG = ' ';
    short WS_MAX_JRN_SEQ = 0;
    int WS_STR_DATE = 0;
    int WS_END_DATE = 0;
    int WS_STR_TR_DATE = 0;
    int WS_END_TR_DATE = 0;
    int WS_STR_TR_TM = 0;
    int WS_END_TR_TM = 0;
    double WS_STR_AMT = 0;
    double WS_END_AMT = 0;
    String WS_AC = " ";
    String WS_TX_CCY = " ";
    String WS_REF_NO = " ";
    long WS_JRNNO = 0;
    String WS_TX_TOOL = " ";
    String WS_TX_TELR = " ";
    String WS_TX_CD = " ";
    String WS_CI_NO = " ";
    int WS_TX_BR = 0;
    short WS_TX_DP = 0;
    char WS_TX_DC = ' ';
    String WS_MAKER_ID = " ";
    String WS_CHECKER_ID = " ";
    int WS_COUNT_NO = 0;
    String WS_PROD_CD = " ";
    String WS_PROD_CD_01 = " ";
    String WS_PROD_CD_02 = " ";
    char WS_BRW_FHIST_FLAG = ' ';
    char WS_LAST_FUNC = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCBFHTD BPCBFHT1 = new BPCBFHTD();
    SCCCALL SCCCALL = new SCCCALL();
    SCCJPMR SCCJPMR = new SCCJPMR();
    SCRJPRM SCRJPRM = new SCRJPRM();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPRFHIS BPRFHIS1 = new BPRFHIS();
    BPRFHIS BPRFHIS2 = new BPRFHIS();
    SCCGWA SCCGWA;
    BPCBFHHD BPCBFHHD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCRCWAT SCRCWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPRFHIST BPRFHISZ;
    public void MP(SCCGWA SCCGWA, BPCBFHHD BPCBFHHD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCBFHHD = BPCBFHHD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZBFHHD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRFHISZ = (BPRFHIST) BPCBFHHD.INPUT.REC_PT;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        S000_CALL_SCZJPMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCRJPRM.OBLIGATE_FILL);
        if (SCRJPRM.OBLIGATE_FILL == null) SCRJPRM.OBLIGATE_FILL = "";
        JIBS_tmp_int = SCRJPRM.OBLIGATE_FILL.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) SCRJPRM.OBLIGATE_FILL += " ";
        WS_HIS_FLG = SCRJPRM.OBLIGATE_FILL.substring(7 - 1, 7 + 1 - 1).charAt(0);
        CEP.TRC(SCCGWA, WS_HIS_FLG);
        IBS.init(SCCGWA, BPRFHIST);
        CEP.TRC(SCCGWA, WS_ALARDY_COUNT);
        CEP.TRC(SCCGWA, WS_HIS_FLG);
        if (WS_ALARDY_COUNT != 'Y') {
            CEP.TRC(SCCGWA, "FIRST COME IN");
        }
        WS_LENGTH = 690;
        CEP.TRC(SCCGWA, WS_LENGTH);
        if (WS_LENGTH == BPCBFHHD.INPUT.REC_LEN) {
            CEP.TRC(SCCGWA, "WS-LENGTH = BFHHD-REC-LEN");
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRFHISZ);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRFHISZ, BPRFHIST);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCBFHHD.OUTPUT.RC);
    }
    public void S000_CALL_SCZJPMR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCJPMR);
        IBS.init(SCCGWA, SCRJPRM);
        SCCJPMR.DAT_PTR = SCRJPRM;
        IBS.CALLCPN(SCCGWA, "SC-JPRM-RD", SCCJPMR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCBFHHD.INPUT.FUNC != '4' 
            && BPCBFHHD.INPUT.FUNC != '5') {
            B005_CHECK_INPUT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.FUNC);
        if (BPCBFHHD.INPUT.FUNC == '1') {
            B010_STARTBR_PROC();
            if (pgmRtn) return;
        } else if (BPCBFHHD.INPUT.FUNC == '2') {
            B020_READNEXT_PROC();
            if (pgmRtn) return;
        } else if (BPCBFHHD.INPUT.FUNC == '3') {
            B030_ENDBR_PROC();
            if (pgmRtn) return;
        } else if (BPCBFHHD.INPUT.FUNC == '4') {
            B040_GROUPMAX_PROC();
            if (pgmRtn) return;
        } else if (BPCBFHHD.INPUT.FUNC == '5') {
            B050_READ_PROC();
            if (pgmRtn) return;
        } else if (BPCBFHHD.INPUT.FUNC == '7') {
            B010_STARTBR_STATEMENT_PROC();
            if (pgmRtn) return;
        } else if (BPCBFHHD.INPUT.FUNC == '8') {
            B080_COUNT_PROC();
            if (pgmRtn) return;
        } else if (BPCBFHHD.INPUT.FUNC == '9') {
            B090_FIRST_READNEXT_CN();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERROR, BPCBFHHD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "BPZBFHHD INVALID FUNC(" + BPCBFHHD.INPUT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.REC_LEN);
        WS_LENGTH = 690;
        CEP.TRC(SCCGWA, WS_LENGTH);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRFHIST);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRFHIST, BPRFHISZ);
    }
    public void B005_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_STR_DATE = BPCBFHHD.INPUT.STR_AC_DT;
        CEP.TRC(SCCGWA, WS_STR_DATE);
        WS_END_DATE = BPCBFHHD.INPUT.END_AC_DT;
        CEP.TRC(SCCGWA, WS_END_DATE);
        WS_STR_TR_DATE = BPCBFHHD.INPUT.STR_TR_DT;
        CEP.TRC(SCCGWA, WS_STR_TR_DATE);
        WS_END_TR_DATE = BPCBFHHD.INPUT.END_TR_DT;
        CEP.TRC(SCCGWA, WS_END_TR_DATE);
        WS_END_AMT = BPCBFHHD.INPUT.END_AMT;
        CEP.TRC(SCCGWA, WS_END_AMT);
        WS_STR_AMT = BPCBFHHD.INPUT.STR_AMT;
        CEP.TRC(SCCGWA, WS_STR_AMT);
        WS_END_TR_TM = BPCBFHHD.INPUT.END_TR_TM;
        CEP.TRC(SCCGWA, WS_END_TR_TM);
        WS_STR_TR_TM = BPCBFHHD.INPUT.STR_TR_TM;
        CEP.TRC(SCCGWA, WS_STR_TR_TM);
        if (WS_STR_DATE > WS_END_DATE 
            || WS_STR_TR_DATE > WS_END_TR_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_STR_DT_GT_END_DT, BPCBFHHD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_STR_TR_TM > WS_END_TR_TM) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_STR_END_TIME_ERR, BPCBFHHD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCBFHHD.INPUT.SORT_FLG == ' ') {
            BPCBFHHD.INPUT.SORT_FLG = 'N';
        }
        if (BPCBFHHD.INPUT.PROD_CD.equalsIgnoreCase("99999999")) {
            WS_PROD_CD_FLG = 'Y';
            WS_PROD_CD_01 = PROD_CD_01;
            WS_PROD_CD_02 = PROD_CD_02;
        } else {
            WS_PROD_CD_FLG = 'N';
        }
        WS_AC = BPCBFHHD.INPUT.AC;
        WS_TX_CCY = BPCBFHHD.INPUT.TX_CCY;
        WS_REF_NO = BPCBFHHD.INPUT.REF_NO;
        WS_JRNNO = BPCBFHHD.INPUT.JRNNO;
        WS_TX_TOOL = BPCBFHHD.INPUT.TX_TOOL;
        WS_TX_TELR = BPCBFHHD.INPUT.TX_TLR;
        WS_TX_CD = BPCBFHHD.INPUT.TX_CD;
        WS_CI_NO = BPCBFHHD.INPUT.CI_NO;
        WS_TX_BR = BPCBFHHD.INPUT.TX_BR;
        WS_TX_DP = BPCBFHHD.INPUT.TX_DP;
        WS_TX_DC = BPCBFHHD.INPUT.DC_FLG;
        WS_MAKER_ID = BPCBFHHD.INPUT.MAKER_ID;
        WS_CHECKER_ID = BPCBFHHD.INPUT.CHECKER_ID;
        WS_PROD_CD = BPCBFHHD.INPUT.PROD_CD;
        CEP.TRC(SCCGWA, WS_JRNNO);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.AC);
        CEP.TRC(SCCGWA, WS_AC);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.TX_CCY);
        CEP.TRC(SCCGWA, WS_TX_CCY);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.REF_NO);
        CEP.TRC(SCCGWA, WS_REF_NO);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.JRNNO);
        CEP.TRC(SCCGWA, WS_JRNNO);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.TX_TOOL);
        CEP.TRC(SCCGWA, WS_TX_TOOL);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.TX_TLR);
        CEP.TRC(SCCGWA, WS_TX_TELR);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.TX_CD);
        CEP.TRC(SCCGWA, WS_TX_CD);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.CI_NO);
        CEP.TRC(SCCGWA, WS_CI_NO);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.TX_BR);
        CEP.TRC(SCCGWA, WS_TX_BR);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.TX_DP);
        CEP.TRC(SCCGWA, WS_TX_DP);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.DC_FLG);
        CEP.TRC(SCCGWA, WS_MAKER_ID);
        CEP.TRC(SCCGWA, WS_CHECKER_ID);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.SORT_FLG);
        CEP.TRC(SCCGWA, WS_STR_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
    }
    public void B080_COUNT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_HIS_FLG);
        if (WS_HIS_FLG == '1' 
            && BPCBFHHD.INPUT.END_AC_DT >= SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE 
            && BPCBFHHD.INPUT.STR_AC_DT <= SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE) {
            B083_GET_LAST_DT_COUNT();
            if (pgmRtn) return;
            WS_AC_DT = SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE;
            R000_GET_LAST_AC_DT_CN();
            if (pgmRtn) return;
            BPCBFHT1.INPUT.END_AC_DT = BPCOCLWD.DATE2;
            WS_END_DATE = BPCOCLWD.DATE2;
            B110_GET_REC_NUM();
            if (pgmRtn) return;
        } else {
            WS_END_DATE = BPCBFHHD.INPUT.END_AC_DT;
            B110_GET_REC_NUM();
            if (pgmRtn) return;
        }
    }
    public void B083_GET_LAST_DT_COUNT() throws IOException,SQLException,Exception {
        WS_LAST_FUNC = '8';
        B090_CALL_BPZBFHT1_OR_BPZBFHT2();
        if (pgmRtn) return;
        WS_COUNT_LAST_NO = BPCBFHT1.OUTPUT.COUNT_NO;
        CEP.TRC(SCCGWA, WS_COUNT_LAST_NO);
        WS_ALARDY_COUNT = 'Y';
    }
    public void B085_STARTBR_LAST_DT_PROC() throws IOException,SQLException,Exception {
        WS_LAST_FUNC = '1';
        WS_BFHT1_BRW_FLG = 'Y';
        B090_CALL_BPZBFHT1_OR_BPZBFHT2();
        if (pgmRtn) return;
    }
    public void B087_READNEXT_LAST_DT_PROC() throws IOException,SQLException,Exception {
        WS_LAST_FUNC = '2';
        B090_CALL_BPZBFHT1_OR_BPZBFHT2();
        if (pgmRtn) return;
    }
    public void B089_ENDBR_LAST_DT_PROC() throws IOException,SQLException,Exception {
        WS_LAST_FUNC = '3';
        WS_END_HT1_BR_FLG = 'Y';
        B090_CALL_BPZBFHT1_OR_BPZBFHT2();
        if (pgmRtn) return;
    }
    public void B090_CALL_BPZBFHT1_OR_BPZBFHT2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCBFHT1);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.SORT_FLG);
        B130_TRANS_DATA_MOVE();
        if (pgmRtn) return;
        BPCBFHT1.INPUT.SORT_FLG = BPCBFHHD.INPUT.SORT_FLG;
        CEP.TRC(SCCGWA, BPCBFHT1.INPUT.SORT_FLG);
        if (WS_LAST_FUNC != ' ') {
            BPCBFHT1.INPUT.FUNC = WS_LAST_FUNC;
        }
        BPCBFHT1.INPUT.END_AC_DT = SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE;
        BPCBFHT1.INPUT.STR_AC_DT = SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE;
        BPCBFHT1.INPUT.REC_PT = BPRFHIST;
        BPCBFHT1.INPUT.REC_LEN = 690;
        S000_CALL_BPZBFHT1();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCBFHT1.OUTPUT.RC.RC_CODE);
        if (BPCBFHT1.OUTPUT.RC.RC_CODE != 0) {
            WS_HOT_END_FLG = 'Y';
            B150_IF_READ_HIS_TABLE();
            if (pgmRtn) return;
        }
    }
    public void B010_STARTBR_PROC() throws IOException,SQLException,Exception {
        WS_BFHHD_BROWS_COND = BPCBFHHD.INPUT.BROWS_COND;
        CEP.TRC(SCCGWA, WS_ALARDY_COUNT);
        CEP.TRC(SCCGWA, WS_HIS_FLG);
        if ((WS_ALARDY_COUNT != 'Y') 
            && WS_HIS_FLG == '1') {
            B080_COUNT_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.FETCH_NO);
        CEP.TRC(SCCGWA, WS_COUNT_LAST_NO);
        if (WS_HIS_FLG == '1' 
            && WS_COUNT_LAST_NO > 0 
            && BPCBFHHD.INPUT.END_AC_DT >= SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE 
            && BPCBFHHD.INPUT.STR_AC_DT <= SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE) {
            if (BPCBFHHD.INPUT.SORT_FLG == 'Y') {
                B085_STARTBR_LAST_DT_PROC();
                if (pgmRtn) return;
            }
            if (BPCBFHHD.INPUT.SORT_FLG == 'N') {
                WS_AC_DT = SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE;
                R000_GET_LAST_AC_DT_CN();
                if (pgmRtn) return;
                WS_END_DATE = BPCOCLWD.DATE2;
                B011_STARTBR_HIS_PROC();
                if (pgmRtn) return;
            }
        } else {
            B011_STARTBR_HIS_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_READNEXT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BFHHD_FETCH_NO);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.END_AC_DT);
        if (WS_HIS_FLG == '1' 
            && BPCBFHHD.INPUT.END_AC_DT >= SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE 
            && BPCBFHHD.INPUT.STR_AC_DT <= SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE 
            && WS_COUNT_LAST_NO > 0) {
            if (BPCBFHHD.INPUT.SORT_FLG == 'Y') {
                if (WS_HOT_END_FLG != 'Y') {
                    if (WS_BFHHD_FETCH_NO <= WS_COUNT_LAST_NO) {
                        B087_READNEXT_LAST_DT_PROC();
                        if (pgmRtn) return;
                    } else {
                        WS_HOT_END_FLG = 'Y';
                        if (WS_BFHT1_BRW_FLG == 'Y' 
                            && WS_END_HT1_BR_FLG != 'Y') {
                            BPCBFHHD.INPUT.FUNC = '3';
                            B089_ENDBR_LAST_DT_PROC();
                            if (pgmRtn) return;
                        }
                    }
                }
                if (WS_HOT_END_FLG == 'Y') {
                    if (WS_HIS_READ_FLG != 'Y') {
                        CEP.TRC(SCCGWA, "FIRST-READ-HIS-2");
                        WS_AC_DT = SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE;
                        R000_GET_LAST_AC_DT_CN();
                        if (pgmRtn) return;
                        WS_END_DATE = BPCOCLWD.DATE2;
                        BPCBFHHD.INPUT.BROWS_COND = WS_BFHHD_BROWS_COND;
                        B011_STARTBR_HIS_PROC();
                        if (pgmRtn) return;
                        B023_READNEXT_HIS_PROC();
                        if (pgmRtn) return;
                        WS_HIS_READ_FLG = 'Y';
                    } else {
                        CEP.TRC(SCCGWA, "B023-READNEXT-1");
                        B023_READNEXT_HIS_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            if (BPCBFHHD.INPUT.SORT_FLG == 'N') {
                if (WS_HOD_HIS_FLG != 'Y') {
                    if (WS_BFHHD_FETCH_NO <= WS_COUNT_HIS_NO) {
                        B023_READNEXT_HIS_PROC();
                        if (pgmRtn) return;
                    } else {
                        WS_HOD_HIS_FLG = 'Y';
                        if (WS_BFHHD_BRW_FLG == 'Y' 
                            && WS_END_HHD_BR_FLG != 'Y') {
                            T000_ENDBR_BPTFHIST();
                            if (pgmRtn) return;
                        }
                    }
                }
                if (WS_HOD_HIS_FLG == 'Y') {
                    if (WS_HOT_READ_FLG != 'Y') {
                        CEP.TRC(SCCGWA, "FIRST-READ-HOT-2");
                        CEP.TRC(SCCGWA, WS_BFHHD_BROWS_COND);
                        BPCBFHHD.INPUT.BROWS_COND = WS_BFHHD_BROWS_COND;
                        B085_STARTBR_LAST_DT_PROC();
                        if (pgmRtn) return;
                        B087_READNEXT_LAST_DT_PROC();
                        if (pgmRtn) return;
                        WS_HOT_READ_FLG = 'Y';
                    } else {
                        B087_READNEXT_LAST_DT_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        } else {
            CEP.TRC(SCCGWA, "COMES-TO-B023-READNEXT");
            B023_READNEXT_HIS_PROC();
            if (pgmRtn) return;
        }
    }
    public void B011_STARTBR_HIS_PROC() throws IOException,SQLException,Exception {
        WS_BRW_FHIST_FLAG = 'N';
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.AC);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.TX_CCY);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.REF_NO);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.JRNNO);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.TX_TLR);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.TX_CD);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.TX_TOOL);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.CI_NO);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.DC_FLG);
        if (BPCBFHHD.INPUT.SORT_FLG != 'Y') {
            BPCBFHHD.INPUT.SORT_FLG = 'N';
        }
        CEP.TRC(SCCGWA, WS_BFHHD_BROWS_COND);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.BROWS_COND);
        if (BPCBFHHD.INPUT.BROWS_COND == '1') {
            T000_STARTBR_JRN();
            if (pgmRtn) return;
        } else if (BPCBFHHD.INPUT.BROWS_COND == '3') {
            T000_STARTBR_ACBRTLR();
            if (pgmRtn) return;
        } else if (BPCBFHHD.INPUT.BROWS_COND == '4') {
            T000_STARTBR_TOOL_BRTLR();
            if (pgmRtn) return;
        } else if (BPCBFHHD.INPUT.BROWS_COND == '5') {
            T000_STARTBR_BRTLR();
            if (pgmRtn) return;
        } else if (BPCBFHHD.INPUT.BROWS_COND == '2') {
            T000_STARTBR_AC();
            if (pgmRtn) return;
        } else if (BPCBFHHD.INPUT.BROWS_COND == '7') {
            T000_STARTBR_AC_PROD();
            if (pgmRtn) return;
        } else if (BPCBFHHD.INPUT.BROWS_COND == '8') {
            T000_STARTBR_TOOL_PROD();
            if (pgmRtn) return;
        } else if (BPCBFHHD.INPUT.BROWS_COND == '9') {
            T000_STARTBR_AC_JUMP();
            if (pgmRtn) return;
        } else if (BPCBFHHD.INPUT.BROWS_COND == 'A') {
            T000_STARTBR_JRN_ALL();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NONE_COND, BPCBFHHD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "BPZBFHHD INVALID FUNC(" + BPCBFHHD.INPUT.BROWS_COND + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        WS_BFHHD_BRW_FLG = 'Y';
        CEP.TRC(SCCGWA, "WS-END-DATE IS");
        CEP.TRC(SCCGWA, WS_END_DATE);
        if (WS_BRW_FHIST_FLAG == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCBFHHD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B110_GET_REC_NUM() throws IOException,SQLException,Exception {
        WS_BRW_FHIST_FLAG = 'N';
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.AC);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.TX_CCY);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.REF_NO);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.JRNNO);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.TX_TLR);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.TX_CD);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.TX_TOOL);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.CI_NO);
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.DC_FLG);
        if (BPCBFHHD.INPUT.BROWS_COND == '2') {
            T000_COUNT_AC();
            if (pgmRtn) return;
        } else if (BPCBFHHD.INPUT.BROWS_COND == '3') {
            T000_COUNT_ACBRTLR();
            if (pgmRtn) return;
        } else if (BPCBFHHD.INPUT.BROWS_COND == '4') {
            T000_COUNT_TOOL_BRTLR();
            if (pgmRtn) return;
        } else if (BPCBFHHD.INPUT.BROWS_COND == '5') {
            T000_COUNT_BRTLR();
            if (pgmRtn) return;
        } else if (BPCBFHHD.INPUT.BROWS_COND == '1') {
            T000_COUNT_JRN();
            if (pgmRtn) return;
        } else if (BPCBFHHD.INPUT.BROWS_COND == '7') {
            T000_COUNT_AC_PROD();
            if (pgmRtn) return;
        } else if (BPCBFHHD.INPUT.BROWS_COND == '8') {
            T000_COUNT_TOOL_PROD();
            if (pgmRtn) return;
        } else if (BPCBFHHD.INPUT.BROWS_COND == '9') {
            T000_COUNT_AC_JUMP();
            if (pgmRtn) return;
        } else if (BPCBFHHD.INPUT.BROWS_COND == 'A') {
            T000_COUNT_JRNNO_ALL();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERROR, BPCBFHHD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "BPZBFHHD INVALID FUNC(" + BPCBFHHD.INPUT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        CEP.TRC(SCCGWA, WS_COUNT_NO);
        if (WS_BRW_FHIST_FLAG == 'N') {
            CEP.TRC(SCCGWA, "HD00000000");
            BPCBFHHD.OUTPUT.COUNT_NO = 0;
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCBFHHD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            if (WS_HIS_FLG == '1' 
                && BPCBFHHD.INPUT.END_AC_DT >= SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE 
                && BPCBFHHD.INPUT.STR_AC_DT <= SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE) {
                WS_COUNT_HIS_NO = WS_COUNT_NO;
                BPCBFHHD.OUTPUT.COUNT_NO = WS_COUNT_LAST_NO + WS_COUNT_HIS_NO;
            } else {
                BPCBFHHD.OUTPUT.COUNT_NO = WS_COUNT_NO;
            }
        }
        CEP.TRC(SCCGWA, BPCBFHHD.OUTPUT.COUNT_NO);
    }
    public void B120_FIRST_READ_BPZBFHT2() throws IOException,SQLException,Exception {
        if (BPCBFHHD.INPUT.FETCH_NO > 0) {
            WS_RCD_SEQ = BPCBFHHD.INPUT.FETCH_NO;
            T000_READNEXT_FIRST_BPTFHIST();
            if (pgmRtn) return;
        } else {
            T000_READNEXT_BPTFHIST_CN();
            if (pgmRtn) return;
        }
        WS_HIS_READ_FLG = 'Y';
    }
    public void B130_TRANS_DATA_MOVE() throws IOException,SQLException,Exception {
        BPCBFHT1.INPUT.FUNC = BPCBFHHD.INPUT.FUNC;
        BPCBFHT1.INPUT.BROWS_COND = BPCBFHHD.INPUT.BROWS_COND;
        BPCBFHT1.INPUT.JRNNO = BPCBFHHD.INPUT.JRNNO;
        BPCBFHT1.INPUT.CI_NO = BPCBFHHD.INPUT.CI_NO;
        BPCBFHT1.INPUT.AC = BPCBFHHD.INPUT.AC;
        BPCBFHT1.INPUT.REF_NO = BPCBFHHD.INPUT.REF_NO;
        BPCBFHT1.INPUT.TX_TOOL = BPCBFHHD.INPUT.TX_TOOL;
        BPCBFHT1.INPUT.TX_CCY = BPCBFHHD.INPUT.TX_CCY;
        BPCBFHT1.INPUT.TX_CD = BPCBFHHD.INPUT.TX_CD;
        BPCBFHT1.INPUT.TX_TLR = BPCBFHHD.INPUT.TX_TLR;
        BPCBFHT1.INPUT.STR_AC_DT = BPCBFHHD.INPUT.STR_AC_DT;
        BPCBFHT1.INPUT.END_AC_DT = BPCBFHHD.INPUT.END_AC_DT;
        BPCBFHT1.INPUT.STR_TR_DT = BPCBFHHD.INPUT.STR_TR_DT;
        BPCBFHT1.INPUT.END_TR_DT = BPCBFHHD.INPUT.END_TR_DT;
        BPCBFHT1.INPUT.STR_TR_TM = BPCBFHHD.INPUT.STR_TR_TM;
        BPCBFHT1.INPUT.END_TR_TM = BPCBFHHD.INPUT.END_TR_TM;
        BPCBFHT1.INPUT.STR_AMT = BPCBFHHD.INPUT.STR_AMT;
        BPCBFHT1.INPUT.END_AMT = BPCBFHHD.INPUT.END_AMT;
        BPCBFHT1.INPUT.TX_BR = BPCBFHHD.INPUT.TX_BR;
        BPCBFHT1.INPUT.TX_DP = BPCBFHHD.INPUT.TX_DP;
        BPCBFHT1.INPUT.DC_FLG = BPCBFHHD.INPUT.DC_FLG;
        BPCBFHT1.INPUT.MAKER_ID = BPCBFHHD.INPUT.MAKER_ID;
        BPCBFHT1.INPUT.CHECKER_ID = BPCBFHHD.INPUT.CHECKER_ID;
        BPCBFHT1.INPUT.PROD_CD = BPCBFHHD.INPUT.PROD_CD;
        BPCBFHT1.INPUT.REC_LEN = BPCBFHHD.INPUT.REC_LEN;
        BPCBFHT1.INPUT.SORT_FLG = BPCBFHHD.INPUT.SORT_FLG;
        BPCBFHT1.INPUT.FETCH_NO = BPCBFHHD.INPUT.FETCH_NO;
        BPCBFHT1.OUTPUT.RC.RC_MMO = BPCBFHHD.OUTPUT.RC.RC_MMO;
        BPCBFHT1.OUTPUT.RC.RC_CODE = BPCBFHHD.OUTPUT.RC.RC_CODE;
        BPCBFHT1.OUTPUT.MAX_JRN_SEQ = BPCBFHHD.OUTPUT.MAX_JRN_SEQ;
        BPCBFHT1.OUTPUT.COUNT_NO = BPCBFHHD.OUTPUT.COUNT_NO;
    }
    public void B140_IF_READ_HOT_TABLE() throws IOException,SQLException,Exception {
        if (WS_HIS_FLG == '1' 
            && BPCBFHHD.INPUT.END_AC_DT >= SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE 
            && BPCBFHHD.INPUT.STR_AC_DT <= SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE 
            && WS_COUNT_LAST_NO > 0 
            && BPCBFHHD.INPUT.SORT_FLG == 'N') {
            CEP.TRC(SCCGWA, "CAN-NOT-END");
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_END_OF_TABLE, BPCBFHHD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B150_IF_READ_HIS_TABLE() throws IOException,SQLException,Exception {
        if (WS_HIS_FLG == '1' 
            && BPCBFHHD.INPUT.END_AC_DT >= SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE 
            && BPCBFHHD.INPUT.STR_AC_DT <= SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE 
            && WS_COUNT_LAST_NO > 0 
            && BPCBFHHD.INPUT.SORT_FLG == 'N') {
            CEP.TRC(SCCGWA, "CAN-NOT-END-1");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_END_OF_TABLE, BPCBFHHD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_COUNT_AC() throws IOException,SQLException,Exception {
        WS_BRW_FHIST_FLAG = 'Y';
        CEP.TRC(SCCGWA, "COUNT_BY_AC");
        CEP.TRC(SCCGWA, WS_STR_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        if (WS_TX_CCY.trim().length() == 0) {
            CEP.TRC(SCCGWA, "NO CCY");
            null.set = "WS-COUNT-NO=COUNT(1)";
            BPTFHIST_RD = new DBParm();
            BPTFHIST_RD.TableName = "BPTFHIST";
            BPTFHIST_RD.where = "AC = :WS_AC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND ( ' ' = :WS_TX_DC "
                + "OR DRCRFLG = :WS_TX_DC ) "
                + "AND PRINT_FLG = 'Y'";
            IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
            CEP.TRC(SCCGWA, WS_COUNT_NO);
        } else {
            null.set = "WS-COUNT-NO=COUNT(1)";
            BPTFHIST_RD = new DBParm();
            BPTFHIST_RD.TableName = "BPTFHIST";
            BPTFHIST_RD.where = "AC = :WS_AC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND ( ' ' = :WS_TX_DC "
                + "OR DRCRFLG = :WS_TX_DC ) "
                + "AND PRINT_FLG = 'Y'";
            IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
        }
    }
    public void T000_COUNT_JRN() throws IOException,SQLException,Exception {
        WS_BRW_FHIST_FLAG = 'Y';
        CEP.TRC(SCCGWA, WS_JRNNO);
        null.set = "WS-COUNT-NO=COUNT(1)";
        BPTFHIST_RD = new DBParm();
        BPTFHIST_RD.TableName = "BPTFHIST";
        BPTFHIST_RD.where = "JRNNO = :WS_JRNNO "
            + "AND AC_DT = :WS_END_DATE "
            + "AND PRINT_FLG = 'Y'";
        IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
        CEP.TRC(SCCGWA, "CESHI");
        SCCGWA.COMM_AREA.DBIO_FLG = ' ';
        BPTFHIST_BR.rp = new DBParm();
        BPTFHIST_BR.rp.TableName = "BPTFHIST";
        BPTFHIST_BR.rp.where = "JRNNO = :WS_JRNNO "
            + "AND AC_DT = :WS_END_DATE "
            + "AND PRINT_FLG = 'Y'";
        IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_COUNT_AC_PROD() throws IOException,SQLException,Exception {
        WS_BRW_FHIST_FLAG = 'Y';
        CEP.TRC(SCCGWA, WS_AC);
        CEP.TRC(SCCGWA, WS_PROD_CD);
        if (WS_TX_CCY.trim().length() == 0) {
            if (WS_PROD_CD_FLG != 'Y') {
                null.set = "WS-COUNT-NO=COUNT(1)";
                BPTFHIST_RD = new DBParm();
                BPTFHIST_RD.TableName = "BPTFHIST";
                BPTFHIST_RD.where = "AC = :WS_AC "
                    + "AND PROD_CD = :WS_PROD_CD "
                    + "AND AC_DT >= :WS_STR_DATE "
                    + "AND AC_DT <= :WS_END_DATE "
                    + "AND PRINT_FLG = 'Y'";
                IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
            } else {
                null.set = "WS-COUNT-NO=COUNT(1)";
                BPTFHIST_RD = new DBParm();
                BPTFHIST_RD.TableName = "BPTFHIST";
                BPTFHIST_RD.where = "AC = :WS_AC "
                    + "AND ( PROD_CD = :WS_PROD_CD_01 "
                    + "OR PROD_CD = :WS_PROD_CD_02 ) "
                    + "AND AC_DT >= :WS_STR_DATE "
                    + "AND AC_DT <= :WS_END_DATE "
                    + "AND PRINT_FLG = 'Y'";
                IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
            }
        } else {
            if (WS_PROD_CD_FLG != 'Y') {
                null.set = "WS-COUNT-NO=COUNT(1)";
                BPTFHIST_RD = new DBParm();
                BPTFHIST_RD.TableName = "BPTFHIST";
                BPTFHIST_RD.where = "AC = :WS_AC "
                    + "AND TX_CCY = :WS_TX_CCY "
                    + "AND PROD_CD = :WS_PROD_CD "
                    + "AND AC_DT >= :WS_STR_DATE "
                    + "AND AC_DT <= :WS_END_DATE "
                    + "AND PRINT_FLG = 'Y'";
                IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
            } else {
                null.set = "WS-COUNT-NO=COUNT(1)";
                BPTFHIST_RD = new DBParm();
                BPTFHIST_RD.TableName = "BPTFHIST";
                BPTFHIST_RD.where = "AC = :WS_AC "
                    + "AND TX_CCY = :WS_TX_CCY "
                    + "AND ( PROD_CD = :WS_PROD_CD_01 "
                    + "OR PROD_CD = :WS_PROD_CD_02 ) "
                    + "AND AC_DT >= :WS_STR_DATE "
                    + "AND AC_DT <= :WS_END_DATE "
                    + "AND PRINT_FLG = 'Y'";
                IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
            }
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_COUNT_TOOL_PROD() throws IOException,SQLException,Exception {
        WS_BRW_FHIST_FLAG = 'Y';
        CEP.TRC(SCCGWA, WS_TX_TOOL);
        CEP.TRC(SCCGWA, WS_PROD_CD);
        if (WS_TX_CCY.trim().length() == 0) {
            if (WS_PROD_CD_FLG != 'Y') {
                null.set = "WS-COUNT-NO=COUNT(1)";
                BPTFHIST_RD = new DBParm();
                BPTFHIST_RD.TableName = "BPTFHIST";
                BPTFHIST_RD.where = "TX_TOOL = :WS_TX_TOOL "
                    + "AND PROD_CD = :WS_PROD_CD "
                    + "AND AC_DT >= :WS_STR_DATE "
                    + "AND AC_DT <= :WS_END_DATE "
                    + "AND PRINT_FLG = 'Y'";
                IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
            } else {
                null.set = "WS-COUNT-NO=COUNT(1)";
                BPTFHIST_RD = new DBParm();
                BPTFHIST_RD.TableName = "BPTFHIST";
                BPTFHIST_RD.where = "TX_TOOL = :WS_TX_TOOL "
                    + "AND ( PROD_CD = :WS_PROD_CD_01 "
                    + "OR PROD_CD = :WS_PROD_CD_02 ) "
                    + "AND AC_DT >= :WS_STR_DATE "
                    + "AND AC_DT <= :WS_END_DATE "
                    + "AND PRINT_FLG = 'Y'";
                IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
            }
        } else {
            if (WS_PROD_CD_FLG != 'Y') {
                null.set = "WS-COUNT-NO=COUNT(1)";
                BPTFHIST_RD = new DBParm();
                BPTFHIST_RD.TableName = "BPTFHIST";
                BPTFHIST_RD.where = "TX_TOOL = :WS_TX_TOOL "
                    + "AND TX_CCY = :WS_TX_CCY "
                    + "AND PROD_CD = :WS_PROD_CD "
                    + "AND AC_DT >= :WS_STR_DATE "
                    + "AND AC_DT <= :WS_END_DATE "
                    + "AND PRINT_FLG = 'Y'";
                IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
            } else {
                null.set = "WS-COUNT-NO=COUNT(1)";
                BPTFHIST_RD = new DBParm();
                BPTFHIST_RD.TableName = "BPTFHIST";
                BPTFHIST_RD.where = "TX_TOOL = :WS_TX_TOOL "
                    + "AND TX_CCY = :WS_TX_CCY "
                    + "AND ( PROD_CD = :WS_PROD_CD_01 "
                    + "OR PROD_CD = :WS_PROD_CD_02 ) "
                    + "AND AC_DT >= :WS_STR_DATE "
                    + "AND AC_DT <= :WS_END_DATE "
                    + "AND PRINT_FLG = 'Y'";
                IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
            }
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_COUNT_TLR_BR_HIS() throws IOException,SQLException,Exception {
        WS_BRW_FHIST_FLAG = 'Y';
        null.set = "WS-COUNT-NO=COUNT(1)";
        BPTFHIST_RD = new DBParm();
        BPTFHIST_RD.TableName = "BPTFHIST";
        BPTFHIST_RD.where = "AC_DT >= :WS_STR_DATE "
            + "AND AC_DT <= :WS_END_DATE "
            + "AND TX_TLR = :WS_TX_TELR "
            + "AND TX_BR = :WS_TX_BR "
            + "AND ( ' ' = :WS_TX_CCY "
            + "OR TX_CCY = :WS_TX_CCY ) "
            + "AND ( ' ' = :WS_TX_DC "
            + "OR DRCRFLG = :WS_TX_DC ) "
            + "AND PRINT_FLG = 'Y'";
        IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
    }
    public void T000_COUNT_ACBRTLR() throws IOException,SQLException,Exception {
        WS_BRW_FHIST_FLAG = 'Y';
        null.set = "WS-COUNT-NO=COUNT(1)";
        BPTFHIST_RD = new DBParm();
        BPTFHIST_RD.TableName = "BPTFHIST";
        BPTFHIST_RD.where = "AC = :WS_AC "
            + "AND TX_BR = :WS_TX_BR "
            + "AND TX_TLR = :WS_TX_TELR "
            + "AND AC_DT >= :WS_STR_DATE "
            + "AND AC_DT <= :WS_END_DATE "
            + "AND ( ' ' = :WS_TX_CCY "
            + "OR TX_CCY = :WS_TX_CCY ) "
            + "AND ( ' ' = :WS_TX_DC "
            + "OR DRCRFLG = :WS_TX_DC ) "
            + "AND PRINT_FLG = 'Y'";
        IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
    }
    public void T000_COUNT_TOOL_BRTLR() throws IOException,SQLException,Exception {
        WS_BRW_FHIST_FLAG = 'Y';
        if (WS_TX_BR != 0 
                && WS_TX_TELR.trim().length() > 0) {
            CEP.TRC(SCCGWA, "COUNT_BY_TOOL_BR_TLR");
            null.set = "WS-COUNT-NO=COUNT(1)";
            BPTFHIST_RD = new DBParm();
            BPTFHIST_RD.TableName = "BPTFHIST";
            BPTFHIST_RD.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND ( ' ' = :WS_TX_DC "
                + "OR DRCRFLG = :WS_TX_DC ) "
                + "AND ( ' ' = :WS_TX_CCY "
                + "OR TX_CCY = :WS_TX_CCY ) "
                + "AND PRINT_FLG = 'Y'";
            IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
        } else if (WS_TX_BR == 0 
                && WS_TX_TELR.trim().length() == 0) {
            CEP.TRC(SCCGWA, "COUNT_BY_TOOL");
            null.set = "WS-COUNT-NO=COUNT(1)";
            BPTFHIST_RD = new DBParm();
            BPTFHIST_RD.TableName = "BPTFHIST";
            BPTFHIST_RD.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND ( ' ' = :WS_TX_DC "
                + "OR DRCRFLG = :WS_TX_DC ) "
                + "AND ( ' ' = :WS_TX_CCY "
                + "OR TX_CCY = :WS_TX_CCY ) "
                + "AND PRINT_FLG = 'Y'";
            IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
        } else if (WS_TX_BR == 0 
                && WS_TX_TELR.trim().length() > 0) {
            CEP.TRC(SCCGWA, "COUNT_BY_TOOL_TLR");
            null.set = "WS-COUNT-NO=COUNT(1)";
            BPTFHIST_RD = new DBParm();
            BPTFHIST_RD.TableName = "BPTFHIST";
            BPTFHIST_RD.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND ( ' ' = :WS_TX_DC "
                + "OR DRCRFLG = :WS_TX_DC ) "
                + "AND ( ' ' = :WS_TX_CCY "
                + "OR TX_CCY = :WS_TX_CCY ) "
                + "AND PRINT_FLG = 'Y'";
            IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
        } else {
            WS_BRW_FHIST_FLAG = 'N';
        }
    }
    public void T000_COUNT_BRTLR() throws IOException,SQLException,Exception {
        T000_COUNT_TLR_BR_HIS();
        if (pgmRtn) return;
    }
    public void T000_COUNT_JRNNO_ALL() throws IOException,SQLException,Exception {
        WS_BRW_FHIST_FLAG = 'Y';
        CEP.TRC(SCCGWA, "COUNT_BY_JRNNO_ALL");
        CEP.TRC(SCCGWA, WS_JRNNO);
        CEP.TRC(SCCGWA, WS_END_DATE);
        null.set = "WS-COUNT-NO=COUNT(1)";
        BPTFHIST_RD = new DBParm();
        BPTFHIST_RD.TableName = "BPTFHIST";
        BPTFHIST_RD.where = "JRNNO = :WS_JRNNO "
            + "AND AC_DT = :WS_END_DATE";
        IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
    }
    public void T000_COUNT_AC_JUMP() throws IOException,SQLException,Exception {
        WS_BRW_FHIST_FLAG = 'Y';
        CEP.TRC(SCCGWA, "COUNT_BY_AC_JUMP");
        CEP.TRC(SCCGWA, WS_STR_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        if (WS_TX_CCY.trim().length() == 0) {
            null.set = "WS-COUNT-NO=COUNT(1)";
            BPTFHIST_RD = new DBParm();
            BPTFHIST_RD.TableName = "BPTFHIST";
            BPTFHIST_RD.where = "AC = :WS_AC "
                + "AND TX_MMO NOT IN ( 'X15' , 'X16' , 'GTU' , 'GTV' ) "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND ( ' ' = :WS_TX_DC "
                + "OR DRCRFLG = :WS_TX_DC ) "
                + "AND PRINT_FLG = 'Y'";
            IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
        } else {
            null.set = "WS-COUNT-NO=COUNT(1)";
            BPTFHIST_RD = new DBParm();
            BPTFHIST_RD.TableName = "BPTFHIST";
            BPTFHIST_RD.where = "AC = :WS_AC "
                + "AND TX_MMO NOT IN ( 'X15' , 'X16' , 'GTU' , 'GTV' ) "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND ( ' ' = :WS_TX_DC "
                + "OR DRCRFLG = :WS_TX_DC ) "
                + "AND PRINT_FLG = 'Y'";
            IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
        }
    }
    public void T000_STARTBR_AC_JUMP() throws IOException,SQLException,Exception {
        WS_BRW_FHIST_FLAG = 'Y';
        CEP.TRC(SCCGWA, "BROWSE_BY_AC-JUMP");
        if (BPCBFHHD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC != ' ') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_MMO NOT IN ( 'X15' , 'X16' , 'GTU' , 'GTV' ) "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC != ' ') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_MMO NOT IN ( 'X15' , 'X16' , 'GTU' , 'GTV' ) "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC != ' ') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_MMO NOT IN ( 'X15' , 'X16' , 'GTU' , 'GTV' ) "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC != ' ') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_MMO NOT IN ( 'X15' , 'X16' , 'GTU' , 'GTV' ) "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC == ' ') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_MMO NOT IN ( 'X15' , 'X16' , 'GTU' , 'GTV' ) "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC == ' ') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_MMO NOT IN ( 'X15' , 'X16' , 'GTU' , 'GTV' ) "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC == ' ') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_MMO NOT IN ( 'X15' , 'X16' , 'GTU' , 'GTV' ) "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC == ' ') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_MMO NOT IN ( 'X15' , 'X16' , 'GTU' , 'GTV' ) "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else {
            CEP.TRC(SCCGWA, "OTHER");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NONE_COND, BPCBFHHD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_AC() throws IOException,SQLException,Exception {
        WS_BRW_FHIST_FLAG = 'Y';
        CEP.TRC(SCCGWA, "BROWSE_BY_AC");
        if (BPCBFHHD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC != ' ') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC != ' ') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC != ' ') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC != ' ') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC == ' ') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC == ' ') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC == ' ') {
            CEP.TRC(SCCGWA, "ONLY AC DESC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC == ' ') {
            CEP.TRC(SCCGWA, "ONLY AC ASC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else {
            CEP.TRC(SCCGWA, "OTHER");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NONE_COND, BPCBFHHD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_JRN() throws IOException,SQLException,Exception {
        WS_BRW_FHIST_FLAG = 'Y';
        CEP.TRC(SCCGWA, WS_JRNNO);
        if (BPCBFHHD.INPUT.SORT_FLG == 'Y') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "JRNNO = :WS_JRNNO "
                + "AND AC_DT = :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "JRNNO = :WS_JRNNO "
                + "AND AC_DT = :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_JRN_ALL() throws IOException,SQLException,Exception {
        WS_BRW_FHIST_FLAG = 'Y';
        CEP.TRC(SCCGWA, WS_JRNNO);
        CEP.TRC(SCCGWA, "BROWSE-BY-JRN-ALL");
        if (BPCBFHHD.INPUT.SORT_FLG == 'Y') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "JRNNO = :WS_JRNNO "
                + "AND AC_DT = :WS_END_DATE";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "JRNNO = :WS_JRNNO "
                + "AND AC_DT = :WS_END_DATE";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_AC_PROD() throws IOException,SQLException,Exception {
        WS_BRW_FHIST_FLAG = 'Y';
        CEP.TRC(SCCGWA, WS_AC);
        CEP.TRC(SCCGWA, WS_PROD_CD);
        if (BPCBFHHD.INPUT.SORT_FLG == 'Y') {
            if (WS_TX_CCY.trim().length() == 0) {
                if (WS_PROD_CD_FLG != 'Y') {
                    BPTFHIST_BR.rp = new DBParm();
                    BPTFHIST_BR.rp.TableName = "BPTFHIST";
                    BPTFHIST_BR.rp.where = "AC = :WS_AC "
                        + "AND PROD_CD = :WS_PROD_CD "
                        + "AND AC_DT >= :WS_STR_DATE "
                        + "AND AC_DT <= :WS_END_DATE "
                        + "AND PRINT_FLG = 'Y'";
                    BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
                    IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
                } else {
                    BPTFHIST_BR.rp = new DBParm();
                    BPTFHIST_BR.rp.TableName = "BPTFHIST";
                    BPTFHIST_BR.rp.where = "AC = :WS_AC "
                        + "AND ( PROD_CD = :WS_PROD_CD_01 "
                        + "OR PROD_CD = :WS_PROD_CD_02 ) "
                        + "AND AC_DT >= :WS_STR_DATE "
                        + "AND AC_DT <= :WS_END_DATE "
                        + "AND PRINT_FLG = 'Y'";
                    BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
                    IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
                }
            } else {
                if (WS_PROD_CD_FLG != 'Y') {
                    BPTFHIST_BR.rp = new DBParm();
                    BPTFHIST_BR.rp.TableName = "BPTFHIST";
                    BPTFHIST_BR.rp.where = "AC = :WS_AC "
                        + "AND PROD_CD = :WS_PROD_CD "
                        + "AND TX_CCY = :WS_TX_CCY "
                        + "AND AC_DT >= :WS_STR_DATE "
                        + "AND AC_DT <= :WS_END_DATE "
                        + "AND PRINT_FLG = 'Y'";
                    BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
                    IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
                } else {
                    BPTFHIST_BR.rp = new DBParm();
                    BPTFHIST_BR.rp.TableName = "BPTFHIST";
                    BPTFHIST_BR.rp.where = "AC = :WS_AC "
                        + "AND ( PROD_CD = :WS_PROD_CD_01 "
                        + "OR PROD_CD = :WS_PROD_CD_02 ) "
                        + "AND TX_CCY = :WS_TX_CCY "
                        + "AND AC_DT >= :WS_STR_DATE "
                        + "AND AC_DT <= :WS_END_DATE "
                        + "AND PRINT_FLG = 'Y'";
                    BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
                    IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
                }
            }
        } else {
            if (WS_TX_CCY.trim().length() == 0) {
                if (WS_PROD_CD_FLG != 'Y') {
                    BPTFHIST_BR.rp = new DBParm();
                    BPTFHIST_BR.rp.TableName = "BPTFHIST";
                    BPTFHIST_BR.rp.where = "AC = :WS_AC "
                        + "AND PROD_CD = :WS_PROD_CD "
                        + "AND AC_DT >= :WS_STR_DATE "
                        + "AND AC_DT <= :WS_END_DATE "
                        + "AND PRINT_FLG = 'Y'";
                    BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
                } else {
                    BPTFHIST_BR.rp = new DBParm();
                    BPTFHIST_BR.rp.TableName = "BPTFHIST";
                    BPTFHIST_BR.rp.where = "AC = :WS_AC "
                        + "AND ( PROD_CD = :WS_PROD_CD_01 "
                        + "OR PROD_CD = :WS_PROD_CD_02 ) "
                        + "AND AC_DT >= :WS_STR_DATE "
                        + "AND AC_DT <= :WS_END_DATE "
                        + "AND PRINT_FLG = 'Y'";
                    BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
                }
            } else {
                if (WS_PROD_CD_FLG != 'Y') {
                    BPTFHIST_BR.rp = new DBParm();
                    BPTFHIST_BR.rp.TableName = "BPTFHIST";
                    BPTFHIST_BR.rp.where = "AC = :WS_AC "
                        + "AND PROD_CD = :WS_PROD_CD "
                        + "AND TX_CCY = :WS_TX_CCY "
                        + "AND AC_DT >= :WS_STR_DATE "
                        + "AND AC_DT <= :WS_END_DATE "
                        + "AND PRINT_FLG = 'Y'";
                    BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
                } else {
                    BPTFHIST_BR.rp = new DBParm();
                    BPTFHIST_BR.rp.TableName = "BPTFHIST";
                    BPTFHIST_BR.rp.where = "AC = :WS_AC "
                        + "AND ( PROD_CD = :WS_PROD_CD_01 "
                        + "OR PROD_CD = :WS_PROD_CD_02 ) "
                        + "AND TX_CCY = :WS_TX_CCY "
                        + "AND AC_DT >= :WS_STR_DATE "
                        + "AND AC_DT <= :WS_END_DATE "
                        + "AND PRINT_FLG = 'Y'";
                    BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
                }
            }
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_TOOL_PROD() throws IOException,SQLException,Exception {
        WS_BRW_FHIST_FLAG = 'Y';
        CEP.TRC(SCCGWA, WS_TX_TOOL);
        CEP.TRC(SCCGWA, WS_PROD_CD);
        if (BPCBFHHD.INPUT.SORT_FLG == 'Y') {
            if (WS_TX_CCY.trim().length() == 0) {
                if (WS_PROD_CD_FLG != 'Y') {
                    BPTFHIST_BR.rp = new DBParm();
                    BPTFHIST_BR.rp.TableName = "BPTFHIST";
                    BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                        + "AND PROD_CD = :WS_PROD_CD "
                        + "AND AC_DT >= :WS_STR_DATE "
                        + "AND AC_DT <= :WS_END_DATE "
                        + "AND PRINT_FLG = 'Y'";
                    BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
                    IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
                } else {
                    BPTFHIST_BR.rp = new DBParm();
                    BPTFHIST_BR.rp.TableName = "BPTFHIST";
                    BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                        + "AND ( PROD_CD = :WS_PROD_CD_01 "
                        + "OR PROD_CD = :WS_PROD_CD_02 ) "
                        + "AND AC_DT >= :WS_STR_DATE "
                        + "AND AC_DT <= :WS_END_DATE "
                        + "AND PRINT_FLG = 'Y'";
                    BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
                    IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
                }
            } else {
                if (WS_PROD_CD_FLG != 'Y') {
                    BPTFHIST_BR.rp = new DBParm();
                    BPTFHIST_BR.rp.TableName = "BPTFHIST";
                    BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                        + "AND TX_CCY = :WS_TX_CCY "
                        + "AND PROD_CD = :WS_PROD_CD "
                        + "AND AC_DT >= :WS_STR_DATE "
                        + "AND AC_DT <= :WS_END_DATE "
                        + "AND PRINT_FLG = 'Y'";
                    BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
                    IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
                } else {
                    BPTFHIST_BR.rp = new DBParm();
                    BPTFHIST_BR.rp.TableName = "BPTFHIST";
                    BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                        + "AND TX_CCY = :WS_TX_CCY "
                        + "AND ( PROD_CD = :WS_PROD_CD_01 "
                        + "OR PROD_CD = :WS_PROD_CD_02 ) "
                        + "AND AC_DT >= :WS_STR_DATE "
                        + "AND AC_DT <= :WS_END_DATE "
                        + "AND PRINT_FLG = 'Y'";
                    BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
                    IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
                }
            }
        } else {
            if (WS_TX_CCY.trim().length() == 0) {
                if (WS_PROD_CD_FLG != 'Y') {
                    BPTFHIST_BR.rp = new DBParm();
                    BPTFHIST_BR.rp.TableName = "BPTFHIST";
                    BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                        + "AND PROD_CD = :WS_PROD_CD "
                        + "AND AC_DT >= :WS_STR_DATE "
                        + "AND AC_DT <= :WS_END_DATE "
                        + "AND PRINT_FLG = 'Y'";
                    BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
                } else {
                    BPTFHIST_BR.rp = new DBParm();
                    BPTFHIST_BR.rp.TableName = "BPTFHIST";
                    BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                        + "AND ( PROD_CD = :WS_PROD_CD_01 "
                        + "OR PROD_CD = :WS_PROD_CD_02 ) "
                        + "AND AC_DT >= :WS_STR_DATE "
                        + "AND AC_DT <= :WS_END_DATE "
                        + "AND PRINT_FLG = 'Y'";
                    BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
                }
            } else {
                if (WS_PROD_CD_FLG != 'Y') {
                    BPTFHIST_BR.rp = new DBParm();
                    BPTFHIST_BR.rp.TableName = "BPTFHIST";
                    BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                        + "AND TX_CCY = :WS_TX_CCY "
                        + "AND PROD_CD = :WS_PROD_CD "
                        + "AND AC_DT >= :WS_STR_DATE "
                        + "AND AC_DT <= :WS_END_DATE "
                        + "AND PRINT_FLG = 'Y'";
                    BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
                } else {
                    BPTFHIST_BR.rp = new DBParm();
                    BPTFHIST_BR.rp.TableName = "BPTFHIST";
                    BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                        + "AND TX_CCY = :WS_TX_CCY "
                        + "AND ( PROD_CD = :WS_PROD_CD_01 "
                        + "OR PROD_CD = :WS_PROD_CD_02 ) "
                        + "AND AC_DT >= :WS_STR_DATE "
                        + "AND AC_DT <= :WS_END_DATE "
                        + "AND PRINT_FLG = 'Y'";
                    BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
                }
            }
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_TLR_BR() throws IOException,SQLException,Exception {
        WS_BRW_FHIST_FLAG = 'Y';
        if (BPCBFHHD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC != ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_CCY_DC DESC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC != ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_CCY_DC ASC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC != ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_CCY_DC DESC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC != ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_CCY_DC ASC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC == ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_CCY DESC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC == ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_CCY ASC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC == ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR DESC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC == ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR ASC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else {
            CEP.TRC(SCCGWA, "OTHER");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NONE_COND, BPCBFHHD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_ACBRTLR() throws IOException,SQLException,Exception {
        WS_BRW_FHIST_FLAG = 'Y';
        if (BPCBFHHD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC != ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_AC_CCY_DC DESC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC != ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_AC_CCY_DC ASC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC != ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_AC_CCY_DC DESC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC != ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_AC_CCY_DC ASC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC == ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_AC_CCY DESC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC == ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_AC_CCY ASC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC == ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_AC DESC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCBFHHD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC == ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_AC ASC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else {
            CEP.TRC(SCCGWA, "OTHER");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NONE_COND, BPCBFHHD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_TOOL_BRTLR() throws IOException,SQLException,Exception {
        WS_BRW_FHIST_FLAG = 'Y';
        if (WS_TX_BR != 0 
                && WS_TX_TELR.trim().length() > 0 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC != ' ' 
                && BPCBFHHD.INPUT.SORT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_TOOL_CCY_DC DESC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (WS_TX_BR != 0 
                && WS_TX_TELR.trim().length() > 0 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC != ' ' 
                && BPCBFHHD.INPUT.SORT_FLG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_TOOL_CCY_DC ASC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (WS_TX_BR != 0 
                && WS_TX_TELR.trim().length() > 0 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC == ' ' 
                && BPCBFHHD.INPUT.SORT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_TOOL_CCY DESC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (WS_TX_BR != 0 
                && WS_TX_TELR.trim().length() > 0 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC == ' ' 
                && BPCBFHHD.INPUT.SORT_FLG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_TOOL_CCY ASC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (WS_TX_BR != 0 
                && WS_TX_TELR.trim().length() > 0 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC != ' ' 
                && BPCBFHHD.INPUT.SORT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_TOOL_DC DESC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (WS_TX_BR != 0 
                && WS_TX_TELR.trim().length() > 0 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC != ' ' 
                && BPCBFHHD.INPUT.SORT_FLG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_TOOL_DC ASC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (WS_TX_BR != 0 
                && WS_TX_TELR.trim().length() > 0 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC == ' ' 
                && BPCBFHHD.INPUT.SORT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_TOOL_DC DESC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (WS_TX_BR != 0 
                && WS_TX_TELR.trim().length() > 0 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC == ' ' 
                && BPCBFHHD.INPUT.SORT_FLG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_TOOL_DC ASC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (WS_TX_BR == 0 
                && WS_TX_TELR.trim().length() == 0 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC != ' ' 
                && BPCBFHHD.INPUT.SORT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_TOOL_DC_CCY DESC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (WS_TX_BR == 0 
                && WS_TX_TELR.trim().length() == 0 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC != ' ' 
                && BPCBFHHD.INPUT.SORT_FLG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_TOOL_DC_CCY ASC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (WS_TX_BR == 0 
                && WS_TX_TELR.trim().length() == 0 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC != ' ' 
                && BPCBFHHD.INPUT.SORT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_TOOL_DC DESC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (WS_TX_BR == 0 
                && WS_TX_TELR.trim().length() == 0 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC != ' ' 
                && BPCBFHHD.INPUT.SORT_FLG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_TOOL_DC ASC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (WS_TX_BR == 0 
                && WS_TX_TELR.trim().length() == 0 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC == ' ' 
                && BPCBFHHD.INPUT.SORT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_TOOL_CCY DESC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (WS_TX_BR == 0 
                && WS_TX_TELR.trim().length() == 0 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC == ' ' 
                && BPCBFHHD.INPUT.SORT_FLG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_TOOL_CCY ASC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (WS_TX_BR == 0 
                && WS_TX_TELR.trim().length() == 0 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC == ' ' 
                && BPCBFHHD.INPUT.SORT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_TOOL ASC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (WS_TX_BR == 0 
                && WS_TX_TELR.trim().length() == 0 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC == ' ' 
                && BPCBFHHD.INPUT.SORT_FLG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_TOOL ASC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NONE_COND, BPCBFHHD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BRTLR() throws IOException,SQLException,Exception {
        T000_STARTBR_TLR_BR();
        if (pgmRtn) return;
    }
    public void B023_READNEXT_HIS_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTFHIST_CN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_HOD_HIS_FLG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_HOD_HIS_FLG = 'Y';
            B140_IF_READ_HOT_TABLE();
            if (pgmRtn) return;
        } else {
        }
        CEP.TRC(SCCGWA, BPRFHIST.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRFHIST.KEY.JRNNO);
        CEP.TRC(SCCGWA, BPRFHIST.KEY.JRN_SEQ);
        CEP.TRC(SCCGWA, BPRFHIST.KEY.AC);
    }
    public void B090_FIRST_READNEXT_CN() throws IOException,SQLException,Exception {
        WS_BFHHD_FETCH_NO = BPCBFHHD.INPUT.FETCH_NO;
        if (WS_HIS_FLG == '1' 
            && BPCBFHHD.INPUT.END_AC_DT >= SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE 
            && BPCBFHHD.INPUT.STR_AC_DT <= SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE 
            && WS_COUNT_LAST_NO > 0) {
            if (BPCBFHHD.INPUT.SORT_FLG == 'Y') {
                if (BPCBFHHD.INPUT.FETCH_NO <= WS_COUNT_LAST_NO) {
                    B090_CALL_BPZBFHT1_OR_BPZBFHT2();
                    if (pgmRtn) return;
                } else {
                    BPCBFHHD.INPUT.FUNC = '3';
                    B089_ENDBR_LAST_DT_PROC();
                    if (pgmRtn) return;
                    BPCBFHHD.INPUT.BROWS_COND = WS_BFHHD_BROWS_COND;
                    WS_AC_DT = SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE;
                    R000_GET_LAST_AC_DT_CN();
                    if (pgmRtn) return;
                    WS_END_DATE = BPCOCLWD.DATE2;
                    B011_STARTBR_HIS_PROC();
                    if (pgmRtn) return;
                    BPCBFHHD.INPUT.FETCH_NO = BPCBFHHD.INPUT.FETCH_NO - WS_COUNT_LAST_NO;
                    WS_RCD_SEQ = BPCBFHHD.INPUT.FETCH_NO;
                    T000_READNEXT_FIRST_BPTFHIST();
                    if (pgmRtn) return;
                    WS_HIS_READ_FLG = 'Y';
                }
            }
            if (BPCBFHHD.INPUT.SORT_FLG == 'N') {
                if (BPCBFHHD.INPUT.FETCH_NO <= WS_COUNT_HIS_NO) {
                    WS_RCD_SEQ = BPCBFHHD.INPUT.FETCH_NO;
                    T000_READNEXT_FIRST_BPTFHIST();
                    if (pgmRtn) return;
                } else {
                    T000_ENDBR_BPTFHIST();
                    if (pgmRtn) return;
                    BPCBFHHD.INPUT.FUNC = '1';
                    BPCBFHHD.INPUT.BROWS_COND = WS_BFHHD_BROWS_COND;
                    B090_CALL_BPZBFHT1_OR_BPZBFHT2();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCBFHHD.INPUT.FETCH_NO);
                    CEP.TRC(SCCGWA, WS_COUNT_LAST_NO);
                    BPCBFHHD.INPUT.FETCH_NO = BPCBFHHD.INPUT.FETCH_NO - WS_COUNT_HIS_NO;
                    BPCBFHHD.INPUT.FUNC = '9';
                    B090_CALL_BPZBFHT1_OR_BPZBFHT2();
                    if (pgmRtn) return;
                    WS_HOT_READ_FLG = 'Y';
                }
            }
        } else {
            if (BPCBFHHD.INPUT.FETCH_NO > 0) {
                WS_RCD_SEQ = BPCBFHHD.INPUT.FETCH_NO;
                T000_READNEXT_FIRST_BPTFHIST();
                if (pgmRtn) return;
            } else {
                T000_READNEXT_BPTFHIST_CN();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCBFHHD.INPUT.FETCH_NO);
    }
    public void B030_ENDBR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_END_HT1_BR_FLG);
        CEP.TRC(SCCGWA, WS_END_HHD_BR_FLG);
        CEP.TRC(SCCGWA, WS_BFHT1_BRW_FLG);
        CEP.TRC(SCCGWA, WS_BFHHD_BRW_FLG);
        if (WS_BFHHD_BRW_FLG == 'Y' 
            && WS_END_HHD_BR_FLG != 'Y') {
            T000_ENDBR_BPTFHIST();
            if (pgmRtn) return;
        }
        if (WS_BFHT1_BRW_FLG == 'Y' 
            && WS_END_HT1_BR_FLG != 'Y') {
            B089_ENDBR_LAST_DT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_READ_PROC() throws IOException,SQLException,Exception {
        if (WS_HIS_FLG == '1' 
            && BPRFHIST.KEY.AC_DT == SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE) {
            B090_CALL_BPZBFHT1_OR_BPZBFHT2();
            if (pgmRtn) return;
        } else {
            T000_READ_BPTFHIST();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.init(SCCGWA, BPRFHIS1);
                BPRFHIS1.KEY.AC_DT = BPRFHIST.KEY.AC_DT;
                BPRFHIS1.KEY.JRNNO = BPRFHIST.KEY.JRNNO;
                BPRFHIS1.KEY.JRN_SEQ = BPRFHIST.KEY.JRN_SEQ;
                T000_READ_BPTFHIS1();
                if (pgmRtn) return;
