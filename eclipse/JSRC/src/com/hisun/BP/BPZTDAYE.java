package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTDAYE {
    DBParm BPTDAYE_RD;
    brParm BPTDAYE_BR = new brParm();
    boolean pgmRtn = false;
    String TBL_BPTDAYE = "BPTDAYE ";
    int WS_REC_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRDAYE BPRDAYE = new BPRDAYE();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPCTDAYE BPCTDAYE;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRDAYE BPRDAYE1;
    public void MP(SCCGWA SCCGWA, BPCTDAYE BPCTDAYE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTDAYE = BPCTDAYE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTDAYE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPRDAYE1 = (BPRDAYE) BPCTDAYE.INFO.POINTER;
        IBS.init(SCCGWA, BPRDAYE);
        WS_REC_LEN = 82;
        IBS.CLONE(SCCGWA, BPRDAYE1, BPRDAYE);
        CEP.TRC(SCCGWA, BPRDAYE.KEY.RGN);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTDAYE.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTDAYE.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTDAYE.INFO.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTDAYE.INFO.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTDAYE.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTDAYE.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCTDAYE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRDAYE, BPRDAYE1);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTDAYE();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTDAYE_UPD();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTDAYE();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTDAYE();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTDAYE();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCTDAYE.INFO.OPT == 'S') {
            T000_STARTBR_BPTDAYE();
            if (pgmRtn) return;
        } else if (BPCTDAYE.INFO.OPT == 'N') {
            T000_READNEXT_BPTDAYE();
            if (pgmRtn) return;
        } else if (BPCTDAYE.INFO.OPT == 'E') {
            T000_ENDBR_BPTDAYE();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCTDAYE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTDAYE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRDAYE.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRDAYE.KEY.RGN);
        CEP.TRC(SCCGWA, BPRDAYE.KEY.BCH);
        CEP.TRC(SCCGWA, BPRDAYE.KEY.DATE);
        BPTDAYE_RD = new DBParm();
        BPTDAYE_RD.TableName = "BPTDAYE";
        BPTDAYE_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRDAYE, BPTDAYE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTDAYE.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTDAYE.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTDAYE;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BPTDAYE() throws IOException,SQLException,Exception {
        BPTDAYE_RD = new DBParm();
        BPTDAYE_RD.TableName = "BPTDAYE";
        BPTDAYE_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRDAYE, BPTDAYE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTDAYE.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTDAYE.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTDAYE;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTDAYE_UPD() throws IOException,SQLException,Exception {
        BPTDAYE_RD = new DBParm();
        BPTDAYE_RD.TableName = "BPTDAYE";
        BPTDAYE_RD.upd = true;
        IBS.READ(SCCGWA, BPRDAYE, BPTDAYE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTDAYE.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTDAYE.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTDAYE;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTDAYE() throws IOException,SQLException,Exception {
        BPTDAYE_RD = new DBParm();
        BPTDAYE_RD.TableName = "BPTDAYE";
        IBS.REWRITE(SCCGWA, BPRDAYE, BPTDAYE_RD);
    }
    public void T000_DELETE_BPTDAYE() throws IOException,SQLException,Exception {
        BPTDAYE_RD = new DBParm();
        BPTDAYE_RD.TableName = "BPTDAYE";
        IBS.DELETE(SCCGWA, BPRDAYE, BPTDAYE_RD);
    }
    public void T000_STARTBR_BPTDAYE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRDAYE.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRDAYE.KEY.RGN);
        BPTDAYE_BR.rp = new DBParm();
        BPTDAYE_BR.rp.TableName = "BPTDAYE";
        BPTDAYE_BR.rp.where = "TYPE = :BPRDAYE.KEY.TYPE "
            + "AND RGN LIKE :BPRDAYE.KEY.RGN "
            + "AND 'DATE' >= :BPRDAYE.KEY.DATE";
        BPTDAYE_BR.rp.order = "DATE";
        IBS.STARTBR(SCCGWA, BPRDAYE, this, BPTDAYE_BR);
    }
    public void T000_READNEXT_BPTDAYE() throws IOException,SQLException,Exception {
        BPTDAYE_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRDAYE, this, BPTDAYE_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTDAYE.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTDAYE.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTDAYE;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTDAYE() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTDAYE_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTDAYE.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTDAYE = ");
            CEP.TRC(SCCGWA, BPCTDAYE);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
