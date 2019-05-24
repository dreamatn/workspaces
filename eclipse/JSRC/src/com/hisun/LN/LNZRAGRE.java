package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRAGRE {
    DBParm LNTAGRE_RD;
    brParm LNTAGRE_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRAGRE LNRAGRE = new LNRAGRE();
    SCCGWA SCCGWA;
    LNCRAGRE LNCRAGRE;
    LNRAGRE LNRAGREA;
    public void MP(SCCGWA SCCGWA, LNCRAGRE LNCRAGRE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRAGRE = LNCRAGRE;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRAGRE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRAGREA = (LNRAGRE) LNCRAGRE.REC_PTR;
        LNCRAGRE.RETURN_INFO = 'F';
        CEP.TRC(SCCGWA, LNRAGRE.KEY.CONTRACT_NO);
        IBS.CLONE(SCCGWA, LNRAGREA, LNRAGRE);
        LNCRAGRE.RC.RC_MMO = "LN";
        LNCRAGRE.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRAGRE.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRAGRE.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRAGRE.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRAGRE.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRAGRE.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRAGRE.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRAGRE.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRAGRE, LNRAGREA);
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
        if (LNCRAGRE.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRAGRE.OPT == 'A') {
            B060_30_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRAGRE.OPT == 'R') {
            B060_70_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRAGRE.OPT == 'E') {
            B060_90_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRAGRE.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_10_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_STBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY_PROC();
        if (pgmRtn) return;
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
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        IBS.READ(SCCGWA, LNRAGRE, LNTAGRE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AGRE_NOTFND, LNCRAGRE.RC);
            LNCRAGRE.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRAGRE.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRAGRE.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRAGRE.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRAGRE.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRAGRE, LNTAGRE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRAGRE.RC);
            LNCRAGRE.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.upd = true;
        IBS.READ(SCCGWA, LNRAGRE, LNTAGRE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AGRE_NOTFND, LNCRAGRE.RC);
            LNCRAGRE.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRAGRE.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRAGRE.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        IBS.REWRITE(SCCGWA, LNRAGRE, LNTAGRE_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        IBS.DELETE(SCCGWA, LNRAGRE, LNTAGRE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AGRE_NOTFND, LNCRAGRE.RC);
            LNCRAGRE.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTAGRE_BR.rp = new DBParm();
        LNTAGRE_BR.rp.TableName = "LNTAGRE";
        LNTAGRE_BR.rp.order = "CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRAGRE, LNTAGRE_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTAGRE_BR.rp = new DBParm();
        LNTAGRE_BR.rp.TableName = "LNTAGRE";
        LNTAGRE_BR.rp.where = "CONTRACT_NO >= :LNRAGRE.KEY.CONTRACT_NO";
        LNTAGRE_BR.rp.order = "CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRAGRE, this, LNTAGRE_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRAGRE, this, LNTAGRE_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AGRE_NOTFND, LNCRAGRE.RC);
            LNCRAGRE.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTAGRE_BR);
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
