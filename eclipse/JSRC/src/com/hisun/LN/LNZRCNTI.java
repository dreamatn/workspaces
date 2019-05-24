package com.hisun.LN;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRCNTI {
    DBParm LNTCNTI_RD;
    brParm LNTCNTI_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRCNTI LNRCNTI = new LNRCNTI();
    SCCGWA SCCGWA;
    LNCRCNTI LNCRCNTI;
    LNRCNTI LNRCNTI1;
    public void MP(SCCGWA SCCGWA, LNCRCNTI LNCRCNTI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRCNTI = LNCRCNTI;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRCNTI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRCNTI1 = (LNRCNTI) LNCRCNTI.REC_PTR;
        LNCRCNTI.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRCNTI);
        IBS.CLONE(SCCGWA, LNRCNTI1, LNRCNTI);
        LNCRCNTI.RC.RC_MMO = "LN";
        LNCRCNTI.RC.RC_CODE = 0;
        if (LNRCNTI.KEY.IBS_AC_BK.trim().length() == 0) {
            LNRCNTI.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_CHECK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRCNTI.KEY.CODE);
        if (LNCRCNTI.FUNC == 'I') {
            T000_READ_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRCNTI.FUNC == 'Y') {
            T000_READ_REC_PROC_1();
            if (pgmRtn) return;
        } else if (LNCRCNTI.FUNC == 'A') {
            T000_WRITE_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRCNTI.FUNC == 'R') {
            T000_READ_UPDATE_PROC();
            if (pgmRtn) return;
        } else if (LNCRCNTI.FUNC == 'U') {
            T000_REWRITE_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRCNTI.FUNC == 'D') {
            T000_DELETE_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRCNTI.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRCNTI.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRCNTI, LNRCNTI1);
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
        if (LNRCNTI.KEY.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE 
            && LNCRCNTI.FUNC == 'A' 
            || LNCRCNTI.FUNC == 'Y') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_INV_EFF_DT, LNCRCNTI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((LNCRCNTI.FUNC == 'A' 
            || LNCRCNTI.FUNC == 'U') 
            && LNRCNTI.EXP_DATE == 0) {
            LNRCNTI.EXP_DATE = 20991231;
        }
        if ((LNCRCNTI.FUNC == 'A' 
            || LNCRCNTI.FUNC == 'U') 
            && LNRCNTI.EXP_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_INV_EXP_DT, LNCRCNTI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (LNCRCNTI.OPT == 'S') {
            B060_20_STBR_BY_ALL();
            if (pgmRtn) return;
        } else if (LNCRCNTI.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRCNTI.OPT == 'E') {
            B060_40_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRCNTI.OPT + ")";
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
        LNTCNTI_RD = new DBParm();
        LNTCNTI_RD.TableName = "LNTCNTI";
        LNTCNTI_RD.where = "IBS_AC_BK = :LNRCNTI.KEY.IBS_AC_BK "
            + "AND CODE = :LNRCNTI.KEY.CODE";
        LNTCNTI_RD.fst = true;
        LNTCNTI_RD.order = "EFF_DATE DESC";
        IBS.READ(SCCGWA, LNRCNTI, this, LNTCNTI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            LNCRCNTI.RETURN_INFO = 'F';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRCNTI.RC);
            LNCRCNTI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_REC_PROC_1() throws IOException,SQLException,Exception {
        LNTCNTI_RD = new DBParm();
        LNTCNTI_RD.TableName = "LNTCNTI";
        LNTCNTI_RD.where = "IBS_AC_BK = :LNRCNTI.KEY.IBS_AC_BK "
            + "AND CODE = :LNRCNTI.KEY.CODE";
        LNTCNTI_RD.fst = true;
        LNTCNTI_RD.order = "EFF_DATE DESC";
        IBS.READ(SCCGWA, LNRCNTI, this, LNTCNTI_RD);
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRCNTI.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRCNTI.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNRCNTI.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTCNTI_RD = new DBParm();
        LNTCNTI_RD.TableName = "LNTCNTI";
        LNTCNTI_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRCNTI, LNTCNTI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRCNTI.RC);
            LNCRCNTI.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTCNTI_RD = new DBParm();
        LNTCNTI_RD.TableName = "LNTCNTI";
        LNTCNTI_RD.upd = true;
        IBS.READ(SCCGWA, LNRCNTI, LNTCNTI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRCNTI.RC);
            LNCRCNTI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRCNTI.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRCNTI.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNRCNTI.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTCNTI_RD = new DBParm();
        LNTCNTI_RD.TableName = "LNTCNTI";
        IBS.REWRITE(SCCGWA, LNRCNTI, LNTCNTI_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTCNTI_RD = new DBParm();
        LNTCNTI_RD.TableName = "LNTCNTI";
        IBS.DELETE(SCCGWA, LNRCNTI, LNTCNTI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRCNTI.RC);
            LNCRCNTI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC_BY_ALL() throws IOException,SQLException,Exception {
        LNTCNTI_BR.rp = new DBParm();
        LNTCNTI_BR.rp.TableName = "LNTCNTI";
        LNTCNTI_BR.rp.order = "CODE";
        IBS.STARTBR(SCCGWA, LNRCNTI, LNTCNTI_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRCNTI, this, LNTCNTI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRCNTI.RC);
            LNCRCNTI.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTCNTI_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRCNTI.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCRCNTI=");
            CEP.TRC(SCCGWA, LNCRCNTI);
            CEP.TRC(SCCGWA, "LNRCNTI =");
            CEP.TRC(SCCGWA, LNRCNTI);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
