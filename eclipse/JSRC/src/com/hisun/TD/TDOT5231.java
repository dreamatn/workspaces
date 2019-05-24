package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5231 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    TDCSQINF TDCSQINF = new TDCSQINF();
    SCCGWA SCCGWA;
    TDB5231_AWA_5231 TDB5231_AWA_5231;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT5231 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB5231_AWA_5231>");
        TDB5231_AWA_5231 = (TDB5231_AWA_5231) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QURY_INF_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDB5231_AWA_5231.SMPRD_AC.trim().length() == 0 
            && TDB5231_AWA_5231.SMPRD_CD.trim().length() == 0 
            && TDB5231_AWA_5231.AC_SEQ == 0 
            && TDB5231_AWA_5231.PROD_CD.trim().length() == 0 
            && TDB5231_AWA_5231.SMPRD_CY.trim().length() == 0 
            && TDB5231_AWA_5231.CHQ_TYP == ' ') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INPUT_ONE_MUST;
            if (TDB5231_AWA_5231.SMPRD_AC.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(TDB5231_AWA_5231.SMPRD_AC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_QURY_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCSQINF);
        TDCSQINF.SMPRD_AC = TDB5231_AWA_5231.SMPRD_AC;
        TDCSQINF.PROD_CD = TDB5231_AWA_5231.PROD_CD;
        TDCSQINF.CARD_NO = TDB5231_AWA_5231.SMPRD_CD;
        TDCSQINF.AC_SEQ = TDB5231_AWA_5231.AC_SEQ;
        TDCSQINF.SMPRD_CY = TDB5231_AWA_5231.SMPRD_CY;
        TDCSQINF.SMPRD_TR = TDB5231_AWA_5231.SMPRD_TR;
        TDCSQINF.CHQ_TYP = TDB5231_AWA_5231.CHQ_TYP;
        TDCSQINF.PAGE_ROW = TDB5231_AWA_5231.PAGE_ROW;
        TDCSQINF.PAGE_NUM = TDB5231_AWA_5231.PAGE_NUM;
        S000_CALL_TDZSQINF();
    }
    public void S000_CALL_TDZSQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TDZSQINF", TDCSQINF);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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