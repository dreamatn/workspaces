package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRYYCT {
    brParm BPTORGR_BR = new brParm();
    boolean pgmRtn = false;
    String K_TBL_ORGR = "BPTORGR ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_ORGR_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRORGR BPRORGR = new BPRORGR();
    SCCGWA SCCGWA;
    BPCRBORR BPCRBORR;
    BPRORGR BPRORGR1;
    public void MP(SCCGWA SCCGWA, BPCRBORR BPCRBORR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRBORR = BPCRBORR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRYYCT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRORGR);
        IBS.init(SCCGWA, BPCRBORR.RC);
        BPRORGR1 = (BPRORGR) BPCRBORR.INFO.POINTER;
        IBS.CLONE(SCCGWA, BPRORGR1, BPRORGR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRBORR.FUNC == 'S') {
            B010_STARTBR_PROC();
            if (pgmRtn) return;
        } else if (BPCRBORR.FUNC == 'R') {
            B020_READNEXT_PROC();
            if (pgmRtn) return;
        } else if (BPCRBORR.FUNC == 'E') {
            B030_ENDBR_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRBORR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRORGR, BPRORGR1);
    }
    public void B010_STARTBR_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTORGR();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTORGR();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTORGR();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTORGR() throws IOException,SQLException,Exception {
        if (BPRORGR.KEY.BR > 0 
            && BPRORGR.KEY.TYP.trim().length() > 0) {
            BPTORGR_BR.rp = new DBParm();
            BPTORGR_BR.rp.TableName = "BPTORGR";
            BPTORGR_BR.rp.where = "BNK = :BPRORGR.KEY.BNK "
                + "AND BR = :BPRORGR.KEY.BR "
                + "AND TYP = :BPRORGR.KEY.TYP";
            IBS.STARTBR(SCCGWA, BPRORGR, this, BPTORGR_BR);
        }
        if (BPRORGR.KEY.BR == 0 
            && BPRORGR.KEY.TYP.trim().length() > 0) {
            BPTORGR_BR.rp = new DBParm();
            BPTORGR_BR.rp.TableName = "BPTORGR";
            BPTORGR_BR.rp.where = "BNK = :BPRORGR.KEY.BNK "
                + "AND TYP = :BPRORGR.KEY.TYP";
            IBS.STARTBR(SCCGWA, BPRORGR, this, BPTORGR_BR);
        }
        if (BPRORGR.KEY.BR > 0 
            && BPRORGR.KEY.TYP.trim().length() == 0) {
            BPTORGR_BR.rp = new DBParm();
            BPTORGR_BR.rp.TableName = "BPTORGR";
            BPTORGR_BR.rp.where = "BNK = :BPRORGR.KEY.BNK "
                + "AND BR = :BPRORGR.KEY.BR";
            IBS.STARTBR(SCCGWA, BPRORGR, this, BPTORGR_BR);
        }
        if (BPRORGR.KEY.BR == 0 
            && BPRORGR.KEY.TYP.trim().length() == 0) {
            BPTORGR_BR.rp = new DBParm();
            BPTORGR_BR.rp.TableName = "BPTORGR";
            BPTORGR_BR.rp.where = "BNK = :BPRORGR.KEY.BNK";
            IBS.STARTBR(SCCGWA, BPRORGR, this, BPTORGR_BR);
        }
    }
    public void T000_READNEXT_BPTORGR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRORGR, this, BPTORGR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBORR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBORR.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTORGR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTORGR_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRBORR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRBORR = ");
            CEP.TRC(SCCGWA, BPCRBORR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}