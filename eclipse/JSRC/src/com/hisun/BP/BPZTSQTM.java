package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTSQTM {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTSQTP_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTSQTM";
    String K_TBL_EXRD = "BPTSQTP ";
    int WS_REC_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRSQTP BPRSQTP = new BPRSQTP();
    SCCGWA SCCGWA;
    BPCTSQTM BPCTSQTM;
    BPRSQTP BPRSQTP1;
    public void MP(SCCGWA SCCGWA, BPCTSQTM BPCTSQTM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTSQTM = BPCTSQTM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTSQTM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRSQTP1 = (BPRSQTP) BPCTSQTM.INFO.POINTER;
        IBS.init(SCCGWA, BPRSQTP);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRSQTP1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRSQTP1, BPRSQTP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTSQTM.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTSQTM.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTSQTM.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTSQTM.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTSQTM.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTSQTM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRSQTP);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRSQTP, BPRSQTP1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTSQTP();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTSQTP_UPD();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTSQTP();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTSQTP();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTSQTP();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTSQTP() throws IOException,SQLException,Exception {
        BPTSQTP_RD = new DBParm();
        BPTSQTP_RD.TableName = "BPTSQTP";
        IBS.READ(SCCGWA, BPRSQTP, BPTSQTP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTSQTM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTSQTM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTSQTP() throws IOException,SQLException,Exception {
        BPTSQTP_RD = new DBParm();
        BPTSQTP_RD.TableName = "BPTSQTP";
        BPTSQTP_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRSQTP, BPTSQTP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTSQTM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTSQTM.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_EXRD;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTSQTP_UPD() throws IOException,SQLException,Exception {
        BPTSQTP_RD = new DBParm();
        BPTSQTP_RD.TableName = "BPTSQTP";
        BPTSQTP_RD.upd = true;
        IBS.READ(SCCGWA, BPRSQTP, BPTSQTP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTSQTM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTSQTM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTSQTP() throws IOException,SQLException,Exception {
        BPTSQTP_RD = new DBParm();
        BPTSQTP_RD.TableName = "BPTSQTP";
        IBS.REWRITE(SCCGWA, BPRSQTP, BPTSQTP_RD);
    }
    public void T000_DELETE_BPTSQTP() throws IOException,SQLException,Exception {
        BPTSQTP_RD = new DBParm();
        BPTSQTP_RD.TableName = "BPTSQTP";
        IBS.DELETE(SCCGWA, BPRSQTP, BPTSQTP_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTSQTM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTSQTM = ");
            CEP.TRC(SCCGWA, BPCTSQTM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
