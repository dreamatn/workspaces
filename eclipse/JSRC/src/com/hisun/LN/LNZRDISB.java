package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRDISB {
    DBParm LNTDISB_RD;
    brParm LNTDISB_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRDISB LNRDISB = new LNRDISB();
    int WS_TOT_CNT = 0;
    double WS_TOT_SUM = 0;
    int WS_STR_DATE = 0;
    int WS_END_DATE = 0;
    SCCGWA SCCGWA;
    LNCRDISB LNCRDISB;
    LNRDISB LNRDISB1;
    public void MP(SCCGWA SCCGWA, LNCRDISB LNCRDISB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRDISB = LNCRDISB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRDISB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LNRDISB1 = (LNRDISB) LNCRDISB.REC_PTR;
        LNCRDISB.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRDISB);
        IBS.CLONE(SCCGWA, LNRDISB1, LNRDISB);
        LNCRDISB.RC.RC_MMO = "LN";
        LNCRDISB.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRDISB.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRDISB.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRDISB.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRDISB.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRDISB.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRDISB.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRDISB.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRDISB, LNRDISB1);
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
        if (LNCRDISB.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRDISB.OPT == 'A') {
            B060_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRDISB.OPT == 'C') {
            B060_20_STBR_BY_KEY2_PROC();
            if (pgmRtn) return;
        } else if (LNCRDISB.OPT == 'F') {
            B060_20_STBR_BY_KEY3_PROC();
            if (pgmRtn) return;
        } else if (LNCRDISB.OPT == 'O') {
            B060_20_STBR_BY_KEY1_PROC();
            if (pgmRtn) return;
        } else if (LNCRDISB.OPT == 'M') {
            B060_20_STBR_BY_SUM_AMT_PROC();
            if (pgmRtn) return;
        } else if (LNCRDISB.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRDISB.OPT == 'E') {
            B060_50_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRDISB.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_10_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_SUM_AMT_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_SUM_AMT_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_KEY1_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY1_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_KEY2_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY2_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_KEY3_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY3_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_PROC();
        if (pgmRtn) return;
    }
    public void B060_50_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        LNTDISB_RD = new DBParm();
        LNTDISB_RD.TableName = "LNTDISB";
        IBS.READ(SCCGWA, LNRDISB, LNTDISB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_DISB_NOTFND, LNCRDISB.RC);
            LNCRDISB.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRDISB.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRDISB.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRDISB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRDISB.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTDISB_RD = new DBParm();
        LNTDISB_RD.TableName = "LNTDISB";
        LNTDISB_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRDISB, LNTDISB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRDISB.RC);
            LNCRDISB.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTDISB_RD = new DBParm();
        LNTDISB_RD.TableName = "LNTDISB";
        LNTDISB_RD.upd = true;
        IBS.READ(SCCGWA, LNRDISB, LNTDISB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_DISB_NOTFND, LNCRDISB.RC);
            LNCRDISB.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRDISB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRDISB.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTDISB_RD = new DBParm();
        LNTDISB_RD.TableName = "LNTDISB";
        IBS.REWRITE(SCCGWA, LNRDISB, LNTDISB_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTDISB_RD = new DBParm();
        LNTDISB_RD.TableName = "LNTDISB";
        IBS.DELETE(SCCGWA, LNRDISB, LNTDISB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_DISB_NOTFND, LNCRDISB.RC);
            LNCRDISB.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTDISB_BR.rp = new DBParm();
        LNTDISB_BR.rp.TableName = "LNTDISB";
        LNTDISB_BR.rp.order = "DISB_REF,TR_VAL_DTE,CTA_NO,CTA_SEQ,AMT_TYP,TERM";
        IBS.STARTBR(SCCGWA, LNRDISB, LNTDISB_BR);
    }
    public void T000_STARTBR_BY_SUM_AMT_PROC() throws IOException,SQLException,Exception {
        WS_TOT_CNT = 0;
        WS_TOT_SUM = 0;
        LNTDISB_BR.rp = new DBParm();
        LNTDISB_BR.rp.TableName = "LNTDISB";
        LNTDISB_BR.rp.set = "WS-TOT-CNT=COUNT(*),WS-TOT-SUM=SUM(D_P_AMT)";
        LNTDISB_BR.rp.where = "DISB_REF = :LNRDISB.KEY.DISB_REF "
            + "AND TR_VAL_DTE = :LNRDISB.KEY.TR_VAL_DTE "
            + "AND AMT_TYP = :LNRDISB.KEY.AMT_TYP "
            + "AND TERM = :LNRDISB.KEY.TERM "
            + "AND PART_ROLE = 'N' "
            + "AND CTA_SEQ > 0";
        LNTDISB_BR.rp.grp = "DISB_REF,TR_VAL_DTE,AMT_TYP,TERM";
        LNTDISB_BR.rp.order = "DISB_REF,TR_VAL_DTE,AMT_TYP,TERM";
        IBS.STARTBR(SCCGWA, LNRDISB, this, LNTDISB_BR);
        LNCRDISB.TOT_CNT = WS_TOT_CNT;
        LNCRDISB.TOT_SUM = WS_TOT_SUM;
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTDISB_BR.rp = new DBParm();
        LNTDISB_BR.rp.TableName = "LNTDISB";
        LNTDISB_BR.rp.where = "DISB_REF >= :LNRDISB.KEY.DISB_REF "
            + "AND TR_VAL_DTE >= :LNRDISB.KEY.TR_VAL_DTE "
            + "AND CTA_NO >= :LNRDISB.KEY.CTA_NO "
            + "AND CTA_SEQ >= :LNRDISB.KEY.CTA_SEQ "
            + "AND AMT_TYP >= :LNRDISB.KEY.AMT_TYP "
            + "AND TERM >= :LNRDISB.KEY.TERM";
        LNTDISB_BR.rp.order = "DISB_REF,TR_VAL_DTE,CTA_NO,CTA_SEQ,AMT_TYP,TERM";
        IBS.STARTBR(SCCGWA, LNRDISB, this, LNTDISB_BR);
    }
    public void T000_STARTBR_BY_KEY1_PROC() throws IOException,SQLException,Exception {
        LNTDISB_BR.rp = new DBParm();
        LNTDISB_BR.rp.TableName = "LNTDISB";
        LNTDISB_BR.rp.where = "DISB_REF = :LNRDISB.KEY.DISB_REF "
            + "AND TR_VAL_DTE = :LNRDISB.KEY.TR_VAL_DTE "
            + "AND CTA_NO = :LNRDISB.KEY.CTA_NO "
            + "AND CTA_SEQ = :LNRDISB.KEY.CTA_SEQ";
        LNTDISB_BR.rp.order = "DUE_DTE, AMT_TYP";
        IBS.STARTBR(SCCGWA, LNRDISB, this, LNTDISB_BR);
    }
    public void T000_STARTBR_BY_KEY2_PROC() throws IOException,SQLException,Exception {
        LNTDISB_BR.rp = new DBParm();
        LNTDISB_BR.rp.TableName = "LNTDISB";
        LNTDISB_BR.rp.where = "DISB_REF = :LNRDISB.KEY.DISB_REF "
            + "AND TR_VAL_DTE = :LNRDISB.KEY.TR_VAL_DTE "
            + "AND CTA_NO = :LNRDISB.KEY.CTA_NO "
            + "AND AMT_TYP = :LNRDISB.KEY.AMT_TYP "
            + "AND TERM = :LNRDISB.KEY.TERM "
            + "AND LENDER_TYP = :LNRDISB.LENDER_TYP "
            + "AND LENDER_PART_NO = :LNRDISB.LENDER_PART_NO";
        LNTDISB_BR.rp.order = "DUE_DTE, AMT_TYP";
        IBS.STARTBR(SCCGWA, LNRDISB, this, LNTDISB_BR);
    }
    public void T000_STARTBR_BY_KEY3_PROC() throws IOException,SQLException,Exception {
        LNTDISB_BR.rp = new DBParm();
        LNTDISB_BR.rp.TableName = "LNTDISB";
        LNTDISB_BR.rp.where = "DISB_REF = :LNRDISB.KEY.DISB_REF "
            + "AND TR_VAL_DTE = :LNRDISB.KEY.TR_VAL_DTE "
            + "AND CTA_NO = :LNRDISB.KEY.CTA_NO "
            + "AND AMT_TYP = :LNRDISB.KEY.AMT_TYP "
            + "AND TERM = :LNRDISB.KEY.TERM";
        LNTDISB_BR.rp.order = "CTA_SEQ";
        IBS.STARTBR(SCCGWA, LNRDISB, this, LNTDISB_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRDISB, this, LNTDISB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_DISB_NOTFND, LNCRDISB.RC);
            LNCRDISB.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTDISB_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRDISB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCRDISB=");
            CEP.TRC(SCCGWA, LNCRDISB);
            CEP.TRC(SCCGWA, "LNRDISB =");
            CEP.TRC(SCCGWA, LNRDISB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
