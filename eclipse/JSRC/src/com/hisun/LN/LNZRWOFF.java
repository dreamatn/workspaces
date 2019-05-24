package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;

public class LNZRWOFF {
    boolean pgmRtn = false;
    String WS_FATHER_WOFFRACT = " ";
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRWOFF LNRWOFF = new LNRWOFF();
    SCCGWA SCCGWA;
    LNCRWOFF LNCRWOFF;
    LNRWOFF LNRWOFF1;
    public void MP(SCCGWA SCCGWA, LNCRWOFF LNCRWOFF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRWOFF = LNCRWOFF;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRWOFF return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRWOFF1 = (LNRWOFF) LNCRWOFF.REC_PTR;
        LNCRWOFF.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRWOFF);
        IBS.CLONE(SCCGWA, LNRWOFF1, LNRWOFF);
        LNCRWOFF.RC.RC_MMO = "LN";
        LNCRWOFF.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, LNRWOFF.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRWOFF.KEY.TYPE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRWOFF.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRWOFF.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRWOFF.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRWOFF.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRWOFF.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRWOFF.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRWOFF.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRWOFF, LNRWOFF1);
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
        if (LNCRWOFF.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRWOFF.OPT == 'A') {
            B060_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRWOFF.OPT == 'T') {
            B060_20_STBR_BY_TYPE();
            if (pgmRtn) return;
        } else if (LNCRWOFF.OPT == 'D') {
            B060_20_STBR_BY_APP_DATE();
            if (pgmRtn) return;
        } else if (LNCRWOFF.OPT == 'C') {
            B060_20_STBR_BY_CTA_NO();
            if (pgmRtn) return;
        } else if (LNCRWOFF.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRWOFF.OPT == 'E') {
            B060_50_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRWOFF.OPT + ")";
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
    public void B060_20_STBR_BY_TYPE() throws IOException,SQLException,Exception {
        T000_STBR_BY_TYPE();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_APP_DATE() throws IOException,SQLException,Exception {
        T000_STBR_BY_APP_DATE();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_CTA_NO() throws IOException,SQLException,Exception {
        T000_STBR_BY_CTA_NO();
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
        LNTWOFF_RD = new DBParm();
        LNTWOFF_RD.TableName = "LNTWOFF";
        IBS.READ(SCCGWA, LNRWOFF, LNTWOFF_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
