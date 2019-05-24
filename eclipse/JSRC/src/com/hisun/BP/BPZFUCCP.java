package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFUCCP {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String CPN_PARM_CHECK = "BP-PARM-READ        ";
    BPZFUCCP_WS_MSGID WS_MSGID = new BPZFUCCP_WS_MSGID();
    short WS_FLD_NO = 0;
    String WS_CPN_NAME = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPRCPN BPRCPN = new BPRCPN();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    BPCRCKPM BPCRCKPM;
    public void MP(SCCGWA SCCGWA, BPCRCKPM BPCRCKPM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRCKPM = BPCRCKPM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFUCCP return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_PROCESS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCRCKPM.VAL.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT, WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (BPCRCKPM.VAL == null) BPCRCKPM.VAL = "";
        JIBS_tmp_int = BPCRCKPM.VAL.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) BPCRCKPM.VAL += " ";
        WS_CPN_NAME = BPCRCKPM.VAL.substring(0, 20);
    }
    public void B020_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPRCPN);
        BPRPRMT.KEY.CD = WS_CPN_NAME;
        BPRPRMT.KEY.TYP = "CPN  ";
        BPCPRMR.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMR();
        CEP.TRC(SCCGWA, "1");
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CPN_NOTFND, WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRCPN);
        if (BPRCPN.DATA_TXT.STATUS != 'Y') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FEE_EFF_DATE_ERR, WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_CHECK, BPCPRMR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRCKPM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRCKPM = ");
            CEP.TRC(SCCGWA, BPCRCKPM);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
