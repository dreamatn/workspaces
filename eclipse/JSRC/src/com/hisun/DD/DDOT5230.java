package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5230 {
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    DDCSMPAY DDCSMPAY = new DDCSMPAY();
    SCCGWA SCCGWA;
    DDB5230_AWA_5230 DDB5230_AWA_5230;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5230 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5230_AWA_5230>");
        DDB5230_AWA_5230 = (DDB5230_AWA_5230) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5230_AWA_5230.CARD_NO);
        CEP.TRC(SCCGWA, DDB5230_AWA_5230.AC);
        CEP.TRC(SCCGWA, DDB5230_AWA_5230.AC_CNAME);
        CEP.TRC(SCCGWA, DDB5230_AWA_5230.AC_ENAME);
        CEP.TRC(SCCGWA, DDB5230_AWA_5230.PSBK_NO);
        CEP.TRC(SCCGWA, DDB5230_AWA_5230.PAY_MTHO);
        CEP.TRC(SCCGWA, DDB5230_AWA_5230.PSWD_O);
        CEP.TRC(SCCGWA, DDB5230_AWA_5230.PAY_MTHN);
        CEP.TRC(SCCGWA, DDB5230_AWA_5230.CIPW_FLG);
        CEP.TRC(SCCGWA, DDB5230_AWA_5230.UCIP_FLG);
        CEP.TRC(SCCGWA, DDB5230_AWA_5230.PSWD_N);
        CEP.TRC(SCCGWA, DDB5230_AWA_5230.CR_N);
        CEP.TRC(SCCGWA, DDB5230_AWA_5230.DR_N);
        CEP.TRC(SCCGWA, DDB5230_AWA_5230.ID_TYPE);
        CEP.TRC(SCCGWA, DDB5230_AWA_5230.ID_NO);
        CEP.TRC(SCCGWA, DDB5230_AWA_5230.SIGN_NO);
        CEP.TRC(SCCGWA, DDB5230_AWA_5230.ID_TYP_O);
        CEP.TRC(SCCGWA, DDB5230_AWA_5230.ID_NO_O);
        if (DDB5230_AWA_5230.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5230_AWA_5230.PAY_MTHN != '1' 
            && DDB5230_AWA_5230.PAY_MTHN != '2' 
            && DDB5230_AWA_5230.PAY_MTHN != '3' 
            && DDB5230_AWA_5230.PAY_MTHN != '4' 
            && DDB5230_AWA_5230.PAY_MTHN != '5') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PAY_MTH_ERROR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "111");
        if (DDB5230_AWA_5230.PAY_MTHN == '3') {
            if (DDB5230_AWA_5230.ID_TYPE.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ID_TYP_M_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (DDB5230_AWA_5230.ID_NO.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ID_NO_M_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
        if (DDB5230_AWA_5230.PAY_MTHO == '1' 
            && DDB5230_AWA_5230.PSWD_O.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_PSW_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "222");
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSMPAY);
        DDCSMPAY.CARD_NO = DDB5230_AWA_5230.CARD_NO;
        DDCSMPAY.AC = DDB5230_AWA_5230.AC;
        DDCSMPAY.AC_CNAME = DDB5230_AWA_5230.AC_CNAME;
        DDCSMPAY.AC_ENAME = DDB5230_AWA_5230.AC_ENAME;
        DDCSMPAY.PSBK_NO = DDB5230_AWA_5230.PSBK_NO;
        DDCSMPAY.PAY_MTH_OLD = DDB5230_AWA_5230.PAY_MTHO;
        DDCSMPAY.PSWD_O = DDB5230_AWA_5230.PSWD_O;
        DDCSMPAY.PAY_MTH_NEW = DDB5230_AWA_5230.PAY_MTHN;
        DDCSMPAY.CIPW_FLG = DDB5230_AWA_5230.CIPW_FLG;
        DDCSMPAY.UCIP_FLG = DDB5230_AWA_5230.UCIP_FLG;
        DDCSMPAY.PSWD1 = DDB5230_AWA_5230.PSWD_N;
        if (DDB5230_AWA_5230.PAY_MTHN == '3') {
            DDCSMPAY.ID_TYPE = DDB5230_AWA_5230.ID_TYPE;
            DDCSMPAY.ID_NO = DDB5230_AWA_5230.ID_NO;
        } else {
            DDCSMPAY.ID_TYPE = DDB5230_AWA_5230.ID_TYP_O;
            DDCSMPAY.ID_NO = DDB5230_AWA_5230.ID_NO_O;
        }
        DDCSMPAY.SIGN_NO = DDB5230_AWA_5230.SIGN_NO;
        DDCSMPAY.CRN = DDB5230_AWA_5230.CR_N;
        DDCSMPAY.DRN = DDB5230_AWA_5230.DR_N;
        DDCSMPAY.OIC_NO = DDB5230_AWA_5230.OIC_NO;
        CEP.TRC(SCCGWA, DDCSMPAY.CARD_NO);
        CEP.TRC(SCCGWA, DDCSMPAY.AC);
        CEP.TRC(SCCGWA, DDCSMPAY.AC_CNAME);
        CEP.TRC(SCCGWA, DDCSMPAY.AC_ENAME);
        CEP.TRC(SCCGWA, DDCSMPAY.PSBK_NO);
        CEP.TRC(SCCGWA, DDCSMPAY.PAY_MTH_OLD);
        CEP.TRC(SCCGWA, DDCSMPAY.PSWD_O);
        CEP.TRC(SCCGWA, DDCSMPAY.PAY_MTH_NEW);
        CEP.TRC(SCCGWA, DDCSMPAY.CIPW_FLG);
        CEP.TRC(SCCGWA, DDCSMPAY.UCIP_FLG);
        CEP.TRC(SCCGWA, DDCSMPAY.PSWD1);
        CEP.TRC(SCCGWA, DDCSMPAY.ID_TYPE);
        CEP.TRC(SCCGWA, DDCSMPAY.ID_NO);
        CEP.TRC(SCCGWA, DDCSMPAY.SIGN_NO);
        S000_CALL_DDZSMPAY();
    }
    public void S000_CALL_DDZSMPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-Z-DDZSMPAY", DDCSMPAY);
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
