package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRBCUS {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTBCUS_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRBCUS";
    String K_TBL_BCUS = "BPTBCUS ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRBCUS BPRBCUS = new BPRBCUS();
    SCCGWA SCCGWA;
    BPCRBCUS BPCRBCUS;
    BPRBCUS BPRBCUS1;
    public void MP(SCCGWA SCCGWA, BPCRBCUS BPCRBCUS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRBCUS = BPCRBCUS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRBCUS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRBCUS1 = (BPRBCUS) BPCRBCUS.INFO.POINTER;
        IBS.init(SCCGWA, BPRBCUS);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBCUS1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRBCUS1, BPRBCUS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRBCUS.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBCUS.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBCUS.INFO.FUNC == 'U') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBCUS.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBCUS.INFO.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRBCUS.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRBCUS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBCUS);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRBCUS, BPRBCUS1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTBCUS();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTBCUS_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTBCUS();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTBCUS();
        if (pgmRtn) return;
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTBCUS();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTBCUS() throws IOException,SQLException,Exception {
        BPTBCUS_RD = new DBParm();
        BPTBCUS_RD.TableName = "BPTBCUS";
        IBS.READ(SCCGWA, BPRBCUS, BPTBCUS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBCUS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBCUS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTBCUS() throws IOException,SQLException,Exception {
        BPTBCUS_RD = new DBParm();
        BPTBCUS_RD.TableName = "BPTBCUS";
        BPTBCUS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRBCUS, BPTBCUS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBCUS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRBCUS.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BCUS;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTBCUS_UPD() throws IOException,SQLException,Exception {
        BPTBCUS_RD = new DBParm();
        BPTBCUS_RD.TableName = "BPTBCUS";
        BPTBCUS_RD.upd = true;
        IBS.READ(SCCGWA, BPRBCUS, BPTBCUS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBCUS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBCUS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_DELETE_BPTBCUS() throws IOException,SQLException,Exception {
        BPTBCUS_RD = new DBParm();
        BPTBCUS_RD.TableName = "BPTBCUS";
        IBS.DELETE(SCCGWA, BPRBCUS, BPTBCUS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBCUS.RETURN_INFO = 'F';
        } else {
        }
    }
    public void T000_REWRITE_BPTBCUS() throws IOException,SQLException,Exception {
        BPTBCUS_RD = new DBParm();
        BPTBCUS_RD.TableName = "BPTBCUS";
        IBS.REWRITE(SCCGWA, BPRBCUS, BPTBCUS_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRBCUS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRBCUS = ");
            CEP.TRC(SCCGWA, BPCRBCUS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
