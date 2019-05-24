package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;
import com.hisun.SM.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSDPRP {
    BPZSDPRP_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSDPRP_WS_TEMP_VARIABLE();
    char WS_PRP_FLG = ' ';
    BPCXP01 BPCXP01 = new BPCXP01();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    BPCRMPRP BPCRMPRP = new BPCRMPRP();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPRPARP BPRPARP = new BPRPARP();
    BPRPARM BPRPARM = new BPRPARM();
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
    BPCSDPRP BPCSDPRP;
    public void MP(SCCGWA SCCGWA, BPCSDPRP BPCSDPRP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSDPRP = BPCSDPRP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSDPRP return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMPRP);
        BPCRMPRP.RC.RC_MMO = "BP";
        BPCRMPRP.PTR = BPRPARP;
        IBS.init(SCCGWA, BPCRMBPM);
        BPCRMBPM.RC.RC_MMO = "BP";
        BPCRMBPM.PTR = BPRPARM;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B005_STARTBR_PARM();
        B010_READNEXT_PARM();
        B015_ENDBR_PARM();
        B020_READ_PARP_PROC();
        B025_DEL_PARP_PROC();
        B030_OUTPUT_FOR_PRINT();
    }
    public void B005_STARTBR_PARM() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'S';
        BPRPARM.KEY.TYPE = BPCSDPRP.TYPE;
        S000_CALL_BPZRMBPM();
    }
    public void B010_READNEXT_PARM() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'R';
        S000_CALL_BPZRMBPM();
        if (BPCRMBPM.RETURN_INFO != 'L') {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_TYPE_INUSE;
            CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        }
    }
    public void B015_ENDBR_PARM() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
    }
    public void B020_READ_PARP_PROC() throws IOException,SQLException,Exception {
        BPCRMPRP.FUNC = 'R';
        BPRPARP.KEY.TYPE = BPCSDPRP.TYPE;
        S000_CALL_BPZRMPRP();
        if (BPCRMPRP.RETURN_INFO == 'N') {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_TYPE_NOT_EXIT;
            CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        }
    }
    public void B025_DEL_PARP_PROC() throws IOException,SQLException,Exception {
        BPCRMPRP.FUNC = 'D';
        BPRPARP.KEY.TYPE = BPCSDPRP.TYPE;
        S000_CALL_BPZRMPRP();
    }
    public void B030_OUTPUT_FOR_PRINT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCXP01);
        IBS.init(SCCGWA, SCCFMT);
        BPCXP01.TYPE = BPCSDPRP.TYPE;
        BPCXP01.NAME = BPCSDPRP.NAME;
        BPCXP01.DSTORE = BPCSDPRP.DSTORE;
        BPCXP01.NSTORE = BPCSDPRP.NSTORE;
        BPCXP01.SDESC = BPCSDPRP.SDESC;
        BPCXP01.CHK = BPCSDPRP.CHK;
        BPCXP01.ENA = BPCSDPRP.ENA;
        BPCXP01.ATTR = BPCSDPRP.ATTR;
        BPCXP01.CLASS = BPCSDPRP.CLASS;
        BPCXP01.SUB = BPCSDPRP.SUB;
        BPCXP01.DM_TYPE = BPCSDPRP.DM_TYPE;
        BPCXP01.LVL = BPCSDPRP.LVL;
        BPCXP01.DOWN_FLAG = BPCSDPRP.DOWN_FLAG;
        BPCXP01.TXN_ID = BPCSDPRP.TXN_ID;
        BPCXP01.CHK_CPNT = BPCSDPRP.CHK_CPNT;
        BPCXP01.LEN = BPCSDPRP.LEN;
        BPCXP01.FILLER = 0X01;
        BPCXP01.HISATR = BPCSDPRP.HISATR;
        BPCXP01.HISYEAR = BPCSDPRP.HISYEAR;
        BPCXP01.STSW = BPCSDPRP.STSW;
        BPCXP01.CPY_NAME = BPCSDPRP.CPY_NAME;
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
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        if (BPCRMBPM.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
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
