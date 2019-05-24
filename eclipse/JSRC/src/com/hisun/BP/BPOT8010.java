package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT8010 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_S_INQ_HIST = "BP-S-INQ-HIST";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSIHIS BPCSIHIS = new BPCSIHIS();
    SCCGWA SCCGWA;
    BPB8030_AWA_8030 BPB8030_AWA_8030;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT8010 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB8030_AWA_8030>");
        BPB8030_AWA_8030 = (BPB8030_AWA_8030) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BROWSE_HIST();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB8030_AWA_8030.STR_DT == 0 
            && BPB8030_AWA_8030.END_DT == 0 
            && BPB8030_AWA_8030.TX_DATES[1-1].TX_DATE == 0 
            && BPB8030_AWA_8030.TX_DATES[2-1].TX_DATE == 0) {
            BPB8030_AWA_8030.STR_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPB8030_AWA_8030.END_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPB8030_AWA_8030.STR_DT > BPB8030_AWA_8030.END_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_STR_GT_END;
            WS_FLD_NO = BPB8030_AWA_8030.STR_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BPB8030_AWA_8030.TX_DATES[1-1].TX_DATE > BPB8030_AWA_8030.TX_DATES[2-1].TX_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_STR_GT_END;
            WS_FLD_NO = BPB8030_AWA_8030.TX_DATES[1-1].TX_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BPB8030_AWA_8030.TX_TIMES[1-1].TX_TIME > BPB8030_AWA_8030.TX_TIMES[2-1].TX_TIME) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_STR_GT_END_TIME;
            WS_FLD_NO = BPB8030_AWA_8030.TX_TIMES[1-1].TX_TIME_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BPB8030_AWA_8030.TX_AMTS[1-1].TX_AMT > BPB8030_AWA_8030.TX_AMTS[2-1].TX_AMT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_STR_GT_END_AMT;
            WS_FLD_NO = BPB8030_AWA_8030.TX_AMTS[1-1].TX_AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BPB8030_AWA_8030.STR_DT != 0 
            && BPB8030_AWA_8030.END_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_SAME_TIME;
            WS_FLD_NO = BPB8030_AWA_8030.END_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BPB8030_AWA_8030.END_DT != 0 
            && BPB8030_AWA_8030.STR_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_SAME_TIME;
            WS_FLD_NO = BPB8030_AWA_8030.STR_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BPB8030_AWA_8030.TX_DATES[1-1].TX_DATE != 0 
            && BPB8030_AWA_8030.TX_DATES[2-1].TX_DATE == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_SAME_TIME;
            WS_FLD_NO = BPB8030_AWA_8030.TX_DATES[2-1].TX_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BPB8030_AWA_8030.TX_DATES[2-1].TX_DATE != 0 
            && BPB8030_AWA_8030.TX_DATES[1-1].TX_DATE == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_SAME_TIME;
            WS_FLD_NO = BPB8030_AWA_8030.TX_DATES[1-1].TX_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BPB8030_AWA_8030.TX_TIMES[1-1].TX_TIME != 0 
            && BPB8030_AWA_8030.TX_TIMES[2-1].TX_TIME == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_SAME_TIME;
            WS_FLD_NO = BPB8030_AWA_8030.TX_TIMES[2-1].TX_TIME_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BPB8030_AWA_8030.TX_TIMES[2-1].TX_TIME != 0 
            && BPB8030_AWA_8030.TX_TIMES[1-1].TX_TIME == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_SAME_TIME;
            WS_FLD_NO = BPB8030_AWA_8030.TX_TIMES[1-1].TX_TIME_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BPB8030_AWA_8030.TX_AMTS[1-1].TX_AMT != 0 
            && BPB8030_AWA_8030.TX_AMTS[2-1].TX_AMT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_SAME_TIME;
            WS_FLD_NO = BPB8030_AWA_8030.TX_AMTS[2-1].TX_AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BPB8030_AWA_8030.TX_AMTS[2-1].TX_AMT != 0 
            && BPB8030_AWA_8030.TX_AMTS[1-1].TX_AMT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_SAME_TIME;
            WS_FLD_NO = BPB8030_AWA_8030.TX_AMTS[1-1].TX_AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B020_BROWSE_HIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSIHIS);
        BPCSIHIS.DATA.JRNNO = BPB8030_AWA_8030.JRNNO;
        BPCSIHIS.DATA.CI_NO = BPB8030_AWA_8030.CI_NO;
        BPCSIHIS.DATA.AC = BPB8030_AWA_8030.AC;
        BPCSIHIS.DATA.REF_NO = BPB8030_AWA_8030.REF_NO;
        BPCSIHIS.DATA.TX_TOOL = BPB8030_AWA_8030.TX_TOOL;
        BPCSIHIS.DATA.CCY = BPB8030_AWA_8030.TX_CCY;
        BPCSIHIS.DATA.STR_AMT = BPB8030_AWA_8030.TX_AMTS[1-1].TX_AMT;
        BPCSIHIS.DATA.END_AMT = BPB8030_AWA_8030.TX_AMTS[2-1].TX_AMT;
        BPCSIHIS.DATA.TX_CD = BPB8030_AWA_8030.TX_CD;
        BPCSIHIS.DATA.TX_TYPE_CD = BPB8030_AWA_8030.TYP_CD;
        BPCSIHIS.DATA.TLR = BPB8030_AWA_8030.TX_TLR;
        BPCSIHIS.DATA.STR_AC_DT = BPB8030_AWA_8030.STR_DT;
        BPCSIHIS.DATA.END_AC_DT = BPB8030_AWA_8030.END_DT;
        BPCSIHIS.DATA.STR_TX_DT = BPB8030_AWA_8030.TX_DATES[1-1].TX_DATE;
        BPCSIHIS.DATA.END_TX_DT = BPB8030_AWA_8030.TX_DATES[2-1].TX_DATE;
        BPCSIHIS.DATA.STR_TX_TM = BPB8030_AWA_8030.TX_TIMES[1-1].TX_TIME;
        BPCSIHIS.DATA.END_TX_TM = BPB8030_AWA_8030.TX_TIMES[2-1].TX_TIME;
        BPCSIHIS.DATA.REC_NUM = BPB8030_AWA_8030.REC_NUM;
        BPCSIHIS.DATA.REC_FLG = BPB8030_AWA_8030.REC_FLG;
        CEP.TRC(SCCGWA, BPCSIHIS);
        S000_CALL_BPZSIHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZSIHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_INQ_HIST, BPCSIHIS);
        CEP.TRC(SCCGWA, BPCSIHIS.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSIHIS.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSIHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
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
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
