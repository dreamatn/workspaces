package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.AI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSCCCY {
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "DD101";
    String K_OUTPUT_FMT2 = "DD111";
    String K_SVR_CD = "DD-SVR-CLEAR-CCY";
    String K_HIS_MMO = "S101";
    String WS_ERR_MSG = " ";
    double WS_TRF_AMT = 0;
    double WS_CLS_BAL = 0;
    String WS_POST_CI = " ";
    String WS_TRF_CI = " ";
    String WS_STMT_CI = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    DDCUPINT DDCUPINT = new DDCUPINT();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCOCCCY DDCOCCCY = new DDCOCCCY();
    DDCOPINT DDCOPINT = new DDCOPINT();
    AICUUPIA AICUUPIA = new AICUUPIA();
    AIRMIB AIRMIB = new AIRMIB();
    AIRCMIB AIRCMIB = new AIRCMIB();
    SCCGWA SCCGWA;
    DDCSCCCY DDCSCCCY;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCSCCCY DDCSCCCY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSCCCY = DDCSCCCY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSCCCY return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B020_POST_INT_PROC();
            B050_CLEAR_CCY_PROC();
        } else {
            B050_CLEAR_CCY_PROC();
            B020_POST_INT_PROC();
        }
        if (WS_CLS_BAL != 0) {
            if (DDCSCCCY.PAY_MTH == 'T') {
                if (DDCSCCCY.TRF_AC == null) DDCSCCCY.TRF_AC = "";
                JIBS_tmp_int = DDCSCCCY.TRF_AC.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) DDCSCCCY.TRF_AC += " ";
                if (DDCSCCCY.TRF_AC.substring(0, 1).equalsIgnoreCase("7")) {
                    B060_TRF_CLS_AMT_PROC();
                } else {
                    B065_TRF_AI_AMT_PROC();
                }
            } else {
                B070_SUB_CASH_BOX_PROC();
            }
        }
        B150_TRANS_DATA_OUTPUT();
        B151_DATA_OUTPUT();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSCCCY.AC);
        CEP.TRC(SCCGWA, DDCSCCCY.CCY);
        CEP.TRC(SCCGWA, DDCSCCCY.PAY_MTH);
        CEP.TRC(SCCGWA, DDCSCCCY.TRF_AC);
        CEP.TRC(SCCGWA, DDCSCCCY.RMK);
        CEP.TRC(SCCGWA, DDCSCCCY.PAY_TYPE);
        CEP.TRC(SCCGWA, DDCSCCCY.ID_TYPE);
        CEP.TRC(SCCGWA, DDCSCCCY.ID_NO);
        CEP.TRC(SCCGWA, DDCSCCCY.PAY_SIGN_NO);
        if (DDCSCCCY.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSCCCY.PAY_MTH == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PAY_MTH_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSCCCY.TRF_AC.trim().length() == 0 
            && DDCSCCCY.PAY_MTH == 'T') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TRF_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSCCCY.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_POST_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUPINT);
        DDCUPINT.AC = DDCSCCCY.AC;
        DDCUPINT.CCY = DDCSCCCY.CCY;
        DDCUPINT.CCY_TYPE = DDCSCCCY.CCY_TYPE;
        DDCUPINT.TX_MMO = K_HIS_MMO;
        DDCUPINT.REMARK = DDCSCCCY.RMK;
        DDCUPINT.TX_TYP = 'O';
        CEP.TRC(SCCGWA, DDCUPINT.CCY);
        CEP.TRC(SCCGWA, DDCUPINT.CCY_TYPE);
        S000_CALL_DDZUPINT();
    }
    public void B050_CLEAR_CCY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        if (DDCSCCCY.PAY_MTH == 'C') {
            DDCUDRAC.TX_TYPE = 'C';
            DDCUDRAC.TX_MMO = "C003";
        } else {
            if (DDCSCCCY.PAY_MTH == 'T') {
                DDCUDRAC.TX_TYPE = 'T';
                DDCUDRAC.TX_MMO = "A019";
            }
        }
        DDCUDRAC.AC = DDCSCCCY.AC;
        DDCUDRAC.OTHER_AC = DDCSCCCY.TRF_AC;
        DDCUDRAC.CCY = DDCSCCCY.CCY;
        DDCUDRAC.CCY_TYPE = DDCSCCCY.CCY_TYPE;
        DDCUDRAC.PSWD = DDCSCCCY.PSWD;
        DDCUDRAC.CARD_NO = DDCSCCCY.CARD_NO;
        DDCUDRAC.PSBK_NO = DDCSCCCY.PSBK_NO;
        DDCUDRAC.CHQ_TYPE = DDCSCCCY.CHQ_TYPE;
        DDCUDRAC.CHQ_NO = DDCSCCCY.CHQ_NO;
        DDCUDRAC.ID_TYPE = DDCSCCCY.ID_TYPE;
        DDCUDRAC.ID_NO = DDCSCCCY.ID_NO;
        DDCUDRAC.PAY_TYPE = DDCSCCCY.PAY_TYPE;
        DDCUDRAC.PAY_SIGN_NO = DDCSCCCY.PAY_SIGN_NO;
        DDCUDRAC.CLEAR_FLG = 'Y';
        S000_CALL_DDZUDRAC();
        WS_CLS_BAL = DDCUDRAC.TX_AMT;
    }
    public void B060_TRF_CLS_AMT_PROC() throws IOException,SQLException,Exception {
        if ((DDCSCCCY.TRF_AC.trim().length() > 0) 
            && (WS_CLS_BAL != 0)) {
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.TX_TYPE = 'T';
            DDCUCRAC.AC = DDCSCCCY.TRF_AC;
            DDCUCRAC.CARD_NO = DDCSCCCY.TRF_CARD;
            DDCUCRAC.CCY = DDCSCCCY.CCY;
            DDCUCRAC.CCY_TYPE = DDCSCCCY.CCY_TYPE;
            DDCUCRAC.OTHER_AC = DDCSCCCY.AC;
            DDCUCRAC.TX_MMO = "A001";
            DDCUCRAC.TX_AMT = DDCUDRAC.TX_AMT;
            S000_CALL_DDZUCRAC();
        }
    }
    public void B065_TRF_AI_AMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = DDCSCCCY.TRF_AC;
        AICUUPIA.DATA.RVS_SEQ = 0;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.AMT = WS_CLS_BAL;
        AICUUPIA.DATA.CCY = DDCSCCCY.CCY;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.POST_NARR = " ";
        AICUUPIA.DATA.RVS_NO = "ODE";
        AICUUPIA.DATA.EVENT_CODE = "DR";
        S000_CALL_AIZUUPIA();
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B150_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOCCCY);
        DDCOCCCY.TX_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCOCCCY.AC = DDCSCCCY.AC;
        DDCOCCCY.AC_CNM = DDCSCCCY.AC_CNM;
        DDCOCCCY.AC_ENM = DDCSCCCY.AC_ENM;
        DDCOCCCY.CCY = DDCSCCCY.CCY;
        DDCOCCCY.CCY_TYPE = DDCSCCCY.CCY_TYPE;
        DDCOCCCY.CARD_NO = DDCSCCCY.CARD_NO;
        DDCOCCCY.PSBK_NO = DDCSCCCY.PSBK_NO;
        DDCOCCCY.CHQ_TYPE = DDCSCCCY.CHQ_TYPE;
        DDCOCCCY.CHQ_NO = DDCSCCCY.CHQ_NO;
        DDCOCCCY.ID_TYPE = DDCSCCCY.ID_TYPE;
        DDCOCCCY.ID_NO = DDCSCCCY.ID_NO;
        DDCOCCCY.PRIN = DDCSCCCY.PRIN;
        DDCOCCCY.DEP_INT = DDCSCCCY.DEP_INT;
        DDCOCCCY.INT_TAX = DDCSCCCY.INT_TAX;
        DDCOCCCY.OD_INT = DDCSCCCY.OD_INT;
        DDCOCCCY.TOT_BAL = WS_CLS_BAL;
        DDCOCCCY.PAY_MTH = DDCSCCCY.PAY_MTH;
        DDCOCCCY.TRF_AC = DDCSCCCY.TRF_AC;
        DDCOCCCY.TRF_CARD = DDCSCCCY.TRF_CARD;
        if (DDCSCCCY.TRF_AC.trim().length() == 0) {
            DDCOCCCY.TRF_CNM = "CASH";
        } else {
            DDCOCCCY.TRF_CNM = DDCSCCCY.TRF_CNM;
        }
        DDCOCCCY.TRF_ENM = DDCSCCCY.TRF_ENM;
        DDCOCCCY.CASH_NO = DDCSCCCY.CASH_NO;
        DDCOCCCY.RMK = DDCSCCCY.RMK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOCCCY;
        SCCFMT.DATA_LEN = 1376;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B151_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOPINT);
        DDCOPINT.TX_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCOPINT.AC = DDCSCCCY.AC;
        DDCOPINT.CCY = DDCSCCCY.CCY;
        DDCOPINT.CCY_TYPE = DDCSCCCY.CCY_TYPE;
        DDCOPINT.PRIN = DDCSCCCY.PRIN;
        DDCOPINT.DEP_INT = DDCSCCCY.DEP_INT;
        DDCOPINT.INT_TAX = DDCSCCCY.INT_TAX;
        DDCOPINT.OD_INT = DDCSCCCY.OD_INT;
        DDCOPINT.TOT_BAL = DDCSCCCY.PRIN + DDCSCCCY.DEP_INT - DDCSCCCY.INT_TAX - DDCSCCCY.OD_INT;
        DDCOPINT.RMK = DDCSCCCY.RMK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT2;
        SCCFMT.DATA_PTR = DDCOPINT;
        SCCFMT.DATA_LEN = 389;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B070_SUB_CASH_BOX_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUSBOX);
        BPCUSBOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUSBOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUSBOX.CCY = DDCSCCCY.CCY;
        BPCUSBOX.AMT = WS_CLS_BAL;
        BPCUSBOX.OPP_AC = DDCSCCCY.AC;
        BPCUSBOX.CASH_NO = DDCSCCCY.CASH_NO;
        S000_CALL_BPZUSBOX();
    }
    public void S000_CALL_DDZUPINT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-POST-INT", DDCUPINT);
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZUSBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-SUB-CBOX", BPCUSBOX);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
