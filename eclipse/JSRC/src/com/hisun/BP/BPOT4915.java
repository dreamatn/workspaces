package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4915 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_TLR_INF_BRW = "BP-S-TLR-INF-BRW    ";
    int K_MAX_ROW = 18;
    int K_MAX_COL = 500;
    String CPN_R_TLR_MAINTAIN = "BP-R-TLR-MAINTAIN   ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTLT BPRTLT = new BPRTLT();
    BPRTDTL BPRTDTL = new BPRTDTL();
    BPCSTLIB BPCSTLIB = new BPCSTLIB();
    BPCRTLTB BPCRTLTB = new BPCRTLTB();
    BPCRTDTL BPCRTDTL = new BPCRTDTL();
    BPCO4915_OPT_4915 BPCO4915_OPT_4915 = new BPCO4915_OPT_4915();
    BPCRTLTM BPCRTLTM = new BPCRTLTM();
    SCCGWA SCCGWA;
    BPB4915_AWA_4915 BPB4915_AWA_4915;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT4915 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4915_AWA_4915>");
        BPB4915_AWA_4915 = (BPB4915_AWA_4915) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4915_AWA_4915.OUT_BR == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4915_AWA_4915.TLR);
        CEP.TRC(SCCGWA, BPB4915_AWA_4915.OUT_BR);
        if (BPB4915_AWA_4915.TLR.trim().length() == 0) {
            B030_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            B040_BROWSE_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTDTL);
        BPRTDTL.OUT_BR = BPB4915_AWA_4915.OUT_BR;
        BPRTDTL.KEY.TYPE = 'T';
        BPCRTDTL.INFO.FUNC = 'B';
        S000_CALL_BPZRTDTL();
        if (pgmRtn) return;
        BPCRTDTL.INFO.FUNC = 'N';
        S000_CALL_BPZRTDTL();
        if (pgmRtn) return;
        if (BPCRTDTL.RETURN_INFO == 'F') {
            B060_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (BPCRTDTL.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, BPRTLT);
            BPRTLT.KEY.TLR = BPRTDTL.KEY.TLR;
            BPCRTLTM.INFO.FUNC = 'Q';
            BPCRTLTM.INFO.POINTER = BPRTLT;
            BPCRTLTM.INFO.LEN = 1404;
            S000_CALL_BPZRTLRM();
            if (pgmRtn) return;
            if (BPRTDTL.BEGIN_DT != 0) {
                B060_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            BPCRTDTL.INFO.FUNC = 'N';
            S000_CALL_BPZRTDTL();
            if (pgmRtn) return;
        }
        BPCRTDTL.INFO.FUNC = 'E';
        S000_CALL_BPZRTDTL();
        if (pgmRtn) return;
    }
    public void B040_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTDTL);
        BPRTDTL.OUT_BR = BPB4915_AWA_4915.OUT_BR;
        BPRTDTL.KEY.TLR = BPB4915_AWA_4915.TLR;
        BPRTDTL.KEY.TYPE = 'T';
        BPCRTDTL.INFO.FUNC = 'T';
        S000_CALL_BPZRTDTL();
        if (pgmRtn) return;
        BPCRTDTL.INFO.FUNC = 'N';
        S000_CALL_BPZRTDTL();
        if (pgmRtn) return;
        if (BPCRTDTL.RETURN_INFO == 'F') {
            B060_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (BPCRTDTL.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, BPRTLT);
            BPRTLT.KEY.TLR = BPRTDTL.KEY.TLR;
            BPCRTLTM.INFO.FUNC = 'Q';
            BPCRTLTM.INFO.POINTER = BPRTLT;
            BPCRTLTM.INFO.LEN = 1404;
            S000_CALL_BPZRTLRM();
            if (pgmRtn) return;
            if (BPRTDTL.BEGIN_DT != 0 
                && BPRTDTL.END_DT >= SCCGWA.COMM_AREA.AC_DATE) {
                B060_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            BPCRTDTL.INFO.FUNC = 'N';
            S000_CALL_BPZRTDTL();
            if (pgmRtn) return;
        }
        BPCRTDTL.INFO.FUNC = 'E';
        S000_CALL_BPZRTDTL();
        if (pgmRtn) return;
    }
    public void B060_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 0;
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO4915_OPT_4915);
        CEP.TRC(SCCGWA, BPRTDTL.KEY.TLR);
        CEP.TRC(SCCGWA, BPRTLT.TLR_CN_NM);
        CEP.TRC(SCCGWA, BPRTDTL.OUT_BR);
        CEP.TRC(SCCGWA, BPRTDTL.IN_BR);
        CEP.TRC(SCCGWA, BPRTLT.TLR_LVL);
        CEP.TRC(SCCGWA, BPRTLT.TX_LVL);
        CEP.TRC(SCCGWA, BPRTLT.ATH_LVL);
        CEP.TRC(SCCGWA, BPRTDTL.BEGIN_DT);
        CEP.TRC(SCCGWA, BPRTDTL.END_DT);
        CEP.TRC(SCCGWA, BPRTDTL.BEGIN_TIME);
        CEP.TRC(SCCGWA, BPRTDTL.END_TIME);
        BPCO4915_OPT_4915.TLR = BPRTDTL.KEY.TLR;
        BPCO4915_OPT_4915.TLR_NM = BPRTLT.TLR_CN_NM;
        BPCO4915_OPT_4915.OUT_BR = BPRTDTL.OUT_BR;
        BPCO4915_OPT_4915.IN_BR = BPRTDTL.IN_BR;
        BPCO4915_OPT_4915.TLR_LVL = BPRTLT.TLR_LVL;
        BPCO4915_OPT_4915.TX_LVL = BPRTLT.TX_LVL;
        BPCO4915_OPT_4915.ATH_LVL = BPRTLT.ATH_LVL;
        BPCO4915_OPT_4915.BEGIN_DT = BPRTDTL.BEGIN_DT;
        BPCO4915_OPT_4915.END_DT = BPRTDTL.END_DT;
        BPCO4915_OPT_4915.BEGIN_TM = BPRTDTL.BEGIN_TIME;
        BPCO4915_OPT_4915.END_TM = BPRTDTL.END_TIME;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCO4915_OPT_4915);
        SCCMPAG.DATA_LEN = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRTDTL() throws IOException,SQLException,Exception {
        BPCRTDTL.INFO.POINTER = BPRTDTL;
        BPCRTDTL.INFO.LEN = 93;
        IBS.CALLCPN(SCCGWA, "BP-R-STARTBR-TDTL", BPCRTDTL);
    }
    public void S000_CALL_BPZRTLTB() throws IOException,SQLException,Exception {
        BPCRTLTB.INFO.POINTER = BPRTLT;
        BPCRTLTB.INFO.LEN = 1404;
        IBS.CALLCPN(SCCGWA, "BP-R-STARTBR-TLT", BPCRTLTB);
        if (BPCRTDTL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTLTB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTLRM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_TLR_MAINTAIN, BPCRTLTM);
        CEP.TRC(SCCGWA, BPCRTLTM.RC.RC_CODE);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
