package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZPTC02 {
    boolean pgmRtn = false;
    String K_REQ_SYS_INTGRT = "INTG";
    String K_ASMSG_TX_CD = "TCAS002";
    String WS_ERR_MSG = " ";
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    TCCASMSG TCCASMSG = new TCCASMSG();
    AICRTC02 AICRTC02 = new AICRTC02();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AICOTC02 AICOTC02;
    public void MP(SCCGWA SCCGWA, AICOTC02 AICOTC02) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICOTC02 = AICOTC02;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZPTC02 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, AICOTC02);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_SEND_CONFIRM();
        if (pgmRtn) return;
    }
    public void B010_SEND_CONFIRM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TCCASMSG);
        IBS.init(SCCGWA, AICRTC02);
        TCCASMSG.REQ_APP = K_REQ_SYS_INTGRT;
        TCCASMSG.MSG_CODE = K_ASMSG_TX_CD;
        TCCASMSG.ENA_MODE = '1';
        AICRTC02.START_SOD_FLG = 'Y';
        TCCASMSG.LEN = 1;
        TCCASMSG.DATA = IBS.CLS2CPY(SCCGWA, AICRTC02);
    }
    public void S000_CALL_TCZASMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TC-ASYNC-MSG", TCCASMSG);
        if (TCCASMSG.RTNCODE != 0) {
            AICOTC02.TC_RTNCD = TCCASMSG.RTNCODE;
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_SEND_CRM_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, WS_ERR_MSG, AICOTC02.RC);
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICOTC02.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICOTC02 = ");
            CEP.TRC(SCCGWA, AICOTC02);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
