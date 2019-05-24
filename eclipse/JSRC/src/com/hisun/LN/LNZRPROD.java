package com.hisun.LN;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRPROD {
    DBParm LNTPROD_RD;
    brParm LNTPROD_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRPROD LNRPROD = new LNRPROD();
    SCCGWA SCCGWA;
    LNCRPROD LNCRPROD;
    LNRPROD LNRPROD1;
    public void MP(SCCGWA SCCGWA, LNCRPROD LNCRPROD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRPROD = LNCRPROD;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRPROD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRPROD1 = (LNRPROD) LNCRPROD.REC_PTR;
        LNCRPROD.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRPROD);
        IBS.CLONE(SCCGWA, LNRPROD1, LNRPROD);
        LNCRPROD.RC.RC_MMO = "LN";
        LNCRPROD.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        LNRPROD.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        CEP.TRC(SCCGWA, LNRPROD.KEY.IBS_AC_BK);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_CHECK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRPROD.KEY.CODE);
        if (LNCRPROD.FUNC == 'I') {
            T000_READ_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRPROD.FUNC == 'Y') {
            T000_READ_REC_PROC_1();
            if (pgmRtn) return;
        } else if (LNCRPROD.FUNC == 'A') {
            T000_WRITE_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRPROD.FUNC == 'R') {
            T000_READ_UPDATE_PROC();
            if (pgmRtn) return;
        } else if (LNCRPROD.FUNC == 'U') {
            T000_REWRITE_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRPROD.FUNC == 'D') {
            T000_DELETE_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRPROD.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRPROD.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRPROD, LNRPROD1);
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
        if (LNRPROD.KEY.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE 
            && LNCRPROD.FUNC == 'A' 
            || LNCRPROD.FUNC == 'Y') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_INV_EFF_DT, LNCRPROD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((LNCRPROD.FUNC == 'A' 
            || LNCRPROD.FUNC == 'U') 
            && LNRPROD.EXP_DATE == 0) {
            LNRPROD.EXP_DATE = 20991231;
        }
        if ((LNCRPROD.FUNC == 'A' 
            || LNCRPROD.FUNC == 'U') 
            && LNRPROD.EXP_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_INV_EXP_DT, LNCRPROD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (LNCRPROD.OPT == 'S') {
            B060_20_STBR_BY_ALL();
            if (pgmRtn) return;
        } else if (LNCRPROD.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRPROD.OPT == 'E') {
            B060_40_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRPROD.OPT + ")";
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
        CEP.TRC(SCCGWA, LNRPROD.KEY.IBS_AC_BK);
        CEP.TRC(SCCGWA, LNRPROD.KEY.CODE);
        LNTPROD_RD = new DBParm();
        LNTPROD_RD.TableName = "LNTPROD";
        LNTPROD_RD.where = "IBS_AC_BK = :LNRPROD.KEY.IBS_AC_BK "
            + "AND CODE = :LNRPROD.KEY.CODE";
        LNTPROD_RD.fst = true;
        LNTPROD_RD.order = "EFF_DATE DESC";
        IBS.READ(SCCGWA, LNRPROD, this, LNTPROD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            LNCRPROD.RETURN_INFO = 'F';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRPROD.RC);
            LNCRPROD.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_REC_PROC_1() throws IOException,SQLException,Exception {
        LNTPROD_RD = new DBParm();
        LNTPROD_RD.TableName = "LNTPROD";
        LNTPROD_RD.where = "IBS_AC_BK = :LNRPROD.KEY.IBS_AC_BK "
            + "AND CODE = :LNRPROD.KEY.CODE";
        LNTPROD_RD.fst = true;
        LNTPROD_RD.order = "EFF_DATE DESC";
        IBS.READ(SCCGWA, LNRPROD, this, LNTPROD_RD);
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRPROD.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPROD.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNRPROD.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, LNRPROD);
        LNTPROD_RD = new DBParm();
        LNTPROD_RD.TableName = "LNTPROD";
        IBS.WRITE(SCCGWA, LNRPROD, LNTPROD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRPROD.RC);
            LNCRPROD.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTPROD_RD = new DBParm();
        LNTPROD_RD.TableName = "LNTPROD";
        LNTPROD_RD.upd = true;
        IBS.READ(SCCGWA, LNRPROD, LNTPROD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRPROD.RC);
            LNCRPROD.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRPROD.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPROD.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNRPROD.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPROD_RD = new DBParm();
        LNTPROD_RD.TableName = "LNTPROD";
        IBS.REWRITE(SCCGWA, LNRPROD, LNTPROD_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTPROD_RD = new DBParm();
        LNTPROD_RD.TableName = "LNTPROD";
        IBS.DELETE(SCCGWA, LNRPROD, LNTPROD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRPROD.RC);
            LNCRPROD.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC_BY_ALL() throws IOException,SQLException,Exception {
        LNTPROD_BR.rp = new DBParm();
        LNTPROD_BR.rp.TableName = "LNTPROD";
        LNTPROD_BR.rp.order = "CODE";
        IBS.STARTBR(SCCGWA, LNRPROD, LNTPROD_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRPROD, this, LNTPROD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRPROD.RC);
            LNCRPROD.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTPROD_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRPROD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCRPROD=");
            CEP.TRC(SCCGWA, LNCRPROD);
            CEP.TRC(SCCGWA, "LNRPROD =");
            CEP.TRC(SCCGWA, LNRPROD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
