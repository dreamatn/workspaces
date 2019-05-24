package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1308 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String CPN_QUERY_TLT_HOLIDAY = "BP-P-QUERY-TLR-HOL  ";
    String CPN_R_TLR_MAINTAIN = "BP-R-TLR-MAINTAIN   ";
    BPOT1308_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT1308_WS_TEMP_VARIABLE();
    BPOT1308_WS_SEQ_CMP WS_SEQ_CMP = new BPOT1308_WS_SEQ_CMP();
    long WS_SEQ_CMP_INT = 0;
    short WS_SEQ_LOC = 0;
    short WS_CTL_LOC = 0;
    short WS_SEQ_CUT_LOC = 0;
    short WS_SEQ_LEN = 0;
    short WS_I = 0;
    short WS_J = 0;
    short WS_M = 0;
    short WS_N = 0;
    short WS_CTL_LEN = 0;
    short WS_CTL_LEN_ACT = 0;
    short WS_CODE_1 = 0;
    String WS_CODE = " ";
    long WS_SEQ_NO = 0;
    String WS_CINO = " ";
    BPCOOTLS BPCOOTLS = new BPCOOTLS();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPRMSG BPRRMSG = new BPRMSG();
    BPRCINO BPRCINO = new BPRCINO();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPCOHSEQ BPCOHSEQ = new BPCOHSEQ();
    SCCGWA SCCGWA;
    BPB1310_AWA_1310 BPB1310_AWA_1310;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "BPOT1308 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1310_AWA_1310>");
        BPB1310_AWA_1310 = (BPB1310_AWA_1310) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_DEAL_PROCESS();
        B300_OUTPUT_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB1310_AWA_1310.CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_HSEQ_CODE_MUST_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (BPB1310_AWA_1310.SEQ_NO == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_HSEQ_SEQ_MUST_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPRCINO);
        IBS.init(SCCGWA, BPCPRMB);
        BPRCINO.KEY.TYP = "CINO";
        BPCPRMB.FUNC = '0';
        BPCPRMB.DAT_PTR = BPRCINO;
        S000_CALL_BPZPRMB();
        IBS.init(SCCGWA, BPCPRMB.RC);
        BPCPRMB.FUNC = '1';
        BPCPRMB.DAT_PTR = BPRCINO;
        S000_CALL_BPZPRMB();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CI_DFN_NOTFOUND, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCPRMB.RC);
        BPCPRMB.FUNC = '2';
        BPCPRMB.DAT_PTR = BPRCINO;
        S000_CALL_BPZPRMB();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPRCINO.DATA_TXT.CTL_LEN);
        CEP.TRC(SCCGWA, BPRCINO.DATA_TXT.IN_USE_FLG);
        CEP.TRC(SCCGWA, BPRCINO.DATA_TXT.CTL_FLD1);
        CEP.TRC(SCCGWA, BPRCINO.DATA_TXT.LEN1);
        CEP.TRC(SCCGWA, BPRCINO.DATA_TXT.CTL_FLD2);
        CEP.TRC(SCCGWA, BPRCINO.DATA_TXT.LEN2);
        CEP.TRC(SCCGWA, BPRCINO.DATA_TXT.CTL_FLD3);
        CEP.TRC(SCCGWA, BPRCINO.DATA_TXT.LEN3);
        CEP.TRC(SCCGWA, BPRCINO.DATA_TXT.CTL_FLD4);
        CEP.TRC(SCCGWA, BPRCINO.DATA_TXT.LEN4);
        CEP.TRC(SCCGWA, BPRCINO.DATA_TXT.CTL_FLD5);
        CEP.TRC(SCCGWA, BPRCINO.DATA_TXT.LEN5);
        CEP.TRC(SCCGWA, BPRCINO.DATA_TXT.CTL_FLD6);
        CEP.TRC(SCCGWA, BPRCINO.DATA_TXT.LEN6);
        CEP.TRC(SCCGWA, BPRCINO.DATA_TXT.CTL_FLD7);
        CEP.TRC(SCCGWA, BPRCINO.DATA_TXT.LEN7);
        CEP.TRC(SCCGWA, BPRCINO.DATA_TXT.CTL_FLD8);
        CEP.TRC(SCCGWA, BPRCINO.DATA_TXT.LEN8);
        CEP.TRC(SCCGWA, BPRCINO.DATA_TXT.SEQ_LEN);
        CEP.TRC(SCCGWA, BPRCINO.DATA_TXT.FUNC_FLG);
        WS_CTL_LEN = BPRCINO.DATA_TXT.CTL_LEN;
        WS_SEQ_LEN = BPRCINO.DATA_TXT.SEQ_LEN;
        WS_SEQ_NO = BPB1310_AWA_1310.SEQ_NO;
        WS_CODE = BPB1310_AWA_1310.CODE;
        WS_J = 12;
        for (WS_I = 1; WS_I <= WS_SEQ_LEN; WS_I += 1) {
            WS_SEQ_CMP.WS_SEQ_CMP_I[WS_J-1] = 9;
            WS_J = (short) (WS_J - 1);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_SEQ_CMP);
        WS_SEQ_CMP_INT = Long.parseLong(JIBS_tmp_str[0]);
        CEP.TRC(SCCGWA, WS_SEQ_CMP);
        CEP.TRC(SCCGWA, WS_SEQ_CMP_INT);
        if (WS_SEQ_NO > WS_SEQ_CMP_INT) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_SEQ_LEN_LAR_CI_DFN, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        WS_N = 10;
        if (WS_CODE == null) WS_CODE = "";
        JIBS_tmp_int = WS_CODE.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) WS_CODE += " ";
        if (WS_CODE.substring(WS_N - 1, WS_N + 1 - 1).trim().length() == 0) WS_CODE_1 = 0;
        else WS_CODE_1 = Short.parseShort(WS_CODE.substring(WS_N - 1, WS_N + 1 - 1));
        CEP.TRC(SCCGWA, WS_N);
        CEP.TRC(SCCGWA, WS_CODE_1);
        for (WS_M = 1; WS_M <= 10 
            && WS_CODE_1 == ' '; WS_M += 1) {
            WS_N = (short) (WS_N - 1);
            CEP.TRC(SCCGWA, WS_N);
            if (WS_CODE == null) WS_CODE = "";
            JIBS_tmp_int = WS_CODE.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_CODE += " ";
            if (WS_CODE.substring(WS_N - 1, WS_N + 1 - 1).trim().length() == 0) WS_CODE_1 = 0;
            else WS_CODE_1 = Short.parseShort(WS_CODE.substring(WS_N - 1, WS_N + 1 - 1));
            CEP.TRC(SCCGWA, WS_CODE_1);
        }
        WS_CTL_LEN_ACT = (short) (10 - WS_M + 1);
        CEP.TRC(SCCGWA, WS_M);
        CEP.TRC(SCCGWA, WS_CODE);
        CEP.TRC(SCCGWA, WS_CTL_LEN_ACT);
        CEP.TRC(SCCGWA, WS_CTL_LEN);
        if (WS_CTL_LEN_ACT != WS_CTL_LEN) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CD_LEN_NO_MCH_CI_DFN, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_DEAL_PROCESS() throws IOException,SQLException,Exception {
        WS_SEQ_LOC = (short) (WS_CTL_LEN + 1);
        WS_SEQ_CUT_LOC = (short) (15 - WS_SEQ_LEN + 1);
        CEP.TRC(SCCGWA, WS_SEQ_LOC);
        CEP.TRC(SCCGWA, WS_CTL_LOC);
        if (WS_CINO == null) WS_CINO = "";
        JIBS_tmp_int = WS_CINO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_CINO += " ";
        if (WS_CODE == null) WS_CODE = "";
        JIBS_tmp_int = WS_CODE.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) WS_CODE += " ";
        WS_CINO = WS_CODE + WS_CINO.substring(WS_CTL_LEN);
        JIBS_tmp_str[0] = "" + WS_SEQ_NO;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (WS_CINO == null) WS_CINO = "";
        JIBS_tmp_int = WS_CINO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_CINO += " ";
        WS_CINO = WS_CINO.substring(0, WS_SEQ_LOC - 1) + JIBS_tmp_str[0].substring(WS_SEQ_CUT_LOC - 1, WS_SEQ_CUT_LOC + WS_SEQ_LEN - 1) + WS_CINO.substring(WS_SEQ_LOC + WS_SEQ_LEN - 1);
        CEP.TRC(SCCGWA, WS_CINO);
    }
    public void B300_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOHSEQ);
        IBS.init(SCCGWA, SCCFMT);
        BPCOHSEQ.CI_NO = WS_CINO;
        SCCFMT.FMTID = "BP565";
        SCCFMT.DATA_PTR = BPCOHSEQ;
        SCCFMT.DATA_LEN = 458;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_SCZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
    }
    public void S000_CALL_BPZPRMB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-BROWSE ", BPCPRMB);
        if (BPCPRMB.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
