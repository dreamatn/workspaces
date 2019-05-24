package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5512 {
    String CPN_S_BRW_HMIB = "AI-S-BRW-HMIB  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICSHMIB AICSHMIB = new AICSHMIB();
    AIRHMIB AIRHMIB = new AIRHMIB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB5510_AWA_5510 AIB5510_AWA_5510;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT5512 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5510_AWA_5510>");
        AIB5510_AWA_5510 = (AIB5510_AWA_5510) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUE_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB5510_AWA_5510.GL_BOOK);
        CEP.TRC(SCCGWA, AIB5510_AWA_5510.AC);
        CEP.TRC(SCCGWA, AIB5510_AWA_5510.CCY);
        CEP.TRC(SCCGWA, AIB5510_AWA_5510.STR_DT);
        CEP.TRC(SCCGWA, AIB5510_AWA_5510.END_DT);
    }
    public void B020_QUE_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSHMIB);
        AICSHMIB.FUNC = 'Q';
        AICSHMIB.GL_BOOK = AIB5510_AWA_5510.GL_BOOK;
        AICSHMIB.AC = AIB5510_AWA_5510.AC;
        AICSHMIB.CCY = AIB5510_AWA_5510.CCY;
        AICSHMIB.STR_DT = AIB5510_AWA_5510.STR_DT;
        AICSHMIB.SET_NO = AIB5510_AWA_5510.SET_NO;
        AICSHMIB.SET_SEQ = AIB5510_AWA_5510.SET_SEQ;
        CEP.TRC(SCCGWA, AICSHMIB.GL_BOOK);
        CEP.TRC(SCCGWA, AICSHMIB.AC);
        CEP.TRC(SCCGWA, AICSHMIB.CCY);
        CEP.TRC(SCCGWA, AICSHMIB.STR_DT);
        S000_CALL_AIZSHMIB();
    }
    public void S000_CALL_AIZSHMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BRW_HMIB, AICSHMIB);
        if (AICSHMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICSHMIB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
