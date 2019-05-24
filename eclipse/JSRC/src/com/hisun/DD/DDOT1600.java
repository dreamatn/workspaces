package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1600 {
    String CPN_CCY_QUERY = "BP-INQUIRE-CCY";
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    int WS_TMP_DT = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DDCSCSHW DDCSCSHW = new DDCSCSHW();
    SCCGWA SCCGWA;
    DDB1600_AWA_1600 DDB1600_AWA_1600;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT1600 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1600_AWA_1600>");
        DDB1600_AWA_1600 = (DDB1600_AWA_1600) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B030_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDB1600_AWA_1600.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB1600_AWA_1600.CCY.trim().length() > 0) {
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB1600_AWA_1600.CASH_AMT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
        } else {
            if (DDB1600_AWA_1600.CASH_AMT < 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_INVALID;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B030_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCSHW);
        DDCSCSHW.BV_TYP = DDB1600_AWA_1600.BV_TYP;
        DDCSCSHW.CARD_NO = DDB1600_AWA_1600.CARD_NO;
        DDCSCSHW.AC = DDB1600_AWA_1600.AC;
        DDCSCSHW.CCY = DDB1600_AWA_1600.CCY;
        DDCSCSHW.CCY_TYPE = DDB1600_AWA_1600.CCY_TYPE;
        DDCSCSHW.PSBK_NO = DDB1600_AWA_1600.PSBK_NO;
        DDCSCSHW.CHQ_TYPE = DDB1600_AWA_1600.CHQ_TYPE;
        DDCSCSHW.CHQ_NO = DDB1600_AWA_1600.CHQ_NO;
        DDCSCSHW.CHQ_ISS_DATE = DDB1600_AWA_1600.CHQ_ISS;
        DDCSCSHW.CASH_AMT = DDB1600_AWA_1600.CASH_AMT;
        DDCSCSHW.TX_RMK = DDB1600_AWA_1600.TX_RMK;
        DDCSCSHW.REMARKS = DDB1600_AWA_1600.REMARKS;
        DDCSCSHW.PSWD = DDB1600_AWA_1600.PSWD;
        DDCSCSHW.CHQ_PSWD = DDB1600_AWA_1600.CHQ_PSWD;
        DDCSCSHW.PAY_TYPE = DDB1600_AWA_1600.PAY_TYPE;
        DDCSCSHW.CHK_PSW = DDB1600_AWA_1600.CHK_PSW;
        DDCSCSHW.TRK_DATE2 = DDB1600_AWA_1600.TRK_DAT2;
        DDCSCSHW.TRK_DATE3 = DDB1600_AWA_1600.TRK_DAT3;
        DDCSCSHW.TX_MMO = DDB1600_AWA_1600.TX_MMO;
        DDCSCSHW.PSBK_SEQ = DDB1600_AWA_1600.PSBK_SEQ;
        DDCSCSHW.CREV_NO = DDB1600_AWA_1600.CREV_NO;
        DDCSCSHW.CASH_NO = DDB1600_AWA_1600.CASH_NO;
        DDCSCSHW.REG_CD = DDB1600_AWA_1600.REG_CD;
        CEP.TRC(SCCGWA, DDCSCSHW.TX_MMO);
        CEP.TRC(SCCGWA, DDCSCSHW.CHK_PSW);
        CEP.TRC(SCCGWA, DDCSCSHW.TRK_DATE2);
        CEP.TRC(SCCGWA, DDCSCSHW.TRK_DATE3);
        CEP.TRC(SCCGWA, DDB1600_AWA_1600.PSBK_SEQ);
        S000_CALL_DDZSCSHW();
    }
    public void S000_CALL_DDZSCSHW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSCSHW", DDCSCSHW);
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CCY_QUERY, BPCQCCY);
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
