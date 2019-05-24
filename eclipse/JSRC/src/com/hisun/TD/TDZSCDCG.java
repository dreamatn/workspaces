package com.hisun.TD;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.AI.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZSCDCG {
    int JIBS_tmp_int;
    DBParm TDTCMST_RD;
    DBParm TDTINST_RD;
    DBParm TDTIREV_RD;
    DBParm TDTMISE_RD;
    DBParm TDTSMST_RD;
    String JIBS_tmp_str[] = new String[10];
    String K_AP_MMO = "TD";
    String K_OUTPUT_FMT = "TD590";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_SYS_ERR = "SC6001";
    char WS_MSREL_FLG = ' ';
    char WS_NOT_STANDARD_FLG = ' ';
    char WS_SMST_FLAG = ' ';
    char WS_GGRP_FLG = ' ';
    char WS_IREV_FLG = ' ';
    char WS_MISE_FLG = ' ';
    String WS_MSGID = " ";
    String WS_CI_NO = " ";
    short WS_RC = 0;
    double WS_TAX_AMT = 0;
    double WS_TXN_AMT = 0;
    int WS_NEXT_DT = 0;
    double WS_INT = 0;
    String WS_N_FRM_APP = " ";
    double WS_ACCRU_INT = 0;
    String WS_MMO = " ";
    short WS_COUNT1 = 0;
    TDZSCDCG_WS_TDRFHIS WS_TDRFHIS = new TDZSCDCG_WS_TDRFHIS();
    TDZSCDCG_WS_TDRSMST WS_TDRSMST = new TDZSCDCG_WS_TDRSMST();
    TDZSCDCG_WS_TDRIREV WS_TDRIREV = new TDZSCDCG_WS_TDRIREV();
    char WS_CR_FLG = ' ';
    char WS_GRP_FLG = ' ';
    double WS_BAL_XH = 0;
    String WS_OPP_CI_NO = " ";
    String WS_PROD_CD = " ";
    String WS_AC_TYPE = " ";
    String WS_AC_TYPE_2 = " ";
    short WS_RC_DISP = 0;
    char WS_CRE_AC_FLG = ' ';
    int WS_SMST_CNT = 0;
    double WS_SMST_AMT = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCUABOX BPCUABOX = new BPCUABOX();
    TDCACCRU TDCACCRU = new TDCACCRU();
    TDCACDRU TDCACDRU = new TDCACDRU();
    CICSACAC CICSACAC = new CICSACAC();
    CICCUST CICCUST = new CICCUST();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCSQIFA DDCSQIFA = new DDCSQIFA();
    SCCBINF SCCBINF = new SCCBINF();
    AICUUPIA AICUUPIA = new AICUUPIA();
    SCCFMT SCCFMT = new SCCFMT();
    TDCOTRAC TDCOTRAC = new TDCOTRAC();
    TDCBVCD TDCBVCD = new TDCBVCD();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    CICACCU CICACCU = new CICACCU();
    TDCOTZZC TDCOTZZC = new TDCOTZZC();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    DCCPACTY DCCPACTY = new DCCPACTY();
    TDCMACO TDCMACO = new TDCMACO();
    TDRSMST TDRSMST = new TDRSMST();
    TDRCMST TDRCMST = new TDRCMST();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    CICQACRL CICQACRL = new CICQACRL();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCCUPSWM DCCUPSWM = new DCCUPSWM();
    DDCIMPAY DDCIMPAY = new DDCIMPAY();
    TDCMACC TDCMACC = new TDCMACC();
    TDRIREV TDRIREV = new TDRIREV();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    CICQACAC CICQACAC = new CICQACAC();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    TDRFHIS TDRFHIS = new TDRFHIS();
    AICPQMIB AICPQMIB = new AICPQMIB();
    BPCPQORG BPCPQORG = new BPCPQORG();
    TDRMISE TDRMISE = new TDRMISE();
    TDRINST TDRINST = new TDRINST();
    TDCKHCR TDCKHCR = new TDCKHCR();
    TDCTDDR TDCTDDR = new TDCTDDR();
    TDCOCDCG TDCOCDCG = new TDCOCDCG();
    DDCSTRAC DDCSTRAC = new DDCSTRAC();
    CICQACRI CICQACRI = new CICQACRI();
    SCCGWA SCCGWA;
    TDCSCDCG TDCSCDCG;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, TDCSCDCG TDCSCDCG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCSCDCG = TDCSCDCG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZSCDCG return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        CEP.TRC(SCCGWA, TDCSCDCG.OLD_AC);
        CEP.TRC(SCCGWA, TDCSCDCG.OLD_SEQ);
        CEP.TRC(SCCGWA, TDCSCDCG.ODRAW_MTH);
        CEP.TRC(SCCGWA, TDCSCDCG.OPSW);
        CEP.TRC(SCCGWA, TDCSCDCG.OREL_AC);
        CEP.TRC(SCCGWA, TDCSCDCG.OID_TYP);
        CEP.TRC(SCCGWA, TDCSCDCG.OID_NO);
        CEP.TRC(SCCGWA, TDCSCDCG.OAC_NAME);
        CEP.TRC(SCCGWA, TDCSCDCG.TXN_AMT);
        CEP.TRC(SCCGWA, TDCSCDCG.HTXN_AMT);
        CEP.TRC(SCCGWA, TDCSCDCG.LTXN_AMT);
        CEP.TRC(SCCGWA, TDCSCDCG.PTXN_AMT);
        CEP.TRC(SCCGWA, TDCSCDCG.NCT_FLG);
        CEP.TRC(SCCGWA, TDCSCDCG.NOP_AC);
        CEP.TRC(SCCGWA, TDCSCDCG.NOPBV_TYP);
        CEP.TRC(SCCGWA, TDCSCDCG.NOPBV_NO);
        CEP.TRC(SCCGWA, TDCSCDCG.NEW_AC);
        CEP.TRC(SCCGWA, TDCSCDCG.NID_TYP);
        CEP.TRC(SCCGWA, TDCSCDCG.NID_NO);
        CEP.TRC(SCCGWA, TDCSCDCG.NCI_NO);
        CEP.TRC(SCCGWA, TDCSCDCG.NAC_NAME);
        CEP.TRC(SCCGWA, TDCSCDCG.NREL_AC_BV);
        CEP.TRC(SCCGWA, TDCSCDCG.NREL_AC);
        CEP.TRC(SCCGWA, TDCSCDCG.NDRAW_MTH);
        CEP.TRC(SCCGWA, TDCSCDCG.NPSW);
        CEP.TRC(SCCGWA, TDCSCDCG.TRK2_DAT);
        CEP.TRC(SCCGWA, TDCSCDCG.TRK3_DAT);
        CEP.TRC(SCCGWA, TDCSCDCG.CVV);
        CEP.TRC(SCCGWA, TDCSCDCG.HLP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_GET_CDINFO();
        B100_CHECK_INPUT();
        B200_TRAN_CDAC();
        B300_DDAC_PROC();
        B800_OUTPUT();
    }
    public void B010_GET_CDINFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = TDCSCDCG.OLD_AC;
        CICQACAC.DATA.AGR_SEQ = TDCSCDCG.OLD_SEQ;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, TDCSCDCG.NEW_AC);
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCSCDCG.NEW_AC;
        T000_READ_TDTCMST();
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_TDTSMST();
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDRIREV.KEY.STR_DATE = TDRSMST.VAL_DATE;
        T000_READ_TDTIREV();
        if (TDRSMST.INSTR_MTH != '0') {
            IBS.init(SCCGWA, TDRINST);
            TDRINST.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
            T000_READ_TDTINST();
        }
        IBS.init(SCCGWA, TDRMISE);
        TDRMISE.NAC_NO = TDCSCDCG.OLD_AC;
        TDRMISE.NAC_SEQ = TDCSCDCG.OLD_SEQ;
        T000_READ_TDTMISE();
        CEP.TRC(SCCGWA, TDRMISE.NAC_NO);
        CEP.TRC(SCCGWA, TDRMISE.NAC_SEQ);
        CEP.TRC(SCCGWA, TDRMISE.MSE_NUM);
        WS_TDRSMST.WS_PVAL_DATE = TDRSMST.PVAL_DATE;
        WS_TDRSMST.WS_LBAL_DATE = TDRSMST.LBAL_DATE;
        WS_TDRSMST.WS_SMST_STSW = TDRSMST.STSW;
        WS_TDRSMST.WS_SMST_PART_NUM = TDRSMST.PART_NUM;
        WS_TDRSMST.WS_SMST_PART_DATE = TDRSMST.PART_DATE;
        WS_TDRSMST.WS_SMST_PROC_NUM = TDRSMST.PROC_NUM;
        WS_TDRSMST.WS_SMST_SER_TIME = TDRSMST.SER_TIME;
        WS_TDRSMST.WS_SMST_BUD_INT = TDRSMST.BUD_INT;
        WS_TDRSMST.WS_SMST_BUD_DATE = TDRSMST.BUD_DATE;
        WS_TDRSMST.WS_SMST_DRW_INT = TDRSMST.DRW_INT;
        WS_TDRSMST.WS_SMST_DRW_TAX = TDRSMST.DRW_TAX;
        WS_TDRSMST.WS_SMST_DRW_BUD_INT = TDRSMST.DRW_BUD_INT;
        WS_TDRSMST.WS_SMST_DRW_BUD_DATE = TDRSMST.DRW_BUD_DATE;
        WS_TDRIREV.WS_STR_DATE = TDRIREV.KEY.STR_DATE;
        WS_TDRIREV.WS_END_DATE = TDRIREV.END_DATE;
        WS_TDRIREV.WS_RATE_SEL = TDRIREV.RATE_SEL;
        WS_TDRIREV.WS_ACTI_NO = TDRIREV.ACTI_NO;
        WS_TDRIREV.WS_DOCU_NO = TDRIREV.DOCU_NO;
        WS_TDRIREV.WS_INT_RUL_CD = TDRIREV.INT_RUL_CD;
        WS_TDRIREV.WS_BAL = TDRIREV.BAL;
        WS_TDRIREV.WS_CCY = TDRIREV.CCY;
        WS_TDRIREV.WS_TERM = TDRIREV.TERM;
        WS_TDRIREV.WS_LVL = TDRIREV.LVL;
        WS_TDRIREV.WS_GRPS_NO = TDRIREV.GRPS_NO;
        WS_TDRIREV.WS_ASS_LVL = TDRIREV.ASS_LVL;
        WS_TDRIREV.WS_BR = TDRIREV.BR;
        WS_TDRIREV.WS_CITY_CD = TDRIREV.CITY_CD;
        WS_TDRIREV.WS_CHNL_NO = TDRIREV.CHNL_NO;
        WS_TDRIREV.WS_OTH_FIL = TDRIREV.OTH_FIL;
        WS_TDRIREV.WS_PRD_RATE = TDRIREV.PRD_RATE;
        WS_TDRIREV.WS_RATE_TYPE = TDRIREV.RATE_TYPE;
        WS_TDRIREV.WS_TENOR = TDRIREV.TENOR;
        WS_TDRIREV.WS_PRD_SPRD = TDRIREV.PRD_SPRD;
        WS_TDRIREV.WS_INT_SEL = TDRIREV.INT_SEL;
        WS_TDRIREV.WS_SPRD_PNT = TDRIREV.SPRD_PNT;
        WS_TDRIREV.WS_SPRD_PCT = TDRIREV.SPRD_PCT;
        WS_TDRIREV.WS_DIS_SPRD = TDRIREV.DIS_SPRD;
        WS_TDRIREV.WS_CON_SPRD = TDRIREV.CON_SPRD;
        WS_TDRIREV.WS_CON_RATE = TDRIREV.CON_RATE;
        WS_TDRIREV.WS_FEE_RUL_CD = TDRIREV.FEE_RUL_CD;
        WS_TDRIREV.WS_MIN_INT_RATE = TDRIREV.MIN_INT_RATE;
        WS_TDRIREV.WS_MAX_INT_RATE = TDRIREV.MAX_INT_RATE;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCSCDCG.NREL_AC;
        S000_CALL_CIZACCU();
        WS_N_FRM_APP = CICACCU.DATA.FRM_APP;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_TMP_HLD);
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ALREADY_HLD);
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_IMPAWNED);
        }
        if (TDRSMST.HBAL > 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AMT_HOLD);
        }
        if (TDRSMST.ACO_STS != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED);
        }
        CEP.TRC(SCCGWA, TDCSCDCG.TXN_AMT);
        CEP.TRC(SCCGWA, TDCSCDCG.LTXN_AMT);
        CEP.TRC(SCCGWA, TDCSCDCG.HTXN_AMT);
        if (TDCSCDCG.TXN_AMT < TDCSCDCG.LTXN_AMT 
            || TDCSCDCG.TXN_AMT > TDCSCDCG.HTXN_AMT) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BAL_MUST_INPUT);
        }
        if (TDRMISE.MSE_NUM >= 1) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CDAC_ERR);
        }
        if (TDRSMST.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_EDT_ERR);
        }
    }
    public void B200_TRAN_CDAC() throws IOException,SQLException,Exception {
        B200_CALL_TD_DR_UNT();
        B200_CALL_TD_CR_UNT();
        B200_UPD_CDAC_INF();
        B270_WRT_FHIS();
    }
    public void B200_CALL_TD_DR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCTDDR);
        TDCTDDR.OPT = '1';
        TDCTDDR.BV_CD = "088";
        TDCTDDR.BV_TYP = '8';
        TDCTDDR.CUS_AC = TDCSCDCG.OLD_AC;
        TDCTDDR.AC_SEQ = TDCSCDCG.OLD_SEQ;
        TDCTDDR.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDCTDDR.NAME = TDCSCDCG.OAC_NAME;
        TDCTDDR.CCY = "156";
        TDCTDDR.CCY_TYP = '1';
        TDCTDDR.BAL = TDRSMST.PBAL;
        TDCTDDR.PVAL_DT = TDRSMST.PVAL_DATE;
        TDCTDDR.VAL_DT = TDRSMST.VAL_DATE;
        TDCTDDR.EXP_DT = TDRSMST.EXP_DATE;
        TDCTDDR.PROC_FLG = '1';
        TDCTDDR.TXN_AMT = TDRSMST.BAL;
        TDCTDDR.DRAW_MTH = TDCSCDCG.ODRAW_MTH;
        TDCTDDR.PSW = TDCSCDCG.OPSW;
        TDCTDDR.ID_TYP = TDCSCDCG.OID_TYP;
        TDCTDDR.ID_NO = TDCSCDCG.OID_NO;
        TDCTDDR.CT_FLG = '2';
        TDCTDDR.OPP_AC = TDCSCDCG.OREL_AC;
        if (TDCSCDCG.OREL_AC.trim().length() == 0) {
            TDCTDDR.OPP_AC = TDCSCDCG.NOP_AC;
        }
        TDCTDDR.OPPBVNO = TDCSCDCG.NOPBV_NO;
        TDCTDDR.OPP_NAME = TDCSCDCG.OAC_NAME;
        if (TDCTDDR.STSW == null) TDCTDDR.STSW = "";
        JIBS_tmp_int = TDCTDDR.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDCTDDR.STSW += " ";
        TDCTDDR.STSW = TDCTDDR.STSW.substring(0, 8 - 1) + "1" + TDCTDDR.STSW.substring(8 + 1 - 1);
        S000_CALL_TDZTDDR();
    }
    public void B200_CALL_TD_CR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCKHCR);
        if (WS_N_FRM_APP.equalsIgnoreCase("DC")) {
            TDCKHCR.AC = TDCSCDCG.NREL_AC;
        }
        TDCKHCR.PROD_CD = TDRSMST.PROD_CD;
        TDCKHCR.MPROD_CD = TDRCMST.PROD_CD;
        TDCKHCR.BV_CD = "088";
        TDCKHCR.BV_TYP = '8';
        TDCKHCR.ID_TYP = TDCSCDCG.NID_TYP;
        TDCKHCR.ID_NO = TDCSCDCG.NID_NO;
        TDCKHCR.CI_NO = TDCSCDCG.NCI_NO;
        TDCKHCR.NAME = TDCSCDCG.NAC_NAME;
        TDCKHCR.CCY = "156";
        TDCKHCR.CCY_TYP = '1';
        TDCKHCR.TXN_AMT = TDRSMST.BAL;
        TDCKHCR.TERM = TDRSMST.TERM;
        TDCKHCR.INSTR_MTH = TDRSMST.INSTR_MTH;
        TDCKHCR.DRAW_MTH = TDCSCDCG.ODRAW_MTH;
        TDCKHCR.PSW = TDCSCDCG.OPSW;
        TDCKHCR.CROS_CR_FLG = TDRCMST.CROS_DR_FLG;
        TDCKHCR.CROS_DR_FLG = TDRCMST.CROS_DR_FLG;
        TDCKHCR.VAL_DT = TDRSMST.VAL_DATE;
        TDCKHCR.CT_FLG = '2';
        TDCKHCR.OPP_AC = TDCSCDCG.OREL_AC;
        if (TDCSCDCG.OREL_AC.trim().length() == 0) {
            TDCKHCR.OPP_AC = TDCSCDCG.NOP_AC;
        }
        TDCKHCR.OPP_BV = TDCSCDCG.NOPBV_TYP;
        TDCKHCR.OPP_BV_NO = TDCSCDCG.NOPBV_NO;
        TDCKHCR.OPP_NAME = TDCSCDCG.OAC_NAME;
        TDCKHCR.OPP_DRAW_MTH = TDCSCDCG.ODRAW_MTH;
        TDCKHCR.OPP_PSW = TDCSCDCG.OPSW;
        TDCKHCR.OPP_ID_TYP = TDCSCDCG.OID_TYP;
        TDCKHCR.OPP_ID_NO = TDCSCDCG.OID_NO;
        TDCKHCR.AC = TDCSCDCG.NEW_AC;
        TDCKHCR.CALR_STD = TDRSMST.CALR_STD;
        TDCKHCR.ZC_NUM = TDRINST.SER_TIME;
        TDCKHCR.INT_AC = TDCSCDCG.NREL_AC;
        TDCKHCR.STL_INT_AC = TDCSCDCG.NREL_AC;
        TDCKHCR.EXP_DT = TDRSMST.EXP_DATE;
        TDCKHCR.AC_NAME = TDCSCDCG.NAC_NAME;
        TDCKHCR.ACTI_NO = TDRSMST.ACTI_NO;
        TDCKHCR.RATE_TYP = TDRIREV.RATE_TYPE;
        TDCKHCR.TENOR = TDRIREV.TERM;
        TDCKHCR.CD_AC = TDCSCDCG.NREL_AC;
        TDCKHCR.CDS_DT = TDRSMST.VAL_DATE;
        S000_CALL_TDZKHCR();
    }
    public void B200_UPD_CDAC_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCKHCR.GACO_AC);
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = TDCKHCR.GACO_AC;
        T000_READU_TDTSMST();
        TDRSMST.PVAL_DATE = WS_TDRSMST.WS_PVAL_DATE;
        TDRSMST.LBAL_DATE = WS_TDRSMST.WS_LBAL_DATE;
        TDRSMST.STSW = WS_TDRSMST.WS_SMST_STSW;
        TDRSMST.PART_NUM = WS_TDRSMST.WS_SMST_PART_NUM;
        TDRSMST.PART_DATE = WS_TDRSMST.WS_SMST_PART_DATE;
        TDRSMST.PROC_NUM = WS_TDRSMST.WS_SMST_PROC_NUM;
        TDRSMST.SER_TIME = WS_TDRSMST.WS_SMST_SER_TIME;
        TDRSMST.DRW_INT = WS_TDRSMST.WS_SMST_DRW_INT;
        TDRSMST.DRW_TAX = WS_TDRSMST.WS_SMST_DRW_TAX;
        TDRSMST.DRW_BUD_INT = WS_TDRSMST.WS_SMST_DRW_BUD_INT;
        TDRSMST.DRW_BUD_DATE = WS_TDRSMST.WS_SMST_DRW_BUD_DATE;
        TDRSMST.OPEN_DR_AC = TDCSCDCG.NREL_AC;
        CEP.TRC(SCCGWA, TDRSMST.STSW);
        CEP.TRC(SCCGWA, TDRSMST.PART_NUM);
        CEP.TRC(SCCGWA, TDRSMST.PART_DATE);
        CEP.TRC(SCCGWA, TDRSMST.PROC_NUM);
        CEP.TRC(SCCGWA, TDRSMST.SER_TIME);
        CEP.TRC(SCCGWA, TDRSMST.BUD_INT);
        CEP.TRC(SCCGWA, TDRSMST.BUD_DATE);
        CEP.TRC(SCCGWA, TDRSMST.DRW_INT);
        CEP.TRC(SCCGWA, TDRSMST.DRW_TAX);
        CEP.TRC(SCCGWA, TDRSMST.DRW_BUD_INT);
        CEP.TRC(SCCGWA, TDRSMST.DRW_BUD_DATE);
        T000_REWRITE_TDTSMST();
        IBS.init(SCCGWA, TDRMISE);
        TDRMISE.KEY.OAC_NO = TDCSCDCG.OLD_AC;
        TDRMISE.OAC_SEQ = TDCSCDCG.OLD_SEQ;
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = TDCSCDCG.OLD_AC;
        S000_CALL_CIZQACRI();
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_NO);
        TDRMISE.KEY.OCI_NO = CICQACRI.O_DATA.O_CI_NO;
        TDRMISE.OAC_AC = TDCSCDCG.OREL_AC;
        if (TDCSCDCG.OREL_AC.trim().length() == 0 
            && TDRSMST.INSTR_MTH != '0') {
            TDRMISE.OAC_AC = TDCSCDCG.NOP_AC;
        }
        TDRMISE.MSE_NUM = 1;
        TDRMISE.NAC_NO = TDCSCDCG.NEW_AC;
        CEP.TRC(SCCGWA, TDRSMST.AC_NO);
        if (TDCSCDCG.NEW_AC.trim().length() == 0) {
            TDRMISE.NAC_NO = TDRSMST.AC_NO;
        }
        TDRMISE.NAC_SEQ = TDCKHCR.AC_SEQ;
        TDRMISE.NCI_NO = TDCSCDCG.NCI_NO;
        TDRMISE.NAC_AC = TDCSCDCG.NREL_AC;
        TDRMISE.MSE_DT = SCCGWA.COMM_AREA.AC_DATE;
        TDRMISE.MSE_BAL = TDCSCDCG.TXN_AMT;
        TDRMISE.SUG_BAL = TDCSCDCG.PTXN_AMT;
        TDRMISE.BAL = TDCSCDCG.LTXN_AMT;
        TDRMISE.INT_BAL = TDCSCDCG.HTXN_AMT;
        TDRMISE.PAY_AC = TDCSCDCG.NREL_AC;
        TDRMISE.REV_AC = TDCSCDCG.OREL_AC;
        if (TDCSCDCG.OREL_AC.trim().length() == 0) {
            TDRMISE.REV_AC = TDCSCDCG.NOP_AC;
        }
        TDRMISE.OPE_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRMISE.OPE_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRMISE.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRMISE.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRMISE.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRMISE.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        T000_WRITE_TDTMISE();
        IBS.init(SCCGWA, TDRIREV);
        CEP.TRC(SCCGWA, TDCKHCR.GACO_AC);
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        CEP.TRC(SCCGWA, TDRSMST.VAL_DATE);
        TDRIREV.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDRIREV.KEY.STR_DATE = TDRSMST.VAL_DATE;
        T000_READU_TDTIREV();
        TDRIREV.KEY.STR_DATE = WS_TDRIREV.WS_STR_DATE;
        TDRIREV.END_DATE = WS_TDRIREV.WS_END_DATE;
        TDRIREV.RATE_SEL = WS_TDRIREV.WS_RATE_SEL;
        TDRIREV.ACTI_NO = WS_TDRIREV.WS_ACTI_NO;
        TDRIREV.DOCU_NO = WS_TDRIREV.WS_DOCU_NO;
        TDRIREV.INT_RUL_CD = WS_TDRIREV.WS_INT_RUL_CD;
        TDRIREV.BAL = WS_TDRIREV.WS_BAL;
        TDRIREV.CCY = WS_TDRIREV.WS_CCY;
        TDRIREV.TERM = WS_TDRIREV.WS_TERM;
        TDRIREV.LVL = WS_TDRIREV.WS_LVL;
        TDRIREV.GRPS_NO = WS_TDRIREV.WS_GRPS_NO;
        TDRIREV.ASS_LVL = WS_TDRIREV.WS_ASS_LVL;
        TDRIREV.BR = WS_TDRIREV.WS_BR;
        TDRIREV.CITY_CD = WS_TDRIREV.WS_CITY_CD;
        TDRIREV.CHNL_NO = WS_TDRIREV.WS_CHNL_NO;
        TDRIREV.OTH_FIL = WS_TDRIREV.WS_OTH_FIL;
        TDRIREV.PRD_RATE = WS_TDRIREV.WS_PRD_RATE;
        TDRIREV.RATE_TYPE = WS_TDRIREV.WS_RATE_TYPE;
        TDRIREV.TENOR = WS_TDRIREV.WS_TENOR;
        TDRIREV.PRD_SPRD = WS_TDRIREV.WS_PRD_SPRD;
        TDRIREV.INT_SEL = WS_TDRIREV.WS_INT_SEL;
        TDRIREV.SPRD_PNT = WS_TDRIREV.WS_SPRD_PNT;
        TDRIREV.SPRD_PCT = WS_TDRIREV.WS_SPRD_PCT;
        TDRIREV.DIS_SPRD = WS_TDRIREV.WS_DIS_SPRD;
        TDRIREV.CON_SPRD = WS_TDRIREV.WS_CON_SPRD;
        TDRIREV.CON_RATE = WS_TDRIREV.WS_CON_RATE;
        TDRIREV.FEE_RUL_CD = WS_TDRIREV.WS_FEE_RUL_CD;
        TDRIREV.MIN_INT_RATE = WS_TDRIREV.WS_MIN_INT_RATE;
        TDRIREV.MAX_INT_RATE = WS_TDRIREV.WS_MAX_INT_RATE;
        T000_REWRITE_TDTIREV();
    }
    public void B270_WRT_FHIS() throws IOException,SQLException,Exception {
    }
    public void B300_DDAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSTRAC);
        DDCSTRAC.FR_BV_AM = '1';
        DDCSTRAC.FR_BV_TYPE = TDCSCDCG.NREL_AC_BV;
        if (TDCSCDCG.NREL_AC_BV == '1') {
            DDCSTRAC.FR_CARD = TDCSCDCG.NREL_AC;
        } else {
            DDCSTRAC.FR_AC = TDCSCDCG.NREL_AC;
        }
        DDCSTRAC.FR_AC_CNAME = TDCSCDCG.NAC_NAME;
        DDCSTRAC.FR_CCY = "156";
        DDCSTRAC.FR_CCY_TYPE = '1';
        DDCSTRAC.FR_AMT = TDCSCDCG.TXN_AMT;
        DDCSTRAC.TO_BV_AM = '1';
        DDCSTRAC.TO_BV_TYPE = TDCSCDCG.NOPBV_TYP;
        if (TDCSCDCG.OREL_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = TDCSCDCG.OREL_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                DDCSTRAC.TO_CARD = TDCSCDCG.OREL_AC;
            } else {
                DDCSTRAC.TO_AC = TDCSCDCG.OREL_AC;
            }
        }
        if (TDCSCDCG.OREL_AC.trim().length() == 0) {
            if (TDCSCDCG.NOPBV_TYP == '1') {
                DDCSTRAC.TO_CARD = TDCSCDCG.NOP_AC;
            } else {
                DDCSTRAC.TO_AC = TDCSCDCG.NOP_AC;
            }
        }
        DDCSTRAC.TO_CCY = "156";
        DDCSTRAC.TO_CCY_TYPE = '1';
        DDCSTRAC.TO_AC_CNAME = TDCSCDCG.OAC_NAME;
        DDCSTRAC.TX_RMK = "The CDS transfer";
        DDCSTRAC.REMARKS = "The CDS transfer";
        DDCSTRAC.PAY_TYPE = TDCSCDCG.NDRAW_MTH;
        DDCSTRAC.PSWD = TDCSCDCG.NPSW;
        DDCSTRAC.PAY_PSWD = TDCSCDCG.NPSW;
        DDCSTRAC.CHK_PSW = 'P';
        CEP.TRC(SCCGWA, DDCSTRAC.FR_BV_AM);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_BV_TYPE);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_CARD);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_AC);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_PSBK);
        CEP.TRC(SCCGWA, DDCSTRAC.CHQ_TYPE);
        CEP.TRC(SCCGWA, DDCSTRAC.CHQ_NO);
        CEP.TRC(SCCGWA, DDCSTRAC.CHQ_ISS_DATE);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_AC_CNAME);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_AC_ENAME);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_CCY);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_AMT);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_BV_AM);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_BV_TYPE);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_CARD);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_AC);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_CREV_NO);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_CREV_NO);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_CCY);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_AC_CNAME);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_AC_ENAME);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_AMT);
        CEP.TRC(SCCGWA, DDCSTRAC.EX_RATE);
        CEP.TRC(SCCGWA, DDCSTRAC.TICK_NO);
        CEP.TRC(SCCGWA, DDCSTRAC.EX_TIME);
        CEP.TRC(SCCGWA, DDCSTRAC.VAL_DATE);
        CEP.TRC(SCCGWA, DDCSTRAC.TX_RMK);
        CEP.TRC(SCCGWA, DDCSTRAC.REMARKS);
        CEP.TRC(SCCGWA, DDCSTRAC.PAY_TYPE);
        CEP.TRC(SCCGWA, DDCSTRAC.PSWD);
        CEP.TRC(SCCGWA, DDCSTRAC.PAY_PSWD);
        CEP.TRC(SCCGWA, DDCSTRAC.CHK_PSW);
        CEP.TRC(SCCGWA, DDCSTRAC.TRK2_DAT);
        CEP.TRC(SCCGWA, DDCSTRAC.TRK3_DAT);
        S000_CALL_DDZSTRAC();
    }
    public void B200_CALL_CSH_DR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUABOX);
        BPCUABOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUABOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUABOX.CCY = TDCKHCR.CCY;
        BPCUABOX.CCY_TYP = TDCKHCR.CCY_TYP;
        BPCUABOX.AMT = TDCKHCR.TXN_AMT;
        BPCUABOX.OPP_AC = TDCKHCR.AC;
        BPCUABOX.OPP_ACNM = TDCKHCR.NAME;
        BPCUABOX.CI_NO = TDCKHCR.CI_NO;
        BPCUABOX.ID_TYP = TDCKHCR.ID_TYP;
        BPCUABOX.IDNO = TDCKHCR.ID_NO;
        BPCUABOX.CASH_NO = "115";
        BPCUABOX.AGT_IDTYP = TDCKHCR.ATTY_ID_TYP;
        BPCUABOX.AGT_IDNO = TDCKHCR.ATTY_ID_NO;
        BPCUABOX.AGT_NAME = TDCKHCR.ATTY_NAME;
        S000_CALL_BPZUABOX();
    }
    public void B210_CALL_AI_DR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = TDCKHCR.OPP_AC;
        AICUUPIA.DATA.RVS_NO = TDCKHCR.OPP_REV_NO;
        AICUUPIA.DATA.RVS_SEQ = 0;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            AICUUPIA.DATA.VALUE_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        }
        AICUUPIA.DATA.AMT = TDCKHCR.TXN_AMT;
        AICUUPIA.DATA.CCY = TDCKHCR.CCY;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.POST_NARR = " ";
        AICUUPIA.DATA.THEIR_AC = TDCKHCR.AC;
        AICUUPIA.DATA.PAY_MAN = TDCKHCR.AC_NAME;
        AICUUPIA.DATA.PAY_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        S000_CALL_AIZUUPIA();
    }
    public void B220_CALL_DC_DR_UNT() throws IOException,SQLException,Exception {
    }
    public void B230_CALL_DD_DR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        CEP.TRC(SCCGWA, TDCKHCR.TXN_AMT);
        DDCUDRAC.AC = TDCKHCR.OPP_AC;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCKHCR.OPP_AC;
        S000_CALL_CIZACCU();
        if (CICACCU.DATA.FRM_APP.equalsIgnoreCase("DC")) {
            DDCUDRAC.CARD_NO = TDCKHCR.OPP_AC;
        }
        CEP.TRC(SCCGWA, DDCUDRAC.CARD_NO);
        CEP.TRC(SCCGWA, DDCUDRAC.AC);
        DDCUDRAC.PSWD = TDCKHCR.OPP_PSW;
        DDCUDRAC.PAY_TYPE = TDCKHCR.OPP_DRAW_MTH;
        DDCUDRAC.ID_TYPE = TDCKHCR.OPP_ID_TYP;
        DDCUDRAC.ID_NO = TDCKHCR.OPP_ID_NO;
        DDCUDRAC.PSBK_NO = TDCKHCR.OPP_BV_NO;
        DDCUDRAC.CCY = TDCKHCR.CCY;
        DDCUDRAC.CCY_TYPE = TDCKHCR.CCY_TYP;
        DDCUDRAC.TX_AMT = TDCKHCR.TXN_AMT;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            DDCUDRAC.VAL_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        }
        DDCUDRAC.CHQ_TYPE = TDCKHCR.CHQ_TYPE;
        DDCUDRAC.CHQ_NO = TDCKHCR.CHQ_NO;
        DDCUDRAC.PAY_PSWD = TDCKHCR.CHQ_PSW;
        CEP.TRC(SCCGWA, TDCKHCR.AC);
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCKHCR.AC;
        S000_CALL_CIZACCU();
        if (CICACCU.DATA.FRM_APP.equalsIgnoreCase("DC")) {
            DDCUDRAC.OTH_TX_TOOL = TDCKHCR.AC;
        }
        if (TDCKHCR.CT_FLG == '0') {
            DDCUDRAC.TX_TYPE = 'C';
        } else {
            DDCUDRAC.TX_TYPE = 'T';
        }
        if (TDCKHCR.CT_FLG == '2') {
            DDCUDRAC.CHK_PSW = 'P';
        }
        CEP.TRC(SCCGWA, TDCKHCR.ATTY_ID_TYP);
        DDCUDRAC.BV_TYP = TDCKHCR.OPP_BV;
        if (TDCKHCR.MMO.trim().length() == 0) {
            DDCUDRAC.TX_MMO = "A005";
        } else {
            DDCUDRAC.TX_MMO = TDCKHCR.MMO;
        }
        CEP.TRC(SCCGWA, DDCUDRAC.TX_MMO);
        if (TDCACCRU.OPT == '3' 
            || TDCKHCR.OPT == '4') {
            DDCUDRAC.CHK_PSW_FLG = 'N';
            DDCUDRAC.PCHQ_FLG = 'Y';
            CEP.TRC(SCCGWA, DDCUDRAC.PCHQ_FLG);
        }
        CEP.TRC(SCCGWA, DDCUDRAC.PCHQ_FLG);
        CEP.TRC(SCCGWA, TDCKHCR.PAY_PSW);
        CEP.TRC(SCCGWA, DDCUDRAC.CHK_PSW);
        DDCUDRAC.BANK_DR_FLG = 'N';
        DDCUDRAC.CHQ_ISS_DATE = TDCKHCR.CHQ_DATE;
        DDCUDRAC.OTHER_AC_NM = TDCKHCR.AC_NAME;
        DDCUDRAC.OTHER_CCY = TDCKHCR.CCY;
        DDCUDRAC.OTHER_AC = TDCKHCR.AC;
        DDCUDRAC.OTHER_AMT = TDCKHCR.TXN_AMT;
        if (TDCKHCR.AUTO_CR == 'Y') {
            DDCUDRAC.SIGN_FLG = 'Y';
        }
        if (TDCKHCR.SHOW == '1') {
            if (!WS_AC_TYPE.equalsIgnoreCase("043") 
                && !WS_AC_TYPE.equalsIgnoreCase("044")) {
                DDCUDRAC.HIS_SHOW_FLG = 'N';
            }
        }
        DDCUDRAC.SUPPLY_FLG = 'N';
        CEP.TRC(SCCGWA, WS_AC_TYPE);
        if (WS_AC_TYPE.equalsIgnoreCase("043") 
            || WS_AC_TYPE.equalsIgnoreCase("044")) {
            DDCUDRAC.CHK_LMT_FLG = '4';
        }
        CEP.TRC(SCCGWA, TDCKHCR.OPP_AC);
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCKHCR.OPP_AC;
        S000_CALL_CIZACCU();
        CEP.TRC(SCCGWA, TDCKHCR.AC);
        CEP.TRC(SCCGWA, CICACCU.DATA.FRM_APP);
        if (CICACCU.DATA.FRM_APP.equalsIgnoreCase("DC")) {
            DDCUDRAC.CHK_LMT_FLG = '4';
        }
        S000_CALL_DDZUDRAC();
        CEP.TRC(SCCGWA, "YSY111");
        CEP.TRC(SCCGWA, DDCUDRAC.AC);
        TDCKHCR.DD_SUB_AC = DDCUDRAC.AC;
    }
    public void B240_OUTPUT_PROC() throws IOException,SQLException,Exception {
    }
    public void B800_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOCDCG);
        TDCOCDCG.OLD_AC = TDCSCDCG.OLD_AC;
        TDCOCDCG.OLD_SEQ = TDCSCDCG.OLD_SEQ;
        TDCOCDCG.NEW_AC = TDRSMST.AC_NO;
        TDCOCDCG.NEW_SEQ = TDCKHCR.AC_SEQ;
        TDCOCDCG.ACTI_NO = TDRSMST.ACTI_NO;
        TDCOCDCG.TXN_AMT = TDCSCDCG.TXN_AMT;
        TDCOCDCG.NEW_REL_AC = TDCSCDCG.NREL_AC;
        TDCOCDCG.DRAW_MTH = TDCSCDCG.ODRAW_MTH;
        TDCOCDCG.VAL_DT = TDRSMST.VAL_DATE;
        TDCOCDCG.EXP_DT = TDRSMST.EXP_DATE;
        TDCOCDCG.BAL = TDRSMST.BAL;
        TDCOCDCG.PART_NUM = TDRSMST.PART_NUM;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = TDCOCDCG;
        SCCFMT.DATA_LEN = 182;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GET_SALE_PROD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = WS_PROD_CD;
        S000_CALL_BPZPQPRD();
    }
    public void R000_GET_AC_NM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCKHCR.DD_SUB_AC;
        S000_CALL_CIZACCU();
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
    }
    public void R000_GET_CI_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACCRU.AC_NO);
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCACCRU.AC_NO;
        CICACCU.DATA.ENTY_TYP = '1';
        S000_CALL_CIZACCU();
        WS_CI_NO = CICACCU.DATA.CI_NO;
        CEP.TRC(SCCGWA, WS_CI_NO);
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCACCRU.OPP_AC_CNO;
        CICACCU.DATA.ENTY_TYP = '1';
        S000_CALL_CIZACCU();
        WS_OPP_CI_NO = CICACCU.DATA.CI_NO;
    }
    public void R000_CHECK_DD_CARD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CI_NO);
        CEP.TRC(SCCGWA, WS_OPP_CI_NO);
        IBS.init(SCCGWA, DCCPFTCK);
        DCCPFTCK.VAL.CARD_NO = TDCKHCR.OPP_CARD_NO;
        DCCPFTCK.VAL.REGN_TYP = '0';
        DCCPFTCK.VAL.TXN_TYPE = "04";
        if (WS_CI_NO.equalsIgnoreCase(WS_OPP_CI_NO)) {
            DCCPFTCK.VAL.SNAME_TRF_FLG = 'Y';
        } else {
            DCCPFTCK.VAL.DNAME_TRF_FLG = 'Y';
        }
        DCCPFTCK.VAL.TXN_CCY = TDCKHCR.CCY;
        DCCPFTCK.VAL.TXN_AMT = TDCKHCR.TXN_AMT;
        DCCPFTCK.TRK2_DAT = TDCKHCR.OPP_TRK2;
        DCCPFTCK.TRK3_DAT = TDCKHCR.OPP_TRK3;
        if (TDCKHCR.CT_FLG == '2') {
            S000_CALL_DCZPFTCK();
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
    }
    public void T000_READ_TDTINST() throws IOException,SQLException,Exception {
        TDTINST_RD = new DBParm();
        TDTINST_RD.TableName = "TDTINST";
        IBS.READ(SCCGWA, TDRINST, TDTINST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        IBS.READ(SCCGWA, TDRIREV, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_WRITE_TDTMISE() throws IOException,SQLException,Exception {
        TDTMISE_RD = new DBParm();
        TDTMISE_RD.TableName = "TDTMISE";
        IBS.WRITE(SCCGWA, TDRMISE, TDTMISE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READU_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READU_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.upd = true;
        IBS.READ(SCCGWA, TDRIREV, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        IBS.REWRITE(SCCGWA, TDRIREV, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_TDTMISE() throws IOException,SQLException,Exception {
        TDTMISE_RD = new DBParm();
        TDTMISE_RD.TableName = "TDTMISE";
        IBS.READ(SCCGWA, TDRMISE, TDTMISE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MISE_FLG = 'N';
        } else {
            WS_MISE_FLG = 'Y';
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZTDDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TD-DR", TDCTDDR);
    }
    public void S000_CALL_TDZKHCR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-KH-CR", TDCKHCR);
    }
    public void S000_CALL_DDZSTRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-TR-AC", DDCSTRAC);
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICSACAC.RC);
            CEP.ERR(SCCGWA, WS_MSGID);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_DCZPFTCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-FINANCE-TR-CHK", DCCPFTCK);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZACDRU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ACDR-UNT", TDCACDRU, true);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZACCRU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ACCR-UNT", TDCACCRU);
    }
    public void S000_CALL_TDZACCRU_NOFMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ACCR-UNT", TDCACCRU, true);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DCZPDRAW() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_BPZUABOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-ADD-CBOX", BPCUABOX);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void S000_CALL_TDZMACO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-MACO-CR", TDCMACO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
