package com.hisun.LN;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRCNTR {
    DBParm LNTCNTR_RD;
    brParm LNTCNTR_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRCNTR LNRCNTR = new LNRCNTR();
    SCCGWA SCCGWA;
    LNCRCNTR LNCRCNTR;
    LNRCNTR LNRCNTR1;
    public void MP(SCCGWA SCCGWA, LNCRCNTR LNCRCNTR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRCNTR = LNCRCNTR;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRCNTR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRCNTR1 = (LNRCNTR) LNCRCNTR.REC_PTR;
        LNCRCNTR.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRCNTR);
        IBS.CLONE(SCCGWA, LNRCNTR1, LNRCNTR);
        LNCRCNTR.RC.RC_MMO = "LN";
        LNCRCNTR.RC.RC_CODE = 0;
        if (LNRCNTR.KEY.IBS_AC_BK.trim().length() == 0) {
            LNRCNTR.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_CHECK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRCNTR.KEY.CODE);
        if (LNCRCNTR.FUNC == 'I') {
            T000_READ_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRCNTR.FUNC == 'Y') {
            T000_READ_REC_PROC_1();
            if (pgmRtn) return;
        } else if (LNCRCNTR.FUNC == 'A') {
            T000_WRITE_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRCNTR.FUNC == 'R') {
            T000_READ_UPDATE_PROC();
            if (pgmRtn) return;
        } else if (LNCRCNTR.FUNC == 'U') {
            T000_REWRITE_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRCNTR.FUNC == 'D') {
            T000_DELETE_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRCNTR.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRCNTR.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRCNTR, LNRCNTR1);
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
        if (LNRCNTR.KEY.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE 
            && LNCRCNTR.FUNC == 'A' 
            || LNCRCNTR.FUNC == 'Y') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_INV_EFF_DT, LNCRCNTR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((LNCRCNTR.FUNC == 'A' 
            || LNCRCNTR.FUNC == 'U') 
            && LNRCNTR.EXP_DATE == 0) {
            LNRCNTR.EXP_DATE = 20991231;
        }
        if ((LNCRCNTR.FUNC == 'A' 
            || LNCRCNTR.FUNC == 'U') 
            && LNRCNTR.EXP_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_INV_EXP_DT, LNCRCNTR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (LNCRCNTR.OPT == 'S') {
            B060_20_STBR_BY_ALL();
            if (pgmRtn) return;
        } else if (LNCRCNTR.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRCNTR.OPT == 'E') {
            B060_40_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRCNTR.OPT + ")";
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
        LNTCNTR_RD = new DBParm();
        LNTCNTR_RD.TableName = "LNTCNTR";
        LNTCNTR_RD.where = "IBS_AC_BK = :LNRCNTR.KEY.IBS_AC_BK "
            + "AND CODE = :LNRCNTR.KEY.CODE";
        LNTCNTR_RD.fst = true;
        LNTCNTR_RD.order = "EFF_DATE DESC";
        IBS.READ(SCCGWA, LNRCNTR, this, LNTCNTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            LNCRCNTR.RETURN_INFO = 'F';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRCNTR.RC);
            LNCRCNTR.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_REC_PROC_1() throws IOException,SQLException,Exception {
        LNTCNTR_RD = new DBParm();
        LNTCNTR_RD.TableName = "LNTCNTR";
        LNTCNTR_RD.where = "IBS_AC_BK = :LNRCNTR.KEY.IBS_AC_BK "
            + "AND CODE = :LNRCNTR.KEY.CODE";
        LNTCNTR_RD.fst = true;
        LNTCNTR_RD.order = "EFF_DATE DESC";
        IBS.READ(SCCGWA, LNRCNTR, this, LNTCNTR_RD);
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRCNTR.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRCNTR.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNRCNTR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTCNTR_RD = new DBParm();
        LNTCNTR_RD.TableName = "LNTCNTR";
        LNTCNTR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRCNTR, LNTCNTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRCNTR.RC);
            LNCRCNTR.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTCNTR_RD = new DBParm();
        LNTCNTR_RD.TableName = "LNTCNTR";
        LNTCNTR_RD.upd = true;
        IBS.READ(SCCGWA, LNRCNTR, LNTCNTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRCNTR.RC);
            LNCRCNTR.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRCNTR.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRCNTR.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNRCNTR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTCNTR_RD = new DBParm();
        LNTCNTR_RD.TableName = "LNTCNTR";
        IBS.REWRITE(SCCGWA, LNRCNTR, LNTCNTR_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTCNTR_RD = new DBParm();
        LNTCNTR_RD.TableName = "LNTCNTR";
        IBS.DELETE(SCCGWA, LNRCNTR, LNTCNTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRCNTR.RC);
            LNCRCNTR.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC_BY_ALL() throws IOException,SQLException,Exception {
        LNTCNTR_BR.rp = new DBParm();
        LNTCNTR_BR.rp.TableName = "LNTCNTR";
        LNTCNTR_BR.rp.order = "CODE";
        IBS.STARTBR(SCCGWA, LNRCNTR, LNTCNTR_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRCNTR, this, LNTCNTR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRCNTR.RC);
            LNCRCNTR.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTCNTR_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRCNTR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCRCNTR=");
            CEP.TRC(SCCGWA, LNCRCNTR);
            CEP.TRC(SCCGWA, "LNRCNTR =");
            CEP.TRC(SCCGWA, LNRCNTR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
