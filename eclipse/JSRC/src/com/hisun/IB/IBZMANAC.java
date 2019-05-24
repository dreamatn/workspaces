package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.AI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZMANAC {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm IBTMANIP_RD;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    String K_OUTPUT_FMT = "IBA60";
    char K_APPROVE = 'A';
    char K_REJECT = 'R';
    char WS_OTH_AC_ATTR = ' ';
    int WS_OTH_NAME = 0;
    int WS_OTH_BR = 0;
    String WS_OTH_CORP = " ";
    String WS_CORP = " ";
    short WS_I = 0;
    char WS_TXNBR_FLAG = ' ';
    char WS_TABLE_REC = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    AICPQMIB AICPQMIB = new AICPQMIB();
    AICUUPIA AICUUPIA = new AICUUPIA();
    AICOCKOP AICOCKOP = new AICOCKOP();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    IBCOMNAC IBCOMNAC = new IBCOMNAC();
    IBCQINF IBCQINF = new IBCQINF();
    IBCPMORG IBCPMORG = new IBCPMORG();
    IBCQIDNM IBCQIDNM = new IBCQIDNM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    IBRMST IBRMST = new IBRMST();
    IBRMANIP IBRMANIP = new IBRMANIP();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    IBCMANAC IBCMANAC;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, IBCMANAC IBCMANAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCMANAC = IBCMANAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBZMANAC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        IBS.init(SCCGWA, SCCEXCP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT();
        if (IBCMANAC.FUNC == K_APPROVE) {
            B020_CHK_INPUT();
            B030_APPROVE_PROC();
        } else if (IBCMANAC.FUNC == K_REJECT) {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + IBCMANAC.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        B040_UPD_MANIP();
        B050_OUTPUT_PROC();
        CEP.TRC(SCCGWA, "NCB TEST END   ");
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NCB TEST START ");
        CEP.TRC(SCCGWA, IBCMANAC.FUNC);
        if (IBCMANAC.FUNC == ' ') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.FCN_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, IBCMANAC.AC_DATE);
        if (IBCMANAC.AC_DATE == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_DATE_M);
        }
        CEP.TRC(SCCGWA, IBCMANAC.JRN_NO);
        if (IBCMANAC.JRN_NO == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.JRNNO_M);
        }
        CEP.TRC(SCCGWA, IBCMANAC.IPT_TLR);
        if (IBCMANAC.IPT_TLR.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.IPT_TLR_M);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            if (SCCGWA.COMM_AREA.TL_ID.equalsIgnoreCase(IBCMANAC.IPT_TLR)) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SAME_TLR);
            }
        }
    }
    public void B020_CHK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCMANAC.AC_NO);
        CEP.TRC(SCCGWA, IBCMANAC.NOS_CD);
        if (IBCMANAC.NOS_CD.trim().length() == 0 
            && IBCMANAC.AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT);
        }
        CEP.TRC(SCCGWA, IBCMANAC.CCY);
        if (IBCMANAC.CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CCY_M);
        }
        CEP.TRC(SCCGWA, IBCMANAC.AMT);
        if (IBCMANAC.AMT == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.IB_AMT_M_INPUT);
        } else {
            if (IBCMANAC.AMT < 0) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.IB_AMT_INVALID);
            }
        }
        CEP.TRC(SCCGWA, IBCMANAC.SIGN);
        if (IBCMANAC.SIGN != 'C' 
            && IBCMANAC.SIGN != 'D') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.DRCR_SIGN_M);
        }
        B020_01_CHECK_OTH_AC();
        B020_02_CHECK_ACT_BR();
        B020_03_CHECK_VAL_DT();
        B020_04_GET_AC_INFO();
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            B020_06_CHECK_TXNBR();
        }
    }
    public void B020_01_CHECK_OTH_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCMANAC.OTH_AC_TYP);
        if (IBCMANAC.OTH_AC_TYP == ' ') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.OTH_AC_ATTR_M);
        }
        CEP.TRC(SCCGWA, IBCMANAC.OTH_AC_NO);
        if (IBCMANAC.OTH_AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.OTH_AC_NO_M);
        } else {
            if (IBCMANAC.OTH_AC_NO.equalsIgnoreCase(IBCMANAC.AC_NO)) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.DRCR_AC_SAME);
            }
            if (IBCMANAC.OTH_AC_TYP == 'N') {
                IBS.init(SCCGWA, IBCQINF);
                IBCQINF.INPUT_DATA.AC_NO = IBCMANAC.OTH_AC_NO;
                S000_CALL_IBZQINF();
                if (IBCQINF.OUTPUT_DATA.AC_STS != 'N') {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL);
                }
                if (!IBCMANAC.CCY.equalsIgnoreCase(IBCQINF.INPUT_DATA.CCY)) {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CCY);
                }
                WS_OTH_AC_ATTR = IBCQINF.OUTPUT_DATA.AC_ATTR;
                WS_OTH_BR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
                if (IBCQINF.OUTPUT_DATA.AC_CHN_NAME.trim().length() == 0) WS_OTH_NAME = 0;
                else WS_OTH_NAME = Integer.parseInt(IBCQINF.OUTPUT_DATA.AC_CHN_NAME);
                B020_06_CHECK_TXNBR();
            } else {
                IBS.init(SCCGWA, AICPQMIB);
                AICPQMIB.INPUT_DATA.GL_BOOK = "BK001";
                AICPQMIB.INPUT_DATA.AC = IBCMANAC.OTH_AC_NO;
                S000_CALL_AIZPQMIB();
                if (IBCMANAC.OTH_AC_NO == null) IBCMANAC.OTH_AC_NO = "";
                JIBS_tmp_int = IBCMANAC.OTH_AC_NO.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) IBCMANAC.OTH_AC_NO += " ";
                if (IBCMANAC.OTH_AC_NO.substring(0, 6).trim().length() == 0) WS_OTH_BR = 0;
                else WS_OTH_BR = Integer.parseInt(IBCMANAC.OTH_AC_NO.substring(0, 6));
                if (AICPQMIB.OUTPUT_DATA.CHS_NM.trim().length() == 0) WS_OTH_NAME = 0;
                else WS_OTH_NAME = Integer.parseInt(AICPQMIB.OUTPUT_DATA.CHS_NM);
            }
        }
    }
    public void B020_02_CHECK_ACT_BR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCMANAC.POST_CTR);
        if (IBCMANAC.POST_CTR == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.ACT_CTR);
        } else {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = IBCMANAC.POST_CTR;
            S000_CALL_BPZPQORG();
            if (BPCPQORG.ATTR != '2') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.ERR_BR_NOT_PQORG_ATTR);
            }
        }
    }
    public void B020_03_CHECK_VAL_DT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCMANAC.VAL_DATE);
        if (IBCMANAC.VAL_DATE == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.VAL_DATE_M);
        } else {
            IBS.init(SCCGWA, AICOCKOP);
            AICOCKOP.VAL_DATE = IBCMANAC.VAL_DATE;
            S000_CALL_AIZCKOP();
        }
    }
    public void B020_04_GET_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINF);
        if (IBCMANAC.AC_NO.trim().length() > 0) {
            IBCQINF.INPUT_DATA.AC_NO = IBCMANAC.AC_NO;
        } else {
            IBCQINF.INPUT_DATA.NOSTRO_CD = IBCMANAC.NOS_CD;
            IBCQINF.INPUT_DATA.CCY = IBCMANAC.CCY;
        }
        CEP.TRC(SCCGWA, IBCQINF.INPUT_DATA.AC_NO);
        CEP.TRC(SCCGWA, IBCQINF.INPUT_DATA.NOSTRO_CD);
        CEP.TRC(SCCGWA, IBCQINF.INPUT_DATA.CCY);
        S000_CALL_IBZQINF();
        if (IBCQINF.OUTPUT_DATA.AC_STS != 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL);
        }
        if (!IBCMANAC.CCY.equalsIgnoreCase(IBCQINF.INPUT_DATA.CCY)) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CCY);
        }
    }
    public void B020_05_CHECK_CORP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_OTH_AC_ATTR);
        CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.AC_ATTR);
        if (IBCQINF.OUTPUT_DATA.AC_ATTR == 'L' 
            || WS_OTH_AC_ATTR == 'L') {
            CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.POST_ACT_CTR);
            IBS.init(SCCGWA, IBCQIDNM);
            IBCQIDNM.BR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
            S000_CALL_IBZQIDNM();
            WS_CORP = IBCQIDNM.CORP_ID;
            CEP.TRC(SCCGWA, WS_OTH_BR);
            IBS.init(SCCGWA, IBCQIDNM);
            IBCQIDNM.BR = WS_OTH_BR;
            S000_CALL_IBZQIDNM();
            WS_OTH_CORP = IBCQIDNM.CORP_ID;
            if (!WS_CORP.equalsIgnoreCase(WS_OTH_CORP)) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_NOT_CORP);
            }
        }
    }
    public void B020_06_CHECK_TXNBR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
        S000_CALL_BPZPQORG();
        if (IBCQINF.OUTPUT_DATA.SWR_BR == '0') {
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != IBCQINF.OUTPUT_DATA.POST_ACT_CTR) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR);
            }
        } else if (IBCQINF.OUTPUT_DATA.SWR_BR == '1') {
            if (BPCPORUP.DATA_INFO.TYP == null) BPCPORUP.DATA_INFO.TYP = "";
            JIBS_tmp_int = BPCPORUP.DATA_INFO.TYP.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) BPCPORUP.DATA_INFO.TYP += " ";
            if (BPCPQORG.TYP == null) BPCPQORG.TYP = "";
            JIBS_tmp_int = BPCPQORG.TYP.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) BPCPQORG.TYP += " ";
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != IBCQINF.OUTPUT_DATA.POST_ACT_CTR 
                && (!BPCPORUP.DATA_INFO.TYP.substring(0, 1).equalsIgnoreCase("0") 
                || BPCPQORG.TYP.substring(0, 1).equalsIgnoreCase("0"))) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR);
            }
        } else if (IBCQINF.OUTPUT_DATA.SWR_BR == '2') {
            if (BPCPQORG.TYP == null) BPCPQORG.TYP = "";
            JIBS_tmp_int = BPCPQORG.TYP.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) BPCPQORG.TYP += " ";
            if (BPCPORUP.DATA_INFO.TYP == null) BPCPORUP.DATA_INFO.TYP = "";
            JIBS_tmp_int = BPCPORUP.DATA_INFO.TYP.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) BPCPORUP.DATA_INFO.TYP += " ";
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != IBCQINF.OUTPUT_DATA.POST_ACT_CTR 
                && (!BPCPQORG.TYP.substring(0, 1).equalsIgnoreCase("0") 
                || BPCPORUP.DATA_INFO.TYP.substring(0, 1).equalsIgnoreCase("0"))) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR);
            }
        } else {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR);
        }
    }
    public void B030_APPROVE_PROC() throws IOException,SQLException,Exception {
        if (IBCMANAC.SIGN == 'D') {
            B030_01_DEPOSIT_PROC();
            if (IBCMANAC.OTH_AC_TYP == 'N') {
                B030_02_DRAW_PROC();
            } else {
                B030_03_CALL_AI_CR_UNT();
            }
        }
        if (IBCMANAC.SIGN == 'C') {
            B030_04_DRAW_PROC();
            if (IBCMANAC.OTH_AC_TYP == 'N') {
                B030_05_DEPOSIT_PROC();
            } else {
                B030_06_CALL_AI_DR_UNT();
            }
        }
    }
    public void B030_01_DEPOSIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        IBCPOSTA.CCY = IBCQINF.INPUT_DATA.CCY;
        IBCPOSTA.CCY_TYP = '1';
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.AMT = IBCMANAC.AMT;
        IBCPOSTA.VAL_DATE = IBCMANAC.VAL_DATE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.OTH_AC_NO = IBCMANAC.OTH_AC_NO;
        CEP.TRC(SCCGWA, IBCPOSTA.OTH_AC_NO);
        IBCPOSTA.OTH_AC_TYPE = IBCMANAC.OTH_AC_TYP;
        IBCPOSTA.AC = IBCMANAC.AC_NO;
        CEP.TRC(SCCGWA, IBCPOSTA.AC);
        IBCPOSTA.OUR_REF = IBCMANAC.REF_NO;
        IBCPOSTA.THR_REF = IBCMANAC.OTH_REF_NO;
        IBCPOSTA.TX_MMO = "A801";
        IBCPOSTA.NARR = IBCMANAC.RMK;
        S000_CALL_IBZDRAC();
    }
    public void B030_02_DRAW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        IBCPOSTA.CCY = IBCQINF.INPUT_DATA.CCY;
        IBCPOSTA.CCY_TYP = '1';
        IBCPOSTA.SIGN = 'C';
        IBCPOSTA.AMT = IBCMANAC.AMT;
        IBCPOSTA.VAL_DATE = IBCMANAC.VAL_DATE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.OTH_AC_NO = IBCMANAC.AC_NO;
        CEP.TRC(SCCGWA, IBCPOSTA.OTH_AC_NO);
        IBCPOSTA.OTH_AC_TYPE = IBCQINF.OUTPUT_DATA.AC_ATTR;
        IBCPOSTA.AC = IBCMANAC.OTH_AC_NO;
        CEP.TRC(SCCGWA, IBCPOSTA.AC);
        IBCPOSTA.THR_REF = IBCMANAC.REF_NO;
        IBCPOSTA.OUR_REF = IBCMANAC.OTH_REF_NO;
        IBCPOSTA.TX_MMO = "A801";
        IBCPOSTA.NARR = IBCMANAC.RMK;
        S000_CALL_IBZCRAC();
    }
    public void B030_03_CALL_AI_CR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = IBCMANAC.OTH_AC_NO;
        AICUUPIA.DATA.AMT = IBCMANAC.AMT;
        AICUUPIA.DATA.CCY = IBCMANAC.CCY;
        AICUUPIA.DATA.VALUE_DATE = IBCMANAC.VAL_DATE;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.RVS_NO = IBCMANAC.SUSPD_NO;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
        AICUUPIA.DATA.BR_OLD = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
        AICUUPIA.DATA.BR_NEW = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
        AICUUPIA.DATA.POST_NARR = IBCMANAC.OTH_RMK;
        AICUUPIA.DATA.DESC = IBCMANAC.OTH_RMK;
        AICUUPIA.DATA.PAY_MAN = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.PAY_MAN);
        AICUUPIA.DATA.THEIR_AC = IBCMANAC.AC_NO;
        AICUUPIA.DATA.PAY_BR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
        S000_CALL_BPZPQORG();
        AICUUPIA.DATA.PAY_BKNM = BPCPQORG.CHN_NM;
        S000_CALL_AIZUUPIA();
    }
    public void B030_04_DRAW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        IBCPOSTA.CCY = IBCQINF.INPUT_DATA.CCY;
        IBCPOSTA.CCY_TYP = '1';
        IBCPOSTA.SIGN = 'C';
        IBCPOSTA.AMT = IBCMANAC.AMT;
        IBCPOSTA.VAL_DATE = IBCMANAC.VAL_DATE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.OTH_AC_NO = IBCMANAC.OTH_AC_NO;
        CEP.TRC(SCCGWA, IBCPOSTA.OTH_AC_NO);
        IBCPOSTA.OTH_AC_TYPE = IBCMANAC.OTH_AC_TYP;
        IBCPOSTA.AC = IBCMANAC.AC_NO;
        CEP.TRC(SCCGWA, IBCPOSTA.AC);
        IBCPOSTA.OUR_REF = IBCMANAC.REF_NO;
        IBCPOSTA.THR_REF = IBCMANAC.OTH_REF_NO;
        IBCPOSTA.TX_MMO = "A801";
        IBCPOSTA.NARR = IBCMANAC.RMK;
        S000_CALL_IBZCRAC();
    }
    public void B030_05_DEPOSIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        IBCPOSTA.CCY = IBCQINF.INPUT_DATA.CCY;
        IBCPOSTA.CCY_TYP = '1';
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.AMT = IBCMANAC.AMT;
        IBCPOSTA.VAL_DATE = IBCMANAC.VAL_DATE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.OTH_AC_NO = IBCMANAC.AC_NO;
        CEP.TRC(SCCGWA, IBCPOSTA.OTH_AC_NO);
        IBCPOSTA.OTH_AC_TYPE = IBCQINF.OUTPUT_DATA.AC_ATTR;
        IBCPOSTA.AC = IBCMANAC.OTH_AC_NO;
        CEP.TRC(SCCGWA, IBCPOSTA.AC);
        IBCPOSTA.THR_REF = IBCMANAC.REF_NO;
        IBCPOSTA.OUR_REF = IBCMANAC.OTH_REF_NO;
        IBCPOSTA.TX_MMO = "A801";
        IBCPOSTA.NARR = IBCMANAC.RMK;
        S000_CALL_IBZDRAC();
    }
    public void B030_06_CALL_AI_DR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = IBCMANAC.OTH_AC_NO;
        AICUUPIA.DATA.AMT = IBCMANAC.AMT;
        AICUUPIA.DATA.CCY = IBCMANAC.CCY;
        AICUUPIA.DATA.VALUE_DATE = IBCMANAC.VAL_DATE;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.RVS_NO = IBCMANAC.SUSPD_NO;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
        AICUUPIA.DATA.BR_OLD = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
        AICUUPIA.DATA.BR_NEW = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
        AICUUPIA.DATA.POST_NARR = IBCMANAC.OTH_RMK;
        AICUUPIA.DATA.DESC = IBCMANAC.OTH_RMK;
        AICUUPIA.DATA.PAY_MAN = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.PAY_MAN);
        AICUUPIA.DATA.THEIR_AC = IBCMANAC.AC_NO;
        AICUUPIA.DATA.PAY_BR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
        S000_CALL_BPZPQORG();
        AICUUPIA.DATA.PAY_BKNM = BPCPQORG.CHN_NM;
        S000_CALL_AIZUUPIA();
    }
    public void B040_UPD_MANIP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRMANIP);
        IBRMANIP.KEY.AC_DATE = IBCMANAC.AC_DATE;
        IBRMANIP.KEY.JRN_NO = IBCMANAC.JRN_NO;
        T000_READUPD_IBTMANIP();
        if (WS_TABLE_REC == 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.REC_NOTFND);
        }
        if (IBCMANAC.FUNC == IBRMANIP.STS 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.STS);
        }
        IBRMANIP.STS = IBCMANAC.FUNC;
        IBRMANIP.CHK_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBRMANIP.AUTH_TLR = SCCGWA.COMM_AREA.SUP1_ID;
        CEP.TRC(SCCGWA, IBRMANIP.AUTH_TLR);
        IBRMANIP.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBRMANIP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRMANIP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        T000_REWRITE_MANIP();
    }
    public void B050_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCOMNAC);
        IBCOMNAC.AC_NO = IBCMANAC.AC_NO;
        IBCOMNAC.NOS_CD = IBCMANAC.NOS_CD;
        IBCOMNAC.CCY = IBCMANAC.CCY;
        IBCOMNAC.NAME = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
        IBCOMNAC.SIGN = IBCMANAC.SIGN;
        IBCOMNAC.AMT = IBCMANAC.AMT;
        IBCOMNAC.OTH_AC_TYP = IBCMANAC.OTH_AC_TYP;
        IBCOMNAC.OTH_AC_NO = IBCMANAC.OTH_AC_NO;
        IBCOMNAC.OTH_NAME = "" + WS_OTH_NAME;
        JIBS_tmp_int = IBCOMNAC.OTH_NAME.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) IBCOMNAC.OTH_NAME = "0" + IBCOMNAC.OTH_NAME;
        IBCOMNAC.POST_CTR = IBCMANAC.POST_CTR;
        IBCOMNAC.VAL_DATE = IBCMANAC.VAL_DATE;
        IBCOMNAC.SUSPD_NO = IBCMANAC.SUSPD_NO;
        IBCOMNAC.REF_NO = IBCMANAC.REF_NO;
        IBCOMNAC.OTH_REF_NO = IBCMANAC.OTH_REF_NO;
        IBCOMNAC.MMO = IBCMANAC.MMO;
        IBCOMNAC.OTH_MMO = IBCMANAC.OTH_MMO;
        IBCOMNAC.RMK = IBCMANAC.RMK;
        IBCOMNAC.OTH_RMK = IBCMANAC.OTH_RMK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = IBCOMNAC;
        SCCFMT.DATA_LEN = 933;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_AIZCKOP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-CHK-OPEN-PERIOD", AICOCKOP);
        if (AICOCKOP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICOCKOP.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_IBZQIDNM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-CORP-ID-INQ", IBCQIDNM);
        if (IBCQIDNM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQIDNM.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_IBZDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZDRAC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_IBZCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZCRAC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READUPD_IBTMANIP() throws IOException,SQLException,Exception {
        IBTMANIP_RD = new DBParm();
        IBTMANIP_RD.TableName = "IBTMANIP";
        IBTMANIP_RD.upd = true;
        IBS.READ(SCCGWA, IBRMANIP, IBTMANIP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTMANIP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_MANIP() throws IOException,SQLException,Exception {
        IBTMANIP_RD = new DBParm();
        IBTMANIP_RD.TableName = "IBTMANIP";
        IBS.REWRITE(SCCGWA, IBRMANIP, IBTMANIP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTMANIP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
