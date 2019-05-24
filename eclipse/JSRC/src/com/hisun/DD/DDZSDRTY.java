package com.hisun.DD;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSDRTY {
    DBParm DDTCCY_RD;
    String CPN_UNIT_DRAW_PROC = "DD-UNIT-DRAW-PROC ";
    String WS_ERR_MSG = " ";
    double WS_TX_AMT = 0;
    double WS_OTHE_AMT = 0;
    double WS_AVL_BAL = 0;
    char WS_CCY_TYPE_FLG = ' ';
    char WS_BAL_ZERO_FLG = ' ';
    char WS_CCY_REC_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDRCCY DDRCCY = new DDRCCY();
    SCCGWA SCCGWA;
    DDCSDRTY DDCSDRTY;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, DDCSDRTY DDCSDRTY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSDRTY = DDCSDRTY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSDRTY return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSDRTY.AC);
        CEP.TRC(SCCGWA, DDCSDRTY.CCY);
        CEP.TRC(SCCGWA, DDCSDRTY.VAL_DATE);
        CEP.TRC(SCCGWA, DDCSDRTY.CARD_NO);
        CEP.TRC(SCCGWA, DDCSDRTY.TX_TYPE);
        CEP.TRC(SCCGWA, DDCSDRTY.CHK_PSW_FLG);
        CEP.TRC(SCCGWA, DDCSDRTY.BANK_DR_FLG);
        CEP.TRC(SCCGWA, DDCSDRTY.TX_BAL_FLG);
        B005_CHECK_INPUT_DATA();
        B010_DRAW_AC_PRCO();
    }
    public void B005_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCSDRTY.AC.trim().length() == 0 
            && DDCUDRAC.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSDRTY.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSDRTY.TX_AMT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
        } else {
            if (DDCSDRTY.TX_AMT < 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_INVALID;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B010_DRAW_AC_PRCO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = DDCSDRTY.AC;
        DDRCCY.CCY = DDCSDRTY.CCY;
        DDRCCY.CCY_TYPE = DDCSDRTY.CCY_TYPE;
        T000_READ_DDTCCY();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CCY_REC_FLG = 'N';
        }
        WS_TX_AMT = DDCSDRTY.TX_AMT;
        CEP.TRC(SCCGWA, WS_TX_AMT);
        WS_CCY_TYPE_FLG = 'N';
        IBS.init(SCCGWA, DDCUDRAC);
        if (WS_CCY_REC_FLG != 'N') {
            DDCUDRAC.CCY_TYPE = DDCSDRTY.CCY_TYPE;
            DDCUDRAC.TX_AMT = DDCSDRTY.TX_AMT;
            B015_DATE_PASS_DRAC_PROC();
        }
        CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
        if (DDCSDRTY.CCY_TYPE == '1' 
            && DDCSDRTY.TX_AMT != DDCUDRAC.TX_AMT) {
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.KEY.AC = DDCSDRTY.AC;
            DDRCCY.CCY = DDCSDRTY.CCY;
            DDRCCY.CCY_TYPE = '2';
            T000_READ_DDTCCY();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_CCY_TYPE_FLG = 'Y';
            }
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1' 
                && DDCUDRAC.TX_AMT == 0 
                && WS_CCY_REC_FLG != 'N') {
                CEP.TRC(SCCGWA, "11111");
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_BAL_ZERO;
                S000_ERR_MSG_PROC();
            }
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1' 
                && WS_CCY_REC_FLG == 'N') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NOT_SUPP_INPUT_CCY;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, WS_CCY_TYPE_FLG);
        if (WS_CCY_TYPE_FLG == 'Y') {
            WS_OTHE_AMT = DDCUDRAC.TX_AMT;
            CEP.TRC(SCCGWA, WS_OTHE_AMT);
            IBS.init(SCCGWA, DDCUDRAC);
            DDCUDRAC.TX_AMT = DDCSDRTY.TX_AMT - WS_OTHE_AMT;
            CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
            DDCUDRAC.CCY_TYPE = '2';
            B015_DATE_PASS_DRAC_PROC();
            CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
            DDCSDRTY.TX_AMT = DDCUDRAC.TX_AMT + WS_OTHE_AMT;
            CEP.TRC(SCCGWA, DDCSDRTY.TX_AMT);
            if (DDCSDRTY.TX_AMT == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_BAL_ZERO;
                S000_ERR_MSG_PROC();
            }
        } else {
            DDCSDRTY.TX_AMT = WS_TX_AMT;
            CEP.TRC(SCCGWA, DDCSDRTY.TX_AMT);
        }
    }
    public void B015_DATE_PASS_DRAC_PROC() throws IOException,SQLException,Exception {
        if (DDCSDRTY.TX_BAL_FLG == ' ') {
            DDCSDRTY.TX_BAL_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, DDCSDRTY.TX_BAL_FLG);
        DDCUDRAC.AC = DDCSDRTY.AC;
        DDCUDRAC.CCY = DDCSDRTY.CCY;
        DDCUDRAC.CLEAR_FLG = DDCSDRTY.CLEAR_FLG;
        DDCUDRAC.VAL_DATE = DDCSDRTY.VAL_DATE;
        DDCUDRAC.PAY_PSWD = DDCSDRTY.PAY_PSWD;
        DDCUDRAC.ID_TYPE = DDCSDRTY.ID_TYPE;
        DDCUDRAC.ID_NO = DDCSDRTY.ID_NO;
        DDCUDRAC.PSBK_NO = DDCSDRTY.PSBK_NO;
        DDCUDRAC.CARD_NO = DDCSDRTY.CARD_NO;
        DDCUDRAC.CHQ_TYPE = DDCSDRTY.CHQ_TYPE;
        DDCUDRAC.CHQ_NO = DDCSDRTY.CHQ_NO;
        DDCUDRAC.CHQ_ISS_DATE = DDCSDRTY.CHQ_ISS_DATE;
        DDCUDRAC.OTHER_AC = DDCSDRTY.OTHER_AC;
        DDCUDRAC.OTHER_CCY = DDCSDRTY.OTHER_CCY;
        DDCUDRAC.OTHER_AMT = DDCSDRTY.OTHER_AMT;
        DDCUDRAC.TX_TYPE = DDCSDRTY.TX_TYPE;
        DDCUDRAC.NARRATIVE = DDCSDRTY.NARRATIVE;
        DDCUDRAC.TX_MMO = DDCSDRTY.TX_MMO;
        DDCUDRAC.TX_REF = DDCSDRTY.TX_REF;
        DDCUDRAC.TX_BAL_FLG = DDCSDRTY.TX_BAL_FLG;
        CEP.TRC(SCCGWA, DDCUDRAC.TX_BAL_FLG);
        DDCUDRAC.REMARKS = DDCSDRTY.REMARKS;
        DDCUDRAC.CHK_PSW_FLG = DDCSDRTY.CHK_PSW_FLG;
        DDCUDRAC.BANK_DR_FLG = DDCSDRTY.BANK_DR_FLG;
        DDCUDRAC.GD_WITHDR_FLG = DDCSDRTY.GD_WITHDR_FLG;
        DDCUDRAC.HLD_NO = DDCSDRTY.HLD_NO;
        DDCUDRAC.PAY_TYPE = DDCSDRTY.PAY_TYPE;
        DDCUDRAC.PAY_SIGN_NO = DDCSDRTY.PAY_SIGN_NO;
        DDCUDRAC.BV_TYP = DDCSDRTY.BV_TYP;
        DDCUDRAC.CHK_PSW = DDCSDRTY.CHK_PSW;
        DDCUDRAC.TRK_DATE2 = DDCSDRTY.TRK_DATE2;
        DDCUDRAC.TRK_DATE3 = DDCSDRTY.TRK_DATE3;
        DDCUDRAC.RLT_AC = DDCSDRTY.RLT_AC;
        DDCUDRAC.RLT_AC_NAME = DDCSDRTY.RLT_AC_NAME;
        DDCUDRAC.RLT_REF_NO = DDCSDRTY.RLT_BANK;
        DDCUDRAC.RLT_REF_NO = DDCSDRTY.RLT_REF_NO;
        DDCUDRAC.RLT_CCY = DDCSDRTY.RLT_CCY;
        S000_CALL_DDZUDRAC();
        WS_TX_AMT = DDCUDRAC.TX_AMT;
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "AC = :DDRCCY.KEY.AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_UNIT_DRAW_PROC, DDCUDRAC);
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
