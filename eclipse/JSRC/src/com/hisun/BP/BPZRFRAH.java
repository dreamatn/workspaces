package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRFRAH {
    DBParm BPTFRAH_RD;
    brParm BPTFRAH_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRFRAH";
    String K_TBL_FARM = "BPTFRAH ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_FARM_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRFRAH BPRFRAH = new BPRFRAH();
    SCCGWA SCCGWA;
    BPCRFRAH BPCRFRAH;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRFRAH BPRFRAH1;
    public void MP(SCCGWA SCCGWA, BPCRFRAH BPCRFRAH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRFRAH = BPCRFRAH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRFRAH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRFRAH1 = (BPRFRAH) BPCRFRAH.INFO.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRFRAH);
        BPCRFRAH.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCRFRAH.RC.RC_CODE = 0;
        IBS.CLONE(SCCGWA, BPRFRAH1, BPRFRAH);
        CEP.TRC(SCCGWA, BPRFRAH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRFRAH.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFRAH.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFRAH.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFRAH.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFRAH.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFRAH.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRFRAH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRFRAH, BPRFRAH1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTFRAH();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTFRAH_UPD();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTFRAH();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTFRAH();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_RECORD();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRFRAH.INFO.OPT == 'S') {
            T000_STARTBR_BPTFRAH();
            if (pgmRtn) return;
        } else if (BPCRFRAH.INFO.OPT == 'N') {
            T000_READNEXT_BPTFRAH();
            if (pgmRtn) return;
        } else if (BPCRFRAH.INFO.OPT == 'E') {
            T000_ENDBR_BPTFRAH();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRFRAH.RC);
        }
    }
    public void T000_DELETE_RECORD() throws IOException,SQLException,Exception {
        BPTFRAH_RD = new DBParm();
        BPTFRAH_RD.TableName = "BPTFRAH";
        IBS.DELETE(SCCGWA, BPRFRAH, BPTFRAH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFRAH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFRAH.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_READ_BPTFRAH() throws IOException,SQLException,Exception {
        BPTFRAH_RD = new DBParm();
        BPTFRAH_RD.TableName = "BPTFRAH";
        IBS.READ(SCCGWA, BPRFRAH, BPTFRAH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFRAH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFRAH.RETURN_INFO = 'D';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFRAH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFRAH() throws IOException,SQLException,Exception {
        BPTFRAH_BR.rp = new DBParm();
        BPTFRAH_BR.rp.TableName = "BPTFRAH";
        BPTFRAH_BR.rp.where = "CTRT_NO = :BPRFRAH.KEY.CTRT_NO";
        BPTFRAH_BR.rp.order = "CTRT_NO,SUB_CTRT_NO,PART_CI_ATTR, PART_CI_NO,VALUE_DATE";
        IBS.STARTBR(SCCGWA, BPRFRAH, this, BPTFRAH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFRAH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFRAH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTFRAH() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFRAH, this, BPTFRAH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFRAH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFRAH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTFRAH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFRAH_BR);
    }
    public void T000_WRITE_BPTFRAH() throws IOException,SQLException,Exception {
        BPTFRAH_RD = new DBParm();
        BPTFRAH_RD.TableName = "BPTFRAH";
        BPTFRAH_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRFRAH, BPTFRAH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFRAH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFRAH.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_FARM;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTFRAH_UPD() throws IOException,SQLException,Exception {
        BPTFRAH_RD = new DBParm();
        BPTFRAH_RD.TableName = "BPTFRAH";
        BPTFRAH_RD.upd = true;
        IBS.READ(SCCGWA, BPRFRAH, BPTFRAH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFRAH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFRAH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTFRAH() throws IOException,SQLException,Exception {
        BPTFRAH_RD = new DBParm();
        BPTFRAH_RD.TableName = "BPTFRAH";
        IBS.REWRITE(SCCGWA, BPRFRAH, BPTFRAH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFRAH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFRAH.RETURN_INFO = 'D';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRFRAH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRFRAH = ");
            CEP.TRC(SCCGWA, BPCRFRAH);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
