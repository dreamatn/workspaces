package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;
import com.hisun.SM.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSUPRR {
    BPZSUPRR_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSUPRR_WS_TEMP_VARIABLE();
    char WS_PRR_FLG = ' ';
    BPCXP05 BPCXP05 = new BPCXP05();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    BPCRMPRR BPCRMPRR = new BPCRMPRR();
    BPCRMPRP BPCRMPRP = new BPCRMPRP();
    BPRPARR BPRPARR = new BPRPARR();
    BPRPARP BPRPARP = new BPRPARP();
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
    BPCSUPRR BPCSUPRR;
    public void MP(SCCGWA SCCGWA, BPCSUPRR BPCSUPRR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSUPRR = BPCSUPRR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSUPRR return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMPRR);
        BPCRMPRR.RC.RC_MMO = "BP";
        BPCRMPRR.PTR = BPRPARR;
        IBS.init(SCCGWA, BPCRMPRP);
        BPCRMPRP.RC.RC_MMO = "BP";
        BPCRMPRP.PTR = BPRPARP;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B005_CHK_PARP_PROC();
        B010_READ_PARR_PROC();
        BPRPARR.KEY.TYPE = BPCSUPRR.TYPEA;
        BPRPARR.KEY.TYPE_R = BPCSUPRR.TYPEB;
        BPRPARR.KEY.RELA = BPCSUPRR.RELA;
        BPRPARR.CTRL = BPCSUPRR.CTRL;
        if (WS_PRR_FLG == 'N') {
            B015_WRITE_PROC();
        } else {
            B020_UPDATE_PROC();
        }
        B025_OUTPUT_FOR_PRINT();
    }
    public void B005_CHK_PARP_PROC() throws IOException,SQLException,Exception {
        BPCRMPRP.FUNC = 'Q';
        BPRPARP.KEY.TYPE = BPCSUPRR.TYPEA;
        S000_CALL_BPZRMPRP();
        if (BPCRMPRP.RETURN_INFO == 'N') {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_TYPEPRO_NOTFND;
            CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        }
        BPCRMPRP.FUNC = 'Q';
        BPRPARP.KEY.TYPE = BPCSUPRR.TYPEB;
        S000_CALL_BPZRMPRP();
        if (BPCRMPRP.RETURN_INFO == 'N') {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_TYPEPRO_NOTFND;
            CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        }
    }
    public void B010_READ_PARR_PROC() throws IOException,SQLException,Exception {
        BPCRMPRR.FUNC = 'R';
        BPRPARR.KEY.TYPE = BPCSUPRR.TYPEA;
        BPRPARR.KEY.TYPE_R = BPCSUPRR.TYPEB;
        BPRPARR.KEY.RELA = BPCSUPRR.RELA;
        S000_CALL_BPZRMPRR();
        if (BPCRMPRR.RETURN_INFO == 'N') {
            WS_PRR_FLG = 'N';
        }
    }
    public void B015_WRITE_PROC() throws IOException,SQLException,Exception {
        BPCRMPRR.FUNC = 'C';
        S000_CALL_BPZRMPRR();
    }
    public void B020_UPDATE_PROC() throws IOException,SQLException,Exception {
        BPCRMPRR.FUNC = 'U';
        S000_CALL_BPZRMPRR();
    }
    public void B025_OUTPUT_FOR_PRINT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCXP05);
        IBS.init(SCCGWA, SCCFMT);
        BPCXP05.TYPEA = BPCSUPRR.TYPEA;
        BPCXP05.TYPEB = BPCSUPRR.TYPEB;
        BPCXP05.RELA = BPCSUPRR.RELA;
        BPCXP05.CTRL = BPCSUPRR.CTRL;
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
    public void S000_CALL_BPZRMPRP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MAINT-PRP", BPCRMPRP);
        if (BPCRMPRP.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCRMPRP.RC);
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
