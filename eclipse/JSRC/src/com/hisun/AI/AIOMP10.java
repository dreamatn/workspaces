package com.hisun.AI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCFTLRQ;
import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCOQPCD;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPCSBORG;
import com.hisun.BP.BPCSIC;
import com.hisun.BP.BPCTBRGN;
import com.hisun.BP.BPRCTL;
import com.hisun.BP.BPRRGND;
import com.hisun.BP.BPRTRT;
import com.hisun.SC.SCCAWAC;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGMSG;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

public class AIOMP10 {
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    String K_HIS_REMARKS_ITAD = "ITAD DETAILS INFO ";
    String K_HIS_REMARKS_ITUS = "ITUS DETAILS INFO ";
    String K_D_TABLE = "D";
    String K_SINGLE_DEL_FMT = "AI518";
    String K_ADD_DEL_FMT = "AI519";
    String K_ADJUST_COA_FMT = "AI521";
    String K_ADJUST_COA_ADD_FMT = "AI522";
    String K_INQ_FMT = "AIX01";
    String K_TBL_RGND = "BPTRGND ";
    short K_MAX_COL_NO = 100;
    String CPN_S_BRW_RGND_ORG = "BP-S-BRW-RGND-ORG ";
    int WS_BR = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_K = 0;
    long WS_IDX = 0;
    char WS_FLG = ' ';
    String WS_ITMNO = " ";
    short WS_COL_CNT = 0;
    int WS_BR_CNT = 0;
    int WS_CNT = 0;
    int WS_RCD_SEQ = 0;
    String WS_ERR_MSG = " ";
    String WS_INFO = " ";
    String WS_ITM_NO_TMP = " ";
    int WS_BR_TMP = 0;
    AIOMP10_WS_OUTPUT_ITUS WS_OUTPUT_ITUS = new AIOMP10_WS_OUTPUT_ITUS();
    AIOMP10_WS_COA_OLD_INF[] WS_COA_OLD_INF = new AIOMP10_WS_COA_OLD_INF[5];
    String WS_TYPE = " ";
    char WS_POST_TYPE = ' ';
    String WS_ENG_NM_NEW = " ";
    String WS_CHS_NM_NEW = " ";
    String WS_TYPE_NEW = " ";
    char WS_POST_TYPE_NEW = ' ';
    int WS_C_IDX = 0;
    int WS_D_IDX = 0;
    AIOMP10_WS_D_FLD WS_D_FLD = new AIOMP10_WS_D_FLD();
    String WS_T_CTL = " ";
    AIOMP10_REDEFINES43 REDEFINES43 = new AIOMP10_REDEFINES43();
    AIOMP10_REDEFINES52 REDEFINES52 = new AIOMP10_REDEFINES52();
    AIOMP10_WS_RGN_NO WS_RGN_NO = new AIOMP10_WS_RGN_NO();
    List<AIOMP10_WS_ACCT_CNTR> WS_ACCT_CNTR = new ArrayList<AIOMP10_WS_ACCT_CNTR>();
    String WS_ITM_NO = " ";
    String WS_COA_FR = " ";
    String WS_COA_TO = " ";
    int WS_PROC_DATE = 0;
    int WS_BR_SQL = 0;
    AIOMP10_WS_CONSTANT WS_CONSTANT = new AIOMP10_WS_CONSTANT();
    char WS_AIRITUS_FLG = ' ';
    char WS_COA_FLG = ' ';
    char WS_BR_FLG = ' ';
    char WS_AIRITM_FLG = ' ';
    char WS_DUEKEY_FLG = ' ';
    char WS_TST_FLG = ' ';
    char WS_ITM_FLG = ' ';
    char WS_ADD_ITM_FLG = ' ';
    char WS_ADD_BR_FLG = ' ';
    char WS_DEL_ITM_FLG = ' ';
    char WS_DEL_BR_FLG = ' ';
    BPRCTL BPRCTLD = new BPRCTL();
    SCCPAGE SCCPAGE = new SCCPAGE();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AIRITUS AIROITUS = new AIRITUS();
    AIRITUS AIRNITUS = new AIRITUS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    AICITAD1 AICITAD1 = new AICITAD1();
    AICX518 AICX518 = new AICX518();
    AICX519 AICX519 = new AICX519();
    AICX520 AICX520 = new AICX520();
    AICX522 AICX522 = new AICX522();
    BPCSIC BPCSIC = new BPCSIC();
    AICPQITM AICPQITM = new AICPQITM();
    AIRGPBR AIRGPBR = new AIRGPBR();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPRMM BPCPRMM = new BPCPRMM();
    SCCFMT SCCFMT = new SCCFMT();
    BPCTBRGN BPCTBRGN = new BPCTBRGN();
    BPRRGND BPRRGND = new BPRRGND();
    BPCSBORG BPCSBORG = new BPCSBORG();
    AIRITUS AIRITUS = new AIRITUS();
    AIRITM AIRITM = new AIRITM();
    AIRMIB AIRMIB = new AIRMIB();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AICRMIB AICRMIB = new AICRMIB();
    AICSMIB AICSMIB = new AICSMIB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGMSG SCCGMSG;
    BPRTRT BPRTRTT;
    AIB0010_AWA_0010 AIB0010_AWA_0010;
    SCCAWAC SCCAWAC;
    public AIOMP10() {
        for (int i=0;i<5;i++) WS_COA_OLD_INF[i] = new AIOMP10_WS_COA_OLD_INF();
    }
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIOMP10 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPRTRTT = (BPRTRT) SCCGSCA_SC_AREA.TR_PARM_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB0010_AWA_0010>");
        AIB0010_AWA_0010 = (AIB0010_AWA_0010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
        IBS.init(SCCGWA, SCCMSG);
        IBS.init(SCCGWA, AIRITUS);
        CEP.TRC(SCCGWA, BPRTRTT.DATA_TXT.PGM[SCCGWA.COMM_AREA.PGM_NO-1].PGM_CTL);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCTBR[1-1].ACCT_CTR);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.COA_FLG);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.COA_FR);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.COA_TO);
        WS_T_CTL = BPRTRTT.DATA_TXT.PGM[SCCGWA.COMM_AREA.PGM_NO-1].PGM_CTL;
        IBS.CPY2CLS(SCCGWA, WS_T_CTL, REDEFINES43);
        IBS.CPY2CLS(SCCGWA, WS_T_CTL, REDEFINES52);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        R000_SEARCH_D_TABLE();
        if (pgmRtn) return;
        for (WS_D_IDX = 1; WS_D_IDX <= BPRCTLD.DATA_TXT.FLD_CNT; WS_D_IDX += 1) {
            IBS.CPY2CLS(SCCGWA, BPRCTLD.DATA_TXT.FLD_TXT.FLD.get(WS_D_IDX-1).FLD_NAME, WS_D_FLD.WS_D_NAME);
            CEP.TRC(SCCGWA, WS_D_FLD.WS_D_NAME);