package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFUPEN {
    String CPN_R_FEE_PARM = "BP-F-Z-Q-B-FPEN     ";
    String K_PGM_NAME = "BPZFUPEN";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_TBL_FPEN_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCTFPEN BPCTFPEN = new BPCTFPEN();
    BPRFPEN BPRFPEN = new BPRFPEN();
    SCCGWA SCCGWA;
    BPCFUPEN BPCFUPEN;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFUPEN BPCFUPEN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFUPEN = BPCFUPEN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFUPEN return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCFUPEN.INFO.FUNC != '0' 
            && BPCFUPEN.INFO.FUNC != '1' 
            && BPCFUPEN.INFO.FUNC != '2' 
            && BPCFUPEN.INFO.FUNC != '3' 
            && BPCFUPEN.INFO.FUNC != '4' 
            && BPCFUPEN.INFO.FUNC != '5') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (BPCFUPEN.INFO.FUNC == '5' 
            && (BPCFUPEN.INFO.OPT != '0' 
            && BPCFUPEN.INFO.OPT != '1' 
            && BPCFUPEN.INFO.OPT != '2')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        B010_PRE_PARM_DATA();
        BPCTFPEN.INFO.FUNC = BPCFUPEN.INFO.FUNC;
        BPCTFPEN.INFO.OPT = BPCFUPEN.INFO.OPT;
        S000_CALL_BPZTFPEN();
        BPCFUPEN.RETURN_INFO = BPCTFPEN.RETURN_INFO;
    }
    public void B010_PRE_PARM_DATA() throws IOException,SQLException,Exception {
        BPCTFPEN.INFO.POINTER = BPCFUPEN.INFO.POINTER;
        BPCTFPEN.INFO.REC_LEN = 291;
    }
    public void S000_CALL_BPZTFPEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_FEE_PARM, BPCTFPEN);
        if (BPCTFPEN.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTFPEN.RC);
            S000_ERR_MSG_PROC();
        }
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
