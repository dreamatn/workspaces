package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6032 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String JIBS_NumStr;
    String JIBS_f0;
    String K_PRDT_INF_MAINT = "BP-S-MAINT-PRDT-INFO";
    String K_P_INQ_PRD_MODEL = "BP-P-INQ-PRD-MODEL";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_FMT_CD = "BPX01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_PARM_TX = " ";
    BPOT6032_WS_FMT_DATA WS_FMT_DATA = new BPOT6032_WS_FMT_DATA();
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCSMPRD BPCSMPRD = new BPCSMPRD();
    BPCPQPDM BPCPQPDM = new BPCPQPDM();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCGWA SCCGWA;
    BPB6030_AWA_6030 BPB6030_AWA_6030;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT6032 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6030_AWA_6030>");
        BPB6030_AWA_6030 = (BPB6030_AWA_6030) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, BPCSMPRD);
        CEP.TRC(SCCGWA, BPCSMPRD.ASS_BR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_FUNC_FLG = BPB6030_AWA_6030.FUNC;
        if ((WS_FUNC_FLG != 'I' 
            && WS_FUNC_FLG != 'M' 
            && WS_FUNC_FLG != 'D' 
            && WS_FUNC_FLG != 'A')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (WS_FUNC_FLG == 'D') {
            IBS.init(SCCGWA, BPCSMPRD);
            BPCSMPRD.PARM_CODE = BPB6030_AWA_6030.PARM_COD;
            BPCSMPRD.INFO.FUNC = 'B';
            CEP.TRC(SCCGWA, BPCSMPRD.ASS_BR);
            S000_CALL_BPZSMPRD();
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_INPUT_ERROR;
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPDM);
        if (BPB6030_AWA_6030.PRD_MODL.trim().length() > 0) {
            CEP.TRC(SCCGWA, BPB6030_AWA_6030.PRD_MODL);
            BPCPQPDM.PRDT_MODEL = BPB6030_AWA_6030.PRD_MODL;
            CEP.TRC(SCCGWA, BPCPQPDM.PRDT_MODEL);
            S000_CALL_BPZPQPDM();
            WS_PARM_TX = BPCPQPDM.PARM_TX;
            B300_SET_RETURN_INFO();
        }
    }
    public void B300_SET_RETURN_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        CEP.TRC(SCCGWA, BPCPQPDM.PARM_TX);
        if (BPCPQPDM.PARM_TX == null) BPCPQPDM.PARM_TX = "";
        JIBS_tmp_int = BPCPQPDM.PARM_TX.length();
        for (int i=0;i<7-JIBS_tmp_int;i++) BPCPQPDM.PARM_TX += " ";
        if (BPCPQPDM.PARM_TX.substring(0, 3).trim().length() == 0) SCCSUBS.AP_CODE = 0;
        else SCCSUBS.AP_CODE = Short.parseShort(BPCPQPDM.PARM_TX.substring(0, 3));
        if (BPCPQPDM.PARM_TX == null) BPCPQPDM.PARM_TX = "";
        JIBS_tmp_int = BPCPQPDM.PARM_TX.length();
        for (int i=0;i<7-JIBS_tmp_int;i++) BPCPQPDM.PARM_TX += " ";
        JIBS_tmp_str[0] = "" + SCCSUBS.TR_CODE;
        JIBS_f0 = "";
        for (int i=0;i<4-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + SCCSUBS.TR_CODE;
        JIBS_NumStr = BPCPQPDM.PARM_TX.substring(4 - 1, 4 + 3 - 1) + JIBS_NumStr.substring(3);
        SCCSUBS.TR_CODE = Short.parseShort(JIBS_NumStr);
        CEP.TRC(SCCGWA, SCCSUBS.AP_CODE);
        CEP.TRC(SCCGWA, SCCSUBS.TR_CODE);
        S000_SET_SUBS_TRN();
        B310_SET_RETURN_DATA();
    }
    public void B310_SET_RETURN_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_FMT_DATA);
        WS_FMT_DATA.WS_FMT_FUNC = BPB6030_AWA_6030.FUNC;
        WS_FMT_DATA.WS_FMT_PARM_COD = BPB6030_AWA_6030.PARM_COD;
        WS_FMT_DATA.WS_FMT_PAR_CODP = BPB6030_AWA_6030.PAR_CODP;
        WS_FMT_DATA.WS_FMT_PRD_MODL = BPB6030_AWA_6030.PRD_MODL;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CD;
        SCCFMT.DATA_PTR = WS_FMT_DATA;
        SCCFMT.DATA_LEN = 25;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZSMPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_PRDT_INF_MAINT, BPCSMPRD);
    }
    public void S000_CALL_BPZPQPDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_P_INQ_PRD_MODEL, BPCPQPDM);
        CEP.TRC(SCCGWA, BPCPQPDM.RC);
        if (BPCPQPDM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPDM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_SET_SBUS_TRN, SCCSUBS);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
