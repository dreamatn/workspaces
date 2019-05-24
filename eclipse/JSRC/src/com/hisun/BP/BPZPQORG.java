package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQORG {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_MAINT_ORGS = "BP-R-ORGS-MAINTAIN  ";
    char K_STS_CLOSE = 'C';
    String CPN_ORGM_READ = "BP-R-BRW-ORGM       ";
    String WS_ERR_MSG = " ";
    BPZPQORG_WS_ORGM_KEY WS_ORGM_KEY = new BPZPQORG_WS_ORGM_KEY();
    char WS_BRCH_CHECK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRORGM BPCRORGM = new BPCRORGM();
    BPRORGM BPRPORGM = new BPRORGM();
    BPCRMOGS BPCRMOGS = new BPCRMOGS();
    BPRORGS BPRORGS = new BPRORGS();
    SCCGWA SCCGWA;
    BPCPQORG BPCPQORG;
    public void MP(SCCGWA SCCGWA, BPCPQORG BPCPQORG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQORG = BPCPQORG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQORG return!");
        Z_RET();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.RC);
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG.RC);
        if (BPCPQORG.BNK.trim().length() == 0) {
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B200_GET_ORG_INFO();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B300_OUTPUT_INFO_CN();
            if (pgmRtn) return;
        }
        B400_GET_ORG_STS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCPQORG.BR == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPQORG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_GET_ORG_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPORGM);
        IBS.init(SCCGWA, BPCRORGM);
        BPRPORGM.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        WS_ORGM_KEY.WS_ORGM_BNK = BPCPQORG.BNK;
        WS_ORGM_KEY.WS_ORGM_BR = BPCPQORG.BR;
        BPRPORGM.KEY.BR = WS_ORGM_KEY.WS_ORGM_BR;
        CEP.TRC(SCCGWA, BPCPQORG.BNK);
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        CEP.TRC(SCCGWA, BPRPORGM.KEY.BR);
        BPCRORGM.INFO.FUNC = 'R';
        BPCRORGM.INFO.POINTER = BPRPORGM;
        BPCRORGM.INFO.LEN = 1252;
        S000_CALL_BPZRORGM();
        if (pgmRtn) return;
        if (BPCRORGM.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "M-BP-PARM-NOTFND");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ORG_NOTFND, BPCPQORG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B400_GET_ORG_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRORGS);
        IBS.init(SCCGWA, BPCRMOGS);
        BPRORGS.KEY.BNK = BPCPQORG.BNK;
        BPRORGS.KEY.BR = BPCPQORG.BR;
        BPCRMOGS.FUNC = 'Q';
        BPCRMOGS.POINTER = BPRORGS;
        BPCRMOGS.DATA_LEN = 39;
        S000_CALL_BPZRMOGS();
        if (pgmRtn) return;
        if (BPCRMOGS.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "RMOGS-NOTFND");
            BPCPQORG.ORG_STS = K_STS_CLOSE;
        } else {
            BPCPQORG.ORG_STS = BPRORGS.STS;
        }
    }
    public void B300_OUTPUT_INFO_CN() throws IOException,SQLException,Exception {
        WS_ORGM_KEY.WS_ORGM_BR = BPRPORGM.KEY.BR;
        BPCPQORG.BNK = WS_ORGM_KEY.WS_ORGM_BNK;
        BPCPQORG.BR = WS_ORGM_KEY.WS_ORGM_BR;
        BPCPQORG.SUPR_BR = BPRPORGM.SUPR_BR;
        BPCPQORG.ATTR = BPRPORGM.ATTR;
        BPCPQORG.LVL = BPRPORGM.LVL;
        BPCPQORG.TYP = BPRPORGM.TYPE;
        BPCPQORG.EFF_DT = BPRPORGM.EFF_DT;
        BPCPQORG.EXP_DT = BPRPORGM.EXP_DT;
        BPCPQORG.CHN_NM = BPRPORGM.CHN_NM;
        BPCPQORG.CHN_ADDR = BPRPORGM.CHN_ADDR;
        BPCPQORG.ENG_NM = BPRPORGM.ENG_NM;
        BPCPQORG.ENG_ADDR = BPRPORGM.ENG_ADDR;
        BPCPQORG.ABBR = BPRPORGM.ABBR;
        BPCPQORG.LINK_MAN = BPRPORGM.LINK_MAN;
        BPCPQORG.LINK_TEL = BPRPORGM.LINK_TEL;
        BPCPQORG.POST = BPRPORGM.POST;
        BPCPQORG.TLR_MAX = BPRPORGM.TLR_MAX;
        BPCPQORG.ATH_MAX = BPRPORGM.ATH_MAX;
        BPCPQORG.FX_BUSI = BPRPORGM.FX_BUSI;
        BPCPQORG.CNAP_NO = BPRPORGM.CNAP_NO;
        BPCPQORG.ACCT_FLG = BPRPORGM.ACCT_FLG;
        BPCPQORG.OPN_DT = BPRPORGM.OPN_DT;
        BPCPQORG.RUN_HDAY = BPRPORGM.RUN_HDAY;
        BPCPQORG.RUN_MODE = BPRPORGM.RUN_MODE;
        BPCPQORG.CALD_CD = BPRPORGM.CALD_CD;
        BPCPQORG.OPN_TM = BPRPORGM.OPN_TM;
        BPCPQORG.CLS_TM = BPRPORGM.CLS_TM;
        BPCPQORG.HOPN_TM = BPRPORGM.HOPN_TM;
        BPCPQORG.HCLS_TM = BPRPORGM.HCLS_TM;
        BPCPQORG.UPT_DT = BPRPORGM.UPT_DT;
        BPCPQORG.UPT_TLR = BPRPORGM.UPT_TLR;
        BPCPQORG.FAX = BPRPORGM.FAX;
        BPCPQORG.TELEX = BPRPORGM.TELEX;
        BPCPQORG.PRO_FLG = BPRPORGM.PRO_FLG;
        BPCPQORG.COST_FLG = BPRPORGM.COST_FLG;
        BPCPQORG.DEF_PTR = BPRPORGM.DEF_PTR;
        BPCPQORG.PAY_PTR = BPRPORGM.PAY_PTR;
        BPCPQORG.CFM_PTR = BPRPORGM.CFM_PTR;
        BPCPQORG.SWF_ID = BPRPORGM.SWF_ID;
        BPCPQORG.BIC_NO = BPRPORGM.BIC_NO;
        BPCPQORG.IBAN_NO = BPRPORGM.IBAN_NO;
        BPCPQORG.CI_LEN = BPRPORGM.CI_LEN;
        BPCPQORG.COUN_CD = BPRPORGM.COUN_CD;
        BPCPQORG.CITY_CD = BPRPORGM.CITY_CD;
        BPCPQORG.UNSCH_HOL = BPRPORGM.UNSCH_HOL;
        BPCPQORG.UNSCH_HOL_TM = BPRPORGM.UNSCH_HOL_TM;
        BPCPQORG.OPN_CHECK_FLG = BPRPORGM.OPN_CHECK_FLG;
        BPCPQORG.VOU_CHECK_FLG = BPRPORGM.VOU_CHECK_FLG;
        BPCPQORG.INT_TAX_FLG = BPRPORGM.INT_TAX_FLG;
        BPCPQORG.ERP_BRAN = BPRPORGM.ERP_BRAN;
        BPCPQORG.BRANCH_BR = BPRPORGM.BRANCH_BR;
        BPCPQORG.VIL_TYP = BPRPORGM.VIL_TYP;
        BPCPQORG.TRA_TYP = BPRPORGM.TRA_TYP;
        BPCPQORG.INT_BR_FLG = BPRPORGM.INT_BR_FLG;
        BPCPQORG.AREA_CD = BPRPORGM.AREA_CD;
        BPCPQORG.STS = BPRPORGM.BR_STS;
        BPCPQORG.BBR = BPRPORGM.BBR;
    }
    public void S000_CALL_BPZRORGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_ORGM_READ, BPCRORGM);
        if (BPCRORGM.RC.RC_CODE != 0 
            && BPCRORGM.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PARM_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRMOGS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_MAINT_ORGS, BPCRMOGS);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCRMOGS.RC);
        if (BPCRMOGS.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRMOGS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQORG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
