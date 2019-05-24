package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZPYIFM {
    String JIBS_tmp_str[] = new String[10];
    brParm LNTPYIF_BR = new brParm();
    DBParm LNTPYIF_RD;
    boolean pgmRtn = false;
    char LNZPYIFM_FILLER1 = ' ';
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRPYIF LNRPYIF = new LNRPYIF();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCGWA SCCGWA;
    LNCPYIFM LNCPYIFM;
    public void MP(SCCGWA SCCGWA, LNCPYIFM LNCPYIFM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCPYIFM = LNCPYIFM;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZPYIFM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCPYIFM.RC.RC_APP = "LN";
        LNCPYIFM.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_CHECK();
        if (pgmRtn) return;
        if (LNCPYIFM.FUNC == '0') {
            B02_FUNC_WRITE();
            if (pgmRtn) return;
        } else if (LNCPYIFM.FUNC == '1') {
            B03_FUNC_DELETE();
            if (pgmRtn) return;
        } else if (LNCPYIFM.FUNC == '2') {
            B04_FUNC_REWRITE();
            if (pgmRtn) return;
        } else if (LNCPYIFM.FUNC == '3') {
            B05_FUNC_READ();
            if (pgmRtn) return;
        } else if (LNCPYIFM.FUNC == '4') {
            B06_FUNC_READ_UPDATE();
            if (pgmRtn) return;
        } else if (LNCPYIFM.FUNC == '5') {
            B07_PYIFM_FUNC_BROWSE();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCPYIFM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPYIFM.REC_DATA.KEY);
        if (JIBS_tmp_str[0].trim().length() == 0 
            || JIBS_tmp_str[0].equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_KEY_MUST_INPUT, LNCPYIFM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B02_FUNC_WRITE() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = ' ';
        B10_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PYIF_EXIST, LNCPYIFM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        LNCPYIFM.REC_DATA.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCPYIFM.REC_DATA.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        R00_COMA_RECA();
        if (pgmRtn) return;
        T00_WRITE_LNTPYIF();
        if (pgmRtn) return;
    }
    public void B03_FUNC_DELETE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPYIF);
        LNRPYIF.KEY.CONTRACT_NO = LNCPYIFM.REC_DATA.KEY.CONTRACT_NO;
        LNRPYIF.KEY.SUB_CTA_NO = LNCPYIFM.REC_DATA.KEY.SUB_CTA_NO;
        T00_READ_LNTPYIF_FIRST();
        if (pgmRtn) return;
        T00_READUPDATE_LNTPYIF_FIRST();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            T00_DELETE_LNTPYIF();
            if (pgmRtn) return;
        } else {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B04_FUNC_REWRITE() throws IOException,SQLException,Exception {
        LNCPYIFM.REC_DATA.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCPYIFM.REC_DATA.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        R00_COMA_RECA();
        if (pgmRtn) return;
        T00_REWRITE_LNTPYIF();
        if (pgmRtn) return;
    }
    public void B05_FUNC_READ() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = ' ';
        B10_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PYIF_NOTFND, LNCPYIFM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R00_RECA_COMA();
        if (pgmRtn) return;
    }
    public void B06_FUNC_READ_UPDATE() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = 'Y';
        B10_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PYIF_NOTFND, LNCPYIFM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R00_RECA_COMA();
        if (pgmRtn) return;
    }
    public void B07_PYIFM_FUNC_BROWSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPYIF);
        LNRPYIF.KEY.CONTRACT_NO = LNCPYIFM.REC_DATA.KEY.CONTRACT_NO;
        LNRPYIF.KEY.SUB_CTA_NO = LNCPYIFM.REC_DATA.KEY.SUB_CTA_NO;
        LNRPYIF.CUR_REPY_DT = LNCPYIFM.REC_DATA.CUR_REPY_DT;
        T000_STARTBR_LNTPYIF();
        if (pgmRtn) return;
        T000_READNEXT_LNTPYIF();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            LNCPYIFM.REC_DATA.AMT += LNRPYIF.REIM_PRIN_AMT;
            T000_READNEXT_LNTPYIF();
            if (pgmRtn) return;
        }
        T000_ENDBR_LNTPYIF();
        if (pgmRtn) return;
    }
    public void B10_READ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPYIF);
        LNRPYIF.KEY.CONTRACT_NO = LNCPYIFM.REC_DATA.KEY.CONTRACT_NO;
        LNRPYIF.KEY.SUB_CTA_NO = LNCPYIFM.REC_DATA.KEY.SUB_CTA_NO;
        LNRPYIF.KEY.TERM = LNCPYIFM.REC_DATA.KEY.TERM;
        LNRPYIF.KEY.REPY_SEQ = LNCPYIFM.REC_DATA.KEY.REPY_SEQ;
        if (WS_UPDATE_FLG == 'Y') {
            T00_READUPDATE_LNTPYIF();
            if (pgmRtn) return;
        } else {
            T00_READ_LNTPYIF();
            if (pgmRtn) return;
        }
    }
    public void R00_COMA_RECA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPYIF);
        LNRPYIF.KEY.CONTRACT_NO = LNCPYIFM.REC_DATA.KEY.CONTRACT_NO;
        LNRPYIF.KEY.SUB_CTA_NO = LNCPYIFM.REC_DATA.KEY.SUB_CTA_NO;
        LNRPYIF.KEY.TERM = LNCPYIFM.REC_DATA.KEY.TERM;
        LNRPYIF.KEY.REPY_SEQ = LNCPYIFM.REC_DATA.KEY.REPY_SEQ;
        LNRPYIF.REPY_MTH = LNCPYIFM.REC_DATA.REPY_MTH;
        LNRPYIF.CUR_REPY_DT = LNCPYIFM.REC_DATA.CUR_REPY_DT;
        LNRPYIF.REPY_TOT_AMT = LNCPYIFM.REC_DATA.REPY_TOT_AMT;
        LNRPYIF.REIM_PRIN_AMT = LNCPYIFM.REC_DATA.REIM_PRIN_AMT;
        LNRPYIF.REIM_INT_AMT = LNCPYIFM.REC_DATA.REIM_INT_AMT;
        LNRPYIF.PENALTY = LNCPYIFM.REC_DATA.PENALTY;
        LNRPYIF.BREAK_FUNDING_COST = LNCPYIFM.REC_DATA.BREAK_FUNDING_COST;
        LNRPYIF.CTL_SEQ = LNCPYIFM.REC_DATA.CTL_SEQ;
        LNRPYIF.SYN_FLG = LNCPYIFM.REC_DATA.SYN_FLG;
        LNRPYIF.EE_TERM = (short) LNCPYIFM.REC_DATA.EE_TERM;
        LNRPYIF.UPDTBL_DATE = LNCPYIFM.REC_DATA.UPDTBL_DATE;
        LNRPYIF.UPDTBL_TLR = LNCPYIFM.REC_DATA.UPDTBL_TLR;
    }
    public void R00_RECA_COMA() throws IOException,SQLException,Exception {
        LNCPYIFM.REC_DATA.KEY.CONTRACT_NO = LNRPYIF.KEY.CONTRACT_NO;
        LNCPYIFM.REC_DATA.KEY.SUB_CTA_NO = LNRPYIF.KEY.SUB_CTA_NO;
        LNCPYIFM.REC_DATA.KEY.TERM = LNRPYIF.KEY.TERM;
        LNCPYIFM.REC_DATA.KEY.REPY_SEQ = LNRPYIF.KEY.REPY_SEQ;
        LNCPYIFM.REC_DATA.REPY_MTH = LNRPYIF.REPY_MTH;
        LNCPYIFM.REC_DATA.CUR_REPY_DT = LNRPYIF.CUR_REPY_DT;
        LNCPYIFM.REC_DATA.REPY_TOT_AMT = LNRPYIF.REPY_TOT_AMT;
        LNCPYIFM.REC_DATA.REIM_PRIN_AMT = LNRPYIF.REIM_PRIN_AMT;
        LNCPYIFM.REC_DATA.REIM_INT_AMT = LNRPYIF.REIM_INT_AMT;
        LNCPYIFM.REC_DATA.PENALTY = LNRPYIF.PENALTY;
        LNCPYIFM.REC_DATA.BREAK_FUNDING_COST = LNRPYIF.BREAK_FUNDING_COST;
        LNCPYIFM.REC_DATA.CTL_SEQ = LNRPYIF.CTL_SEQ;
        LNCPYIFM.REC_DATA.SYN_FLG = LNRPYIF.SYN_FLG;
        LNCPYIFM.REC_DATA.EE_TERM = (double)LNRPYIF.EE_TERM;
        LNCPYIFM.REC_DATA.UPDTBL_DATE = LNRPYIF.UPDTBL_DATE;
        LNCPYIFM.REC_DATA.UPDTBL_TLR = LNRPYIF.UPDTBL_TLR;
        LNCPYIFM.REC_DATA.TS = LNRPYIF.TS;
    }
    public void T000_STARTBR_LNTPYIF() throws IOException,SQLException,Exception {
        LNTPYIF_BR.rp = new DBParm();
        LNTPYIF_BR.rp.TableName = "LNTPYIF";
        LNTPYIF_BR.rp.where = "CONTRACT_NO = :LNRPYIF.KEY.CONTRACT_NO "
            + "AND CUR_REPY_DT = :LNRPYIF.CUR_REPY_DT "
            + "AND REPY_MTH = '0'";
        IBS.STARTBR(SCCGWA, LNRPYIF, this, LNTPYIF_BR);
    }
    public void T000_READNEXT_LNTPYIF() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRPYIF, this, LNTPYIF_BR);
    }
    public void T000_ENDBR_LNTPYIF() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTPYIF_BR);
    }
    public void T00_WRITE_LNTPYIF() throws IOException,SQLException,Exception {
        LNTPYIF_RD = new DBParm();
        LNTPYIF_RD.TableName = "LNTPYIF";
        IBS.WRITE(SCCGWA, LNRPYIF, LNTPYIF_RD);
    }
    public void T00_READ_LNTPYIF_FIRST() throws IOException,SQLException,Exception {
        LNTPYIF_RD = new DBParm();
        LNTPYIF_RD.TableName = "LNTPYIF";
        LNTPYIF_RD.where = "CONTRACT_NO = :LNRPYIF.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRPYIF.KEY.SUB_CTA_NO";
        LNTPYIF_RD.fst = true;
        LNTPYIF_RD.order = "TS DESC";
        IBS.READ(SCCGWA, LNRPYIF, this, LNTPYIF_RD);
    }
    public void T00_READUPDATE_LNTPYIF_FIRST() throws IOException,SQLException,Exception {
        LNTPYIF_RD = new DBParm();
        LNTPYIF_RD.TableName = "LNTPYIF";
        LNTPYIF_RD.upd = true;
        IBS.READ(SCCGWA, LNRPYIF, LNTPYIF_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_DELETE_LNTPYIF() throws IOException,SQLException,Exception {
        LNTPYIF_RD = new DBParm();
        LNTPYIF_RD.TableName = "LNTPYIF";
        IBS.DELETE(SCCGWA, LNRPYIF, LNTPYIF_RD);
    }
    public void T00_READ_LNTPYIF() throws IOException,SQLException,Exception {
        LNTPYIF_RD = new DBParm();
        LNTPYIF_RD.TableName = "LNTPYIF";
        IBS.READ(SCCGWA, LNRPYIF, LNTPYIF_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_READUPDATE_LNTPYIF() throws IOException,SQLException,Exception {
        LNTPYIF_RD = new DBParm();
        LNTPYIF_RD.TableName = "LNTPYIF";
        LNTPYIF_RD.upd = true;
        IBS.READ(SCCGWA, LNRPYIF, LNTPYIF_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_REWRITE_LNTPYIF() throws IOException,SQLException,Exception {
        LNTPYIF_RD = new DBParm();
        LNTPYIF_RD.TableName = "LNTPYIF";
        IBS.REWRITE(SCCGWA, LNRPYIF, LNTPYIF_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCPYIFM.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCPYIFM=");
            CEP.TRC(SCCGWA, LNCPYIFM);
            CEP.TRC(SCCGWA, "LNRPYIF =");
            CEP.TRC(SCCGWA, LNRPYIF);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
