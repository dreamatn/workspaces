package com.hisun.LN;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRMODL {
    DBParm LNTMODL_RD;
    brParm LNTMODL_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRMODL LNRMODL = new LNRMODL();
    SCCGWA SCCGWA;
    LNCRMODL LNCRMODL;
    LNRMODL LNRMODL1;
    public void MP(SCCGWA SCCGWA, LNCRMODL LNCRMODL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRMODL = LNCRMODL;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRMODL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRMODL1 = (LNRMODL) LNCRMODL.REC_PTR;
        LNCRMODL.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRMODL);
        IBS.CLONE(SCCGWA, LNRMODL1, LNRMODL);
        LNCRMODL.RC.RC_MMO = "LN";
        LNCRMODL.RC.RC_CODE = 0;
        LNRMODL.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        CEP.TRC(SCCGWA, "SHI");
        CEP.TRC(SCCGWA, LNRMODL.KEY.IBS_AC_BK);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_CHECK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRMODL.KEY.CODE);
        if (LNCRMODL.FUNC == 'I') {
            T000_READ_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRMODL.FUNC == 'Y') {
            T000_READ_REC_PROC_1();
            if (pgmRtn) return;
        } else if (LNCRMODL.FUNC == 'A') {
            T000_WRITE_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRMODL.FUNC == 'R') {
            T000_READ_UPDATE_PROC();
            if (pgmRtn) return;
        } else if (LNCRMODL.FUNC == 'U') {
            T000_REWRITE_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRMODL.FUNC == 'D') {
            T000_DELETE_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRMODL.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRMODL.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRMODL, LNRMODL1);
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
        if (LNRMODL.KEY.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE 
            && LNCRMODL.FUNC == 'A' 
            || LNCRMODL.FUNC == 'Y') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_INV_EFF_DT, LNCRMODL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((LNCRMODL.FUNC == 'A' 
            || LNCRMODL.FUNC == 'U') 
            && LNRMODL.EXP_DATE == 0) {
            LNRMODL.EXP_DATE = 20991231;
        }
        if ((LNCRMODL.FUNC == 'A' 
            || LNCRMODL.FUNC == 'U') 
            && LNRMODL.EXP_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_INV_EXP_DT, LNCRMODL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (LNCRMODL.OPT == 'S') {
            B060_20_STBR_BY_ALL();
            if (pgmRtn) return;
        } else if (LNCRMODL.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRMODL.OPT == 'E') {
            B060_40_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRMODL.OPT + ")";
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
        LNTMODL_RD = new DBParm();
        LNTMODL_RD.TableName = "LNTMODL";
        LNTMODL_RD.where = "IBS_AC_BK = :LNRMODL.KEY.IBS_AC_BK "
            + "AND CODE = :LNRMODL.KEY.CODE";
        LNTMODL_RD.fst = true;
        LNTMODL_RD.order = "EFF_DATE DESC";
        IBS.READ(SCCGWA, LNRMODL, this, LNTMODL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            LNCRMODL.RETURN_INFO = 'F';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRMODL.RC);
            LNCRMODL.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_REC_PROC_1() throws IOException,SQLException,Exception {
        LNTMODL_RD = new DBParm();
        LNTMODL_RD.TableName = "LNTMODL";
        LNTMODL_RD.where = "IBS_AC_BK = :LNRMODL.KEY.IBS_AC_BK "
            + "AND CODE = :LNRMODL.KEY.CODE";
        LNTMODL_RD.fst = true;
        LNTMODL_RD.order = "EFF_DATE DESC";
        IBS.READ(SCCGWA, LNRMODL, this, LNTMODL_RD);
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRMODL.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRMODL.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNRMODL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTMODL_RD = new DBParm();
        LNTMODL_RD.TableName = "LNTMODL";
        LNTMODL_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRMODL, LNTMODL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRMODL.RC);
            LNCRMODL.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTMODL_RD = new DBParm();
        LNTMODL_RD.TableName = "LNTMODL";
        LNTMODL_RD.upd = true;
        IBS.READ(SCCGWA, LNRMODL, LNTMODL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRMODL.RC);
            LNCRMODL.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRMODL.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRMODL.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNRMODL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTMODL_RD = new DBParm();
        LNTMODL_RD.TableName = "LNTMODL";
        IBS.REWRITE(SCCGWA, LNRMODL, LNTMODL_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTMODL_RD = new DBParm();
        LNTMODL_RD.TableName = "LNTMODL";
        IBS.DELETE(SCCGWA, LNRMODL, LNTMODL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRMODL.RC);
            LNCRMODL.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC_BY_ALL() throws IOException,SQLException,Exception {
        LNTMODL_BR.rp = new DBParm();
        LNTMODL_BR.rp.TableName = "LNTMODL";
        LNTMODL_BR.rp.order = "CODE";
        IBS.STARTBR(SCCGWA, LNRMODL, LNTMODL_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRMODL, this, LNTMODL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRMODL.RC);
            LNCRMODL.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTMODL_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRMODL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCRMODL=");
            CEP.TRC(SCCGWA, LNCRMODL);
            CEP.TRC(SCCGWA, "LNRMODL =");
            CEP.TRC(SCCGWA, LNRMODL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
