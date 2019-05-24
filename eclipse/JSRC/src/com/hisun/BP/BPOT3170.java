package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICUUPIA;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICQACAC;
import com.hisun.DD.DDCUCRAC;
import com.hisun.DD.DDCUDRAC;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPOT3170 {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP170";
    String CPN_F_F_TX_INFO = "BP-F-F-TX-INFO      ";
    String CPN_S_BV_SELL = "BP-S-BV-SELL-OUT ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_UNIT_SIGN_PROC = "DD-UNIT-SIGN-PROC   ";
    String CPN_S_BV_USE_OUT = "BP-S-BV-USE-OUT ";
    String WK_PGM_DDZUDRAC = "DD-UNIT-DRAW-PROC   ";
    String WK_PGM_DDZUCRAC = "DD-UNIT-DEP-PROC    ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_BVNO_LEN = 0;
    long WS_COMP_BEGNO = 0;
    long WS_COMP_ENDNO = 0;
    long WS_BV_NO = 0;
    String WS_CI_NO = " ";
    double WS_TOTAL_AMT = 0;
    String WS_AC = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    CICQACAC CICQACAC = new CICQACAC();
    AICUUPIA AICUUPIA = new AICUUPIA();
    BPCSBVSO BPCSBVSO = new BPCSBVSO();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCSBVUO BPCSBVUO = new BPCSBVUO();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCTCALF BPCTCALF = new BPCTCALF();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    CICACCU CICACCU = new CICACCU();
    CICCUST CICCUST = new CICCUST();
    BPCO170 BPCO170 = new BPCO170();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3170_AWA_3170 BPB3170_AWA_3170;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT3170 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3170_AWA_3170>");
        BPB3170_AWA_3170 = (BPB3170_AWA_3170) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_CH();
        if (pgmRtn) return;
        B020_BV_PAY_OUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.COST_PRICE);
        if (BPCFBVQU.TX_DATA.COST_PRICE != 0) {
            B040_CALL_DD_DRAC();
            if (pgmRtn) return;
            B070_CALL_AI_UPIA();
            if (pgmRtn) return;
        } else {
        }
        B060_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_CH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3170_AWA_3170.AGR_NO);
        CEP.TRC(SCCGWA, BPB3170_AWA_3170.BV_CODE);
        CEP.TRC(SCCGWA, BPB3170_AWA_3170.BEG_NO);
        CEP.TRC(SCCGWA, BPB3170_AWA_3170.END_NO);
        CEP.TRC(SCCGWA, BPB3170_AWA_3170.NUM);
        CEP.TRC(SCCGWA, BPB3170_AWA_3170);
        if (BPB3170_AWA_3170.BV_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_REC;
            if (BPB3170_AWA_3170.BV_CODE.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BPB3170_AWA_3170.BV_CODE);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPB3170_AWA_3170.BV_CODE;
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.KEY.CODE);
        S000_CALL_BPZFBVQU();
        if (pgmRtn) return;
        if (BPB3170_AWA_3170.BEG_NO.trim().length() > 0) {
            if (BPB3170_AWA_3170.BEG_NO == null) BPB3170_AWA_3170.BEG_NO = "";
            JIBS_tmp_int = BPB3170_AWA_3170.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3170_AWA_3170.BEG_NO += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPB3170_AWA_3170.BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
            CEP.TRC(SCCGWA, WS_I);
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                WS_FLD_NO = BPB3170_AWA_3170.BEG_NO_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPB3170_AWA_3170.END_NO.trim().length() > 0) {
            if (BPB3170_AWA_3170.END_NO == null) BPB3170_AWA_3170.END_NO = "";
            JIBS_tmp_int = BPB3170_AWA_3170.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3170_AWA_3170.END_NO += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPB3170_AWA_3170.END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
            CEP.TRC(SCCGWA, WS_I);
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                WS_FLD_NO = BPB3170_AWA_3170.END_NO_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
        if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
            if (BPB3170_AWA_3170.HEAD_NO.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_HEADNO;
                WS_FLD_NO = BPB3170_AWA_3170.HEAD_NO_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPB3170_AWA_3170.BEG_NO.trim().length() > 0 
                || BPB3170_AWA_3170.END_NO.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_BEGEND_NO;
                WS_FLD_NO = BPB3170_AWA_3170.BEG_NO_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
            || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
            CEP.TRC(SCCGWA, BPB3170_AWA_3170.BEG_NO);
            CEP.TRC(SCCGWA, BPB3170_AWA_3170.END_NO);
            CEP.TRC(SCCGWA, WS_BVNO_LEN);
            if (BPB3170_AWA_3170.BEG_NO == null) BPB3170_AWA_3170.BEG_NO = "";
            JIBS_tmp_int = BPB3170_AWA_3170.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3170_AWA_3170.BEG_NO += " ";
            if (BPB3170_AWA_3170.BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
            else WS_COMP_BEGNO = Long.parseLong(BPB3170_AWA_3170.BEG_NO.substring(0, WS_BVNO_LEN));
            if (BPB3170_AWA_3170.END_NO == null) BPB3170_AWA_3170.END_NO = "";
            JIBS_tmp_int = BPB3170_AWA_3170.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3170_AWA_3170.END_NO += " ";
            if (BPB3170_AWA_3170.END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
            else WS_COMP_ENDNO = Long.parseLong(BPB3170_AWA_3170.END_NO.substring(0, WS_BVNO_LEN));
            if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                WS_FLD_NO = BPB3170_AWA_3170.BEG_NO_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (WS_COMP_BEGNO == 0 
                || WS_COMP_ENDNO == 0 
                || BPB3170_AWA_3170.NUM != WS_COMP_ENDNO - WS_COMP_BEGNO + 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END_NUM;
                WS_FLD_NO = BPB3170_AWA_3170.NUM_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.COST_PRICE);
        WS_TOTAL_AMT = BPCFBVQU.TX_DATA.COST_PRICE * BPB3170_AWA_3170.NUM;
    }
    public void B020_BV_PAY_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBVUO);
        BPCSBVUO.PAY_TYP = '0';
        BPCSBVUO.BV_CODE = BPB3170_AWA_3170.BV_CODE;
        BPCSBVUO.HEAD_NO = BPB3170_AWA_3170.HEAD_NO;
        BPCSBVUO.BEG_NO = BPB3170_AWA_3170.BEG_NO;
        BPCSBVUO.END_NO = BPB3170_AWA_3170.END_NO;
        BPCSBVUO.NUM = BPB3170_AWA_3170.NUM;
        S000_CALL_BPZSBVUO();
        if (pgmRtn) return;
    }
    public void B030_CALL_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = BPB3170_AWA_3170.AGR_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        WS_CI_NO = CICACCU.DATA.CI_NO;
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'T';
        BPCTCALF.INPUT_AREA.TX_AC = BPB3170_AWA_3170.AGR_NO;
        BPCTCALF.INPUT_AREA.TX_CI = WS_CI_NO;
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].ADJ_AMT);
    }
    public void B040_CALL_DD_DRAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.AC = BPB3170_AWA_3170.AGR_NO;
        DDCUDRAC.CCY = "156";
        DDCUDRAC.CCY_TYPE = '1';
        DDCUDRAC.TX_AMT = WS_TOTAL_AMT;
        DDCUDRAC.TX_MMO = "A019";
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
    }
    public void B050_CALL_DD_CRAR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.AC = BPB3170_AWA_3170.AC;
        DDCUCRAC.CCY = "156";
        DDCUCRAC.CCY_TYPE = '1';
        DDCUCRAC.TX_AMT = WS_TOTAL_AMT;
        DDCUCRAC.OTHER_AC = BPB3170_AWA_3170.AGR_NO;
        DDCUCRAC.TX_MMO = "A001";
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void B070_CALL_AI_UPIA() throws IOException,SQLException,Exception {
        B080_GET_CI_INFO();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = BPB3170_AWA_3170.AC;
        AICUUPIA.DATA.PAY_MAN = CICCUST.O_DATA.O_CI_NM;
        AICUUPIA.DATA.CCY = "156";
        AICUUPIA.DATA.AMT = WS_TOTAL_AMT;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        CEP.TRC(SCCGWA, "NCB0601006");
        CEP.TRC(SCCGWA, AICUUPIA.DATA.AC_NO);
        CEP.TRC(SCCGWA, AICUUPIA.DATA.CCY);
        CEP.TRC(SCCGWA, AICUUPIA.DATA.AMT);
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void B080_GET_CI_INFO() throws IOException,SQLException,Exception {
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = BPB3170_AWA_3170.AGR_NO;
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        CEP.TRC(SCCGWA, "NCB0908001");
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        CEP.TRC(SCCGWA, AICUUPIA.RC.RC_CODE);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void GET_ACAC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'C';
        CICQACAC.DATA.AGR_NO = BPB3170_AWA_3170.AGR_NO;
        CICQACAC.DATA.CCY_ACAC = "156";
        CICQACAC.DATA.CR_FLG = '1';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        WS_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B060_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R020_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO170;
