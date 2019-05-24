package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRMPRT {
    DBParm BPTPRTR_RD;
    brParm BPTPRTR_BR = new brParm();
    boolean pgmRtn = false;
    String TBL_BPTPRTR = "BPTPRTR";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRPRTR BPRPRTR = new BPRPRTR();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCRMPRT BPCRMPRT;
    BPRPRTR BPRPRTRL;
    public void MP(SCCGWA SCCGWA, BPCRMPRT BPCRMPRT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRMPRT = BPCRMPRT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRMPRT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCRMPRT.RC);
        BPRPRTRL = (BPRPRTR) BPCRMPRT.INFO.POINTER;
        IBS.init(SCCGWA, BPRPRTR);
        IBS.CLONE(SCCGWA, BPRPRTRL, BPRPRTR);
        CEP.TRC(SCCGWA, BPRPRTR);
        CEP.TRC(SCCGWA, BPCRMPRT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRMPRT.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMPRT.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMPRT.INFO.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMPRT.INFO.FUNC == 'I') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMPRT.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMPRT.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCRMPRT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRPRTR, BPRPRTRL);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTPRTR();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPRTR_UPD();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTPRTR();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPRTR();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTPRTR();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRMPRT.INFO.OPT == 'S') {
            T000_STARTBR_BPTPRTR();
            if (pgmRtn) return;
        } else if (BPCRMPRT.INFO.OPT == 'N') {
            T000_READNEXT_BPTPRTR();
            if (pgmRtn) return;
        } else if (BPCRMPRT.INFO.OPT == 'E') {
            T000_ENDBR_BPTPRTR();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCRMPRT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTPRTR() throws IOException,SQLException,Exception {
        BPTPRTR_RD = new DBParm();
        BPTPRTR_RD.TableName = "BPTPRTR";
        BPTPRTR_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRPRTR, BPTPRTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMPRT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRMPRT.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTPRTR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BPTPRTR() throws IOException,SQLException,Exception {
        BPTPRTR_RD = new DBParm();
        BPTPRTR_RD.TableName = "BPTPRTR";
        BPTPRTR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRPRTR, BPTPRTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMPRT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRMPRT.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRTR_ALREDY_EXIST, BPCRMPRT.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTPRTR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTPRTR_UPD() throws IOException,SQLException,Exception {
        BPTPRTR_RD = new DBParm();
        BPTPRTR_RD.TableName = "BPTPRTR";
        BPTPRTR_RD.upd = true;
        IBS.READ(SCCGWA, BPRPRTR, BPTPRTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMPRT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRMPRT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTPRTR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTPRTR() throws IOException,SQLException,Exception {
        BPTPRTR_RD = new DBParm();
        BPTPRTR_RD.TableName = "BPTPRTR";
        IBS.REWRITE(SCCGWA, BPRPRTR, BPTPRTR_RD);
    }
    public void T000_DELETE_BPTPRTR() throws IOException,SQLException,Exception {
        BPTPRTR_RD = new DBParm();
        BPTPRTR_RD.TableName = "BPTPRTR";
        IBS.DELETE(SCCGWA, BPRPRTR, BPTPRTR_RD);
    }
    public void T000_STARTBR_BPTPRTR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        CEP.TRC(SCCGWA, BPRPRTR.KEY.NAME);
        if (BPRPRTR.KEY.NAME.trim().length() == 0) {
            BPTPRTR_BR.rp = new DBParm();
            BPTPRTR_BR.rp.TableName = "BPTPRTR";
            IBS.STARTBR(SCCGWA, BPRPRTR, BPTPRTR_BR);
        } else {
            BPTPRTR_BR.rp = new DBParm();
            BPTPRTR_BR.rp.TableName = "BPTPRTR";
            BPTPRTR_BR.rp.where = "NAME = :BPRPRTR.KEY.NAME";
            IBS.STARTBR(SCCGWA, BPRPRTR, this, BPTPRTR_BR);
        }
    }
    public void T000_READNEXT_BPTPRTR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NEXT BROWER");
        BPTPRTR_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRPRTR, this, BPTPRTR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMPRT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRMPRT.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTPRTR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTPRTR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTPRTR_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRMPRT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRMPRT = ");
            CEP.TRC(SCCGWA, BPCRMPRT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
