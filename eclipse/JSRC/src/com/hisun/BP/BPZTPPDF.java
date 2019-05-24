package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTPPDF {
    DBParm BPTAPPD_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTPPDF";
    String K_TBL_APPD = "BPTAPPD ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_APPD_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRAPPD BPRAPPD = new BPRAPPD();
    SCCGWA SCCGWA;
    BPRAPPD BPRAPPD1;
    BPCTPPDF BPCTPPDF;
    public void MP(SCCGWA SCCGWA, BPCTPPDF BPCTPPDF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTPPDF = BPCTPPDF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTPPDF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRAPPD1 = (BPRAPPD) BPCTPPDF.POINTER;
        IBS.init(SCCGWA, BPCTPPDF.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRAPPD1);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRAPPD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTPPDF.INFO.FUNC == 'A') {
            B100_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTPPDF.INFO.FUNC == 'R') {
            B200_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTPPDF.INFO.FUNC == 'U') {
            B300_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTPPDF.INFO.FUNC == 'Q') {
            B400_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTPPDF.INFO.FUNC == 'D') {
            B500_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTPPDF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRAPPD, BPRAPPD1);
        CEP.TRC(SCCGWA, BPRAPPD);
    }
    public void B100_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTAPPD();
        if (pgmRtn) return;
    }
    public void B200_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTAPPD_UPD();
        if (pgmRtn) return;
    }
    public void B300_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTAPPD();
        if (pgmRtn) return;
    }
    public void B400_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READ");
        T000_READ_BPTAPPD();
        if (pgmRtn) return;
    }
    public void B500_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTAPPD();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTAPPD() throws IOException,SQLException,Exception {
        BPTAPPD_RD = new DBParm();
        BPTAPPD_RD.TableName = "BPTAPPD";
        IBS.READ(SCCGWA, BPRAPPD, BPTAPPD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTPPDF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTPPDF.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_APPD_NOTFND, BPCTPPDF.RC);
        } else {
        }
        CEP.TRC(SCCGWA, BPCTPPDF.RC);
    }
    public void T000_DELETE_BPTAPPD() throws IOException,SQLException,Exception {
        BPTAPPD_RD = new DBParm();
        BPTAPPD_RD.TableName = "BPTAPPD";
        IBS.DELETE(SCCGWA, BPRAPPD, BPTAPPD_RD);
    }
    public void T000_WRITE_BPTAPPD() throws IOException,SQLException,Exception {
        BPTAPPD_RD = new DBParm();
        BPTAPPD_RD.TableName = "BPTAPPD";
        BPTAPPD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRAPPD, BPTAPPD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTPPDF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTPPDF.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_APPD;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTAPPD_UPD() throws IOException,SQLException,Exception {
        BPTAPPD_RD = new DBParm();
        BPTAPPD_RD.TableName = "BPTAPPD";
        BPTAPPD_RD.upd = true;
        IBS.READ(SCCGWA, BPRAPPD, BPTAPPD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTPPDF.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTPPDF.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_APPD_NOTFND, BPCTPPDF.RC);
        } else {
        }
    }
    public void T000_REWRITE_BPTAPPD() throws IOException,SQLException,Exception {
        BPTAPPD_RD = new DBParm();
        BPTAPPD_RD.TableName = "BPTAPPD";
        IBS.REWRITE(SCCGWA, BPRAPPD, BPTAPPD_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTPPDF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTPPDF = ");
            CEP.TRC(SCCGWA, BPCTPPDF);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
