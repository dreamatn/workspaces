package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRAMO {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTFAMO_RD;
    brParm BPTFAMO_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRFAMO";
    String K_TBL_FARM = "BPTFAMO ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_FARM_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRFAMO BPRFAMO = new BPRFAMO();
    SCCGWA SCCGWA;
    BPCRFAMO BPCRFAMO;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRFAMO BPRFAML;
    public void MP(SCCGWA SCCGWA, BPCRFAMO BPCRFAMO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRFAMO = BPCRFAMO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRAMO return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRFAML = (BPRFAMO) BPCRFAMO.INFO.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRFAMO);
        BPCRFAMO.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCRFAMO.RC.RC_CODE = 0;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRFAML);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRFAML, BPRFAMO);
        CEP.TRC(SCCGWA, BPRFAMO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRFAMO.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFAMO.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFAMO.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFAMO.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFAMO.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFAMO.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRFAMO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRFAMO);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRFAMO, BPRFAML);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTFAMO();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTFAMO_UPD();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTFAMO();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTFAMO();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_RECORD();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCRFAMO.INFO.OPT == 'S') {
            T000_STARTBR_BPTFAMO();
            if (pgmRtn) return;
        } else if (BPCRFAMO.INFO.OPT == 'N') {
            T000_READNEXT_BPTFAMO();
            if (pgmRtn) return;
        } else if (BPCRFAMO.INFO.OPT == 'E') {
            T000_ENDBR_BPTFAMO();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRFAMO.RC);
        }
    }
    public void T000_DELETE_RECORD() throws IOException,SQLException,Exception {
        BPTFAMO_RD = new DBParm();
        BPTFAMO_RD.TableName = "BPTFAMO";
        IBS.DELETE(SCCGWA, BPRFAMO, BPTFAMO_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFAMO.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFAMO.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_READ_BPTFAMO() throws IOException,SQLException,Exception {
        BPTFAMO_RD = new DBParm();
        BPTFAMO_RD.TableName = "BPTFAMO";
        IBS.READ(SCCGWA, BPRFAMO, BPTFAMO_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFAMO.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFAMO.RETURN_INFO = 'D';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFAMO.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTFAMO() throws IOException,SQLException,Exception {
        BPTFAMO_BR.rp = new DBParm();
        BPTFAMO_BR.rp.TableName = "BPTFAMO";
        IBS.STARTBR(SCCGWA, BPRFAMO, BPTFAMO_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFAMO.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFAMO.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTFAMO() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFAMO, this, BPTFAMO_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFAMO.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFAMO.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTFAMO() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFAMO_BR);
    }
    public void T000_WRITE_BPTFAMO() throws IOException,SQLException,Exception {
        BPTFAMO_RD = new DBParm();
        BPTFAMO_RD.TableName = "BPTFAMO";
        BPTFAMO_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRFAMO, BPTFAMO_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFAMO.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFAMO.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_FARM;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTFAMO_UPD() throws IOException,SQLException,Exception {
        BPTFAMO_RD = new DBParm();
        BPTFAMO_RD.TableName = "BPTFAMO";
        BPTFAMO_RD.upd = true;
        IBS.READ(SCCGWA, BPRFAMO, BPTFAMO_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFAMO.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFAMO.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTFAMO() throws IOException,SQLException,Exception {
        BPTFAMO_RD = new DBParm();
        BPTFAMO_RD.TableName = "BPTFAMO";
        IBS.REWRITE(SCCGWA, BPRFAMO, BPTFAMO_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFAMO.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFAMO.RETURN_INFO = 'D';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRFAMO.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRFAMO = ");
            CEP.TRC(SCCGWA, BPCRFAMO);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
