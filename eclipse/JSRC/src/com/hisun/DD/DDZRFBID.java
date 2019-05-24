package com.hisun.DD;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZRFBID {
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTFBID_RD;
    brParm DDTFBID_BR = new brParm();
    boolean pgmRtn = false;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    DDRFBID DDRFBID = new DDRFBID();
    SCCGWA SCCGWA;
    DDCRFBID DDCRFBID;
    DDRFBID DDRFBIL;
    public void MP(SCCGWA SCCGWA, DDCRFBID DDCRFBID) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCRFBID = DDCRFBID;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZRFBID return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        DDRFBIL = (DDRFBID) DDCRFBID.REC_PTR;
        DDCRFBID.RETURN_INFO = 'F';
        IBS.init(SCCGWA, DDRFBID);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDRFBIL);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, DDRFBIL, DDRFBID);
        DDCRFBID.RC.RC_MMO = "DD";
        DDCRFBID.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DDCRFBID.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (DDCRFBID.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (DDCRFBID.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (DDCRFBID.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (DDCRFBID.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (DDCRFBID.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCRFBID.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDRFBID);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, DDRFBID, DDRFBIL);
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
        if (DDCRFBID.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (DDCRFBID.OPT == 'K') {
            B060_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (DDCRFBID.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (DDCRFBID.OPT == 'E') {
            B060_40_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + DDCRFBID.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_10_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "AAAAAA");
        CEP.TRC(SCCGWA, DDRFBID.TYPE);
        CEP.TRC(SCCGWA, DDRFBID.STS);
        if (DDRFBID.TYPE != ' ') {
            if (DDRFBID.STS != ' ') {
                T000_STARTBR_BY_KEY_01_PROC();
                if (pgmRtn) return;
            } else {
                T000_STARTBR_BY_KEY_02_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (DDRFBID.STS != ' ') {
                T000_STARTBR_BY_KEY_03_PROC();
                if (pgmRtn) return;
            } else {
                T000_STARTBR_BY_KEY_04_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B060_30_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_PROC();
        if (pgmRtn) return;
    }
    public void B060_40_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        DDTFBID_RD = new DBParm();
        DDTFBID_RD.TableName = "DDTFBID";
        DDTFBID_RD.where = "REF_NO = :DDRFBID.KEY.REF_NO";
        IBS.READ(SCCGWA, DDRFBID, this, DDTFBID_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_FBID_REC_NOTFND, DDCRFBID.RC);
            DDCRFBID.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        DDTFBID_RD = new DBParm();
        DDTFBID_RD.TableName = "DDTFBID";
        DDTFBID_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRFBID, DDTFBID_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_FBID_REC_EXIST, DDCRFBID.RC);
            DDCRFBID.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        DDTFBID_RD = new DBParm();
        DDTFBID_RD.TableName = "DDTFBID";
        DDTFBID_RD.upd = true;
        IBS.READ(SCCGWA, DDRFBID, DDTFBID_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_FBID_REC_NOTFND, DDCRFBID.RC);
            DDCRFBID.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        DDTFBID_RD = new DBParm();
        DDTFBID_RD.TableName = "DDTFBID";
        IBS.REWRITE(SCCGWA, DDRFBID, DDTFBID_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        DDTFBID_RD = new DBParm();
        DDTFBID_RD.TableName = "DDTFBID";
        IBS.DELETE(SCCGWA, DDRFBID, DDTFBID_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_FBID_REC_NOTFND, DDCRFBID.RC);
            DDCRFBID.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        DDTFBID_BR.rp = new DBParm();
        DDTFBID_BR.rp.TableName = "DDTFBID";
        DDTFBID_BR.rp.order = "AC";
        IBS.STARTBR(SCCGWA, DDRFBID, DDTFBID_BR);
    }
    public void T000_STARTBR_BY_KEY_01_PROC() throws IOException,SQLException,Exception {
        DDTFBID_BR.rp = new DBParm();
        DDTFBID_BR.rp.TableName = "DDTFBID";
        DDTFBID_BR.rp.where = "AC = :DDRFBID.AC "
            + "AND TYPE = :DDRFBID.TYPE "
            + "AND ( STS = '1' "
            + "OR STS = '3' )";
        IBS.STARTBR(SCCGWA, DDRFBID, this, DDTFBID_BR);
    }
    public void T000_STARTBR_BY_KEY_02_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRFBID.AC);
        CEP.TRC(SCCGWA, DDRFBID.TYPE);
        DDTFBID_BR.rp = new DBParm();
        DDTFBID_BR.rp.TableName = "DDTFBID";
        DDTFBID_BR.rp.where = "AC = :DDRFBID.AC "
            + "AND TYPE = :DDRFBID.TYPE "
            + "AND ( STS = '1' "
            + "OR STS = '3' )";
        IBS.STARTBR(SCCGWA, DDRFBID, this, DDTFBID_BR);
    }
    public void T000_STARTBR_BY_KEY_03_PROC() throws IOException,SQLException,Exception {
        DDTFBID_BR.rp = new DBParm();
        DDTFBID_BR.rp.TableName = "DDTFBID";
        DDTFBID_BR.rp.where = "AC = :DDRFBID.AC "
            + "AND ( STS = '1' "
            + "OR STS = '3' )";
        IBS.STARTBR(SCCGWA, DDRFBID, this, DDTFBID_BR);
    }
    public void T000_STARTBR_BY_KEY_04_PROC() throws IOException,SQLException,Exception {
        DDTFBID_BR.rp = new DBParm();
        DDTFBID_BR.rp.TableName = "DDTFBID";
        DDTFBID_BR.rp.where = "AC = :DDRFBID.AC "
            + "AND ( STS = '1' "
            + "OR STS = '3' )";
        IBS.STARTBR(SCCGWA, DDRFBID, this, DDTFBID_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRFBID, this, DDTFBID_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            DDCRFBID.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTFBID_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCRFBID.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCRFBID=");
            CEP.TRC(SCCGWA, DDCRFBID);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
