package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5604 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    char WS_FUNC_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    DDCSFBID DDCSFBID = new DDCSFBID();
    DDRMST DDRMST = new DDRMST();
    DDRFBID DDRFBID = new DDRFBID();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDB5603_AWA_5603 DDB5603_AWA_5603;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5604 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5603_AWA_5603>");
        DDB5603_AWA_5603 = (DDB5603_AWA_5603) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B030_TRANS_DATA_OUT();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5603_AWA_5603.AC_NO);
    }
    public void B030_TRANS_DATA_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSFBID);
        DDCSFBID.KEY.AC_NO = DDB5603_AWA_5603.AC_NO;
        DDCSFBID.KEY.TYPE = DDB5603_AWA_5603.TYPE;
        DDCSFBID.AC_NAME = DDB5603_AWA_5603.AC_NAME;
        DDCSFBID.KEY.REF_NO = DDB5603_AWA_5603.REF_NO;
        DDCSFBID.STS = DDB5603_AWA_5603.STS;
        DDCSFBID.CCY = DDB5603_AWA_5603.CCY;
        DDCSFBID.CCY_TYP = DDB5603_AWA_5603.CCY_TYP;
        DDCSFBID.AC_SEQ = DDB5603_AWA_5603.AC_SEQ;
        CEP.TRC(SCCGWA, DDB5603_AWA_5603.STS);
        CEP.TRC(SCCGWA, DDB5603_AWA_5603.AC_NAME);
        CEP.TRC(SCCGWA, DDCSFBID.KEY.AC_NO);
        CEP.TRC(SCCGWA, DDCSFBID.KEY.TYPE);
        CEP.TRC(SCCGWA, DDCSFBID.AC_NAME);
        CEP.TRC(SCCGWA, DDCSFBID.KEY.REF_NO);
        CEP.TRC(SCCGWA, DDCSFBID.STS);
        CEP.TRC(SCCGWA, DDCSFBID.CCY);
        CEP.TRC(SCCGWA, DDCSFBID.CCY_TYP);
        CEP.TRC(SCCGWA, DDCSFBID.AC_SEQ);
        if (DDCSFBID.KEY.AC_NO.trim().length() > 0) {
            DDCSFBID.FUNC = 'L';
        }
        if (DDCSFBID.KEY.REF_NO.trim().length() > 0) {
            DDCSFBID.FUNC = 'I';
        }
        S000_CALL_DDZSFBID();
    }
    public void S000_CALL_DDZSFBID() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSFBID", DDCSFBID);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
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
