package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQCPN {
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRCPN BPRCPN = new BPRCPN();
    BPCPRMM BPCPRMM = new BPCPRMM();
    SCCGWA SCCGWA;
    BPCPQCPN BPCPQCPN;
    public void MP(SCCGWA SCCGWA, BPCPQCPN BPCPQCPN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQCPN = BPCPQCPN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZPQCPN return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_PROCESS();
        B030_TRANS_DATA_OUTPUT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCPQCPN.CD.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT, BPCPQCPN.RC);
            return;
        }
    }
    public void B020_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCPN);
        BPRCPN.KEY.TYP = "CPN";
        BPRCPN.KEY.CD = BPCPQCPN.CD;
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '3';
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPRMM.DAT_PTR = BPRCPN;
        S000_CALL_BPZPRMM();
    }
    public void B030_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        BPCPQCPN.DESC = BPRCPN.DESC;
        BPCPQCPN.CDESC = BPRCPN.CDESC;
        BPCPQCPN.DATA_TXT.PGM_NAME = BPRCPN.DATA_TXT.PGM_NAME;
        BPCPQCPN.DATA_TXT.CLASS = BPRCPN.DATA_TXT.CLASS;
        BPCPQCPN.DATA_TXT.FUN_TYPE = BPRCPN.DATA_TXT.FUN_TYPE;
        BPCPQCPN.DATA_TXT.CNTRL_WORD = BPRCPN.DATA_TXT.CNTRL_WORD;
        BPCPQCPN.DATA_TXT.STATUS = BPRCPN.DATA_TXT.STATUS;
        BPCPQCPN.DATA_TXT.CREUSR = BPRCPN.DATA_TXT.CREUSR;
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_MT, BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CPNT_ID_NOTFND, BPCPQCPN.RC);
            return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQCPN.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQCPN = ");
            CEP.TRC(SCCGWA, BPCPQCPN);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
