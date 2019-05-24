package com.hisun.AI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPCQBKPM;
import com.hisun.BP.BPCSGSEQ;
import com.hisun.BP.BPRCTL;
import com.hisun.BP.BPRTLT;
import com.hisun.BP.BPRTRT;
import com.hisun.CI.CICCUST;
import com.hisun.SC.SCCAWAC;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGAPV;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCPRMR;

public class AIOMP14 {
    boolean pgmRtn = false;
    short K_FLD_CNT = 150;
    String CPN_REC_NHIS = "BP-REC-NHIS ";
    String K_HIS_REMARKS = "TEMPLATE MAINTAIN";
    String WS_ERR_MSG = " ";
    int WS_NUM = 0;
    char WS_FUNC = ' ';
    short WS_I = 0;
    short WS_OUT_ODE_CNT = 0;
    String WS_VIL_TYP = "   ";
    AIOMP14_WS_TEMPNO_INFO WS_TEMPNO_INFO = new AIOMP14_WS_TEMPNO_INFO();
    AIOMP14_WS_TEMP_INFO WS_TEMP_INFO = new AIOMP14_WS_TEMP_INFO();
    AIOMP14_WS_GRPT_INFO WS_GRPT_INFO = new AIOMP14_WS_GRPT_INFO();
    AIOMP14_WS_INQ_INFO WS_INQ_INFO = new AIOMP14_WS_INQ_INFO();
    AIOMP14_WS_ODET_INFO WS_ODET_INFO = new AIOMP14_WS_ODET_INFO();
    AIOMP14_WS_INFO WS_INFO = new AIOMP14_WS_INFO();
    short WS_MP_CNT = 0;
    short WS_K = 0;
    AIOMP14_WS_T_CTL WS_T_CTL = new AIOMP14_WS_T_CTL();
    AIOMP14_REDEFINES143 REDEFINES143 = new AIOMP14_REDEFINES143();
    AIOMP14_WS_D_FLD WS_D_FLD = new AIOMP14_WS_D_FLD();
    AIOMP14_WS_OUT_DATA WS_OUT_DATA = new AIOMP14_WS_OUT_DATA();
    int WS_DEL_TMP_NO = 0;
    String WS_DEL_TMPL_NAME = " ";
    char AIOMP14_FILLER3 = 0X02;
    int WS_DEL_CNT = 0;
    String WS_TMP_INFO = " ";
    short WS_TMP_LEN = 0;
    short WS_NAME_LEN = 0;
    List<AIOMP14_WS_DEL_OCCURS> WS_DEL_OCCURS = new ArrayList<AIOMP14_WS_DEL_OCCURS>();
    char WS_RETURN_GRPT_INFO = ' ';
    char WS_RETURN_ODET_INFO = ' ';
    char WS_RETURN_MST_INFO = ' ';
    AIOMP14_WS_INFO WS_INFO = new AIOMP14_WS_INFO();
    int WS_AIRGRPT_FLG = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    AIRGRPT AIRGRPT = new AIRGRPT();
    AICPQITM AICPQITM = new AICPQITM();
    AIRODET AIRODET = new AIRODET();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRCTL BPRCTLD = new BPRCTL();
    SCCPRMR SCCPRMR = new SCCPRMR();
    BPCQBKPM BPCQBKPM = new BPCQBKPM();
    AICHODET AICNODET = new AICHODET();
    AICHODET AICOODET = new AICHODET();
    AICHGRPT AICNGRPT = new AICHGRPT();
    AICHGRPT AICOGRPT = new AICHGRPT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    CICCUST CICCUST = new CICCUST();
    AICPQMIB AICPQMIB = new AICPQMIB();
    AICX507 AICX507 = new AICX507();
    AICQ01 AICQ01 = new AICQ01();
    AICQMST AICQMST = new AICQMST();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGAPV SCCGAPV;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTRT BPRTRTT;
    BPRTLT BPRTLT;
    SCCAWAC SCCAWAC;
    AIB1780_AWA_1780 AIB1780_AWA_1780;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIOMP14 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICOGRPT);
        IBS.init(SCCGWA, AICNGRPT);
        IBS.init(SCCGWA, AICOODET);
        IBS.init(SCCGWA, AICNODET);
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.init(SCCGWA, SCCEXCP);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        BPRTRTT = (BPRTRT) GWA_SC_AREA.TR_PARM_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB1780_AWA_1780>");
        AIB1780_AWA_1780 = (AIB1780_AWA_1780) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, BPRTRTT.DATA_TXT.PGM[SCCGWA.COMM_AREA.PGM_NO-1].PGM_CTL, WS_T_CTL);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_T_CTL);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], REDEFINES143);
        CEP.TRC(SCCGWA, WS_T_CTL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        R00_SEARCH_D_TABLE();
        if (pgmRtn) return;
        WS_MP_CNT = (short) BPRCTLD.DATA_TXT.FLD_CNT;
        for (WS_K = 1; WS_K <= WS_MP_CNT; WS_K += 1) {
            IBS.CPY2CLS(SCCGWA, BPRCTLD.DATA_TXT.FLD_TXT.FLD.get(WS_K-1).FLD_NAME, WS_D_FLD.WS_STEP);
            WS_D_FLD.WS_D_NAME = IBS.CLS2CPY(SCCGWA, WS_D_FLD.WS_STEP);