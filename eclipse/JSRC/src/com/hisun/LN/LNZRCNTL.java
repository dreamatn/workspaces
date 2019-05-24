package com.hisun.LN;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRCNTL {
    DBParm LNTCNTL_RD;
    brParm LNTCNTL_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRCNTL LNRCNTL = new LNRCNTL();
    SCCGWA SCCGWA;
    LNCRCNTL LNCRCNTL;
    LNRCNTL LNRCNTL1;
    public void MP(SCCGWA SCCGWA, LNCRCNTL LNCRCNTL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRCNTL = LNCRCNTL;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRCNTL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRCNTL1 = (LNRCNTL) LNCRCNTL.REC_PTR;
        LNCRCNTL.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRCNTL);
        IBS.CLONE(SCCGWA, LNRCNTL1, LNRCNTL);
        LNCRCNTL.RC.RC_MMO = "LN";
        LNCRCNTL.RC.RC_CODE = 0;
        if (LNRCNTL.KEY.IBS_AC_BK.trim().length() == 0) {
            LNRCNTL.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_CHECK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRCNTL.KEY.CODE);
        if (LNCRCNTL.FUNC == 'I') {
            T000_READ_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRCNTL.FUNC == 'Y') {
            T000_READ_REC_PROC_1();
            if (pgmRtn) return;
        } else if (LNCRCNTL.FUNC == 'A') {
            T000_WRITE_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRCNTL.FUNC == 'R') {
            T000_READ_UPDATE_PROC();
            if (pgmRtn) return;
        } else if (LNCRCNTL.FUNC == 'U') {
            T000_REWRITE_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRCNTL.FUNC == 'D') {
            T000_DELETE_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRCNTL.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRCNTL.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRCNTL, LNRCNTL1);
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
        if (LNRCNTL.KEY.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE 
            && LNCRCNTL.FUNC == 'A' 
            || LNCRCNTL.FUNC == 'Y') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_INV_EFF_DT, LNCRCNTL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((LNCRCNTL.FUNC == 'A' 
            || LNCRCNTL.FUNC == 'U') 
            && LNRCNTL.EXP_DATE == 0) {
            LNRCNTL.EXP_DATE = 20991231;
        }
        if ((LNCRCNTL.FUNC == 'A' 
            || LNCRCNTL.FUNC == 'U') 
            && LNRCNTL.EXP_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_INV_EXP_DT, LNCRCNTL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (LNCRCNTL.OPT == 'S') {
            B060_20_STBR_BY_ALL();
            if (pgmRtn) return;
        } else if (LNCRCNTL.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRCNTL.OPT == 'E') {
            B060_40_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRCNTL.OPT + ")";
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
        LNTCNTL_RD = new DBParm();
        LNTCNTL_RD.TableName = "LNTCNTL";
        LNTCNTL_RD.where = "IBS_AC_BK = :LNRCNTL.KEY.IBS_AC_BK "
            + "AND CODE = :LNRCNTL.KEY.CODE";
        LNTCNTL_RD.fst = true;
        LNTCNTL_RD.order = "EFF_DATE DESC";
        IBS.READ(SCCGWA, LNRCNTL, this, LNTCNTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            LNCRCNTL.RETURN_INFO = 'F';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRCNTL.RC);
            LNCRCNTL.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_REC_PROC_1() throws IOException,SQLException,Exception {
        LNTCNTL_RD = new DBParm();
        LNTCNTL_RD.TableName = "LNTCNTL";
        LNTCNTL_RD.where = "IBS_AC_BK = :LNRCNTL.KEY.IBS_AC_BK "
            + "AND CODE = :LNRCNTL.KEY.CODE";
        LNTCNTL_RD.fst = true;
        LNTCNTL_RD.order = "EFF_DATE DESC";
        IBS.READ(SCCGWA, LNRCNTL, this, LNTCNTL_RD);
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRCNTL.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRCNTL.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNRCNTL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTCNTL_RD = new DBParm();
        LNTCNTL_RD.TableName = "LNTCNTL";
        LNTCNTL_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRCNTL, LNTCNTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRCNTL.RC);
            LNCRCNTL.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTCNTL_RD = new DBParm();
        LNTCNTL_RD.TableName = "LNTCNTL";
        LNTCNTL_RD.upd = true;
        IBS.READ(SCCGWA, LNRCNTL, LNTCNTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRCNTL.RC);
            LNCRCNTL.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRCNTL.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRCNTL.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNRCNTL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTCNTL_RD = new DBParm();
        LNTCNTL_RD.TableName = "LNTCNTL";
        IBS.REWRITE(SCCGWA, LNRCNTL, LNTCNTL_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTCNTL_RD = new DBParm();
        LNTCNTL_RD.TableName = "LNTCNTL";
        IBS.DELETE(SCCGWA, LNRCNTL, LNTCNTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRCNTL.RC);
            LNCRCNTL.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC_BY_ALL() throws IOException,SQLException,Exception {
        LNTCNTL_BR.rp = new DBParm();
        LNTCNTL_BR.rp.TableName = "LNTCNTL";
        LNTCNTL_BR.rp.order = "CODE";
        IBS.STARTBR(SCCGWA, LNRCNTL, LNTCNTL_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRCNTL, this, LNTCNTL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRCNTL.RC);
            LNCRCNTL.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTCNTL_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRCNTL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCRCNTL=");
            CEP.TRC(SCCGWA, LNCRCNTL);
            CEP.TRC(SCCGWA, "LNRCNTL =");
            CEP.TRC(SCCGWA, LNRCNTL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
