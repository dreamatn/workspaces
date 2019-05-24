package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRPLPI {
    DBParm LNTPLPI_RD;
    brParm LNTPLPI_BR = new brParm();
    boolean pgmRtn = false;
    int WS_TOT_CNT = 0;
    double WS_TOT_SUM = 0;
    int WS_STR_DATE = 0;
    short WS_TERM_MAX = 0;
    int WS_END_DATE = 0;
    int WS_MAX_DUE_DT = 0;
    int WS_DUE_DT = 0;
    double WS_DUE_P_AMT = 0;
    String WS_START_CTA = " ";
    String WS_END_CTA = " ";
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRPLPI LNRPLPI = new LNRPLPI();
    SCCGWA SCCGWA;
    LNCRPLPI LNCRPLPI;
    LNRPLPI LNRPLPI1;
    public void MP(SCCGWA SCCGWA, LNCRPLPI LNCRPLPI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRPLPI = LNCRPLPI;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRPLPI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRPLPI1 = (LNRPLPI) LNCRPLPI.REC_PTR;
        IBS.CLONE(SCCGWA, LNRPLPI1, LNRPLPI);
        LNCRPLPI.RETURN_INFO = 'F';
        LNCRPLPI.RC.RC_MMO = "LN";
        LNCRPLPI.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRPLPI.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.FUNC == 'G') {
            B010_GROUP_TERM_PROC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.FUNC == 'S') {
            B015_GROUP_BY_AMT_SUM_PROC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.FUNC == 'P') {
            B030_READUPD1_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRPLPI.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRPLPI, LNRPLPI1);
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_PROC();
        if (pgmRtn) return;
    }
    public void B010_GROUP_TERM_PROC() throws IOException,SQLException,Exception {
        T000_GROUP_TERM_PROC();
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
    public void B030_READUPD1_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPDATE1_PROC();
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
        if (LNCRPLPI.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.OPT == 'P') {
            B060_30_STBR_DATE_PROC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.OPT == 'G') {
            B060_30_STBR_CTA_SUBNO_P_PROC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.OPT == 'X') {
            B060_30_STBR_DUE1_PROC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.OPT == 'U') {
            B060_30_STBR_DUE2_PROC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.OPT == 'F') {
            B060_30_STBR_BY_KEY_FIRST_PROC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.OPT == 'A') {
            B060_30_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.OPT == 'Z') {
            B060_30_STBR_BY_KEY2_PROC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.OPT == 'Q') {
            B060_30_STBR_COMP_DT_PROC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.OPT == 'N') {
            B060_30_STBR_BY_CTANO_PROC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.OPT == 'K') {
            B060_30_STBR_UPDATE_PROC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.OPT == 'C') {
            B060_30_STBR_BY_DT_SPC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.OPT == 'L') {
            B060_30_STBR_BY_DT_SPC1();
            if (pgmRtn) return;
        } else if (LNCRPLPI.OPT == 'D') {
            B060_50_STBR_BY_DUE_DT_PROC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.OPT == 'Y') {
            B060_50_STBR_BY_DUE2_DT_PROC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.OPT == 'M') {
            B060_50_STBR_BY_DUE3_DT_PROC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.OPT == 'I') {
            B060_30_STBR_FIRST_SPC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.OPT == 'O') {
            B060_30_STBR_FIRST1_SPC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.OPT == 'H') {
            B060_30_STBR_FIRST2_SPC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.OPT == 'B') {
            B060_35_STBR_BY_FATHER_CTA();
            if (pgmRtn) return;
        } else if (LNCRPLPI.OPT == 'V') {
            B060_30_STBR_BY_DUE_SPC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.OPT == 'R') {
            B060_70_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRPLPI.OPT == 'E') {
            B060_90_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRPLPI.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_10_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_STBR_BY_KEY_FIRST_PROC() throws IOException,SQLException,Exception {
        T000_STBR_BY_KEY_FIRST_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_STBR_CTA_SUBNO_P_PROC() throws IOException,SQLException,Exception {
        T000_STBR_SUBNO_P_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_STBR_BY_KEY2_PROC() throws IOException,SQLException,Exception {
        T000_STBR_BY_KEY2_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_STBR_DATE_PROC() throws IOException,SQLException,Exception {
        T000_STBR_BY_DATE_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_STBR_COMP_DT_PROC() throws IOException,SQLException,Exception {
        T000_STBR_COMP_DT_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_STBR_DUE1_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_DUE1_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_STBR_DUE2_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_DUE2_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_STBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        if (LNRPLPI.KEY.REPY_MTH == ' ') {
            T000_STARTBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_BY_KEY_PROC1();
            if (pgmRtn) return;
        }
    }
    public void B060_30_STBR_UPDATE_PROC() throws IOException,SQLException,Exception {
        if (LNRPLPI.KEY.REPY_MTH == ' ') {
            T000_STARTBR_UPDATE_PROC();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_UPDATE_PROC1();
            if (pgmRtn) return;
        }
    }
    public void B015_GROUP_BY_AMT_SUM_PROC() throws IOException,SQLException,Exception {
        if (LNRPLPI.KEY.REPY_MTH == ' ') {
            T000_GROUP_BY_AMT_SUM_PROC();
            if (pgmRtn) return;
        } else {
            T000_GROUP_BY_AMT_SUM_PROC1();
            if (pgmRtn) return;
        }
    }
    public void B060_30_STBR_FIRST_SPC() throws IOException,SQLException,Exception {
        T000_STARTBR_FIRST_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_STBR_FIRST1_SPC() throws IOException,SQLException,Exception {
        T000_STARTBR_FIRST1_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_STBR_FIRST2_SPC() throws IOException,SQLException,Exception {
        T000_STARTBR_FIRST2_PROC();
        if (pgmRtn) return;
    }
    public void B060_35_STBR_BY_FATHER_CTA() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_FATHER_CTA();
        if (pgmRtn) return;
    }
    public void B060_30_STBR_BY_DT_SPC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_DT_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_STBR_BY_DT_SPC1() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_DT1_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_STBR_BY_CTANO_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_CTANO_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_STBR_BY_DUE_SPC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_DUE_SPC();
        if (pgmRtn) return;
    }
    public void B060_50_STBR_BY_DUE_DT_PROC() throws IOException,SQLException,Exception {
        if (LNRPLPI.KEY.REPY_MTH == ' ') {
            T000_STARTBR_BY_DUE_DT_PROC();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_BY_DUE_DT_PROC1();
            if (pgmRtn) return;
        }
    }
    public void B060_70_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_PROC();
        if (pgmRtn) return;
    }
    public void B060_90_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        IBS.READ(SCCGWA, LNRPLPI, LNTPLPI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PLPI_NOTFND, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_GROUP_TERM_PROC() throws IOException,SQLException,Exception {
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.set = "WS-TERM-MAX=IFNULL(MAX(TERM),0),WS-MAX-DUE-DT=IFNULL(MAX(DUE_DT),0)";
        LNTPLPI_RD.eqWhere = "CONTRACT_NO, SUB_CTA_NO, REPY_MTH";
        IBS.GROUP(SCCGWA, LNRPLPI, this, LNTPLPI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PLPI_NOTFND, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '3') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_OTHER, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        LNCRPLPI.TERM_MAX = WS_TERM_MAX;
        LNCRPLPI.MAX_DUE_DT = WS_MAX_DUE_DT;
        CEP.TRC(SCCGWA, LNCRPLPI.TERM_MAX);
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRPLPI.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPLPI.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRPLPI.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPLPI.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRPLPI, LNTPLPI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.upd = true;
        IBS.READ(SCCGWA, LNRPLPI, LNTPLPI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PLPI_NOTFND, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE1_PROC() throws IOException,SQLException,Exception {
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.upd = true;
        LNTPLPI_RD.eqWhere = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH,DUE_DT";
        IBS.READ(SCCGWA, LNRPLPI, LNTPLPI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PLPI_NOTFND, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRPLPI.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPLPI.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        IBS.REWRITE(SCCGWA, LNRPLPI, LNTPLPI_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        IBS.DELETE(SCCGWA, LNRPLPI, LNTPLPI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PLPI_NOTFND, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH,TERM";
        IBS.STARTBR(SCCGWA, LNRPLPI, LNTPLPI_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
        LNTPLPI_BR.rp.where = "REPY_MTH >= :LNRPLPI.KEY.REPY_MTH "
            + "AND TERM >= :LNRPLPI.KEY.TERM";
        LNTPLPI_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH,TERM";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
    }
    public void T000_STBR_SUBNO_P_PROC() throws IOException,SQLException,Exception {
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
        LNTPLPI_BR.rp.where = "REPY_MTH = 'P'";
        LNTPLPI_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
    }
    public void T000_STARTBR_BY_CTANO_PROC() throws IOException,SQLException,Exception {
        null.rp.upd = true;
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
        LNTPLPI_BR.rp.order = "TERM";
        IBS.STARTBR(SCCGWA, LNRPLPI, LNTPLPI_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC1() throws IOException,SQLException,Exception {
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH";
        LNTPLPI_BR.rp.where = "TERM >= :LNRPLPI.KEY.TERM";
        LNTPLPI_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH,TERM";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
    }
    public void T000_STARTBR_UPDATE_PROC1() throws IOException,SQLException,Exception {
        null.rp.upd = true;
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH";
        LNTPLPI_BR.rp.where = "TERM >= :LNRPLPI.KEY.TERM";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
    }
    public void T000_STBR_BY_KEY_FIRST_PROC() throws IOException,SQLException,Exception {
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH";
        LNTPLPI_BR.rp.where = "TERM >= :LNRPLPI.KEY.TERM";
        LNTPLPI_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH,TERM";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PLPI_NOTFND, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '3') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_OTHER, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B060_50_STBR_BY_DUE2_DT_PROC() throws IOException,SQLException,Exception {
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH";
        LNTPLPI_BR.rp.where = "DUE_DT < :LNRPLPI.DUE_DT";
        LNTPLPI_BR.rp.order = "DUE_DT DESC";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PLPI_NOTFND, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B060_50_STBR_BY_DUE3_DT_PROC() throws IOException,SQLException,Exception {
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH";
        LNTPLPI_BR.rp.where = "DUE_DT <= :LNRPLPI.DUE_DT";
        LNTPLPI_BR.rp.order = "DUE_DT DESC";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PLPI_NOTFND, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STBR_BY_KEY2_PROC() throws IOException,SQLException,Exception {
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH,VAL_DT";
        IBS.STARTBR(SCCGWA, LNRPLPI, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PLPI_NOTFND, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '3') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_OTHER, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STBR_BY_DATE_PROC() throws IOException,SQLException,Exception {
        WS_STR_DATE = LNCRPLPI.STR_DATE;
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
        LNTPLPI_BR.rp.where = "VAL_DT <= :WS_STR_DATE "
            + "AND DUE_DT > :WS_STR_DATE";
        LNTPLPI_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH ,TERM";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PLPI_NOTFND, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '3') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_OTHER, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STBR_COMP_DT_PROC() throws IOException,SQLException,Exception {
        WS_STR_DATE = LNCRPLPI.STR_DATE;
        WS_END_DATE = LNCRPLPI.END_DATE;
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
        LNTPLPI_BR.rp.where = "DUE_DT > :WS_STR_DATE "
            + "AND DUE_DT <= :WS_END_DATE";
        LNTPLPI_BR.rp.order = "DUE_DT,REPY_MTH";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
    }
    public void T000_STARTBR_UPDATE_PROC() throws IOException,SQLException,Exception {
        null.rp.upd = true;
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
        LNTPLPI_BR.rp.where = "REPY_MTH >= :LNRPLPI.KEY.REPY_MTH "
            + "AND TERM >= :LNRPLPI.KEY.TERM";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
    }
    public void T000_STARTBR_FIRST_PROC() throws IOException,SQLException,Exception {
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH";
        LNTPLPI_BR.rp.where = "VAL_DT <= :LNRPLPI.VAL_DT "
            + "AND DUE_DT > :LNRPLPI.VAL_DT";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PLPI_NOTFND, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DUE1_PROC() throws IOException,SQLException,Exception {
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH";
        LNTPLPI_BR.rp.where = "DUE_DT > :LNRPLPI.DUE_DT";
        LNTPLPI_BR.rp.order = "TERM";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PLPI_NOTFND, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DUE2_PROC() throws IOException,SQLException,Exception {
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH,DUE_DT";
        IBS.STARTBR(SCCGWA, LNRPLPI, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PLPI_NOTFND, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_FIRST1_PROC() throws IOException,SQLException,Exception {
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH";
        LNTPLPI_BR.rp.where = "VAL_DT >= :LNRPLPI.VAL_DT "
            + "AND VAL_DT < :LNRPLPI.DUE_DT";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PLPI_NOTFND, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_FIRST2_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCRPLPI.STR_DATE);
        WS_STR_DATE = LNCRPLPI.STR_DATE;
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH";
        LNTPLPI_BR.rp.where = "VAL_DT <= :WS_STR_DATE "
            + "AND DUE_DT >= :WS_STR_DATE";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PLPI_NOTFND, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BY_FATHER_CTA() throws IOException,SQLException,Exception {
        WS_START_CTA = LNCRPLPI.START_CTA;
        WS_START_CTA = LNCRPLPI.END_CTA;
        WS_STR_DATE = LNCRPLPI.STR_DATE;
        WS_END_DATE = LNCRPLPI.END_DATE;
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.set = "WS-DUE-DT=DUE_DT,WS-DUE-P-AMT=SUM(DUE_REPY_AMT)";
        LNTPLPI_BR.rp.where = "CONTRACT_NO >= :WS_START_CTA "
            + "AND CONTRACT_NO < :WS_END_CTA "
            + "AND SUB_CTA_NO = :LNRPLPI.KEY.SUB_CTA_NO "
            + "AND REPY_MTH = 'P' "
            + "AND DUE_DT > :WS_STR_DATE "
            + "AND DUE_DT <= :WS_END_DATE";
        LNTPLPI_BR.rp.grp = "DUE_DT,DUE_REPY_AMT";
        LNTPLPI_BR.rp.order = "DUE_DT,DUE_REPY_AMT";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PLPI_NOTFND, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '3') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_OTHER, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BY_DUE_SPC() throws IOException,SQLException,Exception {
        WS_TERM_MAX = LNCRPLPI.TERM_MAX;
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
        LNTPLPI_BR.rp.where = "( ( REPY_MTH = 'I' "
            + "AND TERM >= :LNRPLPI.KEY.TERM ) "
            + "OR ( REPY_MTH = 'P' "
            + "AND TERM >= :WS_TERM_MAX ) )";
        LNTPLPI_BR.rp.order = "DUE_DT,REPY_MTH";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
    }
    public void T000_GROUP_BY_AMT_SUM_PROC1() throws IOException,SQLException,Exception {
        WS_TOT_CNT = 0;
        WS_TOT_SUM = 0;
        CEP.TRC(SCCGWA, LNRPLPI.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRPLPI.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRPLPI.KEY.REPY_MTH);
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.where = "CONTRACT_NO = :LNRPLPI.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRPLPI.KEY.SUB_CTA_NO "
            + "AND REPY_MTH = :LNRPLPI.KEY.REPY_MTH";
        LNTPLPI_RD.set = "WS-TOT-CNT=COUNT(*),WS-TOT-SUM=IFNULL(SUM(PRIN_AMT),0)";
        IBS.GROUP(SCCGWA, LNRPLPI, this, LNTPLPI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PLPI_NOTFND, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '3') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_OTHER, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        LNCRPLPI.TOT_CNT = WS_TOT_CNT;
        LNCRPLPI.TOT_SUM = WS_TOT_SUM;
        CEP.TRC(SCCGWA, WS_TOT_CNT);
        CEP.TRC(SCCGWA, WS_TOT_SUM);
        CEP.TRC(SCCGWA, LNCRPLPI.TOT_CNT);
        CEP.TRC(SCCGWA, LNCRPLPI.TOT_SUM);
    }
    public void T000_GROUP_BY_AMT_SUM_PROC() throws IOException,SQLException,Exception {
        WS_TOT_CNT = 0;
        WS_TOT_SUM = 0;
        LNTPLPI_RD = new DBParm();
        LNTPLPI_RD.TableName = "LNTPLPI";
        LNTPLPI_RD.where = "CONTRACT_NO = :LNRPLPI.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRPLPI.KEY.SUB_CTA_NO "
            + "AND REPY_MTH = :LNRPLPI.KEY.REPY_MTH";
        LNTPLPI_RD.set = "WS-TOT-CNT=COUNT(*),WS-TOT-SUM=IFNULL(SUM(PRIN_AMT),0),";
        IBS.GROUP(SCCGWA, LNRPLPI, this, LNTPLPI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PLPI_NOTFND, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '3') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_OTHER, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        LNCRPLPI.TOT_CNT = WS_TOT_CNT;
        LNCRPLPI.TOT_SUM = WS_TOT_SUM;
    }
    public void T000_STARTBR_BY_DT_PROC() throws IOException,SQLException,Exception {
        WS_STR_DATE = LNCRPLPI.STR_DATE;
        WS_END_DATE = LNCRPLPI.END_DATE;
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
        LNTPLPI_BR.rp.where = "DUE_DT > :WS_STR_DATE "
            + "AND DUE_DT <= :WS_END_DATE";
        LNTPLPI_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH,TERM";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
    }
    public void T000_STARTBR_BY_DT1_PROC() throws IOException,SQLException,Exception {
        WS_STR_DATE = LNCRPLPI.STR_DATE;
        WS_END_DATE = LNCRPLPI.END_DATE;
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.where = "DUE_DT > :WS_STR_DATE "
            + "AND SUB_CTA_NO = :LNRPLPI.KEY.SUB_CTA_NO "
            + "AND DUE_DT < :WS_END_DATE";
        LNTPLPI_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,DUE_DT,REPY_MTH,TERM";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
    }
    public void T000_STARTBR_BY_DUE_DT_PROC() throws IOException,SQLException,Exception {
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
        LNTPLPI_BR.rp.where = "REPY_MTH >= :LNRPLPI.KEY.REPY_MTH "
            + "AND DUE_DT >= :LNRPLPI.DUE_DT";
        LNTPLPI_BR.rp.order = "DUE_DT";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
    }
    public void T000_STARTBR_BY_DUE_DT_PROC1() throws IOException,SQLException,Exception {
        LNTPLPI_BR.rp = new DBParm();
        LNTPLPI_BR.rp.TableName = "LNTPLPI";
        LNTPLPI_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH";
        LNTPLPI_BR.rp.where = "DUE_DT >= :LNRPLPI.DUE_DT";
        LNTPLPI_BR.rp.order = "DUE_DT";
        IBS.STARTBR(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        WS_DUE_DT = 0;
        WS_DUE_P_AMT = 0;
        IBS.READNEXT(SCCGWA, LNRPLPI, this, LNTPLPI_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PLPI_NOTFND, LNCRPLPI.RC);
            LNCRPLPI.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
        LNCRPLPI.DUE_DT = WS_DUE_DT;
        LNCRPLPI.DUE_P_AMT = WS_DUE_P_AMT;
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTPLPI_BR);
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
