package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTFAVM {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTEXFAV_RD;
    boolean pgmRtn = false;
    String PGM_BPZTFAVB = "BPZTFAVB";
    String TBL_BPTEXFAV = "BPTEXFAV";
    int WS_REC_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPREXFAV BPREXFAV = new BPREXFAV();
    SCCGWA SCCGWA;
    BPCRFAVM BPCRFAVM;
    BPREXFAV BPREXFAA;
    public void MP(SCCGWA SCCGWA, BPCRFAVM BPCRFAVM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRFAVM = BPCRFAVM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTFAVM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPREXFAA = (BPREXFAV) BPCRFAVM.INFO.POINTER;
        IBS.init(SCCGWA, BPREXFAV);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPREXFAA);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPREXFAA, BPREXFAV);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRFAVM.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFAVM.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFAVM.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFAVM.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFAVM.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRFAVM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCRFAVM.INFO.FUNC == 'R' 
            || BPCRFAVM.INFO.FUNC == 'U' 
            || BPCRFAVM.INFO.FUNC == 'Q' 
            || BPCRFAVM.INFO.FUNC == 'C') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPREXFAV);
            JIBS_tmp_str[1] = JIBS_tmp_str[0];
            IBS.CLONE(SCCGWA, BPREXFAV, BPREXFAA);
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPREXFAV.KEY.FAV_CODE);
        CEP.TRC(SCCGWA, BPREXFAV.KEY.CCY);
        T000_WRITE_BPTEXFAV();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTEXFAV_UPD();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTEXFAV();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTEXFAV();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTEXFAV();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTEXFAV() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPREXFAV.KEY.FAV_CODE);
        CEP.TRC(SCCGWA, BPREXFAV.KEY.CCY);
        BPTEXFAV_RD = new DBParm();
        BPTEXFAV_RD.TableName = "BPTEXFAV";
        IBS.READ(SCCGWA, BPREXFAV, BPTEXFAV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "FIND NORMAL===============");
            BPCRFAVM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOT FIND==================");
            BPCRFAVM.RETURN_INFO = 'N';
        } else {
        }
        CEP.TRC(SCCGWA, BPREXFAV.CSBUY_SP01);
        CEP.TRC(SCCGWA, BPREXFAV.CSSELL_SP01);
        CEP.TRC(SCCGWA, BPREXFAV.FXBUY_SP01);
        CEP.TRC(SCCGWA, BPREXFAV.FXSELL_SP01);
    }
    public void T000_WRITE_BPTEXFAV() throws IOException,SQLException,Exception {
        BPTEXFAV_RD = new DBParm();
        BPTEXFAV_RD.TableName = "BPTEXFAV";
        BPTEXFAV_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPREXFAV, BPTEXFAV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFAVM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFAVM.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTEXFAV;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTEXFAV_UPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPREXFAV);
        CEP.TRC(SCCGWA, "DEVHZ1922");
        BPTEXFAV_RD = new DBParm();
        BPTEXFAV_RD.TableName = "BPTEXFAV";
        BPTEXFAV_RD.upd = true;
        IBS.READ(SCCGWA, BPREXFAV, BPTEXFAV_RD);
        CEP.TRC(SCCGWA, "DEVHZ2222");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFAVM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFAVM.RETURN_INFO = 'N';
        } else {
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, "DEVHZ3222");
    }
    public void T000_REWRITE_BPTEXFAV() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPREXFAV.KEY.FAV_CODE);
        CEP.TRC(SCCGWA, BPREXFAV.FAV_TYPE);
        CEP.TRC(SCCGWA, BPREXFAV.KEY.CCY);
        CEP.TRC(SCCGWA, BPREXFAV.EFF_DATE);
        BPTEXFAV_RD = new DBParm();
        BPTEXFAV_RD.TableName = "BPTEXFAV";
        IBS.REWRITE(SCCGWA, BPREXFAV, BPTEXFAV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFAVM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRFAVM.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_DELETE_BPTEXFAV() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPREXFAV.KEY.FAV_CODE);
        CEP.TRC(SCCGWA, BPREXFAV.KEY.CCY);
        BPTEXFAV_RD = new DBParm();
        BPTEXFAV_RD.TableName = "BPTEXFAV";
        IBS.DELETE(SCCGWA, BPREXFAV, BPTEXFAV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFAVM.RETURN_INFO = 'F';
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRFAVM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRFAVM = ");
            CEP.TRC(SCCGWA, BPCRFAVM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
