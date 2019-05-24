package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRACCR {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTPACCR_RD;
    brParm BPTPACCR_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRACCR";
    String K_TBL_FARM = "BPTPACCR";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_FARM_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRPACCR BPRPACCR = new BPRPACCR();
    SCCGWA SCCGWA;
    BPCRACCR BPCRACCR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRPACCR BPRPACCL;
    public void MP(SCCGWA SCCGWA, BPCRACCR BPCRACCR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRACCR = BPCRACCR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRACCR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRPACCL = (BPRPACCR) BPCRACCR.INFO.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRPACCR);
        BPCRACCR.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCRACCR.RC.RC_CODE = 0;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPACCL);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRPACCL, BPRPACCR);
        CEP.TRC(SCCGWA, BPRPACCR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRACCR.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRACCR.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRACCR.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRACCR.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRACCR.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRACCR.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRACCR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPACCR);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRPACCR, BPRPACCL);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTPACCR();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPACCR_UPD();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPACCR();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTPACCR();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_RECORD();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRACCR.INFO.OPT == 'S') {
            T000_STARTBR_BPTPACCR();
            if (pgmRtn) return;
        } else if (BPCRACCR.INFO.OPT == 'N') {
            T000_READNEXT_BPTPACCR();
            if (pgmRtn) return;
        } else if (BPCRACCR.INFO.OPT == 'E') {
            T000_ENDBR_BPTPACCR();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRACCR.RC);
        }
    }
    public void T000_DELETE_RECORD() throws IOException,SQLException,Exception {
        BPTPACCR_RD = new DBParm();
        BPTPACCR_RD.TableName = "BPTPACCR";
        IBS.DELETE(SCCGWA, BPRPACCR, BPTPACCR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRACCR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRACCR.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_READ_BPTPACCR() throws IOException,SQLException,Exception {
        BPTPACCR_RD = new DBParm();
        BPTPACCR_RD.TableName = "BPTPACCR";
        IBS.READ(SCCGWA, BPRPACCR, BPTPACCR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRACCR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRACCR.RETURN_INFO = 'D';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRACCR.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTPACCR() throws IOException,SQLException,Exception {
        BPTPACCR_BR.rp = new DBParm();
        BPTPACCR_BR.rp.TableName = "BPTPACCR";
        IBS.STARTBR(SCCGWA, BPRPACCR, BPTPACCR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRACCR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRACCR.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTPACCR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRPACCR, this, BPTPACCR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRACCR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRACCR.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTPACCR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTPACCR_BR);
    }
    public void T000_WRITE_BPTPACCR() throws IOException,SQLException,Exception {
        BPTPACCR_RD = new DBParm();
        BPTPACCR_RD.TableName = "BPTPACCR";
        BPTPACCR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRPACCR, BPTPACCR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRACCR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRACCR.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_FARM;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTPACCR_UPD() throws IOException,SQLException,Exception {
        BPTPACCR_RD = new DBParm();
        BPTPACCR_RD.TableName = "BPTPACCR";
        BPTPACCR_RD.upd = true;
        IBS.READ(SCCGWA, BPRPACCR, BPTPACCR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRACCR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRACCR.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTPACCR() throws IOException,SQLException,Exception {
        BPTPACCR_RD = new DBParm();
        BPTPACCR_RD.TableName = "BPTPACCR";
        IBS.REWRITE(SCCGWA, BPRPACCR, BPTPACCR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRACCR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRACCR.RETURN_INFO = 'D';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRACCR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRACCR = ");
            CEP.TRC(SCCGWA, BPCRACCR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
