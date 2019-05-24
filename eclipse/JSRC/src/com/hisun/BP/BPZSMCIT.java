package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZSMCIT {
    boolean pgmRtn = false;
    String K_CITY_TYPE = "CITY";
    String K_TR_TYP_CITY = "BPCITY";
    String K_INQ_FMT_CD = "BPX01";
    String K_OUTPUT_FMT = "BP743";
    String CPN_PARM_MAINTAIN = "BP-PARM-MAINTAIN";
    String CPN_PARM_BROWSE = "BP-R-MBRW-PARM";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String K_HIS_RMKS = "CITY CODE MAINTAIN";
    String K_HIS_CPB_NM = "BPCHCITY";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    BPZSMCIT_WS_CITY_INFO WS_CITY_INFO = new BPZSMCIT_WS_CITY_INFO();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCOMCIT BPCOMCIT = new BPCOMCIT();
    BPCHCITY BPCCITYO = new BPCHCITY();
    BPCHCITY BPCCITYN = new BPCHCITY();
    BPRCITY BPRCITY = new BPRCITY();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPCSMCIT BPCSMCIT;
    public void MP(SCCGWA SCCGWA, BPCSMCIT BPCSMCIT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSMCIT = BPCSMCIT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSMCIT return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSMCIT.FUNC == 'Q') {
            B010_INQ_CITY_CODE_PROC();
            if (pgmRtn) return;
        } else if (BPCSMCIT.FUNC == 'A') {
            B030_CRT_CITY_CODE_PROC();
            if (pgmRtn) return;
        } else if (BPCSMCIT.FUNC == 'U') {
            B050_UPD_CITY_CODE_PROC();
            if (pgmRtn) return;
        } else if (BPCSMCIT.FUNC == 'D') {
            B070_DEL_CITY_CODE_PROC();
            if (pgmRtn) return;
        } else if (BPCSMCIT.FUNC == 'B') {
            B090_BRW_CITY_CODE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSMCIT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_INQ_CITY_CODE_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRCITY);
        IBS.init(SCCGWA, BPCPRMM);
        BPRCITY.KEY.TYP = "CITY";
        WS_CITY_INFO.WS_CITY_KEY.WS_CNTY_CODE = BPCSMCIT.CNTY_CODE;
        WS_CITY_INFO.WS_CITY_KEY.WS_CITY_CODE = BPCSMCIT.CITY_CODE;
        BPRCITY.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CITY_INFO.WS_CITY_KEY);
        BPCPRMM.EFF_DT = BPCSMCIT.EFF_DT;
        BPCPRMM.FUNC = '3';
        BPCPRMM.DAT_PTR = BPRCITY;
        BPRCITY.DATA_LEN = 52;
        CEP.TRC(SCCGWA, BPRCITY.DATA_LEN);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRCITY.DATA_TXT);
        CEP.TRC(SCCGWA, BPRCITY);
        CEP.TRC(SCCGWA, BPRCITY.EDESC);
        CEP.TRC(SCCGWA, BPRCITY.CDSC);
        CEP.TRC(SCCGWA, BPRCITY.DATA_TXT.DESC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CITY_CD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B100_TRANS_DATA_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B030_CRT_CITY_CODE_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRCITY);
        IBS.init(SCCGWA, BPCPRMM);
        BPRCITY.KEY.TYP = "CITY";
        BPRCITY.DATA_TXT.DESC = BPCSMCIT.ENG_NAME;
        BPRCITY.EDESC = BPCSMCIT.ENG_NAME;
        BPRCITY.CDSC = BPCSMCIT.LOA_NAME;
        BPCPRMM.EFF_DT = BPCSMCIT.EFF_DT;
        BPCPRMM.EXP_DT = BPCSMCIT.EXP_DT;
        WS_CITY_INFO.WS_CITY_KEY.WS_CNTY_CODE = BPCSMCIT.CNTY_CODE;
        WS_CITY_INFO.WS_CITY_KEY.WS_CITY_CODE = BPCSMCIT.CITY_CODE;
        BPRCITY.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CITY_INFO.WS_CITY_KEY);
        BPRCITY.DATA_TXT.PDAYS = BPCSMCIT.PDAYS;
        BPRCITY.DATA_TXT.P_CUTTIME = BPCSMCIT.P_CUTTIME;
        BPRCITY.DATA_TXT.C_CUTTIME = BPCSMCIT.C_CUTTIME;
        BPRCITY.DATA_TXT.CALR_CODE = BPCSMCIT.CALR_CODE;
        BPRCITY.DATA_TXT.TIMEZONE = BPCSMCIT.TIMEZONE;
        CEP.TRC(SCCGWA, BPCSMCIT.ENG_NAME);
        CEP.TRC(SCCGWA, BPCSMCIT.LOA_NAME);
        CEP.TRC(SCCGWA, BPCSMCIT.EFF_DT);
        CEP.TRC(SCCGWA, BPCSMCIT.EXP_DT);
        CEP.TRC(SCCGWA, BPCSMCIT.CNTY_CODE);
        CEP.TRC(SCCGWA, BPCSMCIT.CITY_CODE);
        BPCPRMM.FUNC = '0';
        BPCPRMM.DAT_PTR = BPRCITY;
        BPRCITY.DATA_LEN = 52;
        CEP.TRC(SCCGWA, BPRCITY.DATA_LEN);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_WRI_HIS_PROC();
        if (pgmRtn) return;
        B100_TRANS_DATA_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B050_UPD_CITY_CODE_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRCITY);
        IBS.init(SCCGWA, BPCPRMM);
        BPRCITY.KEY.TYP = "CITY";
        WS_CITY_INFO.WS_CITY_KEY.WS_CNTY_CODE = BPCSMCIT.CNTY_CODE;
        WS_CITY_INFO.WS_CITY_KEY.WS_CITY_CODE = BPCSMCIT.CITY_CODE;
        BPRCITY.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CITY_INFO.WS_CITY_KEY);
        BPCPRMM.EFF_DT = BPCSMCIT.EFF_DT;
        BPCPRMM.EXP_DT = BPCSMCIT.EXP_DT;
        BPCPRMM.FUNC = '4';
        BPCPRMM.DAT_PTR = BPRCITY;
        BPRCITY.DATA_LEN = 52;
        CEP.TRC(SCCGWA, BPRCITY.DATA_LEN);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CITY_CD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCCITYO);
        BPCCITYO.EFF_DT = BPCPRMM.EFF_DT;
        BPCCITYO.EXP_DT = BPCPRMM.EXP_DT;
        BPCCITYO.PDAYS = BPRCITY.DATA_TXT.PDAYS;
        BPCCITYO.ENG_NAME = BPRCITY.DATA_TXT.DESC;
        BPCCITYO.LOA_NAME = BPRCITY.CDSC;
        BPCCITYO.P_CUTTIME = BPRCITY.DATA_TXT.P_CUTTIME;
        BPCCITYO.C_CUTTIME = BPRCITY.DATA_TXT.C_CUTTIME;
        BPCCITYO.CALR_CODE = BPRCITY.DATA_TXT.CALR_CODE;
        BPCCITYO.TIMEZONE = BPRCITY.DATA_TXT.TIMEZONE;
        IBS.init(SCCGWA, BPCCITYN);
        BPCCITYN.EFF_DT = BPCSMCIT.EFF_DT;
        BPCCITYN.EXP_DT = BPCSMCIT.EXP_DT;
        BPCCITYN.PDAYS = BPCSMCIT.PDAYS;
        BPCCITYN.LOA_NAME = BPCSMCIT.LOA_NAME;
        BPCCITYN.ENG_NAME = BPCSMCIT.ENG_NAME;
        BPCCITYN.P_CUTTIME = BPCSMCIT.P_CUTTIME;
        BPCCITYN.C_CUTTIME = BPCSMCIT.C_CUTTIME;
        BPCCITYN.CALR_CODE = BPCSMCIT.CALR_CODE;
        BPCCITYN.TIMEZONE = BPCSMCIT.TIMEZONE;
        R000_WRI_HIS_PROC();
        if (pgmRtn) return;
