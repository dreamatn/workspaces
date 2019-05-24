package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRCVDM {
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTRCVD_RD;
    boolean pgmRtn = false;
    char LNZRCVDM_FILLER1 = ' ';
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNRICTL LNRICTL = new LNRICTL();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    LNCICTLM LNCICTLM = new LNCICTLM();
    SCCGWA SCCGWA;
    LNCRCVDM LNCRCVDM;
    public void MP(SCCGWA SCCGWA, LNCRCVDM LNCRCVDM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRCVDM = LNCRCVDM;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRCVDM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCRCVDM.RC.RC_APP = "LN";
        LNCRCVDM.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCRCVDM.REC_DATA.KEY.SUBS_PROJ_NO);
        B01_CHECK();
        if (pgmRtn) return;
        if (LNCRCVDM.FUNC == '0') {
            B02_FUNC_WRITE();
            if (pgmRtn) return;
        } else if (LNCRCVDM.FUNC == '1') {
            B03_FUNC_DELETE();
            if (pgmRtn) return;
        } else if (LNCRCVDM.FUNC == '2') {
            B04_FUNC_REWRITE();
            if (pgmRtn) return;
        } else if (LNCRCVDM.FUNC == '3') {
            B05_FUNC_READ();
            if (pgmRtn) return;
        } else if (LNCRCVDM.FUNC == '4') {
            B06_FUNC_READ_UPDATE();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCRCVDM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRCVDM.REC_DATA.KEY);
        if (JIBS_tmp_str[0].trim().length() == 0 
            || JIBS_tmp_str[0].equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_KEY_MUST_INPUT, LNCRCVDM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B02_FUNC_WRITE() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = ' ';
        B10_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RCVD_EXIST, LNCRCVDM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        LNCRCVDM.REC_DATA.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCRCVDM.REC_DATA.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNCRCVDM.REC_DATA.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCRCVDM.REC_DATA.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        R00_COMA_RECA();
        if (pgmRtn) return;
        T00_WRITE_LNTRCVD();
        if (pgmRtn) return;
    }
    public void B03_FUNC_DELETE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRCVDM.REC_DATA.KEY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNRRCVD.KEY);
        T00_DELETE_LNTRCVD();
        if (pgmRtn) return;
    }
    public void B04_FUNC_REWRITE() throws IOException,SQLException,Exception {
        LNCRCVDM.REC_DATA.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCRCVDM.REC_DATA.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        R00_COMA_RECA();
        if (pgmRtn) return;
        T00_REWRITE_LNTRCVD();
        if (pgmRtn) return;
    }
    public void B05_FUNC_READ() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = ' ';
        B10_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RCVD_NOTFND, LNCRCVDM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R00_RECA_COMA();
        if (pgmRtn) return;
    }
    public void B06_FUNC_READ_UPDATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCRCVDM.REC_DATA.KEY.SUBS_PROJ_NO);
        WS_UPDATE_FLG = 'Y';
        B10_READ();
        if (pgmRtn) return;
        if (WS_FOUND_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RCVD_NOTFND, LNCRCVDM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R00_RECA_COMA();
        if (pgmRtn) return;
    }
    public void B10_READ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCRCVDM.REC_DATA.KEY.SUBS_PROJ_NO);
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCRCVDM.REC_DATA.KEY.CONTRACT_NO;
        LNRRCVD.KEY.SUB_CTA_NO = LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO;
        LNRRCVD.KEY.AMT_TYP = LNCRCVDM.REC_DATA.KEY.AMT_TYP;
        LNRRCVD.KEY.TERM = LNCRCVDM.REC_DATA.KEY.TERM;
        LNRRCVD.KEY.SUBS_PROJ_NO = LNCRCVDM.REC_DATA.KEY.SUBS_PROJ_NO;
        CEP.TRC(SCCGWA, LNRRCVD.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.AMT_TYP);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.TERM);
        CEP.TRC(SCCGWA, LNRRCVD.KEY.SUBS_PROJ_NO);
        if (WS_UPDATE_FLG == 'Y') {
            T00_READUPDATE_LNTRCVD();
            if (pgmRtn) return;
        } else {
            T00_READ_LNTRCVD();
            if (pgmRtn) return;
        }
    }
    public void R00_COMA_RECA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCRCVDM.REC_DATA.KEY.CONTRACT_NO;
        LNRRCVD.KEY.SUB_CTA_NO = LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO;
        LNRRCVD.KEY.AMT_TYP = LNCRCVDM.REC_DATA.KEY.AMT_TYP;
        LNRRCVD.KEY.TERM = LNCRCVDM.REC_DATA.KEY.TERM;
        LNRRCVD.KEY.SUBS_PROJ_NO = LNCRCVDM.REC_DATA.KEY.SUBS_PROJ_NO;
        LNRRCVD.VAL_DT = LNCRCVDM.REC_DATA.VAL_DT;
        LNRRCVD.DUE_DT = LNCRCVDM.REC_DATA.DUE_DT;
        LNRRCVD.TERM_STS = LNCRCVDM.REC_DATA.TERM_STS;
        LNRRCVD.OVD_DT = LNCRCVDM.REC_DATA.OVD_DT;
        LNRRCVD.REPY_STS = LNCRCVDM.REC_DATA.REPY_STS;
        LNRRCVD.P_REC_AMT = LNCRCVDM.REC_DATA.P_REC_AMT;
        LNRRCVD.P_PAY_AMT = LNCRCVDM.REC_DATA.P_PAY_AMT;
        LNCRCVDM.REC_DATA.P_WAV_AMT = LNRRCVD.P_WAV_AMT;
        LNRRCVD.I_REC_AMT = LNCRCVDM.REC_DATA.I_REC_AMT;
        LNRRCVD.I_PAY_AMT = LNCRCVDM.REC_DATA.I_PAY_AMT;
        LNRRCVD.I_WAV_AMT = LNCRCVDM.REC_DATA.I_WAV_AMT;
        LNRRCVD.F_REC_AMT = LNCRCVDM.REC_DATA.F_REC_AMT;
        LNRRCVD.F_PAY_AMT = LNCRCVDM.REC_DATA.F_PAY_AMT;
        LNRRCVD.F_WAV_AMT = LNCRCVDM.REC_DATA.F_WAV_AMT;
        LNRRCVD.O_REC_AMT = LNCRCVDM.REC_DATA.O_REC_AMT;
        LNRRCVD.O_PAY_AMT = LNCRCVDM.REC_DATA.O_PAY_AMT;
        LNRRCVD.O_WAV_AMT = LNCRCVDM.REC_DATA.O_WAV_AMT;
        LNRRCVD.O_LST_CAL_AMT = LNCRCVDM.REC_DATA.O_LST_CAL_AMT;
        LNRRCVD.O_LST_PST_AMT = LNCRCVDM.REC_DATA.O_LST_PST_AMT;
        LNRRCVD.L_REC_AMT = LNCRCVDM.REC_DATA.L_REC_AMT;
        LNRRCVD.L_PAY_AMT = LNCRCVDM.REC_DATA.L_PAY_AMT;
        LNRRCVD.L_WAV_AMT = LNCRCVDM.REC_DATA.L_WAV_AMT;
        LNRRCVD.L_LST_CAL_AMT = LNCRCVDM.REC_DATA.L_LST_CAL_AMT;
        LNRRCVD.L_LST_PST_AMT = LNCRCVDM.REC_DATA.L_LST_PST_AMT;
        LNRRCVD.PI_STOP_DT = LNCRCVDM.REC_DATA.PI_STOP_DT;
        LNRRCVD.LAST_LC_CAL_DAT = LNCRCVDM.REC_DATA.LAST_LC_CAL_DAT;
        LNRRCVD.ALL_IN_RATE = LNCRCVDM.REC_DATA.ALL_IN_RATE;
        LNRRCVD.REC_FLG = LNCRCVDM.REC_DATA.REC_FLG;
        LNRRCVD.CLR_TYP = LNCRCVDM.REC_DATA.CLR_TYP;
        LNRRCVD.CRT_DATE = LNCRCVDM.REC_DATA.CRT_DATE;
        LNRRCVD.CRT_TLR = LNCRCVDM.REC_DATA.CRT_TLR;
        LNRRCVD.UPDTBL_DATE = LNCRCVDM.REC_DATA.UPDTBL_DATE;
        LNRRCVD.UPDTBL_TLR = LNCRCVDM.REC_DATA.UPDTBL_TLR;
    }
    public void R00_RECA_COMA() throws IOException,SQLException,Exception {
        LNCRCVDM.REC_DATA.KEY.CONTRACT_NO = LNRRCVD.KEY.CONTRACT_NO;
        LNCRCVDM.REC_DATA.KEY.SUB_CTA_NO = LNRRCVD.KEY.SUB_CTA_NO;
        LNCRCVDM.REC_DATA.KEY.AMT_TYP = LNRRCVD.KEY.AMT_TYP;
        LNCRCVDM.REC_DATA.KEY.TERM = LNRRCVD.KEY.TERM;
        LNCRCVDM.REC_DATA.KEY.SUBS_PROJ_NO = LNRRCVD.KEY.SUBS_PROJ_NO;
        LNCRCVDM.REC_DATA.VAL_DT = LNRRCVD.VAL_DT;
        LNCRCVDM.REC_DATA.DUE_DT = LNRRCVD.DUE_DT;
        LNCRCVDM.REC_DATA.TERM_STS = LNRRCVD.TERM_STS;
        LNCRCVDM.REC_DATA.OVD_DT = LNRRCVD.OVD_DT;
        LNCRCVDM.REC_DATA.REPY_STS = LNRRCVD.REPY_STS;
        LNCRCVDM.REC_DATA.P_REC_AMT = LNRRCVD.P_REC_AMT;
        LNCRCVDM.REC_DATA.P_PAY_AMT = LNRRCVD.P_PAY_AMT;
        LNCRCVDM.REC_DATA.P_WAV_AMT = LNRRCVD.P_WAV_AMT;
        LNCRCVDM.REC_DATA.I_REC_AMT = LNRRCVD.I_REC_AMT;
        LNCRCVDM.REC_DATA.I_PAY_AMT = LNRRCVD.I_PAY_AMT;
        LNCRCVDM.REC_DATA.I_WAV_AMT = LNRRCVD.I_WAV_AMT;
        LNCRCVDM.REC_DATA.F_REC_AMT = LNRRCVD.F_REC_AMT;
        LNCRCVDM.REC_DATA.F_PAY_AMT = LNRRCVD.F_PAY_AMT;
        LNCRCVDM.REC_DATA.F_WAV_AMT = LNRRCVD.F_WAV_AMT;
        LNCRCVDM.REC_DATA.O_REC_AMT = LNRRCVD.O_REC_AMT;
        LNCRCVDM.REC_DATA.O_PAY_AMT = LNRRCVD.O_PAY_AMT;
        LNCRCVDM.REC_DATA.O_WAV_AMT = LNRRCVD.O_WAV_AMT;
        LNCRCVDM.REC_DATA.O_LST_CAL_AMT = LNRRCVD.O_LST_CAL_AMT;
        LNCRCVDM.REC_DATA.O_LST_PST_AMT = LNRRCVD.O_LST_PST_AMT;
        LNCRCVDM.REC_DATA.L_REC_AMT = LNRRCVD.L_REC_AMT;
        LNCRCVDM.REC_DATA.L_PAY_AMT = LNRRCVD.L_PAY_AMT;
        LNCRCVDM.REC_DATA.L_WAV_AMT = LNRRCVD.L_WAV_AMT;
        LNCRCVDM.REC_DATA.L_LST_CAL_AMT = LNRRCVD.L_LST_CAL_AMT;
        LNCRCVDM.REC_DATA.L_LST_PST_AMT = LNRRCVD.L_LST_PST_AMT;
        LNCRCVDM.REC_DATA.PI_STOP_DT = LNRRCVD.PI_STOP_DT;
        LNCRCVDM.REC_DATA.LAST_LC_CAL_DAT = LNRRCVD.LAST_LC_CAL_DAT;
        LNCRCVDM.REC_DATA.ALL_IN_RATE = LNRRCVD.ALL_IN_RATE;
        LNCRCVDM.REC_DATA.REC_FLG = LNRRCVD.REC_FLG;
        LNCRCVDM.REC_DATA.CLR_TYP = LNRRCVD.CLR_TYP;
        LNCRCVDM.REC_DATA.CRT_DATE = LNRRCVD.CRT_DATE;
        LNCRCVDM.REC_DATA.CRT_TLR = LNRRCVD.CRT_TLR;
        LNCRCVDM.REC_DATA.UPDTBL_DATE = LNRRCVD.UPDTBL_DATE;
        LNCRCVDM.REC_DATA.UPDTBL_TLR = LNRRCVD.UPDTBL_TLR;
        LNCRCVDM.REC_DATA.TS = LNRRCVD.TS;
    }
    public void T00_WRITE_LNTRCVD() throws IOException,SQLException,Exception {
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        IBS.WRITE(SCCGWA, LNRRCVD, LNTRCVD_RD);
    }
    public void T00_DELETE_LNTRCVD() throws IOException,SQLException,Exception {
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        IBS.DELETE(SCCGWA, LNRRCVD, LNTRCVD_RD);
    }
    public void T00_READ_LNTRCVD() throws IOException,SQLException,Exception {
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        IBS.READ(SCCGWA, LNRRCVD, LNTRCVD_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_READUPDATE_LNTRCVD() throws IOException,SQLException,Exception {
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.upd = true;
        IBS.READ(SCCGWA, LNRRCVD, LNTRCVD_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_REWRITE_LNTRCVD() throws IOException,SQLException,Exception {
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        IBS.REWRITE(SCCGWA, LNRRCVD, LNTRCVD_RD);
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            LNCRCVDM.RC.RC_APP = LNCICTLM.RC.RC_APP;
            LNCRCVDM.RC.RC_RTNCODE = LNCICTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
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
