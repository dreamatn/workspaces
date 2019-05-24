package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTPDME {
    DBParm BPTPDME_RD;
    brParm BPTPDME_BR = new brParm();
    boolean pgmRtn = false;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCTPDME BPCTPDME;
    SCCCWA SCCCWA;
    BPRPDME BPRPDME;
    String LK_MMT = " ";
    public void MP(SCCGWA SCCGWA, BPCTPDME BPCTPDME) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTPDME = BPCTPDME;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTPDME return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCTPDME.RC);
        BPRPDME = (BPRPDME) BPCTPDME.INFO.POINTER;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTPDME.INFO.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTPDME.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTPDME.INFO.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTPDME.INFO.FUNC == 'I') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTPDME.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCTPDME.INFO.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCTPDME.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTPDME();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPDME_UPD();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTPDME();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTPDME();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTPDME();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPCTPDME.INFO.OPT == 'S') {
            T000_STARTBR_BPTPDME();
            if (pgmRtn) return;
        } else if (BPCTPDME.INFO.OPT == 'N') {
            T000_READNEXT_BPTPDME();
            if (pgmRtn) return;
        } else if (BPCTPDME.INFO.OPT == 'E') {
            T000_ENDBR_BPTPDME();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCTPDME.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTPDME() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
            SCCCWA = (SCCCWA) SCCGWA.COMM_AREA.CWA_PTR;
            if (SCCCWA.PARM_IN_USE[7-1] == 1) {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR1_AREA[7-1].PARM_PTR1);
            } else {
                LK_MMT = IBS.CLS2CPY(SCCGWA, SCCCWA.PARM_PTR2_AREA[7-1].PARM_PTR2);
            }
            BPRPDME = (BPRPDME) IBS.HASHLKUP(SCCGWA, "BPRPDME", BPRPDME.KEY);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                BPTPDME_RD = new DBParm();
                BPTPDME_RD.TableName = "BPTPDME";
                IBS.READ(SCCGWA, BPRPDME, BPTPDME_RD);
            }
        } else {
            BPTPDME_RD = new DBParm();
            BPTPDME_RD.TableName = "BPTPDME";
            IBS.READ(SCCGWA, BPRPDME, BPTPDME_RD);
        }
    } else { //FROM #ELSE
        BPTPDME_RD = new DBParm();
        BPTPDME_RD.TableName = "BPTPDME";
        IBS.READ(SCCGWA, BPRPDME, BPTPDME_RD);
    } //FROM #ENDIF
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTPDME.RETURN_INFO = 'F';
        } else {
            BPCTPDME.RETURN_INFO = 'N';
        }
    }
    public void T000_WRITE_BPTPDME() throws IOException,SQLException,Exception {
        BPTPDME_RD = new DBParm();
        BPTPDME_RD.TableName = "BPTPDME";
        BPTPDME_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRPDME, BPTPDME_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTPDME.RETURN_INFO = 'F';
            if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
                SCCGWA.COMM_AREA.PARM_CHANGED = 7;
            }
        } else {
            BPCTPDME.RETURN_INFO = 'D';
        }
    }
    public void T000_READ_BPTPDME_UPD() throws IOException,SQLException,Exception {
        BPTPDME_RD = new DBParm();
        BPTPDME_RD.TableName = "BPTPDME";
        BPTPDME_RD.upd = true;
        IBS.READ(SCCGWA, BPRPDME, BPTPDME_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTPDME.RETURN_INFO = 'F';
        } else {
            BPCTPDME.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCTPDME.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTPDME() throws IOException,SQLException,Exception {
        BPTPDME_RD = new DBParm();
        BPTPDME_RD.TableName = "BPTPDME";
        IBS.REWRITE(SCCGWA, BPRPDME, BPTPDME_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
                SCCGWA.COMM_AREA.PARM_CHANGED = 7;
            }
        }
    }
    public void T000_DELETE_BPTPDME() throws IOException,SQLException,Exception {
        BPTPDME_RD = new DBParm();
        BPTPDME_RD.TableName = "BPTPDME";
        IBS.DELETE(SCCGWA, BPRPDME, BPTPDME_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (SCCGWA.COMM_AREA.PARM_IND == 'E') {
                SCCGWA.COMM_AREA.PARM_CHANGED = 7;
            }
        }
    }
    public void T000_STARTBR_BPTPDME() throws IOException,SQLException,Exception {
        if (BPCTPDME.INFO.INDEX_FLG == '1') {
            BPTPDME_BR.rp = new DBParm();
            BPTPDME_BR.rp.TableName = "BPTPDME";
            IBS.STARTBR(SCCGWA, BPRPDME, BPTPDME_BR);
        }
        if (BPCTPDME.INFO.INDEX_FLG == '2') {
            BPTPDME_BR.rp = new DBParm();
            BPTPDME_BR.rp.TableName = "BPTPDME";
            BPTPDME_BR.rp.eqWhere = "BUSI_TYPE";
            IBS.STARTBR(SCCGWA, BPRPDME, BPTPDME_BR);
        }
        if (BPCTPDME.INFO.INDEX_FLG == '3') {
            BPTPDME_BR.rp = new DBParm();
            BPTPDME_BR.rp.TableName = "BPTPDME";
            BPTPDME_BR.rp.eqWhere = "PRDT_MODEL";
            IBS.STARTBR(SCCGWA, BPRPDME, BPTPDME_BR);
        }
        if (BPCTPDME.INFO.INDEX_FLG == '4') {
            BPTPDME_BR.rp = new DBParm();
            BPTPDME_BR.rp.TableName = "BPTPDME";
            BPTPDME_BR.rp.eqWhere = "PARM_CODE";
            IBS.STARTBR(SCCGWA, BPRPDME, BPTPDME_BR);
        }
    }
    public void T000_READNEXT_BPTPDME() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRPDME, this, BPTPDME_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTPDME.RETURN_INFO = 'F';
        } else {
            BPCTPDME.RETURN_INFO = 'N';
        }
    }
    public void T000_ENDBR_BPTPDME() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTPDME_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTPDME.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTPDME = ");
            CEP.TRC(SCCGWA, BPCTPDME);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
