package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTMOGS {
    DBParm BPTORGS_RD;
    boolean pgmRtn = false;
    char BPZTMOGS_FILLER1 = ' ';
    char WS_TBL_ORG_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPRORGS BPRORGS;
    BPCRMOGS BPCRMOGS;
    SCCCWA SCCCWA;
    String LK_MMT = " ";
    public void MP(SCCGWA SCCGWA, BPCRMOGS BPCRMOGS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRMOGS = BPCRMOGS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTMOGS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRORGS = (BPRORGS) BPCRMOGS.POINTER;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRMOGS.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMOGS.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMOGS.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMOGS.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRMOGS.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRMOGS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTORGS();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTORGS_UPD();
        if (pgmRtn) return;
        if (WS_TBL_ORG_FLAG == 'D') {
            BPCRMOGS.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTORGS();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTORGS();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTORGS();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTORGS() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            SCCCWA = (SCCCWA) SCCGWA.COMM_AREA.CWA_PTR;
            if (SCCCWA.PARM_IN_USE[11-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[11-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[11-1].PARM_PTR2);
            }
            BPRORGS = (BPRORGS) IBS.HASHLKUP(SCCGWA, "BPRORGS", BPRORGS.KEY);
            if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
                BPTORGS_RD = new DBParm();
                BPTORGS_RD.TableName = "BPTORGS";
                IBS.READ(SCCGWA, BPRORGS, BPTORGS_RD);
            }
        } else {
            BPTORGS_RD = new DBParm();
            BPTORGS_RD.TableName = "BPTORGS";
            IBS.READ(SCCGWA, BPRORGS, BPTORGS_RD);
        }
    } else { //FROM #ELSE
        BPTORGS_RD = new DBParm();
        BPTORGS_RD.TableName = "BPTORGS";
        IBS.READ(SCCGWA, BPRORGS, BPTORGS_RD);
    } //FROM #ENDIF
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMOGS.RETURN_INFO = 'F';
        } else {
            BPCRMOGS.RETURN_INFO = 'N';
        }
    }
    public void T000_WRITE_BPTORGS() throws IOException,SQLException,Exception {
        BPTORGS_RD = new DBParm();
        BPTORGS_RD.TableName = "BPTORGS";
        BPTORGS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRORGS, BPTORGS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMOGS.RETURN_INFO = 'F';
            if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
                SCCGWA.COMM_AREA.PARM_CHANGED = 11;
            }
        } else {
            BPCRMOGS.RETURN_INFO = 'D';
        }
    }
    public void T000_READ_BPTORGS_UPD() throws IOException,SQLException,Exception {
        BPTORGS_RD = new DBParm();
        BPTORGS_RD.TableName = "BPTORGS";
        BPTORGS_RD.upd = true;
        IBS.READ(SCCGWA, BPRORGS, BPTORGS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_ORG_FLAG = 'N';
        } else {
            WS_TBL_ORG_FLAG = 'D';
        }
    }
    public void T000_REWRITE_BPTORGS() throws IOException,SQLException,Exception {
        BPTORGS_RD = new DBParm();
        BPTORGS_RD.TableName = "BPTORGS";
        IBS.REWRITE(SCCGWA, BPRORGS, BPTORGS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
                SCCGWA.COMM_AREA.PARM_CHANGED = 11;
            }
        }
    }
    public void T000_DELETE_BPTORGS() throws IOException,SQLException,Exception {
        BPTORGS_RD = new DBParm();
        BPTORGS_RD.TableName = "BPTORGS";
        IBS.DELETE(SCCGWA, BPRORGS, BPTORGS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRMOGS.RETURN_INFO = 'F';
            if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
                SCCGWA.COMM_AREA.PARM_CHANGED = 11;
            }
        } else {
            BPCRMOGS.RETURN_INFO = 'N';
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRMOGS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRMOGS = ");
            CEP.TRC(SCCGWA, BPCRMOGS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
