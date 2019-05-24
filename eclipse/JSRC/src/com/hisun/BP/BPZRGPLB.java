package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRGPLB {
    DBParm BPTGRPL_RD;
    brParm BPTGRPL_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRGPLB";
    String K_TBL_GRPL = "BPTGRPL ";
    char WS_TBL_GRPL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRGRPL BPRGRPL = new BPRGRPL();
    SCCGWA SCCGWA;
    BPCRGPLB BPCRGPLB;
    BPRGRPL BPRGRPL1;
    public void MP(SCCGWA SCCGWA, BPCRGPLB BPCRGPLB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRGPLB = BPCRGPLB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRGPLB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRGRPL1 = (BPRGRPL) BPCRGPLB.INFO.POINTER;
        IBS.init(SCCGWA, BPRGRPL);
        IBS.CLONE(SCCGWA, BPRGRPL1, BPRGRPL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRGPLB.INFO.FUNC == '1'
            || BPCRGPLB.INFO.FUNC == '2'
            || BPCRGPLB.INFO.FUNC == '3') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRGPLB.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRGPLB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRGPLB.INFO.FUNC == 'W') {
            B040_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRGPLB.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRGPLB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRGRPL, BPRGRPL1);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRGPLB.INFO.FUNC == '1') {
            T000_STARTBR_BPTGRPL_01();
            if (pgmRtn) return;
        } else if (BPCRGPLB.INFO.FUNC == '2') {
            T000_STARTBR_BPTGRPL_02();
            if (pgmRtn) return;
        } else if (BPCRGPLB.INFO.FUNC == '3') {
            T000_STARTBR_BPTGRPL_03();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTGRPL();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTGRPL();
        if (pgmRtn) return;
    }
    public void B040_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTGRPL();
        if (pgmRtn) return;
    }
    public void T000_REWRITE_BPTGRPL() throws IOException,SQLException,Exception {
        BPTGRPL_RD = new DBParm();
        BPTGRPL_RD.TableName = "BPTGRPL";
        IBS.REWRITE(SCCGWA, BPRGRPL, BPTGRPL_RD);
    }
    public void T000_STARTBR_BPTGRPL_01() throws IOException,SQLException,Exception {
        BPTGRPL_BR.rp = new DBParm();
        BPTGRPL_BR.rp.TableName = "BPTGRPL";
        BPTGRPL_BR.rp.where = "ASS_TYP LIKE :BPRGRPL.KEY.ASS_TYP "
            + "AND ASS_ID LIKE :BPRGRPL.KEY.ASS_ID "
            + "AND ATH_TYP LIKE :BPRGRPL.KEY.ATH_TYP";
        BPTGRPL_BR.rp.order = "ASS_ID";
        IBS.STARTBR(SCCGWA, BPRGRPL, this, BPTGRPL_BR);
    }
    public void T000_STARTBR_BPTGRPL_02() throws IOException,SQLException,Exception {
        BPTGRPL_BR.rp = new DBParm();
        BPTGRPL_BR.rp.TableName = "BPTGRPL";
        BPTGRPL_BR.rp.where = "ASS_TYP = :BPRGRPL.KEY.ASS_TYP "
            + "AND ASS_ID = :BPRGRPL.KEY.ASS_ID "
            + "AND ATH_TYP = :BPRGRPL.KEY.ATH_TYP";
        IBS.STARTBR(SCCGWA, BPRGRPL, this, BPTGRPL_BR);
    }
    public void T000_STARTBR_BPTGRPL_03() throws IOException,SQLException,Exception {
        BPTGRPL_BR.rp = new DBParm();
        BPTGRPL_BR.rp.TableName = "BPTGRPL";
        BPTGRPL_BR.rp.where = "ASS_TYP = :BPRGRPL.KEY.ASS_TYP "
            + "AND ASS_ID = :BPRGRPL.KEY.ASS_ID "
            + "AND ATH_TYP = :BPRGRPL.KEY.ATH_TYP";
        BPTGRPL_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRGRPL, this, BPTGRPL_BR);
    }
    public void T000_READNEXT_BPTGRPL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRGRPL, this, BPTGRPL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRGPLB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRGPLB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTGRPL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTGRPL_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRGPLB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRGPLB = ");
            CEP.TRC(SCCGWA, BPCRGPLB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
