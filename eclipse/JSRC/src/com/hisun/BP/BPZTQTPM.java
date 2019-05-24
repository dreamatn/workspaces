package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTQTPM {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTSQTPH_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTQTPM";
    String K_TBL_QTPH = "BPTSQTPH";
    int WS_REC_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRSQTPH BPRSQTPH = new BPRSQTPH();
    SCCGWA SCCGWA;
    BPCTQTPM BPCTQTPM;
    BPRSQTPH BPRSQTQH;
    public void MP(SCCGWA SCCGWA, BPCTQTPM BPCTQTPM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTQTPM = BPCTQTPM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTQTPM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRSQTQH = (BPRSQTPH) BPCTQTPM.INFO.POINTER;
        IBS.init(SCCGWA, BPRSQTPH);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRSQTQH);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRSQTQH, BPRSQTPH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTQTPM.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTQTPM.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTQTPM.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTQTPM.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTQTPM.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTQTPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRSQTPH);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRSQTPH, BPRSQTQH);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTSQTPH();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTSQTPH_UPD();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTSQTPH();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTSQTPH();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTSQTPH();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTSQTPH() throws IOException,SQLException,Exception {
        BPTSQTPH_RD = new DBParm();
        BPTSQTPH_RD.TableName = "BPTSQTPH";
        IBS.READ(SCCGWA, BPRSQTPH, BPTSQTPH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTQTPM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTQTPM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTSQTPH() throws IOException,SQLException,Exception {
        BPTSQTPH_RD = new DBParm();
        BPTSQTPH_RD.TableName = "BPTSQTPH";
        BPTSQTPH_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRSQTPH, BPTSQTPH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTQTPM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTQTPM.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_QTPH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTSQTPH_UPD() throws IOException,SQLException,Exception {
        BPTSQTPH_RD = new DBParm();
        BPTSQTPH_RD.TableName = "BPTSQTPH";
        BPTSQTPH_RD.upd = true;
        IBS.READ(SCCGWA, BPRSQTPH, BPTSQTPH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTQTPM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTQTPM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTSQTPH() throws IOException,SQLException,Exception {
        BPTSQTPH_RD = new DBParm();
        BPTSQTPH_RD.TableName = "BPTSQTPH";
        IBS.REWRITE(SCCGWA, BPRSQTPH, BPTSQTPH_RD);
    }
    public void T000_DELETE_BPTSQTPH() throws IOException,SQLException,Exception {
        BPTSQTPH_RD = new DBParm();
        BPTSQTPH_RD.TableName = "BPTSQTPH";
        IBS.DELETE(SCCGWA, BPRSQTPH, BPTSQTPH_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTQTPM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTQTPM = ");
            CEP.TRC(SCCGWA, BPCTQTPM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
