package com.hisun.DD;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZRVSTR {
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTVSTRN_RD;
    brParm DDTVSTRN_BR = new brParm();
    boolean pgmRtn = false;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    DDRVSTRN DDRVSTRN = new DDRVSTRN();
    SCCGWA SCCGWA;
    DDCRVSTR DDCRVSTR;
    DDRVSTRN DDRVSTRL;
    public void MP(SCCGWA SCCGWA, DDCRVSTR DDCRVSTR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCRVSTR = DDCRVSTR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZRVSTR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        DDRVSTRL = (DDRVSTRN) DDCRVSTR.REC_PTR;
        DDCRVSTR.RETURN_INFO = 'F';
        IBS.init(SCCGWA, DDRVSTRN);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDRVSTRL);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, DDRVSTRL, DDRVSTRN);
        DDCRVSTR.RC.RC_MMO = "DD";
        DDCRVSTR.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCRVSTR.FUNC);
        if (DDCRVSTR.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (DDCRVSTR.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (DDCRVSTR.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (DDCRVSTR.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (DDCRVSTR.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (DDCRVSTR.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCRVSTR.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDRVSTRN);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, DDRVSTRN, DDRVSTRL);
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
        if (DDCRVSTR.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (DDCRVSTR.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (DDCRVSTR.OPT == 'E') {
            B060_40_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + DDCRVSTR.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_10_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC();
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
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        DDTVSTRN_RD = new DBParm();
        DDTVSTRN_RD.TableName = "DDTVSTRN";
        IBS.READ(SCCGWA, DDRVSTRN, DDTVSTRN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_VSAC_REC_NOTFND, DDCRVSTR.RC);
            DDCRVSTR.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        DDTVSTRN_RD = new DBParm();
        DDTVSTRN_RD.TableName = "DDTVSTRN";
        DDTVSTRN_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRVSTRN, DDTVSTRN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_VSAC_REC_EXIST, DDCRVSTR.RC);
            DDCRVSTR.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        DDTVSTRN_RD = new DBParm();
        DDTVSTRN_RD.TableName = "DDTVSTRN";
        DDTVSTRN_RD.upd = true;
        IBS.READ(SCCGWA, DDRVSTRN, DDTVSTRN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_VSAC_REC_NOTFND, DDCRVSTR.RC);
            DDCRVSTR.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        DDTVSTRN_RD = new DBParm();
        DDTVSTRN_RD.TableName = "DDTVSTRN";
        IBS.REWRITE(SCCGWA, DDRVSTRN, DDTVSTRN_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        DDTVSTRN_RD = new DBParm();
        DDTVSTRN_RD.TableName = "DDTVSTRN";
        IBS.DELETE(SCCGWA, DDRVSTRN, DDTVSTRN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_VSAC_REC_NOTFND, DDCRVSTR.RC);
            DDCRVSTR.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        DDTVSTRN_BR.rp = new DBParm();
        DDTVSTRN_BR.rp.TableName = "DDTVSTRN";
        DDTVSTRN_BR.rp.order = "CRT_DATE";
        IBS.STARTBR(SCCGWA, DDRVSTRN, DDTVSTRN_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRVSTRN, this, DDTVSTRN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_VSAC_REC_NOTFND, DDCRVSTR.RC);
            DDCRVSTR.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTVSTRN_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCRVSTR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCRVSTR=");
            CEP.TRC(SCCGWA, DDCRVSTR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
