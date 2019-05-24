package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZICTLM {
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTICTL_RD;
    boolean pgmRtn = false;
    char LNZICTLM_FILLER1 = ' ';
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRICTL LNRICTL = new LNRICTL();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCGWA SCCGWA;
    LNCICTLM LNCICTLM;
    public void MP(SCCGWA SCCGWA, LNCICTLM LNCICTLM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCICTLM = LNCICTLM;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZICTLM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCICTLM.RC.RC_APP = "LN";
        LNCICTLM.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.KEY.SUB_CTA_NO);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_CHECK();
        if (pgmRtn) return;
        if (LNCICTLM.FUNC == '0') {
            B02_FUNC_WRITE();
            if (pgmRtn) return;
        } else if (LNCICTLM.FUNC == '1') {
            B03_FUNC_DELETE();
            if (pgmRtn) return;
        } else if (LNCICTLM.FUNC == '2') {
            B04_FUNC_REWRITE();
            if (pgmRtn) return;
        } else if (LNCICTLM.FUNC == '3') {
            B05_FUNC_READ();
            if (pgmRtn) return;
        } else if (LNCICTLM.FUNC == '4') {
            B06_FUNC_READ_UPDATE();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCICTLM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICTLM.REC_DATA.KEY);
        if (JIBS_tmp_str[0].trim().length() == 0 
            || JIBS_tmp_str[0].equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCICTLM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B02_FUNC_WRITE() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = ' ';
        R00_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ICTL_EXIST, LNCICTLM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R00_COMA_RECA();
        if (pgmRtn) return;
        T00_WRITE_LNTICTL();
        if (pgmRtn) return;
    }
    public void B03_FUNC_DELETE() throws IOException,SQLException,Exception {
        LNRICTL.KEY.CONTRACT_NO = LNCICTLM.REC_DATA.KEY.CONTRACT_NO;
        LNRICTL.KEY.SUB_CTA_NO = LNCICTLM.REC_DATA.KEY.SUB_CTA_NO;
        T00_READUPDATE_LNTICTL();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            T00_DELETE_LNTICTL();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ICTL_NOTFND, LNCICTLM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B04_FUNC_REWRITE() throws IOException,SQLException,Exception {
        R00_COMA_RECA();
        if (pgmRtn) return;
        T00_REWRITE_LNTICTL();
        if (pgmRtn) return;
    }
    public void B05_FUNC_READ() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = ' ';
        R00_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ICTL_NOTFND, LNCICTLM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R00_RECA_COMA();
        if (pgmRtn) return;
    }
    public void B06_FUNC_READ_UPDATE() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = 'Y';
        R00_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ICTL_NOTFND, LNCICTLM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R00_RECA_COMA();
        if (pgmRtn) return;
    }
    public void R00_READ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNCICTLM.REC_DATA.KEY.CONTRACT_NO;
        LNRICTL.KEY.SUB_CTA_NO = LNCICTLM.REC_DATA.KEY.SUB_CTA_NO;
        if (WS_UPDATE_FLG == 'Y') {
            T00_READUPDATE_LNTICTL();
            if (pgmRtn) return;
        } else {
            T00_READ_LNTICTL();
            if (pgmRtn) return;
        }
    }
    public void R00_COMA_RECA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNCICTLM.REC_DATA.KEY.CONTRACT_NO;
        LNRICTL.KEY.SUB_CTA_NO = LNCICTLM.REC_DATA.KEY.SUB_CTA_NO;
        LNRICTL.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW;
        LNRICTL.CUR_RAT = LNCICTLM.REC_DATA.CUR_RAT;
        LNRICTL.CUR_RAT_DT = LNCICTLM.REC_DATA.CUR_RAT_DT;
        LNRICTL.CUR_PO_RAT = LNCICTLM.REC_DATA.CUR_PO_RAT;
        LNRICTL.CUR_PO_RAT_DT = LNCICTLM.REC_DATA.CUR_PO_RAT_DT;
        LNRICTL.CUR_IO_RAT = LNCICTLM.REC_DATA.CUR_IO_RAT;
        LNRICTL.CUR_IO_RAT_DT = LNCICTLM.REC_DATA.CUR_IO_RAT_DT;
        LNRICTL.CUR_FO_RAT = LNCICTLM.REC_DATA.CUR_FO_RAT;
        LNRICTL.CUR_FO_RAT_DT = LNCICTLM.REC_DATA.CUR_FO_RAT_DT;
        LNRICTL.P_CUR_TERM = LNCICTLM.REC_DATA.P_CUR_TERM;
        LNRICTL.IC_CUR_TERM = LNCICTLM.REC_DATA.IC_CUR_TERM;
        LNRICTL.SUBS_CUR_TERM = LNCICTLM.REC_DATA.SUBS_CUR_TERM;
        LNRICTL.SUBS_LAST_REPY_DT = LNCICTLM.REC_DATA.SUBS_LAST_REPY_DT;
        LNRICTL.IC_CAL_PHS_NO = LNCICTLM.REC_DATA.IC_CAL_PHS_NO;
        LNRICTL.IC_CMP_PHS_NO = LNCICTLM.REC_DATA.IC_CMP_PHS_NO;
        LNRICTL.IC_CMP_TERM = LNCICTLM.REC_DATA.IC_CMP_TERM;
        LNRICTL.IC_CMP_VAL_DT = LNCICTLM.REC_DATA.IC_CMP_VAL_DT;
        LNRICTL.IC_CMP_DUE_DT = LNCICTLM.REC_DATA.IC_CMP_DUE_DT;
        LNRICTL.IC_CAL_TERM = LNCICTLM.REC_DATA.IC_CAL_TERM;
        LNRICTL.IC_CAL_VAL_DT = LNCICTLM.REC_DATA.IC_CAL_VAL_DT;
        LNRICTL.IC_CAL_DUE_DT = LNCICTLM.REC_DATA.IC_CAL_DUE_DT;
        LNRICTL.P_CMP_TERM = LNCICTLM.REC_DATA.P_CMP_TERM;
        LNRICTL.P_CMP_DUE_DT = LNCICTLM.REC_DATA.P_CMP_DUE_DT;
        LNRICTL.P_CAL_TERM = LNCICTLM.REC_DATA.P_CAL_TERM;
        LNRICTL.P_CAL_DUE_DT = LNCICTLM.REC_DATA.P_CAL_DUE_DT;
        LNRICTL.NEXT_ACCU_DT = LNCICTLM.REC_DATA.NEXT_ACCU_DT;
        LNRICTL.INT_CUT_DT = LNCICTLM.REC_DATA.INT_CUT_DT;
        LNRICTL.NEXT_LC_CAL_DAT = LNCICTLM.REC_DATA.NEXT_LC_CAL_DAT;
        LNRICTL.CRT_DATE = LNCICTLM.REC_DATA.CRT_DATE;
        LNRICTL.CRT_TLR = LNCICTLM.REC_DATA.CRT_TLR;
        LNRICTL.UPDTBL_DATE = LNCICTLM.REC_DATA.UPDTBL_DATE;
        LNRICTL.UPDTBL_TLR = LNCICTLM.REC_DATA.UPDTBL_TLR;
    }
    public void R00_RECA_COMA() throws IOException,SQLException,Exception {
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNRICTL.KEY.CONTRACT_NO;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = LNRICTL.KEY.SUB_CTA_NO;
        CEP.TRC(SCCGWA, LNRICTL.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW);
        LNCICTLM.REC_DATA.CTL_STSW = LNRICTL.CTL_STSW;
        LNCICTLM.REC_DATA.CUR_RAT = LNRICTL.CUR_RAT;
        LNCICTLM.REC_DATA.CUR_RAT_DT = LNRICTL.CUR_RAT_DT;
        LNCICTLM.REC_DATA.CUR_PO_RAT = LNRICTL.CUR_PO_RAT;
        LNCICTLM.REC_DATA.CUR_PO_RAT_DT = LNRICTL.CUR_PO_RAT_DT;
        LNCICTLM.REC_DATA.CUR_IO_RAT = LNRICTL.CUR_IO_RAT;
        LNCICTLM.REC_DATA.CUR_IO_RAT_DT = LNRICTL.CUR_IO_RAT_DT;
        LNCICTLM.REC_DATA.CUR_FO_RAT = LNRICTL.CUR_FO_RAT;
        LNCICTLM.REC_DATA.CUR_FO_RAT_DT = LNRICTL.CUR_FO_RAT_DT;
        LNCICTLM.REC_DATA.P_CUR_TERM = LNRICTL.P_CUR_TERM;
        LNCICTLM.REC_DATA.IC_CUR_TERM = LNRICTL.IC_CUR_TERM;
        LNCICTLM.REC_DATA.SUBS_CUR_TERM = LNRICTL.SUBS_CUR_TERM;
        LNCICTLM.REC_DATA.SUBS_LAST_REPY_DT = LNRICTL.SUBS_LAST_REPY_DT;
        LNCICTLM.REC_DATA.IC_CAL_PHS_NO = LNRICTL.IC_CAL_PHS_NO;
        LNCICTLM.REC_DATA.IC_CMP_PHS_NO = LNRICTL.IC_CMP_PHS_NO;
        LNCICTLM.REC_DATA.IC_CMP_TERM = LNRICTL.IC_CMP_TERM;
        LNCICTLM.REC_DATA.IC_CMP_VAL_DT = LNRICTL.IC_CMP_VAL_DT;
        LNCICTLM.REC_DATA.IC_CMP_DUE_DT = LNRICTL.IC_CMP_DUE_DT;
        LNCICTLM.REC_DATA.IC_CAL_TERM = LNRICTL.IC_CAL_TERM;
        LNCICTLM.REC_DATA.IC_CAL_VAL_DT = LNRICTL.IC_CAL_VAL_DT;
        LNCICTLM.REC_DATA.IC_CAL_DUE_DT = LNRICTL.IC_CAL_DUE_DT;
        LNCICTLM.REC_DATA.P_CMP_TERM = LNRICTL.P_CMP_TERM;
        LNCICTLM.REC_DATA.P_CMP_DUE_DT = LNRICTL.P_CMP_DUE_DT;
        LNCICTLM.REC_DATA.P_CAL_TERM = LNRICTL.P_CAL_TERM;
        LNCICTLM.REC_DATA.P_CAL_DUE_DT = LNRICTL.P_CAL_DUE_DT;
        LNCICTLM.REC_DATA.NEXT_ACCU_DT = LNRICTL.NEXT_ACCU_DT;
        LNCICTLM.REC_DATA.INT_CUT_DT = LNRICTL.INT_CUT_DT;
        LNCICTLM.REC_DATA.NEXT_LC_CAL_DAT = LNRICTL.NEXT_LC_CAL_DAT;
        LNCICTLM.REC_DATA.CRT_DATE = LNRICTL.CRT_DATE;
        LNCICTLM.REC_DATA.CRT_TLR = LNRICTL.CRT_TLR;
        LNCICTLM.REC_DATA.UPDTBL_DATE = LNRICTL.UPDTBL_DATE;
        LNCICTLM.REC_DATA.UPDTBL_TLR = LNRICTL.UPDTBL_TLR;
        LNCICTLM.REC_DATA.TS = LNRICTL.TS;
    }
    public void T00_WRITE_LNTICTL() throws IOException,SQLException,Exception {
        LNRICTL.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRICTL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRICTL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRICTL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.WRITE(SCCGWA, LNRICTL, LNTICTL_RD);
    }
    public void T00_DELETE_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.DELETE(SCCGWA, LNRICTL, LNTICTL_RD);
    }
    public void T00_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_READUPDATE_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        LNTICTL_RD.upd = true;
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_REWRITE_LNTICTL() throws IOException,SQLException,Exception {
        LNRICTL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRICTL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.REWRITE(SCCGWA, LNRICTL, LNTICTL_RD);
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
