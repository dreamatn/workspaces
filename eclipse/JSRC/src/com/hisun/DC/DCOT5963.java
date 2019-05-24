package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5963 {
    String CPN_SCSSCKDT = "SCSSCKDT";
    String CPN_DC_S_IR_PRD = "DC-SVR-IR-PRD";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_INFO = " ";
    short WS_CNT = 0;
    DCOT5963_WS_MMDD WS_MMDD = new DCOT5963_WS_MMDD();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCSFPRD DCCSFPRD = new DCCSFPRD();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    DCB5963_AWA_5963 DCB5963_AWA_5963;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5963 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5963_AWA_5963>");
        DCB5963_AWA_5963 = (DCB5963_AWA_5963) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MOD_PRD_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB5963_AWA_5963.FUNC);
        CEP.TRC(SCCGWA, DCB5963_AWA_5963.PROD_COD);
        if (DCB5963_AWA_5963.PROD_COD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
            WS_FLD_NO = DCB5963_AWA_5963.PROD_COD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5963_AWA_5963.EFFDAT != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = DCB5963_AWA_5963.EFFDAT;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_EFF_DT_INVALID;
                WS_FLD_NO = DCB5963_AWA_5963.EFFDAT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        } else {
            DCB5963_AWA_5963.EFFDAT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (DCB5963_AWA_5963.EXPDAT != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = DCB5963_AWA_5963.EXPDAT;
            SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
            SCSSCKDT2.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_EXP_DT_INVALID;
                WS_FLD_NO = DCB5963_AWA_5963.EXPDAT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        } else {
            DCB5963_AWA_5963.EXPDAT = 99991231;
        }
        if (DCB5963_AWA_5963.EXPDAT < DCB5963_AWA_5963.EFFDAT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_EXP_DATE_LT_EFFDT;
            WS_FLD_NO = DCB5963_AWA_5963.EXPDAT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5963_AWA_5963.CI_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CI_TYP_M_INPUT;
            WS_FLD_NO = DCB5963_AWA_5963.CI_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5963_AWA_5963.CCY.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_M_INPUT;
            WS_FLD_NO = DCB5963_AWA_5963.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        } else {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = DCB5963_AWA_5963.CCY;
            S000_CALL_BPZQCCY();
            CEP.TRC(SCCGWA, BPCQCCY.DATA.TR_FLG);
            CEP.TRC(SCCGWA, BPCQCCY.DATA.CHG_CCY);
            if (BPCQCCY.DATA.TR_FLG != 'Y') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_INVALID;
                WS_FLD_NO = DCB5963_AWA_5963.CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if (DCB5963_AWA_5963.PERM_INF[1-1].PERM_KND == ' ') {
            if (DCB5963_AWA_5963.PROD_COD.equalsIgnoreCase("IRDD000006")) {
            } else {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PER_KND_M_INPUT;
                WS_FLD_NO = DCB5963_AWA_5963.PERM_INF[1-1].PERM_KND_NO;
                CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_INFO, WS_FLD_NO);
            }
        }
        if (DCB5963_AWA_5963.TRIG_TMS == ' ') {
            if (DCB5963_AWA_5963.PROD_COD.equalsIgnoreCase("IRDD000006")) {
            } else {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TRIG_TMS_INPUT;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
        }
        if (DCB5963_AWA_5963.DD_INFO[1-1].PROD_DD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DD_INFO_MST_INPUT;
            WS_FLD_NO = DCB5963_AWA_5963.DD_INFO[1-1].PROD_DD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void B020_MOD_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSFPRD);
        DCCSFPRD.PROD_CODE = DCB5963_AWA_5963.PROD_COD;
        DCCSFPRD.VAL.PROD_DESC = DCB5963_AWA_5963.PROD_DSC;
        DCCSFPRD.VAL.EFFDAT = DCB5963_AWA_5963.EFFDAT;
        DCCSFPRD.VAL.EXPDAT = DCB5963_AWA_5963.EXPDAT;
        DCCSFPRD.VAL.CI_TYP = DCB5963_AWA_5963.CI_TYP;
        DCCSFPRD.VAL.CCY = DCB5963_AWA_5963.CCY;
        DCCSFPRD.VAL.CCY_TYPE = DCB5963_AWA_5963.CCY_TYPE;
        DCCSFPRD.VAL.OVR_CARD_FLG = DCB5963_AWA_5963.OVR_CARD;
        DCCSFPRD.VAL.DRAW_MTH = DCB5963_AWA_5963.DRAW_MTH;
        DCCSFPRD.VAL.DRAW_ORDER = DCB5963_AWA_5963.DRAW_ORD;
        for (WS_CNT = 1; WS_CNT <= 10; WS_CNT += 1) {
            DCCSFPRD.VAL.PROD_DD_INFO[WS_CNT-1].PROD_DD = DCB5963_AWA_5963.DD_INFO[WS_CNT-1].PROD_DD;
        }
        for (WS_CNT = 1; WS_CNT <= 10; WS_CNT += 1) {
            DCCSFPRD.VAL.PROD_DD_INFO[WS_CNT-1].DESC_DD = DCB5963_AWA_5963.DD_INFO[WS_CNT-1].DESC_DD;
        }
        for (WS_CNT = 1; WS_CNT <= 10; WS_CNT += 1) {
            DCCSFPRD.VAL.PROD_TD_INFO[WS_CNT-1].PROD_TD = DCB5963_AWA_5963.TD_INFO[WS_CNT-1].PROD_TD;
        }
        for (WS_CNT = 1; WS_CNT <= 10; WS_CNT += 1) {
            DCCSFPRD.VAL.PROD_TD_INFO[WS_CNT-1].DESC_TD = DCB5963_AWA_5963.TD_INFO[WS_CNT-1].DESC_TD;
        }
        DCCSFPRD.VAL.USAGE_MTH = DCB5963_AWA_5963.USAGE;
        for (WS_CNT = 1; WS_CNT <= 5; WS_CNT += 1) {
            DCCSFPRD.VAL.PERM_INFO[WS_CNT-1].PERM_KND = DCB5963_AWA_5963.PERM_INF[WS_CNT-1].PERM_KND;
        }
        CEP.TRC(SCCGWA, DCB5963_AWA_5963.PERM_INF[1-1].PERM_KND);
        for (WS_CNT = 1; WS_CNT <= 5; WS_CNT += 1) {
            DCCSFPRD.VAL.INT_INFO[WS_CNT-1].INT_MTH = DCB5963_AWA_5963.INT_INFO[WS_CNT-1].INT_MTH;
        }
        for (WS_CNT = 1; WS_CNT <= 5; WS_CNT += 1) {
            DCCSFPRD.VAL.PERM_INFO[WS_CNT-1].PERM_MTH = DCB5963_AWA_5963.TRIG_INF[WS_CNT-1].TRIG_MTH;
        }
        DCCSFPRD.VAL.TRIG_TMS = DCB5963_AWA_5963.TRIG_TMS;
        DCCSFPRD.VAL.INOUT_FG = DCB5963_AWA_5963.INOUT_FG;
        DCCSFPRD.VAL.OUT_LVL = DCB5963_AWA_5963.OUT_LVL;
        DCCSFPRD.VAL.IN_LVL = DCB5963_AWA_5963.IN_LVL;
        DCCSFPRD.VAL.OVR_BANK_FLG = DCB5963_AWA_5963.OVR_BANK;
        CEP.TRC(SCCGWA, DCCSFPRD);
        DCCSFPRD.FUNC = 'M';
        S000_CALL_DCZSFPRD();
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        CEP.TRC(SCCGWA, BPCQCCY.RC);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_DCZSFPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DC_S_IR_PRD, DCCSFPRD);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_INFO, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERR);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
