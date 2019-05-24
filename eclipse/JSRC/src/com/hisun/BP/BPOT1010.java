package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import com.hisun.DC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1010 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    brParm BPTFHIS1_BR = new brParm();
    brParm BPTFHIS2_BR = new brParm();
    brParm BPTFHIST_BR = new brParm();
    DBParm BPTFHISA_RD;
    DBParm BPTPARM_RD;
    boolean pgmRtn = false;
    String CPN_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC       ";
    String K_RBASE = "RBASE";
    String K_RTENO = "RTENO";
    String K_OUTPUT_FMT = "BP056";
    String K_PAMC_MMO = "MMO  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_REC_CNT = 0;
    String WS_TX_MMO_NM = " ";
    int WS_AC_HASH = 0;
    String WS_H_ACO_AC = " ";
    String WS_OUT_CI_NO = " ";
    double WS_OUT_CUR_BAL = 0;
    short WS_D = 0;
    int WS_T = 0;
    int WS_RECORD_NUM = 0;
    int WS_BR = 0;
    String WS_AGR_NO = " ";
    String WS_CCY = " ";
    String WS_BASE_TYP = " ";
    String WS_TENOR = " ";
    double WS_CUR_BAL = 0;
    String WS_TS_ACO_AC = " ";
    String WS_TX_AC_CHNM = " ";
    String WS_AGR_NO1 = " ";
    char WS_BROWS_COND = ' ';
    char WS_CARD_SUB = ' ';
    BPOT1010_WS_TS_QUEUE WS_TS_QUEUE = new BPOT1010_WS_TS_QUEUE();
    char WS_FRZ_FLG = ' ';
    char WS_ACAC_FLG = ' ';
    char WS_NHIST_FLG = ' ';
    char WS_BR_FLG = ' ';
    char WS_BPTFHISA_FLG = ' ';
    int WS_STR_DT = 0;
    int WS_END_DT = 0;
    int WS_SET_DT = 0;
    int WS_SET_TM = 0;
    String WS_ACO_AC = " ";
    int WS_PART_NO = 0;
    long WS_JRNNO = 0;
    short WS_JRN_SEQ = 0;
    int WS_TX_BR = 0;
    String WS_TX_TLR = " ";
    int WS_AC_SEQ = 0;
    String WS_TX_TOOL = " ";
    String WS_TX_CD = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOTLRQ BPCOTLRQ = new BPCOTLRQ();
    BPCFHTTT BPCFHTTT = new BPCFHTTT();
    BPRFHIS BPRFHIS = new BPRFHIS();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPCIFHIS BPCIFHIS = new BPCIFHIS();
    BPCQFHIS BPCQFHIS = new BPCQFHIS();
    CICQACAC CICQACAC = new CICQACAC();
    BPRFHIS BPRFHIS1 = new BPRFHIS();
    BPRFHIS BPRFHIS2 = new BPRFHIS();
    BPCUIBAL BPCUIBAL = new BPCUIBAL();
    BPCIPHIS BPCIPHIS = new BPCIPHIS();
    CICACCU CICACCU = new CICACCU();
    BPRNHIST BPRNHIST = new BPRNHIST();
    BPCTHIST BPCTHIST = new BPCTHIST();
    BPCPORUP BPCPORUP = new BPCPORUP();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPARMC BPCPARMC = new BPCPARMC();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRFHISA BPRFHISA = new BPRFHISA();
    DCCUCINF DCCUCINF = new DCCUCINF();
    CICQACRL CICQACRL = new CICQACRL();
    SCCGWA SCCGWA;
    BPB8030_AWA_8030 BPB8030_AWA_8030;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCRCWAT SCRCWA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT1010 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB8030_AWA_8030>");
        BPB8030_AWA_8030 = (BPB8030_AWA_8030) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        R000_OUTPUT_TITLE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "EEEE");
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.REC_FLG);
        if (BPB8030_AWA_8030.REC_FLG == 'N') {
            B030_MAIN_NFIN_PROC();
            if (pgmRtn) return;
        } else {
            B020_MAIN_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB8030_AWA_8030.STR_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB8030_AWA_8030.END_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB8030_AWA_8030.STR_DT > BPB8030_AWA_8030.END_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFFDT_GT_EXPDT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.STR_DT);
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.JRNNO);
        if (BPB8030_AWA_8030.END_DT > SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB8030_AWA_8030.REC_FLG == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FIN_FLG_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        if (BPB8030_AWA_8030.BR != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
            }
            if (BPCPQORG.TYP.equalsIgnoreCase("01") 
                || BPCPQORG.TYP.equalsIgnoreCase("02") 
                || BPCPQORG.TYP.equalsIgnoreCase("03")) {
                WS_BR_FLG = 'Y';
            } else {
                if (BPB8030_AWA_8030.BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                    WS_BR_FLG = 'Y';
                } else {
                    IBS.init(SCCGWA, BPCPORUP);
                    BPCPORUP.DATA_INFO.BR = BPB8030_AWA_8030.BR;
                    S000_CALL_BPZPORUP();
                    if (pgmRtn) return;
                    for (WS_T = 1; WS_T <= 20; WS_T += 1) {
                        if (BPCPORUP.DATA_INFO.SUPR_GRP[WS_T-1].SUPR_BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                            WS_BR_FLG = 'Y';
                        }
                    }
                }
            }
            if (WS_BR_FLG != 'Y') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NO_AUTO_INQUERY);
            }
        }
        if (BPB8030_AWA_8030.JRNNO == 0 
            && BPB8030_AWA_8030.AC.trim().length() == 0 
            && BPB8030_AWA_8030.TX_TOOL.trim().length() == 0 
            && BPB8030_AWA_8030.TX_TLR.trim().length() == 0 
            && BPB8030_AWA_8030.TX_CD.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INP_ONE_COND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB8030_AWA_8030.JRNNO != 0 
            && BPB8030_AWA_8030.STR_DT != 0) {
            CEP.TRC(SCCGWA, "BROWSE_BY_JRNNO");
            WS_BROWS_COND = '1';
        }
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.AC);
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.TX_TOOL);
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.JRNNO);
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.BR);
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.TX_TLR);
        if (BPB8030_AWA_8030.AC.trim().length() == 0 
            && BPB8030_AWA_8030.TX_TOOL.trim().length() == 0 
            && BPB8030_AWA_8030.JRNNO == 0 
            && BPB8030_AWA_8030.BR != 0) {
            CEP.TRC(SCCGWA, "BROWSE BY BR TLR");
            WS_BROWS_COND = '2';
        }
        if (BPB8030_AWA_8030.TX_TOOL.trim().length() > 0 
            && BPB8030_AWA_8030.AC.trim().length() == 0 
            && BPB8030_AWA_8030.JRNNO == 0) {
            CEP.TRC(SCCGWA, "BROWSE BY CI-AC");
            WS_BROWS_COND = '4';
        }
        if (BPB8030_AWA_8030.AC.trim().length() > 0 
            && BPB8030_AWA_8030.TX_TOOL.trim().length() == 0 
            && BPB8030_AWA_8030.JRNNO == 0) {
            CEP.TRC(SCCGWA, "CI WAY  AC");
            WS_BROWS_COND = '3';
        }
        CEP.TRC(SCCGWA, WS_BROWS_COND);
        if (BPB8030_AWA_8030.TX_TOOL.trim().length() > 0) {
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = BPB8030_AWA_8030.TX_TOOL;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCUCINF.CARD_LNK_TYP);
            if (DCCUCINF.CARD_LNK_TYP == '2') {
                WS_CARD_SUB = 'Y';
            }
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        if ((BPB8030_AWA_8030.STR_DT == SCCGWA.COMM_AREA.AC_DATE) 
            && (BPB8030_AWA_8030.END_DT == SCCGWA.COMM_AREA.AC_DATE)) {
            CEP.TRC(SCCGWA, "READ DAY TBALE ONLY");
            B040_INTR_INFO1();
            if (pgmRtn) return;
        } else {
            if ((BPB8030_AWA_8030.END_DT == SCCGWA.COMM_AREA.AC_DATE) 
                && (BPB8030_AWA_8030.STR_DT < SCCGWA.COMM_AREA.AC_DATE)) {
                CEP.TRC(SCCGWA, "READ TWO TABLES");
                B040_INTR_INFO2();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "READ HIST/HIST1/HIST2 TABLE ");
                B040_INTR_INFO3();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_MAIN_NFIN_PROC() throws IOException,SQLException,Exception {
        if (BPB8030_AWA_8030.AC_SEQ > 0) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = BPB8030_AWA_8030.TX_TOOL;
            CICQACAC.DATA.AGR_SEQ = BPB8030_AWA_8030.AC_SEQ;
            CICQACAC.FUNC = 'R';
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            WS_TS_ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        }
        IBS.init(SCCGWA, BPCTHIST);
        BPCTHIST.INFO.FUNC = '1';
        BPCTHIST.INFO.JRNNO = BPB8030_AWA_8030.JRNNO;
        CEP.TRC(SCCGWA, BPCTHIST.INFO.JRNNO);
        BPCTHIST.INFO.CI_NO = BPB8030_AWA_8030.CI_NO;
        BPCTHIST.INFO.AC = BPB8030_AWA_8030.TX_TOOL;
        BPCTHIST.INFO.REF_NO = BPB8030_AWA_8030.REF_NO;
        BPCTHIST.INFO.TX_BR = BPB8030_AWA_8030.BR;
        BPCTHIST.INFO.TX_CD = BPB8030_AWA_8030.TX_CD;
        BPCTHIST.INFO.TYP_CD = BPB8030_AWA_8030.TYP_CD;
        BPCTHIST.INFO.TX_TLR = BPB8030_AWA_8030.TX_TLR;
        BPCTHIST.INFO.STR_AC_DT = BPB8030_AWA_8030.STR_DT;
        BPCTHIST.INFO.END_AC_DT = BPB8030_AWA_8030.END_DT;
        BPCTHIST.INFO.STR_TX_DT = BPB8030_AWA_8030.TX_DATES[1-1].TX_DATE;
        BPCTHIST.INFO.END_TX_DT = BPB8030_AWA_8030.TX_DATES[2-1].TX_DATE;
        BPCTHIST.INFO.STR_TX_TM = BPB8030_AWA_8030.TX_TIMES[1-1].TX_TIME;
        BPCTHIST.INFO.END_TX_TM = BPB8030_AWA_8030.TX_TIMES[2-1].TX_TIME;
        BPCTHIST.AC_SEQ = BPB8030_AWA_8030.AC_SEQ;
        BPCTHIST.CCY = BPB8030_AWA_8030.TX_CCY;
        BPCTHIST.CCY_TYPE = BPB8030_AWA_8030.CCY_TYP;
        BPCTHIST.PTR = BPRNHIST;
        BPCTHIST.LEN = 436;
        CEP.TRC(SCCGWA, BPCTHIST.LEN);
        S000_CALL_BPZTHIST();
        if (pgmRtn) return;
        R000_READNEXT_BPTNHIST();
        if (pgmRtn) return;
        while (WS_NHIST_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B060_04_DATA_TRANS_TO_FMT();
            if (pgmRtn) return;
            R000_READNEXT_BPTNHIST();
            if (pgmRtn) return;
        }
        R000_ENDBR_BPTNHIST();
        if (pgmRtn) return;
    }
    public void B060_04_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_TS_QUEUE);
        CEP.TRC(SCCGWA, "CCCC");
        CEP.TRC(SCCGWA, BPRFHIS1.KEY.JRNNO);
        WS_TS_QUEUE.WS_TS_TX_DT = BPRNHIST.TX_DT;
        WS_TS_QUEUE.WS_TS_AC_DT = BPRNHIST.KEY.AC_DT;
        WS_TS_QUEUE.WS_TS_JRNNO = BPRNHIST.KEY.JRNNO;
        WS_TS_QUEUE.WS_TS_JRN_SEQ = BPRNHIST.KEY.JRN_SEQ;
        WS_TS_QUEUE.WS_TS_AC = BPRNHIST.AC;
        CEP.TRC(SCCGWA, WS_TS_QUEUE.WS_TS_AC);
        WS_TS_QUEUE.WS_TS_REF_NO = BPRNHIST.REF_NO;
        WS_TS_QUEUE.WS_TS_TX_TOOL = BPRNHIST.TX_TOOL;
        CEP.TRC(SCCGWA, "NCB0821003");
        CEP.TRC(SCCGWA, WS_TS_QUEUE.WS_TS_TX_TOOL);
        WS_TS_QUEUE.WS_TS_APP_MMO = BPRNHIST.APP_MMO;
        WS_TS_QUEUE.WS_TS_TX_CD = BPRNHIST.TX_CD;
        WS_TS_QUEUE.WS_TS_TX_BR = BPRNHIST.TX_BR;
        WS_TS_QUEUE.WS_TS_TX_DP = BPRNHIST.TX_DP;
        WS_TS_QUEUE.WS_TS_TX_TLR = BPRNHIST.TX_TLR;
        WS_TS_QUEUE.WS_TS_SUP1 = BPRNHIST.SUP1;
        WS_TS_QUEUE.WS_TS_SUP2 = BPRNHIST.SUP2;
        WS_TS_QUEUE.WS_TS_TX_CHNL = BPRNHIST.TX_CHNL;
        WS_TS_QUEUE.WS_TS_FIN_FLG = 'N';
        if (BPRNHIST.TX_TYP_CD == null) BPRNHIST.TX_TYP_CD = "";
        JIBS_tmp_int = BPRNHIST.TX_TYP_CD.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPRNHIST.TX_TYP_CD += " ";
        WS_TS_QUEUE.WS_TX_MMO = BPRNHIST.TX_TYP_CD.substring(0, 5);
        CEP.TRC(SCCGWA, BPRNHIST.TX_TYP_CD);
        CEP.TRC(SCCGWA, WS_TS_QUEUE.WS_TX_MMO);
        WS_TS_QUEUE.WS_TS_VCHNO = " ";
        WS_TS_QUEUE.WS_TS_CI_NO = BPRNHIST.CI_NO;
        WS_TS_QUEUE.WS_TS_DRCR_FLG = ' ';
        WS_TS_QUEUE.WS_AC_SEQU = BPRNHIST.AC_SEQ;
        WS_TS_QUEUE.WS_TS_TX_CCY = BPRNHIST.CCY;
        WS_TS_QUEUE.WS_CCY_TYP_CN = BPRNHIST.CCY_TYPE;
        WS_TS_QUEUE.WS_TS_TX_AMT = 0;
        WS_TS_QUEUE.WS_TS_CUR_BAL = 0;
        WS_TS_QUEUE.WS_TS_PROD_CD = " ";
        WS_TS_QUEUE.WS_TS_STS = BPRNHIST.TX_STS;
        WS_TS_QUEUE.WS_TS_TX_TM = BPRNHIST.TX_TM;
        WS_TS_QUEUE.WS_TS_REMARK = BPRNHIST.TX_RMK;
        WS_TS_QUEUE.WS_TS_TX_TYPE = BPRNHIST.TX_TYP;
        WS_TS_QUEUE.WS_MAKER_TLR = BPRNHIST.MAKER_TLR;
        WS_TS_QUEUE.WS_STAFF_FLG = 'N';
        IBS.init(SCCGWA, CICACCU);
        if (BPRNHIST.AC.trim().length() > 0) {
            CICACCU.DATA.AGR_NO = BPRNHIST.AC;
        } else {
            CICACCU.DATA.AGR_NO = BPRNHIST.TX_TOOL;
        }
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        if (CICACCU.RC.RC_CODE == 0) {
            if (CICACCU.DATA.AC_CNM.trim().length() > 0) {
                WS_TX_AC_CHNM = CICACCU.DATA.AC_CNM;
            } else if (CICACCU.DATA.AC_ENM.trim().length() > 0) {
                WS_TX_AC_CHNM = CICACCU.DATA.AC_ENM;
            } else if (CICACCU.DATA.CI_CNM.trim().length() > 0) {
                WS_TX_AC_CHNM = CICACCU.DATA.CI_CNM;
            } else if (CICACCU.DATA.CI_ENM.trim().length() > 0) {
                WS_TX_AC_CHNM = CICACCU.DATA.CI_ENM;
            } else {
                WS_TX_AC_CHNM = " ";
            }
        }
        WS_TX_AC_CHNM = CICACCU.DATA.AC_CNM;
        R000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void R000_READNEXT_BPTNHIST() throws IOException,SQLException,Exception {
        BPCTHIST.INFO.FUNC = '2';
        BPCTHIST.PTR = BPRNHIST;
        BPCTHIST.LEN = 436;
        S000_CALL_BPZTHIST();
        if (pgmRtn) return;
        if (BPCTHIST.RETURN_INFO == 'N') {
            WS_NHIST_FLG = 'N';
        } else {
            WS_NHIST_FLG = 'Y';
        }
    }
    public void R000_ENDBR_BPTNHIST() throws IOException,SQLException,Exception {
        BPCIFHIS.INPUT.FUNC = '3';
        BPCTHIST.PTR = BPRNHIST;
        BPCTHIST.LEN = 436;
        S000_CALL_BPZTHIST();
        if (pgmRtn) return;
    }
    public void R000_GET_AC_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUIBAL);
        if (SCRCWA.JRN_IN_USE == '1') {
            BPCUIBAL.INPUT.AC_DT = BPRFHIS1.KEY.AC_DT;
            BPCUIBAL.INPUT.JRNNO = BPRFHIS1.KEY.JRNNO;
            BPCUIBAL.INPUT.JRN_SEQ = BPRFHIS1.KEY.JRN_SEQ;
            BPCUIBAL.INPUT.AC_IN = BPRFHIS1.ACO_AC;
            BPCUIBAL.INPUT.CCY = BPRFHIS1.TX_CCY;
            BPCUIBAL.INPUT.CCY_TYP = BPRFHIS1.TX_CCY_TYPE;
            BPCUIBAL.INPUT.TX_AMT_IN = BPRFHIS1.TX_AMT;
            BPCUIBAL.INPUT.TX_DRCRFLG = BPRFHIS1.DRCRFLG;
            BPCUIBAL.INPUT.INP_AC_TYP = '1';
            if (BPRFHIS1.SUMUP_FLG != '5') {
                S000_CALL_BPZUIBAL();
                if (pgmRtn) return;
            }
        } else {
            BPCUIBAL.INPUT.AC_DT = BPRFHIS2.KEY.AC_DT;
            BPCUIBAL.INPUT.JRNNO = BPRFHIS2.KEY.JRNNO;
            BPCUIBAL.INPUT.JRN_SEQ = BPRFHIS2.KEY.JRN_SEQ;
            BPCUIBAL.INPUT.AC_IN = BPRFHIS2.ACO_AC;
            BPCUIBAL.INPUT.CCY = BPRFHIS2.TX_CCY;
            BPCUIBAL.INPUT.CCY_TYP = BPRFHIS2.TX_CCY_TYPE;
            BPCUIBAL.INPUT.TX_AMT_IN = BPRFHIS2.TX_AMT;
            BPCUIBAL.INPUT.TX_DRCRFLG = BPRFHIS2.DRCRFLG;
            BPCUIBAL.INPUT.INP_AC_TYP = '1';
            if (BPRFHIS2.SUMUP_FLG != '5') {
                S000_CALL_BPZUIBAL();
                if (pgmRtn) return;
            }
        }
        WS_CUR_BAL = BPCUIBAL.OUTPUT.BAL_E;
        CEP.TRC(SCCGWA, WS_CUR_BAL);
    }
    public void R000_GET_AC_BAL1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHISA);
        BPRFHISA.AC_DT = BPRFHIS1.KEY.AC_DT;
        BPRFHISA.KEY.AC = BPRFHIS1.ACO_AC;
        BPRFHISA.KEY.CCY = BPRFHIS1.TX_CCY;
        BPRFHISA.KEY.CCY_TYP = BPRFHIS1.TX_CCY_TYPE;
        BPRFHISA.KEY.PART_NO = BPRFHIS1.KEY.PART_NO;
        CEP.TRC(SCCGWA, BPRFHISA.KEY.CCY);
        CEP.TRC(SCCGWA, BPRFHISA.KEY.CCY_TYP);
        T000_READ_BPTFHISA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRFHISA.CUR_BAL);
        if (WS_BPTFHISA_FLG == 'Y') {
            WS_CUR_BAL = BPRFHISA.CUR_BAL;
        }
        if (WS_BPTFHISA_FLG == 'N') {
            WS_CUR_BAL = 0;
        }
    }
    public void R000_GET_AC_BAL2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHISA);
        BPRFHISA.AC_DT = BPRFHIS2.KEY.AC_DT;
        BPRFHISA.KEY.AC = BPRFHIS2.ACO_AC;
        BPRFHISA.KEY.CCY = BPRFHIS2.TX_CCY;
        BPRFHISA.KEY.CCY_TYP = BPRFHIS2.TX_CCY_TYPE;
        BPRFHISA.KEY.PART_NO = BPRFHIS2.KEY.PART_NO;
        CEP.TRC(SCCGWA, BPRFHISA.KEY.CCY);
        CEP.TRC(SCCGWA, BPRFHISA.KEY.CCY_TYP);
        T000_READ_BPTFHISA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRFHISA.CUR_BAL);
        if (WS_BPTFHISA_FLG == 'Y') {
            WS_CUR_BAL = BPRFHISA.CUR_BAL;
        }
        if (WS_BPTFHISA_FLG == 'N') {
            WS_CUR_BAL = 0;
        }
    }
    public void B040_INTR_INFO1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIS);
        B050_DATA_TRANS1();
        if (pgmRtn) return;
        if (WS_BROWS_COND == '1') {
            CEP.TRC(SCCGWA, "JRNNO");
            T000_STARTBR_JRNNO();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '2') {
            CEP.TRC(SCCGWA, "BRTLR");
            T000_STARTBR_BRTLR();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '3') {
            CEP.TRC(SCCGWA, "AC");
            T000_STARTBR_ACOAC();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '4') {
            B045_GET_ACOAC_FROM_CIZQACAC();
            if (pgmRtn) return;
            T000_STARTBR_ACOAC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_INQUERY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_READNEXT_BPTFHIS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BBBB");
        CEP.TRC(SCCGWA, BPRFHIS.KEY.JRNNO);
        while (WS_FRZ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B060_01_DATA_TRANS_TO_FMT();
            if (pgmRtn) return;
            T000_READNEXT_BPTFHIS();
            if (pgmRtn) return;
        }
        if (WS_FRZ_FLG == 'N') {
            BPCFHTTT.END_FLG = 'Y';
        }
        T000_ENDBR_BPTFHIS();
        if (pgmRtn) return;
    }
    public void B040_INTR_INFO3() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIS);
        IBS.init(SCCGWA, BPCFHTTT);
        B050_DATA_TRANS3();
        if (pgmRtn) return;
        if (WS_BROWS_COND == '1') {
            T000_STARTBR_JRNNO3();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '2') {
            T000_STARTBR_BRTLR3();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '3') {
            T000_STARTBR_ACOAC3();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '4') {
            B045_GET_ACOAC_FROM_CIZQACAC();
            if (pgmRtn) return;
            T000_STARTBR_ACOAC3();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_INQUERY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_READNEXT_BPTFHIST();
        if (pgmRtn) return;
        while (WS_FRZ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B060_01_DATA_TRANS_TO_FMT3();
            if (pgmRtn) return;
            T000_READNEXT_BPTFHIST();
            if (pgmRtn) return;
        }
        if (WS_FRZ_FLG == 'N') {
            BPCFHTTT.END_FLG = 'Y';
        }
        T000_ENDBR_BPTFHIST();
        if (pgmRtn) return;
        B091_READ_NEXT_TABLE_FHIST1();
        if (pgmRtn) return;
        B092_READ_NEXT_TABLE_FHIST2();
        if (pgmRtn) return;
    }
    public void B040_INTR_INFO2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIS);
        IBS.init(SCCGWA, BPCFHTTT);
        B050_DATA_TRANS3();
        if (pgmRtn) return;
        if (WS_BROWS_COND == '1') {
            T000_STARTBR_JRNNO3();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '2') {
            T000_STARTBR_BRTLR3();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '3') {
            T000_STARTBR_ACOAC3();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "WWW");
        } else if (WS_BROWS_COND == '4') {
            B045_GET_ACOAC_FROM_CIZQACAC();
            if (pgmRtn) return;
            T000_STARTBR_ACOAC3();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_INQUERY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "AAA");
        B080_READ_NEXT_RECORD3();
        if (pgmRtn) return;
        B091_READ_NEXT_TABLE_FHIST1();
        if (pgmRtn) return;
        B092_READ_NEXT_TABLE_FHIST2();
        if (pgmRtn) return;
        if (WS_FRZ_FLG == 'N') {
            BPCFHTTT.END_FLG = 'Y';
        }
    }
    public void B045_GET_ACOAC_FROM_CIZQACAC() throws IOException,SQLException,Exception {
        B045_GET_REL_ACNO();
        if (pgmRtn) return;
        B046_TRANS_DATA_CIZQACAC();
        if (pgmRtn) return;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO.trim().length() > 0) {
            WS_ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        } else {
            if (CICQACAC.O_DATA.O_ACR_DATA.O_REL_AC_NO.trim().length() == 0) {
                WS_ACO_AC = BPB8030_AWA_8030.TX_TOOL;
            } else {
                WS_AGR_NO = CICQACAC.O_DATA.O_ACR_DATA.O_REL_AC_NO;
                IBS.init(SCCGWA, CICQACAC);
                CICQACAC.DATA.AGR_NO = WS_AGR_NO;
                CICQACAC.FUNC = 'R';
                S000_CALL_CIZQACAC();
                if (pgmRtn) return;
                WS_ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            }
        }
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, WS_ACO_AC);
        WS_H_ACO_AC = WS_ACO_AC;
        R000_GET_PART_NO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_PART_NO);
    }
    public void B080_READ_NEXT_RECORD1() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTFHIS();
        if (pgmRtn) return;
        while (WS_FRZ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B060_01_DATA_TRANS_TO_FMT();
            if (pgmRtn) return;
            T000_READNEXT_BPTFHIS();
            if (pgmRtn) return;
        }
        if (WS_FRZ_FLG == 'N') {
            BPCFHTTT.END_FLG = 'Y';
        }
        T000_ENDBR_BPTFHIS();
        if (pgmRtn) return;
    }
    public void B080_READ_NEXT_RECORD3() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BBB");
        T000_READNEXT_BPTFHIST();
        if (pgmRtn) return;
        while (WS_FRZ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B060_01_DATA_TRANS_TO_FMT3();
            if (pgmRtn) return;
            T000_READNEXT_BPTFHIST();
            if (pgmRtn) return;
        }
        if (WS_FRZ_FLG == 'N') {
            BPCFHTTT.END_FLG = 'Y';
        }
        T000_ENDBR_BPTFHIST();
        if (pgmRtn) return;
    }
    public void B090_READ_NEXT_TABLE1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIS);
        B050_DATA_TRANS1();
        if (pgmRtn) return;
        if (WS_BROWS_COND == '1') {
            T000_STARTBR_JRNNO();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '2') {
            T000_STARTBR_BRTLR();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '3') {
            T000_STARTBR_ACOAC();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '4') {
            B045_GET_ACOAC_FROM_CIZQACAC();
            if (pgmRtn) return;
            T000_STARTBR_ACOAC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_INQUERY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_READNEXT_BPTFHIS();
        if (pgmRtn) return;
        while (WS_FRZ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B060_01_DATA_TRANS_TO_FMT();
            if (pgmRtn) return;
            T000_READNEXT_BPTFHIS();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTFHIS();
        if (pgmRtn) return;
    }
    public void B091_READ_NEXT_TABLE_FHIST1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIS);
        B050_DATA_TRANS1();
        if (pgmRtn) return;
        if (WS_BROWS_COND == '1') {
            T000_STARTBR_JRNNO1();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '2') {
            T000_STARTBR_BRTLR1();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '3') {
            T000_STARTBR_ACOAC1();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '4') {
            B045_GET_ACOAC_FROM_CIZQACAC();
            if (pgmRtn) return;
            T000_STARTBR_ACOAC1();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_INQUERY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_READNEXT_BPTFHIS1();
        if (pgmRtn) return;
        WS_D = 0;
        while (WS_FRZ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B060_01_DATA_TRANS_TO_FMT_HIST1();
            if (pgmRtn) return;
            T000_READNEXT_BPTFHIS1();
            if (pgmRtn) return;
        }
        if (WS_FRZ_FLG == 'N') {
            BPCFHTTT.END_FLG = 'Y';
        }
        T000_ENDBR_BPTFHIS1();
        if (pgmRtn) return;
    }
    public void B092_READ_NEXT_TABLE_FHIST2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIS);
        B050_DATA_TRANS1();
        if (pgmRtn) return;
        if (WS_BROWS_COND == '1') {
            T000_STARTBR_JRNNO2();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '2') {
            T000_STARTBR_BRTLR2();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '3') {
            T000_STARTBR_ACOAC2();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '4') {
            B045_GET_ACOAC_FROM_CIZQACAC();
            if (pgmRtn) return;
            T000_STARTBR_ACOAC2();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_INQUERY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_READNEXT_BPTFHIS2();
        if (pgmRtn) return;
        WS_D = 0;
        while (WS_FRZ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B060_01_DATA_TRANS_TO_FMT_HIST2();
            if (pgmRtn) return;
            T000_READNEXT_BPTFHIS2();
            if (pgmRtn) return;
        }
        if (WS_FRZ_FLG == 'N') {
            BPCFHTTT.END_FLG = 'Y';
        }
        T000_ENDBR_BPTFHIS2();
        if (pgmRtn) return;
    }
    public void B045_GET_REL_ACNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.DATA.AC_NO = BPB8030_AWA_8030.TX_TOOL;
        CICQACRL.DATA.AC_REL = "13";
        CICQACRL.FUNC = '3';
        CICQACRL.FUNC = 'I';
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        if (CICQACRL.RC.RC_CODE == 0) {
            WS_AGR_NO1 = CICQACRL.O_DATA.O_REL_AC_NO;
        } else {
            WS_AGR_NO1 = BPB8030_AWA_8030.TX_TOOL;
        }
        CEP.TRC(SCCGWA, WS_AGR_NO1);
    }
    public void B046_TRANS_DATA_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = WS_AGR_NO1;
        CICQACAC.DATA.AGR_SEQ = BPB8030_AWA_8030.AC_SEQ;
        CICQACAC.DATA.CCY_ACAC = BPB8030_AWA_8030.TX_CCY;
        CICQACAC.DATA.CR_FLG = BPB8030_AWA_8030.CCY_TYP;
        CICQACAC.FUNC = 'R';
    }
    public void B050_DATA_TRANS1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIS);
        WS_JRNNO = BPB8030_AWA_8030.JRNNO;
        WS_JRN_SEQ = BPB8030_AWA_8030.JRN_SEQ;
        BPRFHIS.ACO_AC = BPB8030_AWA_8030.AC;
        WS_ACO_AC = BPB8030_AWA_8030.AC;
        BPRFHIS.REF_NO = BPB8030_AWA_8030.REF_NO;
        BPRFHIS.TX_TOOL = BPB8030_AWA_8030.TX_TOOL;
        WS_TX_TOOL = BPB8030_AWA_8030.TX_TOOL;
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.AC);
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.TX_TOOL);
        WS_AC_SEQ = BPB8030_AWA_8030.AC_SEQ;
        BPRFHIS.TX_CCY = BPB8030_AWA_8030.TX_CCY;
        BPRFHIS.TX_CCY_TYPE = BPB8030_AWA_8030.CCY_TYP;
        BPRFHIS.TX_CD = BPB8030_AWA_8030.TX_CD;
        WS_TX_CD = BPB8030_AWA_8030.TX_CD;
        WS_TX_TLR = BPB8030_AWA_8030.TX_TLR;
        WS_TX_BR = BPB8030_AWA_8030.BR;
        WS_STR_DT = BPB8030_AWA_8030.STR_DT;
        WS_END_DT = BPB8030_AWA_8030.END_DT;
        BPRFHIS.CI_NO = BPB8030_AWA_8030.CI_NO;
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.STR_DT);
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.JRNNO);
    }
    public void B050_DATA_TRANS3() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIST);
        WS_JRNNO = BPB8030_AWA_8030.JRNNO;
        WS_JRN_SEQ = BPB8030_AWA_8030.JRN_SEQ;
        BPRFHIST.ACO_AC = BPB8030_AWA_8030.AC;
        WS_ACO_AC = BPB8030_AWA_8030.AC;
        BPRFHIST.REF_NO = BPB8030_AWA_8030.REF_NO;
        BPRFHIST.TX_TOOL = BPB8030_AWA_8030.TX_TOOL;
        WS_TX_TOOL = BPB8030_AWA_8030.TX_TOOL;
        WS_AC_SEQ = BPB8030_AWA_8030.AC_SEQ;
        BPRFHIST.TX_CCY = BPB8030_AWA_8030.TX_CCY;
        BPRFHIST.TX_CCY_TYPE = BPB8030_AWA_8030.CCY_TYP;
        BPRFHIST.TX_CD = BPB8030_AWA_8030.TX_CD;
        WS_TX_CD = BPB8030_AWA_8030.TX_CD;
        WS_TX_TLR = BPB8030_AWA_8030.TX_TLR;
        WS_TX_BR = BPB8030_AWA_8030.BR;
        WS_STR_DT = BPB8030_AWA_8030.STR_DT;
        WS_END_DT = BPB8030_AWA_8030.END_DT;
        BPRFHIST.CI_NO = BPB8030_AWA_8030.CI_NO;
    }
    public void R000_WRITE_PAGE_RECORD_CN() throws IOException,SQLException,Exception {
        BPCIPHIS.FORWORD.DATA_LEN = 988;
        CEP.TRC(SCCGWA, BPCIPHIS.FORWORD.DATA_LEN);
        CEP.TRC(SCCGWA, WS_REC_CNT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TS_QUEUE);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCIPHIS.OUTPUT.REC_DATA.RETURN_DATA[WS_REC_CNT-1].DATA_TEXT);
    }
    public void R000_WRITE_PAGE_TITLE() throws IOException,SQLException,Exception {
        BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT = 0;
        BPCIPHIS.INPUT.PAGE_RECNO = 50;
        BPCIPHIS.OUTPUT.REC_TITLE.RECORD_COUNT = WS_REC_CNT;
        BPCIPHIS.INPUT.PAGE_NO = BPB8030_AWA_8030.PG_NO;
        WS_RECORD_NUM = BPCIPHIS.OUTPUT.REC_TITLE.RECORD_COUNT % BPCIPHIS.INPUT.PAGE_RECNO;
        BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT = (short) ((BPCIPHIS.OUTPUT.REC_TITLE.RECORD_COUNT - WS_RECORD_NUM) / BPCIPHIS.INPUT.PAGE_RECNO);
        if (WS_RECORD_NUM > 0) {
            BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT = (short) (BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT + 1);
        }
        CEP.TRC(SCCGWA, WS_RECORD_NUM);
        CEP.TRC(SCCGWA, BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT);
        if (BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT == 1) {
            BPCIPHIS.OUTPUT.REC_TITLE.RECORD_NUM = (short) WS_RECORD_NUM;
        } else {
            if (BPCIPHIS.INPUT.PAGE_NO < BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT 
                || (BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT > 0 
                && WS_RECORD_NUM == 0)) {
                WS_RECORD_NUM = BPCIPHIS.INPUT.PAGE_RECNO;
            }
        }
        if (BPCIPHIS.INPUT.PAGE_NO > 0) {
            BPCIPHIS.OUTPUT.REC_TITLE.NOW_PAGE_NO = (short) BPCIPHIS.INPUT.PAGE_NO;
        } else {
            BPCIPHIS.OUTPUT.REC_TITLE.NOW_PAGE_NO = 1;
        }
        CEP.TRC(SCCGWA, BPCIPHIS.OUTPUT.REC_TITLE.NOW_PAGE_NO);
        CEP.TRC(SCCGWA, BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT);
        CEP.TRC(SCCGWA, BPCIPHIS.OUTPUT.REC_TITLE.RECORD_COUNT);
        CEP.TRC(SCCGWA, BPCIPHIS.OUTPUT.REC_TITLE.RECORD_NUM);
    }
    public void R000_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 988;
        SCCMPAG.SCR_COL_CNT = 988;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_TS_QUEUE);
        SCCMPAG.DATA_LEN = 988;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_01_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_TS_QUEUE);
        if (SCRCWA.JRN_IN_USE == '1') {
            if (BPRFHIS1.DISPLAY_IND != 'N') {
                if (BPB8030_AWA_8030.DC_FLG == ' ' 
                    || (BPB8030_AWA_8030.DC_FLG != ' ' 
                    && BPB8030_AWA_8030.DC_FLG == BPRFHIS1.DRCRFLG)) {
                    CEP.TRC(SCCGWA, "AAAA");
                    CEP.TRC(SCCGWA, BPRFHIS1.KEY.JRNNO);
                    WS_TS_QUEUE.WS_TS_TX_DT = BPRFHIS1.TX_DT;
                    WS_TS_QUEUE.WS_TS_AC_DT = BPRFHIS1.KEY.AC_DT;
                    WS_TS_QUEUE.WS_TS_JRNNO = BPRFHIS1.KEY.JRNNO;
                    WS_TS_QUEUE.WS_TS_JRN_SEQ = BPRFHIS1.KEY.JRN_SEQ;
                    WS_TS_QUEUE.WS_TS_AC = BPRFHIS1.KEY.AC;
                    WS_TS_QUEUE.WS_TS_REF_NO = BPRFHIS1.REF_NO;
                    WS_TS_QUEUE.WS_TS_TX_TOOL = BPRFHIS1.TX_TOOL;
                    CEP.TRC(SCCGWA, "NCB0821004");
                    CEP.TRC(SCCGWA, WS_TS_QUEUE.WS_TS_TX_TOOL);
                    WS_TS_QUEUE.WS_TS_APP_MMO = BPRFHIS1.APP_MMO;
                    WS_TS_QUEUE.WS_TS_TX_CD = BPRFHIS1.TX_CD;
                    WS_TS_QUEUE.WS_TS_TX_BR = BPRFHIS1.TX_BR;
                    WS_TS_QUEUE.WS_TS_TX_DP = BPRFHIS1.TX_DP;
                    WS_TS_QUEUE.WS_TS_TX_TLR = BPRFHIS1.TX_TLR;
                    WS_TS_QUEUE.WS_TS_SUP1 = BPRFHIS1.SUP1;
                    WS_TS_QUEUE.WS_TS_SUP2 = BPRFHIS1.SUP2;
                    WS_TS_QUEUE.WS_TS_TX_CHNL = BPRFHIS1.TX_CHNL;
                    WS_TS_QUEUE.WS_TS_FIN_FLG = 'F';
                    WS_TS_QUEUE.WS_TS_VCHNO = BPRFHIS1.VCHNO;
                    if (!BPRFHIS1.CI_NO.equalsIgnoreCase("0") 
                        && BPRFHIS1.CI_NO.trim().length() > 0) {
                        WS_TS_QUEUE.WS_TS_CI_NO = BPRFHIS1.CI_NO;
                    } else {
                        WS_TS_QUEUE.WS_TS_CI_NO = WS_OUT_CI_NO;
                    }
                    WS_TS_QUEUE.WS_TS_DRCR_FLG = BPRFHIS1.DRCRFLG;
                    WS_TS_QUEUE.WS_TS_TX_CCY = BPRFHIS1.TX_CCY;
                    WS_TS_QUEUE.WS_TS_TX_AMT = BPRFHIS1.TX_AMT;
                    WS_TS_QUEUE.WS_TS_CUR_BAL = WS_CUR_BAL;
                    WS_TS_QUEUE.WS_TS_TX_VAL_DT = BPRFHIS1.TX_VAL_DT;
                    WS_TS_QUEUE.WS_TS_PROD_CD = BPRFHIS1.PROD_CD;
                    WS_TS_QUEUE.WS_TS_STS = BPRFHIS1.TX_STS;
                    WS_TS_QUEUE.WS_TS_TX_TM = BPRFHIS1.TX_TM;
                    WS_TS_QUEUE.WS_TS_REMARK = BPRFHIS1.REMARK;
                    WS_TS_QUEUE.WS_MAKER_TLR = BPRFHIS1.MAKER;
                    WS_TS_QUEUE.WS_NARRATIVE = BPRFHIS1.NARRATIVE;
                    WS_TS_QUEUE.WS_TX_MMO = BPRFHIS1.TX_MMO;
                    IBS.init(SCCGWA, BPCPARMC);
                    IBS.init(SCCGWA, BPCPRMM);
                    BPCPARMC.KEY.TYP = "PARMC";
                    BPCPRMM.FUNC = '3';
                    BPCPARMC.KEY.CD = K_PAMC_MMO + BPRFHIS1.TX_MMO;
                    CEP.TRC(SCCGWA, BPCPARMC.KEY.CD);
                    BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPCPRMM.DAT_PTR = BPCPARMC;
                    S000_CALL_BPZPRMM();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCPRMM.RC);
                    if (BPCPRMM.RC.RC_RTNCODE == 0) {
                        CEP.TRC(SCCGWA, "FOUND");
                        WS_TX_MMO_NM = BPCPARMC.DATA_TXT.REMARKS;
                    } else {
                        CEP.TRC(SCCGWA, "ZUONOTFND");
                        WS_TX_MMO_NM = " ";
                    }
                    CEP.TRC(SCCGWA, WS_TX_MMO_NM);
                    if (BPRFHIS1.RLT_TX_TOOL.trim().length() > 0) {
                        WS_TS_QUEUE.WS_REL_AC = BPRFHIS1.RLT_TX_TOOL;
                    } else {
                        WS_TS_QUEUE.WS_REL_AC = BPRFHIS1.RLT_AC;
                    }
                    WS_TS_QUEUE.WS_STAFF_FLG = 'N';
                    WS_TS_QUEUE.WS_REL_CI_NAME = BPRFHIS1.RLT_AC_NAME;
                    WS_TS_QUEUE.WS_BV_CODE_CN = BPRFHIS1.BV_CODE;
                    WS_TS_QUEUE.WS_HEAD_NO_CN = BPRFHIS1.HEAD_NO;
                    WS_TS_QUEUE.WS_BV_NO_CN = BPRFHIS1.BV_NO;
                    WS_TS_QUEUE.WS_COM_PROD_CN = BPRFHIS1.COM_PROD;
                    WS_TS_QUEUE.WS_CCY_TYP_CN = BPRFHIS1.TX_CCY_TYPE;
                    WS_ACO_AC = BPRFHIS1.ACO_AC;
                    if (WS_ACO_AC.trim().length() > 0) {
                        CICQACAC.DATA.ACAC_NO = WS_ACO_AC;
                        CICQACAC.FUNC = 'A';
                        S000_CALL_CIZQACAC();
                        if (pgmRtn) return;
                        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ);
                        WS_TS_QUEUE.WS_AC_SEQU = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
                    }
                    WS_TS_QUEUE.WS_RLT_BANK = BPRFHIS1.RLT_BANK;
                    IBS.init(SCCGWA, CICACCU);
                    if (BPRFHIS1.KEY.AC.trim().length() > 0) {
                        CICACCU.DATA.AGR_NO = BPRFHIS1.KEY.AC;
                    } else {
                        CICACCU.DATA.AGR_NO = BPRFHIS1.TX_TOOL;
                    }
                    S000_CALL_CIZACCU();
                    if (pgmRtn) return;
                    if (CICACCU.RC.RC_CODE == 0) {
                        if (CICACCU.DATA.AC_CNM.trim().length() > 0) {
                            WS_TX_AC_CHNM = CICACCU.DATA.AC_CNM;
                        } else if (CICACCU.DATA.AC_ENM.trim().length() > 0) {
                            WS_TX_AC_CHNM = CICACCU.DATA.AC_ENM;
                        } else if (CICACCU.DATA.CI_CNM.trim().length() > 0) {
                            WS_TX_AC_CHNM = CICACCU.DATA.CI_CNM;
                        } else if (CICACCU.DATA.CI_ENM.trim().length() > 0) {
                            WS_TX_AC_CHNM = CICACCU.DATA.CI_ENM;
                        } else {
                            WS_TX_AC_CHNM = " ";
                        }
                    }
                    WS_TX_AC_CHNM = CICACCU.DATA.AC_CNM;
                    WS_REC_CNT = WS_REC_CNT + 1;
                    BPCFHTTT.TOTAL_NUM = (short) (BPCFHTTT.TOTAL_NUM + 1);
                    R000_TRANS_DATA_OUTPUT();
                    if (pgmRtn) return;
                }
            }
        } else {
            if (BPRFHIS2.DISPLAY_IND != 'N') {
                if (BPB8030_AWA_8030.DC_FLG == ' ' 
                    || (BPB8030_AWA_8030.DC_FLG != ' ' 
                    && BPB8030_AWA_8030.DC_FLG == BPRFHIS2.DRCRFLG)) {
                    CEP.TRC(SCCGWA, BPRFHIS2.KEY.JRNNO);
                    WS_TS_QUEUE.WS_TS_TX_DT = BPRFHIS2.TX_DT;
                    WS_TS_QUEUE.WS_TS_AC_DT = BPRFHIS2.KEY.AC_DT;
                    WS_TS_QUEUE.WS_TS_JRNNO = BPRFHIS2.KEY.JRNNO;
                    WS_TS_QUEUE.WS_TS_JRN_SEQ = BPRFHIS2.KEY.JRN_SEQ;
                    WS_TS_QUEUE.WS_TS_AC = BPRFHIS2.KEY.AC;
                    WS_TS_QUEUE.WS_TS_REF_NO = BPRFHIS2.REF_NO;
                    WS_TS_QUEUE.WS_TS_TX_TOOL = BPRFHIS2.TX_TOOL;
                    CEP.TRC(SCCGWA, "NCB0821005");
                    CEP.TRC(SCCGWA, WS_TS_QUEUE.WS_TS_TX_TOOL);
                    WS_TS_QUEUE.WS_TS_APP_MMO = BPRFHIS2.APP_MMO;
                    WS_TS_QUEUE.WS_TS_TX_CD = BPRFHIS2.TX_CD;
                    WS_TS_QUEUE.WS_TS_TX_BR = BPRFHIS2.TX_BR;
                    WS_TS_QUEUE.WS_TS_TX_DP = BPRFHIS2.TX_DP;
                    WS_TS_QUEUE.WS_TS_TX_TLR = BPRFHIS2.TX_TLR;
                    WS_TS_QUEUE.WS_TS_SUP1 = BPRFHIS2.SUP1;
                    WS_TS_QUEUE.WS_TS_SUP2 = BPRFHIS2.SUP2;
                    WS_TS_QUEUE.WS_TS_TX_CHNL = BPRFHIS2.TX_CHNL;
                    WS_TS_QUEUE.WS_TS_FIN_FLG = 'F';
                    WS_TS_QUEUE.WS_TS_VCHNO = BPRFHIS2.VCHNO;
                    if (!BPRFHIS2.CI_NO.equalsIgnoreCase("0") 
                        && BPRFHIS2.CI_NO.trim().length() > 0) {
                        WS_TS_QUEUE.WS_TS_CI_NO = BPRFHIS2.CI_NO;
                    } else {
                        WS_TS_QUEUE.WS_TS_CI_NO = WS_OUT_CI_NO;
                    }
                    WS_TS_QUEUE.WS_TS_DRCR_FLG = BPRFHIS2.DRCRFLG;
                    WS_TS_QUEUE.WS_TS_TX_CCY = BPRFHIS2.TX_CCY;
                    WS_TS_QUEUE.WS_TS_TX_AMT = BPRFHIS2.TX_AMT;
                    WS_TS_QUEUE.WS_TS_CUR_BAL = WS_CUR_BAL;
                    WS_TS_QUEUE.WS_TS_TX_VAL_DT = BPRFHIS2.TX_VAL_DT;
                    WS_TS_QUEUE.WS_TS_PROD_CD = BPRFHIS2.PROD_CD;
                    WS_TS_QUEUE.WS_TS_STS = BPRFHIS2.TX_STS;
                    WS_TS_QUEUE.WS_TS_TX_TM = BPRFHIS2.TX_TM;
                    WS_TS_QUEUE.WS_TS_REMARK = BPRFHIS2.REMARK;
                    WS_TS_QUEUE.WS_MAKER_TLR = BPRFHIS2.MAKER;
                    WS_TS_QUEUE.WS_NARRATIVE = BPRFHIS2.NARRATIVE;
                    WS_TS_QUEUE.WS_TX_MMO = BPRFHIS2.TX_MMO;
                    IBS.init(SCCGWA, CICACCU);
                    if (BPRFHIS2.RLT_TX_TOOL.trim().length() > 0) {
                        WS_TS_QUEUE.WS_REL_AC = BPRFHIS2.RLT_TX_TOOL;
                    } else {
                        WS_TS_QUEUE.WS_REL_AC = BPRFHIS2.RLT_AC;
                    }
                    WS_TS_QUEUE.WS_STAFF_FLG = 'N';
                    WS_TS_QUEUE.WS_REL_CI_NAME = BPRFHIS2.RLT_AC_NAME;
                    WS_TS_QUEUE.WS_BV_CODE_CN = BPRFHIS2.BV_CODE;
                    WS_TS_QUEUE.WS_HEAD_NO_CN = BPRFHIS2.HEAD_NO;
                    WS_TS_QUEUE.WS_BV_NO_CN = BPRFHIS2.BV_NO;
                    WS_TS_QUEUE.WS_COM_PROD_CN = BPRFHIS2.COM_PROD;
                    WS_TS_QUEUE.WS_CCY_TYP_CN = BPRFHIS2.TX_CCY_TYPE;
                    WS_ACO_AC = BPRFHIS2.ACO_AC;
                    if (WS_ACO_AC.trim().length() > 0) {
                        CICQACAC.DATA.ACAC_NO = WS_ACO_AC;
                        CICQACAC.FUNC = 'A';
                        S000_CALL_CIZQACAC();
                        if (pgmRtn) return;
                        WS_TS_QUEUE.WS_AC_SEQU = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
                    }
                    WS_TS_QUEUE.WS_RLT_BANK = BPRFHIS2.RLT_BANK;
                    IBS.init(SCCGWA, CICACCU);
                    if (BPRFHIS2.KEY.AC.trim().length() > 0) {
                        CICACCU.DATA.AGR_NO = BPRFHIS2.KEY.AC;
                    } else {
                        CICACCU.DATA.AGR_NO = BPRFHIS2.TX_TOOL;
                    }
                    S000_CALL_CIZACCU();
                    if (pgmRtn) return;
                    if (CICACCU.RC.RC_CODE == 0) {
                        if (CICACCU.DATA.AC_CNM.trim().length() > 0) {
                            WS_TX_AC_CHNM = CICACCU.DATA.AC_CNM;
                        } else if (CICACCU.DATA.AC_ENM.trim().length() > 0) {
                            WS_TX_AC_CHNM = CICACCU.DATA.AC_ENM;
                        } else if (CICACCU.DATA.CI_CNM.trim().length() > 0) {
                            WS_TX_AC_CHNM = CICACCU.DATA.CI_CNM;
                        } else if (CICACCU.DATA.CI_ENM.trim().length() > 0) {
                            WS_TX_AC_CHNM = CICACCU.DATA.CI_ENM;
                        } else {
                            WS_TX_AC_CHNM = " ";
                        }
                    }
                    WS_TX_AC_CHNM = CICACCU.DATA.AC_CNM;
                    WS_REC_CNT = WS_REC_CNT + 1;
                    R000_TRANS_DATA_OUTPUT();
                    if (pgmRtn) return;
                }
            }
        }
        CEP.TRC(SCCGWA, "ZUOBO");
        CEP.TRC(SCCGWA, WS_TS_QUEUE.WS_TS_JRNNO);
    }
    public void B060_01_DATA_TRANS_TO_FMT_HIST1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_TS_QUEUE);
        if (BPRFHIS1.DISPLAY_IND != 'N') {
            if (BPB8030_AWA_8030.DC_FLG == ' ' 
                || (BPB8030_AWA_8030.DC_FLG != ' ' 
                && BPB8030_AWA_8030.DC_FLG == BPRFHIS1.DRCRFLG)) {
                CEP.TRC(SCCGWA, "AAAA");
                CEP.TRC(SCCGWA, BPRFHIS1.KEY.JRNNO);
                WS_TS_QUEUE.WS_TS_TX_DT = BPRFHIS1.TX_DT;
                WS_TS_QUEUE.WS_TS_AC_DT = BPRFHIS1.KEY.AC_DT;
                WS_TS_QUEUE.WS_TS_JRNNO = BPRFHIS1.KEY.JRNNO;
                WS_TS_QUEUE.WS_TS_JRN_SEQ = BPRFHIS1.KEY.JRN_SEQ;
                WS_TS_QUEUE.WS_TS_AC = BPRFHIS1.KEY.AC;
                WS_TS_QUEUE.WS_TS_REF_NO = BPRFHIS1.REF_NO;
                WS_TS_QUEUE.WS_TS_TX_TOOL = BPRFHIS1.TX_TOOL;
                CEP.TRC(SCCGWA, "NCB0821006");
                CEP.TRC(SCCGWA, WS_TS_QUEUE.WS_TS_TX_TOOL);
                WS_TS_QUEUE.WS_TS_APP_MMO = BPRFHIS1.APP_MMO;
                WS_TS_QUEUE.WS_TS_TX_CD = BPRFHIS1.TX_CD;
                WS_TS_QUEUE.WS_TS_TX_BR = BPRFHIS1.TX_BR;
                WS_TS_QUEUE.WS_TS_TX_DP = BPRFHIS1.TX_DP;
                WS_TS_QUEUE.WS_TS_TX_TLR = BPRFHIS1.TX_TLR;
                WS_TS_QUEUE.WS_TS_SUP1 = BPRFHIS1.SUP1;
                WS_TS_QUEUE.WS_TS_SUP2 = BPRFHIS1.SUP2;
                WS_TS_QUEUE.WS_TS_TX_CHNL = BPRFHIS1.TX_CHNL;
                WS_TS_QUEUE.WS_TS_FIN_FLG = 'F';
                WS_TS_QUEUE.WS_TS_VCHNO = BPRFHIS1.VCHNO;
                if (!BPRFHIS1.CI_NO.equalsIgnoreCase("0") 
                    && BPRFHIS1.CI_NO.trim().length() > 0) {
                    WS_TS_QUEUE.WS_TS_CI_NO = BPRFHIS1.CI_NO;
                } else {
                    WS_TS_QUEUE.WS_TS_CI_NO = WS_OUT_CI_NO;
                }
                WS_TS_QUEUE.WS_TS_DRCR_FLG = BPRFHIS1.DRCRFLG;
                WS_TS_QUEUE.WS_TS_TX_CCY = BPRFHIS1.TX_CCY;
                WS_TS_QUEUE.WS_TS_TX_AMT = BPRFHIS1.TX_AMT;
                WS_TS_QUEUE.WS_TS_CUR_BAL = WS_CUR_BAL;
                WS_TS_QUEUE.WS_TS_TX_VAL_DT = BPRFHIS1.TX_VAL_DT;
                WS_TS_QUEUE.WS_TS_PROD_CD = BPRFHIS1.PROD_CD;
                WS_TS_QUEUE.WS_TS_STS = BPRFHIS1.TX_STS;
                WS_TS_QUEUE.WS_TS_TX_TM = BPRFHIS1.TX_TM;
                WS_TS_QUEUE.WS_TS_REMARK = BPRFHIS1.REMARK;
                CEP.TRC(SCCGWA, WS_TS_QUEUE.WS_TS_REMARK);
                WS_TS_QUEUE.WS_MAKER_TLR = BPRFHIS1.MAKER;
                WS_TS_QUEUE.WS_NARRATIVE = BPRFHIS1.NARRATIVE;
                WS_TS_QUEUE.WS_TX_MMO = BPRFHIS1.TX_MMO;
                IBS.init(SCCGWA, BPCPARMC);
                IBS.init(SCCGWA, BPCPRMM);
                BPCPARMC.KEY.TYP = "PARMC";
                BPCPRMM.FUNC = '3';
                BPCPARMC.KEY.CD = K_PAMC_MMO + BPRFHIS1.TX_MMO;
                CEP.TRC(SCCGWA, BPCPARMC.KEY.CD);
                BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPCPRMM.DAT_PTR = BPCPARMC;
                S000_CALL_BPZPRMM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPRMM.RC);
                if (BPCPRMM.RC.RC_RTNCODE == 0) {
                    CEP.TRC(SCCGWA, "FOUND");
                    WS_TX_MMO_NM = BPCPARMC.DATA_TXT.REMARKS;
                } else {
                    CEP.TRC(SCCGWA, "ZUONOTFND");
                    WS_TX_MMO_NM = " ";
                }
                CEP.TRC(SCCGWA, WS_TX_MMO_NM);
                if (BPRFHIS1.RLT_TX_TOOL.trim().length() > 0) {
                    WS_TS_QUEUE.WS_REL_AC = BPRFHIS1.RLT_TX_TOOL;
                } else {
                    WS_TS_QUEUE.WS_REL_AC = BPRFHIS1.RLT_AC;
                }
                WS_TS_QUEUE.WS_STAFF_FLG = 'N';
                WS_TS_QUEUE.WS_REL_CI_NAME = BPRFHIS1.RLT_AC_NAME;
                CEP.TRC(SCCGWA, WS_TS_QUEUE.WS_REL_CI_NAME);
                WS_TS_QUEUE.WS_BV_CODE_CN = BPRFHIS1.BV_CODE;
                WS_TS_QUEUE.WS_HEAD_NO_CN = BPRFHIS1.HEAD_NO;
                WS_TS_QUEUE.WS_BV_NO_CN = BPRFHIS1.BV_NO;
                WS_TS_QUEUE.WS_COM_PROD_CN = BPRFHIS1.COM_PROD;
                WS_TS_QUEUE.WS_CCY_TYP_CN = BPRFHIS1.TX_CCY_TYPE;
                WS_ACO_AC = BPRFHIS1.ACO_AC;
                if (WS_ACO_AC.trim().length() > 0) {
                    CICQACAC.DATA.ACAC_NO = WS_ACO_AC;
                    CICQACAC.FUNC = 'A';
                    S000_CALL_CIZQACAC();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ);
                    WS_TS_QUEUE.WS_AC_SEQU = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
                }
                WS_TS_QUEUE.WS_RLT_BANK = BPRFHIS1.RLT_BANK;
                IBS.init(SCCGWA, CICACCU);
                if (BPRFHIS1.KEY.AC.trim().length() > 0) {
                    CICACCU.DATA.AGR_NO = BPRFHIS1.KEY.AC;
                } else {
                    CICACCU.DATA.AGR_NO = BPRFHIS1.TX_TOOL;
                }
                S000_CALL_CIZACCU();
                if (pgmRtn) return;
                if (CICACCU.RC.RC_CODE == 0) {
                    if (CICACCU.DATA.AC_CNM.trim().length() > 0) {
                        WS_TX_AC_CHNM = CICACCU.DATA.AC_CNM;
                    } else if (CICACCU.DATA.AC_ENM.trim().length() > 0) {
                        WS_TX_AC_CHNM = CICACCU.DATA.AC_ENM;
                    } else if (CICACCU.DATA.CI_CNM.trim().length() > 0) {
                        WS_TX_AC_CHNM = CICACCU.DATA.CI_CNM;
                    } else if (CICACCU.DATA.CI_ENM.trim().length() > 0) {
                        WS_TX_AC_CHNM = CICACCU.DATA.CI_ENM;
                    } else {
                        WS_TX_AC_CHNM = " ";
                    }
                }
                WS_TX_AC_CHNM = CICACCU.DATA.AC_CNM;
                WS_D = (short) (WS_D + 1);
                WS_REC_CNT = WS_REC_CNT + 1;
                BPCFHTTT.TOTAL_NUM = (short) (BPCFHTTT.TOTAL_NUM + 1);
                R000_TRANS_DATA_OUTPUT();
                if (pgmRtn) return;
            }
        }
    }
    public void B060_01_DATA_TRANS_TO_FMT_HIST2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_TS_QUEUE);
        if (BPRFHIS2.DISPLAY_IND != 'N') {
            if (BPB8030_AWA_8030.DC_FLG == ' ' 
                || (BPB8030_AWA_8030.DC_FLG != ' ' 
                && BPB8030_AWA_8030.DC_FLG == BPRFHIS2.DRCRFLG)) {
                CEP.TRC(SCCGWA, BPRFHIS2.KEY.JRNNO);
                WS_TS_QUEUE.WS_TS_TX_DT = BPRFHIS2.TX_DT;
                WS_TS_QUEUE.WS_TS_AC_DT = BPRFHIS2.KEY.AC_DT;
                WS_TS_QUEUE.WS_TS_JRNNO = BPRFHIS2.KEY.JRNNO;
                WS_TS_QUEUE.WS_TS_JRN_SEQ = BPRFHIS2.KEY.JRN_SEQ;
                WS_TS_QUEUE.WS_TS_AC = BPRFHIS2.KEY.AC;
                WS_TS_QUEUE.WS_TS_REF_NO = BPRFHIS2.REF_NO;
                WS_TS_QUEUE.WS_TS_TX_TOOL = BPRFHIS2.TX_TOOL;
                CEP.TRC(SCCGWA, "NCB0821007");
                CEP.TRC(SCCGWA, WS_TS_QUEUE.WS_TS_TX_TOOL);
                WS_TS_QUEUE.WS_TS_APP_MMO = BPRFHIS2.APP_MMO;
                WS_TS_QUEUE.WS_TS_TX_CD = BPRFHIS2.TX_CD;
                WS_TS_QUEUE.WS_TS_TX_BR = BPRFHIS2.TX_BR;
                WS_TS_QUEUE.WS_TS_TX_DP = BPRFHIS2.TX_DP;
                WS_TS_QUEUE.WS_TS_TX_TLR = BPRFHIS2.TX_TLR;
                WS_TS_QUEUE.WS_TS_SUP1 = BPRFHIS2.SUP1;
                WS_TS_QUEUE.WS_TS_SUP2 = BPRFHIS2.SUP2;
                WS_TS_QUEUE.WS_TS_TX_CHNL = BPRFHIS2.TX_CHNL;
                WS_TS_QUEUE.WS_TS_FIN_FLG = 'F';
                WS_TS_QUEUE.WS_TS_VCHNO = BPRFHIS2.VCHNO;
                if (!BPRFHIS2.CI_NO.equalsIgnoreCase("0") 
                    && BPRFHIS2.CI_NO.trim().length() > 0) {
                    WS_TS_QUEUE.WS_TS_CI_NO = BPRFHIS2.CI_NO;
                } else {
                    WS_TS_QUEUE.WS_TS_CI_NO = WS_OUT_CI_NO;
                }
                WS_TS_QUEUE.WS_TS_DRCR_FLG = BPRFHIS2.DRCRFLG;
                WS_TS_QUEUE.WS_TS_TX_CCY = BPRFHIS2.TX_CCY;
                WS_TS_QUEUE.WS_TS_TX_AMT = BPRFHIS2.TX_AMT;
                WS_TS_QUEUE.WS_TS_CUR_BAL = WS_CUR_BAL;
                WS_TS_QUEUE.WS_TS_TX_VAL_DT = BPRFHIS2.TX_VAL_DT;
                WS_TS_QUEUE.WS_TS_PROD_CD = BPRFHIS2.PROD_CD;
                WS_TS_QUEUE.WS_TS_STS = BPRFHIS2.TX_STS;
                WS_TS_QUEUE.WS_TS_TX_TM = BPRFHIS2.TX_TM;
                WS_TS_QUEUE.WS_TS_REMARK = BPRFHIS2.REMARK;
                WS_TS_QUEUE.WS_MAKER_TLR = BPRFHIS2.MAKER;
                WS_TS_QUEUE.WS_NARRATIVE = BPRFHIS2.NARRATIVE;
                WS_TS_QUEUE.WS_TX_MMO = BPRFHIS2.TX_MMO;
                IBS.init(SCCGWA, CICACCU);
                if (BPRFHIS2.RLT_TX_TOOL.trim().length() > 0) {
                    WS_TS_QUEUE.WS_REL_AC = BPRFHIS2.RLT_TX_TOOL;
                } else {
                    WS_TS_QUEUE.WS_REL_AC = BPRFHIS2.RLT_AC;
                }
                WS_TS_QUEUE.WS_STAFF_FLG = 'N';
                WS_TS_QUEUE.WS_REL_CI_NAME = BPRFHIS2.RLT_AC_NAME;
                WS_TS_QUEUE.WS_BV_CODE_CN = BPRFHIS2.BV_CODE;
                WS_TS_QUEUE.WS_HEAD_NO_CN = BPRFHIS2.HEAD_NO;
                WS_TS_QUEUE.WS_BV_NO_CN = BPRFHIS2.BV_NO;
                WS_TS_QUEUE.WS_COM_PROD_CN = BPRFHIS2.COM_PROD;
                WS_TS_QUEUE.WS_CCY_TYP_CN = BPRFHIS2.TX_CCY_TYPE;
                WS_ACO_AC = BPRFHIS2.ACO_AC;
                if (WS_ACO_AC.trim().length() > 0) {
                    CICQACAC.DATA.ACAC_NO = WS_ACO_AC;
                    CICQACAC.FUNC = 'A';
                    S000_CALL_CIZQACAC();
                    if (pgmRtn) return;
                    WS_TS_QUEUE.WS_AC_SEQU = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
                }
                WS_TS_QUEUE.WS_RLT_BANK = BPRFHIS2.RLT_BANK;
                IBS.init(SCCGWA, CICACCU);
                if (BPRFHIS2.KEY.AC.trim().length() > 0) {
                    CICACCU.DATA.AGR_NO = BPRFHIS2.KEY.AC;
                } else {
                    CICACCU.DATA.AGR_NO = BPRFHIS2.TX_TOOL;
                }
                S000_CALL_CIZACCU();
                if (pgmRtn) return;
                if (CICACCU.RC.RC_CODE == 0) {
                    if (CICACCU.DATA.AC_CNM.trim().length() > 0) {
                        WS_TX_AC_CHNM = CICACCU.DATA.AC_CNM;
                    } else if (CICACCU.DATA.AC_ENM.trim().length() > 0) {
                        WS_TX_AC_CHNM = CICACCU.DATA.AC_ENM;
                    } else if (CICACCU.DATA.CI_CNM.trim().length() > 0) {
                        WS_TX_AC_CHNM = CICACCU.DATA.CI_CNM;
                    } else if (CICACCU.DATA.CI_ENM.trim().length() > 0) {
                        WS_TX_AC_CHNM = CICACCU.DATA.CI_ENM;
                    } else {
                        WS_TX_AC_CHNM = " ";
                    }
                }
                WS_TX_AC_CHNM = CICACCU.DATA.AC_CNM;
                WS_D = (short) (WS_D + 1);
                WS_REC_CNT = WS_REC_CNT + 1;
                R000_TRANS_DATA_OUTPUT();
                if (pgmRtn) return;
            }
        }
    }
    public void B060_01_DATA_TRANS_TO_FMT3() throws IOException,SQLException,Exception {
        if (BPRFHIST.DISPLAY_IND != 'N') {
            if (BPB8030_AWA_8030.DC_FLG == ' ' 
                || (BPB8030_AWA_8030.DC_FLG != ' ' 
                && BPB8030_AWA_8030.DC_FLG == BPRFHIST.DRCRFLG)) {
                IBS.init(SCCGWA, WS_TS_QUEUE);
                WS_TS_QUEUE.WS_TS_TX_DT = BPRFHIST.TX_DT;
                WS_TS_QUEUE.WS_TS_AC_DT = BPRFHIST.KEY.AC_DT;
                WS_TS_QUEUE.WS_TS_JRNNO = BPRFHIST.KEY.JRNNO;
                WS_TS_QUEUE.WS_TS_JRN_SEQ = BPRFHIST.KEY.JRN_SEQ;
                WS_TS_QUEUE.WS_TS_AC = BPRFHIST.KEY.AC;
                WS_TS_QUEUE.WS_TS_REF_NO = BPRFHIST.REF_NO;
                WS_TS_QUEUE.WS_TS_TX_TOOL = BPRFHIST.TX_TOOL;
                CEP.TRC(SCCGWA, "NCB0821008");
                CEP.TRC(SCCGWA, WS_TS_QUEUE.WS_TS_TX_TOOL);
                WS_TS_QUEUE.WS_TS_APP_MMO = BPRFHIST.APP_MMO;
                WS_TS_QUEUE.WS_TS_TX_CD = BPRFHIST.TX_CD;
                WS_TS_QUEUE.WS_TS_TX_BR = BPRFHIST.TX_BR;
                WS_TS_QUEUE.WS_TS_TX_DP = BPRFHIST.TX_DP;
                WS_TS_QUEUE.WS_TS_TX_TLR = BPRFHIST.TX_TLR;
                WS_TS_QUEUE.WS_TS_SUP1 = BPRFHIST.SUP1;
                WS_TS_QUEUE.WS_TS_SUP2 = BPRFHIST.SUP2;
                WS_TS_QUEUE.WS_TS_TX_CHNL = BPRFHIST.TX_CHNL;
                WS_TS_QUEUE.WS_TS_FIN_FLG = 'F';
                WS_TS_QUEUE.WS_TS_VCHNO = BPRFHIST.VCHNO;
                if (!BPRFHIST.CI_NO.equalsIgnoreCase("0") 
                    && BPRFHIST.CI_NO.trim().length() > 0) {
                    WS_TS_QUEUE.WS_TS_CI_NO = BPRFHIST.CI_NO;
                } else {
                    WS_TS_QUEUE.WS_TS_CI_NO = WS_OUT_CI_NO;
                }
                CEP.TRC(SCCGWA, BPRFHIST.CI_NO);
                CEP.TRC(SCCGWA, WS_TS_QUEUE.WS_TS_CI_NO);
                CEP.TRC(SCCGWA, WS_OUT_CI_NO);
                WS_TS_QUEUE.WS_TS_DRCR_FLG = BPRFHIST.DRCRFLG;
                WS_TS_QUEUE.WS_TS_TX_CCY = BPRFHIST.TX_CCY;
                WS_TS_QUEUE.WS_TS_TX_AMT = BPRFHIST.TX_AMT;
                WS_TS_QUEUE.WS_TS_CUR_BAL = WS_CUR_BAL;
                WS_TS_QUEUE.WS_TS_TX_VAL_DT = BPRFHIST.TX_VAL_DT;
                WS_TS_QUEUE.WS_TS_PROD_CD = BPRFHIST.PROD_CD;
                WS_TS_QUEUE.WS_TS_STS = BPRFHIST.TX_STS;
                WS_TS_QUEUE.WS_TS_TX_TM = BPRFHIST.TX_TM;
                WS_TS_QUEUE.WS_TS_REMARK = BPRFHIST.REMARK;
                WS_TS_QUEUE.WS_MAKER_TLR = BPRFHIST.MAKER;
                WS_TS_QUEUE.WS_NARRATIVE = BPRFHIST.NARRATIVE;
                WS_TS_QUEUE.WS_TX_MMO = BPRFHIST.TX_MMO;
                IBS.init(SCCGWA, CICACCU);
                if (BPRFHIST.RLT_TX_TOOL.trim().length() > 0) {
                    WS_TS_QUEUE.WS_REL_AC = BPRFHIST.RLT_TX_TOOL;
                } else {
                    WS_TS_QUEUE.WS_REL_AC = BPRFHIST.RLT_AC;
                }
                WS_TS_QUEUE.WS_STAFF_FLG = 'N';
                WS_TS_QUEUE.WS_REL_CI_NAME = BPRFHIST.RLT_AC_NAME;
                WS_TS_QUEUE.WS_BV_CODE_CN = BPRFHIST.BV_CODE;
                WS_TS_QUEUE.WS_HEAD_NO_CN = BPRFHIST.HEAD_NO;
                WS_TS_QUEUE.WS_BV_NO_CN = BPRFHIST.BV_NO;
                WS_TS_QUEUE.WS_COM_PROD_CN = BPRFHIST.COM_PROD;
                WS_TS_QUEUE.WS_CCY_TYP_CN = BPRFHIST.TX_CCY_TYPE;
                WS_ACO_AC = BPRFHIST.ACO_AC;
                if (WS_ACO_AC.trim().length() > 0) {
                    CICQACAC.DATA.ACAC_NO = WS_ACO_AC;
                    CICQACAC.FUNC = 'A';
                    S000_CALL_CIZQACAC();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ);
                    WS_TS_QUEUE.WS_AC_SEQU = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
                }
                WS_TS_QUEUE.WS_RLT_BANK = BPRFHIST.RLT_BANK;
                IBS.init(SCCGWA, CICACCU);
                if (BPRFHIS1.KEY.AC.trim().length() > 0) {
                    CICACCU.DATA.AGR_NO = BPRFHIS1.KEY.AC;
                } else {
                    CICACCU.DATA.AGR_NO = BPRFHIS1.TX_TOOL;
                }
                S000_CALL_CIZACCU();
                if (pgmRtn) return;
                if (CICACCU.RC.RC_CODE == 0) {
                    if (CICACCU.DATA.AC_CNM.trim().length() > 0) {
                        WS_TX_AC_CHNM = CICACCU.DATA.AC_CNM;
                    } else if (CICACCU.DATA.AC_ENM.trim().length() > 0) {
                        WS_TX_AC_CHNM = CICACCU.DATA.AC_ENM;
                    } else if (CICACCU.DATA.CI_CNM.trim().length() > 0) {
                        WS_TX_AC_CHNM = CICACCU.DATA.CI_CNM;
                    } else if (CICACCU.DATA.CI_ENM.trim().length() > 0) {
                        WS_TX_AC_CHNM = CICACCU.DATA.CI_ENM;
                    } else {
                        WS_TX_AC_CHNM = " ";
                    }
                }
                WS_TX_AC_CHNM = CICACCU.DATA.AC_CNM;
                WS_REC_CNT = WS_REC_CNT + 1;
                BPCFHTTT.TOTAL_NUM = (short) (BPCFHTTT.TOTAL_NUM + 1);
                R000_TRANS_DATA_OUTPUT();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, WS_TS_QUEUE.WS_TS_JRNNO);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void T000_STARTBR_JRNNO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCRCWA.JRN_IN_USE);
        if (SCRCWA.JRN_IN_USE == '1') {
            CEP.TRC(SCCGWA, "READ TABLE2");
            BPTFHIS1_BR.rp = new DBParm();
            BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
            BPTFHIS1_BR.rp.where = "AC_DT <= :WS_END_DT "
                + "AND AC_DT >= :WS_STR_DT "
                + "AND JRNNO = :WS_JRNNO";
            BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
        } else {
            CEP.TRC(SCCGWA, "READ TABLE1");
            BPTFHIS2_BR.rp = new DBParm();
            BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS2_BR.rp.where = "AC_DT <= :WS_END_DT "
                + "AND AC_DT >= :WS_STR_DT "
                + "AND JRNNO = :WS_JRNNO";
            BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
        }
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.STR_DT);
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.JRNNO);
        CEP.TRC(SCCGWA, WS_STR_DT);
        CEP.TRC(SCCGWA, WS_JRNNO);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
            CEP.TRC(SCCGWA, "FOUND CCC");
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BRTLR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCRCWA.JRN_IN_USE);
        if (WS_TX_TLR.trim().length() > 0) {
            if (SCRCWA.JRN_IN_USE == '1') {
                BPTFHIS1_BR.rp = new DBParm();
                BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
                BPTFHIS1_BR.rp.where = "TX_BR = :WS_TX_BR "
                    + "AND TX_TLR = :WS_TX_TLR "
                    + "AND AC_DT >= :WS_STR_DT "
                    + "AND AC_DT <= :WS_END_DT";
                BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
            } else {
                BPTFHIS2_BR.rp = new DBParm();
                BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
                BPTFHIS2_BR.rp.where = "TX_BR = :WS_TX_BR "
                    + "AND TX_TLR = :WS_TX_TLR "
                    + "AND AC_DT >= :WS_STR_DT "
                    + "AND AC_DT <= :WS_END_DT";
                BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
            }
        } else {
            CEP.TRC(SCCGWA, "WS-TX-CD= ");
            CEP.TRC(SCCGWA, WS_TX_CD);
            if (SCRCWA.JRN_IN_USE == '1') {
                BPTFHIS1_BR.rp = new DBParm();
                BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
                BPTFHIS1_BR.rp.where = "TX_BR = :WS_TX_BR "
                    + "AND TX_CD = :WS_TX_CD "
                    + "AND AC_DT >= :WS_STR_DT "
                    + "AND AC_DT <= :WS_END_DT";
                BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
            } else {
                BPTFHIS2_BR.rp = new DBParm();
                BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
                BPTFHIS2_BR.rp.where = "TX_BR = :WS_TX_BR "
                    + "AND TX_CD = :WS_TX_CD "
                    + "AND AC_DT >= :WS_STR_DT "
                    + "AND AC_DT <= :WS_END_DT";
                BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
            }
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_ACOAC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCRCWA.JRN_IN_USE);
        if (WS_TX_TLR.trim().length() == 0) {
            if (SCRCWA.JRN_IN_USE == '1') {
                if (WS_CARD_SUB == 'Y') {
                    BPTFHIS1_BR.rp = new DBParm();
                    BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
                    BPTFHIS1_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                        + "AND AC_DT >= :WS_STR_DT "
                        + "AND AC_DT <= :WS_END_DT "
                        + "AND ( TX_TOOL = :WS_TX_TOOL "
                        + "OR TX_TOOL = ' ' ) "
                        + "AND PART_NO = :WS_PART_NO";
                    BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
                } else {
                    BPTFHIS1_BR.rp = new DBParm();
                    BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
                    BPTFHIS1_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                        + "AND AC_DT >= :WS_STR_DT "
                        + "AND AC_DT <= :WS_END_DT "
                        + "AND PART_NO = :WS_PART_NO";
                    BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
                }
            } else {
                if (WS_CARD_SUB == 'Y') {
                    BPTFHIS2_BR.rp = new DBParm();
                    BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
                    BPTFHIS2_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                        + "AND AC_DT >= :WS_STR_DT "
                        + "AND AC_DT <= :WS_END_DT "
                        + "AND ( TX_TOOL = :WS_TX_TOOL "
                        + "OR TX_TOOL = ' ' ) "
                        + "AND PART_NO = :WS_PART_NO";
                    BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
                } else {
                    BPTFHIS2_BR.rp = new DBParm();
                    BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
                    BPTFHIS2_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                        + "AND AC_DT >= :WS_STR_DT "
                        + "AND AC_DT <= :WS_END_DT "
                        + "AND PART_NO = :WS_PART_NO";
                    BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
                }
            }
        } else {
            if (SCRCWA.JRN_IN_USE == '1') {
                if (WS_CARD_SUB == 'Y') {
                    BPTFHIS1_BR.rp = new DBParm();
                    BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
                    BPTFHIS1_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                        + "AND TX_TLR = :WS_TX_TLR "
                        + "AND TX_BR = :WS_TX_BR "
                        + "AND AC_DT >= :WS_STR_DT "
                        + "AND AC_DT <= :WS_END_DT "
                        + "AND ( TX_TOOL = :WS_TX_TOOL "
                        + "OR TX_TOOL = ' ' ) "
                        + "AND PART_NO = :WS_PART_NO";
                    BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
                } else {
                    BPTFHIS1_BR.rp = new DBParm();
                    BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
                    BPTFHIS1_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                        + "AND TX_TLR = :WS_TX_TLR "
                        + "AND TX_BR = :WS_TX_BR "
                        + "AND AC_DT >= :WS_STR_DT "
                        + "AND AC_DT <= :WS_END_DT "
                        + "AND PART_NO = :WS_PART_NO";
                    BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
                }
            } else {
                if (WS_CARD_SUB == 'Y') {
                    BPTFHIS2_BR.rp = new DBParm();
                    BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
                    BPTFHIS2_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                        + "AND TX_TLR = :WS_TX_TLR "
                        + "AND TX_BR = :WS_TX_BR "
                        + "AND AC_DT >= :WS_STR_DT "
                        + "AND AC_DT <= :WS_END_DT "
                        + "AND ( TX_TOOL = :WS_TX_TOOL "
                        + "OR TX_TOOL = ' ' ) "
                        + "AND PART_NO = :WS_PART_NO";
                    BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
                } else {
                    BPTFHIS2_BR.rp = new DBParm();
                    BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
                    BPTFHIS2_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                        + "AND TX_TLR = :WS_TX_TLR "
                        + "AND TX_BR = :WS_TX_BR "
                        + "AND AC_DT >= :WS_STR_DT "
                        + "AND AC_DT <= :WS_END_DT "
                        + "AND PART_NO = :WS_PART_NO";
                    BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
                }
            }
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "READ NORMAL");
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOT FOUND CAI GUAI");
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_JRNNO1() throws IOException,SQLException,Exception {
        BPTFHIS1_BR.rp = new DBParm();
        BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
        BPTFHIS1_BR.rp.where = "AC_DT <= :WS_END_DT "
            + "AND AC_DT >= :WS_STR_DT "
            + "AND JRNNO = :WS_JRNNO";
        BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
        IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.STR_DT);
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.JRNNO);
        CEP.TRC(SCCGWA, WS_STR_DT);
        CEP.TRC(SCCGWA, WS_JRNNO);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
            CEP.TRC(SCCGWA, "FOUND CCC");
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIS1";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BRTLR1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCRCWA.JRN_IN_USE);
        if (WS_TX_TLR.trim().length() > 0) {
            BPTFHIS1_BR.rp = new DBParm();
            BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
            BPTFHIS1_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TLR "
                + "AND AC_DT >= :WS_STR_DT "
                + "AND AC_DT <= :WS_END_DT";
            BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
        } else {
            CEP.TRC(SCCGWA, "WS-TX-CD= ");
            CEP.TRC(SCCGWA, WS_TX_CD);
            BPTFHIS1_BR.rp = new DBParm();
            BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
            BPTFHIS1_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_CD = :WS_TX_CD "
                + "AND AC_DT >= :WS_STR_DT "
                + "AND AC_DT <= :WS_END_DT";
            BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIS1";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_ACOAC1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCRCWA.JRN_IN_USE);
        if (WS_TX_TLR.trim().length() == 0) {
            if (WS_CARD_SUB == 'Y') {
                BPTFHIS1_BR.rp = new DBParm();
                BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
                BPTFHIS1_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                    + "AND AC_DT >= :WS_STR_DT "
                    + "AND AC_DT <= :WS_END_DT "
                    + "AND ( TX_TOOL = :WS_TX_TOOL "
                    + "OR TX_TOOL = ' ' ) "
                    + "AND PART_NO = :WS_PART_NO";
                BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
            } else {
                BPTFHIS1_BR.rp = new DBParm();
                BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
                BPTFHIS1_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                    + "AND AC_DT >= :WS_STR_DT "
                    + "AND AC_DT <= :WS_END_DT "
                    + "AND PART_NO = :WS_PART_NO";
                BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
            }
        } else {
            if (WS_CARD_SUB == 'Y') {
                BPTFHIS1_BR.rp = new DBParm();
                BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
                BPTFHIS1_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                    + "AND TX_TLR = :WS_TX_TLR "
                    + "AND TX_BR = :WS_TX_BR "
                    + "AND AC_DT >= :WS_STR_DT "
                    + "AND AC_DT <= :WS_END_DT "
                    + "AND ( TX_TOOL = :WS_TX_TOOL "
                    + "OR TX_TOOL = ' ' ) "
                    + "AND PART_NO = :WS_PART_NO";
                BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
            } else {
                BPTFHIS1_BR.rp = new DBParm();
                BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
                BPTFHIS1_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                    + "AND TX_TLR = :WS_TX_TLR "
                    + "AND TX_BR = :WS_TX_BR "
                    + "AND AC_DT >= :WS_STR_DT "
                    + "AND AC_DT <= :WS_END_DT "
                    + "AND PART_NO = :WS_PART_NO";
                BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
            }
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "READ NORMAL");
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOT FOUND CAI GUAI");
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIS1";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_JRNNO2() throws IOException,SQLException,Exception {
        BPTFHIS2_BR.rp = new DBParm();
        BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
        BPTFHIS2_BR.rp.where = "AC_DT <= :WS_END_DT "
            + "AND AC_DT >= :WS_STR_DT "
            + "AND JRNNO = :WS_JRNNO";
        BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
        IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.STR_DT);
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.JRNNO);
        CEP.TRC(SCCGWA, WS_STR_DT);
        CEP.TRC(SCCGWA, WS_JRNNO);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
            CEP.TRC(SCCGWA, "FOUND CCC");
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIS2";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BRTLR2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCRCWA.JRN_IN_USE);
        if (WS_TX_TLR.trim().length() > 0) {
            BPTFHIS2_BR.rp = new DBParm();
            BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS2_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TLR "
                + "AND AC_DT >= :WS_STR_DT "
                + "AND AC_DT <= :WS_END_DT";
            BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
        } else {
            CEP.TRC(SCCGWA, "WS-TX-CD= ");
            CEP.TRC(SCCGWA, WS_TX_CD);
            BPTFHIS2_BR.rp = new DBParm();
            BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS2_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_CD = :WS_TX_CD "
                + "AND AC_DT >= :WS_STR_DT "
                + "AND AC_DT <= :WS_END_DT";
            BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIS1";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_ACOAC2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCRCWA.JRN_IN_USE);
        if (WS_TX_TLR.trim().length() == 0) {
            if (WS_CARD_SUB == 'Y') {
                BPTFHIS2_BR.rp = new DBParm();
                BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
                BPTFHIS2_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                    + "AND AC_DT >= :WS_STR_DT "
                    + "AND AC_DT <= :WS_END_DT "
                    + "AND ( TX_TOOL = :WS_TX_TOOL "
                    + "OR TX_TOOL = ' ' ) "
                    + "AND PART_NO = :WS_PART_NO";
                BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
            } else {
                BPTFHIS2_BR.rp = new DBParm();
                BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
                BPTFHIS2_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                    + "AND AC_DT >= :WS_STR_DT "
                    + "AND AC_DT <= :WS_END_DT "
                    + "AND PART_NO = :WS_PART_NO";
                BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
            }
        } else {
            if (WS_CARD_SUB == 'Y') {
                BPTFHIS2_BR.rp = new DBParm();
                BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
                BPTFHIS2_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                    + "AND TX_TLR = :WS_TX_TLR "
                    + "AND TX_BR = :WS_TX_BR "
                    + "AND AC_DT >= :WS_STR_DT "
                    + "AND AC_DT <= :WS_END_DT "
                    + "AND ( TX_TOOL = :WS_TX_TOOL "
                    + "OR TX_TOOL = ' ' ) "
                    + "AND PART_NO = :WS_PART_NO";
                BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
            } else {
                BPTFHIS2_BR.rp = new DBParm();
                BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
                BPTFHIS2_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                    + "AND TX_TLR = :WS_TX_TLR "
                    + "AND TX_BR = :WS_TX_BR "
                    + "AND AC_DT >= :WS_STR_DT "
                    + "AND AC_DT <= :WS_END_DT "
                    + "AND PART_NO = :WS_PART_NO";
                BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
            }
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "READ NORMAL");
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOT FOUND CAI GUAI");
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIS2";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_JRNNO3() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCRCWA.JRN_IN_USE);
        BPTFHIST_BR.rp = new DBParm();
        BPTFHIST_BR.rp.TableName = "BPTFHIST";
        BPTFHIST_BR.rp.where = "AC_DT >= :WS_STR_DT "
            + "AND JRNNO = :WS_JRNNO "
            + "AND AC_DT <= :WS_END_DT";
        BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
        IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BRTLR3() throws IOException,SQLException,Exception {
        if (WS_TX_TLR.trim().length() > 0) {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TLR "
                + "AND AC_DT >= :WS_STR_DT "
                + "AND AC_DT <= :WS_END_DT";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_CD = :WS_TX_CD "
                + "AND AC_DT >= :WS_STR_DT "
                + "AND AC_DT <= :WS_END_DT";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_ACOAC3() throws IOException,SQLException,Exception {
        if (WS_TX_TLR.trim().length() == 0) {
            if (WS_CARD_SUB == 'Y') {
                BPTFHIST_BR.rp = new DBParm();
                BPTFHIST_BR.rp.TableName = "BPTFHIST";
                BPTFHIST_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                    + "AND AC_DT >= :WS_STR_DT "
                    + "AND AC_DT <= :WS_END_DT "
                    + "AND ( TX_TOOL = :WS_TX_TOOL "
                    + "OR TX_TOOL = ' ' ) "
                    + "AND PART_NO = :WS_PART_NO";
                BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
            } else {
                BPTFHIST_BR.rp = new DBParm();
                BPTFHIST_BR.rp.TableName = "BPTFHIST";
                BPTFHIST_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                    + "AND AC_DT >= :WS_STR_DT "
                    + "AND AC_DT <= :WS_END_DT "
                    + "AND PART_NO = :WS_PART_NO";
                BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
            }
        } else {
            if (WS_CARD_SUB == 'Y') {
                BPTFHIST_BR.rp = new DBParm();
                BPTFHIST_BR.rp.TableName = "BPTFHIST";
                BPTFHIST_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                    + "AND TX_BR = :WS_TX_BR "
                    + "AND TX_TLR = :WS_TX_TLR "
                    + "AND AC_DT >= :WS_STR_DT "
                    + "AND AC_DT <= :WS_END_DT "
                    + "AND ( TX_TOOL = :WS_TX_TOOL "
                    + "OR TX_TOOL = ' ' ) "
                    + "AND PART_NO = :WS_PART_NO";
                BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
            } else {
                BPTFHIST_BR.rp = new DBParm();
                BPTFHIST_BR.rp.TableName = "BPTFHIST";
                BPTFHIST_BR.rp.where = "ACO_AC = :WS_ACO_AC "
                    + "AND TX_BR = :WS_TX_BR "
                    + "AND TX_TLR = :WS_TX_TLR "
                    + "AND AC_DT >= :WS_STR_DT "
                    + "AND AC_DT <= :WS_END_DT "
                    + "AND PART_NO = :WS_PART_NO";
                BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
            }
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTFHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCRCWA.JRN_IN_USE);
        if (SCRCWA.JRN_IN_USE == '1') {
            IBS.READNEXT(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
        } else {
            IBS.READNEXT(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
        }
        CEP.TRC(SCCGWA, "DDDD");
        CEP.TRC(SCCGWA, BPRFHIS1.KEY.JRNNO);
        CEP.TRC(SCCGWA, BPRFHIS2.KEY.JRNNO);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
            if (SCRCWA.JRN_IN_USE == '1') {
                CICQACAC.DATA.ACAC_NO = BPRFHIS1.ACO_AC;
            } else {
                CICQACAC.DATA.ACAC_NO = BPRFHIS2.ACO_AC;
            }
            CICQACAC.FUNC = 'A';
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            WS_OUT_CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
            R000_GET_AC_BAL();
            if (pgmRtn) return;
            if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("IB") 
                || CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("LN")) {
                WS_CUR_BAL = WS_CUR_BAL * ( -1 );
            }
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTFHIS1() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
            if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.trim().length() == 0) {
                CICQACAC.DATA.ACAC_NO = BPRFHIS1.ACO_AC;
                CICQACAC.FUNC = 'A';
                S000_CALL_CIZQACAC();
                if (pgmRtn) return;
            }
            if (WS_D == 0) {
                R000_GET_AC_BAL1();
                if (pgmRtn) return;
            }
            if (BPRFHIS1.DRCRFLG == 'C') {
                WS_CUR_BAL = WS_CUR_BAL + BPRFHIS1.TX_AMT;
            } else {
                WS_CUR_BAL = WS_CUR_BAL - BPRFHIS1.TX_AMT;
            }
            if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("IB") 
                || CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("LN")) {
                WS_OUT_CUR_BAL = WS_CUR_BAL * ( -1 );
            } else {
                WS_OUT_CUR_BAL = WS_CUR_BAL;
            }
            WS_OUT_CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIS1";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTFHIS2() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
            if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.trim().length() == 0) {
                CICQACAC.DATA.ACAC_NO = BPRFHIS2.ACO_AC;
                CICQACAC.FUNC = 'A';
                S000_CALL_CIZQACAC();
                if (pgmRtn) return;
            }
            if (WS_D == 0) {
                R000_GET_AC_BAL2();
                if (pgmRtn) return;
            }
            if (BPRFHIS2.DRCRFLG == 'C') {
                WS_CUR_BAL = WS_CUR_BAL + BPRFHIS2.TX_AMT;
            } else {
                WS_CUR_BAL = WS_CUR_BAL - BPRFHIS2.TX_AMT;
            }
            if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("IB") 
                || CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("LN")) {
                WS_OUT_CUR_BAL = WS_CUR_BAL * ( -1 );
            } else {
                WS_OUT_CUR_BAL = WS_CUR_BAL;
            }
            WS_OUT_CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIS2";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTFHIST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'A';
            CICQACAC.DATA.ACAC_NO = BPRFHIST.ACO_AC;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
            WS_OUT_CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
            if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("IB") 
                || CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("LN")) {
                WS_CUR_BAL = BPRFHIST.CURR_BAL * ( -1 );
            } else {
                WS_CUR_BAL = BPRFHIST.CURR_BAL;
            }
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFHIST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTFHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCRCWA.JRN_IN_USE);
        if (SCRCWA.JRN_IN_USE == '1') {
            IBS.ENDBR(SCCGWA, BPTFHIS1_BR);
        } else {
            IBS.ENDBR(SCCGWA, BPTFHIS2_BR);
        }
    }
    public void T000_ENDBR_BPTFHIS1() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFHIS1_BR);
    }
    public void T000_ENDBR_BPTFHIS2() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFHIS2_BR);
    }
    public void T000_READ_BPTFHISA() throws IOException,SQLException,Exception {
        BPTFHISA_RD = new DBParm();
        BPTFHISA_RD.TableName = "BPTFHISA";
        BPTFHISA_RD.col = "AC_DT,CUR_BAL";
        BPTFHISA_RD.where = "AC = :BPRFHISA.KEY.AC "
            + "AND CCY = :BPRFHISA.KEY.CCY "
            + "AND CCY_TYP = :BPRFHISA.KEY.CCY_TYP "
            + "AND AC_DT < :BPRFHISA.AC_DT "
            + "AND PART_NO = :BPRFHISA.KEY.PART_NO";
        BPTFHISA_RD.fst = true;
        BPTFHISA_RD.order = "AC_DT DESC";
        IBS.READ(SCCGWA, BPRFHISA, this, BPTFHISA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BPTFHISA_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BPTFHISA_FLG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTFHIST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFHIST_BR);
    }
    public void T000_SELECT_BPTPARM() throws IOException,SQLException,Exception {
        BPTPARM_RD = new DBParm();
        BPTPARM_RD.TableName = "BPTPARM";
        IBS.READ(SCCGWA, BPRPARM, BPTPARM_RD);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        CEP.TRC(SCCGWA, CICQACAC.RC);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ORG_NOTFND);
        }
    }
    public void S000_CALL_BPZPORUP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-SUPR-ORG", BPCPORUP);
    }
    public void S000_CALL_BPZUIBAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-U-INQ-BAL";
        SCCCALL.COMMAREA_PTR = BPCUIBAL;
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        CEP.TRC(SCCGWA, BPCUIBAL.RC);
    }
    public void S000_CALL_BPZTHIST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-PROC-NHIST", BPCTHIST);
        CEP.TRC(SCCGWA, BPCTHIST.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTHIST.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            WS_NHIST_FLG = 'Y';
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTHIST.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
                WS_NHIST_FLG = 'N';
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTHIST.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-PARM-MAINTAIN";
        SCCCALL.COMMAREA_PTR = BPCPRMM;
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
        }
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
    public void R000_GET_PART_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.ACAC_NO = WS_H_ACO_AC;
        CICQACAC.FUNC = 'A';
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        WS_AC_HASH = IBS.CalcHash(99991, WS_H_ACO_AC);
        if (WS_AC_HASH >= 0 
                && WS_AC_HASH <= 783) {
            WS_PART_NO = 1;
        } else if (WS_AC_HASH >= 784 
                && WS_AC_HASH <= 1567) {
            WS_PART_NO = 2;
        } else if (WS_AC_HASH >= 1568 
                && WS_AC_HASH <= 2351) {
            WS_PART_NO = 3;
        } else if (WS_AC_HASH >= 2352 
                && WS_AC_HASH <= 3135) {
            WS_PART_NO = 4;
        } else if (WS_AC_HASH >= 3136 
                && WS_AC_HASH <= 3919) {
            WS_PART_NO = 5;
        } else if (WS_AC_HASH >= 3920 
                && WS_AC_HASH <= 4703) {
            WS_PART_NO = 6;
        } else if (WS_AC_HASH >= 4704 
                && WS_AC_HASH <= 5487) {
            WS_PART_NO = 7;
        } else if (WS_AC_HASH >= 5488 
                && WS_AC_HASH <= 6271) {
            WS_PART_NO = 8;
        } else if (WS_AC_HASH >= 6272 
                && WS_AC_HASH <= 7055) {
            WS_PART_NO = 9;
        } else if (WS_AC_HASH >= 7056 
                && WS_AC_HASH <= 7839) {
            WS_PART_NO = 10;
        } else if (WS_AC_HASH >= 7840 
                && WS_AC_HASH <= 8623) {
            WS_PART_NO = 11;
        } else if (WS_AC_HASH >= 8624 
                && WS_AC_HASH <= 9407) {
            WS_PART_NO = 12;
        } else if (WS_AC_HASH >= 9408 
                && WS_AC_HASH <= 10191) {
            WS_PART_NO = 13;
        } else if (WS_AC_HASH >= 10192 
                && WS_AC_HASH <= 10975) {
            WS_PART_NO = 14;
        } else if (WS_AC_HASH >= 10976 
                && WS_AC_HASH <= 11759) {
            WS_PART_NO = 15;
        } else if (WS_AC_HASH >= 11760 
                && WS_AC_HASH <= 12543) {
            WS_PART_NO = 16;
        } else if (WS_AC_HASH >= 12544 
                && WS_AC_HASH <= 13327) {
            WS_PART_NO = 17;
        } else if (WS_AC_HASH >= 13328 
                && WS_AC_HASH <= 14111) {
            WS_PART_NO = 18;
        } else if (WS_AC_HASH >= 14112 
                && WS_AC_HASH <= 14895) {
            WS_PART_NO = 19;
        } else if (WS_AC_HASH >= 14896 
                && WS_AC_HASH <= 15679) {
            WS_PART_NO = 20;
        } else if (WS_AC_HASH >= 15680 
                && WS_AC_HASH <= 16463) {
            WS_PART_NO = 21;
        } else if (WS_AC_HASH >= 16464 
                && WS_AC_HASH <= 17247) {
            WS_PART_NO = 22;
        } else if (WS_AC_HASH >= 17248 
                && WS_AC_HASH <= 18031) {
            WS_PART_NO = 23;
        } else if (WS_AC_HASH >= 18032 
                && WS_AC_HASH <= 18815) {
            WS_PART_NO = 24;
        } else if (WS_AC_HASH >= 18816 
                && WS_AC_HASH <= 19599) {
            WS_PART_NO = 25;
        } else if (WS_AC_HASH >= 19600 
                && WS_AC_HASH <= 20383) {
            WS_PART_NO = 26;
        } else if (WS_AC_HASH >= 20384 
                && WS_AC_HASH <= 21167) {
            WS_PART_NO = 27;
        } else if (WS_AC_HASH >= 21168 
                && WS_AC_HASH <= 21951) {
            WS_PART_NO = 28;
        } else if (WS_AC_HASH >= 21952 
                && WS_AC_HASH <= 22735) {
            WS_PART_NO = 29;
        } else if (WS_AC_HASH >= 22736 
                && WS_AC_HASH <= 23519) {
            WS_PART_NO = 30;
        } else if (WS_AC_HASH >= 23520 
                && WS_AC_HASH <= 24303) {
            WS_PART_NO = 31;
        } else if (WS_AC_HASH >= 24304 
                && WS_AC_HASH <= 25087) {
            WS_PART_NO = 32;
        } else if (WS_AC_HASH >= 25088 
                && WS_AC_HASH <= 25871) {
            WS_PART_NO = 33;
        } else if (WS_AC_HASH >= 25872 
                && WS_AC_HASH <= 26655) {
            WS_PART_NO = 34;
        } else if (WS_AC_HASH >= 26656 
                && WS_AC_HASH <= 27439) {
            WS_PART_NO = 35;
        } else if (WS_AC_HASH >= 27440 
                && WS_AC_HASH <= 28223) {
            WS_PART_NO = 36;
        } else if (WS_AC_HASH >= 28224 
                && WS_AC_HASH <= 29007) {
            WS_PART_NO = 37;
        } else if (WS_AC_HASH >= 29008 
                && WS_AC_HASH <= 29790) {
            WS_PART_NO = 38;
        } else if (WS_AC_HASH >= 29791 
                && WS_AC_HASH <= 30570) {
            WS_PART_NO = 39;
        } else if (WS_AC_HASH >= 30571 
                && WS_AC_HASH <= 31350) {
            WS_PART_NO = 40;
        } else if (WS_AC_HASH >= 31351 
                && WS_AC_HASH <= 32130) {
            WS_PART_NO = 41;
        } else if (WS_AC_HASH >= 32131 
                && WS_AC_HASH <= 32910) {
            WS_PART_NO = 42;
        } else if (WS_AC_HASH >= 32911 
                && WS_AC_HASH <= 33690) {
            WS_PART_NO = 43;
        } else if (WS_AC_HASH >= 33691 
                && WS_AC_HASH <= 34470) {
            WS_PART_NO = 44;
        } else if (WS_AC_HASH >= 34471 
                && WS_AC_HASH <= 35250) {
            WS_PART_NO = 45;
        } else if (WS_AC_HASH >= 35251 
                && WS_AC_HASH <= 36030) {
            WS_PART_NO = 46;
        } else if (WS_AC_HASH >= 36031 
                && WS_AC_HASH <= 36810) {
            WS_PART_NO = 47;
        } else if (WS_AC_HASH >= 36811 
                && WS_AC_HASH <= 37590) {
            WS_PART_NO = 48;
        } else if (WS_AC_HASH >= 37591 
                && WS_AC_HASH <= 38370) {
            WS_PART_NO = 49;
        } else if (WS_AC_HASH >= 38371 
                && WS_AC_HASH <= 39150) {
            WS_PART_NO = 50;
        } else if (WS_AC_HASH >= 39151 
                && WS_AC_HASH <= 39930) {
            WS_PART_NO = 51;
        } else if (WS_AC_HASH >= 39931 
                && WS_AC_HASH <= 40710) {
            WS_PART_NO = 52;
        } else if (WS_AC_HASH >= 40711 
                && WS_AC_HASH <= 41490) {
            WS_PART_NO = 53;
        } else if (WS_AC_HASH >= 41491 
                && WS_AC_HASH <= 42270) {
            WS_PART_NO = 54;
        } else if (WS_AC_HASH >= 42271 
                && WS_AC_HASH <= 43050) {
            WS_PART_NO = 55;
        } else if (WS_AC_HASH >= 43051 
                && WS_AC_HASH <= 43830) {
            WS_PART_NO = 56;
        } else if (WS_AC_HASH >= 43831 
                && WS_AC_HASH <= 44610) {
            WS_PART_NO = 57;
        } else if (WS_AC_HASH >= 44611 
                && WS_AC_HASH <= 45390) {
            WS_PART_NO = 58;
        } else if (WS_AC_HASH >= 45391 
                && WS_AC_HASH <= 46170) {
            WS_PART_NO = 59;
        } else if (WS_AC_HASH >= 46171 
                && WS_AC_HASH <= 46950) {
            WS_PART_NO = 60;
        } else if (WS_AC_HASH >= 46951 
                && WS_AC_HASH <= 47730) {
            WS_PART_NO = 61;
        } else if (WS_AC_HASH >= 47731 
                && WS_AC_HASH <= 48510) {
            WS_PART_NO = 62;
        } else if (WS_AC_HASH >= 48511 
                && WS_AC_HASH <= 49290) {
            WS_PART_NO = 63;
        } else if (WS_AC_HASH >= 49291 
                && WS_AC_HASH <= 50070) {
            WS_PART_NO = 64;
        } else if (WS_AC_HASH >= 50071 
                && WS_AC_HASH <= 50850) {
            WS_PART_NO = 65;
        } else if (WS_AC_HASH >= 50851 
                && WS_AC_HASH <= 51630) {
            WS_PART_NO = 66;
        } else if (WS_AC_HASH >= 51631 
                && WS_AC_HASH <= 52410) {
            WS_PART_NO = 67;
        } else if (WS_AC_HASH >= 52411 
                && WS_AC_HASH <= 53190) {
            WS_PART_NO = 68;
        } else if (WS_AC_HASH >= 53191 
                && WS_AC_HASH <= 53970) {
            WS_PART_NO = 69;
        } else if (WS_AC_HASH >= 53971 
                && WS_AC_HASH <= 54750) {
            WS_PART_NO = 70;
        } else if (WS_AC_HASH >= 54751 
                && WS_AC_HASH <= 55530) {
            WS_PART_NO = 71;
        } else if (WS_AC_HASH >= 55531 
                && WS_AC_HASH <= 56310) {
            WS_PART_NO = 72;
        } else if (WS_AC_HASH >= 56311 
                && WS_AC_HASH <= 57090) {
            WS_PART_NO = 73;
        } else if (WS_AC_HASH >= 57091 
                && WS_AC_HASH <= 57870) {
            WS_PART_NO = 74;
        } else if (WS_AC_HASH >= 57871 
                && WS_AC_HASH <= 58650) {
            WS_PART_NO = 75;
        } else if (WS_AC_HASH >= 58651 
                && WS_AC_HASH <= 59430) {
            WS_PART_NO = 76;
        } else if (WS_AC_HASH >= 59431 
                && WS_AC_HASH <= 60210) {
            WS_PART_NO = 77;
        } else if (WS_AC_HASH >= 60211 
                && WS_AC_HASH <= 60990) {
            WS_PART_NO = 78;
        } else if (WS_AC_HASH >= 60991 
                && WS_AC_HASH <= 61770) {
            WS_PART_NO = 79;
        } else if (WS_AC_HASH >= 61771 
                && WS_AC_HASH <= 62550) {
            WS_PART_NO = 80;
        } else if (WS_AC_HASH >= 62551 
                && WS_AC_HASH <= 63330) {
            WS_PART_NO = 81;
        } else if (WS_AC_HASH >= 63331 
                && WS_AC_HASH <= 64110) {
            WS_PART_NO = 82;
        } else if (WS_AC_HASH >= 64111 
                && WS_AC_HASH <= 64890) {
            WS_PART_NO = 83;
        } else if (WS_AC_HASH >= 64891 
                && WS_AC_HASH <= 65670) {
            WS_PART_NO = 84;
        } else if (WS_AC_HASH >= 65671 
                && WS_AC_HASH <= 66450) {
            WS_PART_NO = 85;
        } else if (WS_AC_HASH >= 66451 
                && WS_AC_HASH <= 67230) {
            WS_PART_NO = 86;
        } else if (WS_AC_HASH >= 67231 
                && WS_AC_HASH <= 68010) {
            WS_PART_NO = 87;
        } else if (WS_AC_HASH >= 68011 
                && WS_AC_HASH <= 68790) {
            WS_PART_NO = 88;
        } else if (WS_AC_HASH >= 68791 
                && WS_AC_HASH <= 69570) {
            WS_PART_NO = 89;
        } else if (WS_AC_HASH >= 69571 
                && WS_AC_HASH <= 70350) {
            WS_PART_NO = 90;
        } else if (WS_AC_HASH >= 70351 
                && WS_AC_HASH <= 71130) {
            WS_PART_NO = 91;
        } else if (WS_AC_HASH >= 71131 
                && WS_AC_HASH <= 71910) {
            WS_PART_NO = 92;
        } else if (WS_AC_HASH >= 71911 
                && WS_AC_HASH <= 72690) {
            WS_PART_NO = 93;
        } else if (WS_AC_HASH >= 72691 
                && WS_AC_HASH <= 73470) {
            WS_PART_NO = 94;
        } else if (WS_AC_HASH >= 73471 
                && WS_AC_HASH <= 74250) {
            WS_PART_NO = 95;
        } else if (WS_AC_HASH >= 74251 
                && WS_AC_HASH <= 75030) {
            WS_PART_NO = 96;
        } else if (WS_AC_HASH >= 75031 
                && WS_AC_HASH <= 75810) {
            WS_PART_NO = 97;
        } else if (WS_AC_HASH >= 75811 
                && WS_AC_HASH <= 76590) {
            WS_PART_NO = 98;
        } else if (WS_AC_HASH >= 76591 
                && WS_AC_HASH <= 77370) {
            WS_PART_NO = 99;
        } else if (WS_AC_HASH >= 77371 
                && WS_AC_HASH <= 78150) {
            WS_PART_NO = 100;
        } else if (WS_AC_HASH >= 78151 
                && WS_AC_HASH <= 78930) {
            WS_PART_NO = 101;
        } else if (WS_AC_HASH >= 78931 
                && WS_AC_HASH <= 79710) {
            WS_PART_NO = 102;
        } else if (WS_AC_HASH >= 79711 
                && WS_AC_HASH <= 80490) {
            WS_PART_NO = 103;
        } else if (WS_AC_HASH >= 80491 
                && WS_AC_HASH <= 81270) {
            WS_PART_NO = 104;
        } else if (WS_AC_HASH >= 81271 
                && WS_AC_HASH <= 82050) {
            WS_PART_NO = 105;
        } else if (WS_AC_HASH >= 82051 
                && WS_AC_HASH <= 82830) {
            WS_PART_NO = 106;
        } else if (WS_AC_HASH >= 82831 
                && WS_AC_HASH <= 83610) {
            WS_PART_NO = 107;
        } else if (WS_AC_HASH >= 83611 
                && WS_AC_HASH <= 84390) {
            WS_PART_NO = 108;
        } else if (WS_AC_HASH >= 84391 
                && WS_AC_HASH <= 85170) {
            WS_PART_NO = 109;
        } else if (WS_AC_HASH >= 85171 
                && WS_AC_HASH <= 85950) {
            WS_PART_NO = 110;
        } else if (WS_AC_HASH >= 85951 
                && WS_AC_HASH <= 86730) {
            WS_PART_NO = 111;
        } else if (WS_AC_HASH >= 86731 
                && WS_AC_HASH <= 87510) {
            WS_PART_NO = 112;
        } else if (WS_AC_HASH >= 87511 
                && WS_AC_HASH <= 88290) {
            WS_PART_NO = 113;
        } else if (WS_AC_HASH >= 88291 
                && WS_AC_HASH <= 89070) {
            WS_PART_NO = 114;
        } else if (WS_AC_HASH >= 89071 
                && WS_AC_HASH <= 89850) {
            WS_PART_NO = 115;
        } else if (WS_AC_HASH >= 89851 
                && WS_AC_HASH <= 90630) {
            WS_PART_NO = 116;
        } else if (WS_AC_HASH >= 90631 
                && WS_AC_HASH <= 91410) {
            WS_PART_NO = 117;
        } else if (WS_AC_HASH >= 91411 
                && WS_AC_HASH <= 92190) {
            WS_PART_NO = 118;
        } else if (WS_AC_HASH >= 92191 
                && WS_AC_HASH <= 92970) {
            WS_PART_NO = 119;
        } else if (WS_AC_HASH >= 92971 
                && WS_AC_HASH <= 93750) {
            WS_PART_NO = 120;
        } else if (WS_AC_HASH >= 93751 
                && WS_AC_HASH <= 94530) {
            WS_PART_NO = 121;
        } else if (WS_AC_HASH >= 94531 
                && WS_AC_HASH <= 95310) {
            WS_PART_NO = 122;
        } else if (WS_AC_HASH >= 95311 
                && WS_AC_HASH <= 96090) {
            WS_PART_NO = 123;
        } else if (WS_AC_HASH >= 96091 
                && WS_AC_HASH <= 96870) {
            WS_PART_NO = 124;
        } else if (WS_AC_HASH >= 96871 
                && WS_AC_HASH <= 97650) {
            WS_PART_NO = 125;
        } else if (WS_AC_HASH >= 97651 
                && WS_AC_HASH <= 98430) {
            WS_PART_NO = 126;
        } else if (WS_AC_HASH >= 98431 
                && WS_AC_HASH <= 99210) {
            WS_PART_NO = 127;
        } else if (WS_AC_HASH >= 99211 
                && WS_AC_HASH <= 99990) {
            WS_PART_NO = 128;
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            WS_PART_NO = WS_PART_NO;
        } else if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            WS_PART_NO = WS_PART_NO + 128;
        } else if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("IB")) {
            WS_PART_NO = 265;
        } else if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("EQ")) {
            WS_PART_NO = 275;
        } else if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("FX")) {
            WS_PART_NO = 270;
        } else if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("BP")) {
            WS_PART_NO = 260;
        } else if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("PN")) {
            WS_PART_NO = 280;
        } else if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("LN")) {
            WS_PART_NO = 285;
        } else if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("PL")) {
            WS_PART_NO = 290;
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INEFC_PRDT_CD);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
