package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.AI.*;
import com.hisun.DD.*;
import com.hisun.IB.*;
import com.hisun.DC.*;
import com.hisun.GD.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSPADD {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTFUND_RD;
    DBParm LNTICTL_RD;
    boolean pgmRtn = false;
    String K_DD_AC = "01";
    String K_INTERNAL = "02";
    String K_IB_AC = "03";
    String K_DC_AC = "05";
    String K_EB_AC = "06";
    String K_CQ_AC = "07";
    char K_CKPD_INQ = '0';
    short WS_FLD_NO = 0;
    short WS_CNT = 0;
    short WS_FEE_CNT = 0;
    short WS_I = 0;
    short WS_IDX = 0;
    char WS_CRDB_FLG = ' ';
    LNZSPADD_WS_MSGID WS_MSGID = new LNZSPADD_WS_MSGID();
    LNZSPADD_WS_LOAN_CONT_AREA WS_LOAN_CONT_AREA = new LNZSPADD_WS_LOAN_CONT_AREA();
    String WS_CHECK_AC_TYP = " ";
    String WS_INP_ACT = " ";
    String WS_CHECK_AC = " ";
    String WS_KFS_NO = " ";
    String WS_GUAR_NO = " ";
    String WS_ACCOUNT_AC = " ";
    String WS_THR_AC = " ";
    String WS_REF_NO = " ";
    int[] WS_ACR_BBR = new int[20];
    short WS_BBR_I = 0;
    char WS_GD_AC_FLG = ' ';
    double WS_TX_AMT = 0;
    String WS_FUND_AC = " ";
    char WS_PAY_FLG = ' ';
    String WS_PAY_P_TRAN_AC = " ";
    String WS_PAY_I_TRAN_AC = " ";
    String WS_SETL_AC_TYPE = " ";
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    AICPQMIB AICPQMIB = new AICPQMIB();
    DDCIMMST DDCIMMST = new DDCIMMST();
    IBCQINF IBCQINF = new IBCQINF();
    DCCUCINF DCCUCINF = new DCCUCINF();
    GDCSMPRL GDCSMPRL = new GDCSMPRL();
    LNRFUND LNRFUND = new LNRFUND();
    LNCRFUND LNCRFUND = new LNCRFUND();
    LNCSRATE LNCSRATE = new LNCSRATE();
    CICCUST CICCUST = new CICCUST();
    CICMACR CICMACR = new CICMACR();
    CICSACR CICSACR = new CICSACR();
    CICSACAC CICSACAC = new CICSACAC();
    CICQACRI CICQACRI = new CICQACRI();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCACLDD BPCACLDD = new BPCACLDD();
    BPCPQAMO BPCPQAMO = new BPCPQAMO();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    LNCRSETL LNCRSETL = new LNCRSETL();
    LNRSETL LNRSETL = new LNRSETL();
    LNRICTL LNRICTL = new LNRICTL();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    AICUUPIA AICUUPIA = new AICUUPIA();
    LNCUDRW LNCUDRW = new LNCUDRW();
    LNCUDRWR LNCUDRWR = new LNCUDRWR();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICACCU CICACCU = new CICACCU();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    BPCOFBAS BPCOFBAS = new BPCOFBAS();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCRATHM LNCRATHM = new LNCRATHM();
    LNCBALLM LNCBALLM = new LNCBALLM();
    BPCFFCON BPCFFCON = new BPCFFCON();
    LNCSCKPD LNCSCKPD = new LNCSCKPD();
    SCCGWA SCCGWA;
    LNCSPADD LNCSPADD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA, LNCSPADD LNCSPADD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSPADD = LNCSPADD;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSPADD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        LNCSPADD.RC.RC_APP = "LN";
        LNCSPADD.RC.RC_RTNCODE = 0;
        WS_BBR_I = 1;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B000_MAIN_PROC();
            if (pgmRtn) return;
        } else {
            B000_CHECK_INPUT();
            if (pgmRtn) return;
            B000_MAIN_PROC();
            if (pgmRtn) return;
        }
    }
    public void B000_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = LNCSPADD.COMM_DATA.BOOK_CRE;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = LNCSPADD.COMM_DATA.DOMI_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = LNCSPADD.COMM_DATA.CCY;
        S000_CALL_BPZQCCY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCSCKPD);
        LNCSCKPD.FUNC = K_CKPD_INQ;
        LNCSCKPD.PROD_CD = LNCSPADD.COMM_DATA.PROD_TYP;
        S000_CALL_LNZSCKPD();
        if (pgmRtn) return;
        if (LNCSPADD.COMM_DATA.GUAR_AC.trim().length() > 0 
            && LNCSPADD.COMM_DATA.KFS_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.ENTY_TYP = '1';
            CICACCU.DATA.AGR_NO = LNCSPADD.COMM_DATA.KFS_AC;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
            WS_KFS_NO = CICACCU.DATA.CI_NO;
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.ENTY_TYP = '1';
            CICACCU.DATA.AGR_NO = LNCSPADD.COMM_DATA.GUAR_AC;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
            WS_GUAR_NO = CICACCU.DATA.CI_NO;
            if (!WS_KFS_NO.equalsIgnoreCase(WS_GUAR_NO)) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_KFS_GUAR_NM, WS_MSGID);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B200_DRAW_DOWN_PROC();
            if (pgmRtn) return;
            B110_CREATE_ACR_PROC();
            if (pgmRtn) return;
        } else {
            B100_LOAN_DAT_CREATE_PROC();
            if (pgmRtn) return;
            B200_DRAW_DOWN_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_LOAN_DAT_CREATE_PROC() throws IOException,SQLException,Exception {
        B110_CREATE_ACR_PROC();
        if (pgmRtn) return;
        B120_CREATE_CONT_PROC();
        if (pgmRtn) return;
        B130_ADD_LOAN_PROCESS();
        if (pgmRtn) return;
        B140_ADD_APRD_PROCESS();
        if (pgmRtn) return;
        B150_ADD_ICTL_PROCESS();
        if (pgmRtn) return;
        B160_ADD_RATH_PROCESS();
        if (pgmRtn) return;
        B170_ADD_CNGM_PROCESS();
        if (pgmRtn) return;
        B180_ADD_SETL_PROCESS();
        if (pgmRtn) return;
        B190_ADD_BALL_PROCESS();
        if (pgmRtn) return;
    }
    public void B110_CREATE_ACR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = LNCSPADD.COMM_DATA.CI_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICSACR);
        IBS.init(SCCGWA, CICSACAC);
        CICSACR.FUNC = 'A';
        CICSACR.DATA.ENTY_TYP = '1';
        CICSACR.DATA.AGR_NO = LNCSPADD.COMM_DATA.PAPER_NO;
        CICSACR.DATA.CI_NO = LNCSPADD.COMM_DATA.CI_NO;
        CICSACR.DATA.PROD_CD = LNCSPADD.COMM_DATA.PROD_TYP;
        CICSACR.DATA.CNTRCT_TYP = LNCSCKPD.PRODMO;
        CICSACR.DATA.FRM_APP = "LN";
        CICSACR.DATA.CCY = LNCSPADD.COMM_DATA.CCY;
        CICSACR.DATA.SHOW_FLG = 'Y';
        CICSACR.DATA.AC_CNM = CICCUST.O_DATA.O_CI_NM;
        CICSACR.DATA.AC_ENM = CICCUST.O_DATA.O_CI_ENM;
        CICSACR.DATA.OPN_BR = LNCSPADD.COMM_DATA.DOMI_BR;
        CICSACR.DATA.OWNER_BK = LNCSPADD.COMM_DATA.BOOK_CRE;
        CICSACR.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_CIZSACR();
        if (pgmRtn) return;
        CICSACAC.FUNC = 'A';
        CICSACAC.DATA.AGR_NO = LNCSPADD.COMM_DATA.PAPER_NO;
        CICSACAC.DATA.BV_NO = LNCSPADD.COMM_DATA.DRAW_NO;
        CICSACAC.DATA.ACAC_NO = LNCSPADD.COMM_DATA.CONT_NO;
        CICSACAC.DATA.PROD_CD = LNCSPADD.COMM_DATA.PROD_TYP;
        CICSACAC.DATA.FRM_APP = "LN";
        CICSACAC.DATA.CCY = LNCSPADD.COMM_DATA.CCY;
        CICSACAC.DATA.OPN_BR = LNCSPADD.COMM_DATA.DOMI_BR;
        CICSACAC.DATA.OWNER_BK = LNCSPADD.COMM_DATA.BOOK_CRE;
        CICSACAC.DATA.ACAC_CTL = "ZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZERO";
        CICSACAC.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        if (LNCSPADD.COMM_DATA.CCY.equalsIgnoreCase("156")) {
            CICSACAC.DATA.CR_FLG = '1';
        } else {
            CICSACAC.DATA.CR_FLG = '2';
        }
        S000_CALL_CIZSACAC();
        if (pgmRtn) return;
    }
    public void B120_CREATE_CONT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '0';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCSPADD.COMM_DATA.CONT_NO;
        LNCCONTM.REC_DATA.CONTRACT_TYPE = "CLDD";
        LNCCONTM.REC_DATA.CTA_STS = '0';
        LNCCONTM.REC_DATA.PROD_CD = LNCSPADD.COMM_DATA.PROD_TYP;
        LNCCONTM.REC_DATA.CCY = LNCSPADD.COMM_DATA.CCY;
        LNCCONTM.REC_DATA.LN_TOT_AMT = LNCSPADD.COMM_DATA.CONT_AMT;
        LNCCONTM.REC_DATA.ORIG_TOT_AMT = LNCSPADD.COMM_DATA.CONT_AMT;
        LNCCONTM.REC_DATA.START_DATE = LNCSPADD.COMM_DATA.START_DT;
        LNCCONTM.REC_DATA.MAT_DATE = LNCSPADD.COMM_DATA.MATU_DT;
        LNCCONTM.REC_DATA.ORI_MAT_DATE = LNCSPADD.COMM_DATA.MATU_DT;
        LNCCONTM.REC_DATA.BOOK_BR = LNCSPADD.COMM_DATA.BOOK_CRE;
        LNCCONTM.REC_DATA.DOMI_BR = LNCSPADD.COMM_DATA.DOMI_BR;
        LNCCONTM.REC_DATA.REMARK1 = LNCSPADD.COMM_DATA.REMARK1;
        LNCCONTM.REC_DATA.LAST_TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNCCONTM.REC_DATA.TRAN_DTE = SCCGWA.COMM_AREA.AC_DATE;
        LNCCONTM.REC_DATA.TRAN_JRN = SCCGWA.COMM_AREA.JRN_NO;
        LNCCONTM.REC_DATA.LAST_F_VAL_DATE = LNCSPADD.COMM_DATA.START_DT;
        LNCCONTM.REC_DATA.LAST_TX_SEQ = 0;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
    }
    public void B130_ADD_LOAN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCLOANM);
        LNCLOANM.FUNC = '0';
        LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCSPADD.COMM_DATA.CONT_NO;
        LNCLOANM.REC_DATA.BRAT_EFF_DT = LNCSPADD.COMM_DATA.START_DT;
        LNCLOANM.REC_DATA.ORAT_EFF_DT = LNCSPADD.COMM_DATA.START_DT;
        LNCLOANM.REC_DATA.LRAT_EFF_DT = LNCSPADD.COMM_DATA.START_DT;
        LNCLOANM.REC_DATA.PRAT_EFF_DT = LNCSPADD.COMM_DATA.START_DT;
        LNCLOANM.REC_DATA.FST_CAL_DT = LNCSPADD.COMM_DATA.CAL_FSDT;
        LNCLOANM.REC_DATA.FST_PAYP_DT = LNCSPADD.COMM_DATA.MATU_DT;
        LNCLOANM.REC_DATA.AUTO_AMT = 0;
        LNCLOANM.REC_DATA.PD_PROJ_NO = LNCSPADD.COMM_DATA.GJPRJNO;
        S000_CALL_LNZLOANM();
        if (pgmRtn) return;
    }
    public void B140_ADD_APRD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '0';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCSPADD.COMM_DATA.CONT_NO;
        LNCAPRDM.REC_DATA.PAY_MTH = LNCSPADD.COMM_DATA.REPY_MTH;
        LNCAPRDM.REC_DATA.INST_MTH = LNCSPADD.COMM_DATA.INST_MTH;
        LNCAPRDM.REC_DATA.CAL_PERD = LNCSPADD.COMM_DATA.CAL_PERD;
        LNCAPRDM.REC_DATA.CAL_PERD_UNIT = LNCSPADD.COMM_DATA.CAL_PERU;
        LNCAPRDM.REC_DATA.BCAL_PERD = LNCSPADD.COMM_DATA.PAYI_PERD;
        LNCAPRDM.REC_DATA.PAYP_PERD = LNCSPADD.COMM_DATA.PYP_PERD;
        LNCAPRDM.REC_DATA.PAYP_PERD_UNIT = LNCSPADD.COMM_DATA.PYP_PERU;
        LNCAPRDM.REC_DATA.BPAYP_PERD = LNCSPADD.COMM_DATA.PYP_CIRC;
        LNCAPRDM.REC_DATA.ORAT_SAME = LNCSPADD.COMM_DATA.CPND_USE;
        LNCAPRDM.REC_DATA.OCAL_PERD = LNCSPADD.COMM_DATA.OLC_PERD;
        LNCAPRDM.REC_DATA.OCAL_PERD_UNIT = LNCSPADD.COMM_DATA.OLC_PERU;
        LNCAPRDM.REC_DATA.GDA_DB_SEQ = LNCSPADD.COMM_DATA.GUARPSEQ;
        if (LNCAPRDM.REC_DATA.GDA_DB_SEQ == ' ' 
            && LNCSPADD.COMM_DATA.GUAPREF.trim().length() > 0) {
            LNCAPRDM.REC_DATA.GDA_DB_SEQ = 'S';
        }
        LNCAPRDM.REC_DATA.PAY_DAY = LNCSPADD.COMM_DATA.CAL_DAY;
        LNCAPRDM.REC_DATA.PAY_DD_TYPE = LNCSPADD.COMM_DATA.PAY_DTYP;
        LNCAPRDM.REC_DATA.INT_DBAS_STD = LNCSPADD.COMM_DATA.INT_D_BA;
        LNCAPRDM.REC_DATA.DUE_AUTO_FLG = '1';
        LNCAPRDM.REC_DATA.ACCRUAL_TYPE = '0';
        LNCAPRDM.REC_DATA.GDA_APRE = LNCSPADD.COMM_DATA.GUAPREF;
        if (LNCAPRDM.REC_DATA.PAY_DD_TYPE == '2' 
            && LNCAPRDM.REC_DATA.PAY_DAY > 7) {
            LNCAPRDM.REC_DATA.PAY_DAY = 1;
        }
        LNCAPRDM.REC_DATA.GDA_AUTO_DB = LNCSPADD.COMM_DATA.GUARDUAP;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
    }
    public void B150_ADD_ICTL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '0';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSPADD.COMM_DATA.CONT_NO;
        if ("0".trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt("0");
        LNCICTLM.REC_DATA.CTL_STSW = "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        LNCICTLM.REC_DATA.CTL_STSW = "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(1);
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 19 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(19 + 1 - 1);
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 52 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(52 + 1 - 1);
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 70 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(70 + 1 - 1);
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 71 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(71 + 1 - 1);
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 31 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(31 + 1 - 1);
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 53 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(53 + 1 - 1);
        LNCICTLM.REC_DATA.CUR_RAT_DT = LNCSPADD.COMM_DATA.START_DT;
        LNCICTLM.REC_DATA.P_CUR_TERM = 1;
        LNCICTLM.REC_DATA.IC_CUR_TERM = 1;
        LNCICTLM.REC_DATA.SUBS_CUR_TERM = 0;
        LNCICTLM.REC_DATA.SUBS_LAST_REPY_DT = 0;
        LNCICTLM.REC_DATA.IC_CAL_PHS_NO = 1;
        LNCICTLM.REC_DATA.IC_CMP_PHS_NO = 1;
        LNCICTLM.REC_DATA.IC_CMP_TERM = 1;
        LNCICTLM.REC_DATA.IC_CMP_VAL_DT = LNCSPADD.COMM_DATA.START_DT;
        LNCICTLM.REC_DATA.IC_CMP_DUE_DT = LNCSPADD.COMM_DATA.MATU_DT;
        LNCICTLM.REC_DATA.IC_CAL_TERM = 1;
        LNCICTLM.REC_DATA.IC_CAL_VAL_DT = LNCSPADD.COMM_DATA.START_DT;
        LNCICTLM.REC_DATA.IC_CAL_DUE_DT = LNCSPADD.COMM_DATA.MATU_DT;
        LNCICTLM.REC_DATA.P_CMP_TERM = 1;
        LNCICTLM.REC_DATA.P_CAL_TERM = 1;
        LNCICTLM.REC_DATA.NEXT_ACCU_DT = 0;
        LNCICTLM.REC_DATA.INT_CUT_DT = LNCSPADD.COMM_DATA.START_DT;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B160_ADD_RATH_PROCESS() throws IOException,SQLException,Exception {
        if ((LNCSPADD.COMM_DATA.IRAT_TP1 != ' ' 
            && LNCSPADD.COMM_DATA.IN_RATE != 0) 
            || (LNCSPADD.COMM_DATA.PEN_RATT != ' ' 
            && LNCSPADD.COMM_DATA.PEN_IRAT != 0) 
            || LNCSPADD.COMM_DATA.CPND_USE == 'Y' 
            || (LNCSPADD.COMM_DATA.CPNDRATT != ' ' 
            && LNCSPADD.COMM_DATA.CPND_IRA != 0)) {
            IBS.init(SCCGWA, LNCSRATE);
            LNCSRATE.FUNC_TYP = 'A';
            LNCSRATE.SPE_DEAL_FLG = '1';
            CEP.TRC(SCCGWA, LNCSRATE.SPE_DEAL_FLG);
            LNCSRATE.DATA.CONTRACT_NO = LNCSPADD.COMM_DATA.CONT_NO;
            LNCSRATE.DATA.SUB_CTA_NO = 0;
            LNCSRATE.DATA.RAT_CHG_DT = LNCSPADD.COMM_DATA.START_DT;
            CEP.TRC(SCCGWA, LNCSRATE.DATA.RAT_CHG_DT);
            LNCSRATE.DATA.RATE_KIND_N = LNCSPADD.COMM_DATA.IRAT_TP1;
            CEP.TRC(SCCGWA, LNCSPADD.COMM_DATA.IRAT_TP1);
            CEP.TRC(SCCGWA, LNCSRATE.DATA.RATE_KIND_N);
            LNCSRATE.DATA.INT_RAT_N = LNCSPADD.COMM_DATA.IN_RATE;
            CEP.TRC(SCCGWA, LNCSPADD.COMM_DATA.IN_RATE);
            CEP.TRC(SCCGWA, LNCSRATE.DATA.INT_RAT_N);
            LNCSRATE.DATA.RATE_KIND_O = LNCSPADD.COMM_DATA.PEN_RATT;
            LNCSRATE.DATA.INT_RAT_O = LNCSPADD.COMM_DATA.PEN_IRAT;
            LNCSRATE.DATA.CPND_USE = LNCSPADD.COMM_DATA.CPND_USE;
            LNCSRATE.DATA.RATE_KIND_L = LNCSPADD.COMM_DATA.CPNDRATT;
            LNCSRATE.DATA.INT_RAT_L = LNCSPADD.COMM_DATA.CPND_IRA;
            LNCSRATE.DATA.RATE_KIND_P = LNCSPADD.COMM_DATA.ABUSRATT;
            LNCSRATE.DATA.INT_RAT_P = LNCSPADD.COMM_DATA.ABUSIRAT;
            S000_CALL_LNZSRATE();
            if (pgmRtn) return;
        }
    }
    public void B170_ADD_CNGM_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCNGL);
        IBS.init(SCCGWA, BPCACLDD);
        BPCQCNGL.DAT.INPUT.CNTR_TYPE = LNCSCKPD.PRODMO;
        BPCQCNGL.DAT.INPUT.BR = LNCSPADD.COMM_DATA.BOOK_CRE;
        BPCACLDD.PROD_CD = LNCSPADD.COMM_DATA.PROD_TYP;
        BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 31;
        BPCQCNGL.DAT.INPUT.OTH_PTR = BPCACLDD;
        IBS.init(SCCGWA, BPCUCNGM);
        BPCUCNGM.KEY.AC = LNCSPADD.COMM_DATA.CONT_NO;
        BPCUCNGM.KEY.REL_SEQ = " ";
        BPCUCNGM.KEY.CNTR_TYPE = LNCSCKPD.PRODMO;
        BPCUCNGM.PROD_TYPE = LNCSPADD.COMM_DATA.PROD_TYP;
        BPCUCNGM.BR = LNCSPADD.COMM_DATA.BOOK_CRE;
        BPCUCNGM.MOD_NO = BPCQCNGL.DAT.OUTPUT.MOD_NO;
        BPCUCNGM.DATA[1-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO;
        BPCUCNGM.DATA[2-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[2-1].MSTNO;
        BPCUCNGM.DATA[3-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[3-1].MSTNO;
        BPCUCNGM.DATA[4-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[4-1].MSTNO;
        BPCUCNGM.FUNC = 'A';
    }
    public void B180_ADD_SETL_PROCESS() throws IOException,SQLException,Exception {
        B181_CHECK_SETL_PROC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCRSETL);
        IBS.init(SCCGWA, LNRSETL);
        LNCRSETL.FUNC = 'A';
        LNRSETL.KEY.CONTRACT_NO = LNCSPADD.COMM_DATA.CONT_NO;
        LNRSETL.KEY.CI_TYPE = 'B';
        LNRSETL.KEY.CCY = LNCSPADD.COMM_DATA.CCY;
        LNRSETL.KEY.SETTLE_TYPE = '1';
        LNRSETL.MWHD_AC_FLG = 'N';
        LNRSETL.AC_TYP = LNCSPADD.COMM_DATA.DRAW_ACT;
        LNRSETL.AC = LNCSPADD.COMM_DATA.DRAW_AC;
        LNRSETL.AC_FLG = '0';
        S000_CALL_LNZRSETL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCRSETL);
        IBS.init(SCCGWA, LNRSETL);
        LNCRSETL.FUNC = 'A';
        LNRSETL.KEY.CONTRACT_NO = LNCSPADD.COMM_DATA.CONT_NO;
        LNRSETL.KEY.CI_TYPE = 'B';
        LNRSETL.KEY.CCY = LNCSPADD.COMM_DATA.CCY;
        LNRSETL.KEY.SETTLE_TYPE = '2';
        LNRSETL.MWHD_AC_FLG = 'N';
        LNRSETL.AC_TYP = LNCSPADD.COMM_DATA.PAY_AC_T;
        LNRSETL.AC = LNCSPADD.COMM_DATA.PAY_AC;
        LNRSETL.AC_FLG = '0';
        S000_CALL_LNZRSETL();
        if (pgmRtn) return;
        if (LNCSPADD.COMM_DATA.GUAR_AC.trim().length() > 0) {
            IBS.init(SCCGWA, LNCRSETL);
            IBS.init(SCCGWA, LNRSETL);
            LNCRSETL.FUNC = 'A';
            LNRSETL.KEY.CONTRACT_NO = LNCSPADD.COMM_DATA.CONT_NO;
            LNRSETL.KEY.CI_TYPE = 'B';
            LNRSETL.KEY.CCY = LNCSPADD.COMM_DATA.CCY;
            LNRSETL.KEY.SETTLE_TYPE = '3';
            LNRSETL.MWHD_AC_FLG = 'N';
            LNRSETL.AC_TYP = "04";
            LNRSETL.AC = LNCSPADD.COMM_DATA.GUAR_AC;
            LNRSETL.AC_FLG = '0';
            S000_CALL_LNZRSETL();
            if (pgmRtn) return;
        }
    }
    public void B181_CHECK_SETL_PROC() throws IOException,SQLException,Exception {
        WS_CHECK_AC = LNCSPADD.COMM_DATA.DRAW_AC;
        WS_INP_ACT = LNCSPADD.COMM_DATA.DRAW_ACT;
        B182_CHECK_ACT_PROC();
        if (pgmRtn) return;
        WS_CHECK_AC_TYP = WS_INP_ACT;
        B183_CHECK_SETL_INFO();
        if (pgmRtn) return;
        WS_CHECK_AC = LNCSPADD.COMM_DATA.PAY_AC;
        WS_INP_ACT = LNCSPADD.COMM_DATA.PAY_AC_T;
        B182_CHECK_ACT_PROC();
        if (pgmRtn) return;
        WS_CHECK_AC_TYP = WS_INP_ACT;
        B183_CHECK_SETL_INFO();
        if (pgmRtn) return;
    }
    public void B182_CHECK_ACT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.DATA.AGR_NO = WS_CHECK_AC;
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("IB")) {
            WS_CHECK_AC_TYP = K_IB_AC;
        } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            WS_CHECK_AC_TYP = K_DD_AC;
        } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            WS_CHECK_AC_TYP = K_DC_AC;
        } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            WS_CHECK_AC_TYP = K_INTERNAL;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_AC_TYP_NOT_MATCH, LNCSPADD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_CHECK_AC_TYP);
        if (WS_INP_ACT.trim().length() == 0) {
            WS_INP_ACT = WS_CHECK_AC_TYP;
        } else {
            if (!WS_CHECK_AC_TYP.equalsIgnoreCase(WS_INP_ACT)) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ACT_NOT_MATCH, WS_MSGID);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B183_CHECK_SETL_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CHECK_AC);
        CEP.TRC(SCCGWA, WS_CHECK_AC_TYP);
        if (WS_CHECK_AC_TYP.equalsIgnoreCase(K_DD_AC)
            || WS_CHECK_AC_TYP.equalsIgnoreCase(K_CQ_AC)) {
            B183_01_CHECK_DDAC();
            if (pgmRtn) return;
        } else if (WS_CHECK_AC_TYP.equalsIgnoreCase(K_INTERNAL)) {
            B183_02_CHECK_INTAC();
            if (pgmRtn) return;
        } else if (WS_CHECK_AC_TYP.equalsIgnoreCase(K_IB_AC)) {
            B183_03_CHECK_IBAC();
            if (pgmRtn) return;
        } else if (WS_CHECK_AC_TYP.equalsIgnoreCase(K_DC_AC)
            || WS_CHECK_AC_TYP.equalsIgnoreCase(K_EB_AC)) {
            B183_04_CHECK_DCAC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.SET_MET_NOT_RIGHT, LNCSPADD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B183_01_CHECK_DDAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIMMST);
        DDCIMMST.DATA.KEY.AC_NO = WS_CHECK_AC;
        DDCIMMST.TX_TYPE = 'I';
        S000_CALL_DDZIMMST();
        if (pgmRtn) return;
    }
    public void B183_02_CHECK_INTAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.AC = WS_CHECK_AC;
        AICPQMIB.INPUT_DATA.CCY = LNCSPADD.COMM_DATA.CCY;
        S000_CALL_AIZPQMIB();
        if (pgmRtn) return;
    }
    public void B183_03_CHECK_IBAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINF);
        IBCQINF.INPUT_DATA.NOSTRO_CD = WS_CHECK_AC;
        IBCQINF.INPUT_DATA.CCY = LNCSPADD.COMM_DATA.CCY;
        S000_CALL_IBZQINF();
        if (pgmRtn) return;
    }
    public void B183_04_CHECK_DCAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = WS_CHECK_AC;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUCINF.CARD_STS);
        if (DCCUCINF.CARD_STS != ' ' 
            && DCCUCINF.CARD_STS != 'N') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CARD_NOT_EXISTS, LNCSPADD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1") 
            || DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            || DCCUCINF.CARD_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            || DCCUCINF.CARD_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
            || DCCUCINF.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CARD_NOT_EXISTS, LNCSPADD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B190_ADD_BALL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCBALLM);
        LNCBALLM.FUNC = '0';
        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNCSPADD.COMM_DATA.CONT_NO;
        LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        WS_LOAN_CONT_AREA.WS_LOAN_CONT_AREA_LEN1 = 1584;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_LOAN_CONT_AREA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCBALLM.REC_DATA.LOAN_CONT_AREA);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.LOAN_CONT_AREA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[1], LNCBALLM.REC_DATA.REDEFINES18);
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
    }
    public void B200_DRAW_DOWN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRFUND);
        LNRFUND.KEY.PROJ_NO = LNCSPADD.COMM_DATA.GJPRJNO;
        T000_READ_LNTFUND();
        if (pgmRtn) return;
        WS_FUND_AC = LNRFUND.FUND_AC;
        LNCSPADD.COMM_DATA.SRC_AC = LNRFUND.FUND_AC;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B220_DRAWDOWN_DELETE();
            if (pgmRtn) return;
            WS_ACCOUNT_AC = LNCSPADD.COMM_DATA.SRC_AC;
            WS_TX_AMT = LNCSPADD.COMM_DATA.CONT_AMT;
            WS_THR_AC = WS_FUND_AC;
            WS_CRDB_FLG = 'D';
            B300_ROUTE_AND_ACCOUNT_PROC();
            if (pgmRtn) return;
            WS_ACCOUNT_AC = WS_FUND_AC;
            WS_TX_AMT = LNCSPADD.COMM_DATA.CONT_AMT;
            WS_THR_AC = LNCSPADD.COMM_DATA.SRC_AC;
            WS_CRDB_FLG = 'C';
            B300_ROUTE_AND_ACCOUNT_PROC();
            if (pgmRtn) return;
            if (LNCSPADD.COMM_DATA.GUAR_AC.trim().length() > 0 
                && LNCSPADD.COMM_DATA.GUAR_AMT != 0 
                && LNCSPADD.COMM_DATA.KFS_AC.trim().length() > 0) {
                WS_ACCOUNT_AC = LNCSPADD.COMM_DATA.KFS_AC;
                WS_TX_AMT = LNCSPADD.COMM_DATA.GUAR_AMT;
                WS_THR_AC = LNCSPADD.COMM_DATA.GUAR_AC;
                WS_CRDB_FLG = 'D';
                B300_ROUTE_AND_ACCOUNT_PROC();
                if (pgmRtn) return;
                WS_ACCOUNT_AC = LNCSPADD.COMM_DATA.GUAR_AC;
                WS_TX_AMT = LNCSPADD.COMM_DATA.GUAR_AMT;
                WS_GD_AC_FLG = 'Y';
                WS_THR_AC = LNCSPADD.COMM_DATA.KFS_AC;
                WS_CRDB_FLG = 'C';
                B400_DD_AC_C();
                if (pgmRtn) return;
            }
            if (!LNCSPADD.COMM_DATA.DRAW_AC.equalsIgnoreCase(LNCSPADD.COMM_DATA.KFS_AC) 
                && LNCSPADD.COMM_DATA.KFS_AC.trim().length() > 0) {
                WS_ACCOUNT_AC = LNCSPADD.COMM_DATA.DRAW_AC;
                WS_TX_AMT = LNCSPADD.COMM_DATA.CONT_AMT;
                WS_THR_AC = LNCSPADD.COMM_DATA.KFS_AC;
                WS_CRDB_FLG = 'D';
                B300_ROUTE_AND_ACCOUNT_PROC();
                if (pgmRtn) return;
                WS_ACCOUNT_AC = LNCSPADD.COMM_DATA.KFS_AC;
                WS_TX_AMT = LNCSPADD.COMM_DATA.CONT_AMT;
                WS_THR_AC = LNCSPADD.COMM_DATA.DRAW_AC;
                WS_CRDB_FLG = 'C';
                B300_ROUTE_AND_ACCOUNT_PROC();
                if (pgmRtn) return;
            }
            if (LNCSPADD.COMM_DATA.YHS_AC.trim().length() > 0 
                && LNCSPADD.COMM_DATA.YHS_AMT > 0) {
                WS_ACCOUNT_AC = LNCSPADD.COMM_DATA.YHS_AC;
                WS_TX_AMT = LNCSPADD.COMM_DATA.YHS_AMT;
                WS_THR_AC = LNCSPADD.COMM_DATA.CONT_NO;
                WS_CRDB_FLG = 'D';
                B300_ROUTE_AND_ACCOUNT_PROC();
                if (pgmRtn) return;
            }
        } else {
            WS_ACCOUNT_AC = LNCSPADD.COMM_DATA.SRC_AC;
            WS_TX_AMT = LNCSPADD.COMM_DATA.CONT_AMT;
            WS_THR_AC = WS_FUND_AC;
            WS_CRDB_FLG = 'D';
            B300_ROUTE_AND_ACCOUNT_PROC();
            if (pgmRtn) return;
            WS_ACCOUNT_AC = WS_FUND_AC;
            WS_TX_AMT = LNCSPADD.COMM_DATA.CONT_AMT;
            WS_THR_AC = LNCSPADD.COMM_DATA.SRC_AC;
            WS_CRDB_FLG = 'C';
            B300_ROUTE_AND_ACCOUNT_PROC();
            if (pgmRtn) return;
            WS_ACCOUNT_AC = WS_FUND_AC;
            WS_TX_AMT = LNCSPADD.COMM_DATA.CONT_AMT;
            WS_THR_AC = LNCSPADD.COMM_DATA.CONT_NO;
            WS_CRDB_FLG = 'D';
            B300_ROUTE_AND_ACCOUNT_PROC();
            if (pgmRtn) return;
            WS_ACCOUNT_AC = LNCSPADD.COMM_DATA.DRAW_AC;
            WS_TX_AMT = LNCSPADD.COMM_DATA.CONT_AMT;
            WS_THR_AC = LNCSPADD.COMM_DATA.CONT_NO;
            WS_CRDB_FLG = 'C';
            B300_ROUTE_AND_ACCOUNT_PROC();
            if (pgmRtn) return;
            if (!LNCSPADD.COMM_DATA.DRAW_AC.equalsIgnoreCase(LNCSPADD.COMM_DATA.KFS_AC) 
                && LNCSPADD.COMM_DATA.KFS_AC.trim().length() > 0) {
                WS_ACCOUNT_AC = LNCSPADD.COMM_DATA.DRAW_AC;
                WS_TX_AMT = LNCSPADD.COMM_DATA.CONT_AMT;
                WS_THR_AC = LNCSPADD.COMM_DATA.KFS_AC;
                WS_CRDB_FLG = 'D';
                B300_ROUTE_AND_ACCOUNT_PROC();
                if (pgmRtn) return;
                WS_ACCOUNT_AC = LNCSPADD.COMM_DATA.KFS_AC;
                WS_TX_AMT = LNCSPADD.COMM_DATA.CONT_AMT;
                WS_THR_AC = LNCSPADD.COMM_DATA.DRAW_AC;
                WS_CRDB_FLG = 'C';
                B300_ROUTE_AND_ACCOUNT_PROC();
                if (pgmRtn) return;
            }
            if (LNCSPADD.COMM_DATA.GUAR_AC.trim().length() > 0 
                && LNCSPADD.COMM_DATA.GUAR_AMT != 0 
                && LNCSPADD.COMM_DATA.KFS_AC.trim().length() > 0) {
                WS_ACCOUNT_AC = LNCSPADD.COMM_DATA.KFS_AC;
                WS_TX_AMT = LNCSPADD.COMM_DATA.GUAR_AMT;
                WS_THR_AC = LNCSPADD.COMM_DATA.GUAR_AC;
                WS_CRDB_FLG = 'D';
                B300_ROUTE_AND_ACCOUNT_PROC();
                if (pgmRtn) return;
                IBS.init(SCCGWA, DCCPACTY);
                DCCPACTY.INPUT.AC = LNCSPADD.COMM_DATA.GUAR_AC;
                DCCPACTY.INPUT.FUNC = '1';
                WS_ACCOUNT_AC = LNCSPADD.COMM_DATA.GUAR_AC;
                WS_TX_AMT = LNCSPADD.COMM_DATA.GUAR_AMT;
                WS_GD_AC_FLG = 'Y';
                WS_THR_AC = LNCSPADD.COMM_DATA.KFS_AC;
                WS_CRDB_FLG = 'C';
                B400_DD_AC_C();
                if (pgmRtn) return;
                WS_BBR_I += 1;
            }
            if (LNCSPADD.COMM_DATA.YHS_AC.trim().length() > 0 
                && LNCSPADD.COMM_DATA.YHS_AMT > 0) {
                WS_ACCOUNT_AC = LNCSPADD.COMM_DATA.YHS_AC;
                WS_TX_AMT = LNCSPADD.COMM_DATA.YHS_AMT;
                WS_THR_AC = LNCSPADD.COMM_DATA.CONT_NO;
                WS_CRDB_FLG = 'D';
                B300_ROUTE_AND_ACCOUNT_PROC();
                if (pgmRtn) return;
            }
            B210_DRAWDOWN_PROCESS();
            if (pgmRtn) return;
        }
        for (WS_IDX = 1; WS_IDX <= 5; WS_IDX += 1) {
            if (LNCSPADD.COMM_DATA.FEE_DATA[WS_IDX-1].FEE_CODE.trim().length() > 0 
                && LNCSPADD.COMM_DATA.FEE_DATA[WS_IDX-1].FEE_AMT != 0) {
                WS_CNT += 1;
            }
        }
        if (WS_CNT > 0) {
            B230_FEE_CHARGING_PROC();
            if (pgmRtn) return;
        }
    }
    public void B210_DRAWDOWN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCUDRW);
        LNCUDRW.COMM_DATA.LN_AC = LNCSPADD.COMM_DATA.CONT_NO;
        LNCUDRW.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCUDRW.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCUDRW.COMM_DATA.SUF_NO = "0" + LNCUDRW.COMM_DATA.SUF_NO;
        LNCUDRW.COMM_DATA.CCY = LNCSPADD.COMM_DATA.CCY;
        LNCUDRW.COMM_DATA.ACM_EVENT = "ST";
        LNCUDRW.COMM_DATA.DRAW_AMT = LNCSPADD.COMM_DATA.CONT_AMT;
        LNCUDRW.COMM_DATA.VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNCUDRW.COMM_DATA.TRAN_AC = LNCSPADD.COMM_DATA.DRAW_AC;
        LNCUDRW.COMM_DATA.DRAW_AC_TYP = LNCSPADD.COMM_DATA.DRAW_ACT;
        LNCUDRW.COMM_DATA.DRAW_BK_TP = LNCSPADD.COMM_DATA.DW_BK_TP;
        for (WS_FEE_CNT = 1; WS_FEE_CNT <= 5; WS_FEE_CNT += 1) {
            LNCUDRW.COMM_DATA.TRU_FEE += LNCSPADD.COMM_DATA.FEE_DATA[WS_FEE_CNT-1].FEE_AMT;
        }
        LNCUDRW.COMM_DATA.TR_MMO = LNCSPADD.COMM_DATA.TR_MMO;
        if (LNCSPADD.COMM_DATA.YHS_AMT > 0 
            && LNCSPADD.COMM_DATA.YHS_AC.trim().length() > 0) {
            LNCUDRW.COMM_DATA.YHS_AMT = LNCSPADD.COMM_DATA.YHS_AMT;
            LNCUDRW.COMM_DATA.YHS_AC = LNCSPADD.COMM_DATA.YHS_AC;
        }
        LNCUDRW.COMM_DATA.REF_NO = WS_REF_NO;
    }
    public void B220_DRAWDOWN_DELETE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCUDRWR);
        LNCUDRWR.COMM_DATA.LN_AC = LNCSPADD.COMM_DATA.CONT_NO;
        LNCUDRWR.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCUDRWR.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCUDRWR.COMM_DATA.SUF_NO = "0" + LNCUDRWR.COMM_DATA.SUF_NO;
        LNCUDRWR.COMM_DATA.ACM_EVENT = "ST";
        LNCUDRWR.COMM_DATA.DRAW_AMT = LNCSPADD.COMM_DATA.CONT_AMT;
        LNCUDRWR.COMM_DATA.VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNCUDRWR.COMM_DATA.TRAN_AC = LNCSPADD.COMM_DATA.DRAW_AC;
        LNCUDRWR.COMM_DATA.TXN_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        LNCUDRWR.COMM_DATA.JRNNO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        S000_CALL_LNZUDRWR();
        if (pgmRtn) return;
    }
    public void B240_FTA_CHK_PROC() throws IOException,SQLException,Exception {
        WS_IDX = 3;
        for (WS_I = 1; WS_I <= 20; WS_I += 1) {
            if (WS_ACR_BBR[WS_I-1] != 0) {
                WS_IDX += 1;
            }
        }
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNCSPADD.COMM_DATA.CONT_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        T000_READUP_LNTICTL();
        if (pgmRtn) return;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        LNRICTL.CTL_STSW = LNRICTL.CTL_STSW.substring(0, 18 - 1) + "1" + LNRICTL.CTL_STSW.substring(18 + 1 - 1);
        T000_UPDATE_ICTL();
        if (pgmRtn) return;
    }
    public void B230_FEE_CHARGING_PROC() throws IOException,SQLException,Exception {
        if (LNCSPADD.COMM_DATA.FEE_AC.trim().length() > 0) {
            WS_INP_ACT = LNCSPADD.COMM_DATA.FEE_ACT;
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = LNCSPADD.COMM_DATA.FEE_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("IB")) {
                WS_SETL_AC_TYPE = K_IB_AC;
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                WS_SETL_AC_TYPE = K_DD_AC;
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                WS_SETL_AC_TYPE = K_DC_AC;
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                WS_SETL_AC_TYPE = K_INTERNAL;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_AC_TYP_NOT_MATCH, WS_MSGID);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_CHECK_AC_TYP);
            if (WS_INP_ACT.trim().length() == 0) {
                WS_INP_ACT = WS_SETL_AC_TYPE;
            } else {
                if (!WS_SETL_AC_TYPE.equalsIgnoreCase(WS_INP_ACT)) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ACT_NOT_MATCH, WS_MSGID);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            LNCSPADD.COMM_DATA.FEE_ACT = WS_INP_ACT;
            WS_BBR_I += 1;
        }
        if (LNCSPADD.COMM_DATA.FEE_ACT.equalsIgnoreCase(K_DD_AC)) {
            B231_GET_AC_CCY_TYP();
            if (pgmRtn) return;
        }
        B232_SET_FEE_INFO();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCFFCON);
        BPCFFCON.FEE_INFO.FEE_CNT = WS_CNT;
        for (WS_CNT = 1; WS_CNT <= 5 
            && LNCSPADD.COMM_DATA.FEE_DATA[WS_CNT-1].FEE_CODE.trim().length() != 0; WS_CNT += 1) {
            IBS.init(SCCGWA, BPCOFBAS);
            BPCOFBAS.KEY.FEE_CODE = LNCSPADD.COMM_DATA.FEE_DATA[WS_CNT-1].FEE_CODE;
            BPCOFBAS.FUNC = 'I';
            S000_CALL_BPZFUBAS();
            if (pgmRtn) return;
            if (BPCOFBAS.VAL.FEE_AMOT_FLG == '1') {
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].AMO_FLG = 'Y';
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].AMO_STDT = LNCSPADD.COMM_DATA.START_DT;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].AMO_EDDT = LNCSPADD.COMM_DATA.MATU_DT;
            }
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_CODE = LNCSPADD.COMM_DATA.FEE_DATA[WS_CNT-1].FEE_CODE;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_BAS = LNCSPADD.COMM_DATA.FEE_DATA[WS_CNT-1].FEE_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_AMT = LNCSPADD.COMM_DATA.FEE_DATA[WS_CNT-1].FEE_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_FLG = '0';
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_AC_TY = BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_AC = BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_CCY = LNCSPADD.COMM_DATA.CCY;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_CCY = LNCSPADD.COMM_DATA.CCY;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_BAS = LNCSPADD.COMM_DATA.FEE_DATA[WS_CNT-1].FEE_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_AMT = LNCSPADD.COMM_DATA.FEE_DATA[WS_CNT-1].FEE_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].ADJ_AMT = LNCSPADD.COMM_DATA.FEE_DATA[WS_CNT-1].FEE_AMT;
        }
        BPCFFCON.FEE_INFO.ACCOUNT_BR = LNCSPADD.COMM_DATA.BOOK_CRE;
        S000_CALL_BPZFFCON();
        if (pgmRtn) return;
    }
    public void B231_GET_AC_CCY_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIMCCY);
        DDCIMCCY.DATA[1-1].AC = LNCSPADD.COMM_DATA.FEE_AC;
        DDCIMCCY.DATA[1-1].CCY = LNCSPADD.COMM_DATA.CCY;
        S000_CALL_DDZIMCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIMCCY.DATA[1-1].CCY_TYPE);
    }
    public void B232_SET_FEE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (LNCSPADD.COMM_DATA.FEE_ACT.equalsIgnoreCase(K_DD_AC)) {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
            if (LNCSPADD.COMM_DATA.CCY.equalsIgnoreCase("156")) {
                BPCFFTXI.TX_DATA.CCY_TYPE = '1';
            } else {
                BPCFFTXI.TX_DATA.CCY_TYPE = '2';
            }
        } else if (LNCSPADD.COMM_DATA.FEE_ACT.equalsIgnoreCase(K_DC_AC)
            || LNCSPADD.COMM_DATA.FEE_ACT.equalsIgnoreCase(K_EB_AC)) {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
            BPCFFTXI.TX_DATA.CARD_PSBK_NO = LNCSPADD.COMM_DATA.FEE_AC;
        } else if (LNCSPADD.COMM_DATA.FEE_ACT.equalsIgnoreCase(K_IB_AC)) {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '2';
        } else if (LNCSPADD.COMM_DATA.FEE_ACT.equalsIgnoreCase(K_INTERNAL)) {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '3';
        } else {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
        }
        CEP.TRC(SCCGWA, LNCSPADD.COMM_DATA.FEE_AC);
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = LNCSPADD.COMM_DATA.FEE_AC;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC);
        BPCFFTXI.TX_DATA.CI_NO = LNCSPADD.COMM_DATA.CI_NO;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = LNCSPADD.COMM_DATA.CCY;
        BPCFFTXI.TX_DATA.REL_CTRT_NO = LNCSPADD.COMM_DATA.CONT_NO;
        BPCFFTXI.TX_DATA.SVR_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCFFTXI.TX_DATA.BSNS_NO = LNCSPADD.COMM_DATA.CONT_NO;
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
    }
    public void B300_ROUTE_AND_ACCOUNT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.DATA.AGR_NO = WS_ACCOUNT_AC;
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        WS_ACR_BBR[WS_BBR_I-1] = CICQACRI.O_DATA.O_OWNER_BK;
        WS_BBR_I += 1;
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_DETL);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            if (WS_CRDB_FLG == 'C') {
                B400_AI_IN_AC_C();
                if (pgmRtn) return;
            } else {
                B400_AI_IN_AC_D();
                if (pgmRtn) return;
            }
        } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")
            || CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            if (WS_CRDB_FLG == 'C') {
                B400_DD_AC_C();
                if (pgmRtn) return;
            } else {
                B400_DD_AC_D();
                if (pgmRtn) return;
            }
        } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("IB")) {
            if (WS_CRDB_FLG == 'C') {
                B400_IB_AC_C();
                if (pgmRtn) return;
            } else {
                B400_IB_AC_D();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, "2");
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_AC_TYP_NOT_MATCH, WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B400_AI_IN_AC_C() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = WS_ACCOUNT_AC;
        AICUUPIA.DATA.CCY = LNCSPADD.COMM_DATA.CCY;
        AICUUPIA.DATA.AMT = WS_TX_AMT;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void B400_IB_AC_C() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.ACT_CTR = LNCSPADD.COMM_DATA.BOOK_CRE;
        IBCPOSTA.NOSTRO_CD = WS_ACCOUNT_AC;
        IBCPOSTA.CCY = LNCSPADD.COMM_DATA.CCY;
        IBCPOSTA.AMT = WS_TX_AMT;
        IBCPOSTA.TXTYPE = "01";
        IBCPOSTA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.SIGN = 'C';
        IBCPOSTA.OUR_REF = WS_THR_AC;
        IBCPOSTA.THR_REF = WS_THR_AC;
        S000_CALL_IBZCRAC();
        if (pgmRtn) return;
    }
    public void B400_DD_AC_C() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.AC = WS_ACCOUNT_AC;
        DDCUCRAC.CCY = LNCSPADD.COMM_DATA.CCY;
        DDCUCRAC.TX_AMT = WS_TX_AMT;
        DDCUCRAC.OTHER_AC = LNCSPADD.COMM_DATA.PAPER_NO;
        DDCUCRAC.RLT_AC = WS_THR_AC;
        if (WS_GD_AC_FLG == 'Y') {
            DDCUCRAC.GD_WITHDR_FLG = 'Y';
        }
        DDCUCRAC.OTHER_CCY = LNCSPADD.COMM_DATA.CCY;
        DDCUCRAC.OTHER_AMT = WS_TX_AMT;
        DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.BANK_CR_FLG = 'Y';
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void B400_DD_AC_D() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.BANK_DR_FLG = 'Y';
        DDCUDRAC.TX_AMT = WS_TX_AMT;
        DDCUDRAC.AC = WS_ACCOUNT_AC;
        DDCUDRAC.CCY = LNCSPADD.COMM_DATA.CCY;
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUDRAC.OTHER_AC = LNCSPADD.COMM_DATA.PAPER_NO;
        DDCUDRAC.RLT_AC = WS_THR_AC;
        DDCUDRAC.OTHER_CCY = LNCSPADD.COMM_DATA.CCY;
        DDCUDRAC.OTHER_AMT = WS_TX_AMT;
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
    }
    public void B400_AI_IN_AC_D() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = WS_ACCOUNT_AC;
        AICUUPIA.DATA.CCY = LNCSPADD.COMM_DATA.CCY;
        AICUUPIA.DATA.AMT = WS_TX_AMT;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void B400_IB_AC_D() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.ACT_CTR = LNCSPADD.COMM_DATA.BOOK_CRE;
        IBCPOSTA.NOSTRO_CD = WS_ACCOUNT_AC;
        IBCPOSTA.CCY = LNCSPADD.COMM_DATA.CCY;
        IBCPOSTA.AMT = WS_TX_AMT;
        IBCPOSTA.TXTYPE = "01";
        IBCPOSTA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.OUR_REF = WS_THR_AC;
        IBCPOSTA.THR_REF = WS_THR_AC;
        S000_CALL_IBZDRAC();
        if (pgmRtn) return;
    }
    public void T000_READ_LNTFUND() throws IOException,SQLException,Exception {
        LNTFUND_RD = new DBParm();
        LNTFUND_RD.TableName = "LNTFUND";
        IBS.READ(SCCGWA, LNRFUND, LNTFUND_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUND_NOT_EXIST, WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUP_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        LNTICTL_RD.upd = true;
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ICTL_NOTFND, LNCSPADD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_ICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.REWRITE(SCCGWA, LNRICTL, LNTICTL_RD);
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIMCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-CCY", DDCIMCCY);
        if (DDCIMCCY.RC.RC_CODE != 0) {
            LNCSPADD.RC.RC_APP = DDCIMCCY.RC.RC_MMO;
            LNCSPADD.RC.RC_RTNCODE = DDCIMCCY.RC.RC_CODE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
        if (DDCIMMST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCIMMST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPADD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPADD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPADD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPADD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRSETL() throws IOException,SQLException,Exception {
        LNCRSETL.REC_PTR = LNRSETL;
        LNCRSETL.REC_LEN = 266;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTSETL", LNCRSETL);
        if (LNCRSETL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRSETL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPADD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSRATE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SEV-RATE-MAINT", LNCSRATE);
        if (LNCSRATE.RC.RC_RTNCODE != 0) {
            LNCSPADD.RC.RC_APP = LNCSRATE.RC.RC_APP;
            LNCSPADD.RC.RC_RTNCODE = LNCSRATE.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC, true);
        if (CICSACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZUDRW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-DRAWDOWN", LNCUDRW);
        if (LNCUDRW.RC.RC_RTNCODE != 0) {
            LNCSPADD.RC.RC_APP = LNCUDRW.RC.RC_APP;
            LNCSPADD.RC.RC_RTNCODE = LNCUDRW.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR, true);
        if (CICSACR.RC.RC_CODE != 0) {
            if (CICSACR.RC.RC_CODE != 8040) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZUDRWR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-REV-DRAW", LNCUDRWR);
        if (LNCUDRWR.RC.RC_RTNCODE != 0) {
            LNCSPADD.RC.RC_APP = LNCUDRWR.RC.RC_APP;
            LNCSPADD.RC.RC_RTNCODE = LNCUDRWR.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-TX-INFO", BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            LNCSPADD.RC.RC_APP = BPCFFTXI.RC.RC_MMO;
            LNCSPADD.RC.RC_RTNCODE = BPCFFTXI.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            LNCSPADD.RC.RC_APP = LNCICTLM.RC.RC_APP;
            LNCSPADD.RC.RC_RTNCODE = LNCICTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CNGL", BPCQCNGL);
        if (BPCQCNGL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCNGL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZMACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-ACR", CICMACR, true);
    }
    public void S000_CALL_BPZFUBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-U-MAINTAIN-FBAS", BPCOFBAS);
    }
    public void S000_CALL_LNZBALLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-BALL-MAINT", LNCBALLM);
        if (LNCBALLM.RC.RC_RTNCODE != 0) {
            LNCSPADD.RC.RC_APP = LNCBALLM.RC.RC_APP;
            LNCSPADD.RC.RC_RTNCODE = LNCBALLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFFCON() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CON-CHG-INFO", BPCFFCON);
        if (BPCFFCON.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFFCON.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPADD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCLOANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPADD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZSMPRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSMPRL", GDCSMPRL);
    }
    public void S000_CALL_LNZRFUND() throws IOException,SQLException,Exception {
        LNCRFUND.REC_PTR = LNRFUND;
        LNCRFUND.REC_LEN = 456;
        IBS.CALLCPN(SCCGWA, "LN-R-FUND-MAIN", LNCRFUND);
        if (LNCRFUND.RETURN_INFO != 'F') {
            IBS.CPY2CLS(SCCGWA, LNCRFUND.RC.RC_CODE+"", WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        CEP.TRC(SCCGWA, DCCPACTY.RC.RC_CODE);
        if (DCCPACTY.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPADD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPADD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSPADD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSCKPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-CHK-PROD", LNCSCKPD);
    }
    public void S000_CALL_BPZUCNGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MAINT-CNGM", BPCUCNGM);
        if (BPCUCNGM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCNGM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC, true);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZCRAC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC, true);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-DEBIT-AC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_MSGID);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSPADD.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSPADD=");
            CEP.TRC(SCCGWA, LNCSPADD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
