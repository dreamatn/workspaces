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

public class TDZSQINF {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    TDCUQINF TDCUQINF = new TDCUQINF();
    SCCGWA SCCGWA;
    TDCSQINF TDCSQINF;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, TDCSQINF TDCSQINF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCSQINF = TDCSQINF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZSQINF return!");
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
        if (TDCSQINF.SMPRD_AC.trim().length() == 0 
            && TDCSQINF.CARD_NO.trim().length() == 0 
            && TDCSQINF.AC_SEQ == 0 
            && TDCSQINF.PROD_CD.trim().length() == 0 
            && TDCSQINF.SMPRD_CY.trim().length() == 0 
            && TDCSQINF.CHQ_TYP == ' ') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INPUT_ONE_MUST;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_QURY_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCUQINF);
        TDCUQINF.INPUT_DATA.SMPRD_AC = TDCSQINF.SMPRD_AC;
        TDCUQINF.INPUT_DATA.CARD_NO = TDCSQINF.CARD_NO;
        TDCUQINF.INPUT_DATA.AC_SEQ = TDCSQINF.AC_SEQ;
        TDCUQINF.INPUT_DATA.PROD_CD = TDCSQINF.PROD_CD;
        TDCUQINF.INPUT_DATA.SMPRD_CY = TDCSQINF.SMPRD_CY;
        TDCUQINF.INPUT_DATA.SMPRD_TR = TDCSQINF.SMPRD_TR;
        TDCUQINF.INPUT_DATA.CHQ_TYP = TDCSQINF.CHQ_TYP;
        TDCUQINF.INPUT_DATA.PAGE_ROW = TDCSQINF.PAGE_ROW;
        TDCUQINF.INPUT_DATA.PAGE_NUM = TDCSQINF.PAGE_NUM;
        S000_CALL_TDZUQINF();
    }
    public void S000_CALL_TDZUQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TDZUQINF", TDCUQINF);
        if (TDCUQINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, TDCUQINF.RC);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (TDCUQINF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "TDZSQINF=");
            CEP.TRC(SCCGWA, TDCUQINF);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
