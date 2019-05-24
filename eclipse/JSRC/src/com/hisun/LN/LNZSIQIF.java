package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.AI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSIQIF {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTBALZ_RD;
    DBParm LNTRATL_RD;
    DBParm LNTRELA_RD;
    DBParm LNTICTL_RD;
    DBParm LNTRCVD_RD;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 0;
    short WS_J = 0;
    int WS_CONT_OVER_TIME = 0;
    String WS_CONT_NO = " ";
    int WS_AC_DATE = 0;
    char WS_END_FLAG = ' ';
    char WS_INT_MODE_FLAG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    LNRRCVD LNRRCVD = new LNRRCVD();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNRCONT LNRCONT = new LNRCONT();
    LNRRATN LNRRATN = new LNRRATN();
    LNRRATL LNRRATL = new LNRRATL();
    LNRAPRD LNRAPRD = new LNRAPRD();
    LNRPAIP LNRPAIP = new LNRPAIP();
    LNRSETL LNRSETL = new LNRSETL();
    LNRRELA LNRRELA = new LNRRELA();
    LNRBALZ LNRBALZ = new LNRBALZ();
    LNCRRATN LNCRRATN = new LNCRRATN();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNCRLOAN LNCRLOAN = new LNCRLOAN();
    LNCRSETL LNCRSETL = new LNCRSETL();
    LNCRPAIP LNCRPAIP = new LNCRPAIP();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNCRRCVD LNCRRCVD = new LNCRRCVD();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    LNRICTL LNRICTL = new LNRICTL();
    LNCICSTS LNCICSTS = new LNCICSTS();
    LNCSCKPD LNCSCKPD = new LNCSCKPD();
    CICCUST CICCUST = new CICCUST();
    AICPQMIB AICPQMIB = new AICPQMIB();
    BPCCINTI BPCCINTI = new BPCCINTI();
    SCCGWA SCCGWA;
    LNCSIQIF LNCSIQIF;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, LNCSIQIF LNCSIQIF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSIQIF = LNCSIQIF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNZSIQIF return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQ_INF();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_INQ_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CICQACAC.DATA.ACAC_NO = LNCSIQIF.CONTRACT_NO;
        S000_CALL_CIZQACAC();
        LNCSIQIF.PAPER_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        LNCSIQIF.DRAW_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_BV_NO;
        LNCSIQIF.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        CEP.TRC(SCCGWA, LNCSIQIF.PROD_CD);
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        S000_CALL_CIZCUST();
        LNCSIQIF.CI_NAME = CICCUST.O_DATA.O_CI_NM;
        IBS.init(SCCGWA, LNCRCONT);
        IBS.init(SCCGWA, LNRCONT);
        LNCRCONT.FUNC = 'I';
        LNRCONT.KEY.CONTRACT_NO = LNCSIQIF.CONTRACT_NO;
        CEP.TRC(SCCGWA, LNCSIQIF.CONTRACT_NO);
        S000_CALL_LNZRCONT();
        LNCSIQIF.CCY = LNRCONT.CCY;
        LNCSIQIF.CONT_AMT = LNRCONT.ORIG_TOT_AMT;
        LNCSIQIF.START_DT = LNRCONT.START_DATE;
        LNCSIQIF.MATU_DT = LNRCONT.MAT_DATE;
        LNCSIQIF.REMARK = LNRCONT.REMARK;
        LNCSIQIF.CRT_DT = LNRCONT.CRT_DATE;
        LNCSIQIF.BOOK_BR = LNRCONT.BOOK_BR;
        LNCSIQIF.DOMI_BR = LNRCONT.DOMI_BR;
        LNCSIQIF.OVER_TIME = LNRCONT.OVER_TIME;
        LNCSIQIF.PROD_CD = LNRCONT.PROD_CD;
        IBS.init(SCCGWA, LNCSCKPD);
        LNCSCKPD.FUNC = '0';
        LNCSCKPD.PROD_CD = LNRCONT.PROD_CD;
        CEP.TRC(SCCGWA, LNRCONT.PROD_CD);
        S000_CALL_LNZSCKPD();
        LNCSIQIF.PROD_NM = LNCSCKPD.PROD_NM;
        IBS.init(SCCGWA, LNRBALZ);
        LNRBALZ.KEY.CONTRACT_NO = LNCSIQIF.CONTRACT_NO;
        LNRBALZ.KEY.SUB_CTA_NO = 0;
        T000_READ_LNTBALZ();
        LNCSIQIF.BAL = LNRBALZ.LOAN_BALL02 + LNRBALZ.LOAN_BALL06 + LNRBALZ.LOAN_BALL05;
        IBS.init(SCCGWA, LNRLOAN);
        IBS.init(SCCGWA, LNCRLOAN);
        LNCRLOAN.FUNC = 'I';
        LNRLOAN.KEY.CONTRACT_NO = LNCSIQIF.CONTRACT_NO;
        S000_CALL_LNZRLOAN();
        LNCSIQIF.ADVA_TYP = "" + LNRLOAN.CTL_TYPE;
        JIBS_tmp_int = LNCSIQIF.ADVA_TYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) LNCSIQIF.ADVA_TYP = "0" + LNCSIQIF.ADVA_TYP;
        LNCSIQIF.ACCLA_FUND = LNRLOAN.PD_PROJ_NO;
        LNCSIQIF.AUTO_AMT = LNRLOAN.AUTO_AMT;
        LNCSIQIF.CAL_FT_DT = LNRLOAN.FST_CAL_DT;
        if (LNRLOAN.AUTO_AMT > 0) {
            LNCSIQIF.AUTO_FLG = 'Y';
        }
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNCSIQIF.CONTRACT_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        T000_READ_LNTICTL();
        IBS.init(SCCGWA, LNRRATN);
        IBS.init(SCCGWA, LNCRRATN);
        LNCRRATN.FUNC = 'F';
        LNRRATN.KEY.ACTV_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNRRATN.KEY.CONTRACT_NO = LNCSIQIF.CONTRACT_NO;
        S000_CALL_LNZRRATN();
        LNCSIQIF.RAT_MTH = LNRRATN.RATE_FLG;
        LNCSIQIF.INT_FLPERD_UN = LNRRATN.FLT_PERD_UNIT;
        LNCSIQIF.INT_FLPERD = LNRRATN.FLT_PERD;
        LNCSIQIF.FLT_IPER = LNRRATN.FLT_GAP_PERD;
        LNCSIQIF.FLT_ADJF = LNRRATN.FLT_DTADJ_FLG;
        LNCSIQIF.FLOAT_FLG = LNRRATN.FIRST_FLT_FLG;
        LNCSIQIF.FLOAT_DAY = LNRRATN.FLT_DAY;
        LNCSIQIF.FLT_FIX_DAYS = LNRRATN.FST_FLT_DT;
        LNCSIQIF.FLOAT_MTH = LNRRATN.VARIATION_METHOD;
        LNCSIQIF.COMP_MTH = LNRRATN.COMPARISON_METHOD;
        LNCSIQIF.RATE_TYP = LNRRATN.INT_RATE_TYPE1;
        LNCSIQIF.RATE_PERD = LNRRATN.INT_RATE_CLAS1;
        LNCSIQIF.RATE_INT = LNRRATN.BASE_RATE1;
        LNCSIQIF.RATE_VAR = LNRRATN.RATE_VARIATION1;
        LNCSIQIF.RATE_PCT = LNRRATN.RATE_PECT1;
        IBS.init(SCCGWA, LNRRATL);
        LNRRATL.KEY.CONTRACT_NO = LNCSIQIF.CONTRACT_NO;
        LNRRATL.KEY.ACTV_DT = LNRICTL.CUR_PO_RAT_DT;
        CEP.TRC(SCCGWA, LNRRATL.KEY.ACTV_DT);
        CEP.TRC(SCCGWA, "20181113");
        LNRRATL.KEY.OVD_KND = 'O';
        T000_READ_LNTRATL();
        LNCSIQIF.PEN_RRAT = LNRRATL.INT_CHRG_MTH;
        LNCSIQIF.PEN_TYP = LNRRATL.VARIATION_METHOD;
        LNCSIQIF.PEN_RATT = LNRRATL.RATE_TYPE;
        LNCSIQIF.PEN_RATC = LNRRATL.RATE_CLAS;
        LNCSIQIF.PEN_SPR = LNRRATL.DIF_IRAT_PNT;
        CEP.TRC(SCCGWA, LNCSIQIF.PEN_SPR);
        LNCSIQIF.PEN_PCT = LNRRATL.DIF_IRAT_PER;
        IBS.init(SCCGWA, LNRRATL);
        LNRRATL.KEY.CONTRACT_NO = LNCSIQIF.CONTRACT_NO;
        LNRRATL.KEY.ACTV_DT = LNRICTL.CUR_IO_RAT_DT;
        LNRRATL.KEY.OVD_KND = 'L';
        T000_READ_LNTRATL();
        LNCSIQIF.CPND_RTY = LNRRATL.INT_CHRG_MTH;
        LNCSIQIF.CPND_TYP = LNRRATL.VARIATION_METHOD;
        LNCSIQIF.CPNDRATT = LNRRATL.RATE_TYPE;
        LNCSIQIF.CPNDRATC = LNRRATL.RATE_CLAS;
        LNCSIQIF.CPND_SPR = LNRRATL.DIF_IRAT_PNT;
        LNCSIQIF.CPND_PCT = LNRRATL.DIF_IRAT_PER;
        IBS.init(SCCGWA, LNRRATL);
        LNRRATL.KEY.CONTRACT_NO = LNCSIQIF.CONTRACT_NO;
        LNRRATL.KEY.ACTV_DT = LNRLOAN.PRAT_EFF_DT;
        LNRRATL.KEY.OVD_KND = 'P';
        T000_READ_LNTRATL();
        LNCSIQIF.ABUS_RAT = LNRRATL.INT_CHRG_MTH;
        LNCSIQIF.ABUSRATT = LNRRATL.VARIATION_METHOD;
        LNCSIQIF.ABUS_TYP = LNRRATL.RATE_TYPE;
        LNCSIQIF.ABUSRATC = LNRRATL.RATE_CLAS;
        LNCSIQIF.ABUSSPR = LNRRATL.DIF_IRAT_PNT;
        LNCSIQIF.ABUSPCT = LNRRATL.DIF_IRAT_PER;
        LNCSIQIF.ABUSIRAT = LNRRATL.EFF_RAT;
        IBS.init(SCCGWA, LNRAPRD);
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCSIQIF.CONTRACT_NO;
        S000_CALL_LNZAPRDM();
        LNCSIQIF.CPND_USE = LNCAPRDM.REC_DATA.ORAT_SAME;
        if (LNCAPRDM.REC_DATA.RCMP_INT == 'Y') {
            LNCSIQIF.INT_MTH = '1';
        }
        if (LNCAPRDM.REC_DATA.RCMP_INT == 'N') {
            LNCSIQIF.INT_MTH = '0';
        }
        LNCSIQIF.PAY_MTH = LNCAPRDM.REC_DATA.PAY_MTH;
        LNCSIQIF.INST_MTH = LNCAPRDM.REC_DATA.INST_MTH;
        LNCSIQIF.PAYI_PER = LNCAPRDM.REC_DATA.BCAL_PERD;
        LNCSIQIF.CAL_PERD_UNIT = LNCAPRDM.REC_DATA.CAL_PERD_UNIT;
        LNCSIQIF.CAL_PERD = LNCAPRDM.REC_DATA.CAL_PERD;
        LNCSIQIF.OLC_PERD = LNCAPRDM.REC_DATA.OCAL_PERD;
        LNCSIQIF.OLC_PERD_UNIT = LNCAPRDM.REC_DATA.OCAL_PERD_UNIT;
        LNCSIQIF.PAY_DAY_TYP = LNCAPRDM.REC_DATA.PAY_DD_TYPE;
        LNCSIQIF.PAY_DAY = LNCAPRDM.REC_DATA.PAY_DAY;
        LNCSIQIF.PAYP_PER = LNCAPRDM.REC_DATA.BPAYP_PERD;
        LNCSIQIF.PYP_PERD = LNCAPRDM.REC_DATA.PAYP_PERD;
        LNCSIQIF.PYP_PERD_UNIT = LNCAPRDM.REC_DATA.PAYP_PERD_UNIT;
        LNCSIQIF.W_I_FLG = LNCAPRDM.REC_DATA.W_I_FLG;
        LNCSIQIF.W_I_PERD_UNIT = LNCAPRDM.REC_DATA.W_I_PERD_UNIT;
        LNCSIQIF.W_I_PERD = LNCAPRDM.REC_DATA.W_I_PERD;
        CEP.TRC(SCCGWA, LNCSIQIF.W_I_FLG);
        CEP.TRC(SCCGWA, LNCSIQIF.W_I_PERD_UNIT);
        CEP.TRC(SCCGWA, LNCSIQIF.W_I_PERD);
        LNCSIQIF.GUAR_REF = LNCAPRDM.REC_DATA.GDA_APRE;
        LNCSIQIF.GUAR_DUAP = LNCAPRDM.REC_DATA.GDA_AUTO_DB;
        LNCSIQIF.GUAR_PAY_SEQ = LNCAPRDM.REC_DATA.GDA_DB_SEQ;
        LNCSIQIF.GRA_TYP = LNCAPRDM.REC_DATA.GRACE_TYP;
        LNCSIQIF.GRA_DAYS = LNCAPRDM.REC_DATA.PRIN_DOG;
        LNCSIQIF.GRATDUE_FLG = LNCAPRDM.REC_DATA.DOG_PSTD_FLG;
        LNCSIQIF.GRA_MTH = LNCAPRDM.REC_DATA.PRIN_DOG_MTH;
        LNCSIQIF.LGRA_MTH = LNCAPRDM.REC_DATA.INT_DOG_MTH;
        LNCSIQIF.PRE_CHRH = LNCAPRDM.REC_DATA.PREP_CHG_RATE;
        LNCSIQIF.ACCU_TYP = LNCAPRDM.REC_DATA.ACCRUAL_TYPE;
        LNCSIQIF.INT_BASE = LNCAPRDM.REC_DATA.INT_DBAS_STD;
        LNCSIQIF.CTAM_FRAT = LNCAPRDM.REC_DATA.ACCT_MGT_RATE;
        LNCSIQIF.HAND_CHR = LNCAPRDM.REC_DATA.HAND_CHG_RATE;
        LNCSIQIF.HAND_CHM = LNCAPRDM.REC_DATA.CHG_DB_FLG;
        LNCSIQIF.DUE_AUTO_FLG = LNCAPRDM.REC_DATA.DUE_AUTO_FLG;
        if (LNCAPRDM.REC_DATA.RCMP_INT == 'Y') {
            LNCSIQIF.INT_MTH = '1';
        } else {
            LNCSIQIF.INT_MTH = '0';
        }
        IBS.init(SCCGWA, BPCCINTI);
        BPCCINTI.BASE_INFO.BASE_TYP = LNCSIQIF.RATE_TYP;
        BPCCINTI.BASE_INFO.TENOR = LNCSIQIF.RATE_PERD;
        BPCCINTI.BASE_INFO.CCY = LNCSIQIF.CCY;
        BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCCINTI.FUNC = 'I';
        S000_CALL_BPZCINTI();
        LNCSIQIF.RATE_INT = BPCCINTI.BASE_INFO.RATE;
        IBS.init(SCCGWA, LNRPAIP);
        IBS.init(SCCGWA, LNCRPAIP);
        LNRPAIP.KEY.CONTRACT_NO = LNCSIQIF.CONTRACT_NO;
        LNRPAIP.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZRPAIP_STBR();
        S000_CALL_LNZRPAIP_RDNT();
        while (LNCRPAIP.RETURN_INFO != 'E' 
            && WS_I != 5) {
            WS_I = (short) (WS_I + 1);
            CEP.TRC(SCCGWA, WS_I);
            LNCSIQIF.PHS_INF[WS_I-1].PHS_TERM = LNRPAIP.KEY.PHS_NO;
            LNCSIQIF.PHS_INF[WS_I-1].PHS_PERD = LNRPAIP.PERD;
            LNCSIQIF.PHS_INF[WS_I-1].PHS_PERD_UN = LNRPAIP.PERD_UNIT;
            LNCSIQIF.PHS_INF[WS_I-1].PHS_AMT = LNRPAIP.PHS_INST_AMT;
            LNCSIQIF.PHS_INF[WS_I-1].PHS_INST = LNRPAIP.INST_MTH;
            S000_CALL_LNZRPAIP_RDNT();
        }
        S000_CALL_LNZRPAIP_EDBR();
        LNCSIQIF.PHS_NUM = WS_I;
        if (WS_I > 1 
            && LNCSIQIF.PHS_INF[WS_I-1].PHS_INST != ' ') {
            LNCSIQIF.PHP_FLG = 'Y';
        } else {
            LNCSIQIF.PHP_FLG = 'N';
        }
        if (LNCSIQIF.PHP_FLG == 'N') {
            IBS.init(SCCGWA, LNCSIQIF.MULT_INF[1-1]);
            IBS.init(SCCGWA, LNCSIQIF.MULT_INF[2-1]);
            IBS.init(SCCGWA, LNCSIQIF.MULT_INF[3-1]);
            IBS.init(SCCGWA, LNCSIQIF.MULT_INF[4-1]);
            IBS.init(SCCGWA, LNCSIQIF.MULT_INF[5-1]);
        }
        IBS.init(SCCGWA, LNRSETL);
        IBS.init(SCCGWA, LNCRSETL);
        IBS.init(SCCGWA, CICQACRI);
        LNCRSETL.FUNC = 'I';
        LNRSETL.KEY.CONTRACT_NO = LNCSIQIF.CONTRACT_NO;
        LNRSETL.KEY.CI_TYPE = 'B';
        LNRSETL.KEY.SETTLE_TYPE = '1';
        S000_CALL_LNZRSETL_READ();
        LNCSIQIF.PAY_AC_TYP = LNRSETL.AC_TYP;
        LNCSIQIF.PAY_AC = LNRSETL.AC;
        CICQACRI.DATA.AGR_NO = LNRSETL.AC;
        CICQACRI.FUNC = 'A';
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AC_CNM);
        if (LNRSETL.AC.trim().length() > 0) {
            S000_CALL_CIZQACRI();
        } else {
            LNCSIQIF.PAY_AC_TYP = " ";
        }
        LNCSIQIF.PAY_AC_NAME = CICQACRI.O_DATA.O_AC_CNM;
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            AICPQMIB.INPUT_DATA.AC = CICQACRI.DATA.AGR_NO;
            S000_CALL_AIZPQMIB();
            LNCSIQIF.PAY_AC_NAME = AICPQMIB.OUTPUT_DATA.CHS_NM;
        }
        CEP.TRC(SCCGWA, LNCSIQIF.PAY_AC_NAME);
        IBS.init(SCCGWA, LNRSETL);
        IBS.init(SCCGWA, LNCRSETL);
        IBS.init(SCCGWA, CICQACRI);
        LNRSETL.KEY.CONTRACT_NO = LNCSIQIF.CONTRACT_NO;
        LNRSETL.KEY.CI_TYPE = 'B';
        LNRSETL.KEY.SETTLE_TYPE = '2';
        S000_CALL_LNZRSETL_STBR();
        S000_CALL_LNZRSETL_RDNT();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRSETL.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase("LN1568") 
            && WS_J != 5) {
            WS_J = (short) (WS_J + 1);
            LNCSIQIF.MULT_PAY_TYP = LNRSETL.MWHD_AC_FLG;
            LNCSIQIF.MULT_INF[WS_J-1].MULT_AC = LNRSETL.AC;
            LNCSIQIF.MULT_INF[WS_J-1].MULT_STL_METH = LNRSETL.KEY.SETTLE_TYPE;
            LNCSIQIF.MULT_INF[WS_J-1].MULT_AC_TYP = LNRSETL.AC_TYP;
            LNCSIQIF.MULT_INF[WS_J-1].MULT_AC_FLG = LNRSETL.AC_FLG;
            LNCSIQIF.MULT_INF[WS_J-1].MULT_CCY = LNRSETL.KEY.CCY;
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = LNRSETL.AC;
            if (LNRSETL.AC.trim().length() > 0) {
                S000_CALL_CIZQACRI();
            } else {
                LNCSIQIF.MULT_INF[WS_J-1].MULT_AC = " ";
                LNCSIQIF.MULT_INF[WS_J-1].MULT_STL_METH = ' ';
                LNCSIQIF.MULT_INF[WS_J-1].MULT_AC_TYP = " ";
                LNCSIQIF.MULT_INF[WS_J-1].MULT_AC_FLG = ' ';
                LNCSIQIF.MULT_INF[WS_J-1].MULT_CCY = " ";
            }
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AC_CNM);
            LNCSIQIF.MULT_INF[WS_J-1].MULT_AC_NAM = CICQACRI.O_DATA.O_AC_CNM;
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                AICPQMIB.INPUT_DATA.AC = CICQACRI.DATA.AGR_NO;
                S000_CALL_AIZPQMIB();
                LNCSIQIF.MULT_INF[WS_J-1].MULT_AC_NAM = AICPQMIB.OUTPUT_DATA.CHS_NM;
            }
            CEP.TRC(SCCGWA, WS_J);
            CEP.TRC(SCCGWA, LNCSIQIF.MULT_INF[WS_J-1].MULT_AC_NAM);
            S000_CALL_LNZRSETL_RDNT();
        }
        S000_CALL_LNZRSETL_EDBR();
        IBS.init(SCCGWA, LNRSETL);
        IBS.init(SCCGWA, LNCRSETL);
        IBS.init(SCCGWA, CICQACRI);
        LNRSETL.KEY.CONTRACT_NO = LNCSIQIF.CONTRACT_NO;
        LNRSETL.KEY.SETTLE_TYPE = '4';
        S000_CALL_LNZRSETL_READ();
        LNCSIQIF.FUND_AC = LNRSETL.AC;
        IBS.init(SCCGWA, LNRSETL);
        IBS.init(SCCGWA, LNCRSETL);
        IBS.init(SCCGWA, CICQACRI);
        LNRSETL.KEY.CONTRACT_NO = LNCSIQIF.CONTRACT_NO;
        LNRSETL.KEY.SETTLE_TYPE = 'A';
        S000_CALL_LNZRSETL_READ();
        LNCSIQIF.CGN_SETTL_AC = LNRSETL.AC;
        IBS.init(SCCGWA, LNRSETL);
        IBS.init(SCCGWA, LNCRSETL);
        IBS.init(SCCGWA, CICQACRI);
        LNRSETL.KEY.CONTRACT_NO = LNCSIQIF.CONTRACT_NO;
        LNRSETL.KEY.SETTLE_TYPE = 'B';
        S000_CALL_LNZRSETL_READ();
        LNCSIQIF.CGN_INT_AC = LNRSETL.AC;
        IBS.init(SCCGWA, LNRRELA);
        LNRRELA.KEY.CONTRACT_NO = LNCSIQIF.CONTRACT_NO;
        LNRRELA.KEY.TYPE = 'S';
        T000_READ_LNTRELA();
        LNCSIQIF.PROJ_NO = LNRRELA.PROJ_NO;
        LNCSIQIF.CERT_NO = LNRRELA.SUBS_CERT_NO;
        LNCSIQIF.SUSB_TERM = LNRRELA.TERM;
        LNCSIQIF.SUST_TERM = LNRRELA.ST_TERM;
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNRCONT.KEY.CONTRACT_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        T000_READ_LNTICTL();
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(0, 1));
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1));
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(11 - 1, 11 + 1 - 1));
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(2 - 1, 2 + 1 - 1));
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(8 - 1, 8 + 1 - 1));
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1));
        LNCSIQIF.IN_RATE = LNRICTL.CUR_RAT;
        LNCSIQIF.PEN_IRAT = LNRICTL.CUR_PO_RAT;
        LNCSIQIF.OLC_PERU = LNRICTL.CUR_IO_RAT;
        IBS.init(SCCGWA, LNCICSTS);
        LNCICSTS.COMM_DATA.CONTRACT_NO = LNRCONT.KEY.CONTRACT_NO;
        CEP.TRC(SCCGWA, LNCICSTS.COMM_DATA.CONTRACT_NO);
        S000_CALL_LNZICSTS();
        LNCSIQIF.STS = LNCICSTS.COMM_DATA.STS;
        CEP.TRC(SCCGWA, LNCICSTS.COMM_DATA.STS_WORD);
        S000_CONT_OVER_TIME();
    }
    public void S000_CALL_LNZRSETL_READ() throws IOException,SQLException,Exception {
        LNCRSETL.FUNC = 'K';
        S000_CALL_LNZRSETL();
    }
    public void S000_CALL_LNZRSETL_STBR() throws IOException,SQLException,Exception {
        LNCRSETL.FUNC = 'B';
        LNCRSETL.OPT = 'A';
        S000_CALL_LNZRSETL();
    }
    public void S000_CALL_LNZRSETL_RDNT() throws IOException,SQLException,Exception {
        LNCRSETL.FUNC = 'B';
        LNCRSETL.OPT = 'R';
        S000_CALL_LNZRSETL();
        if (LNCRSETL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRSETL.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase("LN1568")) {
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRSETL.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSIQIF.RC);
                S000_ERR_MSG_PROC_LAST();
            }
        }
    }
    public void S000_CALL_LNZRSETL_EDBR() throws IOException,SQLException,Exception {
        LNCRSETL.FUNC = 'B';
        LNCRSETL.OPT = 'E';
        S000_CALL_LNZRSETL();
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC, true);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSIQIF.RC);
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST, true);
        CEP.TRC(SCCGWA, CICCUST.RC.RC_CODE);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSIQIF.RC);
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT, true);
        if (LNCRCONT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSIQIF.RC);
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void S000_CALL_LNZRLOAN() throws IOException,SQLException,Exception {
        LNCRLOAN.REC_PTR = LNRLOAN;
        LNCRLOAN.REC_LEN = 217;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTLOAN", LNCRLOAN, true);
        if (LNCRLOAN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRLOAN.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSIQIF.RC);
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void S000_CALL_LNZRRATN() throws IOException,SQLException,Exception {
        LNCRRATN.REC_PTR = LNRRATN;
        LNCRRATN.REC_LEN = 423;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTRATN", LNCRRATN, true);
        if (LNCRRATN.RC.RC_CODE != 0 
            && LNCRRATN.RC.RC_CODE != 1562) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRRATN.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSIQIF.RC);
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM, true);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSIQIF.RC);
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void S000_CALL_LNZSCKPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-CHK-PROD", LNCSCKPD);
    }
    public void S000_CALL_LNZRPAIP_STBR() throws IOException,SQLException,Exception {
        LNCRPAIP.REC_PTR = LNRPAIP;
        LNCRPAIP.REC_LEN = 200;
        LNCRPAIP.FUNC = 'B';
        LNCRPAIP.OPT = 'C';
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNZRPAIP", LNCRPAIP, true);
        if (LNCRPAIP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPAIP.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSIQIF.RC);
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void S000_CALL_LNZRPAIP_RDNT() throws IOException,SQLException,Exception {
        LNCRPAIP.FUNC = 'B';
        LNCRPAIP.OPT = 'R';
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNZRPAIP", LNCRPAIP, true);
        if (LNCRPAIP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPAIP.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase("LN1500")) {
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPAIP.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSIQIF.RC);
                S000_ERR_MSG_PROC_LAST();
            }
        }
    }
    public void S000_CALL_LNZRPAIP_EDBR() throws IOException,SQLException,Exception {
        LNCRPAIP.FUNC = 'B';
        LNCRPAIP.OPT = 'E';
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNZRPAIP", LNCRPAIP, true);
        if (LNCRPAIP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRPAIP.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSIQIF.RC);
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void S000_CALL_LNZRSETL() throws IOException,SQLException,Exception {
        LNCRSETL.REC_PTR = LNRSETL;
        LNCRSETL.REC_LEN = 266;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTSETL", LNCRSETL, true);
        if (LNCRSETL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRSETL.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase("LN1568")) {
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRSETL.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSIQIF.RC);
                S000_ERR_MSG_PROC_LAST();
            }
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSIQIF.RC);
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void T000_READ_LNTBALZ() throws IOException,SQLException,Exception {
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        IBS.READ(SCCGWA, LNRBALZ, LNTBALZ_RD);
    }
    public void T000_READ_LNTRATL() throws IOException,SQLException,Exception {
        LNTRATL_RD = new DBParm();
        LNTRATL_RD.TableName = "LNTRATL";
        LNTRATL_RD.where = "CONTRACT_NO = :LNRRATL.KEY.CONTRACT_NO "
            + "AND ACTV_DT <= :LNRRATL.KEY.ACTV_DT "
            + "AND OVD_KND = :LNRRATL.KEY.OVD_KND";
        LNTRATL_RD.fst = true;
        LNTRATL_RD.order = "ACTV_DT DESC";
        IBS.READ(SCCGWA, LNRRATL, this, LNTRATL_RD);
    }
    public void T000_READ_LNTRELA() throws IOException,SQLException,Exception {
        LNTRELA_RD = new DBParm();
        LNTRELA_RD.TableName = "LNTRELA";
        IBS.READ(SCCGWA, LNRRELA, LNTRELA_RD);
    }
    public void T000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        CEP.TRC(SCCGWA, BPCCINTI.RC);
    }
    public void S000_CALL_LNZICSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-GET-ICTL-STS", LNCICSTS);
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSIQIF.RC);
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void S000_CONT_OVER_TIME() throws IOException,SQLException,Exception {
        WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_CONT_NO = LNCSIQIF.CONTRACT_NO;
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.set = "WS-CONT-OVER-TIME=COUNT(*)";
        LNTRCVD_RD.where = "CONTRACT_NO = :WS_CONT_NO "
            + "AND VAL_DT < :WS_AC_DATE "
            + "AND TERM_STS = '1'";
        IBS.GROUP(SCCGWA, LNRRCVD, this, LNTRCVD_RD);
        LNCSIQIF.CONT_OVER_T = WS_CONT_OVER_TIME;
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSIQIF.RC);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
