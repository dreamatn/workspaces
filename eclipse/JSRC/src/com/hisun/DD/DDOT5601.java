package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5601 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    char WS_FUNC_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    DDCSFBID DDCSFBID = new DDCSFBID();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    DDB5600_AWA_5600 DDB5600_AWA_5600;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5601 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5600_AWA_5600>");
        DDB5600_AWA_5600 = (DDB5600_AWA_5600) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT();
        B030_TRANS_MAIN_PROC();
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5600_AWA_5600.AC_NO);
        CEP.TRC(SCCGWA, DDB5600_AWA_5600.TYPE);
        CEP.TRC(SCCGWA, DDB5600_AWA_5600.REF_NO);
        if (DDB5600_AWA_5600.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5600_AWA_5600.TYPE == '4') {
            if (DDB5600_AWA_5600.TYPE == ' ') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STS_TYP_M_INPUT;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (DDB5600_AWA_5600.REF_NO.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_REF_NO_M_INPUT;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if (DDB5600_AWA_5600.TYPE != '3' 
            && DDB5600_AWA_5600.TYPE != '4') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STS_TYP_INVALID;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B030_TRANS_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSFBID);
        DDCSFBID.KEY.AC_NO = DDB5600_AWA_5600.AC_NO;
        DDCSFBID.KEY.REF_NO = DDB5600_AWA_5600.REF_NO;
        DDCSFBID.KEY.TYPE = DDB5600_AWA_5600.TYPE;
        DDCSFBID.STS = DDB5600_AWA_5600.STS;
        DDCSFBID.AC_NAME = DDB5600_AWA_5600.AC_NAME;
        DDCSFBID.ORG_TYP = DDB5600_AWA_5600.ORG_TYP;
        DDCSFBID.RLS_BOOK_NO = DDB5600_AWA_5600.RLS_BOOK;
        DDCSFBID.ORG_NAME = DDB5600_AWA_5600.ORG_NAME;
        DDCSFBID.RLS_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCSFBID.RLS_REASON = DDB5600_AWA_5600.RLS_RSN;
        DDCSFBID.RLS_TL = SCCGWA.COMM_AREA.TL_ID;
        DDCSFBID.RLS_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDCSFBID.RLS_AUTH_TL = SCCGWA.COMM_AREA.SUP1_ID;
        DDCSFBID.RLAW_NM1 = DDB5600_AWA_5600.RLAW_NM1;
        DDCSFBID.RLAW_NO1 = DDB5600_AWA_5600.RLAW_NO1;
        DDCSFBID.RLAW_NM2 = DDB5600_AWA_5600.RLAW_NM2;
        DDCSFBID.RLAW_NO2 = DDB5600_AWA_5600.RLAW_NO2;
        DDCSFBID.CCY = DDB5600_AWA_5600.CCY;
        DDCSFBID.CCY_TYP = DDB5600_AWA_5600.CCY_TYP;
        DDCSFBID.AC_SEQ = DDB5600_AWA_5600.AC_SEQ;
        CEP.TRC(SCCGWA, DDCSFBID.KEY.AC_NO);
        CEP.TRC(SCCGWA, DDCSFBID.KEY.REF_NO);
        CEP.TRC(SCCGWA, DDCSFBID.KEY.TYPE);
        CEP.TRC(SCCGWA, DDCSFBID.STS);
        CEP.TRC(SCCGWA, DDCSFBID.AC_NAME);
        CEP.TRC(SCCGWA, DDCSFBID.ORG_TYP);
        CEP.TRC(SCCGWA, DDCSFBID.RLS_BOOK_NO);
        CEP.TRC(SCCGWA, DDCSFBID.ORG_NAME);
        CEP.TRC(SCCGWA, DDCSFBID.RLS_DATE);
        CEP.TRC(SCCGWA, DDCSFBID.RLS_REASON);
        CEP.TRC(SCCGWA, DDCSFBID.RLS_TL);
        CEP.TRC(SCCGWA, DDCSFBID.RLS_BR);
        CEP.TRC(SCCGWA, DDCSFBID.RLS_AUTH_TL);
        CEP.TRC(SCCGWA, DDCSFBID.RLAW_NM1);
        CEP.TRC(SCCGWA, DDCSFBID.RLAW_NO1);
        CEP.TRC(SCCGWA, DDCSFBID.RLAW_NM2);
        CEP.TRC(SCCGWA, DDCSFBID.RLAW_NO2);
        CEP.TRC(SCCGWA, DDCSFBID.CCY);
        CEP.TRC(SCCGWA, DDCSFBID.CCY_TYP);
        CEP.TRC(SCCGWA, DDCSFBID.AC_SEQ);
        CEP.TRC(SCCGWA, DDB5600_AWA_5600.TYPE);
        if (DDB5600_AWA_5600.TYPE == '3') {
            if (DDB5600_AWA_5600.REF_NO.trim().length() > 0) {
                DDCSFBID.FUNC = 'B';
            } else {
                DDCSFBID.FUNC = 'A';
            }
            S000_CALL_DDZSFBID();
        } else {
            if (DDB5600_AWA_5600.TYPE == '4') {
                DDCSFBID.FUNC = 'R';
                S000_CALL_DDZSFBID();
            }
        }
    }
    public void S000_CALL_DDZSFBID() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSFBID", DDCSFBID);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
