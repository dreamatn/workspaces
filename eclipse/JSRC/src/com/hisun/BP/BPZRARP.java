package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRARP {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTARP_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRARP ";
    String K_TBL_ARP = "BPTARP  ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRARP BPRARP = new BPRARP();
    SCCGWA SCCGWA;
    BPCRARP BPCRARP;
    BPRARP BPRARP1;
    public void MP(SCCGWA SCCGWA, BPCRARP BPCRARP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRARP = BPCRARP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRARP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRARP1 = (BPRARP) BPCRARP.INFO.POINTER;
        IBS.init(SCCGWA, BPRARP);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRARP1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRARP1, BPRARP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRARP.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRARP.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRARP.INFO.FUNC == 'U') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRARP.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRARP.INFO.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRARP.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRARP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRARP);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRARP, BPRARP1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTARP();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTARP_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTARP();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTARP();
        if (pgmRtn) return;
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTARP();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTARP() throws IOException,SQLException,Exception {
        BPTARP_RD = new DBParm();
        BPTARP_RD.TableName = "BPTARP";
        IBS.READ(SCCGWA, BPRARP, BPTARP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRARP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRARP.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTARP() throws IOException,SQLException,Exception {
        BPTARP_RD = new DBParm();
        BPTARP_RD.TableName = "BPTARP";
        BPTARP_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRARP, BPTARP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRARP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRARP.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_ARP;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTARP_UPD() throws IOException,SQLException,Exception {
        BPTARP_RD = new DBParm();
        BPTARP_RD.TableName = "BPTARP";
        BPTARP_RD.upd = true;
        IBS.READ(SCCGWA, BPRARP, BPTARP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRARP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRARP.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTARP() throws IOException,SQLException,Exception {
        BPTARP_RD = new DBParm();
        BPTARP_RD.TableName = "BPTARP";
        IBS.REWRITE(SCCGWA, BPRARP, BPTARP_RD);
    }
    public void T000_DELETE_BPTARP() throws IOException,SQLException,Exception {
        BPTARP_RD = new DBParm();
        BPTARP_RD.TableName = "BPTARP";
        IBS.DELETE(SCCGWA, BPRARP, BPTARP_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRARP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRARP = ");
            CEP.TRC(SCCGWA, BPCRARP);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
