package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

import java.io.IOException;
import java.sql.SQLException;

public class TDZSQPDF {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    TDCUQPDF TDCUQPDF = new TDCUQPDF();
    SCCGWA SCCGWA;
    TDCSQPDF TDCSQPDF;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, TDCSQPDF TDCSQPDF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCSQPDF = TDCSQPDF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZSQPDF return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QURY_INF_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDCSQPDF.SMPRD_AC.trim().length() == 0 
            && TDCSQPDF.CARD_NO.trim().length() == 0 
            && TDCSQPDF.AC_SEQ == 0 
            && TDCSQPDF.PROD_CD.trim().length() == 0 
            && TDCSQPDF.SMPRD_CY.trim().length() == 0 
            && TDCSQPDF.CHQ_TYP == ' ') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INPUT_ONE_MUST;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_QURY_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCUQPDF);
        TDCUQPDF.INPUT_DATA.SMPRD_AC = TDCSQPDF.SMPRD_AC;
        TDCUQPDF.INPUT_DATA.CARD_NO = TDCSQPDF.CARD_NO;
        TDCUQPDF.INPUT_DATA.AC_SEQ = TDCSQPDF.AC_SEQ;
        TDCUQPDF.INPUT_DATA.PROD_CD = TDCSQPDF.PROD_CD;
        TDCUQPDF.INPUT_DATA.SMPRD_CY = TDCSQPDF.SMPRD_CY;
        TDCUQPDF.INPUT_DATA.SMPRD_TR = TDCSQPDF.SMPRD_TR;
        TDCUQPDF.INPUT_DATA.CHQ_TYP = TDCSQPDF.CHQ_TYP;
        S000_CALL_TDZUQPDF();
    }
    public void S000_CALL_TDZUQPDF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TDZUQPDF", TDCUQPDF);
        if (TDCUQPDF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, TDCUQPDF.RC);
            S000_ERR_MSG_PROC();
        }
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
