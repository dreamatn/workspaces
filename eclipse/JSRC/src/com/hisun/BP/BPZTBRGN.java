package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTBRGN {
    brParm BPTRGND_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTBRGN";
    String K_TBL_RGND = "BPTRGND ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_RGND_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRRGND BPRRGND = new BPRRGND();
    BPCPQRGN BPCPQRGN = new BPCPQRGN();
    SCCGWA SCCGWA;
    BPRRGND BPRRGND1;
    BPCTBRGN BPCTBRGN;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCTBRGN BPCTBRGN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTBRGN = BPCTBRGN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTBRGN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRRGND1 = (BPRRGND) BPCTBRGN.INFO.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRRGND);
        IBS.CLONE(SCCGWA, BPRRGND1, BPRRGND);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTBRGN.FUNC == 'S') {
            B010_STARTBR_PROC();
            if (pgmRtn) return;
        } else if (BPCTBRGN.FUNC == 'R') {
            B020_READNEXT_PROC();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, BPRRGND, BPRRGND1);
        } else if (BPCTBRGN.FUNC == 'E') {
            B030_ENDBR_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTBRGN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_STARTBR_PROC() throws IOException,SQLException,Exception {
        if (BPRRGND.KEY.RGN_TYPE.trim().length() == 0 
                && BPRRGND.KEY.RGN_NO == 0 
                && BPRRGND.KEY.RGN_UNIT.trim().length() == 0) {
            T000_STARTBR_BY_BANK();
            if (pgmRtn) return;
        } else if (BPRRGND.KEY.RGN_TYPE.trim().length() > 0 
                && BPRRGND.KEY.RGN_NO == 0 
                && BPRRGND.KEY.RGN_UNIT.trim().length() == 0) {
            T000_STARTBR_BY_BANK_TYP();
            if (pgmRtn) return;
        } else if (BPRRGND.KEY.RGN_TYPE.trim().length() == 0 
                && BPRRGND.KEY.RGN_NO != 0 
                && BPRRGND.KEY.RGN_UNIT.trim().length() == 0) {
            T000_STARTBR_BY_BANK_SEQ();
            if (pgmRtn) return;
        } else if (BPRRGND.KEY.RGN_TYPE.trim().length() == 0 
                && BPRRGND.KEY.RGN_NO == 0 
                && BPRRGND.KEY.RGN_UNIT.trim().length() > 0) {
            T000_STARTBR_BY_BANK_BR();
            if (pgmRtn) return;
        } else if (BPRRGND.KEY.RGN_TYPE.trim().length() > 0 
                && BPRRGND.KEY.RGN_NO != 0 
                && BPRRGND.KEY.RGN_UNIT.trim().length() == 0) {
            T000_STARTBR_BY_BANK_TYPE_SEQ();
            if (pgmRtn) return;
        } else if (BPRRGND.KEY.RGN_TYPE.trim().length() == 0 
                && BPRRGND.KEY.RGN_NO != 0 
                && BPRRGND.KEY.RGN_UNIT.trim().length() > 0) {
            T000_STARTBR_BY_BANK_SEQ_BR();
            if (pgmRtn) return;
        } else if (BPRRGND.KEY.RGN_TYPE.trim().length() > 0 
                && BPRRGND.KEY.RGN_NO == 0 
                && BPRRGND.KEY.RGN_UNIT.trim().length() > 0) {
            T000_STARTBR_BY_BANK_TYPE_BR();
            if (pgmRtn) return;
        } else if (BPRRGND.KEY.RGN_TYPE.trim().length() > 0 
                && BPRRGND.KEY.RGN_NO != 0 
                && BPRRGND.KEY.RGN_UNIT.trim().length() > 0) {
            T000_STARTBR_BY_ALL();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTBRGN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_READNEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTRGND();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTRGND();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BY_BANK() throws IOException,SQLException,Exception {
        BPTRGND_BR.rp = new DBParm();
        BPTRGND_BR.rp.TableName = "BPTRGND";
        BPTRGND_BR.rp.where = "BNK = :BPRRGND.KEY.BNK";
        BPTRGND_BR.rp.order = "RGN_TYPE , RGN_NO , RGN_UNIT";
        IBS.STARTBR(SCCGWA, BPRRGND, this, BPTRGND_BR);
    }
    public void T000_STARTBR_BY_BANK_TYP() throws IOException,SQLException,Exception {
        BPTRGND_BR.rp = new DBParm();
        BPTRGND_BR.rp.TableName = "BPTRGND";
        BPTRGND_BR.rp.where = "BNK = :BPRRGND.KEY.BNK "
            + "AND RGN_TYPE = :BPRRGND.KEY.RGN_TYPE";
        BPTRGND_BR.rp.order = "BNK , RGN_TYPE , RGN_NO";
        IBS.STARTBR(SCCGWA, BPRRGND, this, BPTRGND_BR);
    }
    public void T000_STARTBR_BY_BANK_SEQ() throws IOException,SQLException,Exception {
        BPTRGND_BR.rp = new DBParm();
        BPTRGND_BR.rp.TableName = "BPTRGND";
        BPTRGND_BR.rp.where = "BNK = :BPRRGND.KEY.BNK "
            + "AND RGN_NO = :BPRRGND.KEY.RGN_NO";
        IBS.STARTBR(SCCGWA, BPRRGND, this, BPTRGND_BR);
    }
    public void T000_STARTBR_BY_BANK_BR() throws IOException,SQLException,Exception {
        BPTRGND_BR.rp = new DBParm();
        BPTRGND_BR.rp.TableName = "BPTRGND";
        BPTRGND_BR.rp.where = "BNK = :BPRRGND.KEY.BNK "
            + "AND RGN_UNIT = :BPRRGND.KEY.RGN_UNIT";
        IBS.STARTBR(SCCGWA, BPRRGND, this, BPTRGND_BR);
    }
    public void T000_STARTBR_BY_BANK_TYPE_SEQ() throws IOException,SQLException,Exception {
        BPTRGND_BR.rp = new DBParm();
        BPTRGND_BR.rp.TableName = "BPTRGND";
        BPTRGND_BR.rp.where = "BNK = :BPRRGND.KEY.BNK "
            + "AND RGN_TYPE = :BPRRGND.KEY.RGN_TYPE "
            + "AND RGN_NO = :BPRRGND.KEY.RGN_NO";
        IBS.STARTBR(SCCGWA, BPRRGND, this, BPTRGND_BR);
    }
    public void T000_STARTBR_BY_BANK_SEQ_BR() throws IOException,SQLException,Exception {
        BPTRGND_BR.rp = new DBParm();
        BPTRGND_BR.rp.TableName = "BPTRGND";
        BPTRGND_BR.rp.where = "BNK = :BPRRGND.KEY.BNK "
            + "AND RGN_NO = :BPRRGND.KEY.RGN_NO "
            + "AND RGN_UNIT = :BPRRGND.KEY.RGN_UNIT";
        IBS.STARTBR(SCCGWA, BPRRGND, this, BPTRGND_BR);
    }
    public void T000_STARTBR_BY_BANK_TYPE_BR() throws IOException,SQLException,Exception {
        BPTRGND_BR.rp = new DBParm();
        BPTRGND_BR.rp.TableName = "BPTRGND";
        BPTRGND_BR.rp.where = "BNK = :BPRRGND.KEY.BNK "
            + "AND RGN_TYPE = :BPRRGND.KEY.RGN_TYPE "
            + "AND RGN_UNIT = :BPRRGND.KEY.RGN_UNIT";
        IBS.STARTBR(SCCGWA, BPRRGND, this, BPTRGND_BR);
    }
    public void T000_STARTBR_BY_ALL() throws IOException,SQLException,Exception {
        BPTRGND_BR.rp = new DBParm();
        BPTRGND_BR.rp.TableName = "BPTRGND";
        BPTRGND_BR.rp.where = "BNK = :BPRRGND.KEY.BNK "
            + "AND RGN_TYPE = :BPRRGND.KEY.RGN_TYPE "
            + "AND RGN_NO = :BPRRGND.KEY.RGN_NO "
            + "AND RGN_UNIT = :BPRRGND.KEY.RGN_UNIT";
        IBS.STARTBR(SCCGWA, BPRRGND, this, BPTRGND_BR);
    }
    public void T000_READNEXT_BPTRGND() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRRGND, this, BPTRGND_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTBRGN.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTBRGN.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTRGND() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTRGND_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
