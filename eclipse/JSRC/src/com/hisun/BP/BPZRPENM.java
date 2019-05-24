package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRPENM {
    DBParm BPTPEND_RD;
    brParm BPTPEND_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRPENM";
    String K_TBL_PEND = "BPTPEND ";
    char WS_TBL_PEND_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRPEND BPRPEND = new BPRPEND();
    SCCGWA SCCGWA;
    BPCRPENM BPCRPENM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRPEND BPRPENDL;
    public void MP(SCCGWA SCCGWA, BPCRPENM BPCRPENM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRPENM = BPCRPENM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRPENM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRPENDL = (BPRPEND) BPCRPENM.INFO.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRPEND);
        IBS.CLONE(SCCGWA, BPRPENDL, BPRPEND);
        BPCRPENM.RETURN_INFO = 'F';
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCRPENM.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRPENM.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRPENM.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRPENM.INFO.FUNC == 'U') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRPENM.INFO.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRPENM.INFO.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRPENM.INFO.FUNC == 'S') {
            B050_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRPENM.INFO.FUNC == 'N') {
            B060_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRPENM.INFO.FUNC == 'E') {
            B070_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRPENM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRPEND, BPRPENDL);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTPEND();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPEND_UPD();
        if (pgmRtn) return;
        if (WS_TBL_PEND_FLAG == 'D') {
            BPCRPENM.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTPEND();
        if (pgmRtn) return;
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTPEND();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_QUERY_BPTPEND();
        if (pgmRtn) return;
        if (WS_TBL_PEND_FLAG == 'D') {
            BPCRPENM.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B050_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTPEND();
        if (pgmRtn) return;
    }
    public void B060_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTPEND();
        if (pgmRtn) return;
    }
    public void B070_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTPEND();
        if (pgmRtn) return;
    }
    public void T000_DELETE_BPTPEND() throws IOException,SQLException,Exception {
        BPTPEND_RD = new DBParm();
        BPTPEND_RD.TableName = "BPTPEND";
        IBS.DELETE(SCCGWA, BPRPEND, BPTPEND_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRPENM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRPENM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTPEND() throws IOException,SQLException,Exception {
        BPTPEND_RD = new DBParm();
        BPTPEND_RD.TableName = "BPTPEND";
        BPTPEND_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRPEND, BPTPEND_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRPENM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRPENM.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_PEND;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTPEND_UPD() throws IOException,SQLException,Exception {
        BPTPEND_RD = new DBParm();
        BPTPEND_RD.TableName = "BPTPEND";
        BPTPEND_RD.upd = true;
        IBS.READ(SCCGWA, BPRPEND, BPTPEND_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_PEND_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_PEND_FLAG = 'D';
        } else {
        }
    }
    public void T000_REWRITE_BPTPEND() throws IOException,SQLException,Exception {
        BPTPEND_RD = new DBParm();
        BPTPEND_RD.TableName = "BPTPEND";
        IBS.REWRITE(SCCGWA, BPRPEND, BPTPEND_RD);
    }
    public void T000_QUERY_BPTPEND() throws IOException,SQLException,Exception {
        BPTPEND_RD = new DBParm();
        BPTPEND_RD.TableName = "BPTPEND";
        IBS.READ(SCCGWA, BPRPEND, BPTPEND_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_PEND_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_PEND_FLAG = 'D';
        } else {
        }
    }
    public void T000_STARTBR_BPTPEND() throws IOException,SQLException,Exception {
        BPTPEND_BR.rp = new DBParm();
        BPTPEND_BR.rp.TableName = "BPTPEND";
        BPTPEND_BR.rp.where = "TLR = :BPRPEND.KEY.TLR";
        BPTPEND_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, BPRPEND, this, BPTPEND_BR);
    }
    public void T000_READNEXT_BPTPEND() throws IOException,SQLException,Exception {
        BPTPEND_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRPEND, this, BPTPEND_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRPENM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRPENM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTPEND() throws IOException,SQLException,Exception {
        BPTPEND_BR.rp.errhdl = true;
        IBS.ENDBR(SCCGWA, BPTPEND_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRPENM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRPENM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRPENM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRPENM = ");
            CEP.TRC(SCCGWA, BPCRPENM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
