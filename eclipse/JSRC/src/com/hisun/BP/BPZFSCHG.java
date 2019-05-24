package com.hisun.BP;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.AI.*;
import com.hisun.CI.*;
import com.hisun.DC.*;
import com.hisun.VT.*;
import com.hisun.IB.*;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFSCHG {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTFABF_RD;
    DBParm BPTFCTR_RD;
    DBParm BPTFEHIS_RD;
    brParm BPTFEHIS_BR = new brParm();
    SimpleDateFormat JIBS_sdf;
    Date JIBS_date;
    BPCGFEEP_FEE_INFO FEE_INFO;
    String JIBS_NumStr;
    brParm BPTFEAG_BR = new brParm();
    brParm BPTFADT_BR = new brParm();
    DBParm BPTFADT_RD;
    DBParm BPTDDIC_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZFSCHG";
    String K_OUTPUT_FMT = "BP081";
    String K_OUTPUT_FMT2 = "BP082";
    String K_OUTPUT_FMT3 = "BP056";
    String K_FEE_HIST = "FEE HISTORY";
    String K_CHNL_TRM = "100100";
    String K_ZERO_9 = "000000000";
    String CPN_S_SET_FEE_IND = "SC-SET-FEE-IND";
    String CPN_U_MAINTAIN_FBAS = "BP-F-U-MAINTAIN-FBAS";
    String CPN_U_MAINTAIN_FSVR = "BP-F-U-MAINTAIN-FSVR";
    String CPN_PROC_FHIS = "BP-PROC-FHIS";
    String CPN_REV_DD_DDZUDRAC = "DD-UNIT-DRAW-PROC";
    String CPN_CALL_CASH_ADD_BOX = "BP-U-ADD-CBOX  ";
    String CPN_CALL_CASH_SUB_BOX = "BP-U-SUB-CBOX  ";
    String CPN_CALL_CIZGFXFE = "CI-INQ-GFX-FEE";
    String CPN_CALL_BPZPNHIS = "BP-REC-NHIS";
    String CPN_CALL_BPZRFCTR = "BP-R-MGM-FEECTR";
    String CPN_R_FPDT = "BP-F-R-FE-UNCHG-DTL";
    String K_TBL_FEHIS = "BPTFEHIS";
    int K_GL_BRANCH = 043400;
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    String WS_FEE_CODE = " ";
    String WS_FEE_CODE_GL = " ";
    double WS_F_CHG_AMT = 0;
    String WS_FEE_NAME = " ";
    String WS_FEE_CINO = " ";
    short WS_CNT1 = 0;
    short WS_CNT2 = 0;
    short WS_FABF_CNT = 0;
    String WS_OUTPUT_FMT = " ";
    short WS_JRN_SEQ = 0;
    char WS_SKPCHG_FLG = ' ';
    char WS_SKPALL_FLG = ' ';
    BPZFSCHG_WS_FEE_CDS[] WS_FEE_CDS = new BPZFSCHG_WS_FEE_CDS[20];
    short WS_I = 0;
    short WS_J = 0;
    short WS_K = 0;
    BPZFSCHG_WS_BR_ARRAY[] WS_BR_ARRAY = new BPZFSCHG_WS_BR_ARRAY[5];
    char WS_DRMCR_FLG = ' ';
    char WS_BAL_LESS_FLG = ' ';
    char WS_BAL_ENOU_FLG = ' ';
    double WS_CHG_AMT_TOL = 0;
    double WS_CHG_AMT_TMP = 0;
    double WS_CHG_AMT_ACT = 0;
    BPZFSCHG_WS_FEE_CTRT_CN WS_FEE_CTRT_CN = new BPZFSCHG_WS_FEE_CTRT_CN();
    BPZFSCHG_WS_CARD_TABLE[] WS_CARD_TABLE = new BPZFSCHG_WS_CARD_TABLE[20];
    BPZFSCHG_WS_FEHIS_BRANCH[] WS_FEHIS_BRANCH = new BPZFSCHG_WS_FEHIS_BRANCH[20];
    String WS_PFEE_CMMT_NO = " ";
    int WS_PFEE_PROC_DT = 0;
    int WS_CHG_BR = 0;
    String WS_STD_AC = " ";
    BPZFSCHG_WS_CHG_F_AC_T[] WS_CHG_F_AC_T = new BPZFSCHG_WS_CHG_F_AC_T[5];
    BPZFSCHG_WS_OUTPUT_BNK_ACC WS_OUTPUT_BNK_ACC = new BPZFSCHG_WS_OUTPUT_BNK_ACC();
    String WS_CHG_AC = " ";
    String WS_CI_NO1 = " ";
    String WS_CI_NO2 = " ";
    String WS_FEE_TX_MMO = " ";
    char WS_AMO_FLG = ' ';
    char WS_FEE_CHG_TYPE = ' ';
    String WS_LAST_FEE_CODE = " ";
    int WS_LAST_BR = 0;
    String WS_CHG_ORG_AC = " ";
    String WS_CHG_ORG_AC_F = " ";
    int WS_MATURITY_DATE = 0;
    short WS_TEM_LEN = 0;
    String WS_CINO_TMP = " ";
    String WS_AC_TMP = " ";
    short WS_F_ITM_CNT = 0;
    BPZFSCHG_WS_FEHIS_AMT[] WS_FEHIS_AMT = new BPZFSCHG_WS_FEHIS_AMT[20];
    BPZFSCHG_WS_FEHIS_AMT2[] WS_FEHIS_AMT2 = new BPZFSCHG_WS_FEHIS_AMT2[20];
    BPZFSCHG_WS_FABF_AMT_N[] WS_FABF_AMT_N = new BPZFSCHG_WS_FABF_AMT_N[5];
    short WS_F_TEST_CNT = 0;
    String WS_TR_CD = " ";
    String WS_TR_NAME = " ";
    String WS_TS_FLG = " ";
    String WS_TS = " ";
    double WS_FPDT_AMT = 0;
    double WS_YS_AMT = 0;
    char WS_CHG_FLG = ' ';
    char WS_IFHIS_FLG = ' ';
    char WS_HIS_FND_FLG = ' ';
    char WS_FEHIS_FLG = ' ';
    char WS_WRT_FHIS_FLG = ' ';
    char WS_FILE_STS = ' ';
    char WS_BR_FLG = ' ';
    char WS_U_CHG_STS = ' ';
    char WS_OVER_FLOWER = ' ';
    char WS_REAL_CHG_FEE = ' ';
    char WS_AMT_CODE_TYPE = ' ';
    char WS_BREAK_FLG = ' ';
    char WS_NHIST_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    BPCUABOX BPCUABOX = new BPCUABOX();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPVFSVR BPVFSVR = new BPVFSVR();
    BPCOFSVR BPCOCSVR = new BPCOFSVR();
    BPCOFBAS BPCOFBAS = new BPCOFBAS();
    BPCOFCOM BPCOFCOM = new BPCOFCOM();
    BPRFFHIS BPRFFHIS = new BPRFFHIS();
    BPCOCHGO BPCOCHGO = new BPCOCHGO();
    BPRFHIST BPRPHIS = new BPRFHIST();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCGFEEO BPCGFEEO = new BPCGFEEO();
    BPCGFEEP BPCGFEEP = new BPCGFEEP();
    AICUUPIA AICUUPIA = new AICUUPIA();
    BPCPQCTR BPCPQCTR = new BPCPQCTR();
    CICCUST CICCUST = new CICCUST();
    BPCSFECT BPCSFECT = new BPCSFECT();
    CICACCU CICACCU = new CICACCU();
    BPRFEHIS BPRFEHIS = new BPRFEHIS();
    BPCRFPDT BPCRFPDT = new BPCRFPDT();
    BPRFEAG BPRFEAG = new BPRFEAG();
    BPRFADT BPRFADT = new BPRFADT();
    BPRFPDT BPRFPDT = new BPRFPDT();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDCSQIFA DDCSQIFA = new DDCSQIFA();
    BPCCNGL BPCICNGL = new BPCCNGL();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCUGLM BPCUGLM = new BPCUGLM();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    CICSIGFX CICSIGFX = new CICSIGFX();
    DCCURHLD DCCURHLD = new DCCURHLD();
    DDCUGCBL DDCUGCBL = new DDCUGCBL();
    DDCUGCHK DDCUGCHK = new DDCUGCHK();
    BPRFCTR BPRFCTR = new BPRFCTR();
    BPCHFCTR BPCOFCTR = new BPCHFCTR();
    BPCHFCTR BPCNFCTR = new BPCHFCTR();
    BPCRFCTR BPCRFCTR = new BPCRFCTR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCGPFEE BPCGTFEE = new BPCGPFEE();
    DCCIIHLD DCCIIHLD = new DCCIIHLD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICQACAC CICQACAC = new CICQACAC();
    DDCSCINM DDCSCINM = new DDCSCINM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRTRT BPRTRT = new BPRTRT();
    BPRFABF BPRFABF = new BPRFABF();
    CICQACRL CICQACRL = new CICQACRL();
    CICQACRI CICQACRI = new CICQACRI();
    BPRDDIC BPRDDIC = new BPRDDIC();
    VTCPQTAX VTCPQTAX = new VTCPQTAX();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    BPRFSCH BPRFSCH = new BPRFSCH();
    BPCRFSCH BPCRFSCH = new BPCRFSCH();
    String WS_SGN_CINO = " ";
    String WS_SGN_AC = " ";
    int WS_CLT_BR = 0;
    String WS_FEE_CD = " ";
    char WS_FDT_TYP = ' ';
    char WS_PRC_STS = ' ';
    int WS_PROC_DT = 0;
    String WS_PROD_CD = " ";
    SCCGWA SCCGWA;
    SCCGMSG SCCGMSG;
    BPCGPFEE BPCGPFEE;
    BPCGCFEE BPCGCFEE;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCFSCHG BPCFSCHG;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public BPZFSCHG() {
        for (int i=0;i<20;i++) WS_FEE_CDS[i] = new BPZFSCHG_WS_FEE_CDS();
        for (int i=0;i<5;i++) WS_BR_ARRAY[i] = new BPZFSCHG_WS_BR_ARRAY();
        for (int i=0;i<20;i++) WS_CARD_TABLE[i] = new BPZFSCHG_WS_CARD_TABLE();
        for (int i=0;i<20;i++) WS_FEHIS_BRANCH[i] = new BPZFSCHG_WS_FEHIS_BRANCH();
        for (int i=0;i<5;i++) WS_CHG_F_AC_T[i] = new BPZFSCHG_WS_CHG_F_AC_T();
        for (int i=0;i<20;i++) WS_FEHIS_AMT[i] = new BPZFSCHG_WS_FEHIS_AMT();
        for (int i=0;i<20;i++) WS_FEHIS_AMT2[i] = new BPZFSCHG_WS_FEHIS_AMT2();
        for (int i=0;i<5;i++) WS_FABF_AMT_N[i] = new BPZFSCHG_WS_FABF_AMT_N();
    }
    public void MP(SCCGWA SCCGWA, BPCFSCHG BPCFSCHG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFSCHG = BPCFSCHG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFSCHG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
        BPCGPFEE = (BPCGPFEE) GWA_BP_AREA.FEE_AREA.FEE_PARM_PTR;
        BPCGCFEE = new BPCGCFEE();
        IBS.init(SCCGWA, BPCGCFEE);
        IBS.CPY2CLS(SCCGWA, GWA_BP_AREA.FEE_AREA.FEE_DATA_PTR, BPCGCFEE);
        //BPCGCFEE = (com.hisun.BP.BPCGCFEE) GWA_BP_AREA.FEE_AREA.FEE_DATA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.init(SCCGWA, BPCSFECT);
        IBS.init(SCCGWA, BPCPQCTR);
        IBS.init(SCCGWA, BPCGTFEE);
        IBS.init(SCCGWA, BPCGFEEO);
        WS_REAL_CHG_FEE = 'N';
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SERV_CODE);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.EX_RATE);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.SEND_FLG);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_CNT);
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA.AUH_FLG);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_IND);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REVERSAL_IND);
        CEP.TRC(SCCGWA, BPCOCSVR.VAL.AUT_FLG);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.FEE_DATA_IND);
        if ((BPCGCFEE.FEE_DATA.FEE_CNT <= 0 
            || BPCGPFEE.TX_DATA.AUH_FLG == '1') 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && ((BPCGPFEE.TX_DATA3.PROC_TYPE != '1' 
            && BPCGPFEE.TX_DATA3.PROC_TYPE != '2'))) {
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.FEE_IND);
        if (SCCGWA.COMM_AREA.FEE_IND == 'X') {
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[1-1].FEE_CODE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_TS_FLG = "A";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.PROC_TYPE);
        if (SCCGWA.COMM_AREA.FEE_DATA_IND == 'B') {
            BPCGPFEE.TX_DATA3.PROC_TYPE = '6';
        }
        WS_BREAK_FLG = 'N';
        for (WS_F_ITM_CNT = 1; WS_F_ITM_CNT <= BPCGCFEE.FEE_DATA.FEE_CNT 
            && WS_BREAK_FLG != 'Y'; WS_F_ITM_CNT += 1) {
            CEP.TRC(SCCGWA, WS_F_ITM_CNT);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_F_ITM_CNT-1].CHG_AMT);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_F_ITM_CNT-1].ADJ_AMT);
            if ((BPCGCFEE.FEE_DATA.FEE_INFO[WS_F_ITM_CNT-1].CHG_AMT != BPCGCFEE.FEE_DATA.FEE_INFO[WS_F_ITM_CNT-1].ADJ_AMT) 
                && SCCGWA.COMM_AREA.FEE_DATA_IND == 'Y') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_UPD_AMT_AUTH_8);
                WS_BREAK_FLG = 'Y';
            }
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_F_ITM_CNT-1].CHG_AC);
            CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_AC);
            if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_F_ITM_CNT-1].CHG_AC.equalsIgnoreCase("99999999999999")) {
                BPCGCFEE.FEE_DATA.FEE_INFO[WS_F_ITM_CNT-1].CHG_AC = BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_AC;
                CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_F_ITM_CNT-1].CHG_AC);
            }
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || SCCGWA.COMM_AREA.REVERSAL_IND == 'Y') {
            B010_CANCEL_REVERSAL_FEE_CN();
            if (pgmRtn) return;
        } else {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.SERV_CODE == null) SCCGWA.COMM_AREA.SERV_CODE = "";
            JIBS_tmp_int = SCCGWA.COMM_AREA.SERV_CODE.length();
            for (int i=0;i<7-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.SERV_CODE += " ";
            if (SCCGWA.COMM_AREA.SERV_CODE.substring(0, 3).equalsIgnoreCase("BSP") 
                || !SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase(K_CHNL_TRM)) {
                BPCOCSVR.VAL.AUT_FLG = '0';
            } else {
                T000_GET_SERVICE_FEE_INFO();
                if (pgmRtn) return;
                T000_GET_FSCH_INFO();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.PROC_TYPE);
            if ((BPCGPFEE.TX_DATA3.PROC_TYPE == '1' 
                || BPCGPFEE.TX_DATA3.PROC_TYPE == '2')) {
                B015_GET_ACC_FEE_INFO();
                if (pgmRtn) return;
            }
            if (BPCGPFEE.TX_DATA3.PROC_TYPE == '7') {
                B015_GET_ACC_FEE_INFO();
                if (pgmRtn) return;
                if (WS_FEE_CTRT_CN.WS_FEE_CTRT_TABLE[1-1].WS_FEE_CTRT_NO.trim().length() > 0) {
                    B017_CHANGE_AMO_ENDDT();
                    if (pgmRtn) return;
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
        CEP.TRC(SCCGWA, BPCOCSVR.VAL.AUT_FLG);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.FEE_DATA_IND);
        CEP.TRC(SCCGWA, GWA_SC_AREA.REQ_TYPE);
        if (SCCGWA.COMM_AREA.FEE_DATA_IND == 'Y' 
            || BPCOCSVR.VAL.AUT_FLG == '0' 
            || SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || (BPCOCSVR.VAL.AUT_FLG == '1' 
            && BPCGPFEE.TX_DATA3.PROC_TYPE == '6')) {
            CEP.TRC(SCCGWA, BPCOCSVR.VAL.AUT_FLG);
            B020_CHARGE_FEE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "GENG1");
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SERV_CODE);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[1-1].FEE_CODE);
            CEP.TRC(SCCGWA, WS_REAL_CHG_FEE);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
            CEP.TRC(SCCGWA, "060200");
            if (SCCGWA.COMM_AREA.SERV_CODE == null) SCCGWA.COMM_AREA.SERV_CODE = "";
            JIBS_tmp_int = SCCGWA.COMM_AREA.SERV_CODE.length();
            for (int i=0;i<7-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.SERV_CODE += " ";
            if (!SCCGWA.COMM_AREA.SERV_CODE.substring(0, 3).equalsIgnoreCase("BSP") 
                && BPCGCFEE.FEE_DATA.FEE_INFO[1-1].FEE_CODE.trim().length() > 0 
                && (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                CEP.TRC(SCCGWA, "GENG2");
                if (BPCGPFEE.TX_DATA3.PROC_TYPE == '1' 
                    || BPCGPFEE.TX_DATA3.PROC_TYPE == '2') {
                    CEP.TRC(SCCGWA, "GENG3");
                    WS_OUTPUT_FMT = K_OUTPUT_FMT3;
                    B030_OUTPUT_BANK_ACC();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, "GENG4");
                    WS_OUTPUT_FMT = K_OUTPUT_FMT2;
                    B030_OUTPUT_FEE_INFO();
                    if (pgmRtn) return;
                }
            }
        } else {
            if (SCCGWA.COMM_AREA.SERV_CODE.equalsIgnoreCase("0115840")) {
                for (WS_F_TEST_CNT = 1; BPCGCFEE.FEE_DATA.FEE_INFO[WS_F_TEST_CNT-1].CHG_AC.trim().length() != 0 
                    && WS_F_TEST_CNT <= 20; WS_F_TEST_CNT += 1) {
                    BPCGCFEE.FEE_DATA.FEE_INFO[WS_F_TEST_CNT-1].CHG_AC = "99999999999999";
                }
            }
            S000_CALL_SET_FEE_IND();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "SET FEE IND");
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.FEE_IND);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.FEE_DATA_IND);
            WS_SKPCHG_FLG = 'N';
            WS_SKPALL_FLG = 'N';
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY);
            CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.CLT_TYPE);
            CEP.TRC(SCCGWA, WS_CNT1);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[1-1].ADJ_AMT);
            WS_CNT1 = 1;
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.PROC_TYPE);
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            JIBS_tmp_str[3] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (BPCGCFEE.FEE_DATA.PROC_TYPE != '3' 
                && BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_FLG != '1' 
                && !JIBS_tmp_str[1].equalsIgnoreCase("9991187") 
                && !JIBS_tmp_str[3].equalsIgnoreCase("9999128")) {
                if (((BPCGPFEE.TX_DATA3.CLT_TYPE == '1' 
                    && WS_CNT1 == 1) 
                    || BPCGPFEE.TX_DATA3.CLT_TYPE != '1') 
                    && BPCGCFEE.FEE_DATA.FEE_INFO[1-1].ADJ_AMT != 0 
                    && BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY != '1') {
                    B023_FEE_COLLECTION_PROC();
                    if (pgmRtn) return;
                }
                WS_SKPALL_FLG = 'N';
                if (WS_SKPCHG_FLG == 'Y' 
                    && !SCCGWA.COMM_AREA.SERV_CODE.equalsIgnoreCase("9999156") 
                    && BPCGPFEE.TX_DATA3.CLT_TYPE == ' ' 
                    && BPCGPFEE.TX_DATA3.PROC_TYPE != '1' 
                    && BPCGPFEE.TX_DATA3.PROC_TYPE != '2' 
                    && BPCGPFEE.TX_DATA3.PROC_TYPE != '3' 
                    && BPCGPFEE.TX_DATA3.PROC_TYPE != '4' 
                    && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    CEP.TRC(SCCGWA, BPRFEAG.CLT_RANGE);
                    if (BPRFEAG.CLT_RANGE != 'A') {
                        for (WS_I = 1; WS_I <= 20 
                            && WS_FEE_CDS[WS_I-1].WS_FEE_CD1.trim().length() != 0 
                            && WS_SKPALL_FLG != 'Y'; WS_I += 1) {
                            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE);
                            CEP.TRC(SCCGWA, WS_FEE_CDS[WS_I-1].WS_FEE_CD1);
                            if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE.equalsIgnoreCase(WS_FEE_CDS[WS_I-1].WS_FEE_CD1) 
                                && BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT != 0) {
                                WS_SKPALL_FLG = 'Y';
                            }
                        }
                    }
                    if (BPRFEAG.CLT_RANGE == 'A') {
                        WS_SKPALL_FLG = 'Y';
                    }
                }
            }
            CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.PROC_TYPE);
            CEP.TRC(SCCGWA, "ZHENG21");
            CEP.TRC(SCCGWA, WS_BAL_LESS_FLG);
            CEP.TRC(SCCGWA, BPCOFBAS.VAL.DEBT_METH);
            CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.CLT_TYPE);
            if (SCCGWA.COMM_AREA.SERV_CODE == null) SCCGWA.COMM_AREA.SERV_CODE = "";
            JIBS_tmp_int = SCCGWA.COMM_AREA.SERV_CODE.length();
            for (int i=0;i<7-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.SERV_CODE += " ";
            if ((BPCGPFEE.TX_DATA3.PROC_TYPE == '6' 
                && WS_SKPALL_FLG != 'Y') 
                || (SCCGWA.COMM_AREA.SERV_CODE.substring(0, 3).equalsIgnoreCase("BSP") 
                && WS_BAL_LESS_FLG == 'Y' 
                && BPCOFBAS.VAL.DEBT_METH == '0' 
                && BPCGPFEE.TX_DATA3.CLT_TYPE != '0' 
                && BPCGPFEE.TX_DATA3.CLT_TYPE != '1' 
                && WS_SKPALL_FLG != 'Y' 
                && BPCGPFEE.TX_DATA3.CLT_TYPE != '2' 
                && BPCGPFEE.TX_DATA2.FEE_CTRT_NO.trim().length() == 0)) {
                WS_SKPALL_FLG = 'Y';
            }
            if (SCCGWA.COMM_AREA.SERV_CODE == null) SCCGWA.COMM_AREA.SERV_CODE = "";
            JIBS_tmp_int = SCCGWA.COMM_AREA.SERV_CODE.length();
            for (int i=0;i<7-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.SERV_CODE += " ";
            if (SCCGWA.COMM_AREA.SERV_CODE.substring(0, 3).equalsIgnoreCase("BSP") 
                && WS_BAL_LESS_FLG == 'Y' 
                && BPCOFBAS.VAL.DEBT_METH == '0' 
                && (BPCGPFEE.TX_DATA3.CLT_TYPE == '0' 
                || BPCGPFEE.TX_DATA3.CLT_TYPE == '2')) {
                WS_SKPALL_FLG = 'Y';
            }
            WS_OUTPUT_FMT = K_OUTPUT_FMT;
            CEP.TRC(SCCGWA, WS_SKPALL_FLG);
            if (WS_SKPALL_FLG == 'Y') {
                CEP.TRC(SCCGWA, "POI");
                BPCGFEEO.FEE_DATA.PROC_TYPE = '2';
            } else {
                CEP.TRC(SCCGWA, "LKJ");
                if (WS_SKPALL_FLG == 'N' 
                    || WS_SKPALL_FLG == ' ') {
                    CEP.TRC(SCCGWA, "MNB");
                    BPCGFEEO.FEE_DATA.PROC_TYPE = '1';
                }
            }
            B030_OUTPUT_FEE_INFO();
            if (pgmRtn) return;
        }
    }
    public void B010_CANCEL_REVERSAL_FEE_CN() throws IOException,SQLException,Exception {
        R000_GET_FEE_HIST_BY_JRN_CN();
        if (pgmRtn) return;
        R000_CANCEL_BPTFPDT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_CNT);
        BPCOCSVR.VAL.AUT_FLG = '1';
    }
    public void R000_CANCEL_BPTFPDT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRFPDT);
        IBS.init(SCCGWA, BPRFPDT);
        BPRFPDT.KEY.TRT_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        BPRFPDT.KEY.JRN_NO = "" + SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        JIBS_tmp_int = BPRFPDT.KEY.JRN_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPRFPDT.KEY.JRN_NO = "0" + BPRFPDT.KEY.JRN_NO;
        BPCRFPDT.INFO.FDT_TYP = '2';
        BPCRFPDT.INFO.FUNC = 'L';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        BPCRFPDT.INFO.FUNC = 'N';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRFPDT.RETURN_INFO);
        while (BPCRFPDT.RETURN_INFO != 'N') {
            if (BPRFPDT.CHG_STS != '0') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ALRDY_GET_OWN);
            }
            S000_CANCEL_FPDT_EVENT();
            if (pgmRtn) return;
            BPRFPDT.CHG_STS = '3';
            BPRFPDT.UPDATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRFPDT.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
            BPCRFPDT.INFO.FUNC = 'M';
            S000_CALL_BPZRFPDT();
            if (pgmRtn) return;
            BPCRFPDT.INFO.FUNC = 'N';
            S000_CALL_BPZRFPDT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCRFPDT.RETURN_INFO);
        }
        BPCRFPDT.INFO.FUNC = 'E';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCRFPDT);
        IBS.init(SCCGWA, BPRFADT);
        BPRFADT.KEY.TRT_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        BPRFADT.KEY.JRN_NO = "" + SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        JIBS_tmp_int = BPRFADT.KEY.JRN_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPRFADT.KEY.JRN_NO = "0" + BPRFADT.KEY.JRN_NO;
        BPCRFPDT.INFO.FDT_TYP = '0';
        BPCRFPDT.INFO.FUNC = 'L';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        BPCRFPDT.INFO.FUNC = 'N';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRFPDT.RETURN_INFO);
        while (BPCRFPDT.RETURN_INFO != 'N') {
            if (BPRFADT.CHG_STS != '0') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ALRDY_GET_OWN);
            }
            S000_CANCEL_FADT_EVENT();
            if (pgmRtn) return;
            BPRFADT.CHG_STS = '3';
            BPRFADT.UPDATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRFADT.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
            BPCRFPDT.INFO.FUNC = 'M';
            S000_CALL_BPZRFPDT();
            if (pgmRtn) return;
            BPCRFPDT.INFO.FUNC = 'N';
            S000_CALL_BPZRFPDT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCRFPDT.RETURN_INFO);
        }
        BPCRFPDT.INFO.FUNC = 'E';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
    }
    public void S000_CANCEL_FPDT_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.PROD_CODE = BPRFPDT.FEE_CODE;
        BPCPOEWA.DATA.PROD_CODE_REL = BPRFPDT.PRD_CD;
        BPCPOEWA.DATA.CNTR_TYPE = "FEES";
        BPCPOEWA.DATA.BR_OLD = BPRFPDT.CHG_BR;
        BPCPOEWA.DATA.BR_NEW = BPRFPDT.CHG_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPRFPDT.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.DESC = "欠费冲正";
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE_REL);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.DESC);
        IBS.init(SCCGWA, VTCPQTAX);
        VTCPQTAX.INPUT_DATA.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTCPQTAX.INPUT_DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        VTCPQTAX.INPUT_DATA.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        VTCPQTAX.INPUT_DATA.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        VTCPQTAX.INPUT_DATA.BR = BPRFPDT.CHG_BR;
        VTCPQTAX.INPUT_DATA.CNTR_TYPE = "FEES";
        VTCPQTAX.INPUT_DATA.PROD_CD = BPRFPDT.FEE_CODE;
        VTCPQTAX.INPUT_DATA.PROD_CD_REL = BPRFPDT.PRD_CD;
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.PROD_CD);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.PROD_CD_REL);
        VTCPQTAX.INPUT_DATA.CI_NO = BPRFPDT.TX_CI;
        BPCPOEWA.DATA.CI_NO = BPRFPDT.TX_CI;
        VTCPQTAX.INPUT_DATA.CCY = BPRFPDT.CCY;
        VTCPQTAX.INPUT_DATA.YJ_AMT = BPRFPDT.CHG_AMT;
        S000_CALL_VTZPQTAX();
        if (pgmRtn) return;
        BPCPOEWA.DATA.EVENT_CODE = "HZFS";
        BPCPOEWA.DATA.AMT_INFO[05-1].AMT = BPRFPDT.CHG_AMT;
        BPCPOEWA.DATA.AMT_INFO[07-1].AMT = VTCPQTAX.OUTPUT_DATA.TAX_AMT;
        BPCPOEWA.DATA.AMT_INFO[01-1].AMT = BPRFPDT.CHG_AMT - VTCPQTAX.OUTPUT_DATA.TAX_AMT;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[01-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[05-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[07-1].AMT);
        BPCPOEWA.DATA.THEIR_AC = BPRFPDT.CARD_PSBK_NO;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.THEIR_AC);
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void S000_CANCEL_FADT_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.PROD_CODE = BPRFADT.FEE_CODE;
        BPCPOEWA.DATA.PROD_CODE_REL = BPRFADT.PRD_CD;
        BPCPOEWA.DATA.CNTR_TYPE = "FEES";
        BPCPOEWA.DATA.BR_OLD = BPRFADT.CHG_BR;
        BPCPOEWA.DATA.BR_NEW = BPRFADT.CHG_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPRFADT.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.DESC = "欠费冲正";
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE_REL);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.DESC);
        IBS.init(SCCGWA, VTCPQTAX);
        VTCPQTAX.INPUT_DATA.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTCPQTAX.INPUT_DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        VTCPQTAX.INPUT_DATA.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        VTCPQTAX.INPUT_DATA.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        VTCPQTAX.INPUT_DATA.BR = BPRFADT.CHG_BR;
        VTCPQTAX.INPUT_DATA.CNTR_TYPE = "FEES";
        VTCPQTAX.INPUT_DATA.PROD_CD = BPRFADT.FEE_CODE;
        VTCPQTAX.INPUT_DATA.PROD_CD_REL = BPRFADT.PRD_CD;
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.PROD_CD);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.PROD_CD_REL);
        VTCPQTAX.INPUT_DATA.CI_NO = BPRFADT.TX_CI;
        BPCPOEWA.DATA.CI_NO = BPRFADT.TX_CI;
        VTCPQTAX.INPUT_DATA.CCY = BPRFADT.CCY;
        VTCPQTAX.INPUT_DATA.YJ_AMT = BPRFADT.CHG_AMT;
        S000_CALL_VTZPQTAX();
        if (pgmRtn) return;
        BPCPOEWA.DATA.EVENT_CODE = "HZFS";
        BPCPOEWA.DATA.AMT_INFO[05-1].AMT = BPRFADT.CHG_AMT;
        BPCPOEWA.DATA.AMT_INFO[07-1].AMT = VTCPQTAX.OUTPUT_DATA.TAX_AMT;
        BPCPOEWA.DATA.AMT_INFO[01-1].AMT = BPRFADT.CHG_AMT - VTCPQTAX.OUTPUT_DATA.TAX_AMT;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[01-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[05-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[07-1].AMT);
        BPCPOEWA.DATA.THEIR_AC = BPRFADT.CARD_PSBK_NO;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.THEIR_AC);
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B020_CHARGE_FEE() throws IOException,SQLException,Exception {
        B023_1_DRMCR_PROC();
        if (pgmRtn) return;
        B022_CHARGE_CN();
        if (pgmRtn) return;
        B023_2_ACC_CLT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_AC);
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.CARD_PSBK_NO);
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_CCY);
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP);
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.CCY_TYPE);
        if (BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_AC.trim().length() > 0) {
            WS_CHG_AC = BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_AC;
        } else {
            if (BPCGPFEE.TX_DATA3.CARD_PSBK_NO.trim().length() > 0) {
                WS_CHG_AC = BPCGPFEE.TX_DATA3.CARD_PSBK_NO;
            }
        }
        CEP.TRC(SCCGWA, WS_CHG_AC);
        if (BPCGPFEE.TX_DATA3.CCY_TYPE == ' ') {
            BPCGPFEE.TX_DATA3.CCY_TYPE = '1';
        }
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_CNT);
        for (WS_CNT1 = 1; WS_CNT1 <= BPCGCFEE.FEE_DATA.FEE_CNT; WS_CNT1 += 1) {
            CEP.TRC(SCCGWA, WS_CNT1);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY);
            if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == ' ') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHG_AC_TYP_M_I);
            }
            if ((BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY != '0' 
                && BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY != '1' 
                && BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY != '2' 
                && BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY != '3' 
                && BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY != '4' 
                && BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY != '5' 
                && BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY != '6' 
                && BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY != '7')) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_FEE_CHG_MTH_ERR);
            }
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY);
            CEP.TRC(SCCGWA, BPCGPFEE.CUS_DATA.CUS_INFO[1-1].CUS_NO);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC);
            if ((BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY != '1' 
                && BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY != '3') 
                && !WS_CHG_AC.equalsIgnoreCase(BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC) 
                && BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP != '3') {
                IBS.init(SCCGWA, CICQACRI);
                CICQACRI.DATA.AGR_NO = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
                CEP.TRC(SCCGWA, CICQACRI.DATA.AGR_NO);
                CICQACRI.FUNC = 'A';
                S000_CALL_CIZQACRI();
                if (pgmRtn) return;
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.AGR_NO = CICQACRI.DATA.AGR_NO;
                S000_CALL_CIZACCU();
                if (pgmRtn) return;
                WS_CI_NO1 = CICACCU.DATA.CI_NO;
                WS_FEE_CINO = CICACCU.DATA.CI_NO;
                if (CICACCU.DATA.AC_CNM.trim().length() > 0) {
                    WS_FEE_NAME = CICACCU.DATA.AC_CNM;
                } else if (CICACCU.DATA.AC_ENM.trim().length() > 0) {
                    WS_FEE_NAME = CICACCU.DATA.AC_ENM;
                } else if (CICACCU.DATA.CI_CNM.trim().length() > 0) {
                    WS_FEE_NAME = CICACCU.DATA.CI_CNM;
                } else if (CICACCU.DATA.CI_ENM.trim().length() > 0) {
                    WS_FEE_NAME = CICACCU.DATA.CI_ENM;
                } else {
                    WS_FEE_NAME = " ";
                }
                if (((BPCGPFEE.CUS_DATA.CUS_INFO[1-1].CUS_NO.trim().length() > 0 
                    || !BPCGPFEE.CUS_DATA.CUS_INFO[1-1].CUS_NO.equalsIgnoreCase("0")) 
                    && (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].PROD_CD.trim().length() > 0)) 
                    || BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP == '1') {
                    WS_CI_NO2 = BPCGPFEE.CUS_DATA.CUS_INFO[1-1].CUS_NO;
                } else {
                    IBS.init(SCCGWA, CICQACRI);
                    CICQACRI.DATA.AGR_NO = WS_CHG_AC;
                    CICQACRI.FUNC = 'A';
                    S000_CALL_CIZQACRI();
                    if (pgmRtn) return;
                    IBS.init(SCCGWA, CICACCU);
                    CICACCU.DATA.AGR_NO = CICQACRI.DATA.AGR_NO;
                    S000_CALL_CIZACCU();
                    if (pgmRtn) return;
                    WS_CI_NO2 = CICACCU.DATA.CI_NO;
                    BPCGPFEE.CUS_DATA.CUS_INFO[1-1].CUS_NO = CICACCU.DATA.CI_NO;
                    BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].PROD_CD = CICACCU.DATA.PROD_CD;
                }
                if (!WS_CI_NO1.equalsIgnoreCase(WS_CI_NO2) 
                    && WS_CI_NO2.trim().length() > 0) {
                    CEP.TRC(SCCGWA, WS_CI_NO1);
                    CEP.TRC(SCCGWA, WS_CI_NO2);
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_CI_NOT_RELATED);
                }
            }
        }
        if (BPCGPFEE.TX_DATA2.FEE_CTRT_NO.trim().length() > 0 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            WS_FEE_CTRT_CN.WS_PFEE_FEE_CTRT = BPCGPFEE.TX_DATA2.FEE_CTRT_NO;
            WS_FEE_CTRT_CN.WS_PFEE_CTRT_FLG = 'Y';
        } else {
            WS_FEE_CTRT_CN.WS_PFEE_CTRT_FLG = 'N';
        }
    }
    public void B022_CHARGE_CN() throws IOException,SQLException,Exception {
        for (WS_CNT1 = 1; WS_CNT1 <= BPCGCFEE.FEE_DATA.FEE_CNT; WS_CNT1 += 1) {
            R000_GET_BASIC_FEE_INFO();
            if (pgmRtn) return;
            B024_GET_AC_BY_MEDIUM();
            if (pgmRtn) return;
            B024_ACCOUNT_BR_PROC();
            if (pgmRtn) return;
            if (BPCOFBAS.VAL.FEE_CHG_TYPE == '0' 
                && BPCOFBAS.VAL.DEBT_METH != '2' 
                && BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT != 0 
                && (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '0' 
                || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '4' 
                || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '5' 
                || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '6' 
                || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '7') 
                && BPCGPFEE.TX_DATA2.FEE_CTRT_NO.trim().length() == 0 
                && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                CEP.TRC(SCCGWA, "WANG39");
                B024_GET_BALANCE();
                if (pgmRtn) return;
            }
            WS_SKPCHG_FLG = 'N';
            WS_SKPALL_FLG = 'N';
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY);
            CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.CLT_TYPE);
            CEP.TRC(SCCGWA, WS_CNT1);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.PROC_TYPE);
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            JIBS_tmp_str[3] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            JIBS_tmp_str[5] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            JIBS_tmp_str[7] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (BPCGCFEE.FEE_DATA.PROC_TYPE != '3' 
                && BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_FLG != '1' 
                && !JIBS_tmp_str[1].equalsIgnoreCase("9991187") 
                && !JIBS_tmp_str[3].equalsIgnoreCase("9999156") 
                && !JIBS_tmp_str[5].equalsIgnoreCase("9991231") 
                && !JIBS_tmp_str[7].equalsIgnoreCase("9999128")) {
                if (((BPCGPFEE.TX_DATA3.CLT_TYPE == '1' 
                    && WS_CNT1 == 1) 
                    || BPCGPFEE.TX_DATA3.CLT_TYPE != '1') 
                    && BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT != 0 
                    && BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY != '1') {
                    CEP.TRC(SCCGWA, "WANG44");
                    B023_FEE_COLLECTION_PROC();
                    if (pgmRtn) return;
                }
                WS_SKPALL_FLG = 'N';
                CEP.TRC(SCCGWA, WS_SKPCHG_FLG);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SERV_CODE);
                CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.CLT_TYPE);
                CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.PROC_TYPE);
                if (WS_SKPCHG_FLG == 'Y' 
                    && !SCCGWA.COMM_AREA.SERV_CODE.equalsIgnoreCase("9999156") 
                    && BPCGPFEE.TX_DATA3.CLT_TYPE == ' ' 
                    && BPCGPFEE.TX_DATA3.PROC_TYPE != '1' 
                    && BPCGPFEE.TX_DATA3.PROC_TYPE != '2' 
                    && BPCGPFEE.TX_DATA3.PROC_TYPE != '3' 
                    && BPCGPFEE.TX_DATA3.PROC_TYPE != '4' 
                    && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    CEP.TRC(SCCGWA, "WANG55");
                    CEP.TRC(SCCGWA, BPRFEAG.CLT_RANGE);
                    if (BPRFEAG.CLT_RANGE != 'A') {
                        for (WS_I = 1; WS_I <= 20 
                            && WS_FEE_CDS[WS_I-1].WS_FEE_CD1.trim().length() != 0 
                            && WS_SKPALL_FLG != 'Y'; WS_I += 1) {
                            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE);
                            CEP.TRC(SCCGWA, WS_FEE_CDS[WS_I-1].WS_FEE_CD1);
                            CEP.TRC(SCCGWA, "WANG56");
                            if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE.equalsIgnoreCase(WS_FEE_CDS[WS_I-1].WS_FEE_CD1) 
                                && BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT != 0) {
                                CEP.TRC(SCCGWA, "WANG57");
                                WS_SKPALL_FLG = 'Y';
                            }
                        }
                    }
                    if (BPRFEAG.CLT_RANGE == 'A') {
                        CEP.TRC(SCCGWA, "WANG58");
                        WS_SKPALL_FLG = 'Y';
                    }
                    if (WS_SKPALL_FLG == 'Y') {
                        CEP.TRC(SCCGWA, "WANG59");
                        if (BPCGPFEE.TX_DATA3.HLD_NO.trim().length() > 0 
                            && !BPCGPFEE.TX_DATA3.HLD_NO.equalsIgnoreCase(BPRFEAG.HLD_NO)) {
                            CEP.TRC(SCCGWA, "WANG60");
                            B027_CHECK_CHANGE_HLDNO();
                            if (pgmRtn) return;
                            BPRFEAG.REMARK = BPRFEAG.HLD_NO;
                            BPRFEAG.HLD_NO = BPCGPFEE.TX_DATA3.HLD_NO;
                            T000_UPDATE_BPTFEAG();
                            if (pgmRtn) return;
                        }
                        B024_WRT_PENDING_CHG();
                        if (pgmRtn) return;
                    }
                }
            }
            CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.PROC_TYPE);
            CEP.TRC(SCCGWA, "ZHENG22");
            CEP.TRC(SCCGWA, WS_BAL_LESS_FLG);
            CEP.TRC(SCCGWA, BPCOFBAS.VAL.DEBT_METH);
            CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.CLT_TYPE);
            if (SCCGWA.COMM_AREA.SERV_CODE == null) SCCGWA.COMM_AREA.SERV_CODE = "";
            JIBS_tmp_int = SCCGWA.COMM_AREA.SERV_CODE.length();
            for (int i=0;i<7-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.SERV_CODE += " ";
            if ((BPCGPFEE.TX_DATA3.PROC_TYPE == '6' 
                && WS_SKPALL_FLG != 'Y') 
                || (SCCGWA.COMM_AREA.SERV_CODE.substring(0, 3).equalsIgnoreCase("BSP") 
                && WS_BAL_LESS_FLG == 'Y' 
                && BPCOFBAS.VAL.DEBT_METH == '0' 
                && BPCGPFEE.TX_DATA3.CLT_TYPE != '0' 
                && BPCGPFEE.TX_DATA3.CLT_TYPE != '1' 
                && WS_SKPALL_FLG != 'Y' 
                && BPCGPFEE.TX_DATA3.CLT_TYPE != '2' 
                && BPCGPFEE.TX_DATA2.FEE_CTRT_NO.trim().length() == 0)) {
                CEP.TRC(SCCGWA, "WANG61");
                WS_SKPALL_FLG = 'Y';
                if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT > 0) {
                    CEP.TRC(SCCGWA, "WANG62");
                    B024_WRT_OWE_CHG();
                    if (pgmRtn) return;
                }
            }
            if (SCCGWA.COMM_AREA.SERV_CODE == null) SCCGWA.COMM_AREA.SERV_CODE = "";
            JIBS_tmp_int = SCCGWA.COMM_AREA.SERV_CODE.length();
            for (int i=0;i<7-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.SERV_CODE += " ";
            if (SCCGWA.COMM_AREA.SERV_CODE.substring(0, 3).equalsIgnoreCase("BSP") 
                && WS_BAL_LESS_FLG == 'Y' 
                && BPCOFBAS.VAL.DEBT_METH == '0' 
                && (BPCGPFEE.TX_DATA3.CLT_TYPE == '0' 
                || BPCGPFEE.TX_DATA3.CLT_TYPE == '2')) {
                CEP.TRC(SCCGWA, "WANG63");
                WS_SKPALL_FLG = 'Y';
                if (BPCGPFEE.TX_DATA3.CLT_TYPE == '0') {
                    CEP.TRC(SCCGWA, "WANG64");
                    B022_1_CLT_FEE_PROCESS();
                    if (pgmRtn) return;
                }
            }
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_CNT);
            CEP.TRC(SCCGWA, WS_CNT1);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].PROD_CD);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_AMT);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_FLG);
            CEP.TRC(SCCGWA, WS_SKPALL_FLG);
            WS_CHG_FLG = 'Y';
            WS_WRT_FHIS_FLG = 'N';
            if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT != 0 
                && WS_SKPALL_FLG != 'Y' 
                && (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                || BPCOCSVR.VAL.AUT_FLG == '0' 
                || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE.trim().length() > 0) 
                && (WS_BAL_LESS_FLG != 'Y' 
                || (WS_BAL_LESS_FLG == 'Y' 
                && (WS_CHG_AMT_ACT != 0 
                || BPCGPFEE.TX_DATA2.FEE_CTRT_NO.trim().length() > 0)))) {
                if (WS_DRMCR_FLG == 'Y') {
                    if (WS_CNT1 == 1) {
                        CEP.TRC(SCCGWA, "WANG67");
                        WS_CHG_AMT_TMP = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
                        BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT = WS_CHG_AMT_TOL;
                        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT);
                        B025_CHARGE_AC_CN();
                        if (pgmRtn) return;
                        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT);
                        BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT = WS_CHG_AMT_TMP;
                    }
                } else {
                    B025_CHARGE_AC_CN();
                    if (pgmRtn) return;
                }
                if (SCCGWA.COMM_AREA.REVERSAL_IND != 'Y') {
                    B026_EXCHANGE_CN();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, WS_CHG_FLG);
                if (WS_CHG_FLG == 'Y') {
                    if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                        CEP.TRC(SCCGWA, "WANG71");
                        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_FLG);
                        CEP.TRC(SCCGWA, WS_CNT1);
                        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.CLT_TYPE);
                        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_FLG);
                        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.CLT_TYPE);
                        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
                        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
                        JIBS_tmp_str[3] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
                        JIBS_tmp_str[5] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
                        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_FLG == '1' 
                            && BPCGPFEE.TX_DATA3.CLT_TYPE != '1' 
                            && !JIBS_tmp_str[1].equalsIgnoreCase("9999122") 
                            && !JIBS_tmp_str[3].equalsIgnoreCase("9991231") 
                            && !JIBS_tmp_str[5].equalsIgnoreCase("9999156")) {
                            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
                                && (BPCGPFEE.TX_DATA3.PROC_TYPE != '1' 
                                && BPCGPFEE.TX_DATA3.PROC_TYPE != '2')) {
                                B060_PRE_GENCTR_DATA();
                                if (pgmRtn) return;
                                B061_CALL_BPZSFECT();
                                if (pgmRtn) return;
                            }
                            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                                || (BPCGPFEE.TX_DATA3.PROC_TYPE == '1' 
                                || BPCGPFEE.TX_DATA3.PROC_TYPE == '2')) {
                                CEP.TRC(SCCGWA, "WANG74");
                                B062_PRE_REV_DATA();
                                if (pgmRtn) return;
                                B061_CALL_BPZSFECT();
                                if (pgmRtn) return;
                            }
                            CEP.TRC(SCCGWA, BPCSFECT.CTNO);
                            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_FLG);
                            BPCGPFEE.TX_DATA2.FEE_CTRT_NO = BPCSFECT.CTNO;
                            BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].REF_NO = BPCSFECT.CTNO;
                        }
                    }
                    B027_WRITE_FEE_VCH();
                    if (pgmRtn) return;
                }
                WS_WRT_FHIS_FLG = 'Y';
            }
            CEP.TRC(SCCGWA, "WANG72");
            WS_TS_FLG = "Z";
            S000_TRC_WS_TS();
            if (pgmRtn) return;
            B028_WRITE_HISTORY_CN();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_NHIST_FLG);
            if (WS_NHIST_FLG != 'Y') {
                R000_WRITE_BPTNHIST();
                if (pgmRtn) return;
            }
            WS_TS_FLG = "1";
            S000_TRC_WS_TS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_BAL_LESS_FLG);
            if (WS_BAL_LESS_FLG == 'Y' 
                && BPCOFBAS.VAL.DEBT_METH == '1' 
                && WS_FEE_CTRT_CN.WS_PFEE_CTRT_FLG == 'N') {
                CEP.TRC(SCCGWA, "WANG73");
                if (BPCGPFEE.TX_DATA3.CLT_TYPE == '0') {
                    B022_1_CLT_FEE_PROCESS();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, "WANG74");
                } else if (BPCGPFEE.TX_DATA3.CLT_TYPE == '1') {
                    CEP.TRC(SCCGWA, "WANG75");
                } else if (BPCGPFEE.TX_DATA3.CLT_TYPE == '2') {
                    CEP.TRC(SCCGWA, "WANG76");
                    if (WS_CHG_AMT_ACT > 0) {
                        CEP.TRC(SCCGWA, "WANG77");
                        WS_U_CHG_STS = '1';
                        B022_2_OWE_FEE_PROCESS();
                        if (pgmRtn) return;
                    }
                } else {
                    CEP.TRC(SCCGWA, "WANG78");
                    B024_WRT_OWE_CHG();
                    if (pgmRtn) return;
                }
            }
            if (WS_BAL_LESS_FLG != 'Y' 
                && WS_WRT_FHIS_FLG == 'Y' 
                && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.CLT_TYPE);
                if (BPCGPFEE.TX_DATA3.CLT_TYPE == '0') {
                    CEP.TRC(SCCGWA, "WANG79");
                    B025_1_CLT_SUCC_UPDATE_STS();
                    if (pgmRtn) return;
                } else if (BPCGPFEE.TX_DATA3.CLT_TYPE == '1') {
                    CEP.TRC(SCCGWA, "WANG80");
                } else if (BPCGPFEE.TX_DATA3.CLT_TYPE == '2') {
                    CEP.TRC(SCCGWA, "WANG81");
                    WS_U_CHG_STS = '2';
                    B022_2_OWE_FEE_PROCESS();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, "WANG82");
                }
            }
        }
    }
    public void B024_GET_AC_BY_MEDIUM() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.FEE_DATA_IND == 'Y') {
            CEP.TRC(SCCGWA, "WANG13");
            if ((BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '4' 
                || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '5' 
                || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '7')) {
                CEP.TRC(SCCGWA, "WANG14");
                if (!BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC.equalsIgnoreCase(BPCGPFEE.TX_DATA3.CARD_PSBK_NO)) {
                    BPCGPFEE.TX_DATA3.CARD_PSBK_NO = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
                    CEP.TRC(SCCGWA, "WANG15");
                    CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.CARD_PSBK_NO);
                }
            } else {
                CEP.TRC(SCCGWA, "WANG16");
                BPCGPFEE.TX_DATA3.CARD_PSBK_NO = " ";
            }
        }
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.CARD_PSBK_NO);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            CEP.TRC(SCCGWA, "WANG17");
            WS_CARD_TABLE[WS_CNT1-1].WS_CARD_NO = BPCGPFEE.TX_DATA3.CARD_PSBK_NO;
        }
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY);
        WS_CHG_ORG_AC = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
        if ((BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '4' 
            || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '5' 
            || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '0')) {
            IBS.init(SCCGWA, DCCPACTY);
            if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '0') {
                DCCPACTY.INPUT.AC = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
            } else {
                DCCPACTY.INPUT.AC = WS_CARD_TABLE[WS_CNT1-1].WS_CARD_NO;
            }
            DCCPACTY.INPUT.CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
            if (BPCGPFEE.TX_DATA3.CCY_TYPE != ' ') {
                CEP.TRC(SCCGWA, "WANG21");
                DCCPACTY.INPUT.CCY_TYPE = BPCGPFEE.TX_DATA3.CCY_TYPE;
            } else {
                CEP.TRC(SCCGWA, "WANG22");
                DCCPACTY.INPUT.CCY_TYPE = '1';
            }
            CEP.TRC(SCCGWA, DCCPACTY.INPUT.AC);
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = WS_CARD_TABLE[WS_CNT1-1].WS_CARD_NO;
            CICQACRI.DATA.AGR_NO = DCCPACTY.INPUT.AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            if ((DCCPACTY.OUTPUT.SASB_AC_FLG == 'Y' 
                || DCCPACTY.OUTPUT.CARD_O_N_FLG == 'O') 
                && DCCPACTY.OUTPUT.N_CARD_NO.trim().length() > 0 
                && (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '4' 
                || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '5')) {
                WS_CHG_ORG_AC = DCCPACTY.OUTPUT.N_CARD_NO;
                BPCGPFEE.TX_DATA3.CARD_PSBK_NO = DCCPACTY.OUTPUT.N_CARD_NO;
            }
            CEP.TRC(SCCGWA, CICQACRI.DATA.AGR_NO);
            WS_STD_AC = CICQACRI.DATA.AGR_NO;
            BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC = WS_STD_AC;
        }
    }
    public void B024_ACCOUNT_BR_PROC() throws IOException,SQLException,Exception {
        WS_TS_FLG = "N";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].RGN_NO);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.ACCOUNT_BR);
        CEP.TRC(SCCGWA, BPCOFBAS.VAL.FEE_OWN_FLG);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, K_GL_BRANCH);
        IBS.init(SCCGWA, BPRDDIC);
        BPRDDIC.KEY.NAME = "BR";
        T000_READ_BPTDDIC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, BPRDDIC.LEN);
        CEP.TRC(SCCGWA, K_ZERO_9);
        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].RGN_NO == null) BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].RGN_NO = "";
        JIBS_tmp_int = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].RGN_NO.length();
        for (int i=0;i<9-JIBS_tmp_int;i++) BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].RGN_NO += " ";
        if (K_ZERO_9 == null) K_ZERO_9 = "";
        JIBS_tmp_int = K_ZERO_9.length();
        for (int i=0;i<9-JIBS_tmp_int;i++) K_ZERO_9 += " ";
        if (!(BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].RGN_NO.trim().length() == 0 
            || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].RGN_NO.substring(0, BPRDDIC.LEN).equalsIgnoreCase(K_ZERO_9.substring(0, BPRDDIC.LEN)))) {
            CEP.TRC(SCCGWA, "WANG26");
            if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].RGN_NO.trim().length() == 0) WS_CHG_BR = 0;
            else WS_CHG_BR = Integer.parseInt(BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].RGN_NO);
            CEP.TRC(SCCGWA, "DEVZHJ1");
            CEP.TRC(SCCGWA, WS_CHG_BR);
        } else {
            if (BPCGCFEE.FEE_DATA.ACCOUNT_BR != 0 
                && !IBS.isNumeric(BPCGCFEE.FEE_DATA.ACCOUNT_BR+"") 
                && BPCGCFEE.FEE_DATA.ACCOUNT_BR > 0) {
                WS_CHG_BR = BPCGCFEE.FEE_DATA.ACCOUNT_BR;
                CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.ACCOUNT_BR);
                CEP.TRC(SCCGWA, WS_CHG_BR);
            } else {
                CEP.TRC(SCCGWA, "WANG29");
                if (BPCOFBAS.VAL.FEE_OWN_FLG == '0') {
                    WS_CHG_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    CEP.TRC(SCCGWA, "DEVZHJ3");
                    CEP.TRC(SCCGWA, WS_CHG_BR);
                } else if (BPCOFBAS.VAL.FEE_OWN_FLG == '1') {
                    if ((BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '0' 
                        || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '4' 
                        || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '5')) {
                        B024_GET_AC_BR();
                        if (pgmRtn) return;
                        CEP.TRC(SCCGWA, "DEVZHJ4");
                        CEP.TRC(SCCGWA, WS_CHG_BR);
                    } else {
                        WS_CHG_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                        CEP.TRC(SCCGWA, "DEVZHJ5");
                        CEP.TRC(SCCGWA, WS_CHG_BR);
                    }
                } else if (BPCOFBAS.VAL.FEE_OWN_FLG == '2') {
                    WS_CHG_BR = K_GL_BRANCH;
                    CEP.TRC(SCCGWA, "DEVZHJ6");
                    CEP.TRC(SCCGWA, WS_CHG_BR);
                } else {
                    WS_CHG_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    CEP.TRC(SCCGWA, "DEVZHJ7");
                    CEP.TRC(SCCGWA, WS_CHG_BR);
                }
            }
        }
        CEP.TRC(SCCGWA, WS_FEHIS_BRANCH[WS_CNT1-1].WS_FEHIS_BR);
        if (WS_FEHIS_BRANCH[WS_CNT1-1].WS_FEHIS_BR != 0) {
            WS_CHG_BR = WS_FEHIS_BRANCH[BPCGCFEE.FEE_DATA.FEE_CNT-1].WS_FEHIS_BR;
            CEP.TRC(SCCGWA, "DEVZHJ8");
            CEP.TRC(SCCGWA, WS_CHG_BR);
        }
        CEP.TRC(SCCGWA, WS_CHG_BR);
        if (WS_CHG_BR != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = WS_CHG_BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.ATTR);
            CEP.TRC(SCCGWA, "DEVZHJ9");
            CEP.TRC(SCCGWA, WS_CHG_BR);
        }
        if ((WS_CHG_BR == 0 
            || BPCPQORG.ATTR != '2') 
            && (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '0' 
            || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '4' 
            || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '5' 
            || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '6' 
            || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '7')) {
            B024_GET_AC_BR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "DEVZHJ10");
            CEP.TRC(SCCGWA, WS_CHG_BR);
        }
        CEP.TRC(SCCGWA, WS_CHG_BR);
        BPCGCFEE.FEE_DATA.ACCOUNT_BR = WS_CHG_BR;
        WS_TS_FLG = "O";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
    }
    public void B024_GET_AC_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
        DDCIQBAL.DATA.CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY.equalsIgnoreCase("156")) {
            DDCIQBAL.DATA.CCY_TYPE = '1';
        } else {
            DDCIQBAL.DATA.CCY_TYPE = BPCGPFEE.TX_DATA3.CCY_TYPE;
        }
        S000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.OWNER_BRDP);
        if (DDCIQBAL.DATA.OWNER_BRDP != 0) {
            WS_CHG_BR = DDCIQBAL.DATA.OWNER_BRDP;
            CEP.TRC(SCCGWA, WS_CHG_BR);
        }
        CEP.TRC(SCCGWA, WS_CHG_BR);
    }
    public void B024_GET_BALANCE() throws IOException,SQLException,Exception {
        WS_TS_FLG = "P";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
        WS_BAL_LESS_FLG = 'N';
        if (SCCGWA.COMM_AREA.SERV_CODE == null) SCCGWA.COMM_AREA.SERV_CODE = "";
        JIBS_tmp_int = SCCGWA.COMM_AREA.SERV_CODE.length();
        for (int i=0;i<7-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.SERV_CODE += " ";
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SERV_CODE.substring(0, 7));
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CCY);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY);
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.CLT_TYPE);
        if (SCCGWA.COMM_AREA.SERV_CODE == null) SCCGWA.COMM_AREA.SERV_CODE = "";
        JIBS_tmp_int = SCCGWA.COMM_AREA.SERV_CODE.length();
        for (int i=0;i<7-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.SERV_CODE += " ";
        if (SCCGWA.COMM_AREA.SERV_CODE.substring(0, 3).equalsIgnoreCase("BSP") 
            && BPCGPFEE.TX_DATA3.CLT_TYPE != '1' 
            && BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CCY.equalsIgnoreCase(BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY)) {
            CEP.TRC(SCCGWA, "CALL-FOR-BAL-FROM-DD!!!");
            IBS.init(SCCGWA, DDCIMCCY);
            if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '4' 
                || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '5') {
                DDCIMCCY.DATA[1-1].AC = WS_STD_AC;
            } else {
                DDCIMCCY.DATA[1-1].AC = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
            }
            CEP.TRC(SCCGWA, DDCIMCCY.DATA[1-1].AC);
            DDCIMCCY.DATA[1-1].CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
            CEP.TRC(SCCGWA, DDCIMCCY.DATA[1-1].CCY);
            DDCIMCCY.DATA[1-1].CCY_TYPE = BPCGPFEE.TX_DATA3.CCY_TYPE;
            CEP.TRC(SCCGWA, DDCIMCCY.DATA[1-1].CCY_TYPE);
            S000_CALL_DDZIMCCY();
            if (pgmRtn) return;
            WS_CHG_AMT_ACT = DDCIMCCY.DATA[1-1].CURR_BAL;
            CEP.TRC(SCCGWA, WS_CHG_AMT_ACT);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT);
            if (SCCGWA.COMM_AREA.SERV_CODE == null) SCCGWA.COMM_AREA.SERV_CODE = "";
            JIBS_tmp_int = SCCGWA.COMM_AREA.SERV_CODE.length();
            for (int i=0;i<7-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.SERV_CODE += " ";
            if (SCCGWA.COMM_AREA.SERV_CODE.substring(0, 3).equalsIgnoreCase("BSP")) {
                IBS.init(SCCGWA, BPRFABF);
                BPRFABF.KEY.CHG_AC = WS_CHG_ORG_AC;
                WS_CHG_ORG_AC_F = WS_CHG_ORG_AC;
                BPRFABF.KEY.CCY = BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_CCY;
                BPRFABF.KEY.CCY_TYPE = BPCGPFEE.TX_DATA3.CCY_TYPE;
                BPRFABF.KEY.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
                T000_READ_REC_PROC();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    WS_CHG_F_AC_T[1-1].WS_CHG_F_AC = BPRFABF.CHG_AC1;
                    WS_CHG_F_AC_T[2-1].WS_CHG_F_AC = BPRFABF.CHG_AC2;
                    WS_CHG_F_AC_T[3-1].WS_CHG_F_AC = BPRFABF.CHG_AC3;
                    WS_CHG_F_AC_T[4-1].WS_CHG_F_AC = BPRFABF.CHG_AC4;
                    WS_CHG_F_AC_T[5-1].WS_CHG_F_AC = BPRFABF.CHG_AC5;
                    CEP.TRC(SCCGWA, BPRFABF.CHG_AC1);
                    CEP.TRC(SCCGWA, BPRFABF.CHG_AC2);
                    CEP.TRC(SCCGWA, BPRFABF.CHG_AC3);
                    CEP.TRC(SCCGWA, BPRFABF.CHG_AC4);
                    CEP.TRC(SCCGWA, BPRFABF.CHG_AC5);
                    CEP.TRC(SCCGWA, WS_CHG_F_AC_T[1-1].WS_CHG_F_AC);
                    CEP.TRC(SCCGWA, WS_CHG_F_AC_T[2-1].WS_CHG_F_AC);
                    CEP.TRC(SCCGWA, WS_CHG_F_AC_T[3-1].WS_CHG_F_AC);
                    CEP.TRC(SCCGWA, WS_CHG_F_AC_T[4-1].WS_CHG_F_AC);
                    CEP.TRC(SCCGWA, WS_CHG_F_AC_T[5-1].WS_CHG_F_AC);
                    for (WS_FABF_CNT = 1; WS_FABF_CNT <= 5 
                        && WS_BAL_ENOU_FLG != 'Y'; WS_FABF_CNT += 1) {
                        if (WS_CHG_F_AC_T[WS_FABF_CNT-1].WS_CHG_F_AC.trim().length() > 0) {
                            IBS.init(SCCGWA, DDCIMCCY);
                            DDCIMCCY.DATA[1-1].AC = WS_CHG_F_AC_T[WS_FABF_CNT-1].WS_CHG_F_AC;
                            CEP.TRC(SCCGWA, DDCIMCCY.DATA[1-1].AC);
                            DDCIMCCY.DATA[1-1].CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
                            CEP.TRC(SCCGWA, DDCIMCCY.DATA[1-1].CCY);
                            DDCIMCCY.DATA[1-1].CCY_TYPE = BPCGPFEE.TX_DATA3.CCY_TYPE;
                            CEP.TRC(SCCGWA, DDCIMCCY.DATA[1-1].CCY_TYPE);
                            S000_CALL_DDZIMCCY();
                            if (pgmRtn) return;
                            CEP.TRC(SCCGWA, DDCIMCCY.DATA[1-1].CURR_BAL);
                            WS_FABF_AMT_N[WS_FABF_CNT-1].WS_FABF_AMT = DDCIMCCY.DATA[1-1].CURR_BAL;
                            CEP.TRC(SCCGWA, WS_FABF_AMT_N[WS_FABF_CNT-1].WS_FABF_AMT);
                            if (WS_FABF_AMT_N[WS_FABF_CNT-1].WS_FABF_AMT >= BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT) {
                                WS_BAL_ENOU_FLG = 'Y';
                                WS_CHG_ORG_AC = WS_CHG_F_AC_T[WS_FABF_CNT-1].WS_CHG_F_AC;
                                CEP.TRC(SCCGWA, WS_CHG_ORG_AC);
                            }
                        }
                    }
                    if (WS_CHG_ORG_AC.equalsIgnoreCase(WS_CHG_ORG_AC_F)) {
                        WS_CHG_ORG_AC = WS_CHG_F_AC_T[1-1].WS_CHG_F_AC;
                    }
                    if (WS_BAL_ENOU_FLG != 'Y') {
                        WS_BAL_LESS_FLG = 'Y';
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, WS_BAL_LESS_FLG);
        WS_TS_FLG = "Q";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        BPTFABF_RD = new DBParm();
        BPTFABF_RD.TableName = "BPTFABF";
        BPTFABF_RD.where = "CHG_AC = :BPRFABF.KEY.CHG_AC "
            + "AND CCY = :BPRFABF.KEY.CCY "
            + "AND CCY_TYPE = :BPRFABF.KEY.CCY_TYPE "
            + "AND EFF_DATE <= :BPRFABF.KEY.EFF_DATE "
            + "AND EXP_DATE >= :BPRFABF.KEY.EFF_DATE";
        IBS.READ(SCCGWA, BPRFABF, this, BPTFABF_RD);
    }
    public void B022_1_CLT_FEE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRFPDT);
        IBS.init(SCCGWA, BPRFADT);
        BPRFADT.KEY.TRT_DT = BPCGPFEE.TX_DATA3.BAT_OTRT_DT;
        BPRFADT.KEY.JRN_NO = "" + BPCGPFEE.TX_DATA3.BAT_OTRT_JRN;
        JIBS_tmp_int = BPRFADT.KEY.JRN_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPRFADT.KEY.JRN_NO = "0" + BPRFADT.KEY.JRN_NO;
        BPRFADT.KEY.JRN_SEQ = BPCGPFEE.TX_DATA3.BAT_OTRT_SEQ;
        BPCRFPDT.INFO.FDT_TYP = '0';
        BPCRFPDT.INFO.FUNC = 'R';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        if (BPCRFPDT.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPRFADT.CHG_STS = '2';
        BPRFADT.UPDATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFADT.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
        BPRFPDT.CHG_BR = BPRFADT.CHG_BR;
        if (BPCOFBAS.VAL.DEBT_METH == '1') {
            BPRFADT.ACC_CHG_AMT = BPRFADT.ACC_CHG_AMT + WS_CHG_AMT_ACT;
            BPRFADT.CUR_OWE_AMT = BPRFADT.CHG_AMT - BPRFADT.ACC_CHG_AMT;
        }
        BPCRFPDT.INFO.FUNC = 'M';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCRFPDT);
        IBS.init(SCCGWA, BPRFPDT);
        BPRFPDT.KEY.TRT_DT = BPRFADT.KEY.TRT_DT;
        BPRFPDT.KEY.JRN_NO = BPRFADT.KEY.JRN_NO;
        BPRFPDT.KEY.JRN_SEQ = BPRFADT.KEY.JRN_SEQ;
        BPRFPDT.CHG_AC = BPRFADT.CHG_AC;
        BPRFPDT.CARD_PSBK_NO = BPRFADT.CARD_PSBK_NO;
        BPRFPDT.TX_CI = BPRFADT.TX_CI;
        BPRFPDT.PRD_CD = BPRFADT.PRD_CD;
        BPRFPDT.CHG_AC_TY = BPRFADT.CHG_AC_TY;
        BPRFPDT.FEE_SRC = BPRFADT.FEE_SRC;
        BPRFPDT.FEE_CODE = BPRFADT.FEE_CODE;
        BPRFPDT.CCY = BPRFADT.CCY;
        BPRFPDT.CCY_TYPE = BPRFADT.CCY_TYPE;
        BPRFPDT.SALE_DATE = BPRFADT.SALE_DATE;
        BPRFPDT.CHG_BR = BPRFADT.CHG_BR;
        if (BPCOFBAS.VAL.DEBT_METH == '1') {
            BPRFPDT.CHG_AMT = BPRFADT.CHG_AMT - WS_CHG_AMT_ACT;
        }
        if (BPCOFBAS.VAL.DEBT_METH == '0') {
            BPRFPDT.CHG_AMT = BPRFADT.CHG_AMT;
        }
        BPRFPDT.ACC_RECH_CNT = 0;
        BPRFPDT.CUR_OWE_AMT = BPRFPDT.CHG_AMT;
        BPRFPDT.ACC_CHG_AMT = 0;
        BPRFPDT.VAT_AMT = BPRFADT.VAT_AMT;
        CEP.TRC(SCCGWA, BPRFPDT.VAT_AMT);
        BPRFPDT.CMMT_NO = BPRFADT.CMMT_NO;
        BPRFPDT.BSNS_NO = BPRFADT.BSNS_NO;
        BPRFPDT.AMO_FLG = BPRFADT.AMO_FLG;
        BPRFPDT.AMO_SDT = BPRFADT.AMO_SDT;
        BPRFPDT.AMO_EDT = BPRFADT.AMO_EDT;
        BPRFPDT.PRC_STS = BPRFADT.PRC_STS;
        BPRFPDT.CHG_STS = '0';
        BPRFPDT.TRT_CHNL = BPRFADT.TRT_CHNL;
        BPRFPDT.TRT_BR = BPRFADT.TRT_BR;
        BPRFPDT.SRC_TR_CD = BPRFADT.SRC_TR_CD;
        if (BPRFADT.SRC_TR_CD.trim().length() > 0) {
            WS_TR_CD = BPRFADT.SRC_TR_CD;
            S000_GET_TR_NAME();
            if (pgmRtn) return;
            BPRFPDT.SRC_TR_NAME = WS_TR_NAME;
        }
        BPRFPDT.REMARK = BPRFADT.REMARK;
        BPRFPDT.CREATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFPDT.CREATE_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPRFPDT.UPDATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFPDT.CREATE_TELL = SCCGWA.COMM_AREA.TL_ID;
        BPRFPDT.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
        BPRFPDT.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPRFPDT.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        BPCRFPDT.INFO.FUNC = 'C';
        BPCRFPDT.INFO.FDT_TYP = '2';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        if (BPCRFPDT.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B022_2_OWE_FEE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRFPDT);
        IBS.init(SCCGWA, BPRFPDT);
        BPRFPDT.KEY.TRT_DT = BPCGPFEE.TX_DATA3.BAT_OTRT_DT;
        BPRFPDT.KEY.JRN_NO = "" + BPCGPFEE.TX_DATA3.BAT_OTRT_JRN;
        JIBS_tmp_int = BPRFPDT.KEY.JRN_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPRFPDT.KEY.JRN_NO = "0" + BPRFPDT.KEY.JRN_NO;
        BPRFPDT.KEY.JRN_SEQ = BPCGPFEE.TX_DATA3.BAT_OTRT_SEQ;
        BPCRFPDT.INFO.FDT_TYP = '2';
        BPCRFPDT.INFO.FUNC = 'R';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        if (BPCRFPDT.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_U_CHG_STS);
        if (WS_U_CHG_STS == '1') {
            BPRFPDT.CUR_OWE_AMT = BPRFPDT.CUR_OWE_AMT - WS_CHG_AMT_ACT;
            BPRFPDT.ACC_CHG_AMT = BPRFPDT.ACC_CHG_AMT + WS_CHG_AMT_ACT;
        } else {
            BPRFPDT.CUR_OWE_AMT = 0;
            BPRFPDT.ACC_CHG_AMT = BPRFPDT.CHG_AMT;
        }
        CEP.TRC(SCCGWA, BPRFPDT.CUR_OWE_AMT);
        CEP.TRC(SCCGWA, BPRFPDT.ACC_CHG_AMT);
        BPRFPDT.CHG_STS = WS_U_CHG_STS;
        CEP.TRC(SCCGWA, BPRFPDT.CHG_STS);
        BPRFPDT.UPDATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFPDT.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
        BPCRFPDT.INFO.FUNC = 'M';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
    }
    public void B023_FEE_COLLECTION_PROC() throws IOException,SQLException,Exception {
        WS_TS_FLG = "H1";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.CLT_TYPE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SERV_CODE);
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA2.FEE_CTRT_NO);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY);
        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '4') {
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.FUNC = 'I';
            CICQACRL.DATA.AC_NO = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
            CEP.TRC(SCCGWA, CICQACRL.DATA.AC_NO);
            S000_CALL_CIZQACRL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRL.RC.RC_CODE);
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_REL);
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
            if (CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("04") 
                && CICQACRL.RC.RC_CODE == 0) {
                BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC = CICQACRL.O_DATA.O_REL_AC_NO;
                IBS.init(SCCGWA, CICQACRI);
                CICQACRI.FUNC = 'A';
                CICQACRI.DATA.AGR_NO = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
                S000_CALL_CIZQACRI();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_PROD_CD);
                BPCGCFEE.FEE_DATA.FEE_INFO[1-1].PROD_CD = CICQACRI.O_DATA.O_PROD_CD;
            }
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC);
        }
        if (SCCGWA.COMM_AREA.SERV_CODE == null) SCCGWA.COMM_AREA.SERV_CODE = "";
        JIBS_tmp_int = SCCGWA.COMM_AREA.SERV_CODE.length();
        for (int i=0;i<7-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.SERV_CODE += " ";
        if (SCCGWA.COMM_AREA.SERV_CODE == null) SCCGWA.COMM_AREA.SERV_CODE = "";
        JIBS_tmp_int = SCCGWA.COMM_AREA.SERV_CODE.length();
        for (int i=0;i<7-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.SERV_CODE += " ";
        if ((!SCCGWA.COMM_AREA.SERV_CODE.substring(0, 3).equalsIgnoreCase("BSP") 
            && BPCGPFEE.TX_DATA2.FEE_CTRT_NO.trim().length() == 0 
            && (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '0' 
            || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '4' 
            || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '5') 
            && ((SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && BPCGPFEE.TX_DATA3.CLT_TYPE == '1') 
            || SCCGWA.COMM_AREA.CANCEL_IND != 'Y')) 
            || (SCCGWA.COMM_AREA.SERV_CODE.substring(0, 3).equalsIgnoreCase("BSP"))) {
            CEP.TRC(SCCGWA, "WANG45");
            if (BPCGPFEE.CUS_DATA.CUS_INFO[1-1].CUS_NO.trim().length() > 0 
                && SCCGWA.COMM_AREA.FEE_DATA_IND != 'Y') {
                CEP.TRC(SCCGWA, "WANG46");
                WS_SGN_CINO = BPCGPFEE.CUS_DATA.CUS_INFO[1-1].CUS_NO;
            } else {
                CEP.TRC(SCCGWA, "WANG47");
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.AGR_NO = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
                S000_CALL_CIZACCU();
                if (pgmRtn) return;
                if (CICACCU.RC.RC_CODE != 0) {
                    CEP.TRC(SCCGWA, "WANG49");
                    WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
                CEP.TRC(SCCGWA, CICACCU.DATA.ENTY_TYP);
                CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
                WS_SGN_CINO = CICACCU.DATA.CI_NO;
                BPCGPFEE.CUS_DATA.CUS_INFO[1-1].CUS_NO = CICACCU.DATA.CI_NO;
            }
            WS_SKPCHG_FLG = 'N';
            WS_SKPALL_FLG = 'N';
            CEP.TRC(SCCGWA, WS_SGN_CINO);
            T000_STARTBR_BPTFEAG_01();
            if (pgmRtn) return;
            T000_READNEXT_BPTFEAG();
            if (pgmRtn) return;
            while (WS_FILE_STS != 'N' 
                && WS_SKPCHG_FLG != 'Y') {
                CEP.TRC(SCCGWA, BPRFEAG);
                CEP.TRC(SCCGWA, WS_SGN_CINO);
                CEP.TRC(SCCGWA, BPRFEAG.KEY.CLT_CI_NO);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
                CEP.TRC(SCCGWA, BPRFEAG.KEY.EFF_DATE);
                CEP.TRC(SCCGWA, BPRFEAG.EXP_DATE);
                CEP.TRC(SCCGWA, BPRFEAG.KEY.CLT_AC);
                CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_AC);
                CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.PROC_TYPE);
                CEP.TRC(SCCGWA, "ZHENG3");
                CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.FEE_CTRT);
                if (BPCGPFEE.TX_DATA3.CLT_TYPE == '1') {
                    CEP.TRC(SCCGWA, "WANG52");
                    WS_PFEE_PROC_DT = BPCGPFEE.TX_DATA3.PROC_DT;
                } else {
                    CEP.TRC(SCCGWA, "WANG53");
                    WS_PFEE_PROC_DT = SCCGWA.COMM_AREA.AC_DATE;
                }
                CEP.TRC(SCCGWA, WS_PFEE_PROC_DT);
                if (((WS_SGN_CINO.equalsIgnoreCase(BPRFEAG.KEY.CLT_CI_NO) 
                    && BPRFEAG.KEY.CLT_AC.trim().length() == 0) 
                    || (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC.equalsIgnoreCase(BPRFEAG.KEY.CLT_AC) 
                    && BPRFEAG.KEY.CLT_AC.trim().length() > 0 
                    && BPCGPFEE.TX_DATA3.PROC_TYPE == ' ') 
                    || (WS_CHG_ORG_AC.equalsIgnoreCase(BPRFEAG.KEY.CLT_AC) 
                    && BPRFEAG.KEY.CLT_AC.trim().length() > 0 
                    && BPCGPFEE.TX_DATA3.PROC_TYPE == ' ') 
                    || ((BPCGPFEE.TX_DATA3.PROC_TYPE == '0' 
                    || BPCGPFEE.TX_DATA3.PROC_TYPE == '1' 
                    || BPCGPFEE.TX_DATA3.PROC_TYPE == '2' 
                    || BPCGPFEE.TX_DATA3.PROC_TYPE == '3' 
                    || BPCGPFEE.TX_DATA3.CLT_TYPE == '1') 
                    && BPCGPFEE.TX_DATA3.FEE_CTRT.equalsIgnoreCase(BPRFEAG.KEY.CLT_AC))) 
                    && WS_PFEE_PROC_DT >= BPRFEAG.KEY.EFF_DATE 
                    && WS_PFEE_PROC_DT <= BPRFEAG.EXP_DATE) {
                    WS_SKPCHG_FLG = 'Y';
                    CEP.TRC(SCCGWA, WS_SKPCHG_FLG);
                    WS_FEE_CDS[1-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE1;
                    WS_FEE_CDS[2-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE2;
                    WS_FEE_CDS[3-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE3;
                    WS_FEE_CDS[4-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE4;
                    WS_FEE_CDS[5-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE5;
                    WS_FEE_CDS[6-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE6;
                    WS_FEE_CDS[7-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE7;
                    WS_FEE_CDS[8-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE8;
                    WS_FEE_CDS[9-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE9;
                    WS_FEE_CDS[10-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE10;
                    WS_FEE_CDS[11-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE11;
                    WS_FEE_CDS[12-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE12;
                    WS_FEE_CDS[13-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE13;
                    WS_FEE_CDS[14-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE14;
                    WS_FEE_CDS[15-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE15;
                    WS_FEE_CDS[16-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE16;
                    WS_FEE_CDS[17-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE17;
                    WS_FEE_CDS[18-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE18;
                    WS_FEE_CDS[19-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE19;
                    WS_FEE_CDS[20-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE20;
                    CEP.TRC(SCCGWA, WS_FEE_CDS[1-1].WS_FEE_CD1);
                    CEP.TRC(SCCGWA, WS_FEE_CDS[2-1].WS_FEE_CD1);
                    CEP.TRC(SCCGWA, WS_FEE_CDS[3-1].WS_FEE_CD1);
                    WS_PFEE_PROC_DT = 0;
                }
                if (WS_SKPCHG_FLG != 'Y') {
                    T000_READNEXT_BPTFEAG();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, "WANG54");
                }
            }
            T000_ENDBR_BPTFEAG();
            if (pgmRtn) return;
            if (WS_SKPCHG_FLG != 'Y') {
                WS_PROD_CD = BPCGCFEE.FEE_DATA.FEE_INFO[1-1].PROD_CD;
                T000_STARTBR_BPTFEAG_02();
                if (pgmRtn) return;
                T000_READNEXT_BPTFEAG();
                if (pgmRtn) return;
                while (WS_FILE_STS != 'N' 
                    && WS_SKPCHG_FLG != 'Y') {
                    if (BPCGPFEE.TX_DATA3.CLT_TYPE == '1') {
                        WS_PFEE_PROC_DT = BPCGPFEE.TX_DATA3.PROC_DT;
                    } else {
                        WS_PFEE_PROC_DT = SCCGWA.COMM_AREA.AC_DATE;
                    }
                    CEP.TRC(SCCGWA, WS_PFEE_PROC_DT);
                    CEP.TRC(SCCGWA, WS_PROD_CD);
                    CEP.TRC(SCCGWA, BPRFEAG.KEY.CLT_AC);
                    CEP.TRC(SCCGWA, BPRFEAG.KEY.CLT_CI_NO);
                    if (((WS_PROD_CD.equalsIgnoreCase(BPRFEAG.KEY.PRDT_CODE) 
                        && BPRFEAG.KEY.CLT_AC.trim().length() == 0 
                        && BPRFEAG.KEY.CLT_CI_NO.trim().length() == 0)) 
                        && WS_PFEE_PROC_DT >= BPRFEAG.KEY.EFF_DATE 
                        && WS_PFEE_PROC_DT <= BPRFEAG.EXP_DATE) {
                        WS_SKPCHG_FLG = 'Y';
                        CEP.TRC(SCCGWA, WS_SKPCHG_FLG);
                        WS_FEE_CDS[1-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE1;
                        WS_FEE_CDS[2-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE2;
                        WS_FEE_CDS[3-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE3;
                        WS_FEE_CDS[4-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE4;
                        WS_FEE_CDS[5-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE5;
                        WS_FEE_CDS[6-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE6;
                        WS_FEE_CDS[7-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE7;
                        WS_FEE_CDS[8-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE8;
                        WS_FEE_CDS[9-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE9;
                        WS_FEE_CDS[10-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE10;
                        WS_FEE_CDS[11-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE11;
                        WS_FEE_CDS[12-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE12;
                        WS_FEE_CDS[13-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE13;
                        WS_FEE_CDS[14-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE14;
                        WS_FEE_CDS[15-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE15;
                        WS_FEE_CDS[16-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE16;
                        WS_FEE_CDS[17-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE17;
                        WS_FEE_CDS[18-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE18;
                        WS_FEE_CDS[19-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE19;
                        WS_FEE_CDS[20-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE20;
                        CEP.TRC(SCCGWA, WS_FEE_CDS[1-1].WS_FEE_CD1);
                        CEP.TRC(SCCGWA, WS_FEE_CDS[2-1].WS_FEE_CD1);
                        CEP.TRC(SCCGWA, WS_FEE_CDS[3-1].WS_FEE_CD1);
                        WS_PFEE_PROC_DT = 0;
                    }
                    if (WS_SKPCHG_FLG != 'Y') {
                        T000_READNEXT_BPTFEAG();
                        if (pgmRtn) return;
                    }
                }
                T000_ENDBR_BPTFEAG();
                if (pgmRtn) return;
            }
        }
        WS_TS_FLG = "H2";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
    }
    public void B025_1_CLT_SUCC_UPDATE_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRFPDT);
        IBS.init(SCCGWA, BPRFADT);
        BPRFADT.KEY.TRT_DT = BPCGPFEE.TX_DATA3.BAT_OTRT_DT;
        BPRFADT.KEY.JRN_NO = "" + BPCGPFEE.TX_DATA3.BAT_OTRT_JRN;
        JIBS_tmp_int = BPRFADT.KEY.JRN_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPRFADT.KEY.JRN_NO = "0" + BPRFADT.KEY.JRN_NO;
        BPRFADT.KEY.JRN_SEQ = BPCGPFEE.TX_DATA3.BAT_OTRT_SEQ;
        BPCRFPDT.INFO.FDT_TYP = BPCGPFEE.TX_DATA3.CLT_TYPE;
        BPCRFPDT.INFO.FUNC = 'R';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        if (BPCRFPDT.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPRFADT.CUR_OWE_AMT = 0;
        BPRFADT.ACC_CHG_AMT = BPRFADT.CHG_AMT;
        BPRFADT.CHG_STS = '2';
        BPRFADT.UPDATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFADT.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
        BPCRFPDT.INFO.FUNC = 'M';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
    }
    public void B023_1_DRMCR_PROC() throws IOException,SQLException,Exception {
        if (BPCGPFEE.TX_DATA3.CLT_TYPE == '1' 
            || BPCGPFEE.TX_DATA3.DRMCR_FLG == 'Y' 
            || BPCOCSVR.VAL.DRMCR_FLG == 'Y') {
            CEP.TRC(SCCGWA, "WANG2");
            WS_DRMCR_FLG = 'Y';
        }
        if (WS_DRMCR_FLG == 'Y' 
            || BPCGPFEE.TX_DATA3.HLD_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "WANG3");
            WS_CHG_AMT_TOL = 0;
            for (WS_CNT1 = 1; WS_CNT1 <= BPCGCFEE.FEE_DATA.FEE_CNT; WS_CNT1 += 1) {
                WS_CHG_AMT_TOL = WS_CHG_AMT_TOL + BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
            }
        }
    }
    public void B023_2_ACC_CLT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.CLT_TYPE);
        if (BPCGPFEE.TX_DATA3.CLT_TYPE == '1') {
            CEP.TRC(SCCGWA, "WANG83");
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                CEP.TRC(SCCGWA, "WANG84");
                B023_2_ACC_CLT_PROC_N();
                if (pgmRtn) return;
            }
        }
    }
    public void B023_2_ACC_CLT_PROC_N() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFADT);
        WS_PFEE_CMMT_NO = BPCGPFEE.TX_DATA3.FEE_CTRT;
        WS_SGN_AC = BPCGPFEE.TX_DATA3.FEE_CTRT;
        WS_FDT_TYP = '1';
        WS_PFEE_PROC_DT = BPCGPFEE.TX_DATA3.PROC_DT;
        WS_PROC_DT = BPCGPFEE.TX_DATA3.PROC_DT;
        WS_PRC_STS = '0';
        WS_I = 0;
        WS_J = 0;
        WS_LAST_BR = 0;
        WS_LAST_FEE_CODE = " ";
        T000_STARTBR_BPTFADT_UPD();
        if (pgmRtn) return;
        T000_READNEXT_BPTFADT();
        if (pgmRtn) return;
        while (WS_FILE_STS != 'N' 
            && WS_I <= 5 
            && WS_J <= 3) {
            if (BPRFADT.CHG_BR != WS_LAST_BR) {
                WS_I += 1;
                WS_LAST_BR = BPRFADT.CHG_BR;
                WS_CHG_BR = BPRFADT.CHG_BR;
                CEP.TRC(SCCGWA, "DEVZHJ12");
                CEP.TRC(SCCGWA, WS_CHG_BR);
            }
            if (!BPRFADT.FEE_CODE.equalsIgnoreCase(WS_LAST_FEE_CODE)) {
                CEP.TRC(SCCGWA, "WANG85");
                WS_J += 1;
                WS_LAST_FEE_CODE = BPRFADT.FEE_CODE;
                IBS.init(SCCGWA, BPCOFBAS);
                BPCOFBAS.KEY.FEE_CODE = BPRFADT.FEE_CODE;
                BPCOFBAS.FUNC = 'I';
                S000_CALL_BPZFUBAS();
                if (pgmRtn) return;
                WS_FEE_TX_MMO = BPCOFBAS.VAL.FEE_TX_MMO;
                WS_AMO_FLG = BPCOFBAS.VAL.FEE_AMOT_FLG;
                WS_FEE_CHG_TYPE = BPCOFBAS.VAL.FEE_CHG_TYPE;
            }
            IBS.init(SCCGWA, BPCSFECT);
            if (WS_AMO_FLG == '1' 
                || BPRFADT.AMO_FLG == '1') {
                CEP.TRC(SCCGWA, "WANG86");
                WS_FEE_CODE_GL = BPRFADT.FEE_CODE;
                B060_PRE_GENCTR_DATA_CLT();
                if (pgmRtn) return;
                B061_CALL_BPZSFECT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCSFECT.CTNO);
            }
            B028_WRITE_HISTORY_CLT();
            if (pgmRtn) return;
            BPRFADT.CHG_STS = '2';
            CEP.TRC(SCCGWA, BPRFADT.CHG_STS);
            BPRFADT.UPDATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CEP.TRC(SCCGWA, BPRFADT.UPDATE_DATE);
            BPRFADT.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
            CEP.TRC(SCCGWA, BPRFADT.LAST_TELL);
            BPRFADT.ACC_CHG_AMT = BPRFADT.CHG_AMT;
            BPRFADT.CUR_OWE_AMT = 0;
            T000_REWRITE_BPTFADT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "REWRITE-BPTFADT");
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            T000_READNEXT_BPTFADT();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTFADT();
        if (pgmRtn) return;
    }
    public void B028_WRITE_HISTORY_CLT() throws IOException,SQLException,Exception {
        WS_TS_FLG = "L";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRFEHIS);
        BPRFEHIS.KEY.AC_DT = BPRFADT.KEY.TRT_DT;
        if (BPRFADT.KEY.JRN_NO.trim().length() == 0) BPRFEHIS.KEY.JRNNO = 0;
        else BPRFEHIS.KEY.JRNNO = Long.parseLong(BPRFADT.KEY.JRN_NO);
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.JRNNO);
        BPRFEHIS.KEY.JRN_SEQ = BPRFADT.KEY.JRN_SEQ;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.ENTY_TYP = '1';
        CICACCU.DATA.AGR_NO = BPRFADT.CHG_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        BPRFEHIS.TX_CI = CICACCU.DATA.CI_NO;
        CEP.TRC(SCCGWA, BPRFEHIS.TX_CI);
        BPRFEHIS.TX_PROD = BPRFADT.PRD_CD;
        BPRFEHIS.FEE_CODE = BPRFADT.FEE_CODE;
        BPRFEHIS.TX_MMO = WS_FEE_TX_MMO;
        BPRFEHIS.DRCRFLG = 'C';
        BPRFEHIS.REQFM_NO = SCCGWA.COMM_AREA.REQ_SYSTEM;
        BPRFEHIS.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPRFEHIS.CHG_BR = BPRFADT.CHG_BR;
        BPRFEHIS.TX_AC = BPRFADT.CHG_AC;
        BPRFEHIS.CHG_AC = BPRFADT.CHG_AC;
        BPRFEHIS.CHG_AC_TY = '0';
        BPRFEHIS.FEE_CCY = BPRFADT.CCY;
        BPRFEHIS.FEE_BAS = BPRFADT.CHG_AMT;
        BPRFEHIS.FEE_AMT = BPRFADT.CHG_AMT;
        BPRFEHIS.CHG_CCY = BPRFADT.CCY;
        BPRFEHIS.CHG_BAS = BPRFADT.CHG_AMT;
        BPRFEHIS.CHG_AMT = BPRFADT.CHG_AMT;
        BPRFEHIS.ADJ_AMT = BPRFADT.CHG_AMT;
        BPRFEHIS.VAT_AMT = BPRFADT.VAT_AMT;
        CEP.TRC(SCCGWA, BPRFEHIS.VAT_AMT);
        BPRFEHIS.CHG_FLG = WS_FEE_CHG_TYPE;
        BPRFEHIS.FEE_CTRT = BPRFADT.CMMT_NO;
        BPRFEHIS.CCY_TYPE = '1';
        BPRFEHIS.BSNS_NO = BPRFADT.BSNS_NO;
        BPRFEHIS.REMARK = BPRFADT.REMARK;
        BPRFEHIS.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRFEHIS.TX_DT = SCCGWA.COMM_AREA.TR_DATE;
        BPRFEHIS.TX_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPRFEHIS.TX_STS = 'N';
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPRFEHIS.TX_STS = 'R';
        }
        BPRFEHIS.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRFEHIS.SUP_TEL1 = SCCGMSG.SUP1_ID;
        BPRFEHIS.SUP_TEL2 = SCCGMSG.SUP2_ID;
        if (WS_BAL_LESS_FLG == 'Y') {
            BPRFEHIS.CHG_AMT = WS_CHG_AMT_ACT;
            BPRFEHIS.ADJ_AMT = WS_CHG_AMT_ACT;
            BPRFEHIS.REMARK = BPRFADT.REMARK;
        }
        if (BPRFADT.AMO_FLG == '1') {
            BPRFEHIS.FEE_CTRT_NO = BPCSFECT.CTNO;
        }
        BPRFEHIS.BLOB_PFEE_DATA = IBS.CLS2CPY(SCCGWA, BPCGPFEE);
        CEP.TRC(SCCGWA, BPRFEHIS.BLOB_PFEE_DATA.trim().length());
        CEP.TRC(SCCGWA, BPRFEHIS.BLOB_PFEE_DATA);
        T000_WRITE_BPTFEHIS_CN();
        if (pgmRtn) return;
        WS_TS_FLG = "M";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
    }
    public void B028_GET_HISTORY_CLT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFEHIS);
        if (BPRFADT.KEY.JRN_NO.trim().length() == 0) BPRFEHIS.KEY.JRNNO = 0;
        else BPRFEHIS.KEY.JRNNO = Long.parseLong(BPRFADT.KEY.JRN_NO);
        BPRFEHIS.KEY.AC_DT = BPRFADT.KEY.TRT_DT;
        BPRFEHIS.KEY.JRN_SEQ = BPRFADT.KEY.JRN_SEQ;
        CEP.TRC(SCCGWA, BPRFADT.KEY.TRT_DT);
        CEP.TRC(SCCGWA, BPRFADT.KEY.JRN_NO);
        CEP.TRC(SCCGWA, BPRFADT.KEY.JRN_SEQ);
        T000_READ_BPTFEHIS_UPD_CN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_FEHIS_FLG);
    }
    public void B028_DEL_HISTORY_CLT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.JRNNO);
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.JRN_SEQ);
        T000_DELETE_BPTFEHIS_CN();
        if (pgmRtn) return;
    }
    public void B024_WRT_PENDING_CHG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRFPDT);
        IBS.init(SCCGWA, BPRFADT);
        BPRFADT.KEY.TRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPRFADT.KEY.TRT_DT);
        BPRFADT.KEY.JRN_NO = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = BPRFADT.KEY.JRN_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPRFADT.KEY.JRN_NO = "0" + BPRFADT.KEY.JRN_NO;
        CEP.TRC(SCCGWA, BPRFADT.KEY.JRN_NO);
        WS_JRN_SEQ = (short) (WS_JRN_SEQ + 1);
        BPRFADT.KEY.JRN_SEQ = WS_JRN_SEQ;
        CEP.TRC(SCCGWA, BPRFADT.KEY.JRN_SEQ);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC);
        CEP.TRC(SCCGWA, BPRFEAG.CHG_AC);
        BPRFADT.CHG_AC = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
        CEP.TRC(SCCGWA, BPRFADT.CHG_AC);
        BPRFADT.FEE_SRC = BPRFEAG.KEY.CLT_TYPE;
        BPRFADT.FEE_CODE = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE;
        BPRFADT.CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
        BPRFADT.CCY_TYPE = BPCGPFEE.TX_DATA3.CCY_TYPE;
        BPRFADT.SALE_DATE = BPCGPFEE.TX_DATA3.SALE_DATE;
        BPRFADT.TRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRFADT.CARD_PSBK_NO = BPCGPFEE.TX_DATA3.CARD_PSBK_NO;
        CEP.TRC(SCCGWA, BPCGPFEE.CUS_DATA.CUS_INFO[1-1].CUS_NO);
        CEP.TRC(SCCGWA, BPRFEAG.KEY.CLT_CI_NO);
        BPRFADT.TX_CI = BPCGPFEE.CUS_DATA.CUS_INFO[1-1].CUS_NO;
        BPRFADT.PRD_CD = BPCGCFEE.FEE_DATA.FEE_INFO[1-1].PROD_CD;
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[1-1].PROD_CD);
        BPRFADT.CHG_AC_TY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY;
        BPRFADT.CHG_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
        BPRFADT.VAT_AMT = WS_FEHIS_AMT2[WS_CNT1-1].WS_ADJ_AMT_T;
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AMT);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT);
        CEP.TRC(SCCGWA, BPRFADT.VAT_AMT);
        BPRFADT.ACC_RECH_CNT = 0;
        BPRFADT.CUR_OWE_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
        BPRFADT.ACC_CHG_AMT = 0;
        BPRFADT.CMMT_NO = BPCGPFEE.TX_DATA3.FEE_CTRT;
        BPRFADT.BSNS_NO = BPCGPFEE.TX_DATA3.BSNS_NO;
        BPRFADT.AMO_FLG = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_FLG;
        if (BPRFADT.AMO_FLG == '1') {
            BPRFADT.AMO_SDT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_STDT;
            BPRFADT.AMO_EDT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_EDDT;
        }
        BPRFADT.PRC_STS = '0';
        BPRFADT.CHG_STS = '0';
        BPRFADT.TRT_CHNL = SCCGWA.COMM_AREA.CHNL;
        BPRFADT.CHG_BR = WS_CHG_BR;
        BPRFADT.SRC_TR_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (BPRFADT.SRC_TR_CD.trim().length() > 0) {
            WS_TR_CD = BPRFADT.SRC_TR_CD;
            S000_GET_TR_NAME();
            if (pgmRtn) return;
            BPRFADT.SRC_TR_NAME = WS_TR_NAME;
        }
        BPRFADT.REMARK = BPCGCFEE.FEE_DATA.REMARK;
        BPRFADT.CREATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFADT.CREATE_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPRFADT.UPDATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFADT.CREATE_TELL = SCCGWA.COMM_AREA.TL_ID;
        BPRFADT.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
        BPRFADT.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPRFADT.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        BPRFADT.VAT_AMT = WS_FEHIS_AMT2[WS_CNT1-1].WS_ADJ_AMT_T;
        BPCRFPDT.INFO.FUNC = 'C';
        BPCRFPDT.INFO.FDT_TYP = BPRFEAG.KEY.CLT_TYPE;
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        if (BPCRFPDT.RETURN_INFO == 'D') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_REC_EXIST);
        }
        WS_YS_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
        S000_WRITE_HZFS_EVENT();
        if (pgmRtn) return;
    }
    public void S000_WRITE_DY_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.PROD_CODE = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE;
        BPCPOEWA.DATA.PROD_CODE_REL = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].PROD_CD;
        BPCPOEWA.DATA.CNTR_TYPE = "FEES";
        BPCPOEWA.DATA.BR_OLD = WS_CHG_BR;
        BPCPOEWA.DATA.BR_NEW = WS_CHG_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.DESC = BPCGCFEE.FEE_DATA.REMARK;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE_REL);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.DESC);
        IBS.init(SCCGWA, VTCPQTAX);
        VTCPQTAX.INPUT_DATA.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTCPQTAX.INPUT_DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        VTCPQTAX.INPUT_DATA.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        VTCPQTAX.INPUT_DATA.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        VTCPQTAX.INPUT_DATA.BR = BPCGCFEE.FEE_DATA.ACCOUNT_BR;
        VTCPQTAX.INPUT_DATA.CNTR_TYPE = "FEES";
        VTCPQTAX.INPUT_DATA.PROD_CD = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE;
        VTCPQTAX.INPUT_DATA.PROD_CD_REL = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].PROD_CD;
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.PROD_CD);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.PROD_CD_REL);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC);
        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            VTCPQTAX.INPUT_DATA.CI_NO = CICACCU.DATA.CI_NO;
            BPCPOEWA.DATA.CI_NO = CICACCU.DATA.CI_NO;
        }
        VTCPQTAX.INPUT_DATA.CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
        VTCPQTAX.INPUT_DATA.YJ_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
        S000_CALL_VTZPQTAX();
        if (pgmRtn) return;
        WS_FEHIS_AMT[WS_CNT1-1].WS_ADJ_AMT_P = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT - VTCPQTAX.OUTPUT_DATA.TAX_AMT;
        WS_FEHIS_AMT2[WS_CNT1-1].WS_ADJ_AMT_T = VTCPQTAX.OUTPUT_DATA.TAX_AMT;
        BPCPOEWA.DATA.EVENT_CODE = "DY";
        BPCPOEWA.DATA.AMT_INFO[05-1].AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
        BPCPOEWA.DATA.AMT_INFO[07-1].AMT = VTCPQTAX.OUTPUT_DATA.TAX_AMT;
        CEP.TRC(SCCGWA, WS_FEHIS_AMT2[WS_CNT1-1].WS_ADJ_AMT_T);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[05-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[07-1].AMT);
        if (BPCGPFEE.TX_DATA3.CARD_PSBK_NO.trim().length() > 0) {
            BPCPOEWA.DATA.THEIR_AC = BPCGPFEE.TX_DATA3.CARD_PSBK_NO;
        } else {
            BPCPOEWA.DATA.THEIR_AC = WS_CHG_ORG_AC;
        }
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.THEIR_AC);
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_FEHIS_AMT2[WS_CNT1-1].WS_ADJ_AMT_T);
    }
    public void B024_WRT_OWE_CHG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRFPDT);
        IBS.init(SCCGWA, BPRFPDT);
        BPRFPDT.KEY.TRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRFPDT.KEY.JRN_NO = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = BPRFPDT.KEY.JRN_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPRFPDT.KEY.JRN_NO = "0" + BPRFPDT.KEY.JRN_NO;
        WS_JRN_SEQ = (short) (WS_JRN_SEQ + 1);
        BPRFPDT.KEY.JRN_SEQ = WS_JRN_SEQ;
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC);
        BPRFPDT.CHG_AC = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '0') {
            if (!WS_CHG_ORG_AC.equalsIgnoreCase(WS_CHG_ORG_AC_F) 
                && WS_CHG_ORG_AC_F.trim().length() > 0) {
                BPRFPDT.CHG_AC = WS_CHG_ORG_AC_F;
            } else {
                BPRFPDT.CHG_AC = WS_CHG_ORG_AC;
            }
        } else {
            BPRFPDT.CHG_AC = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
        }
        CEP.TRC(SCCGWA, BPRFPDT.CHG_AC);
        BPRFPDT.CCY_TYPE = BPCGPFEE.TX_DATA3.CCY_TYPE;
        BPRFPDT.SALE_DATE = BPCGPFEE.TX_DATA3.SALE_DATE;
        BPRFPDT.CHG_BR = WS_CHG_BR;
        CEP.TRC(SCCGWA, "DEVZHJ16");
        CEP.TRC(SCCGWA, WS_CHG_BR);
        BPRFPDT.FEE_SRC = '2';
        BPRFPDT.FEE_CODE = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE;
        BPRFPDT.CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
        CEP.TRC(SCCGWA, BPRFPDT.CCY);
        BPRFPDT.CARD_PSBK_NO = BPCGPFEE.TX_DATA3.CARD_PSBK_NO;
        BPRFPDT.TX_CI = BPCGPFEE.CUS_DATA.CUS_INFO[1-1].CUS_NO;
        BPRFPDT.PRD_CD = BPCGCFEE.FEE_DATA.FEE_INFO[1-1].PROD_CD;
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[1-1].PROD_CD);
        BPRFPDT.CHG_AC_TY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY;
        if (BPCGPFEE.TX_DATA3.PROC_TYPE == '6') {
            BPRFPDT.CHG_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
            BPRFPDT.ACC_RECH_CNT = 0;
            BPRFPDT.CUR_OWE_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
            BPRFPDT.ACC_CHG_AMT = 0;
        } else {
            if (BPCOFBAS.VAL.DEBT_METH == '1') {
                BPRFPDT.CHG_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT - WS_CHG_AMT_ACT;
            }
            if (BPCOFBAS.VAL.DEBT_METH == '0') {
                BPRFPDT.CHG_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
            }
            BPRFPDT.ACC_RECH_CNT = 0;
            BPRFPDT.ACC_CHG_AMT = 0;
            BPRFPDT.CUR_OWE_AMT = BPRFPDT.CHG_AMT;
        }
        BPRFPDT.CMMT_NO = BPCGPFEE.TX_DATA3.FEE_CTRT;
        BPRFPDT.BSNS_NO = BPCGPFEE.TX_DATA3.BSNS_NO;
        BPRFPDT.AMO_FLG = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_FLG;
        BPRFPDT.AMO_SDT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_STDT;
        BPRFPDT.AMO_EDT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_EDDT;
        BPRFPDT.PRC_STS = '0';
        BPRFPDT.CHG_STS = '0';
        BPRFPDT.TRT_CHNL = SCCGWA.COMM_AREA.CHNL;
        BPRFPDT.TRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRFPDT.SRC_TR_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (BPRFPDT.SRC_TR_CD.trim().length() > 0) {
            WS_TR_CD = " ";
            WS_TR_CD = BPRFPDT.SRC_TR_CD;
            S000_GET_TR_NAME();
            if (pgmRtn) return;
            BPRFPDT.SRC_TR_NAME = WS_TR_NAME;
        }
        BPRFPDT.REMARK = BPCGCFEE.FEE_DATA.REMARK;
        BPRFPDT.CREATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFPDT.CREATE_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPRFPDT.UPDATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFPDT.CREATE_TELL = SCCGWA.COMM_AREA.TL_ID;
        BPRFPDT.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
        BPRFPDT.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPRFPDT.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        WS_YS_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
        S000_WRITE_HZFS_EVENT();
        if (pgmRtn) return;
        BPRFPDT.VAT_AMT = WS_FEHIS_AMT2[WS_CNT1-1].WS_ADJ_AMT_T;
        BPCRFPDT.INFO.FUNC = 'C';
        BPCRFPDT.INFO.FDT_TYP = '2';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        if (BPCRFPDT.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B025_CHARGE_AC_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_FLG);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY);
        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '0'
            || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '4'
            || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '5'
            || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '6'
            || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '7') {
            R000_CHARGE_AC();
            if (pgmRtn) return;
        } else if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '1') {
            R000_CHARGE_CASH();
            if (pgmRtn) return;
        } else if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '2') {
            R000_CHARGE_IB_AC();
            if (pgmRtn) return;
        } else if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '3') {
            R000_CHARGE_GL_AC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CHG_MTH_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_REAL_CHG_FEE = 'Y';
    }
    public void B026_EXCHANGE_CN() throws IOException,SQLException,Exception {
        WS_TS_FLG = "T";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CCY);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY);
        CEP.TRC(SCCGWA, "CHECK1");
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_AMT);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT);
        CEP.TRC(SCCGWA, "CHECK5");
        BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
        BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_BAS = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_BAS;
        BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_BAS);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_AMT);
        WS_TS_FLG = "W";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
    }
    public void B027_WRITE_FEE_VCH() throws IOException,SQLException,Exception {
        WS_TS_FLG = "X";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
        B027_01_01_SET_EWA_BASIC_INF();
        if (pgmRtn) return;
        B027_01_02_SET_EWA_EVENTS();
        if (pgmRtn) return;
        WS_TS_FLG = "Y";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
    }
    public void B027_01_03_CALC_FEES_TAX() throws IOException,SQLException,Exception {
        BPCPOEWA.DATA.PROD_CODE = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE;
        BPCPOEWA.DATA.PROD_CODE_REL = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].PROD_CD;
        BPCPOEWA.DATA.CNTR_TYPE = "FEES";
        BPCPOEWA.DATA.BR_OLD = WS_CHG_BR;
        BPCPOEWA.DATA.BR_NEW = WS_CHG_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.DESC = BPCGCFEE.FEE_DATA.REMARK;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE_REL);
        IBS.init(SCCGWA, VTCPQTAX);
        VTCPQTAX.INPUT_DATA.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTCPQTAX.INPUT_DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        VTCPQTAX.INPUT_DATA.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        VTCPQTAX.INPUT_DATA.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        VTCPQTAX.INPUT_DATA.BR = BPCGCFEE.FEE_DATA.ACCOUNT_BR;
        VTCPQTAX.INPUT_DATA.CNTR_TYPE = "FEES";
        VTCPQTAX.INPUT_DATA.PROD_CD = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE;
        VTCPQTAX.INPUT_DATA.PROD_CD_REL = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].PROD_CD;
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.PROD_CD);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.PROD_CD_REL);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC);
        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            if (CICACCU.RC.RC_CODE == 0) {
                VTCPQTAX.INPUT_DATA.CI_NO = CICACCU.DATA.CI_NO;
                BPCPOEWA.DATA.CI_NO = CICACCU.DATA.CI_NO;
            } else {
                VTCPQTAX.INPUT_DATA.CI_NO = BPCGPFEE.CUS_DATA.CUS_INFO[1-1].CUS_NO;
                BPCPOEWA.DATA.CI_NO = BPCGPFEE.CUS_DATA.CUS_INFO[1-1].CUS_NO;
            }
        }
        VTCPQTAX.INPUT_DATA.CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
        VTCPQTAX.INPUT_DATA.SH_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
        S000_CALL_VTZPQTAX();
        if (pgmRtn) return;
        WS_FEHIS_AMT[WS_CNT1-1].WS_ADJ_AMT_P = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT - VTCPQTAX.OUTPUT_DATA.TAX_AMT;
        WS_FEHIS_AMT2[WS_CNT1-1].WS_ADJ_AMT_T = VTCPQTAX.OUTPUT_DATA.TAX_AMT;
        CEP.TRC(SCCGWA, WS_FEHIS_AMT[WS_CNT1-1].WS_ADJ_AMT_P);
        if (BPCOFBAS.VAL.FEE_GTH_METH == '0') {
            BPCPOEWA.DATA.EVENT_CODE = "ST";
            if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE.equalsIgnoreCase("301000601")) {
                BPCPOEWA.DATA.EVENT_CODE = "SMST";
            }
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_FLG);
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_FLG == '1' 
                || JIBS_tmp_str[1].equalsIgnoreCase("9999128")) {
                BPCPOEWA.DATA.AMT_INFO[3-1].AMT = WS_FEHIS_AMT[WS_CNT1-1].WS_ADJ_AMT_P;
                BPCPOEWA.DATA.AMT_INFO[7-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[1-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[2-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[4-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[5-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[6-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[31-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[32-1].AMT = 0;
            } else {
                BPCPOEWA.DATA.AMT_INFO[1-1].AMT = WS_FEHIS_AMT[WS_CNT1-1].WS_ADJ_AMT_P;
                BPCPOEWA.DATA.AMT_INFO[7-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[2-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[3-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[4-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[5-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[6-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[31-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[32-1].AMT = 0;
            }
        } else {
            if (WS_FEHIS_AMT[WS_CNT1-1].WS_ADJ_AMT_P >= 0) {
                BPCPOEWA.DATA.EVENT_CODE = "DY";
                BPCPOEWA.DATA.AMT_INFO[5-1].AMT = WS_FEHIS_AMT[WS_CNT1-1].WS_ADJ_AMT_P;
                BPCPOEWA.DATA.AMT_INFO[7-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[1-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[2-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[3-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[4-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[6-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[31-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[32-1].AMT = 0;
            } else {
                WS_FEHIS_AMT[WS_CNT1-1].WS_ADJ_AMT_P = 0 - WS_FEHIS_AMT[WS_CNT1-1].WS_ADJ_AMT_P;
                CEP.TRC(SCCGWA, WS_FEHIS_AMT[WS_CNT1-1].WS_ADJ_AMT_P);
                BPCPOEWA.DATA.EVENT_CODE = "DY";
                BPCPOEWA.DATA.AMT_INFO[6-1].AMT = WS_FEHIS_AMT[WS_CNT1-1].WS_ADJ_AMT_P;
                BPCPOEWA.DATA.AMT_INFO[8-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[1-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[2-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[3-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[4-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[5-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[31-1].AMT = 0;
                BPCPOEWA.DATA.AMT_INFO[32-1].AMT = 0;
            }
        }
    }
    public void B027_01_01_SET_EWA_BASIC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPEBAS);
        S000_CALL_BPZPEBAS();
        if (pgmRtn) return;
    }
    public void B027_01_02_SET_EWA_EVENTS() throws IOException,SQLException,Exception {
        WS_TS_FLG = "2";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
        if (BPCGPFEE.TX_DATA2.FEE_CTRT_NO.trim().length() > 0) {
            B029_GET_CTRT_INFO();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.PROD_CODE = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE;
        BPCPOEWA.DATA.PROD_CODE_REL = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].PROD_CD;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE_REL);
        BPCPOEWA.DATA.CNTR_TYPE = "FEES";
        BPCPOEWA.DATA.BR_OLD = WS_CHG_BR;
        BPCPOEWA.DATA.BR_NEW = WS_CHG_BR;
        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            BPCPOEWA.DATA.CI_NO = CICACCU.DATA.CI_NO;
        }
        CEP.TRC(SCCGWA, "DEVZHJ17");
        CEP.TRC(SCCGWA, WS_CHG_BR);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.BR_OLD);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.BR_OLD);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.BR_NEW);
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.EVENT_CODE = "ST";
        BPCPOEWA.DATA.DESC = BPCGCFEE.FEE_DATA.REMARK;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.DESC);
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA2.FEE_CTRT_NO);
        CEP.TRC(SCCGWA, BPCPQCTR.INFO.FEE_TYPE);
        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].REF_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, BPCPQCTR.FLAG.CTRT_FLAG);
            CEP.TRC(SCCGWA, BPCPQCTR.INFO.BOOK_CENTRE);
            CEP.TRC(SCCGWA, BPCPQCTR.INFO.PAYMENT_METHOD);
            CEP.TRC(SCCGWA, BPCPQCTR.INFO.PAY_IND);
            BPCPOEWA.DATA.REF_NO = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].REF_NO;
            if (BPCPQCTR.INFO.BOOK_CENTRE != 0) {
                BPCPOEWA.DATA.BR_OLD = BPCPQCTR.INFO.BOOK_CENTRE;
                BPCPOEWA.DATA.BR_NEW = BPCPQCTR.INFO.BOOK_CENTRE;
                WS_CHG_BR = BPCPQCTR.INFO.BOOK_CENTRE;
                CEP.TRC(SCCGWA, "DEVZHJ13");
                CEP.TRC(SCCGWA, WS_CHG_BR);
            }
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (((JIBS_tmp_str[0].equalsIgnoreCase("9999156") 
                || GWA_BP_AREA.CANCEL_AREA.CAN_TR.equalsIgnoreCase("9999156")) 
                && BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_FLG != '1')) {
            BPCPOEWA.DATA.EVENT_CODE = "FS";
            BPCPOEWA.DATA.AMT_INFO[5-1].AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = 0;
            BPCPOEWA.DATA.AMT_INFO[2-1].AMT = 0;
            BPCPOEWA.DATA.AMT_INFO[3-1].AMT = 0;
            BPCPOEWA.DATA.AMT_INFO[4-1].AMT = 0;
            BPCPOEWA.DATA.AMT_INFO[6-1].AMT = 0;
            BPCPOEWA.DATA.AMT_INFO[7-1].AMT = 0;
            BPCPOEWA.DATA.AMT_INFO[31-1].AMT = 0;
            BPCPOEWA.DATA.AMT_INFO[32-1].AMT = 0;
            IBS.init(SCCGWA, VTCPQTAX);
            VTCPQTAX.INPUT_DATA.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            VTCPQTAX.INPUT_DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
            VTCPQTAX.INPUT_DATA.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            VTCPQTAX.INPUT_DATA.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            VTCPQTAX.INPUT_DATA.BR = WS_CHG_BR;
            VTCPQTAX.INPUT_DATA.CNTR_TYPE = "FEES";
            VTCPQTAX.INPUT_DATA.PROD_CD = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE;
            VTCPQTAX.INPUT_DATA.PROD_CD_REL = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].PROD_CD;
            CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.PROD_CD);
            CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.PROD_CD_REL);
            if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC.trim().length() > 0) {
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.AGR_NO = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
                S000_CALL_CIZACCU();
                if (pgmRtn) return;
                VTCPQTAX.INPUT_DATA.CI_NO = CICACCU.DATA.CI_NO;
                BPCPOEWA.DATA.CI_NO = CICACCU.DATA.CI_NO;
            }
            VTCPQTAX.INPUT_DATA.CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
            VTCPQTAX.INPUT_DATA.SH_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
            VTCPQTAX.INPUT_DATA.INQ_TAX_FLG = 'W';
            S000_CALL_VTZPQTAX();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        } else if (JIBS_tmp_str[0].equalsIgnoreCase("9991262")) {
            BPCPOEWA.DATA.EVENT_CODE = "MA";
            BPCPOEWA.DATA.AMT_INFO[5-1].AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
            IBS.init(SCCGWA, VTCPQTAX);
            VTCPQTAX.INPUT_DATA.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            VTCPQTAX.INPUT_DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
            VTCPQTAX.INPUT_DATA.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            VTCPQTAX.INPUT_DATA.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            VTCPQTAX.INPUT_DATA.BR = BPCGCFEE.FEE_DATA.ACCOUNT_BR;
            VTCPQTAX.INPUT_DATA.CNTR_TYPE = "FEES";
            VTCPQTAX.INPUT_DATA.PROD_CD = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE;
            VTCPQTAX.INPUT_DATA.PROD_CD_REL = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].PROD_CD;
            CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.PROD_CD);
            CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.PROD_CD_REL);
            if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC.trim().length() > 0) {
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.AGR_NO = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
                S000_CALL_CIZACCU();
                if (pgmRtn) return;
                VTCPQTAX.INPUT_DATA.CI_NO = CICACCU.DATA.CI_NO;
                BPCPOEWA.DATA.CI_NO = CICACCU.DATA.CI_NO;
            }
            VTCPQTAX.INPUT_DATA.CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
            VTCPQTAX.INPUT_DATA.SH_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
            VTCPQTAX.INPUT_DATA.INQ_TAX_FLG = 'W';
            S000_CALL_VTZPQTAX();
            if (pgmRtn) return;
        } else {
            B027_01_03_CALC_FEES_TAX();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "VCH-DATA");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AC_NO);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.BR_OLD);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.BR_NEW);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.EVENT_CODE);
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA2.FEE_CTRT_NO);
        CEP.TRC(SCCGWA, BPCOFBAS.VAL.FEE_CHG_TYPE);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CCY);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[1-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[2-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[3-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[4-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[5-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[6-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[7-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[31-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[32-1].AMT);
        if (BPCGPFEE.TX_DATA3.CARD_PSBK_NO.trim().length() > 0) {
            BPCPOEWA.DATA.THEIR_AC = BPCGPFEE.TX_DATA3.CARD_PSBK_NO;
        } else {
            BPCPOEWA.DATA.THEIR_AC = WS_CHG_ORG_AC;
        }
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.THEIR_AC);
        WS_TS_FLG = "A1";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
        if (BPCPOEWA.DATA.AMT_INFO[1-1].AMT != 0 
            || BPCPOEWA.DATA.AMT_INFO[2-1].AMT != 0 
            || BPCPOEWA.DATA.AMT_INFO[3-1].AMT != 0 
            || BPCPOEWA.DATA.AMT_INFO[4-1].AMT != 0 
            || BPCPOEWA.DATA.AMT_INFO[5-1].AMT != 0 
            || BPCPOEWA.DATA.AMT_INFO[6-1].AMT != 0 
            || BPCPOEWA.DATA.AMT_INFO[7-1].AMT != 0 
            || BPCPOEWA.DATA.AMT_INFO[8-1].AMT != 0 
            || BPCPOEWA.DATA.AMT_INFO[9-1].AMT != 0 
            || BPCPOEWA.DATA.AMT_INFO[10-1].AMT != 0 
            || BPCPOEWA.DATA.AMT_INFO[11-1].AMT != 0 
            || BPCPOEWA.DATA.AMT_INFO[12-1].AMT != 0) {
            S000_CALL_BPZPOEWA();
            if (pgmRtn) return;
        }
        WS_TS_FLG = "A2";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
    }
    public void B028_WRITE_HISTORY_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFEHIS);
        BPRFEHIS.KEY.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.AC_DT);
        BPRFEHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.JRNNO);
        WS_JRN_SEQ = (short) (WS_JRN_SEQ + 1);
        BPRFEHIS.KEY.JRN_SEQ = WS_JRN_SEQ;
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.JRN_SEQ);
        BPRFEHIS.TX_CI = BPCGPFEE.CUS_DATA.CUS_INFO[1-1].CUS_NO;
        CEP.TRC(SCCGWA, BPCGPFEE.CUS_DATA.CUS_INFO[1-1].CUS_NO);
        CEP.TRC(SCCGWA, WS_SGN_CINO);
        BPRFEHIS.TX_MMO = BPCOFBAS.VAL.FEE_TX_MMO;
        if (BPCOFBAS.VAL.REB_CODE.trim().length() == 0) {
            BPRFEHIS.REB_TYPE = 'N';
        } else {
            BPRFEHIS.REB_TYPE = 'O';
        }
        if (BPCOFBAS.VAL.FEE_CHG_TYPE == '0') {
            BPRFEHIS.DRCRFLG = 'C';
        } else {
            BPRFEHIS.DRCRFLG = 'D';
        }
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA2.FEE_CTRT_NO);
        BPRFEHIS.FEE_CODE = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE;
        CEP.TRC(SCCGWA, WS_CNT1);
        CEP.TRC(SCCGWA, BPRFEHIS.FEE_CODE);
        BPRFEHIS.REQFM_NO = SCCGWA.COMM_AREA.REQ_SYSTEM;
        BPRFEHIS.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPRFEHIS.FREE_FMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FREE_FMT;
        BPRFEHIS.CARD_PSBK_NO = BPCGPFEE.TX_DATA3.CARD_PSBK_NO;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPRFEHIS.CARD_PSBK_NO = WS_CARD_TABLE[WS_CNT1-1].WS_CARD_NO;
        }
        CEP.TRC(SCCGWA, BPRFEHIS.CARD_PSBK_NO);
        BPRFEHIS.SALE_DATE = BPCGPFEE.TX_DATA3.SALE_DATE;
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.CARD_PSBK_NO);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC);
        CEP.TRC(SCCGWA, BPRFEHIS.CARD_PSBK_NO);
        BPRFEHIS.TX_CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].TX_CCY;
        BPRFEHIS.CHG_BR = WS_CHG_BR;
        CEP.TRC(SCCGWA, "DEVZHJ18");
        CEP.TRC(SCCGWA, WS_CHG_BR);
        BPRFEHIS.TX_AC = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].TX_AC;
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].TX_AC);
        BPRFEHIS.REF_NO = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].REF_NO;
        BPRFEHIS.TX_PROD = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].PROD_CD;
        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '0') {
            if (!WS_CHG_ORG_AC.equalsIgnoreCase(WS_CHG_ORG_AC_F) 
                && WS_CHG_ORG_AC_F.trim().length() > 0) {
                BPRFEHIS.CHG_AC = WS_CHG_ORG_AC_F;
                BPRFEHIS.BSNS_NO = WS_CHG_ORG_AC;
            } else {
                BPRFEHIS.CHG_AC = WS_CHG_ORG_AC;
                BPRFEHIS.BSNS_NO = BPCGPFEE.TX_DATA3.BSNS_NO;
            }
        } else {
            BPRFEHIS.CHG_AC = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
            BPRFEHIS.BSNS_NO = BPCGPFEE.TX_DATA3.BSNS_NO;
        }
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_AC);
        CEP.TRC(SCCGWA, BPRFEAG.KEY.CLT_AC);
        BPRFEHIS.CHG_AC_TY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY;
        CEP.TRC(SCCGWA, DDCUDRAC.AC);
        CEP.TRC(SCCGWA, BPRFEHIS.CHG_AC);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY);
        BPRFEHIS.FEE_CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CCY;
        BPRFEHIS.FEE_BAS = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_BAS;
        BPRFEHIS.FEE_FAV = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_BAS - BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_AMT;
        BPRFEHIS.FEE_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_AMT;
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_AMT);
        BPRFEHIS.CHG_CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
        BPRFEHIS.CHG_BAS = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_BAS;
        BPRFEHIS.CHG_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AMT;
        BPRFEHIS.ADJ_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AMT);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT);
        BPRFEHIS.VAT_AMT = WS_FEHIS_AMT2[WS_CNT1-1].WS_ADJ_AMT_T;
        CEP.TRC(SCCGWA, BPRFEHIS.VAT_AMT);
        BPRFEHIS.DER_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].DER_AMT;
        BPRFEHIS.CHG_FLG = BPCOFBAS.VAL.FEE_CHG_TYPE;
        BPRFEHIS.FEE_CTRT = BPCGPFEE.TX_DATA3.FEE_CTRT;
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.FEE_CTRT);
        BPRFEHIS.FEE_CONT = BPCGPFEE.TX_DATA3.FEE_CONT;
        BPRFEHIS.FEE_ADVICE = BPCGPFEE.TX_DATA3.FEE_ADVICE;
        BPRFEHIS.CCY_TYPE = BPCGPFEE.TX_DATA3.CCY_TYPE;
        BPRFEHIS.CREV_NO = BPCGPFEE.TX_DATA3.CREV_NO;
        BPRFEHIS.REMARK = BPCGCFEE.FEE_DATA.REMARK;
        BPRFEHIS.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRFEHIS.TX_DT = SCCGWA.COMM_AREA.TR_DATE;
        BPRFEHIS.TX_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPRFEHIS.TX_STS = 'N';
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || (BPCGPFEE.TX_DATA3.PROC_TYPE == '1' 
            || BPCGPFEE.TX_DATA3.PROC_TYPE == '2')) {
            BPRFEHIS.TX_STS = 'R';
            BPRFEHIS.TX_PROD = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].PROD_CD;
        }
        BPRFEHIS.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRFEHIS.SUP_TEL1 = SCCGMSG.SUP1_ID;
        BPRFEHIS.SUP_TEL2 = SCCGMSG.SUP2_ID;
        if (WS_BAL_LESS_FLG == 'Y') {
            BPRFEHIS.CHG_AMT = WS_CHG_AMT_ACT;
            BPRFEHIS.ADJ_AMT = WS_CHG_AMT_ACT;
        }
        BPRFEHIS.REMARK = BPCGCFEE.FEE_DATA.REMARK;
        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '1') {
            BPRFEHIS.CHG_AC = " ";
            BPRFEHIS.CARD_PSBK_NO = " ";
            BPRFEHIS.TX_CCY = " ";
            BPRFEHIS.TX_PROD = " ";
            BPRFEHIS.TX_AC = " ";
        }
        CEP.TRC(SCCGWA, BPRFEHIS.CHG_AC);
        CEP.TRC(SCCGWA, BPRFEHIS.CARD_PSBK_NO);
        CEP.TRC(SCCGWA, BPRFEHIS.TX_CCY);
        CEP.TRC(SCCGWA, BPRFEHIS.TX_AC);
        CEP.TRC(SCCGWA, BPRFEHIS.TX_PROD);
        if (BPCSFECT.CTNO.trim().length() > 0) {
            BPRFEHIS.FEE_CTRT_NO = BPCSFECT.CTNO;
        }
        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].REF_NO.trim().length() > 0) {
            BPRFEHIS.FEE_CTRT_NO = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].REF_NO;
        }
        CEP.TRC(SCCGWA, BPCSFECT.CTNO);
        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_FLG == '1' 
            && BPRFEHIS.FEE_CTRT_NO.trim().length() > 0) {
            IBS.init(SCCGWA, BPRFCTR);
            BPRFCTR.KEY.CTRT_NO = BPRFEHIS.FEE_CTRT_NO;
            BPTFCTR_RD = new DBParm();
            BPTFCTR_RD.TableName = "BPTFCTR";
            BPTFCTR_RD.upd = true;
            IBS.READ(SCCGWA, BPRFCTR, BPTFCTR_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.TRC(SCCGWA, "ADD FOR AMO");
                BPRFCTR.FCHG_MIN_AMT = BPRFEHIS.VAT_AMT;
                BPTFCTR_RD = new DBParm();
                BPTFCTR_RD.TableName = "BPTFCTR";
                IBS.REWRITE(SCCGWA, BPRFCTR, BPTFCTR_RD);
            }
        } else {
        }
        BPRFEHIS.CHG_FAV = BPRFEHIS.CHG_BAS - BPRFEHIS.ADJ_AMT;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || (BPCGPFEE.TX_DATA3.PROC_TYPE == '1' 
            || BPCGPFEE.TX_DATA3.PROC_TYPE == '2')) {
            BPRFEHIS.FEE_BAS = 0 - BPRFEHIS.FEE_BAS;
            BPRFEHIS.FEE_FAV = 0 - BPRFEHIS.FEE_FAV;
            BPRFEHIS.FEE_AMT = 0 - BPRFEHIS.FEE_AMT;
            BPRFEHIS.CHG_BAS = 0 - BPRFEHIS.CHG_BAS;
            BPRFEHIS.CHG_FAV = 0 - BPRFEHIS.CHG_FAV;
            BPRFEHIS.CHG_AMT = 0 - BPRFEHIS.CHG_AMT;
            BPRFEHIS.ADJ_AMT = 0 - BPRFEHIS.ADJ_AMT;
            BPRFEHIS.DER_AMT = 0 - BPRFEHIS.DER_AMT;
        }
        BPRFEHIS.BLOB_PFEE_DATA = IBS.CLS2CPY(SCCGWA, BPCGPFEE);
        if ((BPCGPFEE.TX_DATA3.CLT_TYPE == '0' 
            || BPCGPFEE.TX_DATA3.CLT_TYPE == '2') 
            || (BPCGPFEE.TX_DATA3.PROC_TYPE == '1' 
            || BPCGPFEE.TX_DATA3.PROC_TYPE == '2')) {
            BPRFEHIS.BAT_OTRT_DT = BPCGPFEE.TX_DATA3.BAT_OTRT_DT;
            BPRFEHIS.BAT_OTRT_JRN = BPCGPFEE.TX_DATA3.BAT_OTRT_JRN;
            BPRFEHIS.BAT_OTRT_SEQ = BPCGPFEE.TX_DATA3.BAT_OTRT_SEQ;
        }
        T000_WRITE_BPTFEHIS_CN();
        if (pgmRtn) return;
    }
    public void T000_WRITE_BPTFEHIS_CN() throws IOException,SQLException,Exception {
        BPTFEHIS_RD = new DBParm();
        BPTFEHIS_RD.TableName = "BPTFEHIS";
        BPTFEHIS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRFEHIS, BPTFEHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_DUPKEY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_FEHIS;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BPTFEHIS_CN() throws IOException,SQLException,Exception {
        BPTFEHIS_BR.rp = new DBParm();
        BPTFEHIS_BR.rp.TableName = "BPTFEHIS";
        BPTFEHIS_BR.rp.where = "AC_DT = :BPRFEHIS.KEY.AC_DT "
            + "AND JRNNO = :BPRFEHIS.KEY.JRNNO";
        BPTFEHIS_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRFEHIS, this, BPTFEHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FEHIS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FEHIS_FLG = 'N';
        } else {
        }
    }
    public void T000_READ_FIRST_BPTFEHIS_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.JRNNO);
        BPRFEHIS.FEE_CODE = WS_FEE_CODE;
        CEP.TRC(SCCGWA, BPRFEHIS.FEE_CODE);
        BPTFEHIS_BR.rp = new DBParm();
        BPTFEHIS_BR.rp.TableName = "BPTFEHIS";
        BPTFEHIS_BR.rp.where = "AC_DT = :BPRFEHIS.KEY.AC_DT "
            + "AND JRNNO = :BPRFEHIS.KEY.JRNNO "
            + "AND FEE_CODE = :BPRFEHIS.FEE_CODE";
        BPTFEHIS_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRFEHIS, this, BPTFEHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FEHIS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FEHIS_FLG = 'N';
        } else {
        }
    }
    public void T000_READ_BPTFEHIS_UPD_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.JRNNO);
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.JRN_SEQ);
        BPTFEHIS_RD = new DBParm();
        BPTFEHIS_RD.TableName = "BPTFEHIS";
        BPTFEHIS_RD.upd = true;
        IBS.READ(SCCGWA, BPRFEHIS, BPTFEHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FEHIS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FEHIS_FLG = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTFEHIS_CN() throws IOException,SQLException,Exception {
        BPTFEHIS_RD = new DBParm();
        BPTFEHIS_RD.TableName = "BPTFEHIS";
        IBS.REWRITE(SCCGWA, BPRFEHIS, BPTFEHIS_RD);
    }
    public void T000_DELETE_BPTFEHIS_CN() throws IOException,SQLException,Exception {
        BPTFEHIS_RD = new DBParm();
        BPTFEHIS_RD.TableName = "BPTFEHIS";
        IBS.DELETE(SCCGWA, BPRFEHIS, BPTFEHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FEHIS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FEHIS_FLG = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTFEHIS_CN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFEHIS, this, BPTFEHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FEHIS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FEHIS_FLG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTFEHIS_CN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFEHIS_BR);
    }
    public void S000_TRC_WS_TS() throws IOException,SQLException,Exception {
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, WS_TS_FLG);
        CEP.TRC(SCCGWA, WS_TS);
    }
    public void B030_OUTPUT_FEE_INFO() throws IOException,SQLException,Exception {
        BPCGCFEE.FEE_DATA.SEND_FLG = '1';
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.SEND_FLG);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_CNT);
        for (WS_CNT2 = 1; WS_CNT2 <= BPCGCFEE.FEE_DATA.FEE_CNT; WS_CNT2 += 1) {
            if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT2-1].FEE_CODE.trim().length() > 0) {
                CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT2-1].CHG_AMT);
                CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT2-1].ADJ_AMT);
                CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT2-1].DER_AMT);
                CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT2-1].FEE_BAS);
                CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT2-1].CHG_BAS);
                CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT2-1].FEE_CODE);
                CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT2-1].FEE_CCY);
                CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT2-1].CHG_AC_TY);
                CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT2-1].CHG_AC);
                CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT2-1].CHG_CCY);
                CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT2-1].PROD_CD);
                if (WS_OUTPUT_FMT.equalsIgnoreCase(K_OUTPUT_FMT)) {
                    BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT2-1].ADJ_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT2-1].CHG_AMT;
                }
            }
        }
        BPCGFEEO.FEE_DATA.FEE_CNT = BPCGCFEE.FEE_DATA.FEE_CNT;
        BPCGFEEP.FEE_DATA.FEE_CNT = BPCGCFEE.FEE_DATA.FEE_CNT;
        FEE_INFO = new BPCGFEEP_FEE_INFO();
        BPCGFEEP.FEE_DATA.FEE_INFO.add(FEE_INFO);
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, BPCGFEEO.TITLE_DATA);
        IBS.init(SCCGWA, BPCGFEEP.TITLE_DATA);
        IBS.init(SCCGWA, BPCGFEEP.CI_DATA);
        CEP.TRC(SCCGWA, WS_OUTPUT_FMT);
        SCCFMT.FMTID = WS_OUTPUT_FMT;
        BPCGFEEO.TITLE_DATA.SEND_FLG = BPCGCFEE.FEE_DATA.SEND_FLG;
        BPCGFEEP.TITLE_DATA.SEND_FLG = BPCGCFEE.FEE_DATA.SEND_FLG;
        BPCGFEEO.TITLE_DATA.EXP_CODE = BPCGCFEE.FEE_DATA.EXP_CODE;
        BPCGFEEP.TITLE_DATA.EXP_CODE = BPCGCFEE.FEE_DATA.EXP_CODE;
        BPCGFEEO.TITLE_DATA.ACCOUNT_BR = BPCGCFEE.FEE_DATA.ACCOUNT_BR;
        BPCGFEEO.TITLE_DATA.EX_RATE = BPCGCFEE.FEE_DATA.EX_RATE;
        BPCGFEEO.TITLE_DATA.TICKET_NO = BPCGCFEE.FEE_DATA.TICKET_NO;
        BPCGFEEO.TITLE_DATA.REMARK = BPCGCFEE.FEE_DATA.REMARK;
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_CNT);
        for (WS_CNT1 = 1; (WS_CNT1 <= 20) 
            && (WS_CNT1 <= BPCGCFEE.FEE_DATA.FEE_CNT); WS_CNT1 += 1) {
            CEP.TRC(SCCGWA, WS_CNT1);
            BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_FLG = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_FLG;
            BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE;
            BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CCY;
            BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_BAS = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_BAS;
            BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_AMT;
            BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY;
            if (WS_CHG_ORG_AC.trim().length() > 0) {
                BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC = WS_CHG_ORG_AC;
            } else {
                BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
            }
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY);
            if ((BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '4' 
                || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '5')) {
                if (SCCGWA.COMM_AREA.FEE_DATA_IND == 'Y') {
                    BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC = WS_CARD_TABLE[WS_CNT1-1].WS_CARD_NO;
                } else {
                    BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC = BPCGPFEE.TX_DATA3.CARD_PSBK_NO;
                }
            }
            CEP.TRC(SCCGWA, BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC);
            BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
            BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_BAS = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_BAS;
            BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AMT;
            BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
            BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].DER_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].DER_AMT;
            BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].DER_AUTH = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].DER_AUTH;
            BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].EXG_DATE = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].EXG_DATE;
            BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].EXG_TIME = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].EXG_TIME;
            BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].RGN_NO = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].RGN_NO;
            BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].FREE_FMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FREE_FMT;
            BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].TX_AC = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].TX_AC;
            BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].REF_NO = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].REF_NO;
            BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].PROD_CD = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].PROD_CD;
            BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].TX_CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].TX_CCY;
            BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].TX_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].TX_AMT;
            BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].TX_CNT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].TX_CNT;
            BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].PSBK_NO = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].PSBK_NO;
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_FLG = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_FLG;
                BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_STDT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_STDT;
                BPCGFEEO.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_EDDT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_EDDT;
            }
            if (WS_FEE_NAME.trim().length() == 0) {
                IBS.init(SCCGWA, CICACCU);
                CEP.TRC(SCCGWA, "GET FEE NAME");
                CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC);
                CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_AC);
                if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC.trim().length() > 0) {
                    CICACCU.DATA.AGR_NO = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
                } else {
                    CICACCU.DATA.AGR_NO = BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_AC;
                }
                CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
                S000_CALL_CIZACCU();
                if (pgmRtn) return;
                WS_FEE_CINO = CICACCU.DATA.CI_NO;
                if (CICACCU.DATA.AC_CNM.trim().length() > 0) {
                    WS_FEE_NAME = CICACCU.DATA.AC_CNM;
                } else if (CICACCU.DATA.AC_ENM.trim().length() > 0) {
                    WS_FEE_NAME = CICACCU.DATA.AC_ENM;
                } else if (CICACCU.DATA.CI_CNM.trim().length() > 0) {
                    WS_FEE_NAME = CICACCU.DATA.CI_CNM;
                } else if (CICACCU.DATA.CI_ENM.trim().length() > 0) {
                    WS_FEE_NAME = CICACCU.DATA.CI_ENM;
                } else {
                    WS_FEE_NAME = " ";
                }
            }
        }
        CEP.TRC(SCCGWA, BPCGFEEO.FEE_DATA.PROC_TYPE);
        CEP.TRC(SCCGWA, WS_OUTPUT_FMT);
        CEP.TRC(SCCGWA, K_OUTPUT_FMT2);
        CEP.TRC(SCCGWA, "ZHENG5");
        if (WS_OUTPUT_FMT.equalsIgnoreCase(K_OUTPUT_FMT2)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCGFEEO.TITLE_DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCGFEEP.TITLE_DATA);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCGFEEO.FEE_DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCGFEEP.FEE_DATA);
            BPCGFEEP.CI_DATA.TX_MMO = BPCOFBAS.VAL.FEE_TX_MMO;
            BPCGFEEP.CI_DATA.FEE_AC_NM = WS_FEE_NAME;
            BPCGFEEP.CI_DATA.FEE_CI_NO = WS_FEE_CINO;
            SCCFMT.DATA_PTR = BPCGFEEP;
            SCCFMT.DATA_LEN = 6750;
            CEP.TRC(SCCGWA, BPCGFEEP.CI_DATA.TX_MMO);
            CEP.TRC(SCCGWA, BPCGFEEP.CI_DATA.FEE_AC_NM);
            CEP.TRC(SCCGWA, BPCGFEEP.CI_DATA.FEE_CI_NO);
            CEP.TRC(SCCGWA, BPCGFEEP);
            CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        } else {
            SCCFMT.DATA_PTR = BPCGFEEO;
            SCCFMT.DATA_LEN = 6481;
            CEP.TRC(SCCGWA, BPCGFEEO);
            CEP.TRC(SCCGWA, BPCGFEEO.FEE_DATA.PROC_TYPE);
        }
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B030_OUTPUT_BANK_ACC() throws IOException,SQLException,Exception {
        WS_OUTPUT_BNK_ACC.OUTP_BSNS_NO = BPCGPFEE.TX_DATA3.BSNS_NO;
        WS_OUTPUT_BNK_ACC.OUTP_CHG_AC_TY = BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP;
        WS_OUTPUT_BNK_ACC.OUTP_CHG_AC = BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_AC;
        WS_OUTPUT_BNK_ACC.OUTP_CHG_CCY = BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_CCY;
        WS_OUTPUT_BNK_ACC.OUTP_CCY_TYPE = BPCGPFEE.TX_DATA3.CCY_TYPE;
        SCCFMT.FMTID = WS_OUTPUT_FMT;
        CEP.TRC(SCCGWA, WS_OUTPUT_BNK_ACC);
        SCCFMT.DATA_PTR = WS_OUTPUT_BNK_ACC;
        SCCFMT.DATA_LEN = 1100;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B040_OUTPUT_FEE_INFO() throws IOException,SQLException,Exception {
        BPCGCFEE.FEE_DATA.SEND_FLG = '1';
        IBS.init(SCCGWA, BPCOCHGO);
        BPCOCHGO.FEE_DATA.SEND_FLG = BPCGCFEE.FEE_DATA.SEND_FLG;
        BPCOCHGO.FEE_DATA.EXP_CODE = BPCGCFEE.FEE_DATA.EXP_CODE;
        BPCOCHGO.FEE_DATA.FEE_CNT = BPCGCFEE.FEE_DATA.FEE_CNT;
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_CNT);
        for (WS_CNT1 = 1; WS_CNT1 <= BPCGCFEE.FEE_DATA.FEE_CNT; WS_CNT1 += 1) {
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].PROD_CD);
            BPCOCHGO.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_FLG = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_FLG;
            BPCOCHGO.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE;
            BPCOCHGO.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CCY;
            BPCOCHGO.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_BAS = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_BAS;
            BPCOCHGO.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_AMT;
            BPCOCHGO.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY;
            BPCOCHGO.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
            BPCOCHGO.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
            BPCOCHGO.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_BAS = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_BAS;
            BPCOCHGO.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AMT;
            BPCOCHGO.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
            BPCOCHGO.FEE_DATA.FEE_INFO[WS_CNT1-1].DER_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].DER_AMT;
            BPCOCHGO.FEE_DATA.FEE_INFO[WS_CNT1-1].DER_AUTH = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].DER_AUTH;
            BPCOCHGO.FEE_DATA.FEE_INFO[WS_CNT1-1].EXG_DATE = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].EXG_DATE;
            BPCOCHGO.FEE_DATA.FEE_INFO[WS_CNT1-1].EXG_TIME = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].EXG_TIME;
            BPCOCHGO.FEE_DATA.FEE_INFO[WS_CNT1-1].FILLER1 = 0X01;
            BPCOCHGO.FEE_DATA.FEE_INFO[WS_CNT1-1].FILLER2 = 0X01;
            BPCOCHGO.FEE_DATA.FEE_INFO[WS_CNT1-1].FILLER3 = 0X01;
            BPCOCHGO.FEE_DATA.FEE_INFO[WS_CNT1-1].FILLER4 = 0X01;
            BPCOCHGO.FEE_DATA.FEE_INFO[WS_CNT1-1].FILLER5 = 0X01;
            BPCOCHGO.FEE_DATA.FEE_INFO[WS_CNT1-1].FILLER6 = 0X01;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT2;
        SCCFMT.DATA_PTR = BPCOCHGO;
        SCCFMT.DATA_LEN = 3470;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_GET_SERVICE_FEE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFSVR);
        IBS.init(SCCGWA, BPCOCSVR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[1-1].FEE_CODE);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (BPCGCFEE.FEE_DATA.FEE_INFO[1-1].FEE_CODE.equalsIgnoreCase("200000501") 
            && !JIBS_tmp_str[1].equalsIgnoreCase("9991262")) {
            BPCOCSVR.KEY.SVR_NO = "0117777";
        } else {
            BPCOCSVR.KEY.SVR_NO = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        }
        CEP.TRC(SCCGWA, BPCOCSVR.KEY.SVR_NO);
        BPCOCSVR.FUNC = 'I';
        S000_CALL_BPZFUSVR();
        if (pgmRtn) return;
    }
    public void R000_GET_BASIC_FEE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFBAS);
        BPCOFBAS.KEY.FEE_CODE = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE;
        BPCOFBAS.FUNC = 'I';
        S000_CALL_BPZFUBAS();
        if (pgmRtn) return;
        if (WS_FEE_CTRT_CN.WS_PFEE_CTRT_FLG == 'Y') {
            BPCGPFEE.TX_DATA2.FEE_CTRT_NO = WS_FEE_CTRT_CN.WS_PFEE_FEE_CTRT;
        } else {
            BPCGPFEE.TX_DATA2.FEE_CTRT_NO = WS_FEE_CTRT_CN.WS_FEE_CTRT_TABLE[WS_CNT1-1].WS_FEE_CTRT_NO;
        }
        CEP.TRC(SCCGWA, BPCOFBAS.VAL.FEE_AMOT_FLG);
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA2.FEE_CTRT_NO);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && BPCGPFEE.TX_DATA2.FEE_CTRT_NO.trim().length() == 0 
            && (BPCGPFEE.TX_DATA3.PROC_TYPE != '1' 
            && BPCGPFEE.TX_DATA3.PROC_TYPE != '2')) {
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_FLG);
            if (BPCOFBAS.VAL.FEE_AMOT_FLG == '1' 
                && BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_FLG != '1') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMOT_FLG_INPUT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_FLG == '1' 
                && JIBS_tmp_str[1].equalsIgnoreCase("9991187")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_NEED_AUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_FLG == '1' 
                && (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_STDT < SCCGWA.COMM_AREA.AC_DATE 
                || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_EDDT < SCCGWA.COMM_AREA.AC_DATE 
                || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_EDDT < BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_STDT)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_DT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_FLG);
        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_FLG == '1') {
            WS_FEE_CODE_GL = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE;
        }
    }
    public void R000_CHARGE_AC() throws IOException,SQLException,Exception {
        WS_TS_FLG = "R";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOFBAS.VAL.DEBT_METH);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            R000_CHARGE_DD_AC_CN();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        } else if ((JIBS_tmp_str[0].equalsIgnoreCase("9991262") 
                || JIBS_tmp_str[0].equalsIgnoreCase("9999156"))) {
            R000_CHARGE_DD_AC_CN();
            if (pgmRtn) return;
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        } else if (BPCOFBAS.VAL.DEBT_METH == '0' 
                && !JIBS_tmp_str[1].equalsIgnoreCase("9991262") 
                && BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_FLG != '1') {
            R000_INQ_AC_AMT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCIQBAL.DATA.AVL_BAL);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT);
            if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
            JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
            if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
            JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
            if (DDCIQBAL.DATA.AVL_BAL >= BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT 
                || DDCIQBAL.DATA.CCY_STS_WORD.substring(62 - 1, 62 + 1 - 1).equalsIgnoreCase("1") 
                || DDCIQBAL.DATA.CCY_STS_WORD.substring(63 - 1, 63 + 1 - 1).equalsIgnoreCase("1")) {
                R000_CHARGE_DD_AC_CN();
                if (pgmRtn) return;
            } else {
                WS_FPDT_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
                R000_WRITE_BPTFPDT();
                if (pgmRtn) return;
                BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT = 0;
                R000_WRITE_BPTNHIST();
                if (pgmRtn) return;
            }
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        } else if (BPCOFBAS.VAL.DEBT_METH == '1' 
                && !JIBS_tmp_str[1].equalsIgnoreCase("9991262") 
                && BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_FLG != '1') {
            R000_CHARGE_DD_AC_CN2();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT);
            if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT > DDCUDRAC.TX_AMT) {
                WS_FPDT_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT - DDCUDRAC.TX_AMT;
                R000_WRITE_BPTFPDT();
                if (pgmRtn) return;
                BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT = DDCUDRAC.TX_AMT;
            }
        } else if (BPCOFBAS.VAL.DEBT_METH == '2') {
            R000_CHARGE_DD_AC_CN();
            if (pgmRtn) return;
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        } else if (BPCOFBAS.VAL.DEBT_METH == '3' 
                && !JIBS_tmp_str[1].equalsIgnoreCase("9991262") 
                && BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_FLG != '1') {
            R000_CHARGE_DD_AC_CN2();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
            BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT = DDCUDRAC.TX_AMT;
        } else {
            R000_CHARGE_DD_AC_CN();
            if (pgmRtn) return;
        }
        WS_TS_FLG = "S";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
    }
    public void R000_INQ_AC_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
        DDCIQBAL.DATA.CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY.equalsIgnoreCase("156")) {
            DDCIQBAL.DATA.CCY_TYPE = '1';
        } else {
            DDCIQBAL.DATA.CCY_TYPE = BPCGPFEE.TX_DATA3.CCY_TYPE;
        }
        S000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_STS_WORD);
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        if ((DDCIQBAL.DATA.CCY_STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            || DDCIQBAL.DATA.CCY_STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
            || DDCIQBAL.DATA.CCY_STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
            || DDCIQBAL.DATA.CCY_STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1"))) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLD_OR_FORBID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHARGE_CASH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "20151109 CASH TEST");
        CEP.TRC(SCCGWA, BPCOFBAS.VAL.FEE_CHG_TYPE);
        if (BPCOFBAS.VAL.FEE_CHG_TYPE == '0') {
            R000_TRANS_BPCUABOX_IN();
            if (pgmRtn) return;
            S000_CALL_FEE_CHARGE_CASH();
            if (pgmRtn) return;
        } else {
            if (BPCOFBAS.VAL.FEE_CHG_TYPE == '1') {
                R000_TRANS_BPCUSBOX_OUT();
                if (pgmRtn) return;
                S000_CALL_FEE_SUB_CASH();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHARGE_IB_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.AC = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
        IBCPOSTA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBCPOSTA.AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
        IBCPOSTA.CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.ENTRY_FLG = '1';
        CEP.TRC(SCCGWA, IBCPOSTA.NOSTRO_CD);
        CEP.TRC(SCCGWA, IBCPOSTA.VAL_DATE);
        CEP.TRC(SCCGWA, IBCPOSTA.AMT);
        CEP.TRC(SCCGWA, IBCPOSTA.CCY);
        CEP.TRC(SCCGWA, IBCPOSTA.SIGN);
        CEP.TRC(SCCGWA, IBCPOSTA.ENTRY_FLG);
        S000_CALL_IBZDRAC();
        if (pgmRtn) return;
    }
    public void S000_CALL_IBZDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-DEBIT-AC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHARGE_DD_AC_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOFBAS.VAL.FEE_CHG_TYPE);
        if (BPCOFBAS.VAL.FEE_CHG_TYPE == '0') {
            R000_TRANS_REV_DDCUDRAC_IN_CN();
            if (pgmRtn) return;
            DDCUDRAC.FEE_FLG = 'Y';
            S000_CALL_DDZUDRAC();
            if (pgmRtn) return;
        } else {
            if (BPCOFBAS.VAL.FEE_CHG_TYPE == '1') {
                R000_TRANS_REV_DDCUCRAC_IN_CN();
                if (pgmRtn) return;
                S000_CALL_DDZUCRAC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHARGE_DD_AC_CN2() throws IOException,SQLException,Exception {
        R000_TRANS_REV_DDCUDRAC_IN_CN();
        if (pgmRtn) return;
        DDCUDRAC.FEE_FLG = 'Y';
        DDCUDRAC.TX_BAL_FLG = 'Y';
        CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
    }
    public void R000_WRITE_BPTFPDT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRFPDT);
        IBS.init(SCCGWA, BPRFPDT);
        BPRFPDT.KEY.TRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRFPDT.KEY.JRN_NO = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = BPRFPDT.KEY.JRN_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPRFPDT.KEY.JRN_NO = "0" + BPRFPDT.KEY.JRN_NO;
        WS_JRN_SEQ = (short) (WS_JRN_SEQ + 1);
        CEP.TRC(SCCGWA, WS_JRN_SEQ);
        BPRFPDT.KEY.JRN_SEQ = WS_JRN_SEQ;
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC);
        BPRFPDT.CHG_AC = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '0') {
            if (!WS_CHG_ORG_AC.equalsIgnoreCase(WS_CHG_ORG_AC_F) 
                && WS_CHG_ORG_AC_F.trim().length() > 0) {
                BPRFPDT.CHG_AC = WS_CHG_ORG_AC_F;
            } else {
                BPRFPDT.CHG_AC = WS_CHG_ORG_AC;
            }
        } else {
            BPRFPDT.CHG_AC = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
        }
        CEP.TRC(SCCGWA, BPRFPDT.CHG_AC);
        BPRFPDT.CCY_TYPE = BPCGPFEE.TX_DATA3.CCY_TYPE;
        BPRFPDT.SALE_DATE = BPCGPFEE.TX_DATA3.SALE_DATE;
        BPRFPDT.CHG_BR = WS_CHG_BR;
        CEP.TRC(SCCGWA, WS_CHG_BR);
        BPRFPDT.FEE_SRC = '2';
        BPRFPDT.FEE_CODE = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE;
        BPRFPDT.CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
        BPRFPDT.CARD_PSBK_NO = BPCGPFEE.TX_DATA3.CARD_PSBK_NO;
        BPRFPDT.TX_CI = BPCGPFEE.CUS_DATA.CUS_INFO[1-1].CUS_NO;
        BPRFPDT.PRD_CD = BPCGCFEE.FEE_DATA.FEE_INFO[1-1].PROD_CD;
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[1-1].PROD_CD);
        BPRFPDT.CHG_AC_TY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY;
        BPRFPDT.CHG_AMT = WS_FPDT_AMT;
        BPRFPDT.ACC_RECH_CNT = 0;
        BPRFPDT.CUR_OWE_AMT = WS_FPDT_AMT;
        BPRFPDT.ACC_CHG_AMT = 0;
        BPRFPDT.CMMT_NO = BPCGPFEE.TX_DATA3.FEE_CTRT;
        BPRFPDT.BSNS_NO = BPCGPFEE.TX_DATA3.BSNS_NO;
        BPRFPDT.AMO_FLG = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_FLG;
        BPRFPDT.AMO_SDT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_STDT;
        BPRFPDT.AMO_EDT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_EDDT;
        BPRFPDT.PRC_STS = '0';
        BPRFPDT.CHG_STS = '0';
        BPRFPDT.TRT_CHNL = SCCGWA.COMM_AREA.CHNL;
        BPRFPDT.TRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRFPDT.SRC_TR_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (BPRFPDT.SRC_TR_CD.trim().length() > 0) {
            WS_TR_CD = " ";
            WS_TR_CD = BPRFPDT.SRC_TR_CD;
            S000_GET_TR_NAME();
            if (pgmRtn) return;
            BPRFPDT.SRC_TR_NAME = WS_TR_NAME;
        }
        BPRFPDT.REMARK = BPCGCFEE.FEE_DATA.REMARK;
        BPRFPDT.CREATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFPDT.CREATE_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPRFPDT.UPDATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFPDT.CREATE_TELL = SCCGWA.COMM_AREA.TL_ID;
        BPRFPDT.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
        BPRFPDT.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPRFPDT.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        WS_YS_AMT = WS_FPDT_AMT;
        S000_WRITE_HZFS_EVENT();
        if (pgmRtn) return;
        BPCRFPDT.INFO.FUNC = 'C';
        BPCRFPDT.INFO.FDT_TYP = '2';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        if (BPCRFPDT.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_WRITE_HZFS_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.PROD_CODE = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE;
        BPCPOEWA.DATA.PROD_CODE_REL = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].PROD_CD;
        BPCPOEWA.DATA.CNTR_TYPE = "FEES";
        BPCPOEWA.DATA.BR_OLD = WS_CHG_BR;
        BPCPOEWA.DATA.BR_NEW = WS_CHG_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.DESC = BPCGCFEE.FEE_DATA.REMARK;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE_REL);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.DESC);
        IBS.init(SCCGWA, VTCPQTAX);
        VTCPQTAX.INPUT_DATA.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTCPQTAX.INPUT_DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        VTCPQTAX.INPUT_DATA.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        VTCPQTAX.INPUT_DATA.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        VTCPQTAX.INPUT_DATA.BR = BPCGCFEE.FEE_DATA.ACCOUNT_BR;
        VTCPQTAX.INPUT_DATA.CNTR_TYPE = "FEES";
        VTCPQTAX.INPUT_DATA.PROD_CD = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE;
        VTCPQTAX.INPUT_DATA.PROD_CD_REL = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].PROD_CD;
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.PROD_CD);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.PROD_CD_REL);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC);
        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            VTCPQTAX.INPUT_DATA.CI_NO = CICACCU.DATA.CI_NO;
            BPCPOEWA.DATA.CI_NO = CICACCU.DATA.CI_NO;
        }
        VTCPQTAX.INPUT_DATA.CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
        VTCPQTAX.INPUT_DATA.YJ_AMT = WS_YS_AMT;
        S000_CALL_VTZPQTAX();
        if (pgmRtn) return;
        BPCPOEWA.DATA.EVENT_CODE = "HZFS";
        BPCPOEWA.DATA.AMT_INFO[05-1].AMT = WS_YS_AMT;
        BPCPOEWA.DATA.AMT_INFO[07-1].AMT = VTCPQTAX.OUTPUT_DATA.TAX_AMT;
        BPCPOEWA.DATA.AMT_INFO[01-1].AMT = WS_YS_AMT - VTCPQTAX.OUTPUT_DATA.TAX_AMT;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[01-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[05-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[07-1].AMT);
        if (BPCGPFEE.TX_DATA3.CARD_PSBK_NO.trim().length() > 0) {
            BPCPOEWA.DATA.THEIR_AC = BPCGPFEE.TX_DATA3.CARD_PSBK_NO;
        } else {
            BPCPOEWA.DATA.THEIR_AC = WS_CHG_ORG_AC;
        }
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.THEIR_AC);
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void R000_WRITE_BPTNHIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.AC = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
        BPCPNHIS.INFO.CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
        BPCPNHIS.INFO.CCY_TYPE = BPCGPFEE.TX_DATA3.CCY_TYPE;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = BPCGPFEE.CUS_DATA.CUS_INFO[1-1].CUS_NO;
        BPCPNHIS.INFO.FMT_ID = "BPRFPDT";
        BPCPNHIS.INFO.FMT_ID_LEN = 558;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRFPDT;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_CHARGE_GL_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOFBAS.VAL.FEE_CHG_TYPE);
        if (BPCOFBAS.VAL.FEE_CHG_TYPE == '0') {
            IBS.init(SCCGWA, AICUUPIA);
            AICUUPIA.DATA.AC_NO = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
            AICUUPIA.DATA.CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
            AICUUPIA.DATA.AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AICUUPIA.DATA.RVS_NO = BPCGPFEE.TX_DATA3.CREV_NO;
            AICUUPIA.DATA.POST_NARR = BPCOFBAS.VAL.FEE_TX_MMO;
            AICUUPIA.DATA.EVENT_CODE = "DR";
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        } else {
            if (BPCOFBAS.VAL.FEE_CHG_TYPE == '1') {
                IBS.init(SCCGWA, AICUUPIA);
                AICUUPIA.DATA.AC_NO = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
                AICUUPIA.DATA.CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
                AICUUPIA.DATA.AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
                AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                AICUUPIA.DATA.RVS_NO = BPCGPFEE.TX_DATA3.CREV_NO;
                AICUUPIA.DATA.POST_NARR = BPCOFBAS.VAL.FEE_TX_MMO;
                AICUUPIA.DATA.EVENT_CODE = "CR";
                S000_CALL_AIZUUPIA();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_TRANS_REV_DDCUDRAC_IN_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        IBS.init(SCCGWA, DCCURHLD);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DDCUDRAC.VAL_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        }
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY);
        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '0') {
            DDCUDRAC.AC = WS_CHG_ORG_AC;
            CEP.TRC(SCCGWA, DDCUDRAC.AC);
        } else if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '4') {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                DDCUDRAC.CARD_NO = WS_CARD_TABLE[WS_CNT1-1].WS_CARD_NO;
            } else {
                DDCUDRAC.CARD_NO = BPCGPFEE.TX_DATA3.CARD_PSBK_NO;
            }
            DDCUDRAC.BV_TYP = '1';
        } else if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '5') {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                DDCUDRAC.AC = WS_CARD_TABLE[WS_CNT1-1].WS_CARD_NO;
            } else {
                DDCUDRAC.AC = BPCGPFEE.TX_DATA3.CARD_PSBK_NO;
            }
            DDCUDRAC.BV_TYP = '2';
        } else if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '6') {
            DDCUDRAC.AC = WS_CHG_ORG_AC;
            DDCUDRAC.CHQ_NO = BPCGPFEE.TX_DATA3.CARD_PSBK_NO;
            DDCUDRAC.CHQ_ISS_DATE = BPCGPFEE.TX_DATA3.SALE_DATE;
            DDCUDRAC.CHQ_TYPE = '2';
            DDCUDRAC.PCHQ_FLG = 'Y';
        } else if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '7') {
            DDCUDRAC.AC = WS_CHG_ORG_AC;
            DDCUDRAC.CHQ_NO = BPCGPFEE.TX_DATA3.CARD_PSBK_NO;
            DDCUDRAC.CHQ_ISS_DATE = BPCGPFEE.TX_DATA3.SALE_DATE;
            DDCUDRAC.CHQ_TYPE = '3';
            DDCUDRAC.PCHQ_FLG = 'Y';
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CHG_MTH_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCUDRAC.CHQ_NO);
        CEP.TRC(SCCGWA, DDCUDRAC.CHQ_TYPE);
        DDCUDRAC.BANK_DR_FLG = 'Y';
        DDCUDRAC.CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
        DDCUDRAC.TX_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.TX_MMO = BPCOFBAS.VAL.FEE_TX_MMO;
        DDCUDRAC.REMARKS = BPCGCFEE.FEE_DATA.REMARK;
        DDCUDRAC.CCY_TYPE = BPCGPFEE.TX_DATA3.CCY_TYPE;
        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY.equalsIgnoreCase("156")) {
            DDCUDRAC.CCY_TYPE = '1';
        }
        CEP.TRC(SCCGWA, "LAOZHENGJIE0710");
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCUDRAC.CCY_TYPE);
        DDCUDRAC.TX_TYPE = 'T';
        CEP.TRC(SCCGWA, DDCUDRAC.TX_TYPE);
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.HLD_NO);
        if (BPCGPFEE.TX_DATA3.HLD_NO.trim().length() > 0 
            && WS_CNT1 == 1) {
            DCCURHLD.DATA.HLD_NO = BPCGPFEE.TX_DATA3.HLD_NO;
            DCCURHLD.DATA.RAMT = WS_CHG_AMT_TOL;
        }
        if (DCCURHLD.DATA.HLD_NO.trim().length() > 0 
            && DCCURHLD.DATA.RAMT != 0) {
            S000_CALL_DCZURHLD();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.SERV_CODE == null) SCCGWA.COMM_AREA.SERV_CODE = "";
        JIBS_tmp_int = SCCGWA.COMM_AREA.SERV_CODE.length();
        for (int i=0;i<7-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.SERV_CODE += " ";
        if (WS_BAL_LESS_FLG == 'Y' 
            && BPCOFBAS.VAL.DEBT_METH != '2' 
            && SCCGWA.COMM_AREA.SERV_CODE.substring(0, 3).equalsIgnoreCase("BSP") 
            && BPCGPFEE.TX_DATA2.FEE_CTRT_NO.trim().length() == 0 
            && (SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && SCCGWA.COMM_AREA.REVERSAL_IND != 'Y')) {
            DDCUDRAC.TX_AMT = WS_CHG_AMT_ACT;
        }
        if ((BPCGPFEE.TX_DATA3.PROC_TYPE == '1' 
            || BPCGPFEE.TX_DATA3.PROC_TYPE == '2')) {
            CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
            DDCUDRAC.TX_AMT = 0 - DDCUDRAC.TX_AMT;
            CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SERV_CODE);
        if (SCCGWA.COMM_AREA.SERV_CODE == null) SCCGWA.COMM_AREA.SERV_CODE = "";
        JIBS_tmp_int = SCCGWA.COMM_AREA.SERV_CODE.length();
        for (int i=0;i<7-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.SERV_CODE += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        JIBS_tmp_str[2] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("9999156") 
            || JIBS_tmp_str[2].equalsIgnoreCase("9999159") 
            || SCCGWA.COMM_AREA.SERV_CODE.substring(0, 3).equalsIgnoreCase("BSP")) {
            CEP.TRC(SCCGWA, "QAQAQ");
            DDCUDRAC.BV_TYP = '3';
        }
        DDCUDRAC.CHK_PSW_FLG = 'N';
        CEP.TRC(SCCGWA, DDCUDRAC.TSTS_TABL);
        DDCUDRAC.TSTS_TABL = "0016";
        CEP.TRC(SCCGWA, DDCUDRAC.TSTS_TABL);
    }
    public void R000_TRANS_REV_DDCUCRAC_IN_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '0') {
            DDCUCRAC.AC = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
        }
        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '4' 
            || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '5') {
            DDCUCRAC.CARD_NO = BPCGPFEE.TX_DATA3.CARD_PSBK_NO;
        }
        DDCUCRAC.CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
        DDCUCRAC.TX_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
        DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUCRAC.TX_MMO = BPCOFBAS.VAL.FEE_TX_MMO;
        DDCUCRAC.CCY_TYPE = BPCGPFEE.TX_DATA3.CCY_TYPE;
        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY.equalsIgnoreCase("156")) {
            DDCUCRAC.CCY_TYPE = '1';
        }
        DDCUCRAC.BANK_CR_FLG = 'Y';
        DDCUCRAC.TX_TYPE = 'T';
        if (SCCGWA.COMM_AREA.SERV_CODE == null) SCCGWA.COMM_AREA.SERV_CODE = "";
        JIBS_tmp_int = SCCGWA.COMM_AREA.SERV_CODE.length();
        for (int i=0;i<7-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.SERV_CODE += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        JIBS_tmp_str[2] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("9999156") 
            || SCCGWA.COMM_AREA.SERV_CODE.substring(0, 3).equalsIgnoreCase("BSP") 
            || JIBS_tmp_str[2].equalsIgnoreCase("9999159")) {
            DDCUCRAC.BV_TYP = '3';
        }
    }
    public void R000_TRANS_BPCUABOX_IN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUABOX);
        BPCUABOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUABOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUABOX.CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
        BPCUABOX.AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
    }
    public void R000_TRANS_BPCUSBOX_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUSBOX);
        BPCUSBOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUSBOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUSBOX.CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
        BPCUSBOX.AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
        BPCUSBOX.CASH_NO = "225";
    }
    public void R000_GET_FEE_HIST_BY_JRN_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCGCFEE);
        BPCGCFEE.FEE_DATA.FEE_CNT = 0;
        WS_FEHIS_FLG = 'Y';
        IBS.init(SCCGWA, BPRFEHIS);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPRFEHIS.KEY.JRNNO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
            BPRFEHIS.KEY.AC_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        } else {
            if (BPCGPFEE.TX_DATA.REV_AC_DATE == 0 
                || BPCGPFEE.TX_DATA.REV_JRNNO == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REV_DATA_MUSTINPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                BPRFEHIS.KEY.JRNNO = BPCGPFEE.TX_DATA.REV_JRNNO;
                BPRFEHIS.KEY.AC_DT = BPCGPFEE.TX_DATA.REV_AC_DATE;
            }
        }
        WS_HIS_FND_FLG = 'N';
        T000_STARTBR_BPTFEHIS_CN();
        if (pgmRtn) return;
        T000_READNEXT_BPTFEHIS_CN();
        if (pgmRtn) return;
        while (WS_FEHIS_FLG != 'N') {
            WS_HIS_FND_FLG = 'Y';
            if (BPRFEHIS.TX_STS == 'R') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_REVERSALED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            BPCGCFEE.FEE_DATA.FEE_CNT += 1;
            WS_FEE_CTRT_CN.WS_FEE_CTRT_TABLE[BPCGCFEE.FEE_DATA.FEE_CNT-1].WS_FEE_CTRT_NO = BPRFEHIS.FEE_CTRT_NO;
            CEP.TRC(SCCGWA, BPRFEHIS.TX_CD);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].AMO_FLG);
            if (BPRFEHIS.FEE_CTRT_NO == null) BPRFEHIS.FEE_CTRT_NO = "";
            JIBS_tmp_int = BPRFEHIS.FEE_CTRT_NO.length();
            for (int i=0;i<25-JIBS_tmp_int;i++) BPRFEHIS.FEE_CTRT_NO += " ";
            if (BPRFEHIS.FEE_CTRT_NO.substring(4 - 1, 4 + 2 - 1).equalsIgnoreCase("51") 
                && (!BPRFEHIS.TX_CD.equalsIgnoreCase("9999122") 
                && !BPRFEHIS.TX_CD.equalsIgnoreCase("9999123"))) {
                CEP.TRC(SCCGWA, "20150420");
                BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].AMO_FLG = '1';
            }
            CEP.TRC(SCCGWA, WS_FEE_CTRT_CN.WS_FEE_CTRT_TABLE[BPCGCFEE.FEE_DATA.FEE_CNT-1].WS_FEE_CTRT_NO);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].AMO_FLG);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_CNT);
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].FEE_CODE = BPRFEHIS.FEE_CODE;
            WS_FEE_CODE = BPRFEHIS.FEE_CODE;
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].FEE_CCY = BPRFEHIS.FEE_CCY;
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].FEE_BAS = BPRFEHIS.FEE_BAS;
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].FEE_AMT = BPRFEHIS.FEE_AMT;
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_AC_TY = BPRFEHIS.CHG_AC_TY;
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_AC = BPRFEHIS.CHG_AC;
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_CCY = BPRFEHIS.CHG_CCY;
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_BAS = BPRFEHIS.CHG_BAS;
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_AMT = BPRFEHIS.CHG_AMT;
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].ADJ_AMT = BPRFEHIS.ADJ_AMT;
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].DER_AMT = BPRFEHIS.DER_AMT;
            JIBS_NumStr = "" + 0;
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].DER_AUTH = JIBS_NumStr.charAt(0);
            WS_FEHIS_BRANCH[BPCGCFEE.FEE_DATA.FEE_CNT-1].WS_FEHIS_BR = BPRFEHIS.CHG_BR;
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].PROD_CD = BPRFEHIS.TX_PROD;
            IBS.init(SCCGWA, BPCGPFEE);
            IBS.CPY2CLS(SCCGWA, BPRFEHIS.BLOB_PFEE_DATA, BPCGPFEE);
            CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.CARD_PSBK_NO);
            WS_CARD_TABLE[BPCGCFEE.FEE_DATA.FEE_CNT-1].WS_CARD_NO = BPCGPFEE.TX_DATA3.CARD_PSBK_NO;
            if (BPCGPFEE.TX_DATA3.CARD_PSBK_NO.trim().length() == 0 
                && BPRFEHIS.CARD_PSBK_NO.trim().length() > 0) {
                CEP.TRC(SCCGWA, BPRFEHIS.CARD_PSBK_NO);
                WS_CARD_TABLE[BPCGCFEE.FEE_DATA.FEE_CNT-1].WS_CARD_NO = BPRFEHIS.CARD_PSBK_NO;
            }
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_CNT);
            CEP.TRC(SCCGWA, WS_CARD_TABLE[BPCGCFEE.FEE_DATA.FEE_CNT-1].WS_CARD_NO);
            CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.CLT_TYPE);
            if (BPCGPFEE.TX_DATA3.CLT_TYPE == '1') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ACC_FEE_NOT_CANCEL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            BPRFEHIS.TX_STS = 'C';
            BPRFEHIS.TX_REV_DT = SCCGWA.COMM_AREA.TR_DATE;
            T000_REWRITE_BPTFEHIS_CN();
            if (pgmRtn) return;
            T000_READNEXT_BPTFEHIS_CN();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTFEHIS_CN();
        if (pgmRtn) return;
    }
    public void B015_GET_ACC_FEE_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[1-1].FEE_CODE);
        WS_FEE_CODE = BPCGCFEE.FEE_DATA.FEE_INFO[1-1].FEE_CODE;
        IBS.init(SCCGWA, BPCGCFEE);
        BPCGCFEE.FEE_DATA.FEE_CNT = 0;
        WS_FEHIS_FLG = 'Y';
        IBS.init(SCCGWA, BPRFEHIS);
        IBS.init(SCCGWA, WS_OUTPUT_BNK_ACC);
        BPRFEHIS.KEY.AC_DT = BPCGPFEE.TX_DATA3.BAT_OTRT_DT;
        BPRFEHIS.KEY.JRNNO = BPCGPFEE.TX_DATA3.BAT_OTRT_JRN;
        BPRFEHIS.KEY.JRN_SEQ = BPCGPFEE.TX_DATA3.BAT_OTRT_SEQ;
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.JRN_SEQ);
        if (BPRFEHIS.KEY.JRN_SEQ != 0) {
            T000_READ_BPTFEHIS_UPD_CN();
            if (pgmRtn) return;
        } else {
            T000_READ_FIRST_BPTFEHIS_CN();
            if (pgmRtn) return;
            T000_READNEXT_BPTFEHIS_CN();
            if (pgmRtn) return;
        }
        if (WS_FEHIS_FLG == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_HIS_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRFEHIS.TX_STS == 'N') {
            BPCGCFEE.FEE_DATA.FEE_CNT += 1;
            WS_FEE_CTRT_CN.WS_FEE_CTRT_TABLE[BPCGCFEE.FEE_DATA.FEE_CNT-1].WS_FEE_CTRT_NO = BPRFEHIS.FEE_CTRT_NO;
            CEP.TRC(SCCGWA, BPRFEHIS.TX_CD);
            if (BPRFEHIS.FEE_CTRT_NO.trim().length() > 0 
                && (!BPRFEHIS.TX_CD.equalsIgnoreCase("9999122") 
                && !BPRFEHIS.TX_CD.equalsIgnoreCase("9999123") 
                && !BPRFEHIS.TX_CD.equalsIgnoreCase("9999156"))) {
                BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].AMO_FLG = '1';
            }
            CEP.TRC(SCCGWA, WS_FEE_CTRT_CN.WS_FEE_CTRT_TABLE[BPCGCFEE.FEE_DATA.FEE_CNT-1].WS_FEE_CTRT_NO);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].AMO_FLG);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_CNT);
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].FEE_CODE = BPRFEHIS.FEE_CODE;
            WS_FEE_CODE = BPRFEHIS.FEE_CODE;
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].FEE_CCY = BPRFEHIS.FEE_CCY;
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].FEE_BAS = BPRFEHIS.FEE_BAS;
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].FEE_AMT = BPRFEHIS.FEE_AMT;
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_AC_TY = BPRFEHIS.CHG_AC_TY;
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_AC = BPRFEHIS.CHG_AC;
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_CCY = BPRFEHIS.CHG_CCY;
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_BAS = BPRFEHIS.CHG_BAS;
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_AMT = BPRFEHIS.CHG_AMT;
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].ADJ_AMT = BPRFEHIS.ADJ_AMT;
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].DER_AMT = BPRFEHIS.DER_AMT;
            JIBS_NumStr = "" + 0;
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].DER_AUTH = JIBS_NumStr.charAt(0);
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].TX_AC = BPRFEHIS.TX_AC;
            BPCGPFEE.CUS_DATA.CUS_INFO[1-1].CUS_NO = BPRFEHIS.TX_CI;
            BPCGPFEE.TX_DATA3.FEE_CTRT = BPRFEHIS.FEE_CTRT;
            BPRFEHIS.TX_STS = 'C';
            BPRFEHIS.TX_REV_DT = SCCGWA.COMM_AREA.TR_DATE;
            WS_FEHIS_BRANCH[BPCGCFEE.FEE_DATA.FEE_CNT-1].WS_FEHIS_BR = BPRFEHIS.CHG_BR;
            WS_OUTPUT_BNK_ACC.OUTP_FEE_CODE1 = BPRFEHIS.FEE_CODE;
            WS_OUTPUT_BNK_ACC.OUTP_ADJ_AMT1 = BPRFEHIS.ADJ_AMT;
            WS_OUTPUT_BNK_ACC.OUTP_FCTR_NO1 = BPRFEHIS.FEE_CTRT_NO;
            WS_OUTPUT_BNK_ACC.OUTP_TX_DT1 = BPRFEHIS.TX_DT;
            WS_OUTPUT_BNK_ACC.OUTP_TX_TM1 = BPRFEHIS.TX_TM;
            BPCGPFEE.TX_DATA3.BSNS_NO = BPRFEHIS.BSNS_NO;
            WS_OUTPUT_BNK_ACC.OUTP_CHG_BR = BPRFEHIS.CHG_BR;
            WS_OUTPUT_BNK_ACC.OUTP_TX_TLR = BPRFEHIS.TX_TLR;
            T000_REWRITE_BPTFEHIS_CN();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_REVERSALED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCGPFEE.TX_DATA3.BAT_OTRT_SEQ == 0) {
            T000_ENDBR_BPTFEHIS_CN();
            if (pgmRtn) return;
        }
    }
    public void B017_CHANGE_AMO_ENDDT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRFCTR);
        IBS.init(SCCGWA, BPRFCTR);
        BPRFCTR.KEY.CTRT_NO = WS_FEE_CTRT_CN.WS_FEE_CTRT_TABLE[1-1].WS_FEE_CTRT_NO;
        BPCRFCTR.INFO.FUNC = 'R';
        S000_CALL_BPZRFCTR();
        if (pgmRtn) return;
        if (BPRFCTR.MATURITY_DATE > SCCGWA.COMM_AREA.AC_DATE 
            && (BPRFCTR.FEE_STATUS == '0' 
            || BPRFCTR.FEE_STATUS == '1')) {
            WS_MATURITY_DATE = BPRFCTR.MATURITY_DATE;
            BPRFCTR.MATURITY_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCRFCTR.INFO.FUNC = 'U';
            S000_CALL_BPZRFCTR();
            if (pgmRtn) return;
            B019_TRANS_NHIS_PROC();
            if (pgmRtn) return;
            S000_CALL_BPZPNHIS();
            if (pgmRtn) return;
        }
    }
    public void R000_NOT_GET_PROC_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRFPDT);
        IBS.init(SCCGWA, BPRFPDT);
        IBS.init(SCCGWA, BPRFADT);
        BPRFPDT.KEY.TRT_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        CEP.TRC(SCCGWA, BPRFPDT.KEY.TRT_DT);
        BPRFPDT.KEY.JRN_NO = "" + SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        JIBS_tmp_int = BPRFPDT.KEY.JRN_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPRFPDT.KEY.JRN_NO = "0" + BPRFPDT.KEY.JRN_NO;
        CEP.TRC(SCCGWA, BPRFPDT.KEY.JRN_NO);
        BPCRFPDT.INFO.FDT_TYP = '2';
        BPCRFPDT.INFO.FUNC = 'L';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        BPCRFPDT.INFO.FUNC = 'N';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_HIS_FND_FLG);
        while (BPCRFPDT.RETURN_INFO != 'N') {
            if (WS_HIS_FND_FLG == 'N') {
                CEP.TRC(SCCGWA, BPRFPDT.ACC_RECH_CNT);
                CEP.TRC(SCCGWA, BPRFPDT.CHG_STS);
                if (BPRFPDT.ACC_RECH_CNT == 0 
                    && BPRFPDT.CHG_STS == '0') {
                    BPRFPDT.CHG_STS = '3';
                    BPRFPDT.UPDATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    BPRFPDT.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
                    BPCRFPDT.INFO.FUNC = 'M';
                    S000_CALL_BPZRFPDT();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, BPRFPDT.SRC_TR_CD);
                    if (BPRFPDT.SRC_TR_CD.equalsIgnoreCase("9991198")) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_E_HAD_OWE_FEE;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    } else {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_W_HAD_OWE_FEE;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_W_HAD_OWE_FEE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            BPCRFPDT.INFO.FUNC = 'N';
            S000_CALL_BPZRFPDT();
            if (pgmRtn) return;
        }
        BPCRFPDT.INFO.FUNC = 'E';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCRFPDT);
        BPRFADT.KEY.TRT_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        CEP.TRC(SCCGWA, BPRFADT.KEY.TRT_DT);
        BPRFADT.KEY.JRN_NO = "" + SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        JIBS_tmp_int = BPRFADT.KEY.JRN_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPRFADT.KEY.JRN_NO = "0" + BPRFADT.KEY.JRN_NO;
        CEP.TRC(SCCGWA, BPRFADT.KEY.JRN_NO);
        BPCRFPDT.INFO.FDT_TYP = '0';
        BPCRFPDT.INFO.FUNC = 'L';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        BPCRFPDT.INFO.FUNC = 'N';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        while (BPCRFPDT.RETURN_INFO != 'N') {
            CEP.TRC(SCCGWA, BPRFADT.KEY.JRN_SEQ);
            CEP.TRC(SCCGWA, BPRFADT.FEE_SRC);
            if (BPRFADT.FEE_SRC != '1') {
                BPRFADT.CHG_STS = '3';
                BPRFADT.UPDATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPRFADT.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
                CEP.TRC(SCCGWA, "S35624-FADT");
                WS_CNT1 += 1;
                BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT = BPRFADT.CHG_AMT;
                BPCGCFEE.FEE_DATA.ACCOUNT_BR = BPRFADT.CHG_BR;
                BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY = BPRFADT.CCY;
                BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE = BPRFADT.FEE_CODE;
                BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC = BPRFADT.CHG_AC;
                BPCRFPDT.INFO.FUNC = 'M';
                S000_CALL_BPZRFPDT();
                if (pgmRtn) return;
            }
            BPCRFPDT.INFO.FUNC = 'N';
            S000_CALL_BPZRFPDT();
            if (pgmRtn) return;
        }
        BPCRFPDT.INFO.FUNC = 'E';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
    }
    public void T000_GET_FSCH_INFO() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("9999156")) {
            BPCOCSVR.VAL.AUT_FLG = '0';
        }
    }
    public void S000_CALL_BPZRFSCH() throws IOException,SQLException,Exception {
        BPCRFSCH.INFO.POINTER = BPRFSCH;
        BPCRFSCH.INFO.REC_LEN = 816;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-FEESCH", BPCRFSCH);
        if (BPCRFSCH.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFSCH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B029_GET_CTRT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQCTR);
        BPCPQCTR.KEY.CTRT_NO = BPCGPFEE.TX_DATA2.FEE_CTRT_NO;
        S000_CALL_BPZPQCTR();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQCTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-FEECTRSCH", BPCPQCTR);
        if (BPCPQCTR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQCTR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_VTZPQTAX() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.TR_DATE);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.JRNNO);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.TR_BR);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.TX_CODE);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.BR);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.CNTR_TYPE);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.PROD_CD);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.PROD_CD_REL);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.CI_NO);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.CCY);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.SH_AMT);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.YJ_AMT);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.INQ_TAX_FLG);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            VTCPQTAX.INPUT_DATA.EC_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            VTCPQTAX.INPUT_DATA.EC_JRNNO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        }
        IBS.CALLCPN(SCCGWA, "VT-P-QUERY-TAX", VTCPQTAX);
        CEP.TRC(SCCGWA, VTCPQTAX.RC);
        if (VTCPQTAX.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCPQTAX.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
        CEP.TRC(SCCGWA, VTCPQTAX.OUTPUT_DATA.TAX_AMT);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        WS_TS_FLG = "U";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
        IBS.CALLCPN(SCCGWA, CPN_REV_DD_DDZUDRAC, DDCUDRAC);
        WS_TS_FLG = "V";
        WS_NHIST_FLG = 'Y';
        S000_TRC_WS_TS();
        if (pgmRtn) return;
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
        WS_NHIST_FLG = 'Y';
    }
    public void S000_CALL_SET_FEE_IND() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_SET_FEE_IND, null);
    }
    public void S000_CALL_BPZFUSVR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_MAINTAIN_FSVR, BPCOCSVR);
    }
    public void S000_CALL_BPZFUBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_MAINTAIN_FBAS, BPCOFBAS);
    }
    public void S000_CALL_FEE_CHARGE_CASH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CALL_CASH_ADD_BOX, BPCUABOX);
        WS_NHIST_FLG = 'Y';
    }
    public void S000_CALL_FEE_SUB_CASH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CALL_CASH_SUB_BOX, BPCUSBOX);
        WS_NHIST_FLG = 'Y';
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PROC_FHIS, BPCPFHIS);
        CEP.TRC(SCCGWA, BPCPFHIS.RC.RC_CODE);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZURHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHLD", DCCURHLD);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_NHIST_FLG = 'Y';
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B060_PRE_GENCTR_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSFECT);
        BPCSFECT.FUNC_CODE = 'A';
        CEP.TRC(SCCGWA, BPCGPFEE.CUS_DATA.CUS_INFO[1-1].CUS_NO);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY);
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.CARD_PSBK_NO);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC);
        if (BPCGPFEE.CUS_DATA.CUS_INFO[1-1].CUS_NO.trim().length() > 0) {
            BPCSFECT.CINO = BPCGPFEE.CUS_DATA.CUS_INFO[1-1].CUS_NO;
        } else {
            if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY != '3' 
                && (BPCGPFEE.TX_DATA3.CARD_PSBK_NO.trim().length() == 0 
                && BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC.trim().length() == 0)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ACORCUS_MUSTINPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        BPCSFECT.CHG_ACNO = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC;
        CEP.TRC(SCCGWA, BPCSFECT.CINO);
        CEP.TRC(SCCGWA, BPCSFECT.CHG_ACNO);
        BPCSFECT.CT_TYP = "FEES";
        BPCSFECT.FEE_TYP = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].FEE_CODE;
        CEP.TRC(SCCGWA, "FEE CONTRACT ACCOUNT BR----");
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.ACCOUNT_BR);
        if (BPCGCFEE.FEE_DATA.ACCOUNT_BR != 0) {
            BPCSFECT.BOOK_ACCT = BPCGCFEE.FEE_DATA.ACCOUNT_BR;
        } else {
            BPCSFECT.BOOK_ACCT = WS_CHG_BR;
            CEP.TRC(SCCGWA, "DEVZHJ19");
            CEP.TRC(SCCGWA, WS_CHG_BR);
        }
        BPCSFECT.PRDT_TYP = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].PROD_CD;
        BPCSFECT.START_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCSFECT.MATURITY_DT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].AMO_EDDT;
        BPCSFECT.HOL_OVR = 'Y';
        BPCSFECT.HOL_METH = 'N';
        BPCSFECT.CAL_CD1 = "CN";
        BPCSFECT.CAL_CD2 = "CN";
        if (BPCOFBAS.VAL.FEE_CHG_TYPE == '0') {
            BPCSFECT.PAY_IND = 'R';
        }
        if (BPCOFBAS.VAL.FEE_CHG_TYPE == '1') {
            BPCSFECT.PAY_IND = 'P';
        }
        BPCSFECT.PAY_METH = 'P';
        BPCSFECT.INT_BASIC = "02";
        BPCSFECT.TXN_CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].TX_CCY;
        BPCSFECT.TXN_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].TX_AMT;
        BPCSFECT.CHG_CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
        BPCSFECT.CHG_CCY_REAL = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_CCY;
        BPCSFECT.CHG_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].ADJ_AMT;
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.CCY_TYPE);
        if (BPCGPFEE.TX_DATA3.CCY_TYPE != ' ') {
            BPCSFECT.CCY_TYPE = BPCGPFEE.TX_DATA3.CCY_TYPE;
        } else {
            BPCSFECT.CCY_TYPE = '1';
        }
        CEP.TRC(SCCGWA, BPCSFECT.CCY_TYPE);
        if (WS_BAL_LESS_FLG == 'Y') {
            BPCSFECT.CHG_AMT = WS_CHG_AMT_ACT;
        }
        BPCSFECT.CHG_AMT_REAL = BPCSFECT.CHG_AMT;
        BPCSFECT.CHG_METH = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY;
        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '4' 
            || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '5') {
            BPCSFECT.CP_NO = BPCGPFEE.TX_DATA3.CARD_PSBK_NO;
        }
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY);
        if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '6' 
            || BPCGCFEE.FEE_DATA.FEE_INFO[WS_CNT1-1].CHG_AC_TY == '7') {
            BPCSFECT.CHQ_NO = BPCGPFEE.TX_DATA3.CARD_PSBK_NO;
            BPCSFECT.SALE_DT = BPCGPFEE.TX_DATA3.SALE_DATE;
        }
        BPCSFECT.CT_STS = '1';
        BPCSFECT.RLTD_CTNO = BPCGPFEE.TX_DATA3.FEE_CTRT;
        BPCSFECT.REMARK = BPCGCFEE.FEE_DATA.REMARK;
        BPCSFECT.CHG_FLG = 'N';
        BPCSFECT.ACU_TYP = 'F';
    }
    public void B060_PRE_GENCTR_DATA_CLT() throws IOException,SQLException,Exception {
        WS_TS_FLG = "J";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GEN CTR - CLT");
        IBS.init(SCCGWA, BPCSFECT);
        BPCSFECT.FUNC_CODE = 'A';
        if (BPRFADT.CHG_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = BPRFADT.CHG_AC;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            BPCSFECT.CINO = CICACCU.DATA.CI_NO;
            BPCSFECT.CHG_ACNO = BPRFADT.CHG_AC;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ACORCUS_MUSTINPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCSFECT.CT_TYP = "FEES";
        BPCSFECT.FEE_TYP = BPRFADT.FEE_CODE;
        BPCSFECT.BOOK_ACCT = BPRFADT.CHG_BR;
        BPCSFECT.PRDT_TYP = " ";
        if (BPRFADT.AMO_EDT <= SCCGWA.COMM_AREA.AC_DATE) {
            BPCSFECT.START_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCSFECT.MATURITY_DT = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            BPCSFECT.START_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCSFECT.MATURITY_DT = BPRFADT.AMO_EDT;
        }
        BPCSFECT.HOL_OVR = 'Y';
        BPCSFECT.HOL_METH = 'N';
        BPCSFECT.CAL_CD1 = "CN";
        BPCSFECT.CAL_CD2 = "CN";
        BPCSFECT.PAY_IND = 'R';
        BPCSFECT.PAY_METH = 'P';
        BPCSFECT.RLTD_CTNO = BPRFADT.CMMT_NO;
        BPCSFECT.TXN_CCY = BPRFADT.CCY;
        BPCSFECT.TXN_AMT = 0;
        BPCSFECT.CHG_CCY = BPRFADT.CCY;
        BPCSFECT.CHG_CCY_REAL = BPRFADT.CCY;
        BPCSFECT.CHG_AMT = BPRFADT.CHG_AMT;
        BPCSFECT.CHG_AMT_REAL = BPRFADT.CHG_AMT;
        BPCSFECT.CHG_METH = '0';
        BPCSFECT.CT_STS = '1';
        BPCSFECT.REMARK = BPRFADT.REMARK;
        BPCSFECT.CHG_FLG = 'N';
        BPCSFECT.ACU_TYP = 'F';
        BPCSFECT.INT_BASIC = "02";
        WS_TS_FLG = "K";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
    }
    public void B062_PRE_REV_DATA() throws IOException,SQLException,Exception {
        BPCSFECT.FUNC_CODE = 'R';
        BPCSFECT.CTNO = WS_FEE_CTRT_CN.WS_FEE_CTRT_TABLE[WS_CNT1-1].WS_FEE_CTRT_NO;
        BPCSFECT.CHG_FLG = 'N';
        CEP.TRC(SCCGWA, WS_FEE_CTRT_CN.WS_FEE_CTRT_TABLE[WS_CNT1-1].WS_FEE_CTRT_NO);
    }
    public void B061_CALL_BPZSFECT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-FECT-MAINT", BPCSFECT, true);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-P-INQ-ORG";
        SCCCALL.COMMAREA_PTR = BPCPQORG;
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        WS_TS_FLG = "E";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU         ", CICACCU);
        WS_TS_FLG = "F";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
    }
    public void S000_CALL_DDZSQIFA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-INQ-IFA", DDCSQIFA, true);
    }
    public void S000_CALL_BPZQCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CNGL", BPCQCNGL);
        if (BPCQCNGL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCNGL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUGLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MAINT-GLM", BPCUGLM);
        if (BPCUGLM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUGLM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BPTFEAG_01() throws IOException,SQLException,Exception {
        BPTFEAG_BR.rp = new DBParm();
        BPTFEAG_BR.rp.TableName = "BPTFEAG";
        BPTFEAG_BR.rp.where = "CLT_CI_NO = :WS_SGN_CINO";
        IBS.STARTBR(SCCGWA, BPRFEAG, this, BPTFEAG_BR);
    }
    public void T000_STARTBR_BPTFEAG_02() throws IOException,SQLException,Exception {
        BPTFEAG_BR.rp = new DBParm();
        BPTFEAG_BR.rp.TableName = "BPTFEAG";
        BPTFEAG_BR.rp.where = "PRDT_CODE = :WS_PROD_CD";
        IBS.STARTBR(SCCGWA, BPRFEAG, this, BPTFEAG_BR);
    }
    public void T000_ENDBR_BPTFEAG() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFEAG_BR);
    }
    public void T000_READNEXT_BPTFEAG() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFEAG, this, BPTFEAG_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FILE_STS = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FILE_STS = 'N';
        } else {
        }
    }
    public void T000_UPDATE_BPTFEAG() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_BPZRFPDT() throws IOException,SQLException,Exception {
        if (BPCRFPDT.INFO.FDT_TYP == '0' 
            || BPCRFPDT.INFO.FDT_TYP == '1') {
            BPCRFPDT.INFO.POINTER = BPRFADT;
            BPCRFPDT.INFO.LEN = 558;
        } else {
            BPCRFPDT.INFO.POINTER = BPRFPDT;
            BPCRFPDT.INFO.LEN = 558;
        }
        IBS.CALLCPN(SCCGWA, CPN_R_FPDT, BPCRFPDT);
        if (BPCRFPDT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFPDT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRFCTR() throws IOException,SQLException,Exception {
        BPCRFCTR.INFO.POINTER = BPRFCTR;
        BPCRFCTR.INFO.REC_LEN = 889;
        IBS.CALLCPN(SCCGWA, CPN_CALL_BPZRFCTR, BPCRFCTR);
        CEP.TRC(SCCGWA, BPCRFCTR.RC);
        if (BPCRFCTR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFCTR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CALL_BPZPNHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B019_TRANS_NHIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.init(SCCGWA, BPCNFCTR);
        IBS.init(SCCGWA, BPCOFCTR);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = BPRFCTR.CI_NO;
        BPCPNHIS.INFO.REF_NO = BPRFCTR.KEY.CTRT_NO;
        BPCPNHIS.INFO.TX_RMK = "FEE CONTRACT - UPD";
        BPCPNHIS.INFO.FMT_ID = "BPCHFCTR";
        BPCNFCTR.KEY.CTRT_NO = BPRFCTR.KEY.CTRT_NO;
        BPCNFCTR.CTRT_DESC = BPRFCTR.CTRT_DESC;
        BPCNFCTR.CI_NO = BPRFCTR.CI_NO;
        BPCNFCTR.CTRT_TYPE = BPRFCTR.CTRT_TYPE;
        BPCNFCTR.FEE_TYPE = BPRFCTR.FEE_TYPE;
        BPCNFCTR.BOOK_CENTRE = BPRFCTR.BOOK_CENTRE;
        BPCNFCTR.PRD_TYPE = BPRFCTR.PRD_TYPE;
        BPCNFCTR.START_DATE = BPRFCTR.START_DATE;
        BPCNFCTR.MATURITY_DATE = BPRFCTR.MATURITY_DATE;
        BPCNFCTR.HOLI_OVER = BPRFCTR.HOLI_OVER;
        BPCNFCTR.CAL_CODE1 = BPRFCTR.CAL_CODE1;
        BPCNFCTR.HOLI_METHOD = BPRFCTR.HOLI_METHOD;
        BPCNFCTR.CAL_CODE2 = BPRFCTR.CAL_CODE2;
        BPCNFCTR.PAY_IND = BPRFCTR.PAY_IND;
        BPCNFCTR.CASHFLOW_IND = BPRFCTR.CASHFLOW_IND;
        BPCNFCTR.BANK_PORTF = BPRFCTR.BANK_PORTF;
        BPCNFCTR.PAYMENT_METHOD = BPRFCTR.PAYMENT_METHOD;
        BPCNFCTR.ACCRUAL_TYPE = BPRFCTR.ACCRUAL_TYPE;
        BPCNFCTR.PRICE_METHOD = BPRFCTR.PRICE_METHOD;
        BPCNFCTR.TXN_CCY = BPRFCTR.TXN_CCY;
        BPCNFCTR.PRIN_AMT = BPRFCTR.PRIN_AMT;
        BPCNFCTR.REL_CTRT_NO = BPRFCTR.REL_CTRT_NO;
        BPCNFCTR.AMT_TYPE = BPRFCTR.AMT_TYPE;
        BPCNFCTR.REF_CCY = BPRFCTR.REF_CCY;
        BPCNFCTR.REF_METHOD = BPRFCTR.REF_METHOD;
        BPCNFCTR.CHARGE_CCY = BPRFCTR.CHARGE_CCY;
        BPCNFCTR.CHARGE_AMT = BPRFCTR.CHARGE_AMT;
        BPCNFCTR.CHARGE_METHOD = BPRFCTR.CHARGE_METHOD;
        BPCNFCTR.CR_TO_BR = BPRFCTR.CR_TO_BR;
        BPCNFCTR.CHARGE_AC = BPRFCTR.CHARGE_AC;
        BPCNFCTR.CHQ_NO = BPRFCTR.CHQ_NO;
        BPCNFCTR.NOSTRO_AC = BPRFCTR.NOSTRO_AC;
        BPCNFCTR.GL_MASTER1 = BPRFCTR.GL_MASTER1;
        BPCNFCTR.GL_MASTER2 = BPRFCTR.GL_MASTER2;
        BPCNFCTR.GL_MASTER3 = BPRFCTR.GL_MASTER3;
        BPCNFCTR.GL_MASTER4 = BPRFCTR.GL_MASTER4;
        BPCNFCTR.OIC_NO1 = "" + BPRFCTR.OIC_NO1;
        JIBS_tmp_int = BPCNFCTR.OIC_NO1.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCNFCTR.OIC_NO1 = "0" + BPCNFCTR.OIC_NO1;
        if (BPRFCTR.OIC_CENTRE1.trim().length() == 0) BPCNFCTR.OIC_CENTRE1 = 0;
        else BPCNFCTR.OIC_CENTRE1 = Integer.parseInt(BPRFCTR.OIC_CENTRE1);
        BPCNFCTR.OIC_PCT1 = BPRFCTR.OIC_PCT1;
        BPCNFCTR.OIC_NO2 = "" + BPRFCTR.OIC_NO2;
        JIBS_tmp_int = BPCNFCTR.OIC_NO2.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCNFCTR.OIC_NO2 = "0" + BPCNFCTR.OIC_NO2;
        if (BPRFCTR.OIC_CENTRE2.trim().length() == 0) BPCNFCTR.OIC_CENTRE2 = 0;
        else BPCNFCTR.OIC_CENTRE2 = Integer.parseInt(BPRFCTR.OIC_CENTRE2);
        BPCNFCTR.OIC_PCT2 = BPRFCTR.OIC_PCT2;
        BPCNFCTR.OIC_NO3 = "" + BPRFCTR.OIC_NO3;
        JIBS_tmp_int = BPCNFCTR.OIC_NO3.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCNFCTR.OIC_NO3 = "0" + BPCNFCTR.OIC_NO3;
        if (BPRFCTR.OIC_CENTRE3.trim().length() == 0) BPCNFCTR.OIC_CENTRE3 = 0;
        else BPCNFCTR.OIC_CENTRE3 = Integer.parseInt(BPRFCTR.OIC_CENTRE3);
        BPCNFCTR.OIC_PCT3 = BPRFCTR.OIC_PCT3;
        BPCNFCTR.FEE_STATUS = BPRFCTR.FEE_STATUS;
        BPCNFCTR.REMARK = BPRFCTR.REMARK;
        BPCNFCTR.FEE_BAS_AMT = BPRFCTR.FEE_BAS_AMT;
        BPCNFCTR.CHG_CCY_REAL = BPRFCTR.CHG_CCY_REAL;
        BPCNFCTR.CHG_AMT_REAL = BPRFCTR.CHG_AMT_REAL;
        BPCNFCTR.TICKET = BPRFCTR.TICKET;
        BPCNFCTR.RATE = BPRFCTR.RATE;
        BPCNFCTR.EXG_DATE = BPRFCTR.EXG_DATE;
        BPCNFCTR.EXG_TIME = BPRFCTR.EXG_TIME;
        BPCNFCTR.FCHG_MIN_AMT = BPRFCTR.FCHG_MIN_AMT;
        BPCPNHIS.INFO.FMT_ID_LEN = 1031;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        IBS.CLONE(SCCGWA, BPCNFCTR, BPCOFCTR);
        BPCNFCTR.MATURITY_DATE = WS_MATURITY_DATE;
        BPCPNHIS.INFO.OLD_DAT_PT = BPCOFCTR;
        BPCPNHIS.INFO.NEW_DAT_PT = BPCNFCTR;
    }
    public void B027_CHECK_CHANGE_HLDNO() throws IOException,SQLException,Exception {
        WS_SGN_AC = BPRFEAG.KEY.CLT_AC;
        WS_PROC_DT = SCCGWA.COMM_AREA.AC_DATE;
        WS_FDT_TYP = '1';
        WS_PRC_STS = '0';
        T000_READ_FIRST_BPTFADT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HAS_UNCHG_REC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCCIIHLD);
        DCCIIHLD.INP_DATA.HLD_NO = BPCGPFEE.TX_DATA3.HLD_NO;
        DCCIIHLD.INP_DATA.FUNC = 'I';
        S000_CALL_DCZIIHLD();
        if (pgmRtn) return;
        if (DCCIIHLD.OUT_DATA_TEXT.OUT_DATA[1-1].HLD_STS_O != 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NEW_HOLD_UNNORMAL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_GET_TR_NAME() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTRT);
        IBS.init(SCCGWA, BPCPRMR);
        BPRTRT.KEY.TYP = "TRT";
        BPRTRT.KEY.CD = WS_TR_CD;
        BPCPRMR.DAT_PTR = BPRTRT;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        WS_TR_NAME = BPRTRT.CDESC;
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        CEP.TRC(SCCGWA, WS_TR_CD);
        CEP.TRC(SCCGWA, WS_TR_NAME);
    }
    public void S000_CALL_DCZIIHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-DCZIIHLD", DCCIIHLD, true);
        if (DCCIIHLD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIIHLD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIMCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-CCY", DDCIMCCY);
        CEP.TRC(SCCGWA, DDCIMCCY.RC.RC_CODE);
        if (DDCIMCCY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIMCCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUGCHK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-U-GPRS-AC-CHK", DDCUGCHK);
        CEP.TRC(SCCGWA, DDCUGCHK.RC.RC_CODE);
        if (DDCUGCHK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCUGCHK.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUGCBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-U-GPRS-BAL-INQ", DDCUGCBL);
        if (DDCUGCBL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIMCCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL, true);
        CEP.TRC(SCCGWA, DDCIQBAL.RC.RC_CODE);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQBAL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BPTFADT_UPD() throws IOException,SQLException,Exception {
        BPTFADT_BR.rp = new DBParm();
        BPTFADT_BR.rp.TableName = "BPTFADT";
        BPTFADT_BR.rp.where = "CMMT_NO = :WS_SGN_AC "
            + "AND FEE_SRC = :WS_FDT_TYP "
            + "AND PRC_STS = :WS_PRC_STS "
            + "AND TRT_DT = :WS_PROC_DT "
            + "AND ( CHG_STS = '0' "
            + "OR CHG_STS = '1' )";
        BPTFADT_BR.rp.upd = true;
        BPTFADT_BR.rp.order = "CHG_BR,FEE_CODE";
        IBS.STARTBR(SCCGWA, BPRFADT, this, BPTFADT_BR);
    }
    public void T000_READ_FIRST_BPTFADT() throws IOException,SQLException,Exception {
        BPTFADT_RD = new DBParm();
        BPTFADT_RD.TableName = "BPTFADT";
        BPTFADT_RD.where = "CMMT_NO = :WS_SGN_AC "
            + "AND FEE_SRC = :WS_FDT_TYP "
            + "AND PRC_STS = :WS_PRC_STS "
            + "AND TRT_DT = :WS_PROC_DT "
            + "AND ( CHG_STS = '0' "
            + "OR CHG_STS = '1' )";
        BPTFADT_RD.fst = true;
        IBS.READ(SCCGWA, BPRFADT, this, BPTFADT_RD);
    }
    public void T000_READNEXT_BPTFADT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFADT, this, BPTFADT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FILE_STS = 'F';
            CEP.TRC(SCCGWA, BPRFADT.KEY.TRT_DT);
            CEP.TRC(SCCGWA, BPRFADT.KEY.JRN_NO);
            CEP.TRC(SCCGWA, BPRFADT.KEY.JRN_SEQ);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FILE_STS = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTFADT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFADT_BR);
    }
    public void T000_REWRITE_BPTFADT() throws IOException,SQLException,Exception {
        BPTFADT_RD = new DBParm();
        BPTFADT_RD.TableName = "BPTFADT";
        IBS.REWRITE(SCCGWA, BPRFADT, BPTFADT_RD);
    }
    public void T000_READ_BPTDDIC() throws IOException,SQLException,Exception {
        BPTDDIC_RD = new DBParm();
        BPTDDIC_RD.TableName = "BPTDDIC";
        IBS.READ(SCCGWA, BPRDDIC, BPTDDIC_RD);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        WS_TS_FLG = "C";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        WS_TS_FLG = "D";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM, true);
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.SEND_FLG);
        IBS.init(SCCGWA, BPCGCFEE);
        WS_TS_FLG = "B";
        S000_TRC_WS_TS();
        if (pgmRtn) return;
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
