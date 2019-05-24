package com.hisun.BP;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT8080 {
    String JIBS_tmp_str[] = new String[10];
    String CPN_S_BPZSSHIS = "BP-S-BROW-HIST";
    String CPN_P_QUE_ACTY = "DC-INQ-AC-INF";
    short WK_DEFAULT_CNT = 10;
    BPOT8080_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT8080_WS_TEMP_VARIABLE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCSSHIS BPCSSHIS = new BPCSSHIS();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCGWA SCCGWA;
    BPB8080_AWA_8080 BPB8080_AWA_8080;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT8080 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB8080_AWA_8080>");
        BPB8080_AWA_8080 = (BPB8080_AWA_8080) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        IBS.init(SCCGWA, BPCSSHIS);
        B030_BROWSE_HIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB8080_AWA_8080.REC_FLG);
        CEP.TRC(SCCGWA, BPB8080_AWA_8080.JUMP_FLG);
        CEP.TRC(SCCGWA, BPB8080_AWA_8080.DC_FLG);
        CEP.TRC(SCCGWA, BPB8080_AWA_8080.BR);
        CEP.TRC(SCCGWA, BPB8080_AWA_8080.TX_TLR);
        CEP.TRC(SCCGWA, BPB8080_AWA_8080.SORT_FLG);
        CEP.TRC(SCCGWA, BPB8080_AWA_8080.STR_DT);
        CEP.TRC(SCCGWA, BPB8080_AWA_8080.END_DT);
        if (BPB8080_AWA_8080.STR_DT == 0 
            && BPB8080_AWA_8080.END_DT == 0) {
            BPB8080_AWA_8080.STR_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPB8080_AWA_8080.END_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPB8080_AWA_8080.STR_DT > BPB8080_AWA_8080.END_DT) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_STR_GT_END;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB8080_AWA_8080.STR_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, BPB8080_AWA_8080.PG_NO);
        if (BPB8080_AWA_8080.PG_NO < 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PAGE_NO_MUST;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB8080_AWA_8080.LIST_CNT);
        if (BPB8080_AWA_8080.LIST_CNT == 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PAGE_RECNO_NONE;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB8080_AWA_8080.AC);
        CEP.TRC(SCCGWA, BPB8080_AWA_8080.TX_TOOL);
        if (BPB8080_AWA_8080.AC.trim().length() == 0 
            && BPB8080_AWA_8080.TX_TOOL.trim().length() == 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_MUST_INPUT;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB8080_AWA_8080.AC_NO;
        }
        if (BPB8080_AWA_8080.AC.trim().length() > 0) {
            IBS.init(SCCGWA, DCCPACTY);
            DCCPACTY.INPUT.AC = BPB8080_AWA_8080.AC;
            DCCPACTY.INPUT.CCY = BPB8080_AWA_8080.TX_CCY;
            S000_CALL_DCZPACTY();
            CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
            CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_AC);
            if (DCCPACTY.OUTPUT.AC_TYPE == 'K') {
                BPB8080_AWA_8080.TX_TOOL = DCCPACTY.OUTPUT.N_CARD_NO;
                BPB8080_AWA_8080.AC = " ";
            } else {
                BPB8080_AWA_8080.AC = DCCPACTY.OUTPUT.STD_AC;
            }
        }
        CEP.TRC(SCCGWA, BPB8080_AWA_8080.TOT_RC);
        CEP.TRC(SCCGWA, BPB8080_AWA_8080.TOD_RC);
        CEP.TRC(SCCGWA, "AAA");
        WS_TEMP_VARIABLE.WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        WS_TEMP_VARIABLE.WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
        CEP.TRC(SCCGWA, "BBB");
    }
    public void B030_BROWSE_HIST() throws IOException,SQLException,Exception {
        BPCSSHIS.DATA.STR_AC_DT = BPB8080_AWA_8080.STR_DT;
        CEP.TRC(SCCGWA, BPCSSHIS.DATA.STR_AC_DT);
        BPCSSHIS.DATA.END_AC_DT = BPB8080_AWA_8080.END_DT;
        CEP.TRC(SCCGWA, BPCSSHIS.DATA.END_AC_DT);
        BPCSSHIS.DATA.TX_TOOL = BPB8080_AWA_8080.TX_TOOL;
        CEP.TRC(SCCGWA, BPCSSHIS.DATA.TX_TOOL);
        BPCSSHIS.DATA.JRNNO = BPB8080_AWA_8080.JRNNO;
        CEP.TRC(SCCGWA, BPCSSHIS.DATA.JRNNO);
        BPCSSHIS.DATA.AC = BPB8080_AWA_8080.AC;
        CEP.TRC(SCCGWA, BPCSSHIS.DATA.AC);
        BPCSSHIS.DATA.PROD_CD = BPB8080_AWA_8080.PROD_CD;
        CEP.TRC(SCCGWA, BPCSSHIS.DATA.PROD_CD);
        BPCSSHIS.DATA.CCY = BPB8080_AWA_8080.TX_CCY;
        CEP.TRC(SCCGWA, BPCSSHIS.DATA.CCY);
        BPCSSHIS.DATA.DC_FLG = BPB8080_AWA_8080.DC_FLG;
        CEP.TRC(SCCGWA, BPCSSHIS.DATA.DC_FLG);
        BPCSSHIS.DATA.TX_CD = BPB8080_AWA_8080.TX_CD;
        CEP.TRC(SCCGWA, BPCSSHIS.DATA.TX_CD);
        BPCSSHIS.DATA.TLR = BPB8080_AWA_8080.TX_TLR;
        CEP.TRC(SCCGWA, BPCSSHIS.DATA.TLR);
        BPCSSHIS.DATA.BR = BPB8080_AWA_8080.BR;
        CEP.TRC(SCCGWA, BPCSSHIS.DATA.BR);
        BPCSSHIS.DATA.SORT_FLG = BPB8080_AWA_8080.SORT_FLG;
        CEP.TRC(SCCGWA, BPCSSHIS.DATA.SORT_FLG);
        BPCSSHIS.DATA.JUMP_FLG = BPB8080_AWA_8080.JUMP_FLG;
        CEP.TRC(SCCGWA, BPCSSHIS.DATA.JUMP_FLG);
        BPCSSHIS.DATA.PAGE_NO = BPB8080_AWA_8080.PG_NO;
        CEP.TRC(SCCGWA, BPCSSHIS.DATA.PAGE_NO);
        BPCSSHIS.DATA.PAGE_ROW = BPB8080_AWA_8080.LIST_CNT;
        CEP.TRC(SCCGWA, BPCSSHIS.DATA.PAGE_ROW);
        BPCSSHIS.DATA.TOT_REC = BPB8080_AWA_8080.TOT_RC;
        CEP.TRC(SCCGWA, BPCSSHIS.DATA.TOT_REC);
        BPCSSHIS.DATA.TOD_REC = BPB8080_AWA_8080.TOD_RC;
        CEP.TRC(SCCGWA, BPCSSHIS.DATA.TOD_REC);
        BPCSSHIS.DATA.LONG_OUTPUT = 'Y';
        CEP.TRC(SCCGWA, BPCSSHIS.DATA.LONG_OUTPUT);
        S000_CALL_BPZSSHIS();
    }
    public void S000_CALL_BPZSSHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BPZSSHIS, BPCSSHIS);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSSHIS.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSSHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_QUE_ACTY, DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG, WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG, WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG, WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}