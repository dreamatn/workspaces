package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTPDCD {
    DBParm BPTPDCD_RD;
    brParm BPTPDCD_BR = new brParm();
    boolean pgmRtn = false;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPCTPDCD BPCTPDCD;
    SCCCWA SCCCWA;
    BPRPDCD BPRPDCD;
    String LK_MMT = " ";
    public void MP(SCCGWA SCCGWA, BPCTPDCD BPCTPDCD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTPDCD = BPCTPDCD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTPDCD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTPDCD.RC);
        BPRPDCD = (BPRPDCD) BPCTPDCD.INFO.POINTER;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRPDCD.KEY.PRDT_CODE);
        if (BPCTPDCD.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTPDCD.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTPDCD.INFO.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTPDCD.INFO.FUNC == 'I') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTPDCD.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTPDCD.INFO.FUNC == 'B') {
            B050_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCTPDCD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTPDCD();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPDCD_UPD();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTPDCD();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPDCD();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTPDCD();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCTPDCD.INFO.OPT == 'S') {
            T000_STARTBR_BPTPDCD();
            if (pgmRtn) return;
        } else if (BPCTPDCD.INFO.OPT == 'N') {
            T000_READNEXT_BPTPDCD();
            if (pgmRtn) return;
        } else if (BPCTPDCD.INFO.OPT == 'E') {
            T000_ENDBR_BPTPDCD();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCTPDCD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTPDCD() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            SCCCWA = (SCCCWA) SCCGWA.COMM_AREA.CWA_PTR;
            if (SCCCWA.PARM_IN_USE[7-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[7-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[7-1].PARM_PTR2);
            }
            BPRPDCD = (BPRPDCD) IBS.HASHLKUP(SCCGWA, "BPRPDCD", BPRPDCD.KEY);
            if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
                BPTPDCD_RD = new DBParm();
                BPTPDCD_RD.TableName = "BPTPDCD";
                IBS.READ(SCCGWA, BPRPDCD, BPTPDCD_RD);
            }
        } else {
            BPTPDCD_RD = new DBParm();
            BPTPDCD_RD.TableName = "BPTPDCD";
            IBS.READ(SCCGWA, BPRPDCD, BPTPDCD_RD);
        }
    } else { //FROM #ELSE
        BPTPDCD_RD = new DBParm();
        BPTPDCD_RD.TableName = "BPTPDCD";
        IBS.READ(SCCGWA, BPRPDCD, BPTPDCD_RD);
    } //FROM #ENDIF
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTPDCD.RETURN_INFO = 'F';
        } else {
            BPCTPDCD.RETURN_INFO = 'N';
        }
    }
    public void T000_WRITE_BPTPDCD() throws IOException,SQLException,Exception {
        BPTPDCD_RD = new DBParm();
        BPTPDCD_RD.TableName = "BPTPDCD";
        BPTPDCD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRPDCD, BPTPDCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTPDCD.RETURN_INFO = 'F';
            if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
                SCCGWA.COMM_AREA.PARM_CHANGED = 7;
            }
        } else {
            BPCTPDCD.RETURN_INFO = 'D';
        }
    }
    public void T000_READ_BPTPDCD_UPD() throws IOException,SQLException,Exception {
        BPTPDCD_RD = new DBParm();
        BPTPDCD_RD.TableName = "BPTPDCD";
        BPTPDCD_RD.upd = true;
        IBS.READ(SCCGWA, BPRPDCD, BPTPDCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTPDCD.RETURN_INFO = 'F';
        } else {
            BPCTPDCD.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCTPDCD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTPDCD() throws IOException,SQLException,Exception {
        BPTPDCD_RD = new DBParm();
        BPTPDCD_RD.TableName = "BPTPDCD";
        IBS.REWRITE(SCCGWA, BPRPDCD, BPTPDCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
                SCCGWA.COMM_AREA.PARM_CHANGED = 7;
            }
        }
    }
    public void T000_DELETE_BPTPDCD() throws IOException,SQLException,Exception {
        BPTPDCD_RD = new DBParm();
        BPTPDCD_RD.TableName = "BPTPDCD";
        IBS.DELETE(SCCGWA, BPRPDCD, BPTPDCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
                SCCGWA.COMM_AREA.PARM_CHANGED = 7;
            }
        }
    }
    public void T000_STARTBR_BPTPDCD() throws IOException,SQLException,Exception {
        if (BPCTPDCD.INFO.INDEX_FLG == '1') {
            BPTPDCD_BR.rp = new DBParm();
            BPTPDCD_BR.rp.TableName = "BPTPDCD";
            BPTPDCD_BR.rp.eqWhere = "LEVEL1";
            IBS.STARTBR(SCCGWA, BPRPDCD, BPTPDCD_BR);
        }
        if (BPCTPDCD.INFO.INDEX_FLG == '2') {
            BPTPDCD_BR.rp = new DBParm();
            BPTPDCD_BR.rp.TableName = "BPTPDCD";
            BPTPDCD_BR.rp.eqWhere = "LEVEL1, LEVEL2";
            IBS.STARTBR(SCCGWA, BPRPDCD, BPTPDCD_BR);
        }
    }
    public void T000_READNEXT_BPTPDCD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRPDCD, this, BPTPDCD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTPDCD.RETURN_INFO = 'F';
        } else {
            BPCTPDCD.RETURN_INFO = 'N';
        }
    }
    public void T000_ENDBR_BPTPDCD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTPDCD_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTPDCD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTPDCD = ");
            CEP.TRC(SCCGWA, BPCTPDCD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
