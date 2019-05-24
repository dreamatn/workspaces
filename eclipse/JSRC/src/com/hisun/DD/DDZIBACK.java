package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZIBACK {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String JIBS_NumStr;
    String JIBS_f0;
    DBParm DDTBALH_RD;
    brParm DDTBACK_BR = new brParm();
    DBParm DDTBACK_RD;
    brParm DDTBALH_BR = new brParm();
    DBParm DDTDREG_RD;
    DBParm DDTBSP88_RD;
    boolean pgmRtn = false;
    char K_STR_BSP_FLG = 'Y';
    String CPN_I_CHK_BACK_DATE = "DD-I-CHK-BACK-DATE  ";
    int WS_LENGTH = 0;
    double WS_BACK_AMT = 0;
    double WS_TMP_BACK_AMT = 0;
    int WS_BACK_DATE = 0;
    int WS_TMP_BACK_DATE = 0;
    int WS_INT_BAL_DATE = 0;
    long WS_EDIT_AP_BAT = 0;
    DDZIBACK_WS_PARM_DATA WS_PARM_DATA = new DDZIBACK_WS_PARM_DATA();
    DDZIBACK_WS_PARM_DATA1 WS_PARM_DATA1 = new DDZIBACK_WS_PARM_DATA1();
    int WS_MAX_AC_DATE = 0;
    char WS_CHK_FLG = ' ';
    char WS_EXIST_REC_FLG = ' ';
    char WS_EOF_BALH_FLG = ' ';
    char WS_EOF_BACK_FLG = ' ';
    char WS_BACK_FOUND_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDRBACK DDRBACK = new DDRBACK();
    DDRBALH DDRBALH = new DDRBALH();
    DDRDREG DDRDREG = new DDRDREG();
    BPCOMGBP BPCOMGBP = new BPCOMGBP();
    DDCIBDCK DDCIBDCK = new DDCIBDCK();
    DDCSBKVL DDCSBKVL = new DDCSBKVL();
    SCCBSP SCCBSP = new SCCBSP();
    SCCBSPS SCCBSPS = new SCCBSPS();
    DDRBSP88 DDRBSP88 = new DDRBSP88();
    SCCRWBSP SCCRWBSP = new SCCRWBSP();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    DDCIBACK DDCIBACK;
    BPCPQBNK_DATA_INFO BPCRBANK;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCRCWAT SCRCWA;
    public void MP(SCCGWA SCCGWA, DDCIBACK DDCIBACK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCIBACK = DDCIBACK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZIBACK return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
        CEP.TRC(SCCGWA, SCRCWA.AC_DATE_AREA[1-1].AC_DATE);
        CEP.TRC(SCCGWA, SCRCWA.AC_DATE_AREA[2-1].AC_DATE);
        CEP.TRC(SCCGWA, SCCGBPA_BP_AREA.COM_AREA.DDBKD_FST_FLG);
        if (SCCGBPA_BP_AREA.COM_AREA.DDBKD_FST_FLG == 'Y') {
            K_STR_BSP_FLG = 'N';
        } else {
            IBS.init(SCCGWA, BPCOMGBP);
            BPCOMGBP.FUNC = '1';
            S000_CALL_BPZPMGBP();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGBPA_BP_AREA.COM_AREA.DDBKD_FST_FLG);
            K_STR_BSP_FLG = 'Y';
        }
        DDCIBACK.RC.RC_MMO = "DD";
        DDCIBACK.RC.RC_CODE = 0;
        WS_EXIST_REC_FLG = 'N';
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AP_MMO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID.TR_AP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        B001_GET_ACO_AC_PROC();
        if (pgmRtn) return;
        if (DDCIBACK.FUNC == 'T'
            || DDCIBACK.FUNC == 'B') {
            B010_BACK_VALUE_PROC();
            if (pgmRtn) return;
        } else if (DDCIBACK.FUNC == 'C') {
            B030_CLR_CCY_CHK_PROC();
            if (pgmRtn) return;
        } else if (DDCIBACK.FUNC == 'D') {
            B050_DATA_CLEAR_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCIBACK.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B001_GET_ACO_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'C';
        CICQACAC.DATA.AGR_NO = DDCIBACK.AC_NO;
        CICQACAC.DATA.CCY_ACAC = DDCIBACK.CCY;
        CICQACAC.DATA.CR_FLG = DDCIBACK.CCY_TYP;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
    }
    public void B010_BACK_VALUE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SERV_CODE);
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B010_10_CHK_BACK_VAL_DT_PROC();
        if (pgmRtn) return;
        B010_20_GET_BACK_AMT_PROC();
        if (pgmRtn) return;
        B010_30_CHK_INT_BAL_PROC();
        if (pgmRtn) return;
        if (DDCIBACK.FUNC == 'T') {
            B010_50_CRT_BACK_REC_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, K_STR_BSP_FLG);
        if (DDCIBACK.FUNC == 'T') {
            if (WS_EXIST_REC_FLG == 'N') {
                B010_70_WRITE_BSP_REC_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, K_STR_BSP_FLG);
            if (K_STR_BSP_FLG == 'Y') {
                K_STR_BSP_FLG = 'N';
                CEP.TRC(SCCGWA, SCRCWA.AC_DATE_AREA[1-1].AC_DATE);
                CEP.TRC(SCCGWA, SCRCWA.AC_DATE_AREA[2-1].AC_DATE);
                if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
                    || (SCRCWA.AC_DATE_AREA[1-1].AC_DATE != SCRCWA.AC_DATE_AREA[2-1].AC_DATE)) {
                } else {
                    B010_90_STR_ONL_BSP_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B010_10_CHK_BACK_VAL_DT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIBDCK);
        DDCIBDCK.AC_NO = DDCIBACK.AC_NO;
        DDCIBDCK.CCY = DDCIBACK.CCY;
        DDCIBDCK.CCY_TYPE = DDCIBACK.CCY_TYP;
        DDCIBDCK.DATE = DDCIBACK.DATE;
        DDCIBDCK.LAST_POST_DATE = DDCIBACK.LAST_POST_DATE;
        S000_CALL_DDZIBDCK();
        if (pgmRtn) return;
        DDCIBACK.DATE = DDCIBDCK.BK_DT;
    }
    public void B010_20_GET_BACK_AMT_PROC() throws IOException,SQLException,Exception {
        if (DDCIBACK.OPT == 'W') {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                WS_TMP_BACK_AMT = ( -1 ) * DDCIBACK.AMT;
            } else {
                WS_TMP_BACK_AMT = DDCIBACK.AMT;
            }
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                WS_TMP_BACK_AMT = ( -1 ) * DDCIBACK.AMT;
            } else {
                WS_TMP_BACK_AMT = DDCIBACK.AMT;
            }
        }
    }
    public void B010_30_CHK_INT_BAL_PROC() throws IOException,SQLException,Exception {
        WS_BACK_AMT = 0;
        WS_CHK_FLG = 'N';
        T000_STARTBR_DDTBACK();
        if (pgmRtn) return;
        T000_READNEXT_DDTBACK();
        if (pgmRtn) return;
        if (WS_EOF_BACK_FLG != 'Y') {
            CEP.TRC(SCCGWA, "NOT RBACK-END");
            WS_TMP_BACK_DATE = DDRBACK.KEY.VALUE_DATE;
            if (DDCIBACK.DATE < WS_TMP_BACK_DATE) {
                CEP.TRC(SCCGWA, "IBACK-DATE < WS-TMP-BACK-DATE");
                WS_BACK_AMT = WS_BACK_AMT + WS_TMP_BACK_AMT;
                WS_BACK_DATE = DDCIBACK.DATE;
                WS_CHK_FLG = 'Y';
                R000_CHK_BALH_BAL_PROC();
                if (pgmRtn) return;
            }
            while (WS_EOF_BACK_FLG != 'Y') {
                if (WS_CHK_FLG == 'N' 
                    && (WS_TMP_BACK_DATE <= DDCIBACK.DATE) 
                    && (DDCIBACK.DATE <= DDRBACK.KEY.VALUE_DATE)) {
                    CEP.TRC(SCCGWA, "WS-CHK-FLG-N AND");
                    CEP.TRC(SCCGWA, "WS-TMP-BACK-DATE <= IBACK-DATE AND");
                    CEP.TRC(SCCGWA, "IBACK-DATE <= BACK-VALUE-DATE");
                    WS_BACK_AMT = WS_BACK_AMT + WS_TMP_BACK_AMT;
                    WS_BACK_DATE = DDCIBACK.DATE;
                    WS_CHK_FLG = 'Y';
                    R000_CHK_BALH_BAL_PROC();
                    if (pgmRtn) return;
                }
                WS_BACK_AMT = WS_BACK_AMT + DDRBACK.AMT;
                WS_BACK_DATE = DDRBACK.KEY.VALUE_DATE;
                CEP.TRC(SCCGWA, "DO-FLAG IS N");
                R000_CHK_BALH_BAL_PROC();
                if (pgmRtn) return;
                WS_TMP_BACK_DATE = DDRBACK.KEY.VALUE_DATE;
                T000_READNEXT_DDTBACK();
                if (pgmRtn) return;
            }
            if (DDCIBACK.DATE > WS_TMP_BACK_DATE) {
                CEP.TRC(SCCGWA, "IBACK-DATE > WS-TMP-BACK-DATE");
                WS_BACK_AMT = WS_BACK_AMT + WS_TMP_BACK_AMT;
                WS_BACK_DATE = DDCIBACK.DATE;
                WS_CHK_FLG = 'Y';
                R000_CHK_BALH_BAL_PROC();
                if (pgmRtn) return;
            }
            T000_ENDBR_DDTBACK();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "RBACK-END");
            WS_BACK_AMT = WS_BACK_AMT + WS_TMP_BACK_AMT;
            WS_BACK_DATE = DDCIBACK.DATE;
            WS_CHK_FLG = 'Y';
            R000_CHK_BALH_BAL_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_50_CRT_BACK_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRBACK);
        DDRBACK.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRBACK.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRBACK.KEY.VALUE_DATE = DDCIBACK.DATE;
        DDRBACK.KEY.TR_JRN = SCCGWA.COMM_AREA.JRN_NO;
        T000_READUPD_DDTBACK();
        if (pgmRtn) return;
        if (WS_BACK_FOUND_FLG == 'Y') {
            WS_EXIST_REC_FLG = 'Y';
        } else {
            WS_EXIST_REC_FLG = 'N';
        }
        if (WS_EXIST_REC_FLG == 'N') {
            B010_50_01_CREATE_NEW_REC();
            if (pgmRtn) return;
        } else {
            B010_50_02_UPDATE_OLD_REC();
            if (pgmRtn) return;
        }
    }
    public void B010_50_01_CREATE_NEW_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRBACK);
        DDRBACK.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRBACK.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRBACK.KEY.VALUE_DATE = DDCIBACK.DATE;
        DDRBACK.KEY.TR_JRN = SCCGWA.COMM_AREA.JRN_NO;
        DDRBACK.DO_FLAG = 'N';
        DDRBACK.AMT = WS_TMP_BACK_AMT;
        DDRBACK.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_WRITE_DDTBACK();
        if (pgmRtn) return;
    }
    public void B010_50_02_UPDATE_OLD_REC() throws IOException,SQLException,Exception {
        DDRBACK.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRBACK.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRBACK.KEY.VALUE_DATE = DDCIBACK.DATE;
        DDRBACK.KEY.TR_JRN = SCCGWA.COMM_AREA.JRN_NO;
        DDRBACK.DO_FLAG = 'N';
        DDRBACK.AMT = DDRBACK.AMT + WS_TMP_BACK_AMT;
        DDRBACK.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_DDTBACK();
        if (pgmRtn) return;
    }
    public void B010_70_WRITE_BSP_REC_PROC() throws IOException,SQLException,Exception {
        R000_GET_BAT_NO_PROC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRBSP88);
        IBS.init(SCCGWA, SCCRWBSP);
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            if (SCCGWA.COMM_AREA.AP_MMO.equalsIgnoreCase("DD")) {
                if (SCCGWA.COMM_AREA.SERV_CODE == null) SCCGWA.COMM_AREA.SERV_CODE = "";
                JIBS_tmp_int = SCCGWA.COMM_AREA.SERV_CODE.length();
                for (int i=0;i<7-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.SERV_CODE += " ";
                DDRBSP88.KEY.AP_TYPE = SCCGWA.COMM_AREA.SERV_CODE.substring(2 - 1, 2 + 6 - 1);
                DDRBSP88.KEY.AP_BATNO = 999999999;
            } else {
                DDRBSP88.KEY.AP_TYPE = "EXPAPP";
                JIBS_tmp_str[0] = "" + WS_EDIT_AP_BAT;
                JIBS_f0 = "";
                for (int i=0;i<12-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                JIBS_NumStr = JIBS_f0 + WS_EDIT_AP_BAT;
                JIBS_tmp_str[1] = "" + 7777;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                JIBS_NumStr = JIBS_tmp_str[1] + JIBS_NumStr.substring(4);
                WS_EDIT_AP_BAT = Long.parseLong(JIBS_NumStr);
                JIBS_tmp_str[0] = "" + WS_EDIT_AP_BAT;
                JIBS_f0 = "";
                for (int i=0;i<12-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                JIBS_NumStr = JIBS_f0 + WS_EDIT_AP_BAT;
                JIBS_tmp_str[1] = "" + SCCGWA.COMM_AREA.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(5 + 8 - 1);
                WS_EDIT_AP_BAT = Long.parseLong(JIBS_NumStr);
                DDRBSP88.KEY.AP_BATNO = WS_EDIT_AP_BAT;
            }
        } else {
            JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            DDRBSP88.KEY.AP_TYPE = JIBS_tmp_str[0].substring(3 - 1, 3 + 6 - 1);
            DDRBSP88.KEY.AP_BATNO = SCCGWA.COMM_AREA.JRN_NO;
            if (SCRCWA.AC_DATE_AREA[1-1].AC_DATE != SCRCWA.AC_DATE_AREA[2-1].AC_DATE) {
                DDRBSP88.KEY.AP_TYPE = "EXPAPP";
                JIBS_tmp_str[0] = "" + WS_EDIT_AP_BAT;
                JIBS_f0 = "";
                for (int i=0;i<12-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                JIBS_NumStr = JIBS_f0 + WS_EDIT_AP_BAT;
                JIBS_tmp_str[1] = "" + 7777;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                JIBS_NumStr = JIBS_tmp_str[1] + JIBS_NumStr.substring(4);
                WS_EDIT_AP_BAT = Long.parseLong(JIBS_NumStr);
                JIBS_tmp_str[0] = "" + WS_EDIT_AP_BAT;
                JIBS_f0 = "";
                for (int i=0;i<12-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                JIBS_NumStr = JIBS_f0 + WS_EDIT_AP_BAT;
                JIBS_tmp_str[1] = "" + SCCGWA.COMM_AREA.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(5 + 8 - 1);
                WS_EDIT_AP_BAT = Long.parseLong(JIBS_NumStr);
                DDRBSP88.KEY.AP_BATNO = WS_EDIT_AP_BAT;
            }
        }
        CEP.TRC(SCCGWA, SCCBSPS.BAT_SEQNO);
        DDRBSP88.KEY.BAT_NO = SCCBSPS.BAT_SEQNO;
        DDRBSP88.BK = SCCGWA.COMM_AREA.TR_BANK;
        DDRBSP88.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRBSP88.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRBSP88.RUN_TYPE = 'T';
        DDRBSP88.RUN_CMP_NAME = "0111180";
        DDRBSP88.ERR_ROLL_FLG = 'Y';
        IBS.init(SCCGWA, DDCSBKVL);
        DDCSBKVL.AC_NO = DDCIBACK.AC_NO;
        DDCSBKVL.CCY = DDCIBACK.CCY;
        DDCSBKVL.CCY_TYPE = DDCIBACK.CCY_TYP;
        DDCSBKVL.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCSBKVL.VALUE_DATE = DDCIBACK.DATE;
        DDCSBKVL.TR_JRN = SCCGWA.COMM_AREA.JRN_NO;
        WS_LENGTH = 64;
        if (DDRBSP88.BLOB_TR_DATA == null) DDRBSP88.BLOB_TR_DATA = "";
        JIBS_tmp_int = DDRBSP88.BLOB_TR_DATA.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) DDRBSP88.BLOB_TR_DATA += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCSBKVL);
        DDRBSP88.BLOB_TR_DATA = JIBS_tmp_str[0] + DDRBSP88.BLOB_TR_DATA.substring(WS_LENGTH);
        CEP.TRC(SCCGWA, DDCSBKVL.AC_NO);
        CEP.TRC(SCCGWA, DDCSBKVL.CCY);
        CEP.TRC(SCCGWA, DDCSBKVL.AC_DATE);
        CEP.TRC(SCCGWA, DDCSBKVL.VALUE_DATE);
        CEP.TRC(SCCGWA, DDCSBKVL.TR_JRN);
        T000_WRITE_DDTBSP88();
        if (pgmRtn) return;
    }
    public void B010_90_STR_ONL_BSP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCBSP);
        SCCBSP.SERV_CODE = "BSPDD88";
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        SCCBSP.BH_SEQ.BH_TYPE = JIBS_tmp_str[0].substring(3 - 1, 3 + 6 - 1);
        SCCBSP.BH_SEQ.BH_BATNO = SCCGWA.COMM_AREA.JRN_NO;
        WS_PARM_DATA.WS_BH_HEAD = "BATNO=";
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_PARM_DATA.WS_BH_TYPE = JIBS_tmp_str[0].substring(3 - 1, 3 + 6 - 1);
        WS_PARM_DATA.WS_BH_BATNO = SCCGWA.COMM_AREA.JRN_NO;
        WS_PARM_DATA1.WS_BUS_TYPE_INFO.WS_REQ_SYS_ID = SCCGWA.COMM_AREA.REQ_SYSTEM;
        WS_PARM_DATA1.WS_BUS_TYPE_INFO.WS_BUS_TYPE2 = SCCBSP.BH_SEQ.BH_TYPE;
        WS_PARM_DATA1.WS_BUS_TYPE_INFO.WS_SERV_CODE = SCCBSP.SERV_CODE;
        WS_PARM_DATA1.WS_BUS_TYPE_INFO.WS_BAT_NO2 = "" + SCCBSP.BH_SEQ.BH_BATNO;
        JIBS_tmp_int = WS_PARM_DATA1.WS_BUS_TYPE_INFO.WS_BAT_NO2.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_PARM_DATA1.WS_BUS_TYPE_INFO.WS_BAT_NO2 = "0" + WS_PARM_DATA1.WS_BUS_TYPE_INFO.WS_BAT_NO2;
        WS_PARM_DATA1.WS_BUS_TYPE_INFO.WS_TRS_DATE = SCCGWA.COMM_AREA.AC_DATE;
        SCCBSP.PARM_DA1 = IBS.CLS2CPY(SCCGWA, WS_PARM_DATA);
        SCCBSP.PARM_DA2 = IBS.CLS2CPY(SCCGWA, WS_PARM_DATA1);
        CEP.TRC(SCCGWA, SCCBSP.BH_SEQ.BH_TYPE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        S000_CALL_SCZOBSP();
        if (pgmRtn) return;
    }
    public void B030_CLR_CCY_CHK_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRBACK);
        DDRBACK.KEY.AC = DDCIBACK.AC_NO;
        DDRBACK.DO_FLAG = 'N';
        T000_READ_DDTBACK();
        if (pgmRtn) return;
        if (WS_EOF_BACK_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_HAS_BACK_TX, DDCIBACK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B050_DATA_CLEAR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRBACK);
        DDRBACK.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRBACK.KEY.AC_DATE = DDCIBACK.AC_DATE;
        DDRBACK.KEY.VALUE_DATE = DDCIBACK.DATE;
        DDRBACK.KEY.TR_JRN = DDCIBACK.JRN_NO;
        T000_READUPD_DDTBACK();
        if (pgmRtn) return;
        T000_DELETE_DDTBACK();
        if (pgmRtn) return;
    }
    public void R000_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIBACK.FUNC);
        CEP.TRC(SCCGWA, DDCIBACK.AC_NO);
        CEP.TRC(SCCGWA, DDCIBACK.DATE);
        CEP.TRC(SCCGWA, DDCIBACK.JRN_NO);
        CEP.TRC(SCCGWA, DDCIBACK.CCY);
        CEP.TRC(SCCGWA, DDCIBACK.CCY_TYP);
        CEP.TRC(SCCGWA, DDCIBACK.AMT);
        CEP.TRC(SCCGWA, DDCIBACK.OPT);
        CEP.TRC(SCCGWA, DDCIBACK.TX_TYPE);
        CEP.TRC(SCCGWA, DDCIBACK.LAST_POST_DATE);
        CEP.TRC(SCCGWA, DDCIBACK.TUOD_FLG);
        CEP.TRC(SCCGWA, DDCIBACK.AC_DATE);
        if (DDCIBACK.AC_NO.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT, DDCIBACK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCIBACK.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_M_INPUT, DDCIBACK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCIBACK.TX_TYPE);
        if (DDCIBACK.TX_TYPE == 'C' 
            || (DDCIBACK.OPT == 'D' 
            && DDCIBACK.TX_TYPE == 'Q')) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_NOT_ALLOW_BACK_DATE, DDCIBACK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DDRDREG);
        DDRDREG.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTDREG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIBACK.DATE);
        CEP.TRC(SCCGWA, DDRDREG.D_DT);
        if (DDCIBACK.DATE <= DDRDREG.D_DT) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CANNOT_BACK_TO_DORM, DDCIBACK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CHK_BALH_BAL_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BACK_DATE);
        T000_STARTBR_DDTBALH();
        if (pgmRtn) return;
        T000_READNEXT_DDTBALH();
        if (pgmRtn) return;
        while (WS_EOF_BALH_FLG != 'Y') {
            CEP.TRC(SCCGWA, DDRBALH.BAL);
            CEP.TRC(SCCGWA, WS_BACK_AMT);
            if (DDRBALH.BAL + WS_BACK_AMT < 0) {
                Z_RET();
                if (pgmRtn) return;
            }
            T000_READNEXT_DDTBALH();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTBALH();
        if (pgmRtn) return;
    }
    public void R000_GET_BAT_NO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCBSPS);
        SCCBSPS.FUN = 'G';
        SCCBSPS.SERV_CODE = "BSPDD88";
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            if (SCCGWA.COMM_AREA.AP_MMO.equalsIgnoreCase("DD")) {
                if (SCCGWA.COMM_AREA.SERV_CODE == null) SCCGWA.COMM_AREA.SERV_CODE = "";
                JIBS_tmp_int = SCCGWA.COMM_AREA.SERV_CODE.length();
                for (int i=0;i<7-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.SERV_CODE += " ";
                SCCBSPS.AP_TYPE = SCCGWA.COMM_AREA.SERV_CODE.substring(2 - 1, 2 + 6 - 1);
                SCCBSPS.AP_BATNO = 999999999;
            } else {
                SCCBSPS.AP_TYPE = "EXPAPP";
                JIBS_tmp_str[0] = "" + WS_EDIT_AP_BAT;
                JIBS_f0 = "";
                for (int i=0;i<12-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                JIBS_NumStr = JIBS_f0 + WS_EDIT_AP_BAT;
                JIBS_tmp_str[1] = "" + 7777;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                JIBS_NumStr = JIBS_tmp_str[1] + JIBS_NumStr.substring(4);
                WS_EDIT_AP_BAT = Long.parseLong(JIBS_NumStr);
                JIBS_tmp_str[0] = "" + WS_EDIT_AP_BAT;
                JIBS_f0 = "";
                for (int i=0;i<12-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                JIBS_NumStr = JIBS_f0 + WS_EDIT_AP_BAT;
                JIBS_tmp_str[1] = "" + SCCGWA.COMM_AREA.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(5 + JIBS_tmp_str[1].length() - 1);
                WS_EDIT_AP_BAT = Long.parseLong(JIBS_NumStr);
                SCCBSPS.AP_BATNO = WS_EDIT_AP_BAT;
            }
        } else {
            JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            SCCBSPS.AP_TYPE = JIBS_tmp_str[0].substring(3 - 1, 3 + 6 - 1);
            SCCBSPS.AP_BATNO = SCCGWA.COMM_AREA.JRN_NO;
            if (SCRCWA.AC_DATE_AREA[1-1].AC_DATE != SCRCWA.AC_DATE_AREA[2-1].AC_DATE) {
                SCCBSPS.AP_TYPE = "EXPAPP";
                JIBS_tmp_str[0] = "" + WS_EDIT_AP_BAT;
                JIBS_f0 = "";
                for (int i=0;i<12-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                JIBS_NumStr = JIBS_f0 + WS_EDIT_AP_BAT;
                JIBS_tmp_str[1] = "" + 7777;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                JIBS_NumStr = JIBS_tmp_str[1] + JIBS_NumStr.substring(4);
                WS_EDIT_AP_BAT = Long.parseLong(JIBS_NumStr);
                JIBS_tmp_str[0] = "" + WS_EDIT_AP_BAT;
                JIBS_f0 = "";
                for (int i=0;i<12-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                JIBS_NumStr = JIBS_f0 + WS_EDIT_AP_BAT;
                JIBS_tmp_str[1] = "" + SCCGWA.COMM_AREA.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(5 + 8 - 1);
                WS_EDIT_AP_BAT = Long.parseLong(JIBS_NumStr);
                SCCBSPS.AP_BATNO = WS_EDIT_AP_BAT;
            }
        }
        CEP.TRC(SCCGWA, SCCBSPS.AP_BATNO);
        S000_CALL_SCZBSPS();
        if (pgmRtn) return;
    }
    public void R000_GET_INT_BAL_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRBALH);
        DDRBALH.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRBALH.END_DATE = WS_BACK_DATE;
        T000_GROUP_DDTBALH_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRBALH.END_DATE);
        WS_INT_BAL_DATE = DDRBALH.KEY.STR_DATE;
        CEP.TRC(SCCGWA, WS_INT_BAL_DATE);
    }
    public void T000_GROUP_DDTBALH_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRBALH.KEY.AC);
        CEP.TRC(SCCGWA, DDRBALH.KEY.STR_DATE);
        DDTBALH_RD = new DBParm();
        DDTBALH_RD.TableName = "DDTBALH";
        DDTBALH_RD.set = "WS-MAX-AC-DATE=IFNULL(MAX(STR_DATE),0)";
        DDTBALH_RD.where = "END_DATE > :DDRBALH.END_DATE "
            + "AND AC = :DDRBALH.KEY.AC";
        IBS.GROUP(SCCGWA, DDRBALH, this, DDTBALH_RD);
        CEP.TRC(SCCGWA, WS_MAX_AC_DATE);
        DDRBALH.KEY.STR_DATE = WS_MAX_AC_DATE;
    }
    public void T000_STARTBR_DDTBACK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRBACK);
        DDRBACK.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRBACK.DO_FLAG = 'N';
        if (DDCIBACK.AC_DATE > 0) {
            DDRBACK.KEY.AC_DATE = DDCIBACK.AC_DATE;
        } else {
            DDRBACK.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        CEP.TRC(SCCGWA, DDRBACK.KEY.AC_DATE);
        CEP.TRC(SCCGWA, DDRBACK.KEY.AC);
        CEP.TRC(SCCGWA, DDRBACK.DO_FLAG);
        DDTBACK_BR.rp = new DBParm();
        DDTBACK_BR.rp.TableName = "DDTBACK";
        DDTBACK_BR.rp.where = "AC = :DDRBACK.KEY.AC "
            + "AND DO_FLAG = :DDRBACK.DO_FLAG "
            + "AND AC_DATE >= :DDRBACK.KEY.AC_DATE";
        IBS.STARTBR(SCCGWA, DDRBACK, this, DDTBACK_BR);
    }
    public void T000_READNEXT_DDTBACK() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRBACK, this, DDTBACK_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_EOF_BACK_FLG = 'N';
        } else {
            WS_EOF_BACK_FLG = 'Y';
        }
    }
    public void T000_ENDBR_DDTBACK() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTBACK_BR);
    }
    public void T000_READ_DDTBACK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRBACK.KEY.AC);
        CEP.TRC(SCCGWA, DDRBACK.DO_FLAG);
        DDTBACK_RD = new DBParm();
        DDTBACK_RD.TableName = "DDTBACK";
        DDTBACK_RD.where = "AC = :DDRBACK.KEY.AC "
            + "AND DO_FLAG = :DDRBACK.DO_FLAG";
        DDTBACK_RD.fst = true;
        IBS.READ(SCCGWA, DDRBACK, this, DDTBACK_RD);
    }
    public void T000_READUPD_DDTBACK() throws IOException,SQLException,Exception {
        DDTBACK_RD = new DBParm();
        DDTBACK_RD.TableName = "DDTBACK";
        DDTBACK_RD.where = "AC = :DDRBACK.KEY.AC "
            + "AND AC_DATE = :DDRBACK.KEY.AC_DATE "
            + "AND VALUE_DATE = :DDRBACK.KEY.VALUE_DATE "
            + "AND TR_JRN = :DDRBACK.KEY.TR_JRN";
        DDTBACK_RD.upd = true;
        IBS.READ(SCCGWA, DDRBACK, this, DDTBACK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BACK_FOUND_FLG = 'Y';
        } else {
            WS_BACK_FOUND_FLG = 'N';
        }
    }
    public void T000_WRITE_DDTBACK() throws IOException,SQLException,Exception {
        DDTBACK_RD = new DBParm();
        DDTBACK_RD.TableName = "DDTBACK";
        IBS.WRITE(SCCGWA, DDRBACK, DDTBACK_RD);
    }
    public void T000_REWRITE_DDTBACK() throws IOException,SQLException,Exception {
        DDRBACK.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTBACK_RD = new DBParm();
        DDTBACK_RD.TableName = "DDTBACK";
        IBS.REWRITE(SCCGWA, DDRBACK, DDTBACK_RD);
    }
    public void T000_DELETE_DDTBACK() throws IOException,SQLException,Exception {
        DDTBACK_RD = new DBParm();
        DDTBACK_RD.TableName = "DDTBACK";
        IBS.DELETE(SCCGWA, DDRBACK, DDTBACK_RD);
    }
    public void T000_STARTBR_DDTBALH() throws IOException,SQLException,Exception {
        R000_GET_INT_BAL_DATE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRBALH);
        DDRBALH.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRBALH.KEY.STR_DATE = WS_INT_BAL_DATE;
        DDRBALH.END_DATE = WS_INT_BAL_DATE;
        CEP.TRC(SCCGWA, DDRBALH.KEY.AC);
        DDTBALH_BR.rp = new DBParm();
        DDTBALH_BR.rp.TableName = "DDTBALH";
        DDTBALH_BR.rp.eqWhere = "AC";
        DDTBALH_BR.rp.where = "STR_DATE >= :DDRBALH.KEY.STR_DATE "
            + "AND END_DATE <= :DDRBALH.END_DATE";
        IBS.STARTBR(SCCGWA, DDRBALH, this, DDTBALH_BR);
    }
    public void T000_READNEXT_DDTBALH() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRBALH, this, DDTBALH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_EOF_BALH_FLG = 'N';
        } else {
            WS_EOF_BALH_FLG = 'Y';
        }
    }
    public void T000_ENDBR_DDTBALH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTBALH_BR);
    }
    public void T000_READ_DDTDREG() throws IOException,SQLException,Exception {
        DDTDREG_RD = new DBParm();
        DDTDREG_RD.TableName = "DDTDREG";
        IBS.READ(SCCGWA, DDRDREG, DDTDREG_RD);
    }
    public void T000_WRITE_DDTBSP88() throws IOException,SQLException,Exception {
        DDTBSP88_RD = new DBParm();
        DDTBSP88_RD.TableName = "DDTBSP88";
        IBS.WRITE(SCCGWA, DDRBSP88, DDTBSP88_RD);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCIBACK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIBDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_CHK_BACK_DATE, DDCIBDCK);
        CEP.TRC(SCCGWA, DDCIBDCK.RC);
        if (DDCIBDCK.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCIBDCK.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCIBACK.RC);
        }
    }
    public void S000_CALL_BPZPMGBP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-MAIN-GBPA", BPCOMGBP);
        CEP.TRC(SCCGWA, BPCOMGBP.RC);
    }
    public void S000_CALL_SCZBSPS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-S-GET-BSP-INF", SCCBSPS);
    }
    public void S000_CALL_SCZOBSP() throws IOException,SQLException,Exception {
        SCZOBSP SCZOBSP = new SCZOBSP();
        SCZOBSP.MP(SCCGWA, SCCBSP);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_LINK_BSP_ERR, DDCIBACK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCIBACK.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCIBACK=");
            CEP.TRC(SCCGWA, DDCIBACK);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
