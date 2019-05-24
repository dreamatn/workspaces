package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DD.*;
import com.hisun.DP.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZMACO {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTCMST_RD;
    DBParm DDTCCY_RD;
    DBParm TDTBVT_RD;
    boolean pgmRtn = false;
    String K_AP_MMO = "TD";
    String K_APP_MMO = "GD";
    String K_PRDP_TYP = "PRDPR";
    String K_OPEN_ADV_FMT = "TD510";
    String K_STS_TBL = "0002";
    String K_STS_TBL_P = "0012";
    String K_HIS_REMARKS = "OPEN TD VA ACCOUNT";
    int K_HEAD_BR = 043999;
    String WS_ERR_MSG = " ";
    char WS_CMST_REC_FLG = ' ';
    short WS_X = 0;
    char WS_GD_AC_FLG = ' ';
    int WS_OWNER_BR = 0;
    TDZMACO_CP_PROD_CD CP_PROD_CD = new TDZMACO_CP_PROD_CD();
    char WS_CI_TYP = ' ';
    char WS_SID_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCHMPW SCCHMPW = new SCCHMPW();
    SCCFMT SCCFMT = new SCCFMT();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPLDPD BPCPLDPD = new BPCPLDPD();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    BPCCGAC BPCCGAC = new BPCCGAC();
    CICSACR CICSACR = new CICSACR();
    CICCUST CICCUST = new CICCUST();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCAMMDP BPCAMMDP = new BPCAMMDP();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    TDCACM TDCACM = new TDCACM();
    TDRBVT TDRBVT = new TDRBVT();
    TDRCMST TDRCMST = new TDRCMST();
    TDCMPRMA TDCMPRMA = new TDCMPRMA();
    TDCOOADV TDCOOADV = new TDCOOADV();
    BPCPQORG BPCPQORG = new BPCPQORG();
    TDCPPRTF TDCPPRTF = new TDCPPRTF();
    DDRCCY DDRCCY = new DDRCCY();
    DPCPARMP DPCPARMP = new DPCPARMP();
    TDCBVCD TDCBVCD = new TDCBVCD();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    DCCIMSTO DCCIMSTO = new DCCIMSTO();
    DCCUSSTS DCCUSSTS = new DCCUSSTS();
    CICCKID CICCKID = new CICCKID();
    CICOCUI CICOCUI = new CICOCUI();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    CICSACRL CICSACRL = new CICSACRL();
    CICCKLS CICCKLS = new CICCKLS();
    TDCMPRD TDCMPRD = new TDCMPRD();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    TDCMACO TDCMACO;
    public void MP(SCCGWA SCCGWA, TDCMACO TDCMACO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCMACO = TDCMACO;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZMACO return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
        CEP.TRC(SCCGWA, TDCMACO.CI_NO);
        CEP.TRC(SCCGWA, TDCMACO.ID_TYPE);
        CEP.TRC(SCCGWA, TDCMACO.ID_NO);
        if (SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("CNTA")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ERR_SYSTEM);
        }
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        if (TDCMACO.BV_TYPE == 'G') {
            WS_GD_AC_FLG = 'Y';
            TDCMACO.BV_TYPE = '0';
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B130_CHK_CI_PROC();
        if (pgmRtn) return;
        B100_CHK_INPUT_PROC();
        if (pgmRtn) return;
        B015_CHECK_CI_LIST();
        if (pgmRtn) return;
        B200_MCA_OPEN_PROC();
        if (pgmRtn) return;
        B300_FMT_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCMACO.PROD_CD);
        CEP.TRC(SCCGWA, TDCMACO.ID_TYPE);
        CEP.TRC(SCCGWA, TDCMACO.ID_NO);
        CEP.TRC(SCCGWA, TDCMACO.CI_NO);
        CEP.TRC(SCCGWA, TDCMACO.AC_NAME);
        CEP.TRC(SCCGWA, TDCMACO.BV_CD);
        CEP.TRC(SCCGWA, TDCMACO.BV_TYPE);
        CEP.TRC(SCCGWA, TDCMACO.BV_NO);
        CEP.TRC(SCCGWA, TDCMACO.DRAW_MTH);
        CEP.TRC(SCCGWA, TDCMACO.DRAW_PSW);
        CEP.TRC(SCCGWA, TDCMACO.D_ID_TYP);
        CEP.TRC(SCCGWA, TDCMACO.D_ID_NO);
        CEP.TRC(SCCGWA, TDCMACO.DRAW_INF);
        if (TDCMACO.AC_NAME.trim().length() == 0 
            && CICCUST.O_DATA.O_CI_TYP != '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NAME_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != 199999) {
            IBS.init(SCCGWA, BPCPQORG);
            if (TDCMACO.BR != 0) {
                BPCPQORG.BR = TDCMACO.BR;
            } else {
                BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            }
            CEP.TRC(SCCGWA, BPCPQORG.BR);
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
        if ((BPCPQORG.ATTR != '2' 
            && BPCPQORG.ATTR != '3')) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BR_NOT_PQORG_ATTR);
        }
    }
    public void B130_CHK_CI_PROC() throws IOException,SQLException,Exception {
        B130_INQ_CI_INF_PROC();
        if (pgmRtn) return;
        if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
        JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
        if (CICCUST.O_DATA.O_STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ERR_CUS_ID_EXP);
        }
        if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
        JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
        if (CICCUST.O_DATA.O_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ERR_CUS_ID_COLSED);
        }
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_AP_MMO;
        if (CICCUST.O_DATA.O_CI_TYP == '1') {
            BPCFCSTS.TBL_NO = K_STS_TBL_P;
        } else {
            BPCFCSTS.TBL_NO = K_STS_TBL;
        }
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 01 - 1) + "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000" + BPCFCSTS.STATUS_WORD.substring(01 + 250 - 1);
        if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
        JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 01 - 1) + CICCUST.O_DATA.O_STSW.substring(0, 80) + BPCFCSTS.STATUS_WORD.substring(01 + 80 - 1);
        S000_CALL_BPZFCSTS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
    }
    public void B200_MCA_OPEN_PROC() throws IOException,SQLException,Exception {
        B200_01_GET_PRD_INF_PROC();
        if (pgmRtn) return;
        B200_05_CHK_PRD_CODE_PROC();
        if (pgmRtn) return;
        B200_07_GET_PRD_INF_KH();
        if (pgmRtn) return;
        B100_CHK_MPROD_INF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCMACO.BV_TYPE);
        if (TDCMACO.BV_TYPE == '1') {
            B200_09_BV_MANAGE_PROC();
            if (pgmRtn) return;
        }
        B200_11_GEN_TD_AC_PROC();
        if (pgmRtn) return;
        B200_13_WRI_TDTCMST_PROC();
        if (pgmRtn) return;
        if (TDCMACO.DRAW_MTH == '1' 
            || TDCMACO.DRAW_MTH == '4') {
            if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                B2A2_GEN_PSW();
                if (pgmRtn) return;
            }
        }
        B200_15_CRE_AC_CI_REL_PROC();
        if (pgmRtn) return;
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("044") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("043") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("045")) {
            B200_17_CRE_ACRL_PROC();
            if (pgmRtn) return;
        }
        if (TDCMACO.BV_TYPE == '1') {
            B200_21_WRI_TDTBVT_PROC();
            if (pgmRtn) return;
        }
        B200_19_WRI_BPTOCAC_PROC();
        if (pgmRtn) return;
        B200_17_WRI_NFIN_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B130_INQ_CI_INF_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCMACO.CI_NO);
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = TDCMACO.CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
        WS_SID_FLG = CICCUST.O_DATA.O_SID_FLG;
        WS_CI_TYP = CICCUST.O_DATA.O_CI_TYP;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_NO);
        CEP.TRC(SCCGWA, TDCMACO.ID_NO);
        if (!CICCUST.O_DATA.O_ID_NO.equalsIgnoreCase(TDCMACO.ID_NO)) {
            IBS.init(SCCGWA, CICCKID);
            CICCKID.DATA.CI_NO = TDCMACO.CI_NO;
            CICCKID.DATA.ID_TYPE = TDCMACO.ID_TYPE;
            CICCKID.DATA.ID_NO = TDCMACO.ID_NO;
            CICCKID.FUNC = 'C';
        }
    }
    public void B015_CHECK_CI_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCKLS);
        CICCKLS.DATA.AP_TYPE = "001";
        CICCKLS.DATA.CI_NO = TDCMACO.CI_NO;
        CEP.TRC(SCCGWA, CICCKLS.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICCKLS.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICCKLS.DATA.AP_TYPE);
        CICCKLS.DATA.EXP_MMO = "P114";
        S000_CALL_CIZCKLS();
        if (pgmRtn) return;
    }
    public void B100_CHK_MPROD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCMPRMA);
        IBS.init(SCCGWA, TDCMPRD);
        TDCMPRD.FUNC = 'I';
        TDCMPRD.PROD_CD = BPCPQPRD.PARM_CODE;
        S000_CALL_TDZMPRD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, TDCMPRD, TDCMPRD);
        CEP.TRC(SCCGWA, TDCMPRMA.PROD_CD_M);
        CEP.TRC(SCCGWA, TDCMPRMA.PROD_CD);
        CEP.TRC(SCCGWA, TDCMPRMA.SUM.SDT);
        CEP.TRC(SCCGWA, TDCMPRMA.SUM.DDT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, TDCMACO.CROS_CR);
        CEP.TRC(SCCGWA, TDCMPRMA.SUM.CROS_CR_LMT);
        if (TDCMACO.CROS_CR == ' ') {
            TDCMACO.CROS_CR = TDCMPRMA.SUM.CROS_CR_LMT;
        }
        if (TDCMACO.CROS_CR != '0' 
            && TDCMACO.CROS_CR != '1' 
            && TDCMACO.CROS_CR != '2') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CROS_FLG_OVERF);
        } else {
            if (TDCMPRMA.SUM.CROS_CR_LMT == '0' 
                && (TDCMACO.CROS_CR != '0' 
                && TDCMACO.CROS_CR != '2')) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CROS_CR_N_MATCH_PRD);
            }
            if (TDCMPRMA.SUM.CROS_CR_LMT == '2' 
                && TDCMACO.CROS_CR != '2') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CROS_CR_N_MATCH_PRD);
            }
        }
        CEP.TRC(SCCGWA, TDCMACO.CROS_DR);
        CEP.TRC(SCCGWA, TDCMPRMA.SUM.CROS_DR_LMT);
        if (TDCMACO.CROS_DR == ' ') {
            TDCMACO.CROS_CR = TDCMPRMA.SUM.CROS_DR_LMT;
        }
        if (TDCMACO.CROS_DR != '0' 
            && TDCMACO.CROS_DR != '1' 
            && TDCMACO.CROS_DR != '2') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CROS_FLG_OVERF);
        } else {
            if (TDCMPRMA.SUM.CROS_DR_LMT == '0' 
                && (TDCMACO.CROS_DR != '0' 
                && TDCMACO.CROS_DR != '2')) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CROS_DR_N_MATCH_PRD);
            }
            if (TDCMPRMA.SUM.CROS_DR_LMT == '2' 
                && TDCMACO.CROS_DR != '2') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CROS_DR_N_MATCH_PRD);
            }
        }
        CEP.TRC(SCCGWA, TDCMPRMA.SUM.CCY);
        CEP.TRC(SCCGWA, TDCMACO.CCY);
    }
    public void B200_01_GET_PRD_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDCMACO.PROD_CD;
        CEP.TRC(SCCGWA, "LUO1");
        CEP.TRC(SCCGWA, BPCPQPRD.PRDT_CODE);
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DPCPARMP);
        DPCPARMP.AC_TYPE = BPCPQPRD.AC_TYPE;
        CEP.TRC(SCCGWA, "LUO");
        CEP.TRC(SCCGWA, BPCPQPRD.STOP_SOLD_DATE);
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        CEP.TRC(SCCGWA, DPCPARMP.AC_TYPE);
    }
    public void B200_05_CHK_PRD_CODE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPLDPD);
        BPCPLDPD.KIND = '1';
        BPCPLDPD.PRDT_CODE = TDCMACO.PROD_CD;
        CEP.TRC(SCCGWA, BPCPLDPD);
        S000_CALL_BPZPLDPD();
        if (pgmRtn) return;
    }
    public void B200_07_GET_PRD_INF_KH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDCMACO.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.CUS_TYPE);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        CEP.TRC(SCCGWA, BPCPQPRD.CUS_PER_FLG);
        CEP.TRC(SCCGWA, BPCPQPRD.CUS_COM_FLG);
        CEP.TRC(SCCGWA, BPCPQPRD.CUS_FIN_FLG);
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        if (CICCUST.O_DATA.O_CI_TYP == '1' 
            && BPCPQPRD.CUS_PER_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CUST_NOT_MATCH);
        }
        if (CICCUST.O_DATA.O_CI_TYP == '2' 
            && BPCPQPRD.CUS_COM_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CUST_NOT_MATCH);
        }
        if (CICCUST.O_DATA.O_CI_TYP == '3' 
            && BPCPQPRD.CUS_FIN_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CUST_NOT_MATCH);
        }
    }
    public void B200_09_BV_MANAGE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUBUSE);
        BPCUBUSE.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBUSE.TYPE = '0';
        CEP.TRC(SCCGWA, TDCMACO.BV_CD);
        CEP.TRC(SCCGWA, TDCMACO.BV_NO);
        BPCUBUSE.BV_CODE = TDCMACO.BV_CD;
        BPCUBUSE.BEG_NO = TDCMACO.BV_NO;
        BPCUBUSE.END_NO = TDCMACO.BV_NO;
        BPCUBUSE.NUM = 1;
        S000_CALL_BPZUBUSE();
        if (pgmRtn) return;
    }
    public void B200_11_GEN_TD_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCGAC);
        BPCCGAC.DATA.AC_KIND = '1';
        if (CICCUST.O_DATA.O_CI_TYP == '1') {
            BPCCGAC.DATA.CI_AC_FLG = '6';
        }
        if (CICCUST.O_DATA.O_CI_TYP == '2' 
            || CICCUST.O_DATA.O_CI_TYP == '3') {
            BPCCGAC.DATA.CI_AC_FLG = '5';
        }
        CEP.TRC(SCCGWA, BPCCGAC.DATA.CI_AC_TYPE);
        BPCCGAC.DATA.CI_AC_TYPE = '2';
        S000_CALL_BPZGACNO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCCGAC.DATA.CI_AC);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
        CEP.TRC(SCCGWA, WS_CI_TYP);
        CEP.TRC(SCCGWA, WS_CI_TYP);
        if (WS_SID_FLG == '2') {
            TDCMACO.FRG_IND = "NRA";
        }
        if (!BPCPQORG.TRA_TYP.equalsIgnoreCase("00")) {
            if (WS_CI_TYP == '1') {
                if (CICCUST.O_DATA.O_SID_FLG == '1') {
                    TDCMACO.FRG_IND = "FTI";
                }
                if (CICCUST.O_DATA.O_SID_FLG == '2') {
                    TDCMACO.FRG_IND = "FTF";
                }
            }
            if (WS_CI_TYP == '2') {
                if (CICCUST.O_DATA.O_SID_FLG == '1') {
                    TDCMACO.FRG_IND = "FTE";
                }
                if (CICCUST.O_DATA.O_SID_FLG == '2') {
                    TDCMACO.FRG_IND = "FTN";
                }
            }
            if (WS_CI_TYP == '3') {
                TDCMACO.FRG_IND = "FTU";
            }
        }
        TDCMACO.MAIN_AC = BPCCGAC.DATA.CI_AC;
        CEP.TRC(SCCGWA, TDCMACO.MAIN_AC);
    }
    public void B200_13_WRI_TDTCMST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCMACO.MAIN_AC;
        T00_READ_TDTCMST_R();
        if (pgmRtn) return;
        if (WS_CMST_REC_FLG == 'N') {
            TDRCMST.KEY.AC_NO = TDCMACO.MAIN_AC;
            TDRCMST.STS = '0';
            TDRCMST.BV_TYP = TDCMACO.BV_TYPE;
            TDRCMST.CCY = TDCMACO.CCY;
            TDRCMST.CI_TYP = CICCUST.O_DATA.O_CI_TYP;
            TDRCMST.AC_PSW_FLG = 'N';
            TDRCMST.DRAW_MTH = TDCMACO.DRAW_MTH;
            TDRCMST.STSW = "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
            TDRCMST.PROD_CD = TDCMACO.PROD_CD;
            TDRCMST.CROS_DR_FLG = TDCMACO.CROS_DR;
            TDRCMST.CROS_CR_FLG = TDCMACO.CROS_CR;
            if (TDCMACO.BR == 0) {
                TDRCMST.OWNER_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            } else {
                TDRCMST.OWNER_BR = TDCMACO.BR;
            }
            TDRCMST.OWNER_BRDP = TDCMACO.BR;
            TDRCMST.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            TDRCMST.CHE_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            TDRCMST.REF_TYP = TDCMACO.BUS_KNB;
            TDRCMST.INT_METH = '2';
            TDRCMST.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRCMST.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
            TDRCMST.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            TDRCMST.FRG_IND = TDCMACO.FRG_IND;
            TDRCMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDRCMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRCMST.OWNER_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            TDRCMST.OWNER_BRDP = BPCPORUP.DATA_INFO.BBR;
            TDRCMST.OWNER_BK = BPCPORUP.DATA_INFO.BR;
            CEP.TRC(SCCGWA, TDCMACO.ACBR_FLG);
            CEP.TRC(SCCGWA, TDRCMST.CI_TYP);
            if (TDCMACO.ACBR_FLG == '1' 
                && TDRCMST.CI_TYP == '3') {
                TDRCMST.CHE_BR = K_HEAD_BR;
            } else {
                TDRCMST.CHE_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            }
            TDRCMST.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
            T000_WRITE_TDTCMST();
            if (pgmRtn) return;
            WS_OWNER_BR = TDRCMST.OWNER_BR;
            CEP.TRC(SCCGWA, TDRCMST.KEY.AC_NO);
        }
    }
    public void B200_15_CRE_AC_CI_REL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'A';
        CICSACR.DATA.CI_NO = TDCMACO.CI_NO;
        CICSACR.DATA.ENTY_TYP = '3';
        CICSACR.DATA.AGR_NO = TDCMACO.MAIN_AC;
        CICSACR.DATA.STSW = "00000000";
        if (CICCUST.O_DATA.O_CI_TYP == '1') {
            if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("041")) {
                if (CICSACR.DATA.STSW == null) CICSACR.DATA.STSW = "";
                JIBS_tmp_int = CICSACR.DATA.STSW.length();
                for (int i=0;i<8-JIBS_tmp_int;i++) CICSACR.DATA.STSW += " ";
                CICSACR.DATA.STSW = CICSACR.DATA.STSW.substring(0, 2 - 1) + "1" + CICSACR.DATA.STSW.substring(2 + 1 - 1);
            } else {
                if (CICSACR.DATA.STSW == null) CICSACR.DATA.STSW = "";
                JIBS_tmp_int = CICSACR.DATA.STSW.length();
                for (int i=0;i<8-JIBS_tmp_int;i++) CICSACR.DATA.STSW += " ";
                CICSACR.DATA.STSW = "1" + CICSACR.DATA.STSW.substring(1);
                if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("044") 
                    || BPCPQPRD.AC_TYPE.equalsIgnoreCase("043")) {
                    if (CICSACR.DATA.STSW == null) CICSACR.DATA.STSW = "";
                    JIBS_tmp_int = CICSACR.DATA.STSW.length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) CICSACR.DATA.STSW += " ";
                    CICSACR.DATA.STSW = CICSACR.DATA.STSW.substring(0, 2 - 1) + "1" + CICSACR.DATA.STSW.substring(2 + 1 - 1);
                }
            }
        } else {
            if (CICSACR.DATA.STSW == null) CICSACR.DATA.STSW = "";
            JIBS_tmp_int = CICSACR.DATA.STSW.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) CICSACR.DATA.STSW += " ";
            CICSACR.DATA.STSW = CICSACR.DATA.STSW.substring(0, 2 - 1) + "11" + CICSACR.DATA.STSW.substring(2 + 2 - 1);
        }
        if (WS_GD_AC_FLG == 'Y') {
            CICSACR.CTL_FLG.EXP_FLG = 'Y';
            CICSACR.CTL_STSW = IBS.CLS2CPY(SCCGWA, CICSACR.CTL_FLG);
        }
        CICSACR.DATA.PROD_CD = TDCMACO.PROD_CD;
        CICSACR.DATA.CNTRCT_TYP = BPCPQPRD.AC_TYPE;
        CICSACR.DATA.FRM_APP = K_AP_MMO;
        CICSACR.DATA.CCY = TDCMACO.CCY;
        CICSACR.DATA.SHOW_FLG = 'Y';
        CICSACR.DATA.AC_CNM = TDCMACO.AC_NAME;
        CICSACR.DATA.OPN_BR = WS_OWNER_BR;
        CICSACR.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, TDCMACO.AC_NAME);
        CEP.TRC(SCCGWA, CICSACR.DATA.AC_CNM);
        S000_CALL_CIZSACR();
        if (pgmRtn) return;
    }
    public void B200_17_CRE_ACRL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACRL);
        CICSACRL.FUNC = 'A';
        CICSACRL.DATA.AC_NO = TDCMACO.MAIN_AC;
        CICSACRL.DATA.REL_AC_NO = TDCMACO.OPP_AC;
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("043")) {
            CICSACRL.DATA.AC_REL = "05";
        }
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("044")) {
            CICSACRL.DATA.AC_REL = "06";
        }
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("045")) {
            CICSACRL.DATA.AC_REL = "11";
        }
        S000_CALL_CIZSACRL();
        if (pgmRtn) return;
    }
    public void B200_17_WRI_NFIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.AC = TDCMACO.MAIN_AC;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = "TDCMACO";
        BPCPNHIS.INFO.CI_NO = TDCMACO.CI_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.TX_CD = "0125100";
        BPCPNHIS.INFO.TX_TYP_CD = "P114";
        BPCPNHIS.INFO.FMT_ID_LEN = 650;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.NEW_DAT_PT = TDCMACO;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B200_19_WRI_BPTOCAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'C';
        BPCSOCAC.AC = TDRCMST.KEY.AC_NO;
        BPCSOCAC.STS = 'N';
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("042")) {
            BPCSOCAC.WORK_TYP = "27";
        } else if (DPCPARMP.AC_TYPE.equalsIgnoreCase("043")) {
            BPCSOCAC.WORK_TYP = "12";
        } else if (DPCPARMP.AC_TYPE.equalsIgnoreCase("044")) {
            BPCSOCAC.WORK_TYP = "13";
        } else if (DPCPARMP.AC_TYPE.equalsIgnoreCase("045")) {
            BPCSOCAC.WORK_TYP = "20";
        } else if (DPCPARMP.AC_TYPE.equalsIgnoreCase("046")) {
            BPCSOCAC.WORK_TYP = "31";
        } else if (DPCPARMP.AC_TYPE.equalsIgnoreCase("041")) {
            BPCSOCAC.WORK_TYP = "26";
        } else if (DPCPARMP.AC_TYPE.equalsIgnoreCase("047")) {
            BPCSOCAC.WORK_TYP = "28";
        } else if (DPCPARMP.AC_TYPE.equalsIgnoreCase("048")) {
            BPCSOCAC.WORK_TYP = "29";
        }
        BPCSOCAC.CI_TYPE = CICCUST.O_DATA.O_CI_TYP;
        if (TDCMACO.BV_TYPE == '7'
            || TDCMACO.BV_TYPE == '8') {
            BPCSOCAC.BV_TYP = '8';
        } else if (TDCMACO.BV_TYPE == '1') {
            BPCSOCAC.BV_TYP = '6';
        } else if (TDCMACO.BV_TYPE == '2') {
            BPCSOCAC.BV_TYP = '4';
        } else if (TDCMACO.BV_TYPE == '3') {
            BPCSOCAC.BV_TYP = '5';
        }
        BPCSOCAC.BV_NO = TDCMACO.BV_NO;
        BPCSOCAC.ID_TYP = TDCMACO.ID_TYPE;
        BPCSOCAC.ID_NO = TDCMACO.ID_NO;
        BPCSOCAC.CI_CNM = TDCMACO.AC_NAME;
        BPCSOCAC.AC_CNM = TDCMACO.AC_NAME;
        BPCSOCAC.CI_CNM = CICCUST.O_DATA.O_CI_NM;
        if (CICCUST.O_DATA.O_CI_TYP == '3') {
            BPCSOCAC.CCY = TDCMACO.CCY;
        }
        BPCSOCAC.AUT_TLR = " ";
        BPCSOCAC.OPEN_DATE = TDRCMST.OPEN_DATE;
        BPCSOCAC.OPEN_TLR = TDRCMST.OPEN_TLR;
        BPCSOCAC.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPCSOCAC.PROD_CD = TDRCMST.PROD_CD;
        BPCSOCAC.BR = TDRCMST.OWNER_BR;
        S000_CALL_BPZSOCAC();
        if (pgmRtn) return;
    }
    public void B200_19_WRI_DCZIMSTO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCIMSTO);
        DCCIMSTO.INP_DATA.CI_NO = TDCMACO.CI_NO;
        DCCIMSTO.INP_DATA.VIA_FLG = '1';
        if (TDCMACO.PROD_CD.equalsIgnoreCase("NBL00117") 
            || TDCMACO.PROD_CD.equalsIgnoreCase("NBL00118")) {
            if (TDCMACO.PROD_CD.equalsIgnoreCase("NBL00117")) {
                DCCIMSTO.INP_DATA.PRD_TYPE = "04";
            }
            if (TDCMACO.PROD_CD.equalsIgnoreCase("NBL00118")) {
                DCCIMSTO.INP_DATA.PRD_TYPE = "03";
            }
        } else {
            if (CICCUST.O_DATA.O_CI_TYP == '1') {
                DCCIMSTO.INP_DATA.PRD_TYPE = "03";
            } else {
                DCCIMSTO.INP_DATA.PRD_TYPE = "04";
            }
        }
        DCCIMSTO.INP_DATA.PRD_CODE = TDCMACO.PROD_CD;
        DCCIMSTO.INP_DATA.APP = "TD";
        CEP.TRC(SCCGWA, TDCMACO.BR);
        if (TDCMACO.BR != 0) {
            DCCIMSTO.INP_DATA.BR = TDCMACO.BR;
        }
        DCCIMSTO.INP_DATA.CI_SHOW_FLG = 'Y';
        DCCIMSTO.INP_DATA.AC_CNM = TDCMACO.AC_NAME;
        CEP.TRC(SCCGWA, DCCIMSTO.INP_DATA.AC_CNM);
        S000_CALL_DCZIMSTO();
        if (pgmRtn) return;
        TDCMACO.MAIN_AC = DCCIMSTO.OUT_DATA.VIA_AC;
        CEP.TRC(SCCGWA, DCCIMSTO.OUT_DATA.VIA_AC);
        CEP.TRC(SCCGWA, TDCMACO.MAIN_AC);
    }
    public void B200_21_WRI_TDTBVT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRBVT);
        TDRBVT.KEY.AC_NO = TDCMACO.MAIN_AC;
        TDRBVT.BV_NO = TDCMACO.BV_NO;
        if (SCCGWA.COMM_AREA.AP_MMO.equalsIgnoreCase(K_APP_MMO)) {
            IBS.init(SCCGWA, BPCPRMM);
            IBS.init(SCCGWA, TDCBVCD);
            BPCPRMM.FUNC = '3';
            BPRPRMT.KEY.TYP = K_PRDP_TYP;
            BPRPRMT.KEY.CD = "0";
            BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCPRMM.DAT_PTR = BPRPRMT;
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCBVCD);
            TDRBVT.BV_CD = TDCBVCD.CD;
        } else {
            TDRBVT.BV_CD = TDCMACO.BV_CD;
        }
        TDRBVT.STSW = "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        TDRBVT.ISSU_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, TDCMACO.BR);
        if (TDCMACO.BR != 0) {
            CEP.TRC(SCCGWA, "F-BUG11");
            TDRBVT.ISSU_BR = TDCMACO.BR;
        } else {
            TDRBVT.ISSU_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        TDRBVT.ISSU_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRBVT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_WRITE_TDTBVT();
        if (pgmRtn) return;
    }
    public void B2A2_GEN_PSW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCACM);
        TDCACM.FUNC = 'R';
        TDCACM.AC_NO = TDCMACO.MAIN_AC;
        TDCACM.OLD_AC_NO = "123456789012";
        TDCACM.CARD_PSW_OLD = TDCMACO.DRAW_PSW;
        TDCACM.CARD_PSW_NEW = TDCMACO.DRAW_PSW;
        TDCACM.ID_TYP = TDCMACO.ID_TYPE;
        TDCACM.ID_NO = TDCMACO.ID_NO;
        if (CICCUST.O_DATA.O_CI_TYP == '1') {
            TDCACM.CI_NM = TDCMACO.AC_NAME;
        } else {
            TDCACM.CI_NM = CICCUST.O_DATA.O_CI_NM;
        }
        CEP.TRC(SCCGWA, TDCACM.CI_NM);
        S000_CALL_TDZACM();
        if (pgmRtn) return;
    }
    public void B210_INQ_GL_MASTER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCNGL);
        IBS.init(SCCGWA, BPCAMMDP);
        BPCQCNGL.DAT.INPUT.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        BPCQCNGL.DAT.INPUT.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 21;
        BPCAMMDP.PROD_TYPE = TDCMACO.PROD_CD;
        BPCQCNGL.DAT.INPUT.OTH_PTR = BPCAMMDP;
        S000_CALL_BPZQCNGL();
        if (pgmRtn) return;
    }
    public void B300_FMT_OUTPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCMACO.BV_NO);
        if (TDCMACO.BV_NO.trim().length() > 0 
            && TDCMACO.BV_TYPE == '1') {
            B320_OUTPUT_COV_MAG();
            if (pgmRtn) return;
        }
        B310_OUTPUT_OPEN_ADV();
        if (pgmRtn) return;
    }
    public void B310_OUTPUT_OPEN_ADV() throws IOException,SQLException,Exception {
        R000_GET_BK_NAME();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDCOOADV);
        TDCOOADV.AC_NO = TDCMACO.MAIN_AC;
        TDCOOADV.AC_NM = TDCMACO.AC_NAME;
        TDCOOADV.OP_BK_NM = BPCPQORG.CHN_NM;
        TDCOOADV.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDCOOADV.TR_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, TDCOOADV.OP_BK_NM);
        CEP.TRC(SCCGWA, TDCOOADV.OPEN_DATE);
        CEP.TRC(SCCGWA, TDCOOADV.TR_TLR);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OPEN_ADV_FMT;
        SCCFMT.DATA_PTR = TDCOOADV;
        SCCFMT.DATA_LEN = 0;
        IBS.FMT(SCCGWA, SCCFMT);
        TDCMACO.MAIN_AC = TDCOOADV.AC_NO;
    }
    public void B320_OUTPUT_COV_MAG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCPPRTF);
        TDCPPRTF.OPT = '2';
        TDCPPRTF.AC = TDCMACO.MAIN_AC;
        TDCPPRTF.PRDAC_CD = BPCPQPRD.AC_TYPE;
        TDCPPRTF.BV_CD = TDCMACO.BV_CD;
        TDCPPRTF.BV_TYP = TDCMACO.BV_TYPE;
        TDCPPRTF.BV_NO = TDCMACO.BV_NO;
        TDCPPRTF.AC_NAME = TDCMACO.AC_NAME;
        TDCPPRTF.RMK = "MAIN AC OPEN";
        S000_CALL_TDZPPRTF();
        if (pgmRtn) return;
    }
    public void B720_WRT_DCTSSTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCCUSSTS);
        DCCUSSTS.OPT = 'A';
        DCCUSSTS.DATA.KEY.AC = TDCMACO.MAIN_AC;
        DCCUSSTS.DATA.KEY.CCY = " ";
        DCCUSSTS.DATA.KEY.CCY_TYPE = ' ';
        DCCUSSTS.DATA.STS = '1';
        DCCUSSTS.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCCUSSTS.DATA.OPEN_DP = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCCUSSTS.DATA.OPEN_BR = BPCPQORG.BRANCH_BR;
        DCCUSSTS.DATA.FRM_APP = "TD";
        DCCUSSTS.DATA.CROS_DR_FLG = ' ';
        S000_CALL_DCZUSSTS();
        if (pgmRtn) return;
    }
    public void R000_GET_BK_NAME() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZCKID() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-ID-CHECK", CICCKID);
        if (CICCKID.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCKID.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_TDZACM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-PSW-CHECK", TDCACM);
    }
    public void S000_CALL_CIZCKLS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CHECK-LST", CICCKLS);
        if (CICCKLS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCKLS.RC);
        }
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
        if (BPCSOCAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCSOCAC.RC.RC_CODE);
        }
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DCZUSSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-CPN-M-SSTS", DCCUSSTS);
        if (DCCUSSTS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUSSTS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPLDPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-LOAD-PROD", BPCPLDPD);
        if (BPCPLDPD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPLDPD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZUBUSE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-TLR-BV-USE", BPCUBUSE);
    }
    public void S000_CALL_BPZGACNO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-GENERTE-ACNO", BPCCGAC);
        CEP.TRC(SCCGWA, BPCCGAC.RC);
        if (BPCCGAC.RC.RC_CODE != 0) {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZQCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CNGL", BPCQCNGL);
        CEP.TRC(SCCGWA, BPCQCNGL.RC);
        if (BPCQCNGL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCNGL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZUCNGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MAINT-CNGM", BPCUCNGM);
        CEP.TRC(SCCGWA, BPCUCNGM.RC);
        if (BPCUCNGM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCNGM.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZSACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACRL", CICSACRL);
        CEP.TRC(SCCGWA, CICSACRL.RC.RC_CODE);
        if (CICSACRL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACRL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_SCZHMPW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCHMPW);
        IBS.CALLCPN(SCCGWA, "SC-HM-PASSWORD", SCCHMPW);
        CEP.TRC(SCCGWA, SCCHMPW.OUT_INFO.ERR_CODE);
        if (!SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("SC0000")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PWD_ERROR);
        }
    }
    public void S000_CALL_TDZPPRTF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-PSBK-PRTF", TDCPPRTF);
        if (TDCPPRTF.RC_MSG.RC != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPPRTF.RC_MSG);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T00_READ_TDTCMST_R() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_REC_EXIST);
            Z_RET();
            if (pgmRtn) return;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CMST_REC_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE TDTCMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTCMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        DDTCCY_RD.fst = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_WRITE_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.WRITE(SCCGWA, TDRBVT, TDTBVT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_REC_EXIST);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTBVT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.WRITE(SCCGWA, TDRCMST, TDTCMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_REC_EXIST);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTCMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZIMSTO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-MST-OPN", DCCIMSTO);
        if (DCCIMSTO.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCIMSTO.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_TDZMPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-MPRD-MAINT", TDCMPRD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
