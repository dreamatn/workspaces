package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6111 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 99;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_REC_LEN = 0;
    int WS_VAL_INP = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    BPCSRTCO BPCSRTCO = new BPCSRTCO();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB6111_AWA_6111 BPB6111_AWA_6111;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        S000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT6111 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void S000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6111_AWA_6111>");
        BPB6111_AWA_6111 = (BPB6111_AWA_6111) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSRTCO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_PROC();
        if (pgmRtn) return;
        B200_BROWSE_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CHECK DATA");
    }
    public void B200_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = 999;
        SCCSUBS.TR_CODE = 5241;
        S200_SET_SUBS_TRN();
        if (pgmRtn) return;
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPB6111_AWA_6111.RT_CCY);
        CEP.TRC(SCCGWA, BPB6111_AWA_6111.RT_RBASE);
        CEP.TRC(SCCGWA, BPB6111_AWA_6111.RT_RTENO);
        if (BPB6111_AWA_6111.RT_CCY.trim().length() > 0) {
            BPCSRTCO.INP.INP_CCY = BPB6111_AWA_6111.RT_CCY;
            WS_VAL_INP = 1;
        }
        if (BPB6111_AWA_6111.RT_RBASE.trim().length() > 0) {
            BPCSRTCO.INP.INP_RBASE = BPB6111_AWA_6111.RT_RBASE;
            WS_VAL_INP = 1;
        }
        if (BPB6111_AWA_6111.RT_RTENO.trim().length() > 0) {
            BPCSRTCO.INP.INP_RTENO = BPB6111_AWA_6111.RT_RTENO;
            WS_VAL_INP = 1;
        }
        B301_STABR_BPTCORT_PROC();
        if (pgmRtn) return;
        B302_READ_NEXT_BPTCORT_PROC();
        if (pgmRtn) return;
        while (BPCSRTCO.RETURN_INFO != 'E' 
            && SCCMPAG.FUNC != 'E') {
            R000_TRANS_REL_MPAGE_OUTPUT();
            if (pgmRtn) return;
            B302_READ_NEXT_BPTCORT_PROC();
            if (pgmRtn) return;
        }
        B303_END_BPTCORT_PROC();
        if (pgmRtn) return;
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 147;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 9;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_REL_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        WS_REC_LEN = 147;
        S000_WRITE_TS();
        if (pgmRtn) return;
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        CEP.TRC(SCCGWA, BPCSRTCO.OUTPUT_INFO.OUT_DATA.OUT_CCY);
        CEP.TRC(SCCGWA, BPCSRTCO.OUTPUT_INFO.OUT_DATA.OUT_RBASE);
        CEP.TRC(SCCGWA, BPCSRTCO.OUTPUT_INFO.OUT_DATA);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCSRTCO.OUTPUT_INFO.OUT_DATA);
        SCCMPAG.DATA_LEN = WS_REC_LEN;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B301_STABR_BPTCORT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VAL_INP);
        if (WS_VAL_INP == 0) {
            BPCSRTCO.FUNC = 'B';
            BPCSRTCO.READBR_INFO = 'S';
        } else {
            BPCSRTCO.FUNC = 'Q';
            BPCSRTCO.QRYBR_INFO = 'T';
        }
        S001_CALL_BPZSRTCO();
        if (pgmRtn) return;
    }
    public void B302_READ_NEXT_BPTCORT_PROC() throws IOException,SQLException,Exception {
        if (WS_VAL_INP == 0) {
            BPCSRTCO.FUNC = 'B';
            BPCSRTCO.READBR_INFO = 'N';
        } else {
            BPCSRTCO.FUNC = 'Q';
            BPCSRTCO.QRYBR_INFO = 'X';
        }
        S001_CALL_BPZSRTCO();
        if (pgmRtn) return;
    }
    public void B303_END_BPTCORT_PROC() throws IOException,SQLException,Exception {
        if (WS_VAL_INP == 0) {
            BPCSRTCO.FUNC = 'B';
            BPCSRTCO.READBR_INFO = 'E';
        } else {
            BPCSRTCO.FUNC = 'Q';
            BPCSRTCO.QRYBR_INFO = 'D';
        }
        S001_CALL_BPZSRTCO();
        if (pgmRtn) return;
    }
    public void S001_CALL_BPZSRTCO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-OPER-RT", BPCSRTCO);
    }
    public void S200_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS", SCCSUBS);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
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
