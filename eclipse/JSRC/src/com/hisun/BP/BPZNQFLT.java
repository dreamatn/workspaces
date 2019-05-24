package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZNQFLT {
    String JIBS_tmp_str[] = new String[10];
    String PGM_NAME = "BPZNQFLT";
    String APP = "BP";
    String PARM_NRULE = "NRULE";
    String CALL_BPZBPCD = "BP-BRW-PUB-CODE     ";
    String R_FEE_BPZPRMM = "BP-PARM-MAINTAIN    ";
    BPZNQFLT_WS_VARIABLES WS_VARIABLES = new BPZNQFLT_WS_VARIABLES();
    BPZNQFLT_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZNQFLT_WS_OUTPUT_DATA();
    BPZNQFLT_WS_COND_FLG WS_COND_FLG = new BPZNQFLT_WS_COND_FLG();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGSCA_SC_AREA SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRRULE BPRRULE = new BPRRULE();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCNRULE BPCNRULE = new BPCNRULE();
    SCCFMT SCCFMT = new SCCFMT();
    BPCBPCD BPCBPCD = new BPCBPCD();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCGWA SCCGWA;
    BPCNQFLT BPCNQFLT;
    public void MP(SCCGWA SCCGWA, BPCNQFLT BPCNQFLT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCNQFLT = BPCNQFLT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZNQFLT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        IBS.init(SCCGWA, BPCBPCD);
        IBS.init(SCCGWA, BPRRULE);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPCNRULE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (BPCNQFLT.FUNC == 'A') {
            B020_ADD_NRULE();
        } else if (BPCNQFLT.FUNC == 'M') {
            B030_MODIFY_NRULE();
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCNQFLT.FUNC != 'A' 
            && BPCNQFLT.FUNC != 'M') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (BPCNQFLT.FMT.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_RULE_OUTPUT_FMT_VAN;
            S000_ERR_MSG_PROC();
        }
        if (BPCNQFLT.FUNC == 'M' 
            && (BPCNQFLT.TYP.trim().length() == 0 
            || BPCNQFLT.CD.trim().length() == 0)) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_RULE_TYP_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_ADD_NRULE() throws IOException,SQLException,Exception {
        T000_BROWSE_BPZBPCD();
        T001_INQUIRE_BPZBPCD();
        S000_TRANS_DATA_OUT();
    }
    public void B030_MODIFY_NRULE() throws IOException,SQLException,Exception {
        T000_GET_NFLT_INFO();
        BPCBPCD.INPUT_DAT.TYPE = "FLT-D";
        BPCBPCD.INPUT_DAT.FUNC = 'B';
        S000_CALL_BPZBPCD();
        WS_VARIABLES.CNT = 1;
        while (BPCBPCD.OUTPUT_DAT.RTN_INF != 'N' 
            && WS_VARIABLES.CNT <= 10) {
            BPCBPCD.INPUT_DAT.FUNC = 'N';
            S000_CALL_BPZBPCD();
            CEP.TRC(SCCGWA, BPCBPCD.OUTPUT_DAT.RTN_INF);
            if (BPCBPCD.OUTPUT_DAT.RTN_INF == 'Y') {
                WS_COND_FLG.FLG = 'G';
                WS_VARIABLES.CNT1 = 1;
                CEP.TRC(SCCGWA, BPCNQFLT.DATA.DAT_TXT[WS_VARIABLES.CNT1-1].FLT_ITEM);
                CEP.TRC(SCCGWA, BPCBPCD.OUTPUT_DAT.CODE);
                while (WS_COND_FLG.FLG != 'E' 
                    && WS_VARIABLES.CNT1 <= 10) {
                    CEP.TRC(SCCGWA, BPCNQFLT.DATA.DAT_TXT[WS_VARIABLES.CNT1-1].FLT_ITEM);
                    CEP.TRC(SCCGWA, BPCBPCD.OUTPUT_DAT.CODE);
                    if (BPCNQFLT.DATA.DAT_TXT[WS_VARIABLES.CNT1-1].FLT_ITEM.equalsIgnoreCase(BPCBPCD.OUTPUT_DAT.CODE)) {
                        WS_OUTPUT_DATA.WS_BPCD_DAT[WS_VARIABLES.CNT-1].USE_FLG = BPCNQFLT.DATA.DAT_TXT[WS_VARIABLES.CNT1-1].USE_FLG;
                        WS_OUTPUT_DATA.WS_BPCD_DAT[WS_VARIABLES.CNT-1].NOR_RULE = BPCNQFLT.DATA.DAT_TXT[WS_VARIABLES.CNT1-1].NOR_RULE;
                        WS_OUTPUT_DATA.WS_BPCD_DAT[WS_VARIABLES.CNT-1].TO_ITEM1 = BPCNQFLT.DATA.DAT_TXT[WS_VARIABLES.CNT1-1].TO_ITEM1;
                        WS_OUTPUT_DATA.WS_BPCD_DAT[WS_VARIABLES.CNT-1].SPC_RULE = BPCNQFLT.DATA.DAT_TXT[WS_VARIABLES.CNT1-1].SPC_RULE;
                        WS_OUTPUT_DATA.WS_BPCD_DAT[WS_VARIABLES.CNT-1].TO_ITEM2 = BPCNQFLT.DATA.DAT_TXT[WS_VARIABLES.CNT1-1].TO_ITEM2;
                        WS_COND_FLG.FLG = 'E';
                    }
                    WS_VARIABLES.CNT1 = (short) (WS_VARIABLES.CNT1 + 1);
                }
                WS_OUTPUT_DATA.WS_BPCD_DAT[WS_VARIABLES.CNT-1].BPCD_CODE = BPCBPCD.OUTPUT_DAT.CODE;
                WS_VARIABLES.CNT = (short) (WS_VARIABLES.CNT + 1);
            }
        }
        BPCBPCD.INPUT_DAT.FUNC = 'E';
        S000_CALL_BPZBPCD();
        S000_TRANS_DATA_OUT();
    }
    public void T000_BROWSE_BPZBPCD() throws IOException,SQLException,Exception {
        BPCBPCD.INPUT_DAT.TYPE = "FLT-D";
        BPCBPCD.INPUT_DAT.FUNC = 'B';
        S000_CALL_BPZBPCD();
        WS_VARIABLES.CNT = 1;
        while (BPCBPCD.OUTPUT_DAT.RTN_INF != 'N' 
            && WS_VARIABLES.CNT <= 10) {
            BPCBPCD.INPUT_DAT.FUNC = 'N';
            S000_CALL_BPZBPCD();
            if (BPCBPCD.OUTPUT_DAT.RTN_INF == 'Y') {
                WS_OUTPUT_DATA.WS_BPCD_DAT[WS_VARIABLES.CNT-1].BPCD_CODE = BPCBPCD.OUTPUT_DAT.CODE;
                CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_BPCD_DAT[WS_VARIABLES.CNT-1].BPCD_CODE);
                WS_VARIABLES.CNT = (short) (WS_VARIABLES.CNT + 1);
            }
        }
        BPCBPCD.INPUT_DAT.FUNC = 'E';
        S000_CALL_BPZBPCD();
    }
    public void T001_INQUIRE_BPZBPCD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = "FLT-T";
        CEP.TRC(SCCGWA, BPCNQFLT.CD);
        BPCOQPCD.INPUT_DATA.CODE = BPCNQFLT.CD;
        S000_CALL_BPZPQPCD();
        CEP.TRC(SCCGWA, BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME);
        CEP.TRC(SCCGWA, BPCOQPCD.OUTPUT_DATA.CODE_INFO.ENG_NAME);
        WS_OUTPUT_DATA.NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
        WS_OUTPUT_DATA.ENG_NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.ENG_NAME;
    }
    public void T000_GET_NFLT_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCNQFLT.TYP);
        CEP.TRC(SCCGWA, BPCNQFLT.CD);
        BPRRULE.KEY.TYP = BPCNQFLT.TYP;
        BPRRULE.KEY.CD = BPCNQFLT.CD;
        CEP.TRC(SCCGWA, BPRRULE.KEY.CD);
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRRULE.DAT_LEN = 4500;
        BPRRULE.DAT_TXT.FILLER = IBS.CLS2CPY(SCCGWA, BPCNRULE.DATA);
        BPCPRMM.DAT_PTR = BPRRULE;
        BPCPRMM.FUNC = '3';
        S000_CALL_BPZPRMM();
        CEP.TRC(SCCGWA, BPRRULE.DESC);
        CEP.TRC(SCCGWA, BPRRULE.CDESC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRRULE.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCNRULE.DATA);
        S000_TRANS_DAT_BPCNQFLT();
    }
    public void S000_TRANS_DAT_BPCNQFLT() throws IOException,SQLException,Exception {
        BPCNQFLT.TYP = BPRRULE.KEY.TYP;
        BPCNQFLT.CD = BPRRULE.KEY.CD;
        WS_OUTPUT_DATA.ENG_NAME = BPRRULE.DESC;
        WS_OUTPUT_DATA.NAME = BPRRULE.CDESC;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCNRULE.DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCNQFLT.DATA);
    }
    public void S000_TRANS_DATA_OUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.NAME);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.ENG_NAME);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCNQFLT.FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT_DATA;
        SCCFMT.DATA_LEN = 570;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZBPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CALL_BPZBPCD, BPCBPCD);
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PC", BPCOQPCD);
        CEP.TRC(SCCGWA, BPCOQPCD.RC);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, R_FEE_BPZPRMM, BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        R000_CHECK_RETURNCODE();
    }
    public void R000_CHECK_RETURNCODE() throws IOException,SQLException,Exception {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCBPCD.OUTPUT_DAT.RTN_INF);
        if (JIBS_tmp_str[0].equalsIgnoreCase(ERROR_MSG.BP_NORMAL)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCBPCD.OUTPUT_DAT.RTN_INF);
        } else if (JIBS_tmp_str[0].equalsIgnoreCase(ERROR_MSG.BP_PARM_NOTFND)) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCBPCD.OUTPUT_DAT.RTN_INF);
        } else if (JIBS_tmp_str[0].equalsIgnoreCase(ERROR_MSG.BP_PARM_INV_EFF_DT)) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
        } else {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
