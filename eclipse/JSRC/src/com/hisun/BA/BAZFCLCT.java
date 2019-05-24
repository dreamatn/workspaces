package com.hisun.BA;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BAZFCLCT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm BATCLCT_RD;
    boolean pgmRtn = false;
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BARCLCT BARCLCT = new BARCLCT();
    SCCGWA SCCGWA;
    BACFCLCT BACFCLCT;
    String LK_REC = " ";
    public void MP(SCCGWA SCCGWA, BACFCLCT BACFCLCT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BACFCLCT = BACFCLCT;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAZFCLCT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LK_REC = IBS.CLS2CPY(SCCGWA, BACFCLCT.REC_PTR);
        BACFCLCT.RETURN_INFO = 'F';
        IBS.init(SCCGWA, BARCLCT);
        if (LK_REC == null) LK_REC = "";
        JIBS_tmp_int = LK_REC.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) LK_REC += " ";
        JIBS_tmp_str[0] = LK_REC.substring(0, BACFCLCT.REC_LEN);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BARCLCT);
        BACFCLCT.RC.RC_MMO = "BA";
        BACFCLCT.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BACFCLCT.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BACFCLCT.FUNC == 'L') {
            B100_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BACFCLCT.FUNC == 'M') {
            B110_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BACFCLCT.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BACFCLCT.FUNC == 'X') {
            B060_READUPD_BY_IND_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BACFCLCT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BARCLCT);
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
    public void B110_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_BY_IND2_PROC();
        if (pgmRtn) return;
    }
    public void B020_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B060_READUPD_BY_IND_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPDATE_IND3_PROC();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        BATCLCT_RD = new DBParm();
        BATCLCT_RD.TableName = "BATCLCT";
        IBS.READ(SCCGWA, BARCLCT, BATCLCT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_CLCT_NOTFND, BACFCLCT.RC);
            BACFCLCT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_REC_BY_IND1_PROC() throws IOException,SQLException,Exception {
        BATCLCT_RD = new DBParm();
        BATCLCT_RD.TableName = "BATCLCT";
        BATCLCT_RD.where = "CNTR_NO = :BARCLCT.CNTR_NO "
            + "AND ACCT_CNT = :BARCLCT.ACCT_CNT";
        BATCLCT_RD.fst = true;
        BATCLCT_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, BARCLCT, this, BATCLCT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, "INSIDE");
        CEP.TRC(SCCGWA, BARCLCT.CNTR_NO);
        CEP.TRC(SCCGWA, BARCLCT.ACCT_CNT);
        CEP.TRC(SCCGWA, BARCLCT.BILL_NO);
        CEP.TRC(SCCGWA, "INSIDE");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_CLCT_NOTFND, BACFCLCT.RC);
            BACFCLCT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "INSIDE111");
            BACFCLCT.RETURN_INFO = 'F';
        }
    }
    public void T000_READ_REC_BY_IND2_PROC() throws IOException,SQLException,Exception {
        BATCLCT_RD = new DBParm();
        BATCLCT_RD.TableName = "BATCLCT";
        BATCLCT_RD.where = "CNTR_NO = :BARCLCT.CNTR_NO "
            + "AND ACCT_CNT = :BARCLCT.ACCT_CNT "
            + "AND CLCT_STS = '1' "
            + "AND TX_DT <= :BARCLCT.KEY.TX_DT";
        BATCLCT_RD.fst = true;
        BATCLCT_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, BARCLCT, this, BATCLCT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_CLCT_NOTFND, BACFCLCT.RC);
            BACFCLCT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        BARCLCT.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARCLCT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BARCLCT.UPDT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARCLCT.UPDT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, BARCLCT.KEY.TX_DT);
        CEP.TRC(SCCGWA, BARCLCT.KEY.CRE_JRN);
        BATCLCT_RD = new DBParm();
        BATCLCT_RD.TableName = "BATCLCT";
        BATCLCT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BARCLCT, BATCLCT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_DUPKEY, BACFCLCT.RC);
            BACFCLCT.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
                IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_BY_DB, BACFCLCT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READ_UPDATE_IND3_PROC() throws IOException,SQLException,Exception {
        BATCLCT_RD = new DBParm();
        BATCLCT_RD.TableName = "BATCLCT";
        BATCLCT_RD.where = "BILL_NO = :BARCLCT.BILL_NO";
        BATCLCT_RD.fst = true;
        BATCLCT_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, BARCLCT, this, BATCLCT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_CLCT_NOTFND, BACFCLCT.RC);
            BACFCLCT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "INSIDE222");
            BACFCLCT.RETURN_INFO = 'F';
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
