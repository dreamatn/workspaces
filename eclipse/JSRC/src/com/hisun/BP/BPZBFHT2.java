package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRCWAT;

public class BPZBFHT2 {
    boolean pgmRtn = false;
    String PROD_CD_01 = "RDP00002";
    String PROD_CD_02 = "RDP00495";
    String WS_ERR_MSG = " ";
    int WS_LENGTH = 0;
    long WS_RCD_SEQ = 0;
    char WS_PROD_CD_FLG = ' ';
    short WS_MAX_JRN_SEQ = 0;
    int WS_STR_DATE = 0;
    int WS_END_DATE = 0;
    int WS_STR_TR_DATE = 0;
    int WS_END_TR_DATE = 0;
    int WS_STR_TR_TM = 0;
    int WS_END_TR_TM = 0;
    double WS_STR_AMT = 0;
    double WS_END_AMT = 0;
    String WS_AC = " ";
    String WS_TX_CCY = " ";
    String WS_REF_NO = " ";
    long WS_JRNNO = 0;
    String WS_TX_TOOL = " ";
    String WS_TX_TELR = " ";
    String WS_TX_CD = " ";
    String WS_CI_NO = " ";
    int WS_TX_BR = 0;
    short WS_TX_DP = 0;
    char WS_TX_DC = ' ';
    String WS_MAKER_ID = " ";
    String WS_CHECKER_ID = " ";
    int WS_COUNT_NO = 0;
    String WS_PROD_CD = " ";
    String WS_PROD_CD_01 = " ";
    String WS_PROD_CD_02 = " ";
    char WS_BRW_FHIS_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    BPRFHIS BPRFHIS2 = new BPRFHIS();
    SCCGWA SCCGWA;
    BPCBFHTD BPCBFHT1;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCRCWAT SCRCWA;
    BPRFHISA BPRFHISM;
    public void MP(SCCGWA SCCGWA, BPCBFHTD BPCBFHT1) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCBFHT1 = BPCBFHT1;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZBFHT2 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRFHISM = (BPRFHIST) BPCBFHT1.INPUT.REC_PT;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
        IBS.init(SCCGWA, BPRFHIS2);
        WS_LENGTH = 690;
        CEP.TRC(SCCGWA, WS_LENGTH);
        CEP.TRC(SCCGWA, BPCBFHT1.INPUT.REC_LEN);
        if (WS_LENGTH == BPCBFHT1.INPUT.REC_LEN) {
            CEP.TRC(SCCGWA, "WS-LENGTH = BFHT1-REC-LEN");
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRFHISM);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[1], BPRFHIS2);
