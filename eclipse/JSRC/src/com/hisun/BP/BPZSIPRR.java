package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;
import com.hisun.SM.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSIPRR {
    BPZSIPRR_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSIPRR_WS_TEMP_VARIABLE();
    char WS_SEQ_FLG = ' ';
    BPCXP05 BPCXP05 = new BPCXP05();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    BPCRMPRR BPCRMPRR = new BPCRMPRR();
    BPRPARR BPRPARR = new BPRPARR();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGCPT SCCGCPT;
    BPRTRT SMRTRTT;
    BPCSIPRR BPCSIPRR;
    public void MP(SCCGWA SCCGWA, BPCSIPRR BPCSIPRR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSIPRR = BPCSIPRR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSIPRR return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMPRR);
        BPCRMPRR.RC.RC_MMO = "BP";
        BPCRMPRR.PTR = BPRPARR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B005_INQ_PROC();
        B014_OUTPUT_FOR_PRINT();
    }
    public void B005_INQ_PROC() throws IOException,SQLException,Exception {
        BPCRMPRR.FUNC = 'Q';
        BPRPARR.KEY.TYPE = BPCSIPRR.TYPEA;
        BPRPARR.KEY.TYPE_R = BPCSIPRR.TYPEB;
        BPRPARR.KEY.RELA = BPCSIPRR.RELA;
        S000_CALL_BPZRMPRR();
        if (BPCRMPRR.RETURN_INFO == 'N') {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_TYPEREL_NOTFND;
            CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        }
    }
    public void B014_OUTPUT_FOR_PRINT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCXP05);
        IBS.init(SCCGWA, SCCFMT);
        BPCXP05.TYPEA = BPRPARR.KEY.TYPE;
        BPCXP05.TYPEB = BPRPARR.KEY.TYPE_R;
        BPCXP05.RELA = BPRPARR.KEY.RELA;
        BPCXP05.CTRL = BPRPARR.CTRL;
        SCCFMT.FMTID = "BPP05";
        SCCFMT.DATA_PTR = BPCXP05;
        SCCFMT.DATA_LEN = 36;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZRMPRR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MAINT-PRR", BPCRMPRR);
        if (BPCRMPRR.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCRMPRR.RC);
            CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
