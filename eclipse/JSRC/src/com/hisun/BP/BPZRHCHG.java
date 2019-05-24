package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRHCHG {
    DBParm BPTHCHG_RD;
    boolean pgmRtn = false;
    String TBL_BPTHCHG = "BPTHCHG";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRHCHG BPRHCHG = new BPRHCHG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCRHCHG BPCRHCHG;
    BPRHCHG BPRHCHG1;
    public void MP(SCCGWA SCCGWA, BPCRHCHG BPCRHCHG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRHCHG = BPCRHCHG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRHCHG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCRHCHG.RC);
        BPRHCHG1 = (BPRHCHG) BPCRHCHG.INFO.POINTER;
        IBS.init(SCCGWA, BPRHCHG);
        IBS.CLONE(SCCGWA, BPRHCHG1, BPRHCHG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRHCHG.INFO.FUNC == 'A') {
            B010_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRHCHG.INFO.FUNC == 'R') {
            B020_READUPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRHCHG.INFO.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRHCHG.INFO.FUNC == 'I') {
            B050_INQUIRE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUN_ERR, BPCRHCHG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRHCHG, BPRHCHG1);
    }
    public void B010_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        T010_ADD_RECORD_PROC();
        if (pgmRtn) return;
    }
    public void B020_READUPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T020_READUPDATE_RECORD_PROC();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T030_UPDATE_RECORD_PROC();
        if (pgmRtn) return;
    }
    public void B050_INQUIRE_RECORD_PROC() throws IOException,SQLException,Exception {
        T050_INQUIRE_RECORD_PROC();
        if (pgmRtn) return;
    }
    public void T010_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        BPTHCHG_RD = new DBParm();
        BPTHCHG_RD.TableName = "BPTHCHG";
        BPTHCHG_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRHCHG, BPTHCHG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRHCHG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRHCHG.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTHCHG;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T020_READUPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPTHCHG_RD = new DBParm();
        BPTHCHG_RD.TableName = "BPTHCHG";
        BPTHCHG_RD.upd = true;
        IBS.READ(SCCGWA, BPRHCHG, BPTHCHG_RD);
    }
    public void T030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPTHCHG_RD = new DBParm();
        BPTHCHG_RD.TableName = "BPTHCHG";
        IBS.REWRITE(SCCGWA, BPRHCHG, BPTHCHG_RD);
    }
    public void T050_INQUIRE_RECORD_PROC() throws IOException,SQLException,Exception {
        BPTHCHG_RD = new DBParm();
        BPTHCHG_RD.TableName = "BPTHCHG";
        BPTHCHG_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRHCHG, BPTHCHG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRHCHG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRHCHG.RETURN_INFO = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INQ_DB_DATA_ERR, BPCRHCHG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRHCHG.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRHCHG = ");
            CEP.TRC(SCCGWA, BPCRHCHG);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
