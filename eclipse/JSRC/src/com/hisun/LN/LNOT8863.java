package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT8863 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCICIQ LNCICIQ = new LNCICIQ();
    SCCGWA SCCGWA;
    LNB8865_AWA_8865 LNB8865_AWA_8865;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT8863 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB8865_AWA_8865>");
        LNB8865_AWA_8865 = (LNB8865_AWA_8865) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        LNCICIQ.RC.RC_MMO = "LN";
        LNCICIQ.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, LNB8865_AWA_8865.PROD_CD);
        CEP.TRC(SCCGWA, LNB8865_AWA_8865.DRAW_MTH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_GET_PROD_PARM();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB8865_AWA_8865.PROD_CD.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB8865_AWA_8865.PROD_CD_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_GET_PROD_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICIQ);
        LNCICIQ.DATA.PROD_CD = LNB8865_AWA_8865.PROD_CD;
        LNCICIQ.DRAW_MTH = LNB8865_AWA_8865.DRAW_MTH;
        LNCICIQ.ICQ_OPT = 'P';
        LNCICIQ.FUNC = 'N';
        S000_CALL_LNZICIQ();
    }
    public void S000_CALL_LNZICIQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-CI-INFO", LNCICIQ);
        if (LNCICIQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCICIQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}