package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6110 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 99;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_REC_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    BPCSRTCO BPCSRTCO = new BPCSRTCO();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB6110_AWA_6110 BPB6110_AWA_6110;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        S000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT6110 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void S000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6110_AWA_6110>");
        BPB6110_AWA_6110 = (BPB6110_AWA_6110) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSRTCO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_PROC();
        if (pgmRtn) return;
        B200_QUERY_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "SELECT CHECK DATA");
        if (BPB6110_AWA_6110.RT_CCY.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_MUST_INPUT_1;
            if (BPB6110_AWA_6110.RT_CCY.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BPB6110_AWA_6110.RT_CCY);
            S000_ERR_MSG_NO_PROC();
            if (pgmRtn) return;
        }
        if (BPB6110_AWA_6110.RT_RBASE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_MUST_INPUT_1;
            if (BPB6110_AWA_6110.RT_RBASE.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BPB6110_AWA_6110.RT_RBASE);
            S000_ERR_MSG_NO_PROC();
            if (pgmRtn) return;
        }
        if (BPB6110_AWA_6110.RT_RTENO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_MUST_INPUT_1;
            if (BPB6110_AWA_6110.RT_RTENO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BPB6110_AWA_6110.RT_RTENO);
            S000_ERR_MSG_NO_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_QUERY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB6110_AWA_6110.RT_CCY);
        CEP.TRC(SCCGWA, BPB6110_AWA_6110.RT_RBASE);
        CEP.TRC(SCCGWA, BPB6110_AWA_6110.RT_RTENO);
        BPCSRTCO.INP.INP_CCY = BPB6110_AWA_6110.RT_CCY;
        BPCSRTCO.INP.INP_RBASE = BPB6110_AWA_6110.RT_RBASE;
        BPCSRTCO.INP.INP_RTENO = BPB6110_AWA_6110.RT_RTENO;
        B301_QUERY_BPTCORT_PROC();
        if (pgmRtn) return;
        R000_TRANS_OUTPUT();
        if (pgmRtn) return;
    }
    public void B301_QUERY_BPTCORT_PROC() throws IOException,SQLException,Exception {
        BPCSRTCO.FUNC = 'Q';
        BPCSRTCO.QRYBR_INFO = 'K';
        S001_CALL_BPZSRTCO();
        if (pgmRtn) return;
    }
    public void R000_TRANS_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "BPZ10";
        SCCFMT.DATA_PTR = BPCSRTCO.OUTPUT_INFO.OUT_DATA;
        SCCFMT.DATA_LEN = 147;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S001_CALL_BPZSRTCO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-OPER-RT", BPCSRTCO);
    }
    public void S000_ERR_MSG_NO_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
