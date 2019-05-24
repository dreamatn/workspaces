package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRRATH {
    DBParm LNTRATH_RD;
    brParm LNTRATH_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRRATH LNRRATH = new LNRRATH();
    SCCGWA SCCGWA;
    LNCRRATH LNCRRATH;
    LNRRATH LNRRATH1;
    public void MP(SCCGWA SCCGWA, LNCRRATH LNCRRATH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRRATH = LNCRRATH;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRRATH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRRATH1 = (LNRRATH) LNCRRATH.REC_PTR;
        LNCRRATH.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRRATH);
        IBS.CLONE(SCCGWA, LNRRATH1, LNRRATH);
        LNCRRATH.RC.RC_MMO = "LN";
        LNCRRATH.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRRATH.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATH.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATH.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATH.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATH.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATH.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRRATH.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRRATH, LNRRATH1);
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
        if (LNCRRATH.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATH.OPT == 'A') {
            B060_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATH.OPT == 'B') {
            B060_20_STBR_BY_KEY1_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATH.OPT == 'Y') {
            B060_20_STBR_BY_KEY2_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATH.OPT == 'Z') {
            B060_20_STBR_BY_KEY3_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATH.OPT == 'C') {
            B060_20_STBR_CTANO_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATH.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATH.OPT == 'E') {
            B060_50_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRRATH.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_10_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC();
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
    public void B060_20_STBR_CTANO_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_CTANO_PROC();
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
        LNTRATH_RD = new DBParm();
        LNTRATH_RD.TableName = "LNTRATH";
        IBS.READ(SCCGWA, LNRRATH, LNTRATH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATH_NOTFND, LNCRRATH.RC);
            LNCRRATH.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRRATH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRRATH.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRRATH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRRATH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTRATH_RD = new DBParm();
        LNTRATH_RD.TableName = "LNTRATH";
        LNTRATH_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRRATH, LNTRATH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRRATH.RC);
            LNCRRATH.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTRATH_RD = new DBParm();
        LNTRATH_RD.TableName = "LNTRATH";
        LNTRATH_RD.upd = true;
        IBS.READ(SCCGWA, LNRRATH, LNTRATH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATH_NOTFND, LNCRRATH.RC);
            LNCRRATH.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRRATH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRRATH.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTRATH_RD = new DBParm();
        LNTRATH_RD.TableName = "LNTRATH";
        IBS.REWRITE(SCCGWA, LNRRATH, LNTRATH_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTRATH_RD = new DBParm();
        LNTRATH_RD.TableName = "LNTRATH";
        IBS.DELETE(SCCGWA, LNRRATH, LNTRATH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATH_NOTFND, LNCRRATH.RC);
            LNCRRATH.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTRATH_BR.rp = new DBParm();
        LNTRATH_BR.rp.TableName = "LNTRATH";
        LNTRATH_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,RATE_TYP,RAT_CHG_DT";
        IBS.STARTBR(SCCGWA, LNRRATH, LNTRATH_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTRATH_BR.rp = new DBParm();
        LNTRATH_BR.rp.TableName = "LNTRATH";
        LNTRATH_BR.rp.where = "CONTRACT_NO >= :LNRRATH.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO >= :LNRRATH.KEY.SUB_CTA_NO "
            + "AND RATE_TYP >= :LNRRATH.KEY.RATE_TYP "
            + "AND RAT_CHG_DT >= :LNRRATH.KEY.RAT_CHG_DT";
        LNTRATH_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,RATE_TYP,RAT_CHG_DT";
        IBS.STARTBR(SCCGWA, LNRRATH, this, LNTRATH_BR);
    }
    public void T000_STARTBR_BY_KEY1_PROC() throws IOException,SQLException,Exception {
        LNTRATH_BR.rp = new DBParm();
        LNTRATH_BR.rp.TableName = "LNTRATH";
        LNTRATH_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
        LNTRATH_BR.rp.order = "RATE_TYP,RAT_CHG_DT";
        IBS.STARTBR(SCCGWA, LNRRATH, LNTRATH_BR);
    }
    public void T000_STARTBR_BY_KEY2_PROC() throws IOException,SQLException,Exception {
        LNTRATH_BR.rp = new DBParm();
        LNTRATH_BR.rp.TableName = "LNTRATH";
        LNTRATH_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,RATE_TYP";
        LNTRATH_BR.rp.order = "RAT_CHG_DT";
        IBS.STARTBR(SCCGWA, LNRRATH, LNTRATH_BR);
    }
    public void T000_STARTBR_BY_KEY3_PROC() throws IOException,SQLException,Exception {
        LNTRATH_BR.rp = new DBParm();
        LNTRATH_BR.rp.TableName = "LNTRATH";
        LNTRATH_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,RATE_TYP";
        LNTRATH_BR.rp.order = "RAT_CHG_DT";
        IBS.STARTBR(SCCGWA, LNRRATH, LNTRATH_BR);
    }
    public void T000_STARTBR_CTANO_PROC() throws IOException,SQLException,Exception {
        LNTRATH_BR.rp = new DBParm();
        LNTRATH_BR.rp.TableName = "LNTRATH";
        LNTRATH_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
        LNTRATH_BR.rp.order = "RATE_TYP, RAT_CHG_DT";
        IBS.STARTBR(SCCGWA, LNRRATH, LNTRATH_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRRATH.TS);
        IBS.READNEXT(SCCGWA, LNRRATH, this, LNTRATH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATH_NOTFND, LNCRRATH.RC);
            LNCRRATH.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRATH_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
