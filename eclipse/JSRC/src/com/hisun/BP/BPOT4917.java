package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4917 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_TDTL_MT = "BP-R-TLR-HOL-M      ";
    String CPN_R_TLR_MAINTAIN = "BP-R-TLR-MAINTAIN   ";
    String K_OUTPUT_FMT = "BP312";
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
    BPCRTLTS BPCRTLTS = new BPCRTLTS();
    BPCRTLTM BPCRTLTM = new BPCRTLTM();
    BPCO4919_OPT_4919 BPCO4919_OPT_4919 = new BPCO4919_OPT_4919();
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
        CEP.TRC(SCCGWA, "BPOT4917 return!");
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
        B020_DELETE_TLR_RECORD();
        if (pgmRtn) return;
        B030_OUTPUT_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4915_AWA_4915.TLR);
        CEP.TRC(SCCGWA, BPB4915_AWA_4915.OPT);
        IBS.init(SCCGWA, BPCRTLTS);
        IBS.init(SCCGWA, BPCRTLTM);
        IBS.init(SCCGWA, BPRTDTL);
        BPRTDTL.KEY.TLR = BPB4915_AWA_4915.TLR;
        BPRTDTL.KEY.TYPE = 'T';
        BPCRTLTS.INFO.FUNC = 'R';
        S000_CALL_BPZRTLTS();
        if (pgmRtn) return;
        BPRTLT.KEY.TLR = BPB4915_AWA_4915.TLR;
        BPCRTLTM.INFO.FUNC = 'R';
        S000_CALL_BPZRTLTM();
        if (pgmRtn) return;
    }
    public void B020_DELETE_TLR_RECORD() throws IOException,SQLException,Exception {
        BPCRTLTS.INFO.FUNC = 'D';
        BPRTDTL.KEY.TLR = BPB4915_AWA_4915.TLR;
        BPRTDTL.KEY.TYPE = 'T';
        S000_CALL_BPZRTLTS();
        if (pgmRtn) return;
    }
    public void B030_OUTPUT_INFO() throws IOException,SQLException,Exception {
        B060_01_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        B060_02_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B060_01_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO4919_OPT_4919);
        BPCO4919_OPT_4919.TLR = BPRTLT.KEY.TLR;
        BPCO4919_OPT_4919.OUT_BR = BPRTLT.TLR_BR;
        BPCO4919_OPT_4919.TLR_NM = BPRTLT.TLR_CN_NM;
        BPCO4919_OPT_4919.IN_BR = BPRTDTL.IN_BR;
        BPCO4919_OPT_4919.TLR_LVL = BPRTLT.TLR_LVL;
        BPCO4919_OPT_4919.BEGIN_DT = BPRTDTL.BEGIN_DT;
        BPCO4919_OPT_4919.END_DT = BPRTDTL.END_DT;
        BPCO4919_OPT_4919.BEGIN_TM = BPRTDTL.BEGIN_TIME;
        BPCO4919_OPT_4919.END_TM = BPRTDTL.END_TIME;
    }
    public void B060_02_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO4919_OPT_4919;
        SCCFMT.DATA_LEN = 0;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZRTLTS() throws IOException,SQLException,Exception {
        BPCRTLTS.INFO.POINTER = BPRTDTL;
        BPCRTLTS.INFO.LEN = 93;
        IBS.CALLCPN(SCCGWA, CPN_TDTL_MT, BPCRTLTS);
        CEP.TRC(SCCGWA, BPCRTLTS.RC.RC_CODE);
        if (BPCRTLTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTLTS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTLTM() throws IOException,SQLException,Exception {
        BPCRTLTM.INFO.POINTER = BPRTLT;
        BPCRTLTM.INFO.LEN = 1404;
        IBS.CALLCPN(SCCGWA, CPN_R_TLR_MAINTAIN, BPCRTLTM);
        CEP.TRC(SCCGWA, BPCRTLTM.RC.RC_CODE);
        if (BPCRTLTM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTLTM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
