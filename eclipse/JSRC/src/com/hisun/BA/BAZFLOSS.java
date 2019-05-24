package com.hisun.BA;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BAZFLOSS {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm BATLOSS_RD;
    boolean pgmRtn = false;
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BARLOSS BARLOSS = new BARLOSS();
    SCCGWA SCCGWA;
    BACFLOSS BACFLOSS;
    String LK_REC = " ";
    public void MP(SCCGWA SCCGWA, BACFLOSS BACFLOSS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BACFLOSS = BACFLOSS;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAZFLOSS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LK_REC = IBS.CLS2CPY(SCCGWA, BACFLOSS.REC_PTR);
        BACFLOSS.RETURN_INFO = 'F';
        CEP.TRC(SCCGWA, "123456");
        CEP.TRC(SCCGWA, "789");
        if (LK_REC == null) LK_REC = "";
        JIBS_tmp_int = LK_REC.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) LK_REC += " ";
        JIBS_tmp_str[0] = LK_REC.substring(0, BACFLOSS.REC_LEN);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BARLOSS);
        BACFLOSS.RC.RC_MMO = "BA";
        BACFLOSS.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, BARLOSS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BACFLOSS.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BACFLOSS.FUNC == 'L') {
            B100_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BACFLOSS.FUNC == 'M') {
            B200_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BACFLOSS.FUNC == 'Z') {
            B110_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BACFLOSS.FUNC == 'O') {
            B120_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BACFLOSS.FUNC == 'B') {
            B140_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BACFLOSS.FUNC == 'Q') {
            B150_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BACFLOSS.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BACFLOSS.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BACFLOSS.FUNC == 'S') {
            B040_READUPD_BY_IND_PROC();
            if (pgmRtn) return;
        } else if (BACFLOSS.FUNC == 'U') {
            B080_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BACFLOSS.FUNC == 'D') {
            B090_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BACFLOSS.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BARLOSS);
        LK_REC = JIBS_tmp_str[0];
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_PROC();
        if (pgmRtn) return;
    }
    public void B100_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_BY_IND1_PROC();
        if (pgmRtn) return;
    }
    public void B200_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_BY_STS_PROC();
        if (pgmRtn) return;
    }
    public void B110_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_BY_IND2_PROC();
        if (pgmRtn) return;
    }
    public void B120_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_BY_IND3_PROC();
        if (pgmRtn) return;
    }
    public void B140_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_BY_IND4_PROC();
        if (pgmRtn) return;
    }
    public void B150_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_BY_IND5_PROC();
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
    public void B040_READUPD_BY_IND_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPDATE_IND1_PROC();
        if (pgmRtn) return;
    }
    public void B080_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B090_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_REC_PROC();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        BATLOSS_RD = new DBParm();
        BATLOSS_RD.TableName = "BATLOSS";
        IBS.READ(SCCGWA, BARLOSS, BATLOSS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_LOSS_NOTFND, BACFLOSS.RC);
            BACFLOSS.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_REC_BY_IND1_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BARLOSS.CNTR_NO);
        CEP.TRC(SCCGWA, BARLOSS.ACCT_CNT);
        BATLOSS_RD = new DBParm();
        BATLOSS_RD.TableName = "BATLOSS";
        BATLOSS_RD.where = "CNTR_NO = :BARLOSS.CNTR_NO "
            + "AND ACCT_CNT = :BARLOSS.ACCT_CNT";
        BATLOSS_RD.fst = true;
        BATLOSS_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, BARLOSS, this, BATLOSS_RD);
        CEP.TRC(SCCGWA, "00000000000000");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_LOSS_NOTFND, BACFLOSS.RC);
            BACFLOSS.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_REC_BY_STS_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BARLOSS.CNTR_NO);
        CEP.TRC(SCCGWA, BARLOSS.ACCT_CNT);
        BATLOSS_RD = new DBParm();
        BATLOSS_RD.TableName = "BATLOSS";
        BATLOSS_RD.where = "CNTR_NO = :BARLOSS.CNTR_NO "
            + "AND ACCT_CNT = :BARLOSS.ACCT_CNT "
            + "AND LOSS_STS = :BARLOSS.LOSS_STS";
        BATLOSS_RD.fst = true;
        BATLOSS_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, BARLOSS, this, BATLOSS_RD);
        CEP.TRC(SCCGWA, "00000000000000");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_LOSS_NOTFND, BACFLOSS.RC);
            BACFLOSS.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_REC_BY_IND2_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BARLOSS.CNTR_NO);
        CEP.TRC(SCCGWA, BARLOSS.ACCT_CNT);
        BATLOSS_RD = new DBParm();
        BATLOSS_RD.TableName = "BATLOSS";
        BATLOSS_RD.where = "CNTR_NO = :BARLOSS.CNTR_NO "
            + "AND ACCT_CNT = :BARLOSS.ACCT_CNT "
            + "AND SEQ = :BARLOSS.SEQ";
        BATLOSS_RD.fst = true;
        BATLOSS_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, BARLOSS, this, BATLOSS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BACFLOSS.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_LOSS_NOTFND, BACFLOSS.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BACFLOSS.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_DUPKEY, BACFLOSS.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BACFLOSS.RETURN_INFO = 'F';
        } else {
            BACFLOSS.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_SYS_ERR, BACFLOSS.RC);
        }
    }
    public void T000_READ_REC_BY_IND3_PROC() throws IOException,SQLException,Exception {
        BATLOSS_RD = new DBParm();
        BATLOSS_RD.TableName = "BATLOSS";
        BATLOSS_RD.where = "BILL_NO = :BARLOSS.BILL_NO";
        BATLOSS_RD.fst = true;
        BATLOSS_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, BARLOSS, this, BATLOSS_RD);
        CEP.TRC(SCCGWA, "00000000000000");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_LOSS_NOTFND, BACFLOSS.RC);
            BACFLOSS.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BACFLOSS.RETURN_INFO = 'F';
        }
    }
    public void T000_READ_REC_BY_IND4_PROC() throws IOException,SQLException,Exception {
        BATLOSS_RD = new DBParm();
        BATLOSS_RD.TableName = "BATLOSS";
        BATLOSS_RD.where = "BILL_NO = :BARLOSS.BILL_NO "
            + "AND LOSS_STS = :BARLOSS.LOSS_STS "
            + "AND TX_DT >= :BARLOSS.KEY.TX_DT";
        BATLOSS_RD.fst = true;
        BATLOSS_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, BARLOSS, this, BATLOSS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BACFLOSS.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_LOSS_NOTFND, BACFLOSS.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BACFLOSS.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_DUPKEY, BACFLOSS.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BACFLOSS.RETURN_INFO = 'F';
        } else {
            BACFLOSS.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_SYS_ERR, BACFLOSS.RC);
        }
    }
    public void T000_READ_REC_BY_IND5_PROC() throws IOException,SQLException,Exception {
        BATLOSS_RD = new DBParm();
        BATLOSS_RD.TableName = "BATLOSS";
        BATLOSS_RD.where = "CNTR_NO = :BARLOSS.CNTR_NO "
            + "AND ACCT_CNT = :BARLOSS.ACCT_CNT "
            + "AND ( TX_DT < :BARLOSS.KEY.TX_DT "
            + "OR TX_DT = :BARLOSS.KEY.TX_DT )";
        BATLOSS_RD.fst = true;
        BATLOSS_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, BARLOSS, this, BATLOSS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_LOSS_NOTFND, BACFLOSS.RC);
            BACFLOSS.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        BARLOSS.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARLOSS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BARLOSS.UPDT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARLOSS.UPDT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BATLOSS_RD = new DBParm();
        BATLOSS_RD.TableName = "BATLOSS";
        BATLOSS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BARLOSS, BATLOSS_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_DUPKEY, BACFLOSS.RC);
            BACFLOSS.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
                IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_BY_DB, BACFLOSS.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        BATLOSS_RD = new DBParm();
        BATLOSS_RD.TableName = "BATLOSS";
        BATLOSS_RD.upd = true;
        IBS.READ(SCCGWA, BARLOSS, BATLOSS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "CCCCC");
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_LOSS_NOTFND, BACFLOSS.RC);
            BACFLOSS.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_IND1_PROC() throws IOException,SQLException,Exception {
        BATLOSS_RD = new DBParm();
        BATLOSS_RD.TableName = "BATLOSS";
        BATLOSS_RD.where = "CNTR_NO = :BARLOSS.CNTR_NO "
            + "AND ACCT_CNT = :BARLOSS.ACCT_CNT";
        BATLOSS_RD.fst = true;
        BATLOSS_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, BARLOSS, this, BATLOSS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_LOSS_NOTFND, BACFLOSS.RC);
            BACFLOSS.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        BARLOSS.UPDT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARLOSS.UPDT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BATLOSS_RD = new DBParm();
        BATLOSS_RD.TableName = "BATLOSS";
        IBS.REWRITE(SCCGWA, BARLOSS, BATLOSS_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_BY_DB, BACFLOSS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        BATLOSS_RD = new DBParm();
        BATLOSS_RD.TableName = "BATLOSS";
        IBS.DELETE(SCCGWA, BARLOSS, BATLOSS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_LOSS_NOTFND, BACFLOSS.RC);
            BACFLOSS.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
