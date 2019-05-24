package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRMGSG {
    DBParm LNTMGSG_RD;
    brParm LNTMGSG_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRMGSG LNRMGSG = new LNRMGSG();
    SCCGWA SCCGWA;
    LNCRMGSG LNCRMGSG;
    LNRMGSG LNRMGSG1;
    public void MP(SCCGWA SCCGWA, LNCRMGSG LNCRMGSG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRMGSG = LNCRMGSG;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRMGSG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRMGSG1 = (LNRMGSG) LNCRMGSG.REC_PTR;
        LNCRMGSG.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRMGSG);
        IBS.CLONE(SCCGWA, LNRMGSG1, LNRMGSG);
        LNCRMGSG.RC.RC_MMO = "LN";
        LNCRMGSG.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRMGSG.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRMGSG.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRMGSG.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRMGSG.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRMGSG.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRMGSG.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRMGSG.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRMGSG, LNRMGSG1);
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_PROC();
        if (pgmRtn) return;
    }
    public void B020_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B030_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPDATE_PROC();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (LNCRMGSG.OPT == 'N') {
            B060_10_STBR_BY_LN_ACNO();
            if (pgmRtn) return;
        } else if (LNCRMGSG.OPT == 'S') {
            B060_20_STBR_BY_ALL();
            if (pgmRtn) return;
        } else if (LNCRMGSG.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRMGSG.OPT == 'E') {
            B060_40_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRMGSG.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_10_STBR_BY_LN_ACNO() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC_BY_LN_ACNO();
        if (pgmRtn) return;
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
        LNTMGSG_RD = new DBParm();
        LNTMGSG_RD.TableName = "LNTMGSG";
        LNTMGSG_RD.errhdl = true;
        IBS.READ(SCCGWA, LNRMGSG, LNTMGSG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "333333");
            LNCRMGSG.RETURN_INFO = 'F';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "4444444");
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MGSG_NOT_EXIST, LNCRMGSG.RC);
            LNCRMGSG.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRMGSG.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRMGSG.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRMGSG.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRMGSG.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, LNRMGSG.KEY.LN_ACNO);
        CEP.TRC(SCCGWA, LNRMGSG.KEY.SG_SEQNO);
        CEP.TRC(SCCGWA, LNRMGSG.MG_BUSI_KND);
        CEP.TRC(SCCGWA, LNRMGSG.CCY);
        CEP.TRC(SCCGWA, LNRMGSG.SG_STS);
        CEP.TRC(SCCGWA, LNRMGSG.MG_DCL_TYP);
        CEP.TRC(SCCGWA, LNRMGSG.LN_NM);
        CEP.TRC(SCCGWA, LNRMGSG.VAL_DT);
        CEP.TRC(SCCGWA, LNRMGSG.LN_BAL);
        CEP.TRC(SCCGWA, LNRMGSG.LN_P_ACNO);
        CEP.TRC(SCCGWA, LNRMGSG.LN_P_AC_CS);
        CEP.TRC(SCCGWA, LNRMGSG.LN_P_ACNM);
        CEP.TRC(SCCGWA, LNRMGSG.RM_ACNO);
        CEP.TRC(SCCGWA, LNRMGSG.RM_ACNM);
        CEP.TRC(SCCGWA, LNRMGSG.RM_F_DT);
        CEP.TRC(SCCGWA, LNRMGSG.RM_SP_DAY);
        CEP.TRC(SCCGWA, LNRMGSG.RM_DUE_DT);
        CEP.TRC(SCCGWA, LNRMGSG.RM_MON);
        CEP.TRC(SCCGWA, LNRMGSG.RM_M_FLG);
        CEP.TRC(SCCGWA, LNRMGSG.RM_STOP_MONTH);
        CEP.TRC(SCCGWA, LNRMGSG.RM_TERM_TOP_BAL);
        CEP.TRC(SCCGWA, LNRMGSG.RM_ONCE_TOP_BAL);
        CEP.TRC(SCCGWA, LNRMGSG.REMARK);
        CEP.TRC(SCCGWA, LNRMGSG.RM_TRY_TM);
        LNTMGSG_RD = new DBParm();
        LNTMGSG_RD.TableName = "LNTMGSG";
        IBS.WRITE(SCCGWA, LNRMGSG, LNTMGSG_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRMGSG.RC);
            LNCRMGSG.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRMGSG.KEY.LN_ACNO);
        CEP.TRC(SCCGWA, LNRMGSG.KEY.SG_SEQNO);
        CEP.TRC(SCCGWA, LNRMGSG.MG_BUSI_KND);
        CEP.TRC(SCCGWA, LNRMGSG.CCY);
        CEP.TRC(SCCGWA, LNRMGSG.SG_STS);
        CEP.TRC(SCCGWA, LNRMGSG.MG_DCL_TYP);
        CEP.TRC(SCCGWA, LNRMGSG.LN_NM);
        CEP.TRC(SCCGWA, LNRMGSG.VAL_DT);
        CEP.TRC(SCCGWA, LNRMGSG.LN_BAL);
        CEP.TRC(SCCGWA, LNRMGSG.LN_P_ACNO);
        CEP.TRC(SCCGWA, LNRMGSG.LN_P_AC_CS);
        CEP.TRC(SCCGWA, LNRMGSG.LN_P_ACNM);
        CEP.TRC(SCCGWA, LNRMGSG.RM_ACNO);
        CEP.TRC(SCCGWA, LNRMGSG.RM_ACNM);
        CEP.TRC(SCCGWA, LNRMGSG.RM_F_DT);
        CEP.TRC(SCCGWA, LNRMGSG.RM_SP_DAY);
        CEP.TRC(SCCGWA, LNRMGSG.RM_DUE_DT);
        CEP.TRC(SCCGWA, LNRMGSG.RM_MON);
        CEP.TRC(SCCGWA, LNRMGSG.RM_M_FLG);
        CEP.TRC(SCCGWA, LNRMGSG.RM_STOP_MONTH);
        CEP.TRC(SCCGWA, LNRMGSG.RM_TERM_TOP_BAL);
        CEP.TRC(SCCGWA, LNRMGSG.RM_ONCE_TOP_BAL);
        CEP.TRC(SCCGWA, LNRMGSG.REMARK);
        CEP.TRC(SCCGWA, LNRMGSG.RM_TRY_TM);
        LNTMGSG_RD = new DBParm();
        LNTMGSG_RD.TableName = "LNTMGSG";
        LNTMGSG_RD.upd = true;
        IBS.READ(SCCGWA, LNRMGSG, LNTMGSG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MGSG_NOT_EXIST, LNCRMGSG.RC);
            LNCRMGSG.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRMGSG.KEY.LN_ACNO);
        CEP.TRC(SCCGWA, LNRMGSG.KEY.SG_SEQNO);
        CEP.TRC(SCCGWA, LNRMGSG.MG_BUSI_KND);
        CEP.TRC(SCCGWA, LNRMGSG.CCY);
        CEP.TRC(SCCGWA, LNRMGSG.SG_STS);
        CEP.TRC(SCCGWA, LNRMGSG.MG_DCL_TYP);
        CEP.TRC(SCCGWA, LNRMGSG.LN_NM);
        CEP.TRC(SCCGWA, LNRMGSG.VAL_DT);
        CEP.TRC(SCCGWA, LNRMGSG.LN_BAL);
        CEP.TRC(SCCGWA, LNRMGSG.LN_P_ACNO);
        CEP.TRC(SCCGWA, LNRMGSG.LN_P_AC_CS);
        CEP.TRC(SCCGWA, LNRMGSG.LN_P_ACNM);
        CEP.TRC(SCCGWA, LNRMGSG.RM_ACNO);
        CEP.TRC(SCCGWA, LNRMGSG.RM_ACNM);
        CEP.TRC(SCCGWA, LNRMGSG.RM_F_DT);
        CEP.TRC(SCCGWA, LNRMGSG.RM_SP_DAY);
        CEP.TRC(SCCGWA, LNRMGSG.RM_DUE_DT);
        CEP.TRC(SCCGWA, LNRMGSG.RM_MON);
        CEP.TRC(SCCGWA, LNRMGSG.RM_M_FLG);
        CEP.TRC(SCCGWA, LNRMGSG.RM_STOP_MONTH);
        CEP.TRC(SCCGWA, LNRMGSG.RM_TERM_TOP_BAL);
        CEP.TRC(SCCGWA, LNRMGSG.RM_ONCE_TOP_BAL);
        CEP.TRC(SCCGWA, LNRMGSG.REMARK);
        CEP.TRC(SCCGWA, LNRMGSG.RM_TRY_TM);
        LNRMGSG.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRMGSG.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTMGSG_RD = new DBParm();
        LNTMGSG_RD.TableName = "LNTMGSG";
        IBS.REWRITE(SCCGWA, LNRMGSG, LNTMGSG_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRMGSG.KEY.LN_ACNO);
        CEP.TRC(SCCGWA, LNRMGSG.KEY.SG_SEQNO);
        CEP.TRC(SCCGWA, LNRMGSG.MG_BUSI_KND);
        CEP.TRC(SCCGWA, LNRMGSG.CCY);
        CEP.TRC(SCCGWA, LNRMGSG.SG_STS);
        CEP.TRC(SCCGWA, LNRMGSG.MG_DCL_TYP);
        CEP.TRC(SCCGWA, LNRMGSG.LN_NM);
        CEP.TRC(SCCGWA, LNRMGSG.VAL_DT);
        CEP.TRC(SCCGWA, LNRMGSG.LN_BAL);
        CEP.TRC(SCCGWA, LNRMGSG.LN_P_ACNO);
        CEP.TRC(SCCGWA, LNRMGSG.LN_P_AC_CS);
        CEP.TRC(SCCGWA, LNRMGSG.LN_P_ACNM);
        CEP.TRC(SCCGWA, LNRMGSG.RM_ACNO);
        CEP.TRC(SCCGWA, LNRMGSG.RM_ACNM);
        CEP.TRC(SCCGWA, LNRMGSG.RM_F_DT);
        CEP.TRC(SCCGWA, LNRMGSG.RM_SP_DAY);
        CEP.TRC(SCCGWA, LNRMGSG.RM_DUE_DT);
        CEP.TRC(SCCGWA, LNRMGSG.RM_MON);
        CEP.TRC(SCCGWA, LNRMGSG.RM_M_FLG);
        CEP.TRC(SCCGWA, LNRMGSG.RM_STOP_MONTH);
        CEP.TRC(SCCGWA, LNRMGSG.RM_TERM_TOP_BAL);
        CEP.TRC(SCCGWA, LNRMGSG.RM_ONCE_TOP_BAL);
        CEP.TRC(SCCGWA, LNRMGSG.REMARK);
        CEP.TRC(SCCGWA, LNRMGSG.RM_TRY_TM);
        LNTMGSG_RD = new DBParm();
        LNTMGSG_RD.TableName = "LNTMGSG";
        IBS.DELETE(SCCGWA, LNRMGSG, LNTMGSG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MGSG_NOT_EXIST, LNCRMGSG.RC);
            LNCRMGSG.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC_BY_LN_ACNO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRMGSG.KEY.LN_ACNO);
        CEP.TRC(SCCGWA, LNRMGSG.KEY.SG_SEQNO);
        CEP.TRC(SCCGWA, LNRMGSG.MG_BUSI_KND);
        CEP.TRC(SCCGWA, LNRMGSG.CCY);
        CEP.TRC(SCCGWA, LNRMGSG.SG_STS);
        CEP.TRC(SCCGWA, LNRMGSG.MG_DCL_TYP);
        CEP.TRC(SCCGWA, LNRMGSG.LN_NM);
        CEP.TRC(SCCGWA, LNRMGSG.VAL_DT);
        CEP.TRC(SCCGWA, LNRMGSG.LN_BAL);
        CEP.TRC(SCCGWA, LNRMGSG.LN_P_ACNO);
        CEP.TRC(SCCGWA, LNRMGSG.LN_P_AC_CS);
        CEP.TRC(SCCGWA, LNRMGSG.LN_P_ACNM);
        CEP.TRC(SCCGWA, LNRMGSG.RM_ACNO);
        CEP.TRC(SCCGWA, LNRMGSG.RM_ACNM);
        CEP.TRC(SCCGWA, LNRMGSG.RM_F_DT);
        CEP.TRC(SCCGWA, LNRMGSG.RM_SP_DAY);
        CEP.TRC(SCCGWA, LNRMGSG.RM_DUE_DT);
        CEP.TRC(SCCGWA, LNRMGSG.RM_MON);
        CEP.TRC(SCCGWA, LNRMGSG.RM_M_FLG);
        CEP.TRC(SCCGWA, LNRMGSG.RM_STOP_MONTH);
        CEP.TRC(SCCGWA, LNRMGSG.RM_TERM_TOP_BAL);
        CEP.TRC(SCCGWA, LNRMGSG.RM_ONCE_TOP_BAL);
        CEP.TRC(SCCGWA, LNRMGSG.REMARK);
        CEP.TRC(SCCGWA, LNRMGSG.RM_TRY_TM);
        LNTMGSG_BR.rp = new DBParm();
        LNTMGSG_BR.rp.TableName = "LNTMGSG";
        LNTMGSG_BR.rp.where = "LN_ACNO = :LNRMGSG.KEY.LN_ACNO";
        LNTMGSG_BR.rp.order = "SG_SEQNO";
        IBS.STARTBR(SCCGWA, LNRMGSG, this, LNTMGSG_BR);
    }
    public void T000_STARTBR_PROC_BY_ALL() throws IOException,SQLException,Exception {
        LNTMGSG_BR.rp = new DBParm();
        LNTMGSG_BR.rp.TableName = "LNTMGSG";
        LNTMGSG_BR.rp.order = "SG_SEQNO";
        IBS.STARTBR(SCCGWA, LNRMGSG, LNTMGSG_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRMGSG, this, LNTMGSG_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MGSG_NOT_EXIST, LNCRMGSG.RC);
            LNCRMGSG.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTMGSG_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRMGSG.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCRMGSG=");
            CEP.TRC(SCCGWA, LNCRMGSG);
            CEP.TRC(SCCGWA, "LNRMGSG =");
            CEP.TRC(SCCGWA, LNRMGSG);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
