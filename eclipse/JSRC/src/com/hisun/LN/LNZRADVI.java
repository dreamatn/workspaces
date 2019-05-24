package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRADVI {
    DBParm LNTADVI_RD;
    brParm LNTADVI_BR = new brParm();
    boolean pgmRtn = false;
    int WS_TOT_COUNT = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRADVI LNRADVI = new LNRADVI();
    SCCGWA SCCGWA;
    LNCRADVI LNCRADVI;
    LNRADVI LNRADVIA;
    public void MP(SCCGWA SCCGWA, LNCRADVI LNCRADVI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRADVI = LNCRADVI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRADVI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LNRADVIA = (LNRADVI) LNCRADVI.REC_PTR;
        LNCRADVI.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRADVI);
        IBS.CLONE(SCCGWA, LNRADVIA, LNRADVI);
        LNCRADVI.RC.RC_MMO = "LN";
        LNCRADVI.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRADVI.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRADVI.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRADVI.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRADVI.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRADVI.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRADVI.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRADVI.FUNC == 'G') {
            B070_GROUP_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRADVI.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRADVI, LNRADVIA);
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
        if (LNCRADVI.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRADVI.OPT == 'A') {
            B060_30_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRADVI.OPT == 'C') {
            B060_40_STBR_CTA_NO_TR_DT();
            if (pgmRtn) return;
        } else if (LNCRADVI.OPT == 'R') {
            B060_50_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRADVI.OPT == 'E') {
            B060_70_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRADVI.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_10_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_STBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY_PROC();
        if (pgmRtn) return;
    }
    public void B060_40_STBR_CTA_NO_TR_DT() throws IOException,SQLException,Exception {
        T000_STARTBR_CTA_NO_TR_DT();
        if (pgmRtn) return;
    }
    public void B060_50_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_PROC();
        if (pgmRtn) return;
    }
    public void B060_70_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void B070_GROUP_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_GROUP_REC_PROC();
        if (pgmRtn) return;
        LNCRADVI.TOT_COUNT = WS_TOT_COUNT;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        LNTADVI_RD = new DBParm();
        LNTADVI_RD.TableName = "LNTADVI";
        IBS.READ(SCCGWA, LNRADVI, LNTADVI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ADVI_NOTFND, LNCRADVI.RC);
            LNCRADVI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRADVI.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRADVI.CRT_TELR = SCCGWA.COMM_AREA.TL_ID;
        LNRADVI.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRADVI.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTADVI_RD = new DBParm();
        LNTADVI_RD.TableName = "LNTADVI";
        LNTADVI_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRADVI, LNTADVI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRADVI.RC);
            LNCRADVI.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTADVI_RD = new DBParm();
        LNTADVI_RD.TableName = "LNTADVI";
        LNTADVI_RD.upd = true;
        IBS.READ(SCCGWA, LNRADVI, LNTADVI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ADVI_NOTFND, LNCRADVI.RC);
            LNCRADVI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRADVI.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRADVI.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTADVI_RD = new DBParm();
        LNTADVI_RD.TableName = "LNTADVI";
        IBS.REWRITE(SCCGWA, LNRADVI, LNTADVI_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTADVI_RD = new DBParm();
        LNTADVI_RD.TableName = "LNTADVI";
        IBS.DELETE(SCCGWA, LNRADVI, LNTADVI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ADVI_NOTFND, LNCRADVI.RC);
            LNCRADVI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTADVI_BR.rp = new DBParm();
        LNTADVI_BR.rp.TableName = "LNTADVI";
        LNTADVI_BR.rp.order = "CTA_NO,TR_DATE,JRN_NO,ADV_TYP";
        IBS.STARTBR(SCCGWA, LNRADVI, LNTADVI_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTADVI_BR.rp = new DBParm();
        LNTADVI_BR.rp.TableName = "LNTADVI";
        LNTADVI_BR.rp.where = "CTA_NO >= :LNRADVI.KEY.CTA_NO "
            + "AND TR_DATE >= :LNRADVI.KEY.TR_DATE "
            + "AND JRN_NO >= :LNRADVI.KEY.JRN_NO "
            + "AND ADV_TYP >= :LNRADVI.KEY.ADV_TYP";
        LNTADVI_BR.rp.order = "CTA_NO,TR_DATE,JRN_NO,ADV_TYP";
        IBS.STARTBR(SCCGWA, LNRADVI, this, LNTADVI_BR);
    }
    public void T000_STARTBR_CTA_NO_TR_DT() throws IOException,SQLException,Exception {
        LNTADVI_BR.rp = new DBParm();
        LNTADVI_BR.rp.TableName = "LNTADVI";
        LNTADVI_BR.rp.where = "CTA_NO = :LNRADVI.KEY.CTA_NO "
            + "AND TR_DATE = :LNRADVI.KEY.TR_DATE";
        LNTADVI_BR.rp.order = "JRN_NO,ADV_TYP";
        IBS.STARTBR(SCCGWA, LNRADVI, this, LNTADVI_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRADVI, this, LNTADVI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ADVI_NOTFND, LNCRADVI.RC);
            LNCRADVI.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTADVI_BR);
    }
    public void T000_GROUP_REC_PROC() throws IOException,SQLException,Exception {
        LNTADVI_RD = new DBParm();
        LNTADVI_RD.TableName = "LNTADVI";
        LNTADVI_RD.set = "WS-TOT-COUNT=IFNULL(COUNT(*),0)";
        LNTADVI_RD.where = "CTA_NO = :LNRADVI.KEY.CTA_NO "
            + "AND TR_DATE = :LNRADVI.KEY.TR_DATE";
        IBS.GROUP(SCCGWA, LNRADVI, this, LNTADVI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '3') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_OTHER, LNCRADVI.RC);
            LNCRADVI.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRADVI.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCRADVI=");
            CEP.TRC(SCCGWA, LNCRADVI);
            CEP.TRC(SCCGWA, "LNRADVI =");
            CEP.TRC(SCCGWA, LNRADVI);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
