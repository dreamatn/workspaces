package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5151 {
    String CPN_S_DEGR = "BP-DEF-EXR-GEN-RULE";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String K_FWD_TENOR = "FWDT ";
    int WS_I = 0;
    String WS_CCY = " ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_DATA_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPREXRT BPREXRT = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCDEGR BPCDEGR = new BPCDEGR();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPREXRD BPREXRD = new BPREXRD();
    BPCTEXRM BPCTEXRM = new BPCTEXRM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5150_AWA_5150 BPB5150_AWA_5150;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5151 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5150_AWA_5150>");
        BPB5150_AWA_5150 = (BPB5150_AWA_5150) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_DATA_FLG = 'S';
        for (WS_I = 1; WS_I <= 20; WS_I += 1) {
            if (BPB5150_AWA_5150.RULE_DAT[WS_I-1].EXR_TYPE.trim().length() == 0) {
                if (BPB5150_AWA_5150.RULE_DAT[WS_I-1].FWD_TENR.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
                    WS_FLD_NO = BPB5150_AWA_5150.RULE_DAT[WS_I-1].FWD_TENR_NO;
                    S000_ERR_MSG_PROC();
                }
                if (BPB5150_AWA_5150.RULE_DAT[WS_I-1].ITP_IND != ' ') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
                    WS_FLD_NO = BPB5150_AWA_5150.RULE_DAT[WS_I-1].ITP_IND_NO;
                    S000_ERR_MSG_PROC();
                }
                if (BPB5150_AWA_5150.RULE_DAT[WS_I-1].SPRD_MTH != ' ') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
                    WS_FLD_NO = BPB5150_AWA_5150.RULE_DAT[WS_I-1].SPRD_MTH_NO;
                    S000_ERR_MSG_PROC();
                }
                if (BPB5150_AWA_5150.RULE_DAT[WS_I-1].CBAS_RAT.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
                    WS_FLD_NO = BPB5150_AWA_5150.RULE_DAT[WS_I-1].CBAS_RAT_NO;
                    S000_ERR_MSG_PROC();
                }
                if (BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_RAT.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
                    WS_FLD_NO = BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_RAT_NO;
                    S000_ERR_MSG_PROC();
                }
                if (BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_CCY.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
                    WS_FLD_NO = BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_CCY_NO;
                    S000_ERR_MSG_PROC();
                }
                if (BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_TNR.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
                    WS_FLD_NO = BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_TNR_NO;
                    S000_ERR_MSG_PROC();
                }
                if (BPB5150_AWA_5150.RULE_DAT[WS_I-1].COMP_MTH != ' ') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
                    WS_FLD_NO = BPB5150_AWA_5150.RULE_DAT[WS_I-1].COMP_MTH_NO;
                    S000_ERR_MSG_PROC();
                }
                if (BPB5150_AWA_5150.RULE_DAT[WS_I-1].SPRD_VAL != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
                    WS_FLD_NO = BPB5150_AWA_5150.RULE_DAT[WS_I-1].SPRD_VAL_NO;
                    S000_ERR_MSG_PROC();
                }
            } else {
                if (BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_RAT.trim().length() > 0) {
                    IBS.init(SCCGWA, BPREXRT);
                    IBS.init(SCCGWA, BPCPRMR);
                    BPREXRT.KEY.TYP = "EXRT";
                    BPREXRT.KEY.CD = BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_RAT;
                    BPCPRMR.DAT_PTR = BPREXRT;
                    S000_CALL_BPZPRMR();
                    if (BPCPRMR.RC.RC_RTNCODE != 0) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INVALID_EXR_TYP;
                        WS_FLD_NO = BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_RAT_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
                if (BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_TNR.trim().length() > 0) {
                    IBS.init(SCCGWA, BPCOQPCD);
                    BPCOQPCD.INPUT_DATA.TYPE = K_FWD_TENOR;
                    BPCOQPCD.INPUT_DATA.CODE = BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_TNR;
                    S000_CALL_BPZQPCD();
                    if (BPCOQPCD.RC.RC_CODE != 0) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INVALID_TENOR;
                        WS_FLD_NO = BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_TNR_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
                if (BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_CCY.trim().length() > 0) {
                    IBS.init(SCCGWA, BPCQCCY);
                    BPCQCCY.DATA.CCY = BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_CCY;
                    S000_CALL_BPZQCCY();
                    if (BPCQCCY.RC.RTNCODE != 0) {
                        WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
                        WS_FLD_NO = BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_CCY_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
                if (BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_RAT.trim().length() > 0 
                    || BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_CCY.trim().length() > 0 
                    || BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_TNR.trim().length() > 0) {
                    IBS.init(SCCGWA, BPREXRD);
                    IBS.init(SCCGWA, BPCTEXRM);
                    BPREXRD.KEY.EXR_TYP = BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_RAT;
                    BPREXRD.KEY.FWD_TENOR = BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_TNR;
                    BPREXRD.KEY.CCY = BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_CCY;
                    CEP.TRC(SCCGWA, BPREXRD.KEY);
                    BPCTEXRM.INFO.FUNC = 'Q';
                    S000_CALL_BPZTEXRM();
                    if (BPCTEXRM.RETURN_INFO == 'N') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EX_RATE_UNDEFINE;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
            CEP.TRC(SCCGWA, WS_DATA_FLG);
            if (BPB5150_AWA_5150.RULE_DAT[WS_I-1].EXR_TYPE.trim().length() == 0) {
                WS_DATA_FLG = 'E';
            } else {
                if (WS_DATA_FLG == 'E' 
                    && BPB5150_AWA_5150.RULE_DAT[WS_I-1].EXR_TYPE.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
                    WS_FLD_NO = BPB5150_AWA_5150.RULE_DAT[WS_I-1].EXR_TYPE_NO;
                    S000_ERR_MSG_PROC();
                }
            }
        }
    }
    public void B020_ADD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCDEGR);
        BPCDEGR.FUNC = 'A';
        CEP.TRC(SCCGWA, BPB5150_AWA_5150.CCY);
        CEP.TRC(SCCGWA, BPB5150_AWA_5150);
        BPCDEGR.CCY = BPB5150_AWA_5150.CCY;
        WS_I = 0;
        for (WS_I = 1; WS_I <= 20 
            && BPB5150_AWA_5150.RULE_DAT[WS_I-1].EXR_TYPE.trim().length() != 0; WS_I += 1) {
            CEP.TRC(SCCGWA, BPB5150_AWA_5150.RULE_DAT[WS_I-1].SPRD_VAL);
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPB5150_AWA_5150.RULE_DAT[WS_I-1].SEQ);
            CEP.TRC(SCCGWA, BPB5150_AWA_5150.RULE_DAT[WS_I-1].FWD_TENR);
            CEP.TRC(SCCGWA, BPB5150_AWA_5150.RULE_DAT[WS_I-1].ITP_IND);
            CEP.TRC(SCCGWA, BPB5150_AWA_5150.RULE_DAT[WS_I-1].SPRD_MTH);
            CEP.TRC(SCCGWA, BPB5150_AWA_5150.RULE_DAT[WS_I-1].CBAS_RAT);
            CEP.TRC(SCCGWA, BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_RAT);
            CEP.TRC(SCCGWA, BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_CCY);
            CEP.TRC(SCCGWA, BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_TNR);
            CEP.TRC(SCCGWA, BPB5150_AWA_5150.RULE_DAT[WS_I-1].COMP_MTH);
            CEP.TRC(SCCGWA, BPB5150_AWA_5150.RULE_DAT[WS_I-1].SPRD_VAL);
            BPCDEGR.RULE_DATA.DATA[WS_I-1].SEQ = BPB5150_AWA_5150.RULE_DAT[WS_I-1].SEQ;
            BPCDEGR.RULE_DATA.DATA[WS_I-1].EXR_TYPE = BPB5150_AWA_5150.RULE_DAT[WS_I-1].EXR_TYPE;
            BPCDEGR.RULE_DATA.DATA[WS_I-1].FWD_TENR = BPB5150_AWA_5150.RULE_DAT[WS_I-1].FWD_TENR;
            BPCDEGR.RULE_DATA.DATA[WS_I-1].ITP_IND = BPB5150_AWA_5150.RULE_DAT[WS_I-1].ITP_IND;
            BPCDEGR.RULE_DATA.DATA[WS_I-1].SPRD_MTH = BPB5150_AWA_5150.RULE_DAT[WS_I-1].SPRD_MTH;
            BPCDEGR.RULE_DATA.DATA[WS_I-1].CBAS_RAT = BPB5150_AWA_5150.RULE_DAT[WS_I-1].CBAS_RAT;
            BPCDEGR.RULE_DATA.DATA[WS_I-1].BASE_RAT = BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_RAT;
            BPCDEGR.RULE_DATA.DATA[WS_I-1].BASE_CCY = BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_CCY;
            BPCDEGR.RULE_DATA.DATA[WS_I-1].BASE_TNR = BPB5150_AWA_5150.RULE_DAT[WS_I-1].BASE_TNR;
            BPCDEGR.RULE_DATA.DATA[WS_I-1].COMP_MTH = BPB5150_AWA_5150.RULE_DAT[WS_I-1].COMP_MTH;
            BPCDEGR.RULE_DATA.DATA[WS_I-1].SPRD_VAL = BPB5150_AWA_5150.RULE_DAT[WS_I-1].SPRD_VAL;
        }
        S00_CALL_BPZDEGR();
    }
    public void S00_CALL_BPZDEGR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_DEGR, BPCDEGR);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
    }
    public void S000_CALL_BPZQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PC", BPCOQPCD);
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
    }
    public void S000_CALL_BPZTEXRM() throws IOException,SQLException,Exception {
        BPCTEXRM.POINTER = BPREXRD;
        BPCTEXRM.LEN = 259;
        IBS.CALLCPN(SCCGWA, "BP-R-EXRD-M", BPCTEXRM);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
