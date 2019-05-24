package com.hisun.GD;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDZRSTAC {
    DBParm GDTSTAC_RD;
    brParm GDTSTAC_BR = new brParm();
    boolean pgmRtn = false;
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    GDRSTAC GDRSTAC = new GDRSTAC();
    SCCGWA SCCGWA;
    GDCRSTAC GDCRSTAC;
    GDRSTAC GDRSTAC1;
    public void MP(SCCGWA SCCGWA, GDCRSTAC GDCRSTAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCRSTAC = GDCRSTAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDZRSTAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GDRSTAC1 = (GDRSTAC) GDCRSTAC.REC_PTR;
        GDCRSTAC.RETURN_INFO = 'F';
        IBS.init(SCCGWA, GDRSTAC);
        IBS.CLONE(SCCGWA, GDRSTAC1, GDRSTAC);
        GDCRSTAC.RC.RC_MMO = "GD";
        GDCRSTAC.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCRSTAC.FUNC);
        if (GDCRSTAC.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (GDCRSTAC.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (GDCRSTAC.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (GDCRSTAC.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (GDCRSTAC.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (GDCRSTAC.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + GDCRSTAC.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, GDRSTAC, GDRSTAC1);
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
        if (GDCRSTAC.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (GDCRSTAC.OPT == 'K') {
            B060_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (GDCRSTAC.OPT == 'C') {
            B060_60_STBR_BY_STAC_PROC();
            if (pgmRtn) return;
        } else if (GDCRSTAC.OPT == 'T') {
            B060_70_STBR_BY_INTAC_PROC();
            if (pgmRtn) return;
        } else if (GDCRSTAC.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (GDCRSTAC.OPT == 'E') {
            B060_40_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + GDCRSTAC.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_10_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_PROC();
        if (pgmRtn) return;
    }
    public void B060_40_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void B060_60_STBR_BY_STAC_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_STAC_PROC();
        if (pgmRtn) return;
    }
    public void B060_70_STBR_BY_INTAC_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_INTAC_PROC();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        IBS.READ(SCCGWA, GDRSTAC, GDTSTAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        GDTSTAC_RD.errhdl = true;
        IBS.WRITE(SCCGWA, GDRSTAC, GDTSTAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_STAC_REC_EXIST, GDCRSTAC.RC);
            GDCRSTAC.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        GDTSTAC_RD.upd = true;
        IBS.READ(SCCGWA, GDRSTAC, GDTSTAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_STAC_REC_NOTFND, GDCRSTAC.RC);
            GDCRSTAC.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        IBS.REWRITE(SCCGWA, GDRSTAC, GDTSTAC_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        IBS.DELETE(SCCGWA, GDRSTAC, GDTSTAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_STAC_REC_NOTFND, GDCRSTAC.RC);
            GDCRSTAC.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        GDTSTAC_BR.rp = new DBParm();
        GDTSTAC_BR.rp.TableName = "GDTSTAC";
        GDTSTAC_BR.rp.order = "CRT_DATE";
        IBS.STARTBR(SCCGWA, GDRSTAC, GDTSTAC_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        GDTSTAC_BR.rp = new DBParm();
        GDTSTAC_BR.rp.TableName = "GDTSTAC";
        GDTSTAC_BR.rp.where = "AC = :GDRSTAC.KEY.AC "
            + "AND AC_SEQ = :GDRSTAC.KEY.AC_SEQ "
            + "AND RELAT_STS = 'N'";
        GDTSTAC_BR.rp.order = "CRT_DATE";
        IBS.STARTBR(SCCGWA, GDRSTAC, this, GDTSTAC_BR);
    }
    public void T000_STARTBR_BY_STAC_PROC() throws IOException,SQLException,Exception {
        GDTSTAC_BR.rp = new DBParm();
        GDTSTAC_BR.rp.TableName = "GDTSTAC";
        GDTSTAC_BR.rp.where = "ST_AC = :GDRSTAC.ST_AC "
            + "AND RELAT_STS = 'N'";
        GDTSTAC_BR.rp.order = "CRT_DATE";
        IBS.STARTBR(SCCGWA, GDRSTAC, this, GDTSTAC_BR);
    }
    public void T000_STARTBR_BY_INTAC_PROC() throws IOException,SQLException,Exception {
        GDTSTAC_BR.rp = new DBParm();
        GDTSTAC_BR.rp.TableName = "GDTSTAC";
        GDTSTAC_BR.rp.where = "INT_AC = :GDRSTAC.INT_AC "
            + "AND RELAT_STS = 'N'";
        GDTSTAC_BR.rp.order = "CRT_DATE";
        IBS.STARTBR(SCCGWA, GDRSTAC, this, GDTSTAC_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, GDRSTAC, this, GDTSTAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_STAC_REC_NOTFND, GDCRSTAC.RC);
            GDCRSTAC.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, GDTSTAC_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (GDCRSTAC.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "GDCRSTAC=");
            CEP.TRC(SCCGWA, GDCRSTAC);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
