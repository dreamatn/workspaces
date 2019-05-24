package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.AI.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPVAPT {
    String JIBS_tmp_str[] = new String[10];
    BPCX998_VCH_AREA VCH_AREA1;
    BPCX999_VCH_AREA VCH_AREA;
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP999";
    String K_OUTPUT_FMT_RVS = "BP998";
    String K_SIGN_DR = "DR";
    String K_SIGN_CR = "CR";
    String WS_ERR_MSG = " ";
    BPZPVAPT_WS_IDX WS_IDX = new BPZPVAPT_WS_IDX();
    BPRVWA_VCH_AREA WS_ITM_TEMP;
    int WS_OUT_LEN = 0;
    BPRANS_ITM WS_ANS_CD_TEMP;
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCX999 BPCX999 = new BPCX999();
    BPCX998 BPCX998 = new BPCX998();
    BPCPQBAI BPCPQBAI = new BPCPQBAI();
    AICOVLOG AICOVLOG = new AICOVLOG();
    CICCUST CICCUST = new CICCUST();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    BPRVWA BPRVWA;
    BPRANS BPRANS;
    BPCOVAPT BPCOVAPT;
    public void MP(SCCGWA SCCGWA, BPCOVAPT BPCOVAPT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOVAPT = BPCOVAPT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPVAPT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        BPRVWA = (BPRVWA) BPCOVAPT.VCH_PTR;
        BPRANS = (BPRANS) GWA_BP_AREA.ANS_AREA_PTR;
        IBS.init(SCCGWA, BPCOVAPT.RC);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.VCH_NO);
        A010_GET_PARM();
        if (pgmRtn) return;
    }
    public void A010_GET_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBAI);
        BPCPQBAI.DATA_INFO.BNK = SCCGWA.COMM_AREA.TR_BANK;
        S000_CALL_BPZPQBAI();
        if (pgmRtn) return;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_SORT_BY_BOOK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQBAI.DATA_INFO.ONL_PVCH);
        CEP.TRC(SCCGWA, SCCGAPV.REF);
        CEP.TRC(SCCGWA, SCCGAPV.TYPE);
        if (BPCPQBAI.DATA_INFO.ONL_PVCH == 'Y') {
            B020_GET_VWA_PARTB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCX999.BASIC_AREA.CNT);
            CEP.TRC(SCCGWA, BPRVWA.BASIC_AREA.ODE_FLG);
            WS_OUT_LEN = 30406;
            CEP.TRC(SCCGWA, WS_OUT_LEN);
            if (BPCX999.BASIC_AREA.CNT > 0) {
                B030_VWA_OUTPUT();
                if (pgmRtn) return;
            }
        }
        if (BPRVWA.BASIC_AREA.ODE_FLG == 'Y' 
            && BPCX999.BASIC_AREA.CNT > 400) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_EXCEED_MAX_VCH_SEQ;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_SORT_BY_BOOK() throws IOException,SQLException,Exception {
        for (WS_IDX.WS_I1 = 1; WS_IDX.WS_I1 <= BPRVWA.BASIC_AREA.CNT; WS_IDX.WS_I1 += 1) {
            WS_IDX.WS_I2 = BPRVWA.BASIC_AREA.CNT - WS_IDX.WS_I1;
            for (WS_IDX.WS_I3 = 1; WS_IDX.WS_I3 <= WS_IDX.WS_I2; WS_IDX.WS_I3 += 1) {
                WS_IDX.WS_I4 = WS_IDX.WS_I3 + 1;
                if (BPRVWA.VCH_AREA.get(WS_IDX.WS_I3-1).PARTB.BOOK_FLG.compareTo(BPRVWA.VCH_AREA.get(WS_IDX.WS_I4-1).PARTB.BOOK_FLG) > 0) {
                    WS_ITM_TEMP = BPRVWA.VCH_AREA.get(WS_IDX.WS_I3-1);
                    BPRVWA.VCH_AREA.set(WS_IDX.WS_I3-1, BPRVWA.VCH_AREA.get(WS_IDX.WS_I4-1));
                    BPRVWA.VCH_AREA.set(WS_IDX.WS_I4-1, WS_ITM_TEMP);
                    if (BPRVWA.BASIC_AREA.ODE_FLG == 'Y') {
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRANS.VCH_AREA.ITM.get(WS_IDX.WS_I3-1));
                        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ANS_CD_TEMP);
                        BPRANS.VCH_AREA.ITM.set(WS_IDX.WS_I3-1, BPRANS.VCH_AREA.ITM.get(WS_IDX.WS_I4-1));
                        BPRANS.VCH_AREA.ITM.set(WS_IDX.WS_I4-1, WS_ANS_CD_TEMP);
                    }
                }
            }
        }
    }
    public void B020_GET_VWA_PARTB() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRVWA.BASIC_AREA.CNT);
        BPCX998.CNT = 0;
        VCH_AREA1 = new BPCX998_VCH_AREA();
        BPCX998.VCH_AREA.add(VCH_AREA1);
        BPCX999.BASIC_AREA.CNT = 0;
        VCH_AREA = new BPCX999_VCH_AREA();
        BPCX999.VCH_AREA.add(VCH_AREA);
        IBS.init(SCCGWA, BPCX999.BASIC_AREA);
        CEP.TRC(SCCGWA, BPCX999.BASIC_AREA.CNT);
        for (WS_IDX.WS_I = 1; WS_IDX.WS_I <= BPRVWA.BASIC_AREA.CNT; WS_IDX.WS_I += 1) {
            CEP.TRC(SCCGWA, WS_IDX.WS_I);
            if (BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.EFF_DAYS == 0 
                && BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.PRINT_FLG != 'N') {
                CEP.TRC(SCCGWA, WS_IDX.WS_I);
                VCH_AREA = new BPCX999_VCH_AREA();
                BPCX999.VCH_AREA.add(VCH_AREA);
                BPCX999.BASIC_AREA.CNT = BPCX999.BASIC_AREA.CNT + 1;
                B022_OUTPUT_RVS();
                if (pgmRtn) return;
            }
        }
    }
    public void B021_GET_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VCH_AREA);
        if (BPRVWA.BASIC_AREA.ODE_FLG == 'Y') {
            BPCX999.BASIC_AREA.DESC = " ";
        } else {
            BPCX999.BASIC_AREA.DESC = BPRVWA.VCH_AREA.get(1-1).PARTB.DESC;
        }
        VCH_AREA.PARTB.BR = BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.BR;
        VCH_AREA.PARTB.ITM = BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.ITM;
        VCH_AREA.PARTB.CCY = BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.CCY;
        if (BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.SIGN == 'D') {
            VCH_AREA.PARTB.SIGN = K_SIGN_DR;
        } else {
            VCH_AREA.PARTB.SIGN = K_SIGN_CR;
        }
        VCH_AREA.PARTB.AMT = BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.AMT;
        VCH_AREA.PARTB.VAL_DATE = BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.VAL_DATE;
        VCH_AREA.PARTB.AC_NO = BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.AC_NO;
        CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.EVENT_CODE_REL);
        VCH_AREA.PARTB.EVENT_CODE_REL = BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.EVENT_CODE_REL;
        CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.REF_NO);
        VCH_AREA.PARTB.REF_NO = BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.REF_NO;
        CEP.TRC(SCCGWA, WS_IDX.WS_I);
        CEP.TRC(SCCGWA, BPCX999.BASIC_AREA.CNT);
        VCH_AREA.PARTB.CHQ_NO = BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.CHQ_NO;
        VCH_AREA.PARTB.POST_DATE = BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.POST_DATE;
        VCH_AREA.PARTB.POST_NARR = BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.POST_NARR;
        VCH_AREA.PARTB.TR_DESC = BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.DESC;
        CEP.TRC(SCCGWA, BPCX999.VCH_AREA.get(BPCX999.BASIC_AREA.CNT-1).PARTB.POST_NARR);
        VCH_AREA.PART_OUTPUT.TRANS_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.FLR == null) BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.FLR = "";
        JIBS_tmp_int = BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.FLR.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.FLR += " ";
        if (BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.FLR.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("Q")) {
            if (BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.FLR == null) BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.FLR = "";
            JIBS_tmp_int = BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.FLR.length();
            for (int i=0;i<60-JIBS_tmp_int;i++) BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.FLR += " ";
            if (BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.FLR.substring(6 - 1, 6 + 8 - 1).trim().length() == 0) VCH_AREA.PART_OUTPUT.TRANS_DATE = 0;
            else VCH_AREA.PART_OUTPUT.TRANS_DATE = Integer.parseInt(BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.FLR.substring(6 - 1, 6 + 8 - 1));
        }
        VCH_AREA.PARTB.MIB_NO = BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.MIB_NO;
        VCH_AREA.PARTB.RVS_NO = BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.RVS_NO;
        VCH_AREA.PARTB.RED_FLG = BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.RED_FLG;
        if (BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.MIB_NO.trim().length() > 0) {
            if (BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.MIB_NO == null) BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.MIB_NO = "";
            JIBS_tmp_int = BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.MIB_NO.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.MIB_NO += " ";
            if (BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.MIB_NO.substring(20 - 1, 20 + 4 - 1).trim().length() == 0) VCH_AREA.PARTB.AC_SEQ = 0;
            else VCH_AREA.PARTB.AC_SEQ = Integer.parseInt(BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.MIB_NO.substring(20 - 1, 20 + 4 - 1));
        }
    }
    public void B022_OUTPUT_RVS() throws IOException,SQLException,Exception {
        if (BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.RVS_NO.trim().length() > 0 
            && BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.SUSPENSE_FLG != 'S' 
            && BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.SUSPENSE_FLG != 'B') {
            BPCX998.CNT += 1;
            VCH_AREA1 = new BPCX998_VCH_AREA();
            BPCX998.VCH_AREA.add(VCH_AREA1);
            IBS.init(SCCGWA, VCH_AREA);
            VCH_AREA1.PARTB.MIB_NO = BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.MIB_NO;
            VCH_AREA1.PARTB.CNTR_TYPE = BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.CNTR_TYPE;
            VCH_AREA1.PARTB.PROD_CODE = BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.PROD_CODE;
            VCH_AREA1.PARTB.EVENT_CODE = BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.EVENT_CODE;
            VCH_AREA1.PARTB.RVS_NO = BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.RVS_NO;
            VCH_AREA1.PARTB.SIGN = BPRVWA.VCH_AREA.get(WS_IDX.WS_I-1).PARTB.SIGN;
        }
    }
    public void B030_VWA_OUTPUT() throws IOException,SQLException,Exception {
        if (BPRVWA.BASIC_AREA.ODE_FLG != 'Y') {
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = K_OUTPUT_FMT_RVS;
            SCCFMT.DATA_PTR = BPCX998;
            SCCFMT.DATA_LEN = 30406;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void S000_CALL_BPZPQBAI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-BKAI", BPCPQBAI);
        if (BPCPQBAI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQBAI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOVAPT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCOVAPT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCOVAPT = ");
            CEP.TRC(SCCGWA, BPCOVAPT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
