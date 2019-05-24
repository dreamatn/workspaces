package com.hisun.LN;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZCMTPM {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_BR = "99999999";
    String K_DRAWDOWN_MTH = "P001";
    String K_PROD_CLS = "P015";
    LNZCMTPM_WS_PRM_KEY WS_PRM_KEY = new LNZCMTPM_WS_PRM_KEY();
    LNZCMTPM_WS_MSGID WS_MSGID = new LNZCMTPM_WS_MSGID();
    char WS_CAN_MOD = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNRCMTP LNRCMTP = new LNRCMTP();
    LNCCTLPM LNCCTLPM = new LNCCTLPM();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCSCGWY BPCSCGWY = new BPCSCGWY();
    BPRCGWY BPRCGWY = new BPRCGWY();
    BPCRCGWY BPCRCGWY = new BPCRCGWY();
    BPCTPDME BPCTPDME = new BPCTPDME();
    BPRPDME BPRPDME = new BPRPDME();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    LNCCMTPM LNCCMTPM;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, LNCCMTPM LNCCMTPM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCCMTPM = LNCCMTPM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZCMTPM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMR);
        BPCPRMM.RC.RC_APP = "BP";
        BPCPRMR.RC.RC_APP = "BP";
        BPCPRMM.DAT_PTR = BPRPRMT;
        BPCPRMR.DAT_PTR = BPRPRMT;
        LNCCMTPM.RC.RC_APP = "LN";
        LNCCMTPM.RC.RC_RTNCODE = 0;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (LNCCMTPM.FUNC == 'I') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
        } else if (LNCCMTPM.FUNC == 'A') {
            B100_WRITE_PROCESS();
            if (pgmRtn) return;
        } else if (LNCCMTPM.FUNC == 'M') {
            B100_READU_PROCESS();
            if (pgmRtn) return;
            B100_CHECK_PRODUCT();
            if (pgmRtn) return;
            B100_REWRITE_PROCESS();
            if (pgmRtn) return;
        } else if (LNCCMTPM.FUNC == 'D') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
            B100_CHECK_PRODUCT();
            if (pgmRtn) return;
            B100_DELETE_PROCESS();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "LAST CHECK REJECT.");
            CEP.TRC(SCCGWA, LNCCMTPM.FUNC);
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCCMTPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B200_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCCMTPM.KEY.TYPE);
        CEP.TRC(SCCGWA, LNCCMTPM.KEY.CODE);
        CEP.TRC(SCCGWA, LNCCMTPM.FUNC);
        CEP.TRC(SCCGWA, LNCCMTPM.DATA_TXT.PROD_MOD);
        CEP.TRC(SCCGWA, LNCCMTPM.EFF_DATE);
        if (LNCCMTPM.KEY.CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PARA_CD_ERR, LNCCMTPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((LNCCMTPM.FUNC != 'A' 
            && LNCCMTPM.FUNC != 'D' 
            && LNCCMTPM.FUNC != 'M' 
            && LNCCMTPM.FUNC != 'I')) {
            CEP.TRC(SCCGWA, "FUNC VALID CHECK REJECT.");
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCCMTPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCCMTPM.FUNC == 'A' 
            || LNCCMTPM.FUNC == 'M') {
            if ((LNCCMTPM.DATA_TXT.PROD_MOD != 'C' 
                && LNCCMTPM.DATA_TXT.PROD_MOD != 'R' 
                && LNCCMTPM.DATA_TXT.PROD_MOD != 'F')) {
                CEP.TRC(SCCGWA, "PROD MOD VALID CHECK REJECT.");
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCCMTPM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCCMTPM.DATA_TXT.BAL_FLG != 'M') {
                CEP.TRC(SCCGWA, "BAL CHECK REJECT.");
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCCMTPM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if ((LNCCMTPM.DATA_TXT.OVER_FLG != 'Y' 
                && LNCCMTPM.DATA_TXT.OVER_FLG != 'N')) {
                CEP.TRC(SCCGWA, "OVERSEA CHECK REJECT.");
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCCMTPM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, LNCCMTPM.DATA_TXT.AUTO_DISB);
            if ((LNCCMTPM.DATA_TXT.AUTO_DISB != 'Y' 
                && LNCCMTPM.DATA_TXT.AUTO_DISB != 'N')) {
                CEP.TRC(SCCGWA, "AUTO-DISB CHECK REJECT.");
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCCMTPM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCCMTPM.DATA_TXT.AUTO_DISB != 'Y') {
                if (LNCCMTPM.DATA_TXT.ADV_CODE.trim().length() > 0) {
                    CEP.TRC(SCCGWA, "MADV-CODE OR ADV-PERD OR ADV-UNT CANNOT INPUT");
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CANNOT_INPUT, LNCCMTPM.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            } else {
                if (LNCCMTPM.DATA_TXT.ADV_CODE.trim().length() == 0) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ADV_CODE_N_INPUT, LNCCMTPM.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    IBS.init(SCCGWA, BPCSCGWY);
                    BPCSCGWY.PRDT_CODE = LNCCMTPM.DATA_TXT.ADV_CODE;
                    BPCSCGWY.FUNC = 'Q';
                    S000_CALL_BPZSCGWY();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCSCGWY.PRDT_CODE);
                    CEP.TRC(SCCGWA, BPCSCGWY.PARM_CODE);
                    if (BPCSCGWY.PARM_CODE.trim().length() == 0) {
                        IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PROD_NOTFND, LNCCMTPM.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                    IBS.init(SCCGWA, LNCCTLPM);
                    LNCCTLPM.FUNC = 'I';
                    LNCCTLPM.KEY.TYP = "PRDPR";
                    LNCCTLPM.KEY.CD = BPCSCGWY.PARM_CODE;
                    CEP.TRC(SCCGWA, LNCCTLPM.KEY.CD);
                    S000_CALL_LNZCTLPM();
                    if (pgmRtn) return;
                    if (!LNCCTLPM.DATA_TXT.PROD_CLS.equalsIgnoreCase(K_PROD_CLS)) {
                        CEP.TRC(SCCGWA, "THE PRODUCT IS NOT ADVANCE IN CASH");
                        IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_NOT_ADVANCE, LNCCMTPM.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void B100_READ_PROCESS() throws IOException,SQLException,Exception {
        BPRPRMT.KEY.TYP = LNCCMTPM.KEY.TYPE;
        WS_PRM_KEY.WS_ACBR = K_BR;
        WS_PRM_KEY.WS_PRM_CD = LNCCMTPM.KEY.CODE;
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_PRM_KEY);
        CEP.TRC(SCCGWA, WS_PRM_KEY);
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
    }
    public void B100_READU_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '4';
        BPRPRMT.KEY.TYP = LNCCMTPM.KEY.TYPE;
        WS_PRM_KEY.WS_ACBR = K_BR;
        WS_PRM_KEY.WS_PRM_CD = LNCCMTPM.KEY.CODE;
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_PRM_KEY);
        BPCPRMM.EFF_DT = LNCCMTPM.EFF_DATE;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_REWRITE_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '2';
        BPRPRMT.KEY.TYP = LNCCMTPM.KEY.TYPE;
        WS_PRM_KEY.WS_ACBR = K_BR;
        WS_PRM_KEY.WS_PRM_CD = LNCCMTPM.KEY.CODE;
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_PRM_KEY);
        BPCPRMM.EFF_DT = LNCCMTPM.EFF_DATE;
        BPCPRMM.EXP_DT = LNCCMTPM.EXP_DATE;
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        CEP.TRC(SCCGWA, BPCPRMM.EXP_DT);
        BPRPRMT.DESC = LNCCMTPM.DESC;
        BPRPRMT.CDESC = LNCCMTPM.CDESC;
        IBS.init(SCCGWA, LNRCMTP.DATA_TXT);
        LNRCMTP.DATA_TXT.PRODMO = LNCCMTPM.DATA_TXT.PRODMO;
        LNRCMTP.DATA_TXT.PROD_MOD = LNCCMTPM.DATA_TXT.PROD_MOD;
        LNRCMTP.DATA_TXT.BAL_FLG = LNCCMTPM.DATA_TXT.BAL_FLG;
        LNRCMTP.DATA_TXT.PROD_CLS = LNCCMTPM.DATA_TXT.PROD_CLS;
        LNRCMTP.DATA_TXT.SYS_FLG = LNCCMTPM.DATA_TXT.SYS_FLG;
        LNRCMTP.DATA_TXT.OVER_FLG = LNCCMTPM.DATA_TXT.OVER_FLG;
        LNRCMTP.DATA_TXT.AUTO_DISB = LNCCMTPM.DATA_TXT.AUTO_DISB;
        LNRCMTP.DATA_TXT.PROD_CD = LNCCMTPM.DATA_TXT.ADV_CODE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNRCMTP.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_DELETE_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '1';
        BPRPRMT.KEY.TYP = LNCCMTPM.KEY.TYPE;
        WS_PRM_KEY.WS_ACBR = K_BR;
        WS_PRM_KEY.WS_PRM_CD = LNCCMTPM.KEY.CODE;
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_PRM_KEY);
        BPCPRMM.EFF_DT = LNCCMTPM.EFF_DATE;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_WRITE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCCMTPM.EFF_DATE);
        BPCPRMM.FUNC = '0';
        CEP.TRC(SCCGWA, LNCCMTPM.KEY.TYPE);
        CEP.TRC(SCCGWA, LNCCMTPM.KEY.CODE);
        CEP.TRC(SCCGWA, LNCCMTPM.EFF_DATE);
        CEP.TRC(SCCGWA, LNCCMTPM.DATA_TXT.SYS_FLG);
        CEP.TRC(SCCGWA, LNCCMTPM.DATA_TXT.OVER_FLG);
        CEP.TRC(SCCGWA, LNCCMTPM.DATA_TXT.AUTO_DISB);
        CEP.TRC(SCCGWA, LNCCMTPM.DATA_TXT.ADV_CODE);
        BPRPRMT.KEY.TYP = LNCCMTPM.KEY.TYPE;
        WS_PRM_KEY.WS_ACBR = K_BR;
        WS_PRM_KEY.WS_PRM_CD = LNCCMTPM.KEY.CODE;
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_PRM_KEY);
        BPCPRMM.EFF_DT = LNCCMTPM.EFF_DATE;
        BPCPRMM.EXP_DT = LNCCMTPM.EXP_DATE;
        BPRPRMT.DESC = LNCCMTPM.DESC;
        BPRPRMT.CDESC = LNCCMTPM.CDESC;
        IBS.init(SCCGWA, LNRCMTP.DATA_TXT);
        LNRCMTP.DATA_TXT.PRODMO = LNCCMTPM.DATA_TXT.PRODMO;
        LNRCMTP.DATA_TXT.PROD_MOD = LNCCMTPM.DATA_TXT.PROD_MOD;
        LNRCMTP.DATA_TXT.BAL_FLG = LNCCMTPM.DATA_TXT.BAL_FLG;
        LNRCMTP.DATA_TXT.PROD_CLS = LNCCMTPM.DATA_TXT.PROD_CLS;
        LNRCMTP.DATA_TXT.SYS_FLG = LNCCMTPM.DATA_TXT.SYS_FLG;
        LNRCMTP.DATA_TXT.OVER_FLG = LNCCMTPM.DATA_TXT.OVER_FLG;
        LNRCMTP.DATA_TXT.AUTO_DISB = LNCCMTPM.DATA_TXT.AUTO_DISB;
        LNRCMTP.DATA_TXT.PROD_CD = LNCCMTPM.DATA_TXT.ADV_CODE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNRCMTP.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.TYP);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
        CEP.TRC(SCCGWA, LNRCMTP.DATA_TXT.PROD_MOD);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCCMTPM.EFF_DATE);
    }
    public void B100_CHECK_PRODUCT() throws IOException,SQLException,Exception {
        WS_CAN_MOD = 'N';
        if (WS_CAN_MOD == 'N') {
            IBS.init(SCCGWA, BPCTPDME);
            BPCTPDME.INFO.FUNC = 'B';
            BPCTPDME.INFO.OPT = 'S';
            BPCTPDME.INFO.INDEX_FLG = '4';
            BPRPDME.PARM_CODE = LNCCMTPM.KEY.CODE;
            S000_CALL_BPZTPDME();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPCTPDME);
            BPCTPDME.INFO.FUNC = 'B';
            BPCTPDME.INFO.OPT = 'N';
            S000_CALL_BPZTPDME();
            if (pgmRtn) return;
            if (BPCTPDME.RETURN_INFO == 'F') {
                WS_CAN_MOD = 'Y';
            }
            IBS.init(SCCGWA, BPCTPDME);
            BPCTPDME.INFO.FUNC = 'B';
            BPCTPDME.INFO.OPT = 'E';
            S000_CALL_BPZTPDME();
            if (pgmRtn) return;
        }
        if (WS_CAN_MOD == 'Y' 
            && LNCCMTPM.FUNC == 'D') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_1675, LNCCMTPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_CAN_MOD == 'Y' 
            && LNCCMTPM.FUNC == 'M') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_1675, LNCCMTPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK() throws IOException,SQLException,Exception {
        if (BPCRCGWY.RETURN_INFO == 'F') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNRCMTP.DATA_TXT);
            CEP.TRC(SCCGWA, LNCCMTPM.DATA_TXT.PROD_MOD);
            CEP.TRC(SCCGWA, LNRCMTP.DATA_TXT.PROD_MOD);
            if (LNCCMTPM.DATA_TXT.PROD_MOD != LNRCMTP.DATA_TXT.PROD_MOD) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CMTP_PROD_MOD, LNCCMTPM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCCMTPM.DATA_TXT.BAL_FLG != LNRCMTP.DATA_TXT.BAL_FLG) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BAL_FLG, LNCCMTPM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (!LNCCMTPM.DATA_TXT.PROD_CLS.equalsIgnoreCase(LNRCMTP.DATA_TXT.PROD_CLS)) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CMTP_PROD_CLS, LNCCMTPM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCCMTPM.DATA_TXT.SYS_FLG != LNRCMTP.DATA_TXT.SYS_FLG) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CMTP_SYS_FLG, LNCCMTPM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCCMTPM.DATA_TXT.OVER_FLG != LNRCMTP.DATA_TXT.OVER_FLG) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CMTP_OVER_FLG, LNCCMTPM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRPRMT.KEY.TYP);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
        LNCCMTPM.KEY.CODE = BPRPRMT.KEY.CD.substring(9-1);
        LNCCMTPM.DESC = BPRPRMT.DESC;
        LNCCMTPM.CDESC = BPRPRMT.CDESC;
        LNCCMTPM.EFF_DATE = BPCPRMM.EFF_DT;
        LNCCMTPM.EXP_DATE = BPCPRMM.EXP_DT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNRCMTP.DATA_TXT);
        LNCCMTPM.DATA_TXT.PRODMO = LNRCMTP.DATA_TXT.PRODMO;
        LNCCMTPM.DATA_TXT.PROD_MOD = LNRCMTP.DATA_TXT.PROD_MOD;
        LNCCMTPM.DATA_TXT.BAL_FLG = LNRCMTP.DATA_TXT.BAL_FLG;
        LNCCMTPM.DATA_TXT.PROD_CLS = LNRCMTP.DATA_TXT.PROD_CLS;
        LNCCMTPM.DATA_TXT.SYS_FLG = LNRCMTP.DATA_TXT.SYS_FLG;
        LNCCMTPM.DATA_TXT.OVER_FLG = LNRCMTP.DATA_TXT.OVER_FLG;
        LNCCMTPM.DATA_TXT.AUTO_DISB = LNRCMTP.DATA_TXT.AUTO_DISB;
        LNCCMTPM.DATA_TXT.ADV_CODE = LNRCMTP.DATA_TXT.PROD_CD;
        CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.TYP);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        CEP.TRC(SCCGWA, BPCPRMM.EXP_DT);
        CEP.TRC(SCCGWA, BPRPRMT.DESC);
        CEP.TRC(SCCGWA, BPRPRMT.CDESC);
        CEP.TRC(SCCGWA, LNCCMTPM.DATA_TXT.PROD_MOD);
        CEP.TRC(SCCGWA, LNCCMTPM.DATA_TXT.OVER_FLG);
        CEP.TRC(SCCGWA, LNCCMTPM.DATA_TXT.AUTO_DISB);
        CEP.TRC(SCCGWA, LNCCMTPM.DATA_TXT.ADV_CODE);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            LNCCMTPM.RC.RC_APP = BPCPRMM.RC.RC_APP;
            LNCCMTPM.RC.RC_RTNCODE = BPCPRMM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCMTPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSCGWY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSCGWY.PRDT_CODE);
        CEP.TRC(SCCGWA, BPCSCGWY.CHANG_WAY);
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-CGWY", BPCSCGWY);
    }
    public void S000_CALL_BPZRCGWY() throws IOException,SQLException,Exception {
        BPCRCGWY.POINTER = BPRCGWY;
        BPCRCGWY.LEN = 50;
        IBS.CALLCPN(SCCGWA, "BP-R-DRW-CGWY", BPCRCGWY);
    }
    public void S000_CALL_BPZTPDME() throws IOException,SQLException,Exception {
        BPCTPDME.INFO.POINTER = BPRPDME;
        BPCTPDME.LEN = 516;
        IBS.CALLCPN(SCCGWA, "BP-T-MAINT-PRDT-MENU", BPCTPDME);
        if (BPCTPDME.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTPDME.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCMTPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCTLPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-PRM-CTLPM-MAINT", LNCCTLPM);
        if (LNCCTLPM.RC.RC_RTNCODE != 0) {
            LNCCMTPM.RC.RC_APP = LNCCTLPM.RC.RC_APP;
            LNCCMTPM.RC.RC_RTNCODE = LNCCTLPM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCCMTPM.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCCMTPM=");
            CEP.TRC(SCCGWA, LNCCMTPM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
