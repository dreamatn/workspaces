package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRCONT {
    DBParm LNTCONT_RD;
    brParm LNTCONT_BR = new brParm();
    boolean pgmRtn = false;
    String WS_FATHER_CONTRACT = " ";
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRCONT LNRCONT = new LNRCONT();
    SCCGWA SCCGWA;
    LNCRCONT LNCRCONT;
    LNRCONT LNRCONT1;
    public void MP(SCCGWA SCCGWA, LNCRCONT LNCRCONT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRCONT = LNCRCONT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRCONT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRCONT.KEY.CONTRACT_NO);
        LNRCONT1 = (LNRCONT) LNCRCONT.REC_PTR;
        LNCRCONT.RETURN_INFO = 'F';
        CEP.TRC(SCCGWA, "1111111");
        CEP.TRC(SCCGWA, "2222222");
        IBS.CLONE(SCCGWA, LNRCONT1, LNRCONT);
        LNCRCONT.RC.RC_MMO = "LN";
        LNCRCONT.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, LNRCONT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRCONT.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRCONT.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRCONT.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRCONT.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRCONT.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRCONT.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRCONT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRCONT, LNRCONT1);
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
        if (LNCRCONT.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRCONT.OPT == 'A') {
            B060_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRCONT.OPT == 'P') {
            B060_20_STBR_FTH_CTA_TYP_PROC();
            if (pgmRtn) return;
        } else if (LNCRCONT.OPT == 'C') {
            B060_20_STBR_CONT_TYPE();
            if (pgmRtn) return;
        } else if (LNCRCONT.OPT == 'F') {
            B060_20_STBR_BY_FATHER();
            if (pgmRtn) return;
        } else if (LNCRCONT.OPT == 'N') {
            B060_20_STBR_BY_FTH_CTA_REQ();
            if (pgmRtn) return;
        } else if (LNCRCONT.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRCONT.OPT == 'E') {
            B060_50_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRCONT.OPT + ")";
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
    public void B060_20_STBR_FTH_CTA_TYP_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_FTH_CTA_TYP();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_CONT_TYPE() throws IOException,SQLException,Exception {
        T000_STARTBR_CONT_TYPE();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_FATHER() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_FATHER();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_FTH_CTA_REQ() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_FTH_CTA_REQ();
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
        CEP.TRC(SCCGWA, LNRCONT.KEY.CONTRACT_NO);
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ASDASDASDAAA");
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CONT_NOTFND, LNCRCONT.RC);
            CEP.TRC(SCCGWA, LNCRCONT.RC);
            LNCRCONT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRCONT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRCONT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRCONT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRCONT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        LNTCONT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRCONT, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRCONT.RC);
            LNCRCONT.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        LNTCONT_RD.upd = true;
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CONT_NOTFND, LNCRCONT.RC);
            LNCRCONT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRCONT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRCONT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.REWRITE(SCCGWA, LNRCONT, LNTCONT_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.DELETE(SCCGWA, LNRCONT, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CONT_NOTFND, LNCRCONT.RC);
            LNCRCONT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTCONT_BR.rp = new DBParm();
        LNTCONT_BR.rp.TableName = "LNTCONT";
        LNTCONT_BR.rp.order = "CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRCONT, LNTCONT_BR);
    }
    public void T000_STARTBR_BY_FTH_CTA_REQ() throws IOException,SQLException,Exception {
        WS_FATHER_CONTRACT = LNCRCONT.FATHER_CTA_REQ;
        LNTCONT_BR.rp = new DBParm();
        LNTCONT_BR.rp.TableName = "LNTCONT";
        LNTCONT_BR.rp.where = "FATHER_CONTRACT = :WS_FATHER_CONTRACT";
        LNTCONT_BR.rp.order = "FATHER_CONTRACT";
        IBS.STARTBR(SCCGWA, LNRCONT, this, LNTCONT_BR);
    }
    public void T000_STARTBR_FTH_CTA_TYP() throws IOException,SQLException,Exception {
        LNTCONT_BR.rp = new DBParm();
        LNTCONT_BR.rp.TableName = "LNTCONT";
        LNTCONT_BR.rp.where = "FATHER_CONTRACT = :LNRCONT.FATHER_CONTRACT "
            + "AND CONTRACT_TYPE = :LNRCONT.CONTRACT_TYPE";
        LNTCONT_BR.rp.order = "CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRCONT, this, LNTCONT_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTCONT_BR.rp = new DBParm();
        LNTCONT_BR.rp.TableName = "LNTCONT";
        LNTCONT_BR.rp.where = "CONTRACT_NO >= :LNRCONT.KEY.CONTRACT_NO";
        LNTCONT_BR.rp.order = "CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRCONT, this, LNTCONT_BR);
    }
    public void T000_STARTBR_CONT_TYPE() throws IOException,SQLException,Exception {
        LNTCONT_BR.rp = new DBParm();
        LNTCONT_BR.rp.TableName = "LNTCONT";
        LNTCONT_BR.rp.eqWhere = "CONTRACT_NO,CONTRACT_TYPE";
        IBS.STARTBR(SCCGWA, LNRCONT, LNTCONT_BR);
    }
    public void T000_STARTBR_BY_FATHER() throws IOException,SQLException,Exception {
        LNTCONT_BR.rp = new DBParm();
        LNTCONT_BR.rp.TableName = "LNTCONT";
        LNTCONT_BR.rp.where = "FATHER_CONTRACT = :LNRCONT.FATHER_CONTRACT";
        LNTCONT_BR.rp.order = "CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRCONT, this, LNTCONT_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRCONT, this, LNTCONT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ZZZZZZZZZZZ");
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CONT_NOTFND, LNCRCONT.RC);
            LNCRCONT.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTCONT_BR);
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
