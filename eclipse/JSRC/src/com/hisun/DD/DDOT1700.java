package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1700 {
    String CPN_CCY_QUERY = "BP-INQUIRE-CCY";
    String PGM_SCSSCKDT = "SCSSCKDT";
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSCSHD DDCSCSHD = new DDCSCSHD();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    DDB1700_AWA_1700 DDB1700_AWA_1700;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT1700 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1700_AWA_1700>");
        DDB1700_AWA_1700 = (DDB1700_AWA_1700) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B030_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDB1700_AWA_1700.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            WS_FLD_NO = DDB1700_AWA_1700.AC_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDB1700_AWA_1700.CCY);
        if (DDB1700_AWA_1700.CCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = DDB1700_AWA_1700.CCY;
            CEP.TRC(SCCGWA, DDB1700_AWA_1700.CCY);
            S000_CALL_BPZQCCY();
            CEP.TRC(SCCGWA, BPCQCCY.RC.RTNCODE);
            if (BPCQCCY.RC.RTNCODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
                if (DDB1700_AWA_1700.CCY.trim().length() == 0) WS_FLD_NO = 0;
                else WS_FLD_NO = Short.parseShort(DDB1700_AWA_1700.CCY);
                S000_ERR_MSG_PROC();
            }
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            WS_FLD_NO = DDB1700_AWA_1700.CCY_NO;
            S000_ERR_MSG_PROC();
        }
        if (DDB1700_AWA_1700.CASH_AMT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_M_INPUT;
            WS_FLD_NO = DDB1700_AWA_1700.CASH_AMT_NO;
            S000_ERR_MSG_PROC();
        } else {
            if (DDB1700_AWA_1700.CASH_AMT < 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_INVALID;
                WS_FLD_NO = DDB1700_AWA_1700.CASH_AMT_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (DDB1700_AWA_1700.CCY_TYPE == ' ' 
            && !DDB1700_AWA_1700.CCY.equalsIgnoreCase("156")) {
            DDB1700_AWA_1700.CCY_TYPE = 'C';
        }
    }
    public void B030_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCSHD);
        DDCSCSHD.CARD_NO = DDB1700_AWA_1700.CARD_NO;
        DDCSCSHD.AC = DDB1700_AWA_1700.AC;
        DDCSCSHD.CCY = DDB1700_AWA_1700.CCY;
        DDCSCSHD.CCY_TYPE = DDB1700_AWA_1700.CCY_TYPE;
        DDCSCSHD.CASH_AMT = DDB1700_AWA_1700.CASH_AMT;
        DDCSCSHD.CASH_CCY = DDB1700_AWA_1700.CASH_CCY;
        DDCSCSHD.CASH_CCY_TYPE = DDB1700_AWA_1700.CASH_C_Y;
        DDCSCSHD.VAL_DATE = DDB1700_AWA_1700.VAL_DATE;
        DDCSCSHD.CASH_NO = DDB1700_AWA_1700.CASH_NO;
        DDCSCSHD.TX_RMK = DDB1700_AWA_1700.TX_RMK;
        DDCSCSHD.REMARKS = DDB1700_AWA_1700.REMARKS;
        DDCSCSHD.CHI_NM = DDB1700_AWA_1700.CHI_NM;
        DDCSCSHD.ENG_NM = DDB1700_AWA_1700.ENG_NM;
        DDCSCSHD.PAY_PERSON = DDB1700_AWA_1700.PAY_P;
        DDCSCSHD.BV_TYP = DDB1700_AWA_1700.BV_TYP;
        DDCSCSHD.BV_VTYP = DDB1700_AWA_1700.BV_VTYP;
        DDCSCSHD.BV_VNO = DDB1700_AWA_1700.BV_VNO;
        DDCSCSHD.TX_MMO = DDB1700_AWA_1700.TX_MMO;
        DDCSCSHD.NARRATIVE = DDB1700_AWA_1700.IN_RMK;
        DDCSCSHD.PSBK_SEQ = DDB1700_AWA_1700.PSBK_SEQ;
        DDCSCSHD.CREV_NO = DDB1700_AWA_1700.RVS_NO;
        DDCSCSHD.REG_CD = DDB1700_AWA_1700.REG_CD;
        DDCSCSHD.TRT_CTLW = DDB1700_AWA_1700.TRT_CTLW;
        CEP.TRC(SCCGWA, DDB1700_AWA_1700.PSBK_SEQ);
        CEP.TRC(SCCGWA, DDB1700_AWA_1700.REMARKS);
        CEP.TRC(SCCGWA, DDB1700_AWA_1700.TX_RMK);
        CEP.TRC(SCCGWA, DDB1700_AWA_1700.IN_RMK);
        S000_CALL_DDZSCSHD();
        CEP.TRC(SCCGWA, "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    }
    public void S000_CALL_DDZSCSHD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSCSHD", DDCSCSHD);
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
