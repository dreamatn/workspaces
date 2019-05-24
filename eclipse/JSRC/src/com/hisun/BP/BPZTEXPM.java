package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTEXPM {
    DBParm BPTEXRP_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTEXPM";
    String K_TBL_EXRP = "BPTEXRP ";
    int WS_REC_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPREXRP BPREXRP = new BPREXRP();
    BPREXRP BPREXRX = new BPREXRP();
    SCCGWA SCCGWA;
    BPCTEXPM BPCTEXPM;
    BPREXRP BPREXRP1;
    public void MP(SCCGWA SCCGWA, BPCTEXPM BPCTEXPM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTEXPM = BPCTEXPM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTEXPM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPREXRP1 = (BPREXRP) BPCTEXPM.POINTER;
        IBS.init(SCCGWA, BPREXRP);
        IBS.CLONE(SCCGWA, BPREXRP1, BPREXRP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "HELPME");
        if (BPCTEXPM.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTEXPM.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTEXPM.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTEXPM.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTEXPM.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTEXPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPREXRP, BPREXRP1);
        CEP.TRC(SCCGWA, "5555");
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTEXRP();
        if (pgmRtn) return;
    }
    public void T000_WRITE_BPTEXRP() throws IOException,SQLException,Exception {
        BPTEXRP_RD = new DBParm();
        BPTEXRP_RD.TableName = "BPTEXRP";
        BPTEXRP_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPREXRP, BPTEXRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTEXPM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTEXPM.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_EXRP;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTEXRP_UPD();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTEXRP_UPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPREXRP.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPREXRP.KEY.CCY);
        BPTEXRP_RD = new DBParm();
        BPTEXRP_RD.TableName = "BPTEXRP";
        BPTEXRP_RD.upd = true;
        IBS.READ(SCCGWA, BPREXRP, BPTEXRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTEXPM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTEXPM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTEXRP();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "1111");
    }
    public void T000_READ_BPTEXRP() throws IOException,SQLException,Exception {
        BPTEXRP_RD = new DBParm();
        BPTEXRP_RD.TableName = "BPTEXRP";
        IBS.READ(SCCGWA, BPREXRP, BPTEXRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTEXPM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTEXPM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTEXRP();
        if (pgmRtn) return;
    }
    public void T000_REWRITE_BPTEXRP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPREXRP.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPREXRP.KEY.CCY);
        BPTEXRP_RD = new DBParm();
        BPTEXRP_RD.TableName = "BPTEXRP";
        IBS.REWRITE(SCCGWA, BPREXRP, BPTEXRP_RD);
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTEXRP();
        if (pgmRtn) return;
    }
    public void T000_DELETE_BPTEXRP() throws IOException,SQLException,Exception {
        BPTEXRP_RD = new DBParm();
        BPTEXRP_RD.TableName = "BPTEXRP";
        IBS.DELETE(SCCGWA, BPREXRP, BPTEXRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTEXPM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTEXPM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTEXPM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTEXPM = ");
            CEP.TRC(SCCGWA, BPCTEXPM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
