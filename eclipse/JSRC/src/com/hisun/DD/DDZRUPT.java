package com.hisun.DD;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZRUPT {
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTUPT_RD;
    brParm DDTUPT_BR = new brParm();
    boolean pgmRtn = false;
    int WS_MAX_SEQ = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    DDRUPT DDRUPT = new DDRUPT();
    SCCGWA SCCGWA;
    DDCRUPT DDCRUPT;
    DDRUPT DDRUPTL;
    public void MP(SCCGWA SCCGWA, DDCRUPT DDCRUPT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCRUPT = DDCRUPT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZRUPT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        DDRUPTL = (DDRUPT) DDCRUPT.REC_PTR;
        DDCRUPT.RETURN_INFO = 'F';
        IBS.init(SCCGWA, DDRUPT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDRUPTL);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, DDRUPTL, DDRUPT);
        DDCRUPT.RC.RC_MMO = "DD";
        DDCRUPT.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DDCRUPT.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (DDCRUPT.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (DDCRUPT.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (DDCRUPT.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (DDCRUPT.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (DDCRUPT.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCRUPT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDRUPT);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, DDRUPT, DDRUPTL);
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_PROC();
        if (pgmRtn) return;
    }
    public void B020_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        T00_GROUP_DDTUPT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_MAX_SEQ);
        WS_MAX_SEQ = WS_MAX_SEQ + 1;
        DDRUPT.KEY.UPT_NO = WS_MAX_SEQ;
        T000_WRITE_REC_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_MAX_SEQ);
        CEP.TRC(SCCGWA, DDRUPT.KEY.CUS_AC);
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
        if (DDCRUPT.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (DDCRUPT.OPT == 'P') {
            B060_20_STBR_AC_UPT_PROC();
            if (pgmRtn) return;
        } else if (DDCRUPT.OPT == 'A') {
            B060_30_STBR_AC_PROC();
            if (pgmRtn) return;
        } else if (DDCRUPT.OPT == 'C') {
            B060_70_STBR_AC_CCY_PROC();
            if (pgmRtn) return;
        } else if (DDCRUPT.OPT == 'J') {
            B060_90_STBR_JRNNO_PROC();
            if (pgmRtn) return;
        } else if (DDCRUPT.OPT == 'U') {
            B060_100_STBR_UPT_BY_CCY_TXN();
            if (pgmRtn) return;
        } else if (DDCRUPT.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (DDCRUPT.OPT == 'E') {
            B060_50_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + DDCRUPT.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_10_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_AC_UPT_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_AC_UPT_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_STBR_AC_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_AC_PROC();
        if (pgmRtn) return;
    }
    public void B060_70_STBR_AC_CCY_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_AC_CCY_PROC();
        if (pgmRtn) return;
    }
    public void B060_90_STBR_JRNNO_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_AC_JRNNO_PROC();
        if (pgmRtn) return;
    }
    public void B060_100_STBR_UPT_BY_CCY_TXN() throws IOException,SQLException,Exception {
        T000_STARTBR_UPT_BY_CCY_TXN();
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
        DDTUPT_RD = new DBParm();
        DDTUPT_RD.TableName = "DDTUPT";
        IBS.READ(SCCGWA, DDRUPT, DDTUPT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_UPT_REC_NOTFND, DDCRUPT.RC);
            DDCRUPT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_GROUP_DDTUPT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRUPT.KEY.CUS_AC);
        DDTUPT_RD = new DBParm();
        DDTUPT_RD.TableName = "DDTUPT";
        DDTUPT_RD.set = "WS-MAX-SEQ=IFNULL(MAX(UPT_NO),0)";
        DDTUPT_RD.where = "CUS_AC = :DDRUPT.KEY.CUS_AC";
        IBS.GROUP(SCCGWA, DDRUPT, this, DDTUPT_RD);
        CEP.TRC(SCCGWA, WS_MAX_SEQ);
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        DDTUPT_RD = new DBParm();
        DDTUPT_RD.TableName = "DDTUPT";
        IBS.WRITE(SCCGWA, DDRUPT, DDTUPT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_UPT_REC_EXIST, DDCRUPT.RC);
            DDCRUPT.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        DDTUPT_RD = new DBParm();
        DDTUPT_RD.TableName = "DDTUPT";
        DDTUPT_RD.upd = true;
        IBS.READ(SCCGWA, DDRUPT, DDTUPT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_UPT_REC_NOTFND, DDCRUPT.RC);
            DDCRUPT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        DDTUPT_RD = new DBParm();
        DDTUPT_RD.TableName = "DDTUPT";
        IBS.REWRITE(SCCGWA, DDRUPT, DDTUPT_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        DDTUPT_RD = new DBParm();
        DDTUPT_RD.TableName = "DDTUPT";
        IBS.DELETE(SCCGWA, DDRUPT, DDTUPT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_UPT_REC_NOTFND, DDCRUPT.RC);
            DDCRUPT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        DDTUPT_BR.rp = new DBParm();
        DDTUPT_BR.rp.TableName = "DDTUPT";
        DDTUPT_BR.rp.order = "CUS_AC,UPT_NO";
        IBS.STARTBR(SCCGWA, DDRUPT, DDTUPT_BR);
    }
    public void T000_STARTBR_AC_UPT_PROC() throws IOException,SQLException,Exception {
        DDRUPT.PRT_FLAG = '0';
        DDTUPT_BR.rp = new DBParm();
        DDTUPT_BR.rp.TableName = "DDTUPT";
        DDTUPT_BR.rp.where = "CUS_AC = :DDRUPT.KEY.CUS_AC "
            + "AND PRT_FLAG = :DDRUPT.PRT_FLAG";
        DDTUPT_BR.rp.upd = true;
        DDTUPT_BR.rp.order = "CUS_AC,UPT_NO";
        IBS.STARTBR(SCCGWA, DDRUPT, this, DDTUPT_BR);
    }
    public void T000_STARTBR_AC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRUPT.KEY.CUS_AC);
        CEP.TRC(SCCGWA, DDRUPT.PSBK_SEQ);
        CEP.TRC(SCCGWA, DDRUPT.PRT_LINE);
        CEP.TRC(SCCGWA, "9999999999999999");
        DDTUPT_BR.rp = new DBParm();
        DDTUPT_BR.rp.TableName = "DDTUPT";
        DDTUPT_BR.rp.where = "CUS_AC = :DDRUPT.KEY.CUS_AC "
            + "AND PSBK_SEQ = :DDRUPT.PSBK_SEQ "
            + "AND PRT_LINE >= :DDRUPT.PRT_LINE";
        DDTUPT_BR.rp.upd = true;
        DDTUPT_BR.rp.order = "CUS_AC,UPT_NO";
        IBS.STARTBR(SCCGWA, DDRUPT, this, DDTUPT_BR);
    }
    public void T000_STARTBR_AC_CCY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRUPT.CCY);
        DDTUPT_BR.rp = new DBParm();
        DDTUPT_BR.rp.TableName = "DDTUPT";
        DDTUPT_BR.rp.where = "CUS_AC = :DDRUPT.KEY.CUS_AC "
            + "AND CCY = :DDRUPT.CCY";
        DDTUPT_BR.rp.order = "CUS_AC,UPT_NO";
        IBS.STARTBR(SCCGWA, DDRUPT, this, DDTUPT_BR);
    }
    public void T000_STARTBR_AC_JRNNO_PROC() throws IOException,SQLException,Exception {
        DDTUPT_BR.rp = new DBParm();
        DDTUPT_BR.rp.TableName = "DDTUPT";
        DDTUPT_BR.rp.where = "CUS_AC = :DDRUPT.KEY.CUS_AC "
            + "AND TXN_DATE = :DDRUPT.TXN_DATE "
            + "AND JRN_NO = :DDRUPT.JRN_NO";
        DDTUPT_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, DDRUPT, this, DDTUPT_BR);
    }
    public void T000_STARTBR_UPT_BY_CCY_TXN() throws IOException,SQLException,Exception {
        DDRUPT.PRT_FLAG = '0';
        DDTUPT_BR.rp = new DBParm();
        DDTUPT_BR.rp.TableName = "DDTUPT";
        DDTUPT_BR.rp.where = "CUS_AC = :DDRUPT.KEY.CUS_AC "
            + "AND PRT_FLAG = :DDRUPT.PRT_FLAG";
        DDTUPT_BR.rp.upd = true;
        DDTUPT_BR.rp.order = "CCY,TXN_TYPE";
        IBS.STARTBR(SCCGWA, DDRUPT, this, DDTUPT_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRUPT, this, DDTUPT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            DDCRUPT.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTUPT_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCRUPT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCRUPT=");
            CEP.TRC(SCCGWA, DDCRUPT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
