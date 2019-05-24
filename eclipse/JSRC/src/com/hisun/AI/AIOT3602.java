package com.hisun.AI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCF3602;
import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCO3602;
import com.hisun.BP.BPCPORUP_DATA_INFO;
import com.hisun.BP.BPCUIBAL;
import com.hisun.BP.BPRFHIS;
import com.hisun.BP.BPRFHIST;
import com.hisun.CI.CICQACAC;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRCWAT;

public class AIOT3602 {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BPW05";
    String WS_ACO_AC = " ";
    int WS_STR_DT = 0;
    int WS_END_DT = 0;
    double WS_STR_AMT = 0;
    double WS_END_AMT = 0;
    int WS_LEN = 0;
    int WS_I = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    double WS_CUR_BAL = 0;
    AIOT3602_WS_BR_AC WS_BR_AC = new AIOT3602_WS_BR_AC();
    AIOT3602_WS_STR_POS WS_STR_POS = new AIOT3602_WS_STR_POS();
    AIOT3602_WS_TXT_BIG[] WS_TXT_BIG = new AIOT3602_WS_TXT_BIG[50];
    AIOT3602_WS_TXT_BIG_ACO[] WS_TXT_BIG_ACO = new AIOT3602_WS_TXT_BIG_ACO[50];
    AIOT3602_WS_OUTPUT_DATE WS_OUTPUT_DATE = new AIOT3602_WS_OUTPUT_DATE();
    char WS_FRZ_FLG = ' ';
    AIOT3602_WS_FILE_NAME WS_FILE_NAME = new AIOT3602_WS_FILE_NAME();
    char WS_BR_FLG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    AICRMIB AICRMIB = new AICRMIB();
    AIRMIB AIRMIB = new AIRMIB();
    AIRHMIB AIRHMIB = new AIRHMIB();
    AICRHMIB AICRHMIB = new AICRHMIB();
    AICF351 AICF351 = new AICF351();
    CICQACAC CICQACAC = new CICQACAC();
    BPRFHIS BPRFHIS1 = new BPRFHIS();
    BPRFHIS BPRFHIS2 = new BPRFHIS();
    BPRFHIS BPRFHIS = new BPRFHIS();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPCF3602 BPCF3602 = new BPCF3602();
    BPCO3602 BPCO3602 = new BPCO3602();
    BPCUIBAL BPCUIBAL = new BPCUIBAL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCRCWAT SCRCWA;
    AIB3602_AWA_3602 AIB3602_AWA_3602;
    BPCPORUP_DATA_INFO BPCPORUP;
    public AIOT3602() {
        for (int i=0;i<50;i++) WS_TXT_BIG[i] = new AIOT3602_WS_TXT_BIG();
        for (int i=0;i<50;i++) WS_TXT_BIG_ACO[i] = new AIOT3602_WS_TXT_BIG_ACO();
    }
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIOT3602 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB3602_AWA_3602>");
        AIB3602_AWA_3602 = (AIB3602_AWA_3602) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BRW_REC_PROC();
        if (pgmRtn) return;
        B030_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AIB3602_AWA_3602.STR_DT == 0 
            || AIB3602_AWA_3602.END_DT == 0 
            || AIB3602_AWA_3602.TXT_BIG.trim().length() == 0) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.MUST_INPUT);
        }
        if (AIB3602_AWA_3602.STR_DT > SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_STRDATE_GT_ACDATE);
        }
        if (AIB3602_AWA_3602.END_DT != 0 
            && AIB3602_AWA_3602.END_DT < AIB3602_AWA_3602.STR_DT) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.END_DT_LESS_STRDT);
        }
        if (AIB3602_AWA_3602.STR_DT < SCCGWA.COMM_AREA.AC_DATE 
            && AIB3602_AWA_3602.END_DT >= SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ENDDATE_LT_ACDATE);
        }
        JIBS_stb = new StringTokenizer(AIB3602_AWA_3602.TXT_BIG, ",");
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[01-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[02-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[03-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[04-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[05-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[06-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[07-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[08-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[09-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[10-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[11-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[12-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[13-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[14-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[15-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[16-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[17-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[18-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[19-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[20-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[21-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[22-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[23-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[24-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[25-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[26-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[27-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[28-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[29-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[30-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[31-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[32-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[33-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[34-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[35-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[36-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[37-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[38-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[39-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[40-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[41-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[42-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[43-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[44-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[45-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[46-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[47-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[48-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[49-1].WS_TXT_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_TXT_BIG[50-1].WS_TXT_AC = JIBS_stb.nextToken();
    }
    public void B020_BRW_REC_PROC() throws IOException,SQLException,Exception {
        WS_FILE_NAME.WS_OUT_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_FILE_NAME.WS_FILE_JRN = SCCGWA.COMM_AREA.JRN_NO;
