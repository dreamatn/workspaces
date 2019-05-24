package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT8802 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    TDCAACE TDCAACE = new TDCAACE();
    TDCSINQ TDCSINQ = new TDCSINQ();
    SCCGWA SCCGWA;
    TDB8802_AWA_8802 TDB8802_AWA_8802;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT8802 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB8802_AWA_8802>");
        TDB8802_AWA_8802 = (TDB8802_AWA_8802) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_QURY_SETT_INT_PROC();
    }
    public void B020_QURY_SETT_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCSINQ);
        TDCSINQ.FUNC = TDB8802_AWA_8802.FUNC;
        TDCSINQ.CI_NO = TDB8802_AWA_8802.CI_NO;
        TDCSINQ.AC_NO = TDB8802_AWA_8802.AC_NO;
        TDCSINQ.PROD_CD = TDB8802_AWA_8802.PROD_CD;
        TDCSINQ.PAGE_ROW = TDB8802_AWA_8802.PAGE_ROW;
        TDCSINQ.PAGE_NUM = TDB8802_AWA_8802.PAGE_NUM;
        S000_CALL_TDZSINQ();
    }
    public void S000_CALL_TDZSINQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TST-TDZSINQ", TDCSINQ);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
