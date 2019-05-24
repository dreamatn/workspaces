package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT4524 {
    String CPN_S_PRTR_MAINT = "BP-S-PRTR-MAINT  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICSGLRP AICSGLRP = new AICSGLRP();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB4520_AWA_4520 AIB4520_AWA_4520;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT4524 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB4520_AWA_4520>");
        AIB4520_AWA_4520 = (AIB4520_AWA_4520) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQ_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AIB4520_AWA_4520.REP_ID.trim().length() == 0) {
            CEP.TRC(SCCGWA, "REPORT ID MUST INPUT");
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_REP_ID_MST_INPUT;
            WS_FLD_NO = AIB4520_AWA_4520.REP_ID_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_INQ_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSGLRP);
        AICSGLRP.I_FUNC = 'I';
        AICSGLRP.RPT_ID = AIB4520_AWA_4520.REP_ID;
        AICSGLRP.RPT_GL_ITEM = AIB4520_AWA_4520.ITEM_CON;
        CEP.TRC(SCCGWA, "TEST AIOT4524");
        CEP.TRC(SCCGWA, AICSGLRP.RPT_ID);
        CEP.TRC(SCCGWA, AICSGLRP.RPT_GL_ITEM);
        S00_CALL_AIZSGLRP();
    }
    public void S00_CALL_AIZSGLRP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-S-GLRP-MAINT", AICSGLRP);
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
