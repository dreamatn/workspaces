package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRFSAF {
    brParm BPTFAGOR_BR = new brParm();
    DBParm BPTFAGOR_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRFSAF";
    String K_TBL_FSAF = "BPTFAGOR";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRFAGOR BPRFAGOR = new BPRFAGOR();
    SCCGWA SCCGWA;
    BPCRFSAF BPCRFSAF;
    BPRFAGOR BPRLAGOR;
    public void MP(SCCGWA SCCGWA, BPCRFSAF BPCRFSAF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRFSAF = BPCRFSAF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRFSAF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRLAGOR = (BPRFAGOR) BPCRFSAF.INFO.POINTER;
        IBS.init(SCCGWA, BPRFAGOR);
        IBS.CLONE(SCCGWA, BPRLAGOR, BPRFAGOR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRFSAF.INFO.FUNC == '1'
            || BPCRFSAF.INFO.FUNC == '2'
            || BPCRFSAF.INFO.FUNC == '3'
            || BPCRFSAF.INFO.FUNC == '4') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFSAF.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFSAF.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFSAF.INFO.FUNC == 'M') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFSAF.INFO.FUNC == 'D') {
            B022_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFSAF.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFSAF.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFSAF.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRFSAF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRFAGOR, BPRLAGOR);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRFSAF.INFO.FUNC == '1') {
            T000_STARTBR_BPTFAGOR_01();
            if (pgmRtn) return;
        } else if (BPCRFSAF.INFO.FUNC == '2') {
            T000_STARTBR_BPTFAGOR_02();
            if (pgmRtn) return;
        } else if (BPCRFSAF.INFO.FUNC == '3') {
            T000_STARTBR_BPTFAGOR_03();
            if (pgmRtn) return;
        } else if (BPCRFSAF.INFO.FUNC == '4') {
            T000_STARTBR_BPTFAGOR_04();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTFAGOR();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTFAGOR_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTFAGOR();
        if (pgmRtn) return;
    }
    public void B022_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTFAGOR();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTFAGOR();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTFAGOR();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTFAGOR();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTFAGOR_01() throws IOException,SQLException,Exception {
        BPTFAGOR_BR.rp = new DBParm();
        BPTFAGOR_BR.rp.TableName = "BPTFAGOR";
        BPTFAGOR_BR.rp.where = "CI_NO LIKE :BPRFAGOR.KEY.CI_NO "
            + "AND FEE_CODE LIKE :BPRFAGOR.KEY.FEE_CODE";
        BPTFAGOR_BR.rp.order = "CI_NO, FEE_CODE";
        IBS.STARTBR(SCCGWA, BPRFAGOR, this, BPTFAGOR_BR);
    }
    public void T000_STARTBR_BPTFAGOR_02() throws IOException,SQLException,Exception {
        BPTFAGOR_BR.rp = new DBParm();
        BPTFAGOR_BR.rp.TableName = "BPTFAGOR";
        BPTFAGOR_BR.rp.where = "CI_NO = :BPRFAGOR.KEY.CI_NO "
            + "AND FEE_CODE = :BPRFAGOR.KEY.FEE_CODE";
        IBS.STARTBR(SCCGWA, BPRFAGOR, this, BPTFAGOR_BR);
    }
    public void T000_STARTBR_BPTFAGOR_03() throws IOException,SQLException,Exception {
        BPTFAGOR_BR.rp = new DBParm();
        BPTFAGOR_BR.rp.TableName = "BPTFAGOR";
        BPTFAGOR_BR.rp.where = "CI_NO LIKE :BPRFAGOR.KEY.CI_NO";
        IBS.STARTBR(SCCGWA, BPRFAGOR, this, BPTFAGOR_BR);
    }
    public void T000_STARTBR_BPTFAGOR_04() throws IOException,SQLException,Exception {
        BPTFAGOR_BR.rp = new DBParm();
        BPTFAGOR_BR.rp.TableName = "BPTFAGOR";
        BPTFAGOR_BR.rp.where = "FEE_CODE LIKE :BPRFAGOR.KEY.FEE_CODE";
        IBS.STARTBR(SCCGWA, BPRFAGOR, this, BPTFAGOR_BR);
    }
    public void T000_READNEXT_BPTFAGOR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFAGOR, this, BPTFAGOR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFSAF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFSAF.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READ_BPTFAGOR() throws IOException,SQLException,Exception {
        BPTFAGOR_RD = new DBParm();
        BPTFAGOR_RD.TableName = "BPTFAGOR";
        IBS.READ(SCCGWA, BPRFAGOR, BPTFAGOR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFSAF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFSAF.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTFAGOR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFAGOR.KEY.CI_NO);
        BPTFAGOR_RD = new DBParm();
        BPTFAGOR_RD.TableName = "BPTFAGOR";
        BPTFAGOR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRFAGOR, BPTFAGOR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFSAF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFSAF.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_FSAF;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTFAGOR_UPD() throws IOException,SQLException,Exception {
        BPTFAGOR_RD = new DBParm();
        BPTFAGOR_RD.TableName = "BPTFAGOR";
        BPTFAGOR_RD.upd = true;
        IBS.READ(SCCGWA, BPRFAGOR, BPTFAGOR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFSAF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFSAF.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTFAGOR() throws IOException,SQLException,Exception {
        BPTFAGOR_RD = new DBParm();
        BPTFAGOR_RD.TableName = "BPTFAGOR";
        IBS.REWRITE(SCCGWA, BPRFAGOR, BPTFAGOR_RD);
    }
    public void T000_DELETE_BPTFAGOR() throws IOException,SQLException,Exception {
        BPTFAGOR_RD = new DBParm();
        BPTFAGOR_RD.TableName = "BPTFAGOR";
        IBS.DELETE(SCCGWA, BPRFAGOR, BPTFAGOR_RD);
    }
    public void T000_ENDBR_BPTFAGOR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFAGOR_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRFSAF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRFSAF = ");
            CEP.TRC(SCCGWA, BPCRFSAF);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
