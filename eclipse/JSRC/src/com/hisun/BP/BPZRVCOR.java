package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRVCOR {
    DBParm BPTVCOR_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRVCOR";
    String K_TBL_VCOR = "BPTVCOR ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRVCOR BPRVCOR = new BPRVCOR();
    SCCGWA SCCGWA;
    BPCRVCOR BPCRVCOR;
    BPRVCOR BPRVCOR1;
    public void MP(SCCGWA SCCGWA, BPCRVCOR BPCRVCOR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRVCOR = BPCRVCOR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRVCOR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRVCOR1 = (BPRVCOR) BPCRVCOR.INFO.POINTER;
        IBS.init(SCCGWA, BPRVCOR);
        IBS.CLONE(SCCGWA, BPRVCOR1, BPRVCOR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRVCOR.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRVCOR.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRVCOR.INFO.FUNC == 'U') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRVCOR.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRVCOR.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRVCOR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRVCOR, BPRVCOR);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRVCOR.BV_CODE);
        CEP.TRC(SCCGWA, BPRVCOR.BEG_NO);
        CEP.TRC(SCCGWA, BPRVCOR.NUM);
        CEP.TRC(SCCGWA, BPRVCOR.KEY.DT);
        CEP.TRC(SCCGWA, BPRVCOR.KEY.JRN_NO);
        CEP.TRC(SCCGWA, BPRVCOR.KEY.SEQ_NO);
        T000_WRITE_BPTVCOR();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTVCOR_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTVCOR();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTVCOR();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTVCOR() throws IOException,SQLException,Exception {
        BPTVCOR_RD = new DBParm();
        BPTVCOR_RD.TableName = "BPTVCOR";
        IBS.READ(SCCGWA, BPRVCOR, BPTVCOR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_VCOR_NOT_FIND, BPCRVCOR.RC);
        } else {
        }
    }
    public void T000_WRITE_BPTVCOR() throws IOException,SQLException,Exception {
        BPTVCOR_RD = new DBParm();
        BPTVCOR_RD.TableName = "BPTVCOR";
        BPTVCOR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRVCOR, BPTVCOR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "111");
        } else {
            CEP.TRC(SCCGWA, "222");
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_VCOR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTVCOR_UPD() throws IOException,SQLException,Exception {
        BPTVCOR_RD = new DBParm();
        BPTVCOR_RD.TableName = "BPTVCOR";
        BPTVCOR_RD.upd = true;
        IBS.READ(SCCGWA, BPRVCOR, BPTVCOR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_VCOR_NOT_FIND, BPCRVCOR.RC);
        } else {
        }
    }
    public void T000_REWRITE_BPTVCOR() throws IOException,SQLException,Exception {
        BPTVCOR_RD = new DBParm();
        BPTVCOR_RD.TableName = "BPTVCOR";
        IBS.REWRITE(SCCGWA, BPRVCOR, BPTVCOR_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRVCOR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRVCOR = ");
            CEP.TRC(SCCGWA, BPCRVCOR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
