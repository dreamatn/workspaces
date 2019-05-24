package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTATB {
    DBParm BPTTATH_RD;
    brParm BPTTATH_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTATB";
    String K_TBL_TATH = "BPTTATH ";
    char WS_TBL_TATH_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTATH BPRTATH = new BPRTATH();
    SCCGWA SCCGWA;
    BPCRTATB BPCRTATB;
    BPRTATH BPRTATL;
    public void MP(SCCGWA SCCGWA, BPCRTATB BPCRTATB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTATB = BPCRTATB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTATB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTATL = (BPRTATH) BPCRTATB.INFO.POINTER;
        IBS.init(SCCGWA, BPRTATH);
        IBS.CLONE(SCCGWA, BPRTATL, BPRTATH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTATB.INFO.FUNC == '1'
            || BPCRTATB.INFO.FUNC == '2'
            || BPCRTATB.INFO.FUNC == '3') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTATB.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTATB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTATB.INFO.FUNC == 'W') {
            B040_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRTATB.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTATB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTATH, BPRTATL);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRTATB.INFO.FUNC == '1') {
            T000_STARTBR_BPTTATH_01();
            if (pgmRtn) return;
        } else if (BPCRTATB.INFO.FUNC == '2') {
            T000_STARTBR_BPTTATH_02();
            if (pgmRtn) return;
        } else if (BPCRTATB.INFO.FUNC == '3') {
            T000_STARTBR_BPTTATH_03();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTTATH();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTTATH();
        if (pgmRtn) return;
    }
    public void B040_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTTATH();
        if (pgmRtn) return;
    }
    public void T000_REWRITE_BPTTATH() throws IOException,SQLException,Exception {
        BPTTATH_RD = new DBParm();
        BPTTATH_RD.TableName = "BPTTATH";
        IBS.REWRITE(SCCGWA, BPRTATH, BPTTATH_RD);
    }
    public void T000_STARTBR_BPTTATH_01() throws IOException,SQLException,Exception {
        BPTTATH_BR.rp = new DBParm();
        BPTTATH_BR.rp.TableName = "BPTTATH";
        BPTTATH_BR.rp.where = "ASS_TYP LIKE :BPRTATH.KEY.ASS_TYP "
            + "AND ASS_ID LIKE :BPRTATH.KEY.ASS_ID "
            + "AND ATH_TYP LIKE :BPRTATH.KEY.ATH_TYP";
        IBS.STARTBR(SCCGWA, BPRTATH, this, BPTTATH_BR);
    }
    public void T000_STARTBR_BPTTATH_02() throws IOException,SQLException,Exception {
        BPTTATH_BR.rp = new DBParm();
        BPTTATH_BR.rp.TableName = "BPTTATH";
        IBS.STARTBR(SCCGWA, BPRTATH, BPTTATH_BR);
    }
    public void T000_STARTBR_BPTTATH_03() throws IOException,SQLException,Exception {
        BPTTATH_BR.rp = new DBParm();
        BPTTATH_BR.rp.TableName = "BPTTATH";
        BPTTATH_BR.rp.where = "ASS_TYP = :BPRTATH.KEY.ASS_TYP "
            + "AND ASS_ID = :BPRTATH.KEY.ASS_ID "
            + "AND ATH_TYP = :BPRTATH.KEY.ATH_TYP";
        BPTTATH_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRTATH, this, BPTTATH_BR);
    }
    public void T000_READNEXT_BPTTATH() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRTATH, this, BPTTATH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTATB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTATB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTTATH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTATH_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTATB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTATB = ");
            CEP.TRC(SCCGWA, BPCRTATB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
