package com.hisun.DC;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPRPRMT;
import com.hisun.DD.DDCMSG_ERROR_MSG;
import com.hisun.DD.DDVMPRD;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class DCZSIMAC {
    boolean pgmRtn = false;
    String K_PRDPR_TYPE = "PRDPR";
    String K_OUT_FMT = "DC941";
    String K_HIS_CPB_NM = "DCCAMPRD";
    String K_HIS_RMKS = "IR(DEPOSIT FOR LOANS) PRD PARM MAINTENANCE";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    char WS_CI_TYPE = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DCCAMPRD DCCMPRDM = new DCCAMPRD();
    DCCAMPRD DCCMPRDN = new DCCAMPRD();
    DCCMPRDO DCCMPRDO = new DCCMPRDO();
    DCCIAPRD DCCIAPRD = new DCCIAPRD();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    DDVMPRD DDVMPRD = new DDVMPRD();
    LNRCTLPM LNRCTLPM = new LNRCTLPM();
    DDCUPARM DDCUPARM = new DDCUPARM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    SCCGWA SCCGWA;
    DCCSIMAC DCCSIMAC;
    public void MP(SCCGWA SCCGWA, DCCSIMAC DCCSIMAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSIMAC = DCCSIMAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSIMAC return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "************INPUT************");
        CEP.TRC(SCCGWA, DCCSIMAC.FUNC);
        CEP.TRC(SCCGWA, DCCSIMAC.KEY.CON_MDEL);
        CEP.TRC(SCCGWA, DCCSIMAC.KEY.PROD_CODE);
        CEP.TRC(SCCGWA, DCCSIMAC.CNAME);
        CEP.TRC(SCCGWA, DCCSIMAC.STR_DATE);
        CEP.TRC(SCCGWA, DCCSIMAC.EXP_DATE);
        CEP.TRC(SCCGWA, DCCSIMAC.PROD_INFO.AC_NUM);
        CEP.TRC(SCCGWA, DCCSIMAC.PROD_INFO.AS_NUM);
        CEP.TRC(SCCGWA, DCCSIMAC.PROD_INFO.CI_TYP);
        CEP.TRC(SCCGWA, DCCSIMAC.PROD_INFO.PROD_DD_INFO[1-1].DD_PROD);
        CEP.TRC(SCCGWA, DCCSIMAC.PROD_INFO.PROD_DD_INFO[1-1].PROD_DNM);
        CEP.TRC(SCCGWA, DCCSIMAC.PROD_INFO.PROD_DD_INFO[2-1].DD_PROD);
        CEP.TRC(SCCGWA, DCCSIMAC.PROD_INFO.PROD_DD_INFO[2-1].PROD_DNM);
        CEP.TRC(SCCGWA, DCCSIMAC.PROD_INFO.PROD_TD_INFO[1-1].LN_PROD);
        CEP.TRC(SCCGWA, DCCSIMAC.PROD_INFO.PROD_TD_INFO[1-1].PROD_LNM);
        CEP.TRC(SCCGWA, DCCSIMAC.PROD_INFO.PROD_TD_INFO[2-1].LN_PROD);
        CEP.TRC(SCCGWA, DCCSIMAC.PROD_INFO.PROD_TD_INFO[2-1].PROD_LNM);
        CEP.TRC(SCCGWA, DCCSIMAC.PROD_INFO.CCY);
        CEP.TRC(SCCGWA, DCCSIMAC.PROD_INFO.CCY_TYPE);
        CEP.TRC(SCCGWA, DCCSIMAC.PROD_INFO.DAYS);
        CEP.TRC(SCCGWA, DCCSIMAC.PROD_INFO.PERD);
        CEP.TRC(SCCGWA, DCCSIMAC.PROD_INFO.PERD_UNIT);
        CEP.TRC(SCCGWA, DCCSIMAC.PROD_INFO.REPY_MTH);
        CEP.TRC(SCCGWA, DCCSIMAC.PROD_INFO.PERM_KND);
        CEP.TRC(SCCGWA, DCCSIMAC.PROD_INFO.MMDD);
        if (DCCSIMAC.FUNC == 'Q') {
            B030_QUERY_PROCESS();
            if (pgmRtn) return;
            B130_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSIMAC.FUNC == 'A') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B050_ADD_PROCESS();
            if (pgmRtn) return;
            B110_WRITE_HIS_PROC();
            if (pgmRtn) return;
            B130_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSIMAC.FUNC == 'M') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B070_MODIFY_PROCESS();
            if (pgmRtn) return;
            B110_WRITE_HIS_PROC();
            if (pgmRtn) return;
            B130_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSIMAC.FUNC == 'D') {
            B090_DELETE_PROCESS();
            if (pgmRtn) return;
            B110_WRITE_HIS_PROC();
            if (pgmRtn) return;
            B130_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DCCSIMAC.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCSIMAC.PROD_INFO.CI_TYP != '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_PERSON;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSIMAC.STR_DATE.compareTo(DCCSIMAC.EXP_DATE) >= 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_EFF_MUST_LT_EXP_DT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            if (DCCSIMAC.PROD_INFO.PROD_DD_INFO[WS_I-1].DD_PROD.trim().length() > 0) {
                IBS.init(SCCGWA, BPCPQPRD);
                BPCPQPRD.PRDT_CODE = DCCSIMAC.PROD_INFO.PROD_DD_INFO[WS_I-1].DD_PROD;
                CEP.TRC(SCCGWA, BPCPQPRD.PRDT_CODE);
                S000_CALL_BPZPQPRD();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
                if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
                    IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_SPT_PRD_INVALID, BPCPQPRD.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, DDCUPARM);
                IBS.init(SCCGWA, DDVMPRD);
                DDCUPARM.TX_TYPE = 'I';
                DDCUPARM.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
                DDCUPARM.DATA.KEY.PRDMO_CD = "CAAC";
                DDCUPARM.DATA.KEY.PARM_CODE = BPCPQPRD.PARM_CODE;
                S000_CALL_DDZUPARM();
                if (pgmRtn) return;
                if (DDCUPARM.RC.RC_CODE != 0) {
                    CEP.ERR(SCCGWA, DDCUPARM.RC);
                } else {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCUPARM.DATA);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDVMPRD);
                }
                if (BPCPQPRD.CUS_PER_FLG == '0') {
                    DCCSIMAC.PROD_INFO.PROD_DD_INFO[WS_I-1].PROD_DNM = BPCPQPRD.PRDT_NAME;
                } else {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPT_PRD_INVALID;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            if (DCCSIMAC.PROD_INFO.PROD_TD_INFO[WS_I-1].LN_PROD.trim().length() > 0) {
                IBS.init(SCCGWA, BPCPQPRD);
                BPCPQPRD.PRDT_CODE = DCCSIMAC.PROD_INFO.PROD_TD_INFO[WS_I-1].LN_PROD;
                CEP.TRC(SCCGWA, BPCPQPRD.PRDT_CODE);
                S000_CALL_BPZPQPRD();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
                if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
                    IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_SPT_PRD_INVALID, BPCPQPRD.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, BPRPRMT);
                IBS.init(SCCGWA, BPCPRMM);
                IBS.init(SCCGWA, LNRCTLPM);
                BPRPRMT.KEY.TYP = K_PRDPR_TYPE;
                if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
                JIBS_tmp_int = BPRPRMT.KEY.CD.length();
                for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
                BPRPRMT.KEY.CD = "999999" + BPRPRMT.KEY.CD.substring(6);
                if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
                JIBS_tmp_int = BPRPRMT.KEY.CD.length();
                for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
                if (BPCPQPRD.PARM_CODE == null) BPCPQPRD.PARM_CODE = "";
                JIBS_tmp_int = BPCPQPRD.PARM_CODE.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) BPCPQPRD.PARM_CODE += " ";
                BPRPRMT.KEY.CD = BPRPRMT.KEY.CD.substring(0, 7 - 1) + BPCPQPRD.PARM_CODE + BPRPRMT.KEY.CD.substring(7 + 8 - 1);
                BPCPRMM.FUNC = '3';
                BPCPRMM.DAT_PTR = BPRPRMT;
                S000_CALL_BPZPRMM();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPT_PRD_INVALID;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNRCTLPM.DATA_TXT);
                    if (LNRCTLPM.DATA_TXT.PROD_MOD == 'R') {
                        WS_CI_TYPE = '1';
                    }
                    if (LNRCTLPM.DATA_TXT.PROD_MOD == 'C') {
                        WS_CI_TYPE = '2';
                    }
                    if (LNRCTLPM.DATA_TXT.PROD_MOD == 'F') {
                        WS_CI_TYPE = '3';
                    }
                    CEP.TRC(SCCGWA, LNRCTLPM.DATA_TXT.PROD_MOD);
                    if (DCCSIMAC.PROD_INFO.CI_TYP != WS_CI_TYPE) {
                        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPT_PRD_INVALID;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void B030_QUERY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSIMAC.KEY.PROD_CODE);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, DCCIAPRD);
        BPRPRMT.KEY.TYP = K_PRDPR_TYPE;
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        BPRPRMT.KEY.CD = "999999" + BPRPRMT.KEY.CD.substring(6);
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        if (DCCSIMAC.KEY.PROD_CODE == null) DCCSIMAC.KEY.PROD_CODE = "";
        JIBS_tmp_int = DCCSIMAC.KEY.PROD_CODE.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) DCCSIMAC.KEY.PROD_CODE += " ";
        BPRPRMT.KEY.CD = BPRPRMT.KEY.CD.substring(0, 7 - 1) + DCCSIMAC.KEY.PROD_CODE + BPRPRMT.KEY.CD.substring(7 + 8 - 1);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCIAPRD);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        BPCPRMM.FUNC = '3';
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INTA_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
