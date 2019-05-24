package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZLOANM {
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTLOAN_RD;
    boolean pgmRtn = false;
    char LNZLOANM_FILLER1 = ' ';
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCGWA SCCGWA;
    LNCLOANM LNCLOANM;
    public void MP(SCCGWA SCCGWA, LNCLOANM LNCLOANM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCLOANM = LNCLOANM;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZLOANM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCLOANM.RC.RC_APP = "LN";
        LNCLOANM.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_CHECK();
        if (pgmRtn) return;
        if (LNCLOANM.FUNC == '0') {
            B02_FUNC_WRITE();
            if (pgmRtn) return;
        } else if (LNCLOANM.FUNC == '1') {
            B03_FUNC_DELETE();
            if (pgmRtn) return;
        } else if (LNCLOANM.FUNC == '2') {
            B04_FUNC_REWRITE();
            if (pgmRtn) return;
        } else if (LNCLOANM.FUNC == '3') {
            B05_FUNC_READ();
            if (pgmRtn) return;
        } else if (LNCLOANM.FUNC == '4') {
            B06_FUNC_READ_UPDATE();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCLOANM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCLOANM.REC_DATA.KEY);
        if (JIBS_tmp_str[0].trim().length() == 0 
            || JIBS_tmp_str[0].equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_KEY_MUST_INPUT, LNCLOANM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "CHECK-END");
    }
    public void B02_FUNC_WRITE() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = ' ';
        B10_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LOAN_EXIST, LNCLOANM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R00_COMA_RECA();
        if (pgmRtn) return;
        T00_WRITE_LNTLOAN();
        if (pgmRtn) return;
    }
    public void B03_FUNC_DELETE() throws IOException,SQLException,Exception {
        LNRLOAN.KEY.CONTRACT_NO = LNCLOANM.REC_DATA.KEY.CONTRACT_NO;
        T00_READUPDATE_LNTLOAN();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            T00_DELETE_LNTLOAN();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LOAN_NOTFND, LNCLOANM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B04_FUNC_REWRITE() throws IOException,SQLException,Exception {
        R00_COMA_RECA();
        if (pgmRtn) return;
        T00_REWRITE_LNTLOAN();
        if (pgmRtn) return;
    }
    public void B05_FUNC_READ() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = ' ';
        B10_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LOAN_NOTFND, LNCLOANM.RC);
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
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LOAN_NOTFND, LNCLOANM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R00_RECA_COMA();
        if (pgmRtn) return;
    }
    public void B10_READ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRLOAN);
        LNRLOAN.KEY.CONTRACT_NO = LNCLOANM.REC_DATA.KEY.CONTRACT_NO;
        if (WS_UPDATE_FLG == 'Y') {
            T00_READUPDATE_LNTLOAN();
            if (pgmRtn) return;
        } else {
            T00_READ_LNTLOAN();
            if (pgmRtn) return;
        }
    }
    public void R00_COMA_RECA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCLOANM.REC_DATA.PD_PROJ_NO);
        IBS.init(SCCGWA, LNRLOAN);
        LNRLOAN.KEY.CONTRACT_NO = LNCLOANM.REC_DATA.KEY.CONTRACT_NO;
        LNRLOAN.BRAT_EFF_DT = LNCLOANM.REC_DATA.BRAT_EFF_DT;
        LNRLOAN.ORAT_EFF_DT = LNCLOANM.REC_DATA.ORAT_EFF_DT;
        LNRLOAN.LRAT_EFF_DT = LNCLOANM.REC_DATA.LRAT_EFF_DT;
        LNRLOAN.PRAT_EFF_DT = LNCLOANM.REC_DATA.PRAT_EFF_DT;
        LNRLOAN.FST_CAL_DT = LNCLOANM.REC_DATA.FST_CAL_DT;
        LNRLOAN.FST_PAYP_DT = LNCLOANM.REC_DATA.FST_PAYP_DT;
        LNRLOAN.STOP_INT_DATE = LNCLOANM.REC_DATA.STOP_INT_DATE;
        LNRLOAN.STOP_VAL_DT = LNCLOANM.REC_DATA.STOP_VAL_DT;
        LNRLOAN.STOP_DUE_DT = LNCLOANM.REC_DATA.STOP_DUE_DT;
        LNRLOAN.EMBEZ_DATE = LNCLOANM.REC_DATA.EMBEZ_DATE;
        LNRLOAN.EMBEZ_CANCEL_DATE = LNCLOANM.REC_DATA.EMBEZ_CANCEL_DATE;
        LNRLOAN.TRANS_NACCR_DATE = LNCLOANM.REC_DATA.TRANS_NACCR_DATE;
        LNRLOAN.CTL_TYPE = LNCLOANM.REC_DATA.CTL_TYPE;
        LNRLOAN.PD_PROJ_NO = LNCLOANM.REC_DATA.PD_PROJ_NO;
        LNRLOAN.AUTO_AMT = LNCLOANM.REC_DATA.AUTO_AMT;
        LNRLOAN.CRT_DATE = LNCLOANM.REC_DATA.CRT_DATE;
        LNRLOAN.CRT_TLR = LNCLOANM.REC_DATA.CRT_TLR;
        LNRLOAN.UPDTBL_DATE = LNCLOANM.REC_DATA.UPDTBL_DATE;
        LNRLOAN.UPDTBL_TLR = LNCLOANM.REC_DATA.UPDTBL_TLR;
    }
    public void R00_RECA_COMA() throws IOException,SQLException,Exception {
        LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNRLOAN.KEY.CONTRACT_NO;
        LNCLOANM.REC_DATA.BRAT_EFF_DT = LNRLOAN.BRAT_EFF_DT;
        LNCLOANM.REC_DATA.ORAT_EFF_DT = LNRLOAN.ORAT_EFF_DT;
        LNCLOANM.REC_DATA.LRAT_EFF_DT = LNRLOAN.LRAT_EFF_DT;
        LNCLOANM.REC_DATA.PRAT_EFF_DT = LNRLOAN.PRAT_EFF_DT;
        LNCLOANM.REC_DATA.FST_CAL_DT = LNRLOAN.FST_CAL_DT;
        LNCLOANM.REC_DATA.FST_PAYP_DT = LNRLOAN.FST_PAYP_DT;
        LNCLOANM.REC_DATA.STOP_INT_DATE = LNRLOAN.STOP_INT_DATE;
        LNCLOANM.REC_DATA.STOP_VAL_DT = LNRLOAN.STOP_VAL_DT;
        LNCLOANM.REC_DATA.STOP_DUE_DT = LNRLOAN.STOP_DUE_DT;
        LNCLOANM.REC_DATA.EMBEZ_DATE = LNRLOAN.EMBEZ_DATE;
        LNCLOANM.REC_DATA.EMBEZ_CANCEL_DATE = LNRLOAN.EMBEZ_CANCEL_DATE;
        LNCLOANM.REC_DATA.TRANS_NACCR_DATE = LNRLOAN.TRANS_NACCR_DATE;
        LNCLOANM.REC_DATA.CTL_TYPE = LNRLOAN.CTL_TYPE;
        LNCLOANM.REC_DATA.PD_PROJ_NO = LNRLOAN.PD_PROJ_NO;
        LNCLOANM.REC_DATA.AUTO_AMT = LNRLOAN.AUTO_AMT;
        LNCLOANM.REC_DATA.CRT_DATE = LNRLOAN.CRT_DATE;
        LNCLOANM.REC_DATA.CRT_TLR = LNRLOAN.CRT_TLR;
        LNCLOANM.REC_DATA.UPDTBL_DATE = LNRLOAN.UPDTBL_DATE;
        LNCLOANM.REC_DATA.UPDTBL_TLR = LNRLOAN.UPDTBL_TLR;
        LNCLOANM.REC_DATA.TS = LNRLOAN.TS;
    }
    public void T00_WRITE_LNTLOAN() throws IOException,SQLException,Exception {
        LNRLOAN.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRLOAN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRLOAN.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, LNRLOAN);
        LNTLOAN_RD = new DBParm();
        LNTLOAN_RD.TableName = "LNTLOAN";
        IBS.WRITE(SCCGWA, LNRLOAN, LNTLOAN_RD);
    }
    public void T00_DELETE_LNTLOAN() throws IOException,SQLException,Exception {
        LNTLOAN_RD = new DBParm();
        LNTLOAN_RD.TableName = "LNTLOAN";
        IBS.DELETE(SCCGWA, LNRLOAN, LNTLOAN_RD);
    }
    public void T00_READ_LNTLOAN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRLOAN);
        LNTLOAN_RD = new DBParm();
        LNTLOAN_RD.TableName = "LNTLOAN";
        IBS.READ(SCCGWA, LNRLOAN, LNTLOAN_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_READUPDATE_LNTLOAN() throws IOException,SQLException,Exception {
        LNTLOAN_RD = new DBParm();
        LNTLOAN_RD.TableName = "LNTLOAN";
        LNTLOAN_RD.upd = true;
        IBS.READ(SCCGWA, LNRLOAN, LNTLOAN_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_REWRITE_LNTLOAN() throws IOException,SQLException,Exception {
        LNRLOAN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRLOAN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTLOAN_RD = new DBParm();
        LNTLOAN_RD.TableName = "LNTLOAN";
        IBS.REWRITE(SCCGWA, LNRLOAN, LNTLOAN_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCLOANM=");
            CEP.TRC(SCCGWA, LNCLOANM);
            CEP.TRC(SCCGWA, "LNRLOAN =");
            CEP.TRC(SCCGWA, LNRLOAN);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
