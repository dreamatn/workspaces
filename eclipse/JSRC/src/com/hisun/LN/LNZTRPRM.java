package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZTRPRM {
    String JIBS_tmp_str[] = new String[10];
    int K_MAX_DATE = 99991231;
    LNZTRPRM_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNZTRPRM_WS_TEMP_VARIABLE();
    SCCSUBS SCCSUBS = new SCCSUBS();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCGWA SCCGWA;
    LNCTRPRM LNCTRPRM;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, LNCTRPRM LNCTRPRM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCTRPRM = LNCTRPRM;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNZTRPRM COMPLETE");
        CEP.TRC(SCCGWA, "LNZTRPRM return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMR);
        BPCPRMM.RC.RC_APP = "SM";
        BPCPRMR.RC.RC_APP = "SM";
        LNCTRPRM.RC.RC_RTNCODE = 0;
        BPCPRMM.DAT_PTR = BPRPRMT;
        BPCPRMR.DAT_PTR = BPRPRMT;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK_INPUT();
        B000_MAIN_PROCESS();
    }
    public void B000_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNCTRPRM.COMM_DATA.FUNC != 'A' 
            && LNCTRPRM.COMM_DATA.FUNC != 'D' 
            && LNCTRPRM.COMM_DATA.FUNC != 'M' 
            && LNCTRPRM.COMM_DATA.FUNC != 'I') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TRPRM_CTRL_TYP, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (LNCTRPRM.COMM_DATA.CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TRPRM_CODE_M, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, LNCTRPRM.COMM_DATA.EFF_DATE);
        CEP.TRC(SCCGWA, LNCTRPRM.COMM_DATA.EXP_DATE);
        if (LNCTRPRM.COMM_DATA.FUNC == 'A' 
            || LNCTRPRM.COMM_DATA.FUNC == 'M') {
            if (LNCTRPRM.COMM_DATA.EFF_DATE == 0) {
                LNCTRPRM.COMM_DATA.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
            }
            if (LNCTRPRM.COMM_DATA.EXP_DATE == 0) {
                LNCTRPRM.COMM_DATA.EXP_DATE = K_MAX_DATE;
            }
            if (LNCTRPRM.COMM_DATA.EXP_DATE < LNCTRPRM.COMM_DATA.EFF_DATE) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_EFFDT_GT_EXPDT, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC();
            }
            LNCTRPRM.COMM_DATA.DATA_TXT.DAT1 = LNCTRPRM.COMM_DATA.EFF_DATE;
            LNCTRPRM.COMM_DATA.DATA_TXT.DAT2 = LNCTRPRM.COMM_DATA.EXP_DATE;
        }
        CEP.TRC(SCCGWA, LNCTRPRM.COMM_DATA.DATA_TXT.DAT1);
        CEP.TRC(SCCGWA, LNCTRPRM.COMM_DATA.DATA_TXT.DAT2);
        if ((LNCTRPRM.COMM_DATA.FUNC == 'A' 
            || LNCTRPRM.COMM_DATA.FUNC == 'M') 
            && (LNCTRPRM.COMM_DATA.DATA_TXT.STS != 'Y' 
            && LNCTRPRM.COMM_DATA.DATA_TXT.STS != 'N')) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TPRPM_STS_INT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        if (LNCTRPRM.COMM_DATA.FUNC == 'I') {
            B100_READ_PROCESS();
        } else if (LNCTRPRM.COMM_DATA.FUNC == 'A') {
            B100_WRITE_PROCESS();
        } else if (LNCTRPRM.COMM_DATA.FUNC == 'M') {
            B100_READU_PROCESS();
            B100_REWRITE_PROCESS();
        } else if (LNCTRPRM.COMM_DATA.FUNC == 'D') {
            B100_READ_PROCESS();
            B100_DELETE_PROCESS();
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TPRPM_STS_INT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        B200_OUTPUT_PROCESS();
    }
    public void B100_READ_PROCESS() throws IOException,SQLException,Exception {
        BPRPRMT.KEY.TYP = LNCTRPRM.COMM_DATA.TYP;
        BPRPRMT.KEY.CD = LNCTRPRM.COMM_DATA.CODE;
        S000_CALL_BPZPRMR();
    }
    public void B100_READU_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '4';
        BPRPRMT.KEY.TYP = LNCTRPRM.COMM_DATA.TYP;
        BPRPRMT.KEY.CD = LNCTRPRM.COMM_DATA.CODE;
        BPCPRMM.EFF_DT = LNCTRPRM.COMM_DATA.EFF_DATE;
        S000_CALL_BPZPRMM();
    }
    public void B100_REWRITE_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '2';
        BPRPRMT.KEY.TYP = LNCTRPRM.COMM_DATA.TYP;
        BPRPRMT.KEY.CD = LNCTRPRM.COMM_DATA.CODE;
        BPCPRMM.EFF_DT = LNCTRPRM.COMM_DATA.EFF_DATE;
        BPCPRMM.EXP_DT = LNCTRPRM.COMM_DATA.EXP_DATE;
        BPRPRMT.DESC = LNCTRPRM.COMM_DATA.DESC;
        BPRPRMT.CDESC = LNCTRPRM.COMM_DATA.CDESC;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCTRPRM.COMM_DATA.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        S000_CALL_BPZPRMM();
    }
    public void B100_DELETE_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '1';
        BPRPRMT.KEY.TYP = LNCTRPRM.COMM_DATA.TYP;
        BPRPRMT.KEY.CD = LNCTRPRM.COMM_DATA.CODE;
        BPCPRMM.EFF_DT = LNCTRPRM.COMM_DATA.EFF_DATE;
        S000_CALL_BPZPRMM();
    }
    public void B100_WRITE_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '0';
        BPRPRMT.KEY.TYP = LNCTRPRM.COMM_DATA.TYP;
        BPRPRMT.KEY.CD = LNCTRPRM.COMM_DATA.CODE;
        BPCPRMM.EFF_DT = LNCTRPRM.COMM_DATA.EFF_DATE;
        BPCPRMM.EXP_DT = LNCTRPRM.COMM_DATA.EXP_DATE;
        BPRPRMT.DESC = LNCTRPRM.COMM_DATA.DESC;
        BPRPRMT.CDESC = LNCTRPRM.COMM_DATA.CDESC;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCTRPRM.COMM_DATA.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        S000_CALL_BPZPRMM();
    }
    public void B200_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        LNCTRPRM.COMM_DATA.DESC = BPRPRMT.DESC;
        LNCTRPRM.COMM_DATA.CDESC = BPRPRMT.CDESC;
        LNCTRPRM.COMM_DATA.EFF_DATE = BPCPRMM.EFF_DT;
        LNCTRPRM.COMM_DATA.EXP_DATE = BPCPRMM.EXP_DT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCTRPRM.COMM_DATA.DATA_TXT);
        CEP.TRC(SCCGWA, LNCTRPRM.COMM_DATA.DATA_TXT.CTL_TAX);
        CEP.TRC(SCCGWA, LNCTRPRM.COMM_DATA.DATA_TXT.CTL_STSW);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
