package com.hisun.DD;

import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.AI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSDACT {
    int JIBS_tmp_int;
    DBParm DDTCCY_RD;
    brParm DDTCCY_BR = new brParm();
    DBParm DDTCLDD_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD630";
    String K_HIS_REMARKS = "ACTIVE DORMANT ACCOUNT";
    String CPN_I_INQ_DDPRD = "DD-I-INQ-DDPRD";
    String K_DDEG1 = "DDEG1";
    String K_DDEG3 = "DDEG3";
    String K_DDEG2 = "DDEG2";
    String WS_ERR_MSG = " ";
    char WS_MST_AC_STS = ' ';
    double WK_DREG_BAL = 0;
    char WK_DREG_STS = ' ';
    char WS_DREG_RCD_STS = ' ';
    String WS_ITM_NO = " ";
    String WS_AC_AC = " ";
    String WS_AC_STS_WORD = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDRMST DDRMST = new DDRMST();
    DDCRMST DDCRMST = new DDCRMST();
    DDCIDREG DDCIDREG = new DDCIDREG();
    DDCODACT DDCODACT = new DDCODACT();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    SCCBINF SCCBINF = new SCCBINF();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDRCLDD DDRCLDD = new DDRCLDD();
    AICPQIA AICPQIA = new AICPQIA();
    AICUUPIA AICUUPIA = new AICUUPIA();
    DDRVCH DDRVCH = new DDRVCH();
    DDRCCY DDRCCY = new DDRCCY();
    AICPQITM AICPQITM = new AICPQITM();
    CICQACAC CICQACAC = new CICQACAC();
    AICPQMIB AICPQMIB = new AICPQMIB();
    BPCPRMR BPCPRMR = new BPCPRMR();
    AIRPAI7 AIRPAI7 = new AIRPAI7();
    AICPUITM AICPUITM = new AICPUITM();
    CICACCU CICACCU = new CICACCU();
    DDRFHIS DDRFHIS = new DDRFHIS();
    SCCGWA SCCGWA;
    DDCSDACT DDCSDACT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCSDACT DDCSDACT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSDACT = DDCSDACT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSDACT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B015_GET_ACAC_INFO();
        if (pgmRtn) return;
        B020_GET_AC_INF_PROC();
        if (pgmRtn) return;
        B025_GET_PRD_INF_PROC();
        if (pgmRtn) return;
        B030_ACT_DREG_PROC();
        if (pgmRtn) return;
        if (WS_MST_AC_STS == 'D' 
            && WK_DREG_BAL != 0) {
            B041_GEN_VCH_PROC();
            if (pgmRtn) return;
            B042_FIN_HIS_PROC();
            if (pgmRtn) return;
        }
        B070_UPD_AC_STS_PROC();
        if (pgmRtn) return;
        if (WS_AC_STS_WORD == null) WS_AC_STS_WORD = "";
        JIBS_tmp_int = WS_AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) WS_AC_STS_WORD += " ";
        if (CICACCU.DATA.CI_TYP != '1' 
            && WS_AC_STS_WORD.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
            B080_CHECK_CCY_STSW_PROC();
            if (pgmRtn) return;
        }
        B090_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSDACT.DATA.AC);
        CEP.TRC(SCCGWA, DDCSDACT.DATA.CCY);
        CEP.TRC(SCCGWA, DDCSDACT.DATA.CCY_TYPE);
        if (DDCSDACT.DATA.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B015_GET_ACAC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDCSDACT.DATA.AC;
        CICQACAC.DATA.CCY_ACAC = DDCSDACT.DATA.CCY;
        CICQACAC.DATA.CR_FLG = DDCSDACT.DATA.CCY_TYPE;
        CICQACAC.FUNC = 'R';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        WS_AC_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("TD")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MUST_DD_AC);
        }
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSDACT.DATA.AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_TYP);
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (CICACCU.DATA.CI_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_STS_STOP);
        }
    }
    public void B020_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            IBS.init(SCCGWA, DDCRMST);
            DDRMST.KEY.CUS_AC = DDCSDACT.DATA.AC;
            DDCRMST.FUNC = 'R';
            DDCRMST.REC_PTR = DDRMST;
            DDCRMST.REC_LEN = 425;
            S000_CALL_DDZRMST();
            if (pgmRtn) return;
            WS_MST_AC_STS = DDRMST.AC_STS;
            WS_AC_STS_WORD = DDRMST.AC_STS_WORD;
            if (DDRMST.OPEN_DP != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH 
                && CICACCU.DATA.CI_TYP != '1') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ERR_TXN_BRANCH);
            }
            if (DDRMST.CI_TYP == '2' 
                || DDRMST.CI_TYP == '3') {
                if (WS_AC_STS_WORD == null) WS_AC_STS_WORD = "";
                JIBS_tmp_int = WS_AC_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) WS_AC_STS_WORD += " ";
                if (WS_MST_AC_STS != 'D' 
                    && !WS_AC_STS_WORD.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NOT_DORM_SLEEP;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        B021_READUP_DDTCCY_PROC();
        if (pgmRtn) return;
        if (DDRCCY.STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_CLEARED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (!DDRCCY.STS_WORD.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            && CICACCU.DATA.CI_TYP == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NOT_DODREG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B021_READUP_DDTCCY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = WS_AC_AC;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B025_GET_PRD_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        DDCIQPRD.INPUT_DATA.CCY = DDCSDACT.DATA.CCY;
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        CEP.TRC(SCCGWA, DDCIQPRD.OUTPUT_DATA.PRDT_MODEL);
        CEP.TRC(SCCGWA, DDCIQPRD.OUTPUT_DATA.AC_TYPE);
    }
    public void B030_ACT_DREG_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIDREG);
        DDCIDREG.DATA.KEY.AC = DDCSDACT.DATA.AC;
        DDCIDREG.DATA.KEY.CCY = DDCSDACT.DATA.CCY;
        DDCIDREG.DATA.KEY.CCY_TYPE = DDCSDACT.DATA.CCY_TYPE;
        DDCIDREG.OPT = 'R';
        S000_CALL_DDZIDREG();
        if (pgmRtn) return;
        DDCIDREG.DATA.KEY.AC = DDCSDACT.DATA.AC;
        DDCIDREG.DATA.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        WK_DREG_STS = DDCIDREG.DATA.STS;
        WK_DREG_BAL = DDCIDREG.DATA.BAL;
        WS_DREG_RCD_STS = DDCIDREG.DATA.RCD_STS;
        DDCIDREG.DATA.N_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDCIDREG.DATA.N_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDCIDREG.DATA.N_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, DDCIDREG.DATA.D_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        if (WK_DREG_STS != '1' 
            && WK_DREG_STS != '2' 
            && WK_DREG_STS != '3' 
            && WK_DREG_STS != '4' 
            && WK_DREG_STS != '9') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DORM_STS_ACT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_DREG_RCD_STS == 'D') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DREG_STS_DELETE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DDCIDREG.OPT = 'T';
        S000_CALL_DDZIDREG();
        if (pgmRtn) return;
    }
    public void B035_WRITE_DDTCLDD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ITM_NO);
        IBS.init(SCCGWA, DDRCLDD);
        DDRCLDD.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCLDD.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        DDRCLDD.KEY.AC = DDCSDACT.DATA.AC;
        DDRCLDD.KEY.CCY = DDCSDACT.DATA.CCY;
        DDRCLDD.KEY.CCY_TYPE = DDCSDACT.DATA.CCY_TYPE;
        DDRCLDD.STS = DDCIDREG.DATA.STS;
        DDRCLDD.BAL = DDCIDREG.DATA.BAL;
        DDRCLDD.FLG = 'O';
        DDRCLDD.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRCLDD.DRCR_FLG = 'D';
        DDRCLDD.ITM_NO = WS_ITM_NO;
        DDRCLDD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCLDD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DDTCLDD();
        if (pgmRtn) return;
    }
    public void B040_GEN_VCH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, AIRPAI7);
        AIRPAI7.KEY.TYP = "PAI07";
        AIRPAI7.KEY.REDEFINES6.AC_TYP = "3";
        AIRPAI7.KEY.REDEFINES6.GL_BOOK = "BK001";
        AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        if (WK_DREG_STS != '4') {
            AIRPAI7.KEY.REDEFINES6.BUSI_KND = "DDEG3";
            AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        } else {
            AIRPAI7.KEY.REDEFINES6.BUSI_KND = "DDEG1";
            AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        }
        BPCPRMR.DAT_PTR = AIRPAI7;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AIRPAI7.DATA_TXT.DATA_INF.ITM_R_C);
        CEP.TRC(SCCGWA, AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_C);
        if (AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_C != 0) {
            IBS.init(SCCGWA, AICPQIA);
            AICPQIA.CD.AC_TYP = "3";
            if (WK_DREG_STS != '4') {
                AICPQIA.CD.BUSI_KND = "DDEG3";
            } else {
                AICPQIA.CD.BUSI_KND = "DDEG1";
            }
            AICPQIA.BR = DDRMST.OWNER_BRDP;
            AICPQIA.CCY = DDCSDACT.DATA.CCY;
            AICPQIA.SIGN = 'C';
            S000_CALL_AIZPQIA();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICPQIA.AC);
            if (AICPQIA.AC == null) AICPQIA.AC = "";
            JIBS_tmp_int = AICPQIA.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) AICPQIA.AC += " ";
            WS_ITM_NO = AICPQIA.AC.substring(13 - 1, 13 + 8 - 1);
            CEP.TRC(SCCGWA, WS_ITM_NO);
            if (WK_DREG_BAL != 0) {
                IBS.init(SCCGWA, AICUUPIA);
                AICUUPIA.DATA.AC_NO = AICPQIA.AC;
                AICUUPIA.DATA.RVS_SEQ = 0;
                AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                AICUUPIA.DATA.AMT = DDCIDREG.DATA.BAL;
                AICUUPIA.DATA.CCY = DDCSDACT.DATA.CCY;
                AICUUPIA.DATA.EVENT_CODE = "DR";
                AICUUPIA.DATA.POST_NARR = " ";
                AICUUPIA.DATA.RVS_NO = " ";
                AICUUPIA.DATA.EVENT_CODE = "DR";
                S000_CALL_AIZUUPIA();
                if (pgmRtn) return;
                if (AICUUPIA.RC.RC_CODE != 0) {
                    WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = AICPQIA.AC;
            S000_CALL_AIZPQMIB();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, AICPUITM);
            AICPUITM.DATA.EVENT_CODE = "DR";
            AICPUITM.DATA.BR_OLD = DDRMST.OWNER_BRDP;
            AICPUITM.DATA.BR_NEW = DDRMST.OWNER_BRDP;
            AICPUITM.DATA.CCY = DDCSDACT.DATA.CCY;
            AICPUITM.DATA.ITM_NO = AIRPAI7.DATA_TXT.DATA_INF.ITM_R_C;
            AICPUITM.DATA.AMT = DDCIDREG.DATA.BAL;
            AICPUITM.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            S000_CALL_AIZPUITM();
            if (pgmRtn) return;
        }
    }
    public void B041_GEN_VCH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = DDCIQPRD.OUTPUT_DATA.PRDT_MODEL;
        BPCPOEWA.DATA.PROD_CODE = DDRCCY.PROD_CODE;
        if (WK_DREG_STS == '2') {
            BPCPOEWA.DATA.EVENT_CODE = "HD";
        }
        BPCPOEWA.DATA.BR_OLD = DDRCCY.OWNER_BR;
        BPCPOEWA.DATA.BR_NEW = DDRCCY.OWNER_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = DDRCCY.CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = WK_DREG_BAL;
        BPCPOEWA.DATA.AMT_INFO[20-1].AMT = WK_DREG_BAL;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CI_NO = CICACCU.DATA.CI_NO;
        BPCPOEWA.DATA.AC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        BPCPOEWA.DATA.REF_NO = " ";
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B042_FIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        BPCPFHIS.DATA.AC = DDRCCY.CUS_AC;
        BPCPFHIS.DATA.ACO_AC = DDRCCY.KEY.AC;
        BPCPFHIS.DATA.TX_TYPE = 'T';
        BPCPFHIS.DATA.TX_VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.TX_CCY = DDRCCY.CCY;
        BPCPFHIS.DATA.TX_AMT = WK_DREG_BAL;
        BPCPFHIS.DATA.PROD_CD = DDRCCY.PROD_CODE;
        BPCPFHIS.DATA.TX_CCY_TYP = DDRCCY.CCY_TYPE;
        BPCPFHIS.DATA.CI_NO = CICACCU.DATA.CI_NO;
        IBS.init(SCCGWA, DDRFHIS);
        DDRFHIS.TX_DATE = SCCGWA.COMM_AREA.TR_DATE;
        DDRFHIS.AC_NO = DDRCCY.CUS_AC;
        DDRFHIS.TX_TYPE = 'F';
        DDRFHIS.TX_AMT = WK_DREG_BAL;
        DDRFHIS.DOMBR = DDRCCY.OWNER_BRDP;
        DDRFHIS.BKBR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRFHIS.CCY = DDRCCY.CCY;
        DDRFHIS.CRDR_FLG = "CR".charAt(0);
        DDRFHIS.TXTIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRFHIS.TXBR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRFHIS.OPR = SCCGWA.COMM_AREA.TL_ID;
        DDRFHIS.LEDGER_BAL = 0;
        BPCPFHIS.DATA.FMT_CODE = "DD099";
        BPCPFHIS.DATA.FMT_LEN = 190;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, DDRFHIS);
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void B050_AMT_TRF_AC_PROC() throws IOException,SQLException,Exception {
        if ((DDCSDACT.DATA.AC.trim().length() > 0) 
            && (WK_DREG_BAL != 0)) {
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.TX_TYPE = 'T';
            DDCUCRAC.AC = DDCSDACT.DATA.AC;
            DDCUCRAC.CCY = DDCSDACT.DATA.CCY;
            DDCUCRAC.CCY_TYPE = DDCSDACT.DATA.CCY_TYPE;
            DDCUCRAC.TX_MMO = "AC00";
            DDCUCRAC.TX_AMT = WK_DREG_BAL;
            if (WK_DREG_STS != '4') {
                DDCUCRAC.OTHER_AC = AICPQIA.AC;
                DDCUCRAC.OTHER_CCY = AICPQMIB.OUTPUT_DATA.CHS_NM;
                DDCUCRAC.OTHER_CCY = AICPQMIB.INPUT_DATA.CCY;
            }
            if (DDRMST.AC_TYPE == 'M') {
                DDCUCRAC.GD_WITHDR_FLG = 'Y';
            }
            S000_CALL_DDZUCRAC();
            if (pgmRtn) return;
        }
    }
    public void B155_ITEM_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQITM);
        AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
        AICPQITM.INPUT_DATA.NO = WS_ITM_NO;
        S000_CALL_AIZPQITM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQITM.OUTPUT_DATA.CHS_NM);
    }
    public void B070_UPD_AC_STS_PROC() throws IOException,SQLException,Exception {
        if (CICACCU.DATA.CI_TYP != '1' 
            && WS_MST_AC_STS == 'D') {
            DDRMST.AC_STS = 'N';
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 03 - 1) + "0" + DDRMST.AC_STS_WORD.substring(03 + 1 - 1);
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(02 - 1, 02 + 1 - 1).equalsIgnoreCase("1")) {
                DDRMST.AC_STS = 'O';
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(01 - 1, 01 + 1 - 1).equalsIgnoreCase("1")) {
                DDRMST.AC_STS = 'V';
            }
            DDCRMST.FUNC = 'U';
            DDCRMST.REC_PTR = DDRMST;
            DDCRMST.REC_LEN = 425;
            S000_CALL_DDZRMST();
            if (pgmRtn) return;
        }
        if (WS_AC_STS_WORD == null) WS_AC_STS_WORD = "";
        JIBS_tmp_int = WS_AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) WS_AC_STS_WORD += " ";
        if (WS_AC_STS_WORD.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 5 - 1) + "0" + DDRMST.AC_STS_WORD.substring(5 + 1 - 1);
            DDCRMST.FUNC = 'U';
            DDCRMST.REC_PTR = DDRMST;
            DDCRMST.REC_LEN = 425;
            S000_CALL_DDZRMST();
            if (pgmRtn) return;
        }
        if (CICACCU.DATA.CI_TYP == '1') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 4 - 1) + "0" + DDRCCY.STS_WORD.substring(4 + 1 - 1);
        } else {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 3 - 1) + "0" + DDRCCY.STS_WORD.substring(3 + 1 - 1);
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 4 - 1) + "0" + DDRCCY.STS_WORD.substring(4 + 1 - 1);
            }
        }
        if (WS_MST_AC_STS == 'D' 
            && WK_DREG_BAL != 0) {
            if (SCCGWA.COMM_AREA.AC_DATE != DDRCCY.LAST_BAL_DATE) {
                if (SCCGWA.COMM_AREA.AC_DATE > DDRCCY.LAST_BAL_DATE) {
                    DDRCCY.LAST_BAL = DDRCCY.CURR_BAL;
                    DDRCCY.LAST_BAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                } else {
                    DDRCCY.LAST_BAL += WK_DREG_BAL;
                }
            }
            if (DDRCCY.LAST_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                DDRCCY.LAST_DRW_CAMT = DDRCCY.DRW_CAMT;
                DDRCCY.LAST_DEP_CAMT = DDRCCY.DEP_CAMT;
                DDRCCY.LAST_DRW_TAMT = DDRCCY.DRW_TAMT;
                DDRCCY.LAST_DEP_TAMT = DDRCCY.DEP_TAMT;
                DDRCCY.LASDAY_TOT_DR_AMT = DDRCCY.DAY_TOT_DR_AMT;
                DDRCCY.LASDAY_TOT_CR_AMT = DDRCCY.DAY_TOT_CR_AMT;
                DDRCCY.DRW_CAMT = 0;
                DDRCCY.DEP_CAMT = 0;
                DDRCCY.DRW_TAMT = 0;
                DDRCCY.DEP_TAMT = 0;
                DDRCCY.DAY_TOT_DR_AMT = 0;
                DDRCCY.DAY_TOT_CR_AMT = 0;
            }
            DDRCCY.CURR_BAL += WK_DREG_BAL;
            DDRCCY.DRW_TAMT += WK_DREG_BAL;
            DDRCCY.DAY_TOT_DR_AMT += WK_DREG_BAL;
            DDRCCY.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void B080_CHECK_CCY_STSW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCSDACT.DATA.AC;
        T000_STARTBR_DDTCCY();
        if (pgmRtn) return;
        T000_READNXT_DDTCCY();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 4 - 1) + "0" + DDRCCY.STS_WORD.substring(4 + 1 - 1);
                T000_REWRITE_DDTCCY();
                if (pgmRtn) return;
            }
            T000_READNXT_DDTCCY();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTCCY();
        if (pgmRtn) return;
    }
    public void B080_NON_FIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = DDCSDACT.DATA.AC;
        BPCPNHIS.INFO.TX_TOOL = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.CCY = DDCSDACT.DATA.CCY;
        BPCPNHIS.INFO.CCY_TYPE = DDCSDACT.DATA.CCY_TYPE;
        if (CICACCU.DATA.CI_TYP == '1') {
            BPCPNHIS.INFO.TX_TYP_CD = "P412";
        }
        if (CICACCU.DATA.CI_TYP == '2' 
            || CICACCU.DATA.CI_TYP == '3') {
            BPCPNHIS.INFO.TX_TYP_CD = "AC00";
        }
        BPCPNHIS.INFO.TX_TYP = 'O';
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B090_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCODACT);
        DDCODACT.AC = DDCSDACT.DATA.AC;
        DDCODACT.CCY = DDCSDACT.DATA.CCY;
        DDCODACT.CCY_TYPE = DDCSDACT.DATA.CCY_TYPE;
        DDCODACT.TX_MMO = DDCSDACT.DATA.TX_MMO;
        DDCODACT.REMARKS = DDCSDACT.DATA.REMARKS;
        DDCODACT.CUS_NM = DDCSDACT.DATA.CUS_NM;
        DDCODACT.ID_TYPE = DDCSDACT.DATA.ID_TYPE;
        DDCODACT.ID_NO = DDCSDACT.DATA.ID_NO;
        DDCODACT.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        DDCODACT.DT = DDCIDREG.DATA.D_DT;
        DDCODACT.AC2 = AICPQIA.AC;
        DDCODACT.ITM_NO = WS_ITM_NO;
        DDCODACT.ITM_NAME = AICPQITM.OUTPUT_DATA.CHS_NM;
        DDCODACT.L_BAL = WK_DREG_BAL;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCODACT;
        SCCFMT.DATA_LEN = 731;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_STARTBR_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_BR.rp = new DBParm();
        DDTCCY_BR.rp.TableName = "DDTCCY";
        DDTCCY_BR.rp.where = "CUS_AC = :DDRCCY.CUS_AC";
        DDTCCY_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, DDRCCY, this, DDTCCY_BR);
    }
    public void T000_READNXT_DDTCCY() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCY, this, DDTCCY_BR);
    }
    public void T000_ENDBR_DDTCCY() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCY_BR);
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void S000_CALL_AIZPUITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-ITM", AICPUITM, true);
        if (AICPUITM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPUITM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCPRMR.RC);
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, AICPQMIB.RC);
        }
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        CEP.TRC(SCCGWA, AICPQITM.RC);
        if (AICPQITM.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIDREG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SRC-DDZIDREG", DDCIDREG);
        if (DDCIDREG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIDREG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DDTCLDD() throws IOException,SQLException,Exception {
        DDTCLDD_RD = new DBParm();
        DDTCLDD_RD.TableName = "DDTCLDD";
        DDTCLDD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRCLDD, DDTCLDD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CLDD_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_INQ_DDPRD, DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZRMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-R-DDTMST", DDCRMST);
        if (DDCRMST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCRMST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCRMST.RETURN_INFO);
        CEP.TRC(SCCGWA, DDCRMST.RC);
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCPFHIS);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        if (AICPQIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
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
