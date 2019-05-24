package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRFEAG {
    DBParm BPTFEAG_RD;
    brParm BPTFEAG_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRFEAG";
    String K_TBL_FARM = "BPTFEAG";
    int K_EFF_STRT = 00000000;
    int K_EFF_ENDT = 99999999;
    String WS_TEMP_RECORD = " ";
    int WS_EFF_STRT = 0;
    int WS_EFF_ENDT = 0;
    char WS_TBL_FARM_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRCMZR BPRCMZR = new BPRCMZR();
    BPRFEAG BPRFEAG = new BPRFEAG();
    SCCGWA SCCGWA;
    BPCRFEAG BPCRFEAG;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRFEAG BPRFEAG1;
    public void MP(SCCGWA SCCGWA, BPCRFEAG BPCRFEAG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRFEAG = BPCRFEAG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRFEAG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRFEAG1 = (BPRFEAG) BPCRFEAG.INFO.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRFEAG);
        BPCRFEAG.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCRFEAG.RC.RC_CODE = 0;
        IBS.CLONE(SCCGWA, BPRFEAG1, BPRFEAG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRFEAG.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFEAG.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFEAG.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFEAG.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFEAG.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFEAG.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFEAG.INFO.FUNC == 'S') {
            B060_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFEAG.INFO.FUNC == 'N') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRFEAG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRFEAG, BPRFEAG1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTFEAG();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTFEAG_UPD();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTFEAG();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTFEAG();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_RECORD();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRFEAG.INFO.OPT == 'S') {
            T000_STARTBR_BPTFEAG();
            if (pgmRtn) return;
        } else if (BPCRFEAG.INFO.OPT == 'B') {
            T000_STARTBR_BPTFEAG_1();
            if (pgmRtn) return;
        } else if (BPCRFEAG.INFO.OPT == 'N') {
            T000_READNEXT_BPTFEAG();
            if (pgmRtn) return;
        } else if (BPCRFEAG.INFO.OPT == 'E') {
            T000_ENDBR_BPTFEAG();
            if (pgmRtn) return;
        } else if (BPCRFEAG.INFO.FUNC == 'N') {
            T000_READNEXT_BPTFEAG();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRFEAG.RC);
        }
    }
    public void B060_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRFEAG.INFO.FUNC == 'S') {
            T000_STARTBR_BPTFEAG();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRFEAG.RC);
        }
    }
    public void T000_DELETE_RECORD() throws IOException,SQLException,Exception {
        BPTFEAG_RD = new DBParm();
        BPTFEAG_RD.TableName = "BPTFEAG";
        IBS.DELETE(SCCGWA, BPRFEAG, BPTFEAG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFEAG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFEAG.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_READ_BPTFEAG() throws IOException,SQLException,Exception {
        BPTFEAG_RD = new DBParm();
        BPTFEAG_RD.TableName = "BPTFEAG";
        IBS.READ(SCCGWA, BPRFEAG, BPTFEAG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFEAG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFEAG.RETURN_INFO = 'D';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFEAG.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFEAG() throws IOException,SQLException,Exception {
        BPTFEAG_BR.rp = new DBParm();
        BPTFEAG_BR.rp.TableName = "BPTFEAG";
        BPTFEAG_BR.rp.where = "CLT_CI_NO = :BPRFEAG.KEY.CLT_CI_NO";
        BPTFEAG_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRFEAG, this, BPTFEAG_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFEAG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFEAG.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFEAG_1() throws IOException,SQLException,Exception {
        BPTFEAG_BR.rp = new DBParm();
        BPTFEAG_BR.rp.TableName = "BPTFEAG";
        BPTFEAG_BR.rp.where = "CLT_CI_NO = :BPRFEAG.KEY.CLT_CI_NO";
        BPTFEAG_BR.rp.order = "CLT_CI_NO";
        IBS.STARTBR(SCCGWA, BPRFEAG, this, BPTFEAG_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFEAG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFEAG.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTFEAG() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFEAG, this, BPTFEAG_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFEAG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFEAG.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTFEAG() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFEAG_BR);
    }
    public void T000_WRITE_BPTFEAG() throws IOException,SQLException,Exception {
        BPTFEAG_RD = new DBParm();
        BPTFEAG_RD.TableName = "BPTFEAG";
        BPTFEAG_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRFEAG, BPTFEAG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFEAG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFEAG.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_FARM;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTFEAG_UPD() throws IOException,SQLException,Exception {
        BPTFEAG_RD = new DBParm();
        BPTFEAG_RD.TableName = "BPTFEAG";
        BPTFEAG_RD.upd = true;
        IBS.READ(SCCGWA, BPRFEAG, BPTFEAG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFEAG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFEAG.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTFEAG() throws IOException,SQLException,Exception {
        BPTFEAG_RD = new DBParm();
        BPTFEAG_RD.TableName = "BPTFEAG";
        IBS.REWRITE(SCCGWA, BPRFEAG, BPTFEAG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFEAG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFEAG.RETURN_INFO = 'D';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRFEAG.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRFEAG = ");
            CEP.TRC(SCCGWA, BPCRFEAG);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
