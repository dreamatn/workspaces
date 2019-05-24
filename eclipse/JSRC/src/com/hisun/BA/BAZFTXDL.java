package com.hisun.BA;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BAZFTXDL {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm BATTXDL_RD;
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BARTXDL BARTXDL = new BARTXDL();
    SCCGWA SCCGWA;
    BACFTXDL BACFTXDL;
    String LK_REC = " ";
    public void MP(SCCGWA SCCGWA, BACFTXDL BACFTXDL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BACFTXDL = BACFTXDL;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BAZFTXDL return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LK_REC = IBS.CLS2CPY(SCCGWA, BACFTXDL.REC_PTR);
        BACFTXDL.RETURN_INFO = 'F';
        IBS.init(SCCGWA, BARTXDL);
        if (LK_REC == null) LK_REC = "";
        JIBS_tmp_int = LK_REC.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) LK_REC += " ";
        JIBS_tmp_str[0] = LK_REC.substring(0, BACFTXDL.REC_LEN);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BARTXDL);
        BACFTXDL.RC.RC_MMO = "BA";
        BACFTXDL.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, BARTXDL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BACFTXDL.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
        } else if (BACFTXDL.FUNC == 'Q') {
            B020_QUERY_RECORD_PROC();
        } else if (BACFTXDL.FUNC == 'A') {
            B030_ADD_RECORD_PROC();
        } else if (BACFTXDL.FUNC == 'R') {
            B040_READUPD_RECORD_PROC();
        } else if (BACFTXDL.FUNC == 'U') {
            B060_UPDATE_RECORD_PROC();
        } else if (BACFTXDL.FUNC == 'D') {
            B070_DELETE_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BACFTXDL.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BARTXDL);
        LK_REC = JIBS_tmp_str[0];
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_PROC();
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_BY_IND_PROC();
    }
    public void B030_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_REC_PROC();
    }
    public void B040_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPDATE_PROC();
    }
    public void B060_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_REC_PROC();
    }
    public void B070_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_REC_PROC();
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        BATTXDL_RD = new DBParm();
        BATTXDL_RD.TableName = "BATTXDL";
        IBS.READ(SCCGWA, BARTXDL, BATTXDL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BACFTXDL.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_TXDL_NOTFND, BACFTXDL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BACFTXDL.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_DUPKEY, BACFTXDL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BACFTXDL.RETURN_INFO = 'F';
        } else {
            BACFTXDL.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_SYS_ERR, BACFTXDL.RC);
        }
    }
    public void T000_READ_REC_BY_IND_PROC() throws IOException,SQLException,Exception {
        BATTXDL_RD = new DBParm();
        BATTXDL_RD.TableName = "BATTXDL";
        BATTXDL_RD.where = "CNTR_NO = :BARTXDL.CNTR_NO "
            + "AND ACCT_CNT = :BARTXDL.ACCT_CNT "
            + "AND TX_CD = :BARTXDL.TX_CD "
            + "AND FUN_CD = :BARTXDL.FUN_CD";
        IBS.READ(SCCGWA, BARTXDL, this, BATTXDL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BACFTXDL.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_TXDL_NOTFND, BACFTXDL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BACFTXDL.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_DUPKEY, BACFTXDL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BACFTXDL.RETURN_INFO = 'F';
        } else {
            BACFTXDL.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_SYS_ERR, BACFTXDL.RC);
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        BARTXDL.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BARTXDL.UPDT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.UPDT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BATTXDL_RD = new DBParm();
        BATTXDL_RD.TableName = "BATTXDL";
        BATTXDL_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BARTXDL, BATTXDL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BACFTXDL.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_TXDL_NOTFND, BACFTXDL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BACFTXDL.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_DUPKEY, BACFTXDL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BACFTXDL.RETURN_INFO = 'F';
        } else {
            BACFTXDL.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_SYS_ERR, BACFTXDL.RC);
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        BATTXDL_RD = new DBParm();
        BATTXDL_RD.TableName = "BATTXDL";
        BATTXDL_RD.upd = true;
        IBS.READ(SCCGWA, BARTXDL, BATTXDL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BACFTXDL.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_TXDL_NOTFND, BACFTXDL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BACFTXDL.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_DUPKEY, BACFTXDL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BACFTXDL.RETURN_INFO = 'F';
        } else {
            BACFTXDL.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_SYS_ERR, BACFTXDL.RC);
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        BARTXDL.UPDT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.UPDT_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BACFTXDL.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_TXDL_NOTFND, BACFTXDL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BACFTXDL.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_DUPKEY, BACFTXDL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BACFTXDL.RETURN_INFO = 'F';
        } else {
            BACFTXDL.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_SYS_ERR, BACFTXDL.RC);
        }
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        BATTXDL_RD = new DBParm();
        BATTXDL_RD.TableName = "BATTXDL";
        IBS.DELETE(SCCGWA, BARTXDL, BATTXDL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BACFTXDL.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_TXDL_NOTFND, BACFTXDL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BACFTXDL.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_DUPKEY, BACFTXDL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BACFTXDL.RETURN_INFO = 'F';
        } else {
            BACFTXDL.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_SYS_ERR, BACFTXDL.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
