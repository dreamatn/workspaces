package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.LN.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSIRLN {
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTIRPR_RD;
    boolean pgmRtn = false;
    String K_PRDPR_TYPE = "PRDPR";
    String K_OUT_FMT = "DC941";
    String K_HIS_CPB_NM = "DCCIRPRD";
    String K_HIS_RMKS = "IR(DEPOSIT FOR LOANS) PRD PARM MAINTENANCE";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    char MOD_TO_ADD_FLG = '0';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DCCRPRDO DCCRPRDO = new DCCRPRDO();
    DCCARPRD DCCRPRDM = new DCCARPRD();
    DCCARPRD DCCRPRDN = new DCCARPRD();
    DCCIRPRD DCCIRPRD = new DCCIRPRD();
    LNRCTLPM LNRCTLPM = new LNRCTLPM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCRIRPR DCRIRPR = new DCRIRPR();
    SCCGWA SCCGWA;
    DCCSIRLN DCCSIRLN;
    public void MP(SCCGWA SCCGWA, DCCSIRLN DCCSIRLN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSIRLN = DCCSIRLN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSIRLN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "************INPUT************");
        CEP.TRC(SCCGWA, DCCSIRLN.IBS_AC_BK);
        CEP.TRC(SCCGWA, DCCSIRLN.FUNC);
        CEP.TRC(SCCGWA, DCCSIRLN.DATA.KEY.PRDMO_CD);
        CEP.TRC(SCCGWA, DCCSIRLN.DATA.KEY.PROD_CODE);
        CEP.TRC(SCCGWA, DCCSIRLN.DATA.PROD_NM);
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            CEP.TRC(SCCGWA, DCCSIRLN.DATA.CCY_INF[WS_I-1].CCY);
            CEP.TRC(SCCGWA, DCCSIRLN.DATA.CCY_INF[WS_I-1].CCY_TYP);
        }
        CEP.TRC(SCCGWA, DCCSIRLN.DATA.LN_MTH);
        CEP.TRC(SCCGWA, DCCSIRLN.DATA.LN_PER);
        CEP.TRC(SCCGWA, DCCSIRLN.DATA.DD_PER);
        CEP.TRC(SCCGWA, DCCSIRLN.DATA.TERM);
        CEP.TRC(SCCGWA, DCCSIRLN.DATA.STRAMT);
        CEP.TRC(SCCGWA, DCCSIRLN.DATA.STRDT);
        CEP.TRC(SCCGWA, DCCSIRLN.DATA.EXPDT);
        if (DCCSIRLN.FUNC == 'Q') {
            B010_CHECK_INPUT_DATA1();
            if (pgmRtn) return;
            B030_QUERY_PROCESS();
            if (pgmRtn) return;
            B130_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSIRLN.FUNC == 'A') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B050_ADD_PROCESS();
            if (pgmRtn) return;
            B110_WRITE_HIS_PROC();
            if (pgmRtn) return;
            B130_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSIRLN.FUNC == 'M') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B070_MODIFY_PROCESS();
            if (pgmRtn) return;
            B110_WRITE_HIS_PROC();
            if (pgmRtn) return;
            B130_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSIRLN.FUNC == 'D') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B090_DELETE_PROCESS();
            if (pgmRtn) return;
            B110_WRITE_HIS_PROC();
            if (pgmRtn) return;
            B130_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DCCSIRLN.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCSIRLN.DATA.KEY.PROD_CODE.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSIRLN.DATA.STRDT == 0) {
            DCCSIRLN.DATA.STRDT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (DCCSIRLN.FUNC == 'A') {
            if (DCCSIRLN.DATA.STRDT < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_EFF_DT_GT_AC_DT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSIRLN.DATA.EXPDT == 0) {
            DCCSIRLN.DATA.EXPDT = 99991231;
        }
        if (DCCSIRLN.DATA.EXPDT < SCCGWA.COMM_AREA.AC_DATE 
            || DCCSIRLN.DATA.EXPDT == SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_END_DT_LESS_AC_DT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSIRLN.DATA.EXPDT < DCCSIRLN.DATA.STRDT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_EFF_MUST_LT_EXP_DT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSIRLN.DATA.CI_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CI_TYP_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSIRLN.DATA.CI_TYP != '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CI_TYP_NOT_SPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSIRLN.DATA.KEY.PROD_CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_CODE_MISSING, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
            IBS.init(SCCGWA, DCRIRPR);
            DCRIRPR.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
            DCRIRPR.KEY.PRDMO_CD = DCCSIRLN.DATA.KEY.PRDMO_CD;
            DCRIRPR.KEY.PARM_CODE = DCCSIRLN.DATA.KEY.PROD_CODE;
            DCRIRPR.KEY.EFF_DATE = DCCSIRLN.DATA.STRDT;
            T000_READ_DCTIRPR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCSIRLN.FUNC);
            if (DCCSIRLN.FUNC == 'A') {
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPT_PRD_INVALID;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else if (DCCSIRLN.FUNC == 'M') {
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    MOD_TO_ADD_FLG = '1';
                }
            } else if (DCCSIRLN.FUNC == 'D') {
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PRD_NOT_INVALID;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
            }
        }
    }
    public void B010_CHECK_INPUT_DATA1() throws IOException,SQLException,Exception {
        if (DCCSIRLN.IBS_AC_BK.trim().length() == 0) {
            DCCSIRLN.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        }
        if (DCCSIRLN.DATA.KEY.PRDMO_CD.trim().length() == 0) {
            DCCSIRLN.DATA.KEY.PRDMO_CD = "IRLN";
        }
        if (DCCSIRLN.DATA.KEY.PROD_CODE.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSIRLN.DATA.STRDT == 0) {
            DCCSIRLN.DATA.STRDT = SCCGWA.COMM_AREA.AC_DATE;
        }
    }
    public void B030_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIRPR);
        DCRIRPR.KEY.IBS_AC_BK = DCCSIRLN.IBS_AC_BK;
        DCRIRPR.KEY.PRDMO_CD = DCCSIRLN.DATA.KEY.PRDMO_CD;
        DCRIRPR.KEY.PARM_CODE = DCCSIRLN.DATA.KEY.PROD_CODE;
        DCRIRPR.KEY.EFF_DATE = DCCSIRLN.DATA.STRDT;
        R000_STARTBR_FIRST_DCTIRPR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRIRPR.LN_MTH);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PRD_NOT_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            S000_IRPR_DATA();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCIRPRD.LN_MTH);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCIRPRD);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSIRLN.DATA);
            CEP.TRC(SCCGWA, DCCSIRLN.DATA.LN_MTH);
        }
    }
    public void B050_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIRPR);
        DCRIRPR.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        DCRIRPR.KEY.PRDMO_CD = DCCSIRLN.DATA.KEY.PRDMO_CD;
        DCRIRPR.KEY.PARM_CODE = DCCSIRLN.DATA.KEY.PROD_CODE;
        DCRIRPR.KEY.EFF_DATE = DCCSIRLN.DATA.STRDT;
        DCRIRPR.EXP_DATE = DCCSIRLN.DATA.EXPDT;
        DCRIRPR.PROD_NM = DCCSIRLN.DATA.PROD_NM;
        DCRIRPR.CI_TYP = DCCSIRLN.DATA.CI_TYP;
        DCRIRPR.CCY1 = DCCSIRLN.DATA.CCY_INF[1-1].CCY;
        DCRIRPR.CCY_TYP1 = DCCSIRLN.DATA.CCY_INF[1-1].CCY_TYP;
        DCRIRPR.CCY2 = DCCSIRLN.DATA.CCY_INF[2-1].CCY;
        DCRIRPR.CCY_TYP2 = DCCSIRLN.DATA.CCY_INF[2-1].CCY_TYP;
        DCRIRPR.CCY3 = DCCSIRLN.DATA.CCY_INF[3-1].CCY;
        DCRIRPR.CCY_TYP3 = DCCSIRLN.DATA.CCY_INF[3-1].CCY_TYP;
        DCRIRPR.CCY4 = DCCSIRLN.DATA.CCY_INF[4-1].CCY;
        DCRIRPR.CCY_TYP4 = DCCSIRLN.DATA.CCY_INF[4-1].CCY_TYP;
        DCRIRPR.CCY5 = DCCSIRLN.DATA.CCY_INF[5-1].CCY;
        DCRIRPR.CCY_TYP5 = DCCSIRLN.DATA.CCY_INF[5-1].CCY_TYP;
        DCRIRPR.CCY6 = DCCSIRLN.DATA.CCY_INF[6-1].CCY;
        DCRIRPR.CCY_TYP6 = DCCSIRLN.DATA.CCY_INF[6-1].CCY_TYP;
        DCRIRPR.CCY7 = DCCSIRLN.DATA.CCY_INF[7-1].CCY;
        DCRIRPR.CCY_TYP7 = DCCSIRLN.DATA.CCY_INF[7-1].CCY_TYP;
        DCRIRPR.CCY8 = DCCSIRLN.DATA.CCY_INF[8-1].CCY;
        DCRIRPR.CCY_TYP8 = DCCSIRLN.DATA.CCY_INF[8-1].CCY_TYP;
        DCRIRPR.CCY9 = DCCSIRLN.DATA.CCY_INF[9-1].CCY;
        DCRIRPR.CCY_TYP9 = DCCSIRLN.DATA.CCY_INF[9-1].CCY_TYP;
        DCRIRPR.CCY10 = DCCSIRLN.DATA.CCY_INF[10-1].CCY;
        DCRIRPR.CCY_TYP10 = DCCSIRLN.DATA.CCY_INF[10-1].CCY_TYP;
        DCRIRPR.LN_MTH = DCCSIRLN.DATA.LN_MTH;
        DCRIRPR.LN_PER = DCCSIRLN.DATA.LN_PER;
        DCRIRPR.DD_PER = DCCSIRLN.DATA.DD_PER;
        DCRIRPR.TERM = DCCSIRLN.DATA.TERM;
        DCRIRPR.STRAMT = DCCSIRLN.DATA.STRAMT;
        DCRIRPR.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIRPR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRIRPR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIRPR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        R000_TRANS_HIS_DATA_NEW();
        if (pgmRtn) return;
        T000_WRITE_DCTIRPR();
        if (pgmRtn) return;
    }
    public void B070_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIRPR);
        DCRIRPR.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        DCRIRPR.KEY.PRDMO_CD = DCCSIRLN.DATA.KEY.PRDMO_CD;
        DCRIRPR.KEY.PARM_CODE = DCCSIRLN.DATA.KEY.PROD_CODE;
        DCRIRPR.KEY.EFF_DATE = DCCSIRLN.DATA.STRDT;
        T000_READ_UPDATE_DCTIRPR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, MOD_TO_ADD_FLG);
        if (MOD_TO_ADD_FLG == '0') {
            DCRIRPR.EXP_DATE = DCCSIRLN.DATA.EXPDT;
            DCRIRPR.PROD_NM = DCCSIRLN.DATA.PROD_NM;
            DCRIRPR.CI_TYP = DCCSIRLN.DATA.CI_TYP;
            DCRIRPR.CCY1 = DCCSIRLN.DATA.CCY_INF[1-1].CCY;
            DCRIRPR.CCY_TYP1 = DCCSIRLN.DATA.CCY_INF[1-1].CCY_TYP;
            DCRIRPR.CCY2 = DCCSIRLN.DATA.CCY_INF[2-1].CCY;
            DCRIRPR.CCY_TYP2 = DCCSIRLN.DATA.CCY_INF[2-1].CCY_TYP;
            DCRIRPR.CCY3 = DCCSIRLN.DATA.CCY_INF[3-1].CCY;
            DCRIRPR.CCY_TYP3 = DCCSIRLN.DATA.CCY_INF[3-1].CCY_TYP;
            DCRIRPR.CCY4 = DCCSIRLN.DATA.CCY_INF[4-1].CCY;
            DCRIRPR.CCY_TYP4 = DCCSIRLN.DATA.CCY_INF[4-1].CCY_TYP;
            DCRIRPR.CCY5 = DCCSIRLN.DATA.CCY_INF[5-1].CCY;
            DCRIRPR.CCY_TYP5 = DCCSIRLN.DATA.CCY_INF[5-1].CCY_TYP;
            DCRIRPR.CCY6 = DCCSIRLN.DATA.CCY_INF[6-1].CCY;
            DCRIRPR.CCY_TYP6 = DCCSIRLN.DATA.CCY_INF[6-1].CCY_TYP;
            DCRIRPR.CCY7 = DCCSIRLN.DATA.CCY_INF[7-1].CCY;
            DCRIRPR.CCY_TYP7 = DCCSIRLN.DATA.CCY_INF[7-1].CCY_TYP;
            DCRIRPR.CCY8 = DCCSIRLN.DATA.CCY_INF[8-1].CCY;
            DCRIRPR.CCY9 = DCCSIRLN.DATA.CCY_INF[9-1].CCY;
            DCRIRPR.CCY_TYP9 = DCCSIRLN.DATA.CCY_INF[9-1].CCY_TYP;
            DCRIRPR.CCY10 = DCCSIRLN.DATA.CCY_INF[10-1].CCY;
            DCRIRPR.CCY_TYP10 = DCCSIRLN.DATA.CCY_INF[10-1].CCY_TYP;
            DCRIRPR.LN_MTH = DCCSIRLN.DATA.LN_MTH;
            DCRIRPR.LN_PER = DCCSIRLN.DATA.LN_PER;
            DCRIRPR.DD_PER = DCCSIRLN.DATA.DD_PER;
            DCRIRPR.TERM = DCCSIRLN.DATA.TERM;
            DCRIRPR.STRAMT = DCCSIRLN.DATA.STRAMT;
            DCRIRPR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRIRPR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            R000_TRANS_HIS_DATA_OLD();
            if (pgmRtn) return;
            R000_TRANS_HIS_DATA_NEW();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCRPRDM);
            CEP.TRC(SCCGWA, DCCRPRDN);
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, DCCRPRDM);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCRPRDN);
            if (JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0])) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DATA_NO_CHANGE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            T000_REWRITE_DCTIRPR();
            if (pgmRtn) return;
        }
    }
    public void B090_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIRPR);
        DCRIRPR.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        DCRIRPR.KEY.PRDMO_CD = DCCSIRLN.DATA.KEY.PRDMO_CD;
        DCRIRPR.KEY.PARM_CODE = DCCSIRLN.DATA.KEY.PROD_CODE;
        DCRIRPR.KEY.EFF_DATE = DCCSIRLN.DATA.STRDT;
        T000_READ_UPDATE_DCTIRPR();
        if (pgmRtn) return;
        DCRIRPR.EXP_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIRPR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIRPR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_IRPR_DATA();
        if (pgmRtn) return;
        R000_TRANS_HIS_DATA_OLD();
        if (pgmRtn) return;
        R000_TRANS_HIS_DATA_NEW();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCRPRDM);
        CEP.TRC(SCCGWA, DCCRPRDN);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, DCCRPRDM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCRPRDN);
        if (JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0])) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DATA_NO_CHANGE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_REWRITE_DCTIRPR();
        if (pgmRtn) return;
    }
    public void B110_WRITE_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.REF_NO = DCCSIRLN.DATA.KEY.PROD_CODE;
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPB_NM;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS;
        if (DCCSIRLN.FUNC == 'A'
            || DCCSIRLN.FUNC == 'M' 
                && MOD_TO_ADD_FLG == '1') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.NEW_DAT_PT = DCCRPRDN;
        } else if (DCCSIRLN.FUNC == 'M') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = DCCRPRDM;
            BPCPNHIS.INFO.NEW_DAT_PT = DCCRPRDN;
        } else if (DCCSIRLN.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.OLD_DAT_PT = DCCRPRDM;
        } else {
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B130_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCRPRDO);
        DCCRPRDO.FUNC = DCCSIRLN.FUNC;
        DCCRPRDO.PROD_CODE = DCCIRPRD.KEY.PROD_CODE;
        DCCRPRDO.PROD_NM = DCCIRPRD.PROD_NM;
        DCCRPRDO.CI_TYP = DCCIRPRD.CI_TYP;
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            DCCRPRDO.CCY_INF[WS_I-1].CCY = DCCIRPRD.CCY_INF[WS_I-1].CCY;
            DCCRPRDO.CCY_INF[WS_I-1].CCY_TYP = DCCIRPRD.CCY_INF[WS_I-1].CCY_TYP;
        }
        DCCRPRDO.LN_MTH = DCCIRPRD.LN_MTH;
        DCCRPRDO.LN_PER = DCCIRPRD.LN_PER;
        DCCRPRDO.DD_PER = DCCIRPRD.DD_PER;
        DCCRPRDO.TERM = DCCIRPRD.TERM;
        DCCRPRDO.STRAMT = DCCIRPRD.STRAMT;
        DCCRPRDO.STRDT = DCCIRPRD.STRDT;
        DCCRPRDO.EXPDT = DCCIRPRD.EXPDT;
        CEP.TRC(SCCGWA, "************OUTPUT************");
        CEP.TRC(SCCGWA, DCCRPRDO.FUNC);
        CEP.TRC(SCCGWA, DCCRPRDO.PROD_CODE);
        CEP.TRC(SCCGWA, DCCRPRDO.PROD_NM);
        CEP.TRC(SCCGWA, DCCRPRDO.CI_TYP);
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            CEP.TRC(SCCGWA, DCCRPRDO.CCY_INF[WS_I-1].CCY);
        }
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            CEP.TRC(SCCGWA, DCCRPRDO.CCY_INF[WS_I-1].CCY_TYP);
        }
        CEP.TRC(SCCGWA, DCCRPRDO.LN_MTH);
        CEP.TRC(SCCGWA, DCCRPRDO.LN_PER);
        CEP.TRC(SCCGWA, DCCRPRDO.DD_PER);
        CEP.TRC(SCCGWA, DCCRPRDO.TERM);
        CEP.TRC(SCCGWA, DCCRPRDO.STRAMT);
        CEP.TRC(SCCGWA, DCCRPRDO.STRDT);
        CEP.TRC(SCCGWA, DCCRPRDO.EXPDT);
        IBS.init(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "FORMAT OUTPUT");
        SCCFMT.FMTID = K_OUT_FMT;
        SCCFMT.DATA_PTR = DCCRPRDO;
        SCCFMT.DATA_LEN = 342;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_HIS_DATA_OLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCRPRDM);
        DCCRPRDM.KEY.PROD_CODE = DCRIRPR.KEY.PARM_CODE;
        DCCRPRDM.PROD_NM = DCRIRPR.PROD_NM;
        DCCRPRDM.STRDT = DCRIRPR.KEY.EFF_DATE;
        DCCRPRDM.EXPDT = DCRIRPR.EXP_DATE;
        DCCRPRDM.CI_TYP = DCRIRPR.CI_TYP;
        DCCRPRDM.CCY_INF[1-1].CCY = DCRIRPR.CCY1;
        DCCRPRDM.CCY_INF[1-1].CCY_TYP = DCRIRPR.CCY_TYP1;
        DCCRPRDM.CCY_INF[2-1].CCY = DCRIRPR.CCY2;
        DCCRPRDM.CCY_INF[2-1].CCY_TYP = DCRIRPR.CCY_TYP2;
        DCCRPRDM.CCY_INF[3-1].CCY = DCRIRPR.CCY3;
        DCCRPRDM.CCY_INF[3-1].CCY_TYP = DCRIRPR.CCY_TYP3;
        DCCRPRDM.CCY_INF[4-1].CCY = DCRIRPR.CCY4;
        DCCRPRDM.CCY_INF[4-1].CCY_TYP = DCRIRPR.CCY_TYP4;
        DCCRPRDM.CCY_INF[5-1].CCY = DCRIRPR.CCY5;
        DCCRPRDM.CCY_INF[5-1].CCY_TYP = DCRIRPR.CCY_TYP5;
        DCCRPRDM.CCY_INF[6-1].CCY = DCRIRPR.CCY6;
        DCCRPRDM.CCY_INF[6-1].CCY_TYP = DCRIRPR.CCY_TYP6;
        DCCRPRDM.CCY_INF[7-1].CCY = DCRIRPR.CCY7;
        DCCRPRDM.CCY_INF[7-1].CCY_TYP = DCRIRPR.CCY_TYP7;
        DCCRPRDM.CCY_INF[8-1].CCY = DCRIRPR.CCY8;
        DCCRPRDM.CCY_INF[8-1].CCY_TYP = DCRIRPR.CCY_TYP8;
        DCCRPRDM.CCY_INF[9-1].CCY = DCRIRPR.CCY9;
        DCCRPRDM.CCY_INF[9-1].CCY_TYP = DCRIRPR.CCY_TYP9;
        DCCRPRDM.CCY_INF[10-1].CCY = DCRIRPR.CCY10;
        DCCRPRDM.CCY_INF[10-1].CCY_TYP = DCRIRPR.CCY_TYP10;
        DCCRPRDM.LN_MTH = DCRIRPR.LN_MTH;
        DCCRPRDM.LN_PER = DCRIRPR.LN_PER;
        DCCRPRDM.DD_PER = DCRIRPR.DD_PER;
        DCCRPRDM.TERM = DCRIRPR.TERM;
        DCCRPRDM.STRAMT = DCRIRPR.STRAMT;
    }
    public void R000_TRANS_HIS_DATA_NEW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCRPRDN);
        DCCRPRDN.KEY.CON_MDEL = DCCSIRLN.DATA.KEY.PRDMO_CD;
        DCCRPRDN.KEY.PROD_CODE = DCCSIRLN.DATA.KEY.PROD_CODE;
        DCCRPRDN.PROD_NM = DCCSIRLN.DATA.PROD_NM;
        DCCRPRDN.STRDT = DCCSIRLN.DATA.STRDT;
        DCCRPRDN.EXPDT = DCCSIRLN.DATA.EXPDT;
        DCCRPRDN.CI_TYP = DCCSIRLN.DATA.CI_TYP;
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            DCCRPRDN.CCY_INF[WS_I-1].CCY = DCCSIRLN.DATA.CCY_INF[WS_I-1].CCY;
            DCCRPRDN.CCY_INF[WS_I-1].CCY_TYP = DCCSIRLN.DATA.CCY_INF[WS_I-1].CCY_TYP;
        }
        DCCRPRDN.LN_MTH = DCCSIRLN.DATA.LN_MTH;
        DCCRPRDN.LN_PER = DCCSIRLN.DATA.LN_PER;
        DCCRPRDN.DD_PER = DCCSIRLN.DATA.DD_PER;
        DCCRPRDN.TERM = DCCSIRLN.DATA.TERM;
        DCCRPRDN.STRAMT = DCCSIRLN.DATA.STRAMT;
        DCCRPRDN.STRDT = DCCSIRLN.DATA.STRDT;
        DCCRPRDN.EXPDT = DCCSIRLN.DATA.EXPDT;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_IRPR_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCIRPRD);
        DCCIRPRD.KEY.PROD_CODE = DCRIRPR.KEY.PARM_CODE;
        DCCIRPRD.PROD_NM = DCRIRPR.PROD_NM;
        DCCIRPRD.CI_TYP = DCRIRPR.CI_TYP;
        DCCIRPRD.CCY_INF[1-1].CCY = DCRIRPR.CCY1;
        DCCIRPRD.CCY_INF[1-1].CCY_TYP = DCRIRPR.CCY_TYP1;
        DCCIRPRD.CCY_INF[2-1].CCY = DCRIRPR.CCY2;
        DCCIRPRD.CCY_INF[2-1].CCY_TYP = DCRIRPR.CCY_TYP2;
        DCCIRPRD.CCY_INF[3-1].CCY = DCRIRPR.CCY3;
        DCCIRPRD.CCY_INF[3-1].CCY_TYP = DCRIRPR.CCY_TYP3;
        DCCIRPRD.CCY_INF[4-1].CCY = DCRIRPR.CCY4;
        DCCIRPRD.CCY_INF[4-1].CCY_TYP = DCRIRPR.CCY_TYP4;
        DCCIRPRD.CCY_INF[5-1].CCY = DCRIRPR.CCY5;
        DCCIRPRD.CCY_INF[5-1].CCY_TYP = DCRIRPR.CCY_TYP5;
        DCCIRPRD.CCY_INF[6-1].CCY = DCRIRPR.CCY6;
        DCCIRPRD.CCY_INF[6-1].CCY_TYP = DCRIRPR.CCY_TYP6;
        DCCIRPRD.CCY_INF[7-1].CCY = DCRIRPR.CCY7;
        DCCIRPRD.CCY_INF[7-1].CCY_TYP = DCRIRPR.CCY_TYP7;
        DCCIRPRD.CCY_INF[8-1].CCY = DCRIRPR.CCY8;
        DCCIRPRD.CCY_INF[8-1].CCY_TYP = DCRIRPR.CCY_TYP8;
        DCCIRPRD.CCY_INF[9-1].CCY = DCRIRPR.CCY9;
        DCCIRPRD.CCY_INF[9-1].CCY_TYP = DCRIRPR.CCY_TYP9;
        DCCIRPRD.CCY_INF[10-1].CCY = DCRIRPR.CCY10;
        DCCIRPRD.CCY_INF[10-1].CCY_TYP = DCRIRPR.CCY_TYP10;
        DCCIRPRD.LN_MTH = DCRIRPR.LN_MTH;
        DCCIRPRD.LN_PER = DCRIRPR.LN_PER;
        DCCIRPRD.DD_PER = DCRIRPR.DD_PER;
        DCCIRPRD.TERM = DCRIRPR.TERM;
        DCCIRPRD.STRAMT = DCRIRPR.STRAMT;
        DCCIRPRD.STRDT = DCRIRPR.KEY.EFF_DATE;
        DCCIRPRD.EXPDT = DCRIRPR.EXP_DATE;
    }
    public void R000_STARTBR_FIRST_DCTIRPR() throws IOException,SQLException,Exception {
        DCTIRPR_RD = new DBParm();
        DCTIRPR_RD.TableName = "DCTIRPR";
        DCTIRPR_RD.eqWhere = "IBS_AC_BK,PRDMO_CD,PARM_CODE";
        DCTIRPR_RD.where = "EFF_DATE <= :DCCSIRLN.DATA.STRDT "
            + "AND EXP_DATE > :DCCSIRLN.DATA.STRDT";
        DCTIRPR_RD.fst = true;
        DCTIRPR_RD.order = "EFF_DATE DESC";
        IBS.READ(SCCGWA, DCRIRPR, this, DCTIRPR_RD);
    }
    public void T000_READ_DCTIRPR() throws IOException,SQLException,Exception {
        DCTIRPR_RD = new DBParm();
        DCTIRPR_RD.TableName = "DCTIRPR";
        DCTIRPR_RD.eqWhere = "IBS_AC_BK,PRDMO_CD,PARM_CODE,EFF_DATE";
        IBS.READ(SCCGWA, DCRIRPR, DCTIRPR_RD);
    }
    public void T000_READ_UPDATE_DCTIRPR() throws IOException,SQLException,Exception {
        DCTIRPR_RD = new DBParm();
        DCTIRPR_RD.TableName = "DCTIRPR";
        DCTIRPR_RD.eqWhere = "IBS_AC_BK,PRDMO_CD,PARM_CODE,EFF_DATE";
        DCTIRPR_RD.upd = true;
        IBS.READ(SCCGWA, DCRIRPR, DCTIRPR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            B050_ADD_PROCESS();
            if (pgmRtn) return;
            MOD_TO_ADD_FLG = '1';
        }
    }
    public void T000_WRITE_DCTIRPR() throws IOException,SQLException,Exception {
        DCTIRPR_RD = new DBParm();
        DCTIRPR_RD.TableName = "DCTIRPR";
        DCTIRPR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRIRPR, DCTIRPR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PRD_NOT_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTIRPR() throws IOException,SQLException,Exception {
        DCTIRPR_RD = new DBParm();
        DCTIRPR_RD.TableName = "DCTIRPR";
        IBS.REWRITE(SCCGWA, DCRIRPR, DCTIRPR_RD);
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
