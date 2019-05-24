package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4919 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP311";
    String CPN_R_TLR_MAINTAIN = "BP-R-TLR-MAINTAIN   ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTLT BPRTLT = new BPRTLT();
    BPRTDTL BPRTDTL = new BPRTDTL();
    BPCPQTLH BPCPQTLH = new BPCPQTLH();
    BPCRTLTM BPCRTLTM = new BPCRTLTM();
    BPCO4919_OPT_4919 BPCO4919_OPT_4919 = new BPCO4919_OPT_4919();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB4915_AWA_4915 BPB4915_AWA_4915;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT4919 return!");
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
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4915_AWA_4915.TLR);
        CEP.TRC(SCCGWA, BPB4915_AWA_4915.OPT);
        IBS.init(SCCGWA, BPCPQTLH);
        IBS.init(SCCGWA, BPCRTLTM);
        BPCPQTLH.INFO.TLR = BPB4915_AWA_4915.TLR;
        BPCPQTLH.INFO.TYPE = BPB4915_AWA_4915.OPT;
        S000_CALL_BPZPQTLH();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRTLT);
        BPRTLT.KEY.TLR = BPB4915_AWA_4915.TLR;
        BPCRTLTM.INFO.FUNC = 'Q';
        BPCRTLTM.INFO.POINTER = BPRTLT;
        BPCRTLTM.INFO.LEN = 1404;
        S000_CALL_BPZRTLRM();
        if (pgmRtn) return;
        if (BPCPQTLH.RC.RC_CODE == 0 
            || BPCRTLTM.RC.RC_CODE == 0) {
            B060_01_DATA_TRANS_TO_FMT();
            if (pgmRtn) return;
            B060_02_DATA_OUTPUT_FMT();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
        }
    }
    public void B060_01_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO4919_OPT_4919);
        CEP.TRC(SCCGWA, BPRTLT.KEY.TLR);
        CEP.TRC(SCCGWA, BPRTLT.TLR_BR);
        CEP.TRC(SCCGWA, BPRTLT.TLR_CN_NM);
        CEP.TRC(SCCGWA, BPCPQTLH.INFO.IN_BR);
        CEP.TRC(SCCGWA, BPRTLT.TLR_LVL);
        CEP.TRC(SCCGWA, BPCPQTLH.INFO.BEGIN_DT);
        CEP.TRC(SCCGWA, BPCPQTLH.INFO.END_DT);
        CEP.TRC(SCCGWA, BPCPQTLH.INFO.BEGIN_TIME);
        CEP.TRC(SCCGWA, BPCPQTLH.INFO.END_TIME);
        BPCO4919_OPT_4919.TLR = BPRTLT.KEY.TLR;
        BPCO4919_OPT_4919.OUT_BR = BPRTLT.TLR_BR;
        BPCO4919_OPT_4919.TLR_NM = BPRTLT.TLR_CN_NM;
        BPCO4919_OPT_4919.IN_BR = BPCPQTLH.INFO.IN_BR;
        BPCO4919_OPT_4919.TLR_LVL = BPRTLT.TLR_LVL;
        BPCO4919_OPT_4919.BEGIN_DT = BPCPQTLH.INFO.BEGIN_DT;
        BPCO4919_OPT_4919.END_DT = BPCPQTLH.INFO.END_DT;
        BPCO4919_OPT_4919.BEGIN_TM = BPCPQTLH.INFO.BEGIN_TIME;
        BPCO4919_OPT_4919.END_TM = BPCPQTLH.INFO.END_TIME;
    }
    public void B060_02_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO4919_OPT_4919;
        SCCFMT.DATA_LEN = 0;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPQTLH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-TLR-HOL", BPCPQTLH);
    }
    public void S000_CALL_BPZRTLRM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_TLR_MAINTAIN, BPCRTLTM);
        CEP.TRC(SCCGWA, BPCRTLTM.RC.RC_CODE);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
