package com.hisun.LN;

import com.hisun.SM.*;
import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZBKCTM {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    LNZBKCTM_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNZBKCTM_WS_TEMP_VARIABLE();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNRBKCTL LNRBKCTL = new LNRBKCTL();
    SCCGWA SCCGWA;
    LNCBKCTM LNCBKCTM;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, LNCBKCTM LNCBKCTM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCBKCTM = LNCBKCTM;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "LNZBKCTM:A000-INIT-PROCESS START!... ");
        CEP.TRC(SCCGWA, LNCBKCTM);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZBKCTM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPRMT);
        BPCPRMM.RC.RC_APP = "SM";
        BPCPRMR.RC.RC_APP = "SM";
        BPCPRMM.DAT_PTR = BPRPRMT;
        BPCPRMR.DAT_PTR = BPRPRMT;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (LNCBKCTM.FUNC == 'I') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
        } else if (LNCBKCTM.FUNC == 'A') {
            B100_WRITE_PROCESS();
            if (pgmRtn) return;
        } else if (LNCBKCTM.FUNC == 'M') {
            B100_READU_PROCESS();
            if (pgmRtn) return;
            B100_REWRITE_PROCESS();
            if (pgmRtn) return;
        } else if (LNCBKCTM.FUNC == 'D') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
            B100_DELETE_PROCESS();
            if (pgmRtn) return;
        } else {
            Z_RET();
            if (pgmRtn) return;
        }
        B200_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCBKCTM.FUNC);
        if (LNCBKCTM.FUNC != 'I' 
            && LNCBKCTM.FUNC != 'A' 
            && LNCBKCTM.FUNC != 'M' 
            && LNCBKCTM.FUNC != 'D') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, LNCBKCTM.RC);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        R00_CHECK_ERROR();
        if (pgmRtn) return;
    }
    public void R00_CHECK_ERROR() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE == 0) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_READ_PROCESS() throws IOException,SQLException,Exception {
        BPRPRMT.KEY.TYP = LNCBKCTM.KEY.TYP;
        BPRPRMT.KEY.CD = LNCBKCTM.KEY.CD;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
    }
    public void B100_READU_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '4';
        BPRPRMT.KEY.TYP = LNCBKCTM.KEY.TYP;
        BPRPRMT.KEY.CD = LNCBKCTM.KEY.CD;
        BPCPRMM.EFF_DT = LNCBKCTM.EFF_DATE;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_REWRITE_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '2';
        BPRPRMT.KEY.TYP = LNCBKCTM.KEY.TYP;
        BPRPRMT.KEY.CD = LNCBKCTM.KEY.CD;
        BPCPRMM.EFF_DT = LNCBKCTM.EFF_DATE;
        BPCPRMM.EXP_DT = LNCBKCTM.EXP_DATE;
        BPRPRMT.DESC = LNCBKCTM.DESC;
        BPRPRMT.CDESC = LNCBKCTM.CDESC;
        IBS.init(SCCGWA, LNRBKCTL.DATA_TXT);
        LNRBKCTL.DATA_TXT.PRODMO = LNCBKCTM.DATA_TXT.PRODMO;
        LNRBKCTL.DATA_TXT.PROD_MOD = LNCBKCTM.DATA_TXT.PROD_MOD;
        LNRBKCTL.DATA_TXT.PROD_ATT = LNCBKCTM.DATA_TXT.PROD_ATT;
        LNRBKCTL.DATA_TXT.BAL_FLG = LNCBKCTM.DATA_TXT.BAL_FLG;
        LNRBKCTL.DATA_TXT.PROD_CLS = LNCBKCTM.DATA_TXT.PROD_CLS;
        LNRBKCTL.DATA_TXT.SYS_FLG = LNCBKCTM.DATA_TXT.SYS_FLG;
        LNRBKCTL.DATA_TXT.CMMT_FLG = LNCBKCTM.DATA_TXT.CMMT_FLG;
        LNRBKCTL.DATA_TXT.REV_FLG = LNCBKCTM.DATA_TXT.REV_FLG;
        LNRBKCTL.DATA_TXT.OVER_FLG = LNCBKCTM.DATA_TXT.OVER_FLG;
        LNRBKCTL.DATA_TXT.HOL_TBNO = LNCBKCTM.DATA_TXT.HOL_TBNO;
        LNRBKCTL.DATA_TXT.APPT_FLG = LNCBKCTM.DATA_TXT.APPT_FLG;
        LNRBKCTL.DATA_TXT.ADV_FLG = LNCBKCTM.DATA_TXT.ADV_FLG;
        LNRBKCTL.DATA_TXT.ADV_CODE = LNCBKCTM.DATA_TXT.ADV_CODE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNRBKCTL.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_DELETE_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '1';
        BPRPRMT.KEY.TYP = LNCBKCTM.KEY.TYP;
        BPRPRMT.KEY.CD = LNCBKCTM.KEY.CD;
        BPCPRMM.EFF_DT = LNCBKCTM.EFF_DATE;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_WRITE_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '0';
        BPRPRMT.KEY.TYP = LNCBKCTM.KEY.TYP;
        BPRPRMT.KEY.CD = LNCBKCTM.KEY.CD;
        BPCPRMM.EFF_DT = LNCBKCTM.EFF_DATE;
        BPCPRMM.EXP_DT = LNCBKCTM.EXP_DATE;
        BPRPRMT.DESC = LNCBKCTM.DESC;
        BPRPRMT.CDESC = LNCBKCTM.CDESC;
        IBS.init(SCCGWA, LNRBKCTL.DATA_TXT);
        LNRBKCTL.DATA_TXT.PRODMO = LNCBKCTM.DATA_TXT.PRODMO;
        LNRBKCTL.DATA_TXT.PROD_MOD = LNCBKCTM.DATA_TXT.PROD_MOD;
        LNRBKCTL.DATA_TXT.PROD_ATT = LNCBKCTM.DATA_TXT.PROD_ATT;
        LNRBKCTL.DATA_TXT.BAL_FLG = LNCBKCTM.DATA_TXT.BAL_FLG;
        LNRBKCTL.DATA_TXT.PROD_CLS = LNCBKCTM.DATA_TXT.PROD_CLS;
        LNRBKCTL.DATA_TXT.SYS_FLG = LNCBKCTM.DATA_TXT.SYS_FLG;
        LNRBKCTL.DATA_TXT.CMMT_FLG = LNCBKCTM.DATA_TXT.CMMT_FLG;
        LNRBKCTL.DATA_TXT.REV_FLG = LNCBKCTM.DATA_TXT.REV_FLG;
        LNRBKCTL.DATA_TXT.OVER_FLG = LNCBKCTM.DATA_TXT.OVER_FLG;
        LNRBKCTL.DATA_TXT.HOL_TBNO = LNCBKCTM.DATA_TXT.HOL_TBNO;
        LNRBKCTL.DATA_TXT.APPT_FLG = LNCBKCTM.DATA_TXT.APPT_FLG;
        LNRBKCTL.DATA_TXT.ADV_FLG = LNCBKCTM.DATA_TXT.ADV_FLG;
        LNRBKCTL.DATA_TXT.ADV_CODE = LNCBKCTM.DATA_TXT.ADV_CODE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNRBKCTL.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B200_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        LNCBKCTM.DESC = BPRPRMT.DESC;
        LNCBKCTM.CDESC = BPRPRMT.CDESC;
        LNCBKCTM.EFF_DATE = BPCPRMM.EFF_DT;
        LNCBKCTM.EXP_DATE = BPCPRMM.EXP_DT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNRBKCTL.DATA_TXT);
        LNCBKCTM.DATA_TXT.PRODMO = LNRBKCTL.DATA_TXT.PRODMO;
        LNCBKCTM.DATA_TXT.PROD_MOD = LNRBKCTL.DATA_TXT.PROD_MOD;
        LNCBKCTM.DATA_TXT.PROD_ATT = LNRBKCTL.DATA_TXT.PROD_ATT;
        LNCBKCTM.DATA_TXT.BAL_FLG = LNRBKCTL.DATA_TXT.BAL_FLG;
        LNCBKCTM.DATA_TXT.PROD_CLS = LNRBKCTL.DATA_TXT.PROD_CLS;
        LNCBKCTM.DATA_TXT.SYS_FLG = LNRBKCTL.DATA_TXT.SYS_FLG;
        LNCBKCTM.DATA_TXT.CMMT_FLG = LNRBKCTL.DATA_TXT.CMMT_FLG;
        LNCBKCTM.DATA_TXT.REV_FLG = LNRBKCTL.DATA_TXT.REV_FLG;
        LNCBKCTM.DATA_TXT.OVER_FLG = LNRBKCTL.DATA_TXT.OVER_FLG;
        LNCBKCTM.DATA_TXT.HOL_TBNO = LNRBKCTL.DATA_TXT.HOL_TBNO;
        LNCBKCTM.DATA_TXT.APPT_FLG = LNRBKCTL.DATA_TXT.APPT_FLG;
        LNCBKCTM.DATA_TXT.ADV_FLG = LNRBKCTL.DATA_TXT.ADV_FLG;
        LNCBKCTM.DATA_TXT.ADV_CODE = LNRBKCTL.DATA_TXT.ADV_CODE;
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = BPCPRMM.RC.RC_APP;
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = BPCPRMM.RC.RC_RTNCODE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = BPCPRMR.RC.RC_APP;
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = BPCPRMR.RC.RC_RTNCODE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCBKCTM.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCBKCTM =");
            CEP.TRC(SCCGWA, LNCBKCTM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
