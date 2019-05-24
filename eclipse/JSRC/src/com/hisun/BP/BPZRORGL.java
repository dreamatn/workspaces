package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRORGL {
    DBParm BPTORGL_RD;
    boolean pgmRtn = false;
    String CPN_R_MGM_ORGL = "BP-R-MGM-ORGL       ";
    String K_PGM_NAME = "BPZRORGL";
    String K_TBL_ORGL = "BPTORGL ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRORGL BPRORGL = new BPRORGL();
    SCCGWA SCCGWA;
    BPCRORGL BPCRORGL;
    BPRORGL BPRORGLL;
    public void MP(SCCGWA SCCGWA, BPCRORGL BPCRORGL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRORGL = BPCRORGL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRORGL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRORGLL = (BPRORGL) BPCRORGL.INFO.POINTER;
        IBS.init(SCCGWA, BPRORGL);
        IBS.CLONE(SCCGWA, BPRORGLL, BPRORGL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRORGL.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRORGL.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRORGL.INFO.FUNC == 'U') {
            B021_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRORGL.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRORGL.INFO.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRORGL.INFO.FUNC == 'I') {
            B050_READ_FOR_AI_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRORGL.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRORGL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRORGL, BPRORGLL);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTORGL();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTORGL_UPD();
        if (pgmRtn) return;
    }
    public void B021_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTORGL();
        if (pgmRtn) return;
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTORGL();
        if (pgmRtn) return;
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTORGL();
        if (pgmRtn) return;
    }
    public void B050_READ_FOR_AI_PROC() throws IOException,SQLException,Exception {
    }
    public void T000_READ_BPTORGL() throws IOException,SQLException,Exception {
        BPTORGL_RD = new DBParm();
        BPTORGL_RD.TableName = "BPTORGL";
        IBS.READ(SCCGWA, BPRORGL, BPTORGL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRORGL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRORGL.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BPTORGL() throws IOException,SQLException,Exception {
        BPTORGL_RD = new DBParm();
        BPTORGL_RD.TableName = "BPTORGL";
        BPTORGL_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRORGL, BPTORGL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRORGL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCRORGL.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_ORGL;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTORGL_UPD() throws IOException,SQLException,Exception {
        BPTORGL_RD = new DBParm();
        BPTORGL_RD.TableName = "BPTORGL";
        BPTORGL_RD.upd = true;
        IBS.READ(SCCGWA, BPRORGL, BPTORGL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRORGL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRORGL.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_DELETE_BPTORGL() throws IOException,SQLException,Exception {
        BPTORGL_RD = new DBParm();
        BPTORGL_RD.TableName = "BPTORGL";
        IBS.DELETE(SCCGWA, BPRORGL, BPTORGL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRORGL.RETURN_INFO = 'F';
        } else {
        }
    }
    public void T000_REWRITE_BPTORGL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRORGL.KEY.TX_DATE);
        CEP.TRC(SCCGWA, BPRORGL.KEY.TX_JRN);
        CEP.TRC(SCCGWA, BPRORGL.KEY.TX_SEQ);
        CEP.TRC(SCCGWA, BPRORGL.TX_FLG);
        CEP.TRC(SCCGWA, BPRORGL.UPT_DT);
        CEP.TRC(SCCGWA, BPRORGL.UPT_TLR);
        BPTORGL_RD = new DBParm();
        BPTORGL_RD.TableName = "BPTORGL";
        IBS.REWRITE(SCCGWA, BPRORGL, BPTORGL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "NORMAL");
            BPCRORGL.RETURN_INFO = 'F';
        } else {
            CEP.TRC(SCCGWA, "DB-EXCP DB-EXCP");
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_ORGL;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRORGL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRORGL = ");
            CEP.TRC(SCCGWA, BPCRORGL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
