package com.hisun.LN;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRPSQ {
    DBParm LNTPAYSQ_RD;
    brParm LNTPAYSQ_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRPAYSQ LNRPAYSQ = new LNRPAYSQ();
    SCCGWA SCCGWA;
    LNCRPSQ LNCRPSQ;
    LNRPAYSQ LNRPAYSQ1;
    public void MP(SCCGWA SCCGWA, LNCRPSQ LNCRPSQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRPSQ = LNCRPSQ;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRPSQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "A00-INIT-PROC START ");
        LNRPAYSQ1 = (LNRPAYSQ) LNCRPSQ.REC_PTR;
        LNCRPSQ.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRPAYSQ);
        CEP.TRC(SCCGWA, LNCRPSQ);
        CEP.TRC(SCCGWA, LNRPAYSQ);
        IBS.CLONE(SCCGWA, LNRPAYSQ1, LNRPAYSQ);
        LNCRPSQ.RC.RC_MMO = "LN";
        LNCRPSQ.RC.RC_CODE = 0;
        if (LNRPAYSQ.KEY.IBS_AC_BK.equalsIgnoreCase(" ")
            || LNRPAYSQ.KEY.IBS_AC_BK.equalsIgnoreCase("0")
            || LNRPAYSQ.KEY.IBS_AC_BK.equalsIgnoreCase("000")) {
            LNRPAYSQ.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        }
        CEP.TRC(SCCGWA, "A00-INIT-PROC END");
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_CHECK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRPAYSQ.KEY.CODE);
        if (LNCRPSQ.FUNC == 'I') {
            T000_READ_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRPSQ.FUNC == 'Y') {
            T000_READ_REC_PROC_1();
            if (pgmRtn) return;
        } else if (LNCRPSQ.FUNC == 'A') {
            T000_WRITE_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRPSQ.FUNC == 'R') {
            T000_READ_UPDATE_PROC();
            if (pgmRtn) return;
        } else if (LNCRPSQ.FUNC == 'U') {
            T000_REWRITE_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRPSQ.FUNC == 'D') {
            T000_DELETE_REC_PROC();
            if (pgmRtn) return;
        } else if (LNCRPSQ.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRPSQ.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRPAYSQ, LNRPAYSQ1);
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B01-CHECK START");
        CEP.TRC(SCCGWA, LNRPAYSQ.KEY.EFF_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (LNRPAYSQ.KEY.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE 
            && LNCRPSQ.FUNC == 'A' 
            && LNCRPSQ.FUNC == 'Y') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_INV_EFF_DT, LNCRPSQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((LNCRPSQ.FUNC == 'A' 
            || LNCRPSQ.FUNC == 'U') 
            && LNRPAYSQ.EXP_DATE == 0) {
            LNRPAYSQ.EXP_DATE = 20991231;
        }
        if ((LNCRPSQ.FUNC == 'A' 
            || LNCRPSQ.FUNC == 'U') 
            && LNRPAYSQ.EXP_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_INV_EXP_DT, LNCRPSQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (LNCRPSQ.OPT == 'S') {
            B060_20_STBR_BY_ALL();
            if (pgmRtn) return;
        } else if (LNCRPSQ.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRPSQ.OPT == 'E') {
            B060_40_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRPSQ.OPT + ")";
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
        CEP.TRC(SCCGWA, LNRPAYSQ.KEY.IBS_AC_BK);
        CEP.TRC(SCCGWA, LNRPAYSQ.KEY.CODE);
        LNTPAYSQ_RD = new DBParm();
        LNTPAYSQ_RD.TableName = "LNTPAYSQ";
        LNTPAYSQ_RD.where = "IBS_AC_BK = :LNRPAYSQ.KEY.IBS_AC_BK "
            + "AND CODE = :LNRPAYSQ.KEY.CODE";
        LNTPAYSQ_RD.fst = true;
        LNTPAYSQ_RD.order = "EFF_DATE DESC";
        IBS.READ(SCCGWA, LNRPAYSQ, this, LNTPAYSQ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            LNCRPSQ.RETURN_INFO = 'F';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRPSQ.RC);
            LNCRPSQ.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_REC_PROC_1() throws IOException,SQLException,Exception {
        LNTPAYSQ_RD = new DBParm();
        LNTPAYSQ_RD.TableName = "LNTPAYSQ";
        LNTPAYSQ_RD.where = "IBS_AC_BK = :LNRPAYSQ.KEY.IBS_AC_BK "
            + "AND CODE = :LNRPAYSQ.KEY.CODE";
        LNTPAYSQ_RD.fst = true;
        LNTPAYSQ_RD.order = "EFF_DATE DESC";
        IBS.READ(SCCGWA, LNRPAYSQ, this, LNTPAYSQ_RD);
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRPAYSQ.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPAYSQ.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNRPAYSQ.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPAYSQ_RD = new DBParm();
        LNTPAYSQ_RD.TableName = "LNTPAYSQ";
        LNTPAYSQ_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRPAYSQ, LNTPAYSQ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRPSQ.RC);
            LNCRPSQ.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTPAYSQ_RD = new DBParm();
        LNTPAYSQ_RD.TableName = "LNTPAYSQ";
        LNTPAYSQ_RD.upd = true;
        IBS.READ(SCCGWA, LNRPAYSQ, LNTPAYSQ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRPSQ.RC);
            LNCRPSQ.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRPAYSQ.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPAYSQ.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNRPAYSQ.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPAYSQ_RD = new DBParm();
        LNTPAYSQ_RD.TableName = "LNTPAYSQ";
        IBS.REWRITE(SCCGWA, LNRPAYSQ, LNTPAYSQ_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTPAYSQ_RD = new DBParm();
        LNTPAYSQ_RD.TableName = "LNTPAYSQ";
        IBS.DELETE(SCCGWA, LNRPAYSQ, LNTPAYSQ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRPSQ.RC);
            LNCRPSQ.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC_BY_ALL() throws IOException,SQLException,Exception {
        LNTPAYSQ_BR.rp = new DBParm();
        LNTPAYSQ_BR.rp.TableName = "LNTPAYSQ";
        LNTPAYSQ_BR.rp.order = "CODE";
        IBS.STARTBR(SCCGWA, LNRPAYSQ, LNTPAYSQ_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRPAYSQ, this, LNTPAYSQ_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.NOT_FUND, LNCRPSQ.RC);
            LNCRPSQ.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTPAYSQ_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRPSQ.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCRPSQ=");
            CEP.TRC(SCCGWA, LNCRPSQ);
            CEP.TRC(SCCGWA, "LNRPAYSQ =");
            CEP.TRC(SCCGWA, LNRPAYSQ);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
