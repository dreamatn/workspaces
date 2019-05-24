package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTOTM {
    DBParm BPTTOT_RD;
    brParm BPTTOT_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTOTM";
    String K_TBL_TOT = "BPTTOT  ";
    char WS_TBL_TOT_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTOT BPRTOT = new BPRTOT();
    SCCGWA SCCGWA;
    BPCRTOTM BPCRTOTM;
    BPRTOT BPRTST;
    public void MP(SCCGWA SCCGWA, BPCRTOTM BPCRTOTM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTOTM = BPCRTOTM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTOTM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTST = (BPRTLT) BPCRTOTM.INFO.POINTER;
        IBS.init(SCCGWA, BPRTOT);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCRTOTM.RC);
        IBS.CLONE(SCCGWA, BPRTST, BPRTOT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRTOTM.INFO.FUNC);
        if (BPCRTOTM.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTOTM.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTOTM.INFO.FUNC == 'U') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTOTM.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTOTM.INFO.FUNC == 'B') {
            B040_STARTBR_RECORD_BR_PROC();
            if (pgmRtn) return;
        } else if (BPCRTOTM.INFO.FUNC == 'T') {
            B050_STARTBR_RECORD_TLR_PROC();
            if (pgmRtn) return;
        } else if (BPCRTOTM.INFO.FUNC == 'P') {
            B055_STARTBR_TLR_UPD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTOTM.INFO.FUNC == 'N') {
            B060_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTOTM.INFO.FUNC == 'E') {
            B070_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTOTM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTOT, BPRTST);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTTOT();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTOT_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTTOT();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTOT();
        if (pgmRtn) return;
    }
    public void B040_STARTBR_RECORD_BR_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTOT_01();
        if (pgmRtn) return;
    }
    public void B050_STARTBR_RECORD_TLR_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTOT_02();
        if (pgmRtn) return;
    }
    public void B055_STARTBR_TLR_UPD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTOT_03();
        if (pgmRtn) return;
    }
    public void B060_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTTOT();
        if (pgmRtn) return;
    }
    public void B070_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTTOT();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTTOT() throws IOException,SQLException,Exception {
        BPTTOT_RD = new DBParm();
        BPTTOT_RD.TableName = "BPTTOT";
        IBS.READ(SCCGWA, BPRTOT, BPTTOT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTOTM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTOTM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTTOT() throws IOException,SQLException,Exception {
        BPTTOT_RD = new DBParm();
        BPTTOT_RD.TableName = "BPTTOT";
        BPTTOT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRTOT, BPTTOT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTOTM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRTOTM.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_TOT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTTOT_UPD() throws IOException,SQLException,Exception {
        BPTTOT_RD = new DBParm();
        BPTTOT_RD.TableName = "BPTTOT";
        BPTTOT_RD.upd = true;
        IBS.READ(SCCGWA, BPRTOT, BPTTOT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTOTM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTOTM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTTOT() throws IOException,SQLException,Exception {
        BPTTOT_RD = new DBParm();
        BPTTOT_RD.TableName = "BPTTOT";
        IBS.REWRITE(SCCGWA, BPRTOT, BPTTOT_RD);
    }
    public void T000_STARTBR_BPTTOT_01() throws IOException,SQLException,Exception {
        BPTTOT_BR.rp = new DBParm();
        BPTTOT_BR.rp.TableName = "BPTTOT";
        BPTTOT_BR.rp.where = "TLR_BR = :BPRTOT.TLR_BR";
        BPTTOT_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRTOT, this, BPTTOT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTOTM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTOTM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTTOT_02() throws IOException,SQLException,Exception {
        BPTTOT_BR.rp = new DBParm();
        BPTTOT_BR.rp.TableName = "BPTTOT";
        BPTTOT_BR.rp.where = "TLR = :BPRTOT.KEY.TLR";
        BPTTOT_BR.rp.errhdl = true;
        BPTTOT_BR.rp.order = "LAST_DATE DESC";
        IBS.STARTBR(SCCGWA, BPRTOT, this, BPTTOT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTOTM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTOTM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTTOT_03() throws IOException,SQLException,Exception {
        BPTTOT_BR.rp = new DBParm();
        BPTTOT_BR.rp.TableName = "BPTTOT";
        BPTTOT_BR.rp.upd = true;
        BPTTOT_BR.rp.where = "TLR = :BPRTOT.KEY.TLR";
        BPTTOT_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRTOT, this, BPTTOT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTOTM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTOTM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTTOT() throws IOException,SQLException,Exception {
        BPTTOT_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRTOT, this, BPTTOT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTOTM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTOTM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTTOT() throws IOException,SQLException,Exception {
        BPTTOT_BR.rp.errhdl = true;
        IBS.ENDBR(SCCGWA, BPTTOT_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTOTM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTOTM = ");
            CEP.TRC(SCCGWA, BPCRTOTM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
