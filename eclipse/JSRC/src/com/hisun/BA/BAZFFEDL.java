package com.hisun.BA;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BAZFFEDL {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm BATFEDL_RD;
    int WS_LNE = 0;
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BARFEDL BARFEDL = new BARFEDL();
    SCCGWA SCCGWA;
    BACFFEDL BACFFEDL;
    String LK_REC = " ";
    public void MP(SCCGWA SCCGWA, BACFFEDL BACFFEDL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BACFFEDL = BACFFEDL;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BAZFFEDL return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LK_REC = IBS.CLS2CPY(SCCGWA, BACFFEDL.REC_PTR);
        CEP.TRC(SCCGWA, "123");
        IBS.init(SCCGWA, BARFEDL);
        CEP.TRC(SCCGWA, "789");
        if (LK_REC == null) LK_REC = "";
        JIBS_tmp_int = LK_REC.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) LK_REC += " ";
        JIBS_tmp_str[0] = LK_REC.substring(0, BACFFEDL.REC_LEN);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BARFEDL);
        BACFFEDL.RC.RC_MMO = "BA";
        BACFFEDL.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, "1111111111111111111111111111111");
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BACFFEDL.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
        } else if (BACFFEDL.FUNC == 'L') {
            B020_QUERY_RECORD_PROC();
        } else if (BACFFEDL.FUNC == 'G') {
            B025_QUERY_RECORD_PROC();
        } else if (BACFFEDL.FUNC == 'A') {
            CEP.TRC(SCCGWA, "AAAAAAAAAAAAA");
            B030_ADD_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BACFFEDL.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BARFEDL);
        LK_REC = JIBS_tmp_str[0];
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_PROC();
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_BY_IND_PROC();
    }
    public void B025_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T100_READ_REC_BY_IND_PROC();
    }
    public void B030_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_REC_PROC();
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        BATFEDL_RD = new DBParm();
        BATFEDL_RD.TableName = "BATFEDL";
        IBS.READ(SCCGWA, BARFEDL, BATFEDL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BACFFEDL.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_FEDL_NOTFND, BACFFEDL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BACFFEDL.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_DUPKEY, BACFFEDL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BACFFEDL.RETURN_INFO = 'F';
        } else {
            BACFFEDL.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_SYS_ERR, BACFFEDL.RC);
        }
    }
    public void T100_READ_REC_BY_IND_PROC() throws IOException,SQLException,Exception {
        BATFEDL_RD = new DBParm();
        BATFEDL_RD.TableName = "BATFEDL";
        BATFEDL_RD.where = "TX_TYP = :BARFEDL.KEY.TX_TYP "
            + "AND CNTR_NO = :BARFEDL.KEY.CNTR_NO "
            + "AND ACCT_CNT = :BARFEDL.KEY.ACCT_CNT "
            + "AND FEE_TYP = :BARFEDL.KEY.FEE_TYP "
            + "AND TX_AC = :BARFEDL.TX_AC";
        BATFEDL_RD.fst = true;
        BATFEDL_RD.order = "TS DESC";
        IBS.READ(SCCGWA, BARFEDL, this, BATFEDL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BACFFEDL.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_FEDL_NOTFND, BACFFEDL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BACFFEDL.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_DUPKEY, BACFFEDL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BACFFEDL.RETURN_INFO = 'F';
        } else {
            BACFFEDL.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_SYS_ERR, BACFFEDL.RC);
        }
    }
    public void T000_READ_REC_BY_IND_PROC() throws IOException,SQLException,Exception {
        BATFEDL_RD = new DBParm();
        BATFEDL_RD.TableName = "BATFEDL";
        BATFEDL_RD.where = "TX_TYP = :BARFEDL.KEY.TX_TYP "
            + "AND CNTR_NO = :BARFEDL.KEY.CNTR_NO "
            + "AND ACCT_CNT = :BARFEDL.KEY.ACCT_CNT "
            + "AND FEE_TYP = :BARFEDL.KEY.FEE_TYP";
        BATFEDL_RD.fst = true;
        BATFEDL_RD.order = "TS DESC";
        IBS.READ(SCCGWA, BARFEDL, this, BATFEDL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BACFFEDL.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_FEDL_NOTFND, BACFFEDL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BACFFEDL.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_DUPKEY, BACFFEDL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BACFFEDL.RETURN_INFO = 'F';
        } else {
            BACFFEDL.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_SYS_ERR, BACFFEDL.RC);
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        BARFEDL.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARFEDL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BARFEDL.UPDT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARFEDL.UPDT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, BARFEDL.KEY.CNTR_NO);
        CEP.TRC(SCCGWA, BARFEDL.KEY.ACCT_CNT);
        CEP.TRC(SCCGWA, BARFEDL.KEY.FEE_TYP);
        BATFEDL_RD = new DBParm();
        BATFEDL_RD.TableName = "BATFEDL";
        BATFEDL_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BARFEDL, BATFEDL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BACFFEDL.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_FEDL_NOTFND, BACFFEDL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BACFFEDL.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_DUPKEY, BACFFEDL.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BACFFEDL.RETURN_INFO = 'F';
        } else {
            BACFFEDL.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_SYS_ERR, BACFFEDL.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
