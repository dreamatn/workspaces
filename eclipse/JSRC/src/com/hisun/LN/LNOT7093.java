package com.hisun.LN;

import com.hisun.BP.*;
import com.hisun.SM.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT7093 {
    String JIBS_tmp_str[] = new String[10];
    String K_PARM_TYPE = "PRDPR";
    int K_MAX_DATE = 99991231;
    int K_BR = "99999999";
    char K_CHN_FILLER = 0X02;
    LNOT7093_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT7093_WS_TEMP_VARIABLE();
    LNOT7093_WS_PRM_KEY WS_PRM_KEY = new LNOT7093_WS_PRM_KEY();
    LNCXP09 LNCXP09 = new LNCXP09();
    LNRCMTP LNRCMTP = new LNRCMTP();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCCMTPM LNCCMTPM = new LNCCMTPM();
    SCCGWA SCCGWA;
    LNB7090_AWA_7090 LNB7090_AWA_7090;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT7093 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB7090_AWA_7090>");
        LNB7090_AWA_7090 = (LNB7090_AWA_7090) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, LNCCMTPM);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRPRMT);
        BPCPRMM.DAT_PTR = BPRPRMT;
        CEP.TRC(SCCGWA, LNB7090_AWA_7090.FUNC);
        CEP.TRC(SCCGWA, LNB7090_AWA_7090.PRDCP);
        CEP.TRC(SCCGWA, LNB7090_AWA_7090.PRDCPM);
        CEP.TRC(SCCGWA, LNB7090_AWA_7090.PRODMO);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_TRANS_DATA();
    }
    public void B100_TRANS_DATA() throws IOException,SQLException,Exception {
        if (LNB7090_AWA_7090.FUNC != 'A') {
            BPCPRMM.FUNC = '3';
            BPRPRMT.KEY.TYP = K_PARM_TYPE;
            WS_PRM_KEY.WS_ACBR = K_BR;
            WS_PRM_KEY.WS_PRM_CD = LNB7090_AWA_7090.PRDCP;
            BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_PRM_KEY);
            BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
            CEP.TRC(SCCGWA, WS_PRM_KEY);
            S000_CALL_BPZPRMM();
            CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
            LNB7090_AWA_7090.EXPDATE = K_MAX_DATE;
            B100_OUTPUT_PROCESS();
        } else {
            IBS.init(SCCGWA, BPRPRMT.DAT_TXT);
            IBS.init(SCCGWA, LNCCMTPM.DATA_TXT);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCMTPM.DATA_TXT);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
            if (LNB7090_AWA_7090.PRDCPM.trim().length() > 0) {
                BPCPRMM.FUNC = '3';
                BPRPRMT.KEY.TYP = K_PARM_TYPE;
                WS_PRM_KEY.WS_ACBR = K_BR;
                WS_PRM_KEY.WS_PRM_CD = LNB7090_AWA_7090.PRDCPM;
                BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_PRM_KEY);
                BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
                S000_CALL_BPZPRMM();
            }
            BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCPRMM.EXP_DT = K_MAX_DATE;
            B100_OUTPUT_PROCESS();
        }
    }
    public void B100_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        IBS.init(SCCGWA, LNCXP09);
        IBS.init(SCCGWA, SCCFMT);
        LNCXP09.FUNC = LNB7090_AWA_7090.FUNC;
        LNCXP09.TYPE = K_PARM_TYPE;
        LNCXP09.CODE = LNB7090_AWA_7090.PRDCP;
        LNCXP09.EFF_DATE = BPCPRMM.EFF_DT;
        LNCXP09.EXP_DATE = BPCPRMM.EXP_DT;
        LNCXP09.DESC = BPRPRMT.DESC;
        LNCXP09.CDESC = BPRPRMT.CDESC;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNRCMTP.DATA_TXT);
        LNCXP09.PRODMO = LNRCMTP.DATA_TXT.PRODMO;
        LNCXP09.PROD_MOD = LNRCMTP.DATA_TXT.PROD_MOD;
        LNCXP09.BAL_FLG = LNRCMTP.DATA_TXT.BAL_FLG;
        LNCXP09.PROD_CLS = LNRCMTP.DATA_TXT.PROD_CLS;
        LNCXP09.SYS_FLG = LNRCMTP.DATA_TXT.SYS_FLG;
        LNCXP09.OVER_FLG = LNRCMTP.DATA_TXT.OVER_FLG;
        LNCXP09.ADV_FLG = LNRCMTP.DATA_TXT.AUTO_DISB;
        LNCXP09.ADV_CODE = LNRCMTP.DATA_TXT.PROD_CD;
        CEP.TRC(SCCGWA, LNCXP09.FUNC);
        CEP.TRC(SCCGWA, LNCXP09.TYPE);
        CEP.TRC(SCCGWA, LNCXP09.CODE);
        CEP.TRC(SCCGWA, LNCXP09.DESC);
        CEP.TRC(SCCGWA, LNCXP09.CDESC);
        CEP.TRC(SCCGWA, LNCXP09.EFF_DATE);
        CEP.TRC(SCCGWA, LNCXP09.EXP_DATE);
        CEP.TRC(SCCGWA, LNCXP09.VAL_LEN);
        CEP.TRC(SCCGWA, LNCXP09.PROD_MOD);
        CEP.TRC(SCCGWA, LNCXP09.BAL_FLG);
        CEP.TRC(SCCGWA, LNCXP09.PROD_CLS);
        CEP.TRC(SCCGWA, LNCXP09.OVER_FLG);
        CEP.TRC(SCCGWA, LNCXP09.ADV_FLG);
        CEP.TRC(SCCGWA, LNCXP09.ADV_CODE);
        SCCFMT.FMTID = "LNP09";
        SCCFMT.DATA_PTR = LNCXP09;
        SCCFMT.DATA_LEN = 141;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = BPCPRMM.RC.RC_APP;
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = BPCPRMM.RC.RC_RTNCODE;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
