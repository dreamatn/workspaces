package com.hisun.DD;

import com.hisun.BP.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.TD.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1590 {
    int JIBS_tmp_int;
    DBParm TDTBVT_RD;
    DBParm DDTCCY_RD;
    String K_OUTPUT_FMT = "DD159";
    String CPN_S_BV_USE_OUT = "BP-S-BV-USE-OUT ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_BVNO_LEN = 0;
    long WS_COMP_BEGNO = 0;
    long WS_COMP_ENDNO = 0;
    String WS_REL_AC = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    TDRBVT TDRBVT = new TDRBVT();
    DDRCCY DDRCCY = new DDRCCY();
    TDCACE TDCACE = new TDCACE();
    CICQACRL CICQACRL = new CICQACRL();
    CICCUST CICCUST = new CICCUST();
    BPCSBVUO BPCSBVUO = new BPCSBVUO();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCUIQMC DCCUIQMC = new DCCUIQMC();
    DCCIMSTR DCCIMSTR = new DCCIMSTR();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    CICACCU CICACCU = new CICACCU();
    BPCTCALF BPCTCALF = new BPCTCALF();
    DDCO1590 DDCO1590 = new DDCO1590();
    SCCFMT SCCFMT = new SCCFMT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DDB1590_AWA_1590 DDB1590_AWA_1590;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT1590 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1590_AWA_1590>");
        DDB1590_AWA_1590 = (DDB1590_AWA_1590) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BV_PAY_OUT();
        B040_NFIN_HIS_PROC();
        R000_FEE_PROC();
        B030_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1590_AWA_1590.BV_CODE);
        CEP.TRC(SCCGWA, DDB1590_AWA_1590.END_NO);
        if (DDB1590_AWA_1590.CT_FLG == '0') {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = DDB1590_AWA_1590.TR_AC_NO;
            S000_CALL_CIZACCU();
            if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
            JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
            if (CICACCU.DATA.CI_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CUST_F_JUS_FF;
                S000_ERR_MSG_PROC_A();
            }
            CEP.TRC(SCCGWA, CICACCU.DATA.FRM_APP);
            if (CICACCU.DATA.FRM_APP.equalsIgnoreCase("DC") 
                || CICACCU.DATA.FRM_APP.equalsIgnoreCase("DD")) {
                if (CICACCU.DATA.FRM_APP.equalsIgnoreCase("DC")) {
                    IBS.init(SCCGWA, CICQACRL);
                    CICQACRL.DATA.AC_NO = DDB1590_AWA_1590.TR_AC_NO;
                    CICQACRL.DATA.AC_REL = "04";
                    CICQACRL.FUNC = 'I';
                    S000_CALL_CIZQACRL();
                    CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
                    WS_REL_AC = CICQACRL.O_DATA.O_REL_AC_NO;
                }
                if (CICACCU.DATA.FRM_APP.equalsIgnoreCase("DD")) {
                    WS_REL_AC = DDB1590_AWA_1590.TR_AC_NO;
                }
                CEP.TRC(SCCGWA, WS_REL_AC);
                IBS.init(SCCGWA, DDRCCY);
                DDRCCY.CUS_AC = WS_REL_AC;
                T000_READ_DDTCCY();
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_IS_LONG_HOVER;
                    S000_ERR_MSG_PROC_A();
                }
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_FREEZE_CNOT_TR;
                    S000_ERR_MSG_PROC_A();
                }
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_FREEZE_CNOT_TR;
                    S000_ERR_MSG_PROC_A();
                }
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.BK_INTER_TEMP_FORBID;
                    S000_ERR_MSG_PROC_A();
                }
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_OFFICE_FORBID;
                    S000_ERR_MSG_PROC_A();
                }
            }
            if (CICACCU.DATA.FRM_APP.equalsIgnoreCase("TD")) {
                IBS.init(SCCGWA, TDCACE);
                TDCACE.PAGE_INF.AC_NO = DDB1590_AWA_1590.TR_AC_NO;
                S000_CALL_TDZACE();
                if (TDCACE.DATA[1-1].ACO_STSW == null) TDCACE.DATA[1-1].ACO_STSW = "";
                JIBS_tmp_int = TDCACE.DATA[1-1].ACO_STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].ACO_STSW += " ";
                if (TDCACE.DATA[1-1].ACO_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_FREEZE_CNOT_TR;
                    S000_ERR_MSG_PROC_A();
                }
                if (TDCACE.DATA[1-1].ACO_STSW == null) TDCACE.DATA[1-1].ACO_STSW = "";
                JIBS_tmp_int = TDCACE.DATA[1-1].ACO_STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].ACO_STSW += " ";
                if (TDCACE.DATA[1-1].ACO_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_FREEZE_CNOT_TR;
                    S000_ERR_MSG_PROC_A();
                }
                if (TDCACE.DATA[1-1].ACO_STSW == null) TDCACE.DATA[1-1].ACO_STSW = "";
                JIBS_tmp_int = TDCACE.DATA[1-1].ACO_STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].ACO_STSW += " ";
                if (TDCACE.DATA[1-1].ACO_STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.BK_INTER_TEMP_FORBID;
                    S000_ERR_MSG_PROC_A();
                }
                if (TDCACE.DATA[1-1].ACO_STSW == null) TDCACE.DATA[1-1].ACO_STSW = "";
                JIBS_tmp_int = TDCACE.DATA[1-1].ACO_STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].ACO_STSW += " ";
                if (TDCACE.DATA[1-1].ACO_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_OFFICE_FORBID;
                    S000_ERR_MSG_PROC_A();
                }
            }
            if (DDB1590_AWA_1590.BV_CODE.trim().length() == 0) {
                DDB1590_AWA_1590.BV_CODE = "096";
            }
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = DDB1590_AWA_1590.BV_CODE;
            S000_CALL_BPZFBVQU();
            if (BPCFBVQU.TX_DATA.BV_CFLG == 'Y' 
                && BPCFBVQU.TX_DATA.TYPE != '3') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CARD_CANT_PUTT;
                if (DDB1590_AWA_1590.BV_CODE.trim().length() == 0) WS_FLD_NO = 0;
                else WS_FLD_NO = Short.parseShort(DDB1590_AWA_1590.BV_CODE);
                S000_ERR_MSG_PROC();
            }
            if (DDB1590_AWA_1590.BEG_NO.trim().length() > 0) {
                if (DDB1590_AWA_1590.BEG_NO == null) DDB1590_AWA_1590.BEG_NO = "";
                JIBS_tmp_int = DDB1590_AWA_1590.BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) DDB1590_AWA_1590.BEG_NO += " ";
                for (WS_I = 1; WS_I <= 20 
                    && IBS.isNumeric(DDB1590_AWA_1590.BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
                }
                if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                    WS_FLD_NO = DDB1590_AWA_1590.BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            if (DDB1590_AWA_1590.END_NO.trim().length() > 0) {
                if (DDB1590_AWA_1590.END_NO == null) DDB1590_AWA_1590.END_NO = "";
                JIBS_tmp_int = DDB1590_AWA_1590.END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) DDB1590_AWA_1590.END_NO += " ";
                for (WS_I = 1; (WS_I <= 20) 
                    && DDB1590_AWA_1590.END_NO.substring(WS_I - 1, WS_I + 1 - 1).trim().length() != 0; WS_I += 1) {
                }
                if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                    WS_FLD_NO = DDB1590_AWA_1590.END_NO_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
            if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
                if (DDB1590_AWA_1590.HEAD_NO.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_HEADNO;
                    WS_FLD_NO = DDB1590_AWA_1590.HEAD_NO_NO;
                    S000_ERR_MSG_PROC();
                }
                if (DDB1590_AWA_1590.BEG_NO.trim().length() > 0 
                    || DDB1590_AWA_1590.END_NO.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_BEGEND_NO;
                    WS_FLD_NO = DDB1590_AWA_1590.BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
                || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
                if (DDB1590_AWA_1590.BEG_NO == null) DDB1590_AWA_1590.BEG_NO = "";
                JIBS_tmp_int = DDB1590_AWA_1590.BEG_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) DDB1590_AWA_1590.BEG_NO += " ";
                if (DDB1590_AWA_1590.BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
                else WS_COMP_BEGNO = Long.parseLong(DDB1590_AWA_1590.BEG_NO.substring(0, WS_BVNO_LEN));
                if (DDB1590_AWA_1590.END_NO == null) DDB1590_AWA_1590.END_NO = "";
                JIBS_tmp_int = DDB1590_AWA_1590.END_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) DDB1590_AWA_1590.END_NO += " ";
                if (DDB1590_AWA_1590.END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
                else WS_COMP_ENDNO = Long.parseLong(DDB1590_AWA_1590.END_NO.substring(0, WS_BVNO_LEN));
                if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                    WS_FLD_NO = DDB1590_AWA_1590.BEG_NO_NO;
                    S000_ERR_MSG_PROC();
                }
                if (WS_COMP_BEGNO == 0 
                    || WS_COMP_ENDNO == 0 
                    || DDB1590_AWA_1590.NUM != WS_COMP_ENDNO - WS_COMP_BEGNO + 1) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END_NUM;
                    WS_FLD_NO = DDB1590_AWA_1590.NUM_NO;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        if (DDB1590_AWA_1590.CT_FLG == '1') {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NM = DDB1590_AWA_1590.DRW_NM;
            CICCUST.DATA.ID_TYPE = DDB1590_AWA_1590.DRW_IDT;
            CICCUST.DATA.ID_NO = DDB1590_AWA_1590.DRW_IDN;
            S000_CALL_CIZCUST();
        }
    }
    public void B020_BV_PAY_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBVUO);
        BPCSBVUO.PAY_TYP = DDB1590_AWA_1590.PAY_TYP;
        BPCSBVUO.BV_CODE = DDB1590_AWA_1590.BV_CODE;
        BPCSBVUO.BV_NAME = DDB1590_AWA_1590.BV_NAME;
        BPCSBVUO.HEAD_NO = DDB1590_AWA_1590.HEAD_NO;
        BPCSBVUO.BEG_NO = DDB1590_AWA_1590.BEG_NO;
        BPCSBVUO.END_NO = DDB1590_AWA_1590.END_NO;
        BPCSBVUO.NUM = DDB1590_AWA_1590.NUM;
        BPCSBVUO.REMARK = DDB1590_AWA_1590.REMARK;
        BPCSBVUO.DRW_NM = DDB1590_AWA_1590.DRW_NM;
        BPCSBVUO.DRW_IDT = DDB1590_AWA_1590.DRW_IDT;
        BPCSBVUO.DRW_IDN = DDB1590_AWA_1590.DRW_IDN;
        S000_CALL_BPZSBVUO();
        DDB1590_AWA_1590.MOV_DT = BPCSBVUO.MOV_DT;
        DDB1590_AWA_1590.CONF_NO = BPCSBVUO.CONF_NO;
        DDB1590_AWA_1590.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDB1590_AWA_1590.TR_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDCO1590.PAY_TYP = DDB1590_AWA_1590.PAY_TYP;
        DDCO1590.BV_CODE = BPCSBVUO.BV_CODE;
        DDCO1590.BV_NAME = BPCSBVUO.BV_NAME;
        DDCO1590.HEAD_NO = BPCSBVUO.HEAD_NO;
        DDCO1590.BEG_NO = DDB1590_AWA_1590.BEG_NO;
        DDCO1590.END_NO = DDB1590_AWA_1590.END_NO;
        DDCO1590.NUM = DDB1590_AWA_1590.NUM;
        DDCO1590.REMARK = BPCSBVUO.REMARK;
        DDCO1590.DRW_NM = BPCSBVUO.DRW_NM;
        DDCO1590.DRW_IDT = BPCSBVUO.DRW_IDT;
        DDCO1590.DRW_IDN = BPCSBVUO.DRW_IDN;
    }
    public void B040_NFIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        if (DDB1590_AWA_1590.BV_CODE.equalsIgnoreCase("096")) {
            BPCPNHIS.INFO.TX_TYP_CD = "P140";
            BPCPNHIS.INFO.TX_RMK = "企业资信证明�?�?";
        } else {
            if (DDB1590_AWA_1590.BV_CODE.equalsIgnoreCase("037")) {
                BPCPNHIS.INFO.TX_TYP_CD = "I001";
                BPCPNHIS.INFO.TX_RMK = "个人理财证明";
            }
        }
        if (CICACCU.DATA.FRM_APP.equalsIgnoreCase("DC") 
            || CICACCU.DATA.FRM_APP.equalsIgnoreCase("DD")) {
            if (CICACCU.DATA.FRM_APP.equalsIgnoreCase("DC")) {
                BPCPNHIS.INFO.TX_TOOL = DDB1590_AWA_1590.TR_AC_NO;
            }
            BPCPNHIS.INFO.AC = WS_REL_AC;
            BPCPNHIS.INFO.CCY = DDRCCY.CCY;
            BPCPNHIS.INFO.CCY_TYPE = DDRCCY.CCY_TYPE;
        } else {
            if (CICACCU.DATA.FRM_APP.equalsIgnoreCase("TD")) {
                BPCPNHIS.INFO.AC = DDB1590_AWA_1590.TR_AC_NO;
                BPCPNHIS.INFO.AC_SEQ = TDCACE.DATA[1-1].AC_SEQ;
                BPCPNHIS.INFO.CCY = TDCACE.DATA[1-1].CCY;
                BPCPNHIS.INFO.CCY_TYPE = TDCACE.DATA[1-1].CCY_TYP;
            }
        }
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        if (CICCUST.DATA.CI_NO.trim().length() == 0) {
            BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        } else {
            BPCPNHIS.INFO.CI_NO = CICCUST.DATA.CI_NO;
        }
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZPNHIS();
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCO1590;
        SCCFMT.DATA_LEN = 675;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZSBVUO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BV_USE_OUT, BPCSBVUO);
        if (BPCSBVUO.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSBVUO.RC);
            WS_FLD_NO = DDB1590_AWA_1590.BV_CODE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_A() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void R000_FEE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1590_AWA_1590.TR_AC_NO);
        if (DDB1590_AWA_1590.TR_AC_NO.trim().length() > 0) {
            CICACCU.DATA.AGR_NO = DDB1590_AWA_1590.TR_AC_NO;
            S000_CALL_CIZACCU();
        } else {
        }
        R000_SET_FEE_INFO();
        R000_CALL_FEE();
    }
    public void R000_SET_FEE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = DDB1590_AWA_1590.CT_FLG;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = DDB1590_AWA_1590.TR_AC_NO;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = "156";
        BPCFFTXI.TX_DATA.CCY_TYPE = '1';
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        BPCFFTXI.TX_DATA.CI_NO = CICACCU.DATA.CI_NO;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY);
        S000_CALL_BPZFFTXI();
    }
    public void R000_CALL_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.ATTR_VAL.BNK_FLG = '0';
        BPCTCALF.INPUT_AREA.ATTR_VAL.DC_FLG = '0';
        BPCTCALF.INPUT_AREA.BVF_CODE = TDRBVT.BV_CD;
        BPCTCALF.INPUT_AREA.TX_CCY = "156";
        BPCTCALF.INPUT_AREA.TX_CNT = (short) DDB1590_AWA_1590.NUM;
        if (DDB1590_AWA_1590.CT_FLG == '0') {
            BPCTCALF.INPUT_AREA.TX_AC = DDB1590_AWA_1590.TR_AC_NO;
            BPCTCALF.INPUT_AREA.TX_CI = CICACCU.DATA.CI_NO;
        }
        if (DDB1590_AWA_1590.CT_FLG == '1') {
            BPCTCALF.INPUT_AREA.TX_CI = CICCUST.DATA.CI_NO;
        }
        CEP.TRC(SCCGWA, BPCTCALF.RC);
        S000_CALL_BPZFCALF();
        CEP.TRC(SCCGWA, BPCTCALF.RC);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-TX-INFO      ", BPCFFTXI);
        CEP.TRC(SCCGWA, BPCFFTXI.RC.RC_CODE);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CAL-FEE      ", BPCTCALF);
        CEP.TRC(SCCGWA, BPCTCALF.RC);
        if (BPCTCALF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTCALF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO";
        TDTBVT_RD.upd = true;
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZACE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-ENQ", TDCACE, true);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
