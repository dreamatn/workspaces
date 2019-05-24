package com.hisun.DD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCOCKAT;
import com.hisun.BP.BPCQCCY;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class DDOT5904 {
    boolean pgmRtn = false;
    String CPN_SCSSCKDT = "SCSSCKDT";
    String CPN_DD_S_MAIN_PRD = "DD-SVR-MAIN-PRD";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_CHK_ACCT_CD = "BP-P-CHK-ACCT-CODE";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_INFO = " ";
    short WS_CNT = 0;
    DDOT5904_WS_MMDD WS_MMDD = new DDOT5904_WS_MMDD();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSMPRD DDCSMPRD = new DDCSMPRD();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOCKAT BPCOCKAT = new BPCOCKAT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    DDB5900_AWA_5900 DDB5900_AWA_5900;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDOT5904 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5900_AWA_5900>");
        DDB5900_AWA_5900 = (DDB5900_AWA_5900) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_ADD_PRD_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.FUNC);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.PRD_CD);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.CUS_TYP);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.C_NAME);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.EFFDT);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.PSB_FLG);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.CARD_FLG);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.CHQ_FLG);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.EXPDT);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.CCY_TYP);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.OVR_FAC);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.CTXN_TYP);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.CMSG_TYP);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.CROS_DR);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.CROS_CR);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.CAL_DMTH);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.DEP_PED1);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.DEP_DT1);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.ODP_PED1);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.ODP_DT1);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.DEP_PED2);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.DEP_DT2);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.ODP_PED2);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.ODP_DT2);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.CERT_FLG);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.AUFR_FLG);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.TD_PROD);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.TD_FLG);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.GM_FLG);
        if (DDB5900_AWA_5900.PRD_CD.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PROD_CD_M_INPUT;
            WS_FLD_NO = DDB5900_AWA_5900.PRD_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDB5900_AWA_5900.EFFDT != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = DDB5900_AWA_5900.EFFDT;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_EFF_DT_INVALID;
                WS_FLD_NO = DDB5900_AWA_5900.EFFDT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        } else {
            DDB5900_AWA_5900.EFFDT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (DDB5900_AWA_5900.EXPDT != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = DDB5900_AWA_5900.EXPDT;
            SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
            SCSSCKDT2.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_EXP_DT_INVALID;
                WS_FLD_NO = DDB5900_AWA_5900.EXPDT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        } else {
            DDB5900_AWA_5900.EXPDT = 99991231;
        }
        if (DDB5900_AWA_5900.EXPDT < DDB5900_AWA_5900.EFFDT) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_EXP_DATE_LT_EFFDT;
            WS_FLD_NO = DDB5900_AWA_5900.EXPDT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDB5900_AWA_5900.CCY_TYP == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYP_M_INPUT;
            WS_FLD_NO = DDB5900_AWA_5900.CCY_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        } else {
            if ((DDB5900_AWA_5900.CCY_TYP != 'L') 
                && (DDB5900_AWA_5900.CCY_TYP != 'F') 
                && (DDB5900_AWA_5900.CCY_TYP != 'M') 
                && (DDB5900_AWA_5900.CCY_TYP != 'A')) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PROD_TYP_INVALID;
                WS_FLD_NO = DDB5900_AWA_5900.CCY_TYP_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (DDB5900_AWA_5900.CCY_INFO[1-1].CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NOT_DEF_CCY_FOR_PRD;
            WS_FLD_NO = DDB5900_AWA_5900.CCY_INFO[1-1].CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.DEP_PED1);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.DEP_DT1);
        IBS.CPY2CLS(SCCGWA, DDB5900_AWA_5900.DEP_DT1+"", WS_MMDD);
        if (DDB5900_AWA_5900.CCY_TYP != 'F') {
            if (DDB5900_AWA_5900.DEP_PED1 == ' ') {
                if (DDB5900_AWA_5900.CAL_DMTH != '3' 
                    && DDB5900_AWA_5900.CAL_DMTH != ' ') {
                    CEP.TRC(SCCGWA, DDB5900_AWA_5900.DEP_PED1);
                    CEP.TRC(SCCGWA, DDB5900_AWA_5900.CAL_DMTH);
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DEP_POST_CYC_M_INPUT;
                    WS_FLD_NO = DDB5900_AWA_5900.DEP_PED1_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else if (DDB5900_AWA_5900.DEP_PED1 == 'D') {
            } else if (DDB5900_AWA_5900.DEP_PED1 == 'W') {
                if ((DDB5900_AWA_5900.DEP_DT1 < 1) 
                    || (DDB5900_AWA_5900.DEP_DT1 > 7)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DEP_POST_DT_INVALID;
                    WS_FLD_NO = DDB5900_AWA_5900.DEP_PED1_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else if (DDB5900_AWA_5900.DEP_PED1 == 'M') {
                if ((DDB5900_AWA_5900.DEP_DT1 < 1) 
                    || (DDB5900_AWA_5900.DEP_DT1 > 31)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DEP_POST_DT_INVALID;
                    WS_FLD_NO = DDB5900_AWA_5900.DEP_PED1_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else if (DDB5900_AWA_5900.DEP_PED1 == 'Q') {
                if ((WS_MMDD.WS_MM < 1) 
                    || (WS_MMDD.WS_MM > 3) 
                    || (WS_MMDD.WS_DD < 1) 
                    || (WS_MMDD.WS_DD > 31)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DEP_POST_DT_INVALID;
                    WS_FLD_NO = DDB5900_AWA_5900.DEP_PED1_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else if (DDB5900_AWA_5900.DEP_PED1 == 'H') {
                if ((WS_MMDD.WS_MM < 1) 
                    || (WS_MMDD.WS_MM > 6) 
                    || (WS_MMDD.WS_DD < 1) 
                    || (WS_MMDD.WS_DD > 31)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DEP_POST_DT_INVALID;
                    WS_FLD_NO = DDB5900_AWA_5900.DEP_PED1_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else if (DDB5900_AWA_5900.DEP_PED1 == 'Y') {
                if ((WS_MMDD.WS_MM < 1) 
                    || (WS_MMDD.WS_MM > 12) 
                    || (WS_MMDD.WS_DD < 1) 
                    || (WS_MMDD.WS_DD > 31)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DEP_POST_DT_INVALID;
                    WS_FLD_NO = DDB5900_AWA_5900.DEP_PED1_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DEP_POST_CYC_INVALID;
                WS_FLD_NO = DDB5900_AWA_5900.DEP_PED1_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.ODP_PED1);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.ODP_DT1);
        IBS.CPY2CLS(SCCGWA, DDB5900_AWA_5900.ODP_DT1+"", WS_MMDD);
        if (DDB5900_AWA_5900.CCY_TYP != 'F' 
            && DDB5900_AWA_5900.OVR_FAC == 'Y') {
            if (DDB5900_AWA_5900.ODP_PED1 == ' ') {
                if (DDB5900_AWA_5900.CAL_DMTH != '3' 
                    && DDB5900_AWA_5900.CAL_DMTH != ' ') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_OD_POST_CYC_M_INPUT;
                    WS_FLD_NO = DDB5900_AWA_5900.ODP_PED1_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else if (DDB5900_AWA_5900.ODP_PED1 == 'D') {
            } else if (DDB5900_AWA_5900.ODP_PED1 == 'W') {
                if ((DDB5900_AWA_5900.ODP_DT1 < 1) 
                    || (DDB5900_AWA_5900.ODP_DT1 > 7)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DEP_POST_DT_INVALID;
                    WS_FLD_NO = DDB5900_AWA_5900.DEP_PED1_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else if (DDB5900_AWA_5900.ODP_PED1 == 'M') {
                if ((DDB5900_AWA_5900.ODP_DT1 < 1) 
                    || (DDB5900_AWA_5900.ODP_DT1 > 31)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_OD_POST_DT_INVALID;
                    WS_FLD_NO = DDB5900_AWA_5900.ODP_PED1_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else if (DDB5900_AWA_5900.ODP_PED1 == 'Q') {
                if ((WS_MMDD.WS_MM < 1) 
                    || (WS_MMDD.WS_MM > 3) 
                    || (WS_MMDD.WS_DD < 1) 
                    || (WS_MMDD.WS_DD > 31)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_OD_POST_DT_INVALID;
                    WS_FLD_NO = DDB5900_AWA_5900.ODP_PED1_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else if (DDB5900_AWA_5900.ODP_PED1 == 'H') {
                if ((WS_MMDD.WS_MM < 1) 
                    || (WS_MMDD.WS_MM > 6) 
                    || (WS_MMDD.WS_DD < 1) 
                    || (WS_MMDD.WS_DD > 31)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_OD_POST_DT_INVALID;
                    WS_FLD_NO = DDB5900_AWA_5900.ODP_PED1_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else if (DDB5900_AWA_5900.ODP_PED1 == 'Y') {
                if ((WS_MMDD.WS_MM < 1) 
                    || (WS_MMDD.WS_MM > 12) 
                    || (WS_MMDD.WS_DD < 1) 
                    || (WS_MMDD.WS_DD > 31)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_OD_POST_DT_INVALID;
                    WS_FLD_NO = DDB5900_AWA_5900.ODP_PED1_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_OD_POST_CYC_INVALID;
                WS_FLD_NO = DDB5900_AWA_5900.ODP_PED1_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.DEP_PED2);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.DEP_DT2);
        IBS.CPY2CLS(SCCGWA, DDB5900_AWA_5900.DEP_DT2+"", WS_MMDD);
        if (DDB5900_AWA_5900.CCY_TYP != 'L') {
            if (DDB5900_AWA_5900.DEP_PED2 == 'D') {
            } else if (DDB5900_AWA_5900.DEP_PED2 == 'W') {
                if ((DDB5900_AWA_5900.DEP_DT2 < 1) 
                    || (DDB5900_AWA_5900.DEP_DT2 > 7)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DEP_POST_DT_INVALID;
                    WS_FLD_NO = DDB5900_AWA_5900.DEP_PED1_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else if (DDB5900_AWA_5900.DEP_PED2 == 'M') {
                if ((DDB5900_AWA_5900.DEP_DT2 < 1) 
                    || (DDB5900_AWA_5900.DEP_DT2 > 31)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DEP_POST_DT_INVALID;
                    WS_FLD_NO = DDB5900_AWA_5900.DEP_PED2_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else if (DDB5900_AWA_5900.DEP_PED2 == 'Q') {
                if ((WS_MMDD.WS_MM < 1) 
                    || (WS_MMDD.WS_MM > 3) 
                    || (WS_MMDD.WS_DD < 1) 
                    || (WS_MMDD.WS_DD > 31)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DEP_POST_DT_INVALID;
                    WS_FLD_NO = DDB5900_AWA_5900.DEP_PED2_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else if (DDB5900_AWA_5900.DEP_PED2 == 'H') {
                if ((WS_MMDD.WS_MM < 1) 
                    || (WS_MMDD.WS_MM > 6) 
                    || (WS_MMDD.WS_DD < 1) 
                    || (WS_MMDD.WS_DD > 31)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DEP_POST_DT_INVALID;
                    WS_FLD_NO = DDB5900_AWA_5900.DEP_PED2_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else if (DDB5900_AWA_5900.DEP_PED2 == 'Y') {
                if ((WS_MMDD.WS_MM < 1) 
                    || (WS_MMDD.WS_MM > 12) 
                    || (WS_MMDD.WS_DD < 1) 
                    || (WS_MMDD.WS_DD > 31)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DEP_POST_DT_INVALID;
                    WS_FLD_NO = DDB5900_AWA_5900.DEP_PED2_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else if (DDB5900_AWA_5900.DEP_PED2 == ' ') {
                DDB5900_AWA_5900.DEP_PED2 = DDB5900_AWA_5900.DEP_PED1;
                DDB5900_AWA_5900.DEP_DT2 = DDB5900_AWA_5900.DEP_DT1;
            } else {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DEP_POST_CYC_INVALID;
                WS_FLD_NO = DDB5900_AWA_5900.DEP_PED2_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.ODP_PED2);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.ODP_DT2);
        IBS.CPY2CLS(SCCGWA, DDB5900_AWA_5900.ODP_DT2+"", WS_MMDD);
        if (DDB5900_AWA_5900.CCY_TYP != 'L' 
            && DDB5900_AWA_5900.OVR_FAC == 'Y') {
            if (DDB5900_AWA_5900.ODP_PED2 == 'D') {
            } else if (DDB5900_AWA_5900.ODP_PED2 == 'W') {
                if ((DDB5900_AWA_5900.ODP_DT2 < 1) 
                    || (DDB5900_AWA_5900.ODP_DT2 > 7)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DEP_POST_DT_INVALID;
                    WS_FLD_NO = DDB5900_AWA_5900.DEP_PED1_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else if (DDB5900_AWA_5900.ODP_PED2 == 'M') {
                if ((DDB5900_AWA_5900.ODP_DT2 < 1) 
                    || (DDB5900_AWA_5900.ODP_DT2 > 31)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_OD_POST_DT_INVALID;
                    WS_FLD_NO = DDB5900_AWA_5900.ODP_PED2_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else if (DDB5900_AWA_5900.ODP_PED2 == 'Q') {
                if ((WS_MMDD.WS_MM < 1) 
                    || (WS_MMDD.WS_MM > 3) 
                    || (WS_MMDD.WS_DD < 1) 
                    || (WS_MMDD.WS_DD > 31)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_OD_POST_DT_INVALID;
                    WS_FLD_NO = DDB5900_AWA_5900.ODP_PED2_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else if (DDB5900_AWA_5900.ODP_PED2 == 'H') {
                if ((WS_MMDD.WS_MM < 1) 
                    || (WS_MMDD.WS_MM > 6) 
                    || (WS_MMDD.WS_DD < 1) 
                    || (WS_MMDD.WS_DD > 31)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_OD_POST_DT_INVALID;
                    WS_FLD_NO = DDB5900_AWA_5900.ODP_PED2_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else if (DDB5900_AWA_5900.ODP_PED2 == 'Y') {
                if ((WS_MMDD.WS_MM < 1) 
                    || (WS_MMDD.WS_MM > 12) 
                    || (WS_MMDD.WS_DD < 1) 
                    || (WS_MMDD.WS_DD > 31)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_OD_POST_DT_INVALID;
                    WS_FLD_NO = DDB5900_AWA_5900.ODP_PED2_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            } else if (DDB5900_AWA_5900.ODP_PED2 == ' ') {
                DDB5900_AWA_5900.ODP_PED2 = DDB5900_AWA_5900.ODP_PED1;
                DDB5900_AWA_5900.ODP_DT2 = DDB5900_AWA_5900.ODP_DT1;
            } else {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_OD_POST_CYC_INVALID;
                WS_FLD_NO = DDB5900_AWA_5900.ODP_PED2_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.CCY_TYP);
        for (WS_CNT = 1; WS_CNT <= 20; WS_CNT += 1) {
            WS_INFO = " ";
            if (DDB5900_AWA_5900.CCY_INFO[WS_CNT-1].CCY.trim().length() > 0 
                && DDB5900_AWA_5900.CCY_TYP != 'A') {
                WS_FLD_NO = DDB5900_AWA_5900.CCY_INFO[WS_CNT-1].CCY_NO;
                IBS.init(SCCGWA, BPCQCCY);
                BPCQCCY.DATA.CCY = DDB5900_AWA_5900.CCY_INFO[WS_CNT-1].CCY;
                S000_CALL_BPZQCCY();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCQCCY.DATA.TR_FLG);
                CEP.TRC(SCCGWA, BPCQCCY.DATA.CHG_CCY);
                if (BPCQCCY.DATA.TR_FLG != 'Y') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NOT_TR_CCY;
                    WS_INFO = DDB5900_AWA_5900.CCY_INFO[WS_CNT-1].CCY;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            }
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B020_ADD_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSMPRD);
        DDCSMPRD.VAL.CUST_TYPE = DDB5900_AWA_5900.CUS_TYP;
        DDCSMPRD.KEY.PARM_CODE = DDB5900_AWA_5900.PRD_CD;
        DDCSMPRD.VAL.CHN_NAME = DDB5900_AWA_5900.C_NAME;
        DDCSMPRD.VAL.REMARK = DDB5900_AWA_5900.RMK;
        DDCSMPRD.EFF_DATE = DDB5900_AWA_5900.EFFDT;
        DDCSMPRD.EXP_DATE = DDB5900_AWA_5900.EXPDT;
        DDCSMPRD.VAL.PRD_TOOL_PSB = DDB5900_AWA_5900.PSB_FLG;
        DDCSMPRD.VAL.PRD_TOOL_CARD = DDB5900_AWA_5900.CARD_FLG;
        DDCSMPRD.VAL.PRD_TOOL_CHQ = DDB5900_AWA_5900.CHQ_FLG;
        DDCSMPRD.VAL.CUR_TYPE = DDB5900_AWA_5900.CCY_TYP;
        DDCSMPRD.VAL.OVERDRAFT_FAC = DDB5900_AWA_5900.OVR_FAC;
        DDCSMPRD.VAL.CASH_TXN_TYPE = DDB5900_AWA_5900.CTXN_TYP;
        DDCSMPRD.VAL.CASH_MSG_TYPE = DDB5900_AWA_5900.CMSG_TYP;
        DDCSMPRD.VAL.CROS_DR_LMT = DDB5900_AWA_5900.CROS_DR;
        DDCSMPRD.VAL.CROS_CR_LMT = DDB5900_AWA_5900.CROS_CR;
        DDCSMPRD.VAL.CAL_DINT_METH = DDB5900_AWA_5900.CAL_DMTH;
        DDCSMPRD.VAL.DEP_POST_PERIOD1 = DDB5900_AWA_5900.DEP_PED1;
        DDCSMPRD.VAL.DEP_POST_DATE1 = DDB5900_AWA_5900.DEP_DT1;
        DDCSMPRD.VAL.OD_POST_PERIOD1 = DDB5900_AWA_5900.ODP_PED1;
        DDCSMPRD.VAL.OD_POST_DATE1 = DDB5900_AWA_5900.ODP_DT1;
        DDCSMPRD.VAL.DEP_POST_PERIOD2 = DDB5900_AWA_5900.DEP_PED2;
        DDCSMPRD.VAL.DEP_POST_DATE2 = DDB5900_AWA_5900.DEP_DT2;
        DDCSMPRD.VAL.OD_POST_PERIOD2 = DDB5900_AWA_5900.ODP_PED2;
        DDCSMPRD.VAL.OD_POST_DATE2 = DDB5900_AWA_5900.ODP_DT2;
        DDCSMPRD.VAL.CERT_FLG = DDB5900_AWA_5900.CERT_FLG;
