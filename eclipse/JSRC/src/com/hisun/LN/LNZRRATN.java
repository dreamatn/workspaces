package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRRATN {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTRATN_RD;
    brParm LNTRATN_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRRATN LNRRATN = new LNRRATN();
    SCCGWA SCCGWA;
    LNCRRATN LNCRRATN;
    String LK_REC = " ";
    public void MP(SCCGWA SCCGWA, LNCRRATN LNCRRATN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRRATN = LNCRRATN;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRRATN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LK_REC = IBS.CLS2CPY(SCCGWA, LNCRRATN.REC_PTR);
        LNCRRATN.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRRATN);
        if (LK_REC == null) LK_REC = "";
        JIBS_tmp_int = LK_REC.length();
        for (int i=0;i<4096-JIBS_tmp_int;i++) LK_REC += " ";
        JIBS_tmp_str[0] = LK_REC.substring(0, LNCRRATN.REC_LEN);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNRRATN);
        LNCRRATN.RC.RC_MMO = "LN";
        LNCRRATN.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRRATN.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATN.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATN.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATN.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATN.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATN.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATN.FUNC == 'F') {
            B070_QUERYFIRST_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRRATN.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNRRATN);
        LK_REC = JIBS_tmp_str[0];
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
    public void B070_QUERYFIRST_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READFIRST_REC_PROC();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (LNCRRATN.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATN.OPT == 'D') {
            B060_10_START_DT_DESC_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATN.OPT == 'V') {
            B060_20_STBR_REVERSE_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATN.OPT == 'A') {
            B060_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATN.OPT == 'U') {
            B060_20_STBR_BY_KEY1_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATN.OPT == 'N') {
            B060_20_STBR_BY_KEY2_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATN.OPT == 'M') {
            B060_20_STBR_BY_KEY2_F_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATN.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRRATN.OPT == 'E') {
            B060_50_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRRATN.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_10_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC();
        if (pgmRtn) return;
    }
    public void B060_10_START_DT_DESC_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_DT_DESC_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_REVERSE_KEY_PROC() throws IOException,SQLException,Exception {
        T000_STBR_REVERSE_KEY_PROC();
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
    public void B060_20_STBR_BY_KEY2_F_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY2_F_PROC();
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
        LNTRATN_RD = new DBParm();
        LNTRATN_RD.TableName = "LNTRATN";
        IBS.READ(SCCGWA, LNRRATN, LNTRATN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATN_NOTFND, LNCRRATN.RC);
            LNCRRATN.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READFIRST_REC_PROC() throws IOException,SQLException,Exception {
        LNTRATN_RD = new DBParm();
        LNTRATN_RD.TableName = "LNTRATN";
        LNTRATN_RD.where = "CONTRACT_NO = :LNRRATN.KEY.CONTRACT_NO "
            + "AND ACTV_DT <= :LNRRATN.KEY.ACTV_DT";
        LNTRATN_RD.fst = true;
        LNTRATN_RD.order = "ACTV_DT DESC";
        IBS.READ(SCCGWA, LNRRATN, this, LNTRATN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATN_NOTFND, LNCRRATN.RC);
            LNCRRATN.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRRATN.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRRATN.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRRATN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRRATN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTRATN_RD = new DBParm();
        LNTRATN_RD.TableName = "LNTRATN";
        LNTRATN_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRRATN, LNTRATN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRRATN.RC);
            LNCRRATN.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTRATN_RD = new DBParm();
        LNTRATN_RD.TableName = "LNTRATN";
        LNTRATN_RD.upd = true;
        IBS.READ(SCCGWA, LNRRATN, LNTRATN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATN_NOTFND, LNCRRATN.RC);
            LNCRRATN.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRRATN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRRATN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTRATN_RD = new DBParm();
        LNTRATN_RD.TableName = "LNTRATN";
        IBS.REWRITE(SCCGWA, LNRRATN, LNTRATN_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTRATN_RD = new DBParm();
        LNTRATN_RD.TableName = "LNTRATN";
        IBS.DELETE(SCCGWA, LNRRATN, LNTRATN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATN_NOTFND, LNCRRATN.RC);
            LNCRRATN.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTRATN_BR.rp = new DBParm();
        LNTRATN_BR.rp.TableName = "LNTRATN";
        LNTRATN_BR.rp.order = "CONTRACT_NO,ACTV_DT";
        IBS.STARTBR(SCCGWA, LNRRATN, LNTRATN_BR);
    }
    public void T000_STARTBR_DT_DESC_PROC() throws IOException,SQLException,Exception {
        LNTRATN_BR.rp = new DBParm();
        LNTRATN_BR.rp.TableName = "LNTRATN";
        LNTRATN_BR.rp.order = "CONTRACT_NO,ACTV_DT DESC";
        IBS.STARTBR(SCCGWA, LNRRATN, LNTRATN_BR);
    }
    public void T000_STBR_REVERSE_KEY_PROC() throws IOException,SQLException,Exception {
        LNTRATN_BR.rp = new DBParm();
        LNTRATN_BR.rp.TableName = "LNTRATN";
        LNTRATN_BR.rp.where = "CONTRACT_NO <= :LNRRATN.KEY.CONTRACT_NO "
            + "AND ACTV_DT <= :LNRRATN.KEY.ACTV_DT";
        LNTRATN_BR.rp.order = "CONTRACT_NO,ACTV_DT";
        IBS.STARTBR(SCCGWA, LNRRATN, this, LNTRATN_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTRATN_BR.rp = new DBParm();
        LNTRATN_BR.rp.TableName = "LNTRATN";
        LNTRATN_BR.rp.where = "CONTRACT_NO >= :LNRRATN.KEY.CONTRACT_NO "
            + "AND ACTV_DT >= :LNRRATN.KEY.ACTV_DT";
        LNTRATN_BR.rp.order = "CONTRACT_NO,ACTV_DT";
        IBS.STARTBR(SCCGWA, LNRRATN, this, LNTRATN_BR);
    }
    public void T000_STARTBR_BY_KEY1_PROC() throws IOException,SQLException,Exception {
        LNTRATN_BR.rp = new DBParm();
        LNTRATN_BR.rp.TableName = "LNTRATN";
        LNTRATN_BR.rp.where = "CONTRACT_NO = :LNRRATN.KEY.CONTRACT_NO "
            + "AND ACTV_DT >= :LNRRATN.KEY.ACTV_DT";
        LNTRATN_BR.rp.order = "ACTV_DT";
        IBS.STARTBR(SCCGWA, LNRRATN, this, LNTRATN_BR);
    }
    public void T000_STARTBR_BY_KEY2_PROC() throws IOException,SQLException,Exception {
        LNTRATN_BR.rp = new DBParm();
        LNTRATN_BR.rp.TableName = "LNTRATN";
        LNTRATN_BR.rp.where = "CONTRACT_NO = :LNRRATN.KEY.CONTRACT_NO "
            + "AND ACTV_DT <= :LNRRATN.KEY.ACTV_DT";
        LNTRATN_BR.rp.order = "ACTV_DT DESC";
        IBS.STARTBR(SCCGWA, LNRRATN, this, LNTRATN_BR);
    }
    public void T000_STARTBR_BY_KEY2_F_PROC() throws IOException,SQLException,Exception {
        LNTRATN_BR.rp = new DBParm();
        LNTRATN_BR.rp.TableName = "LNTRATN";
        LNTRATN_BR.rp.where = "CONTRACT_NO = :LNRRATN.KEY.CONTRACT_NO "
            + "AND ACTV_DT <= :LNRRATN.KEY.ACTV_DT";
        LNTRATN_BR.rp.order = "ACTV_DT DESC";
        IBS.STARTBR(SCCGWA, LNRRATN, this, LNTRATN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATN_NOTFND, LNCRRATN.RC);
            LNCRRATN.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRRATN, this, LNTRATN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATN_NOTFND, LNCRRATN.RC);
            LNCRRATN.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRATN_BR);
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
