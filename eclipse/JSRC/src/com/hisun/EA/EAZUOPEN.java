package com.hisun.EA;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class EAZUOPEN {
    String K_AP_MMO = "EA";
    String K_SYS_ERR = "SC6001";
    int K_OWNER_BR = "706660209";
    String WS_ERR_MSG = " ";
    short WS_RC = 0;
    short WS_RC_DISP = 0;
    EACMSG_ERROR_MSG EACMSG_ERROR_MSG = new EACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    EACCHNL EACCHNL = new EACCHNL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    EACUOPEN EACUOPEN;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, EACUOPEN EACUOPEN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.EACUOPEN = EACUOPEN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "OUTCDINT");
        CEP.TRC(SCCGWA, "EAZUOPEN return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        R_GET_EA_OPEN_PARM();
        if (EACUOPEN.FUNC == '2') {
            if ((EACUOPEN.AC_FLG == '2' 
                && EACCHNL.DATA_TXT.SEC_FLG != 'Y') 
                || (EACUOPEN.AC_FLG == '3' 
                && EACCHNL.DATA_TXT.THR_FLG != 'Y')) {
                WS_ERR_MSG = EACMSG_ERROR_MSG.EA_AC_CANNOT_OPEN;
                S000_ERR_MSG_PROC();
            }
            if ((EACUOPEN.AC_FLG == '2' 
                && !EACUOPEN.PROD_CODE.equalsIgnoreCase(EACCHNL.DATA_TXT.SEC_PRD)) 
                || (EACUOPEN.AC_FLG == '3' 
                && !EACUOPEN.PROD_CODE.equalsIgnoreCase(EACCHNL.DATA_TXT.THR_PRD))) {
                WS_ERR_MSG = EACMSG_ERROR_MSG.EA_AC_PROD_NOT_MATCH;
                S000_ERR_MSG_PROC();
            }
        }
        EACUOPEN.OWNER_BR = K_OWNER_BR;
        EACUOPEN.OPEN_BR = EACCHNL.DATA_TXT.OWNER_BR;
    }
    public void R_GET_EA_OPEN_PARM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EACUOPEN.REQ_SYS);
        IBS.init(SCCGWA, EACCHNL);
        EACCHNL.PARM_TYPE = "PEA01";
        EACCHNL.PARM_CODE = EACUOPEN.REQ_SYS;
        IBS.init(SCCGWA, BPCPRMR);
        BPCPRMR.DAT_PTR = EACCHNL;
        S000_CALL_BPZPRMR();
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        CEP.TRC(SCCGWA, BPCPRMR.RC.RC_RTNCODE);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
