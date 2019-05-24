package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4801 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String CPN_S_TXIF = "BP-S-MGM-TXIF    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC         ";
    String K_PARM_CONNO = "CONNO";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSMTXI BPCSMTXI = new BPCSMTXI();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB4800_AWA_4800 BPB4800_AWA_4800;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4801 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4800_AWA_4800>");
        BPB4800_AWA_4800 = (BPB4800_AWA_4800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4800_AWA_4800.IN_FLG);
        CEP.TRC(SCCGWA, BPB4800_AWA_4800.SYS_MMO);
        CEP.TRC(SCCGWA, BPB4800_AWA_4800.TX_CD);
        CEP.TRC(SCCGWA, BPB4800_AWA_4800.STS);
        CEP.TRC(SCCGWA, BPB4800_AWA_4800.TX_LVL);
        CEP.TRC(SCCGWA, BPB4800_AWA_4800.ATH_LVL1);
        CEP.TRC(SCCGWA, BPB4800_AWA_4800.ATH_LVL2);
        CEP.TRC(SCCGWA, BPB4800_AWA_4800.SELF_FLG);
        CEP.TRC(SCCGWA, BPB4800_AWA_4800.COR_FLG);
        CEP.TRC(SCCGWA, BPB4800_AWA_4800.BAT_FLG);
        CEP.TRC(SCCGWA, BPB4800_AWA_4800.SATH_FLG);
        CEP.TRC(SCCGWA, BPB4800_AWA_4800.STSW_ARR[1-1].STSW);
        CEP.TRC(SCCGWA, BPB4800_AWA_4800.STSW_ARR[2-1].STSW);
        CEP.TRC(SCCGWA, BPB4800_AWA_4800.STSW_ARR[3-1].STSW);
        CEP.TRC(SCCGWA, BPB4800_AWA_4800.STSW_ARR[4-1].STSW);
        CEP.TRC(SCCGWA, BPB4800_AWA_4800.STSW_ARR[5-1].STSW);
        CEP.TRC(SCCGWA, BPB4800_AWA_4800.TAB_CD);
        CEP.TRC(SCCGWA, BPB4800_AWA_4800.EDESC);
        CEP.TRC(SCCGWA, BPB4800_AWA_4800.CDESC);
        if (BPB4800_AWA_4800.TAB_CD.trim().length() > 0) {
            IBS.init(SCCGWA, BPCOQPCD);
            BPCOQPCD.INPUT_DATA.TYPE = K_PARM_CONNO;
            BPCOQPCD.INPUT_DATA.CODE = BPB4800_AWA_4800.TAB_CD;
            S000_CALL_BPZPQPCD();
            if (BPCOQPCD.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
                WS_FLD_NO = BPB4800_AWA_4800.TAB_CD_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_ADD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMTXI);
        BPCSMTXI.FUNC = 'A';
        BPCSMTXI.IN_FLG = BPB4800_AWA_4800.IN_FLG;
        BPCSMTXI.SYS_MMO = BPB4800_AWA_4800.SYS_MMO;
        BPCSMTXI.TX_CD = BPB4800_AWA_4800.TX_CD;
        BPCSMTXI.STS = BPB4800_AWA_4800.STS;
        BPCSMTXI.TX_LVL = BPB4800_AWA_4800.TX_LVL;
        BPCSMTXI.ATH_LVL1 = BPB4800_AWA_4800.ATH_LVL1;
        BPCSMTXI.ATH_LVL2 = BPB4800_AWA_4800.ATH_LVL2;
        BPCSMTXI.SELF_ATH_FLG = BPB4800_AWA_4800.SELF_FLG;
        BPCSMTXI.COR_ALW_FLG = BPB4800_AWA_4800.COR_FLG;
        BPCSMTXI.BAT_ATH_FLG = BPB4800_AWA_4800.BAT_FLG;
        BPCSMTXI.SATH_FLG = BPB4800_AWA_4800.SATH_FLG;
        if (BPCSMTXI.STSW == null) BPCSMTXI.STSW = "";
        JIBS_tmp_int = BPCSMTXI.STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSMTXI.STSW += " ";
        JIBS_tmp_str[0] = "" + BPB4800_AWA_4800.STSW_ARR[1-1].STSW;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSMTXI.STSW = JIBS_tmp_str[0] + BPCSMTXI.STSW.substring(1);
        if (BPCSMTXI.STSW == null) BPCSMTXI.STSW = "";
        JIBS_tmp_int = BPCSMTXI.STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSMTXI.STSW += " ";
        JIBS_tmp_str[0] = "" + BPB4800_AWA_4800.STSW_ARR[2-1].STSW;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSMTXI.STSW = BPCSMTXI.STSW.substring(0, 2 - 1) + JIBS_tmp_str[0] + BPCSMTXI.STSW.substring(2 + 1 - 1);
        if (BPCSMTXI.STSW == null) BPCSMTXI.STSW = "";
        JIBS_tmp_int = BPCSMTXI.STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSMTXI.STSW += " ";
        JIBS_tmp_str[0] = "" + BPB4800_AWA_4800.STSW_ARR[3-1].STSW;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSMTXI.STSW = BPCSMTXI.STSW.substring(0, 3 - 1) + JIBS_tmp_str[0] + BPCSMTXI.STSW.substring(3 + 1 - 1);
        if (BPCSMTXI.STSW == null) BPCSMTXI.STSW = "";
        JIBS_tmp_int = BPCSMTXI.STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSMTXI.STSW += " ";
        JIBS_tmp_str[0] = "" + BPB4800_AWA_4800.STSW_ARR[4-1].STSW;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSMTXI.STSW = BPCSMTXI.STSW.substring(0, 4 - 1) + JIBS_tmp_str[0] + BPCSMTXI.STSW.substring(4 + 1 - 1);
        if (BPCSMTXI.STSW == null) BPCSMTXI.STSW = "";
        JIBS_tmp_int = BPCSMTXI.STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSMTXI.STSW += " ";
        JIBS_tmp_str[0] = "" + BPB4800_AWA_4800.STSW_ARR[5-1].STSW;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSMTXI.STSW = BPCSMTXI.STSW.substring(0, 5 - 1) + JIBS_tmp_str[0] + BPCSMTXI.STSW.substring(5 + 1 - 1);
        BPCSMTXI.RUN_MODE = BPB4800_AWA_4800.RUN_MODE;
        BPCSMTXI.TAB_CD = BPB4800_AWA_4800.TAB_CD;
        BPCSMTXI.EDESC = BPB4800_AWA_4800.EDESC;
        BPCSMTXI.CDESC = BPB4800_AWA_4800.CDESC;
        BPCSMTXI.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCSMTXI.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        S00_CALL_BPZSMTXI();
    }
    public void S00_CALL_BPZSMTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TXIF, BPCSMTXI);
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
