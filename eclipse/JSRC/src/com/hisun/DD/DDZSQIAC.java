package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSQIAC {
    String K_OUTPUT_FMT = "DD102";
    String WS_ERR_MSG = " ";
    String WS_SQIAC_CD = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DDCOQIAC DDCOQIAC = new DDCOQIAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DDCSQIAC DDCSQIAC;
    public void MP(SCCGWA SCCGWA, DDCSQIAC DDCSQIAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSQIAC = DDCSQIAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSQIAC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_GET_INPUT_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSQIAC.BR);
        CEP.TRC(SCCGWA, DDCSQIAC.CCY);
        CEP.TRC(SCCGWA, DDCSQIAC.BUSI_KND);
    }
    public void B020_GET_INPUT_PROC() throws IOException,SQLException,Exception {
        WS_SQIAC_CD = DDCSQIAC.BR;
        CEP.TRC(SCCGWA, WS_SQIAC_CD);
        R000_GET_PSBK_LOST_PARM();
    }
    public void R000_GET_PSBK_LOST_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, DDCOQIAC);
        DDCOQIAC.KEY.TYP = "PDD18";
        DDCOQIAC.KEY.CD = WS_SQIAC_CD;
        BPCPRMR.DAT_PTR = DDCOQIAC;
        S000_CALL_BPZPRMR();
        CEP.TRC(SCCGWA, DDCOQIAC.DATA_TXT.DD_AC);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
