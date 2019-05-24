package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZSIPRP {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BPX01";
    BPZSIPRP_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSIPRP_WS_TEMP_VARIABLE();
    char WS_SEQ_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCRMPRP BPCRMPRP = new BPCRMPRP();
    BPRPARP BPRPARP = new BPRPARP();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    BPCSIPRP BPCSIPRP;
    public void MP(SCCGWA SCCGWA, BPCSIPRP BPCSIPRP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSIPRP = BPCSIPRP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSIPRP return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMPRP);
        IBS.init(SCCGWA, BPRPARP);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCRMPRP.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCSIPRP.FUNC == 'I'
            || BPCSIPRP.FUNC == 'Q'
            || BPCSIPRP.FUNC == 'R'
            || BPCSIPRP.FUNC == 'U') {
            B020_INQ_PROC();
            if (pgmRtn) return;
        } else if (BPCSIPRP.FUNC == 'A') {
            B030_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCSIPRP.FUNC == 'M') {
            B040_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCSIPRP.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCSIPRP.FUNC == 'B') {
            B060_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSIPRP.FUNC != 'B') {
            B070_OUTPUT_FOR_PRINT();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_INQ_PROC() throws IOException,SQLException,Exception {
        if (BPCSIPRP.FUNC == 'R') {
            BPCSIPRP.FUNC = 'D';
        }
        if (BPCSIPRP.FUNC == 'Q') {
            BPCSIPRP.FUNC = 'A';
        }
        if (BPCSIPRP.FUNC == 'I') {
            BPCSIPRP.FUNC = 'I';
        }
        if (BPCSIPRP.FUNC == 'U') {
            BPCSIPRP.FUNC = 'M';
        }
        BPCRMPRP.FUNC = 'Q';
        BPRPARP.KEY.TYPE = BPCSIPRP.DATA.KEY.TYPE;
        S000_CALL_BPZRMPRP();
        if (pgmRtn) return;
    }
    public void B030_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        R000_TRANS_BPRPARP();
        if (pgmRtn) return;
        BPCRMPRP.FUNC = 'C';
        S000_CALL_BPZRMPRP();
        if (pgmRtn) return;
    }
    public void B040_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        BPCRMPRP.FUNC = 'R';
        BPRPARP.KEY.TYPE = BPCSIPRP.DATA.KEY.TYPE;
        S000_CALL_BPZRMPRP();
        if (pgmRtn) return;
        BPCRMPRP.FUNC = 'U';
        R000_TRANS_BPRPARP();
        if (pgmRtn) return;
        S000_CALL_BPZRMPRP();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPCRMPRP.FUNC = 'R';
        BPRPARP.KEY.TYPE = BPCSIPRP.DATA.KEY.TYPE;
        S000_CALL_BPZRMPRP();
        if (pgmRtn) return;
        BPCRMPRP.FUNC = 'D';
        S000_CALL_BPZRMPRP();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_PROC() throws IOException,SQLException,Exception {
    }
    public void B070_OUTPUT_FOR_PRINT() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCSIPRP;
        SCCFMT.DATA_LEN = 1433;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_BPRPARP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPARP);
        BPRPARP.KEY.TYPE = BPCSIPRP.DATA.KEY.TYPE;
        CEP.TRC(SCCGWA, BPCSIPRP.DATA.KEY.TYPE);
        BPRPARP.NAME = BPCSIPRP.DATA.NAME;
        CEP.TRC(SCCGWA, BPCSIPRP.DATA.NAME);
        BPRPARP.CNAME = BPCSIPRP.DATA.CNAME;
        CEP.TRC(SCCGWA, BPCSIPRP.DATA.CNAME);
        BPRPARP.DSTORE = BPCSIPRP.DATA.DSTORE;
        BPRPARP.NSTORE = BPCSIPRP.DATA.NSTORE;
        BPRPARP.SDESC = BPCSIPRP.DATA.SDESC;
        BPRPARP.CHK = BPCSIPRP.DATA.CHK;
        BPRPARP.ENA = BPCSIPRP.DATA.ENA;
        BPRPARP.STS = BPCSIPRP.DATA.STS;
        BPRPARP.ATTR = BPCSIPRP.DATA.ATTR;
        BPRPARP.CLASS = BPCSIPRP.DATA.CLASS;
        BPRPARP.DM_TYPE = BPCSIPRP.DATA.DM_TYPE;
        BPRPARP.MANT = BPCSIPRP.DATA.MANT;
        BPRPARP.USE_LVL = BPCSIPRP.DATA.USE_LVL;
        BPRPARP.STORE = BPCSIPRP.DATA.STORE;
        BPRPARP.DOWN_FLAG = BPCSIPRP.DATA.DOWN_FLAG;
        BPRPARP.HISYEAR = BPCSIPRP.DATA.HISYEAR;
        BPRPARP.DEL_FLG = BPCSIPRP.DATA.DEL_FLG;
        BPRPARP.DUP_DATE_FLG = BPCSIPRP.DATA.DUP_DATE_FLG;
        BPRPARP.PARM_VIEW1 = BPCSIPRP.DATA.PARM_VIEW1;
        BPRPARP.PARM_VIEW2 = BPCSIPRP.DATA.PARM_VIEW2;
        BPRPARP.PARM_VIEW3 = BPCSIPRP.DATA.PARM_VIEW3;
        BPRPARP.PARM_VIEW4 = BPCSIPRP.DATA.PARM_VIEW4;
        BPRPARP.PARM_VIEW5 = BPCSIPRP.DATA.PARM_VIEW5;
        BPRPARP.PARM_VIEW6 = BPCSIPRP.DATA.PARM_VIEW6;
        BPRPARP.PARM_VIEW7 = BPCSIPRP.DATA.PARM_VIEW7;
        BPRPARP.PARM_VIEW8 = BPCSIPRP.DATA.PARM_VIEW8;
        BPRPARP.PARM_VIEW9 = BPCSIPRP.DATA.PARM_VIEW9;
        BPRPARP.TXN_ID = BPCSIPRP.DATA.TXN_ID;
        BPRPARP.INQ_TXN = BPCSIPRP.DATA.INQ_TXN;
        BPRPARP.NXT_TXN = BPCSIPRP.DATA.NXT_TXN;
        BPRPARP.COPYBOOK = BPCSIPRP.DATA.COPYBOOK;
        BPRPARP.CHK_CPNT = BPCSIPRP.DATA.CHK_CPNT;
        CEP.TRC(SCCGWA, BPCSIPRP.DATA.CHK_CPNT);
        BPRPARP.LEN = BPCSIPRP.DATA.LEN;
        BPRPARP.STSW = BPCSIPRP.DATA.STSW;
        CEP.TRC(SCCGWA, BPCSIPRP.DATA.RMK);
    }
    public void R000_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSIPRP.DATA);
        BPCSIPRP.DATA.KEY.TYPE = BPRPARP.KEY.TYPE;
        BPCSIPRP.DATA.NAME = BPRPARP.NAME;
        BPCSIPRP.DATA.CNAME = BPRPARP.CNAME;
        BPCSIPRP.DATA.DSTORE = BPRPARP.DSTORE;
        BPCSIPRP.DATA.NSTORE = BPRPARP.NSTORE;
        BPCSIPRP.DATA.SDESC = BPRPARP.SDESC;
        BPCSIPRP.DATA.CHK = BPRPARP.CHK;
        BPCSIPRP.DATA.ENA = BPRPARP.ENA;
        BPCSIPRP.DATA.STS = BPRPARP.STS;
        BPCSIPRP.DATA.ATTR = BPRPARP.ATTR;
        BPCSIPRP.DATA.CLASS = BPRPARP.CLASS;
        BPCSIPRP.DATA.DM_TYPE = BPRPARP.DM_TYPE;
        BPCSIPRP.DATA.MANT = BPRPARP.MANT;
        BPCSIPRP.DATA.USE_LVL = BPRPARP.USE_LVL;
        BPCSIPRP.DATA.STORE = BPRPARP.STORE;
        BPCSIPRP.DATA.DOWN_FLAG = BPRPARP.DOWN_FLAG;
        BPCSIPRP.DATA.HISYEAR = BPRPARP.HISYEAR;
        BPCSIPRP.DATA.DEL_FLG = BPRPARP.DEL_FLG;
        BPCSIPRP.DATA.DUP_DATE_FLG = BPRPARP.DUP_DATE_FLG;
        BPCSIPRP.DATA.PARM_VIEW1 = BPRPARP.PARM_VIEW1;
        BPCSIPRP.DATA.PARM_VIEW2 = BPRPARP.PARM_VIEW2;
        BPCSIPRP.DATA.PARM_VIEW3 = BPRPARP.PARM_VIEW3;
        BPCSIPRP.DATA.PARM_VIEW4 = BPRPARP.PARM_VIEW4;
        BPCSIPRP.DATA.PARM_VIEW5 = BPRPARP.PARM_VIEW5;
        BPCSIPRP.DATA.PARM_VIEW6 = BPRPARP.PARM_VIEW6;
        BPCSIPRP.DATA.PARM_VIEW7 = BPRPARP.PARM_VIEW7;
        BPCSIPRP.DATA.PARM_VIEW8 = BPRPARP.PARM_VIEW8;
        BPCSIPRP.DATA.PARM_VIEW9 = BPRPARP.PARM_VIEW9;
        BPCSIPRP.DATA.TXN_ID = BPRPARP.TXN_ID;
        BPCSIPRP.DATA.INQ_TXN = BPRPARP.INQ_TXN;
        BPCSIPRP.DATA.NXT_TXN = BPRPARP.NXT_TXN;
        BPCSIPRP.DATA.COPYBOOK = BPRPARP.COPYBOOK;
        BPCSIPRP.DATA.CHK_CPNT = BPRPARP.CHK_CPNT;
        BPCSIPRP.DATA.LEN = BPRPARP.LEN;
        BPCSIPRP.DATA.STSW = BPRPARP.STSW;
