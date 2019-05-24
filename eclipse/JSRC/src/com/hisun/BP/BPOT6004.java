package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6004 {
    char K_ERROR = 'E';
    BPOT6004_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT6004_WS_TEMP_VARIABLE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCSMPDM BPCSMPDM = new BPCSMPDM();
    BPCPQPDM BPCPQPDM = new BPCPQPDM();
    BPCPQTRT BPCPQTRT = new BPCPQTRT();
    SCCGWA SCCGWA;
    BPB6000_AWA_6000 BPB6000_AWA_6000;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "BPOT6004 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6000_AWA_6000>");
        BPB6000_AWA_6000 = (BPB6000_AWA_6000) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_DELETE_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB6000_AWA_6000.PRDT_MOD.trim().length() == 0) {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_MUST_INPUT_PRD_MODEL;
            S000_ERR_MSG_PROC();
        }
        BPCPQPDM.PRDT_MODEL = BPB6000_AWA_6000.PRDT_MOD;
        BPCPQPDM.EFF_DT = BPB6000_AWA_6000.EFF_DT;
        S000_CALL_BPZPQPDM();
        if (BPCPQPDM.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_PRD_MODEL_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMPDM);
        BPCSMPDM.I_FUNC = 'D';
        BPCSMPDM.PRDT_MODEL = BPB6000_AWA_6000.PRDT_MOD;
        BPCSMPDM.EFF_DT = BPB6000_AWA_6000.EFF_DT;
        S000_CALL_BPZSMPDM();
    }
    public void S000_CALL_BPZSMPDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-PRD-MODEL", BPCSMPDM);
    }
    public void S000_CALL_BPZPQPDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PRD-MODEL", BPCPQPDM);
    }
    public void S000_CALL_BPZPQTRT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-TRT", BPCPQTRT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID, WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID, WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
