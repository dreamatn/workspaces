package com.hisun.LN;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRIRUL {
    DBParm LNTIRUL_RD;
    brParm LNTIRUL_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRIRUL LNRIRUL = new LNRIRUL();
    SCCGWA SCCGWA;
    LNCRIRUL LNCRIRUL;
    LNRIRUL LNRIRUL1;
    public void MP(SCCGWA SCCGWA, LNCRIRUL LNCRIRUL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRIRUL = LNCRIRUL;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRIRUL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRIRUL1 = (LNRIRUL) LNCRIRUL.REC_PTR;
        LNCRIRUL.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRIRUL);
        IBS.CLONE(SCCGWA, LNRIRUL1, LNRIRUL);
        LNCRIRUL.RC.RC_MMO = "LN";
        LNCRIRUL.RC.RC_CODE = 0;
        if (LNRIRUL.KEY.IBS_AC_BK.trim().length() == 0) {
            LNRIRUL.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_CHECK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRIRUL.KEY.CODE);
        if (LNCRIRUL.FUNC == 'I') {
            T000_READ_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRIRUL.FUNC == 'Y') {
            T000_READ_REC_PROC_1();
            if (pgmRtn) return;
        } else if (LNCRIRUL.FUNC == 'A') {
            T000_WRITE_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRIRUL.FUNC == 'R') {
            T000_READ_UPDATE_PROC();
            if (pgmRtn) return;
        } else if (LNCRIRUL.FUNC == 'U') {
            T000_REWRITE_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRIRUL.FUNC == 'D') {
            T000_DELETE_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRIRUL.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRIRUL.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRIRUL, LNRIRUL1);
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
        if (LNRIRUL.KEY.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE 
            && LNCRIRUL.FUNC == 'A' 
            || LNCRIRUL.FUNC == 'Y') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_INV_EFF_DT, LNCRIRUL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((LNCRIRUL.FUNC == 'A' 
            || LNCRIRUL.FUNC == 'U') 
            && LNRIRUL.EXP_DATE == 0) {
            LNRIRUL.EXP_DATE = 20991231;
        }
        if ((LNCRIRUL.FUNC == 'A' 
            || LNCRIRUL.FUNC == 'U') 
            && LNRIRUL.EXP_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_INV_EXP_DT, LNCRIRUL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (LNCRIRUL.OPT == 'S') {
            B060_20_STBR_BY_ALL();
            if (pgmRtn) return;
        } else if (LNCRIRUL.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRIRUL.OPT == 'E') {
            B060_40_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRIRUL.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_20_STBR_BY_ALL() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC_BY_ALL();
        if (pgmRtn) return;
    }
    public void B060_30_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_PROC();
        if (pgmRtn) return;
    }
    public void B060_40_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        LNTIRUL_RD = new DBParm();
        LNTIRUL_RD.TableName = "LNTIRUL";
        LNTIRUL_RD.where = "IBS_AC_BK = :LNRIRUL.KEY.IBS_AC_BK "
            + "AND CODE = :LNRIRUL.KEY.CODE";
        LNTIRUL_RD.fst = true;
        LNTIRUL_RD.order = "EFF_DATE DESC";
        IBS.READ(SCCGWA, LNRIRUL, this, LNTIRUL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            LNCRIRUL.RETURN_INFO = 'F';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRIRUL.RC);
            LNCRIRUL.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_REC_PROC_1() throws IOException,SQLException,Exception {
        LNTIRUL_RD = new DBParm();
        LNTIRUL_RD.TableName = "LNTIRUL";
        LNTIRUL_RD.where = "IBS_AC_BK = :LNRIRUL.KEY.IBS_AC_BK "
            + "AND CODE = :LNRIRUL.KEY.CODE";
        LNTIRUL_RD.fst = true;
        LNTIRUL_RD.order = "EFF_DATE DESC";
        IBS.READ(SCCGWA, LNRIRUL, this, LNTIRUL_RD);
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRIRUL.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRIRUL.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNRIRUL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTIRUL_RD = new DBParm();
        LNTIRUL_RD.TableName = "LNTIRUL";
        LNTIRUL_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRIRUL, LNTIRUL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRIRUL.RC);
            LNCRIRUL.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTIRUL_RD = new DBParm();
        LNTIRUL_RD.TableName = "LNTIRUL";
        LNTIRUL_RD.upd = true;
        IBS.READ(SCCGWA, LNRIRUL, LNTIRUL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRIRUL.RC);
            LNCRIRUL.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRIRUL.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRIRUL.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNRIRUL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTIRUL_RD = new DBParm();
        LNTIRUL_RD.TableName = "LNTIRUL";
        IBS.REWRITE(SCCGWA, LNRIRUL, LNTIRUL_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTIRUL_RD = new DBParm();
        LNTIRUL_RD.TableName = "LNTIRUL";
        IBS.DELETE(SCCGWA, LNRIRUL, LNTIRUL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRIRUL.RC);
            LNCRIRUL.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC_BY_ALL() throws IOException,SQLException,Exception {
        LNTIRUL_BR.rp = new DBParm();
        LNTIRUL_BR.rp.TableName = "LNTIRUL";
        LNTIRUL_BR.rp.order = "CODE";
        IBS.STARTBR(SCCGWA, LNRIRUL, LNTIRUL_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRIRUL, this, LNTIRUL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRIRUL.RC);
            LNCRIRUL.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTIRUL_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRIRUL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCRIRUL=");
            CEP.TRC(SCCGWA, LNCRIRUL);
            CEP.TRC(SCCGWA, "LNRIRUL =");
            CEP.TRC(SCCGWA, LNRIRUL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
