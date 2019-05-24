package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZROFCH {
    DBParm BPTOFCH_RD;
    brParm BPTOFCH_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZROFCH";
    String K_TBL_FARM = "BPTOFCH ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_FARM_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPROFCH BPROFCH = new BPROFCH();
    SCCGWA SCCGWA;
    BPCROFCH BPCROFCH;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPROFCH BPROFCHL;
    public void MP(SCCGWA SCCGWA, BPCROFCH BPCROFCH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCROFCH = BPCROFCH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZROFCH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPROFCHL = (BPROFCH) BPCROFCH.INFO.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPROFCH);
        BPCROFCH.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCROFCH.RC.RC_CODE = 0;
        IBS.CLONE(SCCGWA, BPROFCHL, BPROFCH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCROFCH.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCROFCH.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCROFCH.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCROFCH.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCROFCH.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCROFCH.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCROFCH.INFO.FUNC == 'N') {
            B070_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCROFCH.INFO.FUNC == 'E') {
            B080_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCROFCH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPROFCH, BPROFCHL);
        CEP.TRC(SCCGWA, BPROFCHL);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTOFCH();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTOFCH_UPD();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTOFCH();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTOFCH();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_RECORD();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCROFCH.INFO.OPT == '0') {
            T000_STARTBR_BPTOFCH_0();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCROFCH.RC);
        }
    }
    public void B070_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTOFCH();
        if (pgmRtn) return;
    }
    public void B080_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTOFCH();
        if (pgmRtn) return;
    }
    public void T000_DELETE_RECORD() throws IOException,SQLException,Exception {
        BPTOFCH_RD = new DBParm();
        BPTOFCH_RD.TableName = "BPTOFCH";
        IBS.DELETE(SCCGWA, BPROFCH, BPTOFCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCROFCH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCROFCH.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_READ_BPTOFCH() throws IOException,SQLException,Exception {
        BPTOFCH_RD = new DBParm();
        BPTOFCH_RD.TableName = "BPTOFCH";
        IBS.READ(SCCGWA, BPROFCH, BPTOFCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCROFCH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCROFCH.RETURN_INFO = 'D';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCROFCH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTOFCH_0() throws IOException,SQLException,Exception {
        BPTOFCH_BR.rp = new DBParm();
        BPTOFCH_BR.rp.TableName = "BPTOFCH";
        BPTOFCH_BR.rp.upd = true;
        BPTOFCH_BR.rp.order = "JRNNO";
        IBS.STARTBR(SCCGWA, BPROFCH, BPTOFCH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCROFCH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCROFCH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTOFCH() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPROFCH, this, BPTOFCH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCROFCH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCROFCH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTOFCH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTOFCH_BR);
    }
    public void T000_WRITE_BPTOFCH() throws IOException,SQLException,Exception {
        BPTOFCH_RD = new DBParm();
        BPTOFCH_RD.TableName = "BPTOFCH";
        BPTOFCH_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPROFCH, BPTOFCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCROFCH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCROFCH.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_FARM;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTOFCH_UPD() throws IOException,SQLException,Exception {
        BPTOFCH_RD = new DBParm();
        BPTOFCH_RD.TableName = "BPTOFCH";
        BPTOFCH_RD.upd = true;
        IBS.READ(SCCGWA, BPROFCH, BPTOFCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCROFCH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCROFCH.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTOFCH() throws IOException,SQLException,Exception {
        BPTOFCH_RD = new DBParm();
        BPTOFCH_RD.TableName = "BPTOFCH";
        IBS.REWRITE(SCCGWA, BPROFCH, BPTOFCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCROFCH.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCROFCH.RETURN_INFO = 'D';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCROFCH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCROFCH = ");
            CEP.TRC(SCCGWA, BPCROFCH);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
