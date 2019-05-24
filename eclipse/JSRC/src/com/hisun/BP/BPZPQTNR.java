package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQTNR {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_PARM_MAINTAIN = "BP-PARM-MAINTAIN    ";
    String CPN_PARM_BRW = "BP-PARM-BROWSE      ";
    String PGM_SCSSCLDT = "SCSSCLDT";
    BPZPQTNR_WS_TEMP_BANK WS_TEMP_BANK = new BPZPQTNR_WS_TEMP_BANK();
    short WS_CNT = 0;
    short WS_I = 0;
    long WS_MIN_DAYS_TOT = 0;
    long WS_MAX_DAYS_TOT = 0;
    long WS_TEMP_DAYS = 0;
    short WS_MIN_TRM = 0;
    short WS_MAX_TRM = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_TBL_TENOR_FLAG = ' ';
    char WS_REC_FND_FLG = ' ';
    char WS_CD_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTENOR BPRTENOR = new BPRTENOR();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMB BPCPRMB = new BPCPRMB();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCGWA SCCGWA;
    BPCPQTNR BPCPQTNR;
    public void MP(SCCGWA SCCGWA, BPCPQTNR BPCPQTNR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQTNR = BPCPQTNR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQTNR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_TENOR_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CPN INPUT DATA:");
        CEP.TRC(SCCGWA, BPCPQTNR.TYPE);
        CEP.TRC(SCCGWA, BPCPQTNR.EFF_DT);
        CEP.TRC(SCCGWA, BPCPQTNR.EXP_DT);
        CEP.TRC(SCCGWA, BPCPQTNR.DAYS);
        CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.CODE_INFO[1-1].CODE);
        if (BPCPQTNR.TYPE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TENOR_TYP_MUST_INPUT, BPCPQTNR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPQTNR.TYPE.trim().length() == 0 
            && BPCPQTNR.DAT_TXT.CODE_INFO[1-1].CODE.trim().length() == 0) {
            if (BPCPQTNR.DAYS == 0 
                && (BPCPQTNR.EFF_DT == 0 
                || BPCPQTNR.EXP_DT == 0)) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPQTNR.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCPQTNR.DAT_TXT.CODE_INFO[1-1].CODE.trim().length() > 0) {
            if (BPCPQTNR.DAYS != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPQTNR.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPCPQTNR.EFF_DT != 0 
                || BPCPQTNR.EXP_DT != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPQTNR.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCPQTNR.EFF_DT != 0 
            && BPCPQTNR.EXP_DT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_EXP_INPUT_ERR, BPCPQTNR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPQTNR.EXP_DT != 0 
            && BPCPQTNR.EFF_DT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_EXP_INPUT_ERR, BPCPQTNR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPQTNR.DAYS != 0 
            && (BPCPQTNR.EFF_DT != 0 
            || BPCPQTNR.EXP_DT != 0)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPQTNR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "CHECK EFF-DT < EXP-DT");
        if (BPCPQTNR.EFF_DT != 0 
            && BPCPQTNR.EXP_DT != 0 
            && BPCPQTNR.EFF_DT >= BPCPQTNR.EXP_DT) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_EXP_INPUT_ERR, BPCPQTNR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "CHECK PROC END");
    }
    public void B020_GET_TENOR_INFO() throws IOException,SQLException,Exception {
        if (BPCPQTNR.TYPE.trim().length() > 0) {
            B020_01_GET_TENOR_INFO();
            if (pgmRtn) return;
        } else {
            B020_02_GET_TENOR_INFO();
            if (pgmRtn) return;
        }
    }
    public void B020_01_GET_TENOR_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMM);
        BPRPRMT.KEY.TYP = "TENOR";
        BPRPRMT.KEY.CD = BPCPQTNR.TYPE;
        BPCPRMM.FUNC = '3';
        CEP.TRC(SCCGWA, BPCPQTNR.TYPE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
        if (BPCPQTNR.DAT_TXT.CODE_INFO[1-1].CODE.trim().length() == 0) {
            R000_GET_TNR_CD_WITH_DAYS_TYP();
            if (pgmRtn) return;
        } else {
            R000_GET_TNR_CD_WITH_CODE_TYP();
            if (pgmRtn) return;
        }
    }
    public void B020_02_GET_TENOR_INFO() throws IOException,SQLException,Exception {
        if (BPCPQTNR.DAT_TXT.CODE_INFO[1-1].CODE.trim().length() == 0) {
            R000_GET_TNR_CD_WITH_DAYS();
            if (pgmRtn) return;
        } else {
            R000_GET_TNR_CD_WITH_CODE();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_TNR_CD_WITH_DAYS_TYP() throws IOException,SQLException,Exception {
        if (BPCPQTNR.DAYS != 0) {
            WS_TEMP_DAYS = BPCPQTNR.DAYS;
        } else {
            if (BPCPQTNR.EFF_DT != 0 
                && BPCPQTNR.EXP_DT != 0) {
                SCCCLDT.DATE1 = BPCPQTNR.EFF_DT;
                SCCCLDT.DATE2 = BPCPQTNR.EXP_DT;
                SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
                SCSSCLDT1.MP(SCCGWA, SCCCLDT);
                WS_TEMP_DAYS = SCCCLDT.DAYS;
                CEP.TRC(SCCGWA, BPCPQTNR.EFF_DT);
                CEP.TRC(SCCGWA, BPCPQTNR.EXP_DT);
                CEP.TRC(SCCGWA, WS_TEMP_DAYS);
            }
        }
        CEP.TRC(SCCGWA, WS_TEMP_DAYS);
        if (WS_TEMP_DAYS == 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQTNR.DAT_TXT);
        } else {
            IBS.init(SCCGWA, BPRTENOR);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRTENOR.DATA_TXT);
            if (BPRTENOR.DATA_TXT.DATE_CHECK == 'Y' 
                && BPCPQTNR.EFF_DT != 0 
                && BPCPQTNR.EFF_DT != SCCGWA.COMM_AREA.AC_DATE) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DATE_CHK_ERROR, BPCPQTNR.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                T000_GET_TENOR_CODE();
                if (pgmRtn) return;
                if (WS_CD_FND_FLG == 'N') {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NO_MCH_CD_IN_THE_TYP, BPCPQTNR.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
        BPCPQTNR.DESC = BPRPRMT.DESC;
        BPCPQTNR.CDESC = BPRPRMT.CDESC;
        CEP.TRC(SCCGWA, "11111111 OUTPUT DATA:");
        CEP.TRC(SCCGWA, BPCPQTNR.TYPE);
        CEP.TRC(SCCGWA, BPCPQTNR.DESC);
        CEP.TRC(SCCGWA, BPCPQTNR.CDESC);
        CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.DATE_CHECK);
        for (WS_I = 1; WS_I <= 20; WS_I += 1) {
            CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.CODE_INFO[WS_I-1].CODE);
            CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.CODE_INFO[WS_I-1].MIN_TRM);
            CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.CODE_INFO[WS_I-1].MIN_TRM_U);
            CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.CODE_INFO[WS_I-1].MAX_TRM);
            CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.CODE_INFO[WS_I-1].MAX_TRM_U);
        }
    }
    public void R000_GET_TNR_CD_WITH_CODE_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTENOR);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRTENOR.DATA_TXT);
        WS_CD_FND_FLG = 'N';
        for (WS_I = 1; WS_I <= 20 
            && WS_CD_FND_FLG != 'Y'; WS_I += 1) {
            if (BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].CODE.equalsIgnoreCase(BPCPQTNR.DAT_TXT.CODE_INFO[1-1].CODE)) {
                BPCPQTNR.DAT_TXT.DATE_CHECK = BPRTENOR.DATA_TXT.DATE_CHECK;
                BPCPQTNR.DAT_TXT.CODE_INFO[1-1].MIN_TRM = BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].MIN_TRM;
                BPCPQTNR.DAT_TXT.CODE_INFO[1-1].MIN_TRM_U = BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].MIN_TRM_U;
                BPCPQTNR.DAT_TXT.CODE_INFO[1-1].MAX_TRM = BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].MAX_TRM;
                BPCPQTNR.DAT_TXT.CODE_INFO[1-1].MAX_TRM_U = BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].MAX_TRM_U;
                WS_CD_FND_FLG = 'Y';
            }
        }
        if (WS_CD_FND_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NO_MCH_CD_IN_THE_TYP, BPCPQTNR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCPQTNR.DESC = BPRPRMT.DESC;
        BPCPQTNR.CDESC = BPRPRMT.CDESC;
        CEP.TRC(SCCGWA, "22222222 OUTPUT DATA:");
        CEP.TRC(SCCGWA, BPCPQTNR.TYPE);
        CEP.TRC(SCCGWA, BPCPQTNR.DESC);
        CEP.TRC(SCCGWA, BPCPQTNR.CDESC);
        CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.DATE_CHECK);
        CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.CODE_INFO[1-1].CODE);
        CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.CODE_INFO[1-1].MIN_TRM);
        CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.CODE_INFO[1-1].MIN_TRM_U);
        CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.CODE_INFO[1-1].MAX_TRM);
        CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.CODE_INFO[1-1].MAX_TRM_U);
    }
    public void R000_GET_TNR_CD_WITH_DAYS() throws IOException,SQLException,Exception {
        if (BPCPQTNR.DAYS != 0) {
            WS_TEMP_DAYS = BPCPQTNR.DAYS;
        } else {
            if (BPCPQTNR.EFF_DT != 0 
                && BPCPQTNR.EXP_DT != 0) {
                SCCCLDT.DATE1 = BPCPQTNR.EFF_DT;
                SCCCLDT.DATE2 = BPCPQTNR.EXP_DT;
                SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
                SCSSCLDT2.MP(SCCGWA, SCCCLDT);
                WS_TEMP_DAYS = SCCCLDT.DAYS;
                CEP.TRC(SCCGWA, BPCPQTNR.EFF_DT);
                CEP.TRC(SCCGWA, BPCPQTNR.EXP_DT);
                CEP.TRC(SCCGWA, WS_TEMP_DAYS);
            }
        }
        IBS.init(SCCGWA, BPRTENOR);
        IBS.init(SCCGWA, BPCPRMB);
        BPRTENOR.KEY.TYP = "TENOR";
        BPCPRMB.FUNC = '0';
        BPCPRMB.DAT_PTR = BPRTENOR;
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        WS_REC_FND_FLG = 'N';
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
        for (WS_CNT = 1; WS_CNT <= 999 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND) 
            && WS_REC_FND_FLG != 'Y'; WS_CNT += 1) {
            IBS.init(SCCGWA, BPCPRMB.RC);
            BPCPRMB.FUNC = '1';
            BPCPRMB.DAT_PTR = BPRTENOR;
            S000_CALL_BPZPRMB();
            if (pgmRtn) return;
            if (BPRTENOR.DATA_TXT.DATE_CHECK == 'Y' 
                && BPCPQTNR.EFF_DT != 0 
                && BPCPQTNR.EFF_DT != SCCGWA.COMM_AREA.AC_DATE) {
                CEP.TRC(SCCGWA, "DATE CHECK NOT MATCH");
            } else {
                T000_GET_TENOR_CODE();
                if (pgmRtn) return;
                if (WS_CD_FND_FLG == 'Y') {
                    BPCPQTNR.TYPE = BPRTENOR.KEY.TYPE;
                    BPCPQTNR.DESC = BPRTENOR.DESC;
                    BPCPQTNR.CDESC = BPRTENOR.CDESC;
                    WS_REC_FND_FLG = 'Y';
                }
            }
        }
        CEP.TRC(SCCGWA, WS_CD_FND_FLG);
        if (WS_CD_FND_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NO_MCH_CD_IN_ALL_TYP, BPCPQTNR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "STARTBR");
        IBS.init(SCCGWA, BPCPRMB.RC);
        BPCPRMB.FUNC = '2';
        BPCPRMB.DAT_PTR = BPRTENOR;
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "33333333 OUTPUT DATA:");
        CEP.TRC(SCCGWA, BPCPQTNR.TYPE);
        CEP.TRC(SCCGWA, BPCPQTNR.DESC);
        CEP.TRC(SCCGWA, BPCPQTNR.CDESC);
        CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.DATE_CHECK);
        CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.CODE_INFO[1-1].CODE);
        CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.CODE_INFO[1-1].MIN_TRM);
        CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.CODE_INFO[1-1].MIN_TRM_U);
        CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.CODE_INFO[1-1].MAX_TRM);
        CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.CODE_INFO[1-1].MAX_TRM_U);
    }
    public void R000_GET_TNR_CD_WITH_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTENOR);
        IBS.init(SCCGWA, BPCPRMB);
        BPRTENOR.KEY.TYP = "TENOR";
        BPCPRMB.FUNC = '0';
        BPCPRMB.DAT_PTR = BPRTENOR;
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        WS_REC_FND_FLG = 'N';
        for (WS_CNT = 1; WS_CNT <= 999 
            && WS_REC_FND_FLG != 'Y'; WS_CNT += 1) {
            IBS.init(SCCGWA, BPCPRMB.RC);
            BPCPRMB.FUNC = '1';
            BPCPRMB.DAT_PTR = BPRTENOR;
            S000_CALL_BPZPRMB();
            if (pgmRtn) return;
            WS_CD_FND_FLG = 'N';
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
            for (WS_I = 1; WS_I <= 20 
                && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND) 
                && WS_CD_FND_FLG != 'Y'; WS_I += 1) {
                if (BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].CODE.equalsIgnoreCase(BPCPQTNR.DAT_TXT.CODE_INFO[1-1].CODE)) {
                    BPCPQTNR.TYPE = BPRTENOR.KEY.TYPE;
                    BPCPQTNR.DESC = BPRTENOR.DESC;
                    BPCPQTNR.CDESC = BPRTENOR.CDESC;
                    BPCPQTNR.DAT_TXT.DATE_CHECK = BPRTENOR.DATA_TXT.DATE_CHECK;
                    BPCPQTNR.DAT_TXT.CODE_INFO[1-1].MIN_TRM = BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].MIN_TRM;
                    BPCPQTNR.DAT_TXT.CODE_INFO[1-1].MIN_TRM_U = BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].MIN_TRM_U;
                    BPCPQTNR.DAT_TXT.CODE_INFO[1-1].MAX_TRM = BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].MAX_TRM;
                    BPCPQTNR.DAT_TXT.CODE_INFO[1-1].MAX_TRM_U = BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].MAX_TRM_U;
                    WS_CD_FND_FLG = 'Y';
                    WS_REC_FND_FLG = 'Y';
                }
            }
        }
        CEP.TRC(SCCGWA, WS_CD_FND_FLG);
        if (WS_CD_FND_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NO_MCH_CD_IN_ALL_TYP, BPCPQTNR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "STARTBR");
        IBS.init(SCCGWA, BPCPRMB.RC);
        BPCPRMB.FUNC = '2';
        BPCPRMB.DAT_PTR = BPRTENOR;
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "44444444 OUTPUT DATA:");
        CEP.TRC(SCCGWA, BPCPQTNR.TYPE);
        CEP.TRC(SCCGWA, BPCPQTNR.DESC);
        CEP.TRC(SCCGWA, BPCPQTNR.CDESC);
        CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.DATE_CHECK);
        CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.CODE_INFO[1-1].CODE);
        CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.CODE_INFO[1-1].MIN_TRM);
        CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.CODE_INFO[1-1].MIN_TRM_U);
        CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.CODE_INFO[1-1].MAX_TRM);
        CEP.TRC(SCCGWA, BPCPQTNR.DAT_TXT.CODE_INFO[1-1].MAX_TRM_U);
    }
    public void T000_GET_TENOR_CODE() throws IOException,SQLException,Exception {
        WS_CD_FND_FLG = 'N';
        for (WS_I = 1; WS_I <= 20 
            && WS_CD_FND_FLG != 'Y'; WS_I += 1) {
            if (BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].MIN_TRM_U == 'D') {
                WS_MIN_DAYS_TOT = BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].MIN_TRM;
            } else if (BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].MIN_TRM_U == 'M') {
                WS_MIN_TRM = BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].MIN_TRM;
                WS_MIN_DAYS_TOT = WS_MIN_TRM * 30;
            } else if (BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].MIN_TRM_U == 'Y') {
                WS_MIN_TRM = BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].MIN_TRM;
                WS_MIN_DAYS_TOT = WS_MIN_TRM * 365;
            }
            if (BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].MAX_TRM_U == 'D') {
                WS_MAX_DAYS_TOT = BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].MAX_TRM;
            } else if (BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].MAX_TRM_U == 'M') {
                WS_MAX_TRM = BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].MAX_TRM;
                WS_MAX_DAYS_TOT = WS_MAX_TRM * 30;
            } else if (BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].MAX_TRM_U == 'Y') {
                WS_MAX_TRM = BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].MAX_TRM;
                WS_MAX_DAYS_TOT = WS_MAX_TRM * 365;
            }
            CEP.TRC(SCCGWA, WS_MIN_DAYS_TOT);
            CEP.TRC(SCCGWA, WS_MAX_DAYS_TOT);
            if (WS_TEMP_DAYS >= WS_MIN_DAYS_TOT 
                && WS_TEMP_DAYS <= WS_MAX_DAYS_TOT) {
                BPCPQTNR.DAT_TXT.DATE_CHECK = BPRTENOR.DATA_TXT.DATE_CHECK;
                BPCPQTNR.DAT_TXT.CODE_INFO[1-1].CODE = BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].CODE;
                BPCPQTNR.DAT_TXT.CODE_INFO[1-1].MIN_TRM = BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].MIN_TRM;
                BPCPQTNR.DAT_TXT.CODE_INFO[1-1].MIN_TRM_U = BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].MIN_TRM_U;
                BPCPQTNR.DAT_TXT.CODE_INFO[1-1].MAX_TRM = BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].MAX_TRM;
                BPCPQTNR.DAT_TXT.CODE_INFO[1-1].MAX_TRM_U = BPRTENOR.DATA_TXT.CODE_DETAIL[WS_I-1].MAX_TRM_U;
                WS_CD_FND_FLG = 'Y';
            }
            WS_MIN_DAYS_TOT = 0;
            WS_MAX_DAYS_TOT = 0;
        }
    }
    public void S000_CALL_SCZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_MAINTAIN, BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQTNR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_BRW, BPCPRMB);
        if (BPCPRMB.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQTNR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQTNR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQTNR = ");
            CEP.TRC(SCCGWA, BPCPQTNR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
