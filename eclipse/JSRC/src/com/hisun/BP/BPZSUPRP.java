package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;
import com.hisun.SM.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSUPRP {
    int JIBS_tmp_int;
    BPZSUPRP_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSUPRP_WS_TEMP_VARIABLE();
    char WS_PRP_FLG = ' ';
    BPCXP01 BPCXP01 = new BPCXP01();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    BPCRMPRP BPCRMPRP = new BPCRMPRP();
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
    BPCSUPRP BPCSUPRP;
    public void MP(SCCGWA SCCGWA, BPCSUPRP BPCSUPRP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSUPRP = BPCSUPRP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSUPRP return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMPRP);
        BPCRMPRP.RC.RC_MMO = "BP";
        BPCRMPRP.PTR = BPRPARP;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B005_READ_PROC();
        BPRPARP.KEY.TYPE = BPCSUPRP.TYPE;
        BPRPARP.NAME = BPCSUPRP.NAME;
        BPRPARP.DSTORE = BPCSUPRP.DSTORE;
        BPRPARP.NSTORE = BPCSUPRP.NSTORE;
        BPRPARP.SDESC = BPCSUPRP.SDESC;
        BPRPARP.CHK = BPCSUPRP.CHK;
        BPRPARP.ENA = BPCSUPRP.ENA;
        BPRPARP.ATTR = BPCSUPRP.ATTR;
        BPRPARP.CLASS = "" + BPCSUPRP.CLASS;
        JIBS_tmp_int = BPRPARP.CLASS.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) BPRPARP.CLASS = "0" + BPRPARP.CLASS;
        BPRPARP.DM_TYPE = BPCSUPRP.DM_TYPE;
        BPRPARP.DOWN_FLAG = BPCSUPRP.DOWN_FLAG;
        BPRPARP.TXN_ID = BPCSUPRP.TXN_ID;
        BPRPARP.CHK_CPNT = BPCSUPRP.CHK_CPNT;
        BPRPARP.LEN = BPCSUPRP.LEN;
        BPRPARP.HISYEAR = "" + BPCSUPRP.HISYEAR;
        JIBS_tmp_int = BPRPARP.HISYEAR.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPRPARP.HISYEAR = "0" + BPRPARP.HISYEAR;
        BPRPARP.STSW = BPCSUPRP.STSW;
        BPRPARP.COPYBOOK = BPCSUPRP.CPY_NAME;
        CEP.TRC(SCCGWA, BPRPARP.COPYBOOK);
        if (WS_PRP_FLG == 'N') {
            B0010_WRITE_PROC();
        } else {
            B0015_UPDATE_PROC();
        }
        B020_OUTPUT_FOR_PRINT();
    }
    public void B005_READ_PROC() throws IOException,SQLException,Exception {
        BPCRMPRP.FUNC = 'R';
        BPRPARP.KEY.TYPE = BPCSUPRP.TYPE;
        S000_CALL_BPZRMPRP();
        if (BPCRMPRP.RETURN_INFO == 'N') {
            WS_PRP_FLG = 'N';
        }
    }
    public void B0010_WRITE_PROC() throws IOException,SQLException,Exception {
        BPCRMPRP.FUNC = 'C';
        S000_CALL_BPZRMPRP();
    }
    public void B0015_UPDATE_PROC() throws IOException,SQLException,Exception {
        BPCRMPRP.FUNC = 'U';
        S000_CALL_BPZRMPRP();
    }
    public void B020_OUTPUT_FOR_PRINT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCXP01);
        IBS.init(SCCGWA, SCCFMT);
        BPCXP01.TYPE = BPCSUPRP.TYPE;
        BPCXP01.NAME = BPCSUPRP.NAME;
        BPCXP01.DSTORE = BPCSUPRP.DSTORE;
        BPCXP01.NSTORE = BPCSUPRP.NSTORE;
        BPCXP01.SDESC = BPCSUPRP.SDESC;
        BPCXP01.CHK = BPCSUPRP.CHK;
        BPCXP01.ENA = BPCSUPRP.ENA;
        BPCXP01.ATTR = BPCSUPRP.ATTR;
        BPCXP01.CLASS = BPCSUPRP.CLASS;
        BPCXP01.SUB = BPCSUPRP.SUB;
        BPCXP01.DM_TYPE = BPCSUPRP.DM_TYPE;
        BPCXP01.LVL = BPCSUPRP.LVL;
        BPCXP01.DOWN_FLAG = BPCSUPRP.DOWN_FLAG;
        BPCXP01.TXN_ID = BPCSUPRP.TXN_ID;
        BPCXP01.CHK_CPNT = BPCSUPRP.CHK_CPNT;
        BPCXP01.LEN = BPCSUPRP.LEN;
        BPCXP01.FILLER = 0X01;
        BPCXP01.HISATR = BPCSUPRP.HISATR;
        BPCXP01.HISYEAR = BPCSUPRP.HISYEAR;
        BPCXP01.STSW = BPCSUPRP.STSW;
        BPCXP01.CPY_NAME = BPCSUPRP.CPY_NAME;
        CEP.TRC(SCCGWA, BPCSUPRP.CPY_NAME);
        SCCFMT.FMTID = "BPP01";
        SCCFMT.DATA_PTR = BPCXP01;
        SCCFMT.DATA_LEN = 140;
        IBS.FMT(SCCGWA, SCCFMT);
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
