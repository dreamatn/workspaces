package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT8860 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSLXTZ DDCSLXTZ = new DDCSLXTZ();
    SCCGWA SCCGWA;
    DDB8860_AWA_8860 DDB8860_AWA_8860;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT8860 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB8860_AWA_8860>");
        DDB8860_AWA_8860 = (DDB8860_AWA_8860) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B030_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB8860_AWA_8860.CUS_AC);
        CEP.TRC(SCCGWA, DDB8860_AWA_8860.CCY);
        CEP.TRC(SCCGWA, DDB8860_AWA_8860.CCY_TYP);
        CEP.TRC(SCCGWA, DDB8860_AWA_8860.DTOT_INT);
        CEP.TRC(SCCGWA, DDB8860_AWA_8860.OTOT_INT);
        CEP.TRC(SCCGWA, DDB8860_AWA_8860.STOT_INT);
        CEP.TRC(SCCGWA, DDB8860_AWA_8860.DADJ_INT);
        CEP.TRC(SCCGWA, DDB8860_AWA_8860.OADJ_INT);
        CEP.TRC(SCCGWA, DDB8860_AWA_8860.SADJ_INT);
        CEP.TRC(SCCGWA, DDB8860_AWA_8860.ADJD_INT);
        CEP.TRC(SCCGWA, DDB8860_AWA_8860.ADJO_INT);
        CEP.TRC(SCCGWA, DDB8860_AWA_8860.ADJS_INT);
        if (DDB8860_AWA_8860.FUNC == 'U' 
            && DDB8860_AWA_8860.DADJ_INT == 0 
            && DDB8860_AWA_8860.OADJ_INT == 0 
            && DDB8860_AWA_8860.SADJ_INT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ADJ_INT_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSLXTZ);
        DDCSLXTZ.FUNC = DDB8860_AWA_8860.FUNC;
        DDCSLXTZ.CUS_AC = DDB8860_AWA_8860.CUS_AC;
        DDCSLXTZ.CCY = DDB8860_AWA_8860.CCY;
        DDCSLXTZ.CCY_TYP = DDB8860_AWA_8860.CCY_TYP;
        DDCSLXTZ.DTOT_INT = DDB8860_AWA_8860.DTOT_INT;
        DDCSLXTZ.OTOT_INT = DDB8860_AWA_8860.OTOT_INT;
        DDCSLXTZ.STOT_INT = DDB8860_AWA_8860.STOT_INT;
        DDCSLXTZ.DADJ_INT = DDB8860_AWA_8860.DADJ_INT;
        DDCSLXTZ.OADJ_INT = DDB8860_AWA_8860.OADJ_INT;
        DDCSLXTZ.SADJ_INT = DDB8860_AWA_8860.SADJ_INT;
        DDCSLXTZ.ADJD_INT = DDB8860_AWA_8860.ADJD_INT;
        DDCSLXTZ.ADJO_INT = DDB8860_AWA_8860.ADJO_INT;
        DDCSLXTZ.ADJS_INT = DDB8860_AWA_8860.ADJS_INT;
        S000_CALL_DDZSLXTZ();
    }
    public void S000_CALL_DDZSLXTZ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSLXTZ", DDCSLXTZ);
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
