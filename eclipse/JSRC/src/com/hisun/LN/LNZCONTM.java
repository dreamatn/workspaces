package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZCONTM {
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTCONT_RD;
    boolean pgmRtn = false;
    char LNZCONTM_FILLER1 = ' ';
    int WS_AAA = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRCONT LNRCONT = new LNRCONT();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCGWA SCCGWA;
    LNCCONTM LNCCONTM;
    public void MP(SCCGWA SCCGWA, LNCCONTM LNCCONTM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCCONTM = LNCCONTM;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZCONTM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCCONTM.RC.RC_APP = "LN";
        LNCCONTM.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.KEY);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_CHECK();
        if (pgmRtn) return;
        if (LNCCONTM.FUNC == '0') {
            B02_FUNC_WRITE();
            if (pgmRtn) return;
        } else if (LNCCONTM.FUNC == '1') {
            B03_FUNC_DELETE();
            if (pgmRtn) return;
        } else if (LNCCONTM.FUNC == '2') {
            B04_FUNC_REWRITE();
            if (pgmRtn) return;
        } else if (LNCCONTM.FUNC == '3') {
            B05_FUNC_READ();
            if (pgmRtn) return;
        } else if (LNCCONTM.FUNC == '4') {
            B06_FUNC_READ_UPDATE();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCCONTM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCONTM.REC_DATA.KEY);
        if (JIBS_tmp_str[0].trim().length() == 0 
            || JIBS_tmp_str[0].equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_KEY_MUST_INPUT, LNCCONTM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B02_FUNC_WRITE() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = ' ';
        B10_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CONT_EXIST, LNCCONTM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R00_COMA_RECA();
        if (pgmRtn) return;
        T00_WRITE_LNTCONT();
        if (pgmRtn) return;
    }
    public void B03_FUNC_DELETE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCONTM.REC_DATA.KEY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNRCONT.KEY);
        T00_READUPDATE_LNTCONT();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            T00_DELETE_LNTCONT();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CONT_NOTFND, LNCCONTM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B04_FUNC_REWRITE() throws IOException,SQLException,Exception {
        R00_COMA_RECA();
        if (pgmRtn) return;
        T00_REWRITE_LNTCONT();
        if (pgmRtn) return;
    }
    public void B05_FUNC_READ() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = ' ';
        B10_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CONT_NOTFND, LNCCONTM.RC);
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
        CEP.TRC(SCCGWA, "ZZZZZ");
        if (WS_FOUND_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CONT_NOTFND, LNCCONTM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R00_RECA_COMA();
        if (pgmRtn) return;
    }
    public void B10_READ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.KEY);
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.KEY.CONTRACT_NO);
        LNRCONT.KEY.CONTRACT_NO = LNCCONTM.REC_DATA.KEY.CONTRACT_NO;
        CEP.TRC(SCCGWA, LNRCONT.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRCONT.KEY);
        if (WS_UPDATE_FLG == 'Y') {
            T00_READUPDATE_LNTCONT();
            if (pgmRtn) return;
        } else {
            T00_READ_LNTCONT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNRCONT.PROD_CD);
        CEP.TRC(SCCGWA, LNRCONT.START_DATE);
        CEP.TRC(SCCGWA, LNRCONT.MAT_DATE);
        CEP.TRC(SCCGWA, LNRCONT.LAST_F_VAL_DATE);
        CEP.TRC(SCCGWA, LNRCONT.TRAN_DTE);
    }
    public void R00_COMA_RECA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.KEY.CONTRACT_NO);
        LNRCONT.KEY.CONTRACT_NO = LNCCONTM.REC_DATA.KEY.CONTRACT_NO;
        LNRCONT.CONTRACT_TYPE = LNCCONTM.REC_DATA.CONTRACT_TYPE;
        LNRCONT.FATHER_CONTRACT = LNCCONTM.REC_DATA.FATHER_CONTRACT;
        LNRCONT.CTA_STS = LNCCONTM.REC_DATA.CTA_STS;
        LNRCONT.PROD_CD = LNCCONTM.REC_DATA.PROD_CD;
        LNRCONT.CCY = LNCCONTM.REC_DATA.CCY;
        LNRCONT.LN_TOT_AMT = LNCCONTM.REC_DATA.LN_TOT_AMT;
        LNRCONT.ORIG_TOT_AMT = LNCCONTM.REC_DATA.ORIG_TOT_AMT;
        LNRCONT.START_DATE = LNCCONTM.REC_DATA.START_DATE;
        LNRCONT.MAT_DATE = LNCCONTM.REC_DATA.MAT_DATE;
        LNRCONT.ORI_MAT_DATE = LNCCONTM.REC_DATA.ORI_MAT_DATE;
        LNRCONT.BOOK_BR = LNCCONTM.REC_DATA.BOOK_BR;
        LNRCONT.DOMI_BR = LNCCONTM.REC_DATA.DOMI_BR;
        LNRCONT.OVER_TIME = LNCCONTM.REC_DATA.OVER_TIME;
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.BOOK_BR);
        CEP.TRC(SCCGWA, LNRCONT.BOOK_BR);
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.DOMI_BR);
        CEP.TRC(SCCGWA, LNRCONT.DOMI_BR);
        LNRCONT.TRAN_DTE = LNCCONTM.REC_DATA.TRAN_DTE;
        CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.REMARK1);
        LNRCONT.TRAN_JRN = LNCCONTM.REC_DATA.TRAN_JRN;
        LNRCONT.REMARK = LNCCONTM.REC_DATA.REMARK1;
        CEP.TRC(SCCGWA, LNRCONT.REMARK);
        LNRCONT.LAST_F_VAL_DATE = LNCCONTM.REC_DATA.LAST_F_VAL_DATE;
        LNRCONT.LAST_TX_DT = LNCCONTM.REC_DATA.LAST_TX_DT;
        LNRCONT.LAST_TX_SEQ = LNCCONTM.REC_DATA.LAST_TX_SEQ;
        LNRCONT.CRT_DATE = LNCCONTM.REC_DATA.CRT_DATE;
        LNRCONT.CRT_TLR = LNCCONTM.REC_DATA.CRT_TLR;
        LNRCONT.UPDTBL_DATE = LNCCONTM.REC_DATA.UPDTBL_DATE;
        LNRCONT.UPDTBL_TLR = LNCCONTM.REC_DATA.UPDTBL_TLR;
        LNRCONT.REL_CONTRACT = LNCCONTM.REC_DATA.REL_CONTRACT;
        LNRCONT.REQ_FRM_NO = LNCCONTM.REC_DATA.REQ_FRM_NO;
    }
    public void R00_RECA_COMA() throws IOException,SQLException,Exception {
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNRCONT.KEY.CONTRACT_NO;
        LNCCONTM.REC_DATA.CONTRACT_TYPE = LNRCONT.CONTRACT_TYPE;
        LNCCONTM.REC_DATA.FATHER_CONTRACT = LNRCONT.FATHER_CONTRACT;
        LNCCONTM.REC_DATA.CTA_STS = LNRCONT.CTA_STS;
        LNCCONTM.REC_DATA.PROD_CD = LNRCONT.PROD_CD;
        LNCCONTM.REC_DATA.CCY = LNRCONT.CCY;
        LNCCONTM.REC_DATA.LN_TOT_AMT = LNRCONT.LN_TOT_AMT;
        LNCCONTM.REC_DATA.ORIG_TOT_AMT = LNRCONT.ORIG_TOT_AMT;
        LNCCONTM.REC_DATA.START_DATE = LNRCONT.START_DATE;
        LNCCONTM.REC_DATA.MAT_DATE = LNRCONT.MAT_DATE;
        LNCCONTM.REC_DATA.ORI_MAT_DATE = LNRCONT.ORI_MAT_DATE;
        LNCCONTM.REC_DATA.BOOK_BR = LNRCONT.BOOK_BR;
        LNCCONTM.REC_DATA.DOMI_BR = LNRCONT.DOMI_BR;
        LNCCONTM.REC_DATA.OVER_TIME = LNRCONT.OVER_TIME;
        LNCCONTM.REC_DATA.TRAN_DTE = LNRCONT.TRAN_DTE;
        LNCCONTM.REC_DATA.TRAN_JRN = LNRCONT.TRAN_JRN;
        LNCCONTM.REC_DATA.REMARK1 = LNRCONT.REMARK;
        LNCCONTM.REC_DATA.LAST_F_VAL_DATE = LNRCONT.LAST_F_VAL_DATE;
        LNCCONTM.REC_DATA.LAST_TX_DT = LNRCONT.LAST_TX_DT;
        LNCCONTM.REC_DATA.LAST_TX_SEQ = LNRCONT.LAST_TX_SEQ;
        LNCCONTM.REC_DATA.CRT_DATE = LNRCONT.CRT_DATE;
        LNCCONTM.REC_DATA.CRT_TLR = LNRCONT.CRT_TLR;
        LNCCONTM.REC_DATA.UPDTBL_DATE = LNRCONT.UPDTBL_DATE;
        LNCCONTM.REC_DATA.UPDTBL_TLR = LNRCONT.UPDTBL_TLR;
        LNCCONTM.REC_DATA.TS = LNRCONT.TS;
        LNCCONTM.REC_DATA.REL_CONTRACT = LNRCONT.REL_CONTRACT;
        LNCCONTM.REC_DATA.REQ_FRM_NO = LNRCONT.REQ_FRM_NO;
    }
    public void T00_WRITE_LNTCONT() throws IOException,SQLException,Exception {
        LNRCONT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRCONT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRCONT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, LNRCONT.CONTRACT_TYPE);
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.WRITE(SCCGWA, LNRCONT, LNTCONT_RD);
        CEP.TRC(SCCGWA, LNRCONT);
    }
    public void T00_DELETE_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.DELETE(SCCGWA, LNRCONT, LNTCONT_RD);
    }
    public void T00_READ_LNTCONT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRCONT.KEY.CONTRACT_NO);
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, LNRCONT);
        WS_FOUND_FLG = ' ';
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_READUPDATE_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        LNTCONT_RD.upd = true;
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_REWRITE_LNTCONT() throws IOException,SQLException,Exception {
        LNRCONT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRCONT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.REWRITE(SCCGWA, LNRCONT, LNTCONT_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, LNCCONTM);
            CEP.TRC(SCCGWA, LNRCONT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
