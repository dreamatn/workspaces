package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPTLR {
    DBParm BPTTLT_RD;
    boolean pgmRtn = false;
    short WK_REC_LEN = 0;
    int WK_RESP = 0;
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRTLT BPRTLT = new BPRTLT();
    SCCGWA SCCGWA;
    BPCPTLR BPCPTLR;
    BPRTLT BPRTLTC;
    public void MP(SCCGWA SCCGWA, BPCPTLR BPCPTLR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPTLR = BPCPTLR;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_CHK_PROC();
        if (pgmRtn) return;
        C00_MAIN_PROC();
        if (pgmRtn) return;
        D00_OUTP_PROC();
        if (pgmRtn) return;
        E00_GO_BACK();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTLTC = (BPRTLT) BPCPTLR.DATA_PTR;
    }
    public void B00_CHK_PROC() throws IOException,SQLException,Exception {
    }
    public void C00_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCPTLR.FUNC == '1') {
            C01_READ_UPD_TLR_REC();
            if (pgmRtn) return;
        } else if (BPCPTLR.FUNC == '2') {
            C05_REWRITE_TLR_REC();
            if (pgmRtn) return;
        } else if (BPCPTLR.FUNC == '3') {
            C10_READ_ONLY_TLR_REC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "CALL FUNCTION ERROR : " + BPCPTLR.FUNC;
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void C01_READ_UPD_TLR_REC() throws IOException,SQLException,Exception {
        BPRTLT.KEY.TLR = BPCPTLR.TLR_ID;
        CEP.TRC(SCCGWA, BPRTLT.KEY.TLR);
        BPTTLT_RD = new DBParm();
        BPTTLT_RD.TableName = "BPTTLT";
        BPTTLT_RD.upd = true;
        IBS.READ(SCCGWA, BPRTLT, BPTTLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CLONE(SCCGWA, BPRTLT, BPRTLTC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOTFND, BPCPTLR.TLR_ID);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTLR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void C05_REWRITE_TLR_REC() throws IOException,SQLException,Exception {
        IBS.CLONE(SCCGWA, BPRTLTC, BPRTLT);
        BPTTLT_RD = new DBParm();
        BPTTLT_RD.TableName = "BPTTLT";
        IBS.REWRITE(SCCGWA, BPRTLT, BPTTLT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CLONE(SCCGWA, BPRTLT, BPRTLTC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOTFND, BPCPTLR.TLR_ID);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTLR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void C10_READ_ONLY_TLR_REC() throws IOException,SQLException,Exception {
        BPRTLT.KEY.TLR = BPCPTLR.TLR_ID;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
        CEP.TRC(SCCGWA, BPRTLT.KEY.TLR);
        BPTTLT_RD = new DBParm();
        BPTTLT_RD.TableName = "BPTTLT";
        IBS.READ(SCCGWA, BPRTLT, BPTTLT_RD);
        CEP.TRC(SCCGWA, BPRTLT.SIGN_STS);
        CEP.TRC(SCCGWA, BPRTLT.TLR_STS);
        CEP.TRC(SCCGWA, "1111111111111");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CLONE(SCCGWA, BPRTLT, BPRTLTC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOTFND, BPCPTLR.TLR_ID);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTLR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTLT, BPRTLTC);
        CEP.TRC(SCCGWA, "222222222222");
    }
    public void D00_OUTP_PROC() throws IOException,SQLException,Exception {
    }
    public void E00_GO_BACK() throws IOException,SQLException,Exception {
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.EXCP_FLG);
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
