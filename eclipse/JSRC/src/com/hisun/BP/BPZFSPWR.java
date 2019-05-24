package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFSPWR {
    DBParm BPTTLT_RD;
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTLT BPRTLT = new BPRTLT();
    SCCGWA SCCGWA;
    BPCFSPWR BPCFSPWR;
    public void MP(SCCGWA SCCGWA, BPCFSPWR BPCFSPWR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFSPWR = BPCFSPWR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFSPWR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFSPWR.FUNC);
        CEP.TRC(SCCGWA, BPCFSPWR.TLR);
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCFSPWR.FUNC == 'A') {
            B030_ADD_TLR_PSW_RE_TIMES();
            if (pgmRtn) return;
        } else if (BPCFSPWR.FUNC == 'R') {
            B040_RELEASE_TLR_PSW();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCFSPWR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_ADD_TLR_PSW_RE_TIMES() throws IOException,SQLException,Exception {
        BPRTLT.KEY.TLR = BPCFSPWR.TLR;
        T000_READ_BPTTLT_UPD();
        if (pgmRtn) return;
        BPRTLT.PSW_RETRY += 1;
        CEP.TRC(SCCGWA, BPRTLT.PSW_RETRY);
        T000_REWRITE_BPTTLT();
        if (pgmRtn) return;
    }
    public void B040_RELEASE_TLR_PSW() throws IOException,SQLException,Exception {
        BPRTLT.KEY.TLR = BPCFSPWR.TLR;
        T000_READ_BPTTLT_UPD();
        if (pgmRtn) return;
        BPRTLT.PSW_RETRY = 0;
        CEP.TRC(SCCGWA, BPRTLT.PSW_RETRY);
        T000_REWRITE_BPTTLT();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTTLT_UPD() throws IOException,SQLException,Exception {
        BPTTLT_RD = new DBParm();
        BPTTLT_RD.TableName = "BPTTLT";
        BPTTLT_RD.upd = true;
        IBS.READ(SCCGWA, BPRTLT, BPTTLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "NORMAL ");
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOT FND");
        } else {
            CEP.TRC(SCCGWA, "ERROR  ");
        }
    }
    public void T000_REWRITE_BPTTLT() throws IOException,SQLException,Exception {
        BPTTLT_RD = new DBParm();
        BPTTLT_RD.TableName = "BPTTLT";
        IBS.REWRITE(SCCGWA, BPRTLT, BPTTLT_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFSPWR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFSPWR = ");
            CEP.TRC(SCCGWA, BPCFSPWR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
