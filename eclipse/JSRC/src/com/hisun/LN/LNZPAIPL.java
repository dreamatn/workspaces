package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;

public class LNZPAIPL {
    boolean pgmRtn = false;
    String K_TBL_PAIP = "LNTPAIP";
    int WS_LEN = 0;
    short WS_TOT_TENORS = 0;
    short WS_CAL_TENORS = 0;
    double WS_TOT_PRIN = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRPAIP LNRPAIP = new LNRPAIP();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCGWA SCCGWA;
    LNCRPAIP LNCRPAIP;
    LNRPAIP LNRPAIPA;
    public void MP(SCCGWA SCCGWA, LNCRPAIP LNCRPAIP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRPAIP = LNCRPAIP;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZPAIPL return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPAIP);
        LNRPAIPA = (LNRPAIP) LNCRPAIP.REC_PTR;
        LNCRPAIP.RETURN_INFO = 'F';
        IBS.CLONE(SCCGWA, LNRPAIPA, LNRPAIP);
        LNCRPAIP.RC.RC_MMO = "LN";
        LNCRPAIP.RC.RC_CODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRPAIP.FUNC == 'A') {
            B02_FUNC_WRITE();
            if (pgmRtn) return;
        } else if (LNCRPAIP.FUNC == 'D') {
            B03_FUNC_DELETE();
            if (pgmRtn) return;
        } else if (LNCRPAIP.FUNC == 'U') {
            B04_FUNC_REWRITE();
            if (pgmRtn) return;
        } else if (LNCRPAIP.FUNC == 'I') {
            B05_FUNC_READ();
            if (pgmRtn) return;
        } else if (LNCRPAIP.FUNC == 'R') {
            B06_FUNC_READ_UPDATE();
            if (pgmRtn) return;
        } else if (LNCRPAIP.FUNC == 'B') {
            B07_FUNC_BROWSE();
            if (pgmRtn) return;
        } else if (LNCRPAIP.FUNC == 'G') {
            B08_FUNC_GROUP();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCRPAIP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, LNRPAIP, LNRPAIPA);
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
    }
    public void B02_FUNC_WRITE() throws IOException,SQLException,Exception {
        T00_WRITE_LNTPAIP();
        if (pgmRtn) return;
    }
    public void B03_FUNC_DELETE() throws IOException,SQLException,Exception {
        T00_DELETE_LNTPAIP();
        if (pgmRtn) return;
    }
    public void B04_FUNC_REWRITE() throws IOException,SQLException,Exception {
        T00_REWRITE_LNTPAIP();
        if (pgmRtn) return;
    }
    public void B05_FUNC_READ() throws IOException,SQLException,Exception {
        T00_READ_LNTPAIP();
        if (pgmRtn) return;
    }
    public void B06_FUNC_READ_UPDATE() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = 'Y';
        T00_READUPDATE_LNTPAIP();
        if (pgmRtn) return;
        if (WS_FOUND_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PAIP_NOTFND, LNCRPAIP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B07_FUNC_BROWSE() throws IOException,SQLException,Exception {
        if (LNCRPAIP.OPT == 'S') {
            T000_STARTBR_PROCESS();
            if (pgmRtn) return;
        }
        if (LNCRPAIP.OPT == 'R') {
            T000_READ_NEXT_PROCESS();
            if (pgmRtn) return;
        }
        if (LNCRPAIP.OPT == 'E') {
            T000_ENDBR_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B08_FUNC_GROUP() throws IOException,SQLException,Exception {
        T00_GROUP_LNTPAIP();
        if (pgmRtn) return;
    }
    public void R00_READ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPAIP);
        if (WS_UPDATE_FLG == 'Y') {
            T00_READUPDATE_LNTPAIP();
            if (pgmRtn) return;
        } else {
            T00_READ_LNTPAIP();
            if (pgmRtn) return;
        }
    }
    public void T00_WRITE_LNTPAIP() throws IOException,SQLException,Exception {
        LNRPAIP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPAIP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        LNTPAIP_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRPAIP, LNTPAIP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            LNCRPAIP.RETURN_INFO = 'F';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_NORMAL, LNCRPAIP.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            LNCRPAIP.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PAIP_EXIST, LNCRPAIP.RC);
        } else {
            LNCRPAIP.RETURN_INFO = 'O';
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_PAIP;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
        }
    }
    public void T00_DELETE_LNTPAIP() throws IOException,SQLException,Exception {
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        IBS.DELETE(SCCGWA, LNRPAIP, LNTPAIP_RD);
    }
    public void T00_READ_LNTPAIP() throws IOException,SQLException,Exception {
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        LNTPAIP_RD.errhdl = true;
        IBS.READ(SCCGWA, LNRPAIP, LNTPAIP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            LNCRPAIP.RETURN_INFO = 'F';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_NORMAL, LNCRPAIP.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNCRPAIP.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PAIP_NOTFND, LNCRPAIP.RC);
        } else {
            LNCRPAIP.RETURN_INFO = 'O';
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_PAIP;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
        }
    }
    public void T00_READUPDATE_LNTPAIP() throws IOException,SQLException,Exception {
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        LNTPAIP_RD.upd = true;
        LNTPAIP_RD.errhdl = true;
        IBS.READ(SCCGWA, LNRPAIP, LNTPAIP_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_REWRITE_LNTPAIP() throws IOException,SQLException,Exception {
        LNRPAIP.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPAIP.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRPAIP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPAIP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        IBS.REWRITE(SCCGWA, LNRPAIP, LNTPAIP_RD);
    }
    public void T000_STARTBR_PROCESS() throws IOException,SQLException,Exception {
        LNTPAIP_BR.rp = new DBParm();
        LNTPAIP_BR.rp.TableName = "LNTPAIP";
        LNTPAIP_BR.rp.eqWhere = "CONTRACT_NO";
        LNTPAIP_BR.rp.errhdl = true;
        LNTPAIP_BR.rp.order = "CONTRACT_NO, SUB_CTA_NO, PHS_NO";
        IBS.STARTBR(SCCGWA, LNRPAIP, LNTPAIP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNCRPAIP.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PAIP_NOTFND, LNCRPAIP.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            LNCRPAIP.RETURN_INFO = 'F';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_NORMAL, LNCRPAIP.RC);
        } else {
            LNCRPAIP.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCRPAIP.RC);
        }
    }
    public void T000_READ_NEXT_PROCESS() throws IOException,SQLException,Exception {
        LNTPAIP_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, LNRPAIP, this, LNTPAIP_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNCRPAIP.RETURN_INFO = 'E';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PAIP_NOTFND, LNCRPAIP.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            LNCRPAIP.RETURN_INFO = 'F';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_NORMAL, LNCRPAIP.RC);
        } else {
            LNCRPAIP.RETURN_INFO = 'O';
        }
    }
    public void T000_ENDBR_PROCESS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTPAIP_BR);
    }
    public void T00_GROUP_LNTPAIP() throws IOException,SQLException,Exception {
        WS_TOT_TENORS = 0;
        WS_TOT_PRIN = 0;
