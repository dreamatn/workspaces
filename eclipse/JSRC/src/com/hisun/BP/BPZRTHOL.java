package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTHOL {
    DBParm BPTTHOL_RD;
    brParm BPTTHOL_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRTHOL";
    String K_TBL_THOL = "BPTTHOL ";
    int WS_REC_LEN = 0;
    char WS_TBL_IRPD_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTHOL BPRTHOL = new BPRTHOL();
    SCCGWA SCCGWA;
    BPCRTHOL BPCRTHOL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTHOL BPRTHLL;
    public void MP(SCCGWA SCCGWA, BPCRTHOL BPCRTHOL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTHOL = BPCRTHOL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRTHOL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPRTHLL = (BPRTHOL) BPCRTHOL.POINTER;
        IBS.init(SCCGWA, BPRTHOL);
        IBS.CLONE(SCCGWA, BPRTHLL, BPRTHOL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTHOL.BLOB_HOL_TXT);
        if (BPCRTHOL.FUNC == 'A') {
            B010_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTHOL.FUNC == 'U') {
            B020_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTHOL.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTHOL.FUNC == 'B') {
            B040_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTHOL.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTHOL.FUNC == 'R') {
            B060_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTHOL.FUNC == 'I') {
            B070_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTHOL.FUNC == 'T') {
            B080_BROWSE_REC_BY_TS_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCRTHOL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTHOL, BPRTHLL);
        CEP.TRC(SCCGWA, BPRTHOL.BLOB_HOL_TXT);
    }
    public void B010_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTTHOL();
        if (pgmRtn) return;
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTTHOL();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTHOL();
        if (pgmRtn) return;
    }
    public void B040_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRTHOL.OPT == 'S') {
            T000_STARTBR_BPTTHOL();
            if (pgmRtn) return;
        } else if (BPCRTHOL.OPT == 'N') {
            T000_READNEXT_BPTTHOL();
            if (pgmRtn) return;
        } else if (BPCRTHOL.OPT == 'E') {
            T000_ENDBR_BPTTHOL();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCRTHOL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTTHOL();
        if (pgmRtn) return;
    }
    public void B060_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTHOL_UPD();
        if (pgmRtn) return;
    }
    public void B070_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T070_BROWSE_DATE_PROCESS();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTTHOL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READ DATA START");
        BPTTHOL_RD = new DBParm();
        BPTTHOL_RD.TableName = "BPTTHOL";
        BPTTHOL_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRTHOL, BPTTHOL_RD);
        CEP.TRC(SCCGWA, "READ DATA END");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTHOL.RETURN_INFO = 'F';
            BPCRTHOL.RC.RC_MMO = "BP";
            BPCRTHOL.RC.RC_CODE = 0;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTHOL.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCRTHOL.RC);
        } else {
        }
        CEP.TRC(SCCGWA, "END READ");
    }
    public void T000_DELETE_BPTTHOL() throws IOException,SQLException,Exception {
        BPTTHOL_RD = new DBParm();
        BPTTHOL_RD.TableName = "BPTTHOL";
        IBS.DELETE(SCCGWA, BPRTHOL, BPTTHOL_RD);
    }
    public void T000_STARTBR_BPTTHOL() throws IOException,SQLException,Exception {
        if (BPRTHOL.KEY.CAL_CD.trim().length() > 0 
            && BPRTHOL.KEY.EFF_DATE != 0) {
            BPTTHOL_BR.rp = new DBParm();
            BPTTHOL_BR.rp.TableName = "BPTTHOL";
            BPTTHOL_BR.rp.where = "CAL_CD = :BPRTHOL.KEY.CAL_CD "
                + "AND EFF_DATE >= :BPRTHOL.KEY.EFF_DATE";
            BPTTHOL_BR.rp.errhdl = true;
            BPTTHOL_BR.rp.order = "CAL_CD";
            IBS.STARTBR(SCCGWA, BPRTHOL, this, BPTTHOL_BR);
        }
        if (BPRTHOL.KEY.CAL_CD.trim().length() > 0 
            && BPRTHOL.KEY.EFF_DATE == 0) {
            BPTTHOL_BR.rp = new DBParm();
            BPTTHOL_BR.rp.TableName = "BPTTHOL";
            BPTTHOL_BR.rp.where = "CAL_CD = :BPRTHOL.KEY.CAL_CD";
            BPTTHOL_BR.rp.errhdl = true;
            BPTTHOL_BR.rp.order = "CAL_CD";
            IBS.STARTBR(SCCGWA, BPRTHOL, this, BPTTHOL_BR);
        }
        if (BPRTHOL.KEY.CAL_CD.trim().length() == 0 
            && BPRTHOL.KEY.EFF_DATE != 0) {
            BPTTHOL_BR.rp = new DBParm();
            BPTTHOL_BR.rp.TableName = "BPTTHOL";
            BPTTHOL_BR.rp.where = "EFF_DATE >= :BPRTHOL.KEY.EFF_DATE";
            BPTTHOL_BR.rp.errhdl = true;
            BPTTHOL_BR.rp.order = "CAL_CD";
            IBS.STARTBR(SCCGWA, BPRTHOL, this, BPTTHOL_BR);
        }
        if (BPRTHOL.KEY.CAL_CD.trim().length() == 0 
            && BPRTHOL.KEY.EFF_DATE == 0) {
            BPTTHOL_BR.rp = new DBParm();
            BPTTHOL_BR.rp.TableName = "BPTTHOL";
            BPTTHOL_BR.rp.errhdl = true;
            BPTTHOL_BR.rp.order = "CAL_CD";
            IBS.STARTBR(SCCGWA, BPRTHOL, BPTTHOL_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTHOL.RETURN_INFO = 'F';
            BPCRTHOL.RC.RC_MMO = "BP";
            BPCRTHOL.RC.RC_CODE = 0;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTHOL.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_THOL;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
        }
    }
    public void T000_READNEXT_BPTTHOL() throws IOException,SQLException,Exception {
        BPTTHOL_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRTHOL, this, BPTTHOL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTHOL.RETURN_INFO = 'F';
            BPCRTHOL.RC.RC_MMO = "BP";
            BPCRTHOL.RC.RC_CODE = 0;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTHOL.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_THOL;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
        }
    }
    public void T000_ENDBR_BPTTHOL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTHOL_BR);
    }
    public void T000_WRITE_BPTTHOL() throws IOException,SQLException,Exception {
        BPTTHOL_RD = new DBParm();
        BPTTHOL_RD.TableName = "BPTTHOL";
        BPTTHOL_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRTHOL, BPTTHOL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTHOL.RETURN_INFO = 'F';
            BPCRTHOL.RC.RC_MMO = "BP";
            BPCRTHOL.RC.RC_CODE = 0;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRTHOL.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_EXIST, BPCRTHOL.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_THOL;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
        }
    }
    public void T000_READ_BPTTHOL_UPD() throws IOException,SQLException,Exception {
        BPTTHOL_RD = new DBParm();
        BPTTHOL_RD.TableName = "BPTTHOL";
        BPTTHOL_RD.upd = true;
        BPTTHOL_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRTHOL, BPTTHOL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTHOL.RETURN_INFO = 'F';
            BPCRTHOL.RC.RC_CODE = 0;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTHOL.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCRTHOL.RC);
        } else {
        }
    }
    public void T070_BROWSE_DATE_PROCESS() throws IOException,SQLException,Exception {
        BPTTHOL_RD = new DBParm();
        BPTTHOL_RD.TableName = "BPTTHOL";
        BPTTHOL_RD.where = "EFF_DATE <= :BPRTHOL.KEY.EFF_DATE "
            + "AND CAL_CD = :BPRTHOL.KEY.CAL_CD";
        BPTTHOL_RD.fst = true;
        BPTTHOL_RD.errhdl = true;
        BPTTHOL_RD.order = "EFF_DATE DESC";
        IBS.READ(SCCGWA, BPRTHOL, this, BPTTHOL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTHOL.RETURN_INFO = 'F';
            BPCRTHOL.RC.RC_MMO = "BP";
            BPCRTHOL.RC.RC_CODE = 0;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTHOL.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_THOL;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
        }
    }
    public void B080_BROWSE_REC_BY_TS_PROC() throws IOException,SQLException,Exception {
        BPTTHOL_RD = new DBParm();
        BPTTHOL_RD.TableName = "BPTTHOL";
        BPTTHOL_RD.where = "CAL_CD = :BPRTHOL.KEY.CAL_CD";
        BPTTHOL_RD.fst = true;
        BPTTHOL_RD.errhdl = true;
        BPTTHOL_RD.order = "TS DESC";
        IBS.READ(SCCGWA, BPRTHOL, this, BPTTHOL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTHOL.RETURN_INFO = 'F';
            BPCRTHOL.RC.RC_MMO = "BP";
            BPCRTHOL.RC.RC_CODE = 0;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTHOL.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_THOL;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
        }
    }
    public void T000_REWRITE_BPTTHOL() throws IOException,SQLException,Exception {
        BPTTHOL_RD = new DBParm();
        BPTTHOL_RD.TableName = "BPTTHOL";
        IBS.REWRITE(SCCGWA, BPRTHOL, BPTTHOL_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTHOL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTHOL = ");
            CEP.TRC(SCCGWA, BPCRTHOL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
