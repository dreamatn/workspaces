package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSFXOG {
    DBParm BPTFXORG_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZSFXOG";
    String K_TBL_FXORG = "BPTFXORG ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRFXORG BPRFXORG = new BPRFXORG();
    SCCGWA SCCGWA;
    BPCSFXOG BPCSFXOG;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRFXORG BPRRXORG;
    public void MP(SCCGWA SCCGWA, BPCSFXOG BPCSFXOG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSFXOG = BPCSFXOG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSFXOG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRRXORG = (BPRFXORG) BPCSFXOG.INFO.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CLONE(SCCGWA, BPRRXORG, BPRFXORG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSFXOG.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCSFXOG.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCSFXOG.INFO.FUNC == 'M') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCSFXOG.INFO.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERR, BPCSFXOG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRFXORG, BPRRXORG);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTFXORG();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTFXORG_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTFXORG();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_QUERY_BPTFXORG();
        if (pgmRtn) return;
    }
    public void T000_WRITE_BPTFXORG() throws IOException,SQLException,Exception {
        BPTFXORG_RD = new DBParm();
        BPTFXORG_RD.TableName = "BPTFXORG";
        BPTFXORG_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRFXORG, BPTFXORG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCSFXOG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCSFXOG.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_DUPKEY, BPCSFXOG.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_FXORG;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTFXORG_UPD() throws IOException,SQLException,Exception {
        BPTFXORG_RD = new DBParm();
        BPTFXORG_RD.TableName = "BPTFXORG";
        BPTFXORG_RD.upd = true;
        BPTFXORG_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRFXORG, BPTFXORG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCSFXOG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCSFXOG.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCSFXOG.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_FXORG;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTFXORG() throws IOException,SQLException,Exception {
        BPTFXORG_RD = new DBParm();
        BPTFXORG_RD.TableName = "BPTFXORG";
        BPTFXORG_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, BPRFXORG, BPTFXORG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCSFXOG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCSFXOG.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCSFXOG.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_FXORG;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_QUERY_BPTFXORG() throws IOException,SQLException,Exception {
        BPTFXORG_RD = new DBParm();
        BPTFXORG_RD.TableName = "BPTFXORG";
        BPTFXORG_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRFXORG, BPTFXORG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCSFXOG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCSFXOG.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCSFXOG.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_FXORG;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCSFXOG.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCSFXOG = ");
            CEP.TRC(SCCGWA, BPCSFXOG);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
