package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5443 {
    String CPN_DD_S_LCHQ_PROC = "DD-S-LCHQ-PROC";
    String WS_ERR_MSG = " ";
    short WS_RET = 0;
    short WS_RMDR = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    DDCSLCHQ DDCSLCHQ = new DDCSLCHQ();
    SCCGWA SCCGWA;
    DDB5440_AWA_5440 DDB5440_AWA_5440;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5443 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5440_AWA_5440>");
        DDB5440_AWA_5440 = (DDB5440_AWA_5440) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B20_RECORD_CHQ_LOST_REC_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5440_AWA_5440.AC_NO);
        CEP.TRC(SCCGWA, DDB5440_AWA_5440.ENAME);
        CEP.TRC(SCCGWA, DDB5440_AWA_5440.ENAME);
        CEP.TRC(SCCGWA, DDB5440_AWA_5440.CCY);
        CEP.TRC(SCCGWA, DDB5440_AWA_5440.TYPE);
        CEP.TRC(SCCGWA, DDB5440_AWA_5440.STR_NO);
        CEP.TRC(SCCGWA, DDB5440_AWA_5440.END_NO);
        CEP.TRC(SCCGWA, DDB5440_AWA_5440.QTY);
        if (DDB5440_AWA_5440.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB5440_AWA_5440.STR_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STR_CHQ_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B20_RECORD_CHQ_LOST_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSLCHQ);
        DDCSLCHQ.AC_NO = DDB5440_AWA_5440.AC_NO;
        DDCSLCHQ.CHQ_TYP = DDB5440_AWA_5440.QTY;
        DDCSLCHQ.STR_CHQ_NO = DDB5440_AWA_5440.STR_NO;
        DDCSLCHQ.CHQ_AMT = DDB5440_AWA_5440.AMT;
        DDCSLCHQ.CHQ_DATE = DDB5440_AWA_5440.CHQ_DATE;
        DDCSLCHQ.PAYEE = DDB5440_AWA_5440.PAYEE;
        DDCSLCHQ.LOST_RSN = DDB5440_AWA_5440.LOST_RSN;
        DDCSLCHQ.LOS_DATE = DDB5440_AWA_5440.LOS_DATE;
        DDCSLCHQ.LOS_ADDR = DDB5440_AWA_5440.LOS_ADDR;
        DDCSLCHQ.LOST_NAME = DDB5440_AWA_5440.LOST_NAM;
        DDCSLCHQ.PAPER_TY = DDB5440_AWA_5440.PAPER_TY;
        DDCSLCHQ.PAPER_NO = DDB5440_AWA_5440.PAPER_NO;
        DDCSLCHQ.TEL_NO = DDB5440_AWA_5440.TEL_NO;
        DDCSLCHQ.RLS_NAME = DDB5440_AWA_5440.RLS_NAME;
        DDCSLCHQ.RLS_PAPER_TY = DDB5440_AWA_5440.RLS_TYP;
        DDCSLCHQ.RLS_PAPER_NO = DDB5440_AWA_5440.RLS_NO;
        DDCSLCHQ.RLS_TEL_NO = DDB5440_AWA_5440.RLSTELNO;
        DDCSLCHQ.CON_ADDR = DDB5440_AWA_5440.CON_ADDR;
        DDCSLCHQ.LOST_TYPE = DDB5440_AWA_5440.LOSTTYPE;
        DDCSLCHQ.FUNC = DDB5440_AWA_5440.OPT;
        DDCSLCHQ.UNW_LOST_DATE = DDB5440_AWA_5440.ULSTDATE;
        DDCSLCHQ.W_LOST_DATE = DDB5440_AWA_5440.WLSTDATE;
        DDCSLCHQ.EXP_DATE = DDB5440_AWA_5440.EXPDATE;
        DDCSLCHQ.REMARKS = DDB5440_AWA_5440.REMARKS;
        DDCSLCHQ.W_LOST_NAME = DDB5440_AWA_5440.W_LOSTNM;
        DDCSLCHQ.W_PAPER_TYP = DDB5440_AWA_5440.PAPERTYP;
        DDCSLCHQ.W_PAPER_NO = DDB5440_AWA_5440.PAPERNO;
        DDCSLCHQ.BV_TYP = DDB5440_AWA_5440.BV_TYP;
        DDCSLCHQ.BV_CODE = DDB5440_AWA_5440.BV_CODE;
        DDCSLCHQ.LOS_NO = DDB5440_AWA_5440.LOS_NO;
        CEP.TRC(SCCGWA, DDB5440_AWA_5440.OPT);
        CEP.TRC(SCCGWA, DDCSLCHQ.CHQ_TYP);
        CEP.TRC(SCCGWA, DDB5440_AWA_5440.STR_NO);
        CEP.TRC(SCCGWA, DDCSLCHQ.UNW_LOST_DATE);
        S000_CALL_DDZSLCHQ();
    }
    public void S000_CALL_DDZSLCHQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DD_S_LCHQ_PROC, DDCSLCHQ);
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