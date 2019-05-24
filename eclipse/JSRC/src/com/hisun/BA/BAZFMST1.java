package com.hisun.BA;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BAZFMST1 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm BATMST1_RD;
    int WS_LNE = 0;
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BARMST1 BARMST1 = new BARMST1();
    SCCGWA SCCGWA;
    BACFMST1 BACFMST1;
    String LK_REC = " ";
    public void MP(SCCGWA SCCGWA, BACFMST1 BACFMST1) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BACFMST1 = BACFMST1;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BAZFMST1 return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LK_REC = IBS.CLS2CPY(SCCGWA, BACFMST1.REC_PTR);
        CEP.TRC(SCCGWA, "123");
        IBS.init(SCCGWA, BARMST1);
        CEP.TRC(SCCGWA, "789");
        if (LK_REC == null) LK_REC = "";
        JIBS_tmp_int = LK_REC.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) LK_REC += " ";
        JIBS_tmp_str[0] = LK_REC.substring(0, BACFMST1.REC_LEN);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BARMST1);
        BACFMST1.RC.RC_MMO = "BA";
        BACFMST1.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, "1111111111111111111111111111111");
        CEP.TRC(SCCGWA, BARMST1.BILL_NO);
        CEP.TRC(SCCGWA, BARMST1.WO_PAY_FLG);
        CEP.TRC(SCCGWA, BARMST1.ORG_BIL_NO);
        CEP.TRC(SCCGWA, BARMST1.KEY.CNTR_NO);
        CEP.TRC(SCCGWA, BARMST1.KEY.ACCT_CNT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BACFMST1.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
        } else if (BACFMST1.FUNC == 'L') {
            B020_QUERY_RECORD_PROC();
        } else if (BACFMST1.FUNC == 'N') {
            B080_QUERY_RECORD_PROC();
        } else if (BACFMST1.FUNC == 'A') {
            B030_ADD_RECORD_PROC();
        } else if (BACFMST1.FUNC == 'R') {
            B040_READUPD_RECORD_PROC();
        } else if (BACFMST1.FUNC == 'T') {
            B050_READUPD_BY_IND_PROC();
        } else if (BACFMST1.FUNC == 'U') {
            B060_UPDATE_RECORD_PROC();
        } else if (BACFMST1.FUNC == 'D') {
            B070_DELETE_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BACFMST1.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BARMST1);
        LK_REC = JIBS_tmp_str[0];
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_PROC();
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_BY_IND1_PROC();
    }
    public void B080_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_BY_B_NO_PROC();
    }
    public void B030_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_REC_PROC();
    }
    public void B040_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPDATE_PROC();
    }
    public void B050_READUPD_BY_IND_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPDATE_IND1_PROC();
    }
    public void B060_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_REC_PROC();
    }
    public void B070_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_REC_PROC();
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        BATMST1_RD = new DBParm();
        BATMST1_RD.TableName = "BATMST1";
        IBS.READ(SCCGWA, BARMST1, BATMST1_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BACFMST1.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_MST1_NOTFND, BACFMST1.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BACFMST1.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_DUPKEY, BACFMST1.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BACFMST1.RETURN_INFO = 'F';
        } else {
            BACFMST1.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_SYS_ERR, BACFMST1.RC);
        }
    }
    public void T000_READ_REC_BY_IND1_PROC() throws IOException,SQLException,Exception {
        BATMST1_RD = new DBParm();
        BATMST1_RD.TableName = "BATMST1";
        BATMST1_RD.where = "BILL_NO = :BARMST1.BILL_NO";
        BATMST1_RD.fst = true;
        BATMST1_RD.order = "TS DESC";
        IBS.READ(SCCGWA, BARMST1, this, BATMST1_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BACFMST1.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_MST1_NOTFND, BACFMST1.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BACFMST1.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_DUPKEY, BACFMST1.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BACFMST1.RETURN_INFO = 'F';
        } else {
            BACFMST1.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_SYS_ERR, BACFMST1.RC);
        }
    }
    public void T000_READ_REC_BY_B_NO_PROC() throws IOException,SQLException,Exception {
        BATMST1_RD = new DBParm();
        BATMST1_RD.TableName = "BATMST1";
        BATMST1_RD.where = "B_REF_NO = :BARMST1.B_REF_NO";
        BATMST1_RD.fst = true;
        BATMST1_RD.order = "TS DESC";
        IBS.READ(SCCGWA, BARMST1, this, BATMST1_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BACFMST1.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_MST1_NOTFND, BACFMST1.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BACFMST1.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_DUPKEY, BACFMST1.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BACFMST1.RETURN_INFO = 'F';
        } else {
            BACFMST1.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_SYS_ERR, BACFMST1.RC);
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        BARMST1.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        BARMST1.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BARMST1.UPDT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        BARMST1.UPDT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, BARMST1.CRT_TLR);
        CEP.TRC(SCCGWA, BARMST1.UPDT_TLR);
        BATMST1_RD = new DBParm();
        BATMST1_RD.TableName = "BATMST1";
        BATMST1_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BARMST1, BATMST1_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BACFMST1.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_MST1_NOTFND, BACFMST1.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BACFMST1.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_DUPKEY, BACFMST1.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BACFMST1.RETURN_INFO = 'F';
        } else {
            BACFMST1.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_SYS_ERR, BACFMST1.RC);
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        BATMST1_RD = new DBParm();
        BATMST1_RD.TableName = "BATMST1";
        BATMST1_RD.upd = true;
        IBS.READ(SCCGWA, BARMST1, BATMST1_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BACFMST1.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_MST1_NOTFND, BACFMST1.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BACFMST1.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_DUPKEY, BACFMST1.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BACFMST1.RETURN_INFO = 'F';
        } else {
            BACFMST1.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_SYS_ERR, BACFMST1.RC);
        }
    }
    public void T000_READ_UPDATE_IND1_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BARMST1.BILL_NO);
        BATMST1_RD = new DBParm();
        BATMST1_RD.TableName = "BATMST1";
        BATMST1_RD.where = "BILL_NO = :BARMST1.BILL_NO";
        BATMST1_RD.upd = true;
        IBS.READ(SCCGWA, BARMST1, this, BATMST1_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BACFMST1.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_MST1_NOTFND, BACFMST1.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BACFMST1.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_DUPKEY, BACFMST1.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BACFMST1.RETURN_INFO = 'F';
        } else {
            BACFMST1.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_SYS_ERR, BACFMST1.RC);
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        BARMST1.UPDT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARMST1.UPDT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BATMST1_RD = new DBParm();
        BATMST1_RD.TableName = "BATMST1";
        BATMST1_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, BARMST1, BATMST1_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BACFMST1.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_MST1_NOTFND, BACFMST1.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BACFMST1.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_DUPKEY, BACFMST1.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BACFMST1.RETURN_INFO = 'F';
        } else {
            BACFMST1.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_SYS_ERR, BACFMST1.RC);
        }
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        BATMST1_RD = new DBParm();
        BATMST1_RD.TableName = "BATMST1";
        IBS.DELETE(SCCGWA, BARMST1, BATMST1_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BACFMST1.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_MST1_NOTFND, BACFMST1.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BACFMST1.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_DUPKEY, BACFMST1.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BACFMST1.RETURN_INFO = 'F';
        } else {
            BACFMST1.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_SYS_ERR, BACFMST1.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
