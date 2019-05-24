package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRBHIS {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTBHIS_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRBHIS";
    String K_TBL_BHIS = "BPTBHIS ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRBHIS BPRBHIS = new BPRBHIS();
    SCCGWA SCCGWA;
    BPCRBHIS BPCRBHIS;
    BPRBHIS BPRBHIS1;
    public void MP(SCCGWA SCCGWA, BPCRBHIS BPCRBHIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRBHIS = BPCRBHIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRBHIS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRBHIS1 = (BPRBHIS) BPCRBHIS.INFO.POINTER;
        IBS.init(SCCGWA, BPRBHIS);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBHIS1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRBHIS1, BPRBHIS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRBHIS.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBHIS.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBHIS.INFO.FUNC == 'U') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBHIS.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRBHIS.INFO.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRBHIS.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRBHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBHIS);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRBHIS, BPRBHIS1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTBHIS();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTBHIS_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTBHIS();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTBHIS();
        if (pgmRtn) return;
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTBHIS();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTBHIS() throws IOException,SQLException,Exception {
        BPTBHIS_RD = new DBParm();
        BPTBHIS_RD.TableName = "BPTBHIS";
        IBS.READ(SCCGWA, BPRBHIS, BPTBHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBHIS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBHIS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTBHIS() throws IOException,SQLException,Exception {
        BPTBHIS_RD = new DBParm();
        BPTBHIS_RD.TableName = "BPTBHIS";
        BPTBHIS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRBHIS, BPTBHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBHIS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRBHIS.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BHIS;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTBHIS_UPD() throws IOException,SQLException,Exception {
        BPTBHIS_RD = new DBParm();
        BPTBHIS_RD.TableName = "BPTBHIS";
        BPTBHIS_RD.upd = true;
        IBS.READ(SCCGWA, BPRBHIS, BPTBHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBHIS.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRBHIS.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_DELETE_BPTBHIS() throws IOException,SQLException,Exception {
        BPTBHIS_RD = new DBParm();
        BPTBHIS_RD.TableName = "BPTBHIS";
        IBS.DELETE(SCCGWA, BPRBHIS, BPTBHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRBHIS.RETURN_INFO = 'F';
        } else {
        }
    }
    public void T000_REWRITE_BPTBHIS() throws IOException,SQLException,Exception {
        BPTBHIS_RD = new DBParm();
        BPTBHIS_RD.TableName = "BPTBHIS";
        IBS.REWRITE(SCCGWA, BPRBHIS, BPTBHIS_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRBHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRBHIS = ");
            CEP.TRC(SCCGWA, BPCRBHIS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
