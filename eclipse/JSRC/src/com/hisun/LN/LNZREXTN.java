package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZREXTN {
    DBParm LNTEXTN_RD;
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNREXTN LNREXTN = new LNREXTN();
    SCCGWA SCCGWA;
    LNCREXTN LNCREXTN;
    LNREXTN LNREXTN1;
    public void MP(SCCGWA SCCGWA, LNCREXTN LNCREXTN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCREXTN = LNCREXTN;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZREXTN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNREXTN1 = (LNREXTN) LNCREXTN.REC_PTR;
        LNCREXTN.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNREXTN);
        IBS.CLONE(SCCGWA, LNREXTN1, LNREXTN);
        LNCREXTN.RC.RC_MMO = "LN";
        LNCREXTN.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCREXTN.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCREXTN.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCREXTN.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCREXTN.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCREXTN.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCREXTN.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNREXTN, LNREXTN1);
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
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        IBS.READ(SCCGWA, LNREXTN, LNTEXTN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RECO_NOTFND, LNCREXTN.RC);
            LNCREXTN.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNREXTN.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNREXTN.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNREXTN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNREXTN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        LNTEXTN_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNREXTN, LNTEXTN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.TRC(SCCGWA, "DUPKEY.......");
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCREXTN.RC);
            LNCREXTN.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        LNTEXTN_RD.upd = true;
        IBS.READ(SCCGWA, LNREXTN, LNTEXTN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RECO_NOTFND, LNCREXTN.RC);
            LNCREXTN.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNREXTN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNREXTN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        IBS.REWRITE(SCCGWA, LNREXTN, LNTEXTN_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        IBS.DELETE(SCCGWA, LNREXTN, LNTEXTN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RECO_NOTFND, LNCREXTN.RC);
            LNCREXTN.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCREXTN.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCREXTN=");
            CEP.TRC(SCCGWA, LNCREXTN);
            CEP.TRC(SCCGWA, "LNREXTN =");
            CEP.TRC(SCCGWA, LNREXTN);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
