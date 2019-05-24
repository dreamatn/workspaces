package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSCSDX {
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTMST_RD;
    String K_OUTPUT_FMT = "DDA03";
    String CPN_UNIT_DEP_PROC = "DD-UNIT-DEP-PROC ";
    String CPN_UNIT_DRAW_PROC = "DD-UNIT-DRAW-PROC ";
    String CPN_U_ADD_CBOX = "BP-U-ADD-CBOX      ";
    String CPN_U_SUB_CBOX = "BP-U-SUB-CBOX      ";
    String CPN_AMT_TBL_AUTH = "BP-F-AMT-TBL-AUTH  ";
    String CPN_BP_EX = "BP-EX           ";
    String CPN_CCY_QUERY = "BP-INQUIRE-CCY";
    String WS_ERR_MSG = " ";
    String WS_BUY_CCY = " ";
    double WS_BUY_AMT = 0;
    String WS_SELL_CCY = " ";
    double WS_SELL_AMT = 0;
    int WS_TMP_DT = 0;
    char WS_CI_TYP = ' ';
    char WS_CI_TYP1 = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCOCSDX DDCOCSDX = new DDCOCSDX();
    BPCUABOX BPCUABOX = new BPCUABOX();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    BPCFAMTA BPCFAMTA = new BPCFAMTA();
    BPCEX BPCEX = new BPCEX();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    CICACCU CICACCU = new CICACCU();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    DDCSCSDX DDCSCSDX;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, DDCSCSDX DDCSCSDX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSCSDX = DDCSCSDX;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSCSDX return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSCSDX.BV_TYP);
        CEP.TRC(SCCGWA, DDCSCSDX.AC);
        CEP.TRC(SCCGWA, DDCSCSDX.PSBK_NO);
        CEP.TRC(SCCGWA, DDCSCSDX.CHQ_TYPE);
        CEP.TRC(SCCGWA, DDCSCSDX.CHQ_NO);
        CEP.TRC(SCCGWA, DDCSCSDX.CHQ_ISS_DATE);
        CEP.TRC(SCCGWA, DDCSCSDX.CCY);
        CEP.TRC(SCCGWA, DDCSCSDX.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSCSDX.CASH_AMT);
        CEP.TRC(SCCGWA, DDCSCSDX.TX_RMK);
        CEP.TRC(SCCGWA, DDCSCSDX.REMARKS);
        CEP.TRC(SCCGWA, DDCSCSDX.PAY_TYPE);
        CEP.TRC(SCCGWA, DDCSCSDX.PSWD);
        CEP.TRC(SCCGWA, DDCSCSDX.TX_MMO);
        B005_CHECK_INPUT_DATA();
        B012_CARD_LIMT_BAL_CHK();
        B015_WITHDRAW_FROM_AC_PROC();
        B020_DEPOSIT_TO_CASHBOX_PROC();
        B030_WITHDRAW_FROM_CASHBOX_PROC();
        B040_DEPOSIT_TO_AC_PROC();
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B050_OUTPUT_PROC();
        }
    }
    public void B005_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCSCSDX.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = DDCSCSDX.AC;
            S00_LINK_CIZACCU();
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_TYP);
            WS_CI_TYP = CICACCU.DATA.CI_TYP;
        }
        if (DDCSCSDX.CCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = DDCSCSDX.CCY;
            S000_CALL_BPZQCCY();
            if (BPCQCCY.RC.RTNCODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
                S000_ERR_MSG_PROC();
            }
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSCSDX.CASH_AMT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
        } else {
            if (DDCSCSDX.CASH_AMT < 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_INVALID;
                S000_ERR_MSG_PROC();
            }
        }
        if (DDCSCSDX.OTH_AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = DDCSCSDX.OTH_AC;
            S00_LINK_CIZACCU();
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_TYP);
            WS_CI_TYP1 = CICACCU.DATA.CI_TYP;
        }
        if (DDCSCSDX.OTH_CCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = DDCSCSDX.OTH_CCY;
            S000_CALL_BPZQCCY();
            if (BPCQCCY.RC.RTNCODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
                S000_ERR_MSG_PROC();
            }
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSCSDX.DEP_AMT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
        } else {
            if (DDCSCSDX.DEP_AMT < 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_INVALID;
                S000_ERR_MSG_PROC();
            }
        }
        if (!DDCSCSDX.CCY.equalsIgnoreCase(DDCSCSDX.OTH_CCY)) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_NOT_SAME;
            S000_ERR_MSG_PROC();
        }
        if (DDCSCSDX.CHQ_TYPE == '1') {
            if (DDCSCSDX.CHQ_NO.equalsIgnoreCase("0")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_MUST_INP;
                S000_ERR_MSG_PROC();
            }
        }
        if (DDCSCSDX.CHQ_TYPE == '1' 
            && DDCSCSDX.CHQ_ISS_DATE > 0) {
            if (DDCSCSDX.CHQ_ISS_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ISSDT_GT_ACDATE;
                S000_ERR_MSG_PROC();
            }
        }
        if ((WS_CI_TYP == '1' 
            && WS_CI_TYP1 == '1') 
            || ((WS_CI_TYP == '2' 
            || WS_CI_TYP == '3') 
            && (WS_CI_TYP1 == '2' 
            || WS_CI_TYP1 == '3'))) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CI_TYP_INVALID;
            S000_ERR_MSG_PROC();
        }
    }
    public void B012_CARD_LIMT_BAL_CHK() throws IOException,SQLException,Exception {
        if (DDCSCSDX.AC.trim().length() > 0 
            && DDCSCSDX.BV_TYP == '1') {
            IBS.init(SCCGWA, DCCPFTCK);
            DCCPFTCK.VAL.CARD_NO = DDCSCSDX.AC;
            DCCPFTCK.VAL.REGN_TYP = '0';
            DCCPFTCK.VAL.TXN_TYPE = "01";
            DCCPFTCK.VAL.TXN_CCY = DDCSCSDX.CCY;
            DCCPFTCK.VAL.TXN_AMT = DDCSCSDX.CASH_AMT;
        }
    }
    public void B015_WITHDRAW_FROM_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.TX_TYPE = 'C';
        DDCUDRAC.AC = DDCSCSDX.AC;
        DDCUDRAC.PSBK_NO = DDCSCSDX.PSBK_NO;
        DDCUDRAC.CHQ_TYPE = DDCSCSDX.CHQ_TYPE;
        if (DDCSCSDX.CHQ_NO.trim().length() > 0) {
            DDCUDRAC.CHQ_NO = DDCSCSDX.CHQ_NO;
            DDCUDRAC.CHK_PSW_FLG = 'N';
        } else {
            DDCUDRAC.CHK_PSW_FLG = 'Y';
            if (DDCSCSDX.BV_TYP == '2' 
                && DDCSCSDX.PAY_TYPE != '1') {
                DDCUDRAC.CHK_PSW_FLG = 'N';
            }
        }
        DDCUDRAC.CHQ_ISS_DATE = DDCSCSDX.CHQ_ISS_DATE;
        DDCUDRAC.PAY_PSWD = DDCSCSDX.PSWD;
        DDCUDRAC.TX_AMT = DDCSCSDX.CASH_AMT;
        DDCUDRAC.CCY = DDCSCSDX.CCY;
        DDCUDRAC.CCY_TYPE = DDCSCSDX.CCY_TYPE;
        DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUDRAC.NARRATIVE = DDCSCSDX.TX_RMK;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DDCSCSDX.TX_MMO = "G004";
        } else {
            DDCSCSDX.TX_MMO = "A034";
        }
        DDCUDRAC.TX_MMO = DDCSCSDX.TX_MMO;
        DDCUDRAC.REMARKS = DDCSCSDX.REMARKS;
        DDCUDRAC.BV_TYP = DDCSCSDX.BV_TYP;
        DDCUDRAC.PSWD = DDCSCSDX.PSWD;
        S000_CALL_DDZUDRAC();
    }
    public void B030_WITHDRAW_FROM_CASHBOX_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUSBOX);
        BPCUSBOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUSBOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUSBOX.CCY = DDCSCSDX.CCY;
        BPCUSBOX.AMT = DDCSCSDX.CASH_AMT;
        CEP.TRC(SCCGWA, BPCUSBOX.BR);
        CEP.TRC(SCCGWA, BPCUSBOX.TLR);
        CEP.TRC(SCCGWA, BPCUSBOX.CCY);
        CEP.TRC(SCCGWA, BPCUSBOX.AMT);
        BPCUSBOX.OPP_AC = DDCSCSDX.AC;
        BPCUSBOX.CASH_NO = DDCSCSDX.CASH_NO;
        S000_CALL_BPZUSBOX();
    }
    public void B020_DEPOSIT_TO_CASHBOX_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUABOX);
        BPCUABOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUABOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUABOX.CCY = DDCSCSDX.OTH_CCY;
        BPCUABOX.AMT = DDCSCSDX.DEP_AMT;
        CEP.TRC(SCCGWA, BPCUABOX.CCY);
        CEP.TRC(SCCGWA, BPCUABOX.AMT);
        BPCUABOX.OPP_AC = DDCSCSDX.OTH_AC;
        BPCUABOX.CASH_NO = DDCSCSDX.CASH_NO;
        S000_CALL_BPZUABOX();
    }
    public void B040_DEPOSIT_TO_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.TX_TYPE = 'C';
        DDCUCRAC.AC = DDCSCSDX.OTH_AC;
        DDCUCRAC.PSBK_NO = DDCSCSDX.OTH_PSBK_NO;
        DDCUCRAC.TX_AMT = DDCSCSDX.DEP_AMT;
        DDCUCRAC.CCY = DDCSCSDX.OTH_CCY;
        DDCUCRAC.CCY_TYPE = DDCSCSDX.OTH_CCY_TYPE;
        DDCUCRAC.TX_REF = DDCSCSDX.OTH_TX_RMK;
        DDCUCRAC.REMARKS = DDCSCSDX.OTH_REMARKS;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DDCSCSDX.OTH_TX_MMO = "G004";
        } else {
            DDCSCSDX.OTH_TX_MMO = "A026";
        }
        DDCUCRAC.TX_MMO = DDCSCSDX.OTH_TX_MMO;
        S000_CALL_DDZUCRAC();
        CEP.TRC(SCCGWA, DDCUCRAC.TX_MMO);
    }
    public void B050_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOCSDX);
        DDCOCSDX.CASH_NO = DDCSCSDX.CASH_NO;
        DDCOCSDX.BV_TYP = DDCSCSDX.BV_TYP;
        DDCOCSDX.AC = DDCSCSDX.AC;
        DDCOCSDX.PSBK_NO = DDCSCSDX.PSBK_NO;
        DDCOCSDX.CCY = DDCSCSDX.CCY;
        DDCOCSDX.CCY_TYPE = DDCSCSDX.CCY_TYPE;
        DDCOCSDX.CASH_AMT = DDCSCSDX.CASH_AMT;
        DDCOCSDX.PAY_TYPE = DDCSCSDX.PAY_TYPE;
        DDCOCSDX.CHQ_TYPE = DDCSCSDX.CHQ_TYPE;
        DDCOCSDX.CHQ_NO = DDCSCSDX.CHQ_NO;
        DDCOCSDX.CHQ_ISS_DATE = DDCSCSDX.CHQ_ISS_DATE;
        DDCOCSDX.PSWD = DDCSCSDX.PSWD;
        DDCOCSDX.TX_MMO = DDCSCSDX.TX_MMO;
        DDCOCSDX.TX_RMK = DDCSCSDX.TX_RMK;
        DDCOCSDX.REMARKS = DDCSCSDX.REMARKS;
        DDCOCSDX.OTH_AC = DDCSCSDX.OTH_AC;
        DDCOCSDX.OTH_PSBK_NO = DDCSCSDX.OTH_PSBK_NO;
        DDCOCSDX.OTH_CCY = DDCSCSDX.OTH_CCY;
        DDCOCSDX.OTH_CCY_TYPE = DDCSCSDX.OTH_CCY_TYPE;
        DDCOCSDX.BV_CD = DDCSCSDX.BV_CD;
        DDCOCSDX.DEP_AMT = DDCSCSDX.DEP_AMT;
        DDCOCSDX.OTH_TX_MMO = DDCSCSDX.OTH_TX_MMO;
        DDCOCSDX.OTH_TX_RMK = DDCSCSDX.OTH_TX_RMK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOCSDX;
        SCCFMT.DATA_LEN = 405;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_DCZPFTCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-FINANCE-TR-CHK", DCCPFTCK);
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_UNIT_DEP_PROC, DDCUCRAC);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_UNIT_DRAW_PROC, DDCUDRAC);
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CCY_QUERY, BPCQCCY);
    }
    public void S000_CALL_BPZUABOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_ADD_CBOX, BPCUABOX);
    }
    public void S000_CALL_BPZUSBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_SUB_CBOX, BPCUSBOX);
    }
    public void S00_LINK_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_AC_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
