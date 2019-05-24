package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT7020 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    LNOT7020_WS_MSGID WS_MSGID = new LNOT7020_WS_MSGID();
    short WS_MSG_INFO = 0;
    LNOT7020_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT7020_WS_TEMP_VARIABLE();
    LNOT7020_WS_OUT_Z WS_OUT_Z = new LNOT7020_WS_OUT_Z();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    LNCIPAMT LNCIPAMT = new LNCIPAMT();
    LNCICTLM LNCICTLM = new LNCICTLM();
    SCCGWA SCCGWA;
    LNB7020_AWA_7020 LNB7020_AWA_7020;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT7020 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB7020_AWA_7020>");
        LNB7020_AWA_7020 = (LNB7020_AWA_7020) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CTL_STSW);
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (!LNCICTLM.REC_DATA.CTL_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
            && !LNCICTLM.REC_DATA.CTL_STSW.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1")) {
            Z_RET();
            if (pgmRtn) return;
        }
        B200_CALL_SYN_PROCESS();
        if (pgmRtn) return;
        B300_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        B011_CHECK_ICTL_STS();
        if (pgmRtn) return;
    }
    public void B011_CHECK_ICTL_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNB7020_AWA_7020.CONT_NO;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B200_CALL_SYN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIPAMT);
        LNCIPAMT.CTA_NO = LNB7020_AWA_7020.CONT_NO;
        LNCIPAMT.P_AMT = LNB7020_AWA_7020.TOT_PRIN;
        LNCIPAMT.I_AMT = LNB7020_AWA_7020.TOT_INT;
        LNCIPAMT.O_AMT = LNB7020_AWA_7020.TOT_PLC;
        LNCIPAMT.L_AMT = LNB7020_AWA_7020.TOT_ILC;
        LNCIPAMT.F_AMT = LNB7020_AWA_7020.PY_F_AMT;
        S000_CALL_LNZIPAMT();
        if (pgmRtn) return;
    }
    public void B300_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_Z);
        for (WS_TEMP_VARIABLE.WS_IDX = 1; WS_TEMP_VARIABLE.WS_IDX <= 10 
            && LNCIPAMT.PART_DATA[WS_TEMP_VARIABLE.WS_IDX-1].PART_NO != 0; WS_TEMP_VARIABLE.WS_IDX += 1) {
            WS_OUT_Z.WS_OUT[WS_TEMP_VARIABLE.WS_IDX-1].WS_PART_CONT_NO = LNCIPAMT.PART_DATA[WS_TEMP_VARIABLE.WS_IDX-1].PART_CTA_NO;
            CEP.TRC(SCCGWA, LNCIPAMT.PART_DATA[WS_TEMP_VARIABLE.WS_IDX-1].PART_CTA_NO);
            WS_OUT_Z.WS_OUT[WS_TEMP_VARIABLE.WS_IDX-1].WS_PART_NM = LNCIPAMT.PART_DATA[WS_TEMP_VARIABLE.WS_IDX-1].PART_NM;
            CEP.TRC(SCCGWA, LNCIPAMT.PART_DATA[WS_TEMP_VARIABLE.WS_IDX-1].PART_NM);
            WS_OUT_Z.WS_OUT[WS_TEMP_VARIABLE.WS_IDX-1].WS_PART_NO = LNCIPAMT.PART_DATA[WS_TEMP_VARIABLE.WS_IDX-1].PART_NO;
            WS_OUT_Z.WS_OUT[WS_TEMP_VARIABLE.WS_IDX-1].WS_PART_BR = LNCIPAMT.PART_DATA[WS_TEMP_VARIABLE.WS_IDX-1].PART_BR;
            WS_OUT_Z.WS_OUT[WS_TEMP_VARIABLE.WS_IDX-1].WS_PART_INNER_OUT_FLG = LNCIPAMT.INNER_OUT_FLG;
            WS_OUT_Z.WS_OUT[WS_TEMP_VARIABLE.WS_IDX-1].WS_PART_REL_TYPE = LNCIPAMT.PART_DATA[WS_TEMP_VARIABLE.WS_IDX-1].REL_TYPE;
            WS_OUT_Z.WS_OUT[WS_TEMP_VARIABLE.WS_IDX-1].WS_PART_PRIN = LNCIPAMT.PART_DATA[WS_TEMP_VARIABLE.WS_IDX-1].PART_P_AMT;
            WS_OUT_Z.WS_OUT[WS_TEMP_VARIABLE.WS_IDX-1].WS_PART_INT = LNCIPAMT.PART_DATA[WS_TEMP_VARIABLE.WS_IDX-1].PART_I_AMT;
            WS_OUT_Z.WS_OUT[WS_TEMP_VARIABLE.WS_IDX-1].WS_PART_PLC = LNCIPAMT.PART_DATA[WS_TEMP_VARIABLE.WS_IDX-1].PART_O_AMT;
            WS_OUT_Z.WS_OUT[WS_TEMP_VARIABLE.WS_IDX-1].WS_PART_ILC = LNCIPAMT.PART_DATA[WS_TEMP_VARIABLE.WS_IDX-1].PART_L_AMT;
            WS_OUT_Z.WS_OUT[WS_TEMP_VARIABLE.WS_IDX-1].WS_PART_F = LNCIPAMT.PART_DATA[WS_TEMP_VARIABLE.WS_IDX-1].PART_F_AMT;
            CEP.TRC(SCCGWA, WS_OUT_Z.WS_OUT[WS_TEMP_VARIABLE.WS_IDX-1]);
        }
        CEP.TRC(SCCGWA, WS_OUT_Z);
        SCCFMT.FMTID = "LN702";
        SCCFMT.DATA_PTR = WS_OUT_Z;
        SCCFMT.DATA_LEN = 3810;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_LNZIPAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-INQ-PAR-AMT", LNCIPAMT);
        if (LNCIPAMT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCIPAMT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICTLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_MSG_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
