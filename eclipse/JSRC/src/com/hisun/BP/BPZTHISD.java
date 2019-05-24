package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTHISD {
    DBParm BPTNHISD_RD;
    brParm BPTNHISD_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTHISD";
    String K_TBL_NHISD = "BPTNHISD";
    char WS_TEMP_FLG = ' ';
    char WS_TBL_BANK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRNHISD BPRNHISD = new BPRNHISD();
    SCCGWA SCCGWA;
    BPCTHISD BPCTHISD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRNHISD BPRNHISE;
    public void MP(SCCGWA SCCGWA, BPCTHISD BPCTHISD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTHISD = BPCTHISD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTHISD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRNHISE = (BPRNHISD) BPCTHISD.PTR;
        IBS.init(SCCGWA, BPRNHISD);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRNHISE);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRNHISD);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCTHISD.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTHISD.INFO.FUNC == '1') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTHISD.INFO.FUNC == '2') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, BPRNHISD, BPRNHISE);
        } else if (BPCTHISD.INFO.FUNC == '3') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTHISD.INFO.FUNC == '4') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, BPRNHISD, BPRNHISE);
        } else if (BPCTHISD.INFO.FUNC == '5') {
            B050_CREAT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTHISD.INFO.FUNC == '6') {
            B060_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCTHISD.INFO.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCTHISD.INFO.JRNNO == 0 
            || BPCTHISD.INFO.AC_DT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTHISD.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            BPRNHISD.KEY.JRNNO = BPCTHISD.INFO.JRNNO;
            BPRNHISD.KEY.JRN_SEQ = BPCTHISD.INFO.JRN_SEQ;
            BPRNHISD.KEY.AC_DT = BPCTHISD.INFO.AC_DT;
        }
        T000_STARTBR_BPTNHISD();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTNHISD();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTNHISD();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTNHISD();
        if (pgmRtn) return;
    }
    public void B050_CREAT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTNHISD();
        if (pgmRtn) return;
    }
    public void B060_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTNHISD();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTNHISD() throws IOException,SQLException,Exception {
        BPTNHISD_RD = new DBParm();
        BPTNHISD_RD.TableName = "BPTNHISD";
        IBS.READ(SCCGWA, BPRNHISD, BPTNHISD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTHISD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTHISD.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_NHISD;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BPTNHISD() throws IOException,SQLException,Exception {
        BPTNHISD_RD = new DBParm();
        BPTNHISD_RD.TableName = "BPTNHISD";
        BPTNHISD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRNHISD, BPTNHISD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTHISD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTHISD.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_NHISD;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_BPTNHISD() throws IOException,SQLException,Exception {
    }
    public void T000_STARTBR_BPTNHISD() throws IOException,SQLException,Exception {
        BPTNHISD_BR.rp = new DBParm();
        BPTNHISD_BR.rp.TableName = "BPTNHISD";
        BPTNHISD_BR.rp.where = "JRNNO = :BPRNHISD.KEY.JRNNO "
            + "AND JRN_SEQ = :BPRNHISD.KEY.JRN_SEQ "
            + "AND AC_DT = :BPRNHISD.KEY.AC_DT";
        IBS.STARTBR(SCCGWA, BPRNHISD, this, BPTNHISD_BR);
    }
    public void T000_READNEXT_BPTNHISD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRNHISD, this, BPTNHISD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTHISD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTHISD.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_NHISD;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTNHISD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTNHISD_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTHISD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTHISD = ");
            CEP.TRC(SCCGWA, BPCTHISD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
