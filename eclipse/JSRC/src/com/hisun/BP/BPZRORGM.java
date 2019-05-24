package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRORGM {
    brParm BPTORGM_BR = new brParm();
    DBParm BPTORGM_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRORGM";
    String K_TBL_ORGM = "BPTORGM ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPRORGM BPRORGM;
    BPCRORGM BPCRORGM;
    SCCCWA SCCCWA;
    String LK_MMT = " ";
    public void MP(SCCGWA SCCGWA, BPCRORGM BPCRORGM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRORGM = BPCRORGM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRORGM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRORGM = (BPRORGM) BPCRORGM.INFO.POINTER;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRORGM.INFO.FUNC == 'S') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRORGM.INFO.FUNC == 'N') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRORGM.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRORGM.INFO.FUNC == 'W') {
            B040_REWRITE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRORGM.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRORGM.INFO.FUNC == 'C') {
            B060_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRORGM.INFO.FUNC == 'U') {
            B070_READ_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRORGM.INFO.FUNC == 'R') {
            B080_READ_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCRORGM.INFO.FUNC);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRORGM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PRE();
        if (pgmRtn) return;
        T000_STARTBR_BPTORGM();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTORGM();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTORGM();
        if (pgmRtn) return;
    }
    public void B040_REWRITE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTORGM();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTORGM();
        if (pgmRtn) return;
    }
    public void B060_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_CREATE_BPTORGM();
        if (pgmRtn) return;
    }
    public void B070_READ_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPDATE_BPTORGM();
        if (pgmRtn) return;
    }
    public void B080_READ_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTORGM();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_PRE() throws IOException,SQLException,Exception {
        if (BPRORGM.KEY.IBS_AC_BK.trim().length() == 0) {
            BPRORGM.KEY.IBS_AC_BK = " ";
        }
        CEP.TRC(SCCGWA, BPRORGM.KEY.BR);
        if (BPRORGM.KEY.BR == ' ') {
            BPRORGM.KEY.BR = 0;
        }
    }
    public void T000_STARTBR_BPTORGM() throws IOException,SQLException,Exception {
        BPTORGM_BR.rp = new DBParm();
        BPTORGM_BR.rp.TableName = "BPTORGM";
        BPTORGM_BR.rp.where = "IBS_AC_BK >= :BPRORGM.KEY.IBS_AC_BK "
            + "AND BR >= :BPRORGM.KEY.BR";
        IBS.STARTBR(SCCGWA, BPRORGM, this, BPTORGM_BR);
    }
    public void T000_READNEXT_BPTORGM() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRORGM, this, BPTORGM_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRORGM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRORGM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTORGM() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTORGM_BR);
    }
    public void T000_REWRITE_BPTORGM() throws IOException,SQLException,Exception {
        BPTORGM_RD = new DBParm();
        BPTORGM_RD.TableName = "BPTORGM";
        IBS.REWRITE(SCCGWA, BPRORGM, BPTORGM_RD);
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            SCCGWA.COMM_AREA.PARM_CHANGED = 12;
        }
    }
    public void T000_DELETE_BPTORGM() throws IOException,SQLException,Exception {
        BPTORGM_RD = new DBParm();
        BPTORGM_RD.TableName = "BPTORGM";
        IBS.DELETE(SCCGWA, BPRORGM, BPTORGM_RD);
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            SCCGWA.COMM_AREA.PARM_CHANGED = 12;
        }
    }
    public void T000_CREATE_BPTORGM() throws IOException,SQLException,Exception {
        BPTORGM_RD = new DBParm();
        BPTORGM_RD.TableName = "BPTORGM";
        IBS.WRITE(SCCGWA, BPRORGM, BPTORGM_RD);
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            SCCGWA.COMM_AREA.PARM_CHANGED = 12;
        }
    }
    public void T000_READ_UPDATE_BPTORGM() throws IOException,SQLException,Exception {
        BPTORGM_RD = new DBParm();
        BPTORGM_RD.TableName = "BPTORGM";
        BPTORGM_RD.upd = true;
        IBS.READ(SCCGWA, BPRORGM, BPTORGM_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRORGM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRORGM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READ_BPTORGM() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            SCCCWA = (SCCCWA) SCCGWA.COMM_AREA.CWA_PTR;
            if (SCCCWA.PARM_IN_USE[12-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[12-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[12-1].PARM_PTR2);
            }
            BPRORGM = (BPRORGM) IBS.HASHLKUP(SCCGWA, "BPRORGM", BPRORGM.KEY);
            if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
                BPTORGM_RD = new DBParm();
                BPTORGM_RD.TableName = "BPTORGM";
                IBS.READ(SCCGWA, BPRORGM, BPTORGM_RD);
            }
        } else {
            BPTORGM_RD = new DBParm();
            BPTORGM_RD.TableName = "BPTORGM";
            IBS.READ(SCCGWA, BPRORGM, BPTORGM_RD);
        }
    } else { //FROM #ELSE
        BPTORGM_RD = new DBParm();
        BPTORGM_RD.TableName = "BPTORGM";
        IBS.READ(SCCGWA, BPRORGM, BPTORGM_RD);
    } //FROM #ENDIF
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRORGM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRORGM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRORGM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRORGM = ");
            CEP.TRC(SCCGWA, BPCRORGM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
