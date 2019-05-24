package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT4521 {
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
        CEP.TRC(SCCGWA, "AIOT4521 return!");
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
        B020_ADD_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB4520_AWA_4520.REP_ID);
        CEP.TRC(SCCGWA, AIB4520_AWA_4520.ITEM_CON);
        CEP.TRC(SCCGWA, AIB4520_AWA_4520.REP_CAT);
        CEP.TRC(SCCGWA, AIB4520_AWA_4520.REP_SEQ);
        CEP.TRC(SCCGWA, AIB4520_AWA_4520.CAL_METH);
        CEP.TRC(SCCGWA, AIB4520_AWA_4520.FORM);
        if (AIB4520_AWA_4520.REP_ID.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_REP_ID_MST_INPUT;
            WS_FLD_NO = AIB4520_AWA_4520.REP_ID_NO;
            S000_ERR_MSG_PROC();
        }
        if (AIB4520_AWA_4520.ITEM_CON.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITEM_CON_MST_INPUT;
            WS_FLD_NO = AIB4520_AWA_4520.ITEM_CON_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_ADD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSGLRP);
        AICSGLRP.I_FUNC = 'A';
        AICSGLRP.RPT_ID = AIB4520_AWA_4520.REP_ID;
        AICSGLRP.RPT_GL_ITEM = AIB4520_AWA_4520.ITEM_CON;
        AICSGLRP.RPT_CAT = AIB4520_AWA_4520.REP_CAT;
        AICSGLRP.RPT_SEQ = AIB4520_AWA_4520.REP_SEQ;
        AICSGLRP.RPT_CALC_MTH = AIB4520_AWA_4520.CAL_METH;
        AICSGLRP.RPT_FORMULA = AIB4520_AWA_4520.FORM;
        AICSGLRP.RPT_UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, AICSGLRP.RPT_ID);
        CEP.TRC(SCCGWA, AICSGLRP.RPT_GL_ITEM);
        CEP.TRC(SCCGWA, AICSGLRP.RPT_CAT);
        CEP.TRC(SCCGWA, AICSGLRP.RPT_SEQ);
        CEP.TRC(SCCGWA, AICSGLRP.RPT_CALC_MTH);
        CEP.TRC(SCCGWA, AICSGLRP.RPT_FORMULA);
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
